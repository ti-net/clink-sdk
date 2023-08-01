//
//  chatLeaveTitleCell.m
//  TIMClientKit
//
//  Created by apple on 2021/12/20.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "chatLeaveTitleCell.h"
#import "TIMConstants.h"

@interface chatLeaveTitleCell ()

@property (nonatomic, strong) UILabel *titleLbl;


@end

@implementation chatLeaveTitleCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
      
        self.contentView.backgroundColor = [UIColor whiteColor];
    }
    return self;
}

-(void)setTitle:(NSString *)title{
    self.titleLbl.text = title;
    
//    NSDictionary *attrs = @{NSFontAttributeName : [UIFont boldSystemFontOfSize:15]};
//    CGSize size=[title sizeWithAttributes:attrs];
    
    self.titleLbl.frame = CGRectMake(20, 10, SCREEN_WIDTH - 40, 60);
    [self.contentView addSubview:self.titleLbl];

}


-(UILabel *)titleLbl{
    if (!_titleLbl) {
        _titleLbl = [[UILabel alloc]init];
        _titleLbl.font = [UIFont systemFontOfSize:15];
        _titleLbl.numberOfLines = 0;
        _titleLbl.textColor = TOSHexAColor(0x94969b,1.0);
        _titleLbl.textAlignment = NSTextAlignmentLeft;
    }
    return _titleLbl;
}

@end
