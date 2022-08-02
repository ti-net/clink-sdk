//
//  MineViewController.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/11.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "MineConfigViewController.h"
#import "MineInfoModel.h"
#import "LoginModel.h"
#import "MineTextTableCellModel.h"
#import "CustomStylesViewController.h"
#import <MMPopupView/MMPopupView.h>

#import "MineTextTableCell.h"
#import "MineSwitchTableViewCell.h"


@interface MineConfigViewController () <UITableViewDelegate, UITableViewDataSource>

@property (nonatomic, weak) BaseTableView *tableView;
@property (nonatomic, strong) NSMutableArray <NSMutableArray <MineTextTableCellModel *>*>*cellDataSource;

@property (weak, nonatomic) UITextField *textField;


@end

@implementation MineConfigViewController
- (void)setupSubviews {
    [super setupSubviews];
    self.navigationItem.title = @"自定义样式";
    
    self.tableView = [self setupTableView];
    [self bindViewModel];
    

}

- (void)viewDidLoad {
    [super viewDidLoad];
    
//    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(videoMessageReceiveNotification:) name:KVisitorVideoReady object:nil];
//
//    [[NSNotificationCenter defaultCenter] addObserver:self
//                                             selector:@selector(kVideoBothCallHangupNotification:)
//                                                 name:kVideoBothCallHangup
//                                               object:nil];
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
}



- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.tableView reloadData];
}

- (void)bindViewModel {
    self.cellDataSource[0][0].value = @"经典样式";
//    NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
//    CFShow((__bridge CFTypeRef)(infoDictionary));
//    NSString *app_Version = [infoDictionary by_ObjectForKey:@"CFBundleShortVersionString"];
//
//    NSInteger clientType = [LoginModel loginModel].clientType.integerValue;
//    if (clientType == 1 || clientType == 2) {

//        self.cellDataSource[2][2].value = [LoginModel loginModel].softPhoneSwitch.integerValue == 1 ? @"1" : @"0";
//    } else {
//        self.cellDataSource[2][0].value = app_Version?:@"";
//    }
    
}

#pragma mark - UITableViewDataSource
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return self.cellDataSource.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.cellDataSource[section].count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    BaseTableViewCell *cell = [BaseTableViewCell cellXibWithTableView:tableView reuseIdentifie:self.cellDataSource[indexPath.section][indexPath.row].cellType];
//    if (indexPath.section == 1) {
//        MineMenuBarTableCell *menuBarCell = (MineMenuBarTableCell *)cell;
//        self.menuBarCell = menuBarCell;
//        @weakify(self);
//        [menuBarCell setDidClickNoticeBtn:^{
//            @strongify(self);
//            NoticeWMPageController *noticeVC = [[NoticeWMPageController alloc] init];
//            [self.navigationController pushViewController:noticeVC animated:YES];
//        }];
//    }
    return cell;
}

- (void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath {
    [((BaseTableViewCell *)cell) setWithModel:self.cellDataSource[indexPath.section][indexPath.row]];
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    MineTextTableCellModel *model = self.cellDataSource[indexPath.section][indexPath.row];
    if ([model.title isEqualToString:@"聊天窗口UI样式"]) {
        CustomStylesViewController *customStylesVC = [[CustomStylesViewController alloc] initWithNibName:[CustomStylesViewController className] bundle:nil];
        [self.navigationController pushViewController:customStylesVC animated:YES];
    } else if ([model.title isEqualToString:@"时间色值"]) {

//        __weak CustomAlertView *customAlertView = [self addCustomAlertViewWithTitle:@"修改手机号" placeholder:model.value];
//        @weakify(self);
//        [customAlertView setDidClickDetermineBtn:^{
//            @strongify(self);
//            if ([self.textField.text regexValidate:kPhoneNumber]) {
//                [customAlertView hidenView];
//                self.clientBindTelVM.bindTel = self.textField.text;
//                [self.clientBindTelVM requestData];
//                [TRLoadingView showLoadingAddTo:self.view animated:YES];
//            } else {
//                [customAlertView endEditing:YES];
//                [self showErrorView:@"请输入正确的手机号码"];
//            }
//        }];
    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 45.f;
}

#pragma mark - 初始化
- (BaseTableView *)setupTableView {
    BaseTableView *tableView = [[BaseTableView alloc] initWithFrame:CGRectMake(0.f, 0, kWindowWidth, kWindowHeight - kBottomBarHeight - kTabBarHeight) style:(UITableViewStyleGrouped)];
    [self.view addSubview:tableView];
    tableView.delegate = self;
    tableView.dataSource = self;
    tableView.sectionHeaderHeight = 0.f;
    tableView.sectionFooterHeight = 7.f;
    tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    return tableView;
}

- (NSMutableArray *)cellDataSource {
    if (!_cellDataSource) {

        _cellDataSource = [NSMutableArray arrayWithObjects:[NSMutableArray array], [NSMutableArray array], [NSMutableArray array], nil];
        
        NSArray *section0 = [NSArray arrayWithObjects:
                             @{
                                 @"cellType": [MineTextTableCell className],
                                 @"title": @"聊天窗口UI样式",
                                 @"value": @"",
                                 @"selectionStyle": @(UITableViewCellSelectionStyleGray),
                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)},
                             @{
                                 @"cellType": [MineTextTableCell className],
                                 @"title": @"时间色值",
                                 @"value": @"",
                                 @"selectionStyle": @(UITableViewCellSelectionStyleGray),
                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)},
                             @{
                                 @"cellType": [MineSwitchTableViewCell className],
                                 @"title": @"客服/机器人昵称显示",
                                 @"value": @"",
                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
                                 @"accessoryType": @(UITableViewCellAccessoryNone)},
                             @{
                                 @"cellType": [MineSwitchTableViewCell className],
                                 @"title": @"访客昵称显示",
                                 @"value": @"",
                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
                                 @"accessoryType": @(UITableViewCellAccessoryNone)},
                             @{
                                 @"cellType": [MineSwitchTableViewCell className],
                                 @"title": @"客服/机器人头像显示",
                                 @"value": @"",
                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
                                 @"accessoryType": @(UITableViewCellAccessoryNone)},
                             @{
                                 @"cellType": [MineSwitchTableViewCell className],
                                 @"title": @"访客头像显示",
                                 @"value": @"",
                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
                                 @"accessoryType": @(UITableViewCellAccessoryNone)}, nil];
        
        NSArray *section1 = [NSArray arrayWithObjects:
                             @{
                                 @"cellType": [MineTextTableCell className],
                                 @"title": @"输入框背景色值",
                                 @"value": @"",
                                 @"selectionStyle": @(UITableViewCellSelectionStyleGray),
                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)},
                             @{
                                 @"cellType": [MineTextTableCell className],
                                 @"title": @"输入区分割线",
                                 @"value": @"",
                                 @"selectionStyle": @(UITableViewCellSelectionStyleGray),
                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)},
                             @{
                                 @"cellType": [MineTextTableCell className],
                                 @"title": @"语音按钮色值",
                                 @"value": @"",
                                 @"selectionStyle": @(UITableViewCellSelectionStyleGray),
                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)},
                             @{
                                 @"cellType": [MineTextTableCell className],
                                 @"title": @"输入框暗文提示语",
                                 @"value": @"",
                                 @"selectionStyle": @(UITableViewCellSelectionStyleGray),
                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)}, nil];
        
        NSArray *section2 = [NSArray arrayWithObjects:
                             @{
                                 @"cellType": [MineSwitchTableViewCell className],
                                 @"title": @"吐司提示背景色值",
                                 @"value": @"",
                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)},
                             @{
                                 @"cellType": [MineTextTableCell className],
                                 @"title": @"吐司提示文字色值",
                                 @"value": @"",
                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)}, nil];
        
        NSArray *section3 = [NSArray arrayWithObjects:
                             @{
                                 @"cellType": [MineTextTableCell className],
                                 @"title": @"相册导航栏背景色值",
                                 @"value": @"",
                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)},
                             @{
                                 @"cellType": [MineTextTableCell className],
                                 @"title": @"相册导航栏文字色值",
                                 @"value": @"",
                                 @"selectionStyle": @(UITableViewCellSelectionStyleNone),
                                 @"accessoryType": @(UITableViewCellAccessoryDisclosureIndicator)}, nil];
        
        
        [_cellDataSource addObject:[NSMutableArray array]];
        
        for (NSDictionary *dic in section0) {
            [_cellDataSource[0] addObject:[MineTextTableCellModel yy_modelWithJSON:dic]];
        }
        
        for (NSDictionary *dic in section1) {
            [_cellDataSource[1] addObject:[MineTextTableCellModel yy_modelWithJSON:dic]];
        }
        
        for (NSDictionary *dic in section2) {
            [_cellDataSource[2] addObject:[MineTextTableCellModel yy_modelWithJSON:dic]];
        }
        
        for (NSDictionary *dic in section3) {
            [_cellDataSource[3] addObject:[MineTextTableCellModel yy_modelWithJSON:dic]];
        }
    }
    return _cellDataSource;
}


@end
