//
//  TIMSendMultiMedia.h
//  TIMClientKit
//
//  Created by lianpeng on 2021/6/7.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <TOSClientLib/TOSMessage.h>


NS_ASSUME_NONNULL_BEGIN

@interface TIMSendMultiMedia : NSObject

/// 发送图片
/// @param imagePath 图片沙盒路径
/// @param senderId 发送者id
/// @param receiverId 接受者id
/// @param progressBlock 图片上传进度回调
/// @param successBlock 发送成功回调
/// @param errorBlock 发送失败回调
+ (void)TIMSendImageWithPath:(NSString *)imagePath senderID:(NSString *)senderId receiverID:(NSString *)receiverId progress:(void(^)(float progress))progressBlock success:(void (^)(TOSMessage * timMessage))successBlock error:(void (^)(TOSMessage * message, TIMConnectErrorCode nErrorCode, NSString * messageUuId))errorBlock;


@end

NS_ASSUME_NONNULL_END
