//
//  chatLeaveMultilineLineCell.m
//  TIMClientKit
//
//  Created by apple on 2021/12/20.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "chatLeaveMultilineLineCell.h"
#import "TIMConstants.h"

@interface chatLeaveMultilineLineCell ()//<UITextViewDelegate>

@property (nonatomic, strong) UILabel *titleLbl;
@property (nonatomic, strong) UILabel *mustLbl;
@property (nonatomic, strong) NSIndexPath *cellIndex;

@end

@implementation chatLeaveMultilineLineCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        self.contentView.backgroundColor = [UIColor whiteColor];
        [self.contentView addSubview:self.textView];
        [self.contentView addSubview:self.titleLbl];
    }
    return self;
}

-(void)setCellWithTitle:(NSString*)title must:(NSString*)must index:(NSIndexPath *)index{
    NSDictionary *attrs = @{NSFontAttributeName : [UIFont boldSystemFontOfSize:15]};
    CGSize size=[title sizeWithAttributes:attrs];
    if (size.width>0) {
        self.titleLbl.frame = CGRectMake(20, 10, size.width+10, 20);
        
        if ([must isEqualToString:@"1"]) {//是否必填
            self.mustLbl.frame = CGRectMake(size.width+26, 11, 10, 20);
            [self.contentView addSubview:self.mustLbl];
        }
    }
    self.titleLbl.text = title;
    self.cellIndex = index;
    
}

-(UITextView *)textView{
    if (!_textView) {
        _textView = [[UITextView alloc]init];
        _textView.frame = CGRectMake(20, 40, SCREEN_WIDTH - 40, 60);
        _textView.layer.borderWidth = 1.0f;
        _textView.layer.borderColor = TOSHexAColor(0xe9e9ea,1.0).CGColor;
        _textView.layer.cornerRadius = 4.0f;
        _textView.backgroundColor = TOSHexAColor(0xf9fcfc,1.0);
        _textView.textColor = TOSHexAColor(0x999999,1.0);
        _textView.textContainerInset = UIEdgeInsetsMake(5, 10, 5, 10);
//        _textView.delegate = self;

    }
    return _textView;
}


-(UILabel *)titleLbl{
    if (!_titleLbl) {
        _titleLbl = [[UILabel alloc]init];
        _titleLbl.frame = CGRectMake(20, 10, SCREEN_WIDTH - 40, 20);
        _titleLbl.font = [UIFont systemFontOfSize:15];
        _titleLbl.textColor = TOSHexAColor(0x000000,1.0);
        _titleLbl.textAlignment = NSTextAlignmentLeft;
        _titleLbl.text = @"多行留言";
    }
    return _titleLbl;
}

-(UILabel *)mustLbl{
    if (!_mustLbl) {
        _mustLbl = [[UILabel alloc]init];
//        _mustLbl.frame = CGRectMake(20, 10, SCREEN_WIDTH - 40, 20);
//        _mustLbl.font = [UIFont systemFontOfSize:15];
        _mustLbl.textColor = [UIColor redColor];;
        _mustLbl.textAlignment = NSTextAlignmentLeft;
        _mustLbl.text = @"*";
    }
    return _mustLbl;
}

//-(BOOL)textViewShouldBeginEditing:(UITextView *)textView{
//    
//    if (self.multilineDelegate && [self.multilineDelegate respondsToSelector:@selector(scrollerToIndex:)]) {
//        [self.multilineDelegate scrollerToIndex:self.cellIndex];
//    }
//    
//    return YES;
//    
//}


@end
