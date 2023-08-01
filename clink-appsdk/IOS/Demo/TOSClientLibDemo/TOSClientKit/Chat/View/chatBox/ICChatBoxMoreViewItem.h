//
//  ICChatBoxMoreViewItem.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/14.
//  Copyright © 2016年 gxz All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TOSKitExtendBoardItemModel.h"

@interface ICChatBoxMoreViewItem : UIView

@property (nonatomic, strong) NSString *title;
@property (nonatomic, strong) NSString *imageName;
@property (nonatomic, assign) NSInteger index;
@property (nonatomic, assign) TOSChatBoxExtendBoardType type;


- (void)addTarget:(id)target action:(SEL)action
                   forControlEvents:(UIControlEvents)controlEvents;

/**
 *  创建一个ICChatBoxMoreViewItem
 *
 *  @param title     item的标题
 *  @param imageName item的图片
 *  @param index     item的唯一标识
 *  @return item
 */
+ (ICChatBoxMoreViewItem *)createChatBoxMoreItemWithTitle:(NSString *)title
                                                imageName:(NSString *)imageName
                                                    index:(NSInteger)index
                                                     type:(TOSChatBoxExtendBoardType)type;

@end
