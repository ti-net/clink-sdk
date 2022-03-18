//
//  BaseModel.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/4.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "BaseModel.h"

@implementation BaseModel

- (void)encodeWithCoder:(NSCoder*)aCoder {
    [self yy_modelEncodeWithCoder:aCoder];
}

- (id)initWithCoder:(NSCoder*)aDecoder {
    self = [super init];
    return [self yy_modelInitWithCoder:aDecoder];
}

- (id)mutableCopyWithZone:(NSZone *)zone {
    return [self yy_modelCopy];
}

- (nonnull id)copyWithZone:(nullable NSZone *)zone {
    return [self yy_modelCopy];
}

@end
