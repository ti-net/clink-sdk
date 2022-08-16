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
        [domainName.domainName isEqualToString:@"天润北京"]) {
        self.platformNameLabel.hidden = YES;
    } else {
        self.platformNameLabel.hidden = NO;
    }
}

- (IBAction)didClickLoginBtnAction:(UIButton *)sender {
    
    DomainNameSave *domainName = [DomainNameSave shareDomainNameSave];

    BOOL bDebugLog = YES;
    NSDictionary *params = @{};
    if ([domainName.domainName isEqualToString:@"天润北京测试KT"]) {
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
        [self showErrorView:errorDes?:@"登录失败"];
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
    [YBPopupMenu showRelyOnView:self.logoImageView titles:@[@"天润北京", @"天润上海", @"天润北京测试", @"天润北京测试KT"] icons:@[] menuWidth:150.f otherSettings:^(YBPopupMenu *popupMenu) {
        @strongify(self);
        popupMenu.arrowWidth = 0;
        popupMenu.arrowHeight = 0;
        popupMenu.cornerRadius = 3.;
        popupMenu.borderWidth = 1.;
        popupMenu.borderColor = kHexColor(0xECECEC);
        popupMenu.isShowShadow = NO;
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
            domainName.domainName = @"天润北京";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj.clink.cn";
            domainName.accessSecretDomainName = @"72EBF29CB4614F7AB404EEC07BFF0B1B";
            domainName.accessIdDomainName = @"8758096679544ff189d4a9457747f109";
            domainName.enterpriseIdDomainName = @"8000002";
        }
            break;
        case 1: {
            domainName.domainName = @"天润上海";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-sh.clink.cn";
            domainName.accessSecretDomainName = @"50ECA889F8F04EF29EE53FAEB009FBAE";
            domainName.accessIdDomainName = @"b9a4b617017f4316bb567e69fa5e685b";
            domainName.enterpriseIdDomainName = @"8003846";
        }
            break;
        case 2: {
            domainName.domainName = @"天润北京测试";
            domainName.apiUrlDomainName = @"https://tcbus-api-dev.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj-test3.clink.cn";
            domainName.accessSecretDomainName = @"B6F2A2125B2C476ABBA230F22B8B5D79";
            domainName.accessIdDomainName = @"80a71b366026406f83b672a66bdd59ce";
            domainName.enterpriseIdDomainName = @"8000581";
        }
            break;
        case 3: {
            domainName.domainName = @"天润北京测试KT";
            domainName.apiUrlDomainName = @"https://tcbus-api-dev.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://clink2-chat-app-dev.clink.cn/";
            domainName.accessSecretDomainName = @"D3127C4EC8B942BEB8D69E32DB0FF83D";
            domainName.accessIdDomainName = @"ad3046061c94444999a083acd7d34ae3";
            domainName.enterpriseIdDomainName = @"8000376";
        }
            break;
        case 4: {
            domainName.domainName = @"企知道测试";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj.clink.cn";
            domainName.accessSecretDomainName = @"53FFAECE8D7E4EA8813F35E42F28A8CD";
            domainName.accessIdDomainName = @"8ededc2efa82426790af86c055bbd8e3";
            domainName.enterpriseIdDomainName = @"8006638";
        }
            break;
        case 5: {
            domainName.domainName = @"仙麦";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj.clink.cn";
            domainName.accessSecretDomainName = @"C70F081A42D748539B21075CB402E7D2";
            domainName.accessIdDomainName = @"7ef95ee5fe404142b9c25ebb68aba3ab";
            domainName.enterpriseIdDomainName = @"8006574";
        }
            break;
        case 6: {
            domainName.domainName = @"正和共达";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj.clink.cn";
            domainName.accessSecretDomainName = @"0B2A488841FC44C899FC76C5EBEF6491";
            domainName.accessIdDomainName = @"56ab9623cf2840a88aa0897f6da6ef25";
            domainName.enterpriseIdDomainName = @"8004022";
        }
            break;
        case 7: {
            domainName.domainName = @"企知道正式";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj.clink.cn";
            domainName.accessSecretDomainName = @"5B7D78BD409B464D93742ADF754720B1";
            domainName.accessIdDomainName = @"25b1fdb74cfc43a8b53e11fe1b8cfdae";
            domainName.enterpriseIdDomainName = @"8006597";
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
