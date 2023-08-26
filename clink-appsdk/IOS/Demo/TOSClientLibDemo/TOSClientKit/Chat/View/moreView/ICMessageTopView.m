//
//  ICMessageTopView.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/4/11.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICMessageTopView.h"
#import "UIView+SDExtension.h"
#import "TIMConstants.h"
#import "ICMessageHelper.h"

@interface ICMessageTopView ()

@property (nonatomic, weak) UILabel *nameLabel;
//@property (nonatomic, weak) UILabel *timeLabel;
@property (nonatomic, weak) UIView *lineView;

@property (nonatomic, assign) BOOL isSender;

@end

@implementation ICMessageTopView

- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
//        self.backgroundColor = [UIColor redColor];
    }
    return self;
}


- (void)layoutSubviews
{
    [super layoutSubviews];
    
    self.nameLabel.tosSD_x       = 3;
    self.nameLabel.tosSD_centerY = self.tosSD_height * 0.5;
//    _nameLabel.width       = 200;//58;
    [self.nameLabel sizeToFit];

//    [self.timeLabel sizeToFit];
//    self.timeLabel.centerY = self.height * 0.5;
    if (_isSender) {
//        _timeLabel.width = 70;
//        _timeLabel.width = 40;
//        self.timeLabel.x       = self.width - self.timeLabel.width - 3;
//        [_timeLabel sizeToFit];
    } else {
//        _nameLabel.preferredMaxLayoutWidth = 32;
        _nameLabel.numberOfLines = 1;
//        [_nameLabel sizeToFit];
//        self.timeLabel.x       = 40;//self.nameLabel.width + 6;
//        _timeLabel.width       = 40;
//        [_timeLabel sizeToFit];
    }
}

- (void)messageSendName:(NSString *)name
               isSender:(BOOL)isSender
                   date:(NSInteger)date
{
    _isSender = isSender;
    // 时间改成服务器的时间
    @WeakObj(self)
    dispatch_async(dispatch_get_main_queue(), ^{
        @StrongObj(self)
        //    NSString * strTime = [NSString stringWithFormat:@"%ld",(long)date];
        //    NSString *currentDate      = @"";//[ICMessageHelper timeFormatWithDate3:strTime];
            if (isSender) {
        //        self.timeLabel.text    = currentDate;
        //        self.timeLabel.textAlignment = NSTextAlignmentRight;
                self.nameLabel.hidden  = YES;
            } else {
                self.nameLabel.hidden  = NO;
        //        self.timeLabel.text    = currentDate;
        //        self.timeLabel.textAlignment = NSTextAlignmentLeft;
                self.nameLabel.text      = name?:@"";
                [self.nameLabel sizeToFit];
        //        [_timeLabel sizeToFit];
            }
    });
    

}


#pragma mark - Getter

- (UILabel *)nameLabel
{
    if (!_nameLabel) {
        UILabel *label  = [[UILabel alloc] init];
        label.textAlignment = NSTextAlignmentLeft;
        label.font      = [UIFont systemFontOfSize:12.0];
        label.textColor = TOSHexAColor(0x929292,1.0);
        [self addSubview:label];
        _nameLabel      = label;
    }
    return _nameLabel;
}

//- (UILabel *)timeLabel
//{
//    if (!_timeLabel) {
//        UILabel *label = [[UILabel alloc] init];
//        label.textAlignment = NSTextAlignmentRight;
//        label.font     = [UIFont systemFontOfSize:12.0];
//        label.textColor = TOSHexAColor(0x929292,1.0);
//        [self addSubview:label];
//        _timeLabel     = label;
//    }
//    return _timeLabel;
//}

- (UIView *)lineView
{
    if (!_lineView) {
        UIView *line = [[UIView alloc] init];
        line.backgroundColor = TOSHexAColor(0xa8bd61,1.0);
//        [self addSubview:line];
        _lineView    = line;
    }
    return _lineView;
}

@end
