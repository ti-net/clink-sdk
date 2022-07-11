//
//  TIMCustomizeMessage.h
//  TIMClientLib
//
//  Created by YanBo on 2020/8/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "TIMMessageContent.h"

NS_ASSUME_NONNULL_BEGIN

@interface TIMCustomizeMessage : TIMMessageContent<NSCoding>

/**
 自定义消息模板
 */
@property (nonatomic, copy,readonly) NSString * templateName;

/**
 图片详情
 */
@property (nonatomic, copy,readonly) NSString *imgUrl;

/**
 标题
 */
@property (nonatomic, copy,readonly) NSString *shareTitle;

/**
 内容标题
 */
@property (nonatomic, copy,readonly) NSString *title;

/**
 文本描述富文本
 */
@property (nonatomic, copy,readonly) NSString *content;


// 其他模板
@property (nonatomic, copy,readonly) NSDictionary *bodyDic;

/**
 文本消息的附加信息 加密
 */
@property (nonatomic, copy,readonly) NSString *extra;

/**
 初始化文本消息

 @param templateName       自定义消息模板名称
 @param imgUrl                    图片详情URL
 @param shareTitle           标题
 @param title                      内容标题
 @param content                  文本描述富文本
 @return               消息对象
 */
- (instancetype)initMessageWithBody:(NSString *)templateName
                             imgUrl:(NSString *)imgUrl
                         shareTitle:(NSString *)shareTitle
                              title:(NSString *)title
                            content:(NSString *)content;
/**
初始化文本消息

@param templateName       自定义消息模板名称
@param imgUrl                    图片详情URL
@param shareTitle           标题
@param title                      内容标题
@param content                  文本描述富文本
@param extra                      自定义内容
@return               消息对象
*/

- (instancetype)initMessageWithBody:(NSString *)templateName
                             imgUrl:(NSString *)imgUrl
                         shareTitle:(NSString *)shareTitle
                              title:(NSString *)title
                            content:(NSString *)content
                              extra:(NSString *)extra;

/// 初始化文件类型消息
/// @param templateName 自定义模板名称
/// @param body body
/// @param extra extra
-(instancetype)initMessageWithBody:(NSString *)templateName
                              body:(NSDictionary *)body
                             extra:(NSString *)extra;

/**
初始化文本消息

@param rtcMediaVersion                     RTCMedia模板版本号
@param onlyAudio                                    是否只有音频
@param duration                                       通话时长s
@param stopAction                                  通话结束的状态
@param extra                                               自定义内容
@return                           消息对象
*/
-(instancetype)initMessageWithRTCMedia:(NSString *)rtcMediaVersion
                             onlyAudio:(NSNumber*)onlyAudio
                              duration:(int)duration
                                stopAction:(NSString *)stopAction
                                 extra:(NSString *)extra;
@end

NS_ASSUME_NONNULL_END
