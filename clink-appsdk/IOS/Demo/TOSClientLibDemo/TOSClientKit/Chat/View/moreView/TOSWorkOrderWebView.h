//
//  TOSWorkOrderWebView.h
//  TOSClientKit
//
//  Created by 言 on 2024/10/10.
//  Copyright © 2024 YanBo. All rights reserved.
//

#import <TOSClientKit/TOSClientKit.h>
#import <UIKit/UIKit.h>
#import "TOSBaseView.h"

NS_ASSUME_NONNULL_BEGIN

@interface TOSWorkOrderWebView : TOSBaseView

@property (nonatomic, copy) NSString *workOrderUrl;
@property (nonatomic, copy) NSString *titleStr;

/// 展示弹窗
- (void)showPopupView;

@end

NS_ASSUME_NONNULL_END
