//
//  ICMessageHelper.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/4/7.
//  Copyright © 2016年 gxz All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "ICChatServerDefs.h"
#import "TIMICMessage.h"

@class TIMMessageFrame;
@class ICMessage;
@class TIMMessageModel;

typedef void(^Finish)(TIMMessageFrame *messageF);
//typedef void(^FinishText)(NSDictionary *data);

@interface ICMessageHelper : NSObject


/**
 *  创建一条本地消息
 *
 *  @param type    消息类型
 *  @param content 文本消息内容
 *  @param path    图片音频本地路径
 *
 *  @return 一条消息的TIMMessageFrame
 */
+ (TIMMessageFrame *)createMessageFrame:(NSString *)type
                           originalType:(NSString *)originalType
                               content:(NSString *)content
                                 extra:(NSString *)extra
                             auditExtra:(NSString *)auditExtra
                                  path:(NSString *)path
                               urlPath:(NSString *)urlPath
                                  from:(NSString *)from
                                    to:(NSString *)to
                               fileKey:(NSString *)fileKey
                          bigImgFileId:(NSString *)bigImgFileId
                         voiceDuration:(NSNumber *)voiceDuration
                                 msgId:(NSString *)msgId
                              sendTime:(NSTimeInterval)sendTime
                              showTime:(BOOL)showTime
                               picSize:(CGSize)size
                               picType:(NSString *)picType
                              isSender:(BOOL)isSender
              receivedSenderByYourself:(BOOL)receivedSenderByYourself
                                 status:(int)status
                             senderType:(NSNumber *)senderType;

/**
 *  创建一条本地消息
 *
 *  @param type    消息类型
 *  @param content 文本消息内容
 *  @param path    图片音频本地路径
 *
 *  @return 一条消息的TIMMessageFrame
 */
+ (TIMMessageFrame *)createMessageFrame:(NSString *)type
                           originalType:(NSString *)originalType
                               content:(NSString *)content
                                 extra:(NSString *)extra
                             auditExtra:(NSString *)auditExtra
                                  path:(NSString *)path
                               urlPath:(NSString *)urlPath
                                  from:(NSString *)from
                                    to:(NSString *)to
                               fileKey:(NSString *)fileKey
                          bigImgFileId:(NSString *)bigImgFileId
                         voiceDuration:(NSNumber *)voiceDuration
                                 msgId:(NSString *)msgId
                              sendTime:(NSTimeInterval)sendTime
                              showTime:(BOOL)showTime
                               picSize:(CGSize)size
                               picType:(NSString *)picType
                              isSender:(BOOL)isSender
              receivedSenderByYourself:(BOOL)receivedSenderByYourself
                                 status:(int)status
                         fullJSONString:(NSString *)jsonStr
                             senderType:(NSNumber *)senderType;

/**
 *  创建一条发送消息
 *
 *  @param type    消息类型
 *  @param content 消息文本内容，其它类型的类型名称:[图片]
 *  @param fileKey 音频文件的fileKey
 *  @param from    发送者
 *  @param to      接收者
 *  @param lnk     连接地址URL,图片格式,文件名称 （目前没用到）
 *  @param status  消息状态 （目前没用到）
 *
 *  @return 发送的消息
 */
//+ (TIMICMessage *)createSendMessage:(NSString *)type
//                         content:(NSString *)content
//                         fileKey:(NSString *)fileKey
//                            from:(NSString *)from
//                              to:(NSString *)to
//                             lnk:(NSString *)lnk
//                          status:(NSString *)status;

+ (TIMMessageFrame *)createTimeMessageFrame:(NSString *)type
                                   content:(NSString *)content
                                      path:(NSString *)path
                                      from:(NSString *)from
                                        to:(NSString *)to
                                   fileKey:(NSString *)fileKey
                                  isSender:(BOOL)isSender
                  receivedSenderByYourself:(BOOL)receivedSenderByYourself;


+ (CGFloat)getVoiceTimeLengthWithPath:(NSString *)path;


// 坐标转换到窗口中的位置
+ (CGRect)photoFramInWindow:(UIButton *)photoView;

// 放大后的图片按钮在窗口中的位置
+ (CGRect)photoLargerInWindow:(UIButton *)photoView;


//+ (NSString *)senderNameWithID:(NSString *)ID;

// 根据消息类型得到cell的标识
+ (NSString *)cellTypeWithMessageType:(NSString *)type;

// time format
+ (NSString *)timeFormatWithDate:(NSInteger)time;



+ (NSString *)timeFormatWithDate2:(NSInteger)time;


+ (NSString *)timeFormatWithDate3:(NSString *)strTime;


+ (NSNumber *)fileType:(NSString *)type;
+ (UIImage *)allocationImage:(ICFileType)type;

+ (NSString *)timeDurationFormatter:(NSUInteger)duration;

/// 当前时间
+ (NSInteger)currentMessageTime;


@end
