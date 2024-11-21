//
//  TOSWorkOrderLeaveMessageVC.h
//  TOSClientKit
//
//  Created by 言 on 2024/7/11.
//  Copyright © 2024 YanBo. All rights reserved.
//

#import <TOSClientKit/TOSClientKit.h>
#import <TOSClientLib/ChatLeaveMessage.h>

NS_ASSUME_NONNULL_BEGIN

@interface TOSWorkOrderLeaveMessageVC : TOSBaseViewController

@property (nonatomic, strong) ChatLeaveMessage *chatLeaveMessageMsg;

@end

NS_ASSUME_NONNULL_END
