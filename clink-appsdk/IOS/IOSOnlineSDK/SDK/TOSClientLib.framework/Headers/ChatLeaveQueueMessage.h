//
//  ChatLeaveQueueMessage.h
//  OSClientLib
//
//  Created by apple on 2021/10/19.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMMessageContent.h"
NS_ASSUME_NONNULL_BEGIN

@interface ChatLeaveQueueMessage : TIMMessageContent

@property (nonatomic, copy,readonly) NSString *content;

- (instancetype)initMessageWithContent:(NSString *)content;

@end

NS_ASSUME_NONNULL_END
