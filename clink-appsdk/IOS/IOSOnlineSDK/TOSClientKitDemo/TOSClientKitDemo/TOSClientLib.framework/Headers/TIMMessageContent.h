//
//  TIMMessageContent.h
//  TIMClientLib
//
//  Created by YanBo on 2020/5/5.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <TOSClientLib/NSObject+TIMModel.h>

NS_ASSUME_NONNULL_BEGIN

/**
消息内容的基类

@discussion 此类为消息实体类TIMMessage中的消息内容content的基类。
*/

@interface TIMMessageContent : NSObject <TIMModel, NSCopying, NSMutableCopying>

@end

NS_ASSUME_NONNULL_END
