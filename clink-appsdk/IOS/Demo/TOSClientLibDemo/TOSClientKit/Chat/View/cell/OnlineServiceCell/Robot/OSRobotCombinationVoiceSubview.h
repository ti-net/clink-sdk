//
//  OSRobotCombinationVoiceSubview.h
//  TOSClientKit
//
//  Created by 言 on 2022/8/1.
//  Copyright © 2022 YanBo. All rights reserved.
//

//#import "ICChatMessageBaseCell.h"
#import "TOSBaseView.h"

NS_ASSUME_NONNULL_BEGIN

@class CombinationMessage;
@interface OSRobotCombinationVoiceSubview : TOSBaseView

@property (nonatomic, strong) UILabel     *durationLabel;

- (void)setWithModel:(CombinationMessage *)model;

@end

NS_ASSUME_NONNULL_END
