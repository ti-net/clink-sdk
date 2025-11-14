//
//  STBaseWebViewController.m
//  WeiLiHua
//
//  Created by 孙涛 on 2018/3/27.
//  Copyright © 2018年 孙涛. All rights reserved.
//

#import "STBaseWebViewController.h"
//#import "STLogViewController.h"
//#import <Masonry.h>
@interface STBaseWebViewController ()<WKUIDelegate,
WKNavigationDelegate
>
@property (nonatomic,strong,readwrite) UIBarButtonItem *returnButton;
@property (nonatomic,strong,readwrite) UIBarButtonItem *closeItem;
@end

@implementation STBaseWebViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self deleteWebCache];
    [self createWebViewWithURL:self.urlString];
    [self webViewNeedLongPress];
}

- (void)setConfiguration:(WKWebViewConfiguration *)configuration
{
    _configuration = configuration;
}

- (void)setNotLoadUrlImmediately:(BOOL)notLoadUrlImmediately
{
    _notLoadUrlImmediately = notLoadUrlImmediately;
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
}

- (void)viewWillDisappear:(BOOL)animated
{
    [self.webView stopLoading];
    
    [super viewWillDisappear:animated];
}

- (void)setBackButton
{
    UIButton *backButton = [UIButton buttonWithType:UIButtonTypeCustom];
    backButton.frame = CGRectMake(0, 0, 60, 40);
    [backButton setImage:[UIImage imageNamed:@"login_back"] forState:UIControlStateNormal];
    backButton.contentHorizontalAlignment = UIControlContentHorizontalAlignmentLeft;
    [backButton addTarget:self action:@selector(backToSpecifiedPage) forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *backBarButton = [[UIBarButtonItem alloc]initWithCustomView:backButton];
    self.navigationItem.leftBarButtonItem = backBarButton;
}

- (void)deleteWebCache {
    
    if([[UIDevice currentDevice].systemVersion floatValue] >=9.0) {
        
        NSSet *websiteDataTypes = [WKWebsiteDataStore allWebsiteDataTypes];
        
        NSDate *dateFrom = [NSDate dateWithTimeIntervalSince1970:0];
        
        [[WKWebsiteDataStore defaultDataStore] removeDataOfTypes:websiteDataTypes modifiedSince:dateFrom completionHandler:^{
            
        }];
        
    }else{
        
        NSString *libraryPath = [NSSearchPathForDirectoriesInDomains(NSLibraryDirectory,NSUserDomainMask,YES)objectAtIndex:0];
        
        NSString *cookiesFolderPath = [libraryPath stringByAppendingString:@"/Cookies"];
        
        NSError *errors;
        
        [[NSFileManager defaultManager]removeItemAtPath:cookiesFolderPath error:&errors];
    }
    
}

- (UIRectEdge)edgesForExtendedLayout {
    return UIRectEdgeNone;
}

-(void)createWebViewWithURL:(NSString *)urlString {
    self.progressView = [[UIProgressView alloc] init];
    self.progressView.progressTintColor = [UIColor yellowColor];
    self.progressView.trackTintColor = [UIColor whiteColor];
    self.progressView.frame = CGRectMake(0, 0, self.view.frame.size.width, 1);
    [self.view addSubview:self.progressView];
//    [self.progressView mas_makeConstraints:^(MASConstraintMaker *make) {
//        make.top.left.right.equalTo(self.view);
//        make.height.equalTo(@(1));
//    }];
    
    /**网页*/
    if (self.configuration) {
        self.webView = [[WKWebView alloc] initWithFrame:CGRectZero configuration:self.configuration];
    }else {
        self.webView = [[WKWebView alloc] initWithFrame:CGRectZero];
    }

    self.webView.UIDelegate = self;
    self.webView.navigationDelegate = self;
    self.webView.frame = CGRectMake(0, 1, self.view.frame.size.width, self.view.frame.size.height - 1);

    [self.view addSubview:self.webView];
//    [self.webView mas_makeConstraints:^(MASConstraintMaker *make) {
//        make.top.equalTo(self.progressView.mas_bottom);
//        make.left.right.bottom.equalTo(self.view);
//    }];
    
    [self.webView addObserver:self forKeyPath:@"estimatedProgress" options:NSKeyValueObservingOptionNew|NSKeyValueObservingOptionOld context:nil];
    [self.webView addObserver:self forKeyPath:@"title" options:NSKeyValueObservingOptionNew context:nil];
    if (!_configuration && _notLoadUrlImmediately == NO) {
//        [TRLoadingView showLoadingAddTo:self.view animated:YES];
        if (self.loadLocalFile) {
            NSURL *fileURL = [NSURL fileURLWithPath:urlString];
            [self.webView loadFileURL:fileURL allowingReadAccessToURL:[fileURL URLByDeletingLastPathComponent]];
        } else {
            [self.webView loadRequest:[NSURLRequest requestWithURL:[NSURL URLWithString:urlString]]];
        }
    }
}

- (void)webViewNeedLongPress
{
    if (!_needLongPress) {
        //去掉WKWebView所有的长按手势，这样就可以避免长按出现UIMenuController和UIAlertController，而且不会影响到其它的功能
        for (UIView *scrollView in self.webView.subviews) {
            if ([scrollView isKindOfClass:NSClassFromString(@"WKScrollView")]) {
                for (UIView *contentView in scrollView.subviews) {
                    if ([contentView isKindOfClass:NSClassFromString(@"WKContentView")]) {
                        NSMutableArray *gestureRecognizers = [NSMutableArray arrayWithArray:contentView.gestureRecognizers];
                        //                        NSMutableArray *deleteTapArray = [NSMutableArray arrayWithCapacity:0];
                        for (UIGestureRecognizer *tap in gestureRecognizers) {
                            if ([tap isKindOfClass:[UILongPressGestureRecognizer class]]) {
                                tap.enabled = NO;
                            }
                        }
                        contentView.gestureRecognizers = gestureRecognizers;
                    }
                }
            }
        }
    }
}

//观察者方法
-(void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary<NSString *,id> *)change context:(void *)context{
    if ([keyPath isEqualToString:@"estimatedProgress"]&&object == _webView) {
        [self.progressView setAlpha:1.0f];
        [self.progressView setProgress:_webView.estimatedProgress animated:YES];
        if (_webView.estimatedProgress == 1.0f) {
            [UIView animateWithDuration:0.3 delay:0.3 options:UIViewAnimationOptionCurveEaseInOut animations:^{
                [self.progressView setAlpha:0.0f];
            } completion:^(BOOL finished) {
//                [self.progressView mas_updateConstraints:^(MASConstraintMaker *make) {
//                    make.top.left.right.equalTo(self.view);
//                    make.height.equalTo(@(0));
//                }];
                [self.progressView setProgress:0.0f animated:NO];
            }];
        }
    }else if ([keyPath isEqualToString:@"title"]&&object == _webView){
        self.title = @"";//self.webView.title;
//        [self setDefaultNaviWithTitle:self.webView.title];
    }else{
        [super observeValueForKeyPath:keyPath ofObject:object change:change context:context];
    }
}

//移除观察者
-(void)dealloc{
    [_webView removeObserver:self forKeyPath:@"estimatedProgress"];
    [_webView removeObserver:self forKeyPath:@"title"];
    [_webView setNavigationDelegate:nil];
    [_webView setUIDelegate:nil];
    [_webView stopLoading];
}

#pragma mark - WKNavigationDelegate
- (void)webView:(WKWebView *)webView decidePolicyForNavigationAction:(WKNavigationAction *)navigationAction decisionHandler:(void (^)(WKNavigationActionPolicy))decisionHandler
{
    NSURL *URL = navigationAction.request.URL;
//    MyLog(@"%@\n%@\n%@", URL, URL.absoluteString, URL.relativeString);
    
    NSString *absoluteString = URL.absoluteString;
    
    
    decisionHandler(WKNavigationActionPolicyAllow);
}

- (void)webView:(WKWebView *)webView decidePolicyForNavigationResponse:(WKNavigationResponse *)navigationResponse decisionHandler:(void (^)(WKNavigationResponsePolicy))decisionHandler{

    NSURL *URL = navigationResponse.response.URL;
//    MyLog(@"%@\n%@\n%@", URL, URL.absoluteString, URL.relativeString);
    
    NSString *absoluteString = URL.absoluteString;
    if ([absoluteString containsString:[NSString stringWithFormat:@"h5/success.html"]]) {//单点登录
        decisionHandler(WKNavigationResponsePolicyCancel);
    }
    
    
    if (([absoluteString rangeOfString:@".pdf"].location !=NSNotFound)) {
        //加载pdf
        NSData *data = [NSData dataWithContentsOfURL:navigationResponse.response.URL];
        NSURL *weburl = [NSURL URLWithString:absoluteString];
        [self.webView loadData:data MIMEType:@"application/pdf" characterEncodingName:@"UTF-8" baseURL:weburl];
    }else if (([absoluteString rangeOfString:@".txt"].location !=NSNotFound)) {
        NSString*bodyStr = [NSString stringWithContentsOfURL:navigationResponse.response.URL encoding:NSUTF8StringEncoding error:nil];
        if (!bodyStr) {
            bodyStr = [NSString stringWithContentsOfURL:navigationResponse.response.URL encoding:0x80000632 error:nil];
        }
        if (!bodyStr) {
            bodyStr = [NSString stringWithContentsOfURL:navigationResponse.response.URL encoding:0x80000631 error:nil];
        }
        if (bodyStr) {
            bodyStr = [bodyStr stringByReplacingOccurrencesOfString:@"\n" withString:@"<br/>"];
            [self.webView loadHTMLString:bodyStr baseURL:nil];
        }else{
            [self.webView loadRequest:[NSURLRequest requestWithURL:navigationResponse.response.URL]];
        }
    }
    
    //允许跳转
    decisionHandler(WKNavigationResponsePolicyAllow);
}

- (void)webView:(WKWebView *)webView didFinishNavigation:(WKNavigation *)navigation {
    
//    [TRLoadingView hideLoadingForView:self.view animated:YES];
    // 禁止对web进行放大缩小
    NSString *injectionJSString = @"var script = document.createElement('meta');"
    "script.name = 'viewport';"
    "script.content=\"width=device-width, user-scalable=no\";"
    "document.getElementsByTagName('head')[0].appendChild(script);";
    [webView evaluateJavaScript:injectionJSString completionHandler:nil];
}

-(void)webView:(WKWebView *)webView didFailNavigation:(WKNavigation *)navigation withError:(NSError *)error{
    
}
//
//-(void)webView:(WKWebView *)webView didReceiveAuthenticationChallenge:(NSURLAuthenticationChallenge *)challenge completionHandler:(void (^)(NSURLSessionAuthChallengeDisposition disposition, NSURLCredential * _Nullable credential))completionHandler{
//    
//    if([challenge.protectionSpace.authenticationMethod isEqualToString:NSURLAuthenticationMethodServerTrust])
//    {NSURLCredential *card = [[NSURLCredential alloc]initWithTrust:challenge.protectionSpace.serverTrust];  completionHandler(NSURLSessionAuthChallengeUseCredential,card);
//    }
//}


-(void)checkGoBack{
    
    if (self.webView.canGoBack) {
        self.navigationItem.leftBarButtonItem = nil;
        [self showLeftNaviItem];
    }else{
        self.navigationItem.leftBarButtonItems = nil;
        self.navigationItem.leftBarButtonItem = self.returnButton;
    }
}

-(void)showLeftNaviItem{
    self.navigationItem.leftBarButtonItems = @[self.returnButton,self.closeItem];
}

#pragma mark - push to login

- (void)pushToLoginController
{
//    STLogViewController *loginVC = [STLogViewController new];
//    loginVC.popViewController = self;
//    [self.navigationController pushViewController:loginVC animated:YES];
}

#pragma mark - button click

- (void)backToSpecifiedPage
{
    if ([self.webView canGoBack]) {
        [self.webView goBack];
        [self.webView reload];
    }else {
        [self.navigationController popViewControllerAnimated:YES];
    }
}


- (UIBarButtonItem *)returnButton {
    if (!_returnButton) {
        _returnButton = [[UIBarButtonItem alloc] init];
        UIButton *backButton = [UIButton buttonWithType:UIButtonTypeCustom];
        //        [backButton setTitle:@"返回" forState:UIControlStateNormal];
        [backButton setImage:[UIImage imageNamed:@"login_back"] forState:UIControlStateNormal];
        [backButton setImage:[UIImage imageNamed:@"login_back"] forState:UIControlStateHighlighted];
        [backButton addTarget:self action:@selector(respondsToReturnToBack:) forControlEvents:UIControlEventTouchUpInside];
//        backButton.size = CGSizeMake(40, 40);
        UIView *backBgview = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 40, 40)];
        [backBgview addSubview:backButton];
    }
    return _returnButton;
}

- (UIBarButtonItem *)closeItem {
    if (!_closeItem) {
        _closeItem = [[UIBarButtonItem alloc] init];
        
        UIButton *button = [UIButton buttonWithType:UIButtonTypeCustom];
        [button setTitle:@"关闭" forState:UIControlStateNormal];
        [button addTarget:self action:@selector(respondsToReturnToFind:) forControlEvents:UIControlEventTouchUpInside];
        [button.titleLabel setFont:[UIFont systemFontOfSize:17]];
        [button setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
        [button sizeToFit];
        button.contentHorizontalAlignment = UIControlContentHorizontalAlignmentLeft;
        //        button.contentEdgeInsets = UIEdgeInsetsMake(0, -15, 0, 0);
        button.frame = CGRectMake(0, 0, 40, 40);
        _closeItem.customView = button;
        self.navigationItem.leftBarButtonItem = _closeItem;
    }
    return _closeItem;
}

- (void)respondsToReturnToBack:(UIButton *)sender {
    if ([self.webView canGoBack]) {//判断当前的H5页面是否可以返回
        //如果可以返回，则返回到上一个H5页面，并在左上角添加一个关闭按钮
        [self.webView goBack];
        //        self.navigationItem.leftBarButtonItems = @[self.returnButton, self.closeItem];
    } else {
        //如果不可以返回，则直接:
        [self.navigationController popViewControllerAnimated:YES];
    }
}

- (void)respondsToReturnToFind:(UIBarButtonItem *)sender {
    [self.navigationController popViewControllerAnimated:YES];
}

@end
 
