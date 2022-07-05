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
#import "TOSSessionInfoModel.h"

NS_ASSUME_NONNULL_BEGIN
@class ChatInvestigationMessage;
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
                    success:(void (^)(TOSSessionInfoModel * sessModel))successBlock
                      error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  获取全局配置信息
-(void)getAppSettings:(void (^)(void))successBlock
                      error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  获取历史消息
-(void)getChatRecordListLastTime:(NSString *)lastTime
                           limit:(NSString *)limit
                         success:(void (^)(NSArray * chatList))successBlock
                           error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  发送图片消息
-(void)sendImgMessageWithImageData:(NSData *)imageData
                           success:(void (^)(NSDictionary * result))successBlock
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

#pragma mark  获取评价状态
-(void)getInvestigationStateWithMainUniqueId:(NSString *)mainUniqueId
                                    uniqueId:(NSString *)uniqueId
                                     success:(void (^)(OnlineChatRecordModel * model))successBlock
                                       error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark  获取评价地址
-(void)getInvestigationUrlSuccess:(void (^)(NSString * investigationUrl))successBlock
                            error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBloc;

/**
 未读消息获取
@param visitorId          当前用户ID
@param mainUniqueId    会话ID （如果为空字符串就是获取总的未读消息数）
*/
- (void)sessionInfoUnreadCountCurrentVisitorId:(NSString *)visitorId WithMainUniqueId:(NSString *)mainUniqueId withSuccess:(void (^)(NSString *lastMessage , NSInteger unreadCount))successBlock withError:(void (^)(NSString *errorStr))errorBlock;

/*
 进入会话发送已读
@param mainUniqueId    会话ID     (内部 OnlineRequestManager 类调用)
 **/
- (void)sessionInfoReadWithMainUniqueId:(NSString *)mainUniqueId;


@end

NS_ASSUME_NONNULL_END
