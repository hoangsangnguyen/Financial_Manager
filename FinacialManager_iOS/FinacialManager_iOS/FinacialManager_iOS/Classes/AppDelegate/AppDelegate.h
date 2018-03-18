//
//  AppDelegate.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Configure.h"
#import "define.h"
#import "SlideNavigationController.h"
#import <MBProgressHUD/MBProgressHUD.h>

@interface AppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;

@property (strong, nonatomic) Configure *configure;

- (void) showLoading;
- (void) hideLoading;


@end

