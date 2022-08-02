//
//  MainViewController.m
//  TOSClientKitDemo
//
//  Created by 言 on 2022/6/29.
//

#import "MainViewController.h"
#import "LoginViewController.h"
//#import "CustomStylesViewController.h"
#import "MineConfigViewController.h"
#import "MessageViewController.h"
#import "NSDate+TimeFormatting.h"
#import "ProtocolViewController.h"

@interface MainViewController ()

@property (weak, nonatomic) IBOutlet UITextField *visitorsID;

@end

@implementation MainViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.navigationItem.title = @"";
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:YES animated:animated];
}

// 接入
- (IBAction)didClickJoinUpBtnAction:(UIButton *)sender {
    LoginViewController *loginVC = [[LoginViewController alloc] initWithNibName:[LoginViewController className] bundle:nil];
    [self.navigationController pushViewController:loginVC animated:YES];
}

//立即体验
- (IBAction)didClickExperienceImmediatelyBtnAction:(UIButton *)sender {
    TOSInitOption * initOption = [[TOSInitOption alloc] initWithOption:YES apiUrl:@"https://octopus-api-1.vlink.cn/api/sdk/v1" onlineUrl:@"https://chat-app-bj.clink.cn" accessId:@"8758096679544ff189d4a9457747f109" accessSecret:@"72EBF29CB4614F7AB404EEC07BFF0B1B" enterpriseId:@"8000002" advanceParams:@{}];
    [[TOSClientKit sharedTOSKit] initSDK:initOption];
    
    NSString *visitorsID = [NSString stringWithFormat:@"%ld",[[NSDate date] timeSwitchTimestamp]];
    if (self.visitorsID.text &&
        self.visitorsID.text.length > 0) {
        visitorsID = self.visitorsID.text;
    }
    
    TOSConnectOption * connectOption = [[TOSConnectOption alloc] initWithOption:visitorsID nickname:@"客户" headUrl:@"https://image.baidu.com/search/index?ct=201326592&z=undefined&tn=baiduimage&ipn=d&word=%E5%A4%B4%E5%83%8F&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=3627913612,1944792156&os=13889901,1672538297&simid=3627913612,1944792156&pn=11&di=7108135681917976577&ln=1972&fr=&fmq=1657034284813_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fup.enterdesk.com%252Fedpic%252F4d%252F63%252Fbd%252F4d63bd0b3bf8cc9aa0dc3e1111646b1c.jpeg%26refer%3Dhttp%253A%252F%252Fup.enterdesk.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Dauto%3Fsec%3D1659626284%26t%3Dce69786ee9e6adc81303fc40fb461d55&rpstart=0&rpnum=0&adpicid=0&nojc=undefined&tt=1&dyTabStr=MCwzLDEsNSwyLDcsOCw2LDQsOQ%3D%3D" mobile:@"123219321" advanceParams:@{}];
    [[TOSClientKit sharedTOSKit] connect:connectOption success:^{
        //创建会话成功，进入聊天页面
        MessageViewController *chatVC = [[MessageViewController alloc] init];
        chatVC.titleName = @"在线客服";
        chatVC.appName = @"客服SDK";
        self.hidesBottomBarWhenPushed  = YES;
        [self.navigationController pushViewController:chatVC animated:YES];
        self.hidesBottomBarWhenPushed  = NO;
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        NSLog(@"errorDes === %@",errorDes);
    } tokenIncorrect:^{
        NSLog(@"tokenIncorrect");
    }];
}

//自定义样式
- (IBAction)didClickCustomStylesBtnAction:(UIButton *)sender {
    MineConfigViewController *configVC = [[MineConfigViewController alloc] initWithNibName:[MineConfigViewController className] bundle:nil];
    [self.navigationController pushViewController:configVC animated:YES];
}

//接入文档
- (IBAction)didClickDocumentBtnAction:(UIButton *)sender {
    ProtocolViewController *vc = [[ProtocolViewController alloc] init];
    [self.navigationController pushViewController:vc animated:YES];
}


@end
