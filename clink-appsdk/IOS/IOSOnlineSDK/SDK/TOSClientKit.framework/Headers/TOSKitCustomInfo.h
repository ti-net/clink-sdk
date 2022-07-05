//
//  TOSKitCustomInfo.h
//  TOSClientKit
//
//  Created by 言 on 2022/7/1.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <TOSClientLib/TOSClientLib.h>

NS_ASSUME_NONNULL_BEGIN

@interface TOSKitCustomInfo : TIMLibBaseModel

+ (TOSKitCustomInfo *)shareCustomInfo;

@property (nonatomic, assign) CGFloat quickEntryItem_cornerRadius;

@property (nonatomic, strong) UIColor *senderBubble_backGround;
@property (nonatomic, assign) CGFloat senderBubble_cornerRadius;

@property (nonatomic, assign) CGFloat portrait_cornerRadius;

@property (nonatomic, strong) UIColor *chat_backGround;

@property (nonatomic, strong) UIColor *quickEntryItem_backgroundColor;

@property (nonatomic, strong) UIColor *receiveBubble_backGround;
@property (nonatomic, assign) CGFloat receiveBubble_cornerRadius;

@property (nonatomic, strong) UIColor *quickEntryBottom_backgroundColor;

@end

NS_ASSUME_NONNULL_END
