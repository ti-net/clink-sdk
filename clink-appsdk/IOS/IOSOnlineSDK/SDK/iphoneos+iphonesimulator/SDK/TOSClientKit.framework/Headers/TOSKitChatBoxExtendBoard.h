//
//  TOSKitChatBoxExtendBoard.h
//  TOSClientKit
//
//  Created by 言 on 2022/7/13.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <TOSClientLib/TOSClientLib.h>

NS_ASSUME_NONNULL_BEGIN

@class TOSKitExtendBoardItemModel;
@interface TOSKitChatBoxExtendBoard : TIMLibBaseModel

+ (TOSKitChatBoxExtendBoard *)shareChatBoxExtendBoard;

/// 扩展项
@property (nonatomic, strong) NSArray <TOSKitExtendBoardItemModel *>*allItems;

@end

NS_ASSUME_NONNULL_END
