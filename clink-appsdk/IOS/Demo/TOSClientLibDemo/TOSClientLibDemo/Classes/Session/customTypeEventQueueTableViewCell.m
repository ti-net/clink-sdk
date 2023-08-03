//
//  customTypeEventQueueTableViewCell.m
//  TOSClientKitDemo
//
//  Created by 李成 on 2023/6/8.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import "customTypeEventQueueTableViewCell.h"

@implementation customTypeEventQueueTableViewCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        self.contentView.backgroundColor = UIColor.yellowColor;
        self.textLabel.text = @"这个是排队消息";
        self.textLabel.textAlignment = NSTextAlignmentCenter;
        self.textLabel.font = [UIFont systemFontOfSize:13];
    }
    return self;
}


- (CGFloat)cellHeight {
    return 60.0f;
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
