//
//  TOSCustomerChatVC.m
//  TIMClientKit
//
//  Created by apple on 2021/8/23.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "TOSCustomerChatVC.h"
#import "TIMChatBoxViewController.h"
#import "ICMessageConst.h"
#import "ICChatHearder.h"
#import "kitUtils.h"
#import "TOSRobotFormView.h"
#import "TIMMessageFrame.h"
#import "ICMessageHelper.h"
#import "TIMMessageModel.h"
#import "ICPhotoBrowserController.h"
#import "VoiceConverter.h"
#import "TIMConstants.h"
#import "TIMRefresh.h"
#import "UIView+SDExtension.h"
#import "UIImage+PureColorImage.h"
#import "UIImage+Extension.h"
#import "ICVideoBrowserController.h"
#import "UIImage+TIMGIF.h"
#import <TOSClientLib/TOSClientLib.h>
#import <TOSClientKit/TOSClientKit.h>
#import "TIMICMessage.h"
#import "YBImageBrowser.h"
#import "XZEmotion.h"
#import "TOSTextView.h"
#import "TIMMBProgressHUD.h"
#import "NSObject+TIMShowError.h"
#import "TIMICMessage.h"
#import "NSDictionary+TIMTool.h"
#import "NSArray+TIMTool.h"
#import <TOSClientLib/NSObject+TIMModel.h>
#import "TOSSendMultiMedia.h"
#import "STBaseWebViewController.h"
#import "WHToast.h"
#import "TOSWorkOrderWebView.h"
#import "TIMChatLeaveVC.h"
#import "ICChatMessageCommodityCardCell.h"
#import "ICChatMessageCommodityCardDetailsCell.h"
#import "ICChatMessageRichCell.h"
#import <TOSClientLib/TIMFileMessage.h>
#import "YYKit.h"
#import <TOSClientLib/TIMCommodityCardMessage.h>

#import "HWPanModal.h"
#import "TOSSatisfactionModel.h"
#import <TOSClientLib/TOSSessionInfoModel.h>

#import "TIMSYCacheFileViewController.h"
#import "TIMSYCacheFileManager.h"
#import "TIMSYCacheFileModel.h"
#import <TOSClientLib/CombinationMessage.h>
#import "OSEventSatisfactionCell.h"
#import <TOSClientLib/TOSGetInvestigationInfoModel.h>
#import "ICChatMessageTextTagCell.h"
#import <TOSClientLib/TOSMessageTextTagModel.h>
#import <TOSClientLib/OctoIMParamDefines.h>
#import <TOSClientLib/TOSSensitiveWordEventModel.h>
#import <TOSClientLib/TOSMessageSmallProgramModel.h>
#import "ICChatMessageSmallProgramCardCell.h"
#import "ICChatMessageLogisticsCardCell.h"
#import <TOSClientLib/TIMLogisticsCardMessage.h>
#import "ICChatMessageCustomDeafultCell.h"

#import <TOSClientLib/TOSMessageKnowledgeBaseModel.h>

#import "TOSChatCustomBaseTableViewCell.h"

#import "TOSSatisfactionPopupView.h"
#import "TOSInvestigationPopupView.h"
#import "ICChatMessageSatisfactionPopupCell.h"
#import "ICTools.h"
#import "TOSReloadTaskManager.h"
#import "TOSWorkOrderLeaveMessageVC.h"
#import "UIButton+TIMEnlargeEdge.h"
#import "TOSSatisfactionStatusModel.h"
#import <MobileCoreServices/MobileCoreServices.h>
#import "TOSCommodityCardView.h"

#define     MAX_SHOWTIME_MSG_SECOND     180
#define     MIN_UPDATE_MSG_SECOND       (0.5f)
#define     MAX_UPDATE_MSG_COUNT        5

/// 再次发送的消息类型
typedef NS_ENUM(NSInteger, offlineMessageType) {
    /// 说明没有需要再次发送离线消息
    offlineMessageTypeNormal            =   0,
    /// 文本消息
    offlineMessageTypeText             =   1,
    /// 语音消息
    offlineMessageTypeVoice             =   2,
    /// 图片消息
    offlineMessageTypeImage             =   3,
    /// 视频消息
    offlineMessageTypeVideo             =   4
};

@interface TOSCustomerChatVC ()<TIMChatBoxViewControllerDelegate,ICRecordManagerDelegate,
TIMClientReceiveMessageDelegate,UITableViewDelegate,UITableViewDataSource,BaseCellDelegate, UIScrollViewDelegate, YBImageBrowserDelegate, YBImageBrowserDataSource, UIDocumentPickerDelegate, ICChatMessageCommodityCardDetailsCellDelegate, TOSInvestigationPopupViewDelegate, TOSReloadTaskManagerDelegate, TOSCommodityCardViewDelegate> {
    CGRect _smallRect;
    CGRect _bigRect;
    CGRect _videoBtnRect;
    
    UIMenuItem * _saveMenuItem;
    UIMenuItem * _copyMenuItem;
    UIMenuItem * _deleteMenuItem;
    UIMenuItem * _forwardMenuItem;
    UIMenuItem * _recallMenuItem;
    NSIndexPath *_longIndexPath;
    
    BOOL   _isKeyBoardAppear;     // 键盘是否弹出来了
    NSInteger   _firstHasGetHistoryListFinish; //第一次拉取历史消息是否结束
}

@property (nonatomic, strong) dispatch_queue_t propertyQueue;

@property (nonatomic, strong) TOSReloadTaskManager *reloadTaskManager;
@property (nonatomic, strong) dispatch_block_t delayedSendHintEndBlock;

@property (nonatomic, strong) TIMChatBoxViewController *chatBoxVC;
@property (nonatomic, strong) UIView *chatCloseView;


@property (nonatomic, strong) TOSBaseTableView *tableView;
@property (nonatomic, strong) UITextView *textView;
/** tableView数据源 */
@property (nonatomic, strong) NSMutableArray *dataSource;
/** 准备展示的数据源 */
@property (nonatomic, strong) NSMutableArray *readyDisplayDataSource;
/** voice path */
@property (nonatomic, copy) NSString *voicePath;

/// AT用户的数据处理
@property (nonatomic, strong) NSMutableDictionary <NSString *,NSMutableArray *>*atDataDic;

@property (nonatomic, strong) UIImageView *currentVoiceIcon;
@property (nonatomic, strong) UIImageView *presentImageView;
@property (nonatomic, assign)  BOOL presentFlag;  // 是否model出控制器
@property (nonatomic, strong) NSTimer *timer;
@property (nonatomic, strong) ICVoiceHud *voiceHud;
@property (nonatomic, strong) NSString *  oldMessageId;
@property (nonatomic, strong) NSString *  imageOldMessageId;
@property (nonatomic, strong) NSString *  lastSendedMessageId; // 最近的已发送成功的消息
@property (nonatomic, copy) NSString *  curGroupType; // 当前用户在组中的type
@property (nonatomic, assign) long lastDataSourceCount;
@property (nonatomic, strong) NSMutableArray * needDelMessageFrames;  // 需要删除的message
@property (nonatomic, strong) NSMutableArray * needUpdateMessageFrames;  // 需要更新的message
@property (nonatomic, strong) NSMutableArray * needDelRichTextIds;  // 需要删除的富文本messageId

/**
 *  存放所有下载操作的队列
 */
@property (nonatomic,strong) NSOperationQueue* downloadQueue;

/**
 *  存放所有的下载操作（msgId是key，operation对象是value）
 */
@property (nonatomic,strong) NSMutableDictionary* downloadOperations;
/**
 *  存放所有下载完成的数据(包含文件、图片等)，用于缓存
 */
@property (nonatomic,strong) NSMutableDictionary* downloadFileDatas;

@property (nonatomic, strong) TIMMessageModel *recallMessageModel;
@property (nonatomic, strong) NSIndexPath *recallIndexPath;

/// 图片数据
@property (nonatomic, strong) NSMutableArray *imageDataSource;

@property (nonatomic, strong) YBImageBrowser *browser;

/// 正在请求图片类型的历史数据
@property (nonatomic, assign) BOOL isImageDataRequest;

/// 是最后一条图片类型的历史数据
@property (nonatomic, assign) BOOL isLastImageData;

/// 群聊的创建时间
@property (nonatomic,  assign) long long createTime;

//最后一条消息的时间，用于分页
@property (nonatomic,  strong) NSString* lastTime;
@property (nonatomic, strong) NSMutableArray *cellIdList;//记录只加载一次


@property (nonatomic, assign) NSInteger                pageIndex;
@property (nonatomic, assign)  BOOL isOpenSession;      // 是否已打开会话

/// 第一次进入页面
@property (nonatomic, assign)  BOOL firstViewDidAppear;

/// 当前会话是否断开
@property (nonatomic, assign) BOOL                isCloseSession;

/// 需要再次发送的消息类型
@property (nonatomic, assign) offlineMessageType                offlineType;

/// 需要再次发送的离线消息
@property (nonatomic, strong) TIMMessageFrame                * offlineNewMessage;

/// 需要再次发送的媒体数据
@property (nonatomic, strong) NSData                * offlineMediaData;

/// 重写结束会话的UI后，因为需要等到收到欢迎语后才能发送用户的消息，需要做个记录进行记录
@property (nonatomic, assign) BOOL                existedWelcome;


@property (nonatomic, strong) TOSSatisfactionPopupView *popupView;

/// 当前页面是否属于最顶端
@property (nonatomic, assign) BOOL                currentTop;

@property (nonatomic, weak) TOSRobotFormView *robotFormView;

@property (nonatomic, weak) TOSWorkOrderWebView *workOrderView;

@property (nonatomic, strong) dispatch_block_t    delayedResumeFlashBlock;

///  未完成快捷入口的UI展示前不进行Flash
@property (nonatomic, assign) BOOL                stopUIFlash;

/// 底部的商品卡片
@property (nonatomic, strong) TOSCommodityCardView                * bottomCardView;

@end

static const NSInteger kMaxImageDataRequest = 50;


@implementation TOSCustomerChatVC

- (void)sendFileMessageAction:(NSNotification *)notification{
    TIMSYCacheFileModel *model = [notification object];
    
    // 生成新的文件名称为uuid
    NSString *fileName = [[model.filePath lastPathComponent] stringByDeletingPathExtension];
    
    int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
    if (sizeLimitM < model.originalFileSize/1024/1024) {
        NSString * strShow = [NSString stringWithFormat:@"请发送小于%dM文件",sizeLimitM];
        [WHToast showMessage:strShow duration:2 finishHandler:^{
            
        }];
        return;
    }
    
    @WeakObj(self);
    [self.reloadTaskManager startTaskWithScroll:YES completion:^{
        @StrongObj(self);
        switch (model.fileType) {
            case TIMSYCacheFileTypeVideo: {
                
                
                UIImage *imageSrc = [UIImage videoFramerateWithPath:model.filePath];
                
                CGFloat fixelW = imageSrc.size.width;
                CGFloat fixelH = imageSrc.size.height;

                NSData *videoData = [NSData dataWithContentsOfFile:model.filePath];
                
                __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo
                                                                           originalType:@"video"
                                                                                content:@"[视频]"
                                                                                  extra:@""
                                                                             auditExtra:@""
                                                                                   path:model.filePath
                                                                                urlPath:nil
                                                                                   from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                     to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                                fileKey:[[NSUUID UUID] UUIDString]
                                                                           bigImgFileId:@""
                                                                          voiceDuration:[NSNumber numberWithInt:0]
                                                                                  msgId:@""
                                                                               sendTime:[self getNowTimestamp]
                                                                               showTime:[self p_needShowTime:[NSDate date]]
                                                                                picSize:CGSizeMake(fixelW, fixelH)
                                                                                picType:@""
                                                                               isSender:YES
                                                               receivedSenderByYourself:NO
                                                                                 status:TIMMessageStatus_Sending
                                                                             senderType:@(2)];
                // 创建本地消息
                [self addObject:messageF isSender:YES isHeaderInsert:NO];
                // 发送中
                [self messageSending:messageF];
                
                [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendVideoMessageWithVideoData:videoData success:^(NSString * _Nonnull messageId) {
                    
                    @StrongObj(self);
                    messageF.model.message.messageId = messageId;
                    [self updateOldMessageId:messageId];
                    [self messageSendSucced:messageF];
                    self.reloadTaskManager.isScroll = YES;
                    [self.reloadTaskManager startCountdown];
                } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                    
                    @StrongObj(self);
                    [self messageSendFailed:messageF];
                    self.reloadTaskManager.isScroll = YES;
                    [self.reloadTaskManager startCountdown];
                    
                    int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
                    NSString * strShow = [NSString stringWithFormat:@"文件过大，请发送小于%dM文件",sizeLimitM];
                    [WHToast showMessage:errorDes?:strShow duration:2 finishHandler:^{
                        
                    }];
                }];
            }
                break;
            case TIMSYCacheFileTypeAudio: {
                // 获取时长
                int voiceDuration = (int)[[ICRecordManager shareManager] durationWithVideo:[NSURL fileURLWithPath:model.filePath]];
                if (voiceDuration < 1000) {
                    // 小于一秒的显示1秒
                    voiceDuration = 1000;
                }
                
                
                NSData*voiceData = [NSData dataWithContentsOfFile:model.filePath];
                
                // Kit
                __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVoice
                                                                           originalType:@"voice"
                                                                                content:@"[语音]"
                                                                                  extra:@""
                                                                             auditExtra:@""
                                                                                   path:model.filePath
                                                                                urlPath:nil
                                                                                   from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                     to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                                fileKey:[[NSUUID UUID] UUIDString]
                                                                           bigImgFileId:@""
                                                                          voiceDuration:[NSNumber numberWithInt:voiceDuration]
                                                                                  msgId:@""
                                                                               sendTime:[self getNowTimestamp]
                                                                               showTime:[self p_needShowTime:[NSDate date]]
                                                                                picSize:CGSizeZero
                                                                                picType:@""
                                                                               isSender:YES
                                                               receivedSenderByYourself:NO
                                                                                 status:TIMMessageStatus_Sending
                                                                             senderType:@(2)];
                [self addObject:messageF isSender:YES isHeaderInsert:NO];
                // 发送中
                [self messageSending:messageF];
                
                [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVoiceMessageWithVoiceData:voiceData success:^(NSString * _Nonnull messageId) {
                    
                    @StrongObj(self);
                    messageF.model.message.messageId = messageId;
                    [self updateOldMessageId:messageId];
                    [self messageSendSucced:messageF];
                    self.reloadTaskManager.isScroll = YES;
                    [self.reloadTaskManager startCountdown];
                } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                    
                    @StrongObj(self);
                    [self messageSendFailed:messageF];
                    self.reloadTaskManager.isScroll = YES;
                    [self.reloadTaskManager startCountdown];
                }];
            }
                break;
            case TIMSYCacheFileTypeImage: {
                UIImage *image = [UIImage imageWithContentsOfFile:model.filePath];
                
                model.filePath = [[ICMediaManager sharedManager] saveImage:image msgId:[kitUtils getMsgUUID] picType:@"jpg"];
                CGFloat fixelW = image.size.width;
                CGFloat fixelH = image.size.height;
                
                NSData*uploadThumbFileData = UIImageJPEGRepresentation(image, 0.5);
                
                // Kit
                CGSize imageSize = CGSizeMake(fixelW, fixelH);
                __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic
                                                                           originalType:@"image"
                                                                                content:@"[图片]"
                                                                                  extra:@""
                                                                             auditExtra:@""
                                                                                   path:model.filePath
                                                                                urlPath:nil
                                                                                   from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                     to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                                fileKey:[[NSUUID UUID] UUIDString]
                                                                           bigImgFileId:@""
                                                                          voiceDuration:[NSNumber numberWithInt:0]
                                                                                  msgId:@""
                                                                               sendTime:[self getNowTimestamp]
                                                                               showTime:[self p_needShowTime:[NSDate date]]
                                                                                picSize:imageSize
                                                                                picType:@""
                                                                               isSender:YES
                                                               receivedSenderByYourself:NO
                                                                                 status:TIMMessageStatus_Sending
                                                                             senderType:@(2)];
                [self addObject:messageF isSender:YES isHeaderInsert:NO];
                // 发送中
                [self messageSending:messageF];
                
                [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendImageMessageWithImageData:uploadThumbFileData        success:^(NSString * _Nonnull messageId,NSString * _Nonnull fileKey) {
                    
                    @StrongObj(self);
                    messageF.model.message.messageId = messageId;
                    [self updateOldMessageId:messageId];
                    [self messageSendSucced:messageF];
                    self.reloadTaskManager.isScroll = YES;
                    [self.reloadTaskManager startCountdown];
                } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                    
                    @StrongObj(self);
                    [self messageSendFailed:messageF];
                    self.reloadTaskManager.isScroll = YES;
                    [self.reloadTaskManager startCountdown];
                }];
            }
                break;
            case TIMSYCacheFileTypeDocument:
            case TIMSYCacheFileTypeUnknow:
            default: {
                NSString * fileTypeStr = [kitUtils mimeTypeForFileAtPath:model.filePath];
                NSDictionary* fileMessageDic = @{
                    @"fileTypeDesc": @"文件",
                    @"fileType": fileTypeStr,
                    @"fileName": fileName
                };
                // Kit
                __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCustomFile
                                                                           originalType:@"file"
                                                                                content:[kitUtils DataTOjsonString:fileMessageDic]
                                                                                  extra:@""
                                                                             auditExtra:@""
                                                                                   path:model.filePath
                                                                                urlPath:model.filePath
                                                                                   from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                     to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                                fileKey:[[NSUUID UUID] UUIDString]
                                                                           bigImgFileId:@""
                                                                          voiceDuration:[NSNumber numberWithInt:0]
                                                                                  msgId:@""
                                                                               sendTime:[self getNowTimestamp]
                                                                               showTime:[self p_needShowTime:[NSDate date]]
                                                                                picSize:CGSizeMake(150, 60)
                                                                                picType:@""
                                                                               isSender:YES
                                                               receivedSenderByYourself:NO
                                                                                 status:TIMMessageStatus_Sending
                                                                             senderType:@(2)];
                // 创建本地消息
                [self addObject:messageF isSender:YES isHeaderInsert:NO];
                // 发送中
                [self messageSending:messageF];
                
                NSData *fileData = [NSData dataWithContentsOfFile:model.filePath];
                
                [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendFileMessageWithFileData:fileData fileType:fileTypeStr fileName:fileName success:^(NSString * _Nonnull messageId) {
                    
                    @StrongObj(self);
                    messageF.model.message.messageId = messageId;
                    [self updateOldMessageId:messageId];
                    [self messageSendSucced:messageF];
                    self.reloadTaskManager.isScroll = YES;
                    [self.reloadTaskManager startCountdown];
                } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                    
                    @StrongObj(self);
                    [self messageSendFailed:messageF];
                    self.reloadTaskManager.isScroll = YES;
                    [self.reloadTaskManager startCountdown];
                    int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
                    NSString * strShow = [NSString stringWithFormat:@"文件过大，请发送小于%dM文件",sizeLimitM];
                    [WHToast showMessage:errorDes?:strShow duration:2 finishHandler:^{
                        
                    }];
                }];
            }
                break;
        }
    }];
}

#pragma mark UIDocumentPickerDelegate
- (void)documentPicker:(UIDocumentPickerViewController *)controller didPickDocumentsAtURLs:(NSArray <NSURL *>*)urls{
    //获取授权
    NSLog(@"urls.firstObject === %@",urls.firstObject);
    //通过文件协调工具来得到新的文件地址，以此得到文件保护功能
    NSFileCoordinator *fileCoordinator = [[NSFileCoordinator alloc] init];
    NSError *error;
    [urls.firstObject startAccessingSecurityScopedResource];
    
    [fileCoordinator coordinateReadingItemAtURL:urls.firstObject options:0 error:&error byAccessor:^(NSURL *newURL) {
        //读取文件
        NSError *error = nil;
        NSLog(@"newURL ==== %@",newURL);
        NSData *fileData = [NSData dataWithContentsOfURL:newURL options:NSDataReadingMappedIfSafe error:&error];
        if (error) {
            //读取出错
        } else {
            
            NSError *fileTypeError = nil;
            NSString *fileTypeStr = nil;
            TIMSYCacheFileType fileType = TIMSYCacheFileTypeUnknow;

            [newURL getResourceValue:&fileTypeStr forKey:NSURLTypeIdentifierKey error:&fileTypeError];
            
            if (fileTypeError) {
                NSLog(@"Error getting file type: %@", error);
            } else {
                // 判断文件类型
                if (UTTypeConformsTo((__bridge CFStringRef)fileTypeStr, kUTTypeImage)) {
                    fileType = TIMSYCacheFileTypeImage;
                } else if (UTTypeConformsTo((__bridge CFStringRef)fileTypeStr, kUTTypeMovie)) {
                    fileType = TIMSYCacheFileTypeVideo;
                } else if (UTTypeConformsTo((__bridge CFStringRef)fileTypeStr, kUTTypeAudio)) {
                    fileType = TIMSYCacheFileTypeAudio;
                } else {
                    fileType = TIMSYCacheFileTypeDocument;
                }
            }
            
            
            NSNumber *fileSizeValue = 0;
            NSError *fileSizeError = nil;
            [newURL getResourceValue:&fileSizeValue forKey:NSURLFileSizeKey error:&fileSizeError];
            
            if (!fileSizeError) {
                NSLog(@"File size: %@ bytes", fileSizeValue);
            } else {
                NSLog(@"Error retrieving file size: %@", fileSizeError.localizedDescription);
            }
            
            
            // 生成新的文件名称为uuid
            NSString *fileName = [kitUtils getMsgUUID];
            __block NSString *newURLStr = [newURL path];
            
            int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
            if (sizeLimitM < fileSizeValue.floatValue/1024/1024) {
                NSString * strShow = [NSString stringWithFormat:@"请发送小于%dM文件",sizeLimitM];
                [WHToast showMessage:strShow duration:2 finishHandler:^{
                    
                }];
                return;
            }
            
            @WeakObj(self);
            [self.reloadTaskManager startTaskWithScroll:YES completion:^{
                @StrongObj(self);
                switch (fileType) {
                    case TIMSYCacheFileTypeVideo: {
                        
                        UIImage *imageSrc = [UIImage videoFramerateWithPath:newURLStr];
                        NSString *videoUrl = [self createVideoFilePath];
                        
                        [self copyFileFromDirectory:newURLStr toDirectory:videoUrl];
                        
                        CGFloat fixelW = imageSrc.size.width;
                        CGFloat fixelH = imageSrc.size.height;
                        
                        __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo
                                                                                   originalType:@"video"
                                                                                        content:@"[视频]"
                                                                                          extra:@""
                                                                                     auditExtra:@""
                                                                                           path:videoUrl
                                                                                        urlPath:nil
                                                                                           from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                             to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                                        fileKey:[[NSUUID UUID] UUIDString]
                                                                                   bigImgFileId:@""
                                                                                  voiceDuration:[NSNumber numberWithInt:0]
                                                                                          msgId:@""
                                                                                       sendTime:[self getNowTimestamp]
                                                                                       showTime:[self p_needShowTime:[NSDate date]]
                                                                                        picSize:CGSizeMake(fixelW, fixelH)
                                                                                        picType:@""
                                                                                       isSender:YES
                                                                       receivedSenderByYourself:NO
                                                                                         status:TIMMessageStatus_Sending
                                                                                     senderType:@(2)];
                        // 创建本地消息
                        [self addObject:messageF isSender:YES isHeaderInsert:NO];
                        // 发送中
                        [self messageSending:messageF];
                        
                        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendVideoMessageWithVideoData:fileData success:^(NSString * _Nonnull messageId) {
                            
                            @StrongObj(self);
                            messageF.model.message.messageId = messageId;
                            [self updateOldMessageId:messageId];
                            [self messageSendSucced:messageF];
                            self.reloadTaskManager.isScroll = YES;
                            [self.reloadTaskManager startCountdown];
                        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                            
                            @StrongObj(self);
                            [self messageSendFailed:messageF];
                            self.reloadTaskManager.isScroll = YES;
                            [self.reloadTaskManager startCountdown];
                            
                            int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
                            NSString * strShow = [NSString stringWithFormat:@"文件过大，请发送小于%dM文件",sizeLimitM];
                            [WHToast showMessage:errorDes?:strShow duration:2 finishHandler:^{
                                
                            }];
                        }];
                    }
                        break;
                    case TIMSYCacheFileTypeAudio: {
                        
                        NSString *audioUrl = [self recorderPathWithFileName:[self currentRecordFileName]];
                        
                        [self copyFileFromDirectory:newURLStr toDirectory:audioUrl];
                        
                        // 获取时长
                        int voiceDuration = (int)[[ICRecordManager shareManager] durationWithVideo:[NSURL fileURLWithPath:newURLStr]];
                        if (voiceDuration < 1000) {
                            // 小于一秒的显示1秒
                            voiceDuration = 1000;
                        }
                        
                        // Kit
                        __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVoice
                                                                                   originalType:@"voice"
                                                                                        content:@"[语音]"
                                                                                          extra:@""
                                                                                     auditExtra:@""
                                                                                           path:audioUrl
                                                                                        urlPath:nil
                                                                                           from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                             to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                                        fileKey:[[NSUUID UUID] UUIDString]
                                                                                   bigImgFileId:@""
                                                                                  voiceDuration:[NSNumber numberWithInt:voiceDuration]
                                                                                          msgId:@""
                                                                                       sendTime:[self getNowTimestamp]
                                                                                       showTime:[self p_needShowTime:[NSDate date]]
                                                                                        picSize:CGSizeZero
                                                                                        picType:@""
                                                                                       isSender:YES
                                                                       receivedSenderByYourself:NO
                                                                                         status:TIMMessageStatus_Sending
                                                                                     senderType:@(2)];
                        [self addObject:messageF isSender:YES isHeaderInsert:NO];
                        // 发送中
                        [self messageSending:messageF];
                        
                        [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVoiceMessageWithVoiceData:fileData success:^(NSString * _Nonnull messageId) {
                            
                            @StrongObj(self);
                            messageF.model.message.messageId = messageId;
                            [self updateOldMessageId:messageId];
                            [self messageSendSucced:messageF];
                            self.reloadTaskManager.isScroll = YES;
                            [self.reloadTaskManager startCountdown];
                        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                            
                            @StrongObj(self);
                            [self messageSendFailed:messageF];
                            self.reloadTaskManager.isScroll = YES;
                            [self.reloadTaskManager startCountdown];
                        }];
                    }
                        break;
                    case TIMSYCacheFileTypeImage: {
                        
//                        [[ICMediaManager sharedManager] saveImage:photo1 msgId:small picType:[self typeForImageData:imageData]];
                        
                        
                        UIImage *image = [UIImage imageWithContentsOfFile:newURLStr];
                        
                        NSLog(@"newURLStr === %@, image ==== %@",newURLStr,image);
                        newURLStr = [[ICMediaManager sharedManager] saveImage:image msgId:[kitUtils getMsgUUID] picType:@"jpg"];
                        CGFloat fixelW = image.size.width;
                        CGFloat fixelH = image.size.height;
                        
                        NSLog(@"newURLStr === %@",newURLStr);
                        
                        NSData*uploadThumbFileData = UIImageJPEGRepresentation(image, 0.5);
                        
                        // Kit
                        CGSize imageSize = CGSizeMake(fixelW, fixelH);
                        __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic
                                                                                   originalType:@"image"
                                                                                        content:@"[图片]"
                                                                                          extra:@""
                                                                                     auditExtra:@""
                                                                                           path:newURLStr
                                                                                        urlPath:nil
                                                                                           from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                             to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                                        fileKey:[[NSUUID UUID] UUIDString]
                                                                                   bigImgFileId:@""
                                                                                  voiceDuration:[NSNumber numberWithInt:0]
                                                                                          msgId:@""
                                                                                       sendTime:[self getNowTimestamp]
                                                                                       showTime:[self p_needShowTime:[NSDate date]]
                                                                                        picSize:imageSize
                                                                                        picType:@""
                                                                                       isSender:YES
                                                                       receivedSenderByYourself:NO
                                                                                         status:TIMMessageStatus_Sending
                                                                                     senderType:@(2)];
                        [self addObject:messageF isSender:YES isHeaderInsert:NO];
                        // 发送中
                        [self messageSending:messageF];
                        
                        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendImageMessageWithImageData:uploadThumbFileData        success:^(NSString * _Nonnull messageId,NSString * _Nonnull fileKey) {
                            
                            @StrongObj(self);
                            messageF.model.message.messageId = messageId;
                            [self updateOldMessageId:messageId];
                            [self messageSendSucced:messageF];
                            self.reloadTaskManager.isScroll = YES;
                            [self.reloadTaskManager startCountdown];
                        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                            
                            @StrongObj(self);
                            [self messageSendFailed:messageF];
                            self.reloadTaskManager.isScroll = YES;
                            [self.reloadTaskManager startCountdown];
                        }];
                    }
                        break;
                    case TIMSYCacheFileTypeDocument:
                    case TIMSYCacheFileTypeUnknow:
                    default: {
                        NSString * fileTypeStr = [kitUtils mimeTypeForFileAtPath:newURLStr];
                        NSString *fileName = [newURL lastPathComponent];
                        
                        NSString *fileNameString;
                        if ([fileName containsString:[newURLStr pathExtension]]) {
                            fileNameString = [NSString stringWithFormat:@"%@",fileName];
                        } else {
                            fileNameString = [NSString stringWithFormat:@"%@.%@",fileName,[newURLStr pathExtension]];
                        }
                        NSDictionary* fileMessageDic = @{
                            @"fileTypeDesc": @"文件",
                            @"fileType": fileTypeStr,
                            @"fileName": fileNameString
                        };
                        
                        NSString *fileUrl = [self filePathWithFileName:[NSString stringWithFormat:@"timLocalFileApp_%@.%@",[self currentRecordFileName],[newURLStr pathExtension]]];
                        
                        [self copyFileFromDirectory:newURLStr toDirectory:fileUrl];
                        NSLog(@"fileUrl ==== %@",fileUrl);
                        // Kit
                        __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCustomFile
                                                                                   originalType:@"file"
                                                                                        content:[kitUtils DataTOjsonString:fileMessageDic]
                                                                                          extra:@""
                                                                                     auditExtra:@""
                                                                                           path:fileUrl
                                                                                        urlPath:fileUrl
                                                                                           from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                             to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                                        fileKey:[[NSUUID UUID] UUIDString]
                                                                                   bigImgFileId:@""
                                                                                  voiceDuration:[NSNumber numberWithInt:0]
                                                                                          msgId:@""
                                                                                       sendTime:[self getNowTimestamp]
                                                                                       showTime:[self p_needShowTime:[NSDate date]]
                                                                                        picSize:CGSizeMake(150, 60)
                                                                                        picType:@""
                                                                                       isSender:YES
                                                                       receivedSenderByYourself:NO
                                                                                         status:TIMMessageStatus_Sending
                                                                                     senderType:@(2)];
                        // 创建本地消息
                        [self addObject:messageF isSender:YES isHeaderInsert:NO];
                        // 发送中
                        [self messageSending:messageF];
                        
                        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendFileMessageWithFileData:fileData fileType:fileTypeStr fileName:fileNameString success:^(NSString * _Nonnull messageId) {
                            
                            @StrongObj(self);
                            messageF.model.message.messageId = messageId;
                            [self updateOldMessageId:messageId];
                            [self messageSendSucced:messageF];
                            self.reloadTaskManager.isScroll = YES;
                            [self.reloadTaskManager startCountdown];
                        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                            
                            @StrongObj(self);
                            [self messageSendFailed:messageF];
                            self.reloadTaskManager.isScroll = YES;
                            [self.reloadTaskManager startCountdown];
                            int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
                            NSString * strShow = [NSString stringWithFormat:@"文件过大，请发送小于%dM文件",sizeLimitM];
                            [WHToast showMessage:errorDes?:strShow duration:2 finishHandler:^{
                                
                            }];
                        }];
                    }
                        break;
                }
            }];
        }
    }];
    [urls.firstObject stopAccessingSecurityScopedResource];
}

// 将文件复制到应用沙盒中
- (void)copyFileToAppSandbox:(NSURL *)fileURL {
    NSURL *destinationURL = [[self applicationDocumentsDirectory] URLByAppendingPathComponent:fileURL.lastPathComponent];
    
    NSError *error;
    [[NSFileManager defaultManager] copyItemAtURL:fileURL toURL:destinationURL error:&error];
    
    if (error) {
        NSLog(@"Error copying file: %@", error.localizedDescription);
    } else {
        NSLog(@"File copied to: %@", destinationURL.path);
    }
}

// 获取应用的Documents目录
- (NSURL *)applicationDocumentsDirectory {
    return [[[NSFileManager defaultManager] URLsForDirectory:NSDocumentDirectory inDomains:NSUserDomainMask] firstObject];
}

-(void)openSession{
    // 打开一个新的会话
    NSLog(@"链接状态 ---- 打开新会话");
    NSString * moreInfo = [[OnlineDataSave shareOnlineDataSave] getConnectAdParams];
    [[OnlineRequestManager sharedCustomerManager]visitorReadyWithDict:[kitUtils dictionaryWithJsonString:moreInfo]
                                                              success:^(TOSSessionInfoModel * _Nonnull sessModel) {
        NSLog(@"viewload sessModel status:%d startTime:%lld mainUniqueId:%@ enterpriseId:%@ visitorId:%@",
              [sessModel.status intValue],
              [sessModel.startTime longLongValue],
              sessModel.mainUniqueId,
              sessModel.enterpriseId,
              sessModel.visitorId);
        [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[sessModel yy_modelToJSONString]];
        self.isOpenSession = YES;
        
//        [self getAppSetting];
        
        [self loadData];
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
    }];
}

- (void)hiddenNavigationBarSeparatorLine {
    if ([self.navigationController.navigationBar respondsToSelector:@selector(setBackgroundImage:forBarMetrics:)]) {
        NSArray *list=self.navigationController.navigationBar.subviews;
        for (id obj in list) {
            if ([obj isKindOfClass:[UIView class]]) {
                UIView *imageView=(UIView *)obj;
                NSArray *list2=imageView.subviews;
                for (id obj2 in list2) {
                    if ([obj2 isKindOfClass:[UIImageView class]]) {
                        UIImageView *imageView2=(UIImageView *)obj2;
                        imageView2.hidden = YES;
                    }
                }
            }
        }
    }
}

// 进入前台
- (void)willEnterBecomeActive {
    if (self.currentTop) {
        //发送已读事件
        [[OnlineRequestManager sharedCustomerManager] sessionInfoReadWithMainUniqueId:[[OnlineDataSave shareOnlineDataSave] getMainUniqueId]];
    }
    
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.propertyQueue = dispatch_queue_create("com.example.propertyQueue", DISPATCH_QUEUE_SERIAL);
    
    self.delayedSendHintEndBlock = nil;
    
    self.reloadTaskManager = [[TOSReloadTaskManager alloc] init];
    self.reloadTaskManager.delegate = self;
    
    if ([TOSKitCustomInfo shareCustomInfo].titleName) {
        self.titleName = [TOSKitCustomInfo shareCustomInfo].titleName;
    }

    if ([TOSKitCustomInfo shareCustomInfo].appName) {
        self.appName = [TOSKitCustomInfo shareCustomInfo].appName;
    }

    if ([TOSKitCustomInfo shareCustomInfo].quickEntryAllItems) {
        self.quickEntryAllItems = [TOSKitCustomInfo shareCustomInfo].quickEntryAllItems;
    }

    if ([TOSKitCustomInfo shareCustomInfo].commodityCardOption) {
        self.commodityCardOption = [TOSKitCustomInfo shareCustomInfo].commodityCardOption;
    }

    @WeakObj(self);
    [[OnlineRequestManager sharedCustomerManager] getInvestigationInfoSuccess:^{
        @StrongObj(self);
//        dispatch_async(dispatch_get_main_queue(), ^{
//            @StrongObj(self);
//            [self.tableView reloadData];
//        });
        [self.reloadTaskManager startTaskWithScroll:NO completion:^{
            
        }];
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        
    }];
    
    self.isOpenSession = NO;
    [self chatStatusChanged:TinetChatStatusTypeOutline];
    
    NSLog(@"进入会话时链接状态 = %d",[[TOSClientKit sharedTOSKit].mqttConnected boolValue]);
    if ([[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
        NSLog(@"网络已连接状态");
        [self openSession];
    }
    
    self.pageIndex = 0;
    lastDateInterval = 0;
    if (@available(iOS 13.0, *)) {
        self.overrideUserInterfaceStyle = UIUserInterfaceStyleLight;    //关闭暗黑模式
    }
    self.isImageDataRequest = NO;
    self.isLastImageData = NO;
    self.view.backgroundColor = [UIColor whiteColor];
    
    self.title = self.titleName?:@"";
    
    [[OnlineDataSave shareOnlineDataSave] saveCustomTitle:self.title];
    self.oldMessageId = @"";
    self.lastSendedMessageId = @"";

    self.createTime = 0;

    [self setupUI];
    [self setupChatCloseView];
    [self registerCell];
    // 避免回收通知失败
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(routerEventNotification:) name:kTIMRouterEventNotification object:nil];
    /// 有消息发送底部吸底的商品卡片UI移除
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(TOSCommodityCardViewCloseTouch) name:kTIMMessageSendChatUIFromLibNotification object:nil];
    // kTIMMessageSendChatMessageUIFromLibNotification
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(sendMessageCloseCardView) name:kTIMMessageSendChatMessageUIFromLibNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(willEnterBecomeActive) name:UIApplicationDidBecomeActiveNotification object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(messageReceivedNotification:) name:kTIMMessageReceivedNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(messageRecalledNotification:) name:kTIMMessageRecalledNotification object:nil];
    // 增加接收刷新界面的通知
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(messageSendedUpdateUINotification:) name:kTIMMessageUpdateChatUINotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(messageSendedUpdateUIFromLibNotification:) name:kTIMMessageUpdateChatUIFromLibNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(recalledMessageAgainEditNotification:) name:kTIMRecalledMessageAgainEditNotification object:nil];
    
//    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(updateUnreadMessageNotification:) name:kTIMUpdateUnreadMessageNotification object:nil];

    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(sendMultiMediaImage:) name:KTIMSendMultiMediaImageNotification object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(updateConnectStatus:) name:kTIMUpdateConnectStatusNotification object:nil];
    
    
    //退出排队
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(leaveQueueAction:) name:OSLeaveQueueNotification object:nil];
        
    //发送文件消息
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(sendFileMessageAction:) name:kSendFileMessageNotification object:nil];
    
    //拉取历史消息
    [self firstGetHistoryMessage];

    //发送访客上线事件
    [self sendChatOnline];
    
    NSString *inputText = [[OnlineDataSave shareOnlineDataSave] getUserInputText];
    
    //停止UI刷新变量设置
    self.stopUIFlash = NO;
    self.delayedResumeFlashBlock = nil;

    [self.chatBoxVC.chatBox.textView transalteStringEmoticonAttributedWithString:inputText];
    
    UIButton * backBtn = [[UIButton alloc] initWithFrame:(CGRectMake(0, 0, 60, 40))];
    UIImage * backimage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/navi_back", FRAMEWORKS_BUNDLE_PATH]];
    [backBtn setImage:[backimage imageByTintColor:[UIColor blackColor]] forState:(UIControlStateNormal)];
    [backBtn setTitle:@"返回" forState:(UIControlStateNormal)];
    [backBtn setTitleColor:[UIColor blackColor] forState:(UIControlStateNormal)];
    [backBtn addTarget:self action:@selector(investigationAlert) forControlEvents:(UIControlEventTouchUpInside)];
    [ICTools layoutButton:backBtn WithEdgeInsetsStyle:(TeilButtonEdgeInsetsStyleLeft) imageTitleSpace:2];
    self.navigationItem.leftBarButtonItem = [[UIBarButtonItem alloc] initWithCustomView:backBtn];
}

-(void)firstGetHistoryMessage{
    self.firstHasGetHistoryListFinish = 1;
    NSInteger timestamp = [kitUtils getNowTimestampWithSec].integerValue;
    NSString *lastTime = [NSString stringWithFormat:@"%ld",(long)timestamp + (long)1];
    self.lastTime = lastTime;
    [self getChatRecrodListWithLastTime:lastTime];
}


-(void)loadMore{
    [self getChatRecrodListWithLastTime:self.lastTime];
}

-(void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:animated];
    
    [self.chatBoxVC.chatBox addNotification];
    self.currentTop = YES;
    
    if (!self.firstViewDidAppear) {
        [self.chatBoxVC.chatBox switchTextEditing];
        self.reloadTaskManager.isScroll = NO;
        [self.reloadTaskManager startCountdown];
        self.firstViewDidAppear = YES;
        
    }
    else {
        /// 如果页面回来时，当前会话处于开启状态，需要再次显示输入区域。
        if (self.isOpenSession) {
            self.chatBoxVC.view.hidden = NO;
        }
    }
    /// 当页面从栈底回到栈顶时，需要会话发送已读事件。
    [[OnlineRequestManager sharedCustomerManager] sessionInfoReadWithMainUniqueId:[[OnlineDataSave shareOnlineDataSave] getMainUniqueId]];
}

-(void)viewWillDisappear:(BOOL)animated{
    [self.chatBoxVC.chatBox removeNotification];
    /// 清空最后一条消息/未读消息数
    [[NSNotificationCenter defaultCenter] postNotificationName:kTIMMessageClearUnReadCountNotification object:nil];
    
    NSString *inputText = [self.chatBoxVC.chatBox transalteEmoticonAttributedString:self.chatBoxVC.chatBox.textView.attributedText];
    
    [[OnlineDataSave shareOnlineDataSave] saveUserInputText:inputText];
}

- (void)viewDidDisappear:(BOOL)animated {
    [super viewDidDisappear:animated];
    self.currentTop = NO;
    [self timerInvalue];
}

- (void)setupUI
{
    self.view.backgroundColor = TOSColor(240, 237, 237);
    // 注意添加顺序
    self.chatBoxVC.isCurChatGroup = NO;
    [self addChildViewController:self.chatBoxVC];
    
    [self.view addSubview:self.chatBoxVC.view];
    [self.view addSubview:self.tableView];
    
    [self.view bringSubviewToFront:self.chatBoxVC.view];


    self.tableView.backgroundColor = TOSColor(240, 237, 237);
    /// 快捷入口的高度
    CGFloat inputChatBarHeight = self.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
    if (self.commodityCardOption) {
        CGFloat tableViewHeight = APP_Frame_Height-[TOSKitCustomInfo shareCustomInfo].chatBox_Height-kNavTop-kBottomBarHeight-inputChatBarHeight-146.0;
        self.tableView.frame = CGRectMake(0, 0, self.view.tos_width, tableViewHeight);
        
    }
    else {
        self.tableView.frame = CGRectMake(0, 0, self.view.tos_width, APP_Frame_Height-[TOSKitCustomInfo shareCustomInfo].chatBox_Height-kNavTop-kBottomBarHeight-inputChatBarHeight);
    }
}

- (void)updateSessionWindowQuickEntrys:(NSArray <TOSQuickEntryModel *>*)quickEntryAllItems {
    
    __weak typeof(self) weakself = self;
    dispatch_async(dispatch_get_main_queue(), ^{
        __strong typeof(weakself) strongself = weakself;
        strongself.chatBoxVC.chatBox.status = ICChatBoxStatusShowKeyboard;
        [strongself.chatBoxVC resignFirstResponder];
        [strongself.view endEditing:YES];
        
        
        self->_quickEntryAllItems = quickEntryAllItems;
        
        CGFloat inputChatBarHeight = strongself.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
        ///
        CGFloat inputTextHeight = [TOSKitCustomInfo shareCustomInfo].chatBox_Height;
        /// 判断页面是否有上次没有发送出去的文本
        if (strongself.chatBoxVC.chatBox.textView.text.length) {
            inputTextHeight = strongself.chatBoxVC.chatBox.tosCF_height;
        }
        
        if (strongself.commodityCardOption && strongself.bottomCardView != nil) {
            /// 表格的高度
            CGFloat tableViewHeight = APP_Frame_Height-inputTextHeight-kNavTop-kBottomBarHeight-inputChatBarHeight-146.0;
            
            strongself.tableView.frame = CGRectMake(0, 0, strongself.view.tos_width, tableViewHeight);
            strongself.bottomCardView.top_sd = strongself.tableView.tos_height;
            [strongself.chatBoxVC.view setFrame:CGRectMake(0, strongself.bottomCardView.tos_bottom, App_Frame_Width, APP_Frame_Height)];
            NSLog(@"if self.chatBoxVC.view = %@,   inputChatBarHeight = %f,   chatBox.tosCF_height = %f",NSStringFromCGRect(strongself.chatBoxVC.view.frame),inputChatBarHeight,strongself.chatBoxVC.chatBox.tosCF_height);
        }
        else {
            /// 表格的高度
            CGFloat tableViewHeight = APP_Frame_Height-inputTextHeight-kNavTop-kBottomBarHeight-inputChatBarHeight;
            
            strongself.tableView.frame = CGRectMake(0, 0, strongself.view.tos_width, tableViewHeight);
            
            [strongself.chatBoxVC.view setFrame:CGRectMake(0,strongself.tableView.tos_bottom, App_Frame_Width, APP_Frame_Height)];
            
            NSLog(@"self.chatBoxVC.view = %@,   inputChatBarHeight = %f,   chatBox.tosCF_height = %f",NSStringFromCGRect(strongself.chatBoxVC.view.frame),inputChatBarHeight,strongself.chatBoxVC.chatBox.tosCF_height);
        }
        
//        CGFloat chatBoxHeight = strongself.chatBoxVC.chatBox.tosCF_height - (strongself.chatBoxVC.barItemArray.count > 0 ? CHATBOX_BAR_HEIGHT : 0.0f);
        
        strongself.chatBoxVC.barItemArray = quickEntryAllItems;
        
        
        
        [strongself.chatCloseView removeAllSubviews];
        [strongself.chatCloseView removeFromSuperview];
        strongself.chatCloseView = nil;
        
        [strongself setupChatCloseView];
        
        if (strongself.dataSource.count > 0 &&
            [strongself.tableView numberOfRowsInSection:0] >= strongself.dataSource.count) {
            [strongself.tableView scrollToRowAtIndexPath:[NSIndexPath indexPathForRow:strongself.dataSource.count-1 inSection:0] atScrollPosition:UITableViewScrollPositionBottom animated:YES];
        }
        
    });
}

-(void)setupChatCloseView{
    
    CGFloat barHeight = self.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
    [self.view addSubview:self.chatCloseView];
    UILabel*titlelbl = [[UILabel alloc]initWithFrame:CGRectMake((self.view.tos_width - 230)/2, 0, 160, [TOSKitCustomInfo shareCustomInfo].chatBox_Height+barHeight)];
    titlelbl.font = [UIFont systemFontOfSize:16];
    titlelbl.textAlignment = NSTextAlignmentRight;
    titlelbl.textColor = [UIColor blackColor];
    titlelbl.backgroundColor = [UIColor clearColor];
    titlelbl.text = @"对话已结束，您可以";
    [self.chatCloseView addSubview:titlelbl];

    UIButton*openBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    openBtn.frame = CGRectMake((self.view.tos_width - 210)/2+160, 0, 70, [TOSKitCustomInfo shareCustomInfo].chatBox_Height+barHeight);

    [openBtn setTitleColor:TOSHexAColor(0x1890ff,1.0) forState:UIControlStateNormal];
    [openBtn.titleLabel setFont:[UIFont systemFontOfSize:16]];
    [openBtn setTitle:@"继续咨询" forState:UIControlStateNormal];
    openBtn.backgroundColor = [UIColor clearColor];
    [openBtn addTarget:self action:@selector(openAction) forControlEvents:UIControlEventTouchUpInside];
    [self.chatCloseView addSubview:openBtn];
    self.chatCloseView.hidden = YES;
}

- (void)loadData {
    
    NSString *tempPath = NSTemporaryDirectory();
    NSString *filePath = [tempPath stringByAppendingPathComponent:@"RichTextMessageIds"];
    NSArray *msgIdsArr = [NSKeyedUnarchiver unarchiveObjectWithFile:filePath];
    if (msgIdsArr!=nil && msgIdsArr.count>0) {
        //删除只加载一次富文本的Id
        for (NSString*msgId in msgIdsArr) {
            [[NSUserDefaults standardUserDefaults]removeObjectForKey:msgId];
        }
    }
    
    
    @WeakObj(self);
    
    /// 自定义刷新组件不为空就走自定义逻辑
    if (self.customRefreshHeader != nil) {
        _tableView.tos_header = self.customRefreshHeader;
        
    }
    else {
        __weak typeof(_tableView.tos_header) weakHeader = _tableView.tos_header;
        _tableView.tos_header = [TIMRefreshNormalHeader headerWithRefreshingBlock:^{
            typeof (selfWeak)strongSelf = selfWeak;
            typeof (weakHeader)strongHeader = weakHeader;
            NSLog(@"headerWithRefreshingBlock ...");
            [strongHeader beginRefreshing];
            //request
            [strongSelf loadMore];
        }];
    }
    
    
    self.needDelMessageFrames = [NSMutableArray arrayWithCapacity:3];
    self.needUpdateMessageFrames = [NSMutableArray arrayWithCapacity:3];
    self.needDelRichTextIds = [[NSMutableArray alloc]init];
    self.cellIdList = [[NSMutableArray alloc]init];
}


/// 自定义refresh组件回调需要调用的方法
- (void)loadMoreMessage {
    
    [self.customRefreshHeader beginRefreshing];
    /// 加载更多消息
    [self loadMore];
}

- (void)registerCell {
    [self.tableView registerClass:[ICChatMessageTextCell class] forCellReuseIdentifier:TypeText];
    [self.tableView registerClass:[ICChatMessageSatisfactionPopupCell class] forCellReuseIdentifier:TypeSatisfactionPopup];
    [self.tableView registerClass:[ICChatMessageRevokeCell class] forCellReuseIdentifier:TypeRevoke];
    [self.tableView registerClass:[ICChatMessageUnsupportCell class] forCellReuseIdentifier:TypeUnsupport];
    [self.tableView registerClass:[ICChatMessageImageCell class] forCellReuseIdentifier:TypePic];
    [self.tableView registerClass:[ICChatMessageTextTagCell class] forCellReuseIdentifier:TypeTextTag];
    [self.tableView registerClass:[ICChatMessageVideoCell class] forCellReuseIdentifier:TypeVideo];
    [self.tableView registerClass:[ICChatMessageVoiceCell class] forCellReuseIdentifier:TypeVoice];
    [self.tableView registerClass:[ICChatMessageCustomDeafultCell class] forCellReuseIdentifier:TypeCustom];
    [self.tableView registerClass:[ICChatMessageCustomFileCell class] forCellReuseIdentifier:TypeCustomFile];
    [self.tableView registerNib:[UINib nibWithNibName:@"TOSClient.bundle/OSEventSatisfactionCell" bundle:nil] forCellReuseIdentifier:TypeInvestigation];
    [self.tableView registerClass:[ICChatMessageSmallProgramCardCell class] forCellReuseIdentifier:TypeSmallProgramCard];
    [self.tableView registerClass:[ICChatMessageLogisticsCardCell class] forCellReuseIdentifier:TypeLogisticsCard];
    [self.tableView registerClass:[ICChatMessageRichCell class] forCellReuseIdentifier:GXRichText];
    //    [self.tableView registerClass:[ICChatMessageFileCell class] forCellReuseIdentifier:TypeFile];
    [self.tableView registerClass:[OSRobotCombinationCell class] forCellReuseIdentifier:TypeRobotCombination];
    [self.tableView registerClass:[OSRobotSelectCombinationCell class] forCellReuseIdentifier:TypeRobotSelectCombination];
    [self.tableView registerClass:[OSRobotWelcomeCell class] forCellReuseIdentifier:TypeRobotWelcome];
    [self.tableView registerClass:[OSSystomWelcomCell class] forCellReuseIdentifier:TySystomCombination];
    [self.tableView registerClass:[OSEventQueueCell class] forCellReuseIdentifier:TypeEventQueue];
    
    
//    [self.tableView registerClass:[OSEventSystemMessageCell class] forCellReuseIdentifier:TypeSystem];
    
    [self.tableView registerClass:[ICChatSystemCell class] forCellReuseIdentifier:TypeSystem];
    
    [self.tableView registerClass:[ICChatSystemCell class] forCellReuseIdentifier:TypeUnknown];
    
    [self.tableView registerClass:[OSRobotCombinationCell class] forCellReuseIdentifier:TypeRobotCombinationList];
    
    [self.tableView registerNib:[UINib nibWithNibName:@"TOSClient.bundle/ICChatMessageCommodityCardCell" bundle:nil] forCellReuseIdentifier:[ICChatMessageCommodityCardCell className]];
    
    [self.tableView registerClass:[ICChatMessageCommodityCardDetailsCell class] forCellReuseIdentifier:[ICChatMessageCommodityCardDetailsCell className]];
    
}

- (NSString *)messageTypeConversionWithMessage:(TOSMessage *)message type:(NSString *)type {
    NSString *newType;
    if (message.status == TIMMessageStatus_ReCall) {
        newType = TypeRevoke;
    } else {
        newType = type;
    }
    return newType;
}

//新增的拉取消息接口
-(void)getChatRecrodListWithLastTime:(NSString*)lastTime{
    if (lastTime == nil) {
        NSInteger timestamp = [kitUtils getNowTimestampWithSec].integerValue;
        lastTime = [NSString stringWithFormat:@"%ld",(long)timestamp + (long)1];
        self.lastTime = lastTime;
    }
    
    if (lastTime.length <= 10) {
        lastTime = [NSString stringWithFormat:@"%@000",lastTime];
    }
    
    if (self.firstHasGetHistoryListFinish == 1) {
        lastTime = @"";
    }
    @WeakObj(self);
    [[OnlineRequestManager sharedCustomerManager] getChatRecordListLastTime:lastTime
                                                                      limit:@"20"
                                                                    success:^(NSArray * _Nonnull chatList) {
        @StrongObj(self);
        if (chatList.count>0) {
            self.pageIndex++;
            OnlineChatRecordModel*lastmodel = chatList.firstObject;
            self.lastTime = lastmodel.createTime;
            
            BOOL isScroll = self.firstHasGetHistoryListFinish == 1;
            
            NSMutableArray *dataSource = [chatList mutableCopy];
            [self.reloadTaskManager startTaskWithScroll:isScroll completion:^{
                @StrongObj(self);
                //倒叙
                NSMutableArray *historyList  = (NSMutableArray *)[[dataSource reverseObjectEnumerator] allObjects];
                for (OnlineChatRecordModel *model in historyList) {
                    [self analysisHistoryWithModel:model withItemReload:NO];
                }
            }];
            //移到刷新列表位置 reloadTableViewWithScroll
//            if (self.firstHasGetHistoryListFinish == 1) {
//                [self addCommodityCardMessage];
//                self.firstHasGetHistoryListFinish = 0;
//            }
            [self.tableView.tos_header endRefreshing];
            /// 目前用这个不是很合适(后面再优化)
            self.pageIndex = 0;
        }else{
            if (self.firstHasGetHistoryListFinish == 1) {
                [self addCommodityCardMessage];
                self.firstHasGetHistoryListFinish = 0;
            }
            [self.tableView.tos_header endRefreshing];
        }
//        [self addBottomCard];
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        @StrongObj(self);
        if (self.firstHasGetHistoryListFinish == 1) {
            [self addCommodityCardMessage];
            self.firstHasGetHistoryListFinish = 0;
        }
        [self.tableView.tos_header endRefreshing];
//        [self addBottomCard];
    }];
}

- (void)addBottomCard {
    /// 快捷入口的高度
    CGFloat inputChatBarHeight = self.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
    /// 输入区域高度
    CGFloat inputTextHeight = [TOSKitCustomInfo shareCustomInfo].chatBox_Height;
    /// 判断页面是否有上次没有发送出去的文本
    if (self.chatBoxVC.chatBox.textView.text.length) {
        inputTextHeight -= self.chatBoxVC.chatBox.tosCF_height;
    }
    
    if (self.commodityCardOption && self.bottomCardView == nil) {
        
        CGFloat tableViewHeight = APP_Frame_Height-inputTextHeight-kNavTop-kBottomBarHeight-inputChatBarHeight-146.0;
        self.tableView.frame = CGRectMake(0, 0, self.view.tos_width, tableViewHeight);
        self.bottomCardView = [[TOSCommodityCardView alloc] initWithFrame:(CGRectMake(0, tableViewHeight, kScreenWidth, 146.0))];
        self.bottomCardView.delegate = self;
        NSLog(@"底部商品卡片的位置信息：%@", NSStringFromCGRect(self.bottomCardView.frame));
        [self.view addSubview:self.bottomCardView];
        self.chatBoxVC.view.frame = CGRectMake(0, self.bottomCardView.bottom_sd, App_Frame_Width, APP_Frame_Height);
        if (self.dataSource.count > 0 &&
            [self.tableView numberOfRowsInSection:0] >= self.dataSource.count) {
            [self.tableView scrollToRowAtIndexPath:[NSIndexPath indexPathForRow:self.dataSource.count-1 inSection:0] atScrollPosition:UITableViewScrollPositionBottom animated:YES];
        }
        
    }
    else if (self.commodityCardOption && self.bottomCardView != nil) {
        
        CGFloat tableViewHeight = APP_Frame_Height-inputTextHeight-kNavTop-kBottomBarHeight-inputChatBarHeight-146.0;
        
        self.tableView.frame = CGRectMake(0, 0, self.view.tos_width, tableViewHeight);
        self.bottomCardView.frame = CGRectMake(0, tableViewHeight, App_Frame_Width, 146.0);
        self.chatBoxVC.view.frame = CGRectMake(0, self.bottomCardView.bottom_sd, App_Frame_Width, APP_Frame_Height);
        if (self.dataSource.count > 0 &&
            [self.tableView numberOfRowsInSection:0] >= self.dataSource.count) {
            [self.tableView scrollToRowAtIndexPath:[NSIndexPath indexPathForRow:self.dataSource.count-1 inSection:0] atScrollPosition:UITableViewScrollPositionBottom animated:YES];
        }
    }
    
}


- (void)addCommodityCardMessage {
    
    
    [self addBottomCard];
    
}

/// 添加不支持的消息类型
/// @param messageRecvDirection 方向
/// @param object 消息数据
- (void)addUnsupportMessageCell:(BOOL)messageRecvDirection object:(TOSMessage *)object  {
    TIMTextMessage * txtMsg = [[TIMTextMessage alloc] initMessageWithContent:kTIMUnsupportMessageCellType extra:@""];
    if (messageRecvDirection) {
        [self otherSendTextMessageWithContent:TypeText originalType:object.type from:object.senderId to:object.receiverId fileKey:object.messageUUID msgId:object.msg_id sendTime:object.timestamp content:txtMsg.content senderType:object.senderType];
    }else{
        [self sendTextMessageWithContent:TypeText originalType:object.type from:object.senderId to:object.receiverId fileKey:object.messageUUID msgId:object.msg_id sendTime:object.timestamp content:txtMsg.content];
    }
}

/// 自定义cell要实现这个方法
- (TOSChatCustomBaseTableViewCell *)customTableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath withModel:(TIMMessageModel *)model {
    TOSChatCustomBaseTableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"cell"];
    if (!cell) {
        cell = [[TOSChatCustomBaseTableViewCell alloc] initWithStyle:(UITableViewCellStyleDefault) reuseIdentifier:@"cell"];
    }
    return cell;
}

#pragma mark - TOSCommodityCardViewDelegate

- (void)TOSCommodityCardViewSendTouch {
    
    
    [self TOSCommodityCardViewCloseTouch];
    
    [self routerEventWithName:TinetRouterBeSendCommodityCardEventUrl
                     userInfo:@{@"content"   : @""
                              }];
    
//    @WeakObj(self);
//    [self.reloadTaskManager startTaskWithScroll:YES completion:^{
//        @StrongObj(self);
//        
////        NSString * curMainUniqueId = [[OnlineDataSave shareOnlineDataSave] getMainUniqueId];
//    //    NSString * mainUniqueIdStatus = [[OnlineDataSave shareOnlineDataSave] getMainUniqueIdRunningStatus:curMainUniqueId];
//        // 做个保护 针对创建会话的状态
//        if (self.commodityCardOption) {
//            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCommodityCard originalType:@"text" content:(NSString *)self.commodityCardOption extra:@"" auditExtra:@"" path:nil urlPath:nil from:[[OnlineDataSave shareOnlineDataSave] getVisitorId] to:[[OnlineDataSave shareOnlineDataSave] getEndpointId] fileKey:@"" bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:[kitUtils getMsgUUID] sendTime:[self getNowTimestamp] showTime:[self p_needShowTime:[NSDate date]] picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:@(2)];
//            [self addObject:messageF isSender:YES isHeaderInsert:NO];
//            [self messageSendSucced:messageF];
//    //        [self scrollToBottom];
//    //        [self.tableView reloadData];
//        }
//    }];
}

- (void)TOSCommodityCardViewCloseTouch {
    if (self.bottomCardView) {
        [self.bottomCardView removeFromSuperview];
        self.bottomCardView = nil;
        self.tableView.tos_height += 146.0;
    }
    
}

- (void)TOSCommodityCardViewTapTouch {
    
    NSDictionary * cardInfo = @{
        @"subTitle": self.commodityCardOption.subTitle?:@"",
        @"description": self.commodityCardOption.descriptions?:@"",
        @"price": self.commodityCardOption.price?:@"",
        @"time": self.commodityCardOption.time?:@"",
        @"img": self.commodityCardOption.img?:@"",
        @"status": self.commodityCardOption.status?:@"",
        @"title": self.commodityCardOption.title?:@"",
        @"url": self.commodityCardOption.url?:@"",
        @"subUrl": self.commodityCardOption.subUrl?:@"",
        @"buttonText": self.commodityCardOption.buttonText?:@"",
        @"extraInfo": self.commodityCardOption.extraInfo?:@"",
        @"extraData": self.commodityCardOption.extraData?:@"",
    };
    
    
    [self tinet_textMessageClickAction:TinetClickCommodityCard userInfo:cardInfo];
    
    
    
}

- (void)sendMessageCloseCardView {
    
    if (self.bottomCardView) {
        __weak typeof(self) weakself = self;
        dispatch_async(dispatch_get_main_queue(), ^{
            __strong typeof(weakself) strongself = weakself;
            [strongself.bottomCardView removeFromSuperview];
            strongself.bottomCardView = nil;
            strongself.tableView.tos_height += 146.0;
        });
    }
    
}


#pragma mark - 处理每次进入页面可能被刷新UI而不滚动到底的问题
-(void)delayedResumeFlash{
    [self reloadTableViewWithScroll];
}

#pragma mark - Tableview data source

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.dataSource.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    @WeakObj(self)
    id obj                            = selfWeak.dataSource[indexPath.row];
    if ([obj isKindOfClass:[NSString class]]) {
        return nil;
    } else {
        TIMMessageFrame *modelFrame    = (TIMMessageFrame *)obj;
        NSString *ID                   = modelFrame.model.message.type;
        /// 自定义的cell
        if ([[[TOSKitCustomInfo shareCustomInfo].customCellRegister allKeys] containsObject:modelFrame.model.message.type]) {
            TOSChatCustomBaseTableViewCell * cell = [self customTableView:tableView cellForRowAtIndexPath:indexPath withModel:modelFrame.model];
            return cell;
        }
    //系统消息
        if ([ID isEqualToString:TypeSystem] || [ID isEqualToString:TypeUnknown]) {
            ICChatSystemCell *sysCell = [ICChatSystemCell cellWithTableView:tableView reusableId:ID];
            sysCell.messageF              = modelFrame;
            return sysCell;
        } else if ([ID isEqualToString:TypeCommodityCard]) {
            
            NSString *reuseIdentifie = [ICChatMessageCommodityCardCell className];
            ICChatMessageCommodityCardCell *cell = [tableView dequeueReusableCellWithIdentifier:reuseIdentifie];
            cell.modelFrame = modelFrame;
            return cell;
        } else if ([ID isEqualToString:TypeCommodityCardDetails]) {
            
            NSString *reuseIdentifie = [ICChatMessageCommodityCardDetailsCell className];
            ICChatMessageCommodityCardDetailsCell *cell = [tableView dequeueReusableCellWithIdentifier:reuseIdentifie];
            cell.modelFrame = modelFrame;
            cell.currentIndexPath = indexPath;
            cell.delegate = self;
            return cell;
        } else if ([ID isEqualToString:TypeRobotSelectCombination]) {//多选消息
            NSString * identifier = [NSString stringWithFormat:@"SelectCell%ld%ld",(long)indexPath.section,(long)indexPath.row];
            OSRobotSelectCombinationCell *selectCell = [tableView dequeueReusableCellWithIdentifier:identifier];
            if (!selectCell) {
                selectCell = [[OSRobotSelectCombinationCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:identifier];
            }
            int j = 0;
            if (self.cellIdList.count>0) {
                for (NSString*ident in self.cellIdList) {
                    if ([ident isEqualToString:identifier]) {//加载过了
                        j = 1;
                    }
                }
            }
            if (j == 0) {
                [self.cellIdList addObject:identifier];
                selectCell.modelFrame  = modelFrame;
                selectCell.longPressDelegate = selfWeak;
            }
            return selectCell;
        }else if ([ID isEqualToString:TypeText]) {//文字消息
            NSString * identifier = [NSString stringWithFormat:@"TextCell%ld%ld",(long)indexPath.section,(long)indexPath.row];
            ICChatMessageTextCell *textCell = [tableView dequeueReusableCellWithIdentifier:identifier];
            if (!textCell) {
                textCell = [[ICChatMessageTextCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:identifier];
            }
//            int j = 0;
//            if (self.cellIdList.count>0) {
//                for (NSString*ident in self.cellIdList) {
//                    if ([ident isEqualToString:identifier]) {//加载过了
//                        j = 1;
//                    }
//                }
//            }
//            if (j == 0 ) {
//                [self.cellIdList addObject:identifier];
                textCell.modelFrame  = modelFrame;
                textCell.longPressDelegate = selfWeak;
//            }
            return textCell;
        }else if ([ID isEqualToString:TypeEventQueue]) {//放弃排队消息
            NSString * identifier = [NSString stringWithFormat:@"TypeEventQueueCell%ld%ld",(long)indexPath.section,(long)indexPath.row];
            OSEventQueueCell *queueCell = [tableView dequeueReusableCellWithIdentifier:identifier];
            if (!queueCell) {
                queueCell = [[OSEventQueueCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:identifier];
            }
            int j = 0;
            if (self.cellIdList.count>0) {
                for (NSString*ident in self.cellIdList) {
                    if ([ident isEqualToString:identifier]) {//加载过了
                        j = 1;
                    }
                }
            }
            if (j == 0 ) {
                [self.cellIdList addObject:identifier];
                queueCell.modelFrame  = modelFrame;
//                textCell.longPressDelegate = selfWeak;
            }
            return queueCell;
        }
        
        
        ICChatMessageBaseCell *cell    = [tableView dequeueReusableCellWithIdentifier:ID];
        cell.longPressDelegate         = selfWeak;
        if ([modelFrame.model.message.type isEqualToString:TypeVideo]) {
            ICChatMessageVideoCell *videoCell = (ICChatMessageVideoCell *)cell;
            [videoCell stopVideo];
        }
//        [[ICMediaManager sharedManager] clearReuseImageMessage:cell.modelFrame.model];
        
        // 处理cell中下载数据后的刷新
        //取出当前URL对应的下载下载操作 去除视频下载换为封面图下载
        if (([modelFrame.model.message.type isEqualToString:TypePic] ||
             [modelFrame.model.message.type isEqualToString:TypeVoice] /*||
             [modelFrame.model.message.type isEqualToString:TypeVideo]*/) &&
                ![kitUtils isBlankString:modelFrame.model.message.messageId] && !selfWeak.downloadFileDatas[modelFrame.model.message.messageId]) {
            
            
            if ([modelFrame.model.message.type isEqualToString:TypeVoice]) {
                
                NSString *localVoicePath = [ICFileTool filePathWithName:modelFrame.model.message.messageId orgName:@"voice" type:@"wav"];
                if ([ICFileTool fileExistsAtPath:localVoicePath]) {
                    
                    int voiceDuration = (int)[[ICRecordManager shareManager] durationWithVideo:[NSURL fileURLWithPath:localVoicePath]];
                    if (voiceDuration < 1000) {
                        // 小于一秒的显示1秒
                        voiceDuration = 1000;
                    }
                    modelFrame.model.message.voiceDuration = [NSNumber numberWithInt:voiceDuration];
                    
                    modelFrame.model.mediaPath = localVoicePath;
                    selfWeak.downloadFileDatas[modelFrame.model.message.messageId] = localVoicePath;
                    
                    cell.modelFrame = modelFrame;
                    return cell;
                }
            }
            
            
            NSBlockOperation* operation = selfWeak.downloadOperations[modelFrame.model.message.messageId];
            if (nil == operation) {
                //创建下载操作
                operation = [NSBlockOperation blockOperationWithBlock:^{
                   NSURL* url = [NSURL URLWithString:modelFrame.model.urlPath];
                    NSLog(@"modelFrame.model.urlPath ------ %@",url);
                    //1.通过URL创建NSURLRequest
                    NSURLRequest *request = [[NSURLRequest alloc] initWithURL:url cachePolicy:NSURLRequestUseProtocolCachePolicy timeoutInterval:30];
                    //2.创建一个 NSMutableURLRequest 添加 header
                    NSMutableURLRequest *mutableRequest = [request mutableCopy];
                    //3.把值覆给request
                    request = [mutableRequest copy];
                    //3.建立网络连接NSURLConnection，同步请求数据
                    NSHTTPURLResponse *response = nil;
                    NSError *error = nil;
                    NSData *data = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
//                    NSData* data =  [NSData dataWithContentsOfURL:url];
                    if (data) {
                        if ([modelFrame.model.message.type isEqualToString:TypePic]) {
                            if (data) {
                                //防止下载失败为空赋值造成崩溃
                                UIImage* image = [UIImage imageWithData:data];
                                
                                //对图片做存
                                if (image) {
                                    [[ICMediaManager sharedManager] saveImage:image msgId:modelFrame.model.message.messageId picType:@"jpg"];
                                } else {
                                    // 如果图片不存在则使用占位图
                                    image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"loadImage.png"]];
                                    [[ICMediaManager sharedManager] saveImage:image msgId:modelFrame.model.message.messageId picType:@"jpg"];
                                }
                                
                                selfWeak.downloadFileDatas[modelFrame.model.message.messageId] = image;
                                NSString *localImagePath;
                                if ([[UIImage typeForImageData:data] isEqualToString:kGIFTimageType]) {
                                    localImagePath = [[ICMediaManager sharedManager] saveGifImage:data msgId:modelFrame.model.message.messageId picType:kGIFTimageType];
                                }else{
                                    localImagePath = [[ICMediaManager sharedManager] saveImage:image msgId:modelFrame.model.message.messageId picType:@"jpg"];
                                }
                                modelFrame.model.mediaPath = localImagePath;
                                CGFloat fixelW = image.size.width;//CGImageGetWidth(image.CGImage);
                                CGFloat fixelH = image.size.height;//CGImageGetHeight(image.CGImage);

                                modelFrame.model.picWidth = fixelW;
                                modelFrame.model.picHeight = fixelH;
                                
                                /// 图片下载后需要重新进行大小布局的更新，这里重新赋值一个frame，然后替换数据源列表的数据刷新列表
                                TIMMessageFrame *testModelFrame = [ICMessageHelper createMessageFrame:TypePic originalType:@"image" content:@"[图片]" extra:@"" auditExtra:@"" path:modelFrame.model.mediaPath urlPath:modelFrame.model.urlPath from:modelFrame.model.message.from to:modelFrame.model.message.to fileKey:modelFrame.model.message.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:modelFrame.model.message.messageId sendTime:[modelFrame.model.message.msgDate timeIntervalSince1970]*1000 showTime:modelFrame.model.message.showTime picSize:CGSizeMake(modelFrame.model.picWidth, modelFrame.model.picHeight) picType:@"" isSender:modelFrame.model.isSender receivedSenderByYourself:NO status:2 senderType:modelFrame.model.message.senderType];
                                testModelFrame.model.message.deliveryState = ICMessageDeliveryState_Delivered;
                                
                                //回到主线程刷新表格
                                [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                                    @StrongObj(self)
                                    [self.downloadOperations removeObjectForKey:modelFrame.model.message.messageId];
                                    __block NSInteger index;
                                    NSArray *readyDisplayDataSource = [self.readyDisplayDataSource mutableCopy];
                                    [readyDisplayDataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
                                        if ([obj.model.message.messageId isEqualToString:modelFrame.model.message.messageId]) {
                                            index = idx;
                                        }
                                    }];
                                    [self.readyDisplayDataSource replaceObjectAtIndex:index withObject:testModelFrame];
                                    self.reloadTaskManager.isScroll = NO;
                                    [self.reloadTaskManager startCountdown];
                                }];
                            }
                        }else if ([modelFrame.model.message.type isEqualToString:TypeVoice]) {
                            if (data) { //防止下载失败为空赋值造成崩溃
//                                TIMKitLog(@"------- modelFrame.model.mediaPath== %@ -----",modelFrame.model.mediaPath);
                                NSString * localVoicePath = [ICFileTool writeDataToFile:modelFrame.model.message.messageId fileName:@"voice" type:@"wav" data:data];

                                int voiceDuration = (int)[[ICRecordManager shareManager] durationWithVideo:[NSURL fileURLWithPath:localVoicePath]];
                                if (voiceDuration < 1000) {
                                    // 小于一秒的显示1秒
                                    voiceDuration = 1000;
                                }
                                modelFrame.model.message.voiceDuration = [NSNumber numberWithInt:voiceDuration];
                                
                                modelFrame.model.mediaPath = localVoicePath;
                                selfWeak.downloadFileDatas[modelFrame.model.message.messageId] = localVoicePath;
                                //回到主线程刷新表格
                                [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                                    @StrongObj(self)
                                    // 从字典中移除下载操作 (防止operations越来越大，保证下载失败后，能重新下载)
                                    [self.downloadOperations removeObjectForKey:modelFrame.model.message.messageId];
                                    self.reloadTaskManager.isScroll = NO;
                                    [self.reloadTaskManager startCountdown];
                                }];
                            }
                        }else if ([modelFrame.model.message.type isEqualToString:TypeVideo]) {
                            if (data) { //防止下载失败为空赋值造成崩溃
//                                UIImage* image = [UIImage imageWithData:data];
                                NSString * localVideoPath = [[ICVideoManager shareManager] WriteDataToFile:modelFrame.model.message.messageId data:data];
//                                NSString * localImagePath = [[ICMediaManager sharedManager] saveImage:image msgId:modelFrame.model.message.messageId picType:@"jpg"];
                                modelFrame.model.mediaPath = localVideoPath;
//                                modelFrame.model.urlPath = localImagePath;
                                
                                CGFloat fixelW = 100;//image.size.width;
                                CGFloat fixelH = 100;//image.size.height;

                                modelFrame.model.picWidth = fixelW;
                                modelFrame.model.picHeight = fixelH;
                                //回到主线程刷新表格
                                [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                                    @StrongObj(self)
                                    ICChatMessageBaseCell * baseCell = (ICChatMessageBaseCell *)cell;
                                    baseCell.modelFrame = modelFrame;
                                    //                                modelFrame.model.urlPath = videoImagePath;
                                    self.downloadFileDatas[modelFrame.model.message.messageId] = localVideoPath;//localImagePath;

                                    // 从字典中移除下载操作 (防止operations越来越大，保证下载失败后，能重新下载)
                                    [self.downloadOperations removeObjectForKey:modelFrame.model.message.messageId];
                                    
                                    self.reloadTaskManager.isScroll = NO;
                                    [self.reloadTaskManager startCountdown];
                                }];
                            }
                        }
                    }
                }];
                //添加操作到队列中
                [selfWeak.downloadQueue addOperation:operation];
                //添加到字典中
                if ([kitUtils isBlankString:modelFrame.model.message.messageId]) {
                    TIMKitLog(@"downloadOperations key is null %@",modelFrame.model.message);
                }
                selfWeak.downloadOperations[modelFrame.model.message.messageId] = operation;
            }
        }
        
        cell.modelFrame = modelFrame;
        return cell;
    }
}

#pragma mark - ICChatMessageCommodityCardDetailsCellDelegate

/// 卡片折叠/展开
- (void)ICChatMessageCommodityCardDetailsCellWithIndexPath:(NSIndexPath *)indexPath {
//    [self.tableView reloadRowAtIndexPath:indexPath withRowAnimation:(UITableViewRowAnimationAutomatic)];
    self.reloadTaskManager.isScroll = NO;
    [self.reloadTaskManager startCountdown];
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    TIMMessageFrame *messageF = [self.dataSource objectAtIndex:indexPath.row];
    /// 自定义的cell
//    if (self.customCells.count) {
//
//
//    }
    if ([[[TOSKitCustomInfo shareCustomInfo].customCellRegister allKeys] containsObject:messageF.model.message.type]) {
        TOSChatCustomBaseTableViewCell * cell = (TOSChatCustomBaseTableViewCell *)[self customTableView:tableView cellForRowAtIndexPath:indexPath withModel:messageF.model];
        return [cell cellHeight];
//            return messageF.cellHight;
    }
    return messageF.cellHight > 0 ? messageF.cellHight : 0;
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    UIView * view = [[UIView alloc] initWithFrame:(CGRectZero)];
    view.backgroundColor = UIColor.clearColor;
    return view;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    return [TOSKitCustomInfo shareCustomInfo].lastMessage_spacing;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    
    TIMMessageFrame *messageF = [self.dataSource objectAtIndex:indexPath.row];
    
    /// 自定义的cell点击事件由外部处理，需要进行点击拦截
    if ([[[TOSKitCustomInfo shareCustomInfo].customCellRegister allKeys] containsObject:messageF.model.message.type]) {
        TOSChatCustomBaseTableViewCell * cell = (TOSChatCustomBaseTableViewCell *)[self customTableView:tableView cellForRowAtIndexPath:indexPath withModel:messageF.model];
        return;
    }
    
    if (([messageF.model.message.type isEqualToString:TypeCustom] ||
         [messageF.model.message.type isEqualToString:TypeCustomFile]) &&
        messageF.model.message.content) {
        NSLog(@"messageF.model.urlPath ==== %@",messageF.model.urlPath);
        //机器人文件
        if (messageF.model.urlPath != nil && messageF.model.urlPath.length>0 && ([messageF.model.urlPath rangeOfString:@"bot_media"].location !=NSNotFound || [messageF.model.urlPath rangeOfString:@"timLocalFileApp_"].location !=NSNotFound)) {
            STBaseWebViewController*vc = [[STBaseWebViewController alloc]init];
            if ([messageF.model.urlPath rangeOfString:@"timLocalFileApp_"].location !=NSNotFound) {
                vc.loadLocalFile = YES;
            } else {
                vc.loadLocalFile = NO;
            }
            vc.urlString = [messageF.model.urlPath stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
            [self.navigationController pushViewController:vc animated:YES];
        } else {
            
            NSString *urlPath = @"";
            NSData *jsonData = [messageF.model.message.content dataUsingEncoding:NSUTF8StringEncoding];
            NSError *err;
            NSDictionary *extraDict = [NSJSONSerialization JSONObjectWithData:jsonData
                                                                      options:NSJSONReadingMutableContainers
                                                                        error:&err];
            NSString *fileKey = @"null";
            NSString *fileName = @"null";
            if (![kitUtils isBlankString:extraDict[@"fileKey"]]) {
                fileKey = extraDict[@"fileKey"];
            }
            if (![kitUtils isBlankString:extraDict[@"fileName"]]) {
                fileName = extraDict[@"fileName"];
            }
            
            if (![kitUtils isBlankString:extraDict[@"messageType"]] &&
                [extraDict[@"messageType"] isEqualToString:@"8"]) {
                urlPath = messageF.model.urlPath;
            } else {
                NSString * timestamp = [kitUtils getNowTimestampWithSec];
                
                NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
                NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
                urlPath =   [NSString stringWithFormat:@"%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[NSString stringWithFormat:@"%@/api/app/message/file",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl]],fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign];
            }
            
            STBaseWebViewController*vc = [[STBaseWebViewController alloc]init];
            vc.urlString = [urlPath stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
            [self.navigationController pushViewController:vc animated:YES];
        }
    }
    [self.chatBoxVC resignFirstResponder];
    [self.view endEditing:YES];
}

- (void)scrollViewWillBeginDragging:(UIScrollView *)scrollView
{
    [self.chatBoxVC resignFirstResponder];
    [self.view endEditing:YES];
}

- (void)tableView:(UITableView *)tableView didEndDisplayingCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath*)indexPath {
    if ([cell isKindOfClass:[ICChatMessageVideoCell class]] && self) {
        ICChatMessageVideoCell *videoCell = (ICChatMessageVideoCell *)cell;
        [videoCell stopVideo];
    }
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
    CGFloat horizontalOffset = scrollView.contentOffset.x;
    CGFloat verticalOffset = scrollView.contentOffset.y;
    // 在这里处理滑动事件，例如根据滑动距离执行特定操作
    
    [self tableViewContentOffset:(CGPointMake(horizontalOffset, verticalOffset)) withMessageHeight:(self.view.bottom_sd - kNavTop - [TOSKitCustomInfo shareCustomInfo].chatBox_Height - kBottomBarHeight)];
    
}


/// 当前列表的滑动高度，子类重写该方法可以获取当前列表的滑动高度
/// - Parameters:
///   - contentOffset: 当前列表的滑动范围
///   - messageHeight: 消息列表的高度
- (void)tableViewContentOffset:(CGPoint)contentOffset withMessageHeight:(CGFloat)messageHeight {
    
}

- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView {
    if (scrollView != self.tableView) {
        return;
    }
    BOOL scrollToScrollStop = !scrollView.tracking && !scrollView.dragging && !scrollView.decelerating;
    if (scrollToScrollStop) {
        
//        CGFloat height = self.tableView.frame.size.height;
        CGFloat contentOffsetY = self.tableView.contentOffset.y;
        CGFloat bottomOffset = self.tableView.contentSize.height - contentOffsetY;
        if (bottomOffset <= 10 && self.dataSource.count > 0) {
            //在最底部
            NSInteger count = self.dataSource.count - 1;
            TIMMessageFrame *messageFrame = self.dataSource[count];
            [self p_needShowTime:messageFrame.model.message.msgDate];
        }
    }
}




#pragma mark - ICChatBoxViewControllerDelegate

- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController didChangeChatBoxFaceTextViewHeight:(CGFloat)height withFaceHeight:(CGFloat)faceHeight {
    
//    CGFloat chatBarHeight = self.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
    self.chatBoxVC.view.top_sd = kScreenHeight - height - faceHeight - kNavTop - kBottomBarHeight;
    if (self.commodityCardOption && self.bottomCardView != nil) {
        self.tableView.tos_height = kScreenHeight - height - faceHeight - kNavTop - kBottomBarHeight - 146.0;
        self.bottomCardView.top_sd = self.tableView.tos_height;
    }
    else {
        self.tableView.tos_height = kScreenHeight - height - faceHeight - kNavTop - kBottomBarHeight;
    }
    
    int iHeight = (int)height;
    if (iHeight == (int)(self.chatBoxVC.chatBox.textView.tos_height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height)) {
        if (self.chatBoxVC.chatBox.status == ICChatBoxStatusShowKeyboard && self.chatBoxVC.keyboardFrame.size.height <= 0) {
            
        }else{

//            [self.tableView reloadData];
//            [self scrollToBottom];
            self.reloadTaskManager.isScroll = YES;
            [self.reloadTaskManager startCountdown];
            
            _isKeyBoardAppear  = NO;
        }
    } else if(iHeight == (int)(self.chatBoxVC.chatBox.textView.tos_height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height + HEIGHT_CHATBOXVIEW)){

//        [self scrollToBottom];
        self.reloadTaskManager.isScroll = YES;
        [self.reloadTaskManager startCountdown];
    } else {
        if (IphoneX) {
            self.chatBoxVC.view.top_sd += kBottomBarHeight;
            self.tableView.tos_height += kBottomBarHeight;
            if (self.bottomCardView != nil) {
                self.bottomCardView.top_sd += kBottomBarHeight;
            }
        }
//        [self scrollToBottom];
        self.reloadTaskManager.isScroll = YES;
        [self.reloadTaskManager startCountdown];
        _isKeyBoardAppear  = YES;
    }
    if (self.textView == nil) {
        self.textView = chatboxViewController.chatBox.textView;
    }
    
}

- (void)chatBoxDidClickBarItemViewController:(TIMChatBoxViewController *)chatboxViewController withItemModel:(TOSQuickEntryModel *)model {
    
    if ([model.eventName isEqualToString:TOS_EVENT_NAME_TICKET_MESSAGE_STATUS]) {
        
//        [[OnlineRequestManager sharedCustomerManager] jointUrlParam:model.dynamicConfigParameters[TOS_TICKET_PLUGIN_URL] type:@"TICKET" commentCountEnable:model.dynamicConfigParameters[TOS_COMMENT_COUNT_ENABLE] visitorCreatedTicket:model.dynamicConfigParameters[TOS_VISITOR_CREATED_TICKET] success:^(NSString * _Nonnull url) {
            
        if ([kitUtils isBlankString:model.dynamicConfigParameters[TOS_TICKET_PLUGIN_URL_RESULT]]) {
            [self tim_showMBErrorView:@"工单留言页面获取失败"];
        } else {
            if (self.workOrderView) {
                [self.workOrderView removeFromSuperview];
                self.workOrderView = nil;
            }
            TOSWorkOrderWebView *workOrderView = [[TOSWorkOrderWebView alloc] initWithFrame:CGRectMake(0, 0, kWindowWidth, self.view.tos_height)];
            workOrderView.workOrderUrl = model.dynamicConfigParameters[TOS_TICKET_PLUGIN_URL_RESULT];
            workOrderView.titleStr = model.name;
            [self.view addSubview:workOrderView];
            [workOrderView showPopupView];
            self.workOrderView = workOrderView;
            [[NSNotificationCenter defaultCenter] postNotificationName:kUpdateTicketMessageStatus object:nil];
        }
            
//            for (NSInteger i = 0; i < self.quickEntryAllItems.count; i++) {
//                if ([self.quickEntryAllItems[i].eventName isEqualToString:TOS_EVENT_NAME_TICKET_MESSAGE_STATUS]) {
//                    self.quickEntryAllItems[i].dynamicConfigParameters[TOS_STAFF_COMMENT_TOTAL_COUNT] = 0;
//                }
//            }
//            [self updateSessionWindowQuickEntrys:self.quickEntryAllItems];
//        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
//            [self tim_showMBErrorView:@"工单留言页面获取失败"];
//        }];
    }
    [self quickEntryItemDidTouchModel:model];
}

/// 子类可以重写这个方法
- (void)quickEntryItemDidTouchModel:(TOSQuickEntryModel *)model {
}

/// 当前会话状态监听
- (void)chatStatusChanged:(TinetChatStatusType)status{
    /// 扩展面板是否要更改item的判断
    if (status == TinetChatStatusTypeOnline && TOSKitChatBoxExtendBoard.shareChatBoxExtendBoard.onlineChange) {
        [[NSNotificationCenter defaultCenter] postNotificationName:kOnlineChangeExtendBoardItemNotification object:nil];
    }
    NSLog(@"当前页面的会话状态：%i", status);
    /// 语音按钮是否隐藏的判断
    if (status == TinetChatStatusTypeRobot && TOSKitCustomInfo.shareCustomInfo.robotHiddenVoice) {
        self.chatBoxVC.chatBox.robotHiddenVoice = TOSKitCustomInfo.shareCustomInfo.robotHiddenVoice;
    }
    if (status == TinetChatStatusTypeOnline && self.chatBoxVC.chatBox.robotHiddenVoice) {
        self.chatBoxVC.chatBox.robotHiddenVoice = NO;
    }
    
}

- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
                inputATSymbol:(NSString *)symbol
                   inputRange:(NSRange)range {
}

- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
               deleteATSymbol:(NSString *)symbol
                   inputRange:(NSRange)range {

}

/// 删除@符号和空格结尾
/// @param oldString oldString
- (NSString *)deleteATSymbolsWithString:(NSString *)oldString {
    NSString *deleteAT = [oldString stringByReplacingOccurrencesOfString:@"@" withString:@""];
    NSString *deleteATEnd = [deleteAT stringByReplacingOccurrencesOfString:kTIMATEnd withString:@""];
    return deleteATEnd;
}

- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
        didChangeChatBoxTextViewHeight:(CGFloat)height
{
    
    if (self.commodityCardOption && self.bottomCardView != nil) {
        self.chatBoxVC.view.top_sd = kScreenHeight-height - kNavTop - kBottomBarHeight;
        self.tableView.tos_height = kScreenHeight - height - kNavTop - kBottomBarHeight - 146.0;
        self.bottomCardView.bottom_sd = self.chatBoxVC.view.top_sd;
        
    } else {
        self.chatBoxVC.view.top_sd = kScreenHeight-height - kNavTop - kBottomBarHeight;
        self.tableView.tos_height = kScreenHeight - height - kNavTop - kBottomBarHeight;
    }
    
    
//    self.chatBoxVC.view.top = self.view.bottom - height - chatBarHeight;
//    self.tableView.height = self.view.bottom - height - self.chatBoxVC.view.height;
    int iHeight = (int)height;
    if (iHeight == (int)(self.chatBoxVC.chatBox.textView.tos_height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height)) {
        if (self.chatBoxVC.chatBox.status == ICChatBoxStatusShowKeyboard && self.chatBoxVC.keyboardFrame.size.height <= 0) {
            
        }else{

//            [self.tableView reloadData];
//            [self scrollToBottom];
            self.reloadTaskManager.isScroll = YES;
            [self.reloadTaskManager startCountdown];
            
            _isKeyBoardAppear  = NO;
        }
    } else if(iHeight == (int)(self.chatBoxVC.chatBox.textView.tos_height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height + HEIGHT_CHATBOXVIEW)){
//        [self scrollToBottom];
        self.reloadTaskManager.isScroll = YES;
        [self.reloadTaskManager startCountdown];
    } else {
        /// 表情面板不需要再做底部安全区域的计算，系统的软件盘需要考虑安全区域的操作
        if (IphoneX && self.chatBoxVC.chatBox.status != ICChatBoxStatusShowFace) {

            self.chatBoxVC.view.top_sd += kBottomBarHeight;
            self.tableView.tos_height += kBottomBarHeight;
            if (self.bottomCardView != nil) {
                self.bottomCardView.tos_top += kBottomBarHeight;
            }
        }
//        [self scrollToBottom];
        self.reloadTaskManager.isScroll = YES;
        [self.reloadTaskManager startCountdown];
        _isKeyBoardAppear  = YES;
    }
    if (self.textView == nil) {
        // 为避免在每次viewdidload并且快捷入口展示的时候收到flash的消息导致不scroll的问题
        if (self.quickEntryAllItems.count > 0) {
            self.stopUIFlash = YES;
            // 创建新的延迟任务
            self.delayedResumeFlashBlock = dispatch_block_create(0, ^{
                [self delayedResumeFlash];
            });
                
            // 延迟1秒执行任务
            dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1 * NSEC_PER_SEC)), dispatch_get_main_queue(), self.delayedResumeFlashBlock);
        }
        self.textView = chatboxViewController.chatBox.textView;
    }
    
}
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
        didChangeChatBoxKBToVoiceHeight:(CGFloat)height{

    CGFloat chatBarHeight = self.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
    self.chatBoxVC.view.top_sd = kScreenHeight-height - kNavTop - kBottomBarHeight - chatBarHeight;
    self.tableView.tos_height = kScreenHeight - height - kNavTop - kBottomBarHeight - chatBarHeight;
    int iHeight = (int)height;
    if (iHeight == [TOSKitCustomInfo shareCustomInfo].chatBox_Height) {
        if (self.chatBoxVC.chatBox.status == ICChatBoxStatusShowKeyboard && self.chatBoxVC.keyboardFrame.size.height <= 0) {
   
        }else{
            
//            [self.tableView reloadData];
            self.reloadTaskManager.isScroll = NO;
            [self.reloadTaskManager startCountdown];
            _isKeyBoardAppear  = NO;
        }
    } else {
        if (IphoneX) {
            self.chatBoxVC.view.top_sd += kBottomBarHeight;
            self.tableView.tos_height += kBottomBarHeight;
        }
//        [self scrollToBottom];
        self.reloadTaskManager.isScroll = YES;
        [self.reloadTaskManager startCountdown];
        _isKeyBoardAppear  = YES;
    }
    if (self.textView == nil) {
        self.textView = chatboxViewController.chatBox.textView;
    }
}

- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
        didChangeChatBoxHeight:(CGFloat)height
{
    TIMKitLog(@"self.chatBoxVC.chatBox.status ：%ld", (long)self.chatBoxVC.chatBox.status);
    /// 当前状态是更多/表情，需要移除底部的商品卡片UI
    if (self.chatBoxVC.chatBox.status == ICChatBoxStatusShowMore || self.chatBoxVC.chatBox.status == ICChatBoxStatusShowFace) {
        [self TOSCommodityCardViewCloseTouch];
    }
    CGFloat chatBarHeight = self.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
    CGFloat top = kScreenHeight-height - kNavTop- kBottomBarHeight - chatBarHeight;
    if (top - self.chatBoxVC.view.top_sd >= 5 &&
        height == (int)(self.chatBoxVC.chatBox.textView.tos_height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height) &&
        self.chatBoxVC.chatBox.status == ICChatBoxStatusShowKeyboard &&
        self.chatBoxVC.keyboardFrame.size.height <= 0) {
//        [self scrollToBottom];
        self.reloadTaskManager.isScroll = YES;
        [self.reloadTaskManager startCountdown];
    }
//    self.chatBoxVC.view.top_sd = kScreenHeight-height - kNavTop- kBottomBarHeight - chatBarHeight;
    if (self.commodityCardOption && self.bottomCardView != nil) {
        self.tableView.tos_height = kWindowHeight - height - kNavTop- kBottomBarHeight - chatBarHeight - 146.0;
        self.bottomCardView.top_sd = self.tableView.tos_height;
        self.chatBoxVC.view.top_sd = self.bottomCardView.tos_bottom;
    }
    else {
        self.tableView.tos_height = kWindowHeight - height - kNavTop- kBottomBarHeight - chatBarHeight;
        self.chatBoxVC.view.top_sd = self.tableView.bottom_sd;
    }

    int iHeight = (int)height;
    if (iHeight == (int)(self.chatBoxVC.chatBox.textView.tos_height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height)) {
        if ((self.chatBoxVC.chatBox.status == ICChatBoxStatusShowKeyboard ||
             self.chatBoxVC.chatBox.status == ICChatBoxStatusShowVoice) && self.chatBoxVC.keyboardFrame.size.height <= 0) {
//            [self innerScrollToBottom];
        }else{

//            [self.tableView reloadData];
//            [self innerScrollToBottom];
            _isKeyBoardAppear  = NO;
        }
    }else if(iHeight == (int)(self.chatBoxVC.chatBox.textView.tos_height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height + HEIGHT_CHATBOXVIEW)){
//        [self scrollToBottom];
        if (self.bottomCardView != nil && self.dataSource.count > 0 &&
            [self.tableView numberOfRowsInSection:0] >= self.dataSource.count) {
            [self.tableView scrollToRowAtIndexPath:[NSIndexPath indexPathForRow:self.dataSource.count-1 inSection:0] atScrollPosition:UITableViewScrollPositionBottom animated:YES];
        }
        self.reloadTaskManager.isScroll = YES;
        [self.reloadTaskManager startCountdown];
    }else{
        /// 表情面板不需要再做底部安全区域的计算，系统的软件盘需要考虑安全区域的操作
        if (IphoneX && self.chatBoxVC.chatBox.status != ICChatBoxStatusShowFace) {
            self.chatBoxVC.view.top_sd += kBottomBarHeight;
            if (self.bottomCardView != nil) {
                self.bottomCardView.tos_top += kBottomBarHeight;
            }
            self.tableView.tos_height += kBottomBarHeight;
        }
//        [self scrollToBottom];
        self.reloadTaskManager.isScroll = YES;
        [self.reloadTaskManager startCountdown];
        _isKeyBoardAppear  = YES;
    }
    if (self.textView == nil) {
        self.textView = chatboxViewController.chatBox.textView;
    }
}

- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController didVideoViewAppeared:(ICVideoView *)videoView
{
    [_chatBoxVC.view setFrame:CGRectMake(0, HEIGHT_SCREEN-[TOSKitCustomInfo shareCustomInfo].chatBox_Height-kNavTop, App_Frame_Width, APP_Frame_Height)];
    //    videoView.hidden = NO;
    @WeakObj(self);
    [UIView animateWithDuration:0.5 animations:^{
        @StrongObj(self);
        self.tableView.tos_height = HEIGHT_SCREEN - videwViewH - kNavTop;
        self.chatBoxVC.view.frame = CGRectMake(0, videwViewX+kNavTop, App_Frame_Width, videwViewH);
//        [self scrollToBottom];
        self.reloadTaskManager.isScroll = YES;
        [self.reloadTaskManager startCountdown];
    } completion:^(BOOL finished) { // 状态改变
        @StrongObj(self);
        self.chatBoxVC.chatBox.status = ICChatBoxStatusShowVideo;
        // 在这里创建视频设配
        UIView *videoLayerView = [videoView viewWithTag:1000];
        UIView *placeholderView = [videoView viewWithTag:1001];
        [[ICVideoManager shareManager] setVideoPreviewLayer:videoLayerView];
        [NSTimer scheduledTimerWithTimeInterval:1.0 target:self selector:@selector(videoPreviewLayerWillAppear:) userInfo:placeholderView repeats:NO];
    }];
}

- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController sendVideoMessage:(NSString *)videoPath duration:(CGFloat)videoTimeLength  thumbnailImagePath:(NSString *)thumbnailImagePath
{
    if (thumbnailImagePath && videoPath) {
        NSData *imageData = [NSData dataWithContentsOfFile:thumbnailImagePath];
        UIImage * imageSrc = [UIImage imageWithData:imageData];
        CGFloat fixelW = imageSrc.size.width;//CGImageGetWidth(imageSrc.CGImage);
        CGFloat fixelH = imageSrc.size.height;//CGImageGetHeight(imageSrc.CGImage);

        NSData *videoData;
        NSString * strRealVideoPath;
        NSLog(@"videoPath ==== %@",videoPath);
        if ([[videoPath substringToIndex:7] isEqualToString:@"file://"]) {
            strRealVideoPath = [videoPath substringFromIndex:7];
        }else{
            strRealVideoPath = videoPath;
        }
        
        NSLog(@"strRealVideoPath ==== %@",strRealVideoPath);
        videoData = [NSData dataWithContentsOfFile:strRealVideoPath];
        
        // 生成新的文件名称为uuid
        NSString * videoFileName = [[videoPath lastPathComponent] stringByDeletingPathExtension];
//        dispatch_async(dispatch_queue_create(0, 0), ^{
//            [[ICVideoManager shareManager] WriteDataToFile:videoFileName data:videoData];
//        });
        
//        NSString * videoImagePath = [[ICMediaManager sharedManager] saveVideoImage:imageSrc fileName:videoFileName];
        
        double size = [videoData length]/1000.f/1024.f;

        int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;

        NSLog(@"sizeLimitM ==== %d",sizeLimitM);
        if (sizeLimitM < size) {
            NSString * strShow = [NSString stringWithFormat:@"请发送小于%dM文件",sizeLimitM];
            [WHToast showMessage:strShow duration:2 finishHandler:^{
                
            }];
            return;
        }
        
        @WeakObj(self);
        [self.reloadTaskManager startTaskWithScroll:YES completion:^{
            @StrongObj(self);
            
            // Kit
            __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo
                                                                       originalType:@"video"
                                                                            content:@"[视频]"
                                                                              extra:@""
                                                                         auditExtra:@""
                                                                               path:videoPath
                                                                            urlPath:nil
                                                                               from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                 to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                            fileKey:[[NSUUID UUID] UUIDString]
                                                                       bigImgFileId:@""
                                                                      voiceDuration:[NSNumber numberWithInt:0]
                                                                              msgId:@""
                                                                           sendTime:[self getNowTimestamp]
                                                                           showTime:[self p_needShowTime:[NSDate date]]
                                                                            picSize:CGSizeMake(fixelW, fixelH)
                                                                            picType:@""
                                                                           isSender:YES
                                                           receivedSenderByYourself:NO
                                                                             status:TIMMessageStatus_Sending
                                                                         senderType:@(2)];
    //        // 创建本地消息
    //        [self addObject:messageF isSender:YES isHeaderInsert:NO];
    //        // 如果当前mqtt连接中断(包含被踢下线的情况)
    //        if ([[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
    //            // 发送中
    //            [self messageSending:messageF];
    //        } else {
    //            [self messageSendFailed:messageF];
    //            return;
    //        }
            if (self.isCloseSession) {
                
                NSString * moreInfo = [[OnlineDataSave shareOnlineDataSave] getConnectAdParams];
                [[OnlineRequestManager sharedCustomerManager]visitorReadyWithDict:[kitUtils dictionaryWithJsonString:moreInfo]
                                                                          success:^(TOSSessionInfoModel * _Nonnull sessModel) {
                    @StrongObj(self);
    //                self.chatCloseView.hidden = YES;
    //                self.chatBoxVC.view.hidden = NO;
                    
                    /// 保存会话在线状态事件
                    [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[sessModel yy_modelToJSONString]];
                    self.offlineType = offlineMessageTypeVideo;
                    self.offlineNewMessage = messageF;
                    self.offlineMediaData = videoData;
    //                [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVideoMessageWithVideoData:videoData success:^(NSString * _Nonnull messageId) {
    //                    @strongify(self);
    //                    messageF.model.message.messageId = messageId;
    //                    [self updateOldMessageId:messageId];
    //                    [self messageSendSucced:messageF];
    //        //            [self innerScrollToBottom];
    //                } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
    //                    @strongify(self);
    //                    [self messageSendFailed:messageF];
    //        //            [self innerScrollToBottom];
    //
    //                    int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
    //                    NSString * strShow = [NSString stringWithFormat:@"文件过大，请发送小于%dM文件",sizeLimitM];
    //                    [WHToast showMessage:errorDes?:strShow duration:2 finishHandler:^{
    //
    //                    }];
    //                }];
                    
                } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                    
                }];
            }
            else {
                // 创建本地消息
                [self addObject:messageF isSender:YES isHeaderInsert:NO];
                // 如果当前mqtt连接中断(包含被踢下线的情况)
                if ([[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
                    // 发送中
                    [self messageSending:messageF];
                    
                    [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVideoMessageWithVideoData:videoData success:^(NSString * _Nonnull messageId) {
                        @StrongObj(self);
                        messageF.model.message.messageId = messageId;
                        [self updateOldMessageId:messageId];
                        [self messageSendSucced:messageF];
                        self.reloadTaskManager.isScroll = YES;
                        [self.reloadTaskManager startCountdown];
                    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                        @StrongObj(self);
                        [self messageSendFailed:messageF];
                        self.reloadTaskManager.isScroll = YES;
                        [self.reloadTaskManager startCountdown];

                        int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
                        NSString * strShow = [NSString stringWithFormat:@"文件过大，请发送小于%dM文件",sizeLimitM];
                        [WHToast showMessage:errorDes?:strShow duration:2 finishHandler:^{
                            
                        }];
                    }];
                } else {
                    [self messageSendFailed:messageF];
                }
            }
        }];
    }
}

// send image message
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
           sendGifImageMessage:(UIImage *)image
                     imagePath:(NSString *)imgPath
{
//    NSString * sreGetMsgUUID = [[imgPath lastPathComponent] stringByDeletingPathExtension];
    if (image && imgPath) {
        CGFloat fixelW = image.size.width;//CGImageGetWidth(image.CGImage);
        CGFloat fixelH = image.size.width;//CGImageGetHeight(image.CGImage);
//        NSDictionary * contentImage = @{
//            @"type":kGIFTimageType
//        };

        @WeakObj(self);
        [self.reloadTaskManager startTaskWithScroll:YES completion:^{
            @StrongObj(self);
            
            TIMImageMessage * imageMsg = [[TIMImageMessage alloc] initMessageWithImageURI:imgPath extra:@""];
            
            TOSMessage * timMsg = [[TOSMessage alloc] initWithOption:[kitUtils getMsgUUID] msg_id:@"" type:@"image" senderId:[[OnlineDataSave shareOnlineDataSave] getVisitorId] receiverId:[[OnlineDataSave shareOnlineDataSave] getEndpointId] content:imageMsg msg_from:[self isGroupSession]?TIMSessionType_GROUP_CHAT:/*TIMSessionType_SINGLE_CHAT*/TIMSessionType_OnLINESERVICE_CHAT status:TIMMessageStatus_Sending timestamp:0];
    //        TIMMessage * timMsg = [[TIMMessage alloc] initWithOption:imageMsg msgUUID:[kitUtils getMsgUUID]  msg_from:[self isGroupSession]?TIMSessionType_GROUP_CHAT:TIMSessionType_SINGLE_CHAT];
            TIMMessagePushOption * pushOption = [[TIMMessagePushOption alloc] initWithOption:YES title:@"" content:@"[图片]" pushData:nil];
            TIMMessageSendOption * sendOption = [[TIMMessageSendOption alloc] initWithOption:[[OnlineDataSave shareOnlineDataSave] getEndpointId] content:timMsg pushOption:pushOption];
            NSData *imageData = UIImageJPEGRepresentation(image,1.0f);
            [[ICMediaManager sharedManager] saveImage:image msgId:timMsg.messageUUID picType:[UIImage typeForImageData:imageData]];
            
            // Kit
            CGSize imageSize = CGSizeMake(fixelW, fixelH);
            __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic originalType:@"image" content:@"[图片]" extra:@"" auditExtra:@"" path:imgPath urlPath:nil from:[[OnlineDataSave shareOnlineDataSave] getVisitorId] to:[[OnlineDataSave shareOnlineDataSave] getEndpointId] fileKey:timMsg.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:timMsg.msg_id sendTime:[self getNowTimestamp] showTime:[self p_needShowTime:[NSDate date]] picSize:imageSize picType:kGIFTimageType isSender:YES receivedSenderByYourself:NO status:@(timMsg.status).intValue senderType:@(2)];
            [self addObject:messageF isSender:YES isHeaderInsert:NO];
            @WeakObj(self)
            [[TIMClient sharedTIMClient] sendMessage:sendOption  progress:^(float progress) {
                TIMKitLog(@"send gif image progress = %f",progress);
            } success:^(TOSMessage * timMessage){
                @StrongObj(self)
                TIMKitLog(@"send gif image success messageId = %@",timMessage.msg_id);
                messageF.model.message.messageId = timMessage.msg_id;
                [self updateOldMessageId:timMessage.msg_id];
                [self messageSendSucced:messageF];
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
    //            [self scrollToBottom];
            } error:^(TOSMessage *message, TIMConnectErrorCode nErrorCode, NSString * _Nonnull errorDes) {
                @StrongObj(self)
                TIMKitLog(@"send gif image message = %@ nErrorCode = %ld errorDes= %@",message,(long)nErrorCode,errorDes);
                [self tim_showMBErrorView:errorDes];
                [self messageSendFailed:messageF];
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
    //            [self scrollToBottom];
            }];
        }];
    }
}


// send image message
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
              sendImageMessage:(UIImage *)image
                     imagePath:(NSString *)imgPath
{
    TIMKitLog(@"发送图片 path:%@ src:%@ last:%@",imgPath,image,[[imgPath lastPathComponent] stringByDeletingPathExtension]);
//    NSString * sreGetMsgUUID = [[imgPath lastPathComponent] stringByDeletingPathExtension];
    if (image) {
        CGFloat fixelW = image.size.width;// CGImageGetWidth(image.CGImage);
        CGFloat fixelH = image.size.height;// CGImageGetHeight(image.CGImage);

        NSData*uploadThumbFileData = UIImageJPEGRepresentation(image, 0.5);
        if (50 < [uploadThumbFileData length]/1000.f/1024.f) {
            return;
        }
        
        @WeakObj(self);
        [self.reloadTaskManager startTaskWithScroll:YES completion:^{
            @StrongObj(self);
            
            // Kit
            CGSize imageSize = CGSizeMake(fixelW, fixelH);
            __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic
                                                                       originalType:@"image"
                                                                            content:@"[图片]"
                                                                              extra:@""
                                                                         auditExtra:@""
                                                                               path:imgPath
                                                                            urlPath:nil
                                                                               from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                 to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                            fileKey:[[NSUUID UUID] UUIDString]
                                                                       bigImgFileId:@""
                                                                      voiceDuration:[NSNumber numberWithInt:0]
                                                                              msgId:@""
                                                                           sendTime:[self getNowTimestamp]
                                                                           showTime:[self p_needShowTime:[NSDate date]]
                                                                            picSize:imageSize
                                                                            picType:@""
                                                                           isSender:YES
                                                           receivedSenderByYourself:NO
                                                                             status:TIMMessageStatus_Sending
                                                                         senderType:@(2)];//self.session.messageStatus];
    //        [self addObject:messageF isSender:YES isHeaderInsert:NO];
    //        // 如果当前mqtt连接中断(包含被踢下线的情况)
    //        if ([[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
    //            // 发送中
    //            [self messageSending:messageF];
    //        } else {
    //            [self messageSendFailed:messageF];
    //            return;
    //        }
            
            if (self.isCloseSession) {
                @weakify(self);
                NSString * moreInfo = [[OnlineDataSave shareOnlineDataSave] getConnectAdParams];
                [[OnlineRequestManager sharedCustomerManager]visitorReadyWithDict:[kitUtils dictionaryWithJsonString:moreInfo]
                                                                          success:^(TOSSessionInfoModel * _Nonnull sessModel) {
                    @strongify(self);
    //                self.chatCloseView.hidden = YES;
    //                self.chatBoxVC.view.hidden = NO;
                    
                    /// 保存会话在线状态事件
                    [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[sessModel yy_modelToJSONString]];
                    
                    self.offlineType = offlineMessageTypeImage;
                    self.offlineNewMessage = messageF;
                    self.offlineMediaData = uploadThumbFileData;
                    
                    
    //                [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendImageMessageWithImageData:uploadThumbFileData        success:^(NSString * _Nonnull messageId,NSString * _Nonnull fileKey) {
    //                    @strongify(self);
    //                    messageF.model.message.messageId = messageId;//timMessage.msg_id;
    //                    [self updateOldMessageId:messageId];
    //                    [self messageSendSucced:messageF];
    //        //            [self scrollToBottom];
    //                } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
    //                    [self messageSendFailed:messageF];
    //        //            [self scrollToBottom];
    //                }];
                    
                } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                    
                }];
                
            }
            else {
                [self addObject:messageF isSender:YES isHeaderInsert:NO];
                // 如果当前mqtt连接中断(包含被踢下线的情况)
                if ([[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
                    // 发送中
                    [self messageSending:messageF];
                    
                    [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendImageMessageWithImageData:uploadThumbFileData        success:^(NSString * _Nonnull messageId,NSString * _Nonnull fileKey) {
                        messageF.model.message.messageId = messageId;//timMessage.msg_id;
                        [self updateOldMessageId:messageId];
                        [self messageSendSucced:messageF];
                        self.reloadTaskManager.isScroll = YES;
                        [self.reloadTaskManager startCountdown];
            //            [self scrollToBottom];
                    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                        [self messageSendFailed:messageF];
                        self.reloadTaskManager.isScroll = YES;
                        [self.reloadTaskManager startCountdown];
            //            [self scrollToBottom];
                    }];
                } else {
                    [self messageSendFailed:messageF];
                }
            }
        }];
    }
}

- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController sendFileMessage:(NSString *)fileName {
}

// send text message
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
               sendTextMessage:(NSString *)messageStr
{
    // 如果已有延迟任务，取消它
    if (self.delayedSendHintEndBlock) {
        dispatch_block_cancel(self.delayedSendHintEndBlock);
        self.delayedSendHintEndBlock = nil;
    }
        
    // 创建新的延迟任务
    @WeakObj(self);
    self.delayedSendHintEndBlock = dispatch_block_create(0, ^{
        @StrongObj(self);
        [self sendInputHintEndEvent];
    });
        
    // 延迟1秒执行任务
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), self.delayedSendHintEndBlock);
    
    [self sendTextMessagewithText:messageStr knowledge:@"" sysTransfer:NO];
}

-(void)sendTextMessagewithText:(NSString*)messageStr knowledge:(NSString *)knowledge sysTransfer:(BOOL)sysTransfer intent:(NSString *)intent {
    if (!self.chatCloseView.hidden) {   //在会话结束状态下，不再发送消息
        return;
    }
    NSCharacterSet *set = [NSCharacterSet whitespaceAndNewlineCharacterSet];
    NSString *trimedString = [messageStr stringByTrimmingCharactersInSet:set];
    if([trimedString length] == 0) {
        [self.chatBoxVC.chatBox.textView resignFirstResponder];
        [self.view tim_showMBErrorView:@"不能发送空白消息"];
        return;
    }
    
    @WeakObj(self);
    [self.reloadTaskManager startTaskWithScroll:YES completion:^{
        @StrongObj(self);
        
        // Kit
        __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText
                                                                   originalType:@"text"
                                                                        content:messageStr
                                                                          extra:@""
                                                                     auditExtra:@""
                                                                           path:nil
                                                                        urlPath:nil
                                                                           from:[[OnlineDataSave shareOnlineDataSave]getVisitorId]
                                                                             to:[[OnlineDataSave shareOnlineDataSave]getEndpointId]
                                                                        fileKey:[[NSUUID UUID] UUIDString]
                                                                   bigImgFileId:@""
                                                                  voiceDuration:[NSNumber numberWithInt:0]
                                                                          msgId:[NSString stringWithFormat:@"%ld",[self getNowTimestamp]]
                                                                       sendTime:[self getNowTimestamp]
                                                                       showTime:[self p_needShowTime:[NSDate date]]
                                                                        picSize:CGSizeMake(0, 0)
                                                                        picType:@""
                                                                       isSender:YES
                                                       receivedSenderByYourself:NO
                                                                         status:TIMMessageStatus_Sending
                                                                     senderType:@(2)];
        messageF.model.message.knowledge = knowledge;
        messageF.model.message.intent = intent;
        messageF.model.message.sysTransfer = sysTransfer;
    //    [self addObject:messageF isSender:YES isHeaderInsert:NO];
    //    // 发送中
    //    [self messageSending:messageF];
//
//        [NSObject cancelPreviousPerformRequestsWithTarget:self selector:@selector(sendInputHintEvent) object:nil];
//        [self performSelector:@selector(sendInputHintEvent) withObject:nil afterDelay:0.5];
        
//        CFRunLoopRunInMode(kCFRunLoopDefaultMode, 0.6, false);
        
        if (self.isCloseSession) {
            
            NSString * moreInfo = [[OnlineDataSave shareOnlineDataSave] getConnectAdParams];
            [[OnlineRequestManager sharedCustomerManager]visitorReadyWithDict:[kitUtils dictionaryWithJsonString:moreInfo]
                                                                      success:^(TOSSessionInfoModel * _Nonnull sessModel) {
                
                @StrongObj(self);
    //            self.chatCloseView.hidden = YES;
    //            self.chatBoxVC.view.hidden = NO;

                /// 保存会话在线状态事件
                [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[sessModel yy_modelToJSONString]];
                
                self.offlineType = offlineMessageTypeText;
                self.offlineNewMessage = messageF;
                
                
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                
            }];
        }
        else {
            
            [self addObject:messageF isSender:YES isHeaderInsert:NO];
            // 发送中
            [self messageSending:messageF];
            
            //lib
            [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendTextMessageWithMessageStr:messageStr knowledge:knowledge sysTransfer:sysTransfer intent:intent messageUUID:messageF.model.message.fileKey success:^(TOSMessage * timMessage) {
                @StrongObj(self);
                messageF.model.message.messageId = timMessage.msg_id;
                [self updateOldMessageId:timMessage.msg_id];
                [self messageSendSucced:messageF];
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
        //        [self innerScrollToBottom];
                //发送成功预知设置为空
                
        //        [NSObject cancelPreviousPerformRequestsWithTarget:self selector:@selector(sendInputHintEvent) object:nil];
        //        [self performSelector:@selector(sendInputHintEvent) withObject:nil afterDelay:0.5];
                
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                @StrongObj(self);
                [self messageSendFailed:messageF];
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
        //        [self innerScrollToBottom];
                
                //发送成功预知设置为空
        //        [NSObject cancelPreviousPerformRequestsWithTarget:self selector:@selector(sendInputHintEvent) object:nil];
        //        [self performSelector:@selector(sendInputHintEvent) withObject:nil afterDelay:0.5];
                
            
            }];
        }
    }];
}

-(void)sendTextMessagewithText:(NSString*)messageStr knowledge:(NSString *)knowledge sysTransfer:(BOOL)sysTransfer {
    
    [self sendTextMessagewithText:messageStr knowledge:knowledge sysTransfer:sysTransfer intent:@""];
}

//发送输入预知结束事件
-(void)sendInputHintEndEvent{
    [[OnlineEventSendManager sharedOnlineEventSendManager]inputHintEventWithText:@"" success:^{
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
    }];
}

//发送访客上线事件
-(void)sendChatOnline{
    
    [[OnlineEventSendManager sharedOnlineEventSendManager] chatOnlineEventWithSuccess:^{
        //访客上线
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        //访客上线失败
    }];

}


#pragma mark - 对于重写了会话结束UI方法后的机器人欢迎语消息排序问题解决方案（如果没有机器人欢迎语消息会有问题，如果是人工座席接待遇到同样的问题就需要修改里面的逻辑了）
/// 发送再次建立链接的离线消息
- (void)sendOfflineNewMessage {
    
    NSLog(@"sendOfflineNewMessage");
    
    TinetChatStatusType type = [[TOSClientKit sharedTOSKit] getCurrentOnlineStatus];
    /// 当前不是机器人状态不需要走下面的逻辑。
    if (type != TinetChatStatusTypeRobot) {
        return;
    }
    
    /// 没有记录的不需要走后续的发送逻辑
    if (self.offlineType == offlineMessageTypeNormal) {
        return;
    }
    
    __block TIMMessageFrame * messageF = self.offlineNewMessage;
    /// 添加消息到列表中
    [self addObject:messageF isSender:YES isHeaderInsert:NO];
    
    /// 如果当前mqtt连接中断(包含被踢下线的情况)
    if ([[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
        /// 发送中
        [self messageSending:messageF];
        self.reloadTaskManager.isScroll = YES;
        [self.reloadTaskManager startCountdown];
    } else {
        /// 发送失败
        [self messageSendFailed:messageF];
        self.reloadTaskManager.isScroll = YES;
        [self.reloadTaskManager startCountdown];
        return;
    }
    
    
    @weakify(self);
    if (self.offlineType == offlineMessageTypeText) {
        //lib
        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendTextMessageWithMessageStr:messageF.model.message.content knowledge:messageF.model.message.knowledge sysTransfer:messageF.model.message.sysTransfer intent:messageF.model.message.intent messageUUID:messageF.model.message.fileKey success:^(TOSMessage * timMessage) {
            @strongify(self);
            messageF.model.message.messageId = timMessage.msg_id;
            [self updateOldMessageId:timMessage.msg_id];
            [self messageSendSucced:messageF];
            self.reloadTaskManager.isScroll = YES;
            [self.reloadTaskManager startCountdown];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineNewMessage = nil;
            
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            @strongify(self);
            [self messageSendFailed:messageF];
            self.reloadTaskManager.isScroll = YES;
            [self.reloadTaskManager startCountdown];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineNewMessage = nil;
            
        }];
    }
    else if (self.offlineType == offlineMessageTypeVoice) {
        [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVoiceMessageWithVoiceData:self.offlineMediaData success:^(NSString * _Nonnull messageId) {
            @strongify(self);
            messageF.model.message.messageId = messageId;
            [self updateOldMessageId:messageId];
            [self messageSendSucced:messageF];
            self.reloadTaskManager.isScroll = YES;
            [self.reloadTaskManager startCountdown];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineMediaData = nil;
            self.offlineNewMessage = nil;
            
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            @strongify(self);
            [self messageSendFailed:messageF];
            self.reloadTaskManager.isScroll = YES;
            [self.reloadTaskManager startCountdown];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineMediaData = nil;
            self.offlineNewMessage = nil;
        }];
    }
    else if (self.offlineType == offlineMessageTypeImage) {
        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendImageMessageWithImageData:self.offlineMediaData        success:^(NSString * _Nonnull messageId,NSString * _Nonnull fileKey) {
            @strongify(self);
            messageF.model.message.messageId = messageId;//timMessage.msg_id;
            [self updateOldMessageId:messageId];
            [self messageSendSucced:messageF];
            self.reloadTaskManager.isScroll = YES;
            [self.reloadTaskManager startCountdown];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineMediaData = nil;
            self.offlineNewMessage = nil;
            
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            @strongify(self);
            [self messageSendFailed:messageF];
            self.reloadTaskManager.isScroll = YES;
            [self.reloadTaskManager startCountdown];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineMediaData = nil;
            self.offlineNewMessage = nil;
        }];
    }
    else if (self.offlineType == offlineMessageTypeVideo) {
        [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVideoMessageWithVideoData:self.offlineMediaData success:^(NSString * _Nonnull messageId) {
            @strongify(self);
            messageF.model.message.messageId = messageId;
            [self updateOldMessageId:messageId];
            [self messageSendSucced:messageF];
            self.reloadTaskManager.isScroll = YES;
            [self.reloadTaskManager startCountdown];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineMediaData = nil;
            self.offlineNewMessage = nil;
            
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            @strongify(self);
            [self messageSendFailed:messageF];
            self.reloadTaskManager.isScroll = YES;
            [self.reloadTaskManager startCountdown];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineMediaData = nil;
            self.offlineNewMessage = nil;

            int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
            NSString * strShow = [NSString stringWithFormat:@"文件过大，请发送小于%dM文件",sizeLimitM];
            [WHToast showMessage:errorDes?:strShow duration:2 finishHandler:^{
                
            }];
        }];
    }
    
}

#pragma mark - TIMChatBoxViewControllerDelegate
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
                    senderFile:(NSInteger)senderFile
                   openFileApp:(BOOL)openFileApp {
    
    if (openFileApp) {
        
        // 初始化文档选择器，并指定文件类型
        UIDocumentPickerViewController *documentPicker = [[UIDocumentPickerViewController alloc] initWithDocumentTypes:@[@"public.content", @"public.item"] inMode:UIDocumentPickerModeImport];
        // 设置委托
        documentPicker.delegate = self;
        documentPicker.modalPresentationStyle = UIModalPresentationFullScreen;
        // 显示文件选择器
        [self presentViewController:documentPicker animated:YES completion:nil];
    } else {
        TIMSYCacheFileViewController *cacheVC = [[TIMSYCacheFileViewController alloc] init];
        [TIMSYCacheFileManager shareManager].showImageShuffling = YES;
        cacheVC.showType = 0;
        [self.navigationController pushViewController:cacheVC animated:YES];
    }
}

//转人工快捷方式
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
                  zhuanRenGong:(NSInteger)defaultItem{
    //发送转人工文字消息
    [self sendTextMessagewithText:@"转人工" knowledge:@"" sysTransfer:YES];
}

- (void)sendTransferToHumanMessage:(NSString *)messageStr {
    [self sendTextMessagewithText:messageStr knowledge:@"" sysTransfer:YES];
}

//结束会话快捷方式
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
                     closeChat:(NSInteger)defaultItem{
    
    [self.chatBoxVC.chatBox.textView resignFirstResponder];
    self.chatBoxVC.chatBox.textView.text = @"";
    [self.chatBoxVC.chatBox checkSendButtonType];
    [[OnlineDataSave shareOnlineDataSave] saveUserInputText:@""];
    
    [self.chatBoxVC.chatBox switchTextEditing];
    
    [self closeChatActon];
}

/// 自定义按钮
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController item:(ICChatBoxMoreViewItem *)item {
    TOSKitExtendBoardItemModel *model = [[TOSKitExtendBoardItemModel alloc] init];
    model.title = item.title;
    model.image = item.imageName;
    model.index = item.index;
    model.type = item.type;
    [self didClinkCustomExtendBoardItemAction:model];
}

- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController sendVoiceMessage:(NSString *)voicePath {
    TIMKitLog(@"sendVoiceMessage");
    [self timerInvalue]; // 销毁定时器
    self.voiceHud.hidden = YES;
    if ([TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordView != nil) {
        [self removeCustomVoiceHud];
    }
    if (voicePath) {
        // 获取时长
        int voiceDuration = (int)[[ICRecordManager shareManager] durationWithVideo:[NSURL fileURLWithPath:voicePath]];
        if (voiceDuration < 1000) {
            // 小于一秒的显示1秒
            voiceDuration = 1000;
        }
        
        
        NSData*voiceData = [NSData dataWithContentsOfFile:voicePath];
        if (50 < [voiceData length]/1000.f/1024.f) {
            return;
        }
        
        @WeakObj(self);
        [self.reloadTaskManager startTaskWithScroll:YES completion:^{
            @StrongObj(self);
            
            // Kit
            __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVoice
                                                                       originalType:@"voice"
                                                                            content:@"[语音]"
                                                                              extra:@""
                                                                         auditExtra:@""
                                                                               path:voicePath
                                                                            urlPath:nil
                                                                               from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                 to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                            fileKey:[[NSUUID UUID] UUIDString]
                                                                       bigImgFileId:@""
                                                                      voiceDuration:[NSNumber numberWithInt:voiceDuration]
                                                                              msgId:@""
                                                                           sendTime:[self getNowTimestamp]
                                                                           showTime:[self p_needShowTime:[NSDate date]]
                                                                            picSize:CGSizeZero
                                                                            picType:@""
                                                                           isSender:YES
                                                           receivedSenderByYourself:NO
                                                                             status:TIMMessageStatus_Sending
                                                                         senderType:@(2)];//self.session.messageStatus];
    //        [self addObject:messageF isSender:YES isHeaderInsert:NO];
    //        // 如果当前mqtt连接中断(包含被踢下线的情况)
    //
    //        if ([[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
    //            // 发送中
    //            [self messageSending:messageF];
    //        } else {
    //            [self messageSendFailed:messageF];
    //            return;
    //        }
            
            if (self.isCloseSession) {
                @weakify(self);
                NSString * moreInfo = [[OnlineDataSave shareOnlineDataSave] getConnectAdParams];
                [[OnlineRequestManager sharedCustomerManager]visitorReadyWithDict:[kitUtils dictionaryWithJsonString:moreInfo]
                                                                          success:^(TOSSessionInfoModel * _Nonnull sessModel) {
                    
                    @strongify(self);
        //            self.chatCloseView.hidden = YES;
        //            self.chatBoxVC.view.hidden = NO;

                    /// 保存会话在线状态事件
                    [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[sessModel yy_modelToJSONString]];
                    
                    self.offlineType = offlineMessageTypeVoice;
                    self.offlineNewMessage = messageF;
                    self.offlineMediaData = voiceData;
                    
                    
                } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                    
                }];
            }
            else {
                [self addObject:messageF isSender:YES isHeaderInsert:NO];
                // 如果当前mqtt连接中断(包含被踢下线的情况)

                if ([[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
                    // 发送中
                    [self messageSending:messageF];
                    [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVoiceMessageWithVoiceData:voiceData success:^(NSString * _Nonnull messageId) {
                        messageF.model.message.messageId = messageId;
                        [self updateOldMessageId:messageId];
                        [self messageSendSucced:messageF];
                        self.reloadTaskManager.isScroll = YES;
                        [self.reloadTaskManager startCountdown];
                    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                        [self messageSendFailed:messageF];
                        self.reloadTaskManager.isScroll = YES;
                        [self.reloadTaskManager startCountdown];
            //            [self innerScrollToBottom];
                    }];
                } else {
                    [self messageSendFailed:messageF];
                }
            }
        }];
    }
}


- (void)voiceDidCancelRecording {
    [self timerInvalue];
    if ([TOSKitCustomInfo shareCustomInfo].chatBox_voiceCancelRecordView != nil) {
        [self removeCustomVoiceHud];
    }
    else {
        self.voiceHud.hidden = YES;
    }
//    self.voiceHud.hidden = YES;
}


- (void)voiceDidStartRecording {
    [self timerInvalue];
    
    if ([TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordView != nil) {
        [self.view addSubview:[TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordView];
    }
    else {
        self.voiceHud.hidden = NO;
    }
    
//    self.voiceHud.hidden = NO;
    [self timer];
}


- (void)voiceRecordSoShort {
    [self timerInvalue];
    
    if ([TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordSoShortView != nil) {
        
        [self removeCustomVoiceHud];
        [self.view addSubview:[TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordSoShortView];
        @WeakObj(self)
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)([TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordSoShortTime * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            @StrongObj(self)
            [[TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordSoShortView removeFromSuperview];
            [self removeCustomVoiceHud];
        });
    }
    else {
        self.voiceHud.animationImages = nil;
        self.voiceHud.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"voiceShort"]];
        @WeakObj(self)
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.0 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            @StrongObj(self)
            self.voiceHud.hidden = YES;
        });
    }
    
    
}


- (void)voiceWillDragout:(BOOL)inside {
    if (inside) {
        
        if ([TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordView != nil) {
            [_timer setFireDate:[NSDate distantPast]];
            [self removeCustomVoiceHud];
            [self.view addSubview:[TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordView];
        }
        else {
            [_timer setFireDate:[NSDate distantPast]];
            _voiceHud.image  = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"voice_1"]];
        }
        
//        [_timer setFireDate:[NSDate distantPast]];
//        _voiceHud.image  = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"voice_1"]];
    } else {
        if ([TOSKitCustomInfo shareCustomInfo].chatBox_voiceCancelRecordView != nil) {
            [_timer setFireDate:[NSDate distantPast]];
            [self removeCustomVoiceHud];
            [self.view addSubview:[TOSKitCustomInfo shareCustomInfo].chatBox_voiceCancelRecordView];
            
        }
        else {
            [_timer setFireDate:[NSDate distantFuture]];
            self.voiceHud.animationImages  = nil;
            self.voiceHud.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"cancelVoice"]];
        }
//        [_timer setFireDate:[NSDate distantFuture]];
//        self.voiceHud.animationImages  = nil;
//        self.voiceHud.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"cancelVoice"]];
    }
}

/// 移除自定义的语音录制hud视图
- (void)removeCustomVoiceHud {
    for (UIView * item in self.view.subviews) {
        if (item == [TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordView) {
            [item removeFromSuperview];
        }
        if (item == [TOSKitCustomInfo shareCustomInfo].chatBox_voiceCancelRecordView) {
            [item removeFromSuperview];
        }
    }
}



- (void)progressChange
{
    AVAudioRecorder *recorder = [[ICRecordManager shareManager] recorder] ;
    [recorder updateMeters];
    float power= [recorder averagePowerForChannel:0];//取得第一个通道的音频，注意音频强度范围时-160到0,声音越大power绝对值越小
    CGFloat progress = (1.0/160)*(power + 160);
    self.voiceHud.progress = progress;
}

- (void)timerInvalue
{
    [_timer invalidate];
    _timer  = nil;
}

- (NSTimer *)timer
{
    if (!_timer) {
        _timer =[NSTimer scheduledTimerWithTimeInterval:0.3f target:self selector:@selector(progressChange) userInfo:nil repeats:YES];
    }
    return _timer;
}

- (ICVoiceHud *)voiceHud
{
    if (!_voiceHud) {
        _voiceHud = [[ICVoiceHud alloc] initWithFrame:CGRectMake(0, 0, 155, 155)];
        
        _voiceHud.hidden = YES;
        [self.view addSubview:_voiceHud];
        _voiceHud.center = CGPointMake(App_Frame_Width/2, APP_Frame_Height/2);
    }
    return _voiceHud;
}

#pragma mark - Getter and Setter
- (NSInteger)firstHasGetHistoryListFinish {
    __block NSInteger firstHasGetHistoryListFinish;
    dispatch_sync(self.propertyQueue, ^{
        firstHasGetHistoryListFinish = _firstHasGetHistoryListFinish;
    });
    return firstHasGetHistoryListFinish;
}

- (void)setFirstHasGetHistoryListFinish:(NSInteger)firstHasGetHistoryListFinish {
    dispatch_sync(self.propertyQueue, ^{
        _firstHasGetHistoryListFinish = firstHasGetHistoryListFinish;
    });
}

- (TIMChatBoxViewController *) chatBoxVC
{
    if (_chatBoxVC == nil) {
        _chatBoxVC = [[TIMChatBoxViewController alloc] init];
        _chatBoxVC.barItemArray = self.quickEntryAllItems;
        CGFloat barHeight = self.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
        [_chatBoxVC.view setFrame:CGRectMake(0,APP_Frame_Height-[TOSKitCustomInfo shareCustomInfo].chatBox_Height-kNavTop-kBottomBarHeight-barHeight, App_Frame_Width, APP_Frame_Height)];
        _chatBoxVC.delegate = self;
                
        //tableviewHeight:APP_Frame_Height-HEIGHT_TABBAR-kNavTop-kBottomBarHeight
//        _chatBoxVC.view.backgroundColor = [UIColor redColor];
    }
    return _chatBoxVC;
}

-(UIView *)chatCloseView{
    if (!_chatCloseView) {
        CGFloat barHeight = self.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
        _chatCloseView = [[UIView alloc]initWithFrame:CGRectMake(0,APP_Frame_Height-[TOSKitCustomInfo shareCustomInfo].chatBox_Height-kNavTop-kBottomBarHeight-barHeight, SCREEN_WIDTH, [TOSKitCustomInfo shareCustomInfo].chatBox_Height+barHeight)];
        _chatCloseView.backgroundColor = TOSHexAColor(0xC8CBCE,1.0);
    }
    return _chatCloseView;
}
-(void)openAction{
    
    /*访客初始化回调
     当APP端IM mqtt 连接就绪，主动通知服务端，APP端调用该接口后服务端
     就开始会话流程逻辑创建会话*/
    // 此处不会走KT环境
    NSString * moreInfo = [[OnlineDataSave shareOnlineDataSave] getConnectAdParams];
    
    NSArray *readyDisplayDataSource = [self.readyDisplayDataSource mutableCopy];
    @WeakObj(self);
    [readyDisplayDataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
        @StrongObj(self);
        if (obj.model.message.type == TypeRobotCombination) {
            
            NSArray <CombinationMessage *>*richModels = (NSMutableArray <CombinationMessage *>*)obj.model.message.content;
            
            __block BOOL updateModel = NO;
            [richModels enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                if (obj.clickLikeFeature) {
                    obj.clickLikeFeature = NO;
                    updateModel = YES;
                 }
            }];
            
            if (updateModel) {
                obj.model = obj.model;
            }
        }
    }];
    self.readyDisplayDataSource = [NSMutableArray arrayWithArray:readyDisplayDataSource];
    
    [self syncDisplayData];
    [self.tableView reloadData];
    
    [[OnlineRequestManager sharedCustomerManager]visitorReadyWithDict:[kitUtils dictionaryWithJsonString:moreInfo]
                                                              success:^(TOSSessionInfoModel * _Nonnull sessModel) {
        /// 添加商品卡片
        [self addCommodityCardMessage];
        self.chatCloseView.hidden = YES;
        self.chatBoxVC.view.hidden = NO;
        /// 保存会话在线状态事件
        [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[sessModel yy_modelToJSONString]];
        
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        
    }];

}

-(TOSBaseTableView *)tableView
{
    if (nil == _tableView) {
        _tableView = [[TOSBaseTableView alloc] initWithFrame:CGRectZero style:UITableViewStylePlain];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.backgroundView = nil;

        UIView* tempView = [[UIView alloc] initWithFrame:_tableView.frame];
        tempView.backgroundColor= [TOSKitCustomInfo shareCustomInfo].chat_backGround;
        _tableView.backgroundView= tempView;
        _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
        if (@available(iOS 15.0, *)) {
            _tableView.sectionHeaderTopPadding = 0.f;
            [UITableView appearance].sectionHeaderTopPadding = 0.f;
        }
        _tableView.estimatedRowHeight = 0.f;
        _tableView.estimatedSectionHeaderHeight = 0.f;
        _tableView.estimatedSectionFooterHeight = 0.f;
    }
    return _tableView;
}

- (NSMutableArray *)dataSource
{
    if (_dataSource == nil) {
        _dataSource = [NSMutableArray array];
    }
    return _dataSource;
}

- (NSMutableArray *)readyDisplayDataSource {
    if (!_readyDisplayDataSource) {
        _readyDisplayDataSource = [NSMutableArray array];
    }
    return _readyDisplayDataSource;
}

#pragma mark - 接收Chat的刷新通知 来自lib层
- (void)messageSendedUpdateUIFromLibNotification:(NSNotification *)sender {
    TOSMessage *recvMessage = [sender object];
    @WeakObj(self);
    NSArray *readyDisplayDataSource = [self.readyDisplayDataSource mutableCopy];
    [readyDisplayDataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
        @StrongObj(self);
        if (![kitUtils isBlankString:recvMessage.messageUUID] && [obj.model.message.fileKey isEqualToString:recvMessage.messageUUID]) {
            obj.model.message.messageId = recvMessage.msg_id;
            [self messageSendSucced:obj];
            self.reloadTaskManager.isScroll = NO;
            [self.reloadTaskManager startCountdown];
         }
    }];
    self.readyDisplayDataSource = [NSMutableArray arrayWithArray:readyDisplayDataSource];
}

#pragma mark - 接收Chat的刷新通知
- (void)messageSendedUpdateUINotification:(NSNotification *)sender {

    TOSMessage *message = [sender object];
    if (message.msg_from == /*TIMSessionType_SINGLE_CHAT*/TIMSessionType_OnLINESERVICE_CHAT) {
//        if ([self isGroupSession] || message.senderId != [[OnlineDataSave shareOnlineDataSave] getVisitorId]) {
        if ([self isGroupSession] || [message.senderId isEqualToString:[[OnlineDataSave shareOnlineDataSave] getVisitorId]] == NO) {

            // 如果是群聊或者发送者Id不是用户Id则退出
            return;
        }
        if ([message.receiverId isEqualToString:[[OnlineDataSave shareOnlineDataSave] getVisitorId]]) {
            // 如果接收的消息 返回
            return;
        }
    }else{
        // 除了单聊就是群聊
        if ([self isGroupSession] && [message.receiverId isEqualToString:[[OnlineDataSave shareOnlineDataSave] getEndpointId]]) {
            if (![message.senderId isEqualToString:[[OnlineDataSave shareOnlineDataSave] getVisitorId]]) {
                // 如果接收的消息 返回
                return;
            }
        }else{
            return;
        }
    }
    if ([message.type isEqualToString:@"text"]) {
        TIMTextMessage * txtMsg = (TIMTextMessage *)message.content;
        [self sendTextMessageWithContent:TypeText originalType:message.type from:message.senderId to:message.receiverId fileKey:message.messageUUID msgId:message.msg_id sendTime:message.timestamp content:txtMsg.content extra:txtMsg.extra];
    }else if([message.type isEqualToString:@"customize"]){
        TIMCustomizeMessage * customMsg = (TIMCustomizeMessage *)message.content;
        NSDictionary * bodyDic;
        NSString *type;
        if ([customMsg.templateName isEqualToString:@"tim-rtcMedia"]) {
            NSDictionary * rtcMediaBody = [kitUtils dictionaryWithJsonString:customMsg.content];
            bodyDic = @{
                  @"template":customMsg.templateName,
                  @"body":@{
                          @"rtcMediaVersion":rtcMediaBody[@"rtcMediaVersion"],
                          @"onlyAudio":rtcMediaBody[@"onlyAudio"],
                          @"duration":rtcMediaBody[@"duration"],
                          @"stopAction":rtcMediaBody[@"stopAction"],
                  },
                  @"extra":customMsg.extra
            };
            type = TypeCustom;
        } else if ([customMsg.templateName isEqualToString:@"tim-bmwReportFile"]) {
            bodyDic = @{
                  @"template":customMsg.templateName,
                  @"body":customMsg.bodyDic,
                  @"extra":customMsg.extra
            };
            type = TypeCustomFile;
        } else if ([customMsg.templateName isEqualToString:@"default"]) {
            bodyDic = @{
                  @"template":customMsg.templateName,
                  @"body":@{
                          @"imgUrl":customMsg.imgUrl,
                          @"shareTitle":customMsg.shareTitle,
                          @"title":customMsg.title,
                          @"content":customMsg.content,
                  },
                  @"extra":customMsg.extra
            };
            type = TypeCustom;
        } else {
            bodyDic = @{
                  @"template":customMsg.templateName,
                  @"body":customMsg.bodyDic,
                  @"extra":customMsg.extra
            };
            type = TypeCustom;
        }
        [self sendTextMessageWithContent:type originalType:message.type from:message.senderId to:message.receiverId fileKey:message.messageUUID msgId:message.msg_id sendTime:[self getNowTimestamp] content:[kitUtils DataTOjsonString:bodyDic]];
    }
}

#pragma mark - 刷新消息为已读

//- (void)updateUnreadMessageNotification:(NSNotification *)sender {
//    TOSMessage *message = [sender object];
//    NSArray *arr = self.dataSource.copy;
//    [arr enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
//        if ([obj.model.message.messageId isEqualToString:message.msg_id]) {
//            TIMMessageFrame *msgF = obj;
//            msgF.model.message.status = 3;
//            [self.dataSource replaceObjectAtIndex:idx withObject:msgF];
//                *stop = YES;
//
//        }
//    }];
//    [self.tableView reloadData];
//
//}

#pragma mark - 重新编辑的通知
- (void)recalledMessageAgainEditNotification:(NSNotification *)sender {
    NSString *str = [sender object];
    [self.chatBoxVC.chatBox switchTextEditing];
    [self.chatBoxVC.chatBox.textView transalteStringEmoticonAttributedWithString:str];
}

#pragma mark - 接收Chat的消息
- (void)messageReceivedNotification:(NSNotification *)sender {
    TOSMessage *message = [sender object];
    NSLog(@"message ====== %@",[message yy_modelToJSONObject]);

    // TODO 暂时将已读消息在此发送

    TIMMessageReadOption * readOption = [[TIMMessageReadOption alloc] initWithOption:message.senderId targetId:message.receiverId latestMessageId:message.msg_id];

    // 增加msg_id为空的判断 直接返回
    if ([readOption.senderId isEqualToString:[[OnlineDataSave shareOnlineDataSave] getVisitorId]]) {

        return;
    }
    // 发送已读事件
    
    if ([[TOSClientKit sharedTOSKit] getStateActive] && self.currentTop) {//判断是否在前台活跃
        [[OnlineRequestManager sharedCustomerManager] sessionInfoReadWithMainUniqueId:[[OnlineDataSave shareOnlineDataSave] getMainUniqueId]];
    }
    
    __block TOSSessionInfoModel *sessionInfomodel = [[TOSClientKit sharedTOSKit] getCurrentSessionInfo];
    
//    BOOL messageRecvDirection = YES;// 判断是收发消息
    
//    if ([message.type isEqualToString:@"text"]) {
//        TIMTextMessage * txtMsg = (TIMTextMessage *)message.content;
//
//        [self analysisMessageWithExtra:txtMsg.extra messageRecvDirection:messageRecvDirection object:message isHeaderInsert:NO];
//        [self scrollToBottom];
//    }

    @WeakObj(self);
    [self.reloadTaskManager startTaskWithScroll:YES completion:^{
        @StrongObj(self);
        if ([message.type isEqualToString:@"ChatLeadingWordsMessage"]) {//引导语
            ChatLeadingWordsMessage * robotBridgeMsg = (ChatLeadingWordsMessage *)message.content;
            //用系统消息展示
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeSystem originalType:message.type content:robotBridgeMsg.textContent extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
        } else if ([message.type isEqualToString:@"TIMCommodityCardMessage"]) {
            
            TIMCommodityCardMessage *commodityMsg = (TIMCommodityCardMessage *)message.content;
            
            BOOL isSender = NO;
            NSString *senderId;
            NSString *receiverId;
            if ([commodityMsg.senderType isEqualToString:@"2"]) {
                isSender = YES;
                senderId = message.receiverId;
                receiverId = message.senderId;
            } else {
                isSender = NO;
                senderId = message.senderId;
                receiverId = message.receiverId;
            }
            
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCommodityCardDetails originalType:message.type content:commodityMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:senderId to:receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:isSender receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            [self addObject:messageF isSender:isSender isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
            
        } else if ([message.type isEqualToString:@"TIMLogisticsCardMessage"]) {
            
            TIMLogisticsCardMessage *cardMsg = (TIMLogisticsCardMessage *)message.content;
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeLogisticsCard originalType:message.type content:(NSString *)cardMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageSendSucced:messageF];
            
        } else if ([message.type isEqualToString:@"ChatSmallProgramMessage"]) {
            
            TOSMessageSmallProgramModel *smallProgramMsg = (TOSMessageSmallProgramModel *)message.content;
            
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeSmallProgramCard originalType:message.type content:(NSString *)smallProgramMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageSendSucced:messageF];
        } else if ([message.type isEqualToString:@"ChatInvestigationMessage"]) {//满意度评价
            sessionInfomodel.status = [NSNumber numberWithInt:6];
            ChatInvestigationMessage * InvestigationMsg = (ChatInvestigationMessage *)message.content;
            
            if (InvestigationMsg.investigationInviteType.integerValue == 1) {
                
            } else {
                // 增加满意度评价的文案
                NSString *extra = [kitUtils convertToJsonDataWithDic:@{@"alreadyInvestigation" : @"0", @"uniqueId": message.msg_id?:@"",@"mainUniqueId": sessionInfomodel.mainUniqueId?:@""}];
                NSString *content = [[OnlineDataSave shareOnlineDataSave] getAppSetting];
                
                TIMMessageFrame *messageF;
                if ([TOSKitCustomInfo shareCustomInfo].satisfactionViewPopupMode) {
                    messageF = [ICMessageHelper createMessageFrame:TypeSatisfactionPopup originalType:message.type content:content extra:extra auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
                } else {
                    messageF = [ICMessageHelper createMessageFrame:TypeInvestigation originalType:message.type content:content extra:extra auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
                }
                
                [self addObject:messageF isSender:NO isHeaderInsert:NO];
                [self messageReceiveSucced:messageF];
                
                TOSSatisfactionStatusModel *extraModel = [TOSSatisfactionStatusModel yy_modelWithJSON:messageF.model.message.extra?:@""];
                
                [self routerEventWithName:GXRouterEventSatisfactionPopupView
                                 userInfo:@{@"uniqueId": extraModel.uniqueId,
                                            MessageKey: messageF,
                                            @"mainUniqueId": extraModel.mainUniqueId
                                          }];
            }
            
            // 结束会话
            if ([InvestigationMsg.isClose boolValue]) {
                self.isCloseSession = YES;
                sessionInfomodel.status = [NSNumber numberWithInt:7];
                
                if (![kitUtils isBlankString:InvestigationMsg.endMessage]) {
                    
                    NSLog(@"endMessage === %@",[InvestigationMsg yy_modelToJSONObject]);
                    // 增加结束会话的文案
                    TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeSystem originalType:message.type content:InvestigationMsg.endMessage extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
                    [self addObject:messageF isSender:NO isHeaderInsert:NO];
                    [self messageReceiveSucced:messageF];
                }
    //            self.chatBoxVC.view.hidden = YES;
    //            self.chatCloseView.hidden = NO;
    //            [self.chatBoxVC.chatBox.textView resignFirstResponder];
    //            self.chatBoxVC.chatBox.textView.text = @"";
    //            [[OnlineDataSave shareOnlineDataSave] saveUserInputText:@""];
    //            [self.chatBoxVC.chatBox switchTextEditing];
    //
    //            self.tableView.height = self.chatCloseView.top;
                [self closeViewEvent];
                
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
            }
        }else if ([message.type isEqualToString:@"SystemMessage"]) {//系统消息
            TextMessage * textMsg = (TextMessage *)message.content;
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeSystem originalType:message.type content:textMsg.textContent extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
        }else if ([message.type isEqualToString:@"TextMessage"]) {//文本消息
            TextMessage * textMsg = (TextMessage *)message.content;
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText originalType:message.type content:textMsg.textContent extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
        }else if ([message.type isEqualToString:@"RichTextMessage"]) {//富文本消息
            RichTextMessage * richTextMsg = (RichTextMessage *)message.content;
            
            NSLog(@"[message.content yy_modelToJSONObject] ===== %@",[message.content yy_modelToJSONObject]);
            
            [self.needDelRichTextIds addObject:message.msg_id];
            NSString *tempPath = NSTemporaryDirectory();
            NSString *filePath = [tempPath stringByAppendingPathComponent:@"RichTextMessageIds"];
            [NSKeyedArchiver archiveRootObject:self.needDelRichTextIds toFile:filePath];
            
            
            
            NSString *contentStr = (NSString *)richTextMsg.elements;
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:GXRichText originalType:@"text" content:contentStr extra:(NSString *)richTextMsg.repliedMessage auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
            
        }else if ([message.type isEqualToString:@"ImageMessage"]) {//图片消息
            ImageMessage * imgMsg = (ImageMessage *)message.content;
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic originalType:@"image" content:@"[图片]" extra:@"" auditExtra:@"" path:@"" urlPath:imgMsg.imgUrlPath from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(100, 100) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
        }else if ([message.type isEqualToString:@"VideoMessage"]) {//视频消息
            VideoMessage * videoMsg = (VideoMessage *)message.content;
            UIImage *videoArrowImage;
            NSString * localPath;
            localPath = [[ICMediaManager sharedManager] smallImgPath:message.msg_id];
            videoArrowImage = [UIImage imageWithContentsOfFile:localPath];
            if (!videoArrowImage) {
                UIImage *resultImg = [self getVideoPreViewImage:[NSURL URLWithString:videoMsg.videoUrlPath]];
                videoArrowImage = [UIImage addImage2:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"video_play_btn_bg"]] toImage:resultImg];
                if (videoArrowImage) {
                    [[ICMediaManager sharedManager] saveImage:videoArrowImage msgId:message.msg_id picType:@"jpg"];
                }
            }
            CGFloat fixelW = videoArrowImage.size.width;
            CGFloat fixelH = videoArrowImage.size.height;
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:videoMsg.videoUrlPath urlPath:videoMsg.videoUrlPath from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(fixelW, fixelH) picType:@"jpg" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
            
        }else if ([message.type isEqualToString:@"FileMessage"]) {//文件消息
            FileMessage * fileMsg = (FileMessage *)message.content;
            if ([fileMsg.type isEqualToString:TypeVideo]) {
                // 视频
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:fileMsg.fileUrlPath urlPath:fileMsg.fileUrlPath from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(150 * 9.0/16.0, 150) picType:@"jpg" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
                [self addObject:messageF isSender:NO isHeaderInsert:NO];
                [self messageReceiveSucced:messageF];
            }else{
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCustomFile originalType:@"file" content:fileMsg.content extra:@"" auditExtra:@"" path:fileMsg.fileUrlPath urlPath:fileMsg.fileUrlPath from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeZero picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
                [self addObject:messageF isSender:NO isHeaderInsert:NO];
                [self messageReceiveSucced:messageF];
            }
            
        }else if ([message.type isEqualToString:@"VoiceMessage"]) {//语音消息
            VoiceMessage * voiceMsg = (VoiceMessage *)message.content;
            int voiceDuration = (int)[[ICRecordManager shareManager] durationWithVideo:[NSURL fileURLWithPath:voiceMsg.voiceUrlPath]];
            if (voiceDuration < 1000) {
                // 小于一秒的显示1秒
                voiceDuration = 1000;
            }
            
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVoice originalType:@"voice" content:@"[语音]" extra:@"" auditExtra:@"" path:voiceMsg.voiceUrlPath urlPath:voiceMsg.voiceUrlPath from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:voiceDuration] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeZero picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
        }else if ([message.type isEqualToString:@"RobotRichMessage"]) {//机器人选项消息
            
            NSArray <CombinationMessage *>*combinationMessage = message.content;
            
            NSString *contentStr = (NSString *)combinationMessage;
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeRobotCombination originalType:@"text" content:contentStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
            
        }else if ([message.type isEqualToString:@"RobotRichMessage14"]) {//机器人选项消息14
            
            NSArray <CombinationMessage *>*combinationMessage = (NSArray <CombinationMessage *>*)message.content;
            
            [combinationMessage enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                if (obj.clickLikeFeature &&
                    ![TOSKitCustomInfo shareCustomInfo].isOpenHelpfulFeature) {
                    obj.clickLikeFeature = NO;
                }
            }];
            
            NSString *contentStr = (NSString *)combinationMessage;
            
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeRobotCombination originalType:@"text" content:contentStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:@(4)];
            
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
            
            [combinationMessage enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                
                if (obj.type.integerValue == 36) {
                    
                    dispatch_async(dispatch_get_main_queue(), ^{
                        
                        if (self.robotFormView) {
                            [self.robotFormView removeFromSuperview];
                            self.robotFormView = nil;
                        }
                        TOSRobotFormView *robotFormView = [[TOSRobotFormView alloc] initWithFrame:CGRectMake(0, 0, kWindowWidth, self.view.tos_height)];
                        robotFormView.robotFormData = obj.robotFormData;
                        [self.view addSubview:robotFormView];
                        [robotFormView showPopupView];
                        self.robotFormView = robotFormView;
                    });
                }
            }];
        }else if ([message.type isEqualToString:@"RobotBridgeMessage"]) {//接通机器人
            sessionInfomodel.status = [NSNumber numberWithInt:8];
            self.isCloseSession = NO;
            RobotBridgeMessage * robotBridgeMsg = (RobotBridgeMessage *)message.content;
            
            dispatch_async(dispatch_get_main_queue(), ^{
                @StrongObj(self);
                [self chatStatusChanged:TinetChatStatusTypeRobot];
                if (self.titleName ==nil || self.titleName.length == 0) {
                    self.title = robotBridgeMsg.robotNickName;
                }
            });
            //获取机器人头像信息
//            [[OnlineRequestManager sharedCustomerManager] getClientInfoWithSender:robotBridgeMsg.robotId
//                                                                       senderType:@"4"
//                                                                          success:^(OnlineClientInfoModel * _Nonnull model) {
//                if (model.avatar == nil || model.avatar.length == 0) {
//                    //保存头像
//                    [[ICMediaManager sharedManager] savaHeadImgUrl:@"customer_service_default_avatar" userId:robotBridgeMsg.robotId];
//                }else{
//                    [[ICMediaManager sharedManager] savaHeadImgUrl:model.avatar userId:robotBridgeMsg.robotId];
//                }
//                if (model.nickName == nil || model.nickName.length == 0) {
//                    //保存名称
//                    [[ICMediaManager sharedManager] savaHeadName:@"机器人" userId:robotBridgeMsg.robotId];
//                }else{
//                    [[ICMediaManager sharedManager] savaHeadName:model.nickName userId:robotBridgeMsg.robotId];
//                }
//                
//            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
//                
//            }];

            
        }else if ([message.type isEqualToString:@"ChatOpenMessage"]) {//会话开始
            self.isCloseSession = NO;
            ChatOpenMessage * eventMessage = (ChatOpenMessage *)message.content;
        }else if ([message.type isEqualToString:@"ChatBridgeMessage"]) {//接通客服
            NSLog(@"会话接通客服⚠️⚠️⚠️");
            ChatBridgeMessage * eventMessage = (ChatBridgeMessage *)message.content;
            
            dispatch_async(dispatch_get_main_queue(), ^{
                @StrongObj(self);
                [self chatStatusChanged:TinetChatStatusTypeOnline];
                if (self.titleName ==nil || self.titleName.length == 0) {
                    self.title = [[OnlineDataSave shareOnlineDataSave] getCustomTitle];
                }
            });
            sessionInfomodel.status = [NSNumber numberWithInt:4];
            self.isCloseSession = NO;
            //获取客服头像信息
//            [[OnlineRequestManager sharedCustomerManager] getClientInfoWithSender:eventMessage.sender
//                                                                       senderType:@"1"
//                                                                          success:^(OnlineClientInfoModel * _Nonnull model) {
//                if (model.avatar == nil || model.avatar.length == 0) {
//                    //保存头像
//                    [[ICMediaManager sharedManager] savaHeadImgUrl:@"default_avatar" userId:eventMessage.sender];
//                }else{
//                    [[ICMediaManager sharedManager] savaHeadImgUrl:model.avatar userId:eventMessage.sender];
//                }
//                if (model.nickName == nil || model.nickName.length == 0) {
//                    //保存名称
//                    [[ICMediaManager sharedManager] savaHeadName:@"客服" userId:eventMessage.sender];
//                }else{
//                    [[ICMediaManager sharedManager] savaHeadName:model.nickName userId:eventMessage.sender];
//                }
//            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
//                
//            }];
            
            /// 机器人消息去重操作
            __block BOOL repeat = NO;
            NSArray *readyDisplayDataSource = [self.readyDisplayDataSource mutableCopy];
            [readyDisplayDataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *objModel, NSUInteger idx, BOOL * _Nonnull stop) {
                if (objModel.model.message.messageId.length > 0 && eventMessage.clientIntroduceMessageUniqueId.length > 0 && ![objModel.model.message.messageId isEqualToString:@"<null>"]) {
                    if ([objModel.model.message.messageId isEqualToString:eventMessage.clientIntroduceMessageUniqueId]) {
                        NSLog(@"objModel.model.message.messageId : %@ eventMessage.clientIntroduceMessageUniqueId : %@", objModel.model.message.messageId, eventMessage.clientIntroduceMessageUniqueId);
                        repeat = YES;
                    }
                }
                
            }];
            for (int i = 0; i < eventMessage.welcomeMessageUniqueId.count; i++) {
                NSString * welcomeMessageUniqueId = eventMessage.welcomeMessageUniqueId[i];
                [readyDisplayDataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *objModel, NSUInteger idx, BOOL * _Nonnull stop) {
                    if ([objModel.model.message.messageId isEqualToString:welcomeMessageUniqueId]) {
                        repeat = YES;
                    }
                }];
            }
            /// 如果repeat为真，说明历史消息中有重复的ID，不需要再添加这个消息。
            if (repeat) {
                return;
            }
            
            if (eventMessage.welcomStr.length>0) {
                RichTextMessage *richTextMsg = [[RichTextMessage alloc] initMessageWithContent:eventMessage.welcomStr];
                
                NSString *contentStr = (NSString *)richTextMsg.elements;
                
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:GXRichText originalType:@"text" content:contentStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:eventMessage.sender to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
                [self addObject:messageF isSender:NO isHeaderInsert:NO];
                [self messageReceiveSucced:messageF];
            }
            if (eventMessage.clientIntroduce.length>0) {
                RichTextMessage *richTextMsg = [[RichTextMessage alloc] initMessageWithContent:eventMessage.clientIntroduce];
                
                NSString *contentStr = (NSString *)richTextMsg.elements;
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:GXRichText originalType:@"text" content:contentStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:eventMessage.sender to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
                [self addObject:messageF isSender:NO isHeaderInsert:NO];
                [self messageReceiveSucced:messageF];
            }
        }else if ([message.type isEqualToString:@"ChatCloseMessage"]) {//会话结束
            sessionInfomodel.status = [NSNumber numberWithInt:7];
            self.isCloseSession = YES;
            // 增加结束会话的文案
            ChatCloseMessage * closeMessage = (ChatCloseMessage*)message.content;
            if (![kitUtils isBlankString:closeMessage.endMessage]) {
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeSystem originalType:@"text" content:closeMessage.endMessage extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
                [self addObject:messageF isSender:NO isHeaderInsert:NO];
                [self messageReceiveSucced:messageF];
            }
            
            [self closeViewEvent];
            
            self.reloadTaskManager.isScroll = YES;
            [self.reloadTaskManager startCountdown];
        }else if ([message.type isEqualToString:@"ChatQueueMessage"]) {//进入排队
            sessionInfomodel.status = [NSNumber numberWithInt:3];
            ChatQueueMessage * eventMsg = (ChatQueueMessage *)message.content;
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeEventQueue originalType:message.type content:eventMsg.locationstr extra:@"" auditExtra:@"" path:nil urlPath:nil from:[[OnlineDataSave shareOnlineDataSave] getVisitorId] to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
            //在排队的时候自动关闭会话后 在点击继续咨询 主动发消息不能上屏是因为排队的时候没有更新isCloseSession状态导致的所以在排队的时候更新一下状态
            self.isCloseSession = NO;
        }else if ([message.type isEqualToString:@"ChatLocationMessage"]) {//排队位置播报
            ChatLocationMessage * eventMsg = (ChatLocationMessage *)message.content;
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeEventQueue originalType:message.type content:eventMsg.locationstr extra:@"" auditExtra:@"" path:nil urlPath:nil from:[[OnlineDataSave shareOnlineDataSave] getVisitorId] to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
        }else if ([message.type isEqualToString:@"WithdrawMessage"]) {//座席撤回消息
            WithdrawMessage * eventMsg = (WithdrawMessage *)message.content;
            [self getRecallMessage:message msgId:message.msg_id];
    //        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeSystem originalType:message.type content:@"对方撤回了一条消息" extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
    //        [self addObject:messageF isSender:NO isHeaderInsert:NO];
    //        [self messageSendSucced:messageF];
        }else if ([message.type isEqualToString:@"ChatLeaveQueueMessage"]) {//访客退出排队
            ChatLeaveQueueMessage * robotBridgeMsg = (ChatLeaveQueueMessage *)message.content;
        }else if ([message.type isEqualToString:@"ChatOfflineMessage"]) {//访客离线
            ChatOfflineMessage * robotBridgeMsg = (ChatOfflineMessage *)message.content;
        }else if ([message.type isEqualToString:@"ChatOnlineMessage"]) {//访客上线
            ChatOnlineMessage * robotBridgeMsg = (ChatOnlineMessage *)message.content;
        }else if ([message.type isEqualToString:@"ChatLeaveMessage"]) {//留言消息事件
            sessionInfomodel.status = [NSNumber numberWithInt:5];
            dispatch_async(dispatch_get_main_queue(), ^{
                @StrongObj(self);
                ChatLeaveMessage * chatLeaveMessageMsg = (ChatLeaveMessage *)message.content;
                
                __block TOSBaseViewController *weakVC;
                if (chatLeaveMessageMsg.webLeaveMessageType.integerValue == 1) {
                    TIMChatLeaveVC *vc = [[TIMChatLeaveVC alloc] init];
                    weakVC = vc;
                    vc.welcomContent = chatLeaveMessageMsg.welcomContent;
                    vc.leaveTip = chatLeaveMessageMsg.leaveTip;
                    vc.leaveMessageFields = [[NSMutableArray alloc]initWithArray:chatLeaveMessageMsg.leaveMessageFields];
                    [self.navigationController pushViewController:vc animated:NO];
                    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(.5f * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                        @StrongObj(self);
                        if (weakVC) {
                            NSMutableArray *marr = [[NSMutableArray alloc] initWithArray:self.navigationController.viewControllers];
                            for (UIViewController *vc in marr) {
                                if (vc == self &&
                                    [vc isKindOfClass:[TOSCustomerChatVC class]]) {
                                    [marr removeObject:vc];
                                    break;          //break一定要加，不加有时候有bug
                                }
                            }
                            self.navigationController.viewControllers = marr;
                        }
                    });
                } else if (chatLeaveMessageMsg.webLeaveMessageType.integerValue == 3) {
                    TOSWorkOrderLeaveMessageVC *vc = [[TOSWorkOrderLeaveMessageVC alloc] init];
                    weakVC = vc;
                    vc.chatLeaveMessageMsg = [chatLeaveMessageMsg mutableCopy];
                    [self.navigationController pushViewController:vc animated:NO];
                }
                
                
            });
        }else if([message.type isEqualToString:@"ChatLeaveMessage"]){//留言消息
            ChatLeaveReceiveMessage * chatLeaveReceiveMsg = (ChatLeaveReceiveMessage *)message.content;
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText originalType:message.type content:chatLeaveReceiveMsg.textContent extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:YES status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            [self addObject:messageF isSender:YES isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
        } else if ([message.type isEqualToString:@"MessageTextTag"]) {
            
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeTextTag originalType:message.type content:(NSString *)message.content extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:message.senderType.integerValue]];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageSendSucced:messageF];
        } else if ([message.type isEqualToString:@"SensitiveWordEvent"]) {
            
            TOSSensitiveWordEventModel *sensitiveWordModel = (TOSSensitiveWordEventModel *)message.content;
            NSLog(@"sensitiveWordModel ======= %@",[sensitiveWordModel yy_modelToJSONObject]);
            @WeakObj(self)
            NSArray *readyDisplayDataSource = [self.readyDisplayDataSource mutableCopy];
            [readyDisplayDataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
                if ([obj.model.message.fileKey isEqualToString:sensitiveWordModel.messageUUID]) {
                    
                    @StrongObj(self)
                    obj.model.message.deliveryState = ICMessageDeliveryState_Failure_SensitiveWords;
                    obj.model = obj.model;
                    self.reloadTaskManager.isScroll = NO;
                    [self.reloadTaskManager startCountdown];
                    *stop = YES;
                }
            }];
            self.readyDisplayDataSource = [NSMutableArray arrayWithArray:readyDisplayDataSource];
        }
    }];
    
    /// 保存会话在线状态事件
    [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[sessionInfomodel yy_modelToJSONString]];
    
//    TOSSessionInfoModel *model = [[TOSClientKit sharedTOSKit] getCurrentSessionInfo];
//    NSLog(@"保存会话在线状态事件model === %@",[model yy_modelToJSONObject]);
    
    if (self.offlineType != offlineMessageTypeNormal && self.existedWelcome) {
        [self sendOfflineNewMessage];
        self.existedWelcome = NO;
    }
    else if (self.offlineType != offlineMessageTypeNormal && !self.existedWelcome) {
        self.existedWelcome = YES;
        
    }
    
}

- (void)messageRecalledNotification:(NSNotification *)sender {

}

#pragma mark - baseCell delegate

- (void)longPress:(UILongPressGestureRecognizer *)longRecognizer
{
    if (longRecognizer.state == UIGestureRecognizerStateBegan) {
        CGPoint location       = [longRecognizer locationInView:self.tableView];
        NSIndexPath *indexPath = [self.tableView indexPathForRowAtPoint:location];
        _longIndexPath         = indexPath;
        id object              = [self.dataSource objectAtIndex:indexPath.row];
        
        if (![object isKindOfClass:[TIMMessageFrame class]]) return;
        ICChatMessageBaseCell *cell = [self.tableView cellForRowAtIndexPath:indexPath];
        if (self.dataSource.count > indexPath.row) {
            [self showMenuViewController:cell.bubbleView andIndexPath:indexPath message:((TIMMessageFrame *)self.dataSource[indexPath.row]).model];//cell.modelFrame.model];
        }
    }
}

- (void)headImageClicked:(TIMMessageModel *)model {

}

/// 重发
/// @param baseCell cell
- (void)reSendMessage:(ICChatMessageBaseCell *)baseCell {
    
    if (![[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
        return;
    }
    
    
    @WeakObj(self);
    [self.reloadTaskManager startTaskWithScroll:YES completion:^{
        @StrongObj(self);
        
        TIMMessageFrame *modelFrame = baseCell.modelFrame;
        TIMICMessage *message = modelFrame.model.message;
        NSString *mediaPath = modelFrame.model.mediaPath;
        
        [self messageSending:modelFrame];
        
        if ([message.type isEqualToString:TypeText]) {
            
            [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendTextMessageWithMessageStr:message.content knowledge:message.knowledge?:@"" sysTransfer:message.sysTransfer intent:message.intent messageUUID:message.fileKey success:^(TOSMessage * timMessage) {
                @StrongObj(self);
                baseCell.modelFrame.model.message.messageId = timMessage.msg_id;
                [self updateOldMessageId:timMessage.msg_id];
                [self messageSendSucced:baseCell.modelFrame];
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                [self messageSendFailed:baseCell.modelFrame];
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
            }];
        } else if ([message.type isEqualToString:TypePic]) {
            
            UIImage *image = [UIImage imageWithContentsOfFile:mediaPath];
            NSData *imageData = UIImageJPEGRepresentation(image, 0.5);
            [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendImageMessageWithImageData:imageData success:^(NSString * _Nonnull messageId, NSString * _Nonnull fileKey) {
                @StrongObj(self);
                modelFrame.model.message.messageId = messageId;
                [self updateOldMessageId:messageId];
                [self messageSendSucced:modelFrame];
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                @StrongObj(self);
                [self messageSendFailed:modelFrame];
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
            }];
        } else if ([message.type isEqualToString:TypeVideo]) {
            
            NSData *videoData = [NSData dataWithContentsOfFile:mediaPath];
            [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVideoMessageWithVideoData:videoData success:^(NSString * _Nonnull messageId) {
                @StrongObj(self);
                modelFrame.model.message.messageId = messageId;
                [self updateOldMessageId:messageId];
                [self messageSendSucced:modelFrame];
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                @StrongObj(self);
                [self messageSendFailed:modelFrame];
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
                long long sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
                NSString *strShow = [NSString stringWithFormat:@"文件过大，请发送小于%lldM文件",sizeLimitM];
                [WHToast showMessage:errorDes?:strShow duration:2.f finishHandler:^{
                    
                }];
            }];
        } else if ([message.type isEqualToString:TypeVoice]) {
            
            NSData *voiceData = [NSData dataWithContentsOfFile:mediaPath];
            [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendVoiceMessageWithVoiceData:voiceData success:^(NSString * _Nonnull messageId) {
                @StrongObj(self);
                modelFrame.model.message.messageId = messageId;
                [self updateOldMessageId:messageId];
                [self messageSendSucced:modelFrame];
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                @StrongObj(self);
                [self messageSendFailed:modelFrame];
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
            }];
        } else if ([message.type isEqualToString:TypeCustomFile]) {
            
            NSData *fileData = [NSData dataWithContentsOfFile:mediaPath];
            NSString *fileTypeStr = [kitUtils mimeTypeForFileAtPath:mediaPath];
            
            NSString *fileName = [[kitUtils dictionaryWithJsonString:message.content] tim_ObjectForKey:@"fileName"];
            
            [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendFileMessageWithFileData:fileData fileType:fileTypeStr fileName:fileName success:^(NSString * _Nonnull messageId) {
                @StrongObj(self);
                modelFrame.model.message.messageId = messageId;
                [self updateOldMessageId:messageId];
                [self messageSendSucced:modelFrame];
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                @StrongObj(self);
                [self messageSendFailed:modelFrame];
                self.reloadTaskManager.isScroll = YES;
                [self.reloadTaskManager startCountdown];
                long long sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
                NSString *strShow = [NSString stringWithFormat:@"文件过大，请发送小于%lldM文件",sizeLimitM];
                [WHToast showMessage:errorDes?:strShow duration:2.f finishHandler:^{
                    
                }];
            }];
        }
    }];
}

-(void)sendCombinaMessage:(NSString *)text{
    [self sendTextMessagewithText:text knowledge:@"" sysTransfer:NO];
}

//文件下载方法
-(void)downFileMessage:(NSString *)fileUrl{
    
    
}

#pragma mark - 更新Mqtt链接状态

- (void)updateConnectStatus:(NSNotification *)notification {
    
    NSDictionary  *dict = [notification object];
    BOOL connectStatus = [dict[@"connectStatus"] boolValue];
    BOOL kickOutStatus = NO;
    if ([dict objectForKey:@"kickOut"]) {
        kickOutStatus = [dict[@"kickOut"] boolValue];
    }
    if (kickOutStatus) {
        NSLog(@"更新的链接状态 updateConnectStatus kickOutStatus 会话页面被踢下线");
        [self kickoutAlert];
    }
    if (connectStatus){
        if(self.isOpenSession) {
            // mqtt链接成功后需要校验下当前会话状态(非空)
            NSString * curMainUniqueId = [[OnlineDataSave shareOnlineDataSave] getMainUniqueId];
            if (![kitUtils isBlankString:curMainUniqueId]) {
                [[OnlineRequestManager sharedCustomerManager] sessionInfoGet:^(TOSSessionInfoModel * _Nonnull sessModel) {
                    NSLog(@"sessionInfoGet status %@",sessModel.status);
                    if ([sessModel.status isEqual:@(7)]) {
                        NSLog(@"sessionInfoGet will closeSessioin");
                        [self closeViewEvent];
                    }
                } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                    NSLog(@"sessionInfoGet Error %ld",errCode);
                }];
            }
        } else {
            // 变更状态重新打开会话
            [self openSession];
        }
        
    }
}

/// 关闭事件
- (void)closeViewEvent {
    
    @WeakObj(self);
    dispatch_async(dispatch_get_main_queue(), ^{
        
        @StrongObj(self);
        if (self.commodityCardOption && self.bottomCardView != nil) {
            [self.bottomCardView removeFromSuperview];
            self.bottomCardView = nil;
        }
        self.chatBoxVC.view.hidden = YES;
        self.chatCloseView.hidden = NO;
        [self.chatBoxVC.chatBox.textView resignFirstResponder];
        self.chatBoxVC.chatBox.textView.text = @"";
        [[OnlineDataSave shareOnlineDataSave] saveUserInputText:@""];
        [self.chatBoxVC.chatBox switchTextEditing];
        [self.chatBoxVC.chatBox.textView resignFirstResponder];
        self.tableView.tos_height = self.chatCloseView.top_sd;
//        [[OnlineDataSave shareOnlineDataSave] saveMainUniqueId:@""];
    });
    
}



#pragma mark - 收到非聊天界面消息发送通知
- (void)sendMultiMediaImage:(NSNotification *)notification {
    NSDictionary  *dict = [notification object];

    BOOL sendSuccess = [dict[@"sendSuccess"] boolValue];
    NSString *imgPath = dict[@"imagePath"];
    UIImage *image = [UIImage imageWithContentsOfFile:imgPath];
    NSString *receiveId = dict[@"receiveId"];
    TOSMessage * timMessage;
    if (sendSuccess) {
        timMessage = dict[@"timMessage"];
    }
    
    if ([receiveId isEqualToString:[[OnlineDataSave shareOnlineDataSave] getEndpointId]] == NO) {
        TIMKitLog(@"会话目标不匹配");
        return;
    }
    TIMKitLog(@"发送图片 path:%@ src:%@ last:%@",imgPath,image,[[imgPath lastPathComponent] stringByDeletingPathExtension]);
    
    
    @WeakObj(self);
    [self.reloadTaskManager startTaskWithScroll:YES completion:^{
        @StrongObj(self);
        
        NSString * sreGetMsgUUID = [[imgPath lastPathComponent] stringByDeletingPathExtension];
        if (image) {
            CGFloat fixelW = image.size.width;// CGImageGetWidth(image.CGImage);
            CGFloat fixelH = image.size.height;// CGImageGetHeight(image.CGImage);
            TIMImageMessage * imageMsg = [[TIMImageMessage alloc] initMessageWithImageURI:imgPath  extra:@""];
            TOSMessage * timMsg = [[TOSMessage alloc] initWithOption:sreGetMsgUUID msg_id:@"" type:@"image" senderId:[[OnlineDataSave shareOnlineDataSave] getVisitorId] receiverId:[[OnlineDataSave shareOnlineDataSave] getEndpointId] content:imageMsg msg_from:[self isGroupSession]?TIMSessionType_GROUP_CHAT:/*TIMSessionType_SINGLE_CHAT*/TIMSessionType_OnLINESERVICE_CHAT status:TIMMessageStatus_Sending timestamp:0];

            CGSize imageSize = CGSizeMake(fixelW, fixelH);
            __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic originalType:@"image" content:@"[图片]" extra:@"" auditExtra:@"" path:imgPath urlPath:nil from:[[OnlineDataSave shareOnlineDataSave] getVisitorId] to:[[OnlineDataSave shareOnlineDataSave] getEndpointId] fileKey:timMsg.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:timMsg.msg_id sendTime:[self getNowTimestamp] showTime:[self p_needShowTime:[NSDate date]] picSize:imageSize picType:@"" isSender:YES receivedSenderByYourself:NO status:TIMMessageStatus_Sending senderType:@(2)];
            [self addObject:messageF isSender:YES isHeaderInsert:NO];
       
            if (sendSuccess) {
                messageF.model.message.messageId = timMessage.msg_id;
                [self updateOldMessageId:timMessage.msg_id];
                [self messageSendSucced:messageF];
    //            [selfWeak scrollToBottom];
    //            [self innerScrollToBottom];
            }else{
                [self messageSendFailed:messageF];
    //            [selfWeak scrollToBottom];
    //            [self innerScrollToBottom];
            }
        }
    }];
}

#pragma mark 类内函数

- (void)sendTextMessageWithContent:(NSString *)type originalType:(NSString *)originalType from:(NSString *)from to:(NSString *)to fileKey:(NSString *)fileKey msgId:(NSString *)msgId sendTime:(NSInteger)sendTime content:(NSString *)messageStr
{
    [self sendTextMessageWithContent:type originalType:originalType from:from to:to fileKey:fileKey msgId:msgId sendTime:sendTime content:messageStr extra:@""];
}

- (void)sendTextMessageWithContent:(NSString *)type originalType:(NSString *)originalType from:(NSString *)from to:(NSString *)to fileKey:(NSString *)fileKey msgId:(NSString *)msgId sendTime:(NSInteger)sendTime content:(NSString *)messageStr extra:(NSString *)extra
{
    @WeakObj(self);
    [self.reloadTaskManager startTaskWithScroll:YES completion:^{
        @StrongObj(self);
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:type originalType:originalType content:messageStr extra:extra auditExtra:@"" path:nil urlPath:nil from:from to:to fileKey:fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:msgId sendTime:sendTime showTime:[selfWeak p_needShowTimeStamp:(NSTimeInterval)sendTime] picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:@(2)];
        [self addObject:messageF isSender:YES isHeaderInsert:NO];
        
        [self messageSendSucced:messageF];
    }];
}

- (void)otherSendTextMessageWithContent:(NSString*)type originalType:(NSString *)originalType from:(NSString *)from to:(NSString *)to fileKey:(NSString *)fileKey msgId:(NSString *)msgId sendTime:(NSInteger)sendTime content:(NSString *)messageStr extra:(NSString *)extra senderType:(NSString *)senderType
{
    @WeakObj(self)
    
    [self.reloadTaskManager startTaskWithScroll:YES completion:^{
        @StrongObj(self);
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:type originalType:originalType content:messageStr extra:extra auditExtra:@"" path:nil urlPath:nil from:from to:to fileKey:fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:msgId sendTime:sendTime showTime:[selfWeak p_needShowTimeStamp:(NSTimeInterval)sendTime] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:senderType.integerValue]];
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        
        [self messageSendSucced:messageF];
    }];
}

- (void)otherSendTextMessageWithContent:(NSString*)type originalType:(NSString *)originalType from:(NSString *)from to:(NSString *)to fileKey:(NSString *)fileKey msgId:(NSString *)msgId sendTime:(NSInteger)sendTime content:(NSString *)messageStr  senderType:(NSString *)senderType
{
    [self otherSendTextMessageWithContent:type originalType:originalType from:from to:to fileKey:fileKey msgId:msgId sendTime:sendTime content:messageStr extra:@"" senderType:senderType];
}

- (void)sendTextMessageInsertWithContent:(NSString *)to fileKey:(NSString *)fileKey content:(NSString *)messageStr
{
    
}

// 增加数据源并刷新
- (void)addObject:(TIMMessageFrame *)messageF
         isSender:(BOOL)isSender
{
    if (messageF == nil) {
        TIMKitLog(@"(void)addObject:(TIMMessageFrame *)messageF messageF == nil");
        return;
    }
    /// 消息去重
    BOOL find = [self removeDuplicatesMessage:messageF];
    if (find) {
        return;
    }
    messageF.model.message.deliveryState = ICMessageDeliveryState_Delivered;
    [self.readyDisplayDataSource insertObject:messageF atIndex:0];
}

// 增加数据源并刷新
- (void)addObject:(TIMMessageFrame *)messageF
         isSender:(BOOL)isSender isHeaderInsert:(BOOL)isHeaderInsert
{
    if (messageF == nil) {
        TIMKitLog(@"(void)addObject:(TIMMessageFrame *)messageF messageF == nil");
        return;
    }
    /// 消息去重
    BOOL find = [self removeDuplicatesMessage:messageF];
    if (find) {
        return;
    }
    
    messageF.model.message.deliveryState = ICMessageDeliveryState_Delivered;
    if (isHeaderInsert) {
        [self.readyDisplayDataSource insertObject:messageF atIndex:0];
    }else{
        [self.readyDisplayDataSource addObject:messageF];
    }
    
//    /// 目前用这个不是很合适(后面再优化)。  pageIndex  ！= 1 需要刷新，每次在拉取历史记录后，pageIndex会自加，等刷新了tableview后会置为0这个时候其他地方调用这个方法时，下面的条件是成立的。
//    /// 目的就是为了在获取历史记录后不需要每添加一条model就刷新一次数据，造成tableview刷新闪屏。
//    if (self.firstHasGetHistoryListFinish == 0 && self.pageIndex != 1) {
//        [selfWeak.tableView reloadData];
//        if (!isHeaderInsert) {
//            if (isSender || _isKeyBoardAppear) {
//                [selfWeak scrollToBottom];
//            }
//        }
//    }
}

// 修改数据源并刷新
- (void)modifyObject:(TIMMessageFrame *)messageF
            isSender:(BOOL)isSender isHeaderInsert:(BOOL)isHeaderInsert index:(NSInteger)index
{
    if (messageF == nil) {
        TIMKitLog(@"(void)addObject:(TIMMessageFrame *)messageF messageF == nil");
        return;
    }
    
    if (self.readyDisplayDataSource &&
        [self.readyDisplayDataSource objectAtIndex:index]) {
        self.readyDisplayDataSource[index] = messageF;
    }
    
    self.reloadTaskManager.isScroll = NO;
    if (!isHeaderInsert) {
        if (isSender || _isKeyBoardAppear) {
            self.reloadTaskManager.isScroll = YES;
        }
    }
    [self.reloadTaskManager startCountdown];
}

/// 消息去重
- (BOOL)removeDuplicatesMessage:(TIMMessageFrame *)messageF {
    /// 消息去重
    BOOL find = NO;
    NSMutableArray * testMArr = [NSMutableArray array];
    /// 获取接收的最后10条消息
    NSArray *readyDisplayDataSource = [self.readyDisplayDataSource mutableCopy];
    for (TIMMessageFrame * obj in readyDisplayDataSource) {
        if (!obj.model.isSender) {
            [testMArr addObject:obj];
            if (testMArr.count > 9) {
                break;
            }
        }
    }
    /// 接收的最后10条消息跟当前消息进行对比
    for (TIMMessageFrame * obj in testMArr) {
        /// 判断消息ID是否一致
        if ([obj.model.message.messageId isEqualToString:messageF.model.message.messageId]) {
            find = YES;
            break;
        }
    }
    if (find) {
        TIMKitLog(@"重复消息");
    }
    return find;
}

//接收消息成功
- (void)messageReceiveSucced:(TIMMessageFrame *)messageF
{
//    [self scrollToBottom];
}


- (void)messageSendSucced:(TIMMessageFrame *)messageF
{
//    if (self.firstHasGetHistoryListFinish == 0) {
        messageF.model.message.deliveryState = ICMessageDeliveryState_Delivered;
//    }
}

//- (void)newMessageSendSucced:(TIMMessageFrame *)messageF {
//    if (self.firstHasGetHistoryListFinish == 0) {
//        messageF.model.message.deliveryState = ICMessageDeliveryState_Delivered;
//    }
//}

- (void)messageSending:(TIMMessageFrame *)messageF
{
    messageF.model.message.deliveryState = ICMessageDeliveryState_Delivering;
}

- (void)messageSendFailed:(TIMMessageFrame *)messageF
{
    messageF.model.message.deliveryState = ICMessageDeliveryState_Failure;
}

//- (void)newMessageSendFailed:(TIMMessageFrame *)messageF
//{
//    messageF.model.message.deliveryState = ICMessageDeliveryState_Failure;
//}

//- (void)scrollToBottom {
//    
//    [NSObject cancelPreviousPerformRequestsWithTarget:self selector:@selector(innerScrollToBottom) object:nil];
//    [self performSelector:@selector(innerScrollToBottom) withObject:nil afterDelay:.5f];
//}

//- (void)innerScrollToBottom {
//    
//    [self.tableView reloadData];
//    if (self.dataSource.count > 0 &&
//        [self.tableView numberOfRowsInSection:0] >= self.dataSource.count) {
//        [self.tableView scrollToRowAtIndexPath:[NSIndexPath indexPathForRow:self.dataSource.count-1 inSection:0] atScrollPosition:UITableViewScrollPositionBottom animated:YES];
//    }
//}

- (void)showMenuViewController:(UIView *)showInView andIndexPath:(NSIndexPath *)indexPath message:(TIMMessageModel *)messageModel
{
    [self becomeFirstResponder];
    __block BOOL copy = NO;
    if ([messageModel.message.type isEqualToString:GXRichText] ||
        [messageModel.message.type isEqualToString:TypeRobotCombination]) {
        copy = YES;
    } else {
        copy = NO;
    }
    
    if ([messageModel.message.type isEqualToString:TypeText] ||
        copy) {
        
        if (_copyMenuItem == nil) {
            _copyMenuItem = [[UIMenuItem alloc] initWithTitle:@"复制" action:@selector(copyMessage:)];
        }
        
        [[UIMenuController sharedMenuController] setMenuItems:@[_copyMenuItem]];
        [[UIMenuController sharedMenuController] setTargetRect:showInView.frame inView:showInView.superview];
        [[UIMenuController sharedMenuController] setMenuVisible:YES animated:YES];
    }
}

- (void)recallMessage:(UIMenuItem *)sender {
    
}

//坐席端撤回消息处理
- (void)getRecallMessage:(TOSMessage *)object msgId:(NSString*)messageId{

    __block BOOL showTime = NO;
    __block NSTimeInterval sendTime;
    __block NSInteger indexNum = 0;
    NSArray *readyDisplayDataSource = [self.readyDisplayDataSource mutableCopy];
    [readyDisplayDataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
        if ([obj.model.message.messageId isEqualToString:messageId]) {
            indexNum = idx;
            if (obj.model.message.showTime) {
                showTime = obj.model.message.showTime;
                sendTime = obj.model.message.msgDate.timeIntervalSince1970 * 1000;
                *stop = YES;
            } else {
                sendTime = obj.model.message.msgDate.timeIntervalSince1970 * 1000;
            }
        }
    }];
    
    @WeakObj(self);
    [self.reloadTaskManager startTaskWithScroll:NO completion:^{
        @StrongObj(self);
        
        // Kit
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeRevoke originalType:object.type content:object.content extra:@"" auditExtra:@"" path:nil urlPath:nil from:[[OnlineDataSave shareOnlineDataSave] getVisitorId] to:object.receiverId fileKey:object.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:messageId sendTime:sendTime showTime:showTime picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:object.senderType.integerValue]];
        messageF.model.message.status = ICMessageStatus_back;   //将消息类型改为撤回
        
        
        [self modifyObject:messageF isSender:YES isHeaderInsert:NO index:indexNum];
        [self messageSendSucced:messageF];
    //    [self innerScrollToBottom];
    }];
}


//- (NSString*)saveFileMethed
//{
//    UIPasteboard *pasteboard  = [UIPasteboard generalPasteboard];
//    TIMMessageFrame * messageF = [self.dataSource objectAtIndex:_longIndexPath.row];
//    pasteboard.string         = messageF.model.message.content;
//
//    NSString*urlStr = messageF.model.urlPath;
//
//    return urlStr;
//}


- (void)copyMessage:(UIMenuItem *)copyMenuItem
{
    UIPasteboard *pasteboard  = [UIPasteboard generalPasteboard];
    TIMMessageFrame * messageF = [self.dataSource objectAtIndex:_longIndexPath.row];
    
    if ([messageF.model.message.type isEqualToString:TypeText]) {
        
        pasteboard.string         = messageF.model.message.content;
    } else if ([messageF.model.message.type isEqualToString:GXRichText]) {
        
        __block NSString *content = @"";
        NSArray <RichTextMessage *>*elements = (NSArray *)messageF.model.message.content;
        [elements enumerateObjectsUsingBlock:^(RichTextMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if ([content isEqualToString:@""]) {
                
                if ([obj.type isEqualToString:@"img"]) {
                    content = @"[图片]";
                } else if ([obj.type isEqualToString:@"video"]) {
                    content = @"[视频]";
                } else { //text、a、tr
                    content = [NSString stringWithFormat:@"%@",obj.content];
                }
            } else {
                
                NSString *str;
                if ([obj.type isEqualToString:@"img"]) {
                    str = @"[图片]";
                } else if ([obj.type isEqualToString:@"video"]) {
                    str = @"[视频]";
                } else { //text、a、tr
                    str = obj.content;
                }
                
                content = [NSString stringWithFormat:@"%@\n%@",content,str];
            }
            
        }];
        pasteboard.string         = content;
    } else if ([messageF.model.message.type isEqualToString:TypeRobotCombination]) {
        
        __block NSString *content = @"";
        NSArray <CombinationMessage *>*message = (NSArray *)messageF.model.message.content;
        [message enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if ([content isEqualToString:@""]) {
                
                if ([obj.type isEqualToString:@"6"] ||
                    [obj.type isEqualToString:@"15"] ||
                    [obj.type isEqualToString:@"16"] ||
                    [obj.type isEqualToString:@"17"] ||
                    [obj.type isEqualToString:@"18"] ||
                    [obj.type isEqualToString:@"19"] ||
                    [obj.type isEqualToString:@"20"] ||
                    [obj.type isEqualToString:@"31"]) {
//                    content = @"[猜你想问]";
                    content = @"[热门问题]";
                } else if ([obj.type isEqualToString:@"2"]) {
                    content = @"[图片]";
                } else if ([obj.type isEqualToString:@"3"]) {
                    content = @"[文件]";
                } else if ([obj.type isEqualToString:@"4"]) {
                    content = @"[视频]";
                } else if ([obj.type isEqualToString:@"7"]) {
                    content = @"[语音]";
                } else { //5
                    
                    NSArray <RichTextMessage *>*elements = obj.richTextMessage.elements;
                    [elements enumerateObjectsUsingBlock:^(RichTextMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                        if ([content isEqualToString:@""]) {
                            
                            if ([obj.type isEqualToString:@"img"]) {
                                content = @"[图片]";
                            } else if ([obj.type isEqualToString:@"video"]) {
                                content = @"[视频]";
                            } else { //text、a、tr
                                content = [NSString stringWithFormat:@"%@",obj.content];
                            }
                        } else {
                            
                            NSString *str;
                            if ([obj.type isEqualToString:@"img"]) {
                                str = @"[图片]";
                            } else if ([obj.type isEqualToString:@"video"]) {
                                str = @"[视频]";
                            } else { //text、a、tr
                                str = obj.content;
                            }
                            
                            content = [NSString stringWithFormat:@"%@\n%@",content,str];
                        }
                        
                    }];
                }
            } else {
                
                __block NSString *str;
                
                if ([obj.type isEqualToString:@"6"] ||
                    [obj.type isEqualToString:@"15"] ||
                    [obj.type isEqualToString:@"16"] ||
                    [obj.type isEqualToString:@"17"] ||
                    [obj.type isEqualToString:@"18"] ||
                    [obj.type isEqualToString:@"19"] ||
                    [obj.type isEqualToString:@"20"] ||
                    [obj.type isEqualToString:@"31"]) {
//                    str = @"[猜你想问]";
                    str = @"[热门问题]";
                } else if ([obj.type isEqualToString:@"2"]) {
                    str = @"[图片]";
                } else if ([obj.type isEqualToString:@"3"]) {
                    str = @"[文件]";
                } else if ([obj.type isEqualToString:@"4"]) {
                    str = @"[视频]";
                } else if ([obj.type isEqualToString:@"7"]) {
                    str = @"[语音]";
                } else { //5
                    
                    NSArray <RichTextMessage *>*elements = obj.richTextMessage.elements;
                    [elements enumerateObjectsUsingBlock:^(RichTextMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                        if ([str isEqualToString:@""]) {
                            
                            if ([obj.type isEqualToString:@"img"]) {
                                str = @"[图片]";
                            } else if ([obj.type isEqualToString:@"video"]) {
                                str = @"[视频]";
                            } else { //text、a、tr
                                str = [NSString stringWithFormat:@"%@",obj.content];
                            }
                        } else {
                            
                            NSString *subStr;
                            if ([obj.type isEqualToString:@"img"]) {
                                subStr = @"[图片]";
                            } else if ([obj.type isEqualToString:@"video"]) {
                                subStr = @"[视频]";
                            } else { //text、a、tr
                                subStr = obj.content;
                            }
                            
                            str = [NSString stringWithFormat:@"%@\n%@",str,subStr];
                        }
                        
                    }];
                }
                
                content = [NSString stringWithFormat:@"%@\n%@",content,str];
            }
        }];
        pasteboard.string         = content;
    }
}

//- (void)statusChanged:(TIMMessageFrame *)messageF
//{
//    [self.dataSource removeObject:messageF];
//    [self.tableView beginUpdates];
//    [self.tableView deleteRowsAtIndexPaths:@[_longIndexPath] withRowAnimation:UITableViewRowAnimationFade];
//    [self.tableView endUpdates];
//}

- (void)forwardMessage:(UIMenuItem *)forwardItem
{
    TIMKitLog(@"需要用到的数据库，等添加了数据库再做转发...");
}

#pragma mark UIMenuController

- (BOOL)canBecomeFirstResponder
{
    return YES;
}

- (void)tinet_textMessageClickAction:(TinetClickTextMessageEventType)eventType userInfo:(NSDictionary *)userInfo {
    // 超链接点击事例
    NSLog(@"eventType ====== %lu, userInfo ======== %@",(unsigned long)eventType,userInfo);
    
    if (eventType == TinetClickEventTypeUrl) {
        if ([userInfo tim_ObjectForKey:@"content"] &&
            [userInfo[@"content"] isKindOfClass:[NSString class]]) {
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:[userInfo[@"content"] stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLQueryAllowedCharacterSet]]]];
        }
    }
    
//    if (eventType == TinetClickCommodityCard) {
//        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:[userInfo[@"content"][@"message"][@"extra"][@"subUrl"]?:@"" stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLQueryAllowedCharacterSet]]]];
//    }
//
//    if (eventType == TinetClickEventTypeOrderNumber ||
//        eventType == TinetClickEventTypePhone) {
//        UIPasteboard *pboard = [UIPasteboard generalPasteboard];
//        pboard.string = userInfo[@"content"]?:@"";
//    }
//
//    if (eventType == TinetClickMiniProgramCard) {
//        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:[userInfo[@"content"][@"pagepath"]?:@"" stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLQueryAllowedCharacterSet]]]];
//    }
//
//    if (eventType == TinetClickLogisticsCard) {
//        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:[userInfo[@"content"][@"orderLink"]?:@"" stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLQueryAllowedCharacterSet]]]];
//    }
}

#pragma mark - public method

/// 发送商品卡片消息
- (void)sendCard:(TOSClientKitCommodityCardOption *)userInfo {
    TinetChatStatusType statusType = [TOSClientKit.sharedTOSKit getCurrentOnlineStatus];
    /// 不在线或结束会话
    if (statusType == TinetChatStatusTypeOutline) {
        NSLog(@"当前会话已结束，无法发送商品卡片数据");
        return;
    }
    
    NSDictionary *content = @{
        @"subTitle": userInfo.subTitle?:@"",
        @"description": userInfo.descriptions?:@"",
        @"price": userInfo.price?:@"",
        @"time": userInfo.time?:@"",
        @"img": userInfo.img?:@"",
        @"status": userInfo.status?:@"",
        @"title": userInfo.title?:@"",
        @"url": userInfo.url?:@"",
        @"subUrl": userInfo.subUrl?:@"",
        @"buttonText": userInfo.buttonText?:@"",
        @"senderType": @"2"
    };
    
    NSMutableDictionary * mutableDic = [NSMutableDictionary dictionaryWithDictionary:content];
    if (userInfo.extraInfo) {
        [mutableDic setObject:userInfo.extraInfo forKey:@"extraInfo"];
    }
    if (userInfo.extraData) {
        [mutableDic setObject:userInfo.extraData forKey:@"extraData"];
    }
    
    [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendCommodityCardMessageWithMessageStr:[mutableDic copy] success:^(TOSMessage * _Nonnull timMessage) {

    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {

    }];
    
    
}

/// 发送文本消息
- (void)sendText:(NSString *)text {
    
    [self sendTextMessagewithText:text knowledge:@"" sysTransfer:NO];
    
}


// 路由响应
- (void)routerEventWithName:(NSString *)eventName
                   userInfo:(NSDictionary *)userInfo
{
    @weakify(self)
    dispatch_async(dispatch_get_main_queue(), ^{
        @strongify(self)
        [self.view endEditing:YES];
    });
    
    TIMMessageFrame *modelFrame = [userInfo objectForKey:MessageKey];
    if ([eventName isEqualToString:GXRouterEventTextUrlTapEventName]) {
    } else if ([eventName isEqualToString:GXHotIssueSendMessageEventName]) {
        NSString *text = [userInfo objectForKey:RouterEventGetSendTextMessage];
        NSString *knowledge = [userInfo objectForKey:RouterEventGetSendTextMessageKnowledge];
        [self sendTextMessagewithText:text knowledge:knowledge?:@"" sysTransfer:NO];
    } else if ([eventName isEqualToString:GXRouterEventSatisfactionPopupView]) {
        
        dispatch_async(dispatch_get_main_queue(), ^{
            @strongify(self)
            NSLog(@"userInfo ===== %@",userInfo);
            if (self.popupView) {
                [self.popupView removeFromSuperview];
                self.popupView = nil;
            }
            self.popupView = [[TOSSatisfactionPopupView alloc] initWithFrame:CGRectMake(0, 0, kWindowWidth, kWindowHeight - kNavTop)];
            self.popupView.hidden = YES;
            [self.view addSubview:self.popupView];
            
            NSString *uniqueId = [userInfo objectForKey:@"uniqueId"];
            NSString *mainUniqueId = [userInfo objectForKey:@"mainUniqueId"];
            self.popupView.uniqueId = uniqueId?:@"";
            self.popupView.mainUniqueId = mainUniqueId?:@"";
            self.popupView.tempModelFrame = modelFrame;
            self.popupView.popViewController = NO;
            self.popupView.showNotEvaluated = NO;
            [self.popupView showPopupView];
        });
    } else if ([eventName isEqualToString:GXRobotPopViewController]) {
        
        /// present还是push判断返回规则
        if (self.navigationController) {
            [self.navigationController popViewControllerAnimated:YES];
        }
        else {
            [self dismissViewControllerAnimated:YES completion:^{
                
            }];
        }
    } else if ([eventName isEqualToString:GXRobotCombinationHotIssueCellRefreshEventName]) {
        
        [self.reloadTaskManager startTaskWithScroll:NO completion:^{
            @strongify(self)
            __block NSInteger index = 0;
            NSArray *readyDisplayDataSource = [self.readyDisplayDataSource mutableCopy];
            
            if ([kitUtils isBlankString:modelFrame.model.message.messageId]) {
                
                [readyDisplayDataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    if ([obj isEqual:modelFrame]) {
                        obj = modelFrame;
                        index = idx;
                        *stop = YES;
                    }
                }];
            } else {
                
                [readyDisplayDataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    if ([obj.model.message.messageId isEqualToString:modelFrame.model.message.messageId]) {
                        obj = modelFrame;
                        index = idx;
                        *stop = YES;
                    }
                }];
            }
            //        self.readyDisplayDataSource = [NSMutableArray arrayWithArray:readyDisplayDataSource];
            //        self.reloadTaskManager.isScroll = NO;
            //        [self.reloadTaskManager startCountdown];
        }];
    } else if ([eventName isEqualToString:GXRouterEventVideoTapEventName]){//点击视频
        NSString *videoPath      = modelFrame.model.mediaPath;
        NSString *videoId = modelFrame.model.message.messageId;
        if (userInfo &&
            [[userInfo allKeys] containsObject:@"urlPath"]) {
            videoPath = [userInfo objectForKey:@"urlPath"];
            NSArray *array = [videoPath componentsSeparatedByString:@"/"];
            __block NSString *videoIds = @"";
            [array enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                if (idx == array.count - 1) {
                    videoIds = obj;
                }
            }];
            videoId = [videoIds componentsSeparatedByString:@"."].firstObject;
        }
        _videoBtnRect            = [[userInfo objectForKey:@"BtnView"] CGRectValue];
        [self showLargeVideoWithPath:videoPath?:@"" messageId:videoId fromRect:_videoBtnRect];
    } else if ([eventName isEqualToString:GXRouterEventCombinationFileTapEventName]) {
        
        NSString *urlPath = [userInfo objectForKey:@"urlPath"];
        STBaseWebViewController *vc = [[STBaseWebViewController alloc]init];
        vc.urlString = urlPath;
        [self.navigationController pushViewController:vc animated:YES];
    } else if ([eventName isEqualToString:GXRouterEventImageTapEventName]) {//点击图片
        _smallRect             = [[userInfo objectForKey:@"smallRect"] CGRectValue];
        _bigRect               =  [[userInfo objectForKey:@"bigRect"] CGRectValue];
        
        NSString *imgPath      = modelFrame.model.mediaPath;
        if (userInfo &&
            [[userInfo allKeys] containsObject:@"urlPath"]) {
            imgPath = [userInfo objectForKey:@"urlPath"];
        }
        
        NSString *orgImgPath = @"";
        if ([imgPath.pathExtension isEqualToString:kGIFTimageType]) {
            orgImgPath = [[ICMediaManager sharedManager] originGifImgPath:modelFrame];
        }else{
            orgImgPath = [[ICMediaManager sharedManager] originImgPath:modelFrame];
        }
        
        if ([ICFileTool fileExistsAtPath:orgImgPath]) {
            modelFrame.model.mediaPath = orgImgPath;
            imgPath                    = orgImgPath;
            
            ICPhotoBrowserController *imageVC = [[ICPhotoBrowserController alloc] initWithImage:nil msgId:modelFrame.model.message.messageId originImageUrl:imgPath];
            imageVC.modalPresentationStyle    = UIModalPresentationCustom;
            [self presentViewController:imageVC animated:YES completion:nil];
        }else{
            ICPhotoBrowserController *imageVC = [[ICPhotoBrowserController alloc] initWithImage:nil msgId:modelFrame.model.message.messageId originImageUrl:imgPath];
            imageVC.modalPresentationStyle    = UIModalPresentationCustom;
            [self presentViewController:imageVC animated:YES completion:nil];
            
        }
    } else if ([eventName isEqualToString:GXRouterEventImageApprovedTapEventName]) {
        // 通过
        TIMKitLog(@"点击通过审核");

    } else if ([eventName isEqualToString:GXRouterEventImageRejectedTapEventName]) {
        // 拒绝
        TIMKitLog(@"点击审核不合格");
    } else if ([eventName isEqualToString:GXRouterEventVoiceTapEventName]) {
        
        UIImageView *imageView = (UIImageView *)userInfo[VoiceIcon];
        UIView *redView        = (UIView *)userInfo[RedView];
        [self chatVoiceTaped:modelFrame voiceIcon:imageView redView:redView];
    } else if ([eventName isEqualToString:GXRouterEventCombinationVoiceTapEventName]) {
        UIImageView *imageView = (UIImageView *)userInfo[VoiceIcon];
        
        ICRecordManager *recordManager = [ICRecordManager shareManager];
        recordManager.playDelegate = self;
        // 文件路径
        NSString *voicePath = [NSString stringWithFormat:@"%@",[userInfo objectForKey:MessageKey]];
        
        NSData *data = [NSData dataWithContentsOfURL:[NSURL URLWithString:voicePath]];
        
        
        if (self.voicePath) {
            if ([self.voicePath isEqualToString:voicePath]) { // the same recoder
                self.voicePath = nil;
                [[ICRecordManager shareManager] stopPlayRecorder:voicePath];
                [imageView stopAnimating];
                self.currentVoiceIcon = nil;
                return;
            } else {
                [self.currentVoiceIcon stopAnimating];
                self.currentVoiceIcon = nil;
            }
        }
        [[ICRecordManager shareManager] startPlayRecorderWithData:data];
        [imageView startAnimating];
        self.voicePath = voicePath;
        self.currentVoiceIcon = imageView;
        
    } else if ([eventName isEqualToString:TinetRouterClickEventUrl]) {
        if ([self respondsToSelector:@selector(tinet_textMessageClickAction:userInfo:)]) {
            [self tinet_textMessageClickAction:(TinetClickEventTypeUrl) userInfo:userInfo];
        }
    } else if ([eventName isEqualToString:TinetRouterClickEventMiniProgramCard]) {
        if ([self respondsToSelector:@selector(tinet_textMessageClickAction:userInfo:)]) {
            [self tinet_textMessageClickAction:(TinetClickMiniProgramCard) userInfo:userInfo];
        }
    } else if ([eventName isEqualToString:TinetRouterClickEventLogisticsCard]) {
        if ([self respondsToSelector:@selector(tinet_textMessageClickAction:userInfo:)]) {
            [self tinet_textMessageClickAction:(TinetClickLogisticsCard) userInfo:userInfo];
        }
    } else if ([eventName isEqualToString:TinetRouterClickEventRobotFormIntents]) {
        
        NSString *message = @"";
        if ([userInfo isKindOfClass:[NSDictionary class]] &&
            [[userInfo allKeys] containsObject:@"content"]) {
            message = userInfo[@"content"];
        }
        NSString *intent = [userInfo objectForKey:RouterEventGetSendTextMessageRobotFormIntents]?:@"";
        [self sendTextMessagewithText:message knowledge:@"" sysTransfer:NO intent:intent];
    } else if ([eventName isEqualToString:TinetRouterClickEventKnowledge]) {
        
        NSString *message = @"";
        if ([userInfo isKindOfClass:[NSDictionary class]] &&
            [[userInfo allKeys] containsObject:@"content"]) {
            message = userInfo[@"content"];
        }
        NSString *knowledge = [userInfo objectForKey:RouterEventGetSendTextMessageKnowledge]?:@"";
        [self sendTextMessagewithText:message knowledge:knowledge sysTransfer:NO];
    } else if ([eventName isEqualToString:TinetRouterClickEventOrderNumber]) {
        if ([self respondsToSelector:@selector(tinet_textMessageClickAction:userInfo:)]) {
            [self tinet_textMessageClickAction:(TinetClickEventTypeOrderNumber) userInfo:userInfo];
        }
    } else if ([eventName isEqualToString:TinetRouterClickEventPhone]) {
        if ([self respondsToSelector:@selector(tinet_textMessageClickAction:userInfo:)]) {
            [self tinet_textMessageClickAction:(TinetClickEventTypePhone) userInfo:userInfo];
        }
    } else if ([eventName isEqualToString:TinetRouterSenderCommodityCardEventUrl]) {
        
        if (!self.chatCloseView.hidden) {   //在会话结束状态下，不再发送消息
            return;
        }
        
        NSArray *readyDisplayDataSource = [self.readyDisplayDataSource mutableCopy];
        
        __block NSInteger index = -1;
        [readyDisplayDataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if ([obj.model.message.type isEqualToString:TypeCommodityCard]) {
                index = idx;
                *stop = YES;
            }
        }];
        
        if (index >= 0) {
            [self.readyDisplayDataSource removeObjectAtIndex:index];
        }
        
        [self.commodityCardOption yy_modelToJSONObject];
        
        NSDictionary *content = @{
            @"subTitle": self.commodityCardOption.subTitle?:@"",
            @"description": self.commodityCardOption.descriptions?:@"",
            @"price": self.commodityCardOption.price?:@"",
            @"time": self.commodityCardOption.time?:@"",
            @"img": self.commodityCardOption.img?:@"",
            @"status": self.commodityCardOption.status?:@"",
            @"title":self.commodityCardOption.title?:@"",
            @"url": self.commodityCardOption.url?:@"",
            @"subUrl":self.commodityCardOption.subUrl?:@"",
            @"buttonText":self.commodityCardOption.buttonText?:@"",
            @"senderType": @"2"
        };
        
        NSMutableDictionary * mutableDic = [NSMutableDictionary dictionaryWithDictionary:content];
        if (self.commodityCardOption.extraInfo) {
            [mutableDic setObject:self.commodityCardOption.extraInfo forKey:@"extraInfo"];
        }
        if (self.commodityCardOption.extraData) {
            [mutableDic setObject:self.commodityCardOption.extraData forKey:@"extraData"];
        }
        
        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendCommodityCardMessageWithMessageStr:[mutableDic copy] success:^(TOSMessage * _Nonnull timMessage) {
            
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            
        }];
        
        self.reloadTaskManager.isScroll = NO;
        [self.reloadTaskManager startCountdown];
        
    }
    else if ([eventName isEqualToString:TinetRouterBeSendCommodityCardEventUrl]) {
        if (!self.chatCloseView.hidden) {   //在会话结束状态下，不再发送消息
            return;
        }
        
        NSArray *readyDisplayDataSource = [self.readyDisplayDataSource mutableCopy];
        
        __block NSInteger index = -1;
        [readyDisplayDataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if ([obj.model.message.type isEqualToString:TypeCommodityCard]) {
                index = idx;
                *stop = YES;
            }
        }];
        
        if (index >= 0) {
            [self.readyDisplayDataSource removeObjectAtIndex:index];
        }
        
        [self.commodityCardOption yy_modelToJSONObject];
        
        NSDictionary *content = @{
            @"subTitle": self.commodityCardOption.subTitle?:@"",
            @"description": self.commodityCardOption.descriptions?:@"",
            @"price": self.commodityCardOption.price?:@"",
            @"time": self.commodityCardOption.time?:@"",
            @"img": self.commodityCardOption.img?:@"",
            @"status": self.commodityCardOption.status?:@"",
            @"title":self.commodityCardOption.title?:@"",
            @"url": self.commodityCardOption.url?:@"",
            @"subUrl":self.commodityCardOption.subUrl?:@"",
            @"buttonText":self.commodityCardOption.buttonText?:@"",
            @"senderType": @"2"
        };
        
        NSMutableDictionary * mutableDic = [NSMutableDictionary dictionaryWithDictionary:content];
        if (self.commodityCardOption.extraInfo) {
            [mutableDic setObject:self.commodityCardOption.extraInfo forKey:@"extraInfo"];
        }
        if (self.commodityCardOption.extraData) {
            [mutableDic setObject:self.commodityCardOption.extraData forKey:@"extraData"];
        }
        __weak typeof(self) weakself = self;
        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendCommodityCardMessageWithMessageStr:[mutableDic copy] success:^(TOSMessage * _Nonnull timMessage) {
            
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            
        }];
        
        if (self.dataSource.count > 0 &&
            [self.tableView numberOfRowsInSection:0] >= self.dataSource.count) {
            [self.tableView scrollToRowAtIndexPath:[NSIndexPath indexPathForRow:self.dataSource.count-1 inSection:0] atScrollPosition:UITableViewScrollPositionBottom animated:YES];
        }
        
    }
    else if ([eventName isEqualToString:TinetRouterClickCommodityCardEvent]) {
        if ([self respondsToSelector:@selector(tinet_textMessageClickAction:userInfo:)]) {
            [self tinet_textMessageClickAction:(TinetClickCommodityCard) userInfo:userInfo];
        }
    }
}

/**
 返回数据源数量

 @param imageBrowser 图片浏览器
 @return 数量
 */
- (NSInteger)yb_numberOfCellsInImageBrowser:(YBImageBrowser *)imageBrowser {
    return self.imageDataSource.count;
}

/**
 返回当前下标对应的数据

 @param imageBrowser 图片浏览器
 @param index 当前下标
 @return 数据
 */
- (id<YBIBDataProtocol>)yb_imageBrowser:(YBImageBrowser *)imageBrowser dataForCellAtIndex:(NSInteger)index {
    return self.imageDataSource[index];
}

- (void)yb_imageBrowser:(YBImageBrowser *)imageBrowser
            pageChanged:(NSInteger)page
                   data:(id<YBIBDataProtocol>)data {
    if (page <= 10 &&
        !self.isImageDataRequest &&
        !self.isLastImageData) {
        self.isImageDataRequest = YES;
    }
}

// tap video
- (void)showLargeVideoWithPath:(NSString *)videoPath messageId:(NSString*)msgId fromRect:(CGRect)fromRect
{
    @WeakObj(self)
    ICVideoBrowserController *videoVC = [[ICVideoBrowserController alloc] initWithPath:fromRect messageId:msgId videoPath:videoPath];
    videoVC.transitioningDelegate     = selfWeak;
    videoVC.modalPresentationStyle    = UIModalPresentationCustom;
    [self presentViewController:videoVC animated:YES completion:nil];
}

// play voice
- (void)chatVoiceTaped:(TIMMessageFrame *)messageFrame
             voiceIcon:(UIImageView *)voiceIcon
               redView:(UIView *)redView
{
    ICRecordManager *recordManager = [ICRecordManager shareManager];
    recordManager.playDelegate = self;
    // 文件路径
    NSString *voicePath = [self mediaPath:messageFrame.model.mediaPath];
    NSString *amrPath   = [[voicePath stringByDeletingPathExtension] stringByAppendingPathExtension:@"amr"];
    [VoiceConverter ConvertAmrToWav:amrPath wavSavePath:voicePath];
    if (messageFrame.model.message.status == ICMessageStatus_unRead){
        messageFrame.model.message.status = ICMessageStatus_read;
        [kitUtils saveNSUserDefaults:@{
            @"msgId":messageFrame.model.message.messageId,
            @"from":messageFrame.model.message.from,
            @"to":messageFrame.model.message.to,
            @"read":@"true"
        }];
        redView.hidden = YES;
    }
    if (self.voicePath) {
        if ([self.voicePath isEqualToString:voicePath]) { // the same recoder
            self.voicePath = nil;
            [[ICRecordManager shareManager] stopPlayRecorder:voicePath];
            [voiceIcon stopAnimating];
            self.currentVoiceIcon = nil;
            return;
        } else {
            [self.currentVoiceIcon stopAnimating];
            self.currentVoiceIcon = nil;
        }
    }
    [[ICRecordManager shareManager] startPlayRecorder:voicePath];
    [voiceIcon startAnimating];
    self.voicePath = voicePath;
    self.currentVoiceIcon = voiceIcon;
}

// 文件路径
- (NSString *)mediaPath:(NSString *)originPath
{
    // 这里文件路径重新给，根据文件名字来拼接
    NSString *name = [[originPath lastPathComponent] stringByDeletingPathExtension];
    return [[ICRecordManager shareManager] receiveVoicePathWithFileKey:name];
}

// 移除录视频时的占位图片
- (void)videoPreviewLayerWillAppear:(NSTimer *)timer
{
    UIView *placeholderView = (UIView *)[timer userInfo];
    [placeholderView removeFromSuperview];
}

#pragma mark - ICRecordManagerDelegate

- (void)voiceDidPlayFinished
{
    self.voicePath = nil;
    ICRecordManager *manager = [ICRecordManager shareManager];
    manager.playDelegate = nil;
    [self.currentVoiceIcon stopAnimating];
    self.currentVoiceIcon = nil;
}

#pragma mark Inner func
-(BOOL)isGroupSession{
    NSString * preFixTwoByte = [[[OnlineDataSave shareOnlineDataSave] getEndpointId] substringToIndex:2];
    if ([preFixTwoByte isEqualToString:@"g-"]) {
        return YES;
    }else{
        return NO;
    }
}

-(BOOL)isCurrentViewControllerShow:(UIViewController *)vc{
    if (vc && vc.isViewLoaded && vc.view.window) {
        return YES;
    } else {
        return NO;
    }
}

// 当oldMessageId 为空时 即没有拉过历史记录消息的话 主动发送的消息成功后要更新oldMessageId
-(void)updateOldMessageId:(NSString *)updateOldMessageId{
    if ([kitUtils isBlankString:self.oldMessageId]) {
        self.oldMessageId = updateOldMessageId;
    }
}

// 懒加载
- (NSOperationQueue *)downloadQueue
{
    if (!_downloadQueue) {
        self.downloadQueue = [[NSOperationQueue alloc] init];
        self.downloadQueue.maxConcurrentOperationCount = 1;
    }
    return _downloadQueue;
}

- (NSMutableDictionary *)downloadOperations
{
    if (nil == _downloadOperations) {
        
        self.downloadOperations = [NSMutableDictionary dictionary];
    }
    return _downloadOperations;
}

- (NSMutableDictionary *)downloadFileDatas
{
    if (nil == _downloadFileDatas) {
        self.downloadFileDatas = [NSMutableDictionary dictionary];
    }
    return _downloadFileDatas;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    //内存警告时
    // 移除所有的下载操作缓存
    [self.downloadQueue cancelAllOperations];
    [self.downloadOperations removeAllObjects];
    // 移除所有的数据缓存
    [self.downloadFileDatas removeAllObjects];
}

-(NSInteger)getNowTimestamp{
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateStyle:NSDateFormatterMediumStyle];
    [formatter setTimeStyle:NSDateFormatterShortStyle];
    [formatter setDateFormat:@"YYYY-MM-dd HH:mm:ss"]; // ----------设置你想要的格式,hh与HH的区别:分别表示12小时制,24小时制
    //设置时区,这个对于时间的处理有时很重要
    NSTimeZone *timeZone = [NSTimeZone timeZoneWithName:@"Asia/Shanghai"];
    [formatter setTimeZone:timeZone];
    NSDate *datenow = [NSDate date]; //现在时间,你可以输出来看下是什么格式
    return (NSInteger)[datenow timeIntervalSince1970] * 1000;
}

-(NSString *)transferExtra:(NSString *)strExtra{
    NSString * extraStrShow = @"";
    if (strExtra && strExtra.length > 0) {
        BOOL bAudit = NO;
        BOOL bLook = NO;
        NSDictionary * recvExtraDic = [kitUtils dictionaryWithJsonString:strExtra];
        if ([recvExtraDic objectForKey:@"userLookPermission"]) {
//            NSArray *userLookArray = [recvExtraDic[@"userLookPermission"] componentsSeparatedByString:@","];
//            for (NSString *object in userLookArray) {
//                if ([object isEqualToString:self.curGroupType]) {
            if ((self.curGroupType.intValue & 1) == 1) {
                bLook = YES;

            }
//                }
//            }
        }
        if ([recvExtraDic objectForKey:@"userAuditPermission"]) {
//            NSArray *userAuditArray = [recvExtraDic[@"userAuditPermission"] componentsSeparatedByString:@","];
//            for (NSString *object in userAuditArray) {
//                if ([object isEqualToString:self.curGroupType]) {
            if ((self.curGroupType.intValue & 2) == 2) {

                    bAudit = YES;
                }
//            }
        }
        NSNumber * auditStatusNumber;

        if ([recvExtraDic objectForKey:@"auditStatus"]) {
            auditStatusNumber = recvExtraDic[@"auditStatus"];
        }
        
        NSDictionary * extraShowDic = @{
            @"bUserLookPermission":bLook?@"true":@"false",
            @"bUserAuditPermission":bAudit?@"true":@"false",
            @"auditStatus":auditStatusNumber?:@""
        };
        extraStrShow = [kitUtils DataTOjsonString:extraShowDic];
    }
    return extraStrShow;
}

- (void)onlineSendFilemessage:(NSData *)fileData fileName:(NSString *)fileName fileURL:(NSURL *)newURL{
    double size = [fileData length]/1000.f/1024.f;
    int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
    if (sizeLimitM < size) {
        NSString * strShow = [NSString stringWithFormat:@"请发送小于%dM文件",sizeLimitM];
        [WHToast showMessage:strShow duration:2 finishHandler:^{
            
        }];
        return;
    }
    NSString * fileTypeStr = [kitUtils mimeTypeForFileAtPath:newURL.absoluteString];
    NSDictionary* fileMessageDic = @{
                    @"fileTypeDesc": @"文件",
                    @"fileType": fileTypeStr,
                    @"fileName": fileName
                };
    
    @WeakObj(self);
    [self.reloadTaskManager startTaskWithScroll:YES completion:^{
        @StrongObj(self);
        
        // Kit
        __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCustomFile
                                                                   originalType:@"file"
                                                                        content:[kitUtils DataTOjsonString:fileMessageDic]
                                                                          extra:@""
                                                                     auditExtra:@""
                                                                           path:newURL.absoluteString
                                                                        urlPath:nil
                                                                           from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                             to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                        fileKey:[[NSUUID UUID] UUIDString]
                                                                   bigImgFileId:@""
                                                                  voiceDuration:[NSNumber numberWithInt:0]
                                                                          msgId:@""
                                                                       sendTime:[self getNowTimestamp]
                                                                       showTime:[self p_needShowTime:[NSDate date]]
                                                                        picSize:CGSizeMake(150, 60)
                                                                        picType:@""
                                                                       isSender:YES
                                                       receivedSenderByYourself:NO
                                                                         status:TIMMessageStatus_Sending
                                                                     senderType:@(2)];
        // 创建本地消息
        [self addObject:messageF isSender:YES isHeaderInsert:NO];
        // 发送中
        [self messageSending:messageF];
        
        [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendFileMessageWithFileData:fileData fileType:fileTypeStr fileName:fileName success:^(NSString * _Nonnull messageId) {
            
            @StrongObj(self);
            messageF.model.message.messageId = messageId;
            [self updateOldMessageId:messageId];
            [self messageSendSucced:messageF];
            self.reloadTaskManager.isScroll = YES;
            [self.reloadTaskManager startCountdown];
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            
            @StrongObj(self);
            [self messageSendFailed:messageF];
            self.reloadTaskManager.isScroll = YES;
            [self.reloadTaskManager startCountdown];
            //        [self innerScrollToBottom];
            int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
            NSString * strShow = [NSString stringWithFormat:@"文件过大，请发送小于%dM文件",sizeLimitM];
            [WHToast showMessage:errorDes?:strShow duration:2 finishHandler:^{
                
            }];
        }];
        
    }];
}

#pragma mark - # Private Methods
static NSTimeInterval lastUpdateMsgInterval = 0;
//static NSInteger msgRecvAccumulate = 0;
static NSTimeInterval lastDateInterval = 0;
//static NSInteger msgAccumulate = 0;
- (BOOL)p_needShowTime:(NSDate *)date
{
    if (lastDateInterval == 0 || fabs(date.timeIntervalSince1970 - lastDateInterval) > MAX_SHOWTIME_MSG_SECOND) {
        lastDateInterval = date.timeIntervalSince1970;
//        msgAccumulate = 0;
        return YES;
    }
    
//    if (lastDateInterval != 0 && fabs(date.timeIntervalSince1970 - lastDateInterval) > MAX_SHOWTIME_MSG_SECOND) {
//            lastDateInterval = date.timeIntervalSince1970;
//    //        msgAccumulate = 0;
//            NSLog(@"lastDateInterval = YES");
//            return YES;
//        }
//        if (lastDateInterval == 0) {
//            lastDateInterval = date.timeIntervalSince1970;
//        }
//        NSLog(@"lastDateInterval = NO");
//        return NO;
    
    return NO;
}

- (BOOL)p_needShowTimeStamp:(NSTimeInterval)timestamp
{
    NSDate * date = [kitUtils getDateTimeFromMilliSeconds:timestamp];
    return [self p_needShowTime:date];
//    if (lastDateInterval == 0 || timestamp - lastDateInterval > MAX_SHOWTIME_MSG_SECOND) {
//        lastDateInterval = timestamp;
//        msgAccumulate = 0;
//        return YES;
//    }
//    return NO;
}

- (BOOL)p_needUpdateUITime:(NSDate *)date
{
//    if (++msgRecvAccumulate > MAX_UPDATE_MSG_COUNT || lastUpdateMsgInterval == 0 || date.timeIntervalSince1970 - lastUpdateMsgInterval > MIN_UPDATE_MSG_SECOND) {
//        lastUpdateMsgInterval = date.timeIntervalSince1970;
//        msgRecvAccumulate = 0;
//        return YES;
//    }
    if (lastUpdateMsgInterval == 0 || fabs(date.timeIntervalSince1970 - lastUpdateMsgInterval) > MAX_SHOWTIME_MSG_SECOND) {
        lastUpdateMsgInterval = date.timeIntervalSince1970;
//        msgRecvAccumulate = 0;
        return YES;
    }
    return NO;
}

// 异步获取member的头像
-(void)getMemberAvatar:(NSString *)userId memberAvatar:(NSString *)memberAvatar type:(NSString*)type{
    @WeakObj(self)
    dispatch_async(dispatch_queue_create(0, 0), ^{
        // 子线程执行任务（比如获取较大数据）
        //1.通过URL创建NSURLRequest
        NSURLRequest *request = [[NSURLRequest alloc] initWithURL:[NSURL URLWithString:memberAvatar] cachePolicy:NSURLRequestUseProtocolCachePolicy timeoutInterval:10];
        //2.创建一个 NSMutableURLRequest 添加 header
        NSMutableURLRequest *mutableRequest = [request mutableCopy];
        //3.把值覆给request
        request = [mutableRequest copy];
        //3.建立网络连接NSURLConnection，同步请求数据
        NSHTTPURLResponse *response = nil;
        NSError *error = nil;
        NSData *fileData = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
//                    NSData * fileData = [NSData dataWithContentsOfURL:[NSURL URLWithString:member.avatar]];
//        dispatch_async(dispatch_get_main_queue(), ^{
         // 通知主线程刷新 神马的
            if (fileData) {
                TIMKitLog(@"save 头像 userId = %@",userId);
                [[ICMediaManager sharedManager] saveAvatarImage:fileData userId:userId];
            } else {
                UIImage *loadimage = nil;//[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"customer_service_default_avatar"]];
                if ([type isEqualToString:@"robot"]) {//机器人头像
                    loadimage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"customer_service_default_avatar"]];
                }else{
                    loadimage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"default_avatar"]];
                }
                
                NSData * avatrData = UIImageJPEGRepresentation(loadimage,1.0f);
                [[ICMediaManager sharedManager] saveAvatarImage:avatrData userId:userId];
            }
//        });
    });
}

- (void)dealloc {
    TIMKitLog(@"退出ChatView");
    
    
    NSString *inputText = [self.chatBoxVC.chatBox transalteEmoticonAttributedString:self.chatBoxVC.chatBox.textView.attributedText];
    
    [[OnlineDataSave shareOnlineDataSave] saveUserInputText:inputText];
    
    [[ICRecordManager shareManager] pause];
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (NSMutableDictionary <NSString *,NSMutableArray *>*)atDataDic {
    if (!_atDataDic) {
        _atDataDic = [NSMutableDictionary dictionary];
    }
    return _atDataDic;
}

#pragma mark - 历史消息解析
//拉取历史消息
-(void)analysisHistoryWithModel:(OnlineChatRecordModel*)model withItemReload:(BOOL)reload {
    NSLog(@"model ======== %@",[model yy_modelToJSONObject]);
    BOOL messageRecvDirection = nil;
    if ([model.senderType.stringValue isEqualToString:@"2"]) {//访客发送的消息
        messageRecvDirection = NO;
    }else if ([model.senderType.stringValue isEqualToString:@"4"]) {//机器人发送的消息
        messageRecvDirection = YES;
    }else{
        messageRecvDirection = YES;
    }
    
    NSString*messageStr = @"";
    
//    /// 移动端目前只处理到32种类型，超过32的都是未知消息类型
//    if ([model.messageType intValue] > 32) {
//        if ([self historyMessageRevoke:model]) {
//            return;
//        }
//        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeUnknown originalType:@"text" content:@"未知消息类型" extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:[NSNumber numberWithInteger:model.senderType.integerValue]];
//        [self addObject:messageF isSender:YES];
//        if (reload == NO) {
//            return;
//        }
//        if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
//            [self messageSendFailed:messageF];
//        }else{
//            [self messageSendSucced:messageF];
//        }
//        return;
//    }
    
    if ([model.messageType intValue] == 1) {//文本消息
        if (model.content == nil || model.content.length == 0) {
            return;
        }
        messageStr = model.content;
        
        if (messageRecvDirection) {
            //系统消息
            if ([model.senderType.stringValue isEqualToString:@"3"] || [model.senderType.stringValue isEqualToString:@"5"]) {//机器人发送的消息
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeSystem originalType:@"text" content:messageStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
                [self addObject:messageF isSender:NO];
                if (reload == NO) {
                    return;
                }
                [self messageSendSucced:messageF];
            }else{
                if ([self historyMessageRevoke:model]) {
                    return;
                }
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText originalType:@"text" content:messageStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
                [self addObject:messageF isSender:NO];
                if (reload == NO) {
                    return;
                }
                [self messageSendSucced:messageF];
            }
       
        } else {
            if ([self historyMessageRevoke:model]) {
                return;
            }
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText originalType:@"text" content:messageStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:model.senderType];
                [self addObject:messageF isSender:YES];
            if (reload == NO) {
                return;
            }
                if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
                    [self messageSendFailed:messageF];
                }else{
                    [self messageSendSucced:messageF];
                }
        }
        
    }else if ([model.messageType intValue] == 2) {//图片消息
        NSString*fileKey = @"null";
        NSString*fileName = @"null";
        if (![kitUtils isBlankString:model.fileKey]) {
            fileKey = model.fileKey;
        }
        if (![kitUtils isBlankString:model.fileName]) {
            fileName = model.fileName;
        }
        NSString * timestamp = [kitUtils getNowTimestampWithSec];
        
        NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
        NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
        NSString*urlPath =   [[NSString stringWithFormat:@"%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[NSString stringWithFormat:@"%@/api/app/message/file",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl]],fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        
        //根据msgId去本地搜寻
        UIImage *image;
        NSString *localPath = @"";
        CGFloat fixelW = 100;
        CGFloat fixelH = 100;
        NSString * fileKeyName = [model.fileKey stringByDeletingPathExtension];
        localPath = [[ICMediaManager sharedManager] smallImgPath:fileKeyName];
        image = [UIImage imageWithContentsOfFile:localPath];
        if (image) {
            self.downloadFileDatas[model.uniqueId] = image;
            fixelW = image.size.width;
            fixelH = image.size.height;
        } else {
            image = [UIImage imageWithData:[NSData dataWithContentsOfURL:[NSURL URLWithString:urlPath]]];
            if (image) {
                [[ICMediaManager sharedManager] saveImage:image msgId:fileKeyName picType:@"jpg"];
                fixelW = image.size.width;
                fixelH = image.size.height;
            }
        }
        
        if (messageRecvDirection){
            if ([self historyMessageRevoke:model]) {
                return;
            }
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic originalType:@"image" content:@"[图片]" extra:@"" auditExtra:@"" path:localPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
            [self addObject:messageF isSender:NO];
            if (reload == NO) {
                return;
            }
            [self messageSendSucced:messageF];
        }else{
            if ([self historyMessageRevoke:model]) {
                return;
            }
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic originalType:@"image" content:@"[图片]" extra:@"" auditExtra:@"" path:localPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:model.senderType];
            [self addObject:messageF isSender:YES];
            if (reload == NO) {
                return;
            }
            [self messageSendSucced:messageF];
        }
    }
    else if ([model.messageType intValue] == 3 ||
             [model.messageType intValue] == 8){//文件消息
        
        NSString*fileUrlPath = @"";
        NSString*fileKey = @"null";
        NSString*fileName = @"null";
        if (![kitUtils isBlankString:model.fileKey]) {
            fileKey = model.fileKey;
        }
        if (![kitUtils isBlankString:model.fileName]) {
            fileName = model.fileName ;
        }
        
        NSString *path = @"";
        if ([model.messageType intValue] == 8) {
            path = @"/api/kb/files/attachment";
        } else {
            path = @"/api/app/message/file";
        }
        
        //视频消息类型
        if (fileName.length>0 && [fileName rangeOfString:@"mp4"].location !=NSNotFound) {
            
            NSString * timestamp = [kitUtils getNowTimestampWithSec];
            NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
            NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
            
            NSString*urlPath =  [[NSString stringWithFormat:@"%@%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl],path,fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];

            UIImage *videoArrowImage;
            NSString * localPath;
            localPath = [[ICMediaManager sharedManager] smallImgPath:model.uniqueId];
            videoArrowImage = [UIImage imageWithContentsOfFile:localPath];
            if (!videoArrowImage) {
                UIImage *resultImg = [self getVideoPreViewImage:[NSURL URLWithString:urlPath]];
                videoArrowImage = [UIImage addImage2:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"video_play_btn_bg"]] toImage:resultImg];
                if (videoArrowImage) {
                    [[ICMediaManager sharedManager] saveImage:videoArrowImage msgId:model.uniqueId picType:@"jpg"];
                }
            }
            CGFloat fixelW = videoArrowImage.size.width;
            CGFloat fixelH = videoArrowImage.size.height;
            
            if (messageRecvDirection) {
                if ([self historyMessageRevoke:model]) {
                    return;
                }
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"jpg" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
                // 创建本地消息
                [self addObject:messageF isSender:NO];
                if (reload == NO) {
                    return;
                }
                [self messageSendSucced:messageF];
            }else{
                if ([self historyMessageRevoke:model]) {
                    return;
                }
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"jpg" isSender:YES receivedSenderByYourself:NO status:1 senderType:model.senderType];
                // 创建本地消息
                [self addObject:messageF isSender:YES];
                if (reload == NO) {
                    return;
                }
                if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
                    [self messageSendFailed:messageF];
                } else {
                   [self messageSendSucced:messageF];
                }
            }
        }else{//文件类型
            //文件消息类型
            if (fileName.length>0 ) {
                NSString * timestamp = [kitUtils getNowTimestampWithSec];
                NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
                NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
                NSString*urlPath =   [[NSString stringWithFormat:@"%@%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl],path,fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
                fileUrlPath = urlPath;
            }else{
                fileUrlPath = @"";
            }
            
            NSMutableDictionary*dict = [[NSMutableDictionary alloc]init];
            [dict setObject:model.fileName?:@"" forKey:@"fileName"];
            [dict setObject:model.fileKey?:@"" forKey:@"fileKey"];
            NSError *parseError = nil;
                NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dict options:NSJSONWritingPrettyPrinted error:&parseError];
            NSString*messageContent = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
            
            
            if ([model.messageType intValue] == 8) {
                messageContent = [kitUtils DataTOjsonString:@{@"fileName": model.fileName?:@"", @"messageType": @"8"}];
            }
            
            if (messageRecvDirection) {
                if ([self historyMessageRevoke:model]) {
                    return;
                }
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCustomFile originalType:@"file" content:messageContent extra:@"" auditExtra:@"" path:fileUrlPath urlPath:fileUrlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeZero picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
                [self addObject:messageF isSender:NO];
                if (reload == NO) {
                    return;
                }
                [self messageSendSucced:messageF];
            }else{
                
                if ([self historyMessageRevoke:model]) {
                    return;
                }
                
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCustomFile originalType:@"file" content:messageContent extra:@"" auditExtra:@"" path:fileUrlPath urlPath:fileUrlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeZero picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:model.senderType];
                [self addObject:messageF isSender:YES];
                if (reload == NO) {
                    return;
                }
                if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
                    [self messageSendFailed:messageF];
                }
                else {
                   [self messageSendSucced:messageF];
                }
            }
        }
    }
    else if( [model.messageType intValue] == 4) {//4视频消息
        NSString*fileKey = @"null";
        NSString*fileName = @"null";
        if (![kitUtils isBlankString:model.fileKey]) {
            fileKey = model.fileKey;
        }
        if (![kitUtils isBlankString:model.fileName]) {
            fileName = model.fileName;
        }
        
        //视频消息类型
        if (fileName.length>0 && ([fileName rangeOfString:@"mp4"].location !=NSNotFound || [fileName rangeOfString:@"MP4"].location !=NSNotFound)) {
            
            NSString * timestamp = [kitUtils getNowTimestampWithSec];
            NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
            NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
            NSString*urlPath =  [[NSString stringWithFormat:@"%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[NSString stringWithFormat:@"%@/api/app/message/file",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl]],fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];

            UIImage *videoArrowImage;
            NSString * localPath;
            localPath = [[ICMediaManager sharedManager] smallImgPath:model.uniqueId];
            videoArrowImage = [UIImage imageWithContentsOfFile:localPath];
            if (!videoArrowImage) {
                UIImage *resultImg = [self getVideoPreViewImage:[NSURL URLWithString:urlPath]];
                videoArrowImage = [UIImage addImage2:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"video_play_btn_bg"]] toImage:resultImg];
                if (videoArrowImage) {
                    [[ICMediaManager sharedManager] saveImage:videoArrowImage msgId:model.uniqueId picType:@"jpg"];
                }
            }
            CGFloat fixelW = videoArrowImage.size.width;
            CGFloat fixelH = videoArrowImage.size.height;
            
            if (messageRecvDirection) {
                if ([self historyMessageRevoke:model]) {
                    return;
                }
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"jpg" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
                // 创建本地消息
                [self addObject:messageF isSender:NO];
                if (reload == NO) {
                    return;
                }
                [self messageSendSucced:messageF];
            }else{
                if ([self historyMessageRevoke:model]) {
                    return;
                }
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"jpg" isSender:YES receivedSenderByYourself:NO status:1 senderType:model.senderType];
                // 创建本地消息
                [self addObject:messageF isSender:YES];
                if (reload == NO) {
                    return;
                }
                if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
                    [self messageSendFailed:messageF];
                } else {
                   [self messageSendSucced:messageF];
                }
            }
        }else{//统一认定为文件类型
            
        }
        
    }else if ([model.messageType intValue] == 5 ||
              [model.messageType intValue] == 26) {//机器人富文本消息
        
        [self.needDelRichTextIds addObject:model.uniqueId];
        NSString *tempPath = NSTemporaryDirectory();
        NSString *filePath = [tempPath stringByAppendingPathComponent:@"RichTextMessageIds"];
        [NSKeyedArchiver archiveRootObject:self.needDelRichTextIds toFile:filePath];
        
        
        if ([model.messageType intValue] == 26) {
            TOSMessageKnowledgeBaseModel *knowledgeBaseModel = [TOSMessageKnowledgeBaseModel yy_modelWithJSON:model.content];
            model.content = knowledgeBaseModel.head;
            
            if (knowledgeBaseModel.list &&
                [knowledgeBaseModel.list isKindOfClass:[NSArray class]] &&
                knowledgeBaseModel.list.count > 0) {
                
                [knowledgeBaseModel.list enumerateObjectsUsingBlock:^(TOSMessageKnowledgeBaseListModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    
                    if ([obj.type isEqualToString:@"click"]) {
                        model.content = [NSString stringWithFormat:@"%@ <knowledge data-frontend=%@>#%@</knowledge>",model.content,obj.click.content?:@"",obj.click.content?:@""];
                    }
                }];
            }
        }
        
        RichTextMessage *richTextMsg = [[RichTextMessage alloc] initMessageWithContent:model.content];
        
        NSString *contentStr = (NSString *)richTextMsg.elements;
        
        if ([self historyMessageRevoke:model]) {
            return;
        }
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:GXRichText originalType:@"text" content:contentStr extra:(NSString *)model.repliedMessage auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
//        messageF.robotProvider = model.robotProvider;
        [self addObject:messageF isSender:NO];
        if (reload == NO) {
            return;
        }
        [self messageSendSucced:messageF];
    }else if ([model.messageType intValue] == 7) {//语音消息
        NSString*fileKey = @"null";
        NSString*fileName = @"null";
        if (![kitUtils isBlankString:model.fileKey]) {
            fileKey = model.fileKey;
        }
        if (![kitUtils isBlankString:model.fileName]) {
            fileName = model.fileName;
        }

        NSString * timestamp = [kitUtils getNowTimestampWithSec];
        NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
        NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
        NSString*urlPath =   [[NSString stringWithFormat:@"%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[NSString stringWithFormat:@"%@/api/app/message/file",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl]],fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        
        NSNumber *voiceDuration = @1;
        if (messageRecvDirection) {
            if ([self historyMessageRevoke:model]) {
                return;
            }
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVoice originalType:@"voice" content:@"[语音]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:voiceDuration msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeZero picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
            [self addObject:messageF isSender:NO];
            if (reload == NO) {
                return;
            }
            [self messageSendSucced:messageF];
        }else {
            if ([self historyMessageRevoke:model]) {
                return;
            }
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVoice originalType:@"voice" content:@"[语音]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:voiceDuration msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeZero picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:model.senderType];
            [self addObject:messageF isSender:YES];
            if (reload == NO) {
                return;
            }
            if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
                [self messageSendFailed:messageF];
            } else {
               [self messageSendSucced:messageF];
            }
        }
    } else if ([model.messageType intValue] == 10) {
        BOOL isSender = NO;
        NSString *senderId;
        NSString *receiverId;
        if (messageRecvDirection) {
            isSender = NO;
            senderId = model.sender;
            receiverId = model.visitorId;
        } else {
            isSender = YES;
            senderId = model.visitorId;
            receiverId = model.sender;
        }
        
        NSData *jsonData = [model.content dataUsingEncoding:NSUTF8StringEncoding];
        NSError *err;
        NSDictionary *messageContentDic = [NSJSONSerialization JSONObjectWithData:jsonData
                                                           options:NSJSONReadingMutableContainers
                                                             error:&err];
        
        if ([self historyMessageRevoke:model]) {
            return;
        }
        
        if (messageContentDic &&
            [messageContentDic isKindOfClass:[NSDictionary class]] &&
            [[messageContentDic allKeys] containsObject:@"cardType"] &&
            [messageContentDic[@"cardType"] isKindOfClass:[NSString class]] &&
            [messageContentDic[@"cardType"] isEqualToString:@"1"]) {    //物流卡片
            
            TIMLogisticsCardMessage *cardMsg = [TIMLogisticsCardMessage yy_modelWithJSON:model.content];
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeLogisticsCard originalType:model.type content:cardMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:senderId to:receiverId fileKey:model.mainUniqueId bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:model.createTime.integerValue showTime:[self p_needShowTimeStamp:(NSTimeInterval)model.createTime.integerValue] picSize:CGSizeMake(0, 0) picType:@"" isSender:isSender receivedSenderByYourself:NO status:1 senderType:model.senderType];
            [self addObject:messageF isSender:isSender];
        } else {
            TIMCommodityCardMessage *commodityMsg = [TIMCommodityCardMessage yy_modelWithJSON:model.content];
            
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCommodityCardDetails originalType:model.type content:commodityMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:senderId to:receiverId fileKey:model.mainUniqueId bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:model.createTime.integerValue showTime:[self p_needShowTimeStamp:(NSTimeInterval)model.createTime.integerValue] picSize:CGSizeMake(0, 0) picType:@"" isSender:isSender receivedSenderByYourself:NO status:1 senderType:model.senderType];
            [self addObject:messageF isSender:isSender];
        }
        
    } else if ([model.messageType intValue] == 13) {
        
        if ([self historyMessageRevoke:model]) {
            return;
        }
        
        TOSMessageSmallProgramModel *smallProgramMsg = [TOSMessageSmallProgramModel yy_modelWithJSON:model.content];

        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeSmallProgramCard originalType:@"text" content:(NSString *)smallProgramMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:nil bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
        
        [self addObject:messageF isSender:NO];
        [self messageSendSucced:messageF];
    } else if ([model.messageType intValue] == 12) {//留言消息
        
        if (model.content == nil || model.content.length == 0) {
            return;
        }
        
        NSString*jsonTextContent = @"";
//        messageStr = model.content;
        NSError *err;
        NSData *leavejsonData = [model.content dataUsingEncoding:NSUTF8StringEncoding];
        NSDictionary *leavejsonDict = [NSJSONSerialization JSONObjectWithData:leavejsonData
                                                           options:NSJSONReadingMutableContainers
                                                             error:&err];
        if ([leavejsonDict isKindOfClass:[NSDictionary class]]) {
            NSArray* titleArr = [leavejsonDict allKeys];
            if (titleArr.count>0) {
                for (NSString*key in titleArr) {
                    jsonTextContent = [NSString stringWithFormat:@"%@\n%@:%@",jsonTextContent,key,leavejsonDict[key]];
                }
            }
        }
        if (![kitUtils isBlankString:jsonTextContent]) {
            jsonTextContent  = [jsonTextContent substringFromIndex:1];
        } else {
            jsonTextContent = model.content;
        }
        
        if (messageRecvDirection) {
            if ([self historyMessageRevoke:model]) {
                return;
            }
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText originalType:@"text" content:jsonTextContent extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
            [self addObject:messageF isSender:NO];
            if (reload == NO) {
                return;
            }
            [self messageSendSucced:messageF];
        } else {
            if ([self historyMessageRevoke:model]) {
                return;
            }
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText originalType:@"text" content:jsonTextContent extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:model.senderType];
                [self addObject:messageF isSender:YES];
            if (reload == NO) {
                return;
            }
                if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
                    [self messageSendFailed:messageF];
                }else{
                    [self messageSendSucced:messageF];
                }
        }

        
    }else if ([model.messageType intValue] == 6  ||
              [model.messageType intValue] == 15 ||//机器人相关问题
              [model.messageType intValue] == 16 ||//机器人猜你想问(热门问题)
              [model.messageType intValue] == 17 ||//机器人常见问题
              [model.messageType intValue] == 18 ||//机器人近似问题
              [model.messageType intValue] == 19 ||//机器人选项消息
              [model.messageType intValue] == 20) {//机器人相关问题
        
        NSArray <CombinationMessage *>*message = [NSArray yy_modelArrayWithClass:[CombinationMessage class] json:model.content];
        
        [message enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            obj.robotProvider = model.robotProvider;
        }];
        
        BOOL isSender = messageRecvDirection?NO:YES;
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeRobotCombination originalType:@"text" content:(NSString *)message extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:nil bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:isSender receivedSenderByYourself:NO status:1 senderType:model.senderType];
        
        [self addObject:messageF isSender:isSender];
    }else if ([model.messageType intValue] == 14) {//机器人组合消息
        
        NSArray <CombinationMessage *>*message = [NSArray yy_modelArrayWithClass:[CombinationMessage class] json:model.content];
        
        BOOL clickLikeFeature = NO;
        if (([model.robotMessageModule isEqualToString:@"documentQa:documentQa"] ||
            [model.robotMessageModule isEqualToString:@"faq:PERFECT"]) &&
            [[[OnlineDataSave shareOnlineDataSave] getMainUniqueId] isEqualToString:model.mainUniqueId] &&
            [TOSKitCustomInfo shareCustomInfo].isOpenHelpfulFeature) {
            clickLikeFeature = YES;
        }
        
        [message enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            obj.robotProvider = model.robotProvider;
            obj.clickLikeFeature = clickLikeFeature;
            obj.showRequiredWarning = NO;
            obj.unHelpfulSelectTags = [NSMutableArray array];
            /// 机器人回答id
            obj.botAnswerUniqueId = model.botAnswerUniqueId;
            /// 机器人id
            obj.sender = model.sender;
            obj.mainUniqueId = model.mainUniqueId?:@"";
            
            if (model.botAnswer.feedback &&
                [model.botAnswer.feedback isKindOfClass:[NSString class]]) {
                if ([model.botAnswer.feedback isEqualToString:@"HELPFUL"]) {
                    obj.helpfulSelectType = HelpfulAndUnHelpfulSelectType_Helpful;
                } else if ([model.botAnswer.feedback isEqualToString:@"UNHELPFUL"]) {
                    obj.helpfulSelectType = HelpfulAndUnHelpfulSelectType_UnHelpful;
                } else {
                    obj.helpfulSelectType = HelpfulAndUnHelpfulSelectType_Unknown;
                }
            }
            
            NSLog(@"model.botAnswer.feedbackContent = %@,   model.botAnswer.robotNotHelpfulItems = %@",model.botAnswer.feedbackContent,model.botAnswer.robotNotHelpfulItems);
            if ((model.botAnswer.feedbackContent &&
                 model.botAnswer.feedbackContent.length > 0) ||
                (model.botAnswer.robotNotHelpfulItems &&
                 model.botAnswer.robotNotHelpfulItems.count > 0)) {
                obj.closeClick = YES;
            } else {
                obj.closeClick = NO;
            }
            
        }];
        
        BOOL isSender = messageRecvDirection?NO:YES;
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeRobotCombination originalType:@"text" content:(NSString *)message extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:nil bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:isSender receivedSenderByYourself:NO status:1 senderType:model.senderType];
        
        [self addObject:messageF isSender:isSender];
    }else if ([model.messageType intValue] == 27) {//满意度评价
        if (model.content == nil || model.content.length == 0) {
            return;
        }
        messageStr = model.content;
        
        NSString *extra = [kitUtils convertToJsonDataWithDic:@{@"alreadyInvestigation" : model.alreadyInvestigation?:@"", @"uniqueId": model.uniqueId?:@"",@"mainUniqueId": model.mainUniqueId?:@""}];
        NSString *content = [[OnlineDataSave shareOnlineDataSave] getAppSetting];
        
        TIMMessageFrame *messageF;
        if ([TOSKitCustomInfo shareCustomInfo].satisfactionViewPopupMode) {
            messageF = [ICMessageHelper createMessageFrame:TypeSatisfactionPopup originalType:model.type content:content extra:extra auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
        } else {
            messageF = [ICMessageHelper createMessageFrame:TypeInvestigation originalType:model.type content:content extra:extra auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
        }
        
        [self addObject:messageF isSender:NO];
        if (reload == NO) {
            return;
        }
        [self messageSendSucced:messageF];
    } else if ([model.messageType intValue] == 30) {//
        
        TOSMessageTextTagModel *textTagModel = [TOSMessageTextTagModel yy_modelWithJSON:model.content];
        
        NSMutableArray <TOSMessageTextSubTagModel *>*tags = [NSMutableArray array];
        
        [textTagModel.data enumerateObjectsUsingBlock:^(NSString * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            
            TOSMessageTextSubTagModel *model = [TOSMessageTextSubTagModel yy_modelWithJSON:obj];
            [tags addObject:model];
        }];
        
        textTagModel.tags = [NSArray arrayWithArray:tags];
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeTextTag originalType:model.type content:(NSString *)textTagModel extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
        [self addObject:messageF isSender:NO];
        if (reload == NO) {
            return;
        }
        [self messageSendSucced:messageF];
    }
    else {
        if ([self historyMessageRevoke:model]) {
            return;
        }
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeUnknown originalType:@"text" content:@"未知消息类型" extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
        [self addObject:messageF isSender:YES];
        if (reload == NO) {
            return;
        }
        if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
            [self messageSendFailed:messageF];
        }else{
            [self messageSendSucced:messageF];
        }
    }
}

/// 判断拉去的历史消息中是否有消息状态为撤回消息 YES：是撤回，NO：不是撤回消息
- (BOOL)historyMessageRevoke:(OnlineChatRecordModel *)model {
    if ([model.sendStatus intValue] == 3) {
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeRevoke originalType:@"withdraw" content:@"对方撤回了一条消息" extra:@"" auditExtra:@"" path:@"" urlPath:@"" from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeZero picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
        [self addObject:messageF isSender:YES];
        return YES;
    }
    return NO;
}


//退出排队，退出排队事件
-(void)leaveQueueAction:(NSNotification *)notification {
    
    [[OnlineEventSendManager sharedOnlineEventSendManager] leaveQueueEventWithSuccess:^{
        //退出排队成功
//        [self closeChatActon];
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        //退出排队失败
    }];

}
//关闭会话
-(void)closeChatActon{
    self.isOpenSession = NO;
    TOSSessionInfoModel * infoModel = [TOSClientKit.sharedTOSKit getCurrentSessionInfo];
    
    [[OnlineEventSendManager sharedOnlineEventSendManager] closeLastSessionWithVisitorId:infoModel.visitorId success:^{
        [[OnlineDataSave shareOnlineDataSave] saveMainUniqueId:@""];
        //存储会话状态
        NSString * curMainUniqueId = [[OnlineDataSave shareOnlineDataSave] getMainUniqueId];
        TOSSessionInfoModel * model = [[TOSSessionInfoModel alloc] init];
        [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[model yy_modelToJSONString]];
        
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        
    }];
    
}

// 获取网络视频第一帧
- (UIImage*) getVideoPreViewImage:(NSURL *)path
{
    AVURLAsset *asset = [[AVURLAsset alloc] initWithURL:path options:nil];
    AVAssetImageGenerator *assetGen = [[AVAssetImageGenerator alloc] initWithAsset:asset];
    
    assetGen.appliesPreferredTrackTransform = YES;
    CMTime time = CMTimeMakeWithSeconds(0.0, 600);
    NSError *error = nil;
    CMTime actualTime;
    CGImageRef image = [assetGen copyCGImageAtTime:time actualTime:&actualTime error:&error];
    UIImage *videoImage = [[UIImage alloc] initWithCGImage:image];
    CGImageRelease(image);
    return videoImage;
}

//正则去除标签
-(NSString *)getZZwithString:(NSString *)string{
    NSRegularExpression *regularExpretion=[NSRegularExpression regularExpressionWithPattern:@"<[^>]*>|\n"
                                                                                    options:0
                                                                                      error:nil];
    string=[regularExpretion stringByReplacingMatchesInString:string options:NSMatchingReportProgress range:NSMakeRange(0, string.length) withTemplate:@""];
    return string;
}


#pragma mark - TOSInvestigationPopupViewDelegate

- (void)TOSInvestigationPopupViewCommitEvaluateSuccess {
    
    [self TOSInvestigationPopupViewCloseView];
    
}

- (void)TOSInvestigationPopupViewCloseView {
    
    /// 只需要弹出一次，即使访客没有提交评论，关闭了弹窗下次再进来也不需要再弹窗了
    [[OnlineDataSave shareOnlineDataSave] saveAlreadyInvestigation:@"1"];
    
    /// present还是push判断返回规则
    if (self.navigationController) {
        [self.navigationController popViewControllerAnimated:YES];
        
    }
    else {
        [self dismissViewControllerAnimated:YES completion:^{
            
        }];
    }
}

/// 满意度评价弹窗
- (void)investigationAlert {
    if ([TOSKitCustomInfo shareCustomInfo].satisfactionViewShowModel == SatisfactionShowModelFirstTimePopup) {
        [self firstTimeInvestigationAlert];
    } else {
        [self everyTimeInvestigationAlert];
    }
}

/// 被踢的弹窗
- (void)kickoutAlert{
    // 创建UIAlertController对象，标题为“Alert”，信息为“这是一个弹窗”
    UIAlertController *alertController = [UIAlertController alertControllerWithTitle:@"提示"
                                                                        message:@"您的咨询账号已在其他设备上登录，请重新登录"
                                                                        preferredStyle:UIAlertControllerStyleAlert];
        
    // 添加一个“确定”按钮，点击时只关闭弹窗
    UIAlertAction *okAction = [UIAlertAction actionWithTitle:@"关闭"
                                                       style:UIAlertActionStyleDefault
                                                     handler:^(UIAlertAction * _Nonnull action) {
        /// present还是push判断返回规则
        if (self.navigationController) {
            [self.navigationController popViewControllerAnimated:YES];
            
        }
        else {
            [self dismissViewControllerAnimated:YES completion:^{
                
            }];
        }
    }];
        
    [alertController addAction:okAction];
        
    // 显示弹窗，假设self是一个UIViewController
    [self presentViewController:alertController animated:YES completion:nil];
}

/// 首次弹出
- (void)firstTimeInvestigationAlert {
    TIMKitLog(@"[[OnlineDataSave shareOnlineDataSave] getAlreadyInvestigation] : %@", [[OnlineDataSave shareOnlineDataSave] getAlreadyInvestigation]);
    [self.view endEditing:YES];
    /// getAlreadyInvestigation 值为1时表示当前会话已经评论过满意度了或者已经弹出过一次弹窗了，就不进行弹窗，返回上一级页面
    if ([[[OnlineDataSave shareOnlineDataSave] getAlreadyInvestigation] isEqualToString:@"1"] || ![[TIMClient sharedTIMClient] getLibInsertOnline]) {
        [self TOSInvestigationPopupViewCloseView];
        return;
    }
    
    @weakify(self);
    
    [[OnlineRequestManager sharedCustomerManager] getInvestigationInfoSuccess:^{
        @strongify(self);
        TOSGetInvestigationInfoModel *infoModel = [TOSGetInvestigationInfoModel shareInvestigationInfoModel];
        NSLog(@"满意度评价过没：%@", infoModel.alreadyInvestigation.stringValue);
        if (infoModel.alreadyInvestigation.integerValue == 1) {
            [self TOSInvestigationPopupViewCloseView];
            return;
        }
        
        [[OnlineRequestManager sharedCustomerManager] getInvestigationUniqueIdWithType:YES
                                                                               success:^(NSString * _Nonnull messageUniqueId) {
            @strongify(self);
            if ([TOSKitCustomInfo shareCustomInfo].satisfactionViewPopupMode) {
                
                if (self.popupView) {
                    [self.popupView removeFromSuperview];
                    self.popupView = nil;
                }
                self.popupView = [[TOSSatisfactionPopupView alloc] initWithFrame:CGRectMake(0, 0, kWindowWidth, kWindowHeight - kNavTop)];
                self.popupView.hidden = YES;
                [self.view addSubview:self.popupView];
                
                self.popupView.uniqueId = messageUniqueId?:@"";
                self.popupView.mainUniqueId = @"";
                self.popupView.popViewController = YES;
                self.popupView.showNotEvaluated = NO;
                [self.popupView showPopupView];
                [[OnlineDataSave shareOnlineDataSave] saveAlreadyInvestigation:@"1"];
            } else {
                TOSInvestigationPopupView * popupView = [[TOSInvestigationPopupView alloc] initWithFrame:(CGRectMake(0, 0, kScreenWidth, kScreenHeight))];
                popupView.uniqueId = messageUniqueId;
                popupView.delegate = self;
                [popupView showPopupView];
            }
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            NSLog(@"获取满意度弹窗的uniqueid报错：%ld   请求体：%@", errCode, errorDes);
            @strongify(self);
            [self TOSInvestigationPopupViewCloseView];
        }];
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        @strongify(self);
        [self TOSInvestigationPopupViewCloseView];
    }];
}

/// 每次都弹出
- (void)everyTimeInvestigationAlert {
    TIMKitLog(@"[[OnlineDataSave shareOnlineDataSave] getAlreadyInvestigation] : %@", [[OnlineDataSave shareOnlineDataSave] getAlreadyInvestigation]);
    [self.view endEditing:YES];
    /// 只有在接入过座席状态下，才弹出满意度评价
    if (![[TIMClient sharedTIMClient] getLibInsertOnline]) {
        [self TOSInvestigationPopupViewCloseView];
        return;
    }
    
    @weakify(self);
    
    [[OnlineRequestManager sharedCustomerManager] getInvestigationInfoSuccess:^{
        @strongify(self);
        TOSGetInvestigationInfoModel *infoModel = [TOSGetInvestigationInfoModel shareInvestigationInfoModel];
        NSLog(@"满意度评价过没：%@", infoModel.alreadyInvestigation.stringValue);
        TOSSatisfactionModel *satisfactionModel = [TOSSatisfactionModel yy_modelWithJSON:[[OnlineDataSave shareOnlineDataSave] getAppSetting]];
        if (infoModel.alreadyInvestigation.integerValue == 1 &&
            satisfactionModel.investigation.multiInvestigation.integerValue == 0) {
            [self TOSInvestigationPopupViewCloseView];
            return;
        }
        
        [[OnlineRequestManager sharedCustomerManager] getInvestigationUniqueIdWithType:YES
                                                                               success:^(NSString * _Nonnull messageUniqueId) {
            @strongify(self);
            if ([TOSKitCustomInfo shareCustomInfo].satisfactionViewPopupMode) {
                
                if (self.popupView) {
                    [self.popupView removeFromSuperview];
                    self.popupView = nil;
                }
                self.popupView = [[TOSSatisfactionPopupView alloc] initWithFrame:CGRectMake(0, 0, kWindowWidth, kWindowHeight - kNavTop)];
                self.popupView.hidden = YES;
                [self.view addSubview:self.popupView];
                
                self.popupView.uniqueId = messageUniqueId?:@"";
                self.popupView.mainUniqueId = @"";
                self.popupView.popViewController = YES;
                self.popupView.showNotEvaluated = YES;
                [self.popupView showPopupView];
                [[OnlineDataSave shareOnlineDataSave] saveAlreadyInvestigation:@"1"];
            } else {
                TOSInvestigationPopupView * popupView = [[TOSInvestigationPopupView alloc] initWithFrame:(CGRectMake(0, 0, kScreenWidth, kScreenHeight))];
                popupView.uniqueId = messageUniqueId;
                popupView.delegate = self;
                [popupView showPopupView];
            }
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            NSLog(@"获取满意度弹窗的uniqueid报错：%ld   请求体：%@", errCode, errorDes);
            @strongify(self);
            [self TOSInvestigationPopupViewCloseView];
        }];
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        @strongify(self);
        [self TOSInvestigationPopupViewCloseView];
    }];
}

#pragma mark - TOSReloadTaskManagerDelegate
- (void)reloadTableViewWithScroll {
    NSLog(@"reloadTableViewWithScroll");
    [self syncDisplayData];
    [self.tableView reloadData];
    NSLog(@"isScroll firstHasGetHistoryListFinish === %ld",self.firstHasGetHistoryListFinish);
    if (self.dataSource.count > 0 &&
        [self.tableView numberOfRowsInSection:0] >= self.dataSource.count) {
        [self.tableView scrollToRowAtIndexPath:[NSIndexPath indexPathForRow:self.dataSource.count-1 inSection:0] atScrollPosition:UITableViewScrollPositionBottom animated:YES];
        if (self.stopUIFlash) {
            self.stopUIFlash = NO;
        }
        if (self.firstHasGetHistoryListFinish == 1) {
            [self addCommodityCardMessage];
            self.firstHasGetHistoryListFinish = 0;
        }
    }
}

- (void)reloadTableViewWithFlash{
    NSLog(@"reloadTableViewWithFlash");
    if (self.stopUIFlash) {
        NSLog(@"stopUIFlash");
        return;
    }
    [self syncDisplayData];
    [self.tableView reloadData];
    if (self.dataSource.count > 0 &&
        [self.tableView numberOfRowsInSection:0] >= self.dataSource.count &&
        self.firstHasGetHistoryListFinish==1) {
        [self.tableView scrollToRowAtIndexPath:[NSIndexPath indexPathForRow:self.dataSource.count-1 inSection:0] atScrollPosition:UITableViewScrollPositionBottom animated:YES];
        if (self.firstHasGetHistoryListFinish == 1) {
            [self addCommodityCardMessage];
            self.firstHasGetHistoryListFinish = 0;
        }
    }
}

/// 同步准备展示数据到展示中
- (void)syncDisplayData {
    self.dataSource = [NSMutableArray arrayWithArray:self.readyDisplayDataSource];
}

- (void)routerEventNotification:(NSNotification *)sender {
    NSDictionary *dict = [sender object];
    
    NSString *eventName = [dict tim_ObjectForKey:@"eventName"];
    NSDictionary *userInfo = [dict tim_ObjectForKey:@"userInfo"];
    
    [self routerEventWithName:eventName
                     userInfo:userInfo];
}

- (NSString *)createVideoFilePath {
    // 创建视频文件的存储路径
    NSString *filePath = [self createVideoFolderPath];
    if (filePath == nil) {
        return nil;
    }
    
    NSString *videoType = @".mp4";
    NSString *videoDestDateString = [kitUtils getMsgUUID];//[self createFileNamePrefix];
    NSString *videoFileName = [videoDestDateString stringByAppendingString:videoType];
    
    NSUInteger idx = 1;
    /*We only allow 10000 same file name*/
    NSString *finalPath = [NSString stringWithFormat:@"%@/%@", filePath, videoFileName];
    
    while (idx % 10000 && [[NSFileManager defaultManager] fileExistsAtPath:finalPath]) {
        finalPath = [NSString stringWithFormat:@"%@/%@_(%lu)%@", filePath, videoDestDateString, (unsigned long)idx++, videoType];
    }
    return finalPath;
}

- (NSString *)createVideoFolderPath
{
    NSFileManager *fileManager = [NSFileManager defaultManager];
//    NSString *homePath = NSHomeDirectory();
    NSString *documentPath = [[NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject] stringByAppendingPathComponent:@"Chat"];
    
    NSString *tmpFilePath;
    
    if (documentPath.length > 0)
    {
//        NSString *documentPath = [homePath stringByAppendingString:@"/Documents"];
        /// 判断这个目录是否存在，如果不存在需要手动创建
        if ([fileManager fileExistsAtPath:documentPath] == NO) {
            [fileManager createDirectoryAtPath:documentPath withIntermediateDirectories:YES attributes:nil error:nil];
            NSLog(@"创建目录");
        }
        
        if ([fileManager fileExistsAtPath:documentPath isDirectory:NULL] == YES)
        {
            BOOL success = NO;
            
            NSArray *paths = [fileManager contentsOfDirectoryAtPath:documentPath error:nil];
            
            //offline file folder
            tmpFilePath = [documentPath stringByAppendingString:[NSString stringWithFormat:@"/%@", @"Video"]];
            if ([paths containsObject:@"Video"] == NO)
            {
                success = [fileManager createDirectoryAtPath:tmpFilePath withIntermediateDirectories:YES attributes:nil error:nil];
                if (!success)
                {
                    tmpFilePath = nil;
                }
            }
            return tmpFilePath;
        }
    }
    
    return false;
}

- (void)copyFileFromDirectory:(NSString *)sourcePath toDirectory:(NSString *)destinationPath {
    NSFileManager *fileManager = [NSFileManager defaultManager];
    NSError *error = nil;
    
    // 检查源文件是否存在
    if ([fileManager fileExistsAtPath:sourcePath]) {
        // 拷贝文件
        BOOL success = [fileManager copyItemAtPath:sourcePath toPath:destinationPath error:&error];
        if (success) {
            NSLog(@"File copied successfully: ");
        } else {
            NSLog(@"Error copying file: %@", error.localizedDescription);
        }
    } else {
        NSLog(@"Source file does not exist: %@", sourcePath);
    }
}

- (NSString *)currentRecordFileName
{
    NSTimeInterval timeInterval = [[NSDate date] timeIntervalSince1970];
    NSString *fileName = [NSString stringWithFormat:@"%ld",(long)timeInterval];
    return fileName;
}

// 录音文件主路径
- (NSString *)recorderMainPath
{
    return [ICFileTool createPathWithChildPath:@"Chat/File"];
}

- (NSString *)recorderPathWithFileName:(NSString *)fileName
{
    NSString *path = [self recorderMainPath];
    return [path stringByAppendingPathComponent:[NSString stringWithFormat:@"%@.wav",fileName]];
}

- (NSString *)filePathWithFileName:(NSString *)fileName
{
    NSString *path = [ICFileTool createPathWithChildPath:@"File"];
    return [path stringByAppendingPathComponent:[NSString stringWithFormat:@"%@",fileName]];
}

@end
