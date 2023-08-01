//
//  ICChatMessageCommodityCardDetailsCell.h
//  TIMClientKit
//
//  Created by 赵言 on 2022/5/24.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "ICChatMessageBaseCell.h"

NS_ASSUME_NONNULL_BEGIN

@protocol ICChatMessageCommodityCardDetailsCellDelegate <NSObject>

- (void)ICChatMessageCommodityCardDetailsCellWithIndexPath:(NSIndexPath *)indexPath;

@end


@interface ICChatMessageCommodityCardDetailsCell : ICChatMessageBaseCell

@property (nonatomic, strong) NSIndexPath                * currentIndexPath;

@property (nonatomic, weak) id <ICChatMessageCommodityCardDetailsCellDelegate>                delegate;

@end

NS_ASSUME_NONNULL_END
