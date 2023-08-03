//
//  ICChatSystemCell.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/6/7.
//  Copyright © 2016年 gxz All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TIMMessageFrame.h"


@interface ICChatSystemCell : UITableViewCell

+ (instancetype)cellWithTableView:(UITableView *)tableView
                       reusableId:(NSString *)ID;

@property (nonatomic, strong) TIMMessageFrame *messageF;

@end
