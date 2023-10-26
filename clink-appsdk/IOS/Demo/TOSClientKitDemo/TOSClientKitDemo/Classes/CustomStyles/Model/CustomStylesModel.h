//
//  CustomStylesModel.h
//  TOSClientKitDemo
//
//  Created by 言 on 2022/7/5.
//

#import "BaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface CustomStylesModel : BaseModel

@property (nonatomic, copy) NSString *styleName;

@property (nonatomic, strong) NSNumber *isSelected;

@property (nonatomic, copy) NSString *backgroundImage;



@property (nonatomic, copy) NSString *senderBubble_backGround;
@property (nonatomic, strong) NSNumber *senderBubble_cornerRadius;


@property (nonatomic, copy) NSString *receiveBubble_backGround;
@property (nonatomic, strong) NSNumber *receiveBubble_cornerRadius;


@property (nonatomic, copy) NSString *chat_backGround;


@property (nonatomic, strong) NSNumber *portrait_cornerRadius;

@end

NS_ASSUME_NONNULL_END
