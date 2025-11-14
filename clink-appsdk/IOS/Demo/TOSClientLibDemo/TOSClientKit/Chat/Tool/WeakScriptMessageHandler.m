//
//  WeakScriptMessageHandler.m
//  TOSClientKit
//
//  Created by 言 on 2024/9/2.
//  Copyright © 2024 YanBo. All rights reserved.
//

#import "WeakScriptMessageHandler.h"

@implementation WeakScriptMessageHandler

- (instancetype)initWithDelegate:(id<WKScriptMessageHandler>)delegate {
    self = [super init];
    if (self) {
        _delegate = delegate;
    }
    return self;
}

// 转发消息给实际的处理器
- (void)userContentController:(WKUserContentController *)userContentController didReceiveScriptMessage:(WKScriptMessage *)message {
    [self.delegate userContentController:userContentController didReceiveScriptMessage:message];
}

- (void)dealloc {
    
    NSLog(@"dealloc");
}

@end
