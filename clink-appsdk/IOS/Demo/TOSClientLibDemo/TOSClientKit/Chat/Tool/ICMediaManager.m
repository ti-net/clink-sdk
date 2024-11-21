//
//  ICMediaManager.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/12.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICMediaManager.h"
#import "UIImage+Extension.h"
#import "ICFileTool.h"
#import "NSString+Extension.h"
#import "TIMConstants.h"
#import "UIImage+TIMGIF.h"
#import "kitUtils.h"
#import "NSObject+TIMImage.h"

static UIImage *_failedImage;
@interface ICMediaManager ()

@property (nonatomic, strong) NSCache *videoImageCache;
@property (nonatomic, strong) NSCache *imageChacheMe;
@property (nonatomic, strong) NSCache *imageChacheYou;
@property (nonatomic, strong) NSCache *photoCache;
@property (nonatomic, strong) NSCache *avatarCache;

@end

@implementation ICMediaManager

+ (instancetype)sharedManager
{
    static id _instance;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _instance = [[self alloc] init];
        [[NSNotificationCenter defaultCenter] addObserver:_instance selector:@selector(clearCaches) name:UIApplicationDidReceiveMemoryWarningNotification object:nil];
        _failedImage  = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"loadImage.png"]];
    });
    return _instance;
}


- (void)clearCaches
{
    @WeakObj(self)
    [selfWeak.videoImageCache removeAllObjects];
    [selfWeak.imageChacheMe removeAllObjects];
    [selfWeak.imageChacheYou removeAllObjects];
    [selfWeak.photoCache removeAllObjects];
//    [selfWeak.avatarCache removeAllObjects];
}

// 使用文件名为key // 优先使用uuid获取本地图片 如果不存在则使用msgId获取
- (UIImage *)imageWithLocalPath:(NSString *)localPath msgId:(NSString *)msgId
{
    @WeakObj(self)
    if ([selfWeak.photoCache objectForKey:localPath.lastPathComponent]) {
        return [selfWeak.photoCache objectForKey:localPath.lastPathComponent];
    } else if (!([localPath hasSuffix:@".png"] ||
                 [localPath hasSuffix:@".jpg"] ||
                 [localPath hasSuffix:@".jpeg"] ||
                 [localPath hasSuffix:@".gif"])) {
        return nil;
    }
    UIImage *image;
    if ([localPath hasSuffix:@".gif"]) {
        NSData * gifData = [NSData dataWithContentsOfFile:localPath];
        image = [UIImage sd_animatedGIFWithData:gifData];
        // 如果还不存在那么就从msgId查找
        if (!image || [UIImagePNGRepresentation(image) isEqual:UIImagePNGRepresentation(_failedImage)]) {
            NSString * tempMsgIdPath = [localPath stringByReplacingOccurrencesOfString:localPath.lastPathComponent withString:msgId];
            NSData * giftempData = [NSData dataWithContentsOfFile:tempMsgIdPath];
            image = [UIImage sd_animatedGIFWithData:giftempData];
        }
    }else{
        NSString *path = [[localPath lastPathComponent] stringByDeletingPathExtension];

        if (path.length > 35) {
            NSString *smallPath =[NSString stringWithFormat:@"%@_small.jpeg", [localPath stringByDeletingPathExtension]];
            image = [UIImage imageWithContentsOfFile:smallPath];
            if (!image) {
                image = [UIImage imageWithContentsOfFile:localPath];

            }

        }else{
            image = [UIImage imageWithContentsOfFile:localPath];

        }
        
        // 如果还不存在那么就从msgId查找
        if (!image || [UIImagePNGRepresentation(image) isEqual:UIImagePNGRepresentation(_failedImage)]) {
            NSString * tempMsgIdPath = [localPath stringByReplacingOccurrencesOfString:localPath.lastPathComponent withString:msgId];
            image = [UIImage imageWithContentsOfFile:tempMsgIdPath];
        }
    }
    
    if (image && ![UIImagePNGRepresentation(image) isEqual:UIImagePNGRepresentation(_failedImage)]) {
        [selfWeak.photoCache setObject:image forKey:localPath.lastPathComponent];
    } else {
        image = _failedImage;
        if (!image) {
            TIMKitLog(@"image === nil localPath = %@",localPath);
        }else{
            TIMKitLog(@"image != nil localPath = %@",localPath);
        }
    }
    return image;
}

- (void)clearReuseImageMessage:(TIMMessageModel *)message
{
    @WeakObj(self)
    NSString *path = message.mediaPath;
    NSString *videoPath = message.mediaPath;// 这是整个路径
//    [selfWeak.avatarCache removeObjectForKey:message.message.from];
    [selfWeak.photoCache removeObjectForKey:path.lastPathComponent];
    [selfWeak.imageChacheMe removeObjectForKey:path.lastPathComponent];
    [selfWeak.imageChacheYou removeObjectForKey:path.lastPathComponent];
    [selfWeak.videoImageCache removeObjectForKey:[[[[videoPath lastPathComponent] stringByDeletingPathExtension] stringByAppendingPathExtension:kVideoPic] lastPathComponent]];
}

// get and save arrow image
- (UIImage *)arrowMeImage:(UIImage *)image
                     size:(CGSize)imageSize
                mediaPath:(NSString *)mediaPath
                 isSender:(BOOL)isSender
{
    @WeakObj(self)
    NSString *arrowPath = [selfWeak arrowMeImagePathWithOriginImagePath:mediaPath];
    if (!arrowPath) {
        return _failedImage;
    }
    if ([selfWeak.imageChacheMe objectForKey:arrowPath.lastPathComponent]) {
        return [selfWeak.imageChacheMe objectForKey:arrowPath.lastPathComponent];
    }
    UIImage *arrowImage = [UIImage imageWithContentsOfFile:arrowPath];
    if (arrowImage) {
        return arrowImage;
    }
    if ([UIImagePNGRepresentation(image) isEqual:UIImagePNGRepresentation(_failedImage)]) {
        return _failedImage;
    }
    arrowImage = [UIImage makeArrowImageWithSize:imageSize image:image isSender:isSender];
    if (arrowImage) {
        [selfWeak.imageChacheMe setObject:arrowImage forKey:arrowPath.lastPathComponent];
        [selfWeak saveArrowMeImage:arrowImage withMediaPath:arrowPath.lastPathComponent];
        return arrowImage;
    } else {
        [selfWeak.imageChacheMe setObject:image forKey:arrowPath.lastPathComponent];
        [selfWeak saveArrowMeImage:image withMediaPath:arrowPath.lastPathComponent];
        return image;
    }
}

// me to you
- (NSString *)arrowMeImagePathWithOriginImagePath:(NSString  *)orgImgPath
{
    @WeakObj(self)
    NSString *path = [[NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject] stringByAppendingPathComponent:kArrowMe];
    [selfWeak fileManagerWithPath:path];
    NSString *arrowPath = [path stringByAppendingPathComponent:orgImgPath.lastPathComponent];
    return arrowPath;
}

- (void)fileManagerWithPath:(NSString *)path
{
    NSFileManager *fileManager = [NSFileManager defaultManager];
    BOOL isDirExist = [fileManager fileExistsAtPath:path];
    if (!isDirExist) {
        BOOL isCreateDir = [fileManager createDirectoryAtPath:path withIntermediateDirectories:YES attributes:nil error:nil];
        if (!isCreateDir) {
            //ICLog(@"create folder failed");
            return ;
        }
    }
}

- (void)saveArrowMeImage:(UIImage *)image
                 withMediaPath:(NSString *)mediPath
{
    NSData *data = UIImageJPEGRepresentation(image,1.0f);
    [data writeToFile:mediPath atomically:NO];
}

// 路径cache/MyPic
- (NSString *)createFolderPahtWithMainFolder:(NSString *)mainFolder
                                 childFolder:(NSString *)childFolder
{
    @WeakObj(self)
    NSString *path = [mainFolder stringByAppendingPathComponent:childFolder];
    [selfWeak fileManagerWithPath:path];
    return path;
}

// 使用文件名作为key
- (UIImage *)imageConverPhotoWithVideoPath:(NSString *)imgPath
                                      size:(CGSize)imageSize
                                  isSender:(BOOL)isSender
{
    if (!imgPath) return nil;
    NSString *trueFileName = [[[imgPath stringByDeletingPathExtension] stringByAppendingPathExtension:kVideoImageType] lastPathComponent];
    if ([self.videoImageCache objectForKey:trueFileName]) {
        return [self.videoImageCache objectForKey:trueFileName];
    }
    UIImage *videoImg = [UIImage imageWithContentsOfFile:imgPath];
    UIImage *videoArrowImage = [UIImage makeArrowImageWithSize:imageSize image:videoImg isSender:isSender];
    UIImage *addImage = [UIImage addImage2:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"video_play_btn_bg"]] toImage:videoArrowImage];
    if (addImage) {
        [self.videoImageCache setObject:addImage forKey:trueFileName];
    }
    return addImage;
}

// 使用文件名作为key
- (UIImage *)videoConverPhotoWithVideoPath:(NSString *)videoPath
                                      size:(CGSize)imageSize
                                  isSender:(BOOL)isSender
{
    if (!videoPath) return nil;
    NSString *trueFileName = [[[videoPath stringByDeletingPathExtension] stringByAppendingPathExtension:kVideoImageType] lastPathComponent];
    if ([self.videoImageCache objectForKey:trueFileName]) {
        return [self.videoImageCache objectForKey:trueFileName];
    }
    UIImage *videoImg = [self videoImageWithFileName:trueFileName];
    if (videoImg) {
        UIImage *addImage = [UIImage addImage2:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"video_play_btn_bg"]] toImage:videoImg];
        [self.videoImageCache setObject:addImage forKey:trueFileName];
        return addImage;
    }
    UIImage *thumbnailImage = [UIImage videoFramerateWithPath:videoPath];
    UIImage *videoArrowImage = [UIImage makeArrowImageWithSize:imageSize image:thumbnailImage isSender:isSender];
    UIImage *resultImg = [UIImage addImage2:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"video_play_btn_bg"]] toImage:videoArrowImage];
    if (resultImg) {
        [self.videoImageCache setObject:resultImg forKey:trueFileName];
        [self saveVideoImage:resultImg fileName:trueFileName];
    }
    return resultImg;
}

/**
 *  保存图片到沙盒
 *
 *  @param image 图片
 *
 *  @return 图片路径
 */
- (NSString *)saveImage:(UIImage *)image msgId:(NSString *)msgId picType:(NSString *)picType
{
    @WeakObj(self)
    if ([picType isEqualToString:@"gif"]) {
        TIMKitLog(@"saveGIF ----- %@ -------",msgId);
    }
    NSData *imageData = UIImageJPEGRepresentation(image,1.0f);
    NSString *chachePath = [NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject];
    // 图片名称
    NSString *fileName = [NSString stringWithFormat:@"%@.%@",msgId,picType];
    NSString *mainFilePath = [selfWeak createFolderPahtWithMainFolder:chachePath childFolder:kMyPic];
    NSString *filePath = [mainFilePath stringByAppendingPathComponent:fileName];
    [imageData writeToFile:filePath atomically:NO];
    return filePath;
}

- (NSString *)getImagePath:(NSString *)msgId picType:(NSString *)picType
{
    @WeakObj(self)
    if ([picType isEqualToString:@"gif"]) {
        TIMKitLog(@"saveGIF ----- %@ -------",msgId);
    }
    NSString *chachePath = [NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject];
    // 图片名称
    NSString *fileName = [NSString stringWithFormat:@"%@.%@",msgId,picType];
    NSString *mainFilePath = [selfWeak createFolderPahtWithMainFolder:chachePath childFolder:kMyPic];
    NSString *filePath = [mainFilePath stringByAppendingPathComponent:fileName];
    return filePath;
}

// 临时增加saveImage相同的功能 保存到沙盒 在使用原图的时候
- (NSString *)saveImageData:(NSData *)imageData msgId:(NSString *)msgId picType:(NSString *)picType
{
    @WeakObj(self)
    if ([picType isEqualToString:@"gif"]) {
        TIMKitLog(@"saveGIF ----- %@ -------",msgId);
    }
    NSString *chachePath = [NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject];
    // 图片名称
    NSString *fileName = [NSString stringWithFormat:@"%@.%@",msgId,picType];
    NSString *mainFilePath = [selfWeak createFolderPahtWithMainFolder:chachePath childFolder:kMyPic];
    NSString *filePath = [mainFilePath stringByAppendingPathComponent:fileName];
    [imageData writeToFile:filePath atomically:NO];
    return filePath;
}


- (NSString *)saveOriginImage:(UIImage *)image msgId:(NSString *)msgId picType:(NSString *)picType
{
    @WeakObj(self)
    NSData *imageData = UIImageJPEGRepresentation(image,1.0f);
    NSString *chachePath = [NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject];
    // 图片名称
    NSString *fileName = [NSString stringWithFormat:@"%@origin.%@",msgId,picType];
    NSString *mainFilePath = [selfWeak createFolderPahtWithMainFolder:chachePath childFolder:kMyPic];
    NSString *filePath = [mainFilePath stringByAppendingPathComponent:fileName];
    [imageData writeToFile:filePath atomically:NO];
    return filePath;
}


- (NSString *)saveAvatarImage:(NSData *)imageData userId:(NSString *)userId
{
    @WeakObj(self)
//    [selfWeak.avatarCache setObject:imageData forKey:userId];
    NSString *chachePath = [NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject];
    // 图片名称
    NSString *fileName = [NSString stringWithFormat:@"%@",userId];
    NSString *mainFilePath = [selfWeak createFolderPahtWithMainFolder:chachePath childFolder:kAvatar];
    NSString *filePath = [mainFilePath stringByAppendingPathComponent:fileName];
    [imageData writeToFile:filePath atomically:NO];
    return filePath;
}

//存储头像连接
-(void)savaHeadImgUrl:(NSString*)imgUrl userId:(NSString *)userId{
    
    NSString*newUserId = [NSString stringWithFormat:@"%@999999",userId];

    
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    [userDefaults setObject:imgUrl?:@"" forKey:newUserId];
    [userDefaults synchronize];
}
//存储名称
-(void)savaHeadName:(NSString*)name userId:(NSString *)userId{
        
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    [userDefaults setObject:name?:@"" forKey:userId];
    [userDefaults synchronize];
}
//获取头像连接
-(NSString*)getHeadImgUrlWithUserId:(NSString *)userId{
    if (userId == nil) {
        return @"";
    }
//    NSString*newUserId =[userId substringToIndex:userId.length - 6];
    NSString*newUserId = [NSString stringWithFormat:@"%@999999",userId];

    
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    NSString*access = [userDefaults objectForKey:newUserId];
    if (access ==nil) {
        access = @"";
    }
    return access;
}
//获取名称
-(NSString*)getHeadNameWithUserId:(NSString *)userId{
    if (userId == nil) {
        return @"";
    }
    
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    NSString*access = [userDefaults objectForKey:userId];
    if (access ==nil) {
        access = @"";
    }
    return access;
}


- (NSString *)saveGifImage:(NSData *)fileData msgId:(NSString *)msgId picType:(NSString *)picType
{
    @WeakObj(self)
    NSString *chachePath = [NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject];
    // 图片名称
    NSString *fileName = [NSString stringWithFormat:@"%@.%@",msgId,picType];
    NSString *mainFilePath = [selfWeak createFolderPahtWithMainFolder:chachePath childFolder:kMyPic];
    NSString *filePath = [mainFilePath stringByAppendingPathComponent:fileName];
    [fileData writeToFile:filePath atomically:NO];
    return filePath;
}


// 发送图片的地址
- (NSString *)sendImagePath:(NSString *)imgName
{
    @WeakObj(self)
    NSString *chachePath = [NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject];
    NSString *fileName = [NSString stringWithFormat:@"%@",imgName];
    NSString *mainFilePath = [selfWeak createFolderPahtWithMainFolder:chachePath childFolder:kMyPic];
    NSString *filePath = [mainFilePath stringByAppendingPathComponent:fileName];
    return filePath;

}

/// save video image in sandbox
- (NSString *)saveVideoImage:(UIImage *)image
              fileName:(NSString *)fileName
{
    @WeakObj(self)
    NSData *imageData = UIImageJPEGRepresentation(image,1.0f);
    NSString *chachePath = [NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject];
    NSString *mainFilePath = [selfWeak createFolderPahtWithMainFolder:chachePath childFolder:kVideoPic];
    NSString *filePath = [mainFilePath stringByAppendingPathComponent:fileName];
    [imageData writeToFile:filePath atomically:NO];
    return filePath;
}

// get videoImage from sandbox
- (UIImage *)videoImageWithFileName:(NSString *)fileName
{
    return [UIImage imageWithContentsOfFile:[self videoImagePath:fileName]];
}

- (NSString *)videoImagePath:(NSString *)fileName
{
    NSString *path = [[ICFileTool cacheDirectory] stringByAppendingPathComponent:kVideoPic];
    [self fileManagerWithPath:path];
    NSString *fullPath = [path stringByAppendingPathComponent:fileName];
    return fullPath;
}

- (NSString *)avatarImagePath:(NSString *)fileName
{
//    @WeakObj(self)
    NSString *path = [[ICFileTool cacheDirectory] stringByAppendingPathComponent:kAvatar];
    
    [self fileManagerWithPath:path];
//    NSString *nfileName = [NSString stringWithFormat:@"%@",fileName];
    NSString *fullPath = [path stringByAppendingPathComponent:fileName];
//    TIMKitLog(@"avatar === %@",fullPath);
    NSData * avatarData = [NSData dataWithContentsOfFile:fullPath];
    if (avatarData) {
        return fullPath;
    }
    return @"";
}


// 保存接收到图片   fileKey-small.png
- (NSString *)receiveImagePathWithFileKey:(NSString *)fileKey
                                originName:(NSString *)originName
                                 mimeType:(NSString *)mimeType
{
    // 目前是png，以后说不定要改
    NSString *fileName = [NSString stringWithFormat:@"%@%@.%@",fileKey,originName,mimeType];
    NSString *mainFilePath = [self createFolderPahtWithMainFolder:[ICFileTool cacheDirectory] childFolder:kMyPic];
    return [mainFilePath stringByAppendingPathComponent:fileName];
}

// get image with imgName
- (NSString *)imagePathWithName:(NSString *)imageName
{
    return [[[ICFileTool cacheDirectory] stringByAppendingPathComponent:kMyPic] stringByAppendingPathComponent:imageName];
}


// origin image path
- (NSString *)originImgPath:(TIMMessageFrame *)messageF
{
    return [[ICMediaManager sharedManager] receiveImagePathWithFileKey:messageF.model.message.messageId originName:@"origin" mimeType:@"jpg"];
}

- (NSString *)originGifImgPath:(TIMMessageFrame *)messageF
{
    return [[ICMediaManager sharedManager] receiveImagePathWithFileKey:messageF.model.message.messageId originName:@"origin" mimeType:@"gif"];
}

- (NSString *)smallGifImgPath:(TIMMessageFrame *)messageF
{
    return [[ICMediaManager sharedManager] receiveImagePathWithFileKey:messageF.model.message.messageId originName:@"" mimeType:@"gif"];
}


// small image path
- (NSString *)smallImgPath:(NSString *)messageId
{
    return [[ICMediaManager sharedManager] receiveImagePathWithFileKey:messageId originName:@"" mimeType:@"jpg"];
}

- (NSString *)smallGifPath:(NSString *)messageId
{
    return [[ICMediaManager sharedManager] receiveImagePathWithFileKey:messageId originName:@"" mimeType:@"gif"];
}



- (NSString *)delieveImagePath:(NSString *)fileKey
{
    NSString *fileName = [NSString stringWithFormat:@"%@%@",fileKey,@".png"];
    NSString *mainFilePath = [self createFolderPahtWithMainFolder:[ICFileTool cacheDirectory] childFolder:kDeliver];
    return [mainFilePath stringByAppendingPathComponent:fileName];
}

- (NSString *)deliverFilePath:(NSString *)name
                         type:(NSString *)type
{
    NSString *fileName = [NSString stringWithFormat:@"%@.%@",name,type];
    NSString *mainFilePath = [self createFolderPahtWithMainFolder:[ICFileTool cacheDirectory] childFolder:kDeliver];
    return [mainFilePath stringByAppendingPathComponent:fileName];
}

- (BOOL)isEqualFailImage:(UIImage *)image{
    return [_failedImage isEqual:image];
}


#pragma mark - Getter and Setter

- (NSCache *)videoImageCache
{
    if (nil == _videoImageCache) {
        _videoImageCache = [[NSCache alloc] init];
    }
    return _videoImageCache;
}

- (NSCache *)imageChacheMe
{
    if (nil == _imageChacheMe) {
        _imageChacheMe = [[NSCache alloc] init];
    }
    return _imageChacheMe;
}

- (NSCache *)imageChacheYou
{
    if (nil == _imageChacheYou) {
        _imageChacheYou = [[NSCache alloc] init];
    }
    return _imageChacheYou;
}

- (NSCache *)photoCache
{
    if (nil == _photoCache) {
        _photoCache = [[NSCache alloc] init];
    }
    return _photoCache;
}

- (NSCache *)avatarCache
{
    if (nil == _avatarCache) {
        _avatarCache = [[NSCache alloc] init];
    }
    return _avatarCache;
}









@end
