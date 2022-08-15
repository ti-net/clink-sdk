//
//  TOSSessionBaseTableViewCell.h
//  TIMClientKit
//
//  Created by YanBo on 2020/5/27.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(NSInteger,CellLineStyle) {
    CellLineStyleDefault,
    CellLineStyleFill,
    CellLineStyleNone
};

@interface TOSSessionBaseTableViewCell : UITableViewCell

@property (nonatomic, assign) CellLineStyle bottomLineStyle;
@property (nonatomic, assign) CellLineStyle topLineStyle;

@property (nonatomic, assign) CGFloat leftFreeSpace; // 低线的左边距

@property (nonatomic, assign) CGFloat rightFreeSpace;

@property (nonatomic, weak) UIView *bottomLine;
@end

NS_ASSUME_NONNULL_END
