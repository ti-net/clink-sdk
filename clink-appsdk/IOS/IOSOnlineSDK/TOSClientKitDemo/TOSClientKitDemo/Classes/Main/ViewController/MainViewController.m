//
//  MainViewController.m
//  TOSClientKitDemo
//
//  Created by 言 on 2022/6/29.
//

#import "MainViewController.h"
#import "LoginViewController.h"

@interface MainViewController ()

@end

@implementation MainViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:YES animated:animated];
}

// 接入
- (IBAction)didClickJoinUpBtnAction:(UIButton *)sender {
    LoginViewController *loginVC = [[LoginViewController alloc] initWithNibName:[LoginViewController className] bundle:nil];
    [self.navigationController pushViewController:loginVC animated:YES];
}

//立即体验
- (IBAction)didClickExperienceImmediatelyBtnAction:(UIButton *)sender {
    
}

//自定义样式
- (IBAction)didClickCustomStylesBtnAction:(UIButton *)sender {
    
}

//接入文档
- (IBAction)didClickDocumentBtnAction:(UIButton *)sender {
    
}


@end
