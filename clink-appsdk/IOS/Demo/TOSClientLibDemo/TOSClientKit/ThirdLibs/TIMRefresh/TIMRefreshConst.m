
#import <UIKit/UIKit.h>

const CGFloat TIMRefreshLabelLeftInset = 25;
const CGFloat TIMRefreshHeaderHeight = 54.0;
const CGFloat TIMRefreshFooterHeight = 44.0;
const CGFloat TIMRefreshFastAnimationDuration = 0.25;
const CGFloat TIMRefreshSlowAnimationDuration = 0.4;

NSString *const TIMRefreshKeyPathContentOffset = @"contentOffset";
NSString *const TIMRefreshKeyPathContentInset = @"contentInset";
NSString *const TIMRefreshKeyPathContentSize = @"contentSize";
NSString *const TIMRefreshKeyPathPanState = @"state";

NSString *const TIMRefreshHeaderLastUpdatedTimeKey = @"TIMRefreshHeaderLastUpdatedTimeKey";

NSString *const TIMRefreshHeaderIdleText = @"下拉可以刷新";
NSString *const TIMRefreshHeaderPullingText = @"松开立即刷新";
NSString *const TIMRefreshHeaderRefreshingText = @"正在刷新数据中...";

NSString *const TIMRefreshAutoFooterIdleText = @"点击或上拉加载更多";
NSString *const TIMRefreshAutoFooterRefreshingText = @"正在加载更多的数据...";
NSString *const TIMRefreshAutoFooterNoMoreDataText = @"已经全部加载完毕";

NSString *const TIMRefreshBackFooterIdleText = @"上拉可以加载更多";
NSString *const TIMRefreshBackFooterPullingText = @"松开立即加载更多";
NSString *const TIMRefreshBackFooterRefreshingText = @"正在加载更多的数据...";
NSString *const TIMRefreshBackFooterNoMoreDataText = @"已经全部加载完毕";

NSString *const TIMRefreshHeaderLastTimeText = @"最后更新：";
NSString *const TIMRefreshHeaderDateTodayText = @"今天";
NSString *const TIMRefreshHeaderNoneLastDateText = @"无记录";
