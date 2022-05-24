//
//  LoginViewController.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/10.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "LoginViewController.h"
#import "AppDelegate.h"
#import "MainTabBarController.h"



#import <YBPopupMenu/YBPopupMenu.h>

#import "TFHpple.h"

#import "DomainNameSave.h"

@interface LoginViewController () <YBPopupMenuDelegate,
TIMAuditMessageSuccessDelegate,
TIMCustomMessageClickDelegate,
TIMRTCMediaMessageDelegate>

@property (weak, nonatomic) IBOutlet UIImageView *loginLogo;
@property (strong, nonatomic) IBOutlet UITapGestureRecognizer *logoTapGesture;
@property (weak, nonatomic) IBOutlet UITextField *appNumbersTextField;
@property (weak, nonatomic) IBOutlet UITextField *loginNameNumbersTextField;
@property (weak, nonatomic) IBOutlet UITextField *loginPwdTextField;
@property (weak, nonatomic) IBOutlet UILabel *platformNameLabel;


@property (weak, nonatomic) IBOutlet UIButton *loginBtn;

@end

@implementation LoginViewController

-(void)viewDidLoad{
    [super viewDidLoad];
//    self.logoTapGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(logoTapGestureTouch:)];
    self.loginBtn.enabled = YES;
    self.loginBtn.backgroundColor = kHexColor(0x2397FF);
    self.logoTapGesture.numberOfTapsRequired = 5;
    
    [self.loginLogo addGestureRecognizer:self.logoTapGesture];
    
    UITapGestureRecognizer *tapTecoginzer = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(singleTap)];
    tapTecoginzer.numberOfTapsRequired = 1;
    tapTecoginzer.delaysTouchesBegan = YES;
    [self.view addGestureRecognizer:tapTecoginzer];
    
}

- (void)logoTapGestureTouch:(UIGestureRecognizer *)gesture {

    @weakify(self);
    [YBPopupMenu showRelyOnView:self.loginLogo titles:@[ @"天润上海",@"天润北京",@"企知道"] icons:@[] menuWidth:100.f otherSettings:^(YBPopupMenu *popupMenu) {
        @strongify(self);
        popupMenu.arrowWidth = 0;
        popupMenu.arrowHeight = 0;
        popupMenu.cornerRadius = 3.;
        popupMenu.borderWidth = 1.;
        popupMenu.borderColor = kHexColor(0xECECEC);
        popupMenu.isShowShadow = NO;
        popupMenu.itemHeight = 46.f;
        popupMenu.tableView.separatorInset = UIEdgeInsetsMake(0, 14.f, 0, 0);
        popupMenu.tableView.separatorColor = kHexColor(0xECECEC);
        popupMenu.tableView.separatorStyle = UITableViewCellSeparatorStyleSingleLine;
        popupMenu.dismissOnTouchOutside = NO;
        popupMenu.delegate = self;
    }];

}

#pragma mark - YBPopupMenuDelegate
- (void)ybPopupMenu:(YBPopupMenu *)ybPopupMenu didSelectedAtIndex:(NSInteger)index {
    DomainNameSave *domainName = [DomainNameSave shareDomainNameSave];
    switch (index) {
        case 0: {
            self.platformNameLabel.text = @"(上海)";
            domainName.domainName = @"天润上海";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-sh.clink.cn";
            domainName.accessSecretDomainName = @"50ECA889F8F04EF29EE53FAEB009FBAE";
            domainName.accessIdDomainName = @"b9a4b617017f4316bb567e69fa5e685b";
            domainName.enterpriseIdDomainName = @"8003846";
        }
            break;
        case 1: {
            self.platformNameLabel.text = @"(北京)";
            domainName.domainName = @"天润北京";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj.clink.cn";
            domainName.accessSecretDomainName = @"72EBF29CB4614F7AB404EEC07BFF0B1B";
            domainName.accessIdDomainName = @"8758096679544ff189d4a9457747f109";
            domainName.enterpriseIdDomainName = @"8000002";
        }
            break;

        case 2: {
            self.platformNameLabel.text = @"(企知道)";
            domainName.domainName = @"企知道";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj.clink.cn";
            domainName.accessSecretDomainName = @"29B4DE90061641B2866DB0A000A7A329";
            domainName.accessIdDomainName = @"d3b73cb51a85499f80a337331bbed100";
            domainName.enterpriseIdDomainName = @"8000002";
        }
            break;
        default:
            break;
    }
    [domainName saveData];

    [self.platformNameLabel sizeToFit];
}

-(void)singleTap{
    [self.appNumbersTextField resignFirstResponder];
    [self.loginNameNumbersTextField resignFirstResponder];
    [self.loginPwdTextField resignFirstResponder];
}


- (void)onSuccess:(NSString *)groupId{
    NSLog(@"AuditOnSuccess === %@",groupId);
}

// 点击自定义消息
- (void)onClickCustomMessage:(MYHTIMMessage *)timMessage{

    TIMCustomizeMessage * custMessage = (TIMCustomizeMessage *)timMessage.content;
    NSLog(@"点击自定义消息");
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
        [AppDelegate shareAppDelegate].window.rootViewController = tabBarC;
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

}

//显示隐藏密码
- (IBAction)didClickShowOrHiddenPwdAction:(UIButton *)sender {
    sender.selected = !sender.selected;
    self.loginPwdTextField.secureTextEntry = !sender.isSelected;
}

- (BOOL)checkInputItem {
    [NSData dataWithContentsOfFile:@""];
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


/*
 回调
 */
- (void)onRtcMediaMessage:(BOOL)onlyAudio receiveId:(NSString*)receiveId{
    if (onlyAudio) {
//        [[TRTCKit sharedTRTCKit] startSingleCall:receiveId mediaType:TRTCMediaAudioType];
    } else {
//        [[TRTCKit sharedTRTCKit] startSingleCall:receiveId mediaType:TRTCMediaVideoType];
    }
}

- (void)dealloc {
    NSLog(@"login vc dealloc!!!!!");
}

@end
