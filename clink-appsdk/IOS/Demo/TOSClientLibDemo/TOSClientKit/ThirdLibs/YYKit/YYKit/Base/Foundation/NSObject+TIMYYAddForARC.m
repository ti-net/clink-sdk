//
//  NSObject+YYAddForARC.m
//  YYKit <https://github.com/ibireme/YYKit>
//
//  Created by 侯力 on 2024/4/17.
//  Copyright © 2019年 侯力. All rights reserved.
//
//  This source code is licensed under the MIT-style license found in the
//  LICENSE file in the root directory of this source tree.
//

#import "NSObject+TIMYYAddForARC.h"

@interface NSObject_TIMYYAddForARC : NSObject @end
@implementation NSObject_TIMYYAddForARC @end

#if __has_feature(objc_arc)
#error This file must be compiled without ARC. Specify the -fno-objc-arc flag to this file.
#endif


@implementation NSObject (TIMYYAddForARC)

- (instancetype)arcDebugRetain {
    return [self retain];
}

- (oneway void)arcDebugRelease {
    [self release];
}

- (instancetype)arcDebugAutorelease {
    return [self autorelease];
}

- (NSUInteger)arcDebugRetainCount {
    return [self retainCount];
}

@end
