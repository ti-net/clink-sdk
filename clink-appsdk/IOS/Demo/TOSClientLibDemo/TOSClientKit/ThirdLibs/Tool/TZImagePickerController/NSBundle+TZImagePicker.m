//
//  NSBundle+TZImagePicker.m
//  MYHTZImagePickerController
//
//  Created by 谭真 on 16/08/18.
//  Copyright © 2016年 谭真. All rights reserved.
//

#import "NSBundle+TZImagePicker.h"
#import "MYHTZImagePickerController.h"

@implementation NSBundle (TZImagePicker)

+ (NSBundle *)tz_imagePickerBundle {
    
//    NSBundle *bundle = [NSBundle bundleForClass:[MYHTZImagePickerController class]];
//    NSURL *url = [bundle URLForResource:@"TOSKitClient" withExtension:@"bundle"];
//    NSLog(@"TIMClient url = %@",[url absoluteString]);
//    bundle = [NSBundle bundleWithURL:url];
    NSBundle *bundle = [NSBundle bundleWithPath:[[NSBundle mainBundle] pathForResource:@"TOSKitClient" ofType:@"bundle"]];
    
    return bundle;
}

+ (NSString *)tz_localizedStringForKey:(NSString *)key {
    return [self tz_localizedStringForKey:key value:@""];
}

+ (NSString *)tz_localizedStringForKey:(NSString *)key value:(NSString *)value {
//    NSBundle *bundle = [TZImagePickerConfig sharedInstance].languageBundle;
    NSDictionary * localizedStringForKeyDic = @{
        @"KEY":@"简体中文",
        @"OK":@"确定",
        @"Back":@"返回",
        @"Done":@"发送",
        @"Sorry":@"抱歉",
        @"Cancel":@"取消",
        @"Setting":@"设置",
        @"Photos":@"照片",
        @"Videos":@"视频",
        @"Preview":@"预览",
        @"Full image":@"原图",
        @"Processing...":@"正在处理...",
        @"No Photos or Videos":@"无照片或视频",
        @"Synchronizing photos from iCloud":@"正在从iCloud同步照片",
        @"iCloud sync failed":@"iCloud无法同步",
        @"Can not use camera":@"无法使用相机",
        @"Can not choose both video and photo":@"选择照片时不能选择视频",
        @"Can not choose both photo and GIF":@"选择照片时不能选择 GIF",
        @"Select the video when in multi state, we will handle the video as a photo":@"多选状态下选择视频，默认将视频当图片发送",
        @"Can not jump to the privacy settings page, please go to the settings page by self, thank you":@"无法跳转到隐私设置页面，请手动前往设置页面，谢谢",
        @"Select a maximum of %zd photos":@"你最多只能选择 %zd 张照片",
        @"Select a minimum of %zd photos":@"请至少选择 %zd 张照片",
        @"Allow %@ to access your album in \"Settings -> Privacy -> Photos\"":@"请在iPhone的\"设置-隐私-照片\"选项中，允许%@访问你的手机相册",
        @"Please allow %@ to access your camera in \"Settings -> Privacy -> Camera\"":@"请在iPhone的\"设置-隐私-相机\"中允许%@访问相机",
    };
    NSString *value1 = localizedStringForKeyDic[key];//[bundle localizedStringForKey:key value:value table:nil];
    NSLog(@"tz_localizedStringForKey key = %@ value = %@",key,value);
    return value1;
}

@end
