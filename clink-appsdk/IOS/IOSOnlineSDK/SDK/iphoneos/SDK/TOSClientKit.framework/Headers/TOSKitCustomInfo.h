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

/// 快速入口Item的圆角弧度
@property (nonatomic, assign) CGFloat quickEntryItem_cornerRadius;

/// 发送方气泡的颜色
@property (nonatomic, strong) UIColor *senderBubble_backGround;
/// 发送方气泡的圆角弧度
@property (nonatomic, assign) CGFloat senderBubble_cornerRadius;

/// 头像的圆角弧度
@property (nonatomic, assign) CGFloat portrait_cornerRadius;

/// 聊天背景颜色
@property (nonatomic, strong) UIColor *chat_backGround;

/// 快速入口Item的背景颜色
@property (nonatomic, strong) UIColor *quickEntryItem_backgroundColor;

/// 接收方气泡的颜色
@property (nonatomic, strong) UIColor *receiveBubble_backGround;
/// 接收方气泡的圆角弧度
@property (nonatomic, assign) CGFloat receiveBubble_cornerRadius;

/// 快速入口底部的背景颜色
@property (nonatomic, strong) UIColor *quickEntryBottom_backgroundColor;

@end

NS_ASSUME_NONNULL_END
