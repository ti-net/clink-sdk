//  代码地址: https://github.com/CoderMJLee/TIMRefresh
//  代码地址: http://code4app.com/ios/%E5%BF%AB%E9%80%9F%E9%9B%86%E6%88%90%E4%B8%8B%E6%8B%89%E4%B8%8A%E6%8B%89%E5%88%B7%E6%96%B0/52326ce26803fabc46000000
//  TIMRefreshFooter.m
//  TIMRefreshExample
//
//  Created by MJ Lee on 15/3/5.
//  Copyright (c) 2015年 小码哥. All rights reserved.
//

#import "TIMRefreshFooter.h"
#include "UIScrollView+TIMRefresh.h"

@interface TIMRefreshFooter()

@end

@implementation TIMRefreshFooter
#pragma mark - 构造方法
+ (instancetype)footerWithRefreshingBlock:(TIMRefreshComponentAction)refreshingBlock
{
    TIMRefreshFooter *cmp = [[self alloc] init];
    cmp.refreshingBlock = refreshingBlock;
    return cmp;
}
+ (instancetype)footerWithRefreshingTarget:(id)target refreshingAction:(SEL)action
{
    TIMRefreshFooter *cmp = [[self alloc] init];
    [cmp setRefreshingTarget:target refreshingAction:action];
    return cmp;
}

#pragma mark - 重写父类的方法
- (void)prepare
{
    [super prepare];
    
    // 设置自己的高度
    self.mj_h = TIMRefreshFooterHeight;
    
    // 默认不会自动隐藏
//    self.automaticallyHidden = NO;
}

#pragma mark - 公共方法
- (void)endRefreshingWithNoMoreData
{
    TIMRefreshDispatchAsyncOnMainQueue(self.state = TIMRefreshStateNoMoreData;)
}

- (void)noticeNoMoreData
{
    [self endRefreshingWithNoMoreData];
}

- (void)resetNoMoreData
{
    TIMRefreshDispatchAsyncOnMainQueue(self.state = TIMRefreshStateIdle;)
}

- (void)setAutomaticallyHidden:(BOOL)automaticallyHidden
{
    _automaticallyHidden = automaticallyHidden;
}
@end
