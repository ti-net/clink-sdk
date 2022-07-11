//
//  TOSSessionInfoModel.h
//  TOSClientKit
//
//  Created by 高延波 on 2022/6/23.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TOSSessionInfoModel : NSObject

@property (nonatomic, copy) NSString *enterpriseId;

@property (nonatomic, copy) NSString *mainUniqueId;

@property (nonatomic, strong) NSNumber *startTime;

@property (nonatomic, strong) NSNumber *status;

@property (nonatomic, copy) NSString *visitorId;

@end

NS_ASSUME_NONNULL_END
