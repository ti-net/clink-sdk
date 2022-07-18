//
//  TIMMessageUpdateContentOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/6/29.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMMessageUpdateContentOption : NSObject

/**
 targetId
 */
@property (nonatomic, copy, readonly) NSString* targetId;

/**
 messageId
 */
@property (nonatomic, copy, readonly) NSString* messageId;

/**
 content
 */
@property (nonatomic, copy, readonly) NSString* content;

/**
 content
 */
@property (nonatomic, copy, readonly) NSNumber *auditStatus;



/**
 参数对象初始化方法

 @param targetId                目标id
 @param messageId              更新的消息id
 @param content                  消息内容
 @return               参数对象
 */
- (instancetype)initWithOption:(NSString *)targetId messageId:(NSString *)messageId content:(NSString *)content auditStatus:(NSNumber *)auditStatus;

@end

NS_ASSUME_NONNULL_END
