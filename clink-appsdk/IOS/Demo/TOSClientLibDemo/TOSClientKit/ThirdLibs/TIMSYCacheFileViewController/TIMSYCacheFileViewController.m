//
//  TIMSYCacheFileViewController.m
//  zhangshaoyu
//
//  Created by zhangshaoyu on 17/6/27.
//  Copyright © 2017年 zhangshaoyu. All rights reserved.
//

#import "TIMSYCacheFileViewController.h"
#import "TIMSYCacheFileTable.h"
#import "TIMSYCacheFileCollection.h"
#import "TIMSYCacheFileManager.h"
#import "TIMSYCacheFileModel.h"
#import "TIMSYCacheFileRead.h"
#import "TIMConstants.h"
#import "TOSCustomerChatVC.h"
#import "TIMRefreshNormalHeader.h"

@interface TIMSYCacheFileViewController ()

@property (nonatomic, strong) TIMSYCacheFileTable *cacheTable;
@property (nonatomic, strong) TIMSYCacheFileCollection *cacheCollection;

@property (nonatomic, strong) TIMSYCacheFileRead *fileRead;

@end

@implementation TIMSYCacheFileViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    self.navigationItem.title = (_cacheTitle ? _cacheTitle : TIMSYCacheFileTitle);
    
    self.view.backgroundColor = [UIColor whiteColor];
    [self setUI];
    [self loadData];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)loadView
{
    [super loadView];
    self.view.backgroundColor = [UIColor colorWithWhite:0.5 alpha:0.1];
    if ([self respondsToSelector:@selector(setEdgesForExtendedLayout:)]) {
        self.edgesForExtendedLayout = UIRectEdgeNone;
    }
}

- (void)dealloc
{
    NSLog(@"<--- %@ 被释放了 --->", [self class]);
}

- (void)viewWillDisappear:(BOOL)animated
{
    [super viewWillDisappear:animated];
    
    [[TIMSYCacheFileAudio shareAudio] stopAudio];
    if (self.fileRead) {
        [self.fileRead releaseSYCacheFileRead];
    }
}

#pragma mark - 视图

- (void)setUI
{
    if (self.showType == 0) {
        self.cacheTable.hidden = NO;
        self.cacheCollection.hidden = YES;
    } else if (self.showType == 1) {
        self.cacheTable.hidden = YES;
        self.cacheCollection.hidden = NO;
    }
}

#pragma mark - 视频图片保存

- (void)video:(NSString *)videoPath didFinishSavingWithError:(NSError *)error contextInfo:(void *)contextInfo
{
    NSString *message = @"保存视频到相册成功";
    if (error) {
        message = @"保存视频到相册失败";
    }
    UIAlertController *alertController = [UIAlertController alertControllerWithTitle:nil message:message preferredStyle:UIAlertControllerStyleAlert];
    UIAlertAction *cancelAction = [UIAlertAction actionWithTitle:@"确定" style:UIAlertActionStyleCancel handler:^(UIAlertAction * _Nonnull action) {
        
    }];
    [alertController addAction:cancelAction];
    [self presentViewController:alertController animated:YES completion:NULL];
}

- (void)image:(UIImage *)image didFinishSavingWithError:(NSError *)error contextInfo:(void *)contextInfo
{
    NSString *message = @"保存图片到相册成功";
    if (error) {
        message = @"保存图片到相册失败";
    }
    UIAlertController *alertController = [UIAlertController alertControllerWithTitle:nil message:message preferredStyle:UIAlertControllerStyleAlert];
    UIAlertAction *cancelAction = [UIAlertAction actionWithTitle:@"确定" style:UIAlertActionStyleCancel handler:^(UIAlertAction * _Nonnull action) {
        
    }];
    [alertController addAction:cancelAction];
    [self presentViewController:alertController animated:YES completion:NULL];
}

#pragma mark - 响应

#pragma mark - 数据 

- (void)loadData
{
    if (self.cacheArray == nil) {
        @WeakObj(self);
        self.cacheTable.tos_header = [TIMRefreshNormalHeader headerWithRefreshingBlock:^{
            @StrongObj(self);
            dispatch_async(dispatch_get_global_queue(0, 0), ^{
                @StrongObj(self);
                // 初始化，首次显示总目录
                NSString *path = [TIMSYCacheFileManager homeDirectoryPath];
                NSArray *array = [[TIMSYCacheFileManager shareManager] fileModelsWithFilePath:path];
                self.cacheArray = [NSMutableArray arrayWithArray:array];
                dispatch_async(dispatch_get_main_queue(), ^{
                    @StrongObj(self);
                    self.cacheTable.cacheDatas = self.cacheArray;
                    [self.cacheTable reloadData];
                    [self.cacheTable.tos_header endRefreshing];
                });
            });
        }];
        [self.cacheTable.tos_header beginRefreshing];
    } else {
        [self loadUI];
    }
}

- (void)loadUI {
    if (self.cacheArray && 0 < self.cacheArray.count) {
//        self.cacheTable.cacheDatas = self.cacheArray;
//        [self.cacheTable reloadData];
        if (self.showType == 0) {
            self.cacheTable.cacheDatas = self.cacheArray;
            [self.cacheTable reloadData];
        } else if (self.showType == 1) {
            self.cacheCollection.cacheDatas = self.cacheArray;
            [self.cacheCollection reloadData];
        }
    }
}

#pragma mark - getter

- (TIMSYCacheFileTable *)cacheTable
{
    if (_cacheTable == nil) {
        _cacheTable = [[TIMSYCacheFileTable alloc] initWithFrame:self.view.bounds style:UITableViewStylePlain];
        _cacheTable.backgroundColor = [UIColor clearColor];
        _cacheTable.autoresizingMask = UIViewAutoresizingFlexibleHeight | UIViewAutoresizingFlexibleWidth;
        
        [self.view addSubview:_cacheTable];
        typeof(self) __weak weakSelf = self;
        // 点击
        _cacheTable.itemClick = ^(NSIndexPath *indexPath) {
            
            typeof(weakSelf) __strong strongSelf = weakSelf;
            if (strongSelf.cacheTable.isEditing) {
                return ;
            }
            
            if (indexPath.row > strongSelf.cacheArray.count - 1) {
                return;
            }
            TIMSYCacheFileModel *model = strongSelf.cacheArray[indexPath.row];
            NSString *path = model.filePath; // 路径
            TIMSYCacheFileType type = model.fileType; // 类型
            
            
            if (TIMSYCacheFileTypeUnknow == type) {
                // 标题
                NSString *title = model.fileName;
                // 子目录文件
                NSArray *files = [[TIMSYCacheFileManager shareManager] fileModelsWithFilePath:path];
                TIMSYCacheFileViewController *cacheVC = [TIMSYCacheFileViewController new];
                cacheVC.cacheTitle = title;
                cacheVC.showType = weakSelf.showType;
                cacheVC.cacheArray = (NSMutableArray *)files;
                [weakSelf.navigationController pushViewController:cacheVC animated:YES];
            } else {
                
                __block TOSCustomerChatVC *vc;
                [weakSelf.navigationController.viewControllers enumerateObjectsUsingBlock:^(__kindof UIViewController * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    if ([obj isKindOfClass:[TOSCustomerChatVC class]]) {
                        vc = obj;
                        *stop = YES;
                    }
                }];
                if (vc) {
                    [weakSelf.navigationController popToViewController:vc animated:YES];
                    [[NSNotificationCenter defaultCenter] postNotificationName:kSendFileMessageNotification object:model];
                    return;
                }
//                if (type == SYCacheFileTypeImage) {
//                    [SYCacheFileManager shareManager].nameImage = model.filePath;
//                }
//                [weakSelf.fileRead fileReadWithFilePath:path target:weakSelf];
            }
        };
        // 长按
        _cacheTable.longPress = ^(TIMSYCacheFileTable *tableView, NSIndexPath *indexPath) {
            if (indexPath.row > weakSelf.cacheArray.count - 1) {
                return;
            }
            //
            TIMSYCacheFileModel *model = weakSelf.cacheArray[indexPath.row];
            TIMSYCacheFileType type = model.fileType;
            //
            UIAlertController *alertController = [UIAlertController alertControllerWithTitle:nil message:nil preferredStyle:UIAlertControllerStyleActionSheet];
            UIAlertAction *cancelAction = [UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleCancel handler:NULL];
            [alertController addAction:cancelAction];
            if (type == TIMSYCacheFileTypeVideo || type == TIMSYCacheFileTypeImage) {
                UIAlertAction *cacheAction = [UIAlertAction actionWithTitle:@"保存到相册" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
                    if (type == TIMSYCacheFileTypeVideo) {
                        UISaveVideoAtPathToSavedPhotosAlbum(model.filePath, weakSelf, @selector(video:didFinishSavingWithError: contextInfo:), nil);
                    } else if (type == TIMSYCacheFileTypeImage) {
                        UIImage *image = [UIImage imageWithContentsOfFile:model.filePath];
                        UIImageWriteToSavedPhotosAlbum(image, weakSelf, @selector(image:didFinishSavingWithError:contextInfo:), nil);
                    }
                }];
                [alertController addAction:cacheAction];
            }
            UIAlertAction *deleteAction = [UIAlertAction actionWithTitle:@"删除" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
                [tableView deleItemAtIndex:indexPath];
            }];
            [alertController addAction:deleteAction];
            [weakSelf presentViewController:alertController animated:YES completion:NULL];
        };
    }
    return _cacheTable;
}

- (TIMSYCacheFileCollection *)cacheCollection
{
    if (_cacheCollection == nil) {
        UICollectionViewLayout *layout = TIMSYCacheFileCollection.collectionlayout;
        _cacheCollection = [[TIMSYCacheFileCollection alloc] initWithFrame:self.view.bounds collectionViewLayout:layout];
        _cacheCollection.backgroundColor = [UIColor clearColor];
        _cacheCollection.autoresizingMask = UIViewAutoresizingFlexibleHeight | UIViewAutoresizingFlexibleWidth;
        
        [self.view addSubview:_cacheCollection];
        typeof(self) __weak weakSelf = self;
        // 点击
        _cacheCollection.itemClick = ^(NSIndexPath *indexPath) {
            
            if (weakSelf.cacheTable.isEditing) {
                return ;
            }
            
            if (indexPath.row > weakSelf.cacheArray.count - 1) {
                return;
            }
            TIMSYCacheFileModel *model = weakSelf.cacheArray[indexPath.row];
            NSString *path = model.filePath; // 路径
            TIMSYCacheFileType type = model.fileType; // 类型
            if (TIMSYCacheFileTypeUnknow == type) {
                // 标题
                NSString *title = model.fileName;
                // 子目录文件
                NSArray *files = [[TIMSYCacheFileManager shareManager] fileModelsWithFilePath:path];
                TIMSYCacheFileViewController *cacheVC = [TIMSYCacheFileViewController new];
                cacheVC.cacheTitle = title;
                cacheVC.showType = weakSelf.showType;
                cacheVC.cacheArray = (NSMutableArray *)files;
                [weakSelf.navigationController pushViewController:cacheVC animated:YES];
            } else {
                if (type == TIMSYCacheFileTypeImage) {
                    [TIMSYCacheFileManager shareManager].nameImage = model.filePath;
                }
                [weakSelf.fileRead fileReadWithFilePath:path target:weakSelf];
            }
        };
        // 长按
        _cacheCollection.longPress = ^(TIMSYCacheFileCollection *collection, NSIndexPath *indexPath) {
            if (indexPath.row > weakSelf.cacheArray.count - 1) {
                return;
            }
            //
            TIMSYCacheFileModel *model = weakSelf.cacheArray[indexPath.row];
            TIMSYCacheFileType type = model.fileType;
            //
            UIAlertController *alertController = [UIAlertController alertControllerWithTitle:nil message:nil preferredStyle:UIAlertControllerStyleActionSheet];
            UIAlertAction *cancelAction = [UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleCancel handler:NULL];
            [alertController addAction:cancelAction];
            if (type == TIMSYCacheFileTypeVideo || type == TIMSYCacheFileTypeImage) {
                UIAlertAction *cacheAction = [UIAlertAction actionWithTitle:@"保存到相册" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
                    if (type == TIMSYCacheFileTypeVideo) {
                        UISaveVideoAtPathToSavedPhotosAlbum(model.filePath, weakSelf, @selector(video:didFinishSavingWithError: contextInfo:), nil);
                    } else if (type == TIMSYCacheFileTypeImage) {
                        UIImage *image = [UIImage imageWithContentsOfFile:model.filePath];
                        UIImageWriteToSavedPhotosAlbum(image, weakSelf, @selector(image:didFinishSavingWithError:contextInfo:), nil);
                    }
                }];
                [alertController addAction:cacheAction];
            }
            UIAlertAction *deleteAction = [UIAlertAction actionWithTitle:@"删除" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
                [collection deleItemAtIndex:indexPath];
            }];
            [alertController addAction:deleteAction];
            [weakSelf presentViewController:alertController animated:YES completion:NULL];
        };
    }
    return _cacheCollection;
}

- (TIMSYCacheFileRead *)fileRead
{
    if (_fileRead == nil) {
        _fileRead = [[TIMSYCacheFileRead alloc] init];
    }
    return _fileRead;
}

@end
