//
//  TIMClientKit.h
//  TIMClientKit
//
//  Created by YanBo on 2020/5/23.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

//! Project version number for TIMClientKit.
FOUNDATION_EXPORT double TIMClientKitVersionNumber;

//! Project version string for TIMClientKit.
FOUNDATION_EXPORT const unsigned char TIMClientKitVersionString[];

// In this header, you should import all the public headers of your framework using statements like #import <TIMClientKit/PublicHeader.h>
/// TIMKit核心类
#import <TIMClientKit/TIMKit.h>

/// Session相关
#import <TIMClientKit/TIMSessionListViewController.h>
#import <TIMClientKit/TIMSessionBaseCell.h>
#import <TIMClientKit/TIMSessionBaseTableViewCell.h>

/// Chat相关
#import <TIMClientKit/TIMCustomerChatVC.h>


// Base
#import <TIMClientKit/TIMBaseView.h>
#import <TIMClientKit/TIMBaseModel.h>
#import <TIMClientKit/TIMBaseTableView.h>
#import <TIMClientKit/TIMBaseTableViewCell.h>
#import <TIMClientKit/TIMBaseViewController.h>

// 布局相关
#import <TIMClientKit/TIMAutoLayout.h>
#import <TIMClientKit/UIView+TIMAutoLayout.h>
#import <TIMClientKit/UITableView+TIMAutoTableViewCellHeight.h>

//发送多媒体消息 图片 声音 视频
#import <TIMClientKit/TIMSendMultiMedia.h>

// 工具类
#import <TIMClientKit/kitUtils.h>



