//
//  AppDelegate.h
//  TOSClientKitDemo
//
//  Created by 言 on 2022/6/28.
//

#import <UIKit/UIKit.h>
#import <CoreData/CoreData.h>
#import "Reachability.h"

@interface AppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;
@property (readonly, strong) NSPersistentContainer *persistentContainer;

@property (nonatomic,strong) Reachability *hostReachability;
@property (nonatomic, assign) BOOL networkIsOK;//当前网络状态

+ (AppDelegate *)App;
- (void)saveContext;

@end

