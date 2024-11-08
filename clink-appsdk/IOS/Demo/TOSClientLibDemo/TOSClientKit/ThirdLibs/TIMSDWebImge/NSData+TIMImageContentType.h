//
// Created by 侯力 on 2024/03/19.
// Copyright (c) 2014 侯力. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSData (TIMImageContentType)

/**
 *  Compute the content type for an image data
 *
 *  @param data the input data
 *
 *  @return the content type as string (i.e. image/jpeg, image/gif)
 */
+ (NSString *)sd_contentTypeForImageData:(NSData *)data;

@end
