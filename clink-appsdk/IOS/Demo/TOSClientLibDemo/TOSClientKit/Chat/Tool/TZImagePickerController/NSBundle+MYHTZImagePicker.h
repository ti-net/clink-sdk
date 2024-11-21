//
//  NSBundle+MYHTZImagePicker.h
//  MYHTZImagePickerController
//
//  Created by 侯力 on 2024/03/15.
//  Copyright © 2016年 侯力. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface NSBundle (MYHTZImagePicker)
+ (NSBundle *)tos_tz_imagePickerBundle;

+ (NSString *)tos_tz_localizedStringForKey:(NSString *)key value:(NSString *)value;
+ (NSString *)tos_tz_localizedStringForKey:(NSString *)key;

@end

