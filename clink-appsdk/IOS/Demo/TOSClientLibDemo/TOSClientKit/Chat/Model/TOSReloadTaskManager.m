//
//  TaskManager.m
//  ChatTest
//
//  Created by Zhao Yan, CIS-T-11(Baidu) on 2023/11/3.
//

#import "TOSReloadTaskManager.h"

@interface TOSReloadTaskManager ()
{
    dispatch_queue_t _serialQueue;
    dispatch_source_t _countdownTimer;
    BOOL _countdownCancelled;
}

@end

@implementation TOSReloadTaskManager

- (instancetype)init {
    self = [super init];
    if (self) {
        _serialQueue = dispatch_queue_create("com.tosDataSource.serialQueue", DISPATCH_QUEUE_SERIAL);
        _countdownTimer = dispatch_source_create(DISPATCH_SOURCE_TYPE_TIMER, 0, 0, dispatch_get_main_queue());
        _countdownCancelled = NO;
        self.isScroll = NO;
        dispatch_source_set_timer(_countdownTimer, DISPATCH_TIME_FOREVER, 1ull * NSEC_PER_SEC, 0);
        __weak typeof(self) weakSelf = self;
        dispatch_source_set_event_handler(_countdownTimer, ^{
            __strong typeof(weakSelf) strongSelf = weakSelf;
            [strongSelf countdownTick];
        });
        dispatch_resume(_countdownTimer);
    }
    return self;
}

- (void)startTaskWithScroll:(BOOL)isScroll completion:(dispatch_block_t)completion {
    self.isScroll = isScroll;
    // 取消上次的倒计时任务
    [self cancelCountdown];
    __weak typeof(self) weakSelf = self;
    dispatch_async(_serialQueue, ^{
        __strong typeof(weakSelf) strongSelf = weakSelf;
        if (completion) {
            completion();
        }
        
        NSLog(@"任务完成后启动倒计时 %@",[NSThread currentThread]);
        // 任务完成后启动倒计时
        [strongSelf startCountdown];
    });
}

- (void)startCountdown {
    // 5秒
//    dispatch_source_set_timer(_countdownTimer, dispatch_time(DISPATCH_TIME_NOW, 5ull * NSEC_PER_SEC), DISPATCH_TIME_FOREVER, 0);
    // 0.3秒
    dispatch_source_set_timer(_countdownTimer, dispatch_time(DISPATCH_TIME_NOW, 300000000ull), DISPATCH_TIME_FOREVER, 0);
    _countdownCancelled = NO;
}

- (void)cancelCountdown {
    _countdownCancelled = YES;
}

- (void)countdownTick {
    if (!_countdownCancelled) {
        NSLog(@"倒计时结束");
        if (self.delegate &&
            [self.delegate respondsToSelector:@selector(reloadTableViewWithScroll:)]) {
            [self.delegate reloadTableViewWithScroll:self.isScroll];
        }
    } else {
        NSLog(@"倒计时取消");
    }
}

- (void)dealloc {
    NSLog(@"TOSReloadTaskManager dealloc");
}

@end
