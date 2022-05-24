//
//  kefuVC.m
//  TIMClientKitDemo
//
//  Created by apple on 2021/8/20.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "kefuVC.h"
#import "ChatInfoViewController.h"


@interface kefuVC ()<UIAlertViewDelegate>

@end

@implementation kefuVC

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
    //
    
    [self setupView];
}

-(void)setupView{
    
    UIView *titleView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, kWindowWidth, 44.f)];
    UILabel *title = [[UILabel alloc]init];
    title.font = [UIFont fontWithName:kFontNameMedium size:20.f];
    title.text = @"客服";
    [title sizeToFit];
    title.center = titleView.center;
    title.x = 10.f;
    self.navigationItem.titleView = titleView;
    
    
    UIButton*kefuBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    kefuBtn.backgroundColor = [UIColor yellowColor];
    kefuBtn.frame = CGRectMake(100, 100, 100, 100);
    [kefuBtn setTitle:@"客服" forState:UIControlStateNormal];
    [kefuBtn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [kefuBtn addTarget:self action:@selector(kefuAction) forControlEvents:UIControlEventTouchDown];
    [self.view addSubview:kefuBtn];
    
    
//    NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
//    NSString *app_Name = [infoDictionary objectForKey:@"CFBundleDisplayName"];
//    NSString *str = [NSString stringWithFormat:@"无法录制声音 请在iPhone的“设置>%@”中打开麦克风权限",app_Name];
//    UIAlertView *alert = [[UIAlertView alloc]initWithTitle:str message:nil delegate:self cancelButtonTitle:@"取消" otherButtonTitles:@"前往设置", nil];
//    [alert show];
}

//- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex {
//    if (buttonIndex == 1) { // 去设置界面，开启相机访问权限
//        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:UIApplicationOpenSettingsURLString]];
//    }
//}



-(void)kefuAction{
    /*访客初始化回调
     当APP端IM mqtt 连接就绪，主动通知服务端，APP端调用该接口后服务端
     就开始会话流程逻辑创建会话*/
    
    [[OnlineRequestManager sharedCustomerManager]visitorReadyWithDict:@{}
                                                              success:^(NSString * _Nonnull mainUniqueId) {
       
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
    }];

    
    //创建会话成功，进入聊天页面
    ChatInfoViewController *chatVC = [[ChatInfoViewController alloc] init];
    chatVC.titleName = @"客服";
    chatVC.appName = @"客服SDK";
    self.hidesBottomBarWhenPushed  = YES;
    [self.navigationController pushViewController:chatVC animated:YES];
    
}



@end
