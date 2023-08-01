//
//  chatLeaveMultilineLineCell.h
//  TIMClientKit
//
//  Created by apple on 2021/12/20.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@protocol chatLeaveMultilineLineCellDelegate <NSObject>

- (void)scrollerToIndex:(NSIndexPath *)index;

@end


@interface chatLeaveMultilineLineCell : UITableViewCell

@property (nonatomic, strong) UITextView *textView;
@property (nonatomic, copy) NSString *titleStr;
@property (weak, nonatomic) id <chatLeaveMultilineLineCellDelegate> multilineDelegate;


-(void)setCellWithTitle:(NSString*)title must:(NSString*)must index:(NSIndexPath *)index;


@end

NS_ASSUME_NONNULL_END
