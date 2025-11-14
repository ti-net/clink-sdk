//
//  WeakScriptMessageHandler.h
//  TOSClientKit
//
//  Created by 言 on 2024/9/2.
//  Copyright © 2024 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <WebKit/WebKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface WeakScriptMessageHandler : NSObject <WKScriptMessageHandler>

@property (nonatomic, weak) id<WKScriptMessageHandler> delegate;

- (instancetype)initWithDelegate:(id<WKScriptMessageHandler>)delegate;

@end

NS_ASSUME_NONNULL_END
