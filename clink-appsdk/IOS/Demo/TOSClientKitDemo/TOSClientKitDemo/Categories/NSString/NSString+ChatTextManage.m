//
//  NSString+ChatTextManage.m
//  mobileCMS
//
//  Created by 赵言 on 2020/2/3.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "NSString+ChatTextManage.h"

@implementation NSString (ChatTextManage)

- (NSString *)chatTextHandle {
    __block NSString *handle = [NSString stringWithFormat:@"%@",self];
    NSArray <NSString *>*arrar = @[
        @"[:1f31d:]",
        @"[:1f63b:]",
        @"[:1f644:]",
        @"[:1f576:]",
        @"[:1f62d:]",
//        @"[:263a:]",
        @"[:1f64a:]",
        @"[:1f634:]",
        @"[:1f61f:]",
        @"[:1f621:]",
        @"[:1f61b:]",
        @"[:1f62c:]",
        @"[:1f62f:]",
//        @"[:2639:]",
        @"[:1f632:]",
        @"[:1f625:]",
        @"[:1f638:]",
        @"[:1f631:]",
        @"[:1f92e:]",
        @"[:1f63a:]",
        @"[:1f910:]",
        @"[:1f613:]",
        @"[:1f971:]",
        @"[:1f641:]",
        @"[:1f629:]",
        @"[:1f97a:]",
        @"[:1f52a:]",
        @"[:1f349:]",
        @"[:1f942:]",
//        @"[:2615:]",
        @"[:1f61a:]",
        @"[:1f339:]",
        @"[:1f940:]",
        @"[:1f493:]",
        @"[:1f494:]",
        @"[:1f382:]",
        @"[:1f505:]",
        @"[:1f44c:]",
        @"[:1f91e:]",
        @"[:1f601:]",
        @"[:1f637:]",
        @"[:1f602:]",
        @"[:1f61b:]",
        @"[:1f614:]",
        @"[:1f615:]",
        @"[:1f389:]",
        @"[:1f4aa_1f3fd:]",
        @"[:1f3c0:]",
        @"[:1f974:]",
        @"[:1f913:]",
        @"[:1f913:]",
        @"[:1f913:]",
        @"[:1f3d3:]",];
    
    NSArray *emojiArrar = @[
        @"\U0001f31d",
        @"\U0001f63b",
        @"\U0001f644",
        @"\U0001f576",
        @"\U0001f62d",
//        @"\U000263a",
        @"\U0001f64a",
        @"\U0001f634",
        @"\U0001f61f",
        @"\U0001f621",
        @"\U0001f61b",
        @"\U0001f62c",
        @"\U0001f62f",
//        @"\U0002639",
        @"\U0001f632",
        @"\U0001f625",
        @"\U0001f638",
        @"\U0001f631",
        @"\U0001f92e",
        @"\U0001f63a",
        @"\U0001f910",
        @"\U0001f613",
        @"\U0001f971",
        @"\U0001f641",
        @"\U0001f629",
        @"\U0001f97a",
        @"\U0001f52a",
        @"\U0001f349",
        @"\U0001f942",
//        @"\U0002615",
        @"\U0001f61a",
        @"\U0001f339",
        @"\U0001f940",
        @"\U0001f493",
        @"\U0001f494",
        @"\U0001f382",
        @"\U0001f505",
        @"\U0001f44c",
        @"\U0001f91e",
        @"\U0001f601",
        @"\U0001f637",
        @"\U0001f602",
        @"\U0001f61b",
        @"\U0001f614",
        @"\U0001f615",
        @"\U0001f389",
        @"\U0001f4aa_1f3fd",
        @"\U0001f3c0",
        @"\U0001f974",
        @"\U0001f913",
        @"\U0001f913",
        @"\U0001f913",
        @"\U0001f3d3",];
    
    
    [arrar enumerateObjectsUsingBlock:^(NSString * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        if ([handle containsString:obj]) {
            handle = [handle stringByReplacingOccurrencesOfString:obj withString:emojiArrar[idx]];
        }
    }];
    return handle;
}

@end
