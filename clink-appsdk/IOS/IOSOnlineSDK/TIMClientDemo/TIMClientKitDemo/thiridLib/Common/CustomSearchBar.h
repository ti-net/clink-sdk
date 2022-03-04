//
//  CustomSearchBar.h
//  SmartHome
//
//  Created by 赵言 on 2019/8/30.
//  Copyright © 2019 赵言. All rights reserved.
//  自定义SearchBar

#import "BaseView.h"

NS_ASSUME_NONNULL_BEGIN

@interface CustomSearchBar : UISearchBar

// searchBar的textField
@property (nonatomic, weak) UITextField *textField;

/**
 清除搜索条以外的控件
 */
- (void)cleanOtherSubViews;

@end

NS_ASSUME_NONNULL_END
