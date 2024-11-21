//
//  OSRobotCombinationTextSubview.m
//  TOSClientKit
//
//  Created by 言 on 2022/8/9.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "OSRobotCombinationTextSubview.h"
#import "TIMConstants.h"
#import <TOSClientLib/CombinationMessage.h>

#import <TOSClientLib/RichTextMessage.h>
#import "ICMessageConst.h"

#import "TIMMessageModel.h"
#import "ICFaceManager.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
#import "NSDictionary+TIMTool.h"
#import "YYKit.h"
#import "XZEmotion.h"
#import "TOSCustomerChatVC.h"
#import "STBaseWebViewController.h"
#import <TOSClientLib/TOSClientLib.h>
#import <TOSClientKit/TOSClientKit.h>

#import "YYKit.h"

#import "ICChatMessageRichTextCell.h"
#import "ICChatMessageRichImageCell.h"

@interface OSRobotCombinationTextSubview ()

@property (nonatomic, strong) RichTextMessage *model;

@property (nonatomic, strong) CombinationMessage *combinationModel;

@property (nonatomic, strong) UIView *subContentView;

@property (nonatomic, strong) NSMutableArray <RichTextMessage *>*elements;

@end

@implementation OSRobotCombinationTextSubview

- (void)setupSubviews {
    [super setupSubviews];
    
    self.backgroundColor = [UIColor clearColor];
    [self addSubview:self.subContentView];
}

- (void)defineLayout {
    [super defineLayout];
    
}

- (UIView *)subContentView {
    if (!_subContentView) {
        _subContentView = [[UIView alloc] initWithFrame:CGRectZero];
        _subContentView.backgroundColor = [UIColor clearColor];
    }
    return _subContentView;
}

#pragma mark - Private Method
- (void)setWithCombinationModel:(CombinationMessage *)itemmodel {
    
    NSMutableArray <RichTextMessage *>*richModels = itemmodel.richTextMessage.elements;
    
    self.elements = [NSMutableArray arrayWithArray:richModels];
    
    self.subContentView.frame = itemmodel.contentF;
    [self.subContentView removeAllSubviews];
    
    __block CGFloat y = 0;
    CGFloat width = itemmodel.contentF.size.width;
    CGFloat x = 0;
    
    [self.elements enumerateObjectsUsingBlock:^(RichTextMessage * _Nonnull model, NSUInteger idx, BOOL * _Nonnull stop) {
        
        CGFloat height = model.contentF.size.height;
        
        if ([model.type isEqualToString:@"text"] ||
            [model.type isEqualToString:@"a"] ||
            [model.type isEqualToString:@"tr"] ||
            [model.type isEqualToString:@"knowledge"] ||
            [model.type isEqualToString:@"p"] ||
            [model.type isEqualToString:@"div"] ||
            [model.type isEqualToString:@"ul"] ||
            [model.type isEqualToString:@"ol"] ||
            [model.type isEqualToString:@"span"] ||
            [model.type isEqualToString:@"strong"] ||
            [model.type isEqualToString:@"em"] ||
            [model.type isEqualToString:@"sup"] ||
            [model.type isEqualToString:@"sub"] ||
            [model.type isEqualToString:@"h1"] ||
            [model.type isEqualToString:@"h2"] ||
            [model.type isEqualToString:@"h3"] ||
            [model.type isEqualToString:@"h4"] ||
            [model.type isEqualToString:@"h5"] ||
            [model.type isEqualToString:@"h6"] ||
            [model.type isEqualToString:@"code"]) {
            
            ICChatMessageRichTextCell *textSubview = [[ICChatMessageRichTextCell alloc] initWithFrame:CGRectMake(x, y, width, height)];
            [textSubview setWithModel:model];
            [self.subContentView addSubview:textSubview];
        } else if ([model.type isEqualToString:@"img"] ||
                   [model.type isEqualToString:@"video"]) {
            
            ICChatMessageRichImageCell *imageSubview = [[ICChatMessageRichImageCell alloc] initWithFrame:CGRectMake(x, y, width, height)];
            imageSubview.robotProvider = itemmodel.robotProvider;
            [imageSubview setWithModel:model];
            
            [self.subContentView addSubview:imageSubview];
        } else {
            
            ICChatMessageRichTextCell *textSubview = [[ICChatMessageRichTextCell alloc] initWithFrame:CGRectMake(x, y, width, height)];
            [self.subContentView addSubview:textSubview];
        }
        y += height;
    }];
}

@end
