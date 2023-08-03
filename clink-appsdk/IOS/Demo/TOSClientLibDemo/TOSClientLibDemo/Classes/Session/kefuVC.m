//
//  kefuVC.m
//  TIMClientKitDemo
//
//  Created by apple on 2021/8/20.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "kefuVC.h"
#import "ChatInfoViewController.h"
#import "TIMConstants.h"
#import "MainTabBarController.h"
#import "LoginModel.h"
#import "TIMBaseNavigationController.h"
#import "customTableViewCell.h"
#import <TOSClientKit/TOSClientKit.h>
#import <TOSClientKit/TOSKitCustomInfo.h>

@interface kefuVC ()<UIAlertViewDelegate>

@property (nonatomic, strong) UILabel                * unReadInfoLabel;

@end

@implementation kefuVC

- (void)configTOSUI {
    [TOSKitCustomInfo shareCustomInfo].senderBubble_backGround = [UIColor whiteColor];// HQBaseConfig.shareInstance.themeColor;
    [TOSKitCustomInfo shareCustomInfo].senderBubble_cornerRadius = 8;
    [TOSKitCustomInfo shareCustomInfo].receiveText_Color = TOSHexColor(0x010B16);
    [TOSKitCustomInfo shareCustomInfo].senderText_Color = TOSHexColor(0x010B16);//[UIColor whiteColor];
    [TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_title_sender_textColor = TOSHexColor(0x010B16);
    [TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_title_receive_textColor = TOSHexColor(0x010B16);
    [TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_price_sender_textColor = TOSHexColor(0xFF1414);
    [TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_price_receive_textColor = TOSHexColor(0xFF1414);
//    [TOSKitCustomInfo shareCustomInfo].ChatBox_voiceButton_enable = YES;
    [TOSKitCustomInfo shareCustomInfo].ChatBox_textview_placeholder = @"输入消息…";
    [TOSKitCustomInfo shareCustomInfo].Chat_tosRobotName_show = NO;
    [TOSKitCustomInfo shareCustomInfo].Chat_tosRobotName_enable = NO;
    [TOSKitCustomInfo shareCustomInfo].Chat_visitorName_enable = NO;
    [TOSKitCustomInfo shareCustomInfo].Chat_visitorName_show = NO;
    [TOSKitCustomInfo shareCustomInfo].portrait_cornerRadius = 20;
    [TOSKitCustomInfo shareCustomInfo].chatBox_talk_backgroundColor = [UIColor blueColor];
    
    
    [TOSKitCustomInfo shareCustomInfo].titleName = @"客服";
    [TOSKitCustomInfo shareCustomInfo].appName = @"客服SDK";
    //
    TOSClientKitCommodityCardOption *option = [[TOSClientKitCommodityCardOption alloc] init];
    option.subTitle = @"华为P40麒麟990 ";
    option.descriptions = @"这是商品描述，啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦";
    option.price = @"100.99";
    option.time = @"2022/05/24 18:32";
    
    //[@"https://oss-hqwx-edu24ol.hqwx.com/PPT训练营_01-1648707892011.png" stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLQueryAllowedCharacterSet]]
    
//    @"!$&'()*+,-./:;=?@_~%#[]^{}\"|\\<>"
//    @"!$&'()*+,-./:;=?@_~%#[]"
    //stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLQueryAllowedCharacterSet]
    
//    NSString *ss = [@"https://oss-hqwx-edu24ol.hqwx.com/PPT训练营_01-1648707892011.png" stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLQueryAllowedCharacterSet]];
    option.img = @"https://lmg.jj20.com/up/allimg/tp09/210F2130512J47-0-lp.jpg";//[@"https://oss-hqwx-edu24ol.hqwx.com/PPT训练营_01-1648707892011.png" stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];//@"https://img2.baidu.com/it/u=3019548648,4204913203&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500";
    option.status = @"已到货";
    option.extraInfo = @[@{@"name": @"订单号", @"value": @"1234567890"},
                         @{@"name": @"产品类型", @"value": @"电子产品"},
                         @{@"name": @"师傅"   , @"value": @"金师傅"},
                         @{@"name": @"服务地区", @"value": @"北京市"},
                         @{@"name": @"服务"   , @"value": @"满意"},
                         @{@"name": @"师傅电话", @"value": @"12345678900"},
                         @{@"name": @"订单状态", @"value": @"已完成"}];
    option.url = @"https://hellojoy.jd.com/";
    option.title = @"华为P40";
    option.subUrl = @"https://p4psearch.1688.com/";
    option.buttonText = @"buttonText";
    option.extraData = @"https://p4psearch.1688.com/";
    
    [TOSKitCustomInfo shareCustomInfo].commodityCardOption = option;
    
//    [TOSKitCustomInfo shareCustomInfo].quickEntryAllItems = @[@"快捷入口1",@"快捷入口2",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3"];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.edgesForExtendedLayout = UIRectEdgeBottom;
    self.extendedLayoutIncludesOpaqueBars = YES;
    
    [self configTOSUI];
    
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
    [self.view.subviews enumerateObjectsWithOptions:NSEnumerationReverse usingBlock:^(__kindof UIView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        if ([obj isKindOfClass:[UITableView class]]) {
            UITableView * tab = (UITableView*)obj;
            tab.contentInsetAdjustmentBehavior = UIScrollViewContentInsetAdjustmentNever;
        }
    }];
    /// 获取实时的未读消息数和最后一条消息
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(unreadMessage:) name:KTOSClientLibLastMessageReceivedNotification object:nil];
    
    NSString * mainUniqueId = [[OnlineDataSave shareOnlineDataSave] getMainUniqueId];
    NSLog(@"当前客服的mainUniqueId：%@   [[OnlineDataSave shareOnlineDataSave] getVisitorId]: %@", mainUniqueId, [[OnlineDataSave shareOnlineDataSave] getVisitorId]);
    /// 获取未读数(离线期间的)
    [[TOSClientKit sharedTOSKit] getUnreadMessage:^(NSString * _Nonnull lastMessage, NSInteger unreadCount) {
        NSLog(@"+++++未读数：%ld, 最后一条消息：%@", unreadCount, lastMessage);
        self.unReadInfoLabel.text = [NSString stringWithFormat:@"未读数：%ld %@", unreadCount, lastMessage];
    } withError:^(NSString * _Nonnull errorStr) {
        
    }];
    
    [self setupView];
}

- (void)unreadMessage:(NSNotification *)notification {
    NSDictionary * dict = [notification object];
    NSNumber * unreadCount = [NSNumber numberWithInt:[dict[@"unReadCount"] intValue]];
    NSLog(@"未读消息：%@ ,       最后一条消息：%@", unreadCount.stringValue, dict[@"lastMessage"]);
    self.unReadInfoLabel.text = [NSString stringWithFormat:@"未读数：%@ %@", unreadCount.stringValue, dict[@"lastMessage"]];
}



-(void)setupView{
    
    self.title = @"客户界面";
    
    self.view.backgroundColor = [UIColor whiteColor];
    
    
    UIButton*kefuBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    kefuBtn.backgroundColor = TOSHexColor(0xFF7C65);
    kefuBtn.frame = CGRectMake(kWindowWidth-kWindowWidth/3, 100, kWindowWidth/3, 40.f);
    kefuBtn.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:15.f];
    [kefuBtn setTitle:@"咨询客服1" forState:UIControlStateNormal];
    [kefuBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [kefuBtn addTarget:self action:@selector(kefuAction) forControlEvents:UIControlEventTouchDown];
    kefuBtn.layer.masksToBounds = YES;
    kefuBtn.layer.cornerRadius = 5.f;
    [self.view addSubview:kefuBtn];
    
    // 无快捷入口
    UIButton*kefuBtn1 = [UIButton buttonWithType:UIButtonTypeCustom];
    kefuBtn1.backgroundColor = TOSHexColor(0xFF7C65);
    kefuBtn1.frame = CGRectMake(kWindowWidth-kWindowWidth/3, 150, kWindowWidth/3, 40.f);
    kefuBtn1.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:15.f];
    [kefuBtn1 setTitle:@"咨询客服2" forState:UIControlStateNormal];
    [kefuBtn1 setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [kefuBtn1 addTarget:self action:@selector(kefuAction1) forControlEvents:UIControlEventTouchDown];
    kefuBtn1.layer.masksToBounds = YES;
    kefuBtn1.layer.cornerRadius = 5.f;
    [self.view addSubview:kefuBtn1];
    
    // 集度UI无快捷入口
    UIButton * jidukefuBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    jidukefuBtn.backgroundColor = TOSHexColor(0xFF7C65);
    jidukefuBtn.frame = CGRectMake(kWindowWidth-kWindowWidth/3, 200, kWindowWidth/3, 40.f);
    jidukefuBtn.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:15.f];
    [jidukefuBtn setTitle:@"集度客服" forState:UIControlStateNormal];
    [jidukefuBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [jidukefuBtn addTarget:self action:@selector(jidukefuAction) forControlEvents:UIControlEventTouchDown];
    jidukefuBtn.layer.masksToBounds = YES;
    jidukefuBtn.layer.cornerRadius = 5.f;
    [self.view addSubview:jidukefuBtn];
    
    [self.view addSubview:self.unReadInfoLabel];
    
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithTitle:@"跳转" style:(UIBarButtonItemStylePlain) target:self action:@selector(rightAction:)];
    
    
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

- (void)rightAction:(UIBarButtonItem *)sender {
//    TOSSessionInfoModel * model = [[TOSClientKit sharedTOSKit] getCurrentSessionInfo];
//    NSLog(@"viewload sessModel status:%d startTime:%lld mainUniqueId:%@ enterpriseId:%@ visitorId:%@",
//          [model.status intValue],
//          [model.startTime longLongValue],
//          model.mainUniqueId,
//          model.enterpriseId,
//          model.visitorId);
//    
//    NSLog(@"SDKVersion = %@",[TOSClientKit getSDKVersion]);
    kefuVC *vc = [[kefuVC alloc] init];
    vc.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:vc animated:YES];
    vc.hidesBottomBarWhenPushed = NO;
}


-(void)kefuAction{
    /*访客初始化回调
     当APP端IM mqtt 连接就绪，主动通知服务端，APP端调用该接口后服务端
     就开始会话流程逻辑创建会话*/
//    DomainNameSave *domainName = [DomainNameSave shareDomainNameSave];
    /// 配置快捷区域的数据
    [TOSKitCustomInfo shareCustomInfo].quickEntryAllItems = @[@"快捷入口1",@"快捷入口2",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3"];
    
    LoginModel *model = [LoginModel loginModel];
    TOSConnectOption * connectOption = [[TOSConnectOption alloc] initWithOption:model.userId?:@""
                                                                       nickname:model.nickname?:@""
                                                                        headUrl:model.headerUrl?:@""
                                                                         mobile:model.phoneNumber?:@""
                                                                  advanceParams:model.advanceParams?:@{}];
    
//    TOSConnectOption * connectOption = [[TOSConnectOption alloc] initWithOption:@"tinet_ios" nickname:@"昵称" headUrl:@"" mobile:@"1234" advanceParams:@{@"快捷入口":@"哈哈哈"}];
    [[TOSClientKit sharedTOSKit] connect:connectOption success:^{
        //创建会话成功，进入聊天页面
        ChatInfoViewController *chatVC = [[ChatInfoViewController alloc] init];
        chatVC.titleName = @"客服";
        chatVC.appName = @"客服SDK";
//        TOSClientKitCommodityCardOption *option = [[TOSClientKitCommodityCardOption alloc] init];
//        option.subTitle = @"华为P40麒麟990 ";
//        option.descriptions = @"这是商品描述，啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦";
//        option.price = @"100.99";
//        option.time = @"2022/05/24 18:32";
//        option.img = @"https://img2.baidu.com/it/u=3019548648,4204913203&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500";
//        option.status = @"已到货";
//        option.extraInfo = @[@{@"name": @"订单号", @"value": @"1234567890"},
//                             @{@"name": @"产品类型", @"value": @"电子产品"},
//                             @{@"name": @"师傅"   , @"value": @"金师傅"},
//                             @{@"name": @"服务地区", @"value": @"北京市"},
//                             @{@"name": @"服务"   , @"value": @"满意"},
//                             @{@"name": @"师傅电话", @"value": @"12345678900"},
//                             @{@"name": @"订单状态", @"value": @"已完成"}];
//        option.url = @"https://www.baidu.com";
        
//        chatVC.commodityCardOption = option;
//        chatVC.quickEntryAllItems = @[@"快捷入口1",@"快捷入口2",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3",@"快捷入口3"];
        self.hidesBottomBarWhenPushed  = YES;
        [self.navigationController pushViewController:chatVC animated:YES];
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        NSLog(@"errorDes === %@",errorDes);
    } tokenIncorrect:^{
        NSLog(@"tokenIncorrect");
    }];
}

-(void)kefuAction1{
    /*访客初始化回调
     当APP端IM mqtt 连接就绪，主动通知服务端，APP端调用该接口后服务端
     就开始会话流程逻辑创建会话*/
//    DomainNameSave *domainName = [DomainNameSave shareDomainNameSave];
//    NSDictionary * params = @{};
//    if ([domainName.domainName isEqualToString:@"天润北京测试KT"]) {
//        params = @{
//            @"configENVString": @"KTTestEnv"
//        };
//    }
//    [[OnlineRequestManager sharedCustomerManager]visitorReadyWithDict:params
//                                                              success:^(NSString * _Nonnull mainUniqueId) {
//        NSLog(@"kefuAction mainUniqueId === %@",mainUniqueId);
//
//
//        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
//    }];
    ///
//    /// 文本框的内边距
    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_textContainerInset = UIEdgeInsetsMake(10, 10, 10, 10);
    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_backgroundColor = UIColor.whiteColor;

    /// 第二个不开启快捷区域
    [TOSKitCustomInfo shareCustomInfo].quickEntryAllItems = @[];
    /// 头像距离屏幕边缘的边距
//    [TOSKitCustomInfo shareCustomInfo].headMargin = 30.0f;

    [TOSKitCustomInfo shareCustomInfo].ChatBox_textview_placeholder = @"请输入问题";
//    [TOSKitCustomInfo shareCustomInfo].chatBox_Height = 90.0f;
    /// 文本框的默认提示内容距离输入框左边的距离
    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_placeholderMargin = 10.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_cornerRadius = 8.0f;
//    [TOSKitCustomInfo shareCustomInfo].chatBox_itemBottomSpacing = 4.0;
    [TOSKitCustomInfo shareCustomInfo].chatBox_talkText = @"按住 说话";
    [TOSKitCustomInfo shareCustomInfo].chatBox_talkHighlightedText = @"松开 结束";
    [TOSKitCustomInfo shareCustomInfo].chatBox_talkFont = [UIFont boldSystemFontOfSize:16.0f];
    /**  发送按钮的自定义     **/
    /// 关闭表情按钮和更多按钮
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotionButton_enable = YES;
    [TOSKitCustomInfo shareCustomInfo].chatBox_moreButton_enable = YES;
//    /// 打开发送按钮
//    [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton_enable = YES;
//    /// 设置自定义发送按钮的相关属性
//    [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton_cornerRadius = 40/2;
//    [TOSKitCustomInfo shareCustomInfo].chatBox_sendButtonSize = CGSizeMake(80, 40);
//    [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton_borderColor = UIColor.redColor;
//    [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton_HighlightedColor = UIColor.orangeColor;
//    [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton_borderWidth = 2.0f;
//
//    /// 设置自定义的发送按钮
    UIButton * btn = [[UIButton alloc] init];
    [btn setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"resendMessage"]] forState:UIControlStateNormal];
    [TOSKitCustomInfo shareCustomInfo].resendButton = btn;
    [TOSKitCustomInfo shareCustomInfo].resendButtonSize = CGSizeMake(20.0f, 20.0f);
    [TOSKitCustomInfo shareCustomInfo].bubbleMaxWidth = kWindowWidth - 150;
    [TOSKitCustomInfo shareCustomInfo].resendToBubblePadding = 4.0f;
    [TOSKitCustomInfo shareCustomInfo].bubblePadding = 10.0;
    // ChatLocationMessage
    [[TOSKitCustomInfo shareCustomInfo].customCellRegister removeAllObjects];
    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_maxRows = 5;
    [TOSKitCustomInfo shareCustomInfo].chatBox_talk_borderWidth = 0.5f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_talk_borderColor = [UIColor colorWithHexString:@"E8E8E8"];
//    [TOSKitCustomInfo shareCustomInfo].chat_send_voiceLabelToBubbleLeftX = 10.0f;
    [TOSKitCustomInfo shareCustomInfo].chat_send_voiceImageToBubbleRightX = 22.0;
    [TOSKitCustomInfo shareCustomInfo].chat_send_voiceImageToBubbleTop = 10.0f;
    [TOSKitCustomInfo shareCustomInfo].chat_send_voiceLabelToBubbleTop = 10.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_talk_backgroundColor = UIColor.clearColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_talk_backgroundHighlightedColor = [UIColor colorWithHexString:@"#B2B2B2"];
    [TOSKitCustomInfo shareCustomInfo].chatBox_talk_fontHighlightedColor = [UIColor colorWithHexString:@"#434343"];
    /// 语音录制HUD
    [TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordView = nil;
    /// 语音取消录制HUD
    [TOSKitCustomInfo shareCustomInfo].chatBox_voiceCancelRecordView = nil;
    /// 语音录制时间太短HUD
    [TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordSoShortView = nil;
    /// 语音录制时间太短HUD的显示时间
//    [TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordSoShortTime = 3.0f;

    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButton_cornerRadius = 4.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButton_image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_delete"]];
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButtonSize = CGSizeMake(49, 38);
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButtonOffset = CGPointZero;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButtonBackGroundColor = UIColor.whiteColor;
    /// 创建按钮的阴影Layer
    CALayer *shadowLayer = [CALayer layer];
    shadowLayer.shadowColor = [UIColor blackColor].CGColor;
    shadowLayer.shadowOpacity = 0.1;
    shadowLayer.shadowOffset = CGSizeMake(0, 4);
    shadowLayer.shadowRadius = 4.0;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteCALayer = shadowLayer;
    /// 创建按钮的阴影Layer
    CALayer *sendshadowLayer = [CALayer layer];
    sendshadowLayer.shadowColor = [UIColor clearColor].CGColor;
    sendshadowLayer.shadowOffset = CGSizeMake(2, 5);
    sendshadowLayer.shadowOpacity = 0.5;
    sendshadowLayer.shadowRadius = 19.0;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendCALayer = shadowLayer;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButton_text = @"发送";
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButtonSize = CGSizeMake(49, 38);
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButton_textColor = UIColor.whiteColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButton_cornerRadius = 4.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButtonBackGroundColor = [UIColor colorWithHexString:@"#4385FF"];
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButtonMargins = UIEdgeInsetsMake(0, 0, 16, 16);

    [TOSKitCustomInfo shareCustomInfo].chatBox_topLineHeight = 1.0f;
    [TOSKitCustomInfo shareCustomInfo].ChatBox_lineColor = [UIColor colorWithHexString:@"E8E8E8"];
    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_borderWidth = 0.5f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_borderColor = UIColor.clearColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_Item_Width = 28;
//    [TOSKitCustomInfo shareCustomInfo].Chat_visitorName_enable = YES;
//    [TOSKitCustomInfo shareCustomInfo].Chat_tosRobotName_enable = YES;
    [TOSKitCustomInfo shareCustomInfo].lastMessage_spacing = 0.0f;
    [TOSKitCustomInfo shareCustomInfo].cellMargin = 10.0f;
    [TOSKitCustomInfo shareCustomInfo].nickNameToBubbleSpacing = 0.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBubble_CornerType = BubbleCornerTypeAll;

    [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets =  UIEdgeInsetsMake(5, 20, 5, 20);
    [TOSKitCustomInfo shareCustomInfo].chatMessage_system_backgroundColor = [[UIColor colorWithHexString:@"#FFFFFF"] colorWithAlphaComponent:0.65];
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont = [UIFont systemFontOfSize:20];
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets = UIEdgeInsetsMake(10, 10, 10, 10);
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_system_center = NO;

    [TOSKitCustomInfo shareCustomInfo].ChatBox_backGroundColor = [UIColor colorWithHexString:@"#F1F1F8"];
    [TOSKitCustomInfo shareCustomInfo].senderBubble_cornerRadius = 8.0f;
    [TOSKitCustomInfo shareCustomInfo].receiveBubble_cornerRadius = 8.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_more_topLineColor = [UIColor colorWithHexString:@"E8E8E8"];
    [TOSKitCustomInfo shareCustomInfo].chatBox_more_topLineHeight = 1.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_more_itemCornerRadius = 12.0f;
//    [TOSKitCustomInfo shareCustomInfo].chatBox_more_itemBackgroundColor = UIColor.redColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_backgroundColor = [UIColor colorWithHexString:@"#F3F6F9"];
    [TOSKitCustomInfo shareCustomInfo].chatBox_more_backgroundColor = TOSColor(237, 237, 246);
    [TOSKitCustomInfo shareCustomInfo].chatBox_more_itemTextColor = [UIColor colorWithHexString:@"595959"];
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_topLineColor = [UIColor colorWithHexString:@"E8E8E8"];
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_topLineHeight = 1.0f;

    [TOSKitCustomInfo shareCustomInfo].chatBox_Height = 56.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_functionItemDisplayed = NO;
    
    [TOSKitCustomInfo shareCustomInfo].Chat_tosRobotName_show = NO;
    [TOSKitCustomInfo shareCustomInfo].Chat_tosRobotName_enable = NO;
    [TOSKitCustomInfo shareCustomInfo].Chat_visitorName_enable = NO;
    [TOSKitCustomInfo shareCustomInfo].Chat_visitorName_show = NO;
    
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_visitorName_font = [UIFont boldSystemFontOfSize:12.0];
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_visitorName_textColor = [UIColor colorWithHexString:@"#595959"];
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotName_font = [UIFont systemFontOfSize:12.0];
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotName_textColor = [UIColor colorWithHexString:@"#595959"];
//
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_visitorText_font = [UIFont systemFontOfSize:16.0];
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font = [UIFont systemFontOfSize:16.0];
//
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_titleFont = [UIFont fontWithName:@"PingFangSC-Regular" size:16.0];
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_subTitleFont = [UIFont fontWithName:@"PingFangSC-Regular" size:16.0];
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssueTitleFont = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssueTitleColor = [UIColor colorWithHexString:@"#262626"];
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentTextColor = [UIColor colorWithHexString:@"#4385FF"];
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentLineColor = [[UIColor colorWithHexString:@"#000000"] colorWithAlphaComponent:0.04f];
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitleColor = [UIColor colorWithHexString:@"#4385FF"];
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitle = @"换一换";
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber = 5;
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitleFont = [UIFont fontWithName:@"PingFangSC-Regular" size:12.f];
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentFont = [UIFont fontWithName:@"PingFangSC-Regular" size:14.0f];
    
    
    //创建会话成功，进入聊天页面
    ChatInfoViewController *chatVC = [[ChatInfoViewController alloc] init];
    chatVC.titleName = @"客服";
    chatVC.appName = @"客服SDK";
    self.hidesBottomBarWhenPushed  = YES;
    [self.navigationController pushViewController:chatVC animated:YES];
    
    LoginModel *model = [LoginModel loginModel];
    NSLog(@"model.userId =========== %@",model.userId);
    TOSConnectOption * connectOption = [[TOSConnectOption alloc] initWithOption:model.userId?:@""
                                                                       nickname:model.nickname?:@""
                                                                        headUrl:model.headerUrl?:@""
                                                                         mobile:model.phoneNumber?:@""
                                                                  advanceParams:model.advanceParams?:@{}];
    [[TOSClientKit sharedTOSKit] connect:connectOption success:^{
        
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        NSLog(@"errorDes === %@",errorDes);
    } tokenIncorrect:^{
        NSLog(@"tokenIncorrect");
    }];
    
    
//    TOSClientKitCommodityCardOption *option = [[TOSClientKitCommodityCardOption alloc] init];
//    option.subTitle = @"华为P40麒麟990 5G SoC芯片 5000万超感知徕卡三摄 30倍数字变焦";
//    option.descriptions = @"这是商品描述，啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦";
//    option.price = @"100.99";
//    option.time = @"2022/05/24 18:32";
//    option.img = @"https://img2.baidu.com/it/u=3019548648,4204913203&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500";
//    option.status = @"已到货";
//    option.extraInfo =
//    @[@{@"name": @"订单号", @"value": @"1234567890"},
//    @{@"name": @"产品类型", @"value": @"电子产品"},
//    @{@"name": @"师傅"   , @"value": @"金师傅"},
//    @{@"name": @"服务地区", @"value": @"北京市"},
//    @{@"name": @"服务"   , @"value": @"满意"},
//    @{@"name": @"师傅电话", @"value": @"12345678900"},
//    @{@"name": @"订单状态", @"value": @"已完成"}];
//    
//    chatVC.commodityCardOption = option;
//    self.hidesBottomBarWhenPushed  = YES;
//    [self.navigationController pushViewController:chatVC animated:YES];
    
}

/// 集度的UI改造
- (void)jidukefuAction {
    
    /// 文本框的内边距
//    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_textContainerInset = UIEdgeInsetsMake(10, 10, 10, 10);
    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_backgroundColor = UIColor.cyanColor;
    
    /// 第二个不开启快捷区域
    [TOSKitCustomInfo shareCustomInfo].quickEntryAllItems = @[];
    /// 头像距离屏幕边缘的边距
//    [TOSKitCustomInfo shareCustomInfo].headMargin = 30.0f;
    
    [TOSKitCustomInfo shareCustomInfo].ChatBox_textview_placeholder = @"买石灰街车站的海鸥 山水禽兽与年少一梦 买太平湖底陈年水墨 哥本哈根的童年传说";
//    [TOSKitCustomInfo shareCustomInfo].chatBox_Height = 90.0f;
    /// 文本框的默认提示内容距离输入框左边的距离
    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_placeholderMargin = 20.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_cornerRadius = 8.0f;
//    [TOSKitCustomInfo shareCustomInfo].chatBox_itemBottomSpacing = 4.0;
    [TOSKitCustomInfo shareCustomInfo].chatBox_talkText = @"按我试试？";
    [TOSKitCustomInfo shareCustomInfo].chatBox_talkHighlightedText = @"快松开我";
    [TOSKitCustomInfo shareCustomInfo].chatBox_talkFont = [UIFont systemFontOfSize:22];
    /**  发送按钮的自定义     **/
    /// 关闭表情按钮和更多按钮
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotionButton_enable = YES;
    [TOSKitCustomInfo shareCustomInfo].chatBox_moreButton_enable = YES;
//    /// 打开发送按钮
//    [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton_enable = YES;
//    /// 设置自定义发送按钮的相关属性
//    [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton_cornerRadius = 40/2;
//    [TOSKitCustomInfo shareCustomInfo].chatBox_sendButtonSize = CGSizeMake(80, 40);
//    [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton_borderColor = UIColor.redColor;
//    [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton_HighlightedColor = UIColor.orangeColor;
//    [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton_borderWidth = 2.0f;
//
//    /// 设置自定义的发送按钮
    UIButton * btn = [[UIButton alloc] init];
    [btn setImage:[UIImage imageNamed:@"resendIcon"] forState:UIControlStateNormal];
//    [btn setBackgroundImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"resendMessage"]] forState:(UIControlStateNormal)];
    btn.imageEdgeInsets = UIEdgeInsetsMake(0, 0, 0, 20);
    btn.titleLabel.font = [UIFont systemFontOfSize:12];
    [TOSKitCustomInfo shareCustomInfo].resendButton = btn;
    [TOSKitCustomInfo shareCustomInfo].resendButtonSize = CGSizeMake(80, 20);
    [TOSKitCustomInfo shareCustomInfo].resendToBubblePadding = 10.0f;
    [TOSKitCustomInfo shareCustomInfo].bubblePadding = 12.0;
    /// 自定义排队cell
//    [[TOSKitCustomInfo shareCustomInfo].customCellRegister setValue:[customTableViewCell class] forKey:@"GXSystem"];
    // ChatLocationMessage
    [[TOSKitCustomInfo shareCustomInfo].customCellRegister setValue:[customTableViewCell class] forKey:@"TypeEventQueue"];
    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_maxRows = 1;
    [TOSKitCustomInfo shareCustomInfo].chatBox_talk_borderWidth = 2.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_talk_borderColor = UIColor.redColor;
//    [TOSKitCustomInfo shareCustomInfo].chat_send_voiceLabelToBubbleLeftX = 10.0f;
    [TOSKitCustomInfo shareCustomInfo].chat_send_voiceImageToBubbleRightX = 40.0;
    [TOSKitCustomInfo shareCustomInfo].chat_send_voiceImageToBubbleTop = 10.0f;
    [TOSKitCustomInfo shareCustomInfo].chat_send_voiceLabelToBubbleTop = 10.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_talk_backgroundColor = UIColor.redColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_talk_backgroundHighlightedColor = UIColor.yellowColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_talk_fontHighlightedColor = UIColor.blueColor;
    /// 语音录制HUD
    UIView * recordV = [[UIView alloc] initWithFrame:(CGRectMake(UIScreen.mainScreen.bounds.size.width/2-50, UIScreen.mainScreen.bounds.size.height/2-50, 100, 100))];
    recordV.backgroundColor = UIColor.orangeColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordView = recordV;
    /// 语音取消录制HUD
    UIView * cancelRecordV = [[UIView alloc] initWithFrame:(CGRectMake(UIScreen.mainScreen.bounds.size.width/2-50, UIScreen.mainScreen.bounds.size.height/2-50, 100, 100))];
    cancelRecordV.backgroundColor = UIColor.redColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_voiceCancelRecordView = cancelRecordV;
    /// 语音录制时间太短HUD
    UIView * shortV = [[UIView alloc] initWithFrame:(CGRectMake(UIScreen.mainScreen.bounds.size.width/2-50, UIScreen.mainScreen.bounds.size.height/2-50, 100, 50))];
    shortV.backgroundColor = UIColor.blackColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordSoShortView = shortV;
    /// 语音录制时间太短HUD的显示时间
//    [TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordSoShortTime = 3.0f;
    
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButton_cornerRadius = 18.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButton_image = [UIImage imageNamed:@"emotion_delete"];
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButtonSize = CGSizeMake(68, 36);
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButtonOffset = CGPointMake(30, 0);
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButtonBackGroundColor = UIColor.whiteColor;
    /// 创建按钮的阴影Layer
    CALayer *shadowLayer = [CALayer layer];
    shadowLayer.shadowColor = [UIColor blackColor].CGColor;
    shadowLayer.shadowOpacity = 0.1;
    shadowLayer.shadowOffset = CGSizeMake(0, 4);
    shadowLayer.shadowRadius = 4.0;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteCALayer = shadowLayer;
    /// 创建按钮的阴影Layer
    CALayer *sendshadowLayer = [CALayer layer];
    sendshadowLayer.shadowColor = [UIColor orangeColor].CGColor;
    sendshadowLayer.shadowOffset = CGSizeMake(2, 5);
    sendshadowLayer.shadowOpacity = 0.5;
    sendshadowLayer.shadowRadius = 18.0;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendCALayer = shadowLayer;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButton_text = @"单车";
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButtonSize = CGSizeMake(68, 36);
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButton_textColor = UIColor.grayColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButton_cornerRadius = 18.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButtonBackGroundColor = [UIColor colorWithHexString:@"#824DFC"];
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButtonMargins = UIEdgeInsetsMake(0, 0, 8, 20);
    
    [TOSKitCustomInfo shareCustomInfo].chatBox_topLineHeight = 2.0f;
    [TOSKitCustomInfo shareCustomInfo].ChatBox_lineColor = UIColor.orangeColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_borderWidth = 2.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_borderColor = UIColor.yellowColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_Item_Width = 32;
//    [TOSKitCustomInfo shareCustomInfo].Chat_visitorName_enable = YES;
//    [TOSKitCustomInfo shareCustomInfo].Chat_tosRobotName_enable = YES;
    [TOSKitCustomInfo shareCustomInfo].lastMessage_spacing = 20.0f;
//    [TOSKitCustomInfo shareCustomInfo].cellMargin = 20.0f;
    [TOSKitCustomInfo shareCustomInfo].nickNameToBubbleSpacing = 10.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBubble_CornerType = BubbleCornerTypeNoRightTop;
    
    [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets = UIEdgeInsetsMake(5, 30, 10, 30);
    [TOSKitCustomInfo shareCustomInfo].chatMessage_system_backgroundColor = UIColor.clearColor;
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont = [UIFont systemFontOfSize:13];
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets = UIEdgeInsetsMake(10, 10, 10, 10);
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_system_center = NO;
    
    [TOSKitCustomInfo shareCustomInfo].ChatBox_backGroundColor = [UIColor colorWithHexString:@"#F2F2F7"];
    [TOSKitCustomInfo shareCustomInfo].senderBubble_cornerRadius = 20.0f;
    [TOSKitCustomInfo shareCustomInfo].receiveBubble_cornerRadius = 20.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_more_topLineColor = UIColor.orangeColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_more_topLineHeight = 2.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_more_itemCornerRadius = 48.0f;
//    [TOSKitCustomInfo shareCustomInfo].chatBox_more_itemBackgroundColor = UIColor.redColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_backgroundColor = UIColor.yellowColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_more_backgroundColor = UIColor.cyanColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_more_itemTextColor = UIColor.orangeColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_topLineColor = UIColor.orangeColor;
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_topLineHeight = 2.0f;
    
    [TOSKitCustomInfo shareCustomInfo].chatBox_Height = 60.0f;
    [TOSKitCustomInfo shareCustomInfo].chatBox_textView_textContainerInset = UIEdgeInsetsMake(8, 10, 8, 10);
    [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_functionItemDisplayed = YES;
    
    
    [TOSKitCustomInfo shareCustomInfo].Chat_tosRobotName_show = YES;
    [TOSKitCustomInfo shareCustomInfo].Chat_tosRobotName_enable = YES;
    [TOSKitCustomInfo shareCustomInfo].Chat_visitorName_enable = YES;
    [TOSKitCustomInfo shareCustomInfo].Chat_visitorName_show = YES;
    
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_visitorName_font = [UIFont boldSystemFontOfSize:18];
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_visitorName_textColor = UIColor.blackColor;
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotName_font = [UIFont systemFontOfSize:28];
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotName_textColor = UIColor.redColor;
//    
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_visitorText_font = [UIFont systemFontOfSize:20.0];
//    [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font = [UIFont systemFontOfSize:22.0];
//    
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_titleFont = [UIFont systemFontOfSize:30.0];
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_subTitleFont = [UIFont systemFontOfSize:22.0];
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssueTitleFont = [UIFont systemFontOfSize:18.0];
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssueTitleColor = UIColor.redColor;
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentTextColor = UIColor.cyanColor;
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentLineColor = UIColor.blackColor;
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitleColor = UIColor.blackColor;
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitle = @"小伙子，你完了";
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber = 3;
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitleFont = [UIFont systemFontOfSize:22.0];
//    TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentFont = [UIFont systemFontOfSize:10.0f];
    
    
    
    
    //创建会话成功，进入聊天页面
    ChatInfoViewController *chatVC = [[ChatInfoViewController alloc] init];
    chatVC.titleName = @"客服";
    chatVC.appName = @"客服SDK";
    chatVC.isRewriteClose = YES;
    self.hidesBottomBarWhenPushed  = YES;
    [self.navigationController pushViewController:chatVC animated:YES];
    
    LoginModel *model = [LoginModel loginModel];
    NSLog(@"model.userId =========== %@",model.userId);
    TOSConnectOption * connectOption = [[TOSConnectOption alloc] initWithOption:model.userId?:@""
                                                                       nickname:@"ios_jidutest"
                                                                        headUrl:model.headerUrl?:@""
                                                                         mobile:model.phoneNumber?:@""
                                                                  advanceParams:model.advanceParams?:@{}];
    [[TOSClientKit sharedTOSKit] connect:connectOption success:^{
        
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        NSLog(@"errorDes === %@",errorDes);
    } tokenIncorrect:^{
        NSLog(@"tokenIncorrect");
    }];
    
    
}


- (UILabel *)unReadInfoLabel {
    if (!_unReadInfoLabel) {
        _unReadInfoLabel = [[UILabel alloc] initWithFrame:(CGRectMake(0, 250, self.view.bounds.size.width, 50))];
        _unReadInfoLabel.textAlignment = 1;
        
    }
    return _unReadInfoLabel;
}

@end
