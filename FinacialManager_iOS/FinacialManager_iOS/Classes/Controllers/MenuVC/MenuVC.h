//
//  MenuVC.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseVC.h"
#import "BaseTBV.h"
#import "BaseCell.h"
#import "AppDelegate.h"

@interface MenuVC : BaseVC

@property (nonatomic, weak) IBOutlet BaseTBV *tbvMenu;

@property (nonatomic, weak) IBOutlet UIView *vLineMenu;
@property (nonatomic, weak) IBOutlet UIView *vLineUser;

// User
@property (nonatomic, weak) IBOutlet UILabel *lblUserName;
@property (nonatomic, weak) IBOutlet UILabel *lblEmail;
@property (nonatomic, weak) IBOutlet UIButton *btnAvatar;

@end
