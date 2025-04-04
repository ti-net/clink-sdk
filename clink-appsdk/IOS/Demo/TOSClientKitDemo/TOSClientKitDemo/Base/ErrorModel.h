//
//  ErrorModel.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/12.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "BaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface ErrorModel : BaseModel

@property (nonatomic, copy)   NSString *path;
@property (nonatomic, copy)   NSString *error;
@property (nonatomic, copy)   NSString *message;
@property (nonatomic, strong) NSNumber *status;
@property (nonatomic, copy)   NSString *timestamp;

@end

NS_ASSUME_NONNULL_END
