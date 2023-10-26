//
//  ProtocolViewController.m
//  janus-gateway-ios
//
//  Created by 赵言 on 2020/3/25.
//  Copyright © 2020 MineWave. All rights reserved.
//

#import "ProtocolViewController.h"
#import <WebKit/WebKit.h>

@interface ProtocolViewController ()

@property (strong, nonatomic) WKWebView *webView;

@end

@implementation ProtocolViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.navigationItem.title = @"iOS接口文档";
    
    WKWebViewConfiguration *configuration = [[WKWebViewConfiguration alloc] init];
    
    self.webView = [[WKWebView alloc] initWithFrame:CGRectMake(0, 0, kWindowWidth, kWindowHeight - kNavTop) configuration:configuration];
    [self.view addSubview:self.webView];
    NSString *bundleStr = [[NSBundle mainBundle] pathForResource:@"document" ofType:@"html"];
    [self.webView loadRequest:[NSURLRequest requestWithURL:[NSURL fileURLWithPath:bundleStr]]];
}

@end
