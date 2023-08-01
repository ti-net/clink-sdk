//
//  MainTabBarController.m
//  TIMClientDemo
//
//  Created by YanBo on 2020/3/28.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "MainTabBarController.h"
#import "TIMBaseNavigationController.h"
#import "appUtils.h"
#import <TOSClientKit/TOSClientKit.h>


#define kClassKey   @"rootVCClassString"
#define kTitleKey   @"title"
#define kImgKey     @"imageName"
#define kSelImgKey  @"selectedImageName"

#import "kefuVC.h"


@interface MainTabBarController ()

@end

@implementation MainTabBarController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
  NSArray *childItemsArray = @[
      
                              @{kClassKey  : @"kefuVC",
                                kTitleKey  : @"客服",
                                kImgKey    : @"tabbar_contacts",
                                kSelImgKey : @"tabbar_contactsHL"}
  ];
   
   [childItemsArray enumerateObjectsUsingBlock:^(NSDictionary *dict, NSUInteger idx, BOOL *stop) {
       UIViewController *vc = [NSClassFromString(dict[kClassKey]) new];
       vc.title = dict[kTitleKey];
       TIMBaseNavigationController *nav = [[TIMBaseNavigationController alloc] initWithRootViewController:vc];
       UITabBarItem *item = nav.tabBarItem;
       item.title = dict[kTitleKey];
       item.image = [UIImage imageNamed:dict[kImgKey]];
       item.selectedImage = [[UIImage imageNamed:dict[kSelImgKey]] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
       [item setTitleTextAttributes:@{NSForegroundColorAttributeName : Global_tintColor} forState:UIControlStateSelected];
       [self addChildViewController:nav];
   }];
    
    [TOSClientKit.sharedTOSKit setTIMKitMessageRecvDelegate:self];
}

- (void)onTIMReceiveMessage:(TOSMessage *)message left:(int)left {
    
    NSLog(@"当前的消息类型：%d senderType:%d", message.messageType, message.senderType);
    
    NSLog(@"当前会话状态：%ld", [TOSClientKit.sharedTOSKit getCurrentOnlineStatus]);
    
    
}

- (void)getCurrentOnlineStatus:(TinetChatStatusType)statusType {
    NSLog(@"当前的会话状态：%ld", statusType);
}


- (void)dealloc {
    NSLog(@"tab vc dealloc!!!!!");
}

@end
