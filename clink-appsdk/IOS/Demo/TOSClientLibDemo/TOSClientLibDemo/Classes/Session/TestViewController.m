//
//  TestViewController.m
//  TIMClientKitDemo
//
//  Created by 李成 on 2022/5/28.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "TestViewController.h"
#import "kefuVC.h"

@interface TestViewController ()

@end

@implementation TestViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}


- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    kefuVC * kefu = [[kefuVC alloc] init];
    [self.navigationController pushViewController:kefu animated:YES];
}



/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
