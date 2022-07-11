//
//  ChatLeaveMessage.h
//  TIMClientLib
//
//  Created by apple on 2021/12/20.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <TOSClientLib/TOSClientLib.h>

NS_ASSUME_NONNULL_BEGIN

@interface ChatLeaveMessage : TIMMessageContent

@property (nonatomic, copy,readonly) NSString *content;
@property (nonatomic, copy,readonly) NSString *welcomContent;//留言引导语
@property (nonatomic, copy,readonly) NSString *leaveTip;//留言成功提示语
@property (nonatomic, strong,readonly) NSMutableArray *leaveMessageFields;


- (instancetype)initMessageWithContent:(NSString *)content;

@end

NS_ASSUME_NONNULL_END
