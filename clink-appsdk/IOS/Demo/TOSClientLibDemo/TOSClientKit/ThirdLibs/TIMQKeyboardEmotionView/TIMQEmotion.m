//
//  TIMQEmotion.m
//  QKeyboardEmotionView
//
//  Created by 侯力 on 2024/03/20.
//  Copyright (c) 2021年 侯力. All rights reserved.
//

#import "TIMQEmotion.h"

@implementation TIMQEmotion

+ (instancetype)emotionWithIdentifier:(NSString *)identifier displayName:(NSString *)displayName {
    TIMQEmotion *emotion = [[self alloc] init];
    emotion.identifier = identifier;
    emotion.displayName = displayName;
    return emotion;
}

- (BOOL)isEqual:(id)object {
    if (!object) return NO;
    if (self == object) return YES;
    if (![object isKindOfClass:[self class]]) return NO;
    return [self.identifier isEqualToString:((TIMQEmotion *)object).identifier];
}

- (NSString *)description {
    return [NSString stringWithFormat:@"%@, identifier: %@, displayName: %@", [super description], self.identifier, self.displayName];
}

@end
