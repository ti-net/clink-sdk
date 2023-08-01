//
//  OSRobotWelcomeCell.m
//  TIMClientKit
//
//  Created by apple on 2021/9/1.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "OSRobotWelcomeCell.h"
#import "TIMMessageModel.h"
#import <WebKit/WebKit.h>
//#import "TIMConstants.h"

@interface OSRobotWelcomeCell()<WKNavigationDelegate>
{
    TIMMessageFrame *_modelFrame;
}

@property (nonatomic, strong) WKWebView *webView;
@property (nonatomic, strong) WKWebView *webView2;

@end

@implementation OSRobotWelcomeCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {

        [self.bubbleView addSubview:self.webView];
        [self.bubbleView addSubview:self.webView2];
    }
    return self;
}
#pragma mark - Private Method
- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    if (!modelFrame.model.message.content) {
        return;
    }
    _modelFrame = modelFrame;
    
    NSString *msgId = [[NSUserDefaults standardUserDefaults]
        stringForKey:_modelFrame.model.message.messageId];
    if (msgId == nil) {
        //加载本地html文件
        self.webView.frame = CGRectMake(15, 10, 240, 200);//modelFrame.custContentF;
        [self.webView loadHTMLString:modelFrame.model.message.content baseURL:nil];
        
        self.webView2.frame = CGRectMake(15, 220, 240, 220);//modelFrame.custFileNameF;
        [self.webView2 loadHTMLString:modelFrame.model.message.extra baseURL:nil];
    }
}

#pragma mark lazy
-(WKWebView *)webView{
    if (!_webView) {
        _webView = [[WKWebView alloc]init];
        _webView.navigationDelegate = self;
        _webView.scrollView.bounces = false;
    }
    return _webView;
}

-(WKWebView *)webView2{
    if (!_webView2) {
        _webView2 = [[WKWebView alloc]init];
        _webView2.navigationDelegate = self;
        _webView2.scrollView.bounces = false;
    }
    return _webView2;
}


#pragma mark - webView delegate

- (void)webView:(WKWebView *)webView didFinishNavigation:(WKNavigation *)navigation
{
    
    [[NSUserDefaults standardUserDefaults] setObject:_modelFrame.model.message.messageId forKey:_modelFrame.model.message.messageId];
    [[NSUserDefaults standardUserDefaults] synchronize];
}

@end
