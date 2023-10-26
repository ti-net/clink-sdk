//
//  UIResponder+TOSRouter.h
//  TOSClientKitDemo
//
//  Created by 言 on 2022/7/21.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface UIResponder (TOSRouter)

// router message and the responder who you want will respond this method
- (void)routerEventWithName:(NSString *)eventName userInfo:(id)userInfo;

@end

NS_ASSUME_NONNULL_END
