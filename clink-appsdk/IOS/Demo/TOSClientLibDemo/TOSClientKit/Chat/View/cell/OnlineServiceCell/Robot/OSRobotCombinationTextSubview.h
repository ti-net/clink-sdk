//
//  OSRobotCombinationTextSubview.h
//  TOSClientKit
//
//  Created by 言 on 2022/8/9.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "TOSBaseView.h"
#import "YYLabel.h"

NS_ASSUME_NONNULL_BEGIN

@class RichTextMessage;
@class CombinationMessage;
@interface OSRobotCombinationTextSubview : TOSBaseView

@property (nonatomic, strong) YYLabel *chatLabel;

- (void)setWithCombinationModel:(CombinationMessage *)model;

@end

NS_ASSUME_NONNULL_END
