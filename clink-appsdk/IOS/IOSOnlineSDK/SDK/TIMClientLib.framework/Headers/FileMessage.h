//
//  FileMessage.h
//  OSClientLib
//
//  Created by apple on 2021/10/19.
//  Copyright © 2021 YanBo. All rights reserved.
//
#import "TIMMessageContent.h"
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface FileMessage : TIMMessageContent

@property (nonatomic, copy,readonly) NSString *content;
@property (nonatomic, copy,readonly) NSString *fileUrlPath;
@property (nonatomic, copy,readonly) NSString *type;

- (instancetype)initMessageWithContent:(NSString *)content;

@end

NS_ASSUME_NONNULL_END
