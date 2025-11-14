//
//  TOSRobotFormView.m
//  TOSClientKit
//
//  Created by 言 on 2024/7/26.
//  Copyright © 2024 YanBo. All rights reserved.
//

#import "TOSRobotFormView.h"
#import "YYKit.h"
#import "kitUtils.h"
#import "TIMConstants.h"
#import "ICMessageConst.h"
#import <WebKit/WebKit.h>
#import "UIResponder+GXRouter.h"
#import "TOSWorkOrderLeaveMessageWebJSModel.h"

#define kTOSRedirect @"redirect"
#define kTOSTinetWebviewInterface @"tinetWebviewInterface"
#define kTOSJSEventSubmitSuccess @"submit_success"
#define kTOSJSEventClose @"close_ticket_plugin"

@interface TOSRobotFormView () <WKUIDelegate, WKNavigationDelegate, WKScriptMessageHandler>

@property (nonatomic, strong) UIView *maskingView;
@property (nonatomic, strong) UIView *contentView;

@property (nonatomic, strong) WKWebView *wkWebView;

@end

@implementation TOSRobotFormView

- (void)setupSubviews {
    [super setupSubviews];
    
    self.backgroundColor = [UIColor clearColor];
    
    [self addSubview:self.maskingView];
    [self addSubview:self.contentView];
    [self.contentView addSubview:self.wkWebView];
    
    self.hidden = YES;
    self.alpha = 0.f;
    self.maskingView.hidden = YES;
    self.contentView.hidden = YES;
}

- (void)defineLayout {
    [super defineLayout];
    
    self.maskingView.frame = self.bounds;
    self.contentView.frame = CGRectMake(0.f, 74.f + kNavTop, self.tosCF_width, self.tosCF_height - 74.f - kNavTop);
    self.wkWebView.frame = CGRectMake(0, 0, self.contentView.tosCF_width, self.contentView.tosCF_height - kBottomBarHeight);
    [self applyRoundedCorners];
}

- (void)showPopupView {
    self.hidden = NO;
    self.maskingView.hidden = NO;
    self.contentView.hidden = NO;
    @weakify(self)
    [UIView animateWithDuration:.3f animations:^{
        @strongify(self)
        self.alpha = 1.f;
    } completion:^(BOOL finished) {
        @strongify(self)
        NSString *url = [NSString stringWithFormat:@"%@&source=livechat_robot_ticket&pageType=inner&cancelAble=1&authDialog=1",self.robotFormData.link];
        NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:url]
                                                 cachePolicy:(NSURLRequestUseProtocolCachePolicy)
                                             timeoutInterval:20.f];
        [self.wkWebView loadRequest:request];
    }];
}

- (void)didClickTapMaskingViewAction:(UITapGestureRecognizer *)sender {
    
    __block CombinationRobotFormDataIntentsModel *intentsModel = nil;
    [self.robotFormData.intents enumerateObjectsUsingBlock:^(CombinationRobotFormDataIntentsModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        if (obj.type.integerValue == 1) {   // 取消按钮
            intentsModel = obj;
        }
    }];
    
    NSString *desc = @"";
    NSString *intent = @"";
    if (intentsModel) { // 存在匹配数据
        
        desc = intentsModel.desc;
        intent = intentsModel.intent;
    } else {
        
        desc = @"表单未提交";
        intent = @"";
    }
    
    [self routerEventWithName:TinetRouterClickEventRobotFormIntents
                     userInfo:@{@"content"   : desc,
                                RouterEventGetSendTextMessageRobotFormIntents : intent
                              }];
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
            
            NSInteger selectIndex = 0;
            if ([model.data.eventName isEqualToString:kTOSJSEventClose]) {
                
                selectIndex = 1;
            } else if ([model.data.eventName isEqualToString:kTOSJSEventSubmitSuccess]) {
                
                selectIndex = 2;
            } else {
                
                selectIndex = 0;
            }
            
            __block CombinationRobotFormDataIntentsModel *intentsModel = nil;
            [self.robotFormData.intents enumerateObjectsUsingBlock:^(CombinationRobotFormDataIntentsModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                if (obj.type.integerValue == selectIndex) {
                    intentsModel = obj;
                }
            }];
            
            NSString *desc = @"";
            NSString *intent = @"";
            if (intentsModel) { // 存在匹配数据
                
                desc = intentsModel.desc;
                intent = intentsModel.intent;
            } else {
                
                if (selectIndex == 2) { // 不存在匹配数据，但选择了提交成功事件
                    desc = @"表单已提交";
                    intent = @"";
                } else {                // 不存在匹配数据，但选择了退出事件
                    desc = @"表单未提交";
                    intent = @"";
                }
            }
             
            [self routerEventWithName:TinetRouterClickEventRobotFormIntents
                             userInfo:@{@"content"   : desc,
                                        RouterEventGetSendTextMessageRobotFormIntents : intent
                                      }];
            [self closeView];
        }
    });
}

#pragma mark - 懒加载
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
        _contentView.backgroundColor = TOSHexColor(0xF5F5F5);
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
    
    [self.wkWebView stopLoading];
    [self.wkWebView loadHTMLString:@"" baseURL:nil];
    self.wkWebView.navigationDelegate = nil;
    self.wkWebView.UIDelegate = nil;
    [self.wkWebView removeFromSuperview];
    self.wkWebView = nil;
    NSLog(@"dealloc");
}

@end
