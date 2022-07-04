//
//  BaseTableViewCell.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/4.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "BaseTableViewCell.h"

@implementation BaseTableViewCell

+ (instancetype)cellWithTableView:(UITableView *)tableView reuseIdentifie:(NSString *)reuseIdentifie {
    [tableView registerClass:NSClassFromString(reuseIdentifie) forCellReuseIdentifier:reuseIdentifie];
    return [tableView dequeueReusableCellWithIdentifier:reuseIdentifie];
}

+ (instancetype)cellXibWithTableView:(UITableView *)tableView reuseIdentifie:(NSString *)reuseIdentifie {
    [tableView registerNib:[UINib nibWithNibName:reuseIdentifie bundle:nil] forCellReuseIdentifier:reuseIdentifie];
    return [tableView dequeueReusableCellWithIdentifier:reuseIdentifie];
}

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self setupSubviews];
    }
    return self;
}

- (instancetype)initWithCoder:(NSCoder *)coder {
    self = [super initWithCoder:coder];
    if (self) {
        [self setupSubviews];
    }
    return self;
}

- (void)setWithModel:(id)model {
    
    
}

- (void)setupSubviews {
    
}

- (void)layoutSubviews {
    [super layoutSubviews];
    [self defineLayout];
}

- (void)defineLayout {
    
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
