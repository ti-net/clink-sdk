//
//  ICChatBoxViewController.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/10.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "TIMChatBoxViewController.h"
#import "ICChatBoxMoreView.h"
//#import "ICChatBoxFaceView.h"
#import "TIMICMessage.h"
#import "ICMediaManager.h"
#import "ICVideoView.h"
#import "ICMessageConst.h"
#import "ICVideoManager.h"
#import "UIImage+Extension.h"
#import "UIView+SDExtension.h"
#import "NSString+Extension.h"
#import "TIMConstants.h"
//#import "ICDocumentViewController.h"
#import "ICTools.h"
#import "TIMXFCameraController.h"
// TZImagePicker
#import "MYHTZImagePickerController.h"
#import "kitUtils.h"
#import <TOSClientKit/TOSKit.h>
#import "NSObject+TIMShowError.h"
#import "WHToast.h"
#import "TIMSYCacheFileManager.h"
#import "TIMSYCacheFileViewController.h"
#import "TIMMasonry.h"
#import "TOSKitChatBoxExtendBoard.h"
#import "TOSKitExtendBoardItemModel.h"

#import "XZEmotion.h"
#import "ICFaceManager.h"
#import "TIMQEmotionHelper.h"
#import "TIMQEmotionBoardView.h"

@interface TIMChatBoxViewController ()<ICChatBoxDelegate,ICChatBoxMoreViewDelegate,UINavigationControllerDelegate, UIImagePickerControllerDelegate,MYHTZImagePickerControllerDelegate,UIAlertViewDelegate, ICChatBoxBarViewDelegate, ICChatBoxBarViewDataSource, TIMQEmotionBoardViewDelegate> //ICDocumentDelegate

/** 更多功能的面板*/
@property (nonatomic, strong) ICChatBoxMoreView *chatBoxMoreView;
/** 表情面板 */
@property (nonatomic, strong) TIMQEmotionBoardView *chatBoxFaceView;
/** 录音文件名 */
@property (nonatomic, copy) NSString *recordName;
@property (nonatomic, strong) UIImagePickerController *imagePicker;

@property (nonatomic, weak) ICVideoView *videoView;

@property(nonatomic, assign)  NSInteger userAuth;

/// 快捷功能的model数组
@property (nonatomic, strong) NSArray                * barDataCount;

@end

@implementation TIMChatBoxViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    if (@available(iOS 13.0, *)) {
           self.overrideUserInterfaceStyle = UIUserInterfaceStyleLight;    //关闭暗黑模式
    }
//    self.view.backgroundColor = [UIColor whiteColor];
    /// 默认跟chatBox的背景颜色一致
    self.view.backgroundColor = [TOSKitCustomInfo shareCustomInfo].ChatBox_backGroundColor;
    
    self.userAuth = self.curGroupType.intValue;

    [self.view addSubview:self.chatBox];
    self.chatBox.barDelegate = self;
    self.chatBox.barDataSource = self;
    
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillHide:) name:UIKeyboardWillHideNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardFrameWillChange:) name:UIKeyboardWillChangeFrameNotification object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(chatBoxFaceSend:) name:kTextViewChangeNotification object:nil];
    
    /// 监听当前会话状态是否为座席接待，并且打开了更多面板的变更开关（onlineChange）
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(chatBoxExtendBoardChangeItem) name:kOnlineChangeExtendBoardItemNotification object:nil];

}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    
    [self chatBoxExtendBoardChangeItem];
    
}

- (ICChatBoxMoreViewItem *)setupxMoreItem:(NSString *)title
                                imageName:(NSString *)imageName
                                      obj:(TOSKitExtendBoardItemModel *)obj {
    if (![kitUtils isBlankString:obj.title]) {
        title = obj.title;
    }
    
    NSString *image = [NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,imageName];
    if (![kitUtils isBlankString:obj.image]) {
        image = obj.image;
    }
    
    return [ICChatBoxMoreViewItem createChatBoxMoreItemWithTitle:title imageName:image index:obj.index type:obj.type];
}

/// 加载扩展面板的item数据源
- (void)chatBoxExtendBoardChangeItem {
    /// 默认加载扩展面板的item数据源
    NSArray <TOSKitExtendBoardItemModel *>*allItems = [TOSKitChatBoxExtendBoard shareChatBoxExtendBoard].allItems;
    /// 如果开启了座席在线状态下修改更多面板的item开关，并且当前的会话状态是座席接待，替换扩展面板的item数据源
    if (TOSKitChatBoxExtendBoard.shareChatBoxExtendBoard.onlineChange && TOSClientKit.sharedTOSKit.getCurrentOnlineStatus == TinetChatStatusTypeOnline) {
        allItems = TOSKitChatBoxExtendBoard.shareChatBoxExtendBoard.onlienAllItems;
    }
    
    NSMutableArray *arr = [NSMutableArray array];
    [allItems enumerateObjectsUsingBlock:^(TOSKitExtendBoardItemModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        switch (obj.type) {
            case TOSChatBoxExtendBoardTypePhotos: {
                ICChatBoxMoreViewItem *photosItem = [self setupxMoreItem:@"相册" imageName:@"onlinePic" obj:obj];
                [arr addObject:photosItem];
            }
                break;
            case TOSChatBoxExtendBoardTypeTakePicture: {
                ICChatBoxMoreViewItem *takePictureItem = [self setupxMoreItem:@"拍摄" imageName:@"onlineCamera" obj:obj];
                [arr addObject:takePictureItem];
            }
                break;
            case TOSChatBoxExtendBoardTypeCustomFile: {
                ICChatBoxMoreViewItem *customFileItem = [self setupxMoreItem:@"文件" imageName:@"sharemore_file" obj:obj];
                [arr addObject:customFileItem];
            }
                break;
            case TOSChatBoxExtendBoardTypeArtificial: {
                ICChatBoxMoreViewItem *artificialItem = [self setupxMoreItem:@"转人工" imageName:@"onlineRengong" obj:obj];
                [arr addObject:artificialItem];
            }
                break;
            case TOSChatBoxExtendBoardTypeCloseChat: {
                ICChatBoxMoreViewItem *closeChatItem = [self setupxMoreItem:@"结束会话" imageName:@"onlineCloseChat" obj:obj];
                [arr addObject:closeChatItem];
            }
                break;
            case TOSChatBoxExtendBoardTypeCustomFileApp: {
                ICChatBoxMoreViewItem *closeChatItem = [self setupxMoreItem:@"文件" imageName:@"onlineCloseChat" obj:obj];
                [arr addObject:closeChatItem];
            }
                break;
            default: {
                ICChatBoxMoreViewItem *customItem = [self setupxMoreItem:@"" imageName:@"" obj:obj];
                [arr addObject:customItem];
            }
                break;
        }
    }];
        
    [self.chatBoxMoreView setItems:arr];
    
    
}

#pragma mark - QEmotionBoardViewDelegate
/**
 *  选中表情时的回调
 *  @param  index   被选中的表情在`emotions`里的索引
 *  @param  emotion 被选中的表情对应的`QMUIEmotion`对象
 */
- (void)emotionView:(TIMQEmotionBoardView *)emotionView didSelectEmotion:(TIMQEmotion *)emotion atIndex:(NSInteger)index {
    
    XZEmotion *xzEmotion = [[XZEmotion alloc] init];
    xzEmotion.face_name = emotion.identifier;
    
    NSMutableDictionary *userInfo = [NSMutableDictionary dictionary];
    userInfo[GXSelectEmotionKey]  = xzEmotion;
    [[NSNotificationCenter defaultCenter] postNotificationName:GXEmotionDidSelectNotification object:nil userInfo:userInfo];
}

// 删除按钮的点击事件回调
- (void)emotionViewDidSelectDeleteButton:(TIMQEmotionBoardView *)emotionView {
    [[NSNotificationCenter defaultCenter] postNotificationName:GXEmotionDidDeleteNotification object:nil];
}

// 发送按钮的点击事件回调
- (void)emotionViewDidSelectSendButton:(TIMQEmotionBoardView *)emotionView {
    [[NSNotificationCenter defaultCenter] postNotificationName:GXEmotionDidSendNotification object:nil];
}

#pragma mark - Public Methods

- (BOOL)resignFirstResponder
{
    self.view.backgroundColor = [TOSKitCustomInfo shareCustomInfo].ChatBox_backGroundColor;
    if (self.chatBox.status == ICChatBoxStatusShowVideo) { // 录制视频状态
        if (_delegate && [_delegate respondsToSelector:@selector(chatBoxViewController:didChangeChatBoxHeight:)]) {
            [UIView animateWithDuration:0.3 animations:^{
                [self->_delegate chatBoxViewController:self didChangeChatBoxHeight:[self getTextviewHeight]];
            } completion:^(BOOL finished) {
                [self.videoView removeFromSuperview]; // 移除video视图
                self.chatBox.status = ICChatBoxStatusShowKeyboard;//同时改变状态
                dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
                   [[ICVideoManager shareManager] exit];  // 防止内存泄露
                });
            }];
        }
        return [super resignFirstResponder];
    }
    if (self.chatBox.status != ICChatBoxStatusShowVoice) {
        [self.chatBox resignFirstResponder];
        if (_delegate && [_delegate respondsToSelector:@selector(chatBoxViewController:didChangeChatBoxHeight:)]) {
            [UIView animateWithDuration:0.3 animations:^{
//                NSLog(@"status = %ld self.keyboardFrame.size.height = %ld",(long)self.chatBox.status, (long)self.keyboardFrame.size.height);
                    [self->_delegate chatBoxViewController:self didChangeChatBoxHeight:[self getTextviewHeight]];
            } completion:^(BOOL finished) {
                [self.chatBoxFaceView removeFromSuperview];
                [self.chatBoxMoreView removeFromSuperview];
                // 状态改变
                self.chatBox.status = ICChatBoxStatusShowKeyboard;
            }];
        }

    }
    return [super resignFirstResponder];
}

- (BOOL)becomeFirstResponder
{
    return [super becomeFirstResponder];
}

- (void)routerEventWithName:(NSString *)eventName userInfo:(NSDictionary *)userInfo
{
    if ([eventName isEqualToString:GXRouterEventVideoRecordExit]) {
        [self resignFirstResponder];
    } else if ([eventName isEqualToString:GXRouterEventVideoRecordFinish]) {
//        NSString *videoPath = userInfo[VideoPathKey];
        [self resignFirstResponder];
//        if (_delegate && [_delegate respondsToSelector:@selector(chatBoxViewController:sendVideoMessage:duration:thumbnailImage)]) {
//            [_delegate chatBoxViewController:self sendVideoMessage:videoPath];
//        }
    } else if ([eventName isEqualToString:GXRouterEventVideoRecordCancel]) {
        //ICLog(@"record cancel");
    } 
}

#pragma mark - Private Methods

- (NSString *)currentRecordFileName
{
    NSTimeInterval timeInterval = [[NSDate date] timeIntervalSince1970];
    NSString *fileName = [NSString stringWithFormat:@"%ld",(long)timeInterval];
    return fileName;
}

- (void)keyboardWillHide:(NSNotification *)notification
{
    self.keyboardFrameHeight = self.keyboardFrame.size.height;
    if (_chatBox.status == ICChatBoxStatusShowFace || _chatBox.status == ICChatBoxStatusShowMore) {
       return;
   }

    self.keyboardFrame = CGRectZero;
    if (_delegate && [_delegate respondsToSelector:@selector(chatBoxViewController:didChangeChatBoxHeight:)]) {
        [_delegate chatBoxViewController:self didChangeChatBoxHeight:[self getTextviewHeight]];
        _chatBox.status = ICChatBoxStatusShowKeyboard;
    }
}

- (void)keyboardFrameWillChange:(NSNotification *)notification
{
    if ([self.chatBox.textView isFirstResponder]) {
        /// 输入框成为第一响应者，需要发送通知移除底部的商品卡片UI
        [[NSNotificationCenter defaultCenter] postNotificationName:kTIMMessageSendChatUIFromLibNotification object:nil];
        
        self.keyboardFrame = [notification.userInfo[UIKeyboardFrameEndUserInfoKey] CGRectValue];
        self.keyboardFrameHeight = self.keyboardFrame.size.height;
        // MARK: 软键盘可以设置是否启用输入预测，如果没有输入预测，会被下面的方法拦截掉，无法进入后续逻辑，HEIGHT_CHATBOXVIEW是表情/更多面板的高度，不应该用来对软键盘进行高度的比较!!!⚠️⚠️⚠️
    //    if (_chatBox.status == ICChatBoxStatusShowKeyboard && self.keyboardFrame.size.height <= HEIGHT_CHATBOXVIEW) {
    //        return;
    //    }
    //    else
        if ((_chatBox.status == ICChatBoxStatusShowFace || _chatBox.status == ICChatBoxStatusShowMore) && self.keyboardFrame.size.height <= HEIGHT_CHATBOXVIEW) {
            return;
        }
        
        else if (_chatBox.status == ICChatBoxStatusShowFace  || _chatBox.status == ICChatBoxStatusShowMore) {
            return;
        }
        
        if (_delegate && [_delegate respondsToSelector:@selector(chatBoxViewController:didChangeChatBoxHeight:)]) {
            [_delegate chatBoxViewController:self didChangeChatBoxHeight: self.keyboardFrame.size.height + [self getTextviewHeight]];
            _chatBox.status = ICChatBoxStatusShowKeyboard; // 状态改变
            [self.chatBoxFaceView removeFromSuperview];
            [self.chatBoxMoreView removeFromSuperview];
        }
    }
}

- (void)chatBoxFaceSend:(NSNotification *)notification {
    NSDictionary * dict = notification.userInfo;
    
    if (_chatBox.status == ICChatBoxStatusShowFace) {
        /// 表情面板
        NSLog(@"表情面板");
        self.chatBoxFaceView.isHighlighted = [dict[@"isHighlighted"] boolValue];
    }
    NSLog(@"其他面板");
    
}

// 将要弹出视频视图
- (void)videoViewWillAppear
{
    ICVideoView *videoView = [[ICVideoView alloc] initWithFrame:CGRectMake(0, 0, App_Frame_Width, videwViewH)];
    [self.view insertSubview:videoView aboveSubview:self.chatBox];
    self.videoView = videoView;
    videoView.hidden = YES;
    if (_delegate && [_delegate respondsToSelector:@selector(chatBoxViewController:didVideoViewAppeared:)]) {
        [_delegate chatBoxViewController:self didVideoViewAppeared:videoView];
    }
}

- (void)dealloc{
    NSLog(@"ICChatBox dealloc");
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardWillChangeFrameNotification object:nil];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardWillHideNotification object:nil];
    
    [[NSNotificationCenter defaultCenter] removeObserver:self name:kTextViewChangeNotification object:nil];
}

#pragma mark - Getter and Setter

- (ICChatBox *) chatBox
{
    if (_chatBox == nil) {
//        _chatBox = [[ICChatBox alloc] initWithFrame:CGRectMake(0, 0, App_Frame_Width, HEIGHT_TABBAR)];
        _chatBox = [[ICChatBox alloc] initWithFrame:CGRectMake(0, 0, App_Frame_Width, [TOSKitCustomInfo shareCustomInfo].chatBox_Height)];
        _chatBox.delegate = self;
    }
    return _chatBox;
}

- (TIMQEmotionBoardView *)chatBoxFaceView
{
    if (nil == _chatBoxFaceView) {
//        _chatBoxFaceView = [[QEmotionBoardView alloc] initWithFrame:CGRectMake(0, HEIGHT_TABBAR, App_Frame_Width, HEIGHT_CHATBOXVIEW)];
        TIMQEmotionHelper *faceManager = [TIMQEmotionHelper sharedEmotionHelper];
        faceManager.emotionArray = [self createTotalEmotion];
        CGFloat chatBarheight = self.barDataCount.count ? CHATBOX_BAR_HEIGHT : 0.0f;
        _chatBoxFaceView = [[TIMQEmotionBoardView alloc] initWithFrame:CGRectMake(0, [TOSKitCustomInfo shareCustomInfo].chatBox_Height+chatBarheight, App_Frame_Width, HEIGHT_CHATBOXVIEW)];
        _chatBoxFaceView.emotions = faceManager.emotionArray;
        _chatBoxFaceView.delegate = self;
        /***删除按钮相关配置***/
        CGPoint point1 = [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButtonOffset;
        CGPoint point2 = _chatBoxFaceView.deleteButtonOffset;
        
        if (point1.x > point2.x || (point1.x == point2.x && point1.y > point2.y)) {
            _chatBoxFaceView.deleteButtonOffset = [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButtonOffset;
        }
        if ([TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButton_cornerRadius != _chatBoxFaceView.deleteButtonCornerRadius) {
            _chatBoxFaceView.deleteButtonCornerRadius = [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButton_cornerRadius;
        }
        
        /***发送按钮相关配置***/
        if (!UIEdgeInsetsEqualToEdgeInsets([TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButtonMargins, _chatBoxFaceView.sendButtonMargins)) {
            _chatBoxFaceView.sendButtonMargins = [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButtonMargins;
        }
        if ([TOSKitCustomInfo shareCustomInfo].chatBox_emotion_pageControlMarginBottom != _chatBoxFaceView.pageControlMarginBottom) {
            _chatBoxFaceView.pageControlMarginBottom = [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_pageControlMarginBottom;
        }
        if ([TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButton_cornerRadius != _chatBoxFaceView.sendButtonCornerRadius) {
            _chatBoxFaceView.sendButtonCornerRadius = [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButton_cornerRadius;
        }
        _chatBoxFaceView.sendButtonBackgroundColor = [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButtonBackGroundColor;
        _chatBoxFaceView.deleteButtonImage = [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButton_image;
        _chatBoxFaceView.backgroundColor = [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_backgroundColor;
    }
    return _chatBoxFaceView;
}

//创建表情列表。这段代码耗时约0.02秒，占用内存约0.5M
- (NSMutableArray<TIMQEmotion *> *)createTotalEmotion {
    
    NSArray <XZEmotion *>*array = [ICFaceManager customEmotion];
    
    NSMutableArray<TIMQEmotion *> *emotionArray = [[NSMutableArray alloc] init];
    [array enumerateObjectsUsingBlock:^(XZEmotion * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        [emotionArray addObject:[TIMQEmotion emotionWithIdentifier:obj.face_name displayName:obj.face_name]];
    }];
    
    //这一步初始化image很重要，你可能是bundle，也可能是imageNamed。但是一定要做
    for (TIMQEmotion *e in emotionArray) {
        e.image = [[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,e.identifier]] imageWithRenderingMode:(UIImageRenderingModeAlwaysOriginal)];
    }
    return emotionArray;
}

- (ICChatBoxMoreView *)chatBoxMoreView
{
    if (nil == _chatBoxMoreView) {
        CGFloat chatBarheight = self.barDataCount.count ? CHATBOX_BAR_HEIGHT : 0.0f;
        _chatBoxMoreView = [[ICChatBoxMoreView alloc] initWithFrame:CGRectMake(0, [TOSKitCustomInfo shareCustomInfo].chatBox_Height+chatBarheight, App_Frame_Width, HEIGHT_CHATBOXVIEW)];
        _chatBoxMoreView.delegate = self;
    }
    return _chatBoxMoreView;
}

- (NSMutableArray *)configAuthorityArray:(NSMutableArray *)arr {
    NSMutableArray *mArr = [NSMutableArray arrayWithArray:arr];
    //auth只控制群聊权限
    if (self.isCurChatGroup == NO) {
        NSLog(@"self.isCurChatGroup == NO chatbox arr == %@",mArr);
        return mArr;
    }
    self.userAuth = self.curGroupType.intValue;
    [arr enumerateObjectsUsingBlock:^(ICChatBoxMoreViewItem *obj, NSUInteger idx, BOOL * _Nonnull stop) {
        if((self.userAuth & 1) != 1){
            if([obj.title isEqualToString:@"相册(审核)"] || [obj.title isEqualToString:@"拍摄(审核)"]){
                [mArr enumerateObjectsUsingBlock:^(ICChatBoxMoreViewItem *objNew, NSUInteger idx, BOOL * _Nonnull stop) {
                    if ([objNew.title isEqualToString:obj.title]) {
                        [mArr removeObject:objNew];
                    }
                }];
            }
        }
    }];
    NSLog(@"chatbox arr == %@",mArr);
    return mArr;
}

- (UIImagePickerController *)imagePicker
{
    if (nil == _imagePicker) {
        _imagePicker = [[UIImagePickerController alloc] init];
        _imagePicker.delegate = self;
        _imagePicker.modalPresentationStyle = UIModalPresentationCustom;
        _imagePicker.view.backgroundColor = [UIColor whiteColor];
        [_imagePicker.navigationBar setBackgroundImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"daohanglanbeijing"]] forBarMetrics:UIBarMetricsDefault];
        _imagePicker.navigationBar.titleTextAttributes = @{NSForegroundColorAttributeName : [UIColor whiteColor]};
    }
    return _imagePicker;
}

#pragma mark - setter
- (void)setBarItemArray:(NSArray <TOSQuickEntryModel *>*)barItemArray {
    _barItemArray = barItemArray;
    [self.chatBox configurationBarViewDataSrouce];
    
}

#pragma mark - ICChatBoxBarViewDataSource
- (NSArray<TOSQuickEntryModel *> *)ICChatBoxBarDataSource {
    NSLog(@"ICChatBoxBarDataSource");
    NSMutableArray * testArr = [NSMutableArray array];
    for (int i = 0; i < self.barItemArray.count; i++) {
        TOSQuickEntryModel * model = [self.barItemArray[i] mutableCopy];
        [testArr addObject:model];
    }
    
    if (self.barItemArray.count) {
//        self.chatBox.tosCF_height = [TOSKitCustomInfo shareCustomInfo].chatBox_Height + CHATBOX_BAR_HEIGHT;
        self.chatBox.tosCF_height = self.chatBox.textView.tosCF_height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height + CHATBOX_BAR_HEIGHT;
    } else {
//        self.chatBox.tosCF_height = [TOSKitCustomInfo shareCustomInfo].chatBox_Height;
        self.chatBox.tosCF_height = self.chatBox.textView.tosCF_height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height;
    }
    
    return testArr;
}

- (void)ICChatBoxBarWithItemDidselectedIndex:(NSInteger)index {
    if (self.delegate && [self.delegate respondsToSelector:@selector(chatBoxDidClickBarItemViewController:withItemModel:)]) {
        [self.delegate chatBoxDidClickBarItemViewController:self withItemModel:self.barItemArray[index]];
    }
}


#pragma mark - TLChatBoxMoreViewDelegate

- (void) chatBoxMoreView:(ICChatBoxMoreView *)chatBoxMoreView
           didSelectItem:(ICChatBoxMoreViewItem *)item
               itemTitle:(NSString *)itemTitle {
    
    switch (item.type) {
        case TOSChatBoxExtendBoardTypePhotos: {
            [self openPicker];
        }
            break;
        case TOSChatBoxExtendBoardTypeTakePicture: {
            [self openCamera];
        }
            break;
        case TOSChatBoxExtendBoardTypeCustomFile: {
            if (self.delegate &&
                [self.delegate respondsToSelector:@selector(chatBoxViewController:senderFile:openFileApp:)]) {
                [self.delegate chatBoxViewController:self senderFile:item.index openFileApp:NO];
            }
        }
            break;
        case TOSChatBoxExtendBoardTypeArtificial: {
            if (self.delegate && [self.delegate respondsToSelector:@selector(chatBoxViewController:zhuanRenGong:)]) {
                [self.delegate chatBoxViewController:self zhuanRenGong:item.index];
            }
        }
            break;
        case TOSChatBoxExtendBoardTypeCloseChat: {
            [self resignFirstResponder];
            if (self.delegate && [self.delegate respondsToSelector:@selector(chatBoxViewController:closeChat:)]) {
                [self.delegate chatBoxViewController:self closeChat:item.index];
            }
        }
            break;
        case TOSChatBoxExtendBoardTypeCustomFileApp : {
            if (self.delegate &&
                [self.delegate respondsToSelector:@selector(chatBoxViewController:senderFile:openFileApp:)]) {
                [self.delegate chatBoxViewController:self senderFile:item.index openFileApp:YES];
            }
        }
            break;
        default: {
            if ([item isKindOfClass:[ICChatBoxMoreViewItem class]] &&
                self.delegate &&
                [self.delegate respondsToSelector:@selector(chatBoxViewController:item:)]) {
                [self.delegate chatBoxViewController:self item:item];
            }
        }
            break;
    }
}

#pragma mark - MYHTZImagePickerControllerDelegate
- (void)imagePickerController:(MYHTZImagePickerController *)picker didFinishPickingPhotos:(NSArray<UIImage *> *)photos sourceAssets:(NSArray *)assets isSelectOriginalPhoto:(BOOL)isSelectOriginalPhoto{
    __block TIMChatBoxViewController *blockSelf = self;
    for (NSInteger i = 0; i < assets.count; i++) {
        PHAsset *asset = assets[i];
        if(asset.mediaType == PHAssetMediaTypeVideo){
            PHVideoRequestOptions *options = [[PHVideoRequestOptions alloc] init];
            options.version = PHImageRequestOptionsVersionCurrent;
            options.deliveryMode = PHVideoRequestOptionsDeliveryModeAutomatic;
            
            PHImageManager *manager = [PHImageManager defaultManager];
            [manager requestAVAssetForVideo:asset options:options resultHandler:^(AVAsset * _Nullable asset, AVAudioMix * _Nullable audioMix, NSDictionary * _Nullable info) {
                AVURLAsset *urlAsset = (AVURLAsset *)asset;
                
                NSURL *url = urlAsset.URL;
                NSData *data = [NSData dataWithContentsOfURL:url];
                
                TIMKitLog(@"%@ image url=%@ size=%ld",data,[url absoluteString],(long)data.length);
            }];
        }
        if (isSelectOriginalPhoto) {
            
            [[MYHTZImageManager manager] getOriginalPhotoWithAsset:asset progressHandler:^(double progress, NSError *error, BOOL *stop, NSDictionary *info) {
              TIMKitLog(@"选择原图片进度 = %.3f",progress);
            } newCompletion:^(UIImage *photo, NSDictionary *info, BOOL isDegraded) {
                if (!isDegraded) {
                    NSString * strOriginalPhotoPath = [info objectForKey:@"OriginalPhotoPath"];
                    
                    //获取个压缩图用途cell展示
                    NSString *small = [[strOriginalPhotoPath lastPathComponent] stringByDeletingPathExtension];
;
                    small = [NSString stringWithFormat:@"%@_small",small];
                    NSLog(@"small ------%@",small);
                    [[MYHTZImageManager manager] getPhotoWithAsset:asset completion:^(UIImage *photo1, NSDictionary *info, BOOL isDegraded) {
                        NSData *imageData = UIImageJPEGRepresentation(photo1,.5f);
                        NSString *filePath = [[ICMediaManager sharedManager] saveImage:photo1 msgId:small picType:[UIImage typeForImageData:imageData]];
                        
                        NSLog(@"small path ------%@",filePath);

                    }];
                    
                    if (blockSelf->_delegate && [blockSelf->_delegate respondsToSelector:@selector(chatBoxViewController:sendImageMessage:imagePath:)]) {
                               [blockSelf->_delegate chatBoxViewController:blockSelf sendImageMessage:photo imagePath:strOriginalPhotoPath];

                    }
                    
                }
            }];
        }else{
            [[MYHTZImageManager manager] getPhotoWithAsset:asset completion:^(UIImage *photo, NSDictionary *info, BOOL isDegraded) {
                if (!isDegraded) {
                    NSData *imageData = UIImageJPEGRepresentation(photo,1.0f);
                    NSString *filePaht = [[ICMediaManager sharedManager] saveImage:photo msgId:[kitUtils getMsgUUID] picType:[UIImage typeForImageData:imageData]];

                    NSLog(@"选择的照片路径=%@ 大小=%ld isDegraded=%d",filePaht,(long)[imageData length],(int)isDegraded);
                    
                    if (blockSelf->_delegate && [blockSelf->_delegate respondsToSelector:@selector(chatBoxViewController:sendImageMessage:imagePath:)]) {
                               [blockSelf->_delegate chatBoxViewController:blockSelf sendImageMessage:photo imagePath:filePaht];
                    }
                    
                }
            } progressHandler:^(double progress, NSError *error, BOOL *stop, NSDictionary *info) {
                TIMKitLog(@"选择图片进度 = %.3f",progress);
            } networkAccessAllowed:YES];
        }
    }
    [picker dismissViewControllerAnimated:YES completion:nil];
}

- (void)tz_imagePickerControllerDidCancel:(MYHTZImagePickerController *)picker{
    
}

- (void)imagePickerController:(MYHTZImagePickerController *)picker didFinishPickingVideo:(UIImage *)coverImage sourceAssets:(PHAsset *)asset{
    
    // 获取视频路径
    PHVideoRequestOptions *options = [[PHVideoRequestOptions alloc] init];
    options.version = PHVideoRequestOptionsVersionCurrent;
    options.deliveryMode = PHVideoRequestOptionsDeliveryModeHighQualityFormat;
    options.networkAccessAllowed = YES;
    PHImageManager *manager = [PHImageManager defaultManager];
    [manager requestAVAssetForVideo:asset options:options resultHandler:^(AVAsset * _Nullable asset, AVAudioMix * _Nullable audioMix, NSDictionary * _Nullable info) {
        AVURLAsset *urlAsset = (AVURLAsset *)asset;
        if (![asset isKindOfClass:[AVURLAsset class]]) {
            [self tim_showMBErrorView:@"不支持当前媒体类型"];
            return;
        }
        NSURL *videoUrl = urlAsset.URL;
        NSLog(@"audioMix : %@", audioMix);
        NSLog(@"视频的详细信息：%@", info);
        
        NSLog(@"获取视频的绝对路径:%@",[videoUrl absoluteString]);
        
        //判断视频内存大小
//        NSData *videoData;
//        __block NSString * strRealVideoPath;
//        if ([[[videoUrl absoluteString] substringToIndex:7] isEqualToString:@"file://"]) {
//            strRealVideoPath = [[videoUrl absoluteString] substringFromIndex:7];
//        }else{
//            strRealVideoPath = [videoUrl absoluteString];
//        }
//        videoData = [NSData dataWithContentsOfFile:strRealVideoPath];

        [self exportAssetToData:urlAsset withCoverImage:coverImage completion:^(NSData *data, AVAssetExportSessionStatus status, NSError *error) {
            if (status != AVAssetExportSessionStatusCompleted) {
                NSString * errorMsg = @"导出视频失败";
                if (error != nil) {
                    errorMsg = [NSString stringWithFormat:@"%@:%@",errorMsg,error.localizedDescription];
                }
                [self tim_showMBErrorView:errorMsg];
            }
        }];
        
        
    }];
}

- (void)exportAssetToData:(AVURLAsset *)asset withCoverImage:(UIImage *)coverImage completion:(void (^)(NSData *data, AVAssetExportSessionStatus status, NSError * error))completion {
    // 创建 AVAssetExportSession 以导出媒体资源
    AVAssetExportSession *exportSession = [[AVAssetExportSession alloc] initWithAsset:asset presetName:AVAssetExportPresetPassthrough];
    if (!exportSession) {
        completion(nil, AVAssetExportSessionStatusUnknown, nil);
        return;
    }
    
    // 判断文件路径是否存在
    NSString * fullFileName = [NSString stringWithFormat:@"%@.mp4",[[NSUUID UUID] UUIDString]];
    [[ICVideoManager shareManager] videoPathWithFileName:fullFileName];
    // 设置输出文件路径
    NSString *outputFilePath = [[[NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject] stringByAppendingPathComponent:kChatVideoPath] stringByAppendingPathComponent:fullFileName];
    NSURL *outputURL = [NSURL fileURLWithPath:outputFilePath];
    exportSession.outputURL = outputURL;
    exportSession.outputFileType = AVFileTypeMPEG4; // 可根据需求调整文件类型
    
    // 导出文件
    [exportSession exportAsynchronouslyWithCompletionHandler:^{
        if (exportSession.status == AVAssetExportSessionStatusCompleted) {
            // 将导出的文件转换为 NSData
            NSData *data = [NSData dataWithContentsOfURL:outputURL];
            NSLog(@"二进制数据大小：%@", data);
            CGFloat timeLong = CMTimeGetSeconds(asset.duration);
            // 保存路径
            NSString *currentTimeJpg = [NSString stringWithFormat:@"%@.jpg",[outputFilePath.lastPathComponent stringByDeletingPathExtension] ];
            UIImage *simpleImg = [UIImage simpleImage:coverImage];
            NSString * thumbnailImagePath = [[ICMediaManager sharedManager] saveVideoImage:simpleImg fileName:currentTimeJpg];
            
            NSLog(@"strRealVideoPath = %@ timeLong = %ld",outputFilePath,(long)timeLong);
            dispatch_async(dispatch_get_main_queue(), ^{
                if (self->_delegate && [self->_delegate respondsToSelector:@selector(chatBoxViewController:sendVideoMessage:duration:thumbnailImagePath:)]) {
                    [self->_delegate chatBoxViewController:self sendVideoMessage:outputFilePath duration:timeLong thumbnailImagePath:thumbnailImagePath];
                }
            });
            completion(data, AVAssetExportSessionStatusCompleted, nil);
        } else {
            completion(nil, exportSession.status, exportSession.error);
        }
        
    }];
}

#pragma mark - UIImagePickerControllerDelegate   暂时不使用

- (void)imagePickerControllerDidCancel:(UIImagePickerController *)picker {
    [picker dismissViewControllerAnimated:YES completion:nil];
}

- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary<NSString *,id> *)info {
    UIImage *orgImage = info[UIImagePickerControllerOriginalImage];
    [picker dismissViewControllerAnimated:YES completion:nil];
    // 图片压缩后再上传服务器
    // 保存路径
    NSData *imageData = UIImageJPEGRepresentation(orgImage,1.0f);
    UIImage *simpleImg = [UIImage simpleImage:orgImage];
    NSString *filePaht = [[ICMediaManager sharedManager] saveImage:simpleImg msgId:[NSString currentName] picType:[UIImage typeForImageData:imageData]];
    if (_delegate && [_delegate respondsToSelector:@selector(chatBoxViewController:sendImageMessage:imagePath:)]) {
        [_delegate chatBoxViewController:self sendImageMessage:simpleImg imagePath:filePaht];
    }
}


#pragma mark - ICChatBoxDelegate

/**
 *  输入框状态改变
 *
 *  @param chatBox    chatBox
 *  @param fromStatus 起始状态
 *  @param toStatus   目的状态
 */
- (void)chatBox:(ICChatBox *)chatBox changeStatusForm:(ICChatBoxStatus)fromStatus to:(ICChatBoxStatus)toStatus
{
    TIMKitLog(@"changeStatus fromStatus = %ld toStatus = %ld",(long)fromStatus,(long)toStatus);
    
    CGFloat chatBarheight = self.barDataCount.count ? CHATBOX_BAR_HEIGHT : 0.0f;
    NSLog(@"changeStatus fromStatus = %ld toStatus = %ld",(long)fromStatus,(long)toStatus);
    if (toStatus == ICChatBoxStatusShowKeyboard) {  // 显示键盘
        [self.chatBox resumeTextHeight:NO];
//        /// 需要优化
//        [self.chatBox.textView mas_TIMupdateTIMConstraints:^(TIMMASConstraintMaker *make) {
//            make.height.mas_TIMequalTo(HEIGHT_TEXTVIEW);
//        }];
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.3 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            if (self->_delegate && [self->_delegate respondsToSelector:@selector(chatBoxViewController:didChangeChatBoxHeight:)]) {
                [self->_delegate chatBoxViewController:self didChangeChatBoxHeight:[self getTextviewHeight] + self.keyboardFrame.size.height];
            }
            [self.chatBoxFaceView removeFromSuperview];
            [self.chatBoxMoreView removeFromSuperview];
            self.view.backgroundColor = [TOSKitCustomInfo shareCustomInfo].ChatBox_backGroundColor;
        });
        return;
    } else if (toStatus == ICChatBoxStatusShowVoice) {    // 语音输入按钮
        [UIView animateWithDuration:0.3 animations:^{
            if (self->_delegate && [self->_delegate respondsToSelector:@selector(chatBoxViewController:didChangeChatBoxHeight:)]) {
//                TIMLog(@"from = %d keyheight = %ld textviewheight = %ld",fromStatus,(long)self.keyboardFrame.size.height,(long)self.chatBox.textView.height);
                [self.chatBox resumeTextHeight:YES];
                if ((fromStatus == ICChatBoxStatusShowKeyboard) &&
                    self.keyboardFrame.size.height <= 0 &&
                    (int)self.chatBox.textView.tosSD_height <= [TOSKitCustomInfo shareCustomInfo].chatBox_Height) {
                    TIMKitLog(@"nothing to do");
                    [[NSNotificationCenter defaultCenter] postNotificationName:kTIMMessageSendChatUIFromLibNotification object:nil];
                }else{
                    
                    [self->_delegate chatBoxViewController:self didChangeChatBoxHeight:([TOSKitCustomInfo shareCustomInfo].chatBox_textView_height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height + chatBarheight + kBottomBarHeight)];
                }
            }
        } completion:^(BOOL finished) {
            [self.chatBoxFaceView removeFromSuperview];
            [self.chatBoxMoreView removeFromSuperview];
            self.view.backgroundColor = [TOSKitCustomInfo shareCustomInfo].ChatBox_backGroundColor;
        }];
    } else if (toStatus == ICChatBoxStatusShowFace) {     // 表情面板
        
        if (fromStatus == ICChatBoxStatusShowKeyboard) {//从键盘到表情切换

//            self.chatBoxFaceView.y = [self getTextviewHeight];
            self.chatBoxFaceView.tosSD_y = self.chatBox.bottom_sd;
            [self.view addSubview:self.chatBoxFaceView];
            [UIView animateWithDuration:0.3 animations:^{
                if (self->_delegate && [self->_delegate respondsToSelector:@selector(chatBoxViewController:didChangeChatBoxHeight:)]) {
                    [self->_delegate chatBoxViewController:self didChangeChatBoxHeight:[self getTextviewHeight] + HEIGHT_CHATBOXVIEW];
                }
            }];
            if (self.chatBox.textView.attributedText.length > 0) {
                self.chatBoxFaceView.isHighlighted = YES;
            }
        }else if (fromStatus == ICChatBoxStatusShowVoice) {
            if (fromStatus == ICChatBoxStatusShowVoice) {
                [self.chatBox resumeTextHeight:NO];
            }
            NSLog(@"didChangeChatBoxHeight 从语音切表情，并且文本输入框里是多行");
//            self.chatBoxFaceView.y = [self getTextviewHeight];
            /// 需要优化
//            [self.chatBox.textView mas_TIMupdateTIMConstraints:^(TIMMASConstraintMaker *make) {
//                make.height.mas_TIMequalTo(HEIGHT_TEXTVIEW);
//            }];
//            CGFloat inputHeight = [self.chatBox.textView sizeThatFits:CGSizeMake(self.chatBox.textView.width, MAXFLOAT)].height;
//            /// 这里的减3是文本框距下的高度
//            CGFloat textInputHeight = inputHeight + (HEIGHT_TABBAR - HEIGHT_TEXTVIEW)/2 + self.chatBox.textView.y - 3;
            self.chatBoxFaceView.tosSD_y = self.chatBox.bottom_sd;
            [self.view addSubview:self.chatBoxFaceView];

            [UIView animateWithDuration:0.3 animations:^{
//                if (self->_delegate && [self->_delegate respondsToSelector:@selector(chatBoxViewController:didChangeChatBoxHeight:)]) {
//                    [self chatBox:self.chatBox changeChatBoxTextViewHeight:inputHeight+chatBarheight+10];
//                    /// HEIGHT_CHATBOXVIEW = 215
//                    [self->_delegate chatBoxViewController:self didChangeChatBoxHeight:[self getTextviewHeight] + HEIGHT_CHATBOXVIEW];
//
//                }
                if (self->_delegate && [self->_delegate respondsToSelector:@selector(chatBoxViewController:didChangeChatBoxFaceTextViewHeight:withFaceHeight:)]) {
                    [self->_delegate chatBoxViewController:self didChangeChatBoxFaceTextViewHeight:self.chatBox.bottom_sd withFaceHeight:HEIGHT_CHATBOXVIEW+kBottomBarHeight];
                }
            }];
            if (self.chatBox.textView.attributedText.length > 0) {
                self.chatBoxFaceView.isHighlighted = YES;
            }
        } else {  // 表情高度变化
//            self.chatBoxFaceView.y = [self getTextviewHeight] + HEIGHT_CHATBOXVIEW;
            self.chatBoxFaceView.tosSD_y = self.chatBox.bottom_sd;
            [self.view addSubview:self.chatBoxFaceView];
            [UIView animateWithDuration:0.3 animations:^{
//                self.chatBoxFaceView.y = [self getTextviewHeight];
                self.chatBoxFaceView.tosSD_y = self.chatBox.bottom_sd;
            } completion:^(BOOL finished) {
                [self.chatBoxMoreView removeFromSuperview];
            }];
            if (self.chatBox.textView.attributedText.length > 0) {
                self.chatBoxFaceView.isHighlighted = YES;
            }
            if (fromStatus != ICChatBoxStatusShowMore) {
                [UIView animateWithDuration:0.3 animations:^{
                    if (self->_delegate && [self->_delegate respondsToSelector:@selector(chatBoxViewController:didChangeChatBoxHeight:)]) {
                        [self->_delegate chatBoxViewController:self didChangeChatBoxHeight:[self getTextviewHeight] + HEIGHT_CHATBOXVIEW];
                    }
                }];
            }
        }
        self.view.backgroundColor = [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_backgroundColor;
    } else if (toStatus == ICChatBoxStatusShowMore) {
        [self chatBoxExtendBoardChangeItem];
        if (fromStatus == ICChatBoxStatusShowVoice || fromStatus == ICChatBoxStatusShowKeyboard) {
//            self.chatBoxMoreView.y = [self getTextviewHeight] + chatBarheight;
            
            if (fromStatus == ICChatBoxStatusShowVoice) {
                CGFloat inputHeight = [self.chatBox.textView sizeThatFits:CGSizeMake(self.chatBox.textView.tosSD_width, MAXFLOAT)].height;
                if ((int)inputHeight > [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height){
                    [self.chatBox switchTextEditing];
                }
            }
            
            self.chatBoxMoreView.tosSD_y = self.chatBox.bottom_sd;
            [self.view addSubview:self.chatBoxMoreView];
            [UIView animateWithDuration:0.3 animations:^{
                if (self->_delegate && [self->_delegate respondsToSelector:@selector(chatBoxViewController:didChangeChatBoxHeight:)]) {
                    [self->_delegate chatBoxViewController:self didChangeChatBoxHeight:[self getTextviewHeight] + HEIGHT_CHATBOXVIEW];
                }
            }];
        } else {
//            self.chatBoxMoreView.y = [self getTextviewHeight] + HEIGHT_CHATBOXVIEW;
            self.chatBoxMoreView.tosSD_y = self.chatBox.bottom_sd;
            [self.view addSubview:self.chatBoxMoreView];
            [UIView animateWithDuration:0.3 animations:^{
//                self.chatBoxMoreView.y = [self getTextviewHeight];
                self.chatBoxMoreView.tosSD_y = self.chatBox.bottom_sd;
            } completion:^(BOOL finished) {
                [self.chatBoxFaceView removeFromSuperview];
            }];
            
            [UIView animateWithDuration:0.3 animations:^{
                if (self->_delegate && [self->_delegate respondsToSelector:@selector(chatBoxViewController:didChangeChatBoxHeight:)]) {
                    [self->_delegate chatBoxViewController:self didChangeChatBoxHeight:[self getTextviewHeight] + HEIGHT_CHATBOXVIEW];
                }
            }];
        }
        self.view.backgroundColor = [TOSKitCustomInfo shareCustomInfo].chatBox_more_backgroundColor;
    }
    
}


- (void)chatBox:(ICChatBox *)chatBox sendTextMessage:(NSString *)textMessage
{
    if (_delegate && [_delegate respondsToSelector:@selector(chatBoxViewController:sendTextMessage:)]) {
        [_delegate chatBoxViewController:self sendTextMessage:textMessage];
    }
    self.chatBoxFaceView.isHighlighted = NO;
}

- (void)chatBox:(ICChatBox *)chatBox sendGifImageMessage:(NSString *)gifName
{
    NSString *imagePath = [[NSBundle mainBundle] pathForResource:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,gifName] ofType:kGIFTimageType];
    NSData *imageData = [NSData dataWithContentsOfFile:imagePath];
    UIImage *image = [UIImage imageWithData:imageData];
    if (_delegate && [_delegate respondsToSelector:@selector(chatBoxViewController:sendGifImageMessage:imagePath:)]) {
        [_delegate chatBoxViewController:self sendGifImageMessage:image imagePath:imagePath];
    }
}

/**
 *  输入框高度改变
 *
 *  @param chatBox chatBox
 *  @param height  height
 */
- (void)chatBox:(ICChatBox *)chatBox changeChatBoxHeight:(CGFloat)height
{
//    CGFloat barHeight = self.barDataCount.count?CHATBOX_BAR_HEIGHT:0.0f;
//    CGFloat inputHeight = [self.chatBox.textView sizeThatFits:CGSizeMake(self.chatBox.textView.width, MAXFLOAT)].height;
    /// 这里的减3是文本框距下的高度
//    CGFloat textInputHeight = inputHeight + (HEIGHT_TABBAR - HEIGHT_TEXTVIEW)/2 + self.chatBox.textView.y - 3;
//    CGFloat textInputHeight = self.chatBox.textView.height + (HEIGHT_TABBAR - HEIGHT_TEXTVIEW)/2 + self.chatBox.textView.y - 3;
//    self.chatBoxFaceView.y = height;
    self.chatBoxFaceView.tosSD_y = self.chatBox.bottom_sd;
    self.chatBoxMoreView.tosSD_y = self.chatBox.bottom_sd;
    if (_delegate && [_delegate respondsToSelector:@selector(chatBoxViewController:didChangeChatBoxHeight:)]) {
        float h = (self.chatBox.status == ICChatBoxStatusShowFace ? HEIGHT_CHATBOXVIEW : self.keyboardFrame.size.height ) + height;
        [_delegate chatBoxViewController:self didChangeChatBoxHeight: h];
        // TODO
    }

}

/**
 *  文本输入框高度改变
 *
 *  @param chatBox chatBox
 *  @param height  height
 */
- (void)chatBox:(ICChatBox *)chatBox changeChatBoxTextViewHeight:(CGFloat)height
{
    self.chatBox.tosCF_height = height;
    
//    self.chatBoxFaceView.y = height;
//    self.chatBoxMoreView.y = height;
    
    /// 这里的减3是文本框距下的高度
//    CGFloat textInputHeight = self.chatBox.textView.height + (HEIGHT_TABBAR - HEIGHT_TEXTVIEW)/2 + self.chatBox.textView.y - 3;
    self.chatBoxFaceView.tosSD_y = self.chatBox.bottom_sd;
    self.chatBoxMoreView.tosSD_y = self.chatBox.bottom_sd;
    
    if (_delegate && [_delegate respondsToSelector:@selector(chatBoxViewController:didChangeChatBoxTextViewHeight:)]) {
//        [self.chatBox removeFromSuperview];
        TIMKitLog(@"self.chatBox.status = %d",(int)self.chatBox.status);
        float h = (self.chatBox.status == ICChatBoxStatusShowFace ? HEIGHT_CHATBOXVIEW+kBottomBarHeight : self.keyboardFrame.size.height ) + height;
        [_delegate chatBoxViewController:self didChangeChatBoxTextViewHeight: h];
    }
}

- (void)chatBoxDidStartRecordingVoice:(ICChatBox *)chatBox
{
    self.recordName = [self currentRecordFileName];
//    if ([_delegate respondsToSelector:@selector(voiceDidStartRecording)]) {
//        [_delegate voiceDidStartRecording];
//    }
    [[ICRecordManager shareManager] startRecordingWithFileName:self.recordName completion:^(NSError *error) {
        if (error) {   // 加了录音权限的判断
            NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
            NSString *app_Name = [infoDictionary objectForKey:@"CFBundleDisplayName"];
            NSString *str = [NSString stringWithFormat:@"无法录制声音 请在iPhone的“设置>%@”中打开麦克风权限",app_Name];
            
            UIAlertController *alertController = [UIAlertController alertControllerWithTitle:nil message:str preferredStyle:UIAlertControllerStyleAlert];
            
            UIAlertAction *cancelAction = [UIAlertAction actionWithTitle:@"取消" style:(UIAlertActionStyleCancel) handler:nil];
            
            UIAlertAction *setAction = [UIAlertAction actionWithTitle:@"前往设置" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
                NSURL *url = [NSURL URLWithString:UIApplicationOpenSettingsURLString];
                if ([[UIApplication sharedApplication] canOpenURL:url]) {
                    [[UIApplication sharedApplication] openURL:url];
                }
            }];
            
            [alertController addAction:cancelAction];
            [alertController addAction:setAction];
            
            [self presentViewController:alertController animated:YES completion:nil];
        } else {
            
            if ([self->_delegate respondsToSelector:@selector(voiceDidStartRecording)]) {
                [self->_delegate voiceDidStartRecording];
            }
        }
    }];
}

- (void)chatBoxDidStopRecordingVoice:(ICChatBox *)chatBox
{
    __weak typeof(self) weakSelf = self;
    [[ICRecordManager shareManager] stopRecordingWithCompletion:^(NSString *recordPath) {
        if ([recordPath isEqualToString:shortRecord]) {
            if ([weakSelf.delegate respondsToSelector:@selector(voiceRecordSoShort)]) {
                [weakSelf.delegate voiceRecordSoShort];
            }
            [[ICRecordManager shareManager] removeCurrentRecordFile:weakSelf.recordName];
        } else {    // send voice message
            if (weakSelf.delegate && [weakSelf.delegate respondsToSelector:@selector(chatBoxViewController:sendVoiceMessage:)]) {
                [weakSelf.delegate chatBoxViewController:weakSelf sendVoiceMessage:recordPath];
            }
        }
    }];
}
- (void)chatBoxDidCancelRecordingVoice:(ICChatBox *)chatBox
{
    if ([_delegate respondsToSelector:@selector(voiceDidCancelRecording)]) {
        [_delegate voiceDidCancelRecording];
    }
    [[ICRecordManager shareManager] removeCurrentRecordFile:self.recordName];
}

- (void)chatBoxDidDrag:(BOOL)inside
{
    if ([_delegate respondsToSelector:@selector(voiceWillDragout:)]) {
        [_delegate voiceWillDragout:inside];
    }
}

- (void)chatBox:(ICChatBox *)chatBox inputATSymbol:(NSString *)symbol inputRange:(NSRange)range {
    if (self.delegate && [self.delegate respondsToSelector:@selector(chatBoxViewController:inputATSymbol:inputRange:)]) {
        [self.delegate chatBoxViewController:self inputATSymbol:symbol inputRange:range];
    }
}

- (void)chatBox:(ICChatBox *)chatBox deleteATSymbol:(NSString *)symbol inputRange:(NSRange)range {
    if (self.delegate && [self.delegate respondsToSelector:@selector(chatBoxViewController:deleteATSymbol:inputRange:)]) {
        [self.delegate chatBoxViewController:self deleteATSymbol:symbol inputRange:range];
    }
}


#pragma mark - ICDocumentDelegate

- (void)selectedFileName:(NSString *)fileName
{
    if ([self.delegate respondsToSelector:@selector(chatBoxViewController:sendFileMessage:)]) {
        [self.delegate chatBoxViewController:self sendFileMessage:fileName];
    }
}

#pragma mark - getTextHeight
- (CGFloat )getTextviewHeight{
    CGFloat chatBarheight = self.barDataCount.count ? CHATBOX_BAR_HEIGHT : 0.0f;
    return self.chatBox.textView.tosCF_height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height + chatBarheight;
}

- (void)openPicker {
    dispatch_async(dispatch_get_main_queue(), ^{
        //使用TZImagePicker
        MYHTZImagePickerController *imagePickerVc = [[MYHTZImagePickerController alloc] initWithMaxImagesCount:9 columnNumber:4 delegate:self pushPhotoPickerVc:YES];
        
        imagePickerVc.barItemTextColor = [TOSKitCustomInfo shareCustomInfo].imagePicker_barItemTextColor;
        [imagePickerVc setNaviBgColor:[TOSKitCustomInfo shareCustomInfo].imagePicker_naviBgColor];
        
        [imagePickerVc setUiImagePickerControllerSettingBlock:^(UIImagePickerController *imagePickerController) {
            imagePickerController.videoQuality = UIImagePickerControllerQualityTypeHigh;
        }];
        //2. 在这里设置imagePickerVc的外观
        imagePickerVc.iconThemeColor = [UIColor colorWithRed:67 / 255.0 green:133 / 255.0 blue:255 / 255.0 alpha:1.0];
        imagePickerVc.showPhotoCannotSelectLayer = YES;
        imagePickerVc.cannotSelectLayerColor = [[UIColor whiteColor] colorWithAlphaComponent:0.8];
        __block UIColor *okButtonTitleColorNormal = imagePickerVc.oKButtonTitleColorNormal;
        [imagePickerVc setPhotoPickerPageUIConfigBlock:^(UICollectionView *collectionView, UIView *bottomToolBar, UIButton *previewButton, UIButton *originalPhotoButton, UILabel *originalPhotoLabel, UIButton *doneButton, UIImageView *numberImageView, UILabel *numberLabel, UIView *divideLine) {
            [doneButton setTitleColor:okButtonTitleColorNormal forState:UIControlStateNormal];
        }];
        // 3. 设置是否可以选择视频/图片/原图
        imagePickerVc.allowPickingVideo = YES;
        imagePickerVc.allowPickingImage = YES;
        imagePickerVc.allowPickingOriginalPhoto = YES;
        imagePickerVc.allowPickingGif = NO;
        imagePickerVc.allowTakePicture = NO;
        imagePickerVc.allowTakeVideo = NO;
        imagePickerVc.allowPickingMultipleVideo = NO; // 是否可以多选视频
        imagePickerVc.videoMaximumDuration = VIDEO_RECORDER_MAX_TIME;
        imagePickerVc.preferredLanguage = @"zh-Hans";
        
        // 4. 照片排列按修改时间升序
        imagePickerVc.sortAscendingByModificationDate = YES;
        /// 5. 单选模式,maxImagesCount为1时才生效
        imagePickerVc.showSelectBtn = NO;
        imagePickerVc.allowCrop = NO;
        imagePickerVc.needCircleCrop = NO;
        // 设置竖屏下的裁剪尺寸
        NSInteger left = 30;
        NSInteger widthHeight = self.view.tosSD_width - 2 * left;
        NSInteger top = (self.view.tosSD_width - widthHeight) / 2;
        imagePickerVc.cropRect = CGRectMake(left, top, widthHeight, widthHeight);
        imagePickerVc.scaleAspectFillCrop = YES;
        imagePickerVc.statusBarStyle = UIStatusBarStyleLightContent;
        // 设置是否显示图片序号
        imagePickerVc.showSelectedIndex = YES;
        [imagePickerVc setDidFinishPickingPhotosHandle:^(NSArray<UIImage *> *photos, NSArray *assets, BOOL isSelectOriginalPhoto) {
            
        }];
        imagePickerVc.modalPresentationStyle = UIModalPresentationCustom;
        [[kitUtils XG_GetController] presentViewController:imagePickerVc animated:YES completion:nil];
    });
}

- (void)openCamera {
    if (![ICTools hasPermissionToGetCamera]) {
        NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
        NSString *app_Name = [infoDictionary objectForKey:@"CFBundleDisplayName"];
        NSString *str = [NSString stringWithFormat:@"相机或麦克风权限未开启，请在iphone的“设置>%@”中打开相机权限",app_Name];
        
        UIAlertController *alertController = [UIAlertController alertControllerWithTitle:nil message:str preferredStyle:UIAlertControllerStyleAlert];
        
        UIAlertAction *cancelAction = [UIAlertAction actionWithTitle:@"取消" style:(UIAlertActionStyleCancel) handler:nil];
        
        UIAlertAction *setAction = [UIAlertAction actionWithTitle:@"前往设置" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
            NSURL *url = [NSURL URLWithString:UIApplicationOpenSettingsURLString];
            if ([[UIApplication sharedApplication] canOpenURL:url]) {
                [[UIApplication sharedApplication] openURL:url];
            }
        }];
        
        [alertController addAction:cancelAction];
        [alertController addAction:setAction];
        
        [self presentViewController:alertController animated:YES completion:nil];
    } else {
        TIMXFCameraController *cameraController = [TIMXFCameraController defaultCameraController];
        
        __weak TIMXFCameraController *weakCameraController = cameraController;
        
        cameraController.takePhotosCompletionBlock = ^(UIImage *image, NSError *error) {
            // 图片压缩后再上传服务器
            dispatch_async(dispatch_get_main_queue(), ^{
                // 保存路径
                NSData *imageData = UIImageJPEGRepresentation(image,1.0f);
                UIImage *simpleImg = [UIImage simpleImage:image];
                NSString *filePaht = [[ICMediaManager sharedManager] saveImage:simpleImg msgId:[kitUtils getMsgUUID] picType:[UIImage typeForImageData:imageData]];
                if (self->_delegate && [self->_delegate respondsToSelector:@selector(chatBoxViewController:sendImageMessage:imagePath:)]) {
                    [self->_delegate chatBoxViewController:self sendImageMessage:simpleImg imagePath:filePaht];
                }
                
                [weakCameraController dismissViewControllerAnimated:YES completion:nil];
            });
        };
        
        cameraController.shootCompletionBlock = ^(NSURL *videoUrl, CGFloat videoTimeLength, UIImage *thumbnailImage, NSError *error) {
            dispatch_async(dispatch_get_main_queue(), ^{
                // 保存路径
                
                NSString *currentTimeJpg = [NSString stringWithFormat:@"%@.jpg",[videoUrl.lastPathComponent stringByDeletingPathExtension] ];
                UIImage *simpleImg = [UIImage simpleImage:thumbnailImage];
                NSString * thumbnailImagePath = [[ICMediaManager sharedManager] saveVideoImage:simpleImg fileName:currentTimeJpg];
                if (self->_delegate && [self->_delegate respondsToSelector:@selector(chatBoxViewController:sendVideoMessage:duration:thumbnailImagePath:)]) {
                    [self->_delegate chatBoxViewController:self sendVideoMessage:[videoUrl absoluteString] duration:videoTimeLength thumbnailImagePath:thumbnailImagePath];
                }
                
                [weakCameraController dismissViewControllerAnimated:YES completion:nil];
            });
        };
        cameraController.modalPresentationStyle = UIModalPresentationOverFullScreen;
        [[kitUtils XG_GetController] presentViewController:cameraController animated:YES completion:nil];
    }
}

@end
