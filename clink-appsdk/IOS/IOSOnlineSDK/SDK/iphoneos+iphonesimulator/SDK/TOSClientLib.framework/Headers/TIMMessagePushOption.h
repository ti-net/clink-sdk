//
//  TIMMessagePushOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/5/7.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMMessagePushOption : NSObject

/**
 pushEnable  默认是YES
 */
@property (nonatomic, assign, readonly) BOOL pushEnable;

/**
 pushTitle
 */
@property (nonatomic, copy, readonly) NSString* pushTitle;

/**
 pushContent
 */
@property (nonatomic, copy, readonly) NSString* pushContent;

/**
 pushData
 */
@property (nonatomic, copy, readonly,nullable) NSDictionary* pushData;

/**
 参数对象初始化方法

 @param title                      推送的主题
 @param content                  消息内容
 @return               参数对象
 */
- (instancetype)initWithOption:(BOOL)pushEnable title:(NSString *)title content:(NSString *)content pushData:(nullable NSDictionary *)pushData;

@end

NS_ASSUME_NONNULL_END
