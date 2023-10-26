//
//  CustomStylesViewController.m
//  TOSClientKitDemo
//
//  Created by 言 on 2022/7/5.
//

#import "CustomStylesViewController.h"
#import "CustomStylesModel.h"
#import "CustomStylesSaveView.h"
#import "CustomStylesTableViewCell.h"
#import "CustomStylesDetailsViewController.h"
#import "DomainNameSave.h"

@interface CustomStylesViewController () <UITableViewDelegate, UITableViewDataSource>

@property (nonatomic, strong) NSMutableArray <CustomStylesModel *>*dataSource;

@property (nonatomic, weak) BaseTableView *tableView;

@end

static CGFloat const kSaveViewHeight = 64.f;

@implementation CustomStylesViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.navigationItem.title = @"聊天窗口样式";
    self.tableView = [self setupTableView];
    self.view.backgroundColor = [UIColor whiteColor];
    
    UIBarButtonItem *blackButton = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"customStyles_backBtn"] style:(UIBarButtonItemStylePlain) target:self action:@selector(blackClickEvent)];
    self.navigationItem.leftBarButtonItem = blackButton;
    
    
    CustomStylesSaveView *saveView = kInitXibName([CustomStylesSaveView className]);
    saveView.frame = CGRectMake(0, self.tableView.bottom, kWindowWidth, kSaveViewHeight);
    [self.view addSubview:saveView];
    
    NSArray <NSDictionary *>*array = [NSArray readPlistFileWithFileName:@"CustomStylesDataSource"];
    self.dataSource = [[NSArray modelArrayWithClass:[CustomStylesModel class] json:array] mutableCopy];
    
    [self.dataSource enumerateObjectsUsingBlock:^(CustomStylesModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        if ([DomainNameSave shareDomainNameSave].index == idx) {
            obj.isSelected = @(1);
        } else {
            obj.isSelected = @(0);
        }
    }];
    
    [self.tableView reloadData];
}

- (void)blackClickEvent {
    [self.navigationController popViewControllerAnimated:YES];
}

- (void)routerEventWithName:(NSString *)eventName userInfo:(id)userInfo {
    
    if ([eventName isEqualToString:kRouterEventClickSelectedBox]) {
        
        CustomStylesModel *model = userInfo;
        [self.dataSource enumerateObjectsUsingBlock:^(CustomStylesModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if (obj.isSelected.boolValue) {
                obj.isSelected = @(0);
            }
        }];
        model.isSelected = @(1);
        [self.tableView reloadData];
    } else if ([eventName isEqualToString:kRouterEventClickDetails]) {
        
        CustomStylesModel *model = userInfo;
        CustomStylesDetailsViewController *detailsVC = [[CustomStylesDetailsViewController alloc] init];
        detailsVC.model = model;
        [self.navigationController pushViewController:detailsVC animated:YES];
    } else if ([eventName isEqualToString:kRouterEventSaveButton]) {
        __block NSString * theme = @"";
        __block CustomStylesModel *model;
        [self.dataSource enumerateObjectsUsingBlock:^(CustomStylesModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if (obj.isSelected.boolValue) {
                model = obj;
                theme = obj.styleName;
                [DomainNameSave shareDomainNameSave].index = idx;
            }
        }];
        /// 主题名称回调
        if (self.themeClickEvent) {
            self.themeClickEvent(theme);
        }
        
        [TOSKitCustomInfo shareCustomInfo].senderBubble_backGround = [self colorWithHexString:model.senderBubble_backGround alpha:1.f];
        [TOSKitCustomInfo shareCustomInfo].senderBubble_cornerRadius = [model.senderBubble_cornerRadius doubleValue];
        
        
        [TOSKitCustomInfo shareCustomInfo].receiveBubble_backGround = [self colorWithHexString:model.receiveBubble_backGround alpha:1.f];
        [TOSKitCustomInfo shareCustomInfo].receiveBubble_cornerRadius = [model.receiveBubble_cornerRadius doubleValue];
        
        
        [TOSKitCustomInfo shareCustomInfo].chat_backGround = [self colorWithHexString:model.chat_backGround alpha:1.f];
        
        
        [TOSKitCustomInfo shareCustomInfo].portrait_cornerRadius = [model.portrait_cornerRadius doubleValue];
        
        [self.navigationController popViewControllerAnimated:YES];
    }
}

#pragma mark - UITableViewDataSource
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [tableView addByDataSourceCount:self.dataSource.count noDataImage:@"" describe:@"暂无数据"];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    CustomStylesTableViewCell *cell = [CustomStylesTableViewCell cellXibWithTableView:tableView reuseIdentifie:[CustomStylesTableViewCell className]];
    [cell setWithModel:self.dataSource[indexPath.row]];
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (self.dataSource.count == indexPath.row) {
        return 88.f;
    } else {
        return 237.f;
    }
}

#pragma mark - 初始化
- (BaseTableView *)setupTableView {
    BaseTableView *tableView = [[BaseTableView alloc] initWithFrame:CGRectMake(0.f, 0.f, kWindowWidth, kWindowHeight - kNavTop - kBottomBarHeight - kSaveViewHeight) style:(UITableViewStylePlain)];
    [self.view addSubview:tableView];
    tableView.delegate = self;
    tableView.dataSource = self;
    tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    return tableView;
}

- (UIColor *)colorWithHexString:(NSString *)colorStr alpha:(CGFloat)alpha {
    NSString *cString = [[colorStr stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]] uppercaseString];
        
    NSRange range;
    range.location = 0;
    range.length = 2;
    NSString *rString = [cString substringWithRange:range];
    
    range.location = 2;
    NSString *gString = [cString substringWithRange:range];
    
    range.location = 4;
    NSString *bString = [cString substringWithRange:range];
    
    
    unsigned int r, g, b;
    [[NSScanner scannerWithString:rString] scanHexInt:&r];
    [[NSScanner scannerWithString:gString] scanHexInt:&g];
    [[NSScanner scannerWithString:bString] scanHexInt:&b];
    
    return kRGBAColor(r, g, b, alpha);
}

@end
