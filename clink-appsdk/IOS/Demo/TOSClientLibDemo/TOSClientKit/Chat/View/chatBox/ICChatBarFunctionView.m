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

/** chotBox的顶部边线 */
@property (nonatomic, strong) UIView *topLine;

@end


@implementation ICChatBarFunctionView

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        self.backgroundColor = [TOSKitCustomInfo shareCustomInfo].quickEntryBottom_backgroundColor;
        self.clipsToBounds = NO;
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(updateTicketMessageStatus:) name:kUpdateTicketMessageStatus object:nil];
    }
    return self;
}

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self name:kUpdateTicketMessageStatus object:nil];
}

- (void)updateTicketMessageStatus:(NSNotification *)sender {
    [self.scrollView subviews];
    for (UIView *subview in [self.scrollView subviews]) {
        if ([subview isKindOfClass:[ICChatBarItemView class]]) {
            ICChatBarItemView *itemView = (ICChatBarItemView *)subview;
            if ([itemView.model.eventName isEqualToString:TOS_EVENT_NAME_TICKET_MESSAGE_STATUS]) {
                itemView.warnLabel.hidden = YES;
            }
        }
    }
}

- (void)addBarItemView:(NSArray<ICChatBarItemView *> *)items {
    @WeakObj(self);
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
        @StrongObj(self);
        make.edges.equalTo(self);
    }];
    
    [self.scrollView addSubview:self.topLine];
    [self.topLine mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
        @StrongObj(self);
        make.left.right.mas_TIMequalTo(0);
        make.top.mas_TIMequalTo(0);
        make.width.equalTo(self.mas_TIMwidth).offset(0);
        make.height.mas_TIMequalTo([TOSKitCustomInfo shareCustomInfo].chatBox_topLineHeight);
    }];
    
    CGFloat totalWidth = 12.0f;
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
        CGFloat width = [self textContentWidth:item.model.name 
                                          font:[UIFont fontWithName:@"PingFangSC-Regular" size:14.f]]+24.f;
        [item mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
            make.width.mas_TIMequalTo(width);
            make.height.mas_TIMequalTo(32.f);
            make.left.mas_TIMequalTo(totalWidth);
//            make.centerY.equalTo(self.scrollView.mas_TIMcenterY);
            make.top.mas_TIMequalTo(8.f);
        }];
        
        [self.scrollView addSubview:item.warnLabel];
        NSNumber *staffCommentTotalCount = item.model.dynamicConfigParameters[TOS_STAFF_COMMENT_TOTAL_COUNT];
        CGFloat warnWidth = [self textContentWidth:staffCommentTotalCount.stringValue
                                              font:[UIFont fontWithName:@"PingFangSC-Regular" size:12.f]]+8.f;
        [item.warnLabel mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
            make.right.equalTo(item.contentLabel.mas_TIMright).offset(4.f);
            make.bottom.equalTo(item.contentLabel.mas_TIMbottom).offset(-25.f);
            make.height.mas_TIMequalTo(16.f);
            make.width.mas_TIMgreaterThanOrEqualTo(warnWidth);
        }];
        
        UITapGestureRecognizer * tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(tapTouch:)];
        [item addGestureRecognizer:tap];
        totalWidth += (width+manarge);
    }
    
    dispatch_async(dispatch_get_main_queue(), ^{
        [self.scrollView setContentSize:CGSizeMake(totalWidth+12.f, self.scrollView.frame.size.height)];
    });
}

- (void)removeScrollViewAllSubviews {
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
    
    [self.scrollView setContentSize:(CGSizeMake(0, 0))];
}

/// 计算文本的宽度
- (CGFloat )textContentWidth:(NSString *)text font:(UIFont *)font {
    CGSize size = [text sizeWithAttributes:@{NSFontAttributeName : font}];
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
        _scrollView.clipsToBounds = NO;
    }
    return _scrollView;
}

- (UIView *)topLine {
    if (_topLine == nil) {
        _topLine = [[UIView alloc] initWithFrame:CGRectZero];
        [_topLine setBackgroundColor:[TOSKitCustomInfo shareCustomInfo].ChatBox_lineColor];
    }
    return _topLine;
}

@end
