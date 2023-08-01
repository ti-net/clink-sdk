//
//  NSArray+TIMTool.m
//  TIMClientKit
//
//  Created by 赵言 on 2020/12/8.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "NSArray+TIMTool.h"

@implementation NSArray (TIMTool)

- (id)by_ObjectAtIndex:(NSUInteger)index {
    if (self && self.count > index) {
        return [self objectAtIndex:index];
    } else {
        return nil;
    }
}

@end
