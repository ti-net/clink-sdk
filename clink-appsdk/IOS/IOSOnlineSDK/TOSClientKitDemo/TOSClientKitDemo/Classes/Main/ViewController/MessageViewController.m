//
//  MessageViewController.m
//  TOSClientKitDemo
//
//  Created by 言 on 2022/7/5.
//

#import "MessageViewController.h"

#import "BaiduMapViewController.h"

@interface MessageViewController ()

@end

@implementation MessageViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:NO animated:animated];
}

/// 扩展面板，自定义按钮事件 （需要在子类实现这个方法）
- (void)didClinkCustomExtendBoardItemAction:(TOSKitExtendBoardItemModel *)item {
    
    if (item.type == TOSChatBoxExtendBoardTypeCustom &&
        item.index == 10006) {
        [self.navigationController pushViewController:[[BaiduMapViewController alloc] init] animated:YES];
    }
}

@end
