//
//  TIMRefreshBackGifFooter.h
//  TIMRefreshExample
//
//  Created by MJ Lee on 15/4/24.
//  Copyright (c) 2015年 小码哥. All rights reserved.
//

#import "TIMRefreshBackStateFooter.h"

NS_ASSUME_NONNULL_BEGIN

@interface TIMRefreshBackGifFooter : TIMRefreshBackStateFooter
@property (weak, nonatomic, readonly) UIImageView *gifView;

/** 设置state状态下的动画图片images 动画持续时间duration*/
- (void)setImages:(NSArray *)images duration:(NSTimeInterval)duration forState:(TIMRefreshState)state;
- (void)setImages:(NSArray *)images forState:(TIMRefreshState)state;
@end

NS_ASSUME_NONNULL_END
