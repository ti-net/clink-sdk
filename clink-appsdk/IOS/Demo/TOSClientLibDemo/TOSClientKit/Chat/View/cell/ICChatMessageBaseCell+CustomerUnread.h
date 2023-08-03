//
//  ICChatMessageBaseCell+CustomerUnread.h
//  TIMClientKit
//
//  Created by lianpeng on 2021/5/18.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "ICChatMessageBaseCell.h"
#import "TIMMessageModel.h"
NS_ASSUME_NONNULL_BEGIN

@interface ICChatMessageBaseCell (CustomerUnread)


/// cell中展示已读、未读
/// @param model 消息模型
- (void)BMWCustomerUnreadStatusDisplayWithModel:(TIMMessageModel *)model;

@end

NS_ASSUME_NONNULL_END
