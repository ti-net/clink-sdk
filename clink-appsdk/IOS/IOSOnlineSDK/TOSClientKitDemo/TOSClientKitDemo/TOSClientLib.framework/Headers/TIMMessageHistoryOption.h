//
//  TIMMessageHistoryOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/4/27.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMMessageHistoryOption : NSObject

/**
 会话目标Id
 */
@property (nonatomic, copy, readonly) NSString* targetId;


/**
 消息Id
 */
@property (nonatomic, copy, readonly) NSString* messageId;


/**
 查询数量
 */
@property (nonatomic, assign, readonly) int limit;

/**
 选传 消息类型 仅支持查询 text, video, image
 */
@property (nonatomic, copy) NSString *type;

/**
参数对象初始化方法

@param targetId                 接收方userId或groupId
@param messageId                消息Id
@param limit                       查询的最大数
@return               参数对象
*/
- (instancetype)initWithOption:(NSString *)targetId messageId:(NSString *)messageId limit:(int)limit;

@end

NS_ASSUME_NONNULL_END
