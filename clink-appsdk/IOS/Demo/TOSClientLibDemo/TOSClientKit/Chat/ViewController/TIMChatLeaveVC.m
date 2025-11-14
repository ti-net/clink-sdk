//
//  TIMChatLeaveVC.m
//  TIMClientKit
//
//  Created by apple on 2021/12/20.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "TIMChatLeaveVC.h"
#import "chatLeaveTitleCell.h"
#import "chatLeaveSingleLineCell.h"
#import "chatLeaveMultilineLineCell.h"
#import "TIMConstants.h"
#import "kitUtils.h"
#import "WHToast.h"
#import <TOSClientLib/TOSClientLib.h>
//#import "UIView+SDExtension.h"
//#import "IQKeyboardManager.h"
#import "NSString+Extension.h"
#import "UIView+TIMYYAdd.h"

@interface TIMChatLeaveVC ()<UITableViewDelegate,UITableViewDataSource>//chatLeaveMultilineLineCellDelegate

@property (nonatomic, strong) UITableView *chatLeaveTableView;
@property (nonatomic, strong) UIButton *commitBtn;
@property (nonatomic, strong) UIView *successView;//提交成功的view
@property (nonatomic, strong) NSMutableArray *cellIdList;//记录只加载一次


@end

@implementation TIMChatLeaveVC

- (void)viewDidLoad {
    [super viewDidLoad];
    [self setupSubView];
//    [self addNotificationAction];
    
    self.cellIdList = [[NSMutableArray alloc]init];

//    [self setKeyboardAction];
    self.navigationItem.leftBarButtonItem = [[UIBarButtonItem alloc] initWithTitle:@"取消" style:(UIBarButtonItemStylePlain) target:self action:@selector(cancelBtnAction:)];
}

- (void)cancelBtnAction:(UIBarButtonItem *)sender {
    if (self.navigationController.viewControllers.count <= 1) {
        [self dismissViewControllerAnimated:YES completion:nil];
    } else {
        [self.navigationController popViewControllerAnimated:YES];
    }
}

-(void)setupSubView{
    [self.view addSubview:self.chatLeaveTableView];
    [self.view addSubview:self.commitBtn];
    [self.view addSubview:self.successView];
    self.successView.hidden = YES;
}


#pragma mark tableview
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.leaveMessageFields.count+1;
}


-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    if (indexPath.row == 0) {
        NSString * identifier = [NSString stringWithFormat:@"CellId%ld%ld",(long)indexPath.section,(long)indexPath.row];
        chatLeaveTitleCell *titleCell = [tableView dequeueReusableCellWithIdentifier:identifier];
        if (!titleCell) {
            titleCell = [[chatLeaveTitleCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:identifier];
        }
        titleCell.title = self.welcomContent;

        return titleCell;
    }
    
    NSDictionary*dict = self.leaveMessageFields[indexPath.row - 1];
    NSString*type = [NSString stringWithFormat:@"%@",dict[@"type"]];

    if (![kitUtils isBlankString:type]) {
        if ([type isEqualToString:@"0"]) {//单选
            NSString * identifier = [NSString stringWithFormat:@"CellId%ld%ld",(long)indexPath.section,(long)indexPath.row];
            chatLeaveSingleLineCell *singleCell = [tableView dequeueReusableCellWithIdentifier:identifier];
            if (!singleCell) {
                singleCell = [[chatLeaveSingleLineCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:identifier];
            }
            if (![kitUtils isBlankString:dict[@"name"]]) {
                [singleCell setCellWithTitle:[NSString stringWithFormat:@"%@",dict[@"name"]] must:[NSString stringWithFormat:@"%@",dict[@"must"]]];
            }
            return singleCell;
        }else
            if ([type isEqualToString:@"1"]) {//多选
            NSString * identifier = [NSString stringWithFormat:@"CellId%ld%ld",(long)indexPath.section,(long)indexPath.row];
            chatLeaveMultilineLineCell *multilineCell = [tableView dequeueReusableCellWithIdentifier:identifier];
            if (!multilineCell) {
                multilineCell = [[chatLeaveMultilineLineCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:identifier];
            }
                
//            int j = 0;
//            if (self.cellIdList.count>0) {
//                for (NSString*ident in self.cellIdList) {
//                    if ([ident isEqualToString:identifier]) {//加载过了
//                        j = 1;
//                    }
//                }
//            }
//            if (j == 0) {
//                [self.cellIdList addObject:identifier];
//            }
                
            if (![kitUtils isBlankString:dict[@"name"]]) {
                [multilineCell setCellWithTitle:[NSString stringWithFormat:@"%@",dict[@"name"]] must:[NSString stringWithFormat:@"%@",dict[@"must"]] index:indexPath];
//                multilineCell.multilineDelegate = self;
            }

            return multilineCell;
        }
    }

    //防止为空情况崩溃
    UITableViewCell *lineCell = [tableView dequeueReusableCellWithIdentifier:@"CellId"];
    if (!lineCell) {
        lineCell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:@"CellId"];
    }
    return lineCell;

}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    if (indexPath.row == 0) {
//        CGSize size = [self.welcomContent sizewithfont];
        return 80;
    }
    NSDictionary*dict = self.leaveMessageFields[indexPath.row - 1];
    NSString*type = [NSString stringWithFormat:@"%@",dict[@"type"]];

    if (![kitUtils isBlankString:type]) {
        if ([type isEqualToString:@"0"]) {//单选
            return 90;
        }else if ([type isEqualToString:@"1"]) {//多选
            return 110;
        }
    }
    
    return 90;
}

//- (void)scrollViewDidScroll:(UIScrollView *)scrollView{
//    
//    [[[UIApplication sharedApplication] keyWindow] endEditing:YES];
//}


#pragma mark 初始化
-(UITableView *)chatLeaveTableView
{
    if (nil == _chatLeaveTableView) {
        _chatLeaveTableView = [[UITableView alloc] initWithFrame:CGRectMake(0, 0,App_Frame_Width , APP_Frame_Height -kNavTop - kBottomBarHeight - 100) style:UITableViewStylePlain];
        _chatLeaveTableView.delegate = self;
        _chatLeaveTableView.dataSource = self;
        _chatLeaveTableView.backgroundColor = [UIColor whiteColor];
        _chatLeaveTableView.separatorStyle = UITableViewCellSeparatorStyleNone;
        _chatLeaveTableView.keyboardDismissMode = UIScrollViewKeyboardDismissModeOnDrag;
        if (@available(iOS 15.0, *)) {
            _chatLeaveTableView.sectionHeaderTopPadding = 0.f;
            [UITableView appearance].sectionHeaderTopPadding = 0.f;
        }
    }
    return _chatLeaveTableView;
}

-(UIButton *)commitBtn{
    if (!_commitBtn) {
        _commitBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        _commitBtn.frame = CGRectMake(20, APP_Frame_Height -kNavTop - kBottomBarHeight - 90, App_Frame_Width - 40, 40);
        [_commitBtn setBackgroundColor:TOSHexAColor(0x1890ff,1.0)];
        [_commitBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        [_commitBtn setTitle:@"留言" forState:UIControlStateNormal];
        _commitBtn.layer.masksToBounds = YES;
        _commitBtn.layer.cornerRadius = 4.0f;
        [_commitBtn addTarget:self action:@selector(commitAction) forControlEvents:UIControlEventTouchUpInside];
    }
    return _commitBtn;
}

-(UIView *)successView{
    if (!_successView) {
        _successView = [[UIView alloc]initWithFrame:CGRectMake(0, kNavTop, App_Frame_Width, APP_Frame_Height -kNavTop - kBottomBarHeight)];
        _successView.backgroundColor = [UIColor whiteColor];
        
        UIImageView*successIcon = [[UIImageView alloc]initWithFrame:CGRectMake((App_Frame_Width - 60)/2, 30, 60, 60)];
        successIcon.layer.masksToBounds = YES;
        successIcon.layer.cornerRadius = 30;
        successIcon.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatLeaveUpSuccessIcon"]];
        [_successView addSubview:successIcon];
        
        UILabel*title = [[UILabel alloc]initWithFrame:CGRectMake(50, 130, App_Frame_Width - 100, 30)];
        title.textColor = TOSHexAColor(0x000000,1.0);
        title.textAlignment = NSTextAlignmentCenter;
        title.font = [UIFont systemFontOfSize:18];
        title.text = @"留言成功";
        [_successView addSubview:title];
        
        UILabel*subtitle = [[UILabel alloc]initWithFrame:CGRectMake(30, 160, App_Frame_Width - 60, APP_Frame_Height - 100)];
        subtitle.textColor = TOSHexAColor(0x94969b,1.0);
        subtitle.font = [UIFont systemFontOfSize:16];
        subtitle.textAlignment = NSTextAlignmentCenter;
        subtitle.numberOfLines = 0;
        subtitle.text = [NSString stringWithFormat:@"%@",self.leaveTip];//@"我们将尽快与您取得联系并解决您的问题";
        
        CGSize size = [subtitle.text tim_sizeWithMaxWidth:App_Frame_Width - 60 andFont:[UIFont systemFontOfSize:16]];
        subtitle.tos_height = size.height;
        [_successView addSubview:subtitle];

    }
    return _successView;
}


//提交
-(void)commitAction{
    
   __block int ifSuccess = 1;
    
    if (self.leaveMessageFields.count>0) {
        NSMutableDictionary*uploadDict = [[NSMutableDictionary alloc]init];
        for (int i = 0; i<self.leaveMessageFields.count; i++) {
            NSDictionary*dict = self.leaveMessageFields[i];
            NSString*type = [NSString stringWithFormat:@"%@",dict[@"type"]];
            if (![kitUtils isBlankString:type]) {
                if ([type isEqualToString:@"0"]) {//单选
                    NSIndexPath *indexPath = [NSIndexPath indexPathForRow:i+1 inSection:0];
                    chatLeaveSingleLineCell *singleCell = [self.chatLeaveTableView cellForRowAtIndexPath:indexPath];
                    NSString*singleStr = [NSString stringWithFormat:@"%@",singleCell.textField.text];
                    NSString*mustStr = [NSString stringWithFormat:@"%@",dict[@"must"]];
                    if (singleStr.length == 0  && [mustStr isEqualToString:@"1"]) {
                        [WHToast showMessage:[NSString stringWithFormat:@"%@为必填项",dict[@"name"]] duration:1 finishHandler:^{
                        }];
                        ifSuccess = 0;
                        return;
                    }
                    [uploadDict setObject:singleStr forKey:[NSString stringWithFormat:@"%@",dict[@"name"]]];
                }else if ([type isEqualToString:@"1"]) {//多选
                    NSIndexPath *indexPath = [NSIndexPath indexPathForRow:i+1 inSection:0];
                    chatLeaveMultilineLineCell *multilineCell = [self.chatLeaveTableView cellForRowAtIndexPath:indexPath];
                    NSString*multilineStr = [NSString stringWithFormat:@"%@",multilineCell.textView.text];
                    NSString*mustStr = [NSString stringWithFormat:@"%@",dict[@"must"]];
                    if (multilineStr.length == 0  && [mustStr isEqualToString:@"1"]) {
                        [WHToast showMessage:[NSString stringWithFormat:@"%@为必填项",dict[@"name"]] duration:1 finishHandler:^{
                        }];
                        ifSuccess = 0;
                        return;
                    }
                    [uploadDict setObject:multilineStr forKey:[NSString stringWithFormat:@"%@",dict[@"name"]]];

                }
            }
        }
        
        if (ifSuccess == 1) {
            
            WEAKSELF
            [[OnlineEventSendManager sharedOnlineEventSendManager]upLoadChatLeaveMessageEventWithPayloads:uploadDict
                                                                                                  success:^{
                weakSelf.chatLeaveTableView.hidden = YES;
                weakSelf.commitBtn.hidden = YES;
                weakSelf.successView.hidden = NO;
                
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                [WHToast showMessage:@"提交失败" duration:2 finishHandler:^{

                }];
            }];
        }
    }
}


#pragma mark chatLeaveMultilineLineCellDelegate
//-(void)scrollerToIndex:(NSIndexPath *)index{
////    if (self.chatLeaveTableView.height<APP_Frame_Height -kNavTop - kBottomBarHeight  - 100) {
////        [self.chatLeaveTableView scrollToRowAtIndexPath:[NSIndexPath indexPathForRow:index.row inSection:0] atScrollPosition:UITableViewScrollPositionBottom animated:NO];
////    }
//}
//
//
//
//-(void)addNotificationAction{
//    [[NSNotificationCenter defaultCenter]addObserver:self selector:@selector(keyboardWillShow:) name:UIKeyboardWillShowNotification object:nil];
//    [[NSNotificationCenter defaultCenter]addObserver:self selector:@selector(keyboardWillHide:) name:UIKeyboardWillHideNotification object:nil];
//}
//
//#pragma mark 键盘出现
//- (void)keyboardWillShow:(NSNotification*)note
//{
//    CGRect keyBoardRect=[note.userInfo[UIKeyboardFrameEndUserInfoKey] CGRectValue];
//    
////    self.chatLeaveTableView.contentInset= UIEdgeInsetsMake(0,0, keyBoardRect.size.height,0);
//    self.chatLeaveTableView.height = APP_Frame_Height -kNavTop - kBottomBarHeight  - keyBoardRect.size.height;
////    [self innerScrollToBottom];
//}
//
//#pragma mark 键盘消失
//- (void)keyboardWillHide:(NSNotification*)note
//{
////    self.chatLeaveTableView.contentInset = UIEdgeInsetsZero;
//    self.chatLeaveTableView.height = APP_Frame_Height -kNavTop - kBottomBarHeight  - 100;
//
//}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [self.view endEditing:YES];
}

- (void)dealloc {
    NSLog(@"dealloc");
}

@end
