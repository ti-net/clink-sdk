//
//  TOSCommodityCardView.h
//  TOSClientKit
//
//  Created by 李成 on 2024/9/25.
//  Copyright © 2024 YanBo. All rights reserved.
//  聊天页面底部的商品卡片UI

#import <TOSClientKit/TOSClientKit.h>

NS_ASSUME_NONNULL_BEGIN


@protocol TOSCommodityCardViewDelegate <NSObject>

/// 点击关闭事件
- (void)TOSCommodityCardViewCloseTouch;

/// 点击发送商品事件
- (void)TOSCommodityCardViewSendTouch;

/// 卡片整体的点击事件
- (void)TOSCommodityCardViewTapTouch;

@end

@interface TOSCommodityCardView : TOSBaseView

@property (nonatomic, weak) id <TOSCommodityCardViewDelegate>                delegate;



@end

NS_ASSUME_NONNULL_END
