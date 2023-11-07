//
//  TOSSatisfactionStatusModel.h
//  TOSClientKit
//
//  Created by 言 on 2022/8/26.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <TOSClientLib/TOSClientLib.h>

NS_ASSUME_NONNULL_BEGIN

@interface TOSSatisfactionStatusModel : TIMLibBaseModel

/// 判断是否评价 1已评价 0或null未评价  -1刚评价
@property (nonatomic, copy) NSString *alreadyInvestigation;

@property (nonatomic, copy) NSString *uniqueId;

/// 满意度评价消息中的会话ID
@property (nonatomic, copy) NSString *mainUniqueId;

@end

NS_ASSUME_NONNULL_END
