//
//  NSDictionary+Extension.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/9/27.
//  Copyright © 2016年 gxz. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSDictionary (Extension)


+ (NSDictionary *)dictionaryWithJsonString:(NSString *)jsonStr;

- (NSString*)jsonString;

@end
