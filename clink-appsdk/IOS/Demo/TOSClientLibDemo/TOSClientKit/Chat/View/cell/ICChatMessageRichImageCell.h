//
//  ICChatMessageRichImageCell.h
//  TOSClientKit
//
//  Created by 言 on 2022/6/14.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "TOSBaseView.h"

NS_ASSUME_NONNULL_BEGIN

@class RichTextMessage;
@class CombinationMessage;
@interface ICChatMessageRichImageCell : TOSBaseView
/**
 robotProvider
 */
@property (nonatomic, strong) NSString *robotProvider;

- (void)setWithModel:(RichTextMessage *)model;

@end

NS_ASSUME_NONNULL_END
