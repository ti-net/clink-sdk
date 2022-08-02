//
//  MineSubHeadImageTableViewCell.m
//  mobileCMS
//
//  Created by YanBo on 2020/1/16.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "MineProfileTableViewCell.h"
#import "MineInfoModel.h"
#import "LoginModel.h"
#import "MineTextTableCellModel.h"
#import "UIImageView+CornerRadius.h"

@interface MineProfileTableViewCell()

@property (weak, nonatomic) IBOutlet UILabel *title;
@property (weak, nonatomic) IBOutlet UIImageView *rightArrow;
//@property (weak, nonatomic) IBOutlet UIImageView *userPic;

@end

@implementation MineProfileTableViewCell

- (void)setWithModel:(id)model {
    MineTextTableCellModel *mineModel = model;
    self.title.text = mineModel.title?:@"";
//    [self.userPic loadImageWithUrl:[NSString stringWithFormat:@"%@/%@?cno=%@",[DomainNameSave shareDomainNameSave].domainName,kGetMinePic,[LoginModel loginModel].cno?:@"0"] placeholderPic:@"mine_userHeader"];
    self.rightArrow.hidden = mineModel.accessoryType.integerValue == UITableViewCellAccessoryNone;
    
}

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
//    [self.userPic zy_cornerRadiusRoundingRect];
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
