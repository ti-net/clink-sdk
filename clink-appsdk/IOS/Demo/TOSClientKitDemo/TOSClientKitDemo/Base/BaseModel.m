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
    [self modelEncodeWithCoder:aCoder];
}

- (id)initWithCoder:(NSCoder*)aDecoder {
    self = [super init];
    return [self modelInitWithCoder:aDecoder];
}

- (id)mutableCopyWithZone:(NSZone *)zone {
    return [self modelCopy];
}

- (nonnull id)copyWithZone:(nullable NSZone *)zone {
    return [self modelCopy];
}

@end
