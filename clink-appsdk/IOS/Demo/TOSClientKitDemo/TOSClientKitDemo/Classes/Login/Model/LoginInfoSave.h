//
//  LoginInfoSave.h
//  mobileCMS
//
//  Created by 赵言 on 2020/2/24.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "BaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface LoginInfoSave : BaseModel

- (void)saveData;

/// 企业编号
@property (nonatomic, copy) NSString *enterpriseNumbers;

/// 座席工号
@property (nonatomic, copy) NSString *seatingWorkNumber;

@end

NS_ASSUME_NONNULL_END
