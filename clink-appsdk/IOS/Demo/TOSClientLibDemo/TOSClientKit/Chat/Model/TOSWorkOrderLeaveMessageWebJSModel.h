//
//  TOSWorkOrderLeaveMessageWebJSModel.h
//  TOSClientKit
//
//  Created by 言 on 2024/7/11.
//  Copyright © 2024 YanBo. All rights reserved.
//

#import <TOSClientLib/TOSClientLib.h>

NS_ASSUME_NONNULL_BEGIN

@interface TOSWorkOrderLeaveMessageWebJSDataModel : TIMLibBaseModel

@property (nonatomic, copy) NSString *eventName;

@end

@interface TOSWorkOrderLeaveMessageWebJSModel : TIMLibBaseModel

@property (nonatomic, copy) NSString *source;
@property (nonatomic, copy) NSString *event;
@property (nonatomic, strong) TOSWorkOrderLeaveMessageWebJSDataModel *data;

@end

NS_ASSUME_NONNULL_END
