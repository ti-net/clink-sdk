//
//  BaseModel.h
//  SmartHome
//
//  Created by 赵言 on 2019/7/4.
//  Copyright © 2019 赵言. All rights reserved.
//  所有Model的基类，包含copy方法

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMBaseModel : NSObject <NSCopying, NSMutableCopying>

@end

NS_ASSUME_NONNULL_END
