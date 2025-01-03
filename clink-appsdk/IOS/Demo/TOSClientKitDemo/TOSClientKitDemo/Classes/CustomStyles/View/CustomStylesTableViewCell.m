//
//  CustomStylesTableViewCell.m
//  TOSClientKitDemo
//
//  Created by 言 on 2022/7/5.
//

#import "CustomStylesTableViewCell.h"
#import "CustomStylesModel.h"

@interface CustomStylesTableViewCell ()

@property (weak, nonatomic) IBOutlet UILabel *styleName;
@property (weak, nonatomic) IBOutlet UIImageView *isSelected;
@property (weak, nonatomic) IBOutlet UIImageView *backgroundImage;

@property (strong, nonatomic) CustomStylesModel *styleModel;

@end

@implementation CustomStylesTableViewCell

- (void)setWithModel:(id)model {
    CustomStylesModel *styleModel = model;
    self.styleModel = styleModel;
    
    self.styleName.text = styleModel.styleName?:@"";
    if (styleModel.isSelected.boolValue) {
        self.isSelected.image = [UIImage imageNamed:@"customStyles_ selectBox"];
    } else {
        self.isSelected.image = [UIImage imageNamed:@"customStyles_ selectBox_no"];
    }
    self.backgroundImage.image = [UIImage imageNamed:styleModel.backgroundImage?:@""];
}

- (IBAction)didClickSelectedBoxAction:(UIButton *)sender {
    [self routerEventWithName:kRouterEventClickSelectedBox
                     userInfo:self.styleModel];
}

- (IBAction)didClickDetailsAction:(UIButton *)sender {
    [self routerEventWithName:kRouterEventClickDetails
                     userInfo:self.styleModel];
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
