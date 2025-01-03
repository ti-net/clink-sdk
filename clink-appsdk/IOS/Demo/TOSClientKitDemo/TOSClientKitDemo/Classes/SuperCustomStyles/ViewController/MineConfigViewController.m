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

#import "MineTextTableCell.h"
#import "MineSwitchTableViewCell.h"
#import "superCustomStylesModel.h"
#import "CustomStylesModel.h"
#import "MineConfigInputView.h"
#import "NSString+Frame.h"


@interface MineConfigViewController () <UITableViewDelegate, UITableViewDataSource>

@property (nonatomic, weak) BaseTableView *tableView;
@property (nonatomic, strong) NSMutableArray <NSMutableArray <MineTextTableCellModel *>*>*cellDataSource;

@property (weak, nonatomic) UITextField *textField;
@property (nonatomic, strong) NSMutableArray <superCustomStylesModel *>*dataSource;

@property (nonatomic, strong) NSArray                * headDataSource;

@end

@implementation MineConfigViewController
- (void)setupSubviews {
    [super setupSubviews];
    self.navigationItem.title = @"自定义样式";
    self.headDataSource = @[@"对话区域配置", @"输入框配置", @"吐司提示配置", @"相册导航栏配置", @"表情面板配置"];
    self.tableView = [self setupTableView];
    [self bindViewModel];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(configSwitchChange:) name:kTOSClientDemoConfigSwitchChange object:nil];
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
}



- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.tableView reloadData];
}

- (void)bindViewModel {
    UIBarButtonItem *resetConfigButton = [[UIBarButtonItem alloc] initWithTitle:@"恢复默认" style:UIBarButtonItemStylePlain target:self action:@selector(resetConfigClickEvent)];
    [resetConfigButton setTintColor:[UIColor colorWithRed:0.263f green:0.522f blue:255.0f alpha:1.0f]];
    
    self.navigationItem.rightBarButtonItem = resetConfigButton;
    
    UIBarButtonItem *blackButton = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"customStyles_backBtn"] style:(UIBarButtonItemStylePlain) target:self action:@selector(blackClickEvent)];
    self.navigationItem.leftBarButtonItem = blackButton;
    
}

#pragma mark - configSwitchChange

- (void)configSwitchChange:(NSNotification *)notification {
    
    MineTextTableCellModel *model = (MineTextTableCellModel *)notification.object;
    for (int i = 0; i < self.cellDataSource.count; i++) {
        NSArray * nArray = self.cellDataSource[i];
        for (MineTextTableCellModel * nModel in nArray) {
            if ([nModel.title isEqualToString:model.title]) {
                nModel.value = model.value;
                /// 同步设置SDK的数据
                [self customInfoTitle:model.title withValue:model.value];
                break;
            }
        }
    }
    /// 更新沙盒路径下的配置数据
    [self upDataPlist];
}

- (void)upDataPlist {
    
    NSMutableArray * mArray = [NSMutableArray array];
    for (NSArray * nArray in self.cellDataSource) {
        NSMutableArray * subArray = [NSMutableArray array];
        for (MineTextTableCellModel * nModel in nArray) {
            NSMutableDictionary * dict = [self dicFromObject:nModel];
            [subArray addObject:dict];
        }
        [mArray addObject:subArray];
    }
    NSLog(@"解析后的数据：%@", mArray);
    [self writePlist:[mArray mutableCopy]];
}

/// model转化为字典
- (NSMutableDictionary *)dicFromObject:(NSObject *)object {
    NSMutableDictionary *dic = [NSMutableDictionary dictionary];
    unsigned int count;
    objc_property_t *propertyList = class_copyPropertyList([object class], &count);
 
    for (int i = 0; i < count; i++) {
        objc_property_t property = propertyList[i];
        const char *cName = property_getName(property);
        NSString *name = [NSString stringWithUTF8String:cName];
        /// valueForKey返回的数字和字符串都是对象
        NSObject *value = [object valueForKey:name];
 
        if ([value isKindOfClass:[NSString class]] || [value isKindOfClass:[NSNumber class]]) {
            /// string , bool, int ,NSinteger
            [dic setObject:value forKey:name];
 
        } else if ([value isKindOfClass:[NSArray class]] || [value isKindOfClass:[NSDictionary class]]) {
            /// 字典或字典
            [dic setObject:[self arrayOrDicWithObject:(NSArray*)value] forKey:name];
 
        } else if (value == nil) {
            /// null
            [dic setObject:@"" forKey:name];
        } else {
            //model
            [dic setObject:[self dicFromObject:value] forKey:name];
        }
    }
 
    return [dic copy];
}

/// 将可能存在model数组转化为普通数组
- (id)arrayOrDicWithObject:(id)origin {
    if ([origin isKindOfClass:[NSArray class]]) {
        //数组
        NSMutableArray *array = [NSMutableArray array];
        for (NSObject *object in origin) {
            if ([object isKindOfClass:[NSString class]] || [object isKindOfClass:[NSNumber class]]) {
                //string , bool, int ,NSinteger
                [array addObject:object];
 
            } else if ([object isKindOfClass:[NSArray class]] || [object isKindOfClass:[NSDictionary class]]) {
                //数组或字典
                [array addObject:[self arrayOrDicWithObject:(NSArray *)object]];
 
            } else {
                //model
                [array addObject:[self dicFromObject:object]];
            }
        }
 
        return [array copy];
 
    } else if ([origin isKindOfClass:[NSDictionary class]]) {
        //字典
        NSDictionary *originDic = (NSDictionary *)origin;
        NSMutableDictionary *dic = [NSMutableDictionary dictionary];
        for (NSString *key in originDic.allKeys) {
            id object = [originDic objectForKey:key];
 
            if ([object isKindOfClass:[NSString class]] || [object isKindOfClass:[NSNumber class]]) {
                //string , bool, int ,NSinteger
                [dic setObject:object forKey:key];
 
            } else if ([object isKindOfClass:[NSArray class]] || [object isKindOfClass:[NSDictionary class]]) {
                //数组或字典
                [dic setObject:[self arrayOrDicWithObject:object] forKey:key];
 
            } else {
                //model
                [dic setObject:[self dicFromObject:object] forKey:key];
            }
        }
 
        return [dic copy];
    }
 
    return [NSNull null];
}

/// 获取沙盒路径
- (NSString *)customStylesPlist {
    ///获取本地沙盒Document路径
    NSArray *documentPath = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *path = [documentPath objectAtIndex:0];
    ///在Document路径下拼接文件名字
    NSString *plistPath = [path stringByAppendingPathComponent:@"userCustomStyles/userCustomStylesDataSource"];
    return plistPath;
}


/// 写入字典
- (void)writePlist:(NSArray *)array {
    ///在Document路径下拼接文件名字
    NSString *plistPath = [self customStylesPlist];

    NSFileManager *fileManager = [NSFileManager defaultManager];
    ///检测plistPath路径是否存在
    BOOL isFile = [fileManager fileExistsAtPath:plistPath];
    if (isFile) {
        /// 文件存在 删除之后 写入
        [fileManager removeItemAtPath:plistPath error:nil];
    }
    else {
        /// 如果文件不存在需要创建文件
        BOOL isCreatDir = [fileManager createDirectoryAtPath:plistPath withIntermediateDirectories:YES attributes:nil error:nil];
        if (!isCreatDir) {
            NSLog(@"create 自定义样式Plist failed");
        }
        else {
            NSLog(@"创建自定义样式Plist成功！");
        }
    }
    NSError * error;
    BOOL isSuccess = [array writeToURL:[NSURL fileURLWithPath:plistPath] error:&error];
    
    if (isSuccess) {
        NSLog(@"写入成功!");
    }
    else {
        NSLog(@"写入失败! : %@", error);
    }
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
    return cell;
}

- (void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath {
    [((BaseTableViewCell *)cell) setWithModel:self.cellDataSource[indexPath.section][indexPath.row]];
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    MineTextTableCellModel *model = self.cellDataSource[indexPath.section][indexPath.row];
    switch (indexPath.section) {
        case 0: {
            switch (indexPath.row) {
                case 0: {
                    @weakify(self);
                    CustomStylesViewController *customStylesVC = [[CustomStylesViewController alloc] initWithNibName:[CustomStylesViewController className] bundle:nil];
                    customStylesVC.themeClickEvent = ^(NSString * _Nonnull theme) {
                        @strongify(self);
                        model.value = theme;
                        /// 更新沙盒路径下的配置数据
                        [self upDataPlist];
                        [self.tableView reloadData];
                    };
                    [self.navigationController pushViewController:customStylesVC animated:YES];
                    break;
                }
                case 1: {
                    [self showInputViewModel:model];
                    break;
                }
                case 2: case 3: {
                    
                    [self showInputViewModel:model withTipString:@"长度不超过 6" withRemoveSpaces:YES withRegex:RegexTypeFloat withtextMaxLength:6];
                    break;
                }
                    
                default:
                    break;
            }
            break;
        }
        case 1: {
            switch (indexPath.row) {
                case 0: case 1: case 3: case 5: case 10: case 11: case 12: case 13: {
                    /// 色值类型的
                    [self showInputViewModel:model];
                    break;
                }
                case 2: {
                    [self showInputViewModel:model withTipString:@"长度不超过 6" withRemoveSpaces:YES withRegex:RegexTypeFloat withtextMaxLength:6];
                    break;
                }
                case 4: {
                    [self showInputViewModel:model withTipString:@"长度不超过 6" withRemoveSpaces:YES withRegex:RegexTypeFloat withtextMaxLength:6];
                    break;
                }
                case 6: {
                    [self showInputViewModel:model withTipString:@"不超过 13 个字" withRemoveSpaces:NO withRegex:RegexTypeNormal withtextMaxLength:13];
                    
                    break;
                }
                case 7: {
                    [self showInputViewModel:model withTipString:@"不超过 13 个字" withRemoveSpaces:NO withRegex:RegexTypeNormal withtextMaxLength:13];
                    break;
                }
                case 8: {
                    [self showInputViewModel:model withTipString:@"不超过 6 个字" withRemoveSpaces:YES withRegex:RegexTypeFloat withtextMaxLength:6];
                    
                    break;
                }
                case 9: {
                    [self showInputViewModel:model withTipString:@"不超过 6 个字" withRemoveSpaces:YES withRegex:RegexTypeFloat withtextMaxLength:6];
                    break;
                }
                case 14: {
                    [self showInputViewModel:model withTipString:@"不超过 13 个字" withRemoveSpaces:NO withRegex:RegexTypeNormal withtextMaxLength:13];
                    break;
                }
                case 16: {
                    [self showInputViewModel:model withTipString:@"浮点数类型" withRemoveSpaces:YES withRegex:RegexTypeFloat withtextMaxLength:13];
                    break;
                }
                case 17: {
                    [self showInputViewModel:model withTipString:@"以“-”分隔 不超过 100 个字" withRemoveSpaces:YES withRegex:RegexTypeNormal withtextMaxLength:100];
                    break;
                }
                    
                default:
                    break;
            }
            break;
        }
        case 2: {
            [self showInputViewModel:model];
            break;
        }
        case 3: {
            [self showInputViewModel:model];
            break;
        }
        case 4: {
            switch (indexPath.row) {
                case 0: case 6: {
                    [self showInputViewModel:model withTipString:@"长度不超过 6" withRemoveSpaces:YES withRegex:(RegexTypeFloat) withtextMaxLength:6];
                    break;
                }
                case 1: case 2: case 7: case 10: {
                    [self showInputViewModel:model withTipString:@"长度不超过 6" withRemoveSpaces:NO withRegex:(RegexTypeNormal) withtextMaxLength:6];
                    break;
                }
                case 3: {
                    [self showInputViewModel:model withTipString:@"宽,高 以英文\",\"分隔" withRemoveSpaces:NO withRegex:(RegexTypeNormal) withtextMaxLength:7];
                    break;
                }
                case 4: case 8: case 9: {
                    [self showInputViewModel:model];
                    break;
                }
                case 5: {
                    [self showInputViewModel:model withTipString:@"相对于控件右下角,仅right/bottom有效" withRemoveSpaces:NO withRegex:(RegexTypeNormal) withtextMaxLength:6];
                    break;
                }
                case 11: {
                    [self showInputViewModel:model withTipString:@"0表示“NO”,1表示“YES”" withRemoveSpaces:NO withRegex:(RegexTypeNormal) withtextMaxLength:6];
                    break;
                }
                    
                default:
                    break;
            }
            
        }
        default:
            break;
    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    MineTextTableCellModel *model = self.cellDataSource[indexPath.section][indexPath.row];
    if (model.value.length) {
        /// +10是上下需要间距
        CGFloat height = [model.value contentHeightWithFont:[UIFont systemFontOfSize:16.0] width:kScreenWidth/2.0]+10.0;
        return MAX(45.0f, height);
    }
    return 45.f;
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    UIView * headView = [[UIView alloc] initWithFrame:(CGRectMake(0, 0, SCREEN_WIDTH, 30))];
    headView.backgroundColor = kHexColor(0xFFFFFF);
    UILabel * label = [[UILabel alloc] initWithFrame:(CGRectMake(20, 0, headView.width-20, 30))];
    label.textColor = kHexColor(0x8C8C8C);
    label.font = [UIFont systemFontOfSize:14];
    label.text = self.headDataSource[section];
    [headView addSubview:label];
    return headView;
}

/// 色值类型的配置输入框
- (void)showInputViewModel:(MineTextTableCellModel *)model {
    
    MineConfigInputView * inputView = [[MineConfigInputView alloc] initWithFrame:(CGRectMake(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT))];
    inputView.titleString = model.title;
    inputView.textString = model.value.length ? model.value : @"#";
    inputView.tipString = @"16 进制色值";
    inputView.textMaxLength = 9;
    inputView.regex = RegexTypeColor;
    inputView.tipTextColor = [UIColor colorWithHexString:@"#8C8C8C"];
    @weakify(self);
    inputView.action = ^(NSString * _Nonnull string) {
        @strongify(self);
        model.value = string;
        /// 同步设置SDK的数据
        [self customInfoTitle:model.title withValue:string];
        /// 更新沙盒路径下的配置数据
        [self upDataPlist];
        [self.tableView reloadData];
    };
    [inputView show];
    
    
    
}

/// 文案和数字类型的配置输入框
/// - Parameters:
///   - model: 数据model
///   - tipString: 提示文案
///   - removeSpaces: 是否去除所有空格
///   - regex: 是否检验正则
///   - textMaxLength: 最大输入字数长度
- (void)showInputViewModel:(MineTextTableCellModel *)model
             withTipString:(NSString *)tipString
          withRemoveSpaces:(BOOL)removeSpaces
                 withRegex:(RegexType)regex
         withtextMaxLength:(NSInteger)textMaxLength {
    MineConfigInputView * inputView = [[MineConfigInputView alloc] initWithFrame:(CGRectMake(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT))];
    inputView.titleString = model.title;
    inputView.textString = model.value.length ? model.value : @"";
    inputView.tipString = tipString;
    inputView.textMaxLength = textMaxLength;
    inputView.regex = regex;
    inputView.removeSpaces = removeSpaces;
    inputView.tipTextColor = [UIColor colorWithHexString:@"#8C8C8C"];
    inputView.action = ^(NSString * _Nonnull string) {
        model.value = string;
        [self customInfoTitle:model.title withValue:string];
        
        /// 更新沙盒路径下的配置数据
        [self upDataPlist];
        [self.tableView reloadData];
    };
    [inputView show];
}

- (void)customInfoTitle:(NSString *)title withValue:(NSString *)value {
    if ([title isEqualToString:@"时间色值"]) {
        [TOSKitCustomInfo shareCustomInfo].Chat_time_textColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"客服/机器人昵称显示"]) {
        [TOSKitCustomInfo shareCustomInfo].Chat_tosRobotName_show = [value isEqualToString:@"1"] ? YES : NO;
    }
    else if ([title isEqualToString:@"访客昵称显示"]) {
        [TOSKitCustomInfo shareCustomInfo].Chat_visitorName_show = [value isEqualToString:@"1"] ? YES : NO;
    }
    else if ([title isEqualToString:@"客服/机器人头像显示"]) {
        [TOSKitCustomInfo shareCustomInfo].Chat_tosRobot_portrait_enable = [value isEqualToString:@"1"] ? YES : NO;
    }
    else if ([title isEqualToString:@"访客头像显示"]) {
        [TOSKitCustomInfo shareCustomInfo].Chat_visitor_portrait_enable = [value isEqualToString:@"1"] ? YES : NO;
    }
    else if ([title isEqualToString:@"输入区域背景色值"]) {
        [TOSKitCustomInfo shareCustomInfo].ChatBox_backGroundColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"输入框背景色值"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_textView_backgroundColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"输入区分割线"]) {
        [TOSKitCustomInfo shareCustomInfo].ChatBox_lineColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"输入区分割线高度"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_topLineHeight = [value floatValue];
    }
    else if ([title isEqualToString:@"语音按钮色值"]) {
        [TOSKitCustomInfo shareCustomInfo].VoiceButton_textColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"语音按钮默认文案"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_talkText = value;
    }
    else if ([title isEqualToString:@"语音按钮长按文案"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_talkHighlightedText = value;
    }
    else if ([title isEqualToString:@"语音按钮文案字体"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_talkFont = [UIFont boldSystemFontOfSize:[value floatValue]];
    }
    else if ([title isEqualToString:@"语音按钮边框"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_talk_borderWidth = [value floatValue];
    }
    else if ([title isEqualToString:@"语音按钮边框颜色"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_talk_borderColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"录制语音的按钮背景颜色"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_talk_backgroundColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"录制语音的按钮按住背景颜色"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_talk_backgroundHighlightedColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"录制语音的按钮按住字体颜色"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_talk_fontHighlightedColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"输入框暗文提示语"]) {
        [TOSKitCustomInfo shareCustomInfo].ChatBox_textview_placeholder = value;
    }
    else if ([title isEqualToString:@"输入框暗文提示语色值"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_textview_placeholderTextColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"输入区域的快捷入口"]) {
        if (value.length) {
            [TOSKitCustomInfo shareCustomInfo].quickEntryAllItems = [value componentsSeparatedByString:@"-"];
        } else {
            [TOSKitCustomInfo shareCustomInfo].quickEntryAllItems = @[];
        }
    }
    else if ([title isEqualToString:@"语音按钮文案字体"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_talkFont = [UIFont systemFontOfSize:[value floatValue]];
    }
    else if ([title isEqualToString:@"吐司提示背景色值"]) {
        [TOSKitCustomInfo shareCustomInfo].Toast_backGroundColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"吐司提示文字色值"]) {
        [TOSKitCustomInfo shareCustomInfo].Toast_textColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"相册导航栏背景色值"]) {
        [TOSKitCustomInfo shareCustomInfo].imagePicker_naviBgColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"相册导航栏文字色值"]) {
        [TOSKitCustomInfo shareCustomInfo].imagePicker_barItemTextColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"聊天窗口UI样式"]) {
        NSArray <NSDictionary *>*array = [NSArray readPlistFileWithFileName:@"CustomStylesDataSource"];
        NSArray * modelArray = [[NSArray modelArrayWithClass:[CustomStylesModel class] json:array] mutableCopy];
        CustomStylesModel * model = modelArray[0];
        [TOSKitCustomInfo shareCustomInfo].senderBubble_backGround = [self colorWithHexString:model.senderBubble_backGround alpha:1.f];
        [TOSKitCustomInfo shareCustomInfo].senderBubble_cornerRadius = [model.senderBubble_cornerRadius doubleValue];
        [TOSKitCustomInfo shareCustomInfo].receiveBubble_backGround = [self colorWithHexString:model.receiveBubble_backGround alpha:1.f];
        [TOSKitCustomInfo shareCustomInfo].receiveBubble_cornerRadius = [model.receiveBubble_cornerRadius doubleValue];
        [TOSKitCustomInfo shareCustomInfo].chat_backGround = [self colorWithHexString:model.chat_backGround alpha:1.f];
        [TOSKitCustomInfo shareCustomInfo].portrait_cornerRadius = [model.portrait_cornerRadius doubleValue];
        
    }
    else if ([title isEqualToString:@"删除按钮的圆角"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_deleteButton_cornerRadius = [value floatValue];
    }
    else if ([title isEqualToString:@"发送按钮文案"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButton_text = value;
    }
    else if ([title isEqualToString:@"发送按钮高亮文案"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButton_textHighlighted = value;
    }
    else if ([title isEqualToString:@"发送按钮大小"]) {
        NSArray * size = [value componentsSeparatedByString:@","];
        [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButtonSize = CGSizeMake([size[0] floatValue], [size[1] floatValue]);
    }
    else if ([title isEqualToString:@"发送按钮背景色"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButtonBackGroundColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"发送按钮布局时的外边距"]) {
        NSArray * margins = [value componentsSeparatedByString:@","];
        if (margins.count != 2) {
            [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButtonMargins = UIEdgeInsetsMake(0, 0, 16.0, 16.0);
            return;
        }
        else {
            [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButtonMargins = UIEdgeInsetsMake(0, 0, [margins[0] floatValue], [margins[1] floatValue]);
        }
    }
    else if ([title isEqualToString:@"发送按钮的圆角"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButton_cornerRadius = [value floatValue];
    }
    else if ([title isEqualToString:@"发送按钮字体"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButtonFont = [UIFont fontWithName:@"PingFangSC-Regular" size:[value floatValue]];
    }
    else if ([title isEqualToString:@"发送按钮默认的文字颜色"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButton_textColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"发送按钮高亮的文字颜色"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_sendButton_textHighlightedColor = [UIColor colorWithHexString:value];
    }
    else if ([title isEqualToString:@"分页控件距离底部的间距"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_pageControlMarginBottom = [value floatValue];
    }
    else if ([title isEqualToString:@"是否在删除和发送按钮下方显示表情"]) {
        [TOSKitCustomInfo shareCustomInfo].chatBox_emotion_functionItemDisplayed = [value boolValue];
    }
    
    
    
    
}

#pragma mark - 初始化
- (BaseTableView *)setupTableView {
    BaseTableView *tableView = [[BaseTableView alloc] initWithFrame:CGRectMake(0.f, 0.f, kWindowWidth, kWindowHeight - kBottomBarHeight - kNavTop) style:(UITableViewStyleGrouped)];
    [self.view addSubview:tableView];
    tableView.delegate = self;
    tableView.dataSource = self;
    tableView.sectionHeaderHeight = 30.f;
    tableView.sectionFooterHeight = 7.f;
    tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    return tableView;
}

- (NSMutableArray *)cellDataSource {
    if (!_cellDataSource) {
        _cellDataSource = [NSMutableArray arrayWithObjects:[NSMutableArray array], [NSMutableArray array], [NSMutableArray array], [NSMutableArray array], [NSMutableArray array], nil];
        NSString * plistPath = [self customStylesPlist];
        NSFileManager *fileManager = [NSFileManager defaultManager];
        
        ///检测plistPath路径是否存在
        BOOL isFile = [fileManager fileExistsAtPath:plistPath];
        /// 如果存在就读取配置的Plist数据
        if (isFile) {
            
            NSMutableArray * array = [NSMutableArray arrayWithContentsOfURL:[NSURL fileURLWithPath:plistPath]];
            NSLog(@"读取沙盒配置:%@", array);
            for (int i = 0; i < array.count; i++) {
                NSArray * nArray = array[i];
                for (NSDictionary * dic in nArray) {
                    [_cellDataSource[i] addObject:[MineTextTableCellModel yy_modelWithJSON:dic]];
                }
            }
        }
        else {
            /// 获取文件路径
            NSString *path = [[NSBundle mainBundle] pathForResource:@"superCustomStylesDataSource" ofType:@"plist"];
            NSMutableArray * array = [NSMutableArray arrayWithContentsOfFile:path];
            for (int i = 0; i < array.count; i++) {
                NSArray * nArray = array[i];
                NSLog(@"每组的数据：%@", nArray);
                for (NSDictionary * dic in nArray) {
                    [_cellDataSource[i] addObject:[MineTextTableCellModel yy_modelWithJSON:dic]];
                }
            }
            NSLog(@"读取默认的文件配置：%@", _cellDataSource);
            
            /// 如果文件不存在需要创建文件
            BOOL isCreatDir = [fileManager createDirectoryAtPath:plistPath withIntermediateDirectories:YES attributes:nil error:nil];
            if (!isCreatDir) {
                NSLog(@"create 自定义样式Plist failed");
            }
            else {
                NSLog(@"创建自定义样式Plist成功！");
                /// 更新沙盒路径下的配置数据
                [self upDataPlist];
                [DomainNameSave shareDomainNameSave].index = 0;
            }
            
        }
        
        
        
        
    }
    return _cellDataSource;
}

- (void)blackClickEvent {
    [self.navigationController popViewControllerAnimated:YES];
}

- (void)resetConfigClickEvent{
    NSLog(@"resetConfigClickEvent");
    //弹窗
    UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"恢复默认配置" message:@"确认要恢复默认配置吗，恢复后已修改的配置项将不会保存。" delegate:self cancelButtonTitle:@"取消" otherButtonTitles:@"确定", nil];
    
    [alertView show];
}

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex//点击弹窗按钮后
{
    NSLog(@"buttonIndex:%ld",(long)buttonIndex);
 
    if (buttonIndex == 0) {//取消
        NSLog(@"取消");
    }else if (buttonIndex == 1){//确定
        NSLog(@"确定");
        [self resetConfigParams];
    }
}

-(void)resetConfigParams{
    [self.cellDataSource removeAllObjects];
    self.cellDataSource = [NSMutableArray arrayWithObjects:[NSMutableArray array], [NSMutableArray array], [NSMutableArray array], [NSMutableArray array], nil];
    /// 获取文件路径
    NSString *path = [[NSBundle mainBundle] pathForResource:@"superCustomStylesDataSource" ofType:@"plist"];
    NSMutableArray * array = [NSMutableArray arrayWithContentsOfFile:path];
    for (int i = 0; i < array.count; i++) {
        NSArray * nArray = array[i];
        NSLog(@"每组的数据：%@", nArray);
        for (NSDictionary * dic in nArray) {
            [_cellDataSource[i] addObject:[MineTextTableCellModel yy_modelWithJSON:dic]];
        }
    }
    /// 更新沙盒路径下的配置数据
    [self upDataPlist];
    [DomainNameSave shareDomainNameSave].index = 0;
    [self.tableView reloadData];
    
    self.cellDataSource = nil;
    [self.tableView reloadData];
    
    for (NSArray * nArray in self.cellDataSource) {
        for (MineTextTableCellModel * nModel in nArray) {
            /// 同步设置
            [self customInfoTitle:nModel.title withValue:nModel.value];
        }
    }
    
    return;
    superCustomStylesModel *model = self.dataSource[0];
    NSLog(@"model = %@", [model yy_modelToJSONObject]);
    
    [TOSKitCustomInfo shareCustomInfo].Chat_time_textColor = [self colorWithHexString:model.Chat_time_textColor alpha:1.f];
    [TOSKitCustomInfo shareCustomInfo].Chat_tosRobotName_enable = model.Chat_tosRobotName_enable;
    [TOSKitCustomInfo shareCustomInfo].Chat_visitorName_enable = model.Chat_visitorName_enable;
    [TOSKitCustomInfo shareCustomInfo].Chat_tosRobot_portrait_enable = model.Chat_tosRobot_portrait_enable;
    [TOSKitCustomInfo shareCustomInfo].Chat_visitor_portrait_enable = model.Chat_visitor_portrait_enable;
    [TOSKitCustomInfo shareCustomInfo].ChatBox_backGroundColor = [self colorWithHexString:model.ChatBox_backGroundColor alpha:1.0f];
    [TOSKitCustomInfo shareCustomInfo].ChatBox_lineColor = [self colorWithHexString:model.ChatBox_lineColor alpha:1.0f];

    [TOSKitCustomInfo shareCustomInfo].VoiceButton_textColor = [self colorWithHexString:model.VoiceButton_textColor alpha:1.0f];
    [TOSKitCustomInfo shareCustomInfo].ChatBox_textview_placeholder = model.ChatBox_textview_placeholder;
    [TOSKitCustomInfo shareCustomInfo].Toast_backGroundColor = [self colorWithHexString:model.Toast_backGroundColor alpha:1.0f];
    [TOSKitCustomInfo shareCustomInfo].Toast_textColor = [self colorWithHexString:model.Toast_textColor alpha:1.0f];
    [TOSKitCustomInfo shareCustomInfo].imagePicker_naviBgColor = [self colorWithHexString:model.imagePicker_naviBgColor alpha:1.0f];
    [TOSKitCustomInfo shareCustomInfo].imagePicker_barItemTextColor = [self colorWithHexString:model.imagePicker_barItemTextColor alpha:1.0f];
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
