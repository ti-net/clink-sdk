//
//  NSThread+TIMYYAdd.h
//  YYKit <https://github.com/ibireme/YYKit>
//
//  Created by 侯力 on 2024/4/17.
//  Copyright © 2019年 侯力. All rights reserved.
//
//  This source code is licensed under the MIT-style license found in the
//  LICENSE file in the root directory of this source tree.
//

#import "NSThread+TIMYYAdd.h"
#import <CoreFoundation/CoreFoundation.h>

@interface NSThread_TIMYYAdd : NSObject @end
@implementation NSThread_TIMYYAdd @end

#if __has_feature(objc_arc)
#error This file must be compiled without ARC. Specify the -fno-objc-arc flag to this file.
#endif

static NSString *const TIMYYNSThreadAutoleasePoolKey = @"YYNSThreadAutoleasePoolKey";
static NSString *const TIMYYNSThreadAutoleasePoolStackKey = @"YYNSThreadAutoleasePoolStackKey";

static const void *TIMPoolStackRetainCallBack(CFAllocatorRef allocator, const void *value) {
    return value;
}

static void TIMPoolStackReleaseCallBack(CFAllocatorRef allocator, const void *value) {
    CFRelease((CFTypeRef)value);
}


static inline void TIMYYAutoreleasePoolPush() {
    NSMutableDictionary *dic =  [NSThread currentThread].threadDictionary;
    NSMutableArray *poolStack = dic[TIMYYNSThreadAutoleasePoolStackKey];
    
    if (!poolStack) {
        /*
         do not retain pool on push,
         but release on pop to avoid memory analyze warning
         */
        CFArrayCallBacks callbacks = {0};
        callbacks.retain = TIMPoolStackRetainCallBack;
        callbacks.release = TIMPoolStackReleaseCallBack;
        poolStack = (id)CFArrayCreateMutable(CFAllocatorGetDefault(), 0, &callbacks);
        dic[TIMYYNSThreadAutoleasePoolStackKey] = poolStack;
        CFRelease(poolStack);
    }
    NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init]; //< create
    [poolStack addObject:pool]; // push
}

static inline void TIMYYAutoreleasePoolPop() {
    NSMutableDictionary *dic =  [NSThread currentThread].threadDictionary;
    NSMutableArray *poolStack = dic[TIMYYNSThreadAutoleasePoolStackKey];
    [poolStack removeLastObject]; // pop
}

static void TIMYYRunLoopAutoreleasePoolObserverCallBack(CFRunLoopObserverRef observer, CFRunLoopActivity activity, void *info) {
    switch (activity) {
        case kCFRunLoopEntry: {
            TIMYYAutoreleasePoolPush();
        } break;
        case kCFRunLoopBeforeWaiting: {
            TIMYYAutoreleasePoolPop();
            TIMYYAutoreleasePoolPush();
        } break;
        case kCFRunLoopExit: {
            TIMYYAutoreleasePoolPop();
        } break;
        default: break;
    }
}

static void TIMYYRunloopAutoreleasePoolSetup() {
    CFRunLoopRef runloop = CFRunLoopGetCurrent();

    CFRunLoopObserverRef pushObserver;
    pushObserver = CFRunLoopObserverCreate(CFAllocatorGetDefault(), kCFRunLoopEntry,
                                           true,         // repeat
                                           -0x7FFFFFFF,  // before other observers
                                           TIMYYRunLoopAutoreleasePoolObserverCallBack, NULL);
    CFRunLoopAddObserver(runloop, pushObserver, kCFRunLoopCommonModes);
    CFRelease(pushObserver);
    
    CFRunLoopObserverRef popObserver;
    popObserver = CFRunLoopObserverCreate(CFAllocatorGetDefault(), kCFRunLoopBeforeWaiting | kCFRunLoopExit,
                                          true,        // repeat
                                          0x7FFFFFFF,  // after other observers
                                          TIMYYRunLoopAutoreleasePoolObserverCallBack, NULL);
    CFRunLoopAddObserver(runloop, popObserver, kCFRunLoopCommonModes);
    CFRelease(popObserver);
}

@implementation NSThread (TIMYYAdd)

+ (void)addAutoreleasePoolToCurrentRunloop {
    if ([NSThread isMainThread]) return; // The main thread already has autorelease pool.
    NSThread *thread = [self currentThread];
    if (!thread) return;
    if (thread.threadDictionary[TIMYYNSThreadAutoleasePoolKey]) return; // already added
    TIMYYRunloopAutoreleasePoolSetup();
    thread.threadDictionary[TIMYYNSThreadAutoleasePoolKey] = TIMYYNSThreadAutoleasePoolKey; // mark the state
}

@end
