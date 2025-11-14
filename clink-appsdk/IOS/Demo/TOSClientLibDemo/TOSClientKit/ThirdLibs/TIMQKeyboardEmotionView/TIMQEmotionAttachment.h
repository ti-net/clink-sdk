//
//  TIMQEmotionAttachment.h
//  QKeyboardEmotionView
//
//  Created by 侯力 on 2024/3/19.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

//为了获取输入框里的纯文本，只能自己新建一个类
@interface TIMQEmotionAttachment : NSTextAttachment

@property (nonatomic, strong) NSString *displayText;

@end

NS_ASSUME_NONNULL_END
