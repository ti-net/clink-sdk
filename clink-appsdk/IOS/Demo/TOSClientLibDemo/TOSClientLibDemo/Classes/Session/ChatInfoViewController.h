//
//  ChatInfoViewController.h
//  TIMClientKitDemo
//
//  Created by 赵言 on 2021/4/29.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <TOSClientKit/TOSClientKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface ChatInfoViewController : TOSCustomerChatVC

/// 是否重写会话结束UI，默认NO
@property (nonatomic, assign) BOOL                isRewriteClose;

@end

NS_ASSUME_NONNULL_END
