//
//  ICChatBarItemView.h
//  TIMClientKit
//
//  Created by 李成 on 2022/5/20.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>


NS_ASSUME_NONNULL_BEGIN
@class TOSQuickEntryModel;
@interface ICChatBarItemView : UIView

@property (nonatomic, strong) TOSQuickEntryModel                * model;

/// 显示文案内容
@property (nonatomic, strong) UILabel                * contentLabel;

/// 显示文案内容
@property (nonatomic, strong) UILabel                * warnLabel;

@end

NS_ASSUME_NONNULL_END
