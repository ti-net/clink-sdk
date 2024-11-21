//
//  MYHTZImageRequestOperation.h
//  MYHTZImagePickerControllerFramework
//
//  Created by 侯力 on 2024/03/15.
//  Copyright © 2018 侯力. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <Photos/Photos.h>

NS_ASSUME_NONNULL_BEGIN

@interface MYHTZImageRequestOperation : NSOperation

typedef void(^MYHTZImageRequestCompletedBlock)(UIImage *photo, NSDictionary *info, BOOL isDegraded);
typedef void(^MYHTZImageRequestProgressBlock)(double progress, NSError *error, BOOL *stop, NSDictionary *info);

@property (nonatomic, copy, nullable) MYHTZImageRequestCompletedBlock completedBlock;
@property (nonatomic, copy, nullable) MYHTZImageRequestProgressBlock progressBlock;
@property (nonatomic, strong, nullable) PHAsset *asset;

@property (assign, nonatomic, getter = isExecuting) BOOL executing;
@property (assign, nonatomic, getter = isFinished) BOOL finished;

- (instancetype)initWithAsset:(PHAsset *)asset completion:(MYHTZImageRequestCompletedBlock)completionBlock progressHandler:(MYHTZImageRequestProgressBlock)progressHandler;
- (void)done;
@end

NS_ASSUME_NONNULL_END
