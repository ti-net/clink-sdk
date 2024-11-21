//
//  ICEmotionButton.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/4/6.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICEmotionButton.h"
#import "NSString+Extension.h"
#import "TIMConstants.h"
#import "UIImage+TIMGIF.h"

@implementation ICEmotionButton

- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        [self setup];
    }
    return self;
}


- (void)setup
{
    self.titleLabel.font             = [UIFont systemFontOfSize:32.0];
    self.adjustsImageWhenHighlighted = NO;
}

- (void)setEmotion:(XZEmotion *)emotion
{
    _emotion               = emotion;
    if (emotion.code) {
        [self setTitle:self.emotion.code.emoji forState:UIControlStateNormal];
    } else if (emotion.face_name) {
        if ([emotion.type isEqualToString:kGIFTimageType]) {
            NSString *imagePath = [[NSBundle mainBundle] pathForResource:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,self.emotion.face_name] ofType:kGIFTimageType];
//            NSLog(@"setEmotion imagePath = %@",imagePath);
            NSData *imageData = [NSData dataWithContentsOfFile:imagePath];
            [self setImage:[UIImage sd_animatedGIFWithData:imageData] forState:UIControlStateNormal];
        }else{
            [self setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,self.emotion.face_name]] forState:UIControlStateNormal];
        }
    }
}



@end
