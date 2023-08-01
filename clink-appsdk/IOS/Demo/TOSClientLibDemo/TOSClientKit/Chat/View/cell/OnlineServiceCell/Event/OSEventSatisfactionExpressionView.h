//
//  OSEventSatisfactionExpressionView.h
//  TOSClientKit
//
//  Created by 言 on 2022/8/22.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN
@class TOSSatisfactionStarModel;
@interface OSEventSatisfactionExpressionView : UIView

@property (nonatomic, strong) TOSSatisfactionStarModel *obj;

- (void)setupContent:(NSInteger)index title:(NSString *)title gray:(BOOL)gray;

- (void)didClickIconBtnAction:(BOOL)selected;

@end

NS_ASSUME_NONNULL_END
