//
//  TIMCustomerChatVC.h
//  TIMClientKit
//
//  Created by apple on 2021/8/23.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <TIMClientLib/TIMSession.h>


NS_ASSUME_NONNULL_BEGIN

@interface TIMCustomerChatVC : UIViewController

// 会话
@property (nonatomic, strong) TIMSession *session;


/// 自定义欢迎语
@property(nonatomic, copy) NSString *welcomsString;

/// 设置常用语
/// @param commonWords 常用语数组
- (void)setupCommonWords:(NSArray <NSString *>*)commonWords;

//此方法获取文件保存得到的文件路径
-(NSString*)saveFileMethed;


/*以下是会话聊天必须参数*/
//标题名字
@property(nonatomic, copy) NSString *titleName;
//接入号名称
@property(nonatomic, copy) NSString *appName;


@end

NS_ASSUME_NONNULL_END
