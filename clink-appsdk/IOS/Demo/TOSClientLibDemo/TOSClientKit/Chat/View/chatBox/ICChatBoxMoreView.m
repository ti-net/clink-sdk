//
//  ICChatBoxMoreView.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/11.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICChatBoxMoreView.h"
#import "ICChatBoxMoreViewItem.h"
#import "UIView+SDExtension.h"
#import "TIMConstants.h"
#import <TOSClientKit/TOSKitCustomInfo.h>

#define topLineH  1.f
#define bottomH  0

@interface ICChatBoxMoreView() <UIScrollViewDelegate>

@property (nonatomic, weak)UIView *topLine;
@property (nonatomic, weak)UIScrollView *scrollView;
@property (nonatomic, weak)UIPageControl *pageControl;

@end

@implementation ICChatBoxMoreView

- (id) initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
//        self.backgroundColor = TOSColor(237, 237, 246);
        self.backgroundColor = [TOSKitCustomInfo shareCustomInfo].chatBox_more_backgroundColor;
        [self topLine];
        [self scrollView];
        [self pageControl];
    }
    return self;
}

- (void)setFrame:(CGRect)frame
{
    [super setFrame:frame];
    [self.scrollView setFrame:CGRectMake(0, [TOSKitCustomInfo shareCustomInfo].chatBox_more_topLineHeight, frame.size.width,frame.size.height-bottomH)];
    [self.pageControl setFrame:CGRectMake(0, frame.size.height-bottomH, frame.size.width, 8)];
}

#pragma mark - Public Methods

- (void)setItems:(NSMutableArray *)items
{
    _items = items;
    self.pageControl.numberOfPages = items.count/8 + (items.count%8 > 0 ? 1 : 0);
    self.scrollView.contentSize = CGSizeMake(self.scrollView.tosSD_width * self.pageControl.numberOfPages, _scrollView.tosSD_height);
    /// 赋值前先移除之前的item
    for (UIView * item in self.scrollView.subviews) {
        if ([item isKindOfClass:[ICChatBoxMoreViewItem class]]) {
            [item removeFromSuperview];
        }
    }
    
    float x = 24.f;
    float y = 24.f;
    float w = 56.f;
    float h = 56.f + 12.f + 20.f;
    
    float lineSpacing = self.tosSD_height - y*2 - h*2;
    float interitemSpacing = (self.tosSD_width - x*2 - w*4) / 3;
    int i = 0;
    int page = 0;
    
    for (ICChatBoxMoreViewItem * item in _items) {
        [self.scrollView addSubview:item];
        [item setFrame:CGRectMake(x, y, w, h)];
        [item setTag:i];
        [item addTarget:self action:@selector(didSelectedItem:) forControlEvents:UIControlEventTouchUpInside];
        i ++;
        page = i % 8 == 0 ? page + 1 : page;
        x = (i % 4 ? x + w + interitemSpacing : page * self.tosSD_width + 24.f);
        y = (i % 8 < 4 ? 24.f : h + lineSpacing + 24.f);
    }

}

// 点击了某个Item
- (void)didSelectedItem:(ICChatBoxMoreViewItem *)sender {
    if (_delegate && [_delegate respondsToSelector:@selector(chatBoxMoreView:didSelectItem:itemTitle:)]) {
        NSString *title = [NSString stringWithFormat:@"%ld",(long)sender.tag];
        for (ICChatBoxMoreViewItem * item in _items) {
            if (item.tag == sender.tag) {
                title = item.title;
            }
        }
        ICChatBoxMoreViewItem *item;
        if (![sender isKindOfClass:[ICChatBoxMoreViewItem class]]) {
            item = (ICChatBoxMoreViewItem *)sender.superview;
        }
        [_delegate chatBoxMoreView:self didSelectItem:item itemTitle:title];
    }
}

#pragma mark - UIScrollViewDelegate

- (void) scrollViewDidEndDecelerating:(UIScrollView *)scrollView
{
    int page = scrollView.contentOffset.x / self.tosSD_width;
    [_pageControl setCurrentPage:page];
}


#pragma mark - Getter and Setter

- (UIScrollView *)scrollView
{
    if (nil == _scrollView) {
        UIScrollView *scrollView = [[UIScrollView alloc] init];
        [scrollView setShowsHorizontalScrollIndicator:NO];
        [scrollView setShowsVerticalScrollIndicator:NO];
        [scrollView setPagingEnabled:YES];
        scrollView.delegate = self;
        [self addSubview:scrollView];
        _scrollView = scrollView;
    }
    return _scrollView;
}

- (UIPageControl *)pageControl
{
    if (nil == _pageControl) {
        UIPageControl *pageControl = [[UIPageControl alloc] init];
        [self addSubview:pageControl];
        _pageControl = pageControl;
        _pageControl.hidesForSinglePage = YES;
        _pageControl.currentPageIndicatorTintColor = [UIColor whiteColor];
        _pageControl.pageIndicatorTintColor = [UIColor blackColor];
        [_pageControl addTarget:self action:@selector(pageControlClicked:) forControlEvents:UIControlEventValueChanged];
    }
    return _pageControl;
}

- (UIView *)topLine
{
    if (nil == _topLine) {
        UIView * topLine = [[UIView alloc] initWithFrame:CGRectMake(0, 0, App_Frame_Width,[TOSKitCustomInfo shareCustomInfo].chatBox_more_topLineHeight)];
        [self addSubview:topLine];
        topLine.backgroundColor = [TOSKitCustomInfo shareCustomInfo].chatBox_more_topLineColor;
        _topLine = topLine;
    }
    return _topLine;
}


#pragma mark - Privite Method 

- (void)pageControlClicked:(UIPageControl *)pageControl
{
    [self.scrollView scrollRectToVisible:CGRectMake(pageControl.currentPage * App_Frame_Width, 0, App_Frame_Width, self.scrollView.tosCF_height) animated:YES];
}





@end
