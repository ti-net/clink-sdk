//
//  TIMQExtendBoardView.h
//  QKeyBoardDemo
//
//  Created by 侯力 on 2024/03/15.
//  Copyright (c) 2021年 侯力. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface TIMQExtendBoardItemModel : NSObject

/**
 *  Cell图片
 */
@property (nonatomic, strong) UIImage *normalIconImage;

/**
 *  Cell标题
 */
@property (nonatomic, strong) NSString *title;

/**
 *  根据正常图片和标题初始化一个Model对象
 *
 *  @param normalIconImage 正常图片
 *  @param title           标题
 *
 *  @return 返回一个Model对象
 */
- (instancetype)initWithNormalIconImage:(UIImage *)normalIconImage
                                  title:(NSString *)title;

@end


@protocol TIMQExtendBoardViewDelegate <NSObject>

@optional
/**
 *  点击拓展面板的cell
 *
 *  @param shareMenuItem 被点击的第三方Model对象，可以在这里做一些特殊的定制
 *  @param index         被点击的位置
 */
- (void)didSelectExtendBoardItem:(TIMQExtendBoardItemModel *)shareMenuItem atIndex:(NSInteger)index;

@end


@interface TIMQExtendBoardView : UIView

//第三方功能Models
@property (nonatomic, strong) NSArray *extendBoardItems;

@property (nonatomic, weak) id <TIMQExtendBoardViewDelegate> delegate;

/**
 *  根据数据源刷新第三方功能按钮的布局
 */
- (void)reloadData;


- (void)reloadItemOfIndex:(int)index withNormalIconImage:(UIImage *)image withTitle:(NSString *)title ;

@end
