//
//  CustomRefreshHeader.m
//  TOSClientKitDemo
//
//  Created by 李成 on 2023/6/19.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import "CustomRefreshHeader.h"
#define kScreenWidth [UIScreen mainScreen].bounds.size.width

@implementation CustomRefreshHeader

- (void)prepare {
    [super prepare];
    
    UIView * loadView = [[UIView alloc] initWithFrame:(CGRectMake(0, 0, 40, 40))];
    loadView.backgroundColor = UIColor.orangeColor;
    loadView.center = CGPointMake(kScreenWidth/2, self.frame.size.height/2);
    [self addSubview:loadView];
    
}

- (void)placeSubviews {
    [super placeSubviews];
    
    
    
    
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
