//
//  NSArray+TRTool.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/25.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "NSArray+TRTool.h"

@implementation NSArray (TRTool)

+ (NSArray *)readPlistFileWithFileName:(NSString *)fileName {
    NSString *path = [[NSBundle mainBundle] pathForResource:fileName ofType:@"plist"];
    NSArray *array = [NSArray array];
    if (path && path.length > 0) {
        array = [NSArray arrayWithContentsOfFile:path];
    }
    return array;
}

- (id)by_ObjectAtIndex:(NSUInteger)index {
    if (self && self.count > index) {
        return [self objectAtIndex:index];
    } else {
        return nil;
    }
}


@end
