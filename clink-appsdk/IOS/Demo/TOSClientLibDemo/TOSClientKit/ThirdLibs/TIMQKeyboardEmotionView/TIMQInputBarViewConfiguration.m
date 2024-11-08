//
//  TIMQInputBarViewConfiguration.m
//  QKeyBoardDemo
//
//  Created by 侯力 on 2024/03/15.
//  Copyright (c) 2021年 侯力. All rights reserved.
//

#import "TIMQInputBarViewConfiguration.h"
#import "NSBundle+MYHTZImagePicker.h"

@implementation TIMQInputBarViewConfiguration

+ (instancetype)defaultInputBarViewConfiguration {
    TIMQInputBarViewConfiguration *configuration = [TIMQInputBarViewConfiguration new];

    if (@available(iOS 11.0, *)) {
        NSBundle *bundle = [NSBundle tos_tz_imagePickerBundle];//[NSBundle bundleForClass:[QInputBarViewConfiguration class]];
        configuration.inputBarBackgroundColor = [UIColor colorNamed:@"q_input_gray_bg" inBundle:bundle compatibleWithTraitCollection:nil];
        configuration.inputBarBoardColor = [UIColor colorNamed:@"q_border223" inBundle:bundle compatibleWithTraitCollection:nil];
        configuration.textColor = [UIColor colorNamed:@"q_black_gray" inBundle:bundle compatibleWithTraitCollection:nil];
        configuration.textViewBackgroundColor = [UIColor colorNamed:@"q_input" inBundle:bundle compatibleWithTraitCollection:nil];
        configuration.recordButtonTitleColor = [UIColor colorNamed:@"q_black_white" inBundle:bundle compatibleWithTraitCollection:nil];
    } else {
        configuration.inputBarBackgroundColor = [UIColor colorWithRed:242/255.0f green:242/255.0f blue:242/255.0f alpha:1];
        configuration.inputBarBoardColor = [UIColor colorWithRed:223/255.0f green:223/255.0f blue:223/255.0f alpha:1];
        configuration.textColor = [UIColor blackColor];
        configuration.textViewBackgroundColor = [UIColor whiteColor];
        configuration.recordButtonTitleColor = [UIColor darkGrayColor];
    }
    configuration.inputBarHeightChangeAnimationDuration = 0.2;
    configuration.keyboardSendEnabled = YES;
    
    return configuration;
}

@end
