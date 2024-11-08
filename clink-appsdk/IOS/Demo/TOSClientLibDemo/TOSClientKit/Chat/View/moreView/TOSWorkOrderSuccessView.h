//
//  TOSWorkOrderSuccessView.h
//  TOSClientKit
//
//  Created by 李成 on 2024/10/9.
//  Copyright © 2024 YanBo. All rights reserved.
//  工单提交成功页面

#import <TOSClientKit/TOSClientKit.h>

NS_ASSUME_NONNULL_BEGIN

typedef void(^BackTouch)(void);

@interface TOSWorkOrderSuccessView : TOSBaseView

@property (nonatomic, copy) BackTouch                completionBlock;

@end

NS_ASSUME_NONNULL_END
