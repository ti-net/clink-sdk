//
//  LoginViewController.m
//  TOSClientKitDemo
//
//  Created by 言 on 2022/6/29.
//

#import "LoginViewController.h"

#import "UIButton+EnlargeEdge.h"

@interface LoginViewController ()

@property (weak, nonatomic) IBOutlet UIButton *loginBtn;
@property (weak, nonatomic) IBOutlet UITextField *enterpriseIdTextF;
@property (weak, nonatomic) IBOutlet UITextField *accessIdTextF;
@property (weak, nonatomic) IBOutlet UITextField *accessSecretTextF;
@property (weak, nonatomic) IBOutlet UIButton *accessSecretBtn;

@end

@implementation LoginViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    NSArray *testFieldSignalArr = @[
        self.enterpriseIdTextF.rac_textSignal,
        self.accessIdTextF.rac_textSignal,
        self.accessSecretTextF.rac_textSignal];
    
    RACSignal *single = [RACSignal combineLatest:testFieldSignalArr];
    
    @weakify(self);
    [single subscribeNext:^(id  _Nullable x) {
        @strongify(self);
        NSString *enterpriseIdNum = self.enterpriseIdTextF.text;
        NSString *accessIdNum = self.accessIdTextF.text;
        NSString *accessSecretNum = self.accessSecretTextF.text;
        
        if (enterpriseIdNum.length > 0 &&
            accessIdNum.length > 0 &&
            accessSecretNum.length > 0) {
            
            self.loginBtn.enabled = YES;
            self.loginBtn.backgroundColor = kHexColor(0x4385FF);
        } else {
            self.loginBtn.enabled = NO;
            self.loginBtn.backgroundColor = kHexColor(0xBDD5FF);
        }
    }];
    
    [self.accessSecretBtn setEnlargeEdge:10.f];
}

- (IBAction)didClickLoginBtnAction:(UIButton *)sender {
    
}

- (IBAction)didClickAccessSecretBtnAction:(UIButton *)sender {
    self.accessSecretTextF.secureTextEntry = sender.isSelected;
    sender.selected = !sender.selected;
}

@end
