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
    [TOSKitCustomInfo shareCustomInfo].senderText_Color = kHexColor(0x262626);
    
    TOSKitExtendBoardItemModel *model1 = [[TOSKitExtendBoardItemModel alloc] init];
    model1.type = TOSChatBoxExtendBoardTypePhotos;
    
    TOSKitExtendBoardItemModel *model2 = [[TOSKitExtendBoardItemModel alloc] init];
    model2.type = TOSChatBoxExtendBoardTypeTakePicture;
    
    TOSKitExtendBoardItemModel *model3 = [[TOSKitExtendBoardItemModel alloc] init];
    model3.type = TOSChatBoxExtendBoardTypeCustomFile;
    
    TOSKitExtendBoardItemModel *model4 = [[TOSKitExtendBoardItemModel alloc] init];
    model4.type = TOSChatBoxExtendBoardTypeArtificial;
    
    TOSKitExtendBoardItemModel *model5 = [[TOSKitExtendBoardItemModel alloc] init];
    model5.type = TOSChatBoxExtendBoardTypeCloseChat;
    
    TOSKitExtendBoardItemModel *model6 = [[TOSKitExtendBoardItemModel alloc] init];
    model6.title = @"百度地图";
    model6.index = 10006;
    model6.image = @"";
    model6.type = TOSChatBoxExtendBoardTypeCustom;
    
    [TOSKitChatBoxExtendBoard shareChatBoxExtendBoard].allItems = @[model1,model2,model3,model4,model5,model6];
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
    TOSInitOption * initOption = [[TOSInitOption alloc] initWithOption:YES
                                                                apiUrl:@"https://octopus-api-1.vlink.cn/api/sdk/v1"
                                                             onlineUrl:@"https://chat-app-bj.clink.cn"
                                                              accessId:@"8758096679544ff189d4a9457747f109"
                                                          accessSecret:@"72EBF29CB4614F7AB404EEC07BFF0B1B"
                                                          enterpriseId:@"8000002"
                                                         advanceParams:@{}];
    [[TOSClientKit sharedTOSKit] initSDK:initOption];
    
    NSString *visitorsID = [NSString stringWithFormat:@"%ld",[[NSDate date] timeSwitchTimestamp]];
//    if (self.visitorsID.text &&
//        self.visitorsID.text.length > 0) {
//        visitorsID = self.visitorsID.text;
//    }
    
    TOSConnectOption * connectOption = [[TOSConnectOption alloc] initWithOption:[NSString stringWithFormat:@"%@_ios",visitorsID] nickname:@"客户" headUrl:@"https://img2.baidu.com/it/u=1229468480,2938819374&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500" mobile:@"14107240003" advanceParams:@{}];
    [[TOSClientKit sharedTOSKit] connect:connectOption success:^{
        
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        NSLog(@"errorDes === %@",errorDes);
    } tokenIncorrect:^{
        NSLog(@"tokenIncorrect");
    }];
    
    //创建会话成功，进入聊天页面
    MessageViewController *chatVC = [[MessageViewController alloc] init];
    chatVC.titleName = @"在线客服";
    chatVC.appName = @"客服SDK";
    self.hidesBottomBarWhenPushed  = YES;
    [self.navigationController pushViewController:chatVC animated:YES];
    self.hidesBottomBarWhenPushed  = NO;
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
