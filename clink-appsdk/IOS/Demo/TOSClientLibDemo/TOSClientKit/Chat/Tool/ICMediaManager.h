//
//  ICMediaManager.h
//  XZ_WeChat
//
//  Created by ts.
//

#import <Foundation/Foundation.h>
#import "TIMMessageModel.h"
#import "TIMMessageFrame.h"

#define kAvatar @"Chat/Avatar"
#define kArrowMe @"Chat/ArrowMe"
#define kMyPic @"Chat/MyPic"
#define kVideoPic @"Chat/VideoPic"
#define kDeliver @"Deliver"

#define kVideoImageType @"jpg"


@interface ICMediaManager : NSObject

+ (instancetype)sharedManager;

/**
 *  get image from local path
 *
 *  @param localPath 路径
 *
 *  @return 图片
 */
- (UIImage *)imageWithLocalPath:(NSString *)localPath msgId:(NSString *)msgId;
- (UIImage *)imageWithMsgId:(NSString *)msgId localPath:(NSString *)localPath;

- (void)clearReuseImageMessage:(TIMMessageModel *)message;

// me to you
- (UIImage *)arrowMeImage:(UIImage *)image
                     size:(CGSize)imageSize
                mediaPath:(NSString *)mediaPath
                 isSender:(BOOL)isSender;

- (void)saveArrowMeImage:(UIImage *)image
                  withMediaPath:(NSString *)mediPath;

/**
 *  创建图片的保存路径
 *
 *  @param mainFolder  主地址
 *  @param childFolder 子地址
 *
 *  @return 地址
 */
- (NSString *)createFolderPahtWithMainFolder:(NSString *)mainFolder
                                 childFolder:(NSString *)childFolder;

/**
 *  保存图片到沙盒
 *
 *  @param image 图片
 *
 *  @return 图片路径
 */
- (NSString *)saveImage:(UIImage *)image msgId:(NSString*)msgId picType:(NSString *)picType;
/// 获取本地图片路径
/// @param msgId 消息id
/// @param picType 图片类型
- (NSString *)getImagePath:(NSString *)msgId picType:(NSString *)picType;
// 原图保存到沙盒 临时使用 TODO
- (NSString *)saveImageData:(NSData *)imageData msgId:(NSString *)msgId picType:(NSString *)picType;

- (NSString *)saveOriginImage:(UIImage *)image msgId:(NSString*)msgId picType:(NSString *)picType;

- (NSString *)saveAvatarImage:(NSData *)imageData userId:(NSString *)userId;

- (NSString *)saveGifImage:(NSData *)fileData msgId:(NSString*)msgId picType:(NSString *)picType;

- (void)clearCaches;

// 发送图片的地址
- (NSString *)sendImagePath:(NSString *)imgName;

// 保存视频和封面
- (NSString *)saveVideoImage:(UIImage *)image
              fileName:(NSString *)fileName;

/// image cover image
- (UIImage *)imageConverPhotoWithVideoPath:(NSString *)imgPath
                                      size:(CGSize)imageSize
                                  isSender:(BOOL)isSender;
/// video first cover image
- (UIImage *)videoConverPhotoWithVideoPath:(NSString *)videoPath
                                      size:(CGSize)imageSize
                                  isSender:(BOOL)isSender;


// 保存接收到图片 small-fileKey.png
- (NSString *)receiveImagePathWithFileKey:(NSString *)fileKey
                               originName:(NSString *)originName
                                 mimeType:(NSString *)mimeType;

// 小图路径
- (NSString *)smallImgPath:(NSString *)fileKey;
- (NSString *)smallGifPath:(NSString *)msgId;


// 原图路径
- (NSString *)originImgPath:(TIMMessageFrame *)messageF;
- (NSString *)smallGifImgPath:(TIMMessageFrame *)messageF;
- (NSString *)originGifImgPath:(TIMMessageFrame *)messageF;


// get image with imgName
- (NSString *)imagePathWithName:(NSString *)imageName;

// get videoImage from sandbox
- (UIImage *)videoImageWithFileName:(NSString *)fileName;

// 送达号
- (NSString *)delieveImagePath:(NSString *)fileKey;
- (NSString *)deliverFilePath:(NSString *)name
                         type:(NSString *)type;

- (NSString *)videoImagePath:(NSString *)fileName;
- (NSString *)avatarImagePath:(NSString *)fileName;

- (BOOL)isEqualFailImage:(UIImage *)image;


//存储头像连接
-(void)savaHeadImgUrl:(NSString*)imgUrl userId:(NSString *)userId;
//存储头像名称
-(void)savaHeadName:(NSString*)name userId:(NSString *)userId;
//获取头像连接
-(NSString*)getHeadImgUrlWithUserId:(NSString *)userId;
//获取名称
-(NSString*)getHeadNameWithUserId:(NSString *)userId;

@end
