//
//  UITableView+EmptyData.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/11.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "UITableView+EmptyData.h"

@implementation UITableView (EmptyData)

/**
 * 根据数据源的个数显示内容
 */
- (NSInteger)addNetworkStatus:(BOOL)networkStatus byDataSourceCount:(NSInteger)count noDataImage:(NSString *)noDataImage  {
    if (count == 0) {
        UIView *view = [[UIView alloc] init];
        self.backgroundView = ({
            view.backgroundColor = [UIColor whiteColor];
            view;
        });
        UIImageView *imageView = [[UIImageView alloc] initWithFrame:CGRectMake(0, 0, SCALE(160.f), SCALE(160.f))];
        imageView.image = [UIImage imageNamed:noDataImage];
        imageView.contentMode = UIViewContentModeScaleAspectFit;
        [view addSubview:imageView];
        imageView.center = view.center;
//        self.separatorStyle = UITableViewCellSeparatorStyleNone;
        self.scrollEnabled = YES;
        self.tableFooterView.hidden = YES;
        self.mj_footer.hidden = YES;
        self.tableHeaderView.hidden = YES;
        self.tableFooterView.hidden = YES;
    } else {
        self.backgroundView = nil;
//        self.separatorStyle = UITableViewCellSeparatorStyleSingleLine;
        self.scrollEnabled = YES;
        self.tableFooterView.hidden = NO;
        self.mj_footer.hidden = NO;
        self.tableHeaderView.hidden = NO;
        self.tableFooterView.hidden = NO;
    }
    return count;
}

/**
 * 根据数据源的个数显示内容
 */
- (NSInteger)addByDataSourceCount:(NSInteger)count noDataImage:(NSString *)noDataImage describe:(NSString *)describe {
    if (count == 0) {
        UIView *view = [[UIView alloc] initWithFrame:self.bounds];
        self.backgroundView = ({
            view.backgroundColor = [UIColor whiteColor];
            view;
        });
        view.frame = CGRectMake(0, -64.f, self.width, self.height);
        
        UIView *showView = [view viewWithBgColor:[UIColor clearColor]];
        
        //图片
        UIImageView *imageView = [[UIImageView alloc] initWithFrame:CGRectMake(0, 0, SCALE(160.f), SCALE(160.f))];
        imageView.image = [UIImage imageNamed:noDataImage];
        [view addSubview:imageView];
        
        //文字
        UILabel *label = [view labelWithFontSize:14.f color:kHexColor(0xB5B4B4)];
        label.numberOfLines = 0;
        label.text = describe;
        label.textAlignment = NSTextAlignmentCenter;
        
        //添加
        [showView addSubview:imageView];
        [showView addSubview:label];
        
        
        //设置Frame
        [imageView sizeToFit];
        imageView.centerX = showView.width/2;
        imageView.y = 0;
        
        
        label.frame = CGRectMake(0, imageView.bottom, self.width, 50.f);
        [label sizeToFit];
        label.centerX = showView.width/2;
        label.y = imageView.bottom + 14.5f;
        
        showView.size = CGSizeMake(imageView.width, imageView.height + label.height + 14.5f);
        showView.centerX = view.width/2;
        showView.centerY = view.height/2 - 3;
        
        imageView.centerX = showView.width/2;
        imageView.y = 0;
        
        label.centerX = showView.width/2;
        label.y = imageView.bottom + 14.5f;
        
        
        self.scrollEnabled = YES;
        self.tableFooterView.hidden = YES;
        self.mj_footer.hidden = YES;
        self.tableHeaderView.hidden = YES;
        self.tableFooterView.hidden = YES;
    } else {
        self.backgroundView = nil;
        self.scrollEnabled = YES;
        self.tableFooterView.hidden = NO;
        self.mj_footer.hidden = NO;
        self.tableHeaderView.hidden = NO;
        self.tableFooterView.hidden = NO;
    }
    return count;
}

@end
