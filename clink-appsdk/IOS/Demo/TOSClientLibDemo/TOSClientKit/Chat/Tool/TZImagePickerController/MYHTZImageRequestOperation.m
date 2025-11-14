//
//  MYHTZImageRequestOperation.m
//  MYHTZImagePickerControllerFramework
//
//  Created by 侯力 on 2024/03/15.
//  Copyright © 2018 侯力. All rights reserved.
//

#import "MYHTZImageRequestOperation.h"
#import "MYHTZImageManager.h"

@implementation MYHTZImageRequestOperation

@synthesize executing = _executing;
@synthesize finished = _finished;

- (instancetype)initWithAsset:(PHAsset *)asset completion:(MYHTZImageRequestCompletedBlock)completionBlock progressHandler:(MYHTZImageRequestProgressBlock)progressHandler {
    self = [super init];
    self.asset = asset;
    self.completedBlock = completionBlock;
    self.progressBlock = progressHandler;
    _executing = NO;
    _finished = NO;
    return self;
}

- (void)start {
    self.executing = YES;
    [[MYHTZImageManager manager] getPhotoWithAsset:self.asset completion:^(UIImage *photo, NSDictionary *info, BOOL isDegraded) {
        dispatch_async(dispatch_get_main_queue(), ^{
            if (!isDegraded) {
                if (self.completedBlock) {
                    self.completedBlock(photo, info, isDegraded);
                }
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.1 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                    [self done];
                });
            }
        });
    } progressHandler:^(double progress, NSError *error, BOOL *stop, NSDictionary *info) {
        dispatch_async(dispatch_get_main_queue(), ^{
            if (self.progressBlock) {
                self.progressBlock(progress, error, stop, info);
            }
        });
    } networkAccessAllowed:YES];
}

- (void)done {
    self.finished = YES;
    self.executing = NO;
    [self reset];
}

- (void)reset {
    self.asset = nil;
    self.completedBlock = nil;
    self.progressBlock = nil;
}

- (void)setFinished:(BOOL)finished {
    [self willChangeValueForKey:@"isFinished"];
    _finished = finished;
    [self didChangeValueForKey:@"isFinished"];
}

- (void)setExecuting:(BOOL)executing {
    [self willChangeValueForKey:@"isExecuting"];
    _executing = executing;
    [self didChangeValueForKey:@"isExecuting"];
}

- (BOOL)isAsynchronous {
    return YES;
}

@end
