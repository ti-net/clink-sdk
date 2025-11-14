//
//  TRCookieManagement.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/11.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "TRCookieManagement.h"

@implementation TRCookieManagement

//保存cookie
+ (void)saveCookie {
    /**
     *  默认的cookies失效时间是直到浏览器关闭,cookie失效,也就是app退出,cookie就失效,但是可以设置失效时间这部分最好交给后台处理,也就是加载的是页面
     如果采用的是默认失效时间,那可以把cookie存储起来,下次打开app,重新写入
     */
    //将cookie存储起来:需要对cookie进行归档处理,转换为NSData,采用NSUserDefaults存储,可以在afn加载成功,或者webview加载完成进行存储
    NSData *cookieData = [NSKeyedArchiver archivedDataWithRootObject:[[NSHTTPCookieStorage sharedHTTPCookieStorage] cookies]];
    //存储归档后的cookie
    [[NSUserDefaults standardUserDefaults] setObject:cookieData forKey:kCookieString];
    [[NSUserDefaults standardUserDefaults] synchronize];
}


//取出cookie
+ (void)takeOutCookie {
    //写入:取出保存的cookie,对取出的cookie进行反归档
    NSUserDefaults *userDeefaules = [NSUserDefaults standardUserDefaults];
    //对取出的cookie进行反归档处理
    NSArray *cookies = [NSKeyedUnarchiver unarchiveObjectWithData:[userDeefaules objectForKey:kCookieString]];
    
    if (cookies) {
        NSLog(@"有cookie");
        //设置cookie
        NSHTTPCookieStorage *cookieStorage = [NSHTTPCookieStorage sharedHTTPCookieStorage];
        for (id cookie in cookies) {
//            NSHTTPCookie *myCookie = (NSHTTPCookie *)cookie;
//            NSLog(@"cookie,name:= %@,value = %@",myCookie.name,myCookie.value);
            [cookieStorage setCookie:(NSHTTPCookie *)cookie];
        }
    } else {
        
        NSLog(@"无cookies");
    }
}


//删除cookie
+ (void)delegateCookie {
    NSHTTPCookieStorage *cookieStorage = [NSHTTPCookieStorage sharedHTTPCookieStorage];
    NSArray *cookies = [[NSHTTPCookieStorage sharedHTTPCookieStorage] cookies];
    for (NSHTTPCookie *temCookie in cookies) {
        [cookieStorage deleteCookie:temCookie];
    }
    [[NSUserDefaults standardUserDefaults] removeObjectForKey:@"cookie"];
    [[NSUserDefaults standardUserDefaults] synchronize];
}


//写入需要的cookie
//+ (void)needCookie {
//    NSMutableDictionary *cookiePropertiesDic = [NSMutableDictionary dictionary];
//
//    [cookiePropertiesDic setObject:@"Baidu" forKey:NSHTTPCookieName];
//    [cookiePropertiesDic setObject:@"99" forKey:NSHTTPCookieValue];
//    [cookiePropertiesDic setObject:@"www.baidu.com" forKey:NSHTTPCookieDomain];
//    [cookiePropertiesDic setObject:@"www.baidu.com" forKey:NSHTTPCookieOriginURL];
//    [cookiePropertiesDic setObject:@"/" forKey:NSHTTPCookiePath];  [cookiePropertiesDic setObject:@"0" forKey:NSHTTPCookieVersion];
//    [cookiePropertiesDic setObject:@"2016-08-14 00:00:00 +0000" forKey:NSHTTPCookieExpires];
//
//    NSHTTPCookie *cookie = [NSHTTPCookie cookieWithProperties:cookiePropertiesDic];
//
//    [[NSHTTPCookieStorage sharedHTTPCookieStorage] setCookie:cookie];
//}


//在webView请求时添加cookie
/**
 *在NSURLRequest *request = [NSURLRequest requestWithURL:requestUrl];
前调用
 */
+ (void)webViewAndCookie:(NSURL *)requestUrl {
    NSArray *cookies = [NSKeyedUnarchiver unarchiveObjectWithData:[[NSUserDefaults standardUserDefaults] objectForKey:kCookieString]];
    NSHTTPCookieStorage *cookieStorage = [NSHTTPCookieStorage sharedHTTPCookieStorage];
    [cookieStorage setCookies:cookies forURL:requestUrl mainDocumentURL:nil];
}

@end
