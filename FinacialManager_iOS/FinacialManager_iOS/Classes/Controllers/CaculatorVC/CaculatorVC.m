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

#pragma mark InitUI
- (void)initUI {
    [_vBackground setBackgroundColor:Caculator_OUTCOME];
    _lblNumber.text = @"0.00";
}

- (IBAction)selectBtnNext:(id)sender {
    
}


#pragma CustomButton Caculator

- (IBAction)selectedNumber:(id)sender {
    [self animationSelectedBtnWith:OutCome];
}

- (IBAction)selectedNumberDel:(id)sender {
    [self animationSelectedBtnWith:OutCome];
}

- (IBAction)selectedNumberPoint:(id)sender {
    [self animationSelectedBtnWith:OutCome];
}

- (IBAction)selectedBtnIncome:(id)sender {
    [self animationSelectedBtnWith:Income];
}

- (IBAction)selectedBtnOutcome:(id)sender {
    [self animationSelectedBtnWith:OutCome];
}

- (void)animationSelectedBtnWith:(int)type {
    if (type != _typeButton) {
        _typeButton = type;
        
        switch (type) {
            case Income: {
                _lblNumber.textAlignment = NSTextAlignmentCenter;
                _btnIncome.contentHorizontalAlignment = UIControlContentHorizontalAlignmentLeft;
                _btnOutCome.contentHorizontalAlignment = UIControlContentHorizontalAlignmentLeft;
                [UIView animateWithDuration:0.3 animations:^{
                    _vBackground.layer.backgroundColor = Caculator_INCOME.CGColor;
                    [self.view layoutIfNeeded];
                }];
            
            }
                break;
                
            case OutCome: {
                _lblNumber.textAlignment = NSTextAlignmentRight;
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

@end
