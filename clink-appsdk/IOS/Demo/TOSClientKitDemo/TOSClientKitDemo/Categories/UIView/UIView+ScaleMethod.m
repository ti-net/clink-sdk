//
//  UIView+ScaleMethod.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/9.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "UIView+ScaleMethod.h"
#import <objc/runtime.h>

static char scaleKey;
static char heightScaleKey;

CGFloat _scale = 0;
CGFloat _heightScale = 0;

UIStatusBarStyle _statusBarStyle;

@implementation UIView (ScaleMethod)

+ (void)load {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        Method oldMethod = class_getInstanceMethod([self class], @selector(initWithFrame:));
        Method newMethod = class_getInstanceMethod([self class], @selector(scale_initWithFrame:));
        // 调换两个方法的实现
        method_exchangeImplementations(oldMethod, newMethod);
        
    });
}

- (void)setScale:(CGFloat)scale {
    objc_setAssociatedObject(self, &scaleKey, @(scale), OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

- (CGFloat)scale {
    if (_scale == 0) {
        _scale = [objc_getAssociatedObject(self, &scaleKey) floatValue];
    }
    
    return _scale;
}

- (void)setHeightScale:(CGFloat)heightScale {
    objc_setAssociatedObject(self
                             , &heightScaleKey, @(heightScale), OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

- (CGFloat)heightScale {
    if (_heightScale == 0) {
        _heightScale = [objc_getAssociatedObject(self, &heightScaleKey) floatValue];
    }
    return _heightScale;
}

- (instancetype)scale_initWithFrame:(CGRect)frame{
    
    if (IS_IPHONE_5 || IS_IPHONE_4_OR_LESS) {
        self.scale = SCREEN_WIDTH / 375.f;
    } else if (IS_IPHONE_6P) {
        self.scale = 414.0/375.f;
    } else if (IS_IPAD) {
        self.scale = SCREEN_WIDTH / 375.f;
    } else if (IS_IPHONE_X) {
        self.scale = SCREEN_WIDTH / 375.f;
        self.heightScale = SCREEN_HEIGHT/667.f;
    } else if (IS_IPHONE_XsMax) {
        self.scale = 414 / 375.f;
        self.heightScale = SCREEN_HEIGHT/667.f;
    } else {
        self.scale = SCREEN_WIDTH / 375.f;
    }
    
    [self scale_initWithFrame:frame];
    
    return self;
}

@end


@implementation UIViewController (ScaleMethod)

+ (void)load {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        Method oldMethod = class_getInstanceMethod([self class], @selector(viewDidLoad));
        Method newMethod = class_getInstanceMethod([self class], @selector(scale_viewDidLoad));
        // 调换两个方法的实现
        method_exchangeImplementations(oldMethod, newMethod);
    });
}

- (void)setScale:(CGFloat)scale {
    objc_setAssociatedObject(self, &scaleKey, @(scale), OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

- (CGFloat)scale {
    if (_scale == 0) {
        _scale = [objc_getAssociatedObject(self, &scaleKey) floatValue];
    }
    return _scale;
}

- (void)setHeightScale:(CGFloat)heightScale {
    objc_setAssociatedObject(self
                             , &heightScaleKey, @(heightScale), OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

- (CGFloat)heightScale {
    if (_heightScale == 0) {
        _heightScale = [objc_getAssociatedObject(self, &heightScaleKey) floatValue];
    }
    return _heightScale;
}

- (void)scale_viewDidLoad {
    
    dispatch_queue_t concurrentQueue = dispatch_queue_create("myVideo.concurrent.queue", DISPATCH_QUEUE_CONCURRENT);
    dispatch_async(concurrentQueue, ^(){
        [[NSUserDefaults standardUserDefaults] setValue:NSStringFromClass([self class]) forKey:@"currentController.GMYM"];
        [[NSUserDefaults standardUserDefaults] synchronize];
    });
    
    if (IS_IPHONE_5 || IS_IPHONE_4_OR_LESS) {
        self.scale = SCREEN_WIDTH / 375.f;
    } else if (IS_IPHONE_6P) {
        self.scale = 414.0/375.f;
    } else if (IS_IPAD) {
        self.scale = SCREEN_WIDTH / 375.f;
    } else if (IS_IPHONE_X) {
        self.scale = SCREEN_WIDTH / 375.f;
        self.heightScale = SCREEN_HEIGHT/667.f;
    } else if (IS_IPHONE_XsMax) {
        self.scale = 414 / 375.f;
        self.heightScale = SCREEN_HEIGHT/667.f;
    } else {
        self.scale = SCREEN_WIDTH / 375.f;
    }
    [self scale_viewDidLoad];
    
}

@end

