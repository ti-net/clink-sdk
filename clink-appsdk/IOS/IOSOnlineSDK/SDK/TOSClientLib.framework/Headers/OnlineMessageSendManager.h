//
//  OnlineMessageSendManager.h
//  TIMClientLib
//
//  Created by apple on 2021/10/30.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMStatusDefine.h"
#import <TOSClientLib/TOSClientLib.h>

NS_ASSUME_NONNULL_BEGIN

@interface OnlineMessageSendManager : NSObject

+ (instancetype)sharedOnlineMessageSendManager;

#pragma mark  发送商品卡片消息
- (void)sendCommodityCardMessageWithMessageStr:(NSDictionary *)messageStr
                              success:(void (^)(TOSMessage * timMessage))successBlock
                                error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;


#pragma mark  发送文字消息
- (void)sendTextMessageWithMessageStr:(NSString*)messageStr
                              success:(void (^)(TOSMessage * timMessage))successBlock
                                error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  发送图片消息
- (void)sendImageMessageWithImageData:(NSData *)imageData
                              success:(void (^)(NSString * messageId,NSString * fileKey))successBlock
                                error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  发送语音消息
- (void)sendVoiceMessageWithVoiceData:(NSData *)voiceData
                              success:(void (^)(NSString * messageId))successBlock
                                error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  发送视频消息
- (void)sendVideoMessageWithVideoData:(NSData *)videoData
                              success:(void (^)(NSString * messageId))successBlock
                                error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  发送文件消息
- (void)sendFileMessageWithFileData:(NSData *)fileData
                              fileType:(NSString *)fileType
                              success:(void (^)(NSString * messageId))successBlock
                                error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

@end

NS_ASSUME_NONNULL_END
