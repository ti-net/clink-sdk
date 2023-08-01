//
//  AppDelegate.m
//  TOSClientLibDemo
//
//  Created by 言 on 2023/7/5.
//

#import "AppDelegate.h"
#import "LoginViewController.h"
#import "YYKit/YYKit.h"

@interface AppDelegate ()

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    
    //设置window
    self.window  = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    self.window.backgroundColor = [UIColor whiteColor];
    [self.window makeKeyAndVisible];
    self.window.rootViewController = [[LoginViewController alloc] initWithNibName:[LoginViewController className] bundle:nil];
    
    return YES;
}

+ (AppDelegate* )shareAppDelegate {
    return (AppDelegate*)[UIApplication sharedApplication].delegate;
}

@end
