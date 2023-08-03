//
//  ICChatBarItemView.m
//  TIMClientKit
//
//  Created by 李成 on 2022/5/20.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "ICChatBarItemView.h"
#import "TIMMasonry.h"
#import "TIMConstants.h"
#import "kitUtils.h"
#import <TOSClientKit/TOSKitCustomInfo.h>

@interface ICChatBarItemView ()

/// 显示文案内容
@property (nonatomic, strong) UILabel                * contentLabel;

@end


@implementation ICChatBarItemView

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        
        self.backgroundColor = [TOSKitCustomInfo shareCustomInfo].quickEntryItem_backgroundColor;
        
        [self addSubview:self.contentLabel];
        
        [self.contentLabel mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
            make.edges.equalTo(self);
        }];
    }
    return self;
}


- (void)setModel:(ICChatBarItemModel *)model {
    _model = model;
    
    self.contentLabel.text = model.titleStr;
    
}


#pragma mark - 懒加载

- (UILabel *)contentLabel {
    if (!_contentLabel) {
        _contentLabel = [[UILabel alloc] initWithFrame:(CGRectZero)];
        _contentLabel.textAlignment = NSTextAlignmentCenter;
//        _contentLabel.layer.borderColor = [UIColor colorWithRed:241/255.0f green:241/255.0f blue:247/255.0f alpha:1.0].CGColor;
//        _contentLabel.layer.borderWidth = 1.0f;
//        _contentLabel.layer.cornerRadius = 14.0f;
//        _contentLabel.layer.masksToBounds = YES;
        _contentLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:12.f];
        _contentLabel.textColor = TOSHexColor(0x595959);
        
        _contentLabel.backgroundColor = [TOSKitCustomInfo shareCustomInfo].quickEntryItem_backgroundColor;
    }
    return _contentLabel;
}

@end
