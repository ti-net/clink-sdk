//
//  MineSwitchTableViewCell.m
//  mobileCMS
//
//  Created by 赵言 on 2020/4/4.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "MineSwitchTableViewCell.h"
#import "MineTextTableCellModel.h"
#import "LoginModel.h"

@interface MineSwitchTableViewCell ()

@property (weak, nonatomic) IBOutlet UILabel *title;
@property (weak, nonatomic) IBOutlet UISwitch *switchBtn;


@property (nonatomic, strong) MineTextTableCellModel *mineModel;

@end

@implementation MineSwitchTableViewCell

- (void)setWithModel:(id)model {
    MineTextTableCellModel *mineModel = model;
    self.title.text = mineModel.title?:@"";
    if (mineModel.value && mineModel.value.length > 0) {
        self.switchBtn.on = [mineModel.value isEqualToString:@"0"] ? NO : YES;
    } else {
        self.switchBtn.on = @(0);
    }
    self.selectionStyle = mineModel.selectionStyle.integerValue;
    
    self.mineModel = mineModel;
}

- (IBAction)didClickSwitchBtnAction:(UISwitch *)sender {
    self.mineModel.value = sender.isOn ? @"1" : @"0";
    [[NSNotificationCenter defaultCenter] postNotificationName:kTOSClientDemoConfigSwitchChange object:self.mineModel];
    if ([self.mineModel.title isEqualToString:@"接听来电"]) {

    } else if ([self.mineModel.title isEqualToString:@"离线接听"]) {    //离线接听

    } else if ([self.mineModel.title isEqualToString:@"分机外呼"]) {
        LoginModel *model = [LoginModel loginModel];
        model.softPhoneSwitch = sender.isOn ? @(1) : @(0);
        [[LoginModel loginModel] saveLoginModel:model];
        
        if (sender.isOn) {
            [TRLoadingView showLoadingAddTo:[AppDelegate App].window animated:YES];
       
        }
    }
}

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
