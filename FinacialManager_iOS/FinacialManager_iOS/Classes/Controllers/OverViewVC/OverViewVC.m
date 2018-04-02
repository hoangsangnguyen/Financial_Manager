//
//  OverViewVC.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/22/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "OverViewVC.h"
#import "CaculatorVC.h"
#import "AlertVC.h"

@interface OverViewVC ()

@end

@implementation OverViewVC

- (void)viewDidLoad {
    [super viewDidLoad];
    [[self navigationController] setNavigationBarHidden:NO animated:YES];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - SlideNavigationController Methods -

- (BOOL)slideNavigationControllerShouldDisplayLeftMenu
{
    return YES;
}

- (IBAction)btnCaculator:(id)sender {
    CaculatorVC *vc = VCFromSB(CaculatorVC, SB_Overview);
    [AppNav presentViewController:vc animated:YES completion:nil];
}

- (IBAction)btnAlert:(id)sender {
    [AlertVC showAlert:@"AAA" title:@"AAA" callback:^(BOOL hasPressOK) {

    }];
//    [AlertVC show:self content:@"AAA" title:@"AAA" callback:^(BOOL hasPressOK) {
//
//    }];
}

@end
