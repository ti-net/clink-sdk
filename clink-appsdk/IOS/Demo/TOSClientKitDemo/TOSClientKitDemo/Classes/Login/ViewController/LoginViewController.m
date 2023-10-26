//
//  LoginViewController.m
//  TOSClientKitDemo
//
//  Created by 言 on 2022/6/29.
//

#import "LoginViewController.h"
#import "DomainNameSave.h"
#import "UIButton+EnlargeEdge.h"
#import "NSDate+TimeFormatting.h"
#import <YBPopupMenu/YBPopupMenu.h>

@interface LoginViewController () <YBPopupMenuDelegate>

@property (weak, nonatomic) IBOutlet UIButton *loginBtn;
@property (weak, nonatomic) IBOutlet UITextField *enterpriseIdTextF;
@property (weak, nonatomic) IBOutlet UITextField *accessIdTextF;
@property (weak, nonatomic) IBOutlet UITextField *accessSecretTextF;
@property (weak, nonatomic) IBOutlet UIButton *accessSecretBtn;
@property (weak, nonatomic) IBOutlet UIImageView *logoImageView;
@property (weak, nonatomic) IBOutlet UILabel *platformNameLabel;

@property (copy, nonatomic) NSString *userId;

@end

@implementation LoginViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    NSArray *testFieldSignalArr = @[
        self.enterpriseIdTextF.rac_textSignal,
        self.accessIdTextF.rac_textSignal,
        self.accessSecretTextF.rac_textSignal];
    
    RACSignal *single = [RACSignal combineLatest:testFieldSignalArr];
    
    @weakify(self);
    [single subscribeNext:^(id  _Nullable x) {
        @strongify(self);
        NSString *enterpriseIdNum = self.enterpriseIdTextF.text;
        NSString *accessIdNum = self.accessIdTextF.text;
        NSString *accessSecretNum = self.accessSecretTextF.text;
        
        if (enterpriseIdNum.length > 0 &&
            accessIdNum.length > 0 &&
            accessSecretNum.length > 0) {
            
            self.loginBtn.enabled = YES;
            self.loginBtn.backgroundColor = kHexColor(0x4385FF);
        } else {
            self.loginBtn.enabled = NO;
            self.loginBtn.backgroundColor = kHexColor(0xBDD5FF);
        }
    }];
    self.view.backgroundColor = [UIColor whiteColor];
    [self.accessSecretBtn setEnlargeEdge:10.f];
    
    DomainNameSave *domainName = [DomainNameSave shareDomainNameSave];
    self.platformNameLabel.text = [NSString stringWithFormat:@"(%@)",domainName.domainName];
    if (!domainName.domainName ||
        [domainName.domainName isEqualToString:@""] ||
        [domainName.domainName isEqualToString:@"北京"]) {
        self.platformNameLabel.hidden = YES;
    } else {
        self.platformNameLabel.hidden = NO;
    }
}

- (IBAction)didClickLoginBtnAction:(UIButton *)sender {
    
    DomainNameSave *domainName = [DomainNameSave shareDomainNameSave];

    BOOL bDebugLog = YES;
    NSDictionary *params = @{};
    if ([domainName.domainName isEqualToString:@"北京测试KT"]) {
        params = @{@"configENVString": @"KTTestEnv"};
    }
    
    TOSInitOption * initOption = [[TOSInitOption alloc] initWithOption:bDebugLog
                                                                apiUrl:domainName.apiUrlDomainName
                                                             onlineUrl:domainName.onlineUrlDomainName
                                                              accessId:self.accessIdTextF.text
                                                          accessSecret:self.accessSecretTextF.text
                                                          enterpriseId:self.enterpriseIdTextF.text
                                                         advanceParams:params];
    [[TOSClientKit sharedTOSKit] initSDK:initOption];
    
    NSString *nickname     = @"先生";
    NSString *headerUrl    = @"https://img2.baidu.com/it/u=1229468480,2938819374&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500";
    NSString *userId       = [NSString stringWithFormat:@"%ld",[[NSDate date] timeSwitchTimestamp]];
    NSString *phoneNumber  = @"14107240003";
    
    if (self.userId &&
        self.userId.length > 0) {
        userId = self.userId;
    }
    
    TOSConnectOption * connectOption = [[TOSConnectOption alloc] initWithOption:userId
                                                                       nickname:nickname
                                                                        headUrl:headerUrl
                                                                         mobile:phoneNumber
                                                                  advanceParams:@{}];
    [[TOSClientKit sharedTOSKit] connect:connectOption success:^{
        //创建会话成功，进入聊天页面
        TOSCustomerChatVC *chatVC = [[TOSCustomerChatVC alloc] init];
        chatVC.titleName = @"在线客服";
        chatVC.appName = @"客服SDK";
        self.hidesBottomBarWhenPushed  = YES;
        [self.navigationController pushViewController:chatVC animated:YES];
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        if(errCode == TIM_API_REQUEST_FAIL) {
            [self showErrorView:@"请检查您的账号密码或网络问题"];
        } else {
            [self showErrorView:errorDes?:@"登录失败"];
        }
    } tokenIncorrect:^{
        NSLog(@"tokenIncorrect");
    }];
}

- (IBAction)didClickAccessSecretBtnAction:(UIButton *)sender {
    self.accessSecretTextF.secureTextEntry = sender.isSelected;
    sender.selected = !sender.selected;
}

- (IBAction)didClickSwitchServeTapAction:(UITapGestureRecognizer *)sender {
    @weakify(self);
    [YBPopupMenu showRelyOnView:self.logoImageView titles:@[@"北京", @"上海", @"北京测试", @"北京测试KT"] icons:@[] menuWidth:150.f otherSettings:^(YBPopupMenu *popupMenu) {
        @strongify(self);
        popupMenu.arrowWidth = 0;
        popupMenu.arrowHeight = 0;
        popupMenu.cornerRadius = 3.;
        popupMenu.borderWidth = 1.;
        popupMenu.borderColor = kHexColor(0xECECEC);
//        popupMenu.isShowShadow = NO;
        popupMenu.itemHeight = 46.f;
        popupMenu.tableView.separatorInset = UIEdgeInsetsMake(0, 14.f, 0, 0);
        popupMenu.tableView.separatorColor = kHexColor(0xF3F6F7);
        popupMenu.tableView.separatorStyle = UITableViewCellSeparatorStyleSingleLine;
        popupMenu.dismissOnTouchOutside = YES;
        popupMenu.delegate = self;
    }];
}

#pragma mark - YBPopupMenuDelegate
- (void)ybPopupMenu:(YBPopupMenu *)ybPopupMenu didSelectedAtIndex:(NSInteger)index {
    self.platformNameLabel.hidden = NO;
    DomainNameSave *domainName = [DomainNameSave shareDomainNameSave];
    switch (index) {
        case 0: {
            domainName.domainName = @"北京";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj.clink.cn";
            domainName.accessSecretDomainName = @"2C7C5D937C24492C8CC325F8546BACAD";//@"72EBF29CB4614F7AB404EEC07BFF0B1B";
            domainName.accessIdDomainName = @"b0531dc999634ea0ba0b0f4534f833d0";//@"8758096679544ff189d4a9457747f109";
            domainName.enterpriseIdDomainName = @"8000002";
        }
            break;
        case 1: {
            domainName.domainName = @"上海";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-sh.clink.cn";
            domainName.accessSecretDomainName = @"D5B70DD63C4848B6824C0233D2FC201F";
            domainName.accessIdDomainName = @"f8f7d2be61ce4b8ba272b35647ac1eb2";
            domainName.enterpriseIdDomainName = @"8003846";
        }
            break;
        case 2: {
            domainName.domainName = @"北京测试";
            domainName.apiUrlDomainName = @"https://tcbus-api-dev.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj-test3.clink.cn";
            domainName.accessSecretDomainName = @"B6F2A2125B2C476ABBA230F22B8B5D79";
            domainName.accessIdDomainName = @"80a71b366026406f83b672a66bdd59ce";
            domainName.enterpriseIdDomainName = @"8000581";
        }
            break;
        case 3: {
            domainName.domainName = @"北京测试KT";
            domainName.apiUrlDomainName = @"https://tcbus-api-dev.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://clink2-chat-app-dev.clink.cn/";
            domainName.accessSecretDomainName = @"CAEDE8AE3B4840DCBECDD4C77671E524";
            domainName.accessIdDomainName = @"1070fd52f73a4b5f927767bcbafc7e27";
            domainName.enterpriseIdDomainName = @"8000376";
        }
            break;
        default:
            break;
    }
    [domainName saveData];
    
    self.platformNameLabel.text = [NSString stringWithFormat:@"(%@)",domainName.domainName];
    self.accessIdTextF.text = [NSString stringWithFormat:@"%@",domainName.accessIdDomainName];
    self.accessSecretTextF.text = [NSString stringWithFormat:@"%@",domainName.accessSecretDomainName];
    self.enterpriseIdTextF.text = [NSString stringWithFormat:@"%@",domainName.enterpriseIdDomainName];
    self.loginBtn.enabled = YES;
    self.loginBtn.backgroundColor = kHexColor(0x4385FF);
}

- (IBAction)didClickInputUserIdTapAction:(UITapGestureRecognizer *)sender {
    self.userId = @"tinet_mobile_ios111";
    [self showErrorView:@"固定账号已输入"];
}

@end
