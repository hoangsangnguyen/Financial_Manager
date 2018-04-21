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

@interface OverViewVC () <UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout, SlideNavigationControllerDelegate> {
    ListJarDto *_listData;
}

@end

@implementation OverViewVC

- (void)viewDidLoad {
    [super viewDidLoad];
    [self.navigationController setNavigationBarHidden:NO];
    // Do any additional setup after loading the view.
    [self initUI];
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
    
    UIBarButtonItem * barSave = [[UIBarButtonItem alloc] initWithImage:[[UIImage imageNamed:@"ic_add"] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal] style:UIBarButtonItemStylePlain target:self action:@selector(addIncome)];
    self.navigationItem.rightBarButtonItem = barSave;
    
    
    [_vAddIncome setFrame:CGRectMake(SWIDTH - 140, 55, 120, 81)];
    _vAddIncome.layer.borderWidth = 1.0f;
    _vAddIncome.layer.borderColor = [UIColor lightGrayColor].CGColor;
    [_vAddIncome setHidden:YES];
    [self.navigationController.view addSubview:_vAddIncome];
    
    _listData = Config.listJar;
    [_clvContent reloadData];
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

#pragma mark - Action

- (void)addIncome {
    [_vAddIncome setHidden:NO];
}

- (IBAction)btnCaculator:(UIButton *)btn {
    [_vAddIncome setHidden:YES];
    
    CaculatorVC *vc = VCFromSB(CaculatorVC,SB_Caculator);
    vc.type = btn.tag;
    [AppNav presentViewController:vc animated:YES completion:nil];
}

- (IBAction)btnAlert:(id)sender {
    [AlertVC showAlert:@"AAA" title:@"AAA" callback:^(BOOL hasPressOK) {

    }];
}

- (IBAction)btnAPI:(id)sender {
    [self getAllJar];
}

#pragma mark - CollectionView
- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath {
    float size = collectionView.frame.size.width/2 - 4;
    return CGSizeMake(size, size);
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

@end
