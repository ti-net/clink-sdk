//
//  ICEmotionPageView.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/4/6.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICEmotionPageView.h"
#import "ICMessageConst.h"
#import "XZEmotion.h"
#import "ICEmotionButton.h"
#import "ICFaceManager.h"
#import "UIView+SDExtension.h"
#import "TIMConstants.h"
#import "kitUtils.h"

@interface ICEmotionPageView ()

@property (nonatomic, weak) UIButton *deleteBtn;

@end

@implementation ICEmotionPageView

- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        UIButton *deleteBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [deleteBtn setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"emotion_delete"]] forState:UIControlStateNormal];
        [deleteBtn addTarget:self action:@selector(deleteBtnClicked:) forControlEvents:UIControlEventTouchUpInside];
        [self addSubview:deleteBtn];
        self.deleteBtn       =  deleteBtn;
    }
    return self;
}


#pragma mark - Private

- (void)deleteBtnClicked:(UIButton *)deleteBtn
{
    [[NSNotificationCenter defaultCenter] postNotificationName:GXEmotionDidDeleteNotification object:nil];// 通知出去
}

- (void)setEmotions:(NSArray *)emotions
{
    _emotions                   = emotions;
    NSUInteger count            = emotions.count;
    for (int i = 0; i < count; i ++) {
        ICEmotionButton *button = [[ICEmotionButton alloc] init];
        [self addSubview:button];
        button.emotion          = emotions[i];
        [button addTarget:self action:@selector(emotionBtnClicked:) forControlEvents:UIControlEventTouchUpInside];
    }
    
}

- (void)layoutSubviews
{
    [super layoutSubviews];
    CGFloat inset            = 15;
    NSUInteger count         = self.emotions.count;
    // 取第一个对象中的type是否为空 判断是否是自定义表情
    BOOL bGitImage = NO;
    if (count > 0 ) {
        XZEmotion * emotionObj = self.emotions[0];
        if (![kitUtils isBlankString:emotionObj.type] && [emotionObj.type isEqualToString:kGIFTimageType]) {
            bGitImage = YES;
        }
    }
    
    if (bGitImage) {
        [self.deleteBtn removeFromSuperview];
        CGFloat btnW             = (self.tosSD_width - 2*inset)/ICGifEmotionMaxCols;
        CGFloat btnH             = (self.tosSD_height - 2*inset)/ICGifEmotionMaxRows;
        for (int i = 0; i < count; i ++) {
            ICEmotionButton *btn = self.subviews[i];
            btn.tosSD_width            = btnW;
            btn.tosSD_height           = btnH;
            btn.tosSD_x                = inset + (i % ICGifEmotionMaxCols)*btnW;
            btn.tosSD_y                = inset + (i / ICGifEmotionMaxCols)*btnH;
        }
//        self.deleteBtn.width     = btnW;
//        self.deleteBtn.height    = btnH;
//        self.deleteBtn.x         = inset + (count%ICGifEmotionMaxCols)*btnW;
//        self.deleteBtn.y         = inset + (count/ICGifEmotionMaxCols)*btnH;
    }else{
        CGFloat btnW             = (self.tosSD_width - 2*inset)/ICEmotionMaxCols;
        CGFloat btnH             = (self.tosSD_height - 2*inset)/ICEmotionMaxRows;
        for (int i = 0; i < count; i ++) {
            ICEmotionButton *btn = self.subviews[i + 1];//因为已经加了一个deleteBtn了
            btn.tosSD_width            = btnW;
            btn.tosSD_height           = btnH;
            btn.tosSD_x                = inset + (i % ICEmotionMaxCols)*btnW;
            btn.tosSD_y                = inset + (i / ICEmotionMaxCols)*btnH;
        }
        self.deleteBtn.tosSD_width     = btnW;
        self.deleteBtn.tosSD_height    = btnH;
        self.deleteBtn.tosSD_x         = inset + (count%ICEmotionMaxCols)*btnW;
        self.deleteBtn.tosSD_y         = inset + (count/ICEmotionMaxCols)*btnH;
    }
}


- (void)emotionBtnClicked:(ICEmotionButton *)button
{
    NSMutableDictionary *userInfo = [NSMutableDictionary dictionary];
    userInfo[GXSelectEmotionKey]  = button.emotion;
    [[NSNotificationCenter defaultCenter] postNotificationName:GXEmotionDidSelectNotification object:nil userInfo:userInfo];
}


@end
