//
//  BaseTableView.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/11.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "TOSBaseTableView.h"
#import "TIMConstants.h"
#import "TIMRefresh.h"

@interface TOSBaseTableView ()
{
}

@end

@implementation TOSBaseTableView

- (instancetype)initWithFrame:(CGRect)frame style:(UITableViewStyle)style {
    self = [super initWithFrame:frame style:style];
    if (self) {
        self.separatorStyle = UITableViewCellSeparatorStyleSingleLine;
        CGFloat estimatedHeight = 0.f;
        if (style == UITableViewStyleGrouped) {
            estimatedHeight = 0.001f;
            self.backgroundColor = TOSHexColor(kBackgroundColor);
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
        self.separatorColor = TOSHexColor(0xECECEC);
        
        if (@available(iOS 11.0, *)) {
            self.contentInsetAdjustmentBehavior = UIScrollViewContentInsetAdjustmentScrollableAxes;
            self.contentInset = UIEdgeInsetsMake(0,0,0,0);
            self.scrollIndicatorInsets = self.contentInset;
        }
        if (@available(iOS 15.0, *)) {
            self.sectionHeaderTopPadding = 0.f;
            [UITableView appearance].sectionHeaderTopPadding = 0.f;
        }
    }
    return self;
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
