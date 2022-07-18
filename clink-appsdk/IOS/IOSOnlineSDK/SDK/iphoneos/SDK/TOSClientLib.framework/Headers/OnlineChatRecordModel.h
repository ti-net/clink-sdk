//
//  OnlineChatRecordModel.h
//  TIMClientLib
//
//  Created by apple on 2021/10/30.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMLibBaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface OnlineChatRecordModel : TIMLibBaseModel

/**
 join
 */
@property (nonatomic, strong) NSString *join;
/**
 type
 */
@property (nonatomic, strong) NSString *type;
/**
 mainUniqueId
 */
@property (nonatomic, strong) NSString *mainUniqueId;
/**
 uniqueId
 */
@property (nonatomic, strong) NSString *uniqueId;
/**
 enterpriseId
 */
@property (nonatomic, strong) NSString *enterpriseId;
/**
 senderType
 */
@property (nonatomic, strong) NSString *senderType;
/**
 messageType
 */
@property (nonatomic, strong) NSString *messageType;
/**
 sender
 */
@property (nonatomic, strong) NSString *sender;
/**
 sendName
 */
@property (nonatomic, strong) NSString *sendName;
/**
 cno
 */
@property (nonatomic, strong) NSString *cno;
/**
 clientName
 */
@property (nonatomic, strong) NSString *clientName;
/**
 visitorId
 */
@property (nonatomic, strong) NSString *visitorId;
/**
 visitorName
 */
@property (nonatomic, strong) NSString *visitorName;
/**
 url
 */
@property (nonatomic, strong) NSString *url;
/**
 content
 */
@property (nonatomic, strong) NSString *content;
/**
 fileKey
 */
@property (nonatomic, strong) NSString *fileKey;
/**
 fileName
 */
@property (nonatomic, strong) NSString *fileName;
/**
 robotId
 */
@property (nonatomic, strong) NSString *robotId;
/**
 robotName
 */
@property (nonatomic, strong) NSString *robotName;
/**
 robotProvider
 */
@property (nonatomic, strong) NSString *robotProvider;
/**
 sendStatus
 */
@property (nonatomic, strong) NSString *sendStatus;
/**
 sensitiveWord
 */
@property (nonatomic, strong) NSString *sensitiveWord;
/**
 failReason
 */
@property (nonatomic, strong) NSString *failReason;
/**
 botAnswerUniqueId
 */
@property (nonatomic, strong) NSString *botAnswerUniqueId;
/**
 botFaqMatchLevel
 */
@property (nonatomic, strong) NSString *botFaqMatchLevel;
/**
 createTime
 */
@property (nonatomic, strong) NSString *createTime;
//@property (nonatomic, assign, readonly) NSTimeInterval createTime;
/**
 robotAvatar
 */
@property (nonatomic, strong) NSString *robotAvatar;
/**
 clientAvatar
 */
@property (nonatomic, strong) NSString *clientAvatar;
/**
 nickName
 */
@property (nonatomic, strong) NSString *nickName;
/**
 robotNickName
 */
@property (nonatomic, strong) NSString *robotNickName;
/**
 botAnswer
 */
@property (nonatomic, strong) NSString *botAnswer;

/// 判断是否评价 1已评价 0或null未评价
@property (nonatomic, strong) NSString *alreadyInvestigation;


@end

NS_ASSUME_NONNULL_END
