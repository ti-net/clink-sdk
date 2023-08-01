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
        popupMenu.isShowShadow = NO;
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
//            domainName.accessSecretDomainName = @"D5B70DD63C4848B6824C0233D2FC201F";//@"50ECA889F8F04EF29EE53FAEB009FBAE";//@"523A3358B62A433B8281F3524107DD75";
//            domainName.accessIdDomainName = @"f8f7d2be61ce4b8ba272b35647ac1eb2";//@"b9a4b617017f4316bb567e69fa5e685b";//@"b7793dd47ec048b8adb6d118dc1eb00b";
//            domainName.enterpriseIdDomainName = @"8003846";//@"8003846";//@"8008047";
            
            domainName.accessSecretDomainName = @"CAF7303F4ACA4A39831CD59B88FF475D";
            domainName.accessIdDomainName = @"1a9301c2f49d439887d9f10596f7d675";
            domainName.enterpriseIdDomainName = @"8006886";
            
            
            /**下面的都是集度的账号**/
//            domainName.accessSecretDomainName = @"C7A9F08C77094C5B89AC66A296B3E7FF";
//            domainName.accessIdDomainName = @"80ffab421a2b44a9a20a6ba9c51266d0";
//            domainName.enterpriseIdDomainName = @"8009725";
            
//            domainName.accessSecretDomainName = @"2A911DC35309459C88B554B63A8C1B09";
//            domainName.accessIdDomainName = @"ece7c7197fe34459abe98d602fb52072";
//            domainName.enterpriseIdDomainName = @"8009726";
            
//            /// 自建的集度新的 accessSecret 和 accessId
//            domainName.accessSecretDomainName = @"354A4F14CED94694A5C9303FF5CFB443";
//            domainName.accessIdDomainName = @"6aef004204c64ded93aefb097941dd66";
//            domainName.enterpriseIdDomainName = @"8009725";
            
//            domainName.accessSecretDomainName = @"7045358122EA4410B6C3BE6246B0CF14";
//            domainName.accessIdDomainName = @"bcfd1c8a283d403698cf14de2ee3d01e";
//            domainName.enterpriseIdDomainName = @"8009727";
            /**上面的都是集度的账号**/
            
        }
            break;
        case 1: {
            self.platformNameLabel.text = @"(北京)";
            domainName.domainName = @"天润北京";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj.clink.cn";
            
            /// 集度
//            domainName.accessSecretDomainName = @"252920977A7443C385423FD8084FA1F7";
//            domainName.accessIdDomainName = @"c1b86e53996e4c74b6ce28df8f9faee1";
//            domainName.enterpriseIdDomainName = @"8009636";
            
//            domainName.accessSecretDomainName = @"2E2DA31A81B34BAAA42EA70728C00C7E";
//            domainName.accessIdDomainName = @"ec5d4a981a094ffcaefad0579c14a209";
//            domainName.enterpriseIdDomainName = @"8000467";
            
            
//            domainName.accessSecretDomainName = @"D3C4D9DE92C74DF389AFDEBAD97007E0";
//            domainName.accessIdDomainName = @"726637e9b8474675beca358a4817cd43";
//            domainName.enterpriseIdDomainName = @"8007325";
            
            
            domainName.accessSecretDomainName = @"0B787C5B09F144868D367DF0DB373CA2";
            domainName.accessIdDomainName = @"bbf1b992191c4567ac44811e338eb251";
            domainName.enterpriseIdDomainName = @"8000002";
            
//            /// 分贝通
//            domainName.accessSecretDomainName = @"5B002BF8447148DFB06C5D3C4982B49F";
//            domainName.accessIdDomainName = @"78a696762ab04b0ba1e52a1535d4011c";
//            domainName.enterpriseIdDomainName = @"8008921";
            
            //环球
//            domainName.accessSecretDomainName = @"ADA6FD05D804449E81EA073A0585184A";
//            domainName.accessIdDomainName = @"9396287862064752b4d7edab90b02448";
//            domainName.enterpriseIdDomainName = @"8005912";
            
            
//            //环球
//            domainName.accessSecretDomainName = @"146F4FF774974780A92C191C70644E15";
//            domainName.accessIdDomainName = @"e103eb5bd2d14e8981b88326ee4a1637";
//            domainName.enterpriseIdDomainName = @"8005912";
            
            //环球兴学
            //@"8005912"
            //@"e103eb5bd2d14e8981b88326ee4a1637"
            //@"146F4FF774974780A92C191C70644E15"
            
            
            //@"b0531dc999634ea0ba0b0f4534f833d0";
            //@"2C7C5D937C24492C8CC325F8546BACAD";
            
            //北京测试
            //@"8000002";
            //@"72EBF29CB4614F7AB404EEC07BFF0B1B";
            //@"8758096679544ff189d4a9457747f109";
            
//            domainName.accessSecretDomainName = @"762316074B214A69B85B41001FB56008";
//            domainName.accessIdDomainName = @"f819c4d256ef4eadb6047a87aff686b5";
//            domainName.enterpriseIdDomainName = @"8009137";
//            domainName.accessSecretDomainName = @"72EBF29CB4614F7AB404EEC07BFF0B1B";
//            domainName.accessIdDomainName = @"8758096679544ff189d4a9457747f109";
//            domainName.enterpriseIdDomainName = @"8000002";
            
            
            //中青旅遨游
            //@"8008567";
            //57EC597D74324BE0B0E5B6C234B84E77
            //24bd9a30cfaa455696dfc72a2d86f46e
        }
            break;
        case 2: {
            self.platformNameLabel.text = @"(正和共达)";
            domainName.domainName = @"正和共达";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj.clink.cn";
            domainName.accessSecretDomainName = @"0B2A488841FC44C899FC76C5EBEF6491";
            domainName.accessIdDomainName = @"56ab9623cf2840a88aa0897f6da6ef25";
            domainName.enterpriseIdDomainName = @"8004022";
        }
            break;
        case 3: {
            self.platformNameLabel.text = @"(易流)";
            domainName.domainName = @"易流";
            domainName.apiUrlDomainName = @"https://octopus-api-1.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj.clink.cn";
            domainName.accessSecretDomainName = @"03B83916DD8A409A9A4835C958F34245";
            domainName.accessIdDomainName = @"4dfd6ce439434226917dc4c1fdbc29f1";
            domainName.enterpriseIdDomainName = @"8002259";
        }
            break;
        case 4: {
            self.platformNameLabel.text = @"(北京测试)";
            domainName.domainName = @"天润北京测试";
            domainName.apiUrlDomainName = @"https://tcbus-api-dev.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://chat-app-bj-test0.clink.cn";
//            domainName.accessSecretDomainName = @"7286A15D65444A00A8CEEE5F6665091F";//@"F08784A3713D44EFBDC44AB8A8DD1143";//@"C9BA8CCD2B69431FADA31D71E7183D76";
//            domainName.accessIdDomainName = @"bf4cd0e6259640d29fabee960d02c561";//@"9b205d1f71904ceab0a464acd6cfc839";//@"98262f45c9df42a68eecfded8a597fdd";
//            domainName.enterpriseIdDomainName = @"8001678";//@"8001612";//@"8001294";
            
            /// 陈仪(test0)
            domainName.accessSecretDomainName = @"71E0A8FB84BD4BE0B4CC813B73B5B47D";
            domainName.accessIdDomainName = @"7b9ec915f0c74443ac7115d241af9c26";
            domainName.enterpriseIdDomainName = @"8001678";
            
            
        }
            break;
        case 5: {
            self.platformNameLabel.text = @"(北京测试KT)";
            domainName.domainName = @"天润北京测试KT";
            domainName.apiUrlDomainName = @"https://tcbus-api-dev.vlink.cn/api/sdk/v1";
            domainName.onlineUrlDomainName = @"https://clink2-chat-app-dev.clink.cn/";
            
            //小美
//            domainName.accessSecretDomainName = @"FB44FE4A397F40FCAE6F00BA302357AA";
//            domainName.accessIdDomainName = @"35a919ecd0ca4b619f82a655ba1e696d";
//            domainName.enterpriseIdDomainName = @"8000376";
            
            domainName.accessSecretDomainName = @"AE7BA517D22B4E77982472B6088F0CDF";
            domainName.accessIdDomainName = @"5eea43dcfa39447c9bf7e0ebbc388021";
            domainName.enterpriseIdDomainName = @"8000376";
            
            //刘丽测试
//            domainName.accessSecretDomainName = @"530EE3604D884A188A22AD8E134936AF";
//            domainName.accessIdDomainName = @"b543c57d6e9e4eef91d576061dc7b813";
//            domainName.enterpriseIdDomainName = @"8000376";
            
            //姚老板
//            domainName.accessSecretDomainName = @"CAEDE8AE3B4840DCBECDD4C77671E524";
//            domainName.accessIdDomainName = @"1070fd52f73a4b5f927767bcbafc7e27";
//            domainName.enterpriseIdDomainName = @"8000376";
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
