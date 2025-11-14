//
//  MineTextTableCellModel.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/24.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "BaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface MineTextTableCellModel : BaseModel

@property (nonatomic, copy) NSString *cellType;
@property (nonatomic, copy) NSString *title;
@property (nonatomic, copy) NSString *value;
@property (nonatomic, strong) NSNumber *selectionStyle;
@property (nonatomic, strong) NSNumber *accessoryType;

/**
 0或不存在是不高亮。     1是高亮
 */
@property (nonatomic, strong) NSNumber *valueHighlighted;

@end

NS_ASSUME_NONNULL_END
