//
//  OSEventSatisfactionTagView.h
//  TOSClientKit
//
//  Created by 言 on 2022/8/22.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface OSEventSatisfactionTagView : UIView

@property (weak, nonatomic) IBOutlet UIButton *content;

- (void)setupContent:(NSString *)content;
- (void)didClickTagBtnAction:(BOOL)selected;

@end

NS_ASSUME_NONNULL_END
