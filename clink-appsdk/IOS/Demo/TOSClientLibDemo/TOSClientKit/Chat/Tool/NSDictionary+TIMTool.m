//
//  NSDictionary+TIMTool.m
//  TOSClientKit
//
//  Created by 言 on 2024/1/26.
//  Copyright © 2024 YanBo. All rights reserved.
//

#import "NSDictionary+TIMTool.h"

@implementation NSDictionary (TIMTool)

- (id)tim_ObjectForKey:(NSString *)key {
    if (self && [self isKindOfClass:[NSDictionary class]] && [[self allKeys] containsObject:key]) {
        return [self objectForKey:key];
    } else {
        return nil;
    }
}

@end
