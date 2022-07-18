//
//  CustomStylesDetailsViewController.m
//  TOSClientKitDemo
//
//  Created by 言 on 2022/7/5.
//

#import "CustomStylesDetailsViewController.h"
#import "CustomStylesModel.h"
#import "CustomStylesDetailsModel.h"
#import "CustomStylesDetailsTableViewCell.h"

@interface CustomStylesDetailsViewController () <UITableViewDelegate, UITableViewDataSource>

@property (nonatomic, strong) NSMutableArray <CustomStylesDetailsModel *>*dataSource;

@property (nonatomic, weak) BaseTableView *tableView;

@end

@implementation CustomStylesDetailsViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.navigationItem.title = self.model.styleName;
    
    
    self.tableView = [self setupTableView];
    
    NSArray <NSDictionary *>*array = [NSArray readPlistFileWithFileName:@"CustomStylesDetailsDataSource"];
    self.dataSource = [[NSArray modelArrayWithClass:[CustomStylesDetailsModel class] json:array] mutableCopy];
    
    [self.dataSource enumerateObjectsUsingBlock:^(CustomStylesDetailsModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        NSString *valueTop;
        NSString *valueBottom;
        switch (idx) {
            case 0:
                valueTop = self.model.chat_backGround;
                valueBottom = [self.model.portrait_cornerRadius stringValue];
                break;
            case 1:
                valueTop = self.model.senderBubble_backGround;
                valueBottom = [self.model.senderBubble_cornerRadius stringValue];
                break;
            case 2:
                valueTop = self.model.receiveBubble_backGround;
                valueBottom = [self.model.receiveBubble_cornerRadius stringValue];
                break;
            default:
                break;
        }
        obj.value_top = valueTop;
        obj.value_bottom = valueBottom;
    }];
    [self.tableView reloadData];
}

#pragma mark - UITableViewDataSource
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return [tableView addByDataSourceCount:self.dataSource.count noDataImage:@"" describe:@"暂无数据"];
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    CustomStylesDetailsTableViewCell *cell = [CustomStylesDetailsTableViewCell cellXibWithTableView:tableView reuseIdentifie:[CustomStylesDetailsTableViewCell className]];
    [cell setWithModel:self.dataSource[indexPath.section]];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    
}

#pragma mark - 初始化
- (BaseTableView *)setupTableView {
    BaseTableView *tableView = [[BaseTableView alloc] initWithFrame:CGRectMake(0.f, 0.f, kWindowWidth, kWindowHeight - kNavTop - kBottomBarHeight) style:(UITableViewStyleGrouped)];
    [self.view addSubview:tableView];
    tableView.delegate = self;
    tableView.dataSource = self;
    tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    tableView.rowHeight = 153.f;
    tableView.sectionHeaderHeight = 16.f;
    tableView.sectionFooterHeight = 0.01f;
    [tableView setTableHeaderView:[[UIView alloc] initWithFrame:CGRectMake(0, 0, kWindowWidth, 16.f)]];
    return tableView;
}

@end
