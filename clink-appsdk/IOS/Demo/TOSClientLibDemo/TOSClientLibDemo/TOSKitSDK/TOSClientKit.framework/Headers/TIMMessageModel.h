//
//  TIMMessageModel.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/12.
//  Copyright © 2016年 gxz All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMICMessage.h"

@interface TIMMessageModel : NSObject

// 后期重构把这个类可能要去掉--by:gxz

// 是否是发送者
@property (nonatomic, assign) BOOL isSender;
// 是否是群聊
//@property (nonatomic, assign) BOOL isChatGroup;


@property (nonatomic, strong) TIMICMessage * message;

// 包含voice，picture，video的路径;有大图时就是大图路径
// 不用这些路径了，只用里面的名字重新组成路径
@property (nonatomic, copy) NSString *mediaPath;

@property (nonatomic, copy) NSString *urlPath;

// 图片的宽
@property (nonatomic, assign) int picWidth;

// 图片的高
@property (nonatomic, assign) int picHeight;

// 图片的类型
@property (nonatomic, copy) NSString * picType;

// 图片的审核权限使能
@property (nonatomic, copy) NSString * strEnableAudit;

// 图片的查看权限使能
@property (nonatomic, copy) NSString * strEnableLookUp;

// 审核状态
@property (nonatomic, strong) NSNumber* auditStatus;

// 缩略图
@property (nonatomic, copy) NSString * thumbURL;


/*
 机器人相关
 */
//关联消息展示的数目
@property (nonatomic, assign) int combinationNum;
// 关联消息标题
@property (nonatomic, copy) NSString * combinationTitle;
// 关联消息副标题
@property (nonatomic, copy) NSString * combinationSubTitle;
@property (nonatomic, strong) NSArray * robotMessageList;

@end
