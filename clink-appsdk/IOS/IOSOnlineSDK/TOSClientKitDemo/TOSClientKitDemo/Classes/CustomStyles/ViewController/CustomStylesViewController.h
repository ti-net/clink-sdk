//
//  CustomStylesViewController.h
//  TOSClientKitDemo
//
//  Created by 言 on 2022/7/5.
//

#import "BaseViewController.h"

NS_ASSUME_NONNULL_BEGIN

@interface CustomStylesViewController : BaseViewController

@property (nonatomic, copy) void (^themeClickEvent)(NSString * theme);

@end

NS_ASSUME_NONNULL_END
