//
//  AppDelegate.h
//  TIMClientKitDemo
//
//  Created by YanBo on 2020/5/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "MainTabBarController.h"

@interface AppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;
@property (nonatomic, retain) MainTabBarController *tabbarVC;

+ (AppDelegate* )getShareAppDelegate;

@end

