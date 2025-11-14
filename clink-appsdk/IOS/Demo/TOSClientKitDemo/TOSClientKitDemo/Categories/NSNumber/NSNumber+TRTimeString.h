//
//  NSNumber+TRTimeString.h
//  mobileCMS
//
//  Created by 赵言 on 2020/4/3.
//  Copyright © 2020 赵言. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSNumber (TRTimeString)

- (NSString *)getTimestampStringWithFormat:(NSString *)format;

@end

NS_ASSUME_NONNULL_END
