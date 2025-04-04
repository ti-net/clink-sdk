//
//  SLEditImageController.m
//  DarkMode
//
//  Created by wsl on 2019/10/31.
//  Copyright © 2019 wsl. All rights reserved.
//

#import "SLEditImageController.h"
#import <Photos/Photos.h>
#import "UIView+SLImage.h"
#import "SLBlurView.h"
#import "SLEditMenuView.h"
#import "SLEditSelectedBox.h"
#import "SLImage.h"
#import "SLImageView.h"
#import "SLDrawView.h"
#import "SLEditTextView.h"
#import "SLMosaicView.h"
#import "UIImage+SLCommon.h"
#import "SLImageZoomView.h"
#import "SLImageClipController.h"
#import "TIMConstants.h"
#import "UIButton+TIMEnlargeEdge.h"
#import "TIMXFPhotoLibraryManager.h"

@interface SLEditImageController ()<UIGestureRecognizerDelegate, SLImageZoomViewDelegate>
{
    ReturnImage _returnImage;
}
@property (nonatomic, strong) SLImageZoomView *zoomView; // 预览视图 展示编辑的图片 可以缩放

@property (nonatomic, strong) SLBlurView *againShotBtn;  // 再拍一次
@property (nonatomic, strong) UIButton *saveAlbumBtn;  //保存到相册

@property (nonatomic, strong) SLEditMenuView *editMenuView; //编辑菜单栏

@property (nonatomic, strong) SLDrawView *drawView; // 涂鸦视图
@property (nonatomic, strong) NSMutableArray *watermarkArray; // 水印层 所有的贴图和文本
@property (nonatomic, strong) SLEditSelectedBox *selectedBox; //水印选中框
@property (nonatomic, strong) UIButton *deleteBtn;
@property (nonatomic, strong) SLMosaicView *mosaicView; //马赛克画板

@property (nonatomic, assign) SLEditMenuType editingMenuType; //当前正在编辑的菜单类型

@end

@implementation SLEditImageController

#pragma mark - Override
- (void)viewDidLoad {
    [super viewDidLoad];
    [self setupUI];
}
- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
}
- (BOOL)prefersStatusBarHidden {
    return YES;
}
- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    NSLog(@"图片编辑视图释放了");
}

#pragma mark - UI
- (void)setupUI {
    self.view.backgroundColor = [UIColor blackColor];
    [self.view addSubview:self.zoomView];
    self.zoomView.pinchGestureRecognizer.enabled = YES;
    self.zoomView.image = self.image;
    if (self.image.size.width > 0) {
       self.zoomView.imageView.frame = CGRectMake(0, 0, self.zoomView.sl_width, self.zoomView.sl_width * self.image.size.height/self.image.size.width);
    }
    if (self.zoomView.imageView.sl_height <= self.zoomView.sl_height) {
        self.zoomView.imageView.center = CGPointMake(self.zoomView.sl_width/2.0, self.zoomView.sl_height/2.0);
    }
    
    //添加裁剪完成监听
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(imageClippingComplete:) name:@"sl_ImageClippingComplete" object:nil];
    
    [self hiddenEditMenus:NO];
    
    [self.view addSubview:self.againShotBtn];
    [self.view addSubview:self.saveAlbumBtn];
}

#pragma mark - HelpMethods
// 添加拖拽、缩放、旋转、单击、双击手势
- (void)addRotateAndPinchGestureRecognizer:(UIView *)view {
    //单击手势选中
    UITapGestureRecognizer *singleTap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(singleTapAction:)];
    singleTap.numberOfTapsRequired = 1;
    singleTap.numberOfTouchesRequired = 1;
    [view addGestureRecognizer:singleTap];
    if ([view isKindOfClass:[UILabel class]]) {
        UITapGestureRecognizer *doubleTap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(doubleTapAction:)];
        doubleTap.numberOfTapsRequired = 2;
        doubleTap.numberOfTouchesRequired = 1;
        [singleTap requireGestureRecognizerToFail:doubleTap];
        [view addGestureRecognizer:doubleTap];
    }
    //拖拽手势
    UIPanGestureRecognizer *pan = [[UIPanGestureRecognizer alloc] initWithTarget:self action:@selector(dragAction:)];
    pan.minimumNumberOfTouches = 1;
    [view addGestureRecognizer:pan];
    //缩放手势
    UIPinchGestureRecognizer *pinchGestureRecognizer = [[UIPinchGestureRecognizer alloc] initWithTarget:self
                                                                                                 action:@selector(pinchAction:)];
    pinchGestureRecognizer.delegate = self;
    [view addGestureRecognizer:pinchGestureRecognizer];
    //旋转手势
    UIRotationGestureRecognizer *rotateRecognizer = [[UIRotationGestureRecognizer alloc] initWithTarget:self
                                                                                                 action:@selector(rotateAction:)];
    [view addGestureRecognizer:rotateRecognizer];
    rotateRecognizer.delegate = self;
}

//置顶视图
- (void)topSelectedView:(UIView *)topView {
    [self.zoomView.imageView bringSubviewToFront:topView];
    [self.watermarkArray removeObject:topView];
    [self.watermarkArray addObject:topView];
    [SLDelayPerform sl_cancelDelayPerform]; //取消延迟执行
    self.selectedBox.frame = topView.bounds;
    [topView addSubview:self.selectedBox];
    UIImage *image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"SLEditImage_deleteTextBtn"]];
    self.deleteBtn.frame = CGRectMake(self.selectedBox.sl_width - image.size.width/2, -image.size.height/2, image.size.width, image.size.height);
    [topView addSubview:self.deleteBtn];
    [self.deleteBtn setEnlargeEdge:5.f];
}

// 隐藏编辑时菜单按钮
- (void)hiddenEditMenus:(BOOL)isHidden {
    self.editMenuView.hidden = isHidden;
}

#pragma mark - Setter
- (void)setEditingMenuType:(SLEditMenuType)editingMenuType {
    _editingMenuType = editingMenuType;
    switch (_editingMenuType) {
        case SLEditMenuTypeUnknown:
            self.zoomView.scrollEnabled = YES;
            self.zoomView.pinchGestureRecognizer.enabled = YES;
            break;
        case SLEditMenuTypeGraffiti:
            self.zoomView.pinchGestureRecognizer.enabled = YES;
            self.zoomView.scrollEnabled = NO;
            break;
        case SLEditMenuTypeText:
            self.zoomView.scrollEnabled = YES;
            self.zoomView.pinchGestureRecognizer.enabled = NO;
            break;
        case SLEditMenuTypeSticking:
            self.zoomView.scrollEnabled = YES;
            self.zoomView.pinchGestureRecognizer.enabled = NO;
            break;
        case SLEditMenuTypePictureMosaic:
            self.zoomView.scrollEnabled = NO;
            self.zoomView.pinchGestureRecognizer.enabled = YES;
            break;
        case SLEditMenuTypePictureClipping:
            self.zoomView.scrollEnabled = YES;
            self.zoomView.pinchGestureRecognizer.enabled = YES;
            break;
        default:
            break;
    }
}

#pragma mark - Getter
- (SLImageZoomView *)zoomView {
    if (_zoomView == nil) {
        _zoomView = [[SLImageZoomView alloc] initWithFrame:self.view.bounds];
        _zoomView.backgroundColor = [UIColor blackColor];
        _zoomView.userInteractionEnabled = YES;
        _zoomView.maximumZoomScale = 4;
        _zoomView.zoomViewDelegate = self;
    }
    return _zoomView;
}

- (SLBlurView *)againShotBtn {
    if (_againShotBtn == nil) {
        UIImage *image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"SLEditImage_editImage_againShoot"]];
        _againShotBtn = [[SLBlurView alloc] initWithFrame:CGRectMake(19.f, kStatusBarHeight + 16.f, image.size.width, image.size.height)];
        _againShotBtn.blurView.hidden = YES;
        UIButton * btn = [[UIButton alloc] initWithFrame:_againShotBtn.bounds];
        [btn setImage:image forState:UIControlStateNormal];
        [btn addTarget:self action:@selector(againShotBtnClicked:) forControlEvents:UIControlEventTouchUpInside];
        [_againShotBtn addSubview:btn];
    }
    return _againShotBtn;
}

- (UIButton *)saveAlbumBtn {
    if (_saveAlbumBtn == nil) {
        _saveAlbumBtn = [[UIButton alloc] initWithFrame:CGRectMake(0, 0, 56.f, 32.f)];
        _saveAlbumBtn.sl_right = SL_kScreenWidth - 17.f;
        _saveAlbumBtn.sl_bottom = SL_kScreenHeight - 25.f;
        _saveAlbumBtn.layer.cornerRadius = 2.f;
        _saveAlbumBtn.layer.masksToBounds = YES;
        _saveAlbumBtn.backgroundColor = TOSHexColor(0x1C69D4);
        _saveAlbumBtn.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Medium" size:15.f];
        [_saveAlbumBtn setTitle:@"发送" forState:(UIControlStateNormal)];
        [_saveAlbumBtn addTarget:self action:@selector(saveAlbumBtnClicked:) forControlEvents:UIControlEventTouchUpInside];
    }
    return _saveAlbumBtn;
}

- (SLEditMenuView *)editMenuView {
    if (!_editMenuView) {
        _editMenuView = [[SLEditMenuView alloc] initWithFrame:CGRectMake(0.f, self.view.sl_height - 105.f, SL_kScreenWidth,  105.f)];
        _editMenuView.hidden = YES;
        __weak typeof(self) weakSelf = self;
        _editMenuView.editObject = SLEditObjectPicture;
        _editMenuView.selectEditMenu = ^(SLEditMenuType editMenuType, NSDictionary * _Nullable setting) {
            weakSelf.editingMenuType = ![setting[@"hidden"] boolValue] ? editMenuType : SLEditMenuTypeUnknown;
            if (editMenuType == SLEditMenuTypeGraffiti) {
                weakSelf.drawView.userInteractionEnabled = ![setting[@"hidden"] boolValue];
                if ([setting[@"hidden"] boolValue]) weakSelf.editingMenuType = SLEditMenuTypeUnknown;
                [weakSelf.zoomView.imageView insertSubview:weakSelf.drawView atIndex:([weakSelf.zoomView.imageView.subviews containsObject:weakSelf.mosaicView] ? 1: 0)];
                if (setting[@"lineColor"]) {
                    weakSelf.drawView.lineColor = setting[@"lineColor"];
                }
                if (setting[@"goBack"]) {
                    [weakSelf.drawView goBack];
                }
            }else {
                weakSelf.drawView.userInteractionEnabled = NO;
            }
            if (editMenuType == SLEditMenuTypeText) {
                SLEditTextView *editTextView = [[SLEditTextView alloc] initWithFrame:CGRectMake(0, 0, SL_kScreenWidth, SL_kScreenHeight)];
                [weakSelf.view addSubview:editTextView];
                if ([setting[@"hidden"] boolValue]) weakSelf.editingMenuType = SLEditMenuTypeUnknown;
                editTextView.editTextCompleted = ^(UILabel * _Nullable label) {
                    if (label.text.length == 0 || label == nil) {
                        return;
                    }
                    CGRect imageRect = [weakSelf.zoomView convertRect:weakSelf.zoomView.imageView.frame toView:weakSelf.view];
                    CGPoint center = CGPointZero;
                    center.x = fabs(imageRect.origin.x)+weakSelf.zoomView.sl_width/2.0;
                    center.y = 0;
                    if (imageRect.origin.y >= 0 && imageRect.size.height <= weakSelf.zoomView.sl_height) {
                        center.y = imageRect.size.height/2.0;
                    }else {
                        center.y = fabs(imageRect.origin.y) + weakSelf.zoomView.sl_height/2.0;
                    }
                    label.transform = CGAffineTransformMakeScale(1/weakSelf.zoomView.zoomScale, 1/weakSelf.zoomView.zoomScale);
                    center = CGPointMake(center.x/weakSelf.zoomView.zoomScale, center.y/weakSelf.zoomView.zoomScale);
                    label.center = center;
                    [weakSelf.zoomView.imageView addSubview:label];
                    [weakSelf.watermarkArray addObject:label];
                    [weakSelf addRotateAndPinchGestureRecognizer:label];
                    [weakSelf topSelectedView:label];
                    [SLDelayPerform sl_startDelayPerform:^{
                        [weakSelf.selectedBox removeFromSuperview];
                        [weakSelf.deleteBtn removeFromSuperview];
                    } afterDelay:1.0];
                };
            }
            if (editMenuType == SLEditMenuTypePictureClipping) {
                SLImageClipController *imageClipController = [[SLImageClipController alloc] init];
                imageClipController.modalPresentationStyle = UIModalPresentationFullScreen;
                [weakSelf.selectedBox removeFromSuperview];
                [weakSelf.deleteBtn removeFromSuperview];
                UIImage *image = [weakSelf.zoomView.imageView sl_imageByViewInRect:weakSelf.zoomView.imageView.bounds];
                imageClipController.image = image;
                [weakSelf presentViewController:imageClipController animated:NO completion:nil];
            }
        };
        [self.view insertSubview:_editMenuView atIndex:1];
    }
    return _editMenuView;
}

- (SLDrawView *)drawView {
    if (!_drawView) {
        _drawView = [[SLDrawView alloc] initWithFrame:self.zoomView.imageView.bounds];
        _drawView.backgroundColor = [UIColor clearColor];
        _drawView.lineWidth = 5.0;
        __weak typeof(self) weakSelf = self;
        _drawView.drawBegan = ^{
            [weakSelf hiddenEditMenus:YES];
        };
        _drawView.drawEnded = ^{
            [weakSelf hiddenEditMenus:NO];
        };
    }
    return _drawView;
}

- (NSMutableArray *)watermarkArray {
    if (!_watermarkArray) {
        _watermarkArray = [NSMutableArray array];
    }
    return _watermarkArray;
}

- (SLEditSelectedBox *)selectedBox {
    if (!_selectedBox) {
        _selectedBox = [[SLEditSelectedBox alloc] init];
    }
    return _selectedBox;
}

- (SLMosaicView *)mosaicView {
    if (!_mosaicView) {
        _mosaicView = [[SLMosaicView alloc] initWithFrame:self.zoomView.imageView.bounds];
        __weak typeof(self) weakSelf = self;
        _mosaicView.squareWidth = 15;
        _mosaicView.paintSize = CGSizeMake(40, 40);
        _mosaicView.brushColor = ^UIColor *(CGPoint point) {
            point.x = point.x/weakSelf.view.frame.size.width*weakSelf.zoomView.image.size.width;
            point.y = point.y/weakSelf.view.frame.size.height*weakSelf.zoomView.image.size.height;
            point.x = point.x/self.zoomView.zoomScale;
            point.y = point.y/self.zoomView.zoomScale;
            return [weakSelf.zoomView.image sl_colorAtPixel:point];
        };
        _mosaicView.brushBegan = ^{
            [weakSelf hiddenEditMenus:YES];
        };
        _mosaicView.brushEnded = ^{
            [weakSelf hiddenEditMenus:NO];
        };
        _mosaicView.userInteractionEnabled = YES;
    }
    return _mosaicView;
}

- (UIButton *)deleteBtn {
    if (!_deleteBtn) {
        _deleteBtn = [UIButton buttonWithType:(UIButtonTypeCustom)];
        UIImage *image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"SLEditImage_deleteTextBtn"]];
        [_deleteBtn setImage:image forState:(UIControlStateNormal)];
        [_deleteBtn addTarget:self action:@selector(didClickDeleteBtnAction:) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _deleteBtn;
}

- (void)didClickDeleteBtnAction:(UIButton *)sender {
    [sender.superview  removeFromSuperview];
    [self.watermarkArray removeObject:(SLImageView *)sender.superview];
}

#pragma mark - Events Handle
//再试一次 继续拍摄
- (void)againShotBtnClicked:(id)sender {
    [self dismissViewControllerAnimated:NO completion:nil];
}

//保存到相册
- (void)saveAlbumBtnClicked:(id)sender {
    [self.selectedBox removeFromSuperview];
    [self.deleteBtn removeFromSuperview];
    self.image = [self.zoomView.imageView sl_imageByViewInRect:self.zoomView.imageView.bounds];
    if(self.image && _returnImage) {
//        UIImageWriteToSavedPhotosAlbum(self.image, self, @selector(savedPhotoImage:didFinishSavingWithError:contextInfo:), nil);
        __weak typeof(self) weakSelf = self;
        [self dismissViewControllerAnimated:NO completion:^{
            __strong typeof(weakSelf) strongSelf = weakSelf;
            strongSelf->_returnImage(strongSelf.image);
        }];
    }
}

//保存图片完成后调用的方法
- (void)savedPhotoImage:(UIImage*)image didFinishSavingWithError:(NSError *)error contextInfo: (void *)contextInfo {
    SL_DISPATCH_ON_MAIN_THREAD(^{
        [self againShotBtnClicked:nil];
    });
    if (error) {
        NSLog(@"保存图片出错%@", error.localizedDescription);
    } else {
        NSLog(@"保存图片成功");
    }
}

// 点击水印视图
- (void)singleTapAction:(UITapGestureRecognizer *)singleTap {
    [self topSelectedView:singleTap.view];
    if (singleTap.state == UIGestureRecognizerStateFailed || singleTap.state == UIGestureRecognizerStateEnded) {
        [SLDelayPerform sl_startDelayPerform:^{
            [self.selectedBox removeFromSuperview];
            [self.deleteBtn removeFromSuperview];
        } afterDelay:1.0];
    }
}

//双击 文本水印 开始编辑文本
- (void)doubleTapAction:(UITapGestureRecognizer *)doubleTap {
    [self topSelectedView:doubleTap.view];
    doubleTap.view.hidden = YES;
    UILabel *tapLabel = (UILabel *)doubleTap.view;
    SLEditTextView *editTextView = [[SLEditTextView alloc] initWithFrame:CGRectMake(0, 0, SL_kScreenWidth, SL_kScreenHeight)];
    editTextView.configureEditParameters(@{@"textColor":tapLabel.textColor, @"backgroundColor":tapLabel.backgroundColor, @"text":tapLabel.text});
    editTextView.editTextCompleted = ^(UILabel * _Nullable label) {
        doubleTap.view.hidden = NO;
        if (label == nil) {
            return;
        }
        label.transform = tapLabel.transform;
        label.center = tapLabel.center;
        [tapLabel removeFromSuperview];
        [self.watermarkArray removeObject:tapLabel];
        [self.watermarkArray addObject:label];
        [self.zoomView.imageView addSubview:label];
        [self addRotateAndPinchGestureRecognizer:label];
        [self topSelectedView:label];
        [SLDelayPerform sl_startDelayPerform:^{
            [self.selectedBox removeFromSuperview];
            [self.deleteBtn removeFromSuperview];
        } afterDelay:1.0];
    };
    [self.view addSubview:editTextView];
}

// 拖拽 水印视图
- (void)dragAction:(UIPanGestureRecognizer *)pan {
    // 返回的是相对于最原始的手指的偏移量
    CGPoint transP = [pan translationInView:self.zoomView.imageView];
    if (pan.state == UIGestureRecognizerStateBegan) {
        self.zoomView.imageView.clipsToBounds = NO;
        [self hiddenEditMenus:YES];
        [self topSelectedView:pan.view];
    } else if (pan.state == UIGestureRecognizerStateChanged ) {
        pan.view.center = CGPointMake(pan.view.center.x + transP.x, pan.view.center.y + transP.y);
        [pan setTranslation:CGPointZero inView:self.zoomView.imageView];
    } else if (pan.state == UIGestureRecognizerStateFailed || pan.state == UIGestureRecognizerStateEnded) {
        [self hiddenEditMenus:NO];
        self.zoomView.imageView.clipsToBounds = YES;
        //获取拖拽的视图在屏幕上的位置
        CGRect rect = [pan.view convertRect: pan.view.bounds toView:self.view];
        CGRect imageRect = [self.zoomView convertRect:self.zoomView.imageView.frame toView:self.view];
        
        CGFloat rectX = rect.origin.x;
        CGFloat rectY = rect.origin.y;
        
        CGFloat imageRectX = imageRect.origin.x;
        CGFloat imageRectY = imageRect.origin.y;
        
        //下方区域
        CGFloat bottom = MAX(self.editMenuView.sl_height, SL_kScreenHeight - imageRectY - imageRect.size.height);
        //右方区域
        CGFloat right = SL_kScreenWidth - imageRectX - imageRect.size.width;
        
        if (!CGRectIntersectsRect(imageRect, rect) ||
            rectX <=  -rect.size.width * 2/3 + imageRectX ||
            rectY <= -rect.size.height/2 + imageRectY ||
            rectX >= imageRectX + imageRect.size.width - right + imageRectX - rect.size.width * 2/3 ||
            rectY >= imageRectY + imageRect.size.height - bottom + imageRectY - rect.size.height / 2) {
            //如果出了父视图zoomView的范围，则置于父视图中心
            CGPoint center = CGPointZero;
            center.x = fabs(imageRectX)+self.zoomView.sl_width/2.0;
            center.y = 0;
            if (imageRectY >= 0 && imageRect.size.height <= self.zoomView.sl_height) {
                center.y = imageRect.size.height/2.0;
            }else {
                center.y = fabs(imageRectY) + self.zoomView.sl_height/2.0;
            }
            center = CGPointMake(center.x/self.zoomView.zoomScale, center.y/self.zoomView.zoomScale);
            pan.view.center = center;
        }
        [SLDelayPerform sl_startDelayPerform:^{
            [self.selectedBox removeFromSuperview];
            [self.deleteBtn removeFromSuperview];
        } afterDelay:1.0];
    }
}
//缩放 水印视图
- (void)pinchAction:(UIPinchGestureRecognizer *)pinch {
    if (pinch.state == UIGestureRecognizerStateBegan) {
        [self topSelectedView:pinch.view];
        self.zoomView.pinchGestureRecognizer.enabled = NO;
        self.zoomView.imageView.clipsToBounds = NO;
    }else if (pinch.state == UIGestureRecognizerStateFailed || pinch.state == UIGestureRecognizerStateEnded){
        [SLDelayPerform sl_startDelayPerform:^{
            [self.selectedBox removeFromSuperview];
            [self.deleteBtn removeFromSuperview];
        } afterDelay:1.0];
        self.zoomView.pinchGestureRecognizer.enabled = YES;
        self.zoomView.imageView.clipsToBounds = YES;
    }
    pinch.view.transform = CGAffineTransformScale(pinch.view.transform, pinch.scale, pinch.scale);
    pinch.scale = 1.0;
}
//旋转 水印视图 注意：旋转之后的frame会变！！！
- (void)rotateAction:(UIRotationGestureRecognizer *)rotation {
    if (rotation.state == UIGestureRecognizerStateBegan) {
        [self topSelectedView:rotation.view];
    }else if (rotation.state == UIGestureRecognizerStateFailed || rotation.state == UIGestureRecognizerStateEnded){
        [SLDelayPerform sl_startDelayPerform:^{
            [self.selectedBox removeFromSuperview];
            [self.deleteBtn removeFromSuperview];
        } afterDelay:1.0];
    }
    rotation.view.transform = CGAffineTransformRotate(rotation.view.transform, rotation.rotation);
    // 将旋转的弧度清零(注意不是将图片旋转的弧度清零, 而是将当前手指旋转的弧度清零)
    rotation.rotation = 0;
}
// 图片裁剪完成
- (void)imageClippingComplete:(NSNotification *)notification {
    UIImage *clipImage = notification.userInfo[@"image"];
    self.zoomView.zoomScale = 1;
    self.zoomView.image = clipImage;
    self.zoomView.imageView.frame = CGRectMake(0, 0, self.zoomView.sl_width, self.zoomView.sl_width * clipImage.size.height/clipImage.size.width);
    if (self.zoomView.imageView.sl_height <= self.zoomView.sl_height) {
        self.zoomView.imageView.center = CGPointMake(self.zoomView.sl_width/2.0, self.zoomView.sl_height/2.0);
    }
    self.zoomView.contentSize = CGSizeMake(self.zoomView.imageView.sl_width, self.zoomView.imageView.sl_height);
    
    _drawView.frame = self.zoomView.imageView.bounds;
    _mosaicView.frame = self.zoomView.imageView.bounds;
    [_drawView clear];
    [_mosaicView clear];
    for (UIView *view in self.watermarkArray) {
        [view removeFromSuperview];
    }
    [self.watermarkArray removeAllObjects];
}
#pragma mark - UIGestureRecognizerDelegate
// 该方法返回的BOOL值决定了view是否能够同时响应多个手势
- (BOOL)gestureRecognizer:(UIGestureRecognizer *)gestureRecognizer shouldRecognizeSimultaneouslyWithGestureRecognizer:(UIGestureRecognizer *)otherGestureRecognizer {
    //     NSLog(@"%@ - %@", gestureRecognizer.class, otherGestureRecognizer.class);
    return YES;
}

#pragma mark - SLZoomViewDelegate
- (void)zoomViewDidEndMoveImage:(SLImageZoomView *)zoomView {
    self.drawView.lineWidth = 5.0/self.zoomView.zoomScale;
    self.mosaicView.squareWidth = 15/self.zoomView.zoomScale;
    self.mosaicView.paintSize = CGSizeMake(40/self.zoomView.zoomScale, 40/self.zoomView.zoomScale);
}

- (void)setReturnImage:(ReturnImage)returnImage {
    _returnImage = returnImage;
}

@end
