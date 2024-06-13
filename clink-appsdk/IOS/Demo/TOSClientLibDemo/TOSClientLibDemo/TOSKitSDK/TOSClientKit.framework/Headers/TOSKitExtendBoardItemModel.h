//
//  TOSKitExtendBoardItemModel.h
//  TOSClientKit
//
//  Created by 言 on 2022/7/13.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <TOSClientLib/TOSClientLib.h>

NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(NSInteger, TOSChatBoxExtendBoardType) {
    TOSChatBoxExtendBoardTypePhotos = 0,    //相册
    TOSChatBoxExtendBoardTypeTakePicture,   //相机
    TOSChatBoxExtendBoardTypeCustomFile,    //文件
    TOSChatBoxExtendBoardTypeArtificial,    //转人工
    TOSChatBoxExtendBoardTypeCloseChat,     //结束会话
    TOSChatBoxExtendBoardTypeCustom         //自定义
};

@interface TOSKitExtendBoardItemModel : TIMLibBaseModel

/// 图片名
@property (nonatomic, copy) NSString *image;

/// 标题
@property (nonatomic, copy) NSString *title;

/// 扩展项的唯一标示符
@property (nonatomic, assign) NSInteger index;

/// 类型，除自定义类型外，其他类型不填即为默认UI
@property (nonatomic, assign) TOSChatBoxExtendBoardType type;

@end

NS_ASSUME_NONNULL_END
