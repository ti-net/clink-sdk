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
#import "NSDictionary+TIMTool.h"
#import "kitUtils.h"
#import <TOSClientKit/TOSKitCustomInfo.h>

@interface ICChatBarItemView ()

@end


@implementation ICChatBarItemView

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        
        self.backgroundColor = [TOSKitCustomInfo shareCustomInfo].quickEntryItem_backgroundColor;
        [self addSubview:self.contentLabel];
        @WeakObj(self);
        [self.contentLabel mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
            @StrongObj(self);
            make.edges.equalTo(self);
        }];
    }
    return self;
}


- (void)setModel:(TOSQuickEntryModel *)model {
    _model = model;
    
    NSInteger staffCommentTotalCount = ((NSString *)model.dynamicConfigParameters[TOS_STAFF_COMMENT_TOTAL_COUNT]).integerValue;
    if (staffCommentTotalCount <= 0) {
        self.warnLabel.hidden = YES;
    } else {
        self.warnLabel.hidden = NO;
    }
    if (staffCommentTotalCount >= 100) {
        self.warnLabel.text = @"99+";
    } else {
        self.warnLabel.text = [NSString stringWithFormat:@"%ld",staffCommentTotalCount];
    }
    
    self.contentLabel.text = model.name;
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
        _contentLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        _contentLabel.textColor = TOSHexColor(0x595959);
        
        _contentLabel.backgroundColor = [TOSKitCustomInfo shareCustomInfo].quickEntryItem_backgroundColor;
    }
    return _contentLabel;
}

- (UILabel *)warnLabel {
    if (!_warnLabel) {
        _warnLabel = [[UILabel alloc] initWithFrame:CGRectZero];
        _warnLabel.backgroundColor = TOSHexColor(0xFF4D4F);
        _warnLabel.textColor = TOSHexColor(0xFFFFFF);
        _warnLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:12.f];
        _warnLabel.hidden = YES;
        _warnLabel.layer.masksToBounds = YES;
        _warnLabel.layer.cornerRadius = 8.f;
        _warnLabel.numberOfLines = 1;
        _warnLabel.textAlignment = NSTextAlignmentCenter;
    }
    return _warnLabel;
}

@end
