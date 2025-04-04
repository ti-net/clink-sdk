//
//  TOSInvestigationStarTagView.m
//  TOSClientKit
//
//  Created by 李成 on 2023/8/9.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import "TOSInvestigationStarTagView.h"
#import "UIColor+TIMYYAdd.h"

@interface TOSInvestigationStarTagView ()



@end


@implementation TOSInvestigationStarTagView

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        
        
        
    }
    return self;
}


#pragma mark - setter

- (void)setTagArray:(NSArray *)tagArray {
    _tagArray = tagArray;
    
    [self addItemView];
}



- (void)addItemView {
    
    for (UIView * item in self.subviews) {
        if ([item isKindOfClass:[UIButton class]]) {
            [item removeFromSuperview];
        }
    }
    
    CGFloat x = 0.0f;
    CGFloat y = 0.0f;
    CGFloat spacing = 12.0f;
    CGFloat itemWidth = (self.width-spacing)/2;
    
    for (int idx = 0; idx < self.tagArray.count; idx++) {
        NSString * obj = self.tagArray[idx];
        UIButton * itemView = [[UIButton alloc] initWithFrame:(CGRectMake(x, y, itemWidth, 30))];
        [itemView setTitle:obj forState:(UIControlStateNormal)];
        [itemView setTitleColor:[UIColor colorWithHexString:@"#262626"] forState:(UIControlStateNormal)];
        [itemView setTitleColor:[UIColor colorWithHexString:@"#4385FF"] forState:(UIControlStateSelected)];
        itemView.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:12];
        itemView.layer.cornerRadius = 6.0f;
        itemView.layer.borderWidth = 1.0f;
        itemView.layer.borderColor = [[UIColor blackColor] colorWithAlphaComponent:0.15].CGColor;
        itemView.layer.masksToBounds = YES;
        itemView.tag = idx;
        [itemView addTarget:self action:@selector(itemTouch:) forControlEvents:(UIControlEventTouchUpInside)];
        [self addSubview:itemView];
        x += itemWidth+spacing;
        if (idx % 2 != 0 && idx != 0) {
            x = 0;
            y += spacing + 30;
        }
    }
    
    
}


- (void)itemTouch:(UIButton *)sender {
    
    sender.selected = !sender.isSelected;
    
    if (sender.isSelected) {
        NSLog(@"选中");
        sender.layer.borderColor = [UIColor colorWithHexString:@"#4385FF"].CGColor;
        if (self.delegate && [self.delegate respondsToSelector:@selector(TOSInvestigationStarTagViewSelected:)]) {
            [self.delegate TOSInvestigationStarTagViewSelected:sender.titleLabel.text];
        }
    }
    else {
        NSLog(@"未选中");
        sender.layer.borderColor = [[UIColor blackColor] colorWithAlphaComponent:0.15].CGColor;
        if (self.delegate && [self.delegate respondsToSelector:@selector(TOSInvestigationStarTagViewUnSelected:)]) {
            [self.delegate TOSInvestigationStarTagViewUnSelected:sender.titleLabel.text];
        }
    }
    
}


/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
