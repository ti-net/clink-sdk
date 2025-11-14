//
//  TOSSatisfactionModel.m
//  TOSClientKit
//
//  Created by 言 on 2022/8/23.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "TOSSatisfactionModel.h"

@implementation TOSSatisfactionStarInfoModel

+ (NSDictionary *)modelContainerPropertyGenericClass {
    return @{
        @"tabBar" : [NSString class],
    };
}

@end


@implementation TOSSatisfactionOptionsModel

+ (NSDictionary *)modelContainerPropertyGenericClass {
    return @{
        @"star" : [TOSSatisfactionStarInfoModel class],
    };
}

@end


@implementation TOSSatisfactionContentModel

+ (NSDictionary *)modelContainerPropertyGenericClass {
    return @{
        @"options" : [TOSSatisfactionOptionsModel class],
    };
}

@end


@implementation TOSSatisfactionStarModel

@end

@implementation TOSSatisfactionChatSatisSolveStateModel

@end

@implementation TOSSatisfactionInvestigationModel

+ (NSDictionary *)modelContainerPropertyGenericClass {
    return @{
        @"star" : [TOSSatisfactionStarModel class],
    };
}

+ (nullable NSDictionary<NSString *, id> *)modelCustomPropertyMapper {
    return @{
             @"rename_id": @"id"
             };
}

@end


@implementation TOSSatisfactionModel

@end
