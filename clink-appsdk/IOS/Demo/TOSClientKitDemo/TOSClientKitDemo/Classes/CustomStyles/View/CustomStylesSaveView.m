//
//  CustomStylesSaveView.m
//  TOSClientKitDemo
//
//  Created by 言 on 2022/7/21.
//

#import "CustomStylesSaveView.h"

@implementation CustomStylesSaveView

- (IBAction)didClickSaveBtnAction:(UIButton *)sender {
    [self routerEventWithName:kRouterEventSaveButton
                     userInfo:@{}];
}

@end
