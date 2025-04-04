//
//  TOSSendMultiMedia.m
//  TIMClientKit
//
//  Created by lianpeng on 2021/6/7.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "TOSSendMultiMedia.h"
#import "ICMediaManager.h"
#import "kitUtils.h"
#import "UIImage+Extension.h"
#import <TOSClientKit/TOSKit.h>
#import "TIMConstants.h"

@implementation TOSSendMultiMedia

+ (void)TIMSendImageWithPath:(NSString *)imagePath senderID:(NSString *)senderId receiverID:(NSString *)receiverId progress:(void(^)(float progress))progressBlock success:(void (^)(TOSMessage * timMessage))successBlock error:(void (^)(TOSMessage * message, TIMConnectErrorCode nErrorCode, NSString * messageUuId))errorBlock {
    if (imagePath.length == 0) {
        TIMKitLog(@"TIMSendImagePath图片路径无效！");
        return;
    }
    if (senderId.length == 0 || receiverId.length == 0) {
        TIMKitLog(@"TIMSendImagePath senderId或者receiverId不能为空！");
        return;
    }
 
    UIImage *image = [UIImage imageWithContentsOfFile:imagePath];
    NSLog(@"发送图片 path:%@ src:%@ last:%@",imagePath,image,[[imagePath lastPathComponent] stringByDeletingPathExtension]);
    
    // 图片压缩后再上传服务器

    NSData *imageData = UIImageJPEGRepresentation(image,1.0f);
    UIImage *simpleImg = [UIImage simpleImage:image];
    
    NSString *msgId = [kitUtils getMsgUUID];
    
    NSString *filePath = [[ICMediaManager sharedManager] saveImage:simpleImg msgId:msgId picType:[UIImage typeForImageData:imageData]];
//    NSString * sreGetMsgUUID = [[filePath lastPathComponent] stringByDeletingPathExtension];
//    NSLog(@"sreGetMsgUUID == %@<##>",sreGetMsgUUID);
    
    if (simpleImg) {
        TIMSessionType sessionType;
        if([receiverId hasPrefix:@"g-"]) {
            sessionType = TIMSessionType_GROUP_CHAT;
        }else{
            sessionType = TIMSessionType_SINGLE_CHAT;

        }

        TIMImageMessage * imageMsg = [[TIMImageMessage alloc] initMessageWithImageURI:filePath  extra:@""];
        TOSMessage * timMsg = [[TOSMessage alloc] initWithOption:msgId msg_id:@"" type:@"image" senderId:senderId receiverId:receiverId content:imageMsg msg_from:sessionType status:TIMMessageStatus_Sending timestamp:0];
        TIMMessagePushOption * pushOption = [[TIMMessagePushOption alloc] initWithOption:YES title:@"" content:@"[图片]" pushData:nil];
        TIMMessageSendOption * sendOption = [[TIMMessageSendOption alloc] initWithOption:receiverId content:timMsg pushOption:pushOption];
        
        [[TIMClient sharedTIMClient] sendMessage:sendOption  progress:^(float progress) {
            TIMKitLog(@"send image progress = %f",progress);
            progressBlock(progress);
        } success:^(TOSMessage * timMessage){
            TIMKitLog(@"send image success messageId = %@",timMessage.msg_id);
            [[NSNotificationCenter defaultCenter] postNotificationName:KTIMSendMultiMediaImageNotification object:@{@"imagePath":filePath,@"sendSuccess":@(YES),@"receiveId":receiverId,@"timMessage":timMessage}];
            successBlock(timMessage);
        } error:^(TOSMessage *message, TIMConnectErrorCode nErrorCode, NSString * _Nonnull errorDes) {
            TIMKitLog(@"send image fail messageId = %@",errorDes);
            errorBlock(message,nErrorCode,errorDes);
        }];
    }else{
        TIMKitLog(@"该路径图片不存在哦！！！");
    }
}

@end
