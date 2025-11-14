//
//  ZYSegmentControl.m
//  TIMClientKit
//
//  Created by 赵言 on 2022/8/1
//  Copyright © 2022 赵言. All rights reserved.
//

#import "ZYSegmentControl.h"
#import "YYKit.h"
#import "UIView+Creat.h"
#import "UIViewExt.h"
#import "NSArray+TRTool.h"
#import <TOSKitCustomInfo.h>

@interface ZYSegmentControl ()

/**
 存放所有按钮
 */
@property (strong, nonatomic) NSMutableArray <UIButton *> *storageAllBtn;
@property (weak, nonatomic) UIScrollView *bottomView;

/**
 存放所有按钮宽度
 */
@property (strong, nonatomic) NSMutableArray <NSNumber *> *widthBtnArray;

@property (weak, nonatomic) UIButton *tempBtn;
@property (weak, nonatomic) UIView *underline;

@property (assign, nonatomic) BOOL isPerformAnimation;
@property (assign, nonatomic) StyleType styleType;

/**
 按钮颜色
 */
@property (strong, nonatomic) UIColor *btnTitleColor;

/**
 选中颜色
 */
@property (strong, nonatomic) UIColor *btnSelectedColor;

/**
 指示器颜色
 */
@property (strong, nonatomic) UIColor *underColor;

@end

static const NSUInteger BtnTag = 1000;      //按钮Tag
static const CGFloat BtnTitleFont = 14.f;      //按钮字体大小
static const CGFloat AnimateDuration = .2f; //动画时长
static const CGFloat BtnEdgeInsets = 0.f;  //按钮文字两边的空白
static const CGFloat BtnSpacing = 12.f;         //两个按钮的间距
static const CGFloat BottomEdgeInsets = 0.f;//bottomView两边的空白
static const CGFloat LineWidth = 32.f;         //下划线的宽
static const CGFloat LineHeight = 2.f;          //下划线的高

@implementation ZYSegmentControl
@synthesize selectedSegmentIndex = _selectedSegmentIndex;

- (void)reloadDataWithData:(NSString *)itemName {
    [self.itemNames addObject:itemName];
    [self.itemNames addObject:@"请选择"];
    [self reloadLayoutWithData:self.itemNames];
}

- (void)reloadDataWithDatas:(NSMutableArray <NSString *>*)itemNames {
    self.itemNames = [NSMutableArray arrayWithArray:itemNames];
    [self reloadLayoutWithData:self.itemNames];
}

- (instancetype)initWithFrame:(CGRect)frame items:(NSArray <NSString *> *)items styleType:(StyleType)styleType arrayColor:(NSArray <UIColor *> *)arrayColor {
    self = [super initWithFrame:frame];
    if (self) {
        
        UIScrollView *bottomView = [self scrollView];
        bottomView.frame = frame;
        self.bottomView = bottomView;
        self.styleType = styleType;
        self.btnTitleColor = arrayColor[0];
        self.btnSelectedColor = arrayColor[1];
        self.underColor = arrayColor[2];
        self.itemNames = [NSMutableArray arrayWithArray:items];
        
        [self reloadLayoutWithData:self.itemNames];
    }
    return self;
}

- (void)setFrame:(CGRect)frame {
    [super setFrame:frame];
    self.bottomView.frame = CGRectMake(0, 0, frame.size.width, frame.size.height);
    [self reloadLayoutWithData:self.itemNames];
}

- (UIScrollView *)scrollView {
    UIScrollView *scrollView = [[UIScrollView alloc] init];
    scrollView.showsVerticalScrollIndicator = NO;
    scrollView.showsHorizontalScrollIndicator = NO;
    scrollView.userInteractionEnabled = YES;
    scrollView.contentInset = UIEdgeInsetsZero;
    scrollView.scrollEnabled = YES;
    scrollView.bounces = YES;
    [self addSubview:scrollView];
    return scrollView;
}

+ (instancetype)zySegmentControlWithFrame:(CGRect)frame items:(NSArray <NSString *> *)items styleType:(StyleType)styleType arrayColor:(NSArray <UIColor *> *)arrayColor {
    
    return [[self alloc] initWithFrame:frame items:items styleType:styleType arrayColor:arrayColor];
}

/**
 更新布局

 @param items 数据
 */
- (void)reloadLayoutWithData:(NSMutableArray <NSString *> *)items {
    [self.bottomView removeAllSubviews];
    //scrollView 的contentSize的Height
    CGFloat contentSizeWidth = 0;
    
    NSUInteger number = items.count;
    self.widthBtnArray = [NSMutableArray array];
    self.storageAllBtn = [NSMutableArray array];
    CGFloat height = self.tos_height;
    for (NSUInteger i = 0; i < number; i++) {
        
        __block CGFloat viewX = BottomEdgeInsets;
        @weakify(self);
        [self.widthBtnArray enumerateObjectsUsingBlock:^(NSNumber * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            @strongify(self);
            viewX += obj.doubleValue;
            if (idx == self.widthBtnArray.count - 1) {
                viewX += BtnSpacing;
            } else {
                viewX += BtnSpacing;
            }
        }];
//        CGFloat viewW = [self contentWidthWithString:items[i] font:[UIFont fontWithName:@"PingFangSC-Medium" size:BtnTitleFont] height:20.f] + BtnEdgeInsets*2;
        CGFloat viewW = [self contentWidthWithString:items[i] font:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentFont height:self.tos_height] + BtnEdgeInsets*2;
        UIView *view = [self.bottomView viewWithBgColor:[UIColor clearColor]];
        view.frame = CGRectMake(viewX, 0, viewW , height);
        [self.widthBtnArray addObject:[NSNumber numberWithDouble:viewW]];
        if (i == number -1) {   //判断最后一个
            contentSizeWidth = CGRectGetMaxX(view.frame) + BottomEdgeInsets;
        }
        
        UIButton *btn = [view buttonWithType:(UIButtonTypeCustom) title:items[i] titleColor:self.btnTitleColor BgColor:[UIColor clearColor]];
        btn.tag = i + BtnTag;
//        btn.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:BtnTitleFont];
        btn.titleLabel.font = TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentFont;
        btn.titleLabel.numberOfLines = 1;
        btn.titleLabel.textAlignment = NSTextAlignmentCenter;
        [btn setTitleColor:self.btnSelectedColor forState:UIControlStateSelected];
        [btn addTarget:self action:@selector(btnAction:) forControlEvents:(UIControlEventTouchUpInside)];
        btn.frame = CGRectMake(0, 0, viewW, height);
        [self.storageAllBtn addObject:btn];
    }
    self.bottomView.contentSize = CGSizeMake(contentSizeWidth, height);
    UIView *underline = [self.bottomView viewWithBgColor:self.underColor];
    self.underline = underline;
    UIButton *btn = [self.storageAllBtn by_ObjectAtIndex:self.storageAllBtn.count - 1];
    if (self.styleType == StyleTypeLine) {
        
        underline.frame = CGRectMake(0, height - LineHeight, LineWidth, LineHeight);
        
        CGPoint point = self.underline.center;
        point.x = btn.superview.center.x;
        self.underline.center = point;
    } else {
        
        underline.frame = CGRectMake(0, (height - height/3) / 2, btn.tos_width, height * 3 / 4);
        self.underline.layer.cornerRadius = 5.f;
        [self sendSubviewToBack:self.underline];
        self.underline.center = btn.center;
    }
    [self.bottomView setContentOffset:CGPointMake(self.bottomView.contentSize.width - self.tos_width, 0) animated:YES];
    self.selectedSegmentIndex = items.count - 1;
}

#pragma mark - btnAction
- (void)btnAction:(UIButton *)sender {
    NSUInteger index = sender.tag - BtnTag;
    if ([self.delegate respondsToSelector:@selector(didClickSegmentedControl:)]) {
        [self.delegate didClickSegmentedControl:index];
    }
    [self selectedBtn:sender];
}

/**
 选中按钮
 
 @param sender 按钮
 */
- (void)selectedBtn:(UIButton *)sender {
    if (self.isPerformAnimation) {
        return;
    }
    self.isPerformAnimation = YES;
    if (self.tempBtn == nil) {
        sender.selected = YES;
        self.tempBtn = sender;
    } else if (self.tempBtn == sender) {
        sender.selected = YES;
    } else {
        self.tempBtn.selected = NO;
        sender.selected = YES;
        self.tempBtn = sender;
    }
    
    CGFloat offsetX = sender.superview.tos_x + sender.superview.tos_width/2 - self.tos_width/2;
    if (self.bottomView.contentSize.width < self.tos_width || offsetX < self.tos_width/4) {
        offsetX = 0.f;
    } else if (offsetX > self.bottomView.contentSize.width - self.tos_width) {
        offsetX = self.bottomView.contentSize.width - self.tos_width;
    }
    [self.bottomView setContentOffset:CGPointMake(offsetX, 0) animated:YES];
    
//    [UIView animateWithDuration:AnimateDuration animations:^{
        CGPoint point = self.underline.center;
        point.x = sender.superview.center.x;
        self.underline.center = point;
//    } completion:^(BOOL finished) {
        self.isPerformAnimation = NO;
//    }];
}

#pragma mark - GetAndSet
- (void)setSelectedSegmentIndex:(NSUInteger)selectedSegmentIndex {
    _selectedSegmentIndex = selectedSegmentIndex;
    [self selectedBtn:[self.storageAllBtn by_ObjectAtIndex:selectedSegmentIndex]];
}

- (NSUInteger)selectedSegmentIndex {
    return _selectedSegmentIndex;
}

/**
 计算字符串宽度
 
 @param font 字体大小
 @param height 内容高度
 @return 字符串宽度
 */
- (CGFloat)contentWidthWithString:(NSString *)string font:(UIFont *)font height:(CGFloat)height {
    CGRect rect = [string boundingRectWithSize:CGSizeMake(MAXFLOAT, height) options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName:font} context:nil];
    return rect.size.width;
}

@end
