//  输入框页面
//
//  ICChatBoxViewController.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/10.
//  Copyright © 2016年 gxz All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ICRecordManager.h"
#import "TIMChatBoxViewControllerDelegate.h"
#import "ICChatBox.h"

@protocol TIMChatBoxViewControllerDataSource <NSObject>

- (NSArray <NSString *>*)TIMChatBoxViewControllerBarItemDataSoucre;

@end


@interface TIMChatBoxViewController : UIViewController

@property(nonatomic, weak) id<TIMChatBoxViewControllerDelegate>delegate;

@property (nonatomic, assign) CGRect keyboardFrame;

@property (nonatomic, strong) ICChatBox *chatBox;

@property (nonatomic, assign) int keyboardFrameHeight;

@property (nonatomic, assign) BOOL isCurChatGroup;          // 当前会话是否是群组

@property (nonatomic, copy) NSString * curGroupType; // 当前用户在组中的type

@property (nonatomic, strong) NSArray                * barItemArray;
@end
