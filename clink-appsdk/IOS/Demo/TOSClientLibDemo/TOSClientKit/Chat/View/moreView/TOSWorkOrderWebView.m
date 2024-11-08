//
//  TOSWorkOrderWebView.m
//  TOSClientKit
//
//  Created by 言 on 2024/10/10.
//  Copyright © 2024 YanBo. All rights reserved.
//

#import "TOSWorkOrderWebView.h"
#import "YYKit.h"
#import "kitUtils.h"
#import "TIMConstants.h"
#import "ICMessageConst.h"
#import <WebKit/WebKit.h>
#import "UIResponder+GXRouter.h"
#import "TOSWorkOrderLeaveMessageWebJSModel.h"
#import "NSObject+TIMShowError.h"

#define kTOSRedirect @"redirect"
#define kTOSTinetWebviewInterface @"tinetWebviewInterface"
#define kTOSJSEventSubmitSuccess @"submit_success"
#define kTOSJSEventClose @"close_ticket_plugin"

@interface TOSWorkOrderWebView () <WKUIDelegate, WKNavigationDelegate, WKScriptMessageHandler>

@property (nonatomic, strong) UILabel *titleLabel;
@property (nonatomic, strong) UIButton *closeBtn;

@property (nonatomic, strong) UIView *maskingView;
@property (nonatomic, strong) UIView *contentView;

@property (nonatomic, strong) WKWebView *wkWebView;

@end

@implementation TOSWorkOrderWebView
- (void)setupSubviews {
    [super setupSubviews];
    
    self.backgroundColor = [UIColor clearColor];
    
    [self addSubview:self.maskingView];
    [self addSubview:self.contentView];
    [self.contentView addSubview:self.titleLabel];
    [self.contentView addSubview:self.closeBtn];
    [self.contentView addSubview:self.wkWebView];
    
    self.hidden = YES;
    self.alpha = 0.f;
    self.maskingView.hidden = YES;
    self.contentView.hidden = YES;
}

- (void)defineLayout {
    [super defineLayout];
    
    self.maskingView.frame = self.bounds;
    self.contentView.frame = CGRectMake(0.f, 75.f + kNavTop, self.tosCF_width, self.tosCF_height - 75.f - kNavTop);
    self.titleLabel.frame = CGRectMake(0, 18.f, self.tosCF_width, 26.f);
    self.titleLabel.centerX_sd = self.contentView.centerX_sd;
    self.closeBtn.frame = CGRectMake(self.contentView.tos_width - 20.f - 24.f, 0, 20.f, 20.f);
    self.closeBtn.centerY_sd = self.titleLabel.centerY_sd;
    self.wkWebView.frame = CGRectMake(0, self.titleLabel.tos_bottom, self.contentView.tosCF_width, self.contentView.tosCF_height - kBottomBarHeight - self.titleLabel.tos_bottom);
    [self applyRoundedCorners];
}

- (void)showPopupView {
    self.hidden = NO;
    self.maskingView.hidden = NO;
    self.contentView.hidden = NO;
    self.titleLabel.text = self.titleStr?:@"";
    @weakify(self)
    [UIView animateWithDuration:.3f animations:^{
        @strongify(self)
        self.alpha = 1.f;
    } completion:^(BOOL finished) {
        @strongify(self)
        NSString *url = [NSString stringWithFormat:@"%@&pageType=inner&authDialog=1&primaryColor=#4385FF&source=livechat_customer_message",self.workOrderUrl];
        NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:url]
                                                 cachePolicy:(NSURLRequestUseProtocolCachePolicy)
                                             timeoutInterval:20.f];
        [self.wkWebView loadRequest:request];
    }];
}

- (void)didClickTapMaskingViewAction:(UITapGestureRecognizer *)sender {
    
//    [self routerEventWithName:TinetRouterClickEventRobotFormIntents
//                     userInfo:@{@"content"   : desc,
//                                RouterEventGetSendTextMessageRobotFormIntents : intent
//                              }];
    [self closeView];
}

- (void)didClickCloseBtnAction:(UIButton *)sender {
    [self closeView];
}

- (void)closeView {
    
    // 移除所有 scriptMessageHandler
    [self.wkWebView.configuration.userContentController removeScriptMessageHandlerForName:kTOSRedirect];
    [self.wkWebView.configuration.userContentController removeScriptMessageHandlerForName:kTOSTinetWebviewInterface];
    
    @weakify(self)
    [UIView animateWithDuration:.3f animations:^{
        @strongify(self)
        self.alpha = 0.f;
    } completion:^(BOOL finished) {
        @strongify(self)
        self.hidden = YES;
        self.maskingView.hidden = YES;
        self.contentView.hidden = YES;
        [self removeFromSuperview];
    }];
}

#pragma mark - WKNavigationDelegate
// 页面开始加载时调用
- (void)webView:(WKWebView *)webView didStartProvisionalNavigation:(WKNavigation *)navigation {
    NSLog(@"Page started loading: %@", webView.URL);
}

// 页面加载完成时调用
- (void)webView:(WKWebView *)webView didFinishNavigation:(WKNavigation *)navigation {
    NSLog(@"Page finished loading: %@", webView.URL);
}

// 页面加载失败时调用
- (void)webView:(WKWebView *)webView didFailProvisionalNavigation:(WKNavigation *)navigation withError:(NSError *)error {
    NSLog(@"Page failed to load: %@", error.localizedDescription);
}

/**
 * 创建并返回一个新的WKWebView实例。
 * 该方法是在WKWebView的代理方法中被调用，用于处理在当前WKWebView内点击链接或其他导航动作时，
 * 是否需要在一个新的WKWebView实例中打开目标URL。
 *
 * @param webView 当前的WKWebView实例。
 * @param configuration 新的WKWebView实例将要使用的配置信息。
 * @param navigationAction 导航动作的详细信息，包含了触发此次创建新WebView的请求。
 * @param windowFeatures 新的WKWebView实例的窗口特性，例如是否显示工具栏等。
 * @return 返回一个新的WKWebView实例用于加载navigationAction提供的URL，如果不需要新的WKWebView实例，
 *         则返回nil。
 */
- (WKWebView *)webView:(WKWebView *)webView createWebViewWithConfiguration:(WKWebViewConfiguration *)configuration forNavigationAction:(WKNavigationAction *)navigationAction windowFeatures:(WKWindowFeatures *)windowFeatures {

    WKFrameInfo *frameInfo = navigationAction.targetFrame;
    if (![frameInfo isMainFrame]) {
        if (navigationAction.request) {
            [webView loadRequest:navigationAction.request];
        }
    }
    return nil;
}

#pragma mark - WKScriptMessageHandler
/**
 *  处理WKWebView接收到的JavaScript脚本消息。
 *
 *  @param userContentController WKUserContentController实例，用于与网页中的JavaScript进行交互和消息传递。
 *  @param message               WKScriptMessage对象，包含了从网页JavaScript发送过来的数据和标识符。
 *
 *  此方法在WKWebView中注册的JavaScript执行环境向原生应用发送消息时被调用，
 *  允许原生代码处理并响应来自网页端的消息，实现JavaScript与原生代码之间的双向通信。
 */
- (void)userContentController:(WKUserContentController *)userContentController
      didReceiveScriptMessage:(WKScriptMessage *)message {
    @WeakObj(self);
    dispatch_async(dispatch_get_main_queue(), ^{
        @StrongObj(self);
        TOSWorkOrderLeaveMessageWebJSModel *model = [TOSWorkOrderLeaveMessageWebJSModel yy_modelWithJSON:message.body];
        if ([message.name isEqualToString:kTOSRedirect] ||
             [message.name isEqualToString:kTOSTinetWebviewInterface]) {
            
            [self closeView];
            [self tim_showMBErrorView:@"提交成功"];
        }
    });
}

#pragma mark - 懒加载
- (UILabel *)titleLabel {
    if (!_titleLabel) {
        _titleLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, 64.f, 30.f)];
        _titleLabel.textColor = TOSHexColor(0x262626);
        _titleLabel.textAlignment = NSTextAlignmentCenter;
        _titleLabel.font = [UIFont fontWithName:@"PingFangSC-Medium" size:16.f];
        _titleLabel.numberOfLines = 0;
    }
    return _titleLabel;
}

- (UIButton *)closeBtn {
    if (!_closeBtn) {
        _closeBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        _closeBtn.frame = CGRectMake(0, 0, 20.f, 20.f);
        [_closeBtn setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"workOrderWebView_close"]] forState:(UIControlStateNormal)];
        [_closeBtn addTarget:self action:@selector(didClickCloseBtnAction:) forControlEvents:UIControlEventTouchUpInside];
    }
    return _closeBtn;
}

- (UIView *)maskingView {
    if (!_maskingView) {
        _maskingView = [[UIView alloc] init];
        _maskingView.backgroundColor = TOSHexAColor(0x000000, 0.5f);
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(didClickTapMaskingViewAction:)];
        [_maskingView addGestureRecognizer:tap];
    }
    return _maskingView;
}

- (UIView *)contentView {
    if (!_contentView) {
        _contentView = [[UIView alloc] init];
        _contentView.backgroundColor = TOSHexColor(0xFFFFFF);
    }
    return _contentView;
}

- (void)applyRoundedCorners {
    UIBezierPath *path = [UIBezierPath bezierPathWithRoundedRect:self.contentView.bounds
                                               byRoundingCorners:(UIRectCornerTopLeft | UIRectCornerTopRight)
                                                     cornerRadii:CGSizeMake(16.f, 16.f)];
    CAShapeLayer *maskLayer = [[CAShapeLayer alloc] init];
    maskLayer.frame = self.contentView.bounds;
    maskLayer.path = path.CGPath;
    self.contentView.layer.mask = maskLayer;
}

- (WKWebView *)wkWebView {
    if (!_wkWebView) {
        /// 创建网页配置对象
        WKWebViewConfiguration *config = [[WKWebViewConfiguration alloc] init];
        /// 创建设置对象
        WKPreferences *preference = [[WKPreferences alloc] init];
        /// 设置是否支持 javaScript 默认是支持的
        preference.javaScriptEnabled = YES;
        /// 在 iOS 上默认为 NO，表示是否允许不经过用户交互由 javaScript 自动打开窗口
        preference.javaScriptCanOpenWindowsAutomatically = NO;
        config.preferences = preference;
        
        /// 这个类主要用来做 native 与 JavaScript 的交互管理
        WKUserContentController * wkUController = [[WKUserContentController alloc] init];
        /// 注册一个 name 为 getAlbumAuthority 的 js 方法 设置处理接收 JS 方法的对象
        [wkUController addScriptMessageHandler:self name:kTOSRedirect];
        [wkUController addScriptMessageHandler:self name:kTOSTinetWebviewInterface];
        config.userContentController = wkUController;
        
        WKWebView *wkWebView = [[WKWebView alloc] initWithFrame:CGRectZero configuration:config];
        wkWebView.backgroundColor = UIColor.whiteColor;
        wkWebView.scrollView.keyboardDismissMode = UIScrollViewKeyboardDismissModeOnDrag;
        /// UI 代理
        wkWebView.UIDelegate = self;
        /// 导航代理
        wkWebView.navigationDelegate = self;
        _wkWebView = wkWebView;
    }
    return _wkWebView;
}

#pragma mark - 释放
- (void)dealloc {
    
    // 移除所有 scriptMessageHandler
    [self.wkWebView.configuration.userContentController removeScriptMessageHandlerForName:kTOSRedirect];
    [self.wkWebView.configuration.userContentController removeScriptMessageHandlerForName:kTOSTinetWebviewInterface];
    
    [self.wkWebView stopLoading];
    [self.wkWebView loadHTMLString:@"" baseURL:nil];
    self.wkWebView.navigationDelegate = nil;
    self.wkWebView.UIDelegate = nil;
    [self.wkWebView removeFromSuperview];
    self.wkWebView = nil;
    NSLog(@"dealloc");
}

@end
