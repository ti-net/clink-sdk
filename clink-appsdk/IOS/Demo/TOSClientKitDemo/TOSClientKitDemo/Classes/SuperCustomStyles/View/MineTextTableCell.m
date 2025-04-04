//
//  MineTextTableCell.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/26.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "MineTextTableCell.h"
#import "MineTextTableCellModel.h"

@interface MineTextTableCell ()

@property (weak, nonatomic) IBOutlet UILabel *title;
@property (weak, nonatomic) IBOutlet UILabel *value;
@property (weak, nonatomic) IBOutlet UIImageView *rightArrow;

@end

@implementation MineTextTableCell

- (void)setWithModel:(id)model {
    MineTextTableCellModel *mineModel = model;
    self.title.text = mineModel.title?:@"";
    self.value.text = mineModel.value?:@"";
    self.rightArrow.hidden = mineModel.accessoryType.integerValue == UITableViewCellAccessoryNone;
    self.selectionStyle = mineModel.selectionStyle.integerValue;
}

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
