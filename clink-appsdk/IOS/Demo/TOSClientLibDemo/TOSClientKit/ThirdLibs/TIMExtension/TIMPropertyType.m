//
//  TIMPropertyType.m
//  TIMExtension
//
//  Created by mj on 14-1-15.
//  Copyright (c) 2014年 小码哥. All rights reserved.
//

#import "TIMPropertyType.h"
#import "TIMExtension.h"
#import "TIMFoundation.h"
#import "TIMExtensionConst.h"

@implementation TIMPropertyType

static NSMutableDictionary *types_;
+ (void)initialize
{
    types_ = [NSMutableDictionary dictionary];
}

+ (instancetype)cachedTypeWithCode:(NSString *)code
{
    TIMExtensionAssertParamNotNil2(code, nil);
    @synchronized (self) {
        TIMPropertyType *type = types_[code];
        if (type == nil) {
            type = [[self alloc] init];
            type.code = code;
            types_[code] = type;
        }
        return type;
    }
}

#pragma mark - 公共方法
- (void)setCode:(NSString *)code
{
    _code = code;
    
    TIMExtensionAssertParamNotNil(code);
    
    if ([code isEqualToString:TIMPropertyTypeId]) {
        _idType = YES;
    } else if (code.length == 0) {
        _KVCDisabled = YES;
    } else if (code.length > 3 && [code hasPrefix:@"@\""]) {
        // 去掉@"和"，截取中间的类型名称
        _code = [code substringWithRange:NSMakeRange(2, code.length - 3)];
        _typeClass = NSClassFromString(_code);
        _fromFoundation = [TIMFoundation isClassFromFoundation:_typeClass];
        _numberType = [_typeClass isSubclassOfClass:[NSNumber class]];
        
    } else if ([code isEqualToString:TIMPropertyTypeSEL] ||
               [code isEqualToString:TIMPropertyTypeIvar] ||
               [code isEqualToString:TIMPropertyTypeMethod]) {
        _KVCDisabled = YES;
    }
    
    // 是否为数字类型
    NSString *lowerCode = _code.lowercaseString;
    NSArray *numberTypes = @[TIMPropertyTypeInt, TIMPropertyTypeShort, TIMPropertyTypeBOOL1, TIMPropertyTypeBOOL2, TIMPropertyTypeFloat, TIMPropertyTypeDouble, TIMPropertyTypeLong, TIMPropertyTypeLongLong, TIMPropertyTypeChar];
    if ([numberTypes containsObject:lowerCode]) {
        _numberType = YES;
        
        if ([lowerCode isEqualToString:TIMPropertyTypeBOOL1]
            || [lowerCode isEqualToString:TIMPropertyTypeBOOL2]) {
            _boolType = YES;
        }
    }
}
@end
