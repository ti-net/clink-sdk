//
//  TOSWorkOrderLeaveMessageVC.m
//  TOSClientKit
//
//  Created by 言 on 2024/7/11.
//  Copyright © 2024 YanBo. All rights reserved.
//

#import "TOSWorkOrderLeaveMessageVC.h"
#import "kitUtils.h"
#import <WebKit/WebKit.h>
#import "TIMConstants.h"
#import "NSString+Extension.h"
#import "WeakScriptMessageHandler.h"
#import "UIButton+TIMEnlargeEdge.h"
#import "TOSWorkOrderLeaveMessageWebJSModel.h"
#import "WHToast.h"
#import "TOSWorkOrderSuccessView.h"
#define kTOSRedirect @"redirect"
#define kTOSTinetWebviewInterface @"tinetWebviewInterface"
#define kTOSJSEventSubmitSuccess @"submit_success"
#define kTOSJSEventClose @"close_ticket_plugin"

@interface TOSWorkOrderLeaveMessageVC () <WKUIDelegate, WKNavigationDelegate, WKScriptMessageHandler>

@property (nonatomic, strong) UIView *guideLanguageView;
@property (nonatomic, strong) UIImageView *guideLanguageIcon;
@property (nonatomic, strong) UILabel *guideLanguage;
@property (nonatomic, strong) UIButton *moreBtn;

@property (nonatomic, strong) UIView *contentView;
@property (nonatomic, strong) WKWebView *wkWebView;

@property (nonatomic, strong) WeakScriptMessageHandler *scriptMessageHandler;

@end

@implementation TOSWorkOrderLeaveMessageVC

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.view.backgroundColor = TOSHexColor(0xF5F5F5);
    
    [self.view addSubview:self.guideLanguageView];
    [self.guideLanguageView addSubview:self.guideLanguageIcon];
    [self.guideLanguageView addSubview:self.guideLanguage];
    [self.guideLanguageView addSubview:self.moreBtn];
    
    [self.view addSubview:self.contentView];
    [self.contentView addSubview:self.wkWebView];
    
    
    NSString *url = [NSString stringWithFormat:@"%@&source=livechat_leave_message&pageType=inner&cancelAble=1&authDialog=1",self.chatLeaveMessageMsg.ticketPluginUrl];
    NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:url]
                                             cachePolicy:(NSURLRequestUseProtocolCachePolicy)
                                         timeoutInterval:20.f];
    [self.wkWebView loadRequest:request];
    
    CGSize size = [self.chatLeaveMessageMsg.guideLanguageContent tim_sizeWithMaxWidth:self.guideLanguage.width_sd andFont:self.guideLanguage.font];
    if (size.height <= 44.f) {
        self.moreBtn.hidden = YES;
    } else {
        self.moreBtn.hidden = NO;
    }
}

- (void)didClickMoreBtnAction:(UIButton *)sender  {
    sender.selected = !sender.selected;
    
    if (sender.selected) {
        CGSize size = [self.chatLeaveMessageMsg.guideLanguageContent tim_sizeWithMaxWidth:self.guideLanguage.width_sd andFont:self.guideLanguage.font];
        self.guideLanguage.numberOfLines = 0;
        self.guideLanguageView.frame = CGRectMake(8.f, 8.f, kWindowWidth - 16.f, size.height + 30.f);
        self.guideLanguage.frame = CGRectMake(self.guideLanguageIcon.right_sd + 16.f, 12.f, self.guideLanguageView.width_sd - 32.f - self.guideLanguageIcon.right_sd, size.height);
    } else {
        self.guideLanguage.numberOfLines = 2;
        self.guideLanguageView.frame = CGRectMake(8.f, 8.f, kWindowWidth - 16.f, 74.f);
        self.guideLanguage.frame = CGRectMake(self.guideLanguageIcon.right_sd + 16.f, 12.f, self.guideLanguageView.width_sd - 32.f - self.guideLanguageIcon.right_sd, 44.f);
    }
    
    self.moreBtn.frame = CGRectMake(80.f + self.guideLanguage.width_sd/2 - 6.f, 2.f + self.guideLanguage.bottom_sd, 12.f, 12.f);
    self.contentView.frame = CGRectMake(8.f, self.guideLanguageView.bottom_sd + 8.f, kWindowWidth - 16.f, kWindowHeight - kNavTop - kBottomBarHeight - self.guideLanguageView.bottom_sd - 16.f);
    self.wkWebView.frame = self.contentView.bounds;
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
        
        /// 提交成功
        if ([model.data.eventName isEqualToString:kTOSJSEventSubmitSuccess]) {
            self.navigationController.navigationBarHidden = YES;
            TOSWorkOrderSuccessView * successView = [[TOSWorkOrderSuccessView alloc] initWithFrame:(self.view.bounds)];
            __weak typeof(self) weakself = self;
            successView.completionBlock = ^{
                __strong typeof(weakself) strongself = weakself;
                strongself.navigationController.navigationBarHidden = NO;
                /// present还是push判断返回规则
                if (strongself.navigationController) {
                    [strongself.navigationController popViewControllerAnimated:YES];
                }
                else {
                    [strongself dismissViewControllerAnimated:YES completion:nil];
                }
            };
            [self.view addSubview:successView];
            [self.wkWebView removeFromSuperview];
            
        }
        else {
            if (([message.name isEqualToString:kTOSRedirect] ||
                 [message.name isEqualToString:kTOSTinetWebviewInterface]) &&
                [model.data.eventName isEqualToString:kTOSJSEventClose]) {
                
                /// present还是push判断返回规则
                if (self.navigationController) {
                    [self.navigationController popViewControllerAnimated:YES];
                }
                else {
                    [self dismissViewControllerAnimated:YES completion:nil];
                }
            }
        }
        
    });
}

#pragma mark - 懒加载
- (UIView *)guideLanguageView {
    if (!_guideLanguageView) {
        _guideLanguageView = [[UIView alloc] init];
        _guideLanguageView.backgroundColor = [UIColor whiteColor];
        _guideLanguageView.frame = CGRectMake(8.f, 8.f, kWindowWidth - 16.f, 74.f);
        
        if ([kitUtils isBlankString:self.chatLeaveMessageMsg.guideLanguageContent]) {
            _guideLanguageView.frame = CGRectMake(0.f, 0.f, 0.f, 0.f);
        } else {
            _guideLanguageView.frame = CGRectMake(8.f, 8.f, kWindowWidth - 16.f, 74.f);
        }
        _guideLanguageView.layer.masksToBounds = YES;
        _guideLanguageView.layer.cornerRadius = 8.f;
    }
    return _guideLanguageView;
}

- (UIImageView *)guideLanguageIcon {
    if (!_guideLanguageIcon) {
        _guideLanguageIcon = [[UIImageView alloc] init];
        _guideLanguageIcon.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"LeaveMessage_icon"]];
        _guideLanguageIcon.frame = CGRectMake(16.f, 10.f, 48.f, 48.f);
    }
    return _guideLanguageIcon;
}

- (UILabel *)guideLanguage {
    if (!_guideLanguage) {
        _guideLanguage = [[UILabel alloc] init];
        _guideLanguage.frame = CGRectMake(self.guideLanguageIcon.right_sd + 16.f, 12.f, self.guideLanguageView.width_sd - 32.f - self.guideLanguageIcon.right_sd, 44.f);
        _guideLanguage.text = self.chatLeaveMessageMsg.guideLanguageContent;
        _guideLanguage.textColor = TOSHexColor(0x595959);
        _guideLanguage.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        _guideLanguage.numberOfLines = 2;
    }
    return _guideLanguage;
}

- (UIButton *)moreBtn {
    if (!_moreBtn) {
        _moreBtn = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_moreBtn setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"LeaveMessage_downArrow"]] forState:(UIControlStateNormal)];
        [_moreBtn setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"LeaveMessage_upArrow"]] forState:(UIControlStateSelected)];
        _moreBtn.frame = CGRectMake(80.f + self.guideLanguage.width_sd/2 - 6.f, 2.f + self.guideLanguage.bottom_sd, 12.f, 12.f);
        [_moreBtn addTarget:self action:@selector(didClickMoreBtnAction:) forControlEvents:(UIControlEventTouchUpInside)];
        _moreBtn.selected = NO;
        [_moreBtn setEnlargeEdge:10.f];
    }
    return _moreBtn;
}

- (UIView *)contentView {
    if (!_contentView) {
        _contentView = [[UIView alloc] init];
        _contentView.backgroundColor = [UIColor whiteColor];
        _contentView.frame = CGRectMake(8.f, self.guideLanguageView.bottom_sd + 8.f, kWindowWidth - 16.f, kWindowHeight - kNavTop - kBottomBarHeight - self.guideLanguageView.bottom_sd - 16.f);
        _contentView.layer.masksToBounds = YES;
        _contentView.layer.cornerRadius = 8.f;
    }
    return _contentView;
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
        WKUserContentController *wkUController = [[WKUserContentController alloc] init];
        /// 注册一个 name 为 getAlbumAuthority 的 js 方法 设置处理接收 JS 方法的对象
        // 使用强引用持有 WeakScriptMessageHandler
        self.scriptMessageHandler = [[WeakScriptMessageHandler alloc] initWithDelegate:self];
        [wkUController addScriptMessageHandler:self.scriptMessageHandler name:kTOSRedirect];
        [wkUController addScriptMessageHandler:self.scriptMessageHandler name:kTOSTinetWebviewInterface];
        config.userContentController = wkUController;
        
        _wkWebView = [[WKWebView alloc] initWithFrame:self.contentView.bounds configuration:config];
        _wkWebView.backgroundColor = UIColor.whiteColor;
        _wkWebView.scrollView.keyboardDismissMode = UIScrollViewKeyboardDismissModeOnDrag;
        /// UI 代理
        _wkWebView.UIDelegate = self;
        /// 导航代理
        _wkWebView.navigationDelegate = self;
    }
    return _wkWebView;
}

#pragma mark - 释放
- (void)dealloc {
    
    // 移除所有 scriptMessageHandler
    [self.wkWebView.configuration.userContentController removeScriptMessageHandlerForName:kTOSRedirect];
    [self.wkWebView.configuration.userContentController removeScriptMessageHandlerForName:kTOSTinetWebviewInterface];
    NSLog(@"dealloc");
}

@end
