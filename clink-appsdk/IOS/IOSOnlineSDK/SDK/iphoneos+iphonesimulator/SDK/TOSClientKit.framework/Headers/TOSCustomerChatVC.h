//
//  TOSCustomerChatVC.h
//  TIMClientKit
//
//  Created by apple on 2021/8/23.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <TOSClientLib/TIMSession.h>
#import <TOSClientLib/TOSClientKitCommodityCardOption.h>


NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(NSUInteger, TinetClickTextMessageEventType) {
    TinetClickEventTypeUrl,
    TinetClickEventTypeOrderNumber,
    TinetClickEventTypePhone,
    TinetClickCommodityCard,
};

typedef NS_ENUM(NSUInteger, TinetChatStatusType) {
    TinetChatStatusTypeOutline,   // 不在线或结束会话
    TinetChatStatusTypeRobot,     // 机器人在线
    TinetChatStatusTypeOnline,    // 客服在线
};

@interface TOSCustomerChatVC : UIViewController

@property (nonatomic, strong) TOSClientKitCommodityCardOption *commodityCardOption;

// 会话
@property (nonatomic, strong) TIMSession *session;

/// 自定义欢迎语
@property(nonatomic, copy) NSString *welcomsString;

/// 设置常用语
/// @param commonWords 常用语数组
- (void)setupCommonWords:(NSArray <NSString *>*)commonWords;

/// 文本类型消息中关于链接、单号和手机号的相关点击回调
/// @param eventType 事件类型
/// @param userInfo 信息
- (void)tinet_textMessageClickAction:(TinetClickTextMessageEventType)eventType userInfo:(NSDictionary *)userInfo;

//此方法获取文件保存得到的文件路径
-(NSString*)saveFileMethed;


/*以下是会话聊天必须参数*/
//标题名字
@property(nonatomic, copy) NSString *titleName;
//接入号名称
@property(nonatomic, copy) NSString *appName;

/// 快捷入口的数据
@property (nonatomic, strong) NSArray                * barItemDataArray;

/// 快捷入口的点击回调    index    点击索引从0开始
- (void)bariItemDidTouchIndex:(NSInteger)index;

/// 当前会话状态监听
- (void)chatStatusChanged:(TinetChatStatusType)status;

@end

NS_ASSUME_NONNULL_END
