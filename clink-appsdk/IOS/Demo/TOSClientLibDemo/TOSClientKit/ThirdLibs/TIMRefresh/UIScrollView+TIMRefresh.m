
//  UIScrollView+TIMRefresh.m
//  TIMRefreshExample
//
//  Created by MJ Lee on 15/3/4.
//  Copyright (c) 2015年 小码哥. All rights reserved.
//

#import "UIScrollView+TIMRefresh.h"
#import "TIMRefreshHeader.h"
#import "TIMRefreshFooter.h"
#import <objc/runtime.h>

@implementation UIScrollView (TIMRefresh)

#pragma mark - header
static const char TIMRefreshHeaderKey = '\0';

- (void)setTos_header:(TIMRefreshHeader *)tos_header {
    if (tos_header != self.tos_header) {
        // 删除旧的，添加新的
        [self.tos_header removeFromSuperview];
        [self insertSubview:tos_header atIndex:0];

        // 存储新的
        objc_setAssociatedObject(self, &TIMRefreshHeaderKey,
                                 tos_header, OBJC_ASSOCIATION_RETAIN);
    }
}

- (TIMRefreshHeader *)tos_header {
    return objc_getAssociatedObject(self, &TIMRefreshHeaderKey);
}

//- (void)setMj_header:(TIMRefreshHeader *)mj_header
//{
//    if (mj_header != self.mj_header) {
//        // 删除旧的，添加新的
//        [self.mj_header removeFromSuperview];
//        [self insertSubview:mj_header atIndex:0];
//
//        // 存储新的
//        objc_setAssociatedObject(self, &TIMRefreshHeaderKey,
//                                 mj_header, OBJC_ASSOCIATION_RETAIN);
//    }
//}
//
//- (TIMRefreshHeader *)mj_header
//{
//    return objc_getAssociatedObject(self, &TIMRefreshHeaderKey);
//}
//
#pragma mark - footer
static const char TIMRefreshFooterKey = '\0';

- (void)setTos_footer:(TIMRefreshFooter *)tos_footer {
    if (tos_footer != self.tos_footer) {
        // 删除旧的，添加新的
        [self.tos_footer removeFromSuperview];
        [self insertSubview:tos_footer atIndex:0];

        // 存储新的
        objc_setAssociatedObject(self, &TIMRefreshFooterKey,
                                 tos_footer, OBJC_ASSOCIATION_RETAIN);
    }
}

- (TIMRefreshFooter *)tos_footer {
    return objc_getAssociatedObject(self, &TIMRefreshFooterKey);
}

//- (void)setMj_footer:(TIMRefreshFooter *)mj_footer
//{
//    if (mj_footer != self.mj_footer) {
//        // 删除旧的，添加新的
//        [self.mj_footer removeFromSuperview];
//        [self insertSubview:mj_footer atIndex:0];
//
//        // 存储新的
//        objc_setAssociatedObject(self, &TIMRefreshFooterKey,
//                                 mj_footer, OBJC_ASSOCIATION_RETAIN);
//    }
//}
//
//- (TIMRefreshFooter *)mj_footer
//{
//    return objc_getAssociatedObject(self, &TIMRefreshFooterKey);
//}
//
#pragma mark - 过期

- (void)setFooter:(TIMRefreshFooter *)footer
{
    self.tos_footer = footer;
}

- (TIMRefreshFooter *)footer
{
    return self.tos_footer;
}

- (void)setHeader:(TIMRefreshHeader *)header
{
    self.tos_header = header;
}

- (TIMRefreshHeader *)header
{
    return self.tos_header;
}

#pragma mark - other

- (NSInteger)tosmj_totalDataCount {
    NSInteger totalCount = 0;
    if ([self isKindOfClass:[UITableView class]]) {
        UITableView *tableView = (UITableView *)self;

        for (NSInteger section = 0; section < tableView.numberOfSections; section++) {
            totalCount += [tableView numberOfRowsInSection:section];
        }
    } else if ([self isKindOfClass:[UICollectionView class]]) {
        UICollectionView *collectionView = (UICollectionView *)self;

        for (NSInteger section = 0; section < collectionView.numberOfSections; section++) {
            totalCount += [collectionView numberOfItemsInSection:section];
        }
    }
    return totalCount;
}

//- (NSInteger)mj_totalDataCount
//{
//    NSInteger totalCount = 0;
//    if ([self isKindOfClass:[UITableView class]]) {
//        UITableView *tableView = (UITableView *)self;
//
//        for (NSInteger section = 0; section < tableView.numberOfSections; section++) {
//            totalCount += [tableView numberOfRowsInSection:section];
//        }
//    } else if ([self isKindOfClass:[UICollectionView class]]) {
//        UICollectionView *collectionView = (UICollectionView *)self;
//
//        for (NSInteger section = 0; section < collectionView.numberOfSections; section++) {
//            totalCount += [collectionView numberOfItemsInSection:section];
//        }
//    }
//    return totalCount;
//}

@end
