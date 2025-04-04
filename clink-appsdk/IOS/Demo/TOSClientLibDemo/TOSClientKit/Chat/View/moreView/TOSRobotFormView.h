//
//  TOSRobotFormView.h
//  TOSClientKit
//
//  Created by 言 on 2024/7/26.
//  Copyright © 2024 YanBo. All rights reserved.
//

#import <TOSClientKit/TOSClientKit.h>
#import "TOSClientLib/CombinationMessage.h"

NS_ASSUME_NONNULL_BEGIN

@interface TOSRobotFormView : TOSBaseView

@property (nonatomic, strong) CombinationRobotFormDataModel *robotFormData;

/// 展示弹窗
- (void)showPopupView;

@end

NS_ASSUME_NONNULL_END
