//
//  NSObject+ShowError.h
//  SmartHome
//
//  Created by 赵言 on 2019/7/4.
//  Copyright © 2019 赵言. All rights reserved.
//  显示Error弹窗方法

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSObject (ShowError)

- (void)showMBErrorView:(NSString *)str;

@end

NS_ASSUME_NONNULL_END
