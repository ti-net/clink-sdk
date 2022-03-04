//
//  ChatLeaveMessage.h
//  TIMClientLib
//
//  Created by apple on 2021/12/23.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "TIMMessageContent.h"
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface ChatLeaveReceiveMessage : TIMMessageContent

@property (nonatomic, copy,readonly) NSString *content;
@property (nonatomic, copy,readonly) NSString *textContent;


- (instancetype)initMessageWithContent:(NSString *)content;

@end

NS_ASSUME_NONNULL_END
