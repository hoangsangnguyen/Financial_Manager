//
//  SignInVC.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "SignInVC.h"
#import "SignUpVC.h"
#import "WelcomeVC.h"
#import "Configure.h"
#import "OverViewVC.h"

@interface SignInVC () <UITextFieldDelegate, UIGestureRecognizerDelegate> {
    
}


@end

@implementation SignInVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.

    [self initUI];
    //[_btnLogin setStyleBlueSquare];
    
    _tfEmail.returnKeyType = UIReturnKeyNext;
    _tfPassword.returnKeyType = UIReturnKeyGo;
    _tfEmail.text = @"sangnguyen1";
    _tfPassword.text =@"123";
    
}

- (void)initUI {
    UITapGestureRecognizer *gesRecognizer = [[UITapGestureRecognizer alloc]
                                             initWithTarget:self
                                             action:@selector(hiddenKeybroad)];
    gesRecognizer.delegate = self;
    
    // Add Gesture to your view.
    [self.view addGestureRecognizer:gesRecognizer];
}

- (void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    
    // Check Color
    _tfPassword.textColor = [self validPassword:_tfPassword.text] ? MAINCOLOR : RED_COLOR;
    _tfEmail.textColor = [self validEmail:_tfEmail.text] ? MAINCOLOR : RED_COLOR;
}

//- (BOOL) checkForAction {

//    NSString *email = _tfEmail.text;
//    if(![self validEmail:email]) {
//        [_tfEmail becomeFirstResponder];
//        [RMessage showNotificationWithTitle:@"Email invalid!" type:RMessageTypeError customTypeName:nil callback:nil];
//        return NO;
//    }
//
//    NSString *pass = _tfPassword.text;
//    if(![self validPassword:pass]) {
//        [_tfPassword becomeFirstResponder];
//        [RMessage showNotificationWithTitle:@"Password invalid!" type:RMessageTypeError customTypeName:nil callback:nil];
//        return NO;
//    }
//    return YES;
//}

#pragma mark Login
- (IBAction)onBtnLoginClicked:(UIButton*)btn {
    [App showLoading];
    UserDto *user = [[UserDto alloc] init];
    user.userName = _tfEmail.text;
    user.password = _tfPassword.text;
    
    [API login:user callback:^(BOOL success, UserDto *data) {
        [App hideLoading];
        if (success) {
            [Config updateUser:data];
            OverViewVC *vc = VCFromSB(OverViewVC, SB_Overview);
            [AppNav popToRootAndSwitchToViewController:vc withSlideOutAnimation:YES
                                         andCompletion:nil];
        }
    }];
}

- (IBAction)onBtnBackClicked:(UIButton *)btn {
    [self.navigationController popToRootViewControllerAnimated:YES];
}

- (IBAction)onbtnSignupPress:(UIButton *)sender{
    SignUpVC *vc = VCFromSB(SignUpVC, SB_Login);
    [self.navigationController pushViewController:vc animated:YES];
}

#pragma mark UITextField

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    NSString *str = [textField.text stringByReplacingCharactersInRange:range withString:string];
    
    if(_tfEmail == textField) {
        _tfEmail.textColor = [self validEmail:str] ? MAINCOLOR : RED_COLOR;
        
    } else if(_tfPassword == textField) {
        _tfPassword.textColor = [self validPassword:str] ? MAINCOLOR : RED_COLOR;
        
    }
    
    return YES;
}

- (BOOL)textFieldShouldReturn:(UITextField*)textField {
    
    if (_tfEmail == textField) {
        [_tfPassword becomeFirstResponder];
    } else {
        [textField resignFirstResponder];
    }
    
    return YES;
}

- (void)hiddenKeybroad {
    [_tfEmail resignFirstResponder];
    [_tfPassword resignFirstResponder];
}

#pragma mark - Other

- (BOOL)validEmail:(NSString *)email {
    if(isEmpty(email) || email.length <= 5) {
        return NO;
    }
    NSString *emailRegex = @"(\\<|^)[a-zA-Z0-9]{3,}[\\.]{0,1}[a-zA-Z0-9]+@(?:[a-zA-Z0-9]+\\.)+(\\w{2,})(\\>|$)";
    NSPredicate *emailTest = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", emailRegex];
    
    return [emailTest evaluateWithObject:email];
}

- (BOOL)validPassword:(NSString *)pass{
    if(isEmpty(pass) || pass.length < 8) {
        return NO;
    }
    
    NSString *regex = @"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*()?&])[A-Za-z\\d$@$!%*?&()-+><]{8,}";
    NSPredicate *predict = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", regex];
    return [predict evaluateWithObject:pass];
}


@end
