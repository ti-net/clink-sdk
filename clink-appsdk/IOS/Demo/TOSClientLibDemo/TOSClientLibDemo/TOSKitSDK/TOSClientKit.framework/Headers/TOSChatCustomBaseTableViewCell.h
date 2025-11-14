//
//  TOSChatCustomBaseTableViewCell.h
//  TOSClientKit
//
//  Created by 李成 on 2023/6/8.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface TOSChatCustomBaseTableViewCell : UITableViewCell

/// 返回cell的大小(子类需要实现这个方法)
- (CGFloat)cellHeight;

@end

NS_ASSUME_NONNULL_END
