//
//  TOSInvestigationStarView.m
//  TOSClientKit
//
//  Created by 李成 on 2023/8/9.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import "TOSInvestigationStarView.h"
#import "UIColor+TIMYYAdd.h"
#import "TIMConstants.h"
#import "ICTools.h"

@implementation TOSInvestigationStarView

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        
        
        
    }
    return self;
}

#pragma mark - setter

- (void)setStarArray:(NSArray<TOSSatisfactionStarModel *> *)starArray {
    _starArray = starArray;
    
    [self addItemView];
    
}

- (void)addItemView {
    
    CGFloat itemSpacing = 0.0f;
    CGFloat itemWidth = (self.width - itemSpacing*(self.starArray.count - 1))/self.starArray.count;
    CGFloat x = 0;
    
    for (int i = 0; i < self.starArray.count; i++) {
        TOSSatisfactionStarModel * item = self.starArray[i];
        UIButton * itemView = [[UIButton alloc] initWithFrame:(CGRectMake(x, 0, itemWidth, 110))];
        
        [itemView setTitle:item.desc forState:(UIControlStateNormal)];
        [itemView setTitleColor:[UIColor colorWithHexString:@"#595959"] forState:(UIControlStateNormal)];
        [itemView setTitleColor:[UIColor colorWithHexString:@"#FAAD14"] forState:(UIControlStateSelected)];
        [itemView setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/TOSSatisfaction_expression_%ld",FRAMEWORKS_BUNDLE_PATH,(long)i+1]] forState:(UIControlStateSelected)];
        [itemView setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/TOSSatisfaction_expression_gray_%ld",FRAMEWORKS_BUNDLE_PATH,(long)i+1]] forState:(UIControlStateNormal)];
        itemView.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:12];
        itemView.tag = i;
        [itemView addTarget:self action:@selector(itemTouch:) forControlEvents:(UIControlEventTouchUpInside)];
        [ICTools layoutButton:itemView WithEdgeInsetsStyle:(TeilButtonEdgeInsetsStyleTop) imageTitleSpace:8];
        [self addSubview:itemView];
        x += itemWidth+itemSpacing;
        if (i == self.starArray.count-1) {
            itemView.selected = YES;
        }
    }
    
    
    
}


- (void)itemTouch:(UIButton *)sender {
    for (UIView * item in self.subviews) {
        if ([item isKindOfClass:[UIButton class]]) {
            UIButton * objBtn = (UIButton *)item;
            objBtn.selected = NO;
        }
    }
    sender.selected = YES;
    if (self.delegate && [self.delegate respondsToSelector:@selector(TOSInvestigationStarViewDidSelectedIndex:)]) {
        [self.delegate TOSInvestigationStarViewDidSelectedIndex:sender.tag];
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
