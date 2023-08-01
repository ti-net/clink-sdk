//
//  TIMPropertyKey.m
//  TIMExtensionExample
//
//  Created by MJ Lee on 15/8/11.
//  Copyright (c) 2015年 小码哥. All rights reserved.
//

#import "TIMPropertyKey.h"

@implementation TIMPropertyKey

- (id)valueInObject:(id)object
{
    if ([object isKindOfClass:[NSDictionary class]] && self.type == TIMPropertyKeyTypeDictionary) {
        return object[self.name];
    } else if ([object isKindOfClass:[NSArray class]] && self.type == TIMPropertyKeyTypeArray) {
        NSArray *array = object;
        NSUInteger index = self.name.intValue;
        if (index < array.count) return array[index];
        return nil;
    }
    return nil;
}
@end
