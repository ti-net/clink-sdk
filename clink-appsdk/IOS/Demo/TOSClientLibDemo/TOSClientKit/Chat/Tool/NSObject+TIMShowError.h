//
//  NSObject+TIMShowError.h
//  TIMClientKit
//
//  Created by 赵言 on 2020/12/17.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSObject (TIMShowError)

- (void)tim_showMBErrorView:(NSString *)str;

@end

NS_ASSUME_NONNULL_END
