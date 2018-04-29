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
#import "API.h"
#import "StatesDto.h"
#import "JarDto.h"
#import "BaseColCell.h"
#import "JarDetailVC.h"

@interface OverViewVC () <UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout, SlideNavigationControllerDelegate, floatMenuDelegate> {
    ListJarDto *_listData;
}

@end

@implementation OverViewVC

- (void)viewDidLoad {
    [super viewDidLoad];
    [self.navigationController setNavigationBarHidden:NO];
    // Do any additional setup after loading the view.
    [self initUI];
    [self initFloatingButton];
    [_clvContent addPullRefreshAtVC:self toReloadAction:@selector(getAllJar)];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:YES];
    [self.navigationController setNavigationBarHidden:NO];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (BOOL)slideNavigationControllerShouldDisplayLeftMenu
{
    return YES;
}

#pragma mark - InitUI
- (void)initUI {
    
    UIBarButtonItem * barSave = [[UIBarButtonItem alloc] initWithImage:[[UIImage imageNamed:@"ic_summary"] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal] style:UIBarButtonItemStylePlain target:self action:@selector(selectedSummary)];
    self.navigationItem.rightBarButtonItem = barSave;
    

    _listData = Config.listJar;
    [_clvContent reloadData];
}

- (void)initFloatingButton {
    CGRect floatFrame = CGRectMake(SWIDTH - 44 - 20, SHEIGHT - 44 - 70, 44, 44);
    
     _btnFloat = [[VCFloatingActionButton alloc]initWithFrame:floatFrame normalImage:[UIImage imageNamed:@"ic_plus"] andPressedImage:[UIImage imageNamed:@"ic_cross"] withScrollview:nil];
    
    _btnFloat.imageArray = @[@"ic_jar_white",@"ic_jar_white",@"ic_jar_white",@"ic_jar_white"];
    _btnFloat.labelArray = @[@"Incomes",@"Spending",@"Debts",@"General"];
    
    
    _btnFloat.hideWhileScrolling = YES;
    _btnFloat.delegate = self;

    [self.view addSubview:_btnFloat];
}

#pragma mark - API
- (void)getAllJar {
    if (![_clvContent.refreshCtrl isRefreshing]) {
        [App showLoading];
    }
    
    [API getAllJars:^(BOOL success, ListJarDto *data) {
        [App hideLoading];
        [_clvContent hideIndicator];
        if (success) {
            Config.listJar = data;
            _listData = data;
            [_clvContent reloadData];
        }
    }];
}

#pragma mark - SlideNavigationController Methods
- (void)selectedSummary{
}

#pragma mar} - CollectionView
- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath {
    float size = collectionView.frame.size.width/2 - 4;
    return CGSizeMake(size, size+15);
}

- (UIEdgeInsets)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout insetForSectionAtIndex:(NSInteger)section {
    return UIEdgeInsetsMake(2, 2, 2, 2);
}

- (CGFloat)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout minimumInteritemSpacingForSectionAtIndex:(NSInteger)section {
    return 4;
}

- (CGFloat)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout minimumLineSpacingForSectionAtIndex:(NSInteger)section {
    return 4;
}


- (NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView {
    return 1;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section {
    return _listData.list.count;
}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
    
    JarDto *data = _listData.list[indexPath.row];
    
    BaseColCell *cell = [_clvContent dequeueReusableCellWithReuseIdentifier:@"jarCol" forIndexPath:indexPath];
    cell.lblSubTitle.text = data.type;
    cell.lblTitle.text = SF(@"%0.f USD",data.incomes);
    cell.imgIcon.image = [UIImage imageNamed:@"ic_jar"];
    
    return cell;
}

- (void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath {
    JarDetailVC * vc = VCFromSB(JarDetailVC,SB_Overview);
    JarDto *data = _listData.list[indexPath.row];
    vc.jarDto = data;
    [self.navigationController pushViewController:vc animated:YES];
}

-(void) didSelectMenuOptionAtIndex:(NSInteger)row
{
    
    CaculatorVC *vc = VCFromSB(CaculatorVC,SB_Caculator);
    vc.type = (row == 3)? 1 : 0;
    vc.typeJars = row;
    [AppNav presentViewController:vc animated:YES completion:nil];
}

@end
