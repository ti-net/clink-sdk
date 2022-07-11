//
//  TOSClientKit.h
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

// In this header, you should import all the public headers of your framework using statements like #import <TOSClientKit/PublicHeader.h>
/// TIMKit核心类
#import <TOSClientKit/TOSKit.h>

/// Session相关
#import <TOSClientKit/TIMSessionListViewController.h>
#import <TOSClientKit/TIMSessionBaseCell.h>
#import <TOSClientKit/TIMSessionBaseTableViewCell.h>

/// Chat相关
#import <TOSClientKit/TOSCustomerChatVC.h>


// Base
#import <TOSClientKit/TIMBaseView.h>
#import <TOSClientKit/TIMBaseModel.h>
#import <TOSClientKit/TIMBaseTableView.h>
#import <TOSClientKit/TIMBaseTableViewCell.h>
#import <TOSClientKit/TIMBaseViewController.h>

// 布局相关
#import <TOSClientKit/TIMAutoLayout.h>
#import <TOSClientKit/UIView+TIMAutoLayout.h>
#import <TOSClientKit/UITableView+TIMAutoTableViewCellHeight.h>

//发送多媒体消息 图片 声音 视频
#import <TOSClientKit/TIMSendMultiMedia.h>

// 工具类
#import <TOSClientKit/kitUtils.h>



