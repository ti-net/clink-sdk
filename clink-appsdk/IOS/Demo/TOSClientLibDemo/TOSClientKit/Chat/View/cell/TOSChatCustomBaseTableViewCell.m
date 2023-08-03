//
//  TOSChatCustomBaseTableViewCell.m
//  TOSClientKit
//
//  Created by 李成 on 2023/6/8.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import "TOSChatCustomBaseTableViewCell.h"

@implementation TOSChatCustomBaseTableViewCell

- (CGFloat)cellHeight {
    return 0.0f;
}

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        self.selectionStyle = UITableViewCellSelectionStyleNone;
        
    }
    return self;
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
