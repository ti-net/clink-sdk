//
//  OSEventSatisfactionTagView.m
//  TOSClientKit
//
//  Created by 言 on 2022/8/22.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "OSEventSatisfactionTagView.h"
#import "TIMConstants.h"

@interface OSEventSatisfactionTagView ()

@end

@implementation OSEventSatisfactionTagView
- (instancetype)initWithCoder:(NSCoder *)coder {
    self = [super initWithCoder:coder];
    if (self) {
        self.content.layer.cornerRadius = 4.f;
        self.content.layer.masksToBounds = YES;
        self.content.layer.borderWidth = 1.f;
        self.content.layer.borderColor = TOSHexColor(0xD9D9D9).CGColor;
    }
    return self;
}

- (void)setupContent:(NSString *)content {
    [self.content setTitle:content forState:(UIControlStateNormal)];
}

- (void)didClickTagBtnAction:(BOOL)selected {
    if (selected) {
        self.content.layer.borderColor = TOSHexColor(0xFAAD14).CGColor;
        [self.content setBackgroundColor:TOSHexAColor(0xFAAD14, .06f)];
        [self.content setTitleColor:TOSHexColor(0xFAAD14) forState:(UIControlStateNormal)];
    } else {
        self.content.layer.borderColor = TOSHexColor(0xD9D9D9).CGColor;
        [self.content setBackgroundColor:[UIColor clearColor]];
        [self.content setTitleColor:TOSHexColor(0x8C8C8C) forState:(UIControlStateNormal)];
    }
}

@end
