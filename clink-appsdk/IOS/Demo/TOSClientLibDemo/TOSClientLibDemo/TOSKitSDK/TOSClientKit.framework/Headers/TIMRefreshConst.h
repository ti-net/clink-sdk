
#import <UIKit/UIKit.h>
#import <objc/message.h>

// 弱引用
#define TIMWeakSelf __weak typeof(self) weakSelf = self;

// 日志输出
#ifdef DEBUG
#define TIMRefreshLog(...) NSLog(__VA_ARGS__)
#else
#define TIMRefreshLog(...)
#endif

// 过期提醒
#define TIMRefreshDeprecated(DESCRIPTION) __attribute__((deprecated(DESCRIPTION)))

// 运行时objc_msgSend
#define TIMRefreshMsgSend(...) ((void (*)(void *, SEL, UIView *))objc_msgSend)(__VA_ARGS__)
#define TIMRefreshMsgTarget(target) (__bridge void *)(target)

// RGB颜色
#define TIMRefreshColor(r, g, b) [UIColor colorWithRed:(r)/255.0 green:(g)/255.0 blue:(b)/255.0 alpha:1.0]

// 文字颜色
#define TIMRefreshLabelTextColor TIMRefreshColor(90, 90, 90)

// 字体大小
#define TIMRefreshLabelFont [UIFont boldSystemFontOfSize:14]

// 常量
UIKIT_EXTERN const CGFloat TIMRefreshLabelLeftInset;
UIKIT_EXTERN const CGFloat TIMRefreshHeaderHeight;
UIKIT_EXTERN const CGFloat TIMRefreshFooterHeight;
UIKIT_EXTERN const CGFloat TIMRefreshFastAnimationDuration;
UIKIT_EXTERN const CGFloat TIMRefreshSlowAnimationDuration;

UIKIT_EXTERN NSString *const TIMRefreshKeyPathContentOffset;
UIKIT_EXTERN NSString *const TIMRefreshKeyPathContentSize;
UIKIT_EXTERN NSString *const TIMRefreshKeyPathContentInset;
UIKIT_EXTERN NSString *const TIMRefreshKeyPathPanState;

UIKIT_EXTERN NSString *const TIMRefreshHeaderLastUpdatedTimeKey;

UIKIT_EXTERN NSString *const TIMRefreshHeaderIdleText;
UIKIT_EXTERN NSString *const TIMRefreshHeaderPullingText;
UIKIT_EXTERN NSString *const TIMRefreshHeaderRefreshingText;

UIKIT_EXTERN NSString *const TIMRefreshAutoFooterIdleText;
UIKIT_EXTERN NSString *const TIMRefreshAutoFooterRefreshingText;
UIKIT_EXTERN NSString *const TIMRefreshAutoFooterNoMoreDataText;

UIKIT_EXTERN NSString *const TIMRefreshBackFooterIdleText;
UIKIT_EXTERN NSString *const TIMRefreshBackFooterPullingText;
UIKIT_EXTERN NSString *const TIMRefreshBackFooterRefreshingText;
UIKIT_EXTERN NSString *const TIMRefreshBackFooterNoMoreDataText;

UIKIT_EXTERN NSString *const TIMRefreshHeaderLastTimeText;
UIKIT_EXTERN NSString *const TIMRefreshHeaderDateTodayText;
UIKIT_EXTERN NSString *const TIMRefreshHeaderNoneLastDateText;

// 状态检查
#define TIMRefreshCheckState \
TIMRefreshState oldState = self.state; \
if (state == oldState) return; \
[super setState:state];

// 异步主线程执行，不强持有Self
#define TIMRefreshDispatchAsyncOnMainQueue(x) \
__weak typeof(self) weakSelf = self; \
dispatch_async(dispatch_get_main_queue(), ^{ \
typeof(weakSelf) self = weakSelf; \
{x} \
});
