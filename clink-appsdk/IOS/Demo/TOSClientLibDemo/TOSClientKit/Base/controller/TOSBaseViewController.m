//
//  TOSBaseViewController.m
//  TIMClientKit
//
//  Created by YanBo on 2020/5/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "TOSBaseViewController.h"

@interface TOSBaseViewController ()

@end

@implementation TOSBaseViewController

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
    //              👆
    
    self.navigationController.view.backgroundColor = [UIColor whiteColor];
    self.navigationController.navigationBar.tintColor = [UIColor blackColor];
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName:[UIColor blackColor]}];
    [self setupSubviews];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [self.navigationController setNavigationBarHidden:NO animated:animated];
    self.navigationController.navigationBarHidden = NO;
    self.navigationController.navigationBar.hidden = NO;
}

- (void)setupSubviews {
    
}

@end
