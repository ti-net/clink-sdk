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

/// Chat相关
#import <TOSClientKit/TOSCustomerChatVC.h>
#import <TOSClientKit/TOSKitChatBoxExtendBoard.h>
#import <TOSClientKit/TOSKitExtendBoardItemModel.h>
#import <TOSClientKit/TIMRefresh.h>

// Base
#import <TOSClientKit/TOSBaseView.h>
#import <TOSClientKit/TOSBaseTableView.h>
#import <TOSClientKit/TOSBaseViewController.h>
#import <TOSClientKit/TOSChatCustomBaseTableViewCell.h>


// 布局相关
#import <TOSClientKit/TOSAutoLayout.h>
#import <TOSClientKit/UIView+TOSAutoLayout.h>
#import <TOSClientKit/UITableView+TOSAutoTableViewCellHeight.h>

//发送多媒体消息 图片 声音 视频
#import <TOSClientKit/TOSSendMultiMedia.h>

// 工具类
#import <TOSClientKit/TOSKitCustomInfo.h>

/// Model
#import <TOSClientKit/TIMMessageModel.h>
#import <TOSClientKit/TIMICMessage.h>
#import <TOSClientKit/ICChatServerDefs.h>

#import <TOSClientKit/TOSKitCustomInfo.h>
#import <TOSClientKit/TIMMessageFrame.h>

