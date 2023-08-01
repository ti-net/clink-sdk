//
//  ICChatMessageRichTextCell.h
//  TOSClientKit
//
//  Created by 言 on 2022/6/14.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "TOSBaseView.h"
#import "YYLabel.h"

NS_ASSUME_NONNULL_BEGIN

@class RichTextMessage;
@class CombinationMessage;
@interface ICChatMessageRichTextCell : TOSBaseView

@property (nonatomic, strong) YYLabel *chatLabel;

- (void)setWithModel:(RichTextMessage *)model;

@end

NS_ASSUME_NONNULL_END
