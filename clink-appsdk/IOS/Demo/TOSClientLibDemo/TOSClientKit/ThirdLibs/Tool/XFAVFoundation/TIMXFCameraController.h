//
//  TIMXFCameraController.h
//
//
//  Created by xf-ling on 2017/6/1.
//  Copyright © 2017年 LXF. All rights reserved.
//

#import <UIKit/UIKit.h>

#define TIMER_INTERVAL 0.01f                                                    // 定时器记录视频间隔
#define VIDEO_RECORDER_MAX_TIME 90.0f                                // 视频最大时长 (单位/秒)
#define VIDEO_RECORDER_MIN_TIME 1.0f                                   // 最短视频时长 (单位/秒)
#define START_VIDEO_ANIMATION_DURATION 0.3f                      // 录制视频前的动画时间
#define DEFAULT_VIDEO_ZOOM_FACTOR 3.0f                             // 默认放大倍数

/**
 *  拍照完成后的Block回调
 *
 *  @param image 拍照后返回的image
 */
typedef void(^TakePhotosCompletionBlock)(UIImage *image, NSError *error);

/**
 *  拍摄完成后的Block回调
 *
 *  @param videoUrl 拍摄后返回的小视频地址
 *  @param videoTimeLength 小视频时长
 *  @param thumbnailImage 小视频缩略图
 */
typedef void(^ShootCompletionBlock)(NSURL *videoUrl, CGFloat videoTimeLength, UIImage *thumbnailImage, NSError *error);

@interface TIMXFCameraController : UIViewController

/**
 *  拍照完成后的Block回调
 */
@property (copy, nonatomic) TakePhotosCompletionBlock takePhotosCompletionBlock;

/**
 *  拍摄完成后的Block回调
 */
@property (copy, nonatomic) ShootCompletionBlock shootCompletionBlock;

/**
 *  自定义APP相册名字，如果为空则默认为APP的名字
 */
@property (strong, nonatomic) NSString *assetCollectionName;

/**
 *  视频文件保存文件夹，如果没有定义，默认在document/video文件夹下面
 */
@property (strong, nonatomic) NSString *videoFilePath;

+ (instancetype)defaultCameraController;

@end
