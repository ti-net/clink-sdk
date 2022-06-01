//
//  NSString+JsonParsing.m
//  mobileCMS
//
//  Created by 赵言 on 2020/1/11.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "NSString+JsonParsing.h"

@implementation NSString (JsonParsing)

- (id)jsonParsing {
    if (self && [self isKindOfClass:[NSString class]] && self.length > 0) {
        NSError *error;
        id dic = [NSJSONSerialization JSONObjectWithData:[self dataUsingEncoding:NSUTF8StringEncoding] options:NSJSONReadingMutableContainers error:&error];
        if (error) {
            NSLog(@"%@ JSON解析失败4 %@",self,error);
            return nil;
        } else {
            return dic;
        }
    } else {
        return nil;
    }
}

@end
