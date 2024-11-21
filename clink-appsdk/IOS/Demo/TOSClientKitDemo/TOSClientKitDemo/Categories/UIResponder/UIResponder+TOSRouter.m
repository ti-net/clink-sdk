//
//  UIResponder+TOSRouter.m
//  TOSClientKitDemo
//
//  Created by 言 on 2022/7/21.
//

#import "UIResponder+TOSRouter.h"

@implementation UIResponder (TOSRouter)

- (void)routerEventWithName:(NSString *)eventName userInfo:(id)userInfo {
    [[self nextResponder] routerEventWithName:eventName userInfo:userInfo];
}

@end
