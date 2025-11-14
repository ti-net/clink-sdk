//
//  UITextField+ExtenRange.h
//  janus-gateway-ios
//
//  Created by YanBo on 2019/11/26.
//  Copyright © 2019 MineWave. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface UITextField (ExtenRange)

- (NSRange) selectedRange;
- (void) setSelectedRange:(NSRange) range;

@end

NS_ASSUME_NONNULL_END
