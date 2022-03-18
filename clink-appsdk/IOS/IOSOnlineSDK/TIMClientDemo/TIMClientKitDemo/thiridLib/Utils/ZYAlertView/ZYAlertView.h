//
//  ZYAlertView.h
//  CloudDoctorRegionVersion
//
//  Created by 赵言 on 2017/5/19.
//  Copyright © 2017年 DFIIA. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ZYAlertView : UIView

/**
 弹出框

 @param message       提示信息 nil为不显示
 @param options       选项数组
 @param destructive   标红的index
 @param selectedBlock 选择
 @param cancelBlock   取消
 @return 弹出框
 */
- (instancetype)initWithMessage:(NSString *)message
                        options:(NSArray <NSString *>*)options
                    destructive:(NSArray <NSNumber *>*)destructive
                  selectedBlock:(void(^)(NSUInteger indexPath))selectedBlock
                    cancelBlock:(void(^)(void))cancelBlock;

/**
 显示
 */
- (void)show;

@end
