//
//  TOSKitChatBoxExtendBoard.m
//  TOSClientKit
//
//  Created by 言 on 2022/7/13.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "TOSKitChatBoxExtendBoard.h"
#import "TOSKitExtendBoardItemModel.h"

static TOSKitChatBoxExtendBoard *extendBoard = nil;

@implementation TOSKitChatBoxExtendBoard

+ (TOSKitChatBoxExtendBoard *)shareChatBoxExtendBoard {
    return [[self alloc] init];
}

- (instancetype)init {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        extendBoard = [super init];
    });
    return extendBoard;
}

+ (instancetype)allocWithZone:(struct _NSZone *)zone {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        extendBoard = [super allocWithZone:zone];
        NSMutableArray *arr = [NSMutableArray array];
        
        for (NSInteger i = 0; i < 5; i++) {
            TOSKitExtendBoardItemModel *model = [[TOSKitExtendBoardItemModel alloc] init];
            switch (i) {
                case 0:
                    model.type = TOSChatBoxExtendBoardTypePhotos;
                    break;
                case 1:
                    model.type = TOSChatBoxExtendBoardTypeTakePicture;
                    break;
                case 2:
                    model.type = TOSChatBoxExtendBoardTypeCustomFile;
                    break;
                case 3:
                    model.type = TOSChatBoxExtendBoardTypeArtificial;
                    break;
                default:
                    model.type = TOSChatBoxExtendBoardTypeCloseChat;
                    break;
            }
            [arr addObject:model];
        }
        
        extendBoard.allItems = [NSArray arrayWithArray:arr];
    });
    return extendBoard;
}

- (id)copyWithZone:(NSZone *)zone {
    return extendBoard;
}

- (id)mutableCopyWithZone:(NSZone *)zone {
    return extendBoard;
}

@end
