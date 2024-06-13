//
//  ICMessageHelper.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/4/7.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICMessageHelper.h"
#import "TIMMessageFrame.h"
#import "TIMMessageModel.h"
#import "ICMessageConst.h"
#import "ICRecordManager.h"
#import "ICMediaManager.h"
#import "ICVideoManager.h"
#import "ICFileTool.h"
#import "NSDate+Extension.h"
#import "TIMConstants.h"
#import "kitUtils.h"
#import <TOSClientLib/TIMLibUtils.h>
#import <TOSClientLib/TOSClientLib.h>
#import <TOSClientKit/TOSKit.h>
//#import "VoiceConverter.h"
#import <TOSClientLib/OctoIMParamDefines.h>

#define lastUpdateKey [NSString stringWithFormat:@"%@-%@",[ICUser currentUser].eId,@"LastUpdate"]
#define groupInfoLastUpdateKey [NSString stringWithFormat:@"%@-%@",[ICUser currentUser].eId,@"groupInfoLastUpdate"]
#define directLastUpdateKey [NSString stringWithFormat:@"%@-%@",[ICUser currentUser].eId,@"directLastUpdate"]

@implementation ICMessageHelper


// 创建一条本地消息
+ (TIMMessageFrame *)createMessageFrame:(NSString *)type
                           originalType:(NSString *)originalType
                               content:(NSString *)content
                                 extra:(NSString *)extra
                             auditExtra:(NSString *)auditExtra
                                  path:(NSString *)path
                                urlPath:(NSString *)urlPath
                                  from:(NSString *)from
                                    to:(NSString *)to
                               fileKey:(NSString *)fileKey
                          bigImgFileId:(NSString *)bigImgFileId
                         voiceDuration:(NSNumber *)voiceDuration
                                 msgId:(NSString *)msgId
                               sendTime:(NSTimeInterval)sendTime
                               showTime:(BOOL)showTime
                               picSize:(CGSize)size
                               picType:(NSString *)picType
                              isSender:(BOOL)isSender
              receivedSenderByYourself:(BOOL)receivedSenderByYourself
                                 status:(int)status {
    return  [ICMessageHelper createMessageFrame:type originalType:originalType content:content extra:extra auditExtra:auditExtra path:path urlPath:urlPath from:from to:to fileKey:fileKey bigImgFileId:bigImgFileId voiceDuration:voiceDuration msgId:msgId sendTime:sendTime showTime:showTime picSize:size picType:picType isSender:isSender receivedSenderByYourself:receivedSenderByYourself status:status fullJSONString:nil];
}

// 创建一条本地消息
+ (TIMMessageFrame *)createMessageFrame:(NSString *)type
                           originalType:(NSString *)originalType
                               content:(NSString *)content
                                 extra:(NSString *)extra
                             auditExtra:(NSString *)auditExtra
                                  path:(NSString *)path
                                urlPath:(NSString *)urlPath
                                  from:(NSString *)from
                                    to:(NSString *)to
                               fileKey:(NSString *)fileKey
                          bigImgFileId:(NSString *)bigImgFileId
                         voiceDuration:(NSNumber *)voiceDuration
                                 msgId:(NSString *)msgId
                               sendTime:(NSTimeInterval)sendTime
                               showTime:(BOOL)showTime
                               picSize:(CGSize)size
                               picType:(NSString *)picType
                              isSender:(BOOL)isSender
              receivedSenderByYourself:(BOOL)receivedSenderByYourself
                                 status:(int)status
                         fullJSONString:(NSString *)jsonStr
{
    __block TIMICMessage *message    = [[TIMICMessage alloc] init];
    message.to            = to;
    message.from          = from;
    message.fileKey       = fileKey;
    message.messageId     = msgId;
    message.voiceDuration = voiceDuration;
    // 我默认了一个本机的当前时间，其实没用到，成功后都改成服务器时间了
    message.msgDate       = [NSDate dateWithTimeIntervalSince1970:(sendTime/1000)];
    message.showTime      = showTime;
    message.status        = status;
    message.extra = extra;

    
    TIMMessageModel *model = [[TIMMessageModel alloc] init];
    type = [ICMessageHelper cellTypeWithMessageType:type];
    message.type          = type;
    if ([type isEqualToString:TypeText]) {
        message.content = content;
    } else if ([type isEqualToString:TypeCommodityCard]) {
        message.content = @"[商品卡片]";
        message.extra = content;
        message.type = TypeCommodityCard;
    } else if ([type isEqualToString:TypeCommodityCardDetails]) {
        message.content = @"[商品卡片详情]";
        message.extra = content;
        message.type = TypeCommodityCardDetails;
    } else if ([type isEqualToString:TypeLogisticsCard]) {
        message.content = @"[物流卡片详情]";
        message.extra = content;
        message.type = TypeLogisticsCard;
    } else if ([type isEqualToString:TypeSmallProgramCard]) {
        message.content = @"[小程序卡片]";
        message.extra = content;
        message.type = TypeSmallProgramCard;
    } else if ([type isEqualToString:TypePic]) {
        message.content = @"[图片]";
        NSDictionary *dic = [TIMLibUtils dictionaryWithJsonString:jsonStr];
        NSDictionary *subDic = dic[@"content"];
        model.thumbURL = subDic[@"thumbnailImageURL"];
        NSLog(@"mode.thumbURL == %@",model.thumbURL);
        
    } else if ([type isEqualToString:TypeVoice]) {
        message.content = @"[语音]";
    } else if ([type isEqualToString:TypeVideo]) {
        message.content = @"[视频]";
    } else if ([type isEqualToString:TypeFile]) {
        message.content = content;
    } else if ([type isEqualToString:TypeCustom]) {
        message.content = content;
    } else if ([type isEqualToString:TypeCustomFile]) {
        message.content = content;
    } else if ([type isEqualToString:TypeInvestigation] ||
               [type isEqualToString:TypeSatisfactionPopup]) {
        message.content = content;
    } else if ([type isEqualToString:TypeSystem]) {
        message.content = content;
    } else if ([type isEqualToString:TypeRevoke]) {
        if (isSender) {
            NSTimeInterval currentTime = [[NSDate date] timeIntervalSince1970];
            if (([originalType isEqualToString:@"text"] ||
                [originalType isEqualToString:TypeText]) &&
                content &&
                [content isKindOfClass:[NSString class]] &&
                ![content isEqualToString:@""] &&
                currentTime - sendTime/1000 < 24*60*60) {
                message.content = [NSString stringWithFormat:@"你撤回了一条消息 %@",kTIMRecalledMessageAgainEdit];
                message.revokeContent = content;
            } else {
                message.content = @"对方撤回了一条消息";
            }
        } else {
            message.content = @"对方撤回了一条消息";//[NSString stringWithFormat:@"\"%@\"撤回了一条消息",message.senderName];
        }
    }else if ([type isEqualToString:TypeEventQueue]) {//排队消息
        message.content = content;//@"排队中，您当前排在第1位，拍到将自动接入";
    } else if ([type isEqualToString:TypeUnsupport]) {
        message.content = kTIMUnsupportMessageCellType;
    }else if ([type isEqualToString:TypeRobotCombination]) {//机器人组合类型消息14
        message.content = content;
        message.extra = extra;
                
    }else if ([type isEqualToString:TypeRobotCombinationList]) {
        message.content = content;

        model.combinationTitle = @"";
        model.combinationSubTitle = @"";
        model.combinationNum = 0;
        
        NSData* data = [message.content dataUsingEncoding:NSUTF8StringEncoding];
        id jsonObject = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingAllowFragments error:nil];
        if ([jsonObject isKindOfClass:[NSDictionary class]]) {
            model.combinationTitle = jsonObject[@"text"];
            if ([kitUtils isBlankString:model.combinationTitle]) {
                model.combinationTitle = @"";
            }
            NSArray*cards = jsonObject[@"cards"];
            if (cards != nil && cards.count>0) {
                model.combinationNum = cards.count;
            }
        }

    }else if ([type isEqualToString:TypeRobotWelcome]) {//新增机器人欢迎消息
        message.content = content;
        message.extra = extra;
    } else if ([type isEqualToString:GXRichText]) {
        message.content = content;
        message.extra = extra;
    } else if ([type isEqualToString:TypeTextTag]) {
        message.content = content;
        message.extra = extra;
    } else if ([type isEqualToString:TypeUnknown]) {
        message.content = content;
    } else {
        message.content = content;
    }
    
    message.contentSrc    = auditExtra;
    message.bigImageFileId = bigImgFileId;
    model.isSender        = isSender;
    model.mediaPath       = path;
    model.urlPath         = urlPath;
    
    if (isSender) {
        message.deliveryState = ICMessageDeliveryState_Delivering;
    } else {
        message.deliveryState = ICMessageDeliveryState_Delivered;
    }
    if (receivedSenderByYourself) { // 接收到得信息是自己发的
        message.deliveryState = ICMessageDeliveryState_Delivered;
        model.isSender        = YES;
    }
    if ([type isEqualToString:TypeRevoke] || [type isEqualToString:TypeUnsupport]) {
        message.deliveryState = ICMessageDeliveryState_Delivered;
    }
    model.message = message;
    model.picWidth = size.width;
    model.picHeight = size.height;
    model.picType = picType;


    TIMMessageFrame *modelF = [[TIMMessageFrame alloc] init];
    modelF.model = model;
    modelF.tabBarSelect = [NSMutableArray array];
    return modelF;
}


+ (TIMMessageFrame *)createMessageMeReceiverFrame:(NSString *)type
                               content:(NSString *)content
                                  path:(NSString *)path
                                  from:(NSString *)from
                               fileKey:(NSString *)fileKey
{
    TIMICMessage *message = [[TIMICMessage alloc] init];
    message.type       = type;
    TIMMessageModel *model = [[TIMMessageModel alloc] init];
    message.fileKey    = fileKey;
    model.isSender = NO;
    message.content    = content;
    model.mediaPath    = path;
    message.deliveryState = ICMessageDeliveryState_Delivered;
    model.message = message;
    TIMMessageFrame *modelF = [[TIMMessageFrame alloc] init];
    modelF.model = model;
    return modelF;
}

+ (TIMMessageFrame *)createTimeMessageFrame:(NSString *)type
                               content:(NSString *)content
                                  path:(NSString *)path
                                  from:(NSString *)from
                                    to:(NSString *)to
                               fileKey:(NSString *)fileKey
                              isSender:(BOOL)isSender
              receivedSenderByYourself:(BOOL)receivedSenderByYourself
{
    TIMICMessage *message    = [[TIMICMessage alloc] init];
    message.to            = to;
    message.from          = from;
    message.fileKey       = fileKey;
    // 我默认了一个本机的当前时间，其实没用到，成功后都改成服务器时间了
//    message.date          = [ICMessageHelper currentMessageTime];
    TIMMessageModel *model = [[TIMMessageModel alloc] init];
    type = [self cellTypeWithMessageType:type];
    message.type          = type;
    if ([type isEqualToString:TypeText]) {
        message.content = content;
    } else if ([type isEqualToString:TypePic]) {
        message.content = @"[图片]";
    } else if ([type isEqualToString:TypeVoice]) {
        message.content = @"[语音]";
    } else if ([type isEqualToString:TypeVideo]) {
        message.content = @"[视频]";
    } else if ([type isEqualToString:TypeFile]) {
        message.content = @"[文件]";
    } else if ([type isEqualToString:TypeSystem]) {
        message.content = content;
    } else if ([type isEqualToString:TypeRevoke]) {
        if (isSender) {
            message.content = @"你撤回了一条消息";
        } else {
            message.content = [NSString stringWithFormat:@"\"%@\"撤回了一条消息",message.senderName];
        }
    } else if ([type isEqualToString:TypeUnsupport]) {
        message.content = kTIMUnsupportMessageCellType;
    }
    model.isSender        = isSender;
    model.mediaPath       = path;
    if (isSender) {
        message.deliveryState = ICMessageDeliveryState_Delivering;
    } else {
        message.deliveryState = ICMessageDeliveryState_Delivered;
    }
    if (receivedSenderByYourself) { // 接收到得信息是自己发的
        message.deliveryState = ICMessageDeliveryState_Delivered;
        model.isSender        = YES;
    }
    model.message = message;
    TIMMessageFrame *modelF = [[TIMMessageFrame alloc] init];
    modelF.model = model;
    return modelF;
}


/**
 *  创建一条发送消息
 *
 *  @param type    消息类型
 *  @param content 消息文本内容，其它类型的类型名称:[图片]
 *  @param fileKey 音频文件的fileKey
 *  @param from    发送者
 *  @param to      接收者
 *  @param lnk     连接地址URL,图片格式,文件名称 （目前没用到）
 *  @param status  消息状态 （目前没用到）
 *
 *  @return 发送的消息
 */
//+ (TIMICMessage *)createSendMessage:(NSString *)type
//                         content:(NSString *)content
//                         fileKey:(NSString *)fileKey
//                            from:(NSString *)from
//                              to:(NSString *)to
//                             lnk:(NSString *)lnk
//                          status:(NSString *)status
//{
//    TIMICMessage *message    = [[TIMICMessage alloc] init];
//    message.from          = from;
//    message.to            = to;
//    message.content       = content;
//    message.fileKey       = fileKey;
//    message.lnk           = lnk;
//    if ([type isEqualToString:TypeText]) {
//        message.type      = @"1";
//    } else if ([type isEqualToString:TypePic]) {
//        message.type      = @"3";
//    } else if ([type isEqualToString:TypeVoice]) {
//        message.type      = @"2";
//    } else if ([type isEqualToString:TypeVideo]) {
//        message.type      = @"4";
//    } else if ([type isEqualToString:TypeFile]) {
//        message.type      = @"5";
//    }else if ([type isEqualToString:TypePicText]) {
//        message.type      = @"7";
//    }
////    message.localMsgId    = [self localMessageId:content];
////    message.date          = [ICMessageHelper currentMessageTime];
//    return message;
//}


// 获取语音消息时长
+ (CGFloat)getVoiceTimeLengthWithPath:(NSString *)path
{
    AVURLAsset* audioAsset =[AVURLAsset URLAssetWithURL:[NSURL fileURLWithPath:path] options:nil];
    CMTime audioDuration = audioAsset.duration;
    CGFloat audioDurationSeconds =CMTimeGetSeconds(audioDuration);
    return audioDurationSeconds;
}

// 图片按钮在窗口中得位置
+ (CGRect)photoFramInWindow:(UIButton *)photoView
{
    return [photoView convertRect:photoView.bounds toView:[UIApplication sharedApplication].keyWindow];
}

// 放大后的图片按钮在窗口中的位置
+ (CGRect)photoLargerInWindow:(UIButton *)photoView
{
//    CGSize imgSize     = photoView.imageView.image.size;
    CGSize  imgSize    = photoView.currentBackgroundImage.size;
    CGFloat appWidth   = [UIScreen mainScreen].bounds.size.width;
    CGFloat appHeight  = [UIScreen mainScreen].bounds.size.height;
    CGFloat height     = imgSize.height / imgSize.width * appWidth;
    CGFloat photoY     = 0;
    if (height < appHeight) {
        photoY         = (appHeight - height) * 0.5;
    }
    return CGRectMake(0, photoY, appWidth, height);
}

// 根据消息类型得到cell的标识
+ (NSString *)cellTypeWithMessageType:(NSString *)type
{
//    if ([type isEqualToString:@"1"]) {
//        return TypeText;
//    } else if ([type isEqualToString:@"2"]) {
//        return TypeVoice;
//    } else if ([type isEqualToString:@"3"]) {
//        return TypePic;
//    } else if ([type isEqualToString:@"4"]) {
//        return TypeVideo;
//    } else if ([type isEqualToString:@"5"]) {
//        return TypeFile;
//    } else {
        return type;
//    }
}

// current message time
+ (NSInteger)currentMessageTime
{
    NSTimeInterval time = [[NSDate date] timeIntervalSince1970];
    NSInteger iTime     = (NSInteger)(time * 1000);
    return iTime;
}

// time format
+ (NSString *)timeFormatWithDate:(NSInteger)time
{
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"HH:mm"];
    NSDate * currentDate = [NSDate dateWithTimeIntervalSince1970:time/1000];
    NSString *date = [formatter stringFromDate:currentDate];
    return date;
}


+ (NSString *)timeFormatWithDate2:(NSInteger)time
{
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"yy/MM/dd HH:mm"];
    NSDate * currentDate = [NSDate dateWithTimeIntervalSince1970:time/1000];
    NSString *date = [formatter stringFromDate:currentDate];
    return date;
    
}

+ (NSString *)timeFormatWithDate3:(NSString *)strTime
{
    NSTimeInterval time=[strTime doubleValue]/1000;//传入的时间戳str如果是精确到毫秒的记得要/1000
    NSDate *detailDate=[NSDate dateWithTimeIntervalSince1970:time];
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init]; //实例化一个NSDateFormatter对象
    //设定时间格式,这里可以设置成自己需要的格式
    [dateFormatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    NSString *currentDateStr = [dateFormatter stringFromDate: detailDate];
    return currentDateStr;
}



+ (NSDictionary *)fileTypeDictionary
{
    NSDictionary *dic = @{
                          @"mp3":@1,@"mp4":@2,@"mpe":@2,@"docx":@5,
                          @"amr":@1,@"avi":@2,@"wmv":@2,@"xls":@6,
                          @"wav":@1,@"rmvb":@2,@"mkv":@2,@"xlsx":@6,
                          @"mp3":@1,@"rm":@2,@"vob":@2,@"ppt":@7,
                          @"aac":@1,@"asf":@2,@"html":@3,@"pptx":@7,
                          @"wma":@1,@"divx":@2,@"htm":@3,@"png":@8,
                          @"ogg":@1,@"mpg":@2,@"pdf":@4,@"jpg":@8,
                          @"ape":@1,@"mpeg":@2,@"doc":@5,@"jpeg":@8,
                          @"gif":@8,@"bmp":@8,@"tiff":@8,@"svg":@8
                          };
    return dic;
}

+ (NSNumber *)fileType:(NSString *)type
{
    NSDictionary *dic = [self fileTypeDictionary];
    return [dic objectForKey:type];
}

+ (UIImage *)allocationImage:(ICFileType)type
{
    switch (type) {
        case ICFileType_Audio:
            return [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"Filemp3"]];
            break;
        case ICFileType_Video:
            return [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"Fileunknown"]];
            break;
        case ICFileType_Html:
            return [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"Fileunknown"]];
            break;
        case ICFileType_Pdf:
            return [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"Fileppt"]];
            break;
        case ICFileType_Doc:
            return [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"Filedoc"]];
            break;
        case ICFileType_Xls:
            return [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"FileExcel"]];
            break;
        case ICFileType_Ppt:
            return [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"Fileunknown"]];
            break;
        case ICFileType_Img:
            return [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"Fileunknown"]];
            break;
        case ICFileType_Txt:
            return [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"Filetxt"]];
            break;
        default:
            return [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"Fileunknown"]];
            break;
    }
}


+ (NSString *)timeDurationFormatter:(NSUInteger)duration
{
    float M = duration/60.0;
    float S = duration - (int)M * 60;
    NSString *timeFormatter = [NSString stringWithFormat:@"%02.0lf:%02.0lf",M,S];
    return  timeFormatter;
    
}





@end
