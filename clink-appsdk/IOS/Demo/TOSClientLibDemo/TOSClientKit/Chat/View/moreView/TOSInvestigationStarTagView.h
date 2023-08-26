//
//  TOSInvestigationStarTagView.h
//  TOSClientKit
//
//  Created by 李成 on 2023/8/9.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@protocol TOSInvestigationStarTagViewDelegate <NSObject>

/// 选中
- (void)TOSInvestigationStarTagViewSelected:(NSString *)item;
/// 取消选中
- (void)TOSInvestigationStarTagViewUnSelected:(NSString *)item;

@end


@interface TOSInvestigationStarTagView : UIView

@property (nonatomic, weak) id <TOSInvestigationStarTagViewDelegate>                delegate;

@property (nonatomic, strong) NSArray                * tagArray;

@property (nonatomic, assign) CGFloat                width;

@end

NS_ASSUME_NONNULL_END
