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
#import "TIMBaseNavigationController.h"
#import "LoginModel.h"
#import <YBPopupMenu/YBPopupMenu.h>
#import "DomainNameSave.h"
#import <TOSClientKit/TOSClientKit.h>

//16进制颜色值，注意：在使用的时候hexValue写成：0x000000
#define TOSHexColor(hexValue) [UIColor colorWithRed:((float)(((hexValue) & 0xFF0000) >> 16))/255.0 green:((float)(((hexValue) & 0xFF00) >> 8))/255.0 blue:((float)((hexValue) & 0xFF))/255.0 alpha:1.0]


@interface LoginViewController () <YBPopupMenuDelegate>{

    UIActivityIndicatorView *_loading;
}

@property (weak, nonatomic) IBOutlet UIImageView *loginLogo;
@property (strong, nonatomic) IBOutlet UITapGestureRecognizer *logoTapGesture;
@property (weak, nonatomic) IBOutlet UITextField *appNumbersTextField;
@property (weak, nonatomic) IBOutlet UITextField *loginNameNumbersTextField;
@property (weak, nonatomic) IBOutlet UITextField *loginPwdTextField;
@property (weak, nonatomic) IBOutlet UITextField *loginPhoneNumberTextField;
@property (weak, nonatomic) IBOutlet UILabel *platformNameLabel;


@property (weak, nonatomic) IBOutlet UIButton *loginBtn;

@end

@implementation LoginViewController

-(void)viewDidLoad{
    [super viewDidLoad];
    
    _loading = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleGray];
    _loading.center = CGPointMake(UIScreen.mainScreen.bounds.size.width/2, UIScreen.mainScreen.bounds.size.height/2);
    [_loading setColor:[UIColor blueColor]];
    
    [self.view addSubview:_loading];
    
    self.loginBtn.enabled = YES;
    self.loginBtn.backgroundColor = TOSHexColor(0x2397FF);
    self.logoTapGesture.numberOfTapsRequired = 5;
    
    DomainNameSave *domainName = [DomainNameSave shareDomainNameSave];
    if ([domainName.domainName isEqualToString:@"天润北京"] || !domainName.domainName) {
        self.platformNameLabel.text = @"(北京)";
    } else if ([domainName.domainName isEqualToString:@"天润上海"]){
        self.platformNameLabel.text = @"(上海)";
    } else if ([domainName.domainName isEqualToString:@"企知道临时"]) {
        self.platformNameLabel.text = @"(企知道临时)";
    } else if ([domainName.domainName isEqualToString:@"企知道正式"]) {
        self.platformNameLabel.text = @"(企知道正式)";
    } else if ([domainName.domainName isEqualToString:@"企知道测试"]) {
        self.platformNameLabel.text = @"(企知道测试)";
    } else if ([domainName.domainName isEqualToString:@"仙麦"]) {
        self.platformNameLabel.text = @"(仙麦)";
    } else if ([domainName.domainName isEqualToString:@"天润北京测试"]) {
        self.platformNameLabel.text = @"(北京测试)";
    } else if ([domainName.domainName isEqualToString:@"天润北京测试KT"]) {
        self.platformNameLabel.text = @"(北京测试KT)";
    }
    
    UITapGestureRecognizer *tapTecoginzer = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(singleTap)];
    tapTecoginzer.numberOfTapsRequired = 1;
    tapTecoginzer.delaysTouchesBegan = YES;
    [self.view addGestureRecognizer:tapTecoginzer];
}


-(void)singleTap{
    [self.appNumbersTextField resignFirstResponder];
    [self.loginNameNumbersTextField resignFirstResponder];
    [self.loginPwdTextField resignFirstResponder];
    [self.loginPhoneNumberTextField restorationIdentifier];
}


- (void)onSuccess:(NSString *)groupId{
    NSLog(@"AuditOnSuccess === %@",groupId);
}

//登录按钮
- (IBAction)didClickLoginBtnAction:(UIButton *)sender {
    
    DomainNameSave *domainName = [DomainNameSave shareDomainNameSave];
    
    BOOL bDebugLog = YES;
    NSDictionary * params = @{};
    if ([domainName.domainName isEqualToString:@"天润北京测试KT"]) {
        params = @{
            @"configENVString": @"KTTestEnv"
        };
    }
    TOSInitOption * initOption = [[TOSInitOption alloc] initWithOption:bDebugLog
                                                                apiUrl:domainName.apiUrlDomainName
                                                             onlineUrl:domainName.onlineUrlDomainName
                                                              accessId:domainName.accessIdDomainName
                                                          accessSecret:domainName.accessSecretDomainName
                                                          enterpriseId:domainName.enterpriseIdDomainName
                                                         advanceParams:params];
    [[TOSClientKit sharedTOSKit] initSDK:initOption];
    
    NSString * nickname     = @"iOS先生";//@"哈哈哈哈哈";
    NSString * headerUrl    = @"https://img2.baidu.com/it/u=1229468480,2938819374&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500";
    NSString * userId       = @"tinet_mobile_ios";
    NSString * phoneNumber  = @"14107240003";
    
    LoginModel *model = [LoginModel loginModel];
    
    model.nickname = nickname;
    model.headerUrl = headerUrl;
//    NSString * charaters = @"?!@#$^&%*+,:;='\"`<>()[]{}/\\| 《》";
//    NSCharacterSet * set = [[NSCharacterSet characterSetWithCharactersInString:charaters] invertedSet];
//    model.userId = [@"+=《》" stringByAddingPercentEncodingWithAllowedCharacters:set];//self.appNumbersTextField.text;
    
    model.userId = self.appNumbersTextField.text;
    model.phoneNumber = phoneNumber;
    model.advanceParams = @{
        @"channelType": @70,
        @"org_id": @2,
        @"oriAppId": @"edu24olapp",
        @"secondCategory": @5663,
        @"sourceType": @88,
        @"type": @"crm_param",
        @"uid": @100001320,
        @"username": @"hq_100001320",
    };//@{@"tinetAge": @"22"};
    
    [[LoginModel loginModel] saveLoginModel:model];
    
    if (self.loginNameNumbersTextField.text.length>0) {
        nickname = self.loginNameNumbersTextField.text;
    }
    
//    if (self.appNumbersTextField.text.length>0) {
//        userId = self.appNumbersTextField.text;
//    }
    
    if (self.loginPhoneNumberTextField.text.length>0) {
        phoneNumber = self.loginPhoneNumberTextField.text;
    }
    
    if ([userId containsString:@"-"]) {
        NSLog(@"用户Id包含\"-\",请重新输入!");
        return ;
    }
    
    //    本地kit一些配置
    [TOSClientKit sharedTOSKit].disableMessageNotificaiton = YES;  // 取消本地推送
    [TOSClientKit sharedTOSKit].disableMessageAlertSound = YES;  // 取消本地推送声音

    
    MainTabBarController *tabBarC = [[MainTabBarController alloc] init];
    tabBarC.selectedIndex = 0;
    if (@available(iOS 13.0, *)) {
        tabBarC.modalPresentationStyle = UIModalPresentationFullScreen;
        [self presentViewController:tabBarC animated:NO completion:^{
            
        }];
    }else{
        [AppDelegate shareAppDelegate].window.rootViewController = tabBarC;
    }
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
        NSLog(@"应用编号格式不正确");
        return NO;
    } else if (!self.loginNameNumbersTextField.text || self.loginNameNumbersTextField.text.length <= 0) {
        NSLog(@"用户名格式不正确");
        return NO;
    } else if (!self.loginPwdTextField.text || self.loginPwdTextField.text.length <= 0) {
        NSLog(@"密码输入不正确");
        return NO;
    } else if (![self isValidateMobile:self.loginPhoneNumberTextField.text] || self.loginPwdTextField.text.length <= 0) {
        NSLog(@"手机号输入不正确");
        return NO;
    }
    return YES;
}

- (BOOL)isValidateMobile:(NSString *)mobile {
    NSString *phoneRegex = @"1[3456789]([0-9]){9}";
    NSPredicate *phoneTest = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", phoneRegex];
    return [phoneTest evaluateWithObject:mobile];
}

- (IBAction)didClickLogoTapAction:(UITapGestureRecognizer *)sender {
    @weakify(self);
    [YBPopupMenu showRelyOnView:self.loginLogo titles:@[ @"天润上海",@"天润北京",@"正和共达", @"易流", @"天润北京测试",@"天润北京测试KT"] icons:@[] menuWidth:140.f otherSettings:^(YBPopupMenu *popupMenu) {
        @strongify(self);
        popupMenu.arrowWidth = 0;
        popupMenu.arrowHeight = 0;
        popupMenu.cornerRadius = 3.;
        popupMenu.borderWidth = 1.;
        popupMenu.borderColor = TOSHexColor(0xECECEC);
//        popupMenu.isShowShadow = NO;
        popupMenu.itemHeight = 46.f;
        popupMenu.tableView.separatorInset = UIEdgeInsetsMake(0, 14.f, 0, 0);
        popupMenu.tableView.separatorColor = TOSHexColor(0xECECEC);
        popupMenu.tableView.separatorStyle = UITableViewCellSeparatorStyleSingleLine;
        popupMenu.dismissOnTouchOutside = NO;
        popupMenu.delegate = self;
        popupMenu.maxVisibleCount = 12;
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
            domainName.accessSecretDomainName = @"";
            domainName.accessIdDomainName = @"";
            domainName.enterpriseIdDomainName = @"";
        }
            break;
        case 1: {
            self.platformNameLabel.text = @"(北京)";
            domainName.domainName = @"天润北京";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj.clink.cn";
            domainName.accessSecretDomainName = @"";
            domainName.accessIdDomainName = @"";
            domainName.enterpriseIdDomainName = @"";
            
        }
            break;
        case 2: {
            self.platformNameLabel.text = @"(正和共达)";
            domainName.domainName = @"正和共达";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj.clink.cn";
            domainName.accessSecretDomainName = @"";
            domainName.accessIdDomainName = @"";
            domainName.enterpriseIdDomainName = @"";
        }
            break;
        case 3: {
            self.platformNameLabel.text = @"(易流)";
            domainName.domainName = @"易流";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj.clink.cn";
            domainName.accessSecretDomainName = @"";
            domainName.accessIdDomainName = @"";
            domainName.enterpriseIdDomainName = @"";
        }
            break;
        case 4: {
            self.platformNameLabel.text = @"(北京测试)";
            domainName.domainName = @"天润北京测试";
            domainName.apiUrlDomainName = @"https://tcbus-api-dev.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj-test0.clink.cn";
            domainName.accessSecretDomainName = @"";
            domainName.accessIdDomainName = @"";
            domainName.enterpriseIdDomainName = @"";
            
            
        }
            break;
        case 5: {
            self.platformNameLabel.text = @"(北京测试KT)";
            domainName.domainName = @"天润北京测试KT";
            domainName.apiUrlDomainName = @"https://tcbus-api-dev.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://clink2-chat-app-dev.clink.cn/";
            
            domainName.accessSecretDomainName = @"";
            domainName.accessIdDomainName = @"";
            domainName.enterpriseIdDomainName = @"";
        }
            break;

        default:
            break;
    }
    [domainName saveData];

    [self.platformNameLabel sizeToFit];
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [self.view endEditing:YES];
}

- (void)dealloc {
    NSLog(@"login vc dealloc!!!!!");
}

@end
