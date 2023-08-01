//
//  ICChatMessageBaseCell+CustomerUnread.m
//  TIMClientKit
//
//  Created by lianpeng on 2021/5/18.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "ICChatMessageBaseCell+CustomerUnread.h"


@implementation ICChatMessageBaseCell (CustomerUnread)

- (void)BMWCustomerUnreadStatusDisplayWithModel:(TIMMessageModel *)messageModel {
    // (0:未读 1:已读 2:撤回)
   // 消息状态  1.已发送 2.未读 3.已读 4.已撤回 5.已删除

    //判断是否来自于的客户
//    if ([messageModel.message.from containsString:@"customer-"]) {
    if (messageModel.isSender) {
        NSString * preFixTwoByte = [messageModel.message.to substringToIndex:2];
        if ([preFixTwoByte isEqualToString:@"g-"]) {
//            if ([messageModel.message.from containsString:@"customer"] == NO) {
                
            if ([messageModel.message.from containsString:@"customer-customer-customer-customer"]) {

                NSLog(@"message.status == %d",messageModel.message.status);
                if (messageModel.message.status != 3) {
                    if (messageModel.message.deliveryState != ICMessageDeliveryState_Failure) {
                        self.readLabel.text = @"客户未读";
                        self.readLabel.textColor = [UIColor blueColor];
                        NSLog(@"未读");
                    }else{
                        self.readLabel.text = @"";

                    }

                }else if (messageModel.message.status == 3){
                    if (messageModel.message.deliveryState != ICMessageDeliveryState_Failure) {
                        self.readLabel.text = @"客户已读";
                        self.readLabel.textColor = [UIColor lightGrayColor];
                        NSLog(@"已读");
                    }else{
                        self.readLabel.text = @"";

                    }

                }
//                else{
//                    self.readLabel.text = @"";
//                    NSLog(@"没有");
//                }
            }
        }

        
    }
}

@end
