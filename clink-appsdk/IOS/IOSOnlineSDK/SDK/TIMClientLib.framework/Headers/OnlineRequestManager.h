//
//  OnlineRequestManager.h
//  TIMClientLib
//
//  Created by apple on 2021/10/29.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMStatusDefine.h"

#import "OnlineTokenModel.h"
#import "OnlineChatRecordModel.h"
#import "OnlineClientInfoModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface OnlineRequestManager : NSObject

+ (instancetype)sharedCustomerManager;

#pragma mark 获取token
-(void)getUserInfoWithUserId:(NSString*)userId
                    nickname:(NSString*)nickname
                    phoneNum:(NSString*)phoneNum
                   headerUrl:(NSString*)headerUrl
              connectSuccess:(void (^)(void))connectSuccessBlock
                       error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock
              tokenIncorrect:(void (^)(void))tokenIncorrectBlock;

#pragma mark  建立会话访客初始化
-(void)visitorReadyWithDict:(NSDictionary*)moreDict
                    success:(void (^)(NSString * mainUniqueId))successBlock
                      error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  获取历史消息
-(void)getChatRecordListLastTime:(NSString *)lastTime
                           limit:(NSString *)limit
                         success:(void (^)(NSArray * chatList))successBlock
                           error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  发送图片消息
-(void)sendImgMessageWithImageData:(NSData *)imageData
                           success:(void (^)(void))successBlock
                             error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  发送语音消息
-(void)sendVoiceMessageWithVoiceData:(NSData *)voiceData
                             success:(void (^)(void))successBlock
                               error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  发送视频消息
-(void)sendVideoMessageWithVideoData:(NSData *)videoData
                             success:(void (^)(void))successBlock
                               error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  发送文件消息
-(void)sendFileMessageWithFileData:(NSData *)fileData
                          fileType:(NSString *)fileType
                             success:(void (^)(void))successBlock
                               error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  获取客服信息
-(void)getClientInfoWithSender:(NSString *)sender
                    senderType:(NSString *)senderType
                         success:(void (^)(OnlineClientInfoModel * model))successBlock
                         error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

@end

NS_ASSUME_NONNULL_END
