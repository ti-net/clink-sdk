//
//  superCustomStylesModel.h
//  TOSClientKitDemo
//
//  Created by 言 on 2022/7/5.
//

#import "BaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface superCustomStylesModel : BaseModel

@property (nonatomic, copy) NSString *styleName;
@property (nonatomic, strong) NSString *Chat_time_textColor;
@property (nonatomic) BOOL Chat_tosRobotName_enable;
@property (nonatomic) BOOL Chat_visitorName_enable;
@property (nonatomic) BOOL Chat_tosRobot_portrait_enable;
@property (nonatomic) BOOL Chat_visitor_portrait_enable;
@property (nonatomic, copy) NSString *ChatBox_backGroundColor;
@property (nonatomic, copy) NSString *ChatBox_lineColor;

@property (nonatomic, copy) NSString *VoiceButton_textColor;
@property (nonatomic, copy) NSString *ChatBox_textview_placeholder;
@property (nonatomic, copy) NSString *Toast_backGroundColor;
@property (nonatomic, copy) NSString *Toast_textColor;
@property (nonatomic, copy) NSString *imagePicker_naviBgColor;
@property (nonatomic, copy) NSString *imagePicker_barItemTextColor;
@end

NS_ASSUME_NONNULL_END
