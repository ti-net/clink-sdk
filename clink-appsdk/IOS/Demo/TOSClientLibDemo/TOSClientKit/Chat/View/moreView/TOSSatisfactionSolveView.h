//
//  TOSSatisfactionSolveView.h
//  TOSClientKit
//
//  Created by 言 on 2023/9/28.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import <TOSClientKit/TOSClientKit.h>

NS_ASSUME_NONNULL_BEGIN

@class YYAnimatedImageView;
@class TOSSatisfactionModel;
@interface TOSSatisfactionSolveView : TOSBaseView

@property (nonatomic, assign) BOOL resolvedSelected;

@property (nonatomic, strong) TOSSatisfactionModel *contentModel;

@end

NS_ASSUME_NONNULL_END
