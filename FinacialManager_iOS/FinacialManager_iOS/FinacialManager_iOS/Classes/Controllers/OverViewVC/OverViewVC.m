//
//  OverViewVC.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/22/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "OverViewVC.h"

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

@end
