//
//  LoginInfoSave.m
//  mobileCMS
//
//  Created by 赵言 on 2020/2/24.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "LoginInfoSave.h"

@implementation LoginInfoSave

- (instancetype)init {
    self = [super init];
    if (self) {
        NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
//        self.enterpriseNumbers = [userDefaults objectForKey:kEnterpriseNumbers]?:@"";
//        self.seatingWorkNumber = [userDefaults objectForKey:kSeatingWorkNumber]?:@"";
    }
    return self;
}

- (void)saveData {
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
//    [userDefaults setObject:self.enterpriseNumbers?:@"" forKey:kEnterpriseNumbers];
//    [userDefaults setObject:self.seatingWorkNumber?:@"" forKey:kSeatingWorkNumber];
    [userDefaults synchronize];
}

@end
