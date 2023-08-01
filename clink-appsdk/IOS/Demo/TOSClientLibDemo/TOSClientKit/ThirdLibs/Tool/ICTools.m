//
//  ICTools.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/9/27.
//  Copyright © 2016年 gxz. All rights reserved.
//

#import "ICTools.h"
#import <AVFoundation/AVFoundation.h>

@implementation ICTools

+(BOOL)hasPermissionToGetCamera
{
    BOOL hasPermission = YES;
    AVAuthorizationStatus authStatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeVideo];
    if (authStatus == AVAuthorizationStatusRestricted || authStatus == AVAuthorizationStatusDenied) {
        hasPermission = NO;
    }
    return hasPermission;
}

@end
