//
//  CustomStylesDetailsTableViewCell.m
//  TOSClientKitDemo
//
//  Created by 言 on 2022/7/5.
//

#import "CustomStylesDetailsTableViewCell.h"
#import "CustomStylesDetailsModel.h"

@interface CustomStylesDetailsTableViewCell ()

@property (weak, nonatomic) IBOutlet UILabel *title_top;
@property (weak, nonatomic) IBOutlet UILabel *describe_top;
@property (weak, nonatomic) IBOutlet UILabel *value_top;

@property (weak, nonatomic) IBOutlet UILabel *title_bottom;
@property (weak, nonatomic) IBOutlet UILabel *describe_bottom;
@property (weak, nonatomic) IBOutlet UILabel *value_bottom;

@end

@implementation CustomStylesDetailsTableViewCell

- (void)setWithModel:(id)model {
    CustomStylesDetailsModel *detailsModel = model;
    
    self.title_top.text = detailsModel.title_top?:@"";
    self.describe_top.text = detailsModel.describe_top?:@"";
    self.value_top.text = [NSString stringWithFormat:@"#%@",detailsModel.value_top?:@""];
    
    self.title_bottom.text = detailsModel.title_bottom?:@"";
    self.describe_bottom.text = detailsModel.describe_bottom?:@"";
    self.value_bottom.text = detailsModel.value_bottom?:@"";
    
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
