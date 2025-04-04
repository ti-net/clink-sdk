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
    dispatch_block_t _delayedFlashBlock;
}

@end

@implementation TOSReloadTaskManager

- (instancetype)init {
    self = [super init];
    if (self) {
        _serialQueue = dispatch_queue_create("com.tosDataSource.serialQueue", DISPATCH_QUEUE_SERIAL);
        _countdownTimer = dispatch_source_create(DISPATCH_SOURCE_TYPE_TIMER, 0, 0, dispatch_get_main_queue());
        _delayedFlashBlock = nil;
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
    // 上一次是滚动并且要切换为刷新的那么需要延迟执行
    if (self.isScroll && !isScroll) {
        self.isScroll = isScroll;
        // 创建新的延迟任务
        _delayedFlashBlock = dispatch_block_create(0, ^{
            [self subStartTaskWithScroll:completion];
        });
        
        // 延迟0.5秒执行任务
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), _delayedFlashBlock);
    } else {
        self.isScroll = isScroll;
        // 如果已有延迟任务，取消它
        if (_delayedFlashBlock) {
            dispatch_block_cancel(_delayedFlashBlock);
            _delayedFlashBlock = nil;
        }
        [self subStartTaskWithScroll:completion];
    }
}

- (void)subStartTaskWithScroll:(dispatch_block_t)completion{
    
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
    // 0.3秒：300000000ull、0ull
    dispatch_source_set_timer(_countdownTimer, dispatch_time(DISPATCH_TIME_NOW, 0ull), DISPATCH_TIME_FOREVER, 0);
}


- (void)countdownTick {
    NSLog(@"倒计时结束");
    if (self.isScroll) {
        if (self.delegate &&
            [self.delegate respondsToSelector:@selector(reloadTableViewWithScroll)]) {
            [self.delegate reloadTableViewWithScroll];
        }
    } else {
        if (self.delegate &&
            [self.delegate respondsToSelector:@selector(reloadTableViewWithFlash)]) {
            [self.delegate reloadTableViewWithFlash];
        }
    }
}

- (void)dealloc {
    NSLog(@"TOSReloadTaskManager dealloc");
}

@end
