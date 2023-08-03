//
//  ICChatBarFunctionView.h
//  TIMClientKit
//
//  Created by 李成 on 2022/5/20.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ICChatBarItemView.h"

NS_ASSUME_NONNULL_BEGIN

@protocol ICChatBarFunctionViewDelegate <NSObject>

- (void)ICChatBarFunctionViewDidSelect:(NSInteger)index;

@end

@interface ICChatBarFunctionView : UIView

@property (nonatomic, weak) id <ICChatBarFunctionViewDelegate>                delegate;

- (void)addBarItemView:(NSArray <ICChatBarItemView *>*)items;

@end

NS_ASSUME_NONNULL_END
