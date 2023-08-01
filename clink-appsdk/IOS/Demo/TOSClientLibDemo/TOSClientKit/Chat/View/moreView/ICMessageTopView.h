//
//  ICMessageTopView.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/4/11.
//  Copyright © 2016年 gxz All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ICMessageTopView : UIView


- (void)messageSendName:(NSString *)name
               isSender:(BOOL)isSender
                   date:(NSInteger)date;


@end
