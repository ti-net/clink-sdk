//
//  ICChatMessageVideoCell.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/13.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICChatMessageBaseCell.h"

@interface ICChatMessageVideoCell : ICChatMessageBaseCell

@property (nonatomic, strong) UIButton *auditStatusBtn;   //审核状态显示

- (void)stopVideo;

@end
