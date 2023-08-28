//
//  ICEmotionListView.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/4/6.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICEmotionListView.h"
#import "ICEmotionPageView.h"
#import "UIView+SDExtension.h"
#import "TIMConstants.h"

#define topLineH  0.5

@interface ICEmotionListView ()<UIScrollViewDelegate>

@property (nonatomic, weak)UIView *topLine;
@property (nonatomic, weak)UIScrollView *scrollView;
@property (nonatomic, weak)UIPageControl *pageControl;

@end

@implementation ICEmotionListView

- (id) initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        self.backgroundColor = TOSColor(237, 237, 246);
        [self topLine];
        [self scrollView];
        [self pageControl];
    }
    return self;
}


#pragma mark - Priate

- (void)pageControlClicked:(UIPageControl *)pageControl
{
    
}

-(void)layoutSubviews
{
    [super layoutSubviews];
    self.pageControl.tosSD_width          = self.tosSD_width;
    self.pageControl.tosSD_height         = 10;
    self.pageControl.tosSD_x              = 0;
    self.pageControl.tosSD_y              = self.tosSD_height - self.pageControl.tosSD_height;
    self.scrollView.tosSD_width           = self.tosSD_width;
    self.scrollView.tosSD_height          = self.pageControl.tosSD_y;
    self.scrollView.tosSD_x               =self.scrollView.tosSD_y
                                    = 0;
    NSUInteger count                = self.scrollView.subviews.count;
    for (int i = 0; i < count; i ++) {
        ICEmotionPageView *pageView = self.scrollView.subviews[i];
        pageView.tosSD_width              = self.scrollView.tosSD_width;
        pageView.tosSD_height             = self.scrollView.tosSD_height;
        pageView.tosSD_y                  = 0;
        pageView.tosSD_x                  = i * pageView.tosSD_width;
    }
    self.scrollView.contentSize     = CGSizeMake(count*self.scrollView.tosSD_width, 0);
}

- (void)setEmotions:(NSArray *)emotions
{
    _emotions = emotions;
    
    [self.scrollView.subviews makeObjectsPerformSelector:@selector(removeFromSuperview)];
    NSUInteger count = (emotions.count + ICEmotionPageSize - 1)/ ICEmotionPageSize;
    self.pageControl.numberOfPages  = count;
    for (int i = 0; i < count; i ++) {
        ICEmotionPageView *pageView = [[ICEmotionPageView alloc] init];
        NSRange range;
        range.location              = i * ICEmotionPageSize;
        NSUInteger left             = emotions.count - range.location;//剩余
        if (left >= ICEmotionPageSize) {
            range.length            = ICEmotionPageSize;
        } else {
            range.length            = left;
        }
        pageView.emotions           = [emotions subarrayWithRange:range];
        [self.scrollView addSubview:pageView];
    }
    [self setNeedsLayout]; 
    
}

- (void)setGifemotions:(NSArray *)gifemotions
{
    _gifemotions = gifemotions;
    
    [self.scrollView.subviews makeObjectsPerformSelector:@selector(removeFromSuperview)];
    NSUInteger count = (gifemotions.count + ICGifEmotionPageSize - 1)/ ICGifEmotionPageSize;
    self.pageControl.numberOfPages  = count;
    for (int i = 0; i < count; i ++) {
        ICEmotionPageView *pageView = [[ICEmotionPageView alloc] init];
        NSRange range;
        range.location              = i * ICGifEmotionPageSize;
        NSUInteger left             = gifemotions.count - range.location;//剩余
        if (left >= ICGifEmotionPageSize) {
            range.length            = ICGifEmotionPageSize;
        } else {
            range.length            = left;
        }
        pageView.emotions           = [gifemotions subarrayWithRange:range];
        [self.scrollView addSubview:pageView];
    }
    [self setNeedsLayout];
}

#pragma mark - UIScrollViewDelegate

- (void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    double pageNum                = scrollView.contentOffset.x/scrollView.tosSD_width;
    self.pageControl.currentPage  = (int)(pageNum+0.5);
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
        _pageControl.currentPageIndicatorTintColor = [UIColor grayColor];
        _pageControl.pageIndicatorTintColor = [UIColor lightGrayColor];
        _pageControl.userInteractionEnabled = NO;
    }
    return _pageControl;
}

- (UIView *)topLine
{
    if (nil == _topLine) {
        UIView * topLine = [[UIView alloc] initWithFrame:CGRectMake(0, 0, App_Frame_Width,topLineH)];
        [self addSubview:topLine];
        topLine.backgroundColor = TOSColor(188.0, 188.0, 188.0);
        _topLine = topLine;
    }
    return _topLine;
}

@end
