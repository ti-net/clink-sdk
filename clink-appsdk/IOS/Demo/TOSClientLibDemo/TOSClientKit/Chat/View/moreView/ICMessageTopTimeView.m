//
//  ICMessageTopTimeView.m
//  TIMClientKit
//
//  Created by YanBo on 2020/7/24.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "ICMessageTopTimeView.h"
#import "UIView+SDExtension.h"
#import "TIMConstants.h"
#import "NSDate+TIMKit.h"
#import "TOSKitCustomInfo.h"

@interface ICMessageTopTimeView ()

@property (nonatomic, strong) UILabel *showTimeLabel;

@end

@implementation ICMessageTopTimeView

- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        self.showTimeLabel.frame = frame;
//        self.backgroundColor = [UIColor redColor];
    }
    return self;
}

- (void)layoutSubviews
{
    [super layoutSubviews];
    [self.showTimeLabel sizeToFit];
    self.showTimeLabel.center = CGPointMake(CGRectGetMidX(self.bounds), CGRectGetMidY(self.bounds));
}

- (void)messageShowTimeLine:(NSDate *)date show:(BOOL)bShow{
    if (bShow) {
        [self.showTimeLabel setTextColor:[TOSKitCustomInfo shareCustomInfo].Chat_time_textColor];
        [self.showTimeLabel setText:[NSString stringWithFormat:@"%@", date.chatTimeInfo]];
    }else{
        [self.showTimeLabel setText:@""];
    }
    [self.showTimeLabel sizeToFit];
}

- (UILabel *)showTimeLabel
{
    if (!_showTimeLabel) {
        _showTimeLabel    = [[UILabel alloc] init];
        _showTimeLabel.font      = [UIFont systemFontOfSize:12.0];
        _showTimeLabel.textColor = TOSHexAColor(0x929292,1.0);
        [self addSubview:_showTimeLabel];
    }
    return _showTimeLabel;
}

@end
