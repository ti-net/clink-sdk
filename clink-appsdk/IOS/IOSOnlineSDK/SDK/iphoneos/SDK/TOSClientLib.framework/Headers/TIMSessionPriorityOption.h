//
//  TIMSessionPriorityOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/4/27.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMSessionPriorityOption : NSObject

/**
 会话目标Id
 */
@property (nonatomic,copy, readonly) NSString* targetId;

/**
 优先级
 */
@property (nonatomic,assign, readonly) int priority;

@end

NS_ASSUME_NONNULL_END
