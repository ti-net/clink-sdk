//
//  OSEventSatisfactionExpressionView.m
//  TOSClientKit
//
//  Created by 言 on 2022/8/22.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "OSEventSatisfactionExpressionView.h"
#import "TIMConstants.h"

@interface OSEventSatisfactionExpressionView ()

@property (weak, nonatomic) IBOutlet UILabel *content;
@property (weak, nonatomic) IBOutlet UIButton *icon;

@end

@implementation OSEventSatisfactionExpressionView

- (void)setupContent:(NSInteger)index title:(NSString *)title gray:(BOOL)gray {
    self.content.text = [NSString stringWithFormat:@"%@",title];
    NSString *imageStr = [NSString stringWithFormat:@"%@/TOSSatisfaction_expression_%ld",FRAMEWORKS_BUNDLE_PATH,(long)index];
    NSString *imageSelectStr = [NSString stringWithFormat:@"%@/TOSSatisfaction_expression_gray_%ld",FRAMEWORKS_BUNDLE_PATH,(long)index];
    
    [self.icon setImage:[UIImage imageNamed:imageSelectStr] forState:(UIControlStateNormal)];
    [self.icon setImage:[UIImage imageNamed:imageStr] forState:(UIControlStateSelected)];
    self.icon.selected = gray;
}

- (void)didClickIconBtnAction:(BOOL)selected {
    self.icon.selected = selected;
}

@end
