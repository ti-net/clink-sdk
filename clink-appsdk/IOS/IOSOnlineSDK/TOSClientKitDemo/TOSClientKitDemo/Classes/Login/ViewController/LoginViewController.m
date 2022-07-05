//
//  LoginViewController.m
//  TOSClientKitDemo
//
//  Created by 言 on 2022/6/29.
//

#import "LoginViewController.h"

#import "UIButton+EnlargeEdge.h"

@interface LoginViewController ()

@property (weak, nonatomic) IBOutlet UIButton *loginBtn;
@property (weak, nonatomic) IBOutlet UITextField *enterpriseIdTextF;
@property (weak, nonatomic) IBOutlet UITextField *accessIdTextF;
@property (weak, nonatomic) IBOutlet UITextField *accessSecretTextF;
@property (weak, nonatomic) IBOutlet UIButton *accessSecretBtn;

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
    
    [self.accessSecretBtn setEnlargeEdge:10.f];
}

- (IBAction)didClickLoginBtnAction:(UIButton *)sender {
    
    TOSInitOption * initOption = [[TOSInitOption alloc] initWithOption:YES apiUrl:@"https://octopus-api-1.vlink.cn/api/sdk/v1" onlineUrl:@"https://chat-app-bj.clink.cn" accessId:self.accessIdTextF.text accessSecret:self.accessSecretTextF.text enterpriseId:self.enterpriseIdTextF.text advanceParams:@{}];
    [[TOSClientKit sharedTOSKit] initSDK:initOption];
    
    LoginModel *model = [LoginModel loginModel];
    TOSConnectOption * connectOption = [[TOSConnectOption alloc] initWithOption:@"" nickname:@"" headUrl:@"" mobile:@"" advanceParams:@{}];
    [[TOSClientKit sharedTOSKit] connect:connectOption success:^{
        //创建会话成功，进入聊天页面
        TOSCustomerChatVC *chatVC = [[TOSCustomerChatVC alloc] init];
        chatVC.titleName = @"西瓜客服";
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

@end
