

## TOSClinetKit_iOS_开发文档

> 版本号: v1.5.5+

[TOC]



## 概述

> 欢迎使用“智慧服务平台”的在线客服移动端开发者工具套件(SDK)。本文针对iOS端使用做详细说明，通过SDK，可以在您的APP中快速集成访客端在线聊天的功能，以具备文本，图片、视频等类型消息收发及消息通知能力，

## 集成准备
#### 注册账号
请先确保已注册天润在线客服账号

#### 开发环境
- 适配iOS 10.0以上
- 开发工具：Xcode 13
#### 下载天润在线客服SDK    
最新SDK包链接地址:
[iOS_SDK](https://github.com/ti-net/clink-sdk/tree/master/clink-appsdk/IOS/IOSOnlineSDK/SDK)     

#### SDK文件说明

解压下载【iOS_SDK】,下载完为一个压缩包，包含（<font color=Crimson size=2>TOSCilentLib.framework</font>、<font color=Crimson size=2>TOSCilentKit.framework</font>、<font color=Crimson size=2>TOSClinet.bundle</font>)和相关说明文档。


 文件名	| 说明
------------- | ------------  
<font color=Crimson size=2>TOSClinetKit.framework</font>|	SDK UI Kit
<font color=Crimson size=2>TOSClient.bundle</font>|	图片资源文件 表情资源文件
<font color=Crimson size=2>TOSCilentLib.framework	</font>|SDK Lib

#### 自动导入

1. `cd` 至项目根目录。

2. 执行 `vim podfile`。

3. 在 `Podfile` 文件中，添加以下内容

   ```objective-c
   pod 'TOSClientKit'
   ```

4. 执行 `pod install`。如果出现找不到相关版本的问题，可先执行 `pod repo update` ，再执行 `pod install`。

#### 手动导入SDK和添加依赖库

把下载的TOSClinetKit.framework 、TOSClientLib.framework、TOSClient.bundle文件夹中的文件拖入你的工程里。
天润iOS_SDK 的实现，依赖了一些系统的框架，在开发应用时需要在工程里加入这些框架 ( TOSClientLib不包含UI界面，可根据接口自行开发功能 ) 。开发者首先点击工程右边的工程名，然后在工程名右边依次选择<font color= orange size=2>TARGETS -> BuiLd Phases -> Link Binary With Libraries</font>，展开 <font color= orange size=2>LinkBinary With Libraries</font> 后点击展开后下面的 + 来添加下面的依赖项:  

- TOSClientLib.framework(Embed & Sign)
- TOSClientKit.framework(Embed & Sign)
- TOSClient.bundle
- 另外需要增加libc++.tbd来支持c++环境

#### 配置需要的权限

> 相机、麦克风、相册等权限
 - Privacy - Camera Usage Description
 - Privacy - Microphone Usage Description
 - Privacy - Photo Library Additions Usage Description
 - Privacy - Photo Library Usage Description

#### 注意事项

> 注意：
>
> 1. 在APP的 Info.plist 文件中增加 CFBundleDisplayName，value值为APP名称
> 2. 在会话页面中关闭键盘管理的第三方控件，如：IQKeyboardManager
> 3. 如有需要，可在跳转会话页面前设置会话页面的titleName属性，修改顶部标题

## 快速集成
#### 启动SDK
天润SDK提供了一套完整的聊天界面，帮助开发者快速集成，并提供自定义接口，以实现定制需求。只需以下几行代码便可快速启动天润SDK应用。

> 注: 请在链接客服服务成功后再调起会话页面

TOSInitOption 参数说明

| 参数          | 类型         | 必填 | 说明                                                         |
| ------------- | ------------ | ---- | ------------------------------------------------------------ |
| accessId      | NSString     | 是   | 访问标识(后台创建账号时获取,移动端唯一标识，对应座席端渠道ID) |
| accessSecret  | NSString     | 是   | 访问秘钥(后台创建账号时获取,在座席端管理平台创建渠道时生成)  |
| enterpriseId  | NSString     | 是   | 企业号(后台创建账号时获取,企业ID)                            |
| apiUrl        | NSString     | 是   | 平台apiUrl(接口环境平台,目前仅有正式1平台，即:https://octopus-api-1.vlink.cn/api/sdk/v1) |
| onlineUrl     | NSString     | 是   | 平台onlineUrl(客服环境平台,目前有上海和北京2个平台，即:https://chat-app-sh.clink.cn、 https://chat-app-bj.clink.cn) |
| debug         | BOOL         | 否   | 是否开启debug模式                                            |
| advanceParams | NSDictionary | 否   | 自定义可配参数,可为空                                        |

```objective-c
首先在AppDelegate中初始化SDK的Option
TOSInitOption * initOption = [[TOSInitOption alloc] initWithOption:YES 
                                   											  	apiUrl:@"平台apiUrl" 
                           											         onlineUrl:@"平台onlineUrl" 
                            											        accessId:@"移动端唯一标识，对应座席端渠道ID" 
                        										          accessSecret:@"在座席端管理平台创建渠道时生成" 
                       											          enterpriseId:@"企业ID" 
                        										         advanceParams:@{}];
[[TOSClientKit sharedTOSKit] initSDK:initOption];
```

#### 链接服务

##### 功能描述

连接在线客服，同一用户多次调用connect不会触发多次连接

TOSConnectOption 参数说明

| 参数          | 类型         | 必填 | 说明                                                         |
| ------------- | ------------ | ---- | ------------------------------------------------------------ |
| visitorId     | NSString     | 否   | 用户App的userID（为空的情况下，系统默认为UUID去除-号，不可包含中文或特殊符号(-)，建议使用用户系统ID方便APP拓展功能） |
| nickname      | NSString     | 否   | 昵称                                                         |
| headUrl       | NSString     | 否   | 头像地址                                                     |
| mobile        | NSString     | 否   | 手机号                                                       |
| advanceParams | NSDictionary | 否   | 自定义可配参数                                               |

```objective-c
TOSConnectOption * connectOption = [[TOSConnectOption alloc] initWithOption:@"用户App的userID" 
																																	 nickname:@"昵称" 
																																	  headUrl:@"头像地址" 
																														  			 mobile:@"手机号" 
																														  advanceParams:@{}];
[[TOSClientKit sharedTOSKit] connect:connectOption success:^{

    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
    
    } tokenIncorrect:^{
    
}];
```

#### 断开链接

##### 功能描述

断开连接在线客服，当用户退出登录时调用

TOSDisConnectOption 参数说明

| 参数          | 类型 | 必填 | 说明         |
| ------------- | ---- | ---- | ------------ |
| isReceivePush | BOOL | 是   | 是否需要推送 |

```objective-c
TOSDisConnectOption *disOption = [[TOSDisConnectOption alloc] initWithOption:YES];
[[TOSClientKit sharedTOSKit] disconnect:disOption success:^{

} error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
    
}];
```

#### 全局自定义配置

```objective-c
  // 本地kit一些配置
  [TOSClientKit sharedTOSKit].disableMessageNotificaiton = YES;  // 取消本地推送
  [TOSClientKit sharedTOSKit].disableMessageAlertSound = YES;    // 取消本地推送声音
```

#### 获取版本号

```objective-c
// 获取版本号
[TOSClientKit getSDKVersion];
```

## 会话相关

#### 获取会话信息

TOSSessionInfoModel 参数说明

| 参数         | 类型     | 必填 | 说明                                    |
| ------------ | -------- | ---- | --------------------------------------- |
| enterpriseId | NSString | 是   | 企业号                                  |
| mainUniqueId | NSString | 是   | 会话ID                                  |
| startTime    | NSNumber | 是   | 时间戳                                  |
| status       | NSNumber | 是   | 会话状态，详情见TinetChatStatusType说明 |
| visitorId    | NSString | 是   | 当前用户ID                              |

```objective-c
TOSSessionInfoModel *model = [[TOSClientKit sharedTOSKit] getCurrentSessionInfo];
```

#### 客服会话页面

```objective-c
NS_ASSUME_NONNULL_BEGIN

@interface ChatInfoViewController : TOSCustomerChatVC

@end
```

#### 文本类型消息中关于链接、单号和手机号的相关点击回调
> 注：此方法需要在TOSCustomerChatVC类的子类中实现
```objective-c
typedef NS_ENUM(NSUInteger, TinetClickTextMessageEventType) {
    TinetClickEventTypeUrl,					      // 链接
    TinetClickEventTypeOrderNumber,				// 订单号
    TinetClickEventTypePhone，			  	   // 手机号
    TinetClickCommodityCard，						 // 商品卡片
};

/// 文本类型消息中关于链接、商品卡片、订单号和手机号的相关点击回调
/// @param eventType 事件类型
/// @param userInfo  详细信息
- (void)tinet_textMessageClickAction:(TinetClickTextMessageEventType)eventType userInfo:(NSDictionary *)userInfo;

```

#### 会话状态监听

> 注：此方法需要在TOSClientKitCustomerChatVC类的子类中实现

```objective-c
typedef NS_ENUM(NSUInteger, TinetChatStatusType) {
    TinetChatStatusTypeOutline,   // 不在线或结束会话
    TinetChatStatusTypeRobot,     // 机器人在线
    TinetChatStatusTypeOnline,    // 客服在线
};

/// @param status  会话状态
/// 当前会话状态监听
- (void)chatStatusChanged:(TinetChatStatusType)status;

```

#### 获取未读数和最后一条消息

```objective-c
  /**
   未读消息获取
  */
	[[TOSClientKit sharedTOSKit] getUnreadMessage:^(NSString * _Nonnull lastMessage, NSInteger unreadCount) {
        NSLog(@"未读数：%@, 最后一条消息：%@", unreadCount, lastMessage);
    } withError:^(NSString * _Nonnull errorStr) {
        /// 错误提示
    }];

  // 未读数的更新  接收通知
  [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(unreadMessage:) name:KTOSClientLibLastMessageReceivedNotification object:nil];
```

## 自定义UI配置

#### TOSKitCustomInfo 自定义UI类说明

```objective-c
/// 初始化
+ (TOSKitCustomInfo *)shareCustomInfo;

/// 快速入口Item的圆角弧度
@property (nonatomic, assign) CGFloat quickEntryItem_cornerRadius;

/// 发送方气泡的颜色
@property (nonatomic, strong) UIColor *senderBubble_backGround;
/// 发送方气泡的圆角弧度
@property (nonatomic, assign) CGFloat senderBubble_cornerRadius;

/// 头像的圆角弧度
@property (nonatomic, assign) CGFloat portrait_cornerRadius;

/// 聊天背景颜色
@property (nonatomic, strong) UIColor *chat_backGround;

/// 快速入口Item的背景颜色
@property (nonatomic, strong) UIColor *quickEntryItem_backgroundColor;

/// 接收方气泡的颜色
@property (nonatomic, strong) UIColor *receiveBubble_backGround;
/// 接收方气泡的圆角弧度
@property (nonatomic, assign) CGFloat receiveBubble_cornerRadius;

/// 快速入口底部的背景颜色
@property (nonatomic, strong) UIColor *quickEntryBottom_backgroundColor;

/// 接收方字体颜色
@property (nonatomic, strong) UIColor *receiveText_Color;

/// 发送方字体颜色
@property (nonatomic, strong) UIColor *senderText_Color;

/// 聊天底部输入中的语音按钮控制
@property (nonatomic, assign) BOOL ChatBox_voiceButton_enable;

/// 聊天底部输入中文本输入框的暗文设置
@property (nonatomic, strong) NSString *ChatBox_textview_placeholder;

/// 聊天底部背景颜色
@property (nonatomic, strong) UIColor *ChatBox_backGroundColor;

/// 聊天底部中线条颜色
@property (nonatomic, strong) UIColor *ChatBox_lineColor;

/// 聊天中显示的时间字体颜色
@property (nonatomic, strong) UIColor *Chat_time_textColor;

/// 启用或关闭客服或机器人昵称的显示
@property (nonatomic, assign) BOOL Chat_tosRobotName_show;

/// 启用或关闭访客昵称的显示
@property (nonatomic, assign) BOOL Chat_visitorName_show;

/// 启用或关闭客服昵称(机器人昵称)UI区域的显示
@property (nonatomic, assign) BOOL Chat_tosRobotName_enable;

/// 启用或关闭访客昵称UI区域的显示
@property (nonatomic, assign) BOOL Chat_visitorName_enable;

/// 启用或关闭客服和机器人头像UI区域的显示
@property (nonatomic, assign) BOOL Chat_tosRobot_portrait_enable;

/// 启用或关闭访客头像UI区域的显示
@property (nonatomic, assign) BOOL Chat_visitor_portrait_enable;

/// 相册展示导航栏中的文字颜色
@property (nonatomic, strong) UIColor *imagePicker_barItemTextColor;

/// 相册展示导航栏中的背景颜色
@property (nonatomic, strong) UIColor *imagePicker_naviBgColor;

/// 吐司提示气泡背景颜色
@property (nonatomic, strong) UIColor *Toast_backGroundColor;

/// 吐司提示中文字颜色
@property (nonatomic, strong) UIColor *Toast_textColor;

/// 语音按钮中文字颜色
@property (nonatomic, strong) UIColor *VoiceButton_textColor;

/// 语音按钮中文字高亮颜色
@property (nonatomic, strong) UIColor *VoiceButton_highlight_textColor;

示例代码：
[TOSKitCustomInfo shareCustomInfo].senderBubble_backGround = [UIColor redColor];
```

#### 商品卡片示例

```objective-c
 // TOSClientKitCommodityCardOption 详情见 商品卡片参数说明
  TOSClientKitCommodityCardOption *option = [[TOSClientKitCommodityCardOption alloc] init];
  option.subTitle = @"华为P40麒麟990 5G SoC芯片 5000万超感知徕卡三摄 30倍数字变焦";
  option.descriptions = @"这是商品描述，啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦";
  option.price = @"100.99";
  option.time = @"2022/05/24 18:32";
  option.img = @"https://img2.baidu.com/it/u=3019548648,4204913203&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500";
  option.status = @"已到货";
  option.extraInfo =
  @[@{@"name": @"订单号", @"value": @"1234567890"},
  @{@"name": @"产品类型", @"value": @"电子产品"},
  @{@"name": @"师傅"   , @"value": @"金师傅"},
  @{@"name": @"服务地区", @"value": @"北京市"},
  @{@"name": @"服务"   , @"value": @"满意"},
  @{@"name": @"师傅电话", @"value": @"12345678900"},
  @{@"name": @"订单状态", @"value": @"已完成"}];
  
  chatVC.commodityCardOption = option;
```

#### 快捷入口配置

```objective-c
chatVC.quickEntryAllItems = @[@"快捷入口1",@"快捷入口2",@"快捷入口3"];

/// 快捷入口的点击回调    index    点击索引从0开始（需要在子类实现这个方法）
- (void)quickEntryItemDidTouchIndex:(NSInteger)index;
```

#### 扩展面板配置

```objective-c
TOSKitExtendBoardItemModel类说明

+ (TOSKitChatBoxExtendBoard *)shareChatBoxExtendBoard;
/// 扩展项
@property (nonatomic, strong) NSArray <TOSKitExtendBoardItemModel *>*allItems;

/// 示例代码
TOSKitExtendBoardItemModel *model1 = [[TOSKitExtendBoardItemModel alloc] init];
model1.type = TOSChatBoxExtendBoardTypePhotos;	//除自定义类型外，其他类型不填即为默认UI
    
TOSKitExtendBoardItemModel *model2 = [[TOSKitExtendBoardItemModel alloc] init];
model2.type = TOSChatBoxExtendBoardTypeTakePicture;
    
TOSKitExtendBoardItemModel *model3 = [[TOSKitExtendBoardItemModel alloc] init];
model3.type = TOSChatBoxExtendBoardTypeCustom;//类型
model3.title = @"按钮3"; //标题
model3.image = @"image";//图片名称，图片大小：32*32/64*64/96*96
model3.index = 3;       //下标
[TOSKitChatBoxExtendBoard shareChatBoxExtendBoard].allItems = @[model1,model2,model3]; 

/// 扩展面板，自定义按钮事件 （需要在子类实现这个方法）
- (void)didClinkCustomExtendBoardItemAction:(TOSKitExtendBoardItemModel *)item;
```

> TOSKitExtendBoardItemModel参数说明

| 参数名 | 参数值                    | 说明                                             |
| ------ | ------------------------- | ------------------------------------------------ |
| title  | NSString                  | 标题                                             |
| image  | NSString                  | 图片名                                           |
| index  | NSInteger                 | 扩展项的唯一标示符                               |
| type   | TOSChatBoxExtendBoardType | 枚举类型，除自定义类型外，其他类型不填即为默认UI |

> TOSChatBoxExtendBoardType参数说明

| 参数名                               | 参数值 | 说明     |
| ------------------------------------ | ------ | -------- |
| TOSChatBoxExtendBoardTypePhotos      | 0      | 相册     |
| TOSChatBoxExtendBoardTypeTakePicture | 1      | 相机     |
| TOSChatBoxExtendBoardTypeCustomFile  | 2      | 文件     |
| TOSChatBoxExtendBoardTypeArtificial  | 3      | 转人工   |
| TOSChatBoxExtendBoardTypeCloseChat   | 4      | 结束会话 |
| TOSChatBoxExtendBoardTypeCustom      | 5      | 自定义   |

## 详细参数说明

> 会话状态监听

| 参数名                     | 参数值 | 说明           |
| -------------------------- | ------ | -------------- |
| TinetChatStatusTypeOutline | 0      | 离线或结束会话 |
| TinetChatStatusTypeRobot   | 1      | 机器人         |
| TinetChatStatusTypeOnline  | 2      | 客服           |

> TOSClientKitCommodityCardOption 商品卡片参数说明

| 参数名       | 参数类型     | 说明                                   |
| ------------ | ------------ | -------------------------------------- |
| subTitle     | NSString     | 卡片消息副标题                         |
| descriptions | NSString     | 卡片消息的描述，例如商品的简单描述     |
| price        | NSString     | 商品卡片的价格                         |
| time         | NSString     | 卡片消息的时间，例如订单的生成时间     |
| img          | NSString     | 卡片消息的图片地址，例如商品的图片地址 |
| status       | NSString     | 商品状态                               |
| extraInfo    | NSDictionary | 额外信息                               |
