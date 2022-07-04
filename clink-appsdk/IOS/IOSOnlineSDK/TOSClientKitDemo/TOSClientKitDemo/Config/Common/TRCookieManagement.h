//
//  TRCookieManagement.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/11.
//  Copyright © 2019 赵言. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TRCookieManagement : NSObject

//保存cookie
+ (void)saveCookie;

//取出cookie
+ (void)takeOutCookie;

//删除cookie
+ (void)delegateCookie;

@end

NS_ASSUME_NONNULL_END
