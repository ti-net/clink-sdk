//
//  transportDetailsTableCell.h
//  TIMClientKit
//
//  Created by 赵言 on 2022/5/24.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface ICTransportDetailsTableCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UILabel *name;
@property (weak, nonatomic) IBOutlet UILabel *value;


@end

NS_ASSUME_NONNULL_END
