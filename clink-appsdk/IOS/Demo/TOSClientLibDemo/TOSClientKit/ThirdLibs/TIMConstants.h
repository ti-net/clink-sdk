//
//  TIMConstants.h
//  TIMClientDemo
//
//  Created by YanBo on 2020/5/27.
//  Copyright © 2020 YanBo. All rights reserved.
//

#ifndef TIMConstants_h
#define TIMConstants_h

#import "UIView+SLFrame.h"
#import "SLDelayPerform.h"
//#import <TOSClientKit/TOSKitCustomInfo.h>

/// 屏幕宽高
#define SL_kScreenWidth [UIScreen mainScreen].bounds.size.width
#define SL_kScreenHeight [UIScreen mainScreen].bounds.size.height

///主线程操作
#define SL_DISPATCH_ON_MAIN_THREAD(mainQueueBlock) dispatch_async(dispatch_get_main_queue(),mainQueueBlock);
#define SL_GCDWithGlobal(block) dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), block)

#define APP_Frame_Height   [[UIScreen mainScreen] bounds].size.height

#define App_Frame_Width    [[UIScreen mainScreen] bounds].size.width

#define ALERT(msg)  [[[UIAlertView alloc]initWithTitle:@"提示" message:msg delegate:nil \
cancelButtonTitle:@"确定" otherButtonTitles:nil,nil] show]

#define App_Delegate ((AppDelegate*)[[UIApplication sharedApplication]delegate])

#define App_RootCtr  [UIApplication sharedApplication].keyWindow.rootViewController

#define WEAKSELF __weak typeof(self) weakSelf = self;

#define TOSColor(r, g, b) [UIColor colorWithRed:(r)/255.0 green:(g)/255.0 blue:(b)/255.0 alpha:1.0]

#define TOSAColor(r, g, b, a) [UIColor colorWithRed:(r / 255.0) green:(g / 255.0) blue:(b / 255.0) alpha:a]

#define TOSHexColor(hexValue) [UIColor colorWithRed:((float)(((hexValue) & 0xFF0000) >> 16))/255.0 green:((float)(((hexValue) & 0xFF00) >> 8))/255.0 blue:((float)((hexValue) & 0xFF))/255.0 alpha:1.0]

#define TOSHexAColor(rgbValue,a) [UIColor colorWithRed:((float)((rgbValue & 0xFF0000) >> 16)) / 255.0 green:((float)((rgbValue & 0xFF00) >> 8)) / 255.0 blue:((float)(rgbValue & 0xFF)) / 255.0 alpha:a]

#define EMOJI_CODE_TO_SYMBOL(x) ((((0x808080F0 | (x & 0x3F000) >> 4) | (x & 0xFC0) << 10) | (x & 0x1C0000) << 18) | (x & 0x3F) << 24);

#define ICFont(FONTSIZE)  [UIFont systemFontOfSize:(FONTSIZE)]
#define ICBOLDFont(FONTSIZE)  [UIFont boldSystemFontOfSize:(FONTSIZE)]
#define ICSEARCHCANCELCOLOR    [UIColor orangeColor]
#define SEARCH_HEIGHT_COLOR   TOSHexAColor(0x027996,1.0)

#pragma mark - UI适配

#define IphoneX ({ \
BOOL ipX = NO; \
if (@available(iOS 11.0, *)) { \
    UIWindow *window = [[UIApplication sharedApplication].windows firstObject]; \
    ipX = window.safeAreaInsets.bottom > 0; \
} \
  ipX; \
})

#define IS_IPAD (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
#define IS_IPHONE (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPhone)

#define SCREEN_WIDTH ([[UIScreen mainScreen] bounds].size.width)
#define SCREEN_HEIGHT ([[UIScreen mainScreen] bounds].size.height)
#define SCREEN_MAX_LENGTH (MAX(SCREEN_WIDTH, SCREEN_HEIGHT))
#define SCREEN_MIN_LENGTH (MIN(SCREEN_WIDTH, SCREEN_HEIGHT))
#define SCALE(x)  x*self.scale
#define HEIGHTSCALE(x)  (IS_IPHONE_X || IS_IPHONE_XsMax ? x*self.heightScale : x*self.scale)


#define IS_IPHONE_4_OR_LESS (IS_IPHONE && SCREEN_MAX_LENGTH < 568.0)
#define IS_IPHONE_5 (IS_IPHONE && SCREEN_MAX_LENGTH == 568.0)

#define IS_IPHONE_6 (IS_IPHONE && SCREEN_MAX_LENGTH == 667.0)
#define IS_IPHONE_6P (IS_IPHONE && SCREEN_MAX_LENGTH == 736.0)
#define IS_IPHONE_X (IS_IPHONE && SCREEN_MAX_LENGTH == 812)
#define IS_IPHONE_XsMax (IS_IPHONE && SCREEN_MAX_LENGTH == 896)

#define IS_IPHONE_BangsScreen (IS_IPHONE && SCREEN_MAX_LENGTH >= 780)

#define kNavTop (IS_IPHONE_BangsScreen ? 88.f : 64.f)
#define kBottomBarHeight (IS_IPHONE_BangsScreen ? 34.0f : 0)
#define kStatusBarHeight    (IS_IPHONE_BangsScreen ? 44.f : 20.f)

#define kTabBarHeight 49.f

#define kWindowWidth    [UIScreen mainScreen].bounds.size.width
#define kWindowHeight   [[UIScreen mainScreen] bounds].size.height
#define kMainWindow      [[[UIApplication sharedApplication] delegate] window]

//初始化xib文件
#define kInitXibName(nibName) [[UINib nibWithNibName:nibName bundle:nil] instantiateWithOwner:nil options:nil].firstObject

#define kTOSClientResourcesPath(path)  [NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,path]

#define NE_BACKGROUND_COLOR TOSHexAColor(0x027996,1.0)

#define kDiscvoerVideoPath @"Download/Video"  // video子路径
#define kChatVideoPath @"Chat/Video"  // video子路径
#define kVideoType @".mp4"        // video类型
#define kRecoderType @".wav"

#define kGIFTimageType @"gif"


static NSString * const FRAMEWORKS_BUNDLE_PATH=@"TOSKitClient.bundle";

//新增消息推送
static NSString * const kTIMMessageReceivedNotification = @"kTIMMessageReceivedNotification";
///  清空最后一条消息/消息未读数
static NSString * const kTIMMessageClearUnReadCountNotification = @"kTIMMessageClearUnReadCountNotification";
//消息撤回
static NSString * const kTIMMessageRecalledNotification = @"kTIMMessageRecalledNotification";

//更新链接状态 YES NO
static NSString * const kTIMUpdateConnectStatusNotification = @"kTIMUpdateConnectStatusNotification";

//发送文件消息
static NSString * const kSendFileMessageNotification = @"kSendFileMessageNotification";

/// 输入框文本发生变化，表情面板的发送按钮是否高亮显示
static NSString * const kTextViewChangeNotification = @"kTextViewChangeNotification";

/// 更新扩展面板的item，（从机器人转到座席时触发）
static NSString * const kOnlineChangeExtendBoardItemNotification = @"kOnlineChangeExtendBoardItemNotification";

//撤回消息重新编辑的展示字符
static NSString * const kTIMRecalledMessageAgainEdit = @"重新编辑";
//有人@我的展示字符
static NSString * const kTIMATMyMessage = @"［有人@我］";

#define kTinetATagRegular  @"<a[a-zA-Z0-9\\s:/%=><&\\?\\-~_\\$#+\\.,;\"']*(</a>)"
#define kTinetATagHrefRegular  @"(href=)[a-zA-Z0-9\\s:/%=><&\\?\\-~_\\$#+\\.,;\"']*( )"
#define kTinetATagContentRegular  @"(\">)[a-zA-Z0-9\\s:/%=><&\\?\\-~_\\$#+\\.,;\"']*(</a>)"


#define kTIMATRegular @"@[\\u4e00-\\u9fa5\\w\\-\\_]+ "  //最后一段特殊的空格
#define kTIMATEnd @" "   //这是一段特殊的空格，专用于@用户的结尾
//未知的消息类型展示文本
//static NSString * const kTIMUnsupportMessageCellType = @"[未知的消息类型]";

//通知界面刷新 仅限于发送消息使用 kit层
static NSString * const kTIMMessageUpdateChatUINotification = @"kTIMMessageUpdateChatUINotification";
//撤回消息重新编辑的通知
static NSString * const kTIMRecalledMessageAgainEditNotification = @"kTIMRecalledMessageAgainEditNotification";
//TIMSendMultiMediaImageNotification
static NSString * const KTIMSendMultiMediaImageNotification = @"KTIMSendMultiMediaImageNotification";
//推出排队通知
static NSString * const OSLeaveQueueNotification = @"OSLeaveQueueNotification";
//拉取历史消息通知
static NSString * const OSGetHistoryListNotification = @"OSGetHistoryListNotification";

// rtcMediaVersion
#define strRTCMediaVersion @"1.0"

#define WeakObj(o) autoreleasepool{} __weak typeof(o) o##Weak = o;  //宏定义self
#define StrongObj(o) autoreleasepool{} __strong typeof(o) o = o##Weak;

static NSInteger const kStateBarColor = 0xFFFFFF;
static NSInteger const kBackgroundColor = 0xF5F8F9;
static NSInteger const kThemeColor = 0x2397FF;
static NSInteger const kDefaultBlue = 0x007AFF;

#endif /* TIMConstants_h */
