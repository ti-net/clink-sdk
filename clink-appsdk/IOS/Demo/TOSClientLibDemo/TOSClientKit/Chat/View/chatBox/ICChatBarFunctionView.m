//
//  ICChatBarFunctionView.m
//  TIMClientKit
//
//  Created by 李成 on 2022/5/20.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "ICChatBarFunctionView.h"
#import "ICChatBarItemView.h"
#import "TIMMasonry.h"
#import "kitUtils.h"
#import "TIMConstants.h"
#import <TOSClientKit/TOSKitCustomInfo.h>

@interface ICChatBarFunctionView ()<UIScrollViewDelegate>

@property (nonatomic, strong) UIScrollView                * scrollView;

@end


@implementation ICChatBarFunctionView

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        self.backgroundColor = [TOSKitCustomInfo shareCustomInfo].quickEntryBottom_backgroundColor;
    }
    return self;
}

- (void)addBarItemView:(NSArray<ICChatBarItemView *> *)items {
    if (self.scrollView) {
        for (UIView * item in self.scrollView.subviews) {
            [item removeFromSuperview];
        }
    }

    for (UIView * item in self.subviews) {
        [item removeFromSuperview];
    }
    
    [self addSubview:self.scrollView];
    [self.scrollView mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
        make.edges.equalTo(self);
    }];
    
    CGFloat totalWidth = 8.0f;
    CGFloat manarge = 8.0f;
    for (NSInteger i = 0; i < items.count; i++) {
        ICChatBarItemView * item = items[i];
        item.tag = i;
        item.userInteractionEnabled = YES;
        item.layer.borderColor = TOSHexAColor(0x000000, 0.1f).CGColor;
        item.layer.borderWidth = 1.0f;
        item.layer.cornerRadius = [TOSKitCustomInfo shareCustomInfo].quickEntryItem_cornerRadius;
        item.layer.masksToBounds = YES;
        
        
        [self.scrollView addSubview:item];
        /// 获取文本长度
        CGFloat width = [self textContentWidth:item.model.titleStr]+24.f;
        [item mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
            make.width.mas_TIMequalTo(width);
            make.height.mas_TIMequalTo(25.f);
            make.left.mas_TIMequalTo(totalWidth+manarge);
//            make.centerY.equalTo(self.scrollView.mas_TIMcenterY);
            make.top.mas_TIMequalTo(8.f);
        }];
        
        UITapGestureRecognizer * tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(tapTouch:)];
        [item addGestureRecognizer:tap];
        totalWidth += (width+manarge);
    }
    
    [self.scrollView setContentSize:(CGSizeMake(totalWidth+20, 0))];
}

/// 计算文本的宽度
- (CGFloat )textContentWidth:(NSString *)text {
    CGSize size = [text sizeWithAttributes:@{NSFontAttributeName : [UIFont fontWithName:@"PingFangSC-Regular" size:12.f]}];
    return ceilf(size.width);
}


- (void)tapTouch:(UIGestureRecognizer *)gesture {
    
    if (self.delegate && [self.delegate respondsToSelector:@selector(ICChatBarFunctionViewDidSelect:)]) {
        [self.delegate ICChatBarFunctionViewDidSelect:gesture.view.tag];
    }
    
    
}


#pragma mark - 懒加载
- (UIScrollView *)scrollView {
    if (!_scrollView) {
        _scrollView = [[UIScrollView alloc] initWithFrame:(CGRectZero)];
        _scrollView.showsVerticalScrollIndicator = NO;
        _scrollView.showsHorizontalScrollIndicator = NO;
        
    }
    return _scrollView;
}

@end
