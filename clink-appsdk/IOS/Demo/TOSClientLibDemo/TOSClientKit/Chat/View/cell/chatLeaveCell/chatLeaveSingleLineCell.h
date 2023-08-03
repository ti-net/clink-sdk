//
//  chatLeaveSingleLineCell.h
//  TIMClientKit
//
//  Created by apple on 2021/12/20.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface chatLeaveSingleLineCell : UITableViewCell

@property (nonatomic, strong) UITextField *textField;
@property (nonatomic, copy) NSString *titleStr;

-(void)setCellWithTitle:(NSString*)title must:(NSString*)must;

@end

NS_ASSUME_NONNULL_END
