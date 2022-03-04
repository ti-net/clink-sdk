//
//  BaseTableView.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/11.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "BaseTableView.h"
#import <MJRefresh/MJRefresh.h>

@interface BaseTableView ()
{
    FooterRefresh _footerRefresh;
    HeaderRefresh _headerRefresh;
}

@end

@implementation BaseTableView

- (instancetype)initWithFrame:(CGRect)frame style:(UITableViewStyle)style {
    self = [super initWithFrame:frame style:style];
    if (self) {
        self.separatorStyle = UITableViewCellSeparatorStyleSingleLine;
        CGFloat estimatedHeight = 0.f;
        if (style == UITableViewStyleGrouped) {
            estimatedHeight = 0.001f;
            self.backgroundColor = kHexColor(0xF5F8F9);
        } else {
            estimatedHeight = 0.f;
            self.backgroundColor = [UIColor whiteColor];
        }
        self.estimatedRowHeight = estimatedHeight;
        self.estimatedSectionFooterHeight = estimatedHeight;
        self.estimatedSectionHeaderHeight = estimatedHeight;
        self.sectionFooterHeight = estimatedHeight;
        self.sectionHeaderHeight = estimatedHeight;
        self.showsVerticalScrollIndicator = YES;
        self.showsHorizontalScrollIndicator = NO;
        [self setTableHeaderView:[[UIView alloc] initWithFrame:CGRectMake(0, 0, kWindowWidth, estimatedHeight)]];
        [self setTableFooterView:[[UIView alloc] initWithFrame:CGRectMake(0, 0, kWindowWidth, estimatedHeight)]];
        self.separatorColor = kHexColor(0xECECEC);
        
        if (@available(iOS 11.0, *)) {
            self.contentInsetAdjustmentBehavior = UIScrollViewContentInsetAdjustmentScrollableAxes;
            self.contentInset = UIEdgeInsetsMake(0,0,0,0);
            self.scrollIndicatorInsets = self.contentInset;
        }
    }
    return self;
}

- (MJRefreshBackNormalFooter *)setFooterRefresh:(FooterRefresh)footerRefresh {
    _footerRefresh = footerRefresh;
    MJRefreshBackNormalFooter *footer = [MJRefreshBackNormalFooter footerWithRefreshingTarget:self refreshingAction:@selector(footerAction)];
    [footer setTitle:TRLocalizedString(@"没有更多数据了") forState:MJRefreshStateNoMoreData];
    [footer setTitle:TRLocalizedString(@"上拉可以加载更多") forState:(MJRefreshStateIdle)];
    [footer setTitle:TRLocalizedString(@"松开立即加载更多") forState:(MJRefreshStatePulling)];
    [footer setTitle:TRLocalizedString(@"正在加载更多数据...") forState:(MJRefreshStateRefreshing)];
    [footer setTitle:TRLocalizedString(@"加载完成") forState:(MJRefreshStateWillRefresh)];
    
    self.mj_footer = footer;
    self.mj_footer.hidden = NO;
    self.mj_footer.automaticallyChangeAlpha = YES;
    return footer;
}

- (MJRefreshNormalHeader *)setHeaderRefresh:(HeaderRefresh)headerRefresh {
    _headerRefresh = headerRefresh;
    MJRefreshNormalHeader *header = [MJRefreshNormalHeader headerWithRefreshingTarget:self refreshingAction:@selector(headerAction)];
    header.lastUpdatedTimeLabel.hidden = YES;
    
    [header setTitle:TRLocalizedString(@"下拉可以刷新") forState:(MJRefreshStateIdle)];
    [header setTitle:TRLocalizedString(@"松开立即刷新") forState:(MJRefreshStatePulling)];
    [header setTitle:TRLocalizedString(@"正在刷新数据中...") forState:(MJRefreshStateRefreshing)];
    [header setTitle:TRLocalizedString(@"刷新完成") forState:(MJRefreshStateWillRefresh)];
    
    self.mj_header = header;
    self.mj_header.hidden = NO;
    self.mj_header.automaticallyChangeAlpha = YES;
    return header;
}

- (void)footerAction {
    if (_footerRefresh) {
        _footerRefresh();
    }
}

- (void)headerAction {
    if (_headerRefresh) {
        _headerRefresh();
    }
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event{
//    if (TRSharedAppDelegate.isShowKeyboard) {
//        [TRSharedAppDelegate.window endEditing:YES];
//    }
    /// 全局
    [super touchesBegan:touches withEvent:event];
}

- (CGFloat)tableView:(UITableView *)tableView estimatedHeightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 0.001f;
}

- (CGFloat)tableView:(UITableView *)tableView estimatedHeightForFooterInSection:(NSInteger)section {
    return 0.001f;
}

- (CGFloat)tableView:(UITableView *)tableView estimatedHeightForHeaderInSection:(NSInteger)section {
    return 0.001f;
}

@end
