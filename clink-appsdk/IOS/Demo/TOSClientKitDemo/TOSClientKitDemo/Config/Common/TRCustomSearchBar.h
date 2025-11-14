//
//  TRCustomSearchBar.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/9.
//  Copyright © 2019 赵言. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface TRCustomSearchBar : UISearchBar

// searchBar的textField
@property (nonatomic, weak) UITextField *textField;

/**
 清除搜索条以外的控件
 */
- (void)cleanOtherSubViews;

@end

NS_ASSUME_NONNULL_END
