//
//  TaskManager.h
//  ChatTest
//
//  Created by Zhao Yan, CIS-T-11(Baidu) on 2023/11/3.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@protocol TOSReloadTaskManagerDelegate <NSObject>

// 滑倒底的刷新
- (void)reloadTableViewWithScroll;

// 静态刷新
- (void)reloadTableViewWithFlash;

@end

@interface TOSReloadTaskManager : NSObject

@property (nonatomic, weak) id<TOSReloadTaskManagerDelegate> delegate;

@property (nonatomic, assign) BOOL isScroll;

- (void)startTaskWithScroll:(BOOL)isScroll completion:(dispatch_block_t)completion;
- (void)startCountdown;

@end

NS_ASSUME_NONNULL_END
