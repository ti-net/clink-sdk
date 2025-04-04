//
//  ICTools.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/9/27.
//  Copyright © 2016年 gxz. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

/// UIButton 枚举
typedef NS_ENUM(NSInteger, TeilButtonEdgeInsetsStyle) {
    TeilButtonEdgeInsetsStyleTop          =   0,
    TeilButtonEdgeInsetsStyleLeft         =   1,
    TeilButtonEdgeInsetsStyleBottom       =   2,
    TeilButtonEdgeInsetsStyleRight        =   3
};

@interface ICTools : NSObject

+(BOOL)hasPermissionToGetCamera;

/// UIButton 图片布局
+ (void)layoutButton:(UIButton *)button WithEdgeInsetsStyle:(TeilButtonEdgeInsetsStyle)style
     imageTitleSpace:(CGFloat)space;

@end
