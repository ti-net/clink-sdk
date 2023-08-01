//
//  HWPanModalContentView.h
//  Pods
//
//  Created by heath wang on 2019/10/17.
//

#import <UIKit/UIKit.h>
#import <HWPanModalPresentable.h>
#import <HWPanModalPresentationUpdateProtocol.h>
#import <UIViewController+LayoutHelper.h>

@class HWPanModalContainerView;

NS_ASSUME_NONNULL_BEGIN

/// when use `HWPanModalContentView`, you should take care of the safe area by yourself.
@interface HWPanModalContentView : UIView <HWPanModalPresentable, HWPanModalPresentationUpdateProtocol, HWPanModalPresentableLayoutProtocol>

/**
 * present in the target view
 * @param view The view which present to. If the view is nil, will use UIWindow's keyWindow.
 */
- (void)presentInView:(nullable UIView *)view;

/**
 * call this method to dismiss contentView directly.
 * @param flag should animate flag
 * @param completion dismiss completion block
 */
- (void)dismissAnimated:(BOOL)flag completion:(void (^)(void))completion;

@end

NS_ASSUME_NONNULL_END
