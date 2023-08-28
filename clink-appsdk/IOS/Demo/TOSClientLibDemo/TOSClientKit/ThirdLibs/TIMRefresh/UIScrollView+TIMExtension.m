//  代码地址: https://github.com/CoderMJLee/TIMRefresh
//  代码地址: http://code4app.com/ios/%E5%BF%AB%E9%80%9F%E9%9B%86%E6%88%90%E4%B8%8B%E6%8B%89%E4%B8%8A%E6%8B%89%E5%88%B7%E6%96%B0/52326ce26803fabc46000000
//  UIScrollView+Extension.m
//  TIMRefreshExample
//
//  Created by MJ Lee on 14-5-28.
//  Copyright (c) 2014年 小码哥. All rights reserved.
//

#import "UIScrollView+TIMExtension.h"
#import <objc/runtime.h>

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunguarded-availability-new"

@implementation UIScrollView (TIMExtension)

static BOOL tosrespondsToAdjustedContentInset_;

+ (void)initialize
{
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        tosrespondsToAdjustedContentInset_ = [self instancesRespondToSelector:@selector(adjustedContentInset)];
    });
}

- (UIEdgeInsets)tosmj_inset {
#ifdef __IPHONE_11_0
    if (tosrespondsToAdjustedContentInset_) {
        return self.adjustedContentInset;
    }
#endif
    return self.contentInset;
    
}

- (void)setTosmj_insetT:(CGFloat)tosmj_insetT {
    UIEdgeInsets inset = self.contentInset;
    inset.top = tosmj_insetT;
#ifdef __IPHONE_11_0
    if (tosrespondsToAdjustedContentInset_) {
        inset.top -= (self.adjustedContentInset.top - self.contentInset.top);
    }
#endif
    self.contentInset = inset;
}

- (CGFloat)tosmj_insetT {
    return self.tosmj_inset.top;
}

- (void)setTosmj_insetB:(CGFloat)tosmj_insetB {
    UIEdgeInsets inset = self.contentInset;
    inset.bottom = tosmj_insetB;
#ifdef __IPHONE_11_0
    if (tosrespondsToAdjustedContentInset_) {
        inset.bottom -= (self.adjustedContentInset.bottom - self.contentInset.bottom);
    }
#endif
    self.contentInset = inset;
}

- (CGFloat)tosmj_insetB {
    return self.tosmj_inset.bottom;
}

- (void)setTosmj_insetL:(CGFloat)tosmj_insetL {
    UIEdgeInsets inset = self.contentInset;
    inset.left = tosmj_insetL;
#ifdef __IPHONE_11_0
    if (tosrespondsToAdjustedContentInset_) {
        inset.left -= (self.adjustedContentInset.left - self.contentInset.left);
    }
#endif
    self.contentInset = inset;
}

- (CGFloat)tosmj_insetL {
    return self.tosmj_inset.left;
}

- (void)setTosmj_insetR:(CGFloat)tosmj_insetR {
    UIEdgeInsets inset = self.contentInset;
    inset.right = tosmj_insetR;
#ifdef __IPHONE_11_0
    if (tosrespondsToAdjustedContentInset_) {
        inset.right -= (self.adjustedContentInset.right - self.contentInset.right);
    }
#endif
    self.contentInset = inset;
}

- (CGFloat)tosmj_insetR {
    return self.tosmj_inset.right;
}

- (void)setTosmj_offsetX:(CGFloat)tosmj_offsetX {
    CGPoint offset = self.contentOffset;
    offset.x = tosmj_offsetX;
    self.contentOffset = offset;
}

- (CGFloat)tosmj_offsetX {
    return self.contentOffset.x;
}

- (void)setTosmj_offsetY:(CGFloat)tosmj_offsetY {
    CGPoint offset = self.contentOffset;
    offset.y = tosmj_offsetY;
    self.contentOffset = offset;
}

- (CGFloat)tosmj_offsetY {
    return self.contentOffset.y;
}

- (void)setTosmj_contentW:(CGFloat)tosmj_contentW {
    CGSize size = self.contentSize;
    size.width = tosmj_contentW;
    self.contentSize = size;
}

- (CGFloat)tosmj_contentW {
    return self.contentSize.width;
}

- (void)setTosmj_contentH:(CGFloat)tosmj_contentH {
    CGSize size = self.contentSize;
    size.height = tosmj_contentH;
    self.contentSize = size;
}

- (CGFloat)tosmj_contentH {
    return self.contentSize.height;
}


//- (UIEdgeInsets)mj_inset
//{
//#ifdef __IPHONE_11_0
//    if (respondsToAdjustedContentInset_) {
//        return self.adjustedContentInset;
//    }
//#endif
//    return self.contentInset;
//}
//
//- (void)setMj_insetT:(CGFloat)mj_insetT
//{
//    UIEdgeInsets inset = self.contentInset;
//    inset.top = mj_insetT;
//#ifdef __IPHONE_11_0
//    if (respondsToAdjustedContentInset_) {
//        inset.top -= (self.adjustedContentInset.top - self.contentInset.top);
//    }
//#endif
//    self.contentInset = inset;
//}
//
//- (CGFloat)mj_insetT
//{
//    return self.mj_inset.top;
//}
//
//- (void)setMj_insetB:(CGFloat)mj_insetB
//{
//    UIEdgeInsets inset = self.contentInset;
//    inset.bottom = mj_insetB;
//#ifdef __IPHONE_11_0
//    if (respondsToAdjustedContentInset_) {
//        inset.bottom -= (self.adjustedContentInset.bottom - self.contentInset.bottom);
//    }
//#endif
//    self.contentInset = inset;
//}
//
//- (CGFloat)mj_insetB
//{
//    return self.mj_inset.bottom;
//}
//
//- (void)setMj_insetL:(CGFloat)mj_insetL
//{
//    UIEdgeInsets inset = self.contentInset;
//    inset.left = mj_insetL;
//#ifdef __IPHONE_11_0
//    if (respondsToAdjustedContentInset_) {
//        inset.left -= (self.adjustedContentInset.left - self.contentInset.left);
//    }
//#endif
//    self.contentInset = inset;
//}
//
//- (CGFloat)mj_insetL
//{
//    return self.mj_inset.left;
//}
//
//- (void)setMj_insetR:(CGFloat)mj_insetR
//{
//    UIEdgeInsets inset = self.contentInset;
//    inset.right = mj_insetR;
//#ifdef __IPHONE_11_0
//    if (respondsToAdjustedContentInset_) {
//        inset.right -= (self.adjustedContentInset.right - self.contentInset.right);
//    }
//#endif
//    self.contentInset = inset;
//}
//
//- (CGFloat)mj_insetR
//{
//    return self.mj_inset.right;
//}
//
//- (void)setMj_offsetX:(CGFloat)mj_offsetX
//{
//    CGPoint offset = self.contentOffset;
//    offset.x = mj_offsetX;
//    self.contentOffset = offset;
//}
//
//- (CGFloat)mj_offsetX
//{
//    return self.contentOffset.x;
//}
//
//- (void)setMj_offsetY:(CGFloat)mj_offsetY
//{
//    CGPoint offset = self.contentOffset;
//    offset.y = mj_offsetY;
//    self.contentOffset = offset;
//}
//
//- (CGFloat)mj_offsetY
//{
//    return self.contentOffset.y;
//}
//
//- (void)setMj_contentW:(CGFloat)mj_contentW
//{
//    CGSize size = self.contentSize;
//    size.width = mj_contentW;
//    self.contentSize = size;
//}
//
//- (CGFloat)mj_contentW
//{
//    return self.contentSize.width;
//}
//
//- (void)setMj_contentH:(CGFloat)mj_contentH
//{
//    CGSize size = self.contentSize;
//    size.height = mj_contentH;
//    self.contentSize = size;
//}
//
//- (CGFloat)mj_contentH
//{
//    return self.contentSize.height;
//}

@end
#pragma clang diagnostic pop
