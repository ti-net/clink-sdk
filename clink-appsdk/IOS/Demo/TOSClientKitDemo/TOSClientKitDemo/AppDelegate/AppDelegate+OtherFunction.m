//
//  AppDelegate+OtherFunction.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/6.
//  Copyright © 2019 赵言. All rights reserved.
//
#import "AppDelegate.h"
#import "AppDelegate+OtherFunction.h"

@implementation AppDelegate (OtherFunction)

#pragma mark - 网络状态
- (void)configureReachability {
    self.networkIsOK = YES;
    self.hostReachability = [Reachability reachabilityWithHostName:@"www.apple.com"];
    [self.hostReachability startNotifier];
    NetworkStatus status = [self.hostReachability currentReachabilityStatus];
    switch (status) {
        case NotReachable:
            self.networkIsOK = NO;
            break;
        case ReachableViaWWAN:
        case ReachableViaWiFi:
            self.networkIsOK = YES;
            break;
        default:
            break;
    }
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(reachabilityChangeds:) name:kReachabilityChangedNotification object:nil];
}

/// 监听网络发生变化
/// @param note 监听的消息
- (void)reachabilityChangeds:(NSNotification *)note {
    Reachability *curReach = [note object];
    NSParameterAssert([curReach isKindOfClass:[Reachability class]]);
    NetworkStatus status = [curReach currentReachabilityStatus];
    
    if (status == NotReachable) {
        NSLog(@"断网");
        self.networkIsOK = NO;
        return;
    }
    
    if (status == ReachableViaWWAN) {
        NSLog(@"4G");
        self.networkIsOK = YES;
    }
    
    if(status == ReachableViaWiFi) {
        NSLog(@"wifi");
        self.networkIsOK = YES;
    }
}

@end
