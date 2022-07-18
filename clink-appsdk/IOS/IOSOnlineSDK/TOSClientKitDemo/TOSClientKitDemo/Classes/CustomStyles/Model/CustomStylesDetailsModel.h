//
//  CustomStylesDetailsModel.h
//  TOSClientKitDemo
//
//  Created by 言 on 2022/7/5.
//

#import "BaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface CustomStylesDetailsModel : BaseModel

@property (nonatomic, copy) NSString *title_top;

@property (nonatomic, copy) NSString *describe_top;

@property (nonatomic, copy) NSString *value_top;

@property (nonatomic, copy) NSString *title_bottom;

@property (nonatomic, copy) NSString *describe_bottom;

@property (nonatomic, copy) NSString *value_bottom;

@end

NS_ASSUME_NONNULL_END
