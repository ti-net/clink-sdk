//
//  AppDelegate.h
//  TOSClientLibDemo
//
//  Created by 言 on 2023/7/5.
//

#import <UIKit/UIKit.h>

@interface AppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;

+ (AppDelegate* )shareAppDelegate;

@end

