//
//  TOSCustomerChatVC.h
//  TIMClientKit
//
//  Created by apple on 2021/8/23.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <TOSClientKit/TOSBaseViewController.h>
#import <TOSClientLib/TOSClientKitCommodityCardOption.h>
#import <TOSClientKit/TOSKitExtendBoardItemModel.h>

#import <TOSClientKit/TIMMessageModel.h>
#import <TOSClientKit/TOSChatCustomBaseTableViewCell.h>
#import <TOSClientKit/TIMRefresh.h>

NS_ASSUME_NONNULL_BEGIN
@class TOSQuickEntryModel;

typedef NS_ENUM(NSUInteger, TinetClickTextMessageEventType) {
    TinetClickEventTypeUrl,
    TinetClickEventTypeOrderNumber,
    TinetClickEventTypePhone,
    TinetClickCommodityCard,
    TinetClickMiniProgramCard,
    TinetClickLogisticsCard,
};

//typedef NS_ENUM(NSUInteger, TinetChatStatusType) {
//    TinetChatStatusTypeOutline,   // 不在线或结束会话
//    TinetChatStatusTypeRobot,     // 机器人在线
//    TinetChatStatusTypeOnline,    // 客服在线
//};

@class TIMChatBoxViewController;

@interface TOSCustomerChatVC : TOSBaseViewController

/// 标题名字
@property(nonatomic, copy) NSString *titleName;

/// 接入号名称
@property(nonatomic, copy) NSString *appName;

/// 快捷入口的数据
@property (nonatomic, strong) NSArray <TOSQuickEntryModel *>*quickEntryAllItems;

/// 商品卡片配置数据
@property (nonatomic, strong) TOSClientKitCommodityCardOption *commodityCardOption;

/// 自定义欢迎语
@property(nonatomic, copy) NSString *welcomsString;

/// 自定义顶部刷新控件, 重写这个组件的回调方法要调用 loadMoreMessage 方法，（block回调需要注意循环引用问题）
@property (nonatomic, strong) TIMRefreshHeader                * customRefreshHeader;

/// 文本类型消息中关于链接、单号和手机号的相关点击回调
/// @param eventType 事件类型
/// @param userInfo 信息
- (void)tinet_textMessageClickAction:(TinetClickTextMessageEventType)eventType userInfo:(NSDictionary *)userInfo;

//此方法获取文件保存得到的文件路径
//-(NSString*)saveFileMethed;

/// 快接入口数据更新方法
- (void)updateSessionWindowQuickEntrys:(NSArray <TOSQuickEntryModel *>*)quickEntryAllItems;

/// 快捷入口的点击事件（需要在子类实现这个方法）
- (void)quickEntryItemDidTouchModel:(TOSQuickEntryModel *)model;

/// 当前会话状态监听
- (void)chatStatusChanged:(TinetChatStatusType)status;

/// 扩展面板，自定义按钮事件 （需要在子类实现这个方法）
- (void)didClinkCustomExtendBoardItemAction:(TOSKitExtendBoardItemModel *)item;

/// 结束会话事件
- (void)closeViewEvent;

/// 自定义cell要实现这个方法，要在实现该方法前，配置 [TOSKitCustomInfo shareCustomInfo].customCellRegister
- (TOSChatCustomBaseTableViewCell *)customTableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath withModel:(TIMMessageModel *)model;


/// 自定义refresh组件回调需要调用的方法
- (void)loadMoreMessage;

/// 当前列表的滑动高度，子类重写该方法可以获取当前列表的滑动高度
/// - Parameters:
///   - contentOffset: 当前列表的滑动范围
///   - messageHeight: 消息列表的高度
- (void)tableViewContentOffset:(CGPoint)contentOffset withMessageHeight:(CGFloat)messageHeight;

/// 发送商品卡片消息
- (void)sendCard:(TOSClientKitCommodityCardOption *)userInfo;

/// 发送文本消息
- (void)sendText:(NSString *)text;

/// 发送转人工事件
/// @param messageStr 自定义文本消息
- (void)sendTransferToHumanMessage:(NSString *)messageStr;

/// 重写返回事件，是否弹出满意度弹窗
- (void)investigationAlert;

- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
             sendImageMessage:(UIImage *)image
                    imagePath:(NSString *)imgPath;

- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
             sendVideoMessage:(NSString *)videoPath
                     duration:(CGFloat)videoTimeLength
           thumbnailImagePath:(NSString *)thumbnailImagePath;

@end

NS_ASSUME_NONNULL_END
