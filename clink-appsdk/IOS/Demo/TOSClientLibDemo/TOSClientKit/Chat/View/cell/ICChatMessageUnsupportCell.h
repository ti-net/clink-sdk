//
//  ICChatMessageUnsupportCell.h
//  TIMClientKit
//
//  Created by 赵言 on 2021/1/11.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "ICChatMessageBaseCell.h"
#import "TIMLabel.h"

NS_ASSUME_NONNULL_BEGIN

@interface ICChatMessageUnsupportCell : ICChatMessageBaseCell

@property (nonatomic, strong) TIMLabel *chatLabel;

@end

NS_ASSUME_NONNULL_END
