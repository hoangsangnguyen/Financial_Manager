//
//  SignUpVC.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "SignUpVC.h"
#import "WelcomeVC.h"
#import "SignInVC.h"
#import "AppDelegate.h"
#import "UserDto.h"

@interface SignUpVC () <UITextFieldDelegate, UIGestureRecognizerDelegate> {
    
}


@end

@implementation SignUpVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    [self initUI];
    //[_btnRegister setStyleBlueSquare];
    
    _tfEmail.returnKeyType = UIReturnKeyNext;
    _tfFristName.returnKeyType = UIReturnKeyNext;
    _tfLastName.returnKeyType = UIReturnKeyNext;
    _tfPhone.returnKeyType = UIReturnKeyNext;
    _tfUserName.returnKeyType = UIReturnKeyNext;
    _tfPassword.returnKeyType = UIReturnKeyNext;
    _tfRePassword.returnKeyType = UIReturnKeyGo;
    
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

- (BOOL) checkForAction {
    
    NSString *str ;
    
    NSString *email = _tfEmail.text;
    if(![self validEmail:email]) {
        [_tfEmail becomeFirstResponder];
        str = @"Email invalid.";
    }
    
    NSString *pass = _tfPassword.text;
    if(![self validPassword:pass]) {
        [_tfPassword becomeFirstResponder];
        str = @"Your password must contain at least eight characters including one uppercase letter, one special character and alphanumeric characters.";
    }
    
    NSString *repass = _tfRePassword.text;
    if(![repass isEqualToString:pass]) {
        [_tfRePassword becomeFirstResponder];
        str = @"Your password must contain at least eight characters including one uppercase letter, one special character and alphanumeric characters.";
    }
    
    if ([str isEqualToString:@""] || str == nil) {
        return YES;
    } else {
//        [RMessage showNotificationWithTitle:str type:RMessageTypeError customTypeName:nil callback:nil];
        return NO;
    }
}

#pragma mark Login
- (IBAction)onBtnLoginClicked:(UIButton*)btn {
    SignInVC *vc = VCFromSB(SignInVC, SB_Login);
    [self.navigationController pushViewController:vc animated:YES];
}

- (IBAction)onBtnBackClicked:(UIButton *)btn {
    [self.navigationController popViewControllerAnimated:YES];
}

- (IBAction)onbtnSignupPress:(UIButton *)sender{
    [App showLoading];
    
    UserDto *user = [[UserDto alloc] init];
    user.email = _tfEmail.text;
    user.password = _tfPassword.text;
    user.firstName = _tfFristName.text;
    user.lastName = _tfLastName.text;
    user.phone = _tfPhone.text;
    user.userName = _tfUserName.text;
    
    [API registerAccount:user callback:^(BOOL success, id data) {
        [App hideLoading];
        if (success) {
            
            UIAlertController *alert = [UIAlertController alertControllerWithTitle:@"Notification" message:@"Register Successfully!" preferredStyle:UIAlertControllerStyleActionSheet];
            UIAlertAction *action = [UIAlertAction actionWithTitle:@"Done" style:UIAlertActionStyleDefault handler:^(UIAlertAction *action) {
                SignInVC *vc = VCFromSB(SignInVC, SB_Login);
                [self.navigationController pushViewController:vc animated:YES];
            }];
            
            [alert addAction:action];
            [self presentViewController:alert animated:YES completion:nil];
        }
    }];
}

#pragma mark UITextField

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    NSString *str = [textField.text stringByReplacingCharactersInRange:range withString:string];
    if(_tfEmail == textField) {
        _tfEmail.textColor = [self validEmail:str] ? MAINCOLOR : RED_COLOR;
        
    } else if(_tfPassword == textField) {
        _tfPassword.textColor = [self validPassword:str] ? MAINCOLOR : RED_COLOR;
        
    } else if(_tfRePassword == textField) {
        _tfRePassword.textColor = [str isEqualToString:_tfPassword.text] ? MAINCOLOR : RED_COLOR;
    }
    return YES;
}

- (BOOL)textFieldShouldReturn:(UITextField*)textField {
    
    if (_tfFristName == textField ) {
        [_tfLastName becomeFirstResponder];
        
    } else if (_tfLastName == textField) {
        [_tfEmail becomeFirstResponder];
        
    } else if (_tfEmail == textField) {
        [_tfPhone becomeFirstResponder];
        
    } else if (_tfPhone == textField) {
        [_tfUserName becomeFirstResponder];
        
    } else if (_tfUserName == textField) {
        [_tfPassword becomeFirstResponder];
        
    } else if(_tfPassword == textField) {
        [_tfRePassword becomeFirstResponder];
    } else {
        [textField resignFirstResponder];
    }
    return YES;
}

- (void)hiddenKeybroad {
    [_tfFristName resignFirstResponder];
    [_tfLastName resignFirstResponder];
    [_tfPhone resignFirstResponder];
    [_tfUserName resignFirstResponder];
    [_tfEmail resignFirstResponder];
    [_tfPassword resignFirstResponder];
    [_tfRePassword resignFirstResponder];
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
