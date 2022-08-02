//
//  MineInfoModel.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/26.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "MineInfoModel.h"

static MineInfoModel *mineInfoModel = nil;

@implementation MineInfoModel

+ (MineInfoModel *)shareMineInfoModel {
    return [[self alloc] init];
}

- (instancetype)init {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        mineInfoModel = [super init];
    });
    return mineInfoModel;
}

+ (instancetype)allocWithZone:(struct _NSZone *)zone {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        mineInfoModel = [super allocWithZone:zone];
    });
    return mineInfoModel;
}

- (id)copyWithZone:(NSZone *)zone {
    return mineInfoModel;
}

- (id)mutableCopyWithZone:(NSZone *)zone {
    return mineInfoModel;
}

@end
