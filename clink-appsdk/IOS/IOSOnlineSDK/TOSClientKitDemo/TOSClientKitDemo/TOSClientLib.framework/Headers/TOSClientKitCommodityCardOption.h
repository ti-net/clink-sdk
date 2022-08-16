//
//  TOSClientKitCommodityCardOption.h
//  TIMClientLib
//
//  Created by 赵言 on 2022/5/24.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN


@interface TOSClientKitCommodityCardOption : NSObject

/// 副标题
@property (nonatomic, strong) NSString *subTitle;

/// 描述
@property (nonatomic, strong) NSString *descriptions;

/// 价格
@property (nonatomic, strong) NSString *price;

/// 时间
@property (nonatomic, strong) NSString *time;

/// 图片链接，http/https
@property (nonatomic, strong) NSString *img;

/// 运输状态
@property (nonatomic, strong) NSString *status;

/// 附加字段，例：[{"name":"订单号","value":"1234567890"},{"name":"产品类型","value":"电子产品"},{"name":"师傅","value":"金师傅"},{"name":"服务地区","value":"北京市"},{"name":"服务","value":"满意"},{"name":"师傅电话","value":"12345678900"},{"name":"订单状态","value":"已完成"}]
@property (nonatomic, strong) NSArray <NSDictionary <NSString *, NSString *>*>*extraInfo;

@end

NS_ASSUME_NONNULL_END
