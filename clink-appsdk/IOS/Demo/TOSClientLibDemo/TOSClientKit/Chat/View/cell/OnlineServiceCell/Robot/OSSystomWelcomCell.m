//
//  OSSystomWelcomCell.m
//  TIMClientKit
//
//  Created by apple on 2021/9/10.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "OSSystomWelcomCell.h"
#import "TIMMessageModel.h"
#import <WebKit/WebKit.h>

@interface OSSystomWelcomCell()<WKNavigationDelegate>
{
    TIMMessageFrame *_modelFrame;
}

@property (nonatomic, strong) WKWebView *webView;

@end

@implementation OSSystomWelcomCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.bubbleView addSubview:self.webView];
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
        stringForKey:modelFrame.model.message.messageId];
    if (msgId == nil) {
        //加载本地html文件
        self.webView.frame = CGRectMake(15, 10, 240, 200);//modelFrame.custContentF;
        [self.webView loadHTMLString:modelFrame.model.message.content baseURL:nil];
    
        [[NSUserDefaults standardUserDefaults] setObject:modelFrame.model.message.messageId forKey:modelFrame.model.message.messageId];
        [[NSUserDefaults standardUserDefaults] synchronize];
        
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



#pragma mark - webView delegate

- (void)webView:(WKWebView *)webView didFinishNavigation:(WKNavigation *)navigation
{
    
    
}


@end
