//
//  TRCommonTool.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/9.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "TRCommonTool.h"
#import "LoginModel.h"
#import <CommonCrypto/CommonDigest.h>
#import "MainTabBarController.h"

//#import "CustomFieldModel.h"
//#import "BaseNavigationController.h"

@implementation TRCommonTool

#pragma mark - 电话模块

/// 通话类型
/// @param statusInt 标识
+ (NSString *)callType:(NSInteger)statusInt {
    NSString *statusStr;
    if (statusInt == 1) {
        statusStr = @"人工接听";
    } else if (statusInt == 2) {
        statusStr = @"人工未接听";
    } else if (statusInt == 3) {
        statusStr = @"系统应答";
    } else if (statusInt >= 4 && statusInt <= 13) {
        statusStr = @"系统未应答";
    } else if (statusInt == 31 ||
               statusInt == 40 ||
               statusInt == 50 ||
               statusInt == 51 ||
               statusInt == 32) {
        statusStr = @"客户未接听";
    } else if (statusInt == 30 ||
               statusInt == 42 ||
               statusInt == 41) {
        statusStr = @"座席未接听";
    } else if (statusInt == 33 ||
               statusInt == 43 ||
               statusInt == 52) {
        statusStr = @"双方接听";
    } else {
        statusStr = @"未知";
    }
    return statusStr;
}

#pragma mark - 客户模块工具
/// 客户模块，过滤不展示的控件
/// @param type type字段
+ (BOOL)filterTimeControlWithType:(NSInteger)type {
    switch (type) {    //11、12、13为时间控件，不展示。 14、100暂时不展示
        case 11:
        case 12:
        case 13:
        case 14:
        case 100: {
            return NO;
        }
            break;
            
        default: {
            return YES;
        }
            break;
    }
}

/// 客户模块，过滤不展示的控件
/// @param name name字段
+ (BOOL)filterTimeControlWithName:(NSString *)name {
    if ([name isEqualToString:@"修改人"]) {
        return NO;
    } else if ([name isEqualToString:@"修改时间"]) {
        return NO;
    } else if ([name isEqualToString:@"创建人"]) {
        return NO;
    } else if ([name isEqualToString:@"创建时间"]) {
        return NO;
    } else if ([name isEqualToString:@"来源"]) {
        return NO;
    } else if ([name isEqualToString:@"最后一次联系时间"]) {
        return NO;
    } else {
        return YES;
    }
}

/// 客户模块，获取等级、客户归属、性别的字典文件名
/// @param title 标题名称
+ (NSString *)customerModuleGetFileNameWithTitle:(NSString *)title {
    NSString *fileName;
    if ([title isEqualToString:@"等级"]) {
        fileName = @"LevelType";
    } else if ([title isEqualToString:@"客户归属"]) {
        fileName = @"CustomerShareType";
    } else if ([title isEqualToString:@"性别"]) {
        fileName = @"SexType";
    } else if ([title isEqualToString:@"来源"]) {
        fileName = @"SourceType";
    } else {
        fileName = @"";
    }
    return fileName;
}

/// 客户搜索模块，设置标签样式
/// @param tagView TTGTextTagCollectionView
+ (void)setupTTGTextTagView:(TTGTextTagCollectionView *)tagView {
    
    // Use manual height, update preferredMaxLayoutWidth
    tagView.preferredMaxLayoutWidth = kWindowWidth - 45.f;
    // Alignment
    tagView.alignment = TTGTagCollectionAlignmentFillByExpandingWidth;
    
    // Use manual calculate height
    tagView.manualCalculateHeight = YES;
    
    tagView.enableTagSelection = YES;
    
    tagView.defaultConfig = [self setupTTGTextTagConfig];
}

/// 全局默认标签样式配置
+ (TTGTextTagConfig *)setupTTGTextTagConfig {
    TTGTextTagConfig *defaultConfig = [[TTGTextTagConfig alloc] init];
    defaultConfig.textFont = [UIFont fontWithName:kFontNameRegular size:14.f];
    defaultConfig.textColor = kHexColor(0x8C8C8C);
    defaultConfig.selectedTextColor = [UIColor whiteColor];
    defaultConfig.borderColor = kHexColor(0x8C8C8C);
    defaultConfig.selectedBorderColor = kHexColor(0x2397FF);
    defaultConfig.shadowColor = [UIColor clearColor];
    defaultConfig.backgroundColor = [UIColor whiteColor];
    defaultConfig.selectedBackgroundColor = kHexColor(0x2397FF);
    return defaultConfig;
}


/// 客户模块，获取默认字段对应的key，上传数据
/// @param model CustomFieldModel
//+ (NSString *)customerModuleGetKeyWithModel:(CustomFieldModel *)model {
//    //默认字段字典
//    NSDictionary *defaultField = [NSDictionary readPlistFileWithFileName:@"DefaultFieldType"];
//    return [defaultField by_ObjectForKey:model.name?:@"备注"];
//}

/// 客户模块，搜索功能，处理创建时间的开始和结束数据
/// @param startTime 开始时间   yyyy/MM/dd 00:00:00
/// @param endTime   结束时间   yyyy/MM/dd 23:59:59
+ (NSString *)customerSearchCreationTimeWithStartTime:(NSString *)startTime endTime:(NSString *)endTime {
    return [NSString stringWithFormat:@"%@&%@",startTime,endTime];
}

/// 客户模块，客户详情，回访计划界面，回访状态Label的颜色
/// @param state 回访状态
+ (UIColor *)getAgainVisitStateColor:(NSInteger)state {
    UIColor *color;
    switch (state) {
        case 0:                             //未回访
            color = kHexColor(0x2397FF); //蓝
            break;
        case 1:                             //已超时
            color = kHexColor(0xFF7C65);    //红
            break;
        case 2:                             //已回访
            color = kHexColor(0x999999);    //灰
            break;
        case 3:                             //已取消
            color = kHexColor(0x999999);    //灰
            break;
        default:
            color = kHexColor(0x999999);    //灰
            break;
    }
    return color;
}

#pragma mark - 工单模块
/// 工单模块，优先级Label的颜色
/// @param state 优先级状态
+ (UIColor *)getWorkOrderLevelStateColor:(NSInteger)state {
    UIColor *color;
    switch (state) {
        case 0:                             //低
            color = kHexColor(0x8F8F8F);    //灰
            break;
        case 1:                             //中
            color = kHexColor(0xFB9E6C);    //橙
            break;
        case 2:                             //高
            color = kHexColor(0xEF4848);    //红
            break;
        case 3:                             //紧急
            color = kHexColor(0xEF4848);    //红
            break;
        default:
            color = kHexColor(0x8F8F8F);    //灰
            break;
    }
    return color;
}

/// 工单模块，处理状态Label的颜色
/// @param state 处理状态
+ (UIColor *)getWorkOrderHandleStatusStateColor:(NSInteger)state {
    UIColor *color;
    switch (state) {
        case 0:                             //待领取
            color = kHexColor(0x75D5AB);    //绿
            break;
        case 1:                             //已超时
            color = kHexColor(0xEF4848);    //红
            break;
        case 2:                             //处理中
            color = kHexColor(0xFB9E6C);    //橙
            break;
        case 3:                             //已撤销
            color = kHexColor(0x8F8F8F);    //灰
            break;
        case 4:                             //已完成
            color = kHexColor(0x8F8F8F);    //灰
            break;
        case 5:                             //已关闭
            color = kHexColor(0x8F8F8F);    //灰
            break;
        case 6:                             //我的已完成
            color = kHexColor(0x8F8F8F);    //灰
            break;
        default:
            color = kHexColor(0x8F8F8F);    //灰
            break;
    }
    return color;
}

/// 工单模块-标签颜色
/// @param index 标签
+ (UIColor *)matchingColorWithIndex:(NSInteger)index {
    UIColor *color;
    switch (index) {
        case 1:
            color = kHexColor(0xff0000);
            break;
        case 2:
            color = kHexColor(0x008000);
            break;
        case 3:
            color = kHexColor(0x0000ff);
            break;
        case 4:
            color = kHexColor(0xffff00);
            break;
        case 5:
            color = kHexColor(0x00ffff);
            break;
        case 6:
            color = kHexColor(0x800080);
            break;
        case 7:
            color = kHexColor(0xff6600);
            break;
        case 8:
            color = kHexColor(0x33ff66);
            break;
        case 9:
            color = kHexColor(0xff0099);
            break;
        case 10:
            color = kHexColor(0x999999);
            break;
        default:
            color = kHexColor(0xff0000);
            break;
    }
    return color;
}

#pragma make - 其他
// md5 小写
+ (NSString *)md5:(NSString *)string {
    const char *cStr = [string UTF8String];
    unsigned char digest[CC_MD5_DIGEST_LENGTH];
    CC_MD5(cStr, (CC_LONG)strlen(cStr), digest);
    NSMutableString *result = [NSMutableString stringWithCapacity:CC_MD5_DIGEST_LENGTH * 2];
    for (int i = 0; i < CC_MD5_DIGEST_LENGTH; i++) {
        [result appendFormat:@"%02x", digest[i]];
    }
    return result;
}

// 获取当前控制器
+ (UIViewController *)getCurrentVC {
    UIViewController *result = nil;
    
    UIWindow * window = [[UIApplication sharedApplication] keyWindow];
    if (window.windowLevel != UIWindowLevelNormal) {
        NSArray *windows = [[UIApplication sharedApplication] windows];
        for(UIWindow * tmpWin in windows) {
            if (tmpWin.windowLevel == UIWindowLevelNormal) {
                window = tmpWin;
                break;
            }
        }
    }
    
    UIView *frontView = [[window subviews] objectAtIndex:0];
    id nextResponder = [frontView nextResponder];
    
    if ([nextResponder isKindOfClass:[UIViewController class]]) {
        result = nextResponder;
    } else {
        result = window.rootViewController;
    }
    /*
     *  在此判断返回的视图是不是你的根视图--我的根视图是tabbar
     */
    if ([result isKindOfClass:[MainTabBarController class]]) {
        MainTabBarController *mainTabBarC = (MainTabBarController *)result;
        result = [mainTabBarC selectedViewController];
        result = [result.childViewControllers lastObject];
    }
    return result;
}

/// 秒 -> 时分秒
/// @param totalSeconds 秒
+ (NSString *)timeFormatted:(NSInteger)totalSeconds {
    NSInteger seconds = totalSeconds % 60;
    NSInteger minutes = (totalSeconds / 60) % 60;
    NSInteger hours = totalSeconds / 3600;
    return [NSString stringWithFormat:@"%02ld:%02ld:%02ld",(long)hours, minutes, seconds];
}

/// 秒 -> 时分秒
/// @param totalSeconds 秒
+ (NSString *)callTimeFormatted:(NSInteger)totalSeconds {
    
    NSInteger seconds = totalSeconds % 60;
    NSInteger minutes = (totalSeconds / 60) % 60;
    NSInteger hours = totalSeconds / 3600;
    
    if (totalSeconds > 3600) {
        return [NSString stringWithFormat:@"%02ld:%02ld:%02ld",(long)hours, minutes, seconds];
    } else {
        return [NSString stringWithFormat:@"%02ld:%02ld", minutes, seconds];
    }
}

//+ (void)callPhoneWithModel:(CallInfoModel *)model vc:(BaseViewController *)vc {
//    
//    if ([LoginModel loginModel].softPhoneSwitch.integerValue == 0) {    //软电话开关 0：关闭 1：打开
//        
//        BothCallViewController *bothCallVC = [[BothCallViewController alloc] initWithNibName:[BothCallViewController className] bundle:nil];
//        bothCallVC.callInfoModel = model;
//        bothCallVC.parentVC = vc;
//        
//        [bothCallVC setModalTransitionStyle:UIModalTransitionStyleCrossDissolve];
//        bothCallVC.modalPresentationStyle = UIModalPresentationFullScreen;
//        
//        [vc presentViewController:bothCallVC animated:YES completion:^{
//            if ([XHFloatWindowSingleton Ins].floatVC.minimizeView.timeLength > 0) {
//                [bothCallVC setTime:[XHFloatWindowSingleton Ins].floatVC.minimizeView.timeLength];
//            }
//        }];
//    } else {
//        VoipCallViewController *voipCallVC = [[VoipCallViewController alloc] initWithNibName:[VoipCallViewController className] bundle:nil];
//        voipCallVC.callInfoModel = model;
//        voipCallVC.parentVC = vc;
//        
//        [voipCallVC setModalTransitionStyle:UIModalTransitionStyleCrossDissolve];
//        voipCallVC.modalPresentationStyle = UIModalPresentationFullScreen;
//        
//        [vc presentViewController:voipCallVC animated:YES completion:^{
//            if ([XHFloatWindowSingleton Ins].floatVC.minimizeView.timeLength > 0) {
//                [voipCallVC setTime:[XHFloatWindowSingleton Ins].floatVC.minimizeView.timeLength];
//            }
//        }];
//    }
//}

@end
