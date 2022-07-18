//
//  DomainNameSave.h
//  mobileCMS
//
//  Created by 赵言 on 2020/3/6.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "BaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface DomainNameSave : BaseModel

+ (DomainNameSave *)shareDomainNameSave;

/// 保存选择的自定义UI样式
@property (nonatomic, assign) NSInteger index;

@end

NS_ASSUME_NONNULL_END
