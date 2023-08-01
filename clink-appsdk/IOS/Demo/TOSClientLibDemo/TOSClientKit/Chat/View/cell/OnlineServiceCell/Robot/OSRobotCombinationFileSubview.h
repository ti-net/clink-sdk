//
//  OSRobotCombinationFileSubview.h
//  TOSClientKit
//
//  Created by 言 on 2022/8/1.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "TOSBaseView.h"

NS_ASSUME_NONNULL_BEGIN
@class CombinationMessage;
@interface OSRobotCombinationFileSubview : TOSBaseView

- (void)setWithModel:(CombinationMessage *)model;

@end

NS_ASSUME_NONNULL_END
