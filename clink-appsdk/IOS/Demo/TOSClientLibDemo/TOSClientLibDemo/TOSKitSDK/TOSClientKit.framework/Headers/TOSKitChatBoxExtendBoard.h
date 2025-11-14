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

/// 座席状态下是否开启变更扩展项
/// 如果重写了- (void)chatStatusChanged:(TinetChatStatusType)status方法 需要调用[super chatStatusChanged:status];
/// default: NO
@property (nonatomic, assign) BOOL                onlineChange;

/// 座席状态下的扩展项数组
@property (nonatomic, strong) NSArray <TOSKitExtendBoardItemModel *>                * onlienAllItems;


@end

NS_ASSUME_NONNULL_END
