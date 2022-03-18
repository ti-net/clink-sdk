//
//  RichTextMessage.h
//  TIMClientLib
//
//  Created by apple on 2021/10/28.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <TIMClientLib/TIMClientLib.h>
#import "TIMMessageContent.h"

NS_ASSUME_NONNULL_BEGIN

@interface RichTextMessage : TIMMessageContent
@property (nonatomic, copy,readonly) NSString *content;
@property (nonatomic, copy,readonly) NSString *textContent;
@property (nonatomic, copy,readonly) NSString *type;
@property (nonatomic, copy,readonly) NSString *urlPath;


- (instancetype)initMessageWithContent:(NSString *)content;

@end

NS_ASSUME_NONNULL_END
