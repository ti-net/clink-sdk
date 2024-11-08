//
//  STBaseWebViewController.h
//  WeiLiHua
//
//  Created by 孙涛 on 2018/3/27.
//  Copyright © 2018年 孙涛. All rights reserved.
//

//#import "SuperViewController.h"
#import <WebKit/WebKit.h>
@interface STBaseWebViewController : UIViewController
@property (nonatomic, strong) WKWebView *webView;
@property (nonatomic, strong) UIProgressView *progressView;
@property (nonatomic, copy) NSString *urlString;
@property (nonatomic, assign) BOOL needLongPress;
@property (nonatomic, strong) WKWebViewConfiguration *configuration;
@property (nonatomic, assign) BOOL notLoadUrlImmediately;

@property (nonatomic, assign) BOOL loadLocalFile;

- (void)backToSpecifiedPage;
@end
