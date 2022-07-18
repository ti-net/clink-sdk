//
//  ChatInvestigationMessage.h
//  TIMClientLib
//
//  Created by 高延波 on 2022/4/11.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "TIMMessageContent.h"
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface ChatInvestigationMessage : TIMMessageContent

@property (nonatomic, copy,readonly) NSString *content;
@property (nonatomic, copy,readonly) NSNumber *isClose;
@property (nonatomic, copy,readonly) NSString *endMessage;
@property (nonatomic, copy,readonly) NSString *webUrl;

- (instancetype)initMessageWithContent:(NSString *)content;

@end

NS_ASSUME_NONNULL_END
