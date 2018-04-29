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
@property (nonatomic, strong) IBOutlet UIView *vCategory;
@property (nonatomic, strong) IBOutlet UIView *vCategoryHeader;

// VDetail
@property (nonatomic, strong) IBOutlet UIView *vDetail;
@property (nonatomic, strong) IBOutlet UILabel *lblDate;
@property (nonatomic, strong) IBOutlet UITextView *tvDetail;

// VState
@property (nonatomic, strong) IBOutlet UIView *vState;
@property (nonatomic, strong) IBOutlet UITextField *tfOrigin;
@property (nonatomic, strong) IBOutlet UILabel *lblTitleState;
@property (nonatomic, strong) IBOutlet UIButton *btnNev;
@property (nonatomic, strong) IBOutlet UIButton *btnPos;


@property (nonatomic, strong) IBOutlet UICollectionView *clvCategory;

@property (nonatomic, strong) IBOutlet UIPageControl *pageCategory;

@property (nonatomic, assign) NSInteger type;
@property (nonatomic, assign) NSInteger typeJars;


@end
