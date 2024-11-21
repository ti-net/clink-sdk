//
//  ICRecordManager.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/16.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICRecordManager.h"
#import "ICFileTool.h"
#import <UIKit/UIKit.h>
#import "TIMConstants.h"
#import "VoiceConverter.h"
#import "kitUtils.h"

#define kChildPath @"Chat/File"
#define kAmrType @"amr"

#define kMinRecordDuration 1.0

@interface ICRecordManager()<UIAlertViewDelegate>
{
    NSDate *_startDate;
    NSDate *_endDate;
    void (^recordFinish)(NSString *recordPath);
}

@property (nonatomic, strong) NSDictionary *recordSetting;

@property (nonatomic, assign) AVAuthorizationStatus microPhoneStatus;

@end

@implementation ICRecordManager

+ (id)shareManager
{
    static id _instance ;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _instance = [[self alloc] init];
    });
    return _instance;
}


// here also need to limit recording time
- (void)startRecordingWithFileName:(NSString *)fileName completion:(void(^)(NSError *error))completion
{
    NSError *error = nil;
    if (![[ICRecordManager shareManager] canRecord]) {
//        [MBProgressHUD showError:@"请在手机设置中打开录音权限" toView:[UIApplication sharedApplication].keyWindow];
        if (self.microPhoneStatus == AVAuthorizationStatusNotDetermined) {
            return;
        }
        
        if (completion) {
            error = [NSError errorWithDomain:NSLocalizedString(@"error", @"没权限") code:122 userInfo:nil];
            completion(error);
        }
        return;
    }
    if ([_recorder isRecording]) {
        [_recorder stop];
        [self cancelCurrentRecording];
        return;
    } else {
        NSString *wavFilePath = [self recorderPathWithFileName:fileName];
        NSURL *wavUrl = [[NSURL alloc] initFileURLWithPath:wavFilePath];
        // 在实例化AVAudioRecorder之前，先开启会话,否则真机上录制失败
        AVAudioSession *session = [AVAudioSession sharedInstance];
        NSError *setCategoryError = nil;
        [session setCategory:AVAudioSessionCategoryPlayAndRecord error:&setCategoryError];
        if(setCategoryError){
            TIMKitLog(@"%@", [setCategoryError description]);
        }
        
        _recorder = [[AVAudioRecorder alloc] initWithURL:wavUrl settings:self.recordSetting error:&error];
        _recorder.meteringEnabled = YES;
        if (!_recorder || error) {
            _recorder = nil;
            if (completion) {
                error = [NSError errorWithDomain:NSLocalizedString(@"error.initRecorderFail", @"Failed to initialize AVAudioRecorder") code:123 userInfo:nil];
                completion(error);
            }
            return;
        }
        _startDate = [NSDate date];
        _recorder.meteringEnabled = YES;
        _recorder.delegate = self;
        [_recorder record];
        if (completion) {
            completion(error);
        }
    }
}

//- (BOOL)canRecord
//{
//    __block BOOL bCanRecord = YES;
//    if ([[[UIDevice currentDevice] systemVersion] compare:@"7.0"] != NSOrderedAscending)
//    {
//        AVAudioSession *audioSession = [AVAudioSession sharedInstance];
//        if ([audioSession respondsToSelector:@selector(requestRecordPermission:)]) {
//            [audioSession performSelector:@selector(requestRecordPermission:) withObject:^(BOOL granted) {
//                bCanRecord = granted;
//            }];
//        }
//    }
//    return bCanRecord;
//}

- (BOOL)canRecord
{
    BOOL bCanRecord = YES;
    AVAuthorizationStatus microPhoneStatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeAudio];
    self.microPhoneStatus = microPhoneStatus;
    switch (microPhoneStatus) {
        case AVAuthorizationStatusDenied:
        case AVAuthorizationStatusRestricted:
        {
            // 被拒绝
            bCanRecord = NO;
        }
            break;
        case AVAuthorizationStatusNotDetermined:
        {
            // 没弹窗
            [self requestMicroPhoneAuth];
            bCanRecord = NO;
        }
            break;
        case AVAuthorizationStatusAuthorized:
        {
            // 有授权
        }
            break;

        default:
            break;
    }
    return bCanRecord;
}

-(BOOL) requestMicroPhoneAuth
{
    __block BOOL bCanRecord = YES;
    [AVCaptureDevice requestAccessForMediaType:AVMediaTypeAudio completionHandler:^(BOOL granted) {
            bCanRecord = granted;
    }];
    return bCanRecord;
}



// here also need format conversion
- (void)stopRecordingWithCompletion:(void(^)(NSString *recordPath))completion
{
//    [self.recorder stop];
    _endDate = [NSDate date];
    if ([_recorder isRecording]) {
        if ([_endDate timeIntervalSinceDate:_startDate] < [self recordMinDuration]) {
            if (completion) {
                completion(shortRecord);
            }
            [self.recorder stop];
            [self cancelCurrentRecording];
            sleep(1.0);//a temporary method，let it sheep a minute,because recorder generated need time，to prevented clicked quickly situation
            //ICLog(@"record time duration is too short");
            return;
        }
        recordFinish = completion;
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
            [self.recorder stop];
            //ICLog(@"record time duration :%f",[_endDate timeIntervalSinceDate:_startDate]);
        });
    }
}

// 录音文件主路径
- (NSString *)recorderMainPath
{
    return [ICFileTool createPathWithChildPath:kChildPath];
}

- (NSString *)recorderPathWithFileName:(NSString *)fileName
{
    NSString *path = [self recorderMainPath];
    return [path stringByAppendingPathComponent:[NSString stringWithFormat:@"%@%@",fileName,kRecoderType]];
}


- (void)cancelCurrentRecording
{
    _recorder.delegate = nil;
    if (_recorder.recording) {
        [_recorder stop];
    }
    _recorder = nil;
    recordFinish = nil;
}

- (NSTimeInterval)recordMinDuration
{
    return kMinRecordDuration;
}

// 移除音频
- (void)removeCurrentRecordFile:(NSString *)fileName
{
    [self cancelCurrentRecording];
    NSFileManager *fileManager = [NSFileManager defaultManager];
    NSString *path = [self recorderPathWithFileName:fileName];
    BOOL isDirExist = [fileManager fileExistsAtPath:path];
    if (isDirExist) {
        [fileManager removeItemAtPath:path error:nil];
    }
}


#pragma mark - Getter

- (NSDictionary *)recordSetting
{
    if (!_recordSetting) {
        _recordSetting = [[NSDictionary alloc] initWithObjectsAndKeys:
                          [NSNumber numberWithFloat:8000.0],AVSampleRateKey,
                          [NSNumber numberWithInt:kAudioFormatLinearPCM],AVFormatIDKey,
                          [NSNumber numberWithInt:16],AVLinearPCMBitDepthKey,
                          [NSNumber numberWithInt:1],AVNumberOfChannelsKey,
                          nil];
    }
    return _recordSetting;
}

#pragma mark - AVAudioRecorderDelegate

- (void)audioRecorderDidFinishRecording:(AVAudioRecorder *)recorder
                           successfully:(BOOL)flag
{
    NSString *recordPath = [[_recorder url] path];
    // 音频转换
    NSString *amrPath = [[recordPath stringByDeletingPathExtension] stringByAppendingPathExtension:kAmrType];
    [VoiceConverter ConvertWavToAmr:recordPath amrSavePath:amrPath];
    if (recordFinish) {
        if (!flag) {
            recordPath = nil;
        }
        recordFinish(recordPath);
    }
    _recorder = nil;
    recordFinish = nil;
}

- (void)audioRecorderEncodeErrorDidOccur:(AVAudioRecorder *)recorder
                                   error:(NSError *)error
{
    //ICLog(@"audioRecorderEncodeErrorDidOccur");
}


#pragma mark - Player

- (void)startPlayRecorder:(NSString *)recorderPath
{
//    [self.player stop];
//    self.player = nil;  // clear previous player

    AVAudioSession *audioSession = [AVAudioSession sharedInstance];
    NSError *err = nil;  // 加上这两句，否则声音会很小
    [audioSession setCategory :AVAudioSessionCategoryPlayback error:&err];
    self.player = [[AVAudioPlayer alloc] initWithContentsOfURL:[NSURL fileURLWithPath:recorderPath] error:&err];
    NSLog(@"%@",err);
    self.player.numberOfLoops = 0;
    [self.player prepareToPlay];
    self.player.delegate = self;
    [self.player play];
}

- (void)startPlayRecorderWithData:(NSData *)data
{
//    [self.player stop];
//    self.player = nil;  // clear previous player

    AVAudioSession *audioSession = [AVAudioSession sharedInstance];
    NSError *err = nil;  // 加上这两句，否则声音会很小
    [audioSession setCategory :AVAudioSessionCategoryPlayback error:&err];
    self.player = [[AVAudioPlayer alloc] initWithData:data error:&err];
    NSLog(@"%@",err);
    self.player.numberOfLoops = 0;
    [self.player prepareToPlay];
    self.player.delegate = self;
    [self.player play];
}

- (void)stopPlayRecorder:(NSString *)recorderPath
{
    [self.player stop];
    self.player = nil;
    self.player.delegate = nil;
}

- (void)pause
{
    [self.player pause];
}

#pragma mark - AVAudioPlayerDelegate

- (void)audioPlayerDidFinishPlaying:(AVAudioPlayer *)player
                       successfully:(BOOL)flag
{
    [self.player stop];
    self.player = nil;
    if (self.playDelegate && [self.playDelegate respondsToSelector:@selector(voiceDidPlayFinished)]) {
        [self.playDelegate voiceDidPlayFinished];
    }
}





// 接收到的语音保存路径(文件以fileKey为名字)
- (NSString *)receiveVoicePathWithFileKey:(NSString *)fileKey
{
    return [self recorderPathWithFileName:fileKey];
}



// 获取语音时长
- (NSUInteger)durationWithVideo:(NSURL *)voiceUrl{
    
    NSDictionary *opts = [NSDictionary dictionaryWithObject:@(NO) forKey:AVURLAssetPreferPreciseDurationAndTimingKey];
    AVURLAsset *urlAsset = [AVURLAsset URLAssetWithURL:voiceUrl options:opts]; // 初始化视频媒体文件
    NSUInteger msecond = 0;
    msecond = urlAsset.duration.value * 1000/ urlAsset.duration.timescale; // 获取视频总时长,单位毫秒
    return msecond;
}






@end
