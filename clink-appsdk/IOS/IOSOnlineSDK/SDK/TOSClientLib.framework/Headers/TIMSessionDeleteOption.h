//
//  TIMSessionDeleteOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/4/27.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMSessionDeleteOption : NSObject

/**
 会话目标Id
 */
@property (nonatomic,copy, readonly) NSString* targetId;

/**
 参数对象初始化方法

 @param targetId                 目标会话Id
 @return                参数对象
 */
- (instancetype)initWithOption:(NSString*)targetId;

@end

NS_ASSUME_NONNULL_END
