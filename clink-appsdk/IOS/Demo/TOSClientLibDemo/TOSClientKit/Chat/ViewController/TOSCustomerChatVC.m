//
//  TOSCustomerChatVC.m
//  TIMClientKit
//
//  Created by apple on 2021/8/23.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "TOSCustomerChatVC.h"
#import "TIMChatBoxViewController.h"
#import "ICMessageConst.h"
#import "ICChatHearder.h"
#import "kitUtils.h"
#import <TOSClientLib/TIMLibUtils.h>
#import "TIMMessageFrame.h"
#import "ICMessageHelper.h"
#import "TIMMessageModel.h"
#import "ICPhotoBrowserController.h"
#import "VoiceConverter.h"
#import "TIMConstants.h"
#import "TIMRefresh.h"
#import "UIView+SDExtension.h"
#import "UIImage+PureColorImage.h"
#import "UIImage+Extension.h"
#import "ICVideoBrowserController.h"
#import "UIImage+GIF.h"
#import <TOSClientLib/TOSClientLib.h>
#import <TOSClientKit/TOSClientKit.h>
#import "TIMICMessage.h"
#import "YBImageBrowser.h"
#import "UIImage+GIF.h"
#import "XZEmotion.h"
#import "TOSTextView.h"
#import "TIMMBProgressHUD.h"
#import "NSObject+TIMShowError.h"
#import "TIMICMessage.h"
#import "NSDictionary+TIMTool.h"
#import "NSArray+TIMTool.h"
#import <TOSClientLib/NSObject+TIMModel.h>
#import "TOSSendMultiMedia.h"
#import "STBaseWebViewController.h"
#import "WHToast.h"
#import "TIMChatLeaveVC.h"
#import "ICChatMessageCommodityCardCell.h"
#import "ICChatMessageCommodityCardDetailsCell.h"
#import "ICChatMessageRichCell.h"
#import <TOSClientLib/TIMFileMessage.h>
#import "YYKit.h"
#import <TOSClientLib/TIMCommodityCardMessage.h>

#import "HWPanModal.h"
#import <TOSClientLib/TOSSessionInfoModel.h>

#import "SYCacheFileViewController.h"
#import "SYCacheFileManager.h"
#import "SYCacheFileModel.h"
#import <TOSClientLib/CombinationMessage.h>
#import "OSEventSatisfactionCell.h"
#import <TOSClientLib/TOSGetInvestigationInfoModel.h>
#import "ICChatMessageTextTagCell.h"
#import <TOSClientLib/TOSMessageTextTagModel.h>
#import <TOSClientLib/OctoIMParamDefines.h>
#import <TOSClientLib/TOSSensitiveWordEventModel.h>
#import <TOSClientLib/TOSMessageSmallProgramModel.h>
#import "ICChatMessageSmallProgramCardCell.h"
#import "ICChatMessageLogisticsCardCell.h"
#import <TOSClientLib/TIMLogisticsCardMessage.h>
#import "ICChatMessageCustomDeafultCell.h"

#import <TOSClientLib/TOSMessageKnowledgeBaseModel.h>

#import "TOSChatCustomBaseTableViewCell.h"

#define     MAX_SHOWTIME_MSG_SECOND     180
#define     MIN_UPDATE_MSG_SECOND       (0.5f)
#define     MAX_UPDATE_MSG_COUNT        5

/// 再次发送的消息类型
typedef NS_ENUM(NSInteger, offlineMessageType) {
    /// 说明没有需要再次发送离线消息
    offlineMessageTypeNormal            =   0,
    /// 文本消息
    offlineMessageTypeText             =   1,
    /// 语音消息
    offlineMessageTypeVoice             =   2,
    /// 图片消息
    offlineMessageTypeImage             =   3,
    /// 视频消息
    offlineMessageTypeVideo             =   4
};

@interface TOSCustomerChatVC ()<TIMChatBoxViewControllerDelegate,ICRecordManagerDelegate,
TIMClientReceiveMessageDelegate,UITableViewDelegate,UITableViewDataSource,BaseCellDelegate, UIScrollViewDelegate, YBImageBrowserDelegate, YBImageBrowserDataSource, UIDocumentPickerDelegate, ICChatMessageCommodityCardDetailsCellDelegate> {
    CGRect _smallRect;
    CGRect _bigRect;
    CGRect _videoBtnRect;
    
    UIMenuItem * _saveMenuItem;
    UIMenuItem * _copyMenuItem;
    UIMenuItem * _deleteMenuItem;
    UIMenuItem * _forwardMenuItem;
    UIMenuItem * _recallMenuItem;
    NSIndexPath *_longIndexPath;
    
    BOOL   _isKeyBoardAppear;     // 键盘是否弹出来了
    int   _firstHasGetHistoryListFinish; //第一次拉取历史消息是否结束
}

@property (nonatomic, strong) TIMChatBoxViewController *chatBoxVC;
@property (nonatomic, strong) UIView *chatCloseView;


@property (nonatomic, strong) TOSBaseTableView *tableView;
@property (nonatomic, strong) UITextView *textView;
/** 数据源 */
@property (nonatomic, strong) NSMutableArray *dataSource;
/** voice path */
@property (nonatomic, copy) NSString *voicePath;

/// AT用户的数据处理
@property (nonatomic, strong) NSMutableDictionary <NSString *,NSMutableArray *>*atDataDic;

@property (nonatomic, strong) UIImageView *currentVoiceIcon;
@property (nonatomic, strong) UIImageView *presentImageView;
@property (nonatomic, assign)  BOOL presentFlag;  // 是否model出控制器
@property (nonatomic, strong) NSTimer *timer;
@property (nonatomic, strong) ICVoiceHud *voiceHud;
@property (nonatomic, strong) NSString *  oldMessageId;
@property (nonatomic, strong) NSString *  imageOldMessageId;
@property (nonatomic, strong) NSString *  lastSendedMessageId; // 最近的已发送成功的消息
@property (nonatomic, copy) NSString *  curGroupType; // 当前用户在组中的type
@property (nonatomic, assign) long lastDataSourceCount;
@property (nonatomic, strong) NSMutableArray * needDelMessageFrames;  // 需要删除的message
@property (nonatomic, strong) NSMutableArray * needUpdateMessageFrames;  // 需要更新的message
@property (nonatomic, strong) NSMutableArray * needDelRichTextIds;  // 需要删除的富文本messageId

/**
 *  存放所有下载操作的队列
 */
@property (nonatomic,strong) NSOperationQueue* downloadQueue;

/**
 *  存放所有的下载操作（msgId是key，operation对象是value）
 */
@property (nonatomic,strong) NSMutableDictionary* downloadOperations;
/**
 *  存放所有下载完成的数据(包含文件、图片等)，用于缓存
 */
@property (nonatomic,strong) NSMutableDictionary* downloadFileDatas;

@property (nonatomic, strong) TIMMessageModel *recallMessageModel;
@property (nonatomic, strong) NSIndexPath *recallIndexPath;

/// 图片数据
@property (nonatomic, strong) NSMutableArray *imageDataSource;

@property (nonatomic, strong) YBImageBrowser *browser;

/// 正在请求图片类型的历史数据
@property (nonatomic, assign) BOOL isImageDataRequest;

/// 是最后一条图片类型的历史数据
@property (nonatomic, assign) BOOL isLastImageData;

/// 群聊的创建时间
@property (nonatomic,  assign) long long createTime;

//最后一条消息的时间，用于分页
@property (nonatomic,  strong) NSString* lastTime;
@property (nonatomic, strong) NSMutableArray *cellIdList;//记录只加载一次


@property (nonatomic, assign) NSInteger                pageIndex;
@property (nonatomic, assign)  BOOL isOpenSession;      // 是否已打开会话

/// 第一次进入页面
@property (nonatomic, assign)  BOOL firstViewDidAppear;

/// 当前会话是否断开
@property (nonatomic, assign) BOOL                isCloseSession;

/// 需要再次发送的消息类型
@property (nonatomic, assign) offlineMessageType                offlineType;

/// 需要再次发送的离线消息
@property (nonatomic, strong) TIMMessageFrame                * offlineNewMessage;

/// 需要再次发送的媒体数据
@property (nonatomic, strong) NSData                * offlineMediaData;

/// 重写结束会话的UI后，因为需要等到收到欢迎语后才能发送用户的消息，需要做个记录进行记录
@property (nonatomic, assign) BOOL                existedWelcome;

@end

static const NSInteger kMaxImageDataRequest = 50;


@implementation TOSCustomerChatVC

- (void)sendFileMessageAction:(NSNotification *)notification{
    SYCacheFileModel *model = [notification object];
    
    // 生成新的文件名称为uuid
    NSString *fileName = [[model.filePath lastPathComponent] stringByDeletingPathExtension];
    
    int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
    if (sizeLimitM < model.originalFileSize/1024/1024) {
        NSString * strShow = [NSString stringWithFormat:@"请发送小于%dM文件",sizeLimitM];
        [WHToast showMessage:strShow duration:2 finishHandler:^{
            
        }];
        return;
    }
    
    switch (model.fileType) {
        case SYCacheFileTypeVideo: {
            
            UIImage *imageSrc = [UIImage videoFramerateWithPath:model.filePath];
            
            CGFloat fixelW = imageSrc.size.width;
            CGFloat fixelH = imageSrc.size.height;
            NSData *videoData = [NSData dataWithContentsOfFile:model.filePath];
            
            __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo
                                                                       originalType:@"video"
                                                                            content:@"[视频]"
                                                                              extra:@""
                                                                         auditExtra:@""
                                                                               path:model.filePath
                                                                            urlPath:nil
                                                                               from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                 to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                            fileKey:[[NSUUID UUID] UUIDString]
                                                                       bigImgFileId:@""
                                                                      voiceDuration:[NSNumber numberWithInt:0]
                                                                              msgId:@""
                                                                           sendTime:[self getNowTimestamp]
                                                                           showTime:[self p_needShowTime:[NSDate date]]
                                                                            picSize:CGSizeMake(fixelW, fixelH)
                                                                            picType:@""
                                                                           isSender:YES
                                                           receivedSenderByYourself:NO
                                                                             status:TIMMessageStatus_Sending];
            // 创建本地消息
            [self addObject:messageF isSender:YES isHeaderInsert:NO];
            // 发送中
            [self messageSending:messageF];
            
            [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVideoMessageWithVideoData:videoData success:^(NSString * _Nonnull messageId) {
                messageF.model.message.messageId = messageId;
                [self updateOldMessageId:messageId];
                [self messageSendSucced:messageF];
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                [self messageSendFailed:messageF];
                int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
                NSString * strShow = [NSString stringWithFormat:@"文件过大，请发送小于%dM文件",sizeLimitM];
                [WHToast showMessage:errorDes?:strShow duration:2 finishHandler:^{
                    
                }];
            }];
        }
            break;
        case SYCacheFileTypeAudio: {
            // 获取时长
            int voiceDuration = (int)[[ICRecordManager shareManager] durationWithVideo:[NSURL fileURLWithPath:model.filePath]];
            if (voiceDuration < 1000) {
                // 小于一秒的显示1秒
                voiceDuration = 1000;
            }
            
            
            NSData*voiceData = [NSData dataWithContentsOfFile:model.filePath];
            
            // Kit
            __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVoice
                                                                       originalType:@"voice"
                                                                            content:@"[语音]"
                                                                              extra:@""
                                                                         auditExtra:@""
                                                                               path:model.filePath
                                                                            urlPath:nil
                                                                               from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                 to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                            fileKey:[[NSUUID UUID] UUIDString]
                                                                       bigImgFileId:@""
                                                                      voiceDuration:[NSNumber numberWithInt:voiceDuration]
                                                                              msgId:@""
                                                                           sendTime:[self getNowTimestamp]
                                                                           showTime:[self p_needShowTime:[NSDate date]]
                                                                            picSize:CGSizeZero
                                                                            picType:@""
                                                                           isSender:YES
                                                           receivedSenderByYourself:NO
                                                                             status:TIMMessageStatus_Sending];
            [self addObject:messageF isSender:YES isHeaderInsert:NO];
            // 发送中
            [self messageSending:messageF];
            
            [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVoiceMessageWithVoiceData:voiceData success:^(NSString * _Nonnull messageId) {
                messageF.model.message.messageId = messageId;
                [self updateOldMessageId:messageId];
                [self messageSendSucced:messageF];
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                [self messageSendFailed:messageF];
            }];
        }
            break;
        case SYCacheFileTypeImage: {
            UIImage *image = [UIImage imageWithContentsOfFile:model.filePath];
            
            CGFloat fixelW = image.size.width;
            CGFloat fixelH = image.size.height;
            
            NSData*uploadThumbFileData = UIImageJPEGRepresentation(image, 0.5);
            
            // Kit
            CGSize imageSize = CGSizeMake(fixelW, fixelH);
            __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic
                                                                       originalType:@"image"
                                                                            content:@"[图片]"
                                                                              extra:@""
                                                                         auditExtra:@""
                                                                               path:model.filePath
                                                                            urlPath:nil
                                                                               from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                 to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                            fileKey:[[NSUUID UUID] UUIDString]
                                                                       bigImgFileId:@""
                                                                      voiceDuration:[NSNumber numberWithInt:0]
                                                                              msgId:@""
                                                                           sendTime:[self getNowTimestamp]
                                                                           showTime:[self p_needShowTime:[NSDate date]]
                                                                            picSize:imageSize
                                                                            picType:@""
                                                                           isSender:YES
                                                           receivedSenderByYourself:NO
                                                                             status:TIMMessageStatus_Sending];
            [self addObject:messageF isSender:YES isHeaderInsert:NO];
            // 发送中
            [self messageSending:messageF];
            
            [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendImageMessageWithImageData:uploadThumbFileData        success:^(NSString * _Nonnull messageId,NSString * _Nonnull fileKey) {
                messageF.model.message.messageId = messageId;
                [self updateOldMessageId:messageId];
                [self messageSendSucced:messageF];
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                [self messageSendFailed:messageF];
            }];
        }
            break;
        case SYCacheFileTypeDocument:
        case SYCacheFileTypeUnknow:
        default: {
            NSString * fileTypeStr = [kitUtils mimeTypeForFileAtPath:model.filePath];
            NSDictionary* fileMessageDic = @{
                @"fileTypeDesc": @"文件",
                @"fileType": fileTypeStr,
                @"fileName": fileName
            };
            // Kit
            __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCustomFile
                                                                       originalType:@"file"
                                                                            content:[kitUtils DataTOjsonString:fileMessageDic]
                                                                              extra:@""
                                                                         auditExtra:@""
                                                                               path:model.filePath
                                                                            urlPath:nil
                                                                               from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                                 to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                            fileKey:[[NSUUID UUID] UUIDString]
                                                                       bigImgFileId:@""
                                                                      voiceDuration:[NSNumber numberWithInt:0]
                                                                              msgId:@""
                                                                           sendTime:[self getNowTimestamp]
                                                                           showTime:[self p_needShowTime:[NSDate date]]
                                                                            picSize:CGSizeMake(150, 60)
                                                                            picType:@""
                                                                           isSender:YES
                                                           receivedSenderByYourself:NO
                                                                             status:TIMMessageStatus_Sending];
            // 创建本地消息
            [self addObject:messageF isSender:YES isHeaderInsert:NO];
            // 发送中
            [self messageSending:messageF];
            
            NSData *fileData = [NSData dataWithContentsOfFile:model.filePath];
            
            [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendFileMessageWithFileData:fileData fileType:fileTypeStr fileName:fileName success:^(NSString * _Nonnull messageId) {
                messageF.model.message.messageId = messageId;
                [self updateOldMessageId:messageId];
                [self messageSendSucced:messageF];
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                [self messageSendFailed:messageF];
                int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
                NSString * strShow = [NSString stringWithFormat:@"文件过大，请发送小于%dM文件",sizeLimitM];
                [WHToast showMessage:errorDes?:strShow duration:2 finishHandler:^{
                    
                }];
            }];
        }
            break;
    }
}

#pragma mark UIDocumentPickerDelegate
- (void)documentPicker:(UIDocumentPickerViewController *)controller didPickDocumentsAtURLs:(NSArray <NSURL *>*)urls{
    //获取授权
    BOOL fileUrlAuthozied = [urls.firstObject startAccessingSecurityScopedResource];
    if (fileUrlAuthozied) {
        //通过文件协调工具来得到新的文件地址，以此得到文件保护功能
        NSFileCoordinator *fileCoordinator = [[NSFileCoordinator alloc] init];
        NSError *error;
        
        [fileCoordinator coordinateReadingItemAtURL:urls.firstObject options:0 error:&error byAccessor:^(NSURL *newURL) {
            //读取文件
            NSString *fileName = [newURL lastPathComponent];
            NSError *error = nil;
            NSData *fileData = [NSData dataWithContentsOfURL:newURL options:NSDataReadingMappedIfSafe error:&error];
            if (error) {
                //读取出错
            } else {
                //上传
                [self onlineSendFilemessage:fileData fileName:fileName fileURL:newURL];
            }
        }];
        [urls.firstObject stopAccessingSecurityScopedResource];
    } else {
        //授权失败
    }
}

-(void)openSession{
    // 打开一个新的会话
    NSLog(@"链接状态 ---- 打开新会话");
    NSString * moreInfo = [[OnlineDataSave shareOnlineDataSave] getConnectAdParams];
    [[OnlineRequestManager sharedCustomerManager]visitorReadyWithDict:[kitUtils dictionaryWithJsonString:moreInfo]
                                                              success:^(TOSSessionInfoModel * _Nonnull sessModel) {
        NSLog(@"viewload sessModel status:%d startTime:%lld mainUniqueId:%@ enterpriseId:%@ visitorId:%@",
              [sessModel.status intValue],
              [sessModel.startTime longLongValue],
              sessModel.mainUniqueId,
              sessModel.enterpriseId,
              sessModel.visitorId);
        [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[sessModel yy_modelToJSONString]];
        self.isOpenSession = YES;
        
        [self loadData];
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
    }];
}

- (void)hiddenNavigationBarSeparatorLine {
    if ([self.navigationController.navigationBar respondsToSelector:@selector(setBackgroundImage:forBarMetrics:)]) {
        NSArray *list=self.navigationController.navigationBar.subviews;
        for (id obj in list) {
            if ([obj isKindOfClass:[UIView class]]) {
                UIView *imageView=(UIView *)obj;
                NSArray *list2=imageView.subviews;
                for (id obj2 in list2) {
                    if ([obj2 isKindOfClass:[UIImageView class]]) {
                        UIImageView *imageView2=(UIImageView *)obj2;
                        imageView2.hidden = YES;
                    }
                }
            }
        }
    }
}

// 进入前台
- (void)willEnterBecomeActive {
    //发送已读事件
    [[OnlineRequestManager sharedCustomerManager] sessionInfoReadWithMainUniqueId:[[OnlineDataSave shareOnlineDataSave] getMainUniqueId]];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    if ([TOSKitCustomInfo shareCustomInfo].titleName) {
        self.titleName = [TOSKitCustomInfo shareCustomInfo].titleName;
    }
    
    if ([TOSKitCustomInfo shareCustomInfo].appName) {
        self.appName = [TOSKitCustomInfo shareCustomInfo].appName;
    }
    
    if ([TOSKitCustomInfo shareCustomInfo].quickEntryAllItems) {
        self.quickEntryAllItems = [TOSKitCustomInfo shareCustomInfo].quickEntryAllItems;
    }
    
    if ([TOSKitCustomInfo shareCustomInfo].commodityCardOption) {
        self.commodityCardOption = [TOSKitCustomInfo shareCustomInfo].commodityCardOption;
    }
    
    @WeakObj(self);
    [[OnlineRequestManager sharedCustomerManager] getInvestigationInfoSuccess:^{
        @StrongObj(self);
        dispatch_async(dispatch_get_main_queue(), ^{
            @StrongObj(self);
            [self.tableView reloadData];
        });
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        
    }];
    
    
    self.isOpenSession = NO;
    [self chatStatusChanged:TinetChatStatusTypeOutline];
    
    NSLog(@"进入会话时链接状态 = %d",[[TOSClientKit sharedTOSKit].mqttConnected boolValue]);
    if ([[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
        NSLog(@"网络已连接状态");
        [self openSession];
    }
    
    self.pageIndex = 0;
    lastDateInterval = 0;
    if (@available(iOS 13.0, *)) {
        self.overrideUserInterfaceStyle = UIUserInterfaceStyleLight;    //关闭暗黑模式
    }
    self.isImageDataRequest = NO;
    self.isLastImageData = NO;
    self.view.backgroundColor = [UIColor whiteColor];
    
    self.title = self.titleName?:@"";
    
    [[OnlineDataSave shareOnlineDataSave] saveCustomTitle:self.title];
    self.oldMessageId = @"";
    self.lastSendedMessageId = @"";
    
    self.createTime = 0;
    
    [self setupUI];
    [self setupChatCloseView];
    [self registerCell];
    // 避免回收通知失败
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(willEnterBecomeActive) name:UIApplicationDidBecomeActiveNotification object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(messageReceivedNotification:) name:kTIMMessageReceivedNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(messageRecalledNotification:) name:kTIMMessageRecalledNotification object:nil];
    // 增加接收刷新界面的通知
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(messageSendedUpdateUINotification:) name:kTIMMessageUpdateChatUINotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(messageSendedUpdateUIFromLibNotification:) name:kTIMMessageUpdateChatUIFromLibNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(recalledMessageAgainEditNotification:) name:kTIMRecalledMessageAgainEditNotification object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(sendMultiMediaImage:) name:KTIMSendMultiMediaImageNotification object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(updateConnectStatus:) name:kTIMUpdateConnectStatusNotification object:nil];
    
    
    //退出排队
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(leaveQueueAction:) name:OSLeaveQueueNotification object:nil];
    
    //发送文件消息
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(sendFileMessageAction:) name:kSendFileMessageNotification object:nil];
    
    //拉取历史消息
    [self firstGetHistoryMessage];
    
    //发送访客上线事件
    [self sendChatOnline];
    
    NSString *inputText = [[OnlineDataSave shareOnlineDataSave] getUserInputText];
    
    [self.chatBoxVC.chatBox.textView transalteStringEmoticonAttributedWithString:inputText];
}

-(void)firstGetHistoryMessage{
    self->_firstHasGetHistoryListFinish = 1;
    NSInteger timestamp = [kitUtils getNowTimestampWithSec].integerValue;
    NSString *lastTime = [NSString stringWithFormat:@"%ld",(long)timestamp + (long)1];
    self.lastTime = lastTime;
    [self getChatRecrodListWithLastTime:lastTime];
}


-(void)loadMore{
    [self getChatRecrodListWithLastTime:self.lastTime];
}

-(void)viewDidAppear:(BOOL)animated{
    [self.chatBoxVC.chatBox addNotification];
    
    if (!self.firstViewDidAppear) {
        [self.chatBoxVC.chatBox switchTextEditing];
        [self scrollToBottom];
        self.firstViewDidAppear = YES;
    }
}

-(void)viewWillDisappear:(BOOL)animated{
    [self.chatBoxVC.chatBox removeNotification];
    /// 清空最后一条消息/未读消息数
    [[NSNotificationCenter defaultCenter] postNotificationName:kTIMMessageClearUnReadCountNotification object:nil];
}

- (void)setupUI
{
    self.view.backgroundColor = TOSColor(240, 237, 237);
    // 注意添加顺序
    self.chatBoxVC.isCurChatGroup = NO;
    [self addChildViewController:self.chatBoxVC];
    
    [self.view addSubview:self.chatBoxVC.view];
    [self.view addSubview:self.tableView];
    
    [self.view bringSubviewToFront:self.chatBoxVC.view];
    
    
    self.tableView.backgroundColor = TOSColor(240, 237, 237);
    /// 快捷入口的高度
    CGFloat inputChatBarHeight = self.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
    self.tableView.frame = CGRectMake(0, 0, self.view.width, APP_Frame_Height-[TOSKitCustomInfo shareCustomInfo].chatBox_Height-kNavTop-kBottomBarHeight-inputChatBarHeight);
}

-(void)setupChatCloseView{
    
    CGFloat barHeight = self.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
    [self.view addSubview:self.chatCloseView];
    UILabel*titlelbl = [[UILabel alloc]initWithFrame:CGRectMake((self.view.width - 230)/2, 0, 160, [TOSKitCustomInfo shareCustomInfo].chatBox_Height+barHeight)];
    titlelbl.font = [UIFont systemFontOfSize:16];
    titlelbl.textAlignment = NSTextAlignmentRight;
    titlelbl.textColor = [UIColor blackColor];
    titlelbl.backgroundColor = [UIColor clearColor];
    titlelbl.text = @"对话已结束，您可以";
    [self.chatCloseView addSubview:titlelbl];
    
    UIButton*openBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    openBtn.frame = CGRectMake((self.view.width - 210)/2+160, 0, 70, [TOSKitCustomInfo shareCustomInfo].chatBox_Height+barHeight);
    
    [openBtn setTitleColor:TOSHexAColor(0x1890ff,1.0) forState:UIControlStateNormal];
    [openBtn.titleLabel setFont:[UIFont systemFontOfSize:16]];
    [openBtn setTitle:@"继续咨询" forState:UIControlStateNormal];
    openBtn.backgroundColor = [UIColor clearColor];
    [openBtn addTarget:self action:@selector(openAction) forControlEvents:UIControlEventTouchUpInside];
    [self.chatCloseView addSubview:openBtn];
    self.chatCloseView.hidden = YES;
}

- (void)loadData {
    
    NSString *tempPath = NSTemporaryDirectory();
    NSString *filePath = [tempPath stringByAppendingPathComponent:@"RichTextMessageIds"];
    NSArray *msgIdsArr = [NSKeyedUnarchiver unarchiveObjectWithFile:filePath];
    if (msgIdsArr!=nil && msgIdsArr.count>0) {
        //删除只加载一次富文本的Id
        for (NSString*msgId in msgIdsArr) {
            [[NSUserDefaults standardUserDefaults]removeObjectForKey:msgId];
        }
    }
    
    
    @WeakObj(self);
    
    /// 自定义刷新组件不为空就走自定义逻辑
    if (self.customRefreshHeader != nil) {
        _tableView.mj_header = self.customRefreshHeader;
        
    }
    else {
        __weak typeof(_tableView.mj_header) weakHeader = _tableView.mj_header;
        _tableView.mj_header = [TIMRefreshNormalHeader headerWithRefreshingBlock:^{
            typeof (selfWeak)strongSelf = selfWeak;
            typeof (weakHeader)strongHeader = weakHeader;
            NSLog(@"headerWithRefreshingBlock ...");
            [strongHeader beginRefreshing];
            //request
            [strongSelf loadMore];
        }];
    }
    
    
    self.needDelMessageFrames = [NSMutableArray arrayWithCapacity:3];
    self.needUpdateMessageFrames = [NSMutableArray arrayWithCapacity:3];
    self.needDelRichTextIds = [[NSMutableArray alloc]init];
    self.cellIdList = [[NSMutableArray alloc]init];
}


/// 自定义refresh组件回调需要调用的方法
- (void)loadMoreMessage {
    
    [self.customRefreshHeader beginRefreshing];
    /// 加载更多消息
    [self loadMore];
}

- (void)registerCell {
    [self.tableView registerClass:[ICChatMessageTextCell class] forCellReuseIdentifier:TypeText];
    [self.tableView registerClass:[ICChatMessageRevokeCell class] forCellReuseIdentifier:TypeRevoke];
    [self.tableView registerClass:[ICChatMessageUnsupportCell class] forCellReuseIdentifier:TypeUnsupport];
    [self.tableView registerClass:[ICChatMessageImageCell class] forCellReuseIdentifier:TypePic];
    [self.tableView registerClass:[ICChatMessageTextTagCell class] forCellReuseIdentifier:TypeTextTag];
    [self.tableView registerClass:[ICChatMessageVideoCell class] forCellReuseIdentifier:TypeVideo];
    [self.tableView registerClass:[ICChatMessageVoiceCell class] forCellReuseIdentifier:TypeVoice];
    [self.tableView registerClass:[ICChatMessageCustomDeafultCell class] forCellReuseIdentifier:TypeCustom];
    [self.tableView registerClass:[ICChatMessageCustomFileCell class] forCellReuseIdentifier:TypeCustomFile];
    [self.tableView registerNib:[UINib nibWithNibName:@"TOSKitClient.bundle/OSEventSatisfactionCell" bundle:nil] forCellReuseIdentifier:TypeInvestigation];
    [self.tableView registerClass:[ICChatMessageSmallProgramCardCell class] forCellReuseIdentifier:TypeSmallProgramCard];
    [self.tableView registerClass:[ICChatMessageLogisticsCardCell class] forCellReuseIdentifier:TypeLogisticsCard];
    [self.tableView registerClass:[ICChatMessageRichCell class] forCellReuseIdentifier:GXRichText];
    [self.tableView registerClass:[OSRobotCombinationCell class] forCellReuseIdentifier:TypeRobotCombination];
    [self.tableView registerClass:[OSRobotSelectCombinationCell class] forCellReuseIdentifier:TypeRobotSelectCombination];
    [self.tableView registerClass:[OSRobotWelcomeCell class] forCellReuseIdentifier:TypeRobotWelcome];
    [self.tableView registerClass:[OSSystomWelcomCell class] forCellReuseIdentifier:TySystomCombination];
    [self.tableView registerClass:[OSEventQueueCell class] forCellReuseIdentifier:TypeEventQueue];
    [self.tableView registerClass:[ICChatSystemCell class] forCellReuseIdentifier:TypeSystem];
    [self.tableView registerClass:[OSRobotCombinationCell class] forCellReuseIdentifier:TypeRobotCombinationList];
    [self.tableView registerNib:[UINib nibWithNibName:@"TOSKitClient.bundle/ICChatMessageCommodityCardCell" bundle:nil] forCellReuseIdentifier:[ICChatMessageCommodityCardCell className]];
    [self.tableView registerClass:[ICChatMessageCommodityCardDetailsCell class] forCellReuseIdentifier:[ICChatMessageCommodityCardDetailsCell className]];
}

- (NSString *)messageTypeConversionWithMessage:(TOSMessage *)message type:(NSString *)type {
    NSString *newType;
    if (message.status == TIMMessageStatus_ReCall) {
        newType = TypeRevoke;
    } else {
        newType = type;
    }
    return newType;
}

//新增的拉取消息接口
-(void)getChatRecrodListWithLastTime:(NSString*)lastTime{
    if (lastTime == nil) {
        NSInteger timestamp = [kitUtils getNowTimestampWithSec].integerValue;
        lastTime = [NSString stringWithFormat:@"%ld",(long)timestamp + (long)1];
        self.lastTime = lastTime;
    }
    
    if (lastTime.length <= 10) {
        lastTime = [NSString stringWithFormat:@"%@000",lastTime];
    }
    
    if (self->_firstHasGetHistoryListFinish == 1) {
        lastTime = @"";
    }
    
    [[OnlineRequestManager sharedCustomerManager] getChatRecordListLastTime:lastTime
                                                                      limit:@"20"
                                                                    success:^(NSArray * _Nonnull chatList) {
        if (chatList.count>0) {
            self.pageIndex++;
            OnlineChatRecordModel*lastmodel = chatList.firstObject;
            self.lastTime = lastmodel.createTime;
            //倒叙
            NSMutableArray*historyList  = (NSMutableArray *)[[chatList reverseObjectEnumerator] allObjects];
            for (OnlineChatRecordModel *model in historyList) {
                [self analysisHistoryWithModel:model withItemReload:NO];
            }
            
            [self.tableView reloadData];
            if (self->_firstHasGetHistoryListFinish == 1) {
                [self addCommodityCardMessage];
                if (self.dataSource.count > 0 &&
                    [self.tableView numberOfRowsInSection:0] >= self.dataSource.count) {
                    
                    [self scrollToBottom];
                }
                self->_firstHasGetHistoryListFinish = 0;
            }
            [self.tableView.mj_header endRefreshing];
            /// 目前用这个不是很合适(后面再优化)
            self.pageIndex = 0;
        }else{
            if (self->_firstHasGetHistoryListFinish == 1) {
                [self addCommodityCardMessage];
                [self scrollToBottom];
                self->_firstHasGetHistoryListFinish = 0;
            }
            [self.tableView reloadData];
            [self.tableView.mj_header endRefreshing];
        }
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        if (self->_firstHasGetHistoryListFinish == 1) {
            [self addCommodityCardMessage];
            [self scrollToBottom];
            self->_firstHasGetHistoryListFinish = 0;
        }
        [self.tableView reloadData];
        [self.tableView.mj_header endRefreshing];
    }];
}

- (void)addCommodityCardMessage {
    NSString * curMainUniqueId = [[OnlineDataSave shareOnlineDataSave] getMainUniqueId];
    NSString * mainUniqueIdStatus = [[OnlineDataSave shareOnlineDataSave] getMainUniqueIdRunningStatus:curMainUniqueId];
    // 做个保护 针对创建会话的状态
    if (self.commodityCardOption &&
        self->_firstHasGetHistoryListFinish == 1) {
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCommodityCard originalType:@"text" content:(NSString *)self.commodityCardOption extra:@"" auditExtra:@"" path:nil urlPath:nil from:[[OnlineDataSave shareOnlineDataSave] getVisitorId] to:[[OnlineDataSave shareOnlineDataSave] getEndpointId] fileKey:@"" bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:[kitUtils getMsgUUID] sendTime:[self getNowTimestamp] showTime:[self p_needShowTime:[NSDate date]] picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:YES isHeaderInsert:NO];
        [self messageSendSucced:messageF];
        [self.tableView reloadData];
        [[OnlineDataSave shareOnlineDataSave] saveMainUniqueIdRunningStatus:curMainUniqueId runningStatus:@"RunningStatus"];
    }
}

/// 添加不支持的消息类型
/// @param messageRecvDirection 方向
/// @param object 消息数据
- (void)addUnsupportMessageCell:(BOOL)messageRecvDirection object:(TOSMessage *)object  {
    TIMTextMessage * txtMsg = [[TIMTextMessage alloc] initMessageWithContent:kTIMUnsupportMessageCellType extra:@""];
    if (messageRecvDirection) {
        [self otherSendTextMessageWithContent:TypeText originalType:object.type from:object.senderId to:object.receiverId fileKey:object.messageUUID msgId:object.msg_id sendTime:object.timestamp content:txtMsg.content];
        [self scrollToBottom];
    }else{
        [self sendTextMessageWithContent:TypeText originalType:object.type from:object.senderId to:object.receiverId fileKey:object.messageUUID msgId:object.msg_id sendTime:object.timestamp content:txtMsg.content];
        [self scrollToBottom];
    }
}

/// 自定义cell要实现这个方法
- (TOSChatCustomBaseTableViewCell *)customTableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath withModel:(TIMMessageModel *)model {
    TOSChatCustomBaseTableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"cell"];
    if (!cell) {
        cell = [[TOSChatCustomBaseTableViewCell alloc] initWithStyle:(UITableViewCellStyleDefault) reuseIdentifier:@"cell"];
    }
    return cell;
}

#pragma mark - Tableview data source

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.dataSource.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    @WeakObj(self)
    id obj                            = selfWeak.dataSource[indexPath.row];
    if ([obj isKindOfClass:[NSString class]]) {
        return nil;
    } else {
        TIMMessageFrame *modelFrame    = (TIMMessageFrame *)obj;
        NSString *ID                   = modelFrame.model.message.type;
        /// 自定义的cell
        if ([[[TOSKitCustomInfo shareCustomInfo].customCellRegister allKeys] containsObject:modelFrame.model.message.type]) {
            TOSChatCustomBaseTableViewCell * cell = [self customTableView:tableView cellForRowAtIndexPath:indexPath withModel:modelFrame.model];
            return cell;
        }
        //系统消息
        if ([ID isEqualToString:TypeSystem] || [ID isEqualToString:TypeUnknown]) {
            ICChatSystemCell *sysCell = [ICChatSystemCell cellWithTableView:tableView reusableId:ID];
            sysCell.messageF              = modelFrame;
            return sysCell;
        } else if ([ID isEqualToString:TypeCommodityCard]) {
            
            NSString *reuseIdentifie = [ICChatMessageCommodityCardCell className];
            ICChatMessageCommodityCardCell *cell = [tableView dequeueReusableCellWithIdentifier:reuseIdentifie];
            cell.modelFrame = modelFrame;
            return cell;
        } else if ([ID isEqualToString:TypeCommodityCardDetails]) {
            
            NSString *reuseIdentifie = [ICChatMessageCommodityCardDetailsCell className];
            ICChatMessageCommodityCardDetailsCell *cell = [tableView dequeueReusableCellWithIdentifier:reuseIdentifie];
            cell.modelFrame = modelFrame;
            cell.currentIndexPath = indexPath;
            cell.delegate = self;
            return cell;
        } else if ([ID isEqualToString:TypeRobotSelectCombination]) {//多选消息
            NSString * identifier = [NSString stringWithFormat:@"SelectCell%ld%ld",(long)indexPath.section,(long)indexPath.row];
            OSRobotSelectCombinationCell *selectCell = [tableView dequeueReusableCellWithIdentifier:identifier];
            if (!selectCell) {
                selectCell = [[OSRobotSelectCombinationCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:identifier];
            }
            int j = 0;
            if (self.cellIdList.count>0) {
                for (NSString*ident in self.cellIdList) {
                    if ([ident isEqualToString:identifier]) {//加载过了
                        j = 1;
                    }
                }
            }
            if (j == 0) {
                [self.cellIdList addObject:identifier];
                selectCell.modelFrame  = modelFrame;
                selectCell.longPressDelegate = selfWeak;
            }
            return selectCell;
        }else if ([ID isEqualToString:TypeText]) {//文字消息
            NSString * identifier = [NSString stringWithFormat:@"TextCell%ld%ld",(long)indexPath.section,(long)indexPath.row];
            ICChatMessageTextCell *textCell = [tableView dequeueReusableCellWithIdentifier:identifier];
            if (!textCell) {
                textCell = [[ICChatMessageTextCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:identifier];
            }
            textCell.modelFrame  = modelFrame;
            textCell.longPressDelegate = selfWeak;
            return textCell;
        }else if ([ID isEqualToString:TypeEventQueue]) {//放弃排队消息
            NSString * identifier = [NSString stringWithFormat:@"TypeEventQueueCell%ld%ld",(long)indexPath.section,(long)indexPath.row];
            OSEventQueueCell *queueCell = [tableView dequeueReusableCellWithIdentifier:identifier];
            if (!queueCell) {
                queueCell = [[OSEventQueueCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:identifier];
            }
            int j = 0;
            if (self.cellIdList.count>0) {
                for (NSString*ident in self.cellIdList) {
                    if ([ident isEqualToString:identifier]) {//加载过了
                        j = 1;
                    }
                }
            }
            if (j == 0 ) {
                [self.cellIdList addObject:identifier];
                queueCell.modelFrame  = modelFrame;
                }
            return queueCell;
        }
        
        
        ICChatMessageBaseCell *cell    = [tableView dequeueReusableCellWithIdentifier:ID];
        cell.longPressDelegate         = selfWeak;
        if ([modelFrame.model.message.type isEqualToString:TypeVideo]) {
            ICChatMessageVideoCell *videoCell = (ICChatMessageVideoCell *)cell;
            [videoCell stopVideo];
        }
        
        // 处理cell中下载数据后的刷新
        //取出当前URL对应的下载下载操作 去除视频下载换为封面图下载
        if (([modelFrame.model.message.type isEqualToString:TypePic] ||
             [modelFrame.model.message.type isEqualToString:TypeVoice]) &&
            ![kitUtils isBlankString:modelFrame.model.message.messageId] && !selfWeak.downloadFileDatas[modelFrame.model.message.messageId]) {
            
            
            if ([modelFrame.model.message.type isEqualToString:TypeVoice]) {
                
                NSString *localVoicePath = [ICFileTool filePathWithName:modelFrame.model.message.messageId orgName:@"voice" type:@"wav"];
                if ([ICFileTool fileExistsAtPath:localVoicePath]) {
                    
                    int voiceDuration = (int)[[ICRecordManager shareManager] durationWithVideo:[NSURL fileURLWithPath:localVoicePath]];
                    if (voiceDuration < 1000) {
                        // 小于一秒的显示1秒
                        voiceDuration = 1000;
                    }
                    modelFrame.model.message.voiceDuration = [NSNumber numberWithInt:voiceDuration];
                    
                    modelFrame.model.mediaPath = localVoicePath;
                    selfWeak.downloadFileDatas[modelFrame.model.message.messageId] = localVoicePath;
                    
                    cell.modelFrame = modelFrame;
                    return cell;
                }
            }
            
            
            NSBlockOperation* operation = selfWeak.downloadOperations[modelFrame.model.message.messageId];
            if (nil == operation) {
                //创建下载操作
                operation = [NSBlockOperation blockOperationWithBlock:^{
                    NSURL* url = [NSURL URLWithString:modelFrame.model.urlPath];
                    //1.通过URL创建NSURLRequest
                    NSURLRequest *request = [[NSURLRequest alloc] initWithURL:url cachePolicy:NSURLRequestUseProtocolCachePolicy timeoutInterval:30];
                    //2.创建一个 NSMutableURLRequest 添加 header
                    NSMutableURLRequest *mutableRequest = [request mutableCopy];
                    //3.把值覆给request
                    request = [mutableRequest copy];
                    //3.建立网络连接NSURLConnection，同步请求数据
                    NSHTTPURLResponse *response = nil;
                    NSError *error = nil;
                    NSData *data = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
                    if (data) {
                        if ([modelFrame.model.message.type isEqualToString:TypePic]) {
                            if (data) {
                                //防止下载失败为空赋值造成崩溃
                                UIImage* image = [UIImage imageWithData:data];
                                
                                //对图片做存
                                if (image) {
                                    [[ICMediaManager sharedManager] saveImage:image msgId:modelFrame.model.message.messageId picType:@"jpg"];
                                } else {
                                    // 如果图片不存在则使用占位图
                                    image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"loadImage.png"]];
                                    [[ICMediaManager sharedManager] saveImage:image msgId:modelFrame.model.message.messageId picType:@"jpg"];
                                }
                                
                                selfWeak.downloadFileDatas[modelFrame.model.message.messageId] = image;
                                NSString *localImagePath;
                                if ([[UIImage typeForImageData:data] isEqualToString:kGIFTimageType]) {
                                    localImagePath = [[ICMediaManager sharedManager] saveGifImage:data msgId:modelFrame.model.message.messageId picType:kGIFTimageType];
                                }else{
                                    localImagePath = [[ICMediaManager sharedManager] saveImage:image msgId:modelFrame.model.message.messageId picType:@"jpg"];
                                }
                                modelFrame.model.mediaPath = localImagePath;
                                CGFloat fixelW = image.size.width;
                                CGFloat fixelH = image.size.height;
                                
                                modelFrame.model.picWidth = fixelW;
                                modelFrame.model.picHeight = fixelH;
                                //回到主线程刷新表格
                                [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                                    @StrongObj(self)
                                    [self.downloadOperations removeObjectForKey:modelFrame.model.message.messageId];
                                    __block NSInteger index;
                                    [self.dataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
                                        if ([obj.model.message.messageId isEqualToString:modelFrame.model.message.messageId]) {
                                            index = idx;
                                        }
                                    }];
                                    
                                    NSIndexPath *indexPath = [NSIndexPath indexPathForRow:index inSection:0];
                                    [self.tableView reloadRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationNone];
                                }];
                            }
                        }else if ([modelFrame.model.message.type isEqualToString:TypeVoice]) {
                            if (data) { //防止下载失败为空赋值造成崩溃
                                NSString * localVoicePath = [ICFileTool writeDataToFile:modelFrame.model.message.messageId fileName:@"voice" type:@"wav" data:data];
                                
                                int voiceDuration = (int)[[ICRecordManager shareManager] durationWithVideo:[NSURL fileURLWithPath:localVoicePath]];
                                if (voiceDuration < 1000) {
                                    // 小于一秒的显示1秒
                                    voiceDuration = 1000;
                                }
                                modelFrame.model.message.voiceDuration = [NSNumber numberWithInt:voiceDuration];
                                
                                modelFrame.model.mediaPath = localVoicePath;
                                selfWeak.downloadFileDatas[modelFrame.model.message.messageId] = localVoicePath;
                                //回到主线程刷新表格
                                [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                                    @StrongObj(self)
                                    // 从字典中移除下载操作 (防止operations越来越大，保证下载失败后，能重新下载)
                                    [self.downloadOperations removeObjectForKey:modelFrame.model.message.messageId];
                                    //刷新当前行的图片数据
                                    [selfWeak.tableView reloadRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationNone];
                                }];
                            }
                        }else if ([modelFrame.model.message.type isEqualToString:TypeVideo]) {
                            if (data) { //防止下载失败为空赋值造成崩溃
                                NSString * localVideoPath = [[ICVideoManager shareManager] WriteDataToFile:modelFrame.model.message.messageId data:data];
                                modelFrame.model.mediaPath = localVideoPath;
                                
                                CGFloat fixelW = 100;
                                CGFloat fixelH = 100;
                                
                                modelFrame.model.picWidth = fixelW;
                                modelFrame.model.picHeight = fixelH;
                                //回到主线程刷新表格
                                [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                                    @StrongObj(self)
                                    ICChatMessageBaseCell * baseCell = (ICChatMessageBaseCell *)cell;
                                    baseCell.modelFrame = modelFrame;
                                    selfWeak.downloadFileDatas[modelFrame.model.message.messageId] = localVideoPath;
                                    
                                    // 从字典中移除下载操作 (防止operations越来越大，保证下载失败后，能重新下载)
                                    [self.downloadOperations removeObjectForKey:modelFrame.model.message.messageId];
                                    //刷新当前行的图片数据
                                    [self.tableView reloadRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationNone];
                                }];
                            }
                        }
                    }
                }];
                //添加操作到队列中
                [selfWeak.downloadQueue addOperation:operation];
                //添加到字典中
                if ([kitUtils isBlankString:modelFrame.model.message.messageId]) {
                    TIMKitLog(@"downloadOperations key is null %@",modelFrame.model.message);
                }
                selfWeak.downloadOperations[modelFrame.model.message.messageId] = operation;
            }
        }
        
        cell.modelFrame = modelFrame;
        return cell;
    }
}

#pragma mark - ICChatMessageCommodityCardDetailsCellDelegate

/// 卡片折叠/展开
- (void)ICChatMessageCommodityCardDetailsCellWithIndexPath:(NSIndexPath *)indexPath {
    [self.tableView reloadRowAtIndexPath:indexPath withRowAnimation:(UITableViewRowAnimationAutomatic)];
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    TIMMessageFrame *messageF = [self.dataSource objectAtIndex:indexPath.row];
    
    if ([[[TOSKitCustomInfo shareCustomInfo].customCellRegister allKeys] containsObject:messageF.model.message.type]) {
        TOSChatCustomBaseTableViewCell * cell = (TOSChatCustomBaseTableViewCell *)[self customTableView:tableView cellForRowAtIndexPath:indexPath withModel:messageF.model];
        return [cell cellHeight];
    }
    return messageF.cellHight > 0 ? messageF.cellHight : 0;
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    UIView * view = [[UIView alloc] initWithFrame:(CGRectZero)];
    view.backgroundColor = UIColor.clearColor;
    return view;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    return [TOSKitCustomInfo shareCustomInfo].lastMessage_spacing;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    
    TIMMessageFrame *messageF = [self.dataSource objectAtIndex:indexPath.row];
    if (([messageF.model.message.type isEqualToString:TypeCustom] ||
         [messageF.model.message.type isEqualToString:TypeCustomFile]) &&
        messageF.model.message.content) {
        
        //机器人文件
        if (messageF.model.urlPath != nil && messageF.model.urlPath.length>0 && [messageF.model.urlPath rangeOfString:@"bot_media"].location !=NSNotFound) {
            STBaseWebViewController*vc = [[STBaseWebViewController alloc]init];
            vc.urlString = [messageF.model.urlPath stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
            [self.navigationController pushViewController:vc animated:YES];
        } else {
            
            NSString *urlPath = @"";
            NSData *jsonData = [messageF.model.message.content dataUsingEncoding:NSUTF8StringEncoding];
            NSError *err;
            NSDictionary *extraDict = [NSJSONSerialization JSONObjectWithData:jsonData
                                                                      options:NSJSONReadingMutableContainers
                                                                        error:&err];
            NSString *fileKey = @"null";
            NSString *fileName = @"null";
            if (![kitUtils isBlankString:extraDict[@"fileKey"]]) {
                fileKey = extraDict[@"fileKey"];
            }
            if (![kitUtils isBlankString:extraDict[@"fileName"]]) {
                fileName = extraDict[@"fileName"];
            }
            
            if (![kitUtils isBlankString:extraDict[@"messageType"]] &&
                [extraDict[@"messageType"] isEqualToString:@"8"]) {
                urlPath = messageF.model.urlPath;
            } else {
                NSString * timestamp = [kitUtils getNowTimestampWithSec];
                
                NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
                NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
                urlPath =   [NSString stringWithFormat:@"%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[NSString stringWithFormat:@"%@/api/app/message/file",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl]],fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign];
            }
            
            STBaseWebViewController*vc = [[STBaseWebViewController alloc]init];
            vc.urlString = [urlPath stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
            [self.navigationController pushViewController:vc animated:YES];
        }
    }
    [self.chatBoxVC resignFirstResponder];
}

- (void)scrollViewWillBeginDragging:(UIScrollView *)scrollView
{
    [self.chatBoxVC resignFirstResponder];
}

- (void)tableView:(UITableView *)tableView didEndDisplayingCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath*)indexPath {
    if ([cell isKindOfClass:[ICChatMessageVideoCell class]] && self) {
        ICChatMessageVideoCell *videoCell = (ICChatMessageVideoCell *)cell;
        [videoCell stopVideo];
    }
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
    CGFloat horizontalOffset = scrollView.contentOffset.x;
    CGFloat verticalOffset = scrollView.contentOffset.y;
    // 在这里处理滑动事件，例如根据滑动距离执行特定操作
    
    [self tableViewContentOffset:(CGPointMake(horizontalOffset, verticalOffset)) withMessageHeight:(self.view.bottom - kNavTop - [TOSKitCustomInfo shareCustomInfo].chatBox_Height - kBottomBarHeight)];
    
}


/// 当前列表的滑动高度，子类重写该方法可以获取当前列表的滑动高度
/// - Parameters:
///   - contentOffset: 当前列表的滑动范围
///   - messageHeight: 消息列表的高度
- (void)tableViewContentOffset:(CGPoint)contentOffset withMessageHeight:(CGFloat)messageHeight {
    
}

- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView {
    if (scrollView != self.tableView) {
        return;
    }
    BOOL scrollToScrollStop = !scrollView.tracking && !scrollView.dragging && !scrollView.decelerating;
    if (scrollToScrollStop) {
        
        CGFloat contentOffsetY = self.tableView.contentOffset.y;
        CGFloat bottomOffset = self.tableView.contentSize.height - contentOffsetY;
        if (bottomOffset <= 10 && self.dataSource.count > 0) {
            //在最底部
            NSInteger count = self.dataSource.count - 1;
            TIMMessageFrame *messageFrame = self.dataSource[count];
            [self p_needShowTime:messageFrame.model.message.msgDate];
        }
    }
}




#pragma mark - ICChatBoxViewControllerDelegate

- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController didChangeChatBoxFaceTextViewHeight:(CGFloat)height withFaceHeight:(CGFloat)faceHeight {
    
    self.chatBoxVC.view.top = kScreenHeight - height - faceHeight - kNavTop - kBottomBarHeight;
    self.tableView.height = kScreenHeight - height - faceHeight - kNavTop - kBottomBarHeight;
    
    int iHeight = (int)height;
    if (iHeight == (int)(self.chatBoxVC.chatBox.textView.height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height)) {
        if (self.chatBoxVC.chatBox.status == ICChatBoxStatusShowKeyboard && self.chatBoxVC.keyboardFrame.size.height <= 0) {
            
        }else{
            [self scrollToBottom];
            _isKeyBoardAppear  = NO;
        }
    } else if(iHeight == (int)(self.chatBoxVC.chatBox.textView.height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height + HEIGHT_CHATBOXVIEW)){
        
        [self scrollToBottom];
    } else {
        if (IphoneX) {
            self.chatBoxVC.view.top += kBottomBarHeight;
            self.tableView.height += kBottomBarHeight;
        }
        [self scrollToBottom];
        _isKeyBoardAppear  = YES;
    }
    if (self.textView == nil) {
        self.textView = chatboxViewController.chatBox.textView;
    }
    
}

- (void)chatBoxDidClickBarItemViewController:(TIMChatBoxViewController *)chatboxViewController withItemIndex:(NSInteger)index {
    
    [self quickEntryItemDidTouchIndex:index];
    
}
/// 子类可以重写这个方法
- (void)quickEntryItemDidTouchIndex:(NSInteger)index {
}

/// 当前会话状态监听
- (void)chatStatusChanged:(TinetChatStatusType)status{
    
}

- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
                inputATSymbol:(NSString *)symbol
                   inputRange:(NSRange)range {
}

- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
               deleteATSymbol:(NSString *)symbol
                   inputRange:(NSRange)range {
    
}

/// 删除@符号和空格结尾
/// @param oldString oldString
- (NSString *)deleteATSymbolsWithString:(NSString *)oldString {
    NSString *deleteAT = [oldString stringByReplacingOccurrencesOfString:@"@" withString:@""];
    NSString *deleteATEnd = [deleteAT stringByReplacingOccurrencesOfString:kTIMATEnd withString:@""];
    return deleteATEnd;
}

- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
didChangeChatBoxTextViewHeight:(CGFloat)height
{
    self.chatBoxVC.view.top = kScreenHeight-height - kNavTop - kBottomBarHeight;
    self.tableView.height = kScreenHeight - height - kNavTop - kBottomBarHeight;
    int iHeight = (int)height;
    if (iHeight == (int)(self.chatBoxVC.chatBox.textView.height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height)) {
        if (self.chatBoxVC.chatBox.status == ICChatBoxStatusShowKeyboard && self.chatBoxVC.keyboardFrame.size.height <= 0) {
            
        }else{
            [self scrollToBottom];
            _isKeyBoardAppear  = NO;
        }
    } else if(iHeight == (int)(self.chatBoxVC.chatBox.textView.height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height + HEIGHT_CHATBOXVIEW)){
        [self scrollToBottom];
    } else {
        /// 表情面板不需要再做底部安全区域的计算，系统的软件盘需要考虑安全区域的操作
        if (IphoneX && self.chatBoxVC.chatBox.status != ICChatBoxStatusShowFace) {
            
            self.chatBoxVC.view.top += kBottomBarHeight;
            self.tableView.height += kBottomBarHeight;
        }
        [self scrollToBottom];
        _isKeyBoardAppear  = YES;
    }
    if (self.textView == nil) {
        self.textView = chatboxViewController.chatBox.textView;
    }
}
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
didChangeChatBoxKBToVoiceHeight:(CGFloat)height{
    
    CGFloat chatBarHeight = self.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
    self.chatBoxVC.view.top = kScreenHeight-height - kNavTop - kBottomBarHeight - chatBarHeight;
    self.tableView.height = kScreenHeight - height - kNavTop - kBottomBarHeight - chatBarHeight;
    int iHeight = (int)height;
    if (iHeight == [TOSKitCustomInfo shareCustomInfo].chatBox_Height) {
        if (self.chatBoxVC.chatBox.status == ICChatBoxStatusShowKeyboard && self.chatBoxVC.keyboardFrame.size.height <= 0) {
            
        }else{
            
            [self.tableView reloadData];
            _isKeyBoardAppear  = NO;
        }
    } else {
        if (IphoneX) {
            self.chatBoxVC.view.top += kBottomBarHeight;
            self.tableView.height += kBottomBarHeight;
        }
        [self scrollToBottom];
        _isKeyBoardAppear  = YES;
    }
    if (self.textView == nil) {
        self.textView = chatboxViewController.chatBox.textView;
    }
}

- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
        didChangeChatBoxHeight:(CGFloat)height
{
    
    CGFloat chatBarHeight = self.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
    CGFloat top = kScreenHeight-height - kNavTop- kBottomBarHeight - chatBarHeight;
    if (top - self.chatBoxVC.view.top >= 5 &&
        height == (int)(self.chatBoxVC.chatBox.textView.height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height) &&
        self.chatBoxVC.chatBox.status == ICChatBoxStatusShowKeyboard &&
        self.chatBoxVC.keyboardFrame.size.height <= 0) {
        [self scrollToBottom];
    }
    self.chatBoxVC.view.top = kScreenHeight-height - kNavTop- kBottomBarHeight - chatBarHeight;
    self.tableView.height = kWindowHeight - height - kNavTop- kBottomBarHeight - chatBarHeight;
    
    int iHeight = (int)height;
    if (iHeight == (int)(self.chatBoxVC.chatBox.textView.height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height)) {
        if ((self.chatBoxVC.chatBox.status == ICChatBoxStatusShowKeyboard ||
             self.chatBoxVC.chatBox.status == ICChatBoxStatusShowVoice) && self.chatBoxVC.keyboardFrame.size.height <= 0) {
        }else{
            _isKeyBoardAppear  = NO;
        }
    }else if(iHeight == (int)(self.chatBoxVC.chatBox.textView.height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height + HEIGHT_CHATBOXVIEW)){
        [self scrollToBottom];
    }else{
        /// 表情面板不需要再做底部安全区域的计算，系统的软件盘需要考虑安全区域的操作
        if (IphoneX && self.chatBoxVC.chatBox.status != ICChatBoxStatusShowFace) {
            self.chatBoxVC.view.top += kBottomBarHeight;
            self.tableView.height += kBottomBarHeight;
        }
        [self scrollToBottom];
        _isKeyBoardAppear  = YES;
    }
    if (self.textView == nil) {
        self.textView = chatboxViewController.chatBox.textView;
    }
}

- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController didVideoViewAppeared:(ICVideoView *)videoView
{
    [_chatBoxVC.view setFrame:CGRectMake(0, HEIGHT_SCREEN-[TOSKitCustomInfo shareCustomInfo].chatBox_Height-kNavTop, App_Frame_Width, APP_Frame_Height)];
    [UIView animateWithDuration:0.5 animations:^{
        self.tableView.height = HEIGHT_SCREEN - videwViewH - kNavTop;
        self.chatBoxVC.view.frame = CGRectMake(0, videwViewX+kNavTop, App_Frame_Width, videwViewH);
        [self scrollToBottom];
    } completion:^(BOOL finished) { // 状态改变
        self.chatBoxVC.chatBox.status = ICChatBoxStatusShowVideo;
        // 在这里创建视频设配
        UIView *videoLayerView = [videoView viewWithTag:1000];
        UIView *placeholderView = [videoView viewWithTag:1001];
        [[ICVideoManager shareManager] setVideoPreviewLayer:videoLayerView];
        [NSTimer scheduledTimerWithTimeInterval:1.0 target:self selector:@selector(videoPreviewLayerWillAppear:) userInfo:placeholderView repeats:NO];
    }];
}

- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController sendVideoMessage:(NSString *)videoPath duration:(CGFloat)videoTimeLength  thumbnailImagePath:(NSString *)thumbnailImagePath
{
    if (thumbnailImagePath && videoPath) {
        NSData *imageData = [NSData dataWithContentsOfFile:thumbnailImagePath];
        UIImage * imageSrc = [UIImage imageWithData:imageData];
        CGFloat fixelW = imageSrc.size.width;
        CGFloat fixelH = imageSrc.size.height;
        
        NSData *videoData;
        NSString * strRealVideoPath;
        if ([[videoPath substringToIndex:7] isEqualToString:@"file://"]) {
            strRealVideoPath = [videoPath substringFromIndex:7];
        }else{
            strRealVideoPath = videoPath;
        }
        videoData = [NSData dataWithContentsOfFile:strRealVideoPath];
        
        // 生成新的文件名称为uuid
        NSString * videoFileName = [[videoPath lastPathComponent] stringByDeletingPathExtension];
        
        NSString * videoImagePath = [[ICMediaManager sharedManager] saveVideoImage:imageSrc fileName:videoFileName];
        
        double size = [videoData length]/1000.f/1024.f;
        
        int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
        
        if (sizeLimitM < size) {
            NSString * strShow = [NSString stringWithFormat:@"请发送小于%dM文件",sizeLimitM];
            [WHToast showMessage:strShow duration:2 finishHandler:^{
                
            }];
            return;
        }
        
        
        // Kit
        __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo
                                                                   originalType:@"video"
                                                                        content:@"[视频]"
                                                                          extra:@""
                                                                     auditExtra:@""
                                                                           path:videoPath
                                                                        urlPath:nil
                                                                           from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                             to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                        fileKey:[[NSUUID UUID] UUIDString]
                                                                   bigImgFileId:@""
                                                                  voiceDuration:[NSNumber numberWithInt:0]
                                                                          msgId:@""
                                                                       sendTime:[self getNowTimestamp]
                                                                       showTime:[self p_needShowTime:[NSDate date]]
                                                                        picSize:CGSizeMake(fixelW, fixelH)
                                                                        picType:@""
                                                                       isSender:YES
                                                       receivedSenderByYourself:NO
                                                                         status:TIMMessageStatus_Sending];
        
        @weakify(self);
        if (self.isCloseSession) {
            
            NSString * moreInfo = [[OnlineDataSave shareOnlineDataSave] getConnectAdParams];
            [[OnlineRequestManager sharedCustomerManager]visitorReadyWithDict:[kitUtils dictionaryWithJsonString:moreInfo]
                                                                      success:^(TOSSessionInfoModel * _Nonnull sessModel) {
                @strongify(self);
                
                /// 保存会话在线状态事件
                [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[sessModel yy_modelToJSONString]];
                self.offlineType = offlineMessageTypeVideo;
                self.offlineNewMessage = messageF;
                self.offlineMediaData = videoData;
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                
            }];
        }
        else {
            // 创建本地消息
            [self addObject:messageF isSender:YES isHeaderInsert:NO];
            // 如果当前mqtt连接中断(包含被踢下线的情况)
            if ([[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
                // 发送中
                [self messageSending:messageF];
            } else {
                [self messageSendFailed:messageF];
                return;
            }
            
            [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVideoMessageWithVideoData:videoData success:^(NSString * _Nonnull messageId) {
                @strongify(self);
                messageF.model.message.messageId = messageId;
                [self updateOldMessageId:messageId];
                [self messageSendSucced:messageF];
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                @strongify(self);
                [self messageSendFailed:messageF];
                
                int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
                NSString * strShow = [NSString stringWithFormat:@"文件过大，请发送小于%dM文件",sizeLimitM];
                [WHToast showMessage:errorDes?:strShow duration:2 finishHandler:^{
                    
                }];
            }];
        }
    }
}

// send image message
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
           sendGifImageMessage:(UIImage *)image
                     imagePath:(NSString *)imgPath
{
    if (image && imgPath) {
        CGFloat fixelW = image.size.width;
        CGFloat fixelH = image.size.width;
        
        TIMImageMessage * imageMsg = [[TIMImageMessage alloc] initMessageWithImageURI:imgPath extra:@""];
        
        TOSMessage * timMsg = [[TOSMessage alloc] initWithOption:[kitUtils getMsgUUID] msg_id:@"" type:@"image" senderId:[[OnlineDataSave shareOnlineDataSave] getVisitorId] receiverId:[[OnlineDataSave shareOnlineDataSave] getEndpointId] content:imageMsg msg_from:[self isGroupSession]?TIMSessionType_GROUP_CHAT:/*TIMSessionType_SINGLE_CHAT*/TIMSessionType_OnLINESERVICE_CHAT status:TIMMessageStatus_Sending timestamp:0];
        TIMMessagePushOption * pushOption = [[TIMMessagePushOption alloc] initWithOption:YES title:@"" content:@"[图片]" pushData:nil];
        TIMMessageSendOption * sendOption = [[TIMMessageSendOption alloc] initWithOption:[[OnlineDataSave shareOnlineDataSave] getEndpointId] content:timMsg pushOption:pushOption];
        NSData *imageData = UIImageJPEGRepresentation(image,1.0f);
        [[ICMediaManager sharedManager] saveImage:image msgId:timMsg.messageUUID picType:[UIImage typeForImageData:imageData]];
        
        // Kit
        CGSize imageSize = CGSizeMake(fixelW, fixelH);
        __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic originalType:@"image" content:@"[图片]" extra:@"" auditExtra:@"" path:imgPath urlPath:nil from:[[OnlineDataSave shareOnlineDataSave] getVisitorId] to:[[OnlineDataSave shareOnlineDataSave] getEndpointId] fileKey:timMsg.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:timMsg.msg_id sendTime:[self getNowTimestamp] showTime:[self p_needShowTime:[NSDate date]] picSize:imageSize picType:kGIFTimageType isSender:YES receivedSenderByYourself:NO status:@(timMsg.status).intValue];
        [self addObject:messageF isSender:YES isHeaderInsert:NO];
        @WeakObj(self)
        [[TIMClient sharedTIMClient] sendMessage:sendOption  progress:^(float progress) {
            TIMKitLog(@"send gif image progress = %f",progress);
        } success:^(TOSMessage * timMessage){
            @StrongObj(self)
            TIMKitLog(@"send gif image success messageId = %@",timMessage.msg_id);
            messageF.model.message.messageId = timMessage.msg_id;
            [self updateOldMessageId:timMessage.msg_id];
            [self messageSendSucced:messageF];
        } error:^(TOSMessage *message, TIMConnectErrorCode nErrorCode, NSString * _Nonnull errorDes) {
            @StrongObj(self)
            TIMKitLog(@"send gif image message = %@ nErrorCode = %ld errorDes= %@",message,(long)nErrorCode,errorDes);
            [self tim_showMBErrorView:errorDes];
            [self messageSendFailed:messageF];
        }];
    }
}


// send image message
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
              sendImageMessage:(UIImage *)image
                     imagePath:(NSString *)imgPath
{
    TIMKitLog(@"发送图片 path:%@ src:%@ last:%@",imgPath,image,[[imgPath lastPathComponent] stringByDeletingPathExtension]);
    if (image) {
        CGFloat fixelW = image.size.width;
        CGFloat fixelH = image.size.height;
        
        NSData*uploadThumbFileData = UIImageJPEGRepresentation(image, 0.5);
        if (50 < [uploadThumbFileData length]/1000.f/1024.f) {
            return;
        }
        
        // Kit
        CGSize imageSize = CGSizeMake(fixelW, fixelH);
        __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic
                                                                   originalType:@"image"
                                                                        content:@"[图片]"
                                                                          extra:@""
                                                                     auditExtra:@""
                                                                           path:imgPath
                                                                        urlPath:nil
                                                                           from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                             to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                        fileKey:[[NSUUID UUID] UUIDString]
                                                                   bigImgFileId:@""
                                                                  voiceDuration:[NSNumber numberWithInt:0]
                                                                          msgId:@""
                                                                       sendTime:[self getNowTimestamp]
                                                                       showTime:[self p_needShowTime:[NSDate date]]
                                                                        picSize:imageSize
                                                                        picType:@""
                                                                       isSender:YES
                                                       receivedSenderByYourself:NO
                                                                         status:TIMMessageStatus_Sending];
        if (self.isCloseSession) {
            @weakify(self);
            NSString * moreInfo = [[OnlineDataSave shareOnlineDataSave] getConnectAdParams];
            [[OnlineRequestManager sharedCustomerManager]visitorReadyWithDict:[kitUtils dictionaryWithJsonString:moreInfo]
                                                                      success:^(TOSSessionInfoModel * _Nonnull sessModel) {
                @strongify(self);
                
                /// 保存会话在线状态事件
                [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[sessModel yy_modelToJSONString]];
                
                self.offlineType = offlineMessageTypeImage;
                self.offlineNewMessage = messageF;
                self.offlineMediaData = uploadThumbFileData;
                
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                
            }];
            
        }
        else {
            [self addObject:messageF isSender:YES isHeaderInsert:NO];
            // 如果当前mqtt连接中断(包含被踢下线的情况)
            if ([[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
                // 发送中
                [self messageSending:messageF];
            } else {
                [self messageSendFailed:messageF];
                return;
            }
            [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendImageMessageWithImageData:uploadThumbFileData        success:^(NSString * _Nonnull messageId,NSString * _Nonnull fileKey) {
                messageF.model.message.messageId = messageId;//timMessage.msg_id;
                [self updateOldMessageId:messageId];
                [self messageSendSucced:messageF];
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                [self messageSendFailed:messageF];
            }];
        }
    }
}

- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController sendFileMessage:(NSString *)fileName {
}

// send text message
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
               sendTextMessage:(NSString *)messageStr
{
    
    [self sendTextMessagewithText:messageStr knowledge:@""];
}

-(void)sendTextMessagewithText:(NSString*)messageStr knowledge:(NSString *)knowledge {
    if (!self.chatCloseView.hidden) {   //在会话结束状态下，不再发送消息
        return;
    }
    NSCharacterSet *set = [NSCharacterSet whitespaceAndNewlineCharacterSet];
    NSString *trimedString = [messageStr stringByTrimmingCharactersInSet:set];
    if([trimedString length] == 0) {
        [self.chatBoxVC.chatBox.textView resignFirstResponder];
        [self.view tim_showMBErrorView:@"不能发送空白消息"];
        return;
    }
    // Kit
    __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText
                                                               originalType:@"text"
                                                                    content:messageStr
                                                                      extra:@""
                                                                 auditExtra:@""
                                                                       path:nil
                                                                    urlPath:nil
                                                                       from:[[OnlineDataSave shareOnlineDataSave]getVisitorId]
                                                                         to:[[OnlineDataSave shareOnlineDataSave]getEndpointId]
                                                                    fileKey:[[NSUUID UUID] UUIDString]
                                                               bigImgFileId:@""
                                                              voiceDuration:[NSNumber numberWithInt:0]
                                                                      msgId:[NSString stringWithFormat:@"%ld",[self getNowTimestamp]]
                                                                   sendTime:[self getNowTimestamp]
                                                                   showTime:[self p_needShowTime:[NSDate date]]
                                                                    picSize:CGSizeMake(0, 0)
                                                                    picType:@""
                                                                   isSender:YES
                                                   receivedSenderByYourself:NO
                                                                     status:TIMMessageStatus_Sending];
    messageF.model.message.knowledge = knowledge;
    
    [NSObject cancelPreviousPerformRequestsWithTarget:self selector:@selector(sendInputHintEvent) object:nil];
    [self performSelector:@selector(sendInputHintEvent) withObject:nil afterDelay:0.5];
    
    if (self.isCloseSession) {
        @weakify(self);
        NSString * moreInfo = [[OnlineDataSave shareOnlineDataSave] getConnectAdParams];
        [[OnlineRequestManager sharedCustomerManager]visitorReadyWithDict:[kitUtils dictionaryWithJsonString:moreInfo]
                                                                  success:^(TOSSessionInfoModel * _Nonnull sessModel) {
            
            @strongify(self);
            /// 保存会话在线状态事件
            [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[sessModel yy_modelToJSONString]];
            
            self.offlineType = offlineMessageTypeText;
            self.offlineNewMessage = messageF;
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            
        }];
    }
    else {
        
        [self addObject:messageF isSender:YES isHeaderInsert:NO];
        // 发送中
        [self messageSending:messageF];
        
        //lib
        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendTextMessageWithMessageStr:messageStr knowledge:knowledge messageUUID:messageF.model.message.fileKey success:^(TOSMessage * timMessage) {
            messageF.model.message.messageId = timMessage.msg_id;
            [self updateOldMessageId:timMessage.msg_id];
            [self messageSendSucced:messageF];
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            [self messageSendFailed:messageF];
        }];
    }
    
    
}

//发送输入预知事件
-(void)sendInputHintEvent{
    [[OnlineEventSendManager sharedOnlineEventSendManager]inputHintEventWithText:@"" success:^{
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
    }];
}

//发送访客上线事件
-(void)sendChatOnline{
    
    [[OnlineEventSendManager sharedOnlineEventSendManager] chatOnlineEventWithSuccess:^{
        //访客上线
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        //访客上线失败
    }];
    
}


#pragma mark - 对于重写了会话结束UI方法后的机器人欢迎语消息排序问题解决方案（如果没有机器人欢迎语消息会有问题，如果是人工座席接待遇到同样的问题就需要修改里面的逻辑了）
/// 发送再次建立链接的离线消息
- (void)sendOfflineNewMessage {
    
    NSLog(@"sendOfflineNewMessage");
    
    TinetChatStatusType type = [[TOSClientKit sharedTOSKit] getCurrentOnlineStatus];
    /// 当前不是机器人状态不需要走下面的逻辑。
    if (type != TinetChatStatusTypeRobot) {
        return;
    }
    
    /// 没有记录的不需要走后续的发送逻辑
    if (self.offlineType == offlineMessageTypeNormal) {
        return;
    }
    
    __block TIMMessageFrame * messageF = self.offlineNewMessage;
    /// 添加消息到列表中
    [self addObject:messageF isSender:YES isHeaderInsert:NO];
    
    /// 如果当前mqtt连接中断(包含被踢下线的情况)
    if ([[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
        /// 发送中
        [self messageSending:messageF];
    } else {
        /// 发送失败
        [self messageSendFailed:messageF];
        return;
    }
    
    
    @weakify(self);
    if (self.offlineType == offlineMessageTypeText) {
        //lib
        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendTextMessageWithMessageStr:messageF.model.message.content knowledge:messageF.model.message.knowledge messageUUID:messageF.model.message.fileKey success:^(TOSMessage * timMessage) {
            @strongify(self);
            messageF.model.message.messageId = timMessage.msg_id;
            [self updateOldMessageId:timMessage.msg_id];
            [self messageSendSucced:messageF];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineNewMessage = nil;
            
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            @strongify(self);
            [self messageSendFailed:messageF];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineNewMessage = nil;
        }];
    }
    else if (self.offlineType == offlineMessageTypeVoice) {
        [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVoiceMessageWithVoiceData:self.offlineMediaData success:^(NSString * _Nonnull messageId) {
            @strongify(self);
            messageF.model.message.messageId = messageId;
            [self updateOldMessageId:messageId];
            [self messageSendSucced:messageF];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineMediaData = nil;
            self.offlineNewMessage = nil;
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            @strongify(self);
            [self messageSendFailed:messageF];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineMediaData = nil;
            self.offlineNewMessage = nil;
        }];
    }
    else if (self.offlineType == offlineMessageTypeImage) {
        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendImageMessageWithImageData:self.offlineMediaData        success:^(NSString * _Nonnull messageId,NSString * _Nonnull fileKey) {
            @strongify(self);
            messageF.model.message.messageId = messageId;//timMessage.msg_id;
            [self updateOldMessageId:messageId];
            [self messageSendSucced:messageF];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineMediaData = nil;
            self.offlineNewMessage = nil;
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            @strongify(self);
            [self messageSendFailed:messageF];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineMediaData = nil;
            self.offlineNewMessage = nil;
        }];
    }
    else if (self.offlineType == offlineMessageTypeVideo) {
        [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVideoMessageWithVideoData:self.offlineMediaData success:^(NSString * _Nonnull messageId) {
            @strongify(self);
            messageF.model.message.messageId = messageId;
            [self updateOldMessageId:messageId];
            [self messageSendSucced:messageF];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineMediaData = nil;
            self.offlineNewMessage = nil;
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            @strongify(self);
            [self messageSendFailed:messageF];
            self.offlineType = offlineMessageTypeNormal;
            self.offlineMediaData = nil;
            self.offlineNewMessage = nil;
            
            int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
            NSString * strShow = [NSString stringWithFormat:@"文件过大，请发送小于%dM文件",sizeLimitM];
            [WHToast showMessage:errorDes?:strShow duration:2 finishHandler:^{
                
            }];
        }];
    }
    
}

#pragma mark - TIMChatBoxViewControllerDelegate
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
                    senderFile:(NSInteger)senderFile {
    
    SYCacheFileViewController *cacheVC = [[SYCacheFileViewController alloc] init];
    [SYCacheFileManager shareManager].showImageShuffling = YES;
    cacheVC.showType = 0;
    [self.navigationController pushViewController:cacheVC animated:YES];
}

//转人工快捷方式
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
                  zhuanRenGong:(NSInteger)defaultItem{
    //发送转人工文字消息
    [self sendTextMessagewithText:@"转人工" knowledge:@""];
}

//结束会话快捷方式
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
                     closeChat:(NSInteger)defaultItem{
    
    [self.chatBoxVC.chatBox.textView resignFirstResponder];
    self.chatBoxVC.chatBox.textView.text = @"";
    [self.chatBoxVC.chatBox checkSendButtonType];
    [[OnlineDataSave shareOnlineDataSave] saveUserInputText:@""];
    
    [self.chatBoxVC.chatBox switchTextEditing];
    
    [self closeChatActon];
}

/// 自定义按钮
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController item:(ICChatBoxMoreViewItem *)item {
    TOSKitExtendBoardItemModel *model = [[TOSKitExtendBoardItemModel alloc] init];
    model.title = item.title;
    model.image = item.imageName;
    model.index = item.index;
    model.type = item.type;
    [self didClinkCustomExtendBoardItemAction:model];
}

- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController sendVoiceMessage:(NSString *)voicePath {
    TIMKitLog(@"sendVoiceMessage");
    [self timerInvalue]; // 销毁定时器
    self.voiceHud.hidden = YES;
    if ([TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordView != nil) {
        [self removeCustomVoiceHud];
    }
    if (voicePath) {
        // 获取时长
        int voiceDuration = (int)[[ICRecordManager shareManager] durationWithVideo:[NSURL fileURLWithPath:voicePath]];
        if (voiceDuration < 1000) {
            // 小于一秒的显示1秒
            voiceDuration = 1000;
        }
        
        
        NSData*voiceData = [NSData dataWithContentsOfFile:voicePath];
        if (50 < [voiceData length]/1000.f/1024.f) {
            return;
        }
        
        // Kit
        __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVoice
                                                                   originalType:@"voice"
                                                                        content:@"[语音]"
                                                                          extra:@""
                                                                     auditExtra:@""
                                                                           path:voicePath
                                                                        urlPath:nil
                                                                           from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                             to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                        fileKey:[[NSUUID UUID] UUIDString]
                                                                   bigImgFileId:@""
                                                                  voiceDuration:[NSNumber numberWithInt:voiceDuration]
                                                                          msgId:@""
                                                                       sendTime:[self getNowTimestamp]
                                                                       showTime:[self p_needShowTime:[NSDate date]]
                                                                        picSize:CGSizeZero
                                                                        picType:@""
                                                                       isSender:YES
                                                       receivedSenderByYourself:NO
                                                                         status:TIMMessageStatus_Sending];
        if (self.isCloseSession) {
            @weakify(self);
            NSString * moreInfo = [[OnlineDataSave shareOnlineDataSave] getConnectAdParams];
            [[OnlineRequestManager sharedCustomerManager]visitorReadyWithDict:[kitUtils dictionaryWithJsonString:moreInfo]
                                                                      success:^(TOSSessionInfoModel * _Nonnull sessModel) {
                
                @strongify(self);
                
                /// 保存会话在线状态事件
                [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[sessModel yy_modelToJSONString]];
                
                self.offlineType = offlineMessageTypeVoice;
                self.offlineNewMessage = messageF;
                self.offlineMediaData = voiceData;
                
                
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                
            }];
        }
        else {
            [self addObject:messageF isSender:YES isHeaderInsert:NO];
            // 如果当前mqtt连接中断(包含被踢下线的情况)
            
            if ([[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
                // 发送中
                [self messageSending:messageF];
            } else {
                [self messageSendFailed:messageF];
                return;
            }
            [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVoiceMessageWithVoiceData:voiceData success:^(NSString * _Nonnull messageId) {
                messageF.model.message.messageId = messageId;
                [self updateOldMessageId:messageId];
                [self messageSendSucced:messageF];
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                [self messageSendFailed:messageF];
            }];
        }
        
    }
}


- (void)voiceDidCancelRecording {
    [self timerInvalue];
    if ([TOSKitCustomInfo shareCustomInfo].chatBox_voiceCancelRecordView != nil) {
        [self removeCustomVoiceHud];
    }
    else {
        self.voiceHud.hidden = YES;
    }
}


- (void)voiceDidStartRecording {
    [self timerInvalue];
    
    if ([TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordView != nil) {
        [self.view addSubview:[TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordView];
    }
    else {
        self.voiceHud.hidden = NO;
    }
    [self timer];
}


- (void)voiceRecordSoShort {
    [self timerInvalue];
    
    if ([TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordSoShortView != nil) {
        
        [self removeCustomVoiceHud];
        [self.view addSubview:[TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordSoShortView];
        @WeakObj(self)
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)([TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordSoShortTime * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            @StrongObj(self)
            [[TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordSoShortView removeFromSuperview];
            [self removeCustomVoiceHud];
        });
    }
    else {
        self.voiceHud.animationImages = nil;
        self.voiceHud.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"voiceShort"]];
        @WeakObj(self)
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.0 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            @StrongObj(self)
            self.voiceHud.hidden = YES;
        });
    }
}


- (void)voiceWillDragout:(BOOL)inside {
    if (inside) {
        
        if ([TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordView != nil) {
            [_timer setFireDate:[NSDate distantPast]];
            [self removeCustomVoiceHud];
            [self.view addSubview:[TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordView];
        }
        else {
            [_timer setFireDate:[NSDate distantPast]];
            _voiceHud.image  = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"voice_1"]];
        }
    } else {
        if ([TOSKitCustomInfo shareCustomInfo].chatBox_voiceCancelRecordView != nil) {
            [_timer setFireDate:[NSDate distantPast]];
            [self removeCustomVoiceHud];
            [self.view addSubview:[TOSKitCustomInfo shareCustomInfo].chatBox_voiceCancelRecordView];
            
        }
        else {
            [_timer setFireDate:[NSDate distantFuture]];
            self.voiceHud.animationImages  = nil;
            self.voiceHud.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"cancelVoice"]];
        }
    }
}

/// 移除自定义的语音录制hud视图
- (void)removeCustomVoiceHud {
    for (UIView * item in self.view.subviews) {
        if (item == [TOSKitCustomInfo shareCustomInfo].chatBox_voiceRecordView) {
            [item removeFromSuperview];
        }
        if (item == [TOSKitCustomInfo shareCustomInfo].chatBox_voiceCancelRecordView) {
            [item removeFromSuperview];
        }
    }
}



- (void)progressChange
{
    AVAudioRecorder *recorder = [[ICRecordManager shareManager] recorder] ;
    [recorder updateMeters];
    float power= [recorder averagePowerForChannel:0];//取得第一个通道的音频，注意音频强度范围时-160到0,声音越大power绝对值越小
    CGFloat progress = (1.0/160)*(power + 160);
    self.voiceHud.progress = progress;
}

- (void)timerInvalue
{
    [_timer invalidate];
    _timer  = nil;
}

- (NSTimer *)timer
{
    if (!_timer) {
        _timer =[NSTimer scheduledTimerWithTimeInterval:0.3f target:self selector:@selector(progressChange) userInfo:nil repeats:YES];
    }
    return _timer;
}

- (ICVoiceHud *)voiceHud
{
    if (!_voiceHud) {
        _voiceHud = [[ICVoiceHud alloc] initWithFrame:CGRectMake(0, 0, 155, 155)];
        
        _voiceHud.hidden = YES;
        [self.view addSubview:_voiceHud];
        _voiceHud.center = CGPointMake(App_Frame_Width/2, APP_Frame_Height/2);
    }
    return _voiceHud;
}

#pragma mark - Getter and Setter

- (TIMChatBoxViewController *) chatBoxVC
{
    if (_chatBoxVC == nil) {
        _chatBoxVC = [[TIMChatBoxViewController alloc] init];
        _chatBoxVC.barItemArray = self.quickEntryAllItems;
        CGFloat barHeight = self.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
        [_chatBoxVC.view setFrame:CGRectMake(0,APP_Frame_Height-[TOSKitCustomInfo shareCustomInfo].chatBox_Height-kNavTop-kBottomBarHeight-barHeight, App_Frame_Width, APP_Frame_Height)];
        _chatBoxVC.delegate = self;
    }
    return _chatBoxVC;
}

-(UIView *)chatCloseView{
    if (!_chatCloseView) {
        CGFloat barHeight = self.quickEntryAllItems.count ? CHATBOX_BAR_HEIGHT : 0.0f;
        _chatCloseView = [[UIView alloc]initWithFrame:CGRectMake(0,APP_Frame_Height-[TOSKitCustomInfo shareCustomInfo].chatBox_Height-kNavTop-kBottomBarHeight-barHeight, SCREEN_WIDTH, [TOSKitCustomInfo shareCustomInfo].chatBox_Height+barHeight)];
        _chatCloseView.backgroundColor = TOSHexAColor(0xC8CBCE,1.0);
    }
    return _chatCloseView;
}
-(void)openAction{
    
    /*访客初始化回调
     当APP端IM mqtt 连接就绪，主动通知服务端，APP端调用该接口后服务端
     就开始会话流程逻辑创建会话*/
    // 此处不会走KT环境
    NSString * moreInfo = [[OnlineDataSave shareOnlineDataSave] getConnectAdParams];
    [[OnlineRequestManager sharedCustomerManager]visitorReadyWithDict:[kitUtils dictionaryWithJsonString:moreInfo]
                                                              success:^(TOSSessionInfoModel * _Nonnull sessModel) {
        self.chatCloseView.hidden = YES;
        self.chatBoxVC.view.hidden = NO;
        
        /// 保存会话在线状态事件
        [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[sessModel yy_modelToJSONString]];
        
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        
    }];
}

-(TOSBaseTableView *)tableView
{
    if (nil == _tableView) {
        _tableView = [[TOSBaseTableView alloc] initWithFrame:CGRectZero style:UITableViewStylePlain];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.backgroundView = nil;
        
        UIView* tempView = [[UIView alloc] initWithFrame:_tableView.frame];
        tempView.backgroundColor= [TOSKitCustomInfo shareCustomInfo].chat_backGround;
        _tableView.backgroundView= tempView;
        _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
        if (@available(iOS 15.0, *)) {
            _tableView.sectionHeaderTopPadding = 0.f;
            [UITableView appearance].sectionHeaderTopPadding = 0.f;
        }
        _tableView.estimatedRowHeight = 0.f;
        _tableView.estimatedSectionHeaderHeight = 0.f;
        _tableView.estimatedSectionFooterHeight = 0.f;
    }
    return _tableView;
}

- (NSMutableArray *)dataSource
{
    if (_dataSource == nil) {
        _dataSource = [NSMutableArray array];
    }
    return _dataSource;
}

#pragma mark - 接收Chat的刷新通知 来自lib层
- (void)messageSendedUpdateUIFromLibNotification:(NSNotification *)sender {
    TOSMessage *recvMessage = [sender object];
    [self.dataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
        if (![kitUtils isBlankString:recvMessage.messageUUID] && [obj.model.message.fileKey isEqualToString:recvMessage.messageUUID]) {
            obj.model.message.messageId = recvMessage.msg_id;
            [self messageSendSucced:obj];
        }
    }];
}

#pragma mark - 接收Chat的刷新通知
- (void)messageSendedUpdateUINotification:(NSNotification *)sender {
    
    TOSMessage *message = [sender object];
    if (message.msg_from == /*TIMSessionType_SINGLE_CHAT*/TIMSessionType_OnLINESERVICE_CHAT) {
        if ([self isGroupSession] || [message.senderId isEqualToString:[[OnlineDataSave shareOnlineDataSave] getVisitorId]] == NO) {
            
            // 如果是群聊或者发送者Id不是用户Id则退出
            return;
        }
        if ([message.receiverId isEqualToString:[[OnlineDataSave shareOnlineDataSave] getVisitorId]]) {
            // 如果接收的消息 返回
            return;
        }
    }else{
        // 除了单聊就是群聊
        if ([self isGroupSession] && [message.receiverId isEqualToString:[[OnlineDataSave shareOnlineDataSave] getEndpointId]]) {
            if (![message.senderId isEqualToString:[[OnlineDataSave shareOnlineDataSave] getVisitorId]]) {
                // 如果接收的消息 返回
                return;
            }
        }else{
            return;
        }
    }
    if ([message.type isEqualToString:@"text"]) {
        TIMTextMessage * txtMsg = (TIMTextMessage *)message.content;
        [self sendTextMessageWithContent:TypeText originalType:message.type from:message.senderId to:message.receiverId fileKey:message.messageUUID msgId:message.msg_id sendTime:message.timestamp content:txtMsg.content extra:txtMsg.extra];
        [self scrollToBottom];
    }else if([message.type isEqualToString:@"customize"]){
        TIMCustomizeMessage * customMsg = (TIMCustomizeMessage *)message.content;
        NSDictionary * bodyDic;
        NSString *type;
        if ([customMsg.templateName isEqualToString:@"tim-rtcMedia"]) {
            NSDictionary * rtcMediaBody = [kitUtils dictionaryWithJsonString:customMsg.content];
            bodyDic = @{
                @"template":customMsg.templateName,
                @"body":@{
                    @"rtcMediaVersion":rtcMediaBody[@"rtcMediaVersion"],
                    @"onlyAudio":rtcMediaBody[@"onlyAudio"],
                    @"duration":rtcMediaBody[@"duration"],
                    @"stopAction":rtcMediaBody[@"stopAction"],
                },
                @"extra":customMsg.extra
            };
            type = TypeCustom;
        } else if ([customMsg.templateName isEqualToString:@"tim-bmwReportFile"]) {
            bodyDic = @{
                @"template":customMsg.templateName,
                @"body":customMsg.bodyDic,
                @"extra":customMsg.extra
            };
            type = TypeCustomFile;
        } else if ([customMsg.templateName isEqualToString:@"default"]) {
            bodyDic = @{
                @"template":customMsg.templateName,
                @"body":@{
                    @"imgUrl":customMsg.imgUrl,
                    @"shareTitle":customMsg.shareTitle,
                    @"title":customMsg.title,
                    @"content":customMsg.content,
                },
                @"extra":customMsg.extra
            };
            type = TypeCustom;
        } else {
            bodyDic = @{
                @"template":customMsg.templateName,
                @"body":customMsg.bodyDic,
                @"extra":customMsg.extra
            };
            type = TypeCustom;
        }
        [self sendTextMessageWithContent:type originalType:message.type from:message.senderId to:message.receiverId fileKey:message.messageUUID msgId:message.msg_id sendTime:[self getNowTimestamp] content:[kitUtils DataTOjsonString:bodyDic]];
        [self scrollToBottom];
    }
}

#pragma mark - 重新编辑的通知
- (void)recalledMessageAgainEditNotification:(NSNotification *)sender {
    NSString *str = [sender object];
    [self.chatBoxVC.chatBox switchTextEditing];
    [self.chatBoxVC.chatBox.textView transalteStringEmoticonAttributedWithString:str];
}

#pragma mark - 接收Chat的消息
- (void)messageReceivedNotification:(NSNotification *)sender {
    TOSMessage *message = [sender object];
    NSLog(@"message ====== %@",[message yy_modelToJSONObject]);
    
    // TODO 暂时将已读消息在此发送
    
    TIMMessageReadOption * readOption = [[TIMMessageReadOption alloc] initWithOption:message.senderId targetId:message.receiverId latestMessageId:message.msg_id];
    
    // 增加msg_id为空的判断 直接返回
    if ([readOption.senderId isEqualToString:[[OnlineDataSave shareOnlineDataSave] getVisitorId]]) {
        
        return;
    }
    // 发送已读事件
    
    if ([[TOSClientKit sharedTOSKit] getStateActive]) {//判断是否在前台活跃
        [[OnlineRequestManager sharedCustomerManager] sessionInfoReadWithMainUniqueId:[[OnlineDataSave shareOnlineDataSave] getMainUniqueId]];
    }
    
    TOSSessionInfoModel *sessionInfomodel = [[TOSClientKit sharedTOSKit] getCurrentSessionInfo];
    
    if ([message.type isEqualToString:@"ChatLeadingWordsMessage"]) {//引导语
        ChatLeadingWordsMessage * robotBridgeMsg = (ChatLeadingWordsMessage *)message.content;
        //用系统消息展示
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeSystem originalType:message.type content:robotBridgeMsg.textContent extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self messageReceiveSucced:messageF];
    } else if ([message.type isEqualToString:@"TIMCommodityCardMessage"]) {
        
        TIMCommodityCardMessage *commodityMsg = (TIMCommodityCardMessage *)message.content;
        
        BOOL isSender = NO;
        NSString *senderId;
        NSString *receiverId;
        if ([commodityMsg.senderType isEqualToString:@"2"]) {
            isSender = YES;
            senderId = message.receiverId;
            receiverId = message.senderId;
        } else {
            isSender = NO;
            senderId = message.senderId;
            receiverId = message.receiverId;
        }
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCommodityCardDetails originalType:message.type content:commodityMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:senderId to:receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:isSender receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:isSender isHeaderInsert:NO];
        [self messageReceiveSucced:messageF];
        
    } else if ([message.type isEqualToString:@"TIMLogisticsCardMessage"]) {
        
        TIMLogisticsCardMessage *cardMsg = (TIMLogisticsCardMessage *)message.content;
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeLogisticsCard originalType:message.type content:(NSString *)cardMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self newMessageSendSucced:messageF];
        
    } else if ([message.type isEqualToString:@"ChatSmallProgramMessage"]) {
        
        TOSMessageSmallProgramModel *smallProgramMsg = (TOSMessageSmallProgramModel *)message.content;
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeSmallProgramCard originalType:message.type content:(NSString *)smallProgramMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self newMessageSendSucced:messageF];
    } else if ([message.type isEqualToString:@"ChatInvestigationMessage"]) {//满意度评价
        sessionInfomodel.status = [NSNumber numberWithInt:6];
        ChatInvestigationMessage * InvestigationMsg = (ChatInvestigationMessage *)message.content;
        // 增加满意度评价的文案
        NSString *extra = [TIMLibUtils convertToJsonDataWithDic:@{@"alreadyInvestigation" : @"0", @"uniqueId": message.msg_id?:@""}];
        NSString *content = [[OnlineDataSave shareOnlineDataSave] getAppSetting];
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeInvestigation originalType:message.type content:content extra:extra auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self messageReceiveSucced:messageF];
        // 结束会话
        if ([InvestigationMsg.isClose boolValue]) {
            self.isCloseSession = YES;
            sessionInfomodel.status = [NSNumber numberWithInt:7];
            // 增加结束会话的文案
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText originalType:message.type content:InvestigationMsg.endMessage extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
            
            [self closeViewEvent];
            
            dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.5f * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                [self scrollToBottom];
            });
        }
    }else if ([message.type isEqualToString:@"SystemMessage"]) {//系统消息
        TextMessage * textMsg = (TextMessage *)message.content;
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeSystem originalType:message.type content:textMsg.textContent extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self messageReceiveSucced:messageF];
    }else if ([message.type isEqualToString:@"TextMessage"]) {//文本消息
        TextMessage * textMsg = (TextMessage *)message.content;
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText originalType:message.type content:textMsg.textContent extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self messageReceiveSucced:messageF];
    }else if ([message.type isEqualToString:@"RichTextMessage"]) {//富文本消息
        RichTextMessage * richTextMsg = (RichTextMessage *)message.content;
        
        [self.needDelRichTextIds addObject:message.msg_id];
        NSString *tempPath = NSTemporaryDirectory();
        NSString *filePath = [tempPath stringByAppendingPathComponent:@"RichTextMessageIds"];
        [NSKeyedArchiver archiveRootObject:self.needDelRichTextIds toFile:filePath];
        
        
        
        NSString *contentStr = (NSString *)richTextMsg.elements;
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:GXRichText originalType:@"text" content:contentStr extra:(NSString *)richTextMsg.repliedMessage auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self messageReceiveSucced:messageF];
        
    }else if ([message.type isEqualToString:@"ImageMessage"]) {//图片消息
        ImageMessage * imgMsg = (ImageMessage *)message.content;
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic originalType:@"image" content:@"[图片]" extra:@"" auditExtra:@"" path:@"" urlPath:imgMsg.imgUrlPath from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(100, 100) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self messageReceiveSucced:messageF];
    }else if ([message.type isEqualToString:@"VideoMessage"]) {//视频消息
        VideoMessage * videoMsg = (VideoMessage *)message.content;
        UIImage *videoArrowImage;
        NSString * localPath;
        localPath = [[ICMediaManager sharedManager] smallImgPath:message.msg_id];
        videoArrowImage = [UIImage imageWithContentsOfFile:localPath];
        if (!videoArrowImage) {
            UIImage *resultImg = [self getVideoPreViewImage:[NSURL URLWithString:videoMsg.videoUrlPath]];
            videoArrowImage = [UIImage addImage2:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"video_play_btn_bg"]] toImage:resultImg];
            if (videoArrowImage) {
                [[ICMediaManager sharedManager] saveImage:videoArrowImage msgId:message.msg_id picType:@"jpg"];
            }
        }
        CGFloat fixelW = videoArrowImage.size.width;
        CGFloat fixelH = videoArrowImage.size.height;
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:videoMsg.videoUrlPath urlPath:videoMsg.videoUrlPath from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(fixelW, fixelH) picType:@"jpg" isSender:NO receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self messageReceiveSucced:messageF];
        
    }else if ([message.type isEqualToString:@"FileMessage"]) {//文件消息
        FileMessage * fileMsg = (FileMessage *)message.content;
        if ([fileMsg.type isEqualToString:TypeVideo]) {
            // 视频
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:fileMsg.fileUrlPath urlPath:fileMsg.fileUrlPath from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(150 * 9.0/16.0, 150) picType:@"jpg" isSender:NO receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
        }else{
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCustomFile originalType:@"file" content:fileMsg.content extra:@"" auditExtra:@"" path:fileMsg.fileUrlPath urlPath:fileMsg.fileUrlPath from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeZero picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
        }
        
    }else if ([message.type isEqualToString:@"VoiceMessage"]) {//语音消息
        VoiceMessage * voiceMsg = (VoiceMessage *)message.content;
        int voiceDuration = (int)[[ICRecordManager shareManager] durationWithVideo:[NSURL fileURLWithPath:voiceMsg.voiceUrlPath]];
        if (voiceDuration < 1000) {
            // 小于一秒的显示1秒
            voiceDuration = 1000;
        }
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVoice originalType:@"voice" content:@"[语音]" extra:@"" auditExtra:@"" path:voiceMsg.voiceUrlPath urlPath:voiceMsg.voiceUrlPath from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:voiceDuration] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeZero picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self messageReceiveSucced:messageF];
    }else if ([message.type isEqualToString:@"RobotRichMessage"]) {//机器人选项消息
        
        NSArray <CombinationMessage *>*combinationMessage = message.content;
        
        NSString *contentStr = (NSString *)combinationMessage;
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeRobotCombination originalType:@"text" content:contentStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self messageReceiveSucced:messageF];
        
    }else if ([message.type isEqualToString:@"RobotRichMessage14"]) {//机器人选项消息14
        
        NSArray <CombinationMessage *>*combinationMessage = message.content;
        
        NSString *contentStr = (NSString *)combinationMessage;
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeRobotCombination originalType:@"text" content:contentStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self messageReceiveSucced:messageF];
    }else if ([message.type isEqualToString:@"RobotBridgeMessage"]) {//接通机器人
        sessionInfomodel.status = [NSNumber numberWithInt:8];
        self.isCloseSession = NO;
        [self chatStatusChanged:TinetChatStatusTypeRobot];
        RobotBridgeMessage * robotBridgeMsg = (RobotBridgeMessage *)message.content;
        if (self.titleName ==nil || self.titleName.length == 0) {
            self.title = robotBridgeMsg.robotNickName;
        }
        //获取机器人头像信息
        [[OnlineRequestManager sharedCustomerManager] getClientInfoWithSender:robotBridgeMsg.robotId
                                                                   senderType:@"4"
                                                                      success:^(OnlineClientInfoModel * _Nonnull model) {
            if (model.avatar == nil || model.avatar.length == 0) {
                //保存头像
                [[ICMediaManager sharedManager] savaHeadImgUrl:@"customer_service_default_avatar" userId:robotBridgeMsg.robotId];
            }else{
                [[ICMediaManager sharedManager] savaHeadImgUrl:model.avatar userId:robotBridgeMsg.robotId];
            }
            if (model.nickName == nil || model.nickName.length == 0) {
                //保存名称
                [[ICMediaManager sharedManager] savaHeadName:@"机器人" userId:robotBridgeMsg.robotId];
            }else{
                [[ICMediaManager sharedManager] savaHeadName:model.nickName userId:robotBridgeMsg.robotId];
            }
            
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            
        }];
    }else if ([message.type isEqualToString:@"ChatOpenMessage"]) {//会话开始
        self.isCloseSession = NO;
        ChatOpenMessage * eventMessage = (ChatOpenMessage *)message.content;
    }else if ([message.type isEqualToString:@"ChatBridgeMessage"]) {//接通客服
        [self chatStatusChanged:TinetChatStatusTypeOnline];
        sessionInfomodel.status = [NSNumber numberWithInt:4];
        self.isCloseSession = NO;
        ChatBridgeMessage * eventMessage = (ChatBridgeMessage *)message.content;
        if (self.titleName ==nil || self.titleName.length == 0) {
            self.title = [[OnlineDataSave shareOnlineDataSave] getCustomTitle];
        }
        //获取客服头像信息
        [[OnlineRequestManager sharedCustomerManager] getClientInfoWithSender:eventMessage.sender
                                                                   senderType:@"1"
                                                                      success:^(OnlineClientInfoModel * _Nonnull model) {
            if (model.avatar == nil || model.avatar.length == 0) {
                //保存头像
                [[ICMediaManager sharedManager] savaHeadImgUrl:@"default_avatar" userId:eventMessage.sender];
            }else{
                [[ICMediaManager sharedManager] savaHeadImgUrl:model.avatar userId:eventMessage.sender];
            }
            if (model.nickName == nil || model.nickName.length == 0) {
                //保存名称
                [[ICMediaManager sharedManager] savaHeadName:@"客服" userId:eventMessage.sender];
            }else{
                [[ICMediaManager sharedManager] savaHeadName:model.nickName userId:eventMessage.sender];
            }
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            
        }];
        
        if (eventMessage.welcomStr.length>0) {
            RichTextMessage *richTextMsg = [[RichTextMessage alloc] initMessageWithContent:eventMessage.welcomStr];
            
            NSString *contentStr = (NSString *)richTextMsg.elements;
            
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:GXRichText originalType:@"text" content:contentStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:eventMessage.sender to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
        }
        if (eventMessage.clientIntroduce.length>0) {
            RichTextMessage *richTextMsg = [[RichTextMessage alloc] initMessageWithContent:eventMessage.clientIntroduce];
            
            NSString *contentStr = (NSString *)richTextMsg.elements;
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:GXRichText originalType:@"text" content:contentStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:eventMessage.sender to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
        }
    }else if ([message.type isEqualToString:@"ChatCloseMessage"]) {//会话结束
        sessionInfomodel.status = [NSNumber numberWithInt:7];
        self.isCloseSession = YES;
        // 增加结束会话的文案
        ChatCloseMessage * closeMessage = (ChatCloseMessage*)message.content;
        if (![kitUtils isBlankString:closeMessage.endMessage]) {
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeSystem originalType:@"text" content:closeMessage.endMessage extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:NO isHeaderInsert:NO];
            [self messageReceiveSucced:messageF];
        }
        
        [self closeViewEvent];
        
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.5f * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            [self scrollToBottom];
        });
    }else if ([message.type isEqualToString:@"ChatQueueMessage"]) {//进入排队
        sessionInfomodel.status = [NSNumber numberWithInt:3];
        ChatQueueMessage * eventMsg = (ChatQueueMessage *)message.content;
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeEventQueue originalType:message.type content:eventMsg.locationstr extra:@"" auditExtra:@"" path:nil urlPath:nil from:[[OnlineDataSave shareOnlineDataSave] getVisitorId] to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self messageReceiveSucced:messageF];
    }else if ([message.type isEqualToString:@"ChatLocationMessage"]) {//排队位置播报
        ChatLocationMessage * eventMsg = (ChatLocationMessage *)message.content;
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeEventQueue originalType:message.type content:eventMsg.locationstr extra:@"" auditExtra:@"" path:nil urlPath:nil from:[[OnlineDataSave shareOnlineDataSave] getVisitorId] to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self messageReceiveSucced:messageF];
    }else if ([message.type isEqualToString:@"WithdrawMessage"]) {//座席撤回消息
        WithdrawMessage * eventMsg = (WithdrawMessage *)message.content;
        [self getRecallMessage:message msgId:message.msg_id];
    }else if ([message.type isEqualToString:@"ChatLeaveQueueMessage"]) {//访客退出排队
        ChatLeaveQueueMessage * robotBridgeMsg = (ChatLeaveQueueMessage *)message.content;
    }else if ([message.type isEqualToString:@"ChatOfflineMessage"]) {//访客离线
        ChatOfflineMessage * robotBridgeMsg = (ChatOfflineMessage *)message.content;
    }else if ([message.type isEqualToString:@"ChatOnlineMessage"]) {//访客上线
        ChatOnlineMessage * robotBridgeMsg = (ChatOnlineMessage *)message.content;
    }else if ([message.type isEqualToString:@"ChatLeaveMessage"]) {//留言消息事件
        sessionInfomodel.status = [NSNumber numberWithInt:5];
        ChatLeaveMessage * chatLeaveMessageMsg = (ChatLeaveMessage *)message.content;
        
        TIMChatLeaveVC*vc = [[TIMChatLeaveVC alloc]init];
        vc.welcomContent = chatLeaveMessageMsg.welcomContent;
        vc.leaveTip = chatLeaveMessageMsg.leaveTip;
        vc.leaveMessageFields = [[NSMutableArray alloc]initWithArray:chatLeaveMessageMsg.leaveMessageFields];
        [self.navigationController pushViewController:vc animated:NO];
        
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(.5f * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            NSMutableArray *marr = [[NSMutableArray alloc] initWithArray:self.navigationController.viewControllers];
            for (UIViewController *vc in marr) {
                if (vc == self) {
                    [marr removeObject:vc];
                    break;          //break一定要加，不加有时候有bug
                }
            }
            self.navigationController.viewControllers = marr;
        });
        
    }else if([message.type isEqualToString:@"ChatLeaveMessage"]){//留言消息
        ChatLeaveReceiveMessage * chatLeaveReceiveMsg = (ChatLeaveReceiveMessage *)message.content;
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText originalType:message.type content:chatLeaveReceiveMsg.textContent extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:YES status:1];
        [self addObject:messageF isSender:YES isHeaderInsert:NO];
        [self messageReceiveSucced:messageF];
    } else if ([message.type isEqualToString:@"MessageTextTag"]) {
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeTextTag originalType:message.type content:(NSString *)message.content extra:@"" auditExtra:@"" path:nil urlPath:nil from:message.senderId to:message.receiverId fileKey:message.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:message.msg_id sendTime:message.timestamp showTime:[self p_needShowTimeStamp:(NSTimeInterval)message.timestamp] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:NO isHeaderInsert:NO];
        [self newMessageSendSucced:messageF];
    } else if ([message.type isEqualToString:@"SensitiveWordEvent"]) {
        
        TOSSensitiveWordEventModel *sensitiveWordModel = (TOSSensitiveWordEventModel *)message.content;
        NSLog(@"sensitiveWordModel ======= %@",[sensitiveWordModel yy_modelToJSONObject]);
        @WeakObj(self)
        [self.dataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if ([obj.model.message.fileKey isEqualToString:sensitiveWordModel.messageUUID]) {
                
                @StrongObj(self)
                obj.model.message.deliveryState = ICMessageDeliveryState_Failure_SensitiveWords;
                obj.model = obj.model;
                [selfWeak.tableView reloadData];
                *stop = YES;
            }
        }];
    }
    
    /// 保存会话在线状态事件
    [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[sessionInfomodel yy_modelToJSONString]];
    
    [self scrollToBottom];
    
    if (self.offlineType != offlineMessageTypeNormal && self.existedWelcome) {
        [self sendOfflineNewMessage];
        self.existedWelcome = NO;
    }
    else if (self.offlineType != offlineMessageTypeNormal && !self.existedWelcome) {
        self.existedWelcome = YES;
        
    }
    
}

- (void)messageRecalledNotification:(NSNotification *)sender {
    
}

#pragma mark - baseCell delegate

- (void)longPress:(UILongPressGestureRecognizer *)longRecognizer
{
    if (longRecognizer.state == UIGestureRecognizerStateBegan) {
        CGPoint location       = [longRecognizer locationInView:self.tableView];
        NSIndexPath *indexPath = [self.tableView indexPathForRowAtPoint:location];
        _longIndexPath         = indexPath;
        id object              = [self.dataSource objectAtIndex:indexPath.row];
        
        if (![object isKindOfClass:[TIMMessageFrame class]]) return;
        ICChatMessageBaseCell *cell = [self.tableView cellForRowAtIndexPath:indexPath];
        if (self.dataSource.count > indexPath.row) {
            [self showMenuViewController:cell.bubbleView andIndexPath:indexPath message:((TIMMessageFrame *)self.dataSource[indexPath.row]).model];//cell.modelFrame.model];
        }
    }
}

- (void)headImageClicked:(TIMMessageModel *)model {
    
}

/// 重发
/// @param baseCell cell
- (void)reSendMessage:(ICChatMessageBaseCell *)baseCell {
    
    if (![[TOSClientKit sharedTOSKit].mqttConnected boolValue]) {
        return;
    }
    
    
    TIMMessageFrame *modelFrame = baseCell.modelFrame;
    TIMICMessage *message = modelFrame.model.message;
    NSString *mediaPath = modelFrame.model.mediaPath;
    
    [self messageSending:modelFrame];
    
    if ([message.type isEqualToString:TypeText]) {
        
        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendTextMessageWithMessageStr:message.content knowledge:message.knowledge?:@"" messageUUID:message.fileKey success:^(TOSMessage * timMessage) {
            baseCell.modelFrame.model.message.messageId = timMessage.msg_id;
            [self updateOldMessageId:timMessage.msg_id];
            [self messageSendSucced:baseCell.modelFrame];
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            [self messageSendFailed:baseCell.modelFrame];
        }];
    } else if ([message.type isEqualToString:TypePic]) {
        
        UIImage *image = [UIImage imageWithContentsOfFile:mediaPath];
        NSData *imageData = UIImageJPEGRepresentation(image, 0.5);
        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendImageMessageWithImageData:imageData success:^(NSString * _Nonnull messageId, NSString * _Nonnull fileKey) {
            modelFrame.model.message.messageId = messageId;
            [self updateOldMessageId:messageId];
            [self messageSendSucced:modelFrame];
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            [self messageSendFailed:modelFrame];
        }];
    } else if ([message.type isEqualToString:TypeVideo]) {
        
        NSData *videoData = [NSData dataWithContentsOfFile:mediaPath];
        [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendVideoMessageWithVideoData:videoData success:^(NSString * _Nonnull messageId) {
            modelFrame.model.message.messageId = messageId;
            [self updateOldMessageId:messageId];
            [self messageSendSucced:modelFrame];
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            [self messageSendFailed:modelFrame];
            long long sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
            NSString *strShow = [NSString stringWithFormat:@"文件过大，请发送小于%lldM文件",sizeLimitM];
            [WHToast showMessage:errorDes?:strShow duration:2.f finishHandler:^{
                
            }];
        }];
    } else if ([message.type isEqualToString:TypeVoice]) {
        
        NSData *voiceData = [NSData dataWithContentsOfFile:mediaPath];
        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendVoiceMessageWithVoiceData:voiceData success:^(NSString * _Nonnull messageId) {
            modelFrame.model.message.messageId = messageId;
            [self updateOldMessageId:messageId];
            [self messageSendSucced:modelFrame];
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            [self messageSendFailed:modelFrame];
        }];
    } else if ([message.type isEqualToString:TypeCustomFile]) {
        
        NSData *fileData = [NSData dataWithContentsOfFile:mediaPath];
        NSString *fileTypeStr = [kitUtils mimeTypeForFileAtPath:mediaPath];
        
        NSString *fileName = [[kitUtils dictionaryWithJsonString:message.content] by_ObjectForKey:@"fileName"];
        
        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendFileMessageWithFileData:fileData fileType:fileTypeStr fileName:fileName success:^(NSString * _Nonnull messageId) {
            modelFrame.model.message.messageId = messageId;
            [self updateOldMessageId:messageId];
            [self messageSendSucced:modelFrame];
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            [self messageSendFailed:modelFrame];
            long long sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
            NSString *strShow = [NSString stringWithFormat:@"文件过大，请发送小于%lldM文件",sizeLimitM];
            [WHToast showMessage:errorDes?:strShow duration:2.f finishHandler:^{
                
            }];
        }];
    }
}

-(void)sendCombinaMessage:(NSString *)text{
    [self sendTextMessagewithText:text knowledge:@""];
}

//文件下载方法
-(void)downFileMessage:(NSString *)fileUrl{
    
    
}

#pragma mark - 更新Mqtt链接状态

- (void)updateConnectStatus:(NSNotification *)notification {
    
    NSDictionary  *dict = [notification object];
    BOOL connectStatus = [dict[@"connectStatus"] boolValue];
    BOOL kickOutStatus = NO;
    if ([dict objectForKey:@"kickOut"]) {
        kickOutStatus = [dict[@"connectStatus"] boolValue];
    }
    if (kickOutStatus) {
        NSLog(@"更新的链接状态 updateConnectStatus kickOutStatus 会话页面被踢下线");
    }
    if (connectStatus && !self.isOpenSession) {
        // 变更状态重新打开会话
        [self openSession];
    }
}

/// 关闭事件
- (void)closeViewEvent {
    
    self.chatBoxVC.view.hidden = YES;
    self.chatCloseView.hidden = NO;
    [self.chatBoxVC.chatBox.textView resignFirstResponder];
    self.chatBoxVC.chatBox.textView.text = @"";
    [[OnlineDataSave shareOnlineDataSave] saveUserInputText:@""];
    [self.chatBoxVC.chatBox switchTextEditing];
    [self.chatBoxVC.chatBox.textView resignFirstResponder];
    
    self.tableView.height = self.chatCloseView.top;
    
}

#pragma mark - 收到非聊天界面消息发送通知
- (void)sendMultiMediaImage:(NSNotification *)notification {
    NSDictionary  *dict = [notification object];
    
    BOOL sendSuccess = [dict[@"sendSuccess"] boolValue];
    NSString *imgPath = dict[@"imagePath"];
    UIImage *image = [UIImage imageWithContentsOfFile:imgPath];
    NSString *receiveId = dict[@"receiveId"];
    TOSMessage * timMessage;
    if (sendSuccess) {
        timMessage = dict[@"timMessage"];
    }
    
    if ([receiveId isEqualToString:[[OnlineDataSave shareOnlineDataSave] getEndpointId]] == NO) {
        TIMKitLog(@"会话目标不匹配");
        return;
    }
    TIMKitLog(@"发送图片 path:%@ src:%@ last:%@",imgPath,image,[[imgPath lastPathComponent] stringByDeletingPathExtension]);
    NSString * sreGetMsgUUID = [[imgPath lastPathComponent] stringByDeletingPathExtension];
    if (image) {
        CGFloat fixelW = image.size.width;// CGImageGetWidth(image.CGImage);
        CGFloat fixelH = image.size.height;// CGImageGetHeight(image.CGImage);
        TIMImageMessage * imageMsg = [[TIMImageMessage alloc] initMessageWithImageURI:imgPath  extra:@""];
        TOSMessage * timMsg = [[TOSMessage alloc] initWithOption:sreGetMsgUUID msg_id:@"" type:@"image" senderId:[[OnlineDataSave shareOnlineDataSave] getVisitorId] receiverId:[[OnlineDataSave shareOnlineDataSave] getEndpointId] content:imageMsg msg_from:[self isGroupSession]?TIMSessionType_GROUP_CHAT:/*TIMSessionType_SINGLE_CHAT*/TIMSessionType_OnLINESERVICE_CHAT status:TIMMessageStatus_Sending timestamp:0];
        
        CGSize imageSize = CGSizeMake(fixelW, fixelH);
        __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic originalType:@"image" content:@"[图片]" extra:@"" auditExtra:@"" path:imgPath urlPath:nil from:[[OnlineDataSave shareOnlineDataSave] getVisitorId] to:[[OnlineDataSave shareOnlineDataSave] getEndpointId] fileKey:timMsg.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:timMsg.msg_id sendTime:[self getNowTimestamp] showTime:[self p_needShowTime:[NSDate date]] picSize:imageSize picType:@"" isSender:YES receivedSenderByYourself:NO status:TIMMessageStatus_Sending];
        [self addObject:messageF isSender:YES isHeaderInsert:NO];
        
        if (sendSuccess) {
            messageF.model.message.messageId = timMessage.msg_id;
            [self updateOldMessageId:timMessage.msg_id];
            [self messageSendSucced:messageF];
        }else{
            [self messageSendFailed:messageF];
        }
    }
}

#pragma mark 类内函数

- (void)sendImageMessageWithImgPath:(NSString *)imgPath originalType:(NSString *)originalType fileKey:(NSString *)fileKey bigImgFileId:(NSString *)bigImgFileId from:(NSString *)from to:(NSString *)to msgId:(NSString *)msgId sendTime:(NSInteger)sendTime picType:(NSString *)picType extra:(NSString *)strExtra
{
    @WeakObj(self)
    TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic originalType:originalType content:@"[图片]" extra:[selfWeak transferExtra:strExtra] auditExtra:@"" path:imgPath urlPath:nil from:from to:to fileKey:fileKey bigImgFileId:bigImgFileId voiceDuration:[NSNumber numberWithInt:0] msgId:msgId sendTime:sendTime showTime:[selfWeak p_needShowTime:[NSDate date]] picSize:CGSizeMake(0, 0) picType:picType isSender:YES receivedSenderByYourself:NO status:1];
    [selfWeak addObject:messageF isSender:YES isHeaderInsert:NO];
    
    [selfWeak messageSendSucced:messageF];
}

- (void)otherSendImageMessageWithImgPath:(NSString *)imageUrl originalType:(NSString *)originalType image:(UIImage *)image fileKey:(NSString *)fileKey bigImgFileId:(NSString *)bigImgFileId from:(NSString *)from to:(NSString*)to msgId:(NSString *)msgId sendTime:(NSInteger)sendTime picType:(NSString *)picType picSize:(CGSize)size extra:(NSString *)strExtra auditExtra:(NSString *)auditExtra{
    // 接收消息
    @WeakObj(self)
    NSString * localImagePath;
    if (image != nil) {
        localImagePath = [[ICMediaManager sharedManager] saveImage:image msgId:msgId picType:picType];
    }
    TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic originalType:originalType content:@"[图片]" extra:strExtra?:@"" auditExtra:auditExtra path:localImagePath urlPath:imageUrl from:from to:to fileKey:fileKey bigImgFileId:bigImgFileId voiceDuration:[NSNumber numberWithInt:0] msgId:msgId sendTime:sendTime showTime:[selfWeak p_needShowTimeStamp:(NSTimeInterval)sendTime] picSize:size picType:picType isSender:NO receivedSenderByYourself:NO status:1];
    [selfWeak addObject:messageF isSender:NO isHeaderInsert:NO];
    
    [selfWeak messageSendSucced:messageF];
    [self scrollToBottom];
}

- (void)otherSendImageMessageInsertWithImgPath:(NSString *)imageUrl originalType:(NSString *)originalType image:(UIImage *)image fileKey:(NSString *)fileKey bigImgFileId:(NSString *)bigImgFileId from:(NSString *)from to:(NSString*)to msgId:msgId sendTime:(NSInteger)sendTime picType:picType picSize:(CGSize)picSize content:(NSString *)content extra:(NSString*)strExtra isPlaceHolder:(BOOL)isPlaceHolder auditExtra:(NSString *)auditExtra type:(NSString *)type isHeaderInsert:(BOOL)isHeaderInsert fullJSONString:(NSString *)jsonStr{
    NSString * localImagePath;
    @WeakObj(self)
    if (isPlaceHolder) {
        // 如果输入为空 则表示占位符
        NSString *placeImagePath = [[ICMediaManager sharedManager] saveImage:image msgId:@"imageplaceholder" picType:picType];
        localImagePath = placeImagePath;
    }else{
        if ([picType isEqualToString:kGIFTimageType]) {
            localImagePath = [[ICMediaManager sharedManager] smallGifPath:msgId];
        }else{
            localImagePath = [[ICMediaManager sharedManager] smallImgPath:msgId];
        }
    }
    
    NSString * showExtra = [self transferExtra:strExtra];
    if ([@"NoLooKPermission" isEqualToString:showExtra]) {
        // 无查看权限
        return;
    }
    TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:type originalType:originalType content:content extra:showExtra  auditExtra:auditExtra path:localImagePath urlPath:imageUrl from:from to:to fileKey:fileKey bigImgFileId:bigImgFileId voiceDuration:[NSNumber numberWithInt:0] msgId:msgId sendTime:sendTime showTime:[selfWeak p_needShowTimeStamp:(NSTimeInterval)sendTime] picSize:picSize picType:picType isSender:NO receivedSenderByYourself:NO status:1 fullJSONString:jsonStr];
    [selfWeak addObject:messageF isSender:NO isHeaderInsert:isHeaderInsert];
    
    [selfWeak messageSendSucced:messageF];
    [self scrollToBottom];
}

- (void)sendTextMessageWithContent:(NSString *)type originalType:(NSString *)originalType from:(NSString *)from to:(NSString *)to fileKey:(NSString *)fileKey msgId:(NSString *)msgId sendTime:(NSInteger)sendTime content:(NSString *)messageStr
{
    [self sendTextMessageWithContent:type originalType:originalType from:from to:to fileKey:fileKey msgId:msgId sendTime:sendTime content:messageStr extra:@""];
}

- (void)sendTextMessageWithContent:(NSString *)type originalType:(NSString *)originalType from:(NSString *)from to:(NSString *)to fileKey:(NSString *)fileKey msgId:(NSString *)msgId sendTime:(NSInteger)sendTime content:(NSString *)messageStr extra:(NSString *)extra
{
    @WeakObj(self)
    TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:type originalType:originalType content:messageStr extra:extra auditExtra:@"" path:nil urlPath:nil from:from to:to fileKey:fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:msgId sendTime:sendTime showTime:[selfWeak p_needShowTimeStamp:(NSTimeInterval)sendTime] picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1];
    [selfWeak addObject:messageF isSender:YES isHeaderInsert:NO];
    
    [selfWeak messageSendSucced:messageF];
}

- (void)otherSendTextMessageWithContent:(NSString*)type originalType:(NSString *)originalType from:(NSString *)from to:(NSString *)to fileKey:(NSString *)fileKey msgId:(NSString *)msgId sendTime:(NSInteger)sendTime content:(NSString *)messageStr extra:(NSString *)extra
{
    @WeakObj(self)
    
    TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:type originalType:originalType content:messageStr extra:extra auditExtra:@"" path:nil urlPath:nil from:from to:to fileKey:fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:msgId sendTime:sendTime showTime:[selfWeak p_needShowTimeStamp:(NSTimeInterval)sendTime] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
    [selfWeak addObject:messageF isSender:NO isHeaderInsert:NO];
    
    [selfWeak messageSendSucced:messageF];
    [self scrollToBottom];
}

- (void)otherSendTextMessageWithContent:(NSString*)type originalType:(NSString *)originalType from:(NSString *)from to:(NSString *)to fileKey:(NSString *)fileKey msgId:(NSString *)msgId sendTime:(NSInteger)sendTime content:(NSString *)messageStr
{
    [self otherSendTextMessageWithContent:type originalType:originalType from:from to:to fileKey:fileKey msgId:msgId sendTime:sendTime content:messageStr];
}

- (void)sendTextMessageInsertWithContent:(NSString *)to fileKey:(NSString *)fileKey content:(NSString *)messageStr
{
    
}

- (void)otherSendTextMessageInsertWithContent:(NSString *)type originalType:(NSString *)originalType from:(NSString *)from to:(NSString *)to fileKey:(NSString *)fileKey msgId:msgId sendTime:(NSTimeInterval)sendTime content:(NSString *)messageStr isHeaderInsert:(BOOL)isHeaderInsert extra:(NSString *)extra
{
    @WeakObj(self)
    TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:type originalType:originalType content:messageStr extra:extra auditExtra:@"" path:nil urlPath:nil from:from to:to fileKey:fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:msgId sendTime:sendTime showTime:[selfWeak p_needShowTimeStamp:sendTime] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
    [selfWeak addObject:messageF isSender:NO isHeaderInsert:isHeaderInsert];
    
    [selfWeak messageSendSucced:messageF];
    [self scrollToBottom];
}

- (void)otherSendTextMessageInsertWithContent:(NSString *)type originalType:(NSString *)originalType from:(NSString *)from to:(NSString *)to fileKey:(NSString *)fileKey msgId:msgId sendTime:(NSTimeInterval)sendTime content:(NSString *)messageStr isHeaderInsert:(BOOL)isHeaderInsert {
    [self otherSendTextMessageInsertWithContent:type originalType:originalType from:from to:to fileKey:fileKey msgId:msgId sendTime:sendTime content:messageStr isHeaderInsert:isHeaderInsert extra:@""];
    
}

// 增加数据源并刷新
- (void)addObject:(TIMMessageFrame *)messageF
         isSender:(BOOL)isSender
{
    @WeakObj(self)
    if (messageF == nil) {
        TIMKitLog(@"(void)addObject:(TIMMessageFrame *)messageF messageF == nil");
        return;
    }
    messageF.model.message.deliveryState = ICMessageDeliveryState_Delivered;
    [selfWeak.dataSource insertObject:messageF atIndex:0];
}

// 增加数据源并刷新
- (void)addObject:(TIMMessageFrame *)messageF
         isSender:(BOOL)isSender isHeaderInsert:(BOOL)isHeaderInsert
{
    @WeakObj(self)
    if (messageF == nil) {
        TIMKitLog(@"(void)addObject:(TIMMessageFrame *)messageF messageF == nil");
        return;
    }
    messageF.model.message.deliveryState = ICMessageDeliveryState_Delivered;
    if (isHeaderInsert) {
        [selfWeak.dataSource insertObject:messageF atIndex:0];
    }else{
        [selfWeak.dataSource addObject:messageF];
    }
    
    if (self->_firstHasGetHistoryListFinish == 0 && self.pageIndex != 1) {
        [selfWeak.tableView reloadData];
        if (!isHeaderInsert) {
            if (isSender || _isKeyBoardAppear) {
                [selfWeak scrollToBottom];
            }
        }
    }
}

// 修改数据源并刷新
- (void)modifyObject:(TIMMessageFrame *)messageF
            isSender:(BOOL)isSender isHeaderInsert:(BOOL)isHeaderInsert index:(NSInteger)index
{
    if (messageF == nil) {
        TIMKitLog(@"(void)addObject:(TIMMessageFrame *)messageF messageF == nil");
        return;
    }
    
    if (self.dataSource &&
        [self.dataSource objectAtIndex:index]) {
        self.dataSource[index] = messageF;
    }
    
    [self.tableView reloadData];
    if (!isHeaderInsert) {
        if (isSender || _isKeyBoardAppear) {
            [self scrollToBottom];
        }
    }
}

//接收消息成功
- (void)messageReceiveSucced:(TIMMessageFrame *)messageF
{
    [self scrollToBottom];
}


- (void)messageSendSucced:(TIMMessageFrame *)messageF
{
    @WeakObj(self)
    if (self->_firstHasGetHistoryListFinish == 0) {
        messageF.model.message.deliveryState = ICMessageDeliveryState_Delivered;
    }
    [selfWeak.tableView reloadData];
}

- (void)newMessageSendSucced:(TIMMessageFrame *)messageF {
    @WeakObj(self)
    if (self->_firstHasGetHistoryListFinish == 0) {
        messageF.model.message.deliveryState = ICMessageDeliveryState_Delivered;
    }
}

- (void)messageSending:(TIMMessageFrame *)messageF
{
    @WeakObj(self)
    messageF.model.message.deliveryState = ICMessageDeliveryState_Delivering;
    [selfWeak.tableView reloadData];
}

- (void)messageSendFailed:(TIMMessageFrame *)messageF
{
    @WeakObj(self)
    messageF.model.message.deliveryState = ICMessageDeliveryState_Failure;
    [selfWeak.tableView reloadData];
}

- (void)newMessageSendFailed:(TIMMessageFrame *)messageF
{
    @WeakObj(self)
    messageF.model.message.deliveryState = ICMessageDeliveryState_Failure;
}

- (void)scrollToBottom {
    
    [NSObject cancelPreviousPerformRequestsWithTarget:self selector:@selector(innerScrollToBottom) object:nil];
    [self performSelector:@selector(innerScrollToBottom) withObject:nil afterDelay:.5f];
}

- (void)innerScrollToBottom {
    
    [self.tableView reloadData];
    if (self.dataSource.count > 0 &&
        [self.tableView numberOfRowsInSection:0] >= self.dataSource.count) {
        [self.tableView scrollToRowAtIndexPath:[NSIndexPath indexPathForRow:self.dataSource.count-1 inSection:0] atScrollPosition:UITableViewScrollPositionBottom animated:YES];
    }
}

- (void)showMenuViewController:(UIView *)showInView andIndexPath:(NSIndexPath *)indexPath message:(TIMMessageModel *)messageModel
{
    [self becomeFirstResponder];
    __block BOOL copy = NO;
    if ([messageModel.message.type isEqualToString:GXRichText] ||
        [messageModel.message.type isEqualToString:TypeRobotCombination]) {
        copy = YES;
    } else {
        copy = NO;
    }
    
    if ([messageModel.message.type isEqualToString:TypeText] ||
        copy) {
        
        if (_copyMenuItem == nil) {
            _copyMenuItem = [[UIMenuItem alloc] initWithTitle:@"复制" action:@selector(copyMessage:)];
        }
        
        [[UIMenuController sharedMenuController] setMenuItems:@[_copyMenuItem]];
        [[UIMenuController sharedMenuController] setTargetRect:showInView.frame inView:showInView.superview];
        [[UIMenuController sharedMenuController] setMenuVisible:YES animated:YES];
    }
}

- (void)recallMessage:(UIMenuItem *)sender {
    
}

//坐席端撤回消息处理
- (void)getRecallMessage:(TOSMessage *)object msgId:(NSString*)messageId{
    
    __block BOOL showTime = NO;
    __block NSTimeInterval sendTime;
    __block NSInteger indexNum = 0;
    [self.dataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
        if ([obj.model.message.messageId isEqualToString:messageId]) {
            indexNum = idx;
            if (obj.model.message.showTime) {
                showTime = obj.model.message.showTime;
                sendTime = obj.model.message.msgDate.timeIntervalSince1970 * 1000;
                *stop = YES;
            } else {
                sendTime = obj.model.message.msgDate.timeIntervalSince1970 * 1000;
            }
        }
    }];
    
    // Kit
    TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeRevoke originalType:object.type content:object.content extra:@"" auditExtra:@"" path:nil urlPath:nil from:[[OnlineDataSave shareOnlineDataSave] getVisitorId] to:object.receiverId fileKey:object.messageUUID bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:messageId sendTime:sendTime showTime:showTime picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1];
    messageF.model.message.status = ICMessageStatus_back;   //将消息类型改为撤回
    
    
    [self modifyObject:messageF isSender:YES isHeaderInsert:NO index:indexNum];
    [self messageSendSucced:messageF];
}

- (void)copyMessage:(UIMenuItem *)copyMenuItem
{
    UIPasteboard *pasteboard  = [UIPasteboard generalPasteboard];
    TIMMessageFrame * messageF = [self.dataSource objectAtIndex:_longIndexPath.row];
    
    if ([messageF.model.message.type isEqualToString:TypeText]) {
        
        pasteboard.string         = messageF.model.message.content;
    } else if ([messageF.model.message.type isEqualToString:GXRichText]) {
        
        __block NSString *content = @"";
        NSArray <RichTextMessage *>*elements = (NSArray *)messageF.model.message.content;
        [elements enumerateObjectsUsingBlock:^(RichTextMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if ([content isEqualToString:@""]) {
                
                if ([obj.type isEqualToString:@"img"]) {
                    content = @"[图片]";
                } else if ([obj.type isEqualToString:@"video"]) {
                    content = @"[视频]";
                } else { //text、a、tr
                    content = [NSString stringWithFormat:@"%@",obj.content];
                }
            } else {
                
                NSString *str;
                if ([obj.type isEqualToString:@"img"]) {
                    str = @"[图片]";
                } else if ([obj.type isEqualToString:@"video"]) {
                    str = @"[视频]";
                } else { //text、a、tr
                    str = obj.content;
                }
                
                content = [NSString stringWithFormat:@"%@\n%@",content,str];
            }
            
        }];
        pasteboard.string         = content;
    } else if ([messageF.model.message.type isEqualToString:TypeRobotCombination]) {
        
        __block NSString *content = @"";
        NSArray <CombinationMessage *>*message = (NSArray *)messageF.model.message.content;
        [message enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if ([content isEqualToString:@""]) {
                
                if ([obj.type isEqualToString:@"6"] ||
                    [obj.type isEqualToString:@"15"] ||
                    [obj.type isEqualToString:@"16"] ||
                    [obj.type isEqualToString:@"17"] ||
                    [obj.type isEqualToString:@"18"] ||
                    [obj.type isEqualToString:@"19"] ||
                    [obj.type isEqualToString:@"20"] ||
                    [obj.type isEqualToString:@"31"]) {
                    content = @"[猜你想问]";
                } else if ([obj.type isEqualToString:@"2"]) {
                    content = @"[图片]";
                } else if ([obj.type isEqualToString:@"3"]) {
                    content = @"[文件]";
                } else if ([obj.type isEqualToString:@"4"]) {
                    content = @"[视频]";
                } else if ([obj.type isEqualToString:@"7"]) {
                    content = @"[语音]";
                } else { //5
                    
                    NSArray <RichTextMessage *>*elements = obj.richTextMessage.elements;
                    [elements enumerateObjectsUsingBlock:^(RichTextMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                        if ([content isEqualToString:@""]) {
                            
                            if ([obj.type isEqualToString:@"img"]) {
                                content = @"[图片]";
                            } else if ([obj.type isEqualToString:@"video"]) {
                                content = @"[视频]";
                            } else { //text、a、tr
                                content = [NSString stringWithFormat:@"%@",obj.content];
                            }
                        } else {
                            
                            NSString *str;
                            if ([obj.type isEqualToString:@"img"]) {
                                str = @"[图片]";
                            } else if ([obj.type isEqualToString:@"video"]) {
                                str = @"[视频]";
                            } else { //text、a、tr
                                str = obj.content;
                            }
                            
                            content = [NSString stringWithFormat:@"%@\n%@",content,str];
                        }
                        
                    }];
                }
            } else {
                
                __block NSString *str;
                
                if ([obj.type isEqualToString:@"6"] ||
                    [obj.type isEqualToString:@"15"] ||
                    [obj.type isEqualToString:@"16"] ||
                    [obj.type isEqualToString:@"17"] ||
                    [obj.type isEqualToString:@"18"] ||
                    [obj.type isEqualToString:@"19"] ||
                    [obj.type isEqualToString:@"20"] ||
                    [obj.type isEqualToString:@"31"]) {
                    str = @"[猜你想问]";
                } else if ([obj.type isEqualToString:@"2"]) {
                    str = @"[图片]";
                } else if ([obj.type isEqualToString:@"3"]) {
                    str = @"[文件]";
                } else if ([obj.type isEqualToString:@"4"]) {
                    str = @"[视频]";
                } else if ([obj.type isEqualToString:@"7"]) {
                    str = @"[语音]";
                } else { //5
                    
                    NSArray <RichTextMessage *>*elements = obj.richTextMessage.elements;
                    [elements enumerateObjectsUsingBlock:^(RichTextMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                        if ([str isEqualToString:@""]) {
                            
                            if ([obj.type isEqualToString:@"img"]) {
                                str = @"[图片]";
                            } else if ([obj.type isEqualToString:@"video"]) {
                                str = @"[视频]";
                            } else { //text、a、tr
                                str = [NSString stringWithFormat:@"%@",obj.content];
                            }
                        } else {
                            
                            NSString *subStr;
                            if ([obj.type isEqualToString:@"img"]) {
                                subStr = @"[图片]";
                            } else if ([obj.type isEqualToString:@"video"]) {
                                subStr = @"[视频]";
                            } else { //text、a、tr
                                subStr = obj.content;
                            }
                            
                            str = [NSString stringWithFormat:@"%@\n%@",str,subStr];
                        }
                        
                    }];
                }
                
                content = [NSString stringWithFormat:@"%@\n%@",content,str];
            }
        }];
        pasteboard.string         = content;
    }
}

- (void)statusChanged:(TIMMessageFrame *)messageF
{
    [self.dataSource removeObject:messageF];
    [self.tableView beginUpdates];
    [self.tableView deleteRowsAtIndexPaths:@[_longIndexPath] withRowAnimation:UITableViewRowAnimationFade];
    [self.tableView endUpdates];
}

- (void)forwardMessage:(UIMenuItem *)forwardItem
{
    TIMKitLog(@"需要用到的数据库，等添加了数据库再做转发...");
}

#pragma mark UIMenuController

- (BOOL)canBecomeFirstResponder
{
    return YES;
}

- (void)tinet_textMessageClickAction:(TinetClickTextMessageEventType)eventType userInfo:(NSDictionary *)userInfo {
    // 超链接点击事例
    NSLog(@"eventType ====== %lu, userInfo ======== %@",(unsigned long)eventType,userInfo);
    
    if (eventType == TinetClickEventTypeUrl) {
        if ([userInfo by_ObjectForKey:@"content"] &&
            [userInfo[@"content"] isKindOfClass:[NSString class]]) {
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:[userInfo[@"content"] stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLQueryAllowedCharacterSet]]]];
        }
    }
}

#pragma mark - public method

/// 发送商品卡片消息
- (void)sendCard:(NSDictionary *)userInfo {
    
    TinetChatStatusType statusType = [TOSClientKit.sharedTOSKit getCurrentOnlineStatus];
    /// 不在线或结束会话
    if (statusType == TinetChatStatusTypeOutline) {
        NSLog(@"当前会话以结束，无法发送商品卡片数据");
        return;
    }
    
    NSArray * allkeys = [userInfo allKeys];
    
    /// 判断数据数量是否正确
    if (allkeys.count != 13) {
        NSLog(@"卡片信息不正确，已拦截");
        return;
    }
    
    NSArray * userAllKey = @[@"subTitle", @"description", @"price", @"time", @"img", @"status", @"extraInfo", @"title", @"url", @"subUrl", @"buttonText", @"extraData", @"senderType"];
    /// 数组进行排序
    NSArray *sortedKeys1 = [allkeys sortedArrayUsingSelector:@selector(compare:)];
    NSArray *sortedKeys2 = [userAllKey sortedArrayUsingSelector:@selector(compare:)];
    /// 比较两个数组是否一致
    BOOL isNullKey = [sortedKeys1 isEqualToArray:sortedKeys2];
    if (!isNullKey) {
        NSLog(@"传入的商品卡片信息有误，请检查传入的商品卡片信息");
        NSLog(@"接入方传入的key : %@", sortedKeys1);
        NSLog(@"应该传入的key : %@", sortedKeys2);
        return;
    }
    
    NSLog(@"userinfo：%@", userInfo);
    
    [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendCommodityCardMessageWithMessageStr:userInfo success:^(TOSMessage * _Nonnull timMessage) {
        
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        
    }];
    
    [self.tableView reloadData];
}

// 路由响应
- (void)routerEventWithName:(NSString *)eventName
                   userInfo:(NSDictionary *)userInfo
{
    TIMMessageFrame *modelFrame = [userInfo objectForKey:MessageKey];
    if ([eventName isEqualToString:GXRouterEventTextUrlTapEventName]) {
    } else if ([eventName isEqualToString:GXHotIssueSendMessageEventName]) {
        NSString *text = [userInfo objectForKey:RouterEventGetSendTextMessage];
        NSString *knowledge = [userInfo objectForKey:RouterEventGetSendTextMessageKnowledge];
        [self sendTextMessagewithText:text knowledge:knowledge?:@""];
    } else if ([eventName isEqualToString:GXRobotCombinationHotIssueCellRefreshEventName]) {
        
        __block NSInteger index = 0;
        if ([kitUtils isBlankString:modelFrame.model.message.messageId]) {
            
            [self.dataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
                if ([obj isEqual:modelFrame]) {
                    obj = modelFrame;
                    index = idx;
                    *stop = YES;
                }
            }];
        } else {
            
            [self.dataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
                if ([obj.model.message.messageId isEqualToString:modelFrame.model.message.messageId]) {
                    obj = modelFrame;
                    index = idx;
                    *stop = YES;
                }
            }];
        }
        
        NSIndexPath *indexPath=[NSIndexPath indexPathForRow:index inSection:0];
        [self.tableView reloadRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationNone];
    } else if ([eventName isEqualToString:GXRouterEventVideoTapEventName]){//点击视频
        NSString *videoPath      = modelFrame.model.mediaPath;
        NSString *videoId = modelFrame.model.message.messageId;
        if (userInfo &&
            [[userInfo allKeys] containsObject:@"urlPath"]) {
            videoPath = [userInfo objectForKey:@"urlPath"];
            NSArray *array = [videoPath componentsSeparatedByString:@"/"];
            __block NSString *videoIds = @"";
            [array enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                if (idx == array.count - 1) {
                    videoIds = obj;
                }
            }];
            videoId = [videoIds componentsSeparatedByString:@"."].firstObject;
        }
        _videoBtnRect            = [[userInfo objectForKey:@"BtnView"] CGRectValue];
        [self showLargeVideoWithPath:videoPath?:@"" messageId:videoId fromRect:_videoBtnRect];
    } else if ([eventName isEqualToString:GXRouterEventCombinationFileTapEventName]) {
        
        NSString *urlPath = [userInfo objectForKey:@"urlPath"];
        STBaseWebViewController *vc = [[STBaseWebViewController alloc]init];
        vc.urlString = urlPath;
        [self.navigationController pushViewController:vc animated:YES];
    } else if ([eventName isEqualToString:GXRouterEventImageTapEventName]) {//点击图片
        _smallRect             = [[userInfo objectForKey:@"smallRect"] CGRectValue];
        _bigRect               =  [[userInfo objectForKey:@"bigRect"] CGRectValue];
        
        NSString *imgPath      = modelFrame.model.mediaPath;
        if (userInfo &&
            [[userInfo allKeys] containsObject:@"urlPath"]) {
            imgPath = [userInfo objectForKey:@"urlPath"];
        }
        
        NSString *orgImgPath = @"";
        if ([imgPath.pathExtension isEqualToString:kGIFTimageType]) {
            orgImgPath = [[ICMediaManager sharedManager] originGifImgPath:modelFrame];
        }else{
            orgImgPath = [[ICMediaManager sharedManager] originImgPath:modelFrame];
        }
        
        if ([ICFileTool fileExistsAtPath:orgImgPath]) {
            modelFrame.model.mediaPath = orgImgPath;
            imgPath                    = orgImgPath;
            
            ICPhotoBrowserController *imageVC = [[ICPhotoBrowserController alloc] initWithImage:nil msgId:modelFrame.model.message.messageId originImageUrl:imgPath];
            imageVC.modalPresentationStyle    = UIModalPresentationCustom;
            [self presentViewController:imageVC animated:YES completion:nil];
        }else{
            ICPhotoBrowserController *imageVC = [[ICPhotoBrowserController alloc] initWithImage:nil msgId:modelFrame.model.message.messageId originImageUrl:imgPath];
            imageVC.modalPresentationStyle    = UIModalPresentationCustom;
            [self presentViewController:imageVC animated:YES completion:nil];
            
        }
    } else if ([eventName isEqualToString:GXRouterEventImageApprovedTapEventName]) {
        // 通过
        TIMKitLog(@"点击通过审核");
        
    } else if ([eventName isEqualToString:GXRouterEventImageRejectedTapEventName]) {
        // 拒绝
        TIMKitLog(@"点击审核不合格");
    } else if ([eventName isEqualToString:GXRouterEventVoiceTapEventName]) {
        
        UIImageView *imageView = (UIImageView *)userInfo[VoiceIcon];
        UIView *redView        = (UIView *)userInfo[RedView];
        [self chatVoiceTaped:modelFrame voiceIcon:imageView redView:redView];
    } else if ([eventName isEqualToString:GXRouterEventCombinationVoiceTapEventName]) {
        UIImageView *imageView = (UIImageView *)userInfo[VoiceIcon];
        
        ICRecordManager *recordManager = [ICRecordManager shareManager];
        recordManager.playDelegate = self;
        // 文件路径
        NSString *voicePath = [NSString stringWithFormat:@"%@",[userInfo objectForKey:MessageKey]];
        
        NSData *data = [NSData dataWithContentsOfURL:[NSURL URLWithString:voicePath]];
        
        
        if (self.voicePath) {
            if ([self.voicePath isEqualToString:voicePath]) { // the same recoder
                self.voicePath = nil;
                [[ICRecordManager shareManager] stopPlayRecorder:voicePath];
                [imageView stopAnimating];
                self.currentVoiceIcon = nil;
                return;
            } else {
                [self.currentVoiceIcon stopAnimating];
                self.currentVoiceIcon = nil;
            }
        }
        [[ICRecordManager shareManager] startPlayRecorderWithData:data];
        [imageView startAnimating];
        self.voicePath = voicePath;
        self.currentVoiceIcon = imageView;
        
    } else if ([eventName isEqualToString:TinetRouterClickEventUrl]) {
        if ([self respondsToSelector:@selector(tinet_textMessageClickAction:userInfo:)]) {
            [self tinet_textMessageClickAction:(TinetClickEventTypeUrl) userInfo:userInfo];
        }
    } else if ([eventName isEqualToString:TinetRouterClickEventMiniProgramCard]) {
        if ([self respondsToSelector:@selector(tinet_textMessageClickAction:userInfo:)]) {
            [self tinet_textMessageClickAction:(TinetClickMiniProgramCard) userInfo:userInfo];
        }
    } else if ([eventName isEqualToString:TinetRouterClickEventLogisticsCard]) {
        if ([self respondsToSelector:@selector(tinet_textMessageClickAction:userInfo:)]) {
            [self tinet_textMessageClickAction:(TinetClickLogisticsCard) userInfo:userInfo];
        }
    } else if ([eventName isEqualToString:TinetRouterClickEventKnowledge]) {
        
        NSString *message = @"";
        if ([userInfo isKindOfClass:[NSDictionary class]] &&
            [[userInfo allKeys] containsObject:@"content"]) {
            message = userInfo[@"content"];
        }
        NSString *knowledge = [userInfo objectForKey:RouterEventGetSendTextMessageKnowledge]?:@"";
        [self sendTextMessagewithText:message knowledge:knowledge];
    } else if ([eventName isEqualToString:TinetRouterClickEventOrderNumber]) {
        if ([self respondsToSelector:@selector(tinet_textMessageClickAction:userInfo:)]) {
            [self tinet_textMessageClickAction:(TinetClickEventTypeOrderNumber) userInfo:userInfo];
        }
    } else if ([eventName isEqualToString:TinetRouterClickEventPhone]) {
        if ([self respondsToSelector:@selector(tinet_textMessageClickAction:userInfo:)]) {
            [self tinet_textMessageClickAction:(TinetClickEventTypePhone) userInfo:userInfo];
        }
    } else if ([eventName isEqualToString:TinetRouterSenderCommodityCardEventUrl]) {
        
        if (!self.chatCloseView.hidden) {   //在会话结束状态下，不再发送消息
            return;
        }
        [self.dataSource enumerateObjectsUsingBlock:^(TIMMessageFrame *obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if ([obj.model.message.type isEqualToString:TypeCommodityCard]) {
                [self.dataSource removeObjectAtIndex:idx];
                *stop = YES;
            }
        }];
        
        [self.commodityCardOption yy_modelToJSONObject];
        
        NSDictionary *content = @{
            @"subTitle": self.commodityCardOption.subTitle?:@"",
            @"description": self.commodityCardOption.descriptions?:@"",
            @"price": self.commodityCardOption.price?:@"",
            @"time": self.commodityCardOption.time?:@"",
            @"img": self.commodityCardOption.img?:@"",
            @"status": self.commodityCardOption.status?:@"",
            @"extraInfo": self.commodityCardOption.extraInfo?:@"",
            @"title":self.commodityCardOption.title?:@"",
            @"url": self.commodityCardOption.url?:@"",
            @"subUrl":self.commodityCardOption.subUrl?:@"",
            @"buttonText":self.commodityCardOption.buttonText?:@"",
            @"extraData":self.commodityCardOption.extraData?:@"",
            @"senderType": @"2"
        };
        
        [[OnlineMessageSendManager sharedOnlineMessageSendManager] sendCommodityCardMessageWithMessageStr:content success:^(TOSMessage * _Nonnull timMessage) {
            
        } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
            
        }];
        
        [self.tableView reloadData];
        
    } else if ([eventName isEqualToString:TinetRouterClickCommodityCardEvent]) {
        if ([self respondsToSelector:@selector(tinet_textMessageClickAction:userInfo:)]) {
            [self tinet_textMessageClickAction:(TinetClickCommodityCard) userInfo:userInfo];
        }
    }
}

/**
 返回数据源数量
 
 @param imageBrowser 图片浏览器
 @return 数量
 */
- (NSInteger)yb_numberOfCellsInImageBrowser:(YBImageBrowser *)imageBrowser {
    return self.imageDataSource.count;
}

/**
 返回当前下标对应的数据
 
 @param imageBrowser 图片浏览器
 @param index 当前下标
 @return 数据
 */
- (id<YBIBDataProtocol>)yb_imageBrowser:(YBImageBrowser *)imageBrowser dataForCellAtIndex:(NSInteger)index {
    return self.imageDataSource[index];
}

- (void)yb_imageBrowser:(YBImageBrowser *)imageBrowser
            pageChanged:(NSInteger)page
                   data:(id<YBIBDataProtocol>)data {
    if (page <= 10 &&
        !self.isImageDataRequest &&
        !self.isLastImageData) {
        self.isImageDataRequest = YES;
    }
}

// tap video
- (void)showLargeVideoWithPath:(NSString *)videoPath messageId:(NSString*)msgId fromRect:(CGRect)fromRect
{
    @WeakObj(self)
    ICVideoBrowserController *videoVC = [[ICVideoBrowserController alloc] initWithPath:fromRect messageId:msgId videoPath:videoPath];
    videoVC.transitioningDelegate     = selfWeak;
    videoVC.modalPresentationStyle    = UIModalPresentationCustom;
    [self presentViewController:videoVC animated:YES completion:nil];
}

// play voice
- (void)chatVoiceTaped:(TIMMessageFrame *)messageFrame
             voiceIcon:(UIImageView *)voiceIcon
               redView:(UIView *)redView
{
    ICRecordManager *recordManager = [ICRecordManager shareManager];
    recordManager.playDelegate = self;
    // 文件路径
    NSString *voicePath = [self mediaPath:messageFrame.model.mediaPath];
    NSString *amrPath   = [[voicePath stringByDeletingPathExtension] stringByAppendingPathExtension:@"amr"];
    [VoiceConverter ConvertAmrToWav:amrPath wavSavePath:voicePath];
    if (messageFrame.model.message.status == ICMessageStatus_unRead){
        messageFrame.model.message.status = ICMessageStatus_read;
        [kitUtils saveNSUserDefaults:@{
            @"msgId":messageFrame.model.message.messageId,
            @"from":messageFrame.model.message.from,
            @"to":messageFrame.model.message.to,
            @"read":@"true"
        }];
        redView.hidden = YES;
    }
    if (self.voicePath) {
        if ([self.voicePath isEqualToString:voicePath]) { // the same recoder
            self.voicePath = nil;
            [[ICRecordManager shareManager] stopPlayRecorder:voicePath];
            [voiceIcon stopAnimating];
            self.currentVoiceIcon = nil;
            return;
        } else {
            [self.currentVoiceIcon stopAnimating];
            self.currentVoiceIcon = nil;
        }
    }
    [[ICRecordManager shareManager] startPlayRecorder:voicePath];
    [voiceIcon startAnimating];
    self.voicePath = voicePath;
    self.currentVoiceIcon = voiceIcon;
}

// 文件路径
- (NSString *)mediaPath:(NSString *)originPath
{
    // 这里文件路径重新给，根据文件名字来拼接
    NSString *name = [[originPath lastPathComponent] stringByDeletingPathExtension];
    return [[ICRecordManager shareManager] receiveVoicePathWithFileKey:name];
}

// 移除录视频时的占位图片
- (void)videoPreviewLayerWillAppear:(NSTimer *)timer
{
    UIView *placeholderView = (UIView *)[timer userInfo];
    [placeholderView removeFromSuperview];
}

#pragma mark - ICRecordManagerDelegate

- (void)voiceDidPlayFinished
{
    self.voicePath = nil;
    ICRecordManager *manager = [ICRecordManager shareManager];
    manager.playDelegate = nil;
    [self.currentVoiceIcon stopAnimating];
    self.currentVoiceIcon = nil;
}

#pragma mark Inner func
-(BOOL)isGroupSession{
    NSString * preFixTwoByte = [[[OnlineDataSave shareOnlineDataSave] getEndpointId] substringToIndex:2];
    if ([preFixTwoByte isEqualToString:@"g-"]) {
        return YES;
    }else{
        return NO;
    }
}

-(BOOL)isCurrentViewControllerShow:(UIViewController *)vc{
    if (vc && vc.isViewLoaded && vc.view.window) {
        return YES;
    } else {
        return NO;
    }
}

// 当oldMessageId 为空时 即没有拉过历史记录消息的话 主动发送的消息成功后要更新oldMessageId
-(void)updateOldMessageId:(NSString *)updateOldMessageId{
    if ([kitUtils isBlankString:self.oldMessageId]) {
        self.oldMessageId = updateOldMessageId;
    }
}

// 懒加载
- (NSOperationQueue *)downloadQueue
{
    if (!_downloadQueue) {
        self.downloadQueue = [[NSOperationQueue alloc] init];
        self.downloadQueue.maxConcurrentOperationCount = 1;
    }
    return _downloadQueue;
}

- (NSMutableDictionary *)downloadOperations
{
    if (nil == _downloadOperations) {
        
        self.downloadOperations = [NSMutableDictionary dictionary];
    }
    return _downloadOperations;
}

- (NSMutableDictionary *)downloadFileDatas
{
    if (nil == _downloadFileDatas) {
        self.downloadFileDatas = [NSMutableDictionary dictionary];
    }
    return _downloadFileDatas;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    //内存警告时
    // 移除所有的下载操作缓存
    [self.downloadQueue cancelAllOperations];
    [self.downloadOperations removeAllObjects];
    // 移除所有的数据缓存
    [self.downloadFileDatas removeAllObjects];
}

-(NSInteger)getNowTimestamp{
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateStyle:NSDateFormatterMediumStyle];
    [formatter setTimeStyle:NSDateFormatterShortStyle];
    [formatter setDateFormat:@"YYYY-MM-dd HH:mm:ss"]; // ----------设置你想要的格式,hh与HH的区别:分别表示12小时制,24小时制
    //设置时区,这个对于时间的处理有时很重要
    NSTimeZone *timeZone = [NSTimeZone timeZoneWithName:@"Asia/Shanghai"];
    [formatter setTimeZone:timeZone];
    NSDate *datenow = [NSDate date]; //现在时间,你可以输出来看下是什么格式
    return (NSInteger)[datenow timeIntervalSince1970] * 1000;
}

-(NSString *)transferExtra:(NSString *)strExtra{
    NSString * extraStrShow = @"";
    if (strExtra && strExtra.length > 0) {
        BOOL bAudit = NO;
        BOOL bLook = NO;
        NSDictionary * recvExtraDic = [kitUtils dictionaryWithJsonString:strExtra];
        if ([recvExtraDic objectForKey:@"userLookPermission"]) {
            if ((self.curGroupType.intValue & 1) == 1) {
                bLook = YES;
                
            }
        }
        if ([recvExtraDic objectForKey:@"userAuditPermission"]) {
            if ((self.curGroupType.intValue & 2) == 2) {
                
                bAudit = YES;
            }
        }
        NSNumber * auditStatusNumber;
        
        if ([recvExtraDic objectForKey:@"auditStatus"]) {
            auditStatusNumber = recvExtraDic[@"auditStatus"];
        }
        
        NSDictionary * extraShowDic = @{
            @"bUserLookPermission":bLook?@"true":@"false",
            @"bUserAuditPermission":bAudit?@"true":@"false",
            @"auditStatus":auditStatusNumber?:@""
        };
        extraStrShow = [kitUtils DataTOjsonString:extraShowDic];
    }
    return extraStrShow;
}

- (void)onlineSendFilemessage:(NSData *)fileData fileName:(NSString *)fileName fileURL:(NSURL *)newURL{
    double size = [fileData length]/1000.f/1024.f;
    int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
    if (sizeLimitM < size) {
        NSString * strShow = [NSString stringWithFormat:@"请发送小于%dM文件",sizeLimitM];
        [WHToast showMessage:strShow duration:2 finishHandler:^{
            
        }];
        return;
    }
    NSString * fileTypeStr = [kitUtils mimeTypeForFileAtPath:newURL.absoluteString];
    NSDictionary* fileMessageDic = @{
        @"fileTypeDesc": @"文件",
        @"fileType": fileTypeStr,
        @"fileName": fileName
    };
    // Kit
    __block TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCustomFile
                                                               originalType:@"file"
                                                                    content:[kitUtils DataTOjsonString:fileMessageDic]
                                                                      extra:@""
                                                                 auditExtra:@""
                                                                       path:newURL.absoluteString
                                                                    urlPath:nil
                                                                       from:[[OnlineDataSave shareOnlineDataSave] getVisitorId]
                                                                         to:[[OnlineDataSave shareOnlineDataSave] getEndpointId]
                                                                    fileKey:[[NSUUID UUID] UUIDString]
                                                               bigImgFileId:@""
                                                              voiceDuration:[NSNumber numberWithInt:0]
                                                                      msgId:@""
                                                                   sendTime:[self getNowTimestamp]
                                                                   showTime:[self p_needShowTime:[NSDate date]]
                                                                    picSize:CGSizeMake(150, 60)
                                                                    picType:@""
                                                                   isSender:YES
                                                   receivedSenderByYourself:NO
                                                                     status:TIMMessageStatus_Sending];
    // 创建本地消息
    [self addObject:messageF isSender:YES isHeaderInsert:NO];
    // 发送中
    [self messageSending:messageF];
    
    [[OnlineMessageSendManager sharedOnlineMessageSendManager]sendFileMessageWithFileData:fileData fileType:fileTypeStr fileName:fileName success:^(NSString * _Nonnull messageId) {
        messageF.model.message.messageId = messageId;
        [self updateOldMessageId:messageId];
        [self messageSendSucced:messageF];
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        [self messageSendFailed:messageF];
        int sizeLimitM = [[OnlineDataSave shareOnlineDataSave] getAppSettingFileSize].longLongValue /1024/1024;
        NSString * strShow = [NSString stringWithFormat:@"文件过大，请发送小于%dM文件",sizeLimitM];
        [WHToast showMessage:errorDes?:strShow duration:2 finishHandler:^{
            
        }];
    }];
}

#pragma mark - # Private Methods
static NSTimeInterval lastUpdateMsgInterval = 0;
static NSTimeInterval lastDateInterval = 0;
- (BOOL)p_needShowTime:(NSDate *)date
{
    if (lastDateInterval == 0 || fabs(date.timeIntervalSince1970 - lastDateInterval) > MAX_SHOWTIME_MSG_SECOND) {
        lastDateInterval = date.timeIntervalSince1970;
        return YES;
    }
    return NO;
}

- (BOOL)p_needShowTimeStamp:(NSTimeInterval)timestamp
{
    NSDate * date = [kitUtils getDateTimeFromMilliSeconds:timestamp];
    return [self p_needShowTime:date];
}

- (BOOL)p_needUpdateUITime:(NSDate *)date
{
    if (lastUpdateMsgInterval == 0 || fabs(date.timeIntervalSince1970 - lastUpdateMsgInterval) > MAX_SHOWTIME_MSG_SECOND) {
        lastUpdateMsgInterval = date.timeIntervalSince1970;
        return YES;
    }
    return NO;
}

// 异步获取member的头像
-(void)getMemberAvatar:(NSString *)userId memberAvatar:(NSString *)memberAvatar type:(NSString*)type{
    @WeakObj(self)
    dispatch_async(dispatch_queue_create(0, 0), ^{
        // 子线程执行任务（比如获取较大数据）
        //1.通过URL创建NSURLRequest
        NSURLRequest *request = [[NSURLRequest alloc] initWithURL:[NSURL URLWithString:memberAvatar] cachePolicy:NSURLRequestUseProtocolCachePolicy timeoutInterval:10];
        //2.创建一个 NSMutableURLRequest 添加 header
        NSMutableURLRequest *mutableRequest = [request mutableCopy];
        //3.把值覆给request
        request = [mutableRequest copy];
        //3.建立网络连接NSURLConnection，同步请求数据
        NSHTTPURLResponse *response = nil;
        NSError *error = nil;
        NSData *fileData = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
        // 通知主线程刷新 神马的
        if (fileData) {
            TIMKitLog(@"save 头像 userId = %@",userId);
            [[ICMediaManager sharedManager] saveAvatarImage:fileData userId:userId];
        } else {
            UIImage *loadimage = nil;
            if ([type isEqualToString:@"robot"]) {//机器人头像
                loadimage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"customer_service_default_avatar"]];
            }else{
                loadimage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"default_avatar"]];
            }
            
            NSData * avatrData = UIImageJPEGRepresentation(loadimage,1.0f);
            [[ICMediaManager sharedManager] saveAvatarImage:avatrData userId:userId];
        }
    });
}

- (void)dealloc {
    TIMKitLog(@"退出ChatView");
    
    
    NSString *inputText = [self.chatBoxVC.chatBox transalteEmoticonAttributedString:self.chatBoxVC.chatBox.textView.attributedText];
    
    [[OnlineDataSave shareOnlineDataSave] saveUserInputText:inputText];
    
    [[ICRecordManager shareManager] pause];
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (NSMutableDictionary <NSString *,NSMutableArray *>*)atDataDic {
    if (!_atDataDic) {
        _atDataDic = [NSMutableDictionary dictionary];
    }
    return _atDataDic;
}

#pragma mark - 历史消息解析
//拉取历史消息
-(void)analysisHistoryWithModel:(OnlineChatRecordModel*)model withItemReload:(BOOL)reload {
    NSLog(@"model ======== %@",[model yy_modelToJSONObject]);
    BOOL messageRecvDirection = nil;
    if ([model.senderType isEqualToString:@"2"]) {//访客发送的消息
        messageRecvDirection = NO;
    }else if ([model.senderType isEqualToString:@"4"]) {//机器人发送的消息
        messageRecvDirection = YES;
    }else{
        messageRecvDirection = YES;
    }
    
    NSString*messageStr = @"";
    
    /// 移动端目前只处理到32种类型，超过32的都是未知消息类型
    if ([model.messageType intValue] > 32) {
        if ([self historyMessageRevoke:model]) {
            return;
        }
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeUnknown originalType:@"text" content:@"未知消息类型" extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:YES];
        if (reload == NO) {
            return;
        }
        if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
            [self newMessageSendFailed:messageF];
        }else{
            [self newMessageSendSucced:messageF];
        }
        return;
    }
    
    if ([model.messageType intValue] == 1) {//文本消息
        if (model.content == nil || model.content.length == 0) {
            return;
        }
        messageStr = model.content;
        
        if (messageRecvDirection) {
            //系统消息
            if ([model.senderType isEqualToString:@"3"] || [model.senderType isEqualToString:@"5"]) {//机器人发送的消息
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeSystem originalType:@"text" content:messageStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
                [self addObject:messageF isSender:NO];
                if (reload == NO) {
                    return;
                }
                [self newMessageSendSucced:messageF];
            }else{
                if ([self historyMessageRevoke:model]) {
                    return;
                }
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText originalType:@"text" content:messageStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
                [self addObject:messageF isSender:NO];
                if (reload == NO) {
                    return;
                }
                [self newMessageSendSucced:messageF];
            }
            
        } else {
            if ([self historyMessageRevoke:model]) {
                return;
            }
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText originalType:@"text" content:messageStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:YES];
            if (reload == NO) {
                return;
            }
            if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
                [self newMessageSendFailed:messageF];
            }else{
                [self newMessageSendSucced:messageF];
            }
        }
        
    }else if ([model.messageType intValue] == 2) {//图片消息
        NSString*fileKey = @"null";
        NSString*fileName = @"null";
        if (![kitUtils isBlankString:model.fileKey]) {
            fileKey = model.fileKey;
        }
        if (![kitUtils isBlankString:model.fileName]) {
            fileName = model.fileName;
        }
        NSString * timestamp = [kitUtils getNowTimestampWithSec];
        
        NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
        NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
        NSString*urlPath =   [[NSString stringWithFormat:@"%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[NSString stringWithFormat:@"%@/api/app/message/file",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl]],fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        
        //根据msgId去本地搜寻
        UIImage *image;
        NSString *localPath = @"";
        CGFloat fixelW = 100;
        CGFloat fixelH = 100;
        NSString * fileKeyName = [model.fileKey stringByDeletingPathExtension];
        localPath = [[ICMediaManager sharedManager] smallImgPath:fileKeyName];
        image = [UIImage imageWithContentsOfFile:localPath];
        if (image) {
            self.downloadFileDatas[model.uniqueId] = image;
            fixelW = image.size.width;
            fixelH = image.size.height;
        } else {
            image = [UIImage imageWithData:[NSData dataWithContentsOfURL:[NSURL URLWithString:urlPath]]];
            if (image) {
                [[ICMediaManager sharedManager] saveImage:image msgId:fileKeyName picType:@"jpg"];
                fixelW = image.size.width;
                fixelH = image.size.height;
            }
        }
        
        if (messageRecvDirection){
            if ([self historyMessageRevoke:model]) {
                return;
            }
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic originalType:@"image" content:@"[图片]" extra:@"" auditExtra:@"" path:localPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:NO];
            if (reload == NO) {
                return;
            }
            [self newMessageSendSucced:messageF];
        }else{
            if ([self historyMessageRevoke:model]) {
                return;
            }
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypePic originalType:@"image" content:@"[图片]" extra:@"" auditExtra:@"" path:localPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"" isSender:YES receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:YES];
            if (reload == NO) {
                return;
            }
            [self newMessageSendSucced:messageF];
        }
    }
    else if ([model.messageType intValue] == 3 ||
             [model.messageType intValue] == 8){//文件消息
        
        NSString*fileUrlPath = @"";
        NSString*fileKey = @"null";
        NSString*fileName = @"null";
        if (![kitUtils isBlankString:model.fileKey]) {
            fileKey = model.fileKey;
        }
        if (![kitUtils isBlankString:model.fileName]) {
            fileName = model.fileName ;
        }
        
        NSString *path = @"";
        if ([model.messageType intValue] == 8) {
            path = @"/api/kb/files/attachment";
        } else {
            path = @"/api/app/message/file";
        }
        
        //视频消息类型
        if (fileName.length>0 && [fileName rangeOfString:@"mp4"].location !=NSNotFound) {
            
            NSString * timestamp = [kitUtils getNowTimestampWithSec];
            NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
            NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
            
            NSString*urlPath =  [[NSString stringWithFormat:@"%@%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl],path,fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
            
            UIImage *videoArrowImage;
            NSString * localPath;
            localPath = [[ICMediaManager sharedManager] smallImgPath:model.uniqueId];
            videoArrowImage = [UIImage imageWithContentsOfFile:localPath];
            if (!videoArrowImage) {
                UIImage *resultImg = [self getVideoPreViewImage:[NSURL URLWithString:urlPath]];
                videoArrowImage = [UIImage addImage2:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"video_play_btn_bg"]] toImage:resultImg];
                if (videoArrowImage) {
                    [[ICMediaManager sharedManager] saveImage:videoArrowImage msgId:model.uniqueId picType:@"jpg"];
                }
            }
            CGFloat fixelW = videoArrowImage.size.width;
            CGFloat fixelH = videoArrowImage.size.height;
            
            if (messageRecvDirection) {
                if ([self historyMessageRevoke:model]) {
                    return;
                }
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"jpg" isSender:NO receivedSenderByYourself:NO status:1];
                // 创建本地消息
                [self addObject:messageF isSender:NO];
                if (reload == NO) {
                    return;
                }
                [self newMessageSendSucced:messageF];
            }else{
                if ([self historyMessageRevoke:model]) {
                    return;
                }
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"jpg" isSender:YES receivedSenderByYourself:NO status:1];
                // 创建本地消息
                [self addObject:messageF isSender:YES];
                if (reload == NO) {
                    return;
                }
                if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
                    [self newMessageSendFailed:messageF];
                } else {
                    [self newMessageSendSucced:messageF];
                }
            }
        }else{//文件类型
            //文件消息类型
            if (fileName.length>0 ) {
                NSString * timestamp = [kitUtils getNowTimestampWithSec];
                NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
                NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
                NSString*urlPath =   [[NSString stringWithFormat:@"%@%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl],path,fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
                fileUrlPath = urlPath;
            }else{
                fileUrlPath = @"";
            }
            
            NSMutableDictionary*dict = [[NSMutableDictionary alloc]init];
            [dict setObject:model.fileName?:@"" forKey:@"fileName"];
            [dict setObject:model.fileKey?:@"" forKey:@"fileKey"];
            NSError *parseError = nil;
            NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dict options:NSJSONWritingPrettyPrinted error:&parseError];
            NSString*messageContent = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
            
            
            if ([model.messageType intValue] == 8) {
                messageContent = [TIMLibUtils DataTOjsonString:@{@"fileName": model.fileName?:@"", @"messageType": @"8"}];
            }
            
            if (messageRecvDirection) {
                if ([self historyMessageRevoke:model]) {
                    return;
                }
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCustomFile originalType:@"file" content:messageContent extra:@"" auditExtra:@"" path:fileUrlPath urlPath:fileUrlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeZero picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
                [self addObject:messageF isSender:NO];
                if (reload == NO) {
                    return;
                }
                [self newMessageSendSucced:messageF];
            }else{
                
                if ([self historyMessageRevoke:model]) {
                    return;
                }
                
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCustomFile originalType:@"file" content:messageContent extra:@"" auditExtra:@"" path:fileUrlPath urlPath:fileUrlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeZero picType:@"" isSender:YES receivedSenderByYourself:NO status:1];
                [self addObject:messageF isSender:YES];
                if (reload == NO) {
                    return;
                }
                if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
                    [self newMessageSendFailed:messageF];
                }
                else {
                    [self newMessageSendSucced:messageF];
                }
            }
        }
    }
    else if( [model.messageType intValue] == 4) {//4视频消息
        NSString*fileKey = @"null";
        NSString*fileName = @"null";
        if (![kitUtils isBlankString:model.fileKey]) {
            fileKey = model.fileKey;
        }
        if (![kitUtils isBlankString:model.fileName]) {
            fileName = model.fileName;
        }
        
        //视频消息类型
        if (fileName.length>0 && ([fileName rangeOfString:@"mp4"].location !=NSNotFound || [fileName rangeOfString:@"MP4"].location !=NSNotFound)) {
            
            NSString * timestamp = [kitUtils getNowTimestampWithSec];
            NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
            NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
            NSString*urlPath =  [[NSString stringWithFormat:@"%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[NSString stringWithFormat:@"%@/api/app/message/file",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl]],fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
            
            UIImage *videoArrowImage;
            NSString * localPath;
            localPath = [[ICMediaManager sharedManager] smallImgPath:model.uniqueId];
            videoArrowImage = [UIImage imageWithContentsOfFile:localPath];
            if (!videoArrowImage) {
                UIImage *resultImg = [self getVideoPreViewImage:[NSURL URLWithString:urlPath]];
                videoArrowImage = [UIImage addImage2:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"video_play_btn_bg"]] toImage:resultImg];
                if (videoArrowImage) {
                    [[ICMediaManager sharedManager] saveImage:videoArrowImage msgId:model.uniqueId picType:@"jpg"];
                }
            }
            CGFloat fixelW = videoArrowImage.size.width;
            CGFloat fixelH = videoArrowImage.size.height;
            
            if (messageRecvDirection) {
                if ([self historyMessageRevoke:model]) {
                    return;
                }
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"jpg" isSender:NO receivedSenderByYourself:NO status:1];
                // 创建本地消息
                [self addObject:messageF isSender:NO];
                if (reload == NO) {
                    return;
                }
                [self newMessageSendSucced:messageF];
            }else{
                if ([self historyMessageRevoke:model]) {
                    return;
                }
                TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"jpg" isSender:YES receivedSenderByYourself:NO status:1];
                // 创建本地消息
                [self addObject:messageF isSender:YES];
                if (reload == NO) {
                    return;
                }
                if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
                    [self newMessageSendFailed:messageF];
                } else {
                    [self newMessageSendSucced:messageF];
                }
            }
        }else{//统一认定为文件类型
            
        }
        
    }else if ([model.messageType intValue] == 5 ||
              [model.messageType intValue] == 26) {//机器人富文本消息
        
        [self.needDelRichTextIds addObject:model.uniqueId];
        NSString *tempPath = NSTemporaryDirectory();
        NSString *filePath = [tempPath stringByAppendingPathComponent:@"RichTextMessageIds"];
        [NSKeyedArchiver archiveRootObject:self.needDelRichTextIds toFile:filePath];
        
        
        if ([model.messageType intValue] == 26) {
            TOSMessageKnowledgeBaseModel *knowledgeBaseModel = [TOSMessageKnowledgeBaseModel yy_modelWithJSON:model.content];
            model.content = knowledgeBaseModel.head;
            
            if (knowledgeBaseModel.list &&
                [knowledgeBaseModel.list isKindOfClass:[NSArray class]] &&
                knowledgeBaseModel.list.count > 0) {
                
                [knowledgeBaseModel.list enumerateObjectsUsingBlock:^(TOSMessageKnowledgeBaseListModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    
                    if ([obj.type isEqualToString:@"click"]) {
                        model.content = [NSString stringWithFormat:@"%@ <knowledge data-frontend=%@>#%@</knowledge>",model.content,obj.click.content?:@"",obj.click.content?:@""];
                    }
                }];
            }
        }
        
        RichTextMessage *richTextMsg = [[RichTextMessage alloc] initMessageWithContent:model.content];
        
        NSString *contentStr = (NSString *)richTextMsg.elements;
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:GXRichText originalType:@"text" content:contentStr extra:(NSString *)model.repliedMessage auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:NO];
        if (reload == NO) {
            return;
        }
        [self newMessageSendSucced:messageF];
    }else if ([model.messageType intValue] == 7) {//语音消息
        NSString*fileKey = @"null";
        NSString*fileName = @"null";
        if (![kitUtils isBlankString:model.fileKey]) {
            fileKey = model.fileKey;
        }
        if (![kitUtils isBlankString:model.fileName]) {
            fileName = model.fileName;
        }
        
        NSString * timestamp = [kitUtils getNowTimestampWithSec];
        NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
        NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
        NSString*urlPath =   [[NSString stringWithFormat:@"%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[NSString stringWithFormat:@"%@/api/app/message/file",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl]],fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        
        NSNumber *voiceDuration = @1;
        if (messageRecvDirection) {
            if ([self historyMessageRevoke:model]) {
                return;
            }
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVoice originalType:@"voice" content:@"[语音]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:voiceDuration msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeZero picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:NO];
            if (reload == NO) {
                return;
            }
            [self newMessageSendSucced:messageF];
        }else {
            if ([self historyMessageRevoke:model]) {
                return;
            }
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeVoice originalType:@"voice" content:@"[语音]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:voiceDuration msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeZero picType:@"" isSender:YES receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:YES];
            if (reload == NO) {
                return;
            }
            if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
                [self newMessageSendFailed:messageF];
            } else {
                [self newMessageSendSucced:messageF];
            }
        }
    } else if ([model.messageType intValue] == 10) {
        BOOL isSender = NO;
        NSString *senderId;
        NSString *receiverId;
        if (messageRecvDirection) {
            isSender = NO;
            senderId = model.sender;
            receiverId = model.visitorId;
        } else {
            isSender = YES;
            senderId = model.visitorId;
            receiverId = model.sender;
        }
        
        NSData *jsonData = [model.content dataUsingEncoding:NSUTF8StringEncoding];
        NSError *err;
        NSDictionary *messageContentDic = [NSJSONSerialization JSONObjectWithData:jsonData
                                                                          options:NSJSONReadingMutableContainers
                                                                            error:&err];
        
        if (messageContentDic &&
            [messageContentDic isKindOfClass:[NSDictionary class]] &&
            [[messageContentDic allKeys] containsObject:@"cardType"] &&
            [messageContentDic[@"cardType"] isKindOfClass:[NSString class]] &&
            [messageContentDic[@"cardType"] isEqualToString:@"1"]) {    //物流卡片
            
            TIMLogisticsCardMessage *cardMsg = [TIMLogisticsCardMessage yy_modelWithJSON:model.content];
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeLogisticsCard originalType:model.type content:cardMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:senderId to:receiverId fileKey:model.mainUniqueId bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:model.createTime.integerValue showTime:[self p_needShowTimeStamp:(NSTimeInterval)model.createTime.integerValue] picSize:CGSizeMake(0, 0) picType:@"" isSender:isSender receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:isSender];
        } else {
            TIMCommodityCardMessage *commodityMsg = [TIMCommodityCardMessage yy_modelWithJSON:model.content];
            
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeCommodityCardDetails originalType:model.type content:commodityMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:senderId to:receiverId fileKey:model.mainUniqueId bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:model.createTime.integerValue showTime:[self p_needShowTimeStamp:(NSTimeInterval)model.createTime.integerValue] picSize:CGSizeMake(0, 0) picType:@"" isSender:isSender receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:isSender];
        }
        
    } else if ([model.messageType intValue] == 13) {
        
        TOSMessageSmallProgramModel *smallProgramMsg = [TOSMessageSmallProgramModel yy_modelWithJSON:model.content];
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeSmallProgramCard originalType:@"text" content:(NSString *)smallProgramMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:nil bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        
        [self addObject:messageF isSender:NO];
        [self newMessageSendSucced:messageF];
    } else if ([model.messageType intValue] == 12) {//留言消息
        
        if (model.content == nil || model.content.length == 0) {
            return;
        }
        
        NSString*jsonTextContent = @"";
        NSError *err;
        NSData *leavejsonData = [model.content dataUsingEncoding:NSUTF8StringEncoding];
        NSDictionary *leavejsonDict = [NSJSONSerialization JSONObjectWithData:leavejsonData
                                                                      options:NSJSONReadingMutableContainers
                                                                        error:&err];
        if ([leavejsonDict isKindOfClass:[NSDictionary class]]) {
            NSArray* titleArr = [leavejsonDict allKeys];
            if (titleArr.count>0) {
                for (NSString*key in titleArr) {
                    jsonTextContent = [NSString stringWithFormat:@"%@\n%@:%@",jsonTextContent,key,leavejsonDict[key]];
                }
            }
        }
        jsonTextContent  = [jsonTextContent substringFromIndex:1];
        
        if (messageRecvDirection) {
            if ([self historyMessageRevoke:model]) {
                return;
            }
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText originalType:@"text" content:jsonTextContent extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:NO];
            if (reload == NO) {
                return;
            }
            [self newMessageSendSucced:messageF];
        } else {
            if ([self historyMessageRevoke:model]) {
                return;
            }
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeText originalType:@"text" content:jsonTextContent extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1];
            [self addObject:messageF isSender:YES];
            if (reload == NO) {
                return;
            }
            if ([model.sendStatus intValue] < TIMMessageStatus_Sended) {
                [self newMessageSendFailed:messageF];
            }else{
                [self newMessageSendSucced:messageF];
            }
        }
        
        
    }else if ([model.messageType intValue] == 6  ||
              [model.messageType intValue] == 15 ||//机器人相关问题
              [model.messageType intValue] == 16 ||//机器人猜你想问
              [model.messageType intValue] == 17 ||//机器人常见问题
              [model.messageType intValue] == 18 ||//机器人近似问题
              [model.messageType intValue] == 19 ||//机器人选项消息
              [model.messageType intValue] == 20) {//机器人相关问题
        
        NSArray <CombinationMessage *>*message = [NSArray yy_modelArrayWithClass:[CombinationMessage class] json:model.content];
        
        [message enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            obj.robotProvider = model.robotProvider;
        }];
        
        BOOL isSender = messageRecvDirection?NO:YES;
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeRobotCombination originalType:@"text" content:(NSString *)message extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:nil bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:isSender receivedSenderByYourself:NO status:1];
        
        [self addObject:messageF isSender:isSender];
    }else if ([model.messageType intValue] == 14) {//机器人组合消息
        
        NSArray <CombinationMessage *>*message = [NSArray yy_modelArrayWithClass:[CombinationMessage class] json:model.content];
        
        [message enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            obj.robotProvider = model.robotProvider;
        }];
        
        BOOL isSender = messageRecvDirection?NO:YES;
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeRobotCombination originalType:@"text" content:(NSString *)message extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:nil bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:isSender receivedSenderByYourself:NO status:1];
        
        [self addObject:messageF isSender:isSender];
    }else if ([model.messageType intValue] == 27) {//满意度评价
        if (model.content == nil || model.content.length == 0) {
            return;
        }
        messageStr = model.content;
        
        NSString *extra = [TIMLibUtils convertToJsonDataWithDic:@{@"alreadyInvestigation" : model.alreadyInvestigation?:@"", @"uniqueId": model.uniqueId?:@""}];
        NSString *content = [[OnlineDataSave shareOnlineDataSave] getAppSetting];
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeInvestigation originalType:model.type content:content extra:extra auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:NO];
        if (reload == NO) {
            return;
        }
        [self newMessageSendSucced:messageF];
    } else if ([model.messageType intValue] == 30) {//
        
        TOSMessageTextTagModel *textTagModel = [TOSMessageTextTagModel yy_modelWithJSON:model.content];
        
        NSMutableArray <TOSMessageTextSubTagModel *>*tags = [NSMutableArray array];
        
        [textTagModel.data enumerateObjectsUsingBlock:^(NSString * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            
            TOSMessageTextSubTagModel *model = [TOSMessageTextSubTagModel yy_modelWithJSON:obj];
            [tags addObject:model];
        }];
        
        textTagModel.tags = [NSArray arrayWithArray:tags];
        
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeTextTag originalType:model.type content:(NSString *)textTagModel extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:NO];
        if (reload == NO) {
            return;
        }
        [self newMessageSendSucced:messageF];
    }
}

/// 判断拉去的历史消息中是否有消息状态为撤回消息 YES：是撤回，NO：不是撤回消息
- (BOOL)historyMessageRevoke:(OnlineChatRecordModel *)model {
    if ([model.sendStatus intValue] == 3) {
        TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeRevoke originalType:@"withdraw" content:@"对方撤回了一条消息" extra:@"" auditExtra:@"" path:@"" urlPath:@"" from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeZero picType:@"" isSender:NO receivedSenderByYourself:NO status:1];
        [self addObject:messageF isSender:YES];
        return YES;
    }
    return NO;
}


//退出排队，退出排队事件
-(void)leaveQueueAction:(NSNotification *)notification {
    
    [[OnlineEventSendManager sharedOnlineEventSendManager] leaveQueueEventWithSuccess:^{
        //退出排队成功
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        //退出排队失败
    }];
    
}
//关闭会话
-(void)closeChatActon{
    self.isOpenSession = NO;
    [[OnlineEventSendManager sharedOnlineEventSendManager] chatCloseEventWithSuccess:^{
        //存储会话状态
        NSString * curMainUniqueId = [[OnlineDataSave shareOnlineDataSave] getMainUniqueId];
        TOSSessionInfoModel * model = [[TOSSessionInfoModel alloc] init];
        [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[model yy_modelToJSONString]];
        [[OnlineDataSave shareOnlineDataSave] saveMainUniqueIdRunningStatus:curMainUniqueId runningStatus:@"ClosedStatus"];// 目前只做ClosedStatus RunningStatus俩个状态
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        
    }];
}

// 获取网络视频第一帧
- (UIImage*) getVideoPreViewImage:(NSURL *)path
{
    AVURLAsset *asset = [[AVURLAsset alloc] initWithURL:path options:nil];
    AVAssetImageGenerator *assetGen = [[AVAssetImageGenerator alloc] initWithAsset:asset];
    
    assetGen.appliesPreferredTrackTransform = YES;
    CMTime time = CMTimeMakeWithSeconds(0.0, 600);
    NSError *error = nil;
    CMTime actualTime;
    CGImageRef image = [assetGen copyCGImageAtTime:time actualTime:&actualTime error:&error];
    UIImage *videoImage = [[UIImage alloc] initWithCGImage:image];
    CGImageRelease(image);
    return videoImage;
}

//正则去除标签
-(NSString *)getZZwithString:(NSString *)string{
    NSRegularExpression *regularExpretion=[NSRegularExpression regularExpressionWithPattern:@"<[^>]*>|\n"
                                                                                    options:0
                                                                                      error:nil];
    string=[regularExpretion stringByReplacingMatchesInString:string options:NSMatchingReportProgress range:NSMakeRange(0, string.length) withTemplate:@""];
    return string;
}

@end