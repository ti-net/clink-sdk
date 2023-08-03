//
//  ChatInfoViewController.m
//  TIMClientKitDemo
//
//  Created by 赵言 on 2021/4/29.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "ChatInfoViewController.h"
#import "STBaseWebViewController.h"
#import "customTableViewCell.h"
#import "customTypeEventQueueTableViewCell.h"
#import "CustomRefreshHeader.h"

@interface ChatInfoViewController ()<TIMOnlineQueueDelegate>

@end

@implementation ChatInfoViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [[TOSClientKit sharedTOSKit] setTIMOnlineQueueDelegate:self];
    
    TOSKitExtendBoardItemModel *model1 = [[TOSKitExtendBoardItemModel alloc] init];
    model1.type = TOSChatBoxExtendBoardTypePhotos;
    model1.title = @"";
    model1.image = @"";
    model1.index = 1;
    
    TOSKitExtendBoardItemModel *model2 = [[TOSKitExtendBoardItemModel alloc] init];
    model2.type = TOSChatBoxExtendBoardTypeTakePicture;
    model2.title = @"";
    model2.image = @"";
    model2.index = 2;
    
    TOSKitExtendBoardItemModel *model3 = [[TOSKitExtendBoardItemModel alloc] init];
    model3.type = TOSChatBoxExtendBoardTypeCustomFile;
    model3.title = @"";
    model3.image = @"";
    model3.index = 3;
    
    TOSKitExtendBoardItemModel *model4 = [[TOSKitExtendBoardItemModel alloc] init];
    model4.type = TOSChatBoxExtendBoardTypeArtificial;
    model4.title = @"";
    model4.image = @"";
    model4.index = 4;
    
    TOSKitExtendBoardItemModel *model5 = [[TOSKitExtendBoardItemModel alloc] init];
    model5.type = TOSChatBoxExtendBoardTypeCloseChat;
    model5.title = @"";
    model5.image = @"";
    model5.index = 5;
    
    NSMutableArray *array = [NSMutableArray arrayWithArray:@[model1,model2,model3,model4,model5,model1,model2,model3,model4,model5,model1,model2,model3,model4,model5,model1,model2,model3,model4,model5,model1,model2,model3,model4,model5,model1,model2,model3,model4,model5,model1,model2,model3,model4,model5]];
    
    [TOSKitChatBoxExtendBoard shareChatBoxExtendBoard].allItems = array;
    /// 防止循环引用需要__weak
    __weak typeof(self) weakself = self;
    self.customRefreshHeader = [CustomRefreshHeader headerWithRefreshingBlock:^{
        __strong typeof(self) strongself = weakself;
        [strongself loadMoreMessage];
    }];
    
//    [self didToCustomerServiceAction];
    
    //断开链接
//        TOSDisConnectOption * disOption = [[TOSDisConnectOption alloc] initWithOption:YES];
//        [[TIMClient sharedTIMClient] disconnect:disOption success:^{
//            
//        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
//            
//        }];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
}

- (void)viewDidDisappear:(BOOL)animated {
    [super viewDidDisappear:animated];
    
    TOSSessionInfoModel *model = [[TOSClientKit sharedTOSKit] getCurrentSessionInfo];
    NSLog(@"model === %@",[model yy_modelToJSONObject]);
}

- (void)didClinkCustomExtendBoardItemAction:(TOSKitExtendBoardItemModel *)item {
    NSLog(@"%@",[item yy_modelToJSONObject]);
}

- (void)bariItemDidTouchIndex:(NSInteger)index {
//    [super bariItemDidTouchIndex:index];
    
    NSLog(@"bariItemDidTouchIndex       %ld", index);
    
}

- (void)quickEntryItemDidTouchIndex:(NSInteger)index {
    NSLog(@"点击了快捷按钮的第%ld个", index);
}

- (void)chatStatusChanged:(TinetChatStatusType)status {
    NSLog(@"chatStatusChanged       %ld", status);
}

- (void)closeViewEvent {
    NSLog(@"重写了结束会话的功能事件");
    if (self.isRewriteClose) {
        
    }
    else {
        [super closeViewEvent];
    }
    
    
}

- (void)tableViewContentOffset:(CGPoint)contentOffset withMessageHeight:(CGFloat)messageHeight {
    NSLog(@"Horizontal offset: %f, Vertical offset: %f, 消息列表的高度：%f", contentOffset.x, contentOffset.y, messageHeight);
}


#pragma mark - TIMOnlineQueueDelegate

- (void)chatBridge:(ChatBridgeMessage *)message {
    NSLog(@"排队结束");
}

- (void)chatQueue:(ChatQueueMessage *)message {
    NSLog(@"放弃排队是否启用：%ld", (long)message.abandonEnabled);
    NSLog(@"进入排队");
}

- (void)chatQueueLocation:(ChatLocationMessage *)message {
    NSLog(@"排队播报");
}

- (void)exitChatQueue {
    NSLog(@"退出排队");
}


//- (void)tinet_textMessageClickAction:(TinetClickTextMessageEventType)eventType userInfo:(NSDictionary *)userInfo {
//    /// 超链接点击事例
//    if (eventType == TinetClickEventTypeUrl) {
//        STBaseWebViewController * webView = [[STBaseWebViewController alloc] init];
//        webView.urlString = [NSString stringWithFormat:@"%@", userInfo[@"content"]];
//        [self.navigationController pushViewController:webView animated:YES];
//    }
//    
//    if (eventType == TinetClickMiniProgramCard) {
//        STBaseWebViewController * webView = [[STBaseWebViewController alloc] init];
//        webView.urlString = [NSString stringWithFormat:@"%@", userInfo[@"content"][@"pagepath"]?:@""];
//        [self.navigationController pushViewController:webView animated:YES];
//    }
//    
//    if (eventType == TinetClickLogisticsCard) {
//        STBaseWebViewController * webView = [[STBaseWebViewController alloc] init];
//        webView.urlString = [NSString stringWithFormat:@"%@", userInfo[@"content"][@"orderLink"]?:@""];
//        [self.navigationController pushViewController:webView animated:YES];
//    }
//    
//    [self showMBErrorView:[NSString stringWithFormat:@"%ld = %@",eventType,userInfo]];
//}

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

}


- (TOSChatCustomBaseTableViewCell *)customTableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath withModel:(nonnull TIMMessageModel *)model {
    TOSChatCustomBaseTableViewCell * cell ;
    
    NSLog(@"消息的内容： %@   messageId : %@  type : %@", model.message.content, model.message.messageId, model.message.type);
    if ([model.message.type isEqualToString:@"GXText"]) {
        customTableViewCell * customCell = [tableView dequeueReusableCellWithIdentifier:@"customTableViewCell"];
        if (!customCell) {
            customCell = [[customTableViewCell alloc] initWithStyle:(UITableViewCellStyleDefault) reuseIdentifier:@"customTableViewCell"];
        }
        customCell.textLabel.text = model.message.content;
        return customCell;
    }
    if ([model.message.type isEqualToString:@"TypeEventQueue"]) {
        customTypeEventQueueTableViewCell * customCell = [tableView dequeueReusableCellWithIdentifier:@"customTableViewCell"];
        if (!customCell) {
            customCell = [[customTypeEventQueueTableViewCell alloc] initWithStyle:(UITableViewCellStyleDefault) reuseIdentifier:@"customTableViewCell"];
        }
        customCell.textLabel.text = model.message.content;
        return customCell;
    }
    return cell;
}

@end
