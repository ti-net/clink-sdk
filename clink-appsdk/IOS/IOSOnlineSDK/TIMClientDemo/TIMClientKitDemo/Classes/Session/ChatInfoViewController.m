//
//  ChatInfoViewController.m
//  TIMClientKitDemo
//
//  Created by 赵言 on 2021/4/29.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "ChatInfoViewController.h"

@interface ChatInfoViewController ()

@end

@implementation ChatInfoViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
//    [self didToCustomerServiceAction];
    
    //断开链接
//        TIMDisConnectOption * disOption = [[TIMDisConnectOption alloc] initWithOption:YES];
//        [[TIMClient sharedTIMClient] disconnect:disOption success:^{
//            
//        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
//            
//        }];
}

//连接客服
- (void)didToCustomerServiceAction {
 
 //连接客服
    __weak __typeof(self) weakSelf = self;
    [[OnlineRequestManager sharedCustomerManager] getUserInfoWithUserId:@"10110006341"//@"fagag"//@"486664"
                                                               nickname:@"钟高荣"//@"测试"
                                                               phoneNum:@"13164707736"//@"13688889999"
                                                              headerUrl:@""//@"https://img2.baidu.com/it/u=468449452,4173225115&fm=26&fmt=auto"
                                                         connectSuccess:^{
        NSLog(@"链接成功");
        [weakSelf connectToCustomerServiceAction];
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
//        [WCUtils showToastWithMessage:@"网络请求错误，请检查网络"];
//        [[HudHelper getInstance] hideHud];
    } tokenIncorrect:^{
//        [WCUtils showToastWithMessage:@"accessToken不正确"];
//        [[HudHelper getInstance] hideHud];
    }];
    
    //本地kit一些配置
    [TOSClientKit sharedTOSKit].disableMessageNotificaiton = YES;  // 取消本地推送
    [TOSClientKit sharedTOSKit].disableMessageAlertSound = YES;  // 取消本地推送声音
//    [TIMKit sharedTIMKit].auditMessageSuccessDelagate = self;
//    [TIMKit sharedTIMKit].customMessageClickDelagate = self;
//    [TIMKit sharedTIMKit].rtcMediaMessageDelagate = self;
//    [TIMKit sharedTIMKit].customerKitUIType = TIMKitUIRDSA20Type;

}

- (void)connectToCustomerServiceAction {
    /*访客初始化回调
     当APP端IM mqtt 连接就绪，主动通知服务端，APP端调用该接口后服务端
     就开始会话流程逻辑创建会话*/
    __weak typeof(self) weakSelf = self;
    
    [[OnlineRequestManager sharedCustomerManager] visitorReadyWithDict:@{@"ctiy":@"深圳市"} success:^(TOSSessionInfoModel * _Nonnull sessModel) {
        //创建会话成功，进入聊天页面
        weakSelf.titleName = @"客服";
        weakSelf.appName = @"客服SDK";
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        
    }];
}


@end
