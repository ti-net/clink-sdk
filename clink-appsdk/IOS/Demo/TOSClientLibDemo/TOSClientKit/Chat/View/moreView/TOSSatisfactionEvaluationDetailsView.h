//
//  TOSSatisfactionEvaluationDetailsView.h
//  TOSClientKit
//
//  Created by 言 on 2023/9/28.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import <TOSClientKit/TOSClientKit.h>

NS_ASSUME_NONNULL_BEGIN

@class TOSSatisfactionModel;
@class TOSSatisfactionStarModel;
@class TOSSatisfactionOptionsModel;
@class TOSSatisfactionPopupView;
@interface TOSSatisfactionEvaluationDetailsView : TOSBaseView

@property (nonatomic, weak) TOSSatisfactionPopupView *popupView;

@property (nonatomic, strong) TOSSatisfactionOptionsModel *optionsModel;
@property (nonatomic, strong) NSArray <TOSSatisfactionStarModel *>*star;

@property (nonatomic, strong) TOSSatisfactionStarModel *starModel;

@property (nonatomic, strong) NSMutableArray <NSString *>*tabBarSelect;

@end

NS_ASSUME_NONNULL_END
