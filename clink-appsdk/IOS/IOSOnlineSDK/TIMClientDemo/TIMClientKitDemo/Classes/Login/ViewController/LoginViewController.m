//
//  LoginViewController.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/10.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "LoginViewController.h"
#import "AppDelegate.h"
#import "LoginViewModel.h"
#import "MainTabBarController.h"
#import "GetTIMAccessTokenViewModel.h"
#import "LoginModel.h"

#import "LoginInfoSave.h"
#import <YBPopupMenu/YBPopupMenu.h>


@interface LoginViewController () <YBPopupMenuDelegate, UITextFieldDelegate,TIMAuditMessageSuccessDelegate,
TIMCustomMessageClickDelegate,
TIMRTCMediaMessageDelegate>

@property (weak, nonatomic) IBOutlet UILabel *helloLabel;
@property (strong, nonatomic) IBOutlet UITapGestureRecognizer *logoTapGesture;
@property (weak, nonatomic) IBOutlet UITextField *appNumbersTextField;
@property (weak, nonatomic) IBOutlet UITextField *loginNameNumbersTextField;
@property (weak, nonatomic) IBOutlet UITextField *loginPwdTextField;
@property (weak, nonatomic) IBOutlet UILabel *platformNameLabel;


@property (weak, nonatomic) IBOutlet UIView *appNumbersLine;
@property (weak, nonatomic) IBOutlet UIView *loginNameLine;
@property (weak, nonatomic) IBOutlet UIView *loginPwdLine;


@property (strong, nonatomic) LoginViewModel *viewModel;

@property (weak, nonatomic) IBOutlet UIButton *loginBtn;

@property (strong, nonatomic) GetTIMAccessTokenViewModel *getTIMAccessTokenVM;


@end

@implementation LoginViewController

- (void)setTextFieldClearBtnImage:(UITextField *)sender {
    UIButton *clearButton = [sender valueForKey:@"_clearButton"];
    [clearButton setImage:[UIImage imageNamed:@"login_testField_delete"] forState:UIControlStateNormal];
}

- (void)setupSubviews {
    [super setupSubviews];
    @weakify(self);
    
    self.appNumbersTextField.delegate = self;
    self.loginNameNumbersTextField.delegate = self;
    self.loginPwdTextField.delegate = self;
    
    [self setTextFieldClearBtnImage:self.appNumbersTextField];
    [self setTextFieldClearBtnImage:self.loginNameNumbersTextField];
    [self setTextFieldClearBtnImage:self.loginPwdTextField];
    
    NSArray *testFieldSignalArr = @[
        self.appNumbersTextField.rac_textSignal,
        self.loginNameNumbersTextField.rac_textSignal,
        self.loginPwdTextField.rac_textSignal];
    
    RACSignal *single = [RACSignal combineLatest:testFieldSignalArr];
    
    DomainNameSave *domainSave = [DomainNameSave shareDomainNameSave];
    LoginInfoSave *loginInfoSave = [LoginInfoSave shareLoginInfoSave];
    [loginInfoSave getData:domainSave.loginDomainName];
    
    DomainNameSave *domainName = [DomainNameSave shareDomainNameSave];
    self.appNumbersTextField.text = [loginInfoSave.dicAppId by_ObjectForKey:domainName.loginDomainName];
    self.loginNameNumbersTextField.text = [loginInfoSave.dicUserLoginName by_ObjectForKey:domainName.loginDomainName];
    self.loginPwdTextField.text = [loginInfoSave.dicUserPassword by_ObjectForKey:domainName.loginDomainName];
    
    self.platformNameLabel.text = loginInfoSave.platformShowName;
    
    self.loginBtn.enabled = YES;
//    [single subscribeNext:^(id  _Nullable x) {
//        @strongify(self);
//        NSString *enterpriseNum = self.appNumbersTextField.text;
//        NSString *seatingWorkNum = self.loginNameNumbersTextField.text;
//        NSString *loginPwd = self.loginPwdTextField.text;
//
//        if (enterpriseNum.length > 0 &&
//            seatingWorkNum.length > 0 &&
//            loginPwd.length > 0) {
//            self.loginBtn.enabled = YES;
//            self.loginBtn.backgroundColor = kHexColor(0x2373D9);
//        } else {
//            self.loginBtn.enabled = NO;
//            self.loginBtn.backgroundColor = kHexAColor(0x2373D9, .3f);
//        }
//    }];
    
    
    self.logoTapGesture.numberOfTapsRequired = 5;
    

    
    
}

//登录按钮
- (IBAction)didClickLoginBtnAction:(UIButton *)sender {
    
    NSString*name = @"先生";//@"哈哈哈哈哈";
    NSString*headerUrl = @"https://img2.baidu.com/it/u=468449452,4173225115&fm=26&fmt=auto";
    NSString*visitorId = @"ma";//[[[NSUUID UUID] UUIDString] stringByReplacingOccurrencesOfString:@"-" withString:@""];
    
    if (self.loginNameNumbersTextField.text.length>0) {
        name = self.loginNameNumbersTextField.text;
    }
    
    if (self.appNumbersTextField.text.length>0) {
        visitorId = self.appNumbersTextField.text;
    }
    

    NSLog(@"链接成功");

    MainTabBarController *tabBarC = [[MainTabBarController alloc] init];
    tabBarC.selectedIndex = 0;
    if (@available(iOS 13.0, *)) {
        tabBarC.modalPresentationStyle = UIModalPresentationFullScreen;
        [self presentViewController:tabBarC animated:NO completion:^{

        }];
    }else{
        [AppDelegate getShareAppDelegate].window.rootViewController = tabBarC;
    }
    
    
 //连接客服
    [[OnlineRequestManager sharedCustomerManager] getUserInfoWithUserId:visitorId
                                                               nickname:name
                                                               phoneNum:@"14107240003"
                                                              headerUrl:headerUrl
                                                         connectSuccess:^{


    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {

    } tokenIncorrect:^{

    }];
    
//    本地kit一些配置
    [TIMKit sharedTIMKit].disableMessageNotificaiton = YES;  // 取消本地推送
    [TIMKit sharedTIMKit].disableMessageAlertSound = YES;  // 取消本地推送声音
    [TIMKit sharedTIMKit].auditMessageSuccessDelagate = self;
    [TIMKit sharedTIMKit].customMessageClickDelagate = self;
    [TIMKit sharedTIMKit].rtcMediaMessageDelagate = self;
//    [TIMKit sharedTIMKit].customerKitUIType = TIMKitUIRDSA20Type;
    
    
    
    
//    NSString*name = @"哈哈哈哈哈";
//    NSString*headerUrl = @"https://img2.baidu.com/it/u=468449452,4173225115&fm=26&fmt=auto";
//    NSString*visitorId = @"la";//[[[NSUUID UUID] UUIDString] stringByReplacingOccurrencesOfString:@"-" withString:@""];
//
//    if (self.loginNameNumbersTextField.text.length>0) {
//        name = self.loginNameNumbersTextField.text;
//    }
//
//    if (self.appNumbersTextField.text.length>0) {
//        visitorId = self.appNumbersTextField.text;
//    }
//
////    if (self.loginPwdTextField.text.length>0) {
////        headerUrl = self.loginPwdTextField.text;
////    }
//
//
// //连接客服
//
////       [[OnlineRequestManager sharedCustomerManager] getUserInfoWithUserId:visitorId
////                                                                  nickname:name
////                                                                  phoneNum:@"14107240003"
////                                                                 headerUrl:headerUrl
////                                                            connectSuccess:^{
////
////
////       } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
////
////       } tokenIncorrect:^{
////
////       }];
//
//
//    [[OnlineRequestManager sharedCustomerManager] getUserInfoWithUserId:visitorId
//                                                               nickname:name
//                                                               phoneNum:@"13688889999"
//                                                              headerUrl:headerUrl
//                                                         connectSuccess:^{
//        [TRLoadingView hideLoadingForView:self.view animated:YES];
//        NSLog(@"链接成功");
//    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
//        [TRLoadingView hideLoadingForView:self.view animated:YES];
//        [self showMBErrorView:@"网络请求错误，请检查网络"];
//    } tokenIncorrect:^{
//        [TRLoadingView hideLoadingForView:self.view animated:YES];
//        [self showMBErrorView:@"accessToken不正确"];
//    }];
//
//    //本地kit一些配置
//    [TIMKit sharedTIMKit].disableMessageNotificaiton = YES;  // 取消本地推送
//    [TIMKit sharedTIMKit].disableMessageAlertSound = YES;  // 取消本地推送声音
//    [TIMKit sharedTIMKit].auditMessageSuccessDelagate = self;
//    [TIMKit sharedTIMKit].customMessageClickDelagate = self;
//    [TIMKit sharedTIMKit].rtcMediaMessageDelagate = self;
//
//
//
////进入
//    MainTabBarController *tabBarC = [[MainTabBarController alloc] init];
//    tabBarC.selectedIndex = 0;
//    if (@available(iOS 13.0, *)) {
//        tabBarC.modalPresentationStyle = UIModalPresentationFullScreen;
//        [self presentViewController:tabBarC animated:NO completion:^{
//
//        }];
//    }else{
//        [AppDelegate getShareAppDelegate].window.rootViewController = tabBarC;
//    }
}

//显示隐藏密码
- (IBAction)didClickShowOrHiddenPwdAction:(UIButton *)sender {
    sender.selected = !sender.selected;
    self.loginPwdTextField.secureTextEntry = !sender.isSelected;
}

- (BOOL)checkInputItem {
    // 校验输入的数据存在性
    if(!self.appNumbersTextField.text || self.appNumbersTextField.text.length <= 0) {
        [self showMBErrorView:@"应用编号格式不正确"];
        return NO;
    } else if (!self.loginNameNumbersTextField.text || self.loginNameNumbersTextField.text.length <= 0) {
        [self showMBErrorView:@"用户名格式不正确"];
        return NO;
    } else if (!self.loginPwdTextField.text || self.loginPwdTextField.text.length <= 0) {
        [self showMBErrorView:@"密码输入不正确"];
        return NO;
    }
    return YES;
}




#pragma mark - UITextFieldDelegate
- (void)textFieldDidBeginEditing:(UITextField *)textField {
    [self textFieldLineColor:textField color:kHexColor(0x2373D9)];
}

- (void)textFieldDidEndEditing:(UITextField *)textField {
    [self textFieldLineColor:textField color:kHexColor(0xF1F5F7)];
}

- (void)textFieldLineColor:(UITextField *)textField color:(UIColor *)color {
    [UIView animateWithDuration:.3f animations:^{
        if (textField == self.appNumbersTextField) {
            self.appNumbersLine.backgroundColor = color;
        } else if (textField == self.loginNameNumbersTextField) {
            self.loginNameLine.backgroundColor = color;
        } else {
            self.loginPwdLine.backgroundColor = color;
        }
    }];
}


@end
