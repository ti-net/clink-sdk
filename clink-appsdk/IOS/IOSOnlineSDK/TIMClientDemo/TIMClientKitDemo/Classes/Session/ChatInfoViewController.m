//
//  ChatInfoViewController.m
//  TIMClientKitDemo
//
//  Created by 赵言 on 2020/10/13.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "ChatInfoViewController.h"
#import "LoginModel.h"
#import <IQKeyboardManager/IQKeyboardManager.h>

@interface ChatInfoViewController () <TIMCustomMessageWillSendDelegate>

@end

@implementation ChatInfoViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    

}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    //TODO: 页面appear 禁用
    [[IQKeyboardManager sharedManager] setEnable:NO];
    

}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    //TODO: 页面Disappear 启用
    [[IQKeyboardManager sharedManager] setEnable:YES];
}




@end
