//
//  CaculatorVC.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/26/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "CaculatorVC.h"
#import <QuartzCore/QuartzCore.h>

typedef enum : NSUInteger {
    Income = 0,
    OutCome,
} typeInput;
@interface CaculatorVC () {
    NSInteger _typeButton;
    NSString *_strNumber;
    NSString *_strNumberPoint;
    Boolean _typePoint;
}

@end

@implementation CaculatorVC

- (void)viewDidLoad {
    [super viewDidLoad];
    [self initUI];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - InitUI
- (void)initUI {
    // default OutCome
    _typeButton = OutCome;
    [_vBackground setBackgroundColor:Caculator_OUTCOME];
    _btnNumber.contentHorizontalAlignment = UIControlContentHorizontalAlignmentCenter;
    
    _strNumber = @"0";
    _strNumberPoint = @"0";
    _typePoint = NO;
    [self updateNumber];
}

- (void)updateNumber:(NSInteger)number {
    if (_typePoint == NO) {
        if ([_strNumber isEqualToString:@"0"]) {
            _strNumber = SF(@"%li",number);
        } else {
            _strNumber = SF(@"%@%li",_strNumber,number);
        }
    } else {
        if ([_strNumberPoint isEqualToString:@"0"]) {
            _strNumberPoint = SF(@"%li",(long)number);
        } else {
            _strNumberPoint = SF(@"%@%li",_strNumberPoint,number);
        }
    }
    [self updateNumber];
}

- (void)updateNumber {
    char strType = *((_typeButton == Income) ? "+" : "-");
    [_btnNumber setTitle:SF(@"%c%@.%@",strType,_strNumber,_strNumberPoint) forState:UIControlStateNormal];
}

#pragma mark - ActionButton

- (IBAction)selectedDismisVC:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}


-(IBAction)selectedBtnNext:(id)sender {
    [self animationNext];
}

- (IBAction)selectedNumber:(UIButton *)btn {
    [self updateNumber:btn.tag];
}

- (IBAction)selectedNumberDel:(id)sender {
    _strNumber = @"0";
    _strNumberPoint = @"0";
    [self updateNumber];
}

- (IBAction)selectedNumberPoint:(UIButton*)btn {
    _typePoint = !_typePoint;
    if (_typePoint) {
        btn.backgroundColor = GRAY_TAB_COLOR;
    } else {
        btn.backgroundColor = WHITE_COLOR;
    }
}

- (IBAction)selectedBtnIncome:(id)sender {
    [self animationSelectedBtnWith:Income];
}

- (IBAction)selectedBtnOutcome:(id)sender {
    [self animationSelectedBtnWith:OutCome];
}

#pragma mark - CustomAnimation Caculator
- (void)animationSelectedBtnWith:(int)type {
    if (type != _typeButton) {
        _typeButton = type;
        [self updateNumber];
        switch (type) {
            case Income: {
                _btnNumber.contentHorizontalAlignment = UIControlContentHorizontalAlignmentRight;
                _btnIncome.contentHorizontalAlignment = UIControlContentHorizontalAlignmentLeft;
                _btnOutCome.contentHorizontalAlignment = UIControlContentHorizontalAlignmentLeft;
                [UIView animateWithDuration:0.3 animations:^{
                    _vBackground.layer.backgroundColor = Caculator_INCOME.CGColor;
                    [self.view layoutIfNeeded];
                }];
            
            }
                break;
                
            case OutCome: {
                _btnNumber.contentHorizontalAlignment = UIControlContentHorizontalAlignmentCenter;
                _btnIncome.contentHorizontalAlignment = UIControlContentHorizontalAlignmentRight;
                _btnOutCome.contentHorizontalAlignment = UIControlContentHorizontalAlignmentRight;
                [UIView animateWithDuration:0.3 animations:^{
                    _vBackground.layer.backgroundColor = Caculator_OUTCOME.CGColor;
                    [self.view layoutIfNeeded];
                }];
            }
                break;
        }
    }
}

- (void)animationNext {
    CGRect rectBtnIncome = _btnIncome.frame;
    CGRect rectBtnOutCome = _btnOutCome.frame;
    CGRect rectBtnNumber = _btnNumber.frame;
    [UIView animateWithDuration:0.3 delay:0 options:UIViewAnimationOptionCurveLinear  animations:^{
        if (_typeButton == OutCome) {
            [_btnOutCome setFrame:CGRectMake(SWIDTH/2 - rectBtnOutCome.size.width/2 +5, 0, rectBtnOutCome.size.width, rectBtnOutCome.size.height)];
            _btnOutCome.contentHorizontalAlignment = UIControlContentHorizontalAlignmentCenter;
            [_btnIncome setHidden:YES];
        } else {
            [_btnIncome setFrame:CGRectMake(SWIDTH/2 - rectBtnIncome.size.width/2 +5, 0, rectBtnIncome.size.width, rectBtnIncome.size.height)];
            _btnIncome.contentHorizontalAlignment = UIControlContentHorizontalAlignmentCenter;
            [_btnOutCome setHidden:YES];
        }
        
        [_btnNumber setFrame:CGRectMake(rectBtnNumber.origin.x,0, rectBtnNumber.size.width, rectBtnNumber.size.height)];
        _btnNumber.contentHorizontalAlignment = UIControlContentHorizontalAlignmentCenter;
        _btnNumber.transform = CGAffineTransformScale(CGAffineTransformIdentity,0.8, 0.8);
        [self.view layoutIfNeeded];
    } completion:^(BOOL finished) {
        //code for completion
    }];

}

@end
