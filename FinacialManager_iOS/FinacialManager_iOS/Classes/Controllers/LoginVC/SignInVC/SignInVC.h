//
//  SignInVC.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseVC.h"

@interface SignInVC : BaseVC

@property (nonatomic, weak) IBOutlet UITextField *tfEmail;
@property (nonatomic, weak) IBOutlet UITextField *tfPassword;
@property (nonatomic, weak) IBOutlet UIButton *btnLogin;
@property (nonatomic, weak) IBOutlet UIButton *btnRegister;

@property (nonatomic, strong) NSString *email;
@property (nonatomic, strong) NSString *password;


@end
