//
//  TIMBaseNavigationController.m
//  TIMClientDemo
//
//  Created by YanBo on 2020/5/13.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "TIMBaseNavigationController.h"
#import "UIButton+EnlargeEdge.h"

@interface TIMBaseNavigationController () <UINavigationControllerDelegate, UIGestureRecognizerDelegate>

@end

@implementation TIMBaseNavigationController
+ (void)initialize {
    //取出设置主题的对象
    UINavigationBar *navBar = [UINavigationBar appearance];
    //标题颜色
    [navBar setTitleTextAttributes:@{NSForegroundColorAttributeName:kHexColor(0x404D5D), NSFontAttributeName: [UIFont fontWithName:kFontNameRegular size:18.f]}];
    
    //左右按钮
    UIBarButtonItem *barButtonItem = [UIBarButtonItem appearance];
    [barButtonItem setTitleTextAttributes:@{NSForegroundColorAttributeName: kHexColor(0x2373D9), NSFontAttributeName: [UIFont fontWithName:kFontNameMedium size:16.f]} forState:UIControlStateNormal];
    [barButtonItem setTitleTextAttributes:@{NSForegroundColorAttributeName: kHexColor(0x2373D9), NSFontAttributeName: [UIFont fontWithName:kFontNameMedium size:16.f]} forState:UIControlStateHighlighted];
    [barButtonItem setTitleTextAttributes:@{NSForegroundColorAttributeName: kHexColor(0x2373D9), NSFontAttributeName: [UIFont fontWithName:kFontNameMedium size:16.f]} forState:UIControlStateSelected];
    [barButtonItem setTitleTextAttributes:@{NSForegroundColorAttributeName: kHexColor(0xA6B5C8), NSFontAttributeName: [UIFont fontWithName:kFontNameMedium size:16.f]} forState:UIControlStateDisabled];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    if ([self respondsToSelector:@selector(interactivePopGestureRecognizer)]) {
        self.interactivePopGestureRecognizer.delegate = self;
    }
}

//当手势开始滑动作用：拦截手势触发
- (BOOL)gestureRecognizerShouldBegin:(UIGestureRecognizer *)gestureRecognizer{
    //子控制器个数只剩下一个(这一个就是根控制器),手势不可用
    BOOL open = self.childViewControllers.count != 1;
    return open;
}

- (void)pushViewController:(UIViewController *)viewController animated:(BOOL)animated {
    if (self.childViewControllers.count > 0) {
        viewController.hidesBottomBarWhenPushed = YES;
    }
    
    [super pushViewController:viewController animated:animated];
    
    //    替换掉leftBarButtonItem
    if (viewController.navigationItem.leftBarButtonItem == nil && [self.viewControllers count] > 1) {
        viewController.navigationItem.leftBarButtonItem = [self customLeftBackButton];
    }
    
    CGRect frame = self.tabBarController.tabBar.frame;
    frame.origin.y = [UIScreen mainScreen].bounds.size.height - frame.size.height;
    self.tabBarController.tabBar.frame = frame;
}

#pragma mark - 自定义返回按钮图片
- (UIBarButtonItem *)customLeftBackButton {
    
    UIView *view = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 30.f, 44.f)];
    UIImage *image = [UIImage imageNamed:@"backPrevViewC"];
    UIButton *backButton = [UIButton buttonWithType:UIButtonTypeCustom];
    backButton.frame = CGRectMake(0, 22.f - image.size.height/2, image.size.width, image.size.height);
    [backButton setBackgroundImage:image
                          forState:UIControlStateNormal];
    [backButton addTarget:self
                   action:@selector(popSelf)
         forControlEvents:UIControlEventTouchUpInside];
    [backButton setEnlargeEdgeWithTop:50.f right:50.f bottom:50.f left:50.f];
    [view addSubview:backButton];
    UIBarButtonItem *backItem = [[UIBarButtonItem alloc] initWithCustomView:view];
    return backItem;
}

#pragma mark - 返回按钮事件(pop)
- (void)popSelf {
    [self popViewControllerAnimated:YES];
}

@end
