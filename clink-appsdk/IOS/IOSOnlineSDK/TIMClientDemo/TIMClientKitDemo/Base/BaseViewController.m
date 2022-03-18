//
//  BaseViewController.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/4.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "BaseViewController.h"

#import "UIImage+PureColorImage.h"

@interface BaseViewController ()

@end

@implementation BaseViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    if (@available(iOS 13.0, *)) {
        self.overrideUserInterfaceStyle = UIUserInterfaceStyleLight;    //关闭暗黑模式
    }
    self.modalPresentationStyle = UIModalPresentationFullScreen;
    
    //              👇
    /* 1. 在baseVC设置如下代码，更改导航栏属性
     * 2. 当前页面隐藏 或者 显示导航栏 都是在viewWillAppear方法里面
     *    调用 [self.navigationController setNavigationBarHidden: YesOrNo animated: animated];
     * 3. 不要在viewWillDisappear方法里面更改导航栏隐藏属性
     */
    self.navigationController.navigationBar.translucent = NO;
    self.automaticallyAdjustsScrollViewInsets = NO;
    self.view.autoresizesSubviews = NO;
    self.view.backgroundColor = kHexColor(0xFAFBFD);
    //              👆
    
    [self changeNavigationBarBackColor:Navi_BackColor_Default];
    
    [self setupSubviews];
    self.isCreate = YES;
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:NO animated:animated];
}

- (void)viewDidDisappear:(BOOL)animated {
    [super viewDidDisappear:animated];
    self.isCreate = NO;
}

#pragma mark - 导航栏
- (void)changeNavigationBarBackColor:(int)type {//更换导航栏背景
    if (self.navigationController) {
        UIImage *title_bg=nil;
        if (type == Navi_BackColor_Default) {
            title_bg = [UIImage imageWithColor:[UIColor whiteColor]];
            [self.navigationController.navigationBar setBackgroundImage:title_bg forBarMetrics:UIBarMetricsDefault];
            self.navigationController.navigationBar.tintColor = [UIColor blackColor];
        } else if (type == Navi_BackColor_Clear){
            title_bg =  [UIImage imageWithColor:[UIColor clearColor]];
            [self.navigationController.navigationBar setBackgroundImage:title_bg forBarMetrics:UIBarMetricsDefault];
            self.navigationController.navigationBar.tintColor = [UIColor clearColor];
        }
        self.navigationController.navigationBar.shadowImage = [UIImage imageWithColor:kHexColor(0xF3F6F7)];//取消导航边框黑线
    }
}

- (void)dealloc {
    NSLog(@">>dealloc BaseViewController===>%@",self);
}

- (void)setupSubviews {
    
}

@end
