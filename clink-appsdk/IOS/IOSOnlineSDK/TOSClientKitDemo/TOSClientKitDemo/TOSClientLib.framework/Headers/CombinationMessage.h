//
//  CombinationMessage.h
//  TOSClientLib
//
//  Created by 言 on 2022/8/1.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <TOSClientLib/TOSClientLib.h>

NS_ASSUME_NONNULL_BEGIN

@interface CombinationDataModel : TIMMessageContent 

@property (nonatomic, copy) NSString *name;
@property (nonatomic, strong) NSArray <NSString *>*topics;

@property (nonatomic, assign) CGRect contentF;

@property (nonatomic, assign) CGFloat tableViewH;

@end

@interface CombinationMessage : TIMMessageContent

/// 2：图片，3：文件，4：视频，5：文本，7：语音，15~20：热点问题
@property (nonatomic, copy) NSString *type;
@property (nonatomic, copy) NSString *text;
@property (nonatomic, copy) NSString *name;
@property (nonatomic, copy) NSString *subType;
@property (nonatomic, strong) NSArray <NSString *>*cards;
@property (nonatomic, strong) NSArray <CombinationDataModel *>*data;

/// 文件沙盒存储地址
@property (nonatomic, copy) NSString *mediaPath;
/**
 robotProvider
 */
@property (nonatomic, strong) NSString *robotProvider;

/// 选择的问题类型
@property (nonatomic, assign) NSInteger selectData;

/// 当前展示在哪页
@property (nonatomic, assign) NSInteger selectPageData;

@property (nonatomic, assign) CGRect contentF;

@property (nonatomic, assign) CGFloat tableViewH;

@end


NS_ASSUME_NONNULL_END
