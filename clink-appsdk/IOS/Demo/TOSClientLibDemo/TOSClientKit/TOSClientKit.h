//
//  TOSClientKit.h
//  TOSClientKit
//
//  Created by 言 on 2023/7/5.
//

#import <Foundation/Foundation.h>

//! Project version number for TOSClientKit.
FOUNDATION_EXPORT double TOSClientKitVersionNumber;

//! Project version string for TOSClientKit.
FOUNDATION_EXPORT const unsigned char TOSClientKitVersionString[];

// In this header, you should import all the public headers of your framework using statements like #import <TOSClientKit/PublicHeader.h>
/// TIMKit核心类
#import <TOSClientKit/TOSKit.h>
#import <TOSClientLib/TIMClient.h>

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

