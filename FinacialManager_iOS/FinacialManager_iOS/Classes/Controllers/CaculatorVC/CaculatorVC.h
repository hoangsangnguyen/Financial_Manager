//
//  CaculatorVC.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/26/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseVC.h"

@interface CaculatorVC : BaseVC

@property (nonatomic, strong) IBOutlet UIButton *btnIncome;
@property (nonatomic, strong) IBOutlet UIButton *btnOutCome;

@property (nonatomic, strong) IBOutlet UIButton *btnNumber;

@property (nonatomic, strong) IBOutlet UIView *vConent;
@property (nonatomic, strong) IBOutlet UIView *vBackground;
@property (nonatomic, strong) IBOutlet UIView *vNext;
@property (nonatomic, strong) IBOutlet UIView *vCategory;
@property (nonatomic, strong) IBOutlet UIView *vCategoryHeader;

@property (nonatomic, strong) IBOutlet UICollectionView *clvCategory;

@property (nonatomic, strong) IBOutlet NSLayoutConstraint *contTopClvCategory;

@property (nonatomic, strong) IBOutlet UIPageControl *pageCategory;


@end
