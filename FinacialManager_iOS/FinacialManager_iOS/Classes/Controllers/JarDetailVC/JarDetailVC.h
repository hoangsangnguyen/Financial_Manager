//
//  JarDetailVC.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 4/11/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseVC.h"

#import "JarDto.h"

@interface JarDetailVC : BaseVC

@property (nonatomic, weak) IBOutlet UIView *vHeaderView;
@property (nonatomic, weak) IBOutlet BaseTBV * tbvContent;

@property (nonatomic, weak) IBOutlet UIView *vNav;
@property (nonatomic, weak) IBOutlet UIView *vHeaderTbv;
@property (nonatomic, weak) IBOutlet UIImageView *imgHeaderView;
@property (nonatomic, weak) IBOutlet UIImageView *imgAvatar;

@property (nonatomic, weak) IBOutlet UIButton *btnBack;
@property (nonatomic, weak) IBOutlet UIButton *btnReload;
@property (nonatomic, weak) IBOutlet UIButton *btnShare;

@property (nonatomic, weak) IBOutlet UILabel *lblHeader;

@property (weak, nonatomic) IBOutlet UIVisualEffectView *vEff;

@property (nonatomic, weak) IBOutlet UIButton *btnIncomes;
@property (nonatomic, weak) IBOutlet UIButton *btnDebts;
@property (nonatomic, weak) IBOutlet UIButton *btnSpendings;
@property (nonatomic, weak) IBOutlet UIView *vIncomes;
@property (nonatomic, weak) IBOutlet UIView *vDebts;
@property (nonatomic, weak) IBOutlet UIView *vSpendings;

@property (nonatomic, weak) IBOutlet UILabel *lblTitleHeaderTBV;
@property (nonatomic, weak) IBOutlet UILabel *lblDescAvaiable;
@property (nonatomic, weak) IBOutlet UILabel *lblDesSpendings;
@property (nonatomic, weak) IBOutlet UILabel *lblDesIncomes;


@property (nonatomic, strong) JarDto *jarDto;

@end
