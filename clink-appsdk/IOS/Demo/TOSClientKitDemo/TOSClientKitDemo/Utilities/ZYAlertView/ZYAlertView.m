//
//  ZYAlertView.m
//  CloudDoctorRegionVersion
//
//  Created by 赵言 on 2017/5/19.
//  Copyright © 2017年 DFIIA. All rights reserved.
//

#import "ZYAlertView.h"
#import "NSString+Frame.h"

@interface ZYAlertView () <UITableViewDelegate, UITableViewDataSource>

@property (strong, nonatomic) UITableView *tableView;
@property (strong, nonatomic) UIView *maskView;
@property (strong, nonatomic) NSString *message;

/**
 判断是否有提示信息
 */
@property (assign, nonatomic) BOOL isMessage;

/**
 cell 总个数
 */
@property (assign, nonatomic) NSUInteger count;
@property (assign, nonatomic) CGFloat messageHeight;
@property (strong, nonatomic) NSArray <NSString *> *options;
@property (strong, nonatomic) NSArray <NSNumber *> *destructive;
@property (copy, nonatomic) void(^selectedBlock)(NSUInteger);
@property (copy, nonatomic) void(^cancelBlock)(void);

@end

//提示信息字体大小
static const CGFloat messageFont = 19.f;

//按钮字体大小
static const CGFloat cancelFont = 16.f;
static const CGFloat optionsFont = 15.f;

//提示信息边距单边
static const CGFloat messageEdgeWidth = 15.f;

static const CGFloat tableViewFooterHeight = 0.f;

static const CGFloat tableViewHeight = 48.f;
static const CGFloat cencelHeight = 48.f;

@implementation ZYAlertView
#pragma mark - 初始化
- (instancetype)initWithMessage:(NSString *)message options:(NSArray <NSString *>*)options destructive:(NSArray <NSNumber *>*)destructive selectedBlock:(void(^)(NSUInteger))selectedBlock cancelBlock:(void(^)(void))cancelBlock {
    
    self = [super initWithFrame:[UIScreen mainScreen].bounds];
    if (self) {
        
        self.backgroundColor = [UIColor clearColor];
        _message = message;
        CGFloat height = [message contentHeightWithFont:[UIFont fontWithName:kFontNameRegular size:messageFont] width:self.width - messageEdgeWidth * 2] + 30.f;
        _count = options.count;
        _isMessage = (message != nil && ![message isEqualToString:@""]);
        if (_isMessage) {
            _messageHeight = height > tableViewHeight ? height : tableViewHeight;
        } else {
            _messageHeight = 0.f;
        }
        _options = options;
        _destructive = destructive;
        _selectedBlock = selectedBlock;
        _cancelBlock = cancelBlock;
        [self addSubview:self.maskView];
        [self addSubview:self.tableView];
    }
    return self;
}

#pragma mark - UITableViewDataSource
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 2;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    tableView.backgroundView = ({
        UIBlurEffect *effect = [UIBlurEffect effectWithStyle:UIBlurEffectStyleExtraLight];
        UIVisualEffectView *visualEffectView = [[UIVisualEffectView alloc] initWithEffect:effect];
        [visualEffectView setFrame:self.tableView.frame];
        visualEffectView;
    });
    return section == 0 ? (_isMessage ? _count + 1 : _count) : 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"Navi_Cell"];
    cell.backgroundColor = [[UIColor whiteColor] colorWithAlphaComponent:0.4f];
    if (_isMessage && indexPath.section == 0 && indexPath.row == 0) {
        cell.textLabel.text = _message;
        cell.textLabel.numberOfLines = 0;
        cell.textLabel.font = [UIFont fontWithName:kFontNameRegular size:messageFont];
        cell.textLabel.textColor = kHexColor(0x5C5C5C);
        cell.textLabel.textAlignment = NSTextAlignmentCenter;
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        return cell;
    }
    if (indexPath.section == 0) {
        if (_isMessage) {
            cell.textLabel.text = _options[indexPath.row - 1];
        } else {
            cell.textLabel.text = _options[indexPath.row];
        }
        cell.textLabel.textColor = kHexColor(0x101010);
        cell.textLabel.font = [UIFont fontWithName:kFontNameRegular size:optionsFont];
    } else {
        cell.textLabel.text = @"取消";
        cell.textLabel.textColor = kHexColor(0x2E88F6);
        cell.textLabel.font = [UIFont fontWithName:kFontNameRegular size:cancelFont];
    }
    
    if (indexPath.section == 0) {
        if (_isMessage ? [_destructive containsObject:@(indexPath.row - 1)] : [_destructive containsObject:@(indexPath.row)]) {
            cell.textLabel.textColor = kRGBColor(204.f, 0.f, 0.f);
        }
    }
    cell.textLabel.textAlignment = NSTextAlignmentCenter;
    cell.selectionStyle = UITableViewCellSelectionStyleDefault;
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (_isMessage && indexPath.section == 0 && indexPath.row == 0) {
        return _messageHeight;
    } else {
        return indexPath.section == 0 ? tableViewHeight : cencelHeight;
    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    return section == 0 ? tableViewFooterHeight : 0.f;
}

- (UIView*)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    UIView *footerView = [[UIView alloc]initWithFrame:CGRectMake(0, 0, tableView.bounds.size.width, tableViewFooterHeight)];
    footerView.backgroundColor = [UIColor clearColor];
    return footerView;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    if (indexPath.section == 0) {
        if (_isMessage && indexPath.row == 0) {
            return;
        }
        if (_selectedBlock) {
            _selectedBlock(_isMessage ? indexPath.row - 1 : indexPath.row);
        }
    } else {
        if (_cancelBlock) {
            _cancelBlock();
        }
    }
    [self dismiss];
}

#pragma mark - 添加子视图
- (void)layoutSubviews {
    [super layoutSubviews];
    
    self.tableView.frame = CGRectMake(15.f, kWindowHeight, kWindowWidth - 15.f*2, tableViewHeight * _count + tableViewFooterHeight + _messageHeight + cencelHeight);
    self.tableView.layer.cornerRadius = 3.f;
    self.tableView.layer.masksToBounds = YES;
    [UIView animateWithDuration:0.15f animations:^{
        self.maskView.alpha = 0.4f;
    } completion:^(BOOL finished) {
        
        [UIView animateWithDuration:0.25f animations:^{
            CGRect rect = self.tableView.frame;
            rect.origin.y = rect.origin.y - self.tableView.bounds.size.height - 15.f - kBottomBarHeight;
            self.tableView.frame = rect;
        }];
    }];
}

- (void)show {
    [[UIApplication sharedApplication].keyWindow addSubview:self];
}

- (void)dismiss {
    [UIView animateWithDuration:0.25f animations:^{
        CGRect rect = self.tableView.frame;
        rect.origin.y += self.tableView.bounds.size.height;
        self.tableView.frame = rect;
    } completion:^(BOOL finished) {
        
        [UIView animateWithDuration:0.15f animations:^{
            self.maskView.alpha = 0.f;
        } completion:^(BOOL finished) {
            [self removeFromSuperview];
        }];
    }];
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [self dismiss];
}

#pragma mark - 懒加载
- (UITableView *)tableView {
    if (!_tableView) {
        _tableView = [[UITableView alloc] initWithFrame:CGRectZero style:(UITableViewStylePlain)];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.clipsToBounds = YES;
        _tableView.bounces = NO;
        _tableView.separatorInset = UIEdgeInsetsMake(0, 21.f, 0, 21.f);
        _tableView.backgroundColor = [[UIColor whiteColor] colorWithAlphaComponent:0.01f];
        [_tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Navi_Cell"];
    }
    return _tableView;
}

- (UIView *)maskView {
    if (!_maskView) {
        _maskView = [[UIView alloc] initWithFrame:self.bounds];
        _maskView.backgroundColor = [UIColor blackColor];
        _maskView.alpha = 0.f;
        _maskView.userInteractionEnabled = YES;
    }
    return _maskView;
}

@end
