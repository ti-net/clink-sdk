//
//  OSRobotSelectCombinationCell.m
//  TIMClientKit
//
//  Created by apple on 2021/9/10.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "OSRobotSelectCombinationCell.h"

#import "TIMLabel.h"
#import "TIMConstants.h"
#import "UIView+SDExtension.h"
#import "TIMMessageModel.h"
#import "kitUtils.h"
#import "UIImageView+TIMWebCache.h"
#import "ICFaceManager.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
#import "ICFaceManager.h"

@interface OSRobotSelectCombinationCell()<UITableViewDelegate,UITableViewDataSource>
{
    NSMutableArray *selectArr;
    NSMutableArray *arrayTitle;
    NSMutableArray *selectBtnArr;

    NSString*selectTitle;
}
@property (nonatomic, strong) TIMLabel *titleLabel;
@property (nonatomic, strong) UIView *midLine;
@property (nonatomic, retain) UITableView *tableView;
@property (nonatomic, strong) UIView*selectView;
@property (nonatomic, strong) UIView*selectBtnLine;//按钮选择下划线

@end

@implementation OSRobotSelectCombinationCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.midLine];
        [self.contentView addSubview:self.titleLabel];
        
        arrayTitle = [[NSMutableArray alloc]init];
        selectBtnArr = [[NSMutableArray alloc]init];
        [self.contentView addSubview:self.tableView];
    }
    return self;
}
#pragma mark - Private Method
- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    if (!modelFrame.model.message.content) {
        return;
    }
    self.readLabel.frame = modelFrame.unReadLabelF;
    self.tableView.frame = modelFrame.custContentF;
    NSData* data = [modelFrame.model.message.content dataUsingEncoding:NSUTF8StringEncoding];
    id jsonObject = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingAllowFragments error:nil];
    if ([jsonObject isKindOfClass:[NSArray class]]) {
        for (NSDictionary *dict in jsonObject) {
            if ([dict[@"type"]intValue] == 5) {//富文本标题
                [self.titleLabel setFrame:modelFrame.custShareTitleF];
                self.titleLabel.attributedText = [ICFaceManager transferMessageString:dict[@"text"]?:@"" font:self.titleLabel.font  foreColor:TOSHexAColor(0x000000,1.0) lineHeight:0];
            }else if ([dict[@"type"]intValue] == 6) {//点击文本
                NSArray*cards = dict[@"cards"];
                if ([cards isKindOfClass:[NSArray class]] && cards.count>0) {
                    [arrayTitle removeAllObjects];
                    for (NSString*cardTitle in cards) {
                        [arrayTitle addObject:cardTitle];
                        [self.tableView reloadData];
                    }
                }
            }else if ([dict[@"type"]intValue] == 20) {//点击文本
                selectTitle = dict[@"text"];
                selectArr = dict[@"data"];
                if ([selectArr isKindOfClass:[NSArray class]] && selectArr.count>0) {
                    
                    for (int i = 0; i<selectArr.count; i++) {
                        NSDictionary*dict = selectArr[i];
                        
                        if (i== 0) {
                            NSArray*arr = dict[@"topics"];
                            if (arr!=nil && arr.count>0) {
                                [arrayTitle removeAllObjects];
                                for (NSString*cardTitle in arr) {
                                    [arrayTitle addObject:cardTitle];
                                }
                            }
                        }
                        UIButton*selectBtn = [UIButton buttonWithType:UIButtonTypeCustom];
                        CGFloat btnWidth = 240/selectArr.count;
                        selectBtn.frame = CGRectMake(0+i*btnWidth, 0, btnWidth, 25);
                        [selectBtn setTitle:dict[@"name"] forState:UIControlStateNormal];
                        if (i == 0) {
                            [selectBtn setTitleColor:TOSHexAColor(0x1890ff,1.0) forState:UIControlStateNormal];
                            self.selectBtnLine.frame = CGRectMake(0+i*btnWidth, 26, btnWidth, 1);
                        }else{
                            [selectBtn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
                        }
                        selectBtn.tag = i;
                        [selectBtn addTarget:self action:@selector(selectTitleAction:) forControlEvents:UIControlEventTouchUpInside];
                        [self.selectView addSubview:selectBtn];
                        [self.selectView addSubview:self.selectBtnLine];

                        [selectBtnArr addObject:selectBtn];
                    }

                    [self.tableView reloadData];
                }
            }
        }
        CGRect midLineRect = modelFrame.custShareTitleF;
        midLineRect.origin.y = CGRectGetMaxY(modelFrame.custShareTitleF) + 5;
        midLineRect.size.height = 1.0f;
        [self.midLine setFrame:midLineRect];
        if (arrayTitle.count == 0) {
            self.midLine.hidden = YES;
        }
    }
}

#pragma mark - Getter and Setter

- (TIMLabel *)titleLabel
{
    if (nil == _titleLabel) {
        _titleLabel = [[TIMLabel alloc] init];
        _titleLabel.numberOfLines = 0;
        _titleLabel.font = MessageFont;
        _titleLabel.textColor = TOSHexAColor(0x282724,1.0);
//        _titleLabel.linkDetectionTypes = KILinkTypeOptionNone;
    }
    return _titleLabel;
}

- (UIView *)midLine
{
    if (nil == _midLine) {
        _midLine = [[UIView alloc] init];
        _midLine.tosSD_height = 1.0f;
        _midLine.backgroundColor = [UIColor grayColor];
        _midLine.alpha = 1.0;
    }
    return _midLine;
}

-(UIView *)selectView{
    if (!_selectView) {
        _selectView = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREEN_WIDTH, 30)];
    }
    return _selectView;
}

-(UIView *)selectBtnLine{
    if (!_selectBtnLine) {
        _selectBtnLine = [[UIView alloc]init];
        _selectBtnLine.backgroundColor = TOSHexAColor(0x1890ff,1.0);
    }
    return _selectBtnLine;
}

-(UITableView *)tableView{
    if (!_tableView) {
        _tableView = [[UITableView alloc]initWithFrame:CGRectZero style:UITableViewStylePlain];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
        _tableView.scrollEnabled = NO;
        _tableView.backgroundColor = [UIColor clearColor];
        [_tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"cell0"];
        [_tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"cell1"];

        //去掉cell左边的15像素
        if ([self.tableView respondsToSelector:@selector(setSeparatorInset:)]) {
            [self.tableView setSeparatorInset:UIEdgeInsetsZero];
        }
        if ([self.tableView respondsToSelector:@selector(setLayoutMargins:)]) {
            [self.tableView setLayoutMargins:UIEdgeInsetsZero];
        }
        if (@available(iOS 15.0, *)) {
            _tableView.sectionHeaderTopPadding = 0.f;
            [UITableView appearance].sectionHeaderTopPadding = 0.f;
        }
    }
    return _tableView;
}

#pragma mark tableview
#pragma mark -- tableview代理方法
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 2;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{

    if (section == 0) {
        return 1;
    }
     return [arrayTitle count];
}
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.section == 0) {
        UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"cell0" forIndexPath:indexPath];
        [cell.contentView addSubview:self.selectView];
        
        
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.accessoryType = UITableViewCellAccessoryNone;
        cell.backgroundColor = [UIColor clearColor];
        return cell;
    }
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"cell1" forIndexPath:indexPath];
    cell.textLabel.text = arrayTitle[indexPath.row];
    cell.textLabel.font = [UIFont systemFontOfSize:15];
    cell.textLabel.textColor = TOSHexAColor(0x1890ff,1.0);//[UIColor blueColor];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    cell.accessoryType = UITableViewCellAccessoryNone;
    cell.backgroundColor = [UIColor clearColor];
    return cell;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    
    if (section == 0) {
        UILabel*label = [[UILabel alloc]initWithFrame:CGRectMake(0, 0, 200, 30)];
        label.text = selectTitle;
        label.textAlignment = NSTextAlignmentLeft;
        label.backgroundColor = [UIColor clearColor];
        return label;
    }
    
    return nil;
}
- (void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath{
    if ([cell respondsToSelector:@selector(setSeparatorInset:)]) {
        [cell setSeparatorInset:UIEdgeInsetsZero];
    }
    if ([cell respondsToSelector:@selector(setLayoutMargins:)]) {
        [cell setLayoutMargins:UIEdgeInsetsZero];
    }
}


-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 30;
}
-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    if (section == 0) {
        return 30;
    }
    return 0.001;
}
-(CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 0.1;
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{

    if ([self.longPressDelegate respondsToSelector:@selector(sendCombinaMessage:)]) {
        [self.longPressDelegate sendCombinaMessage:arrayTitle[indexPath.row]];
    }

}

-(void)selectTitleAction:(UIButton*)btn {
    
    for (int i = 0; i<selectArr.count; i++) {
        if (i == btn.tag) {
            NSDictionary*dict = selectArr[i];
            NSArray*arr = dict[@"topics"];
            if (arr!=nil && arr.count>0) {
                [arrayTitle removeAllObjects];
                for (NSString*cardTitle in arr) {
                    [arrayTitle addObject:cardTitle];
                }
            }
        }
    }
    for (UIButton*selectBtn in selectBtnArr) {
        if (selectBtn.tag == btn.tag) {
            [selectBtn setTitleColor:TOSHexAColor(0x1890ff,1.0) forState:UIControlStateNormal];
            
            CGFloat btnWidth = 240/selectArr.count;
            self.selectBtnLine.frame = CGRectMake(0+btn.tag*btnWidth, 26, btnWidth, 1);

        }else{
            [selectBtn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
        }
    }
    [self.tableView reloadData];
}

@end
