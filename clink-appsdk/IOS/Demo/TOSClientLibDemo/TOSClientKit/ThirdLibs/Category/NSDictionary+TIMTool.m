//
//  NSDictionary+TIMTool.m
//  TIMClientKit
//
//  Created by 赵言 on 2020/12/8.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "NSDictionary+TIMTool.h"

@implementation NSDictionary (TIMTool)

- (id)by_ObjectForKey:(NSString *)key {
    if (self && [self isKindOfClass:[NSDictionary class]] && [[self allKeys] containsObject:key]) {
        return [self objectForKey:key];
    } else {
        return nil;
    }
}

@end
