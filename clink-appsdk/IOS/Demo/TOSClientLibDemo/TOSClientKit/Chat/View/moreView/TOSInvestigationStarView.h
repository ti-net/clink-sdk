//
//  TOSInvestigationStarView.h
//  TOSClientKit
//
//  Created by 李成 on 2023/8/9.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TOSSatisfactionModel.h"

NS_ASSUME_NONNULL_BEGIN

@protocol TOSInvestigationStarViewDelegate <NSObject>

- (void)TOSInvestigationStarViewDidSelectedIndex:(NSInteger)index;

@end


@interface TOSInvestigationStarView : UIView

@property (nonatomic, weak) id <TOSInvestigationStarViewDelegate>                delegate;

@property (nonatomic, strong) NSArray<TOSSatisfactionStarModel *>                * starArray;

@property (nonatomic, assign) CGFloat                width;


@end

NS_ASSUME_NONNULL_END
