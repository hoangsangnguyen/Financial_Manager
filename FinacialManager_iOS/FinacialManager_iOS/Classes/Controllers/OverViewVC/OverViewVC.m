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

@interface OverViewVC () <UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout> {
    ListJarDto *_listData;
}

@end

@implementation OverViewVC

- (void)viewDidLoad {
    [super viewDidLoad];
    [[self navigationController] setNavigationBarHidden:NO animated:YES];
    // Do any additional setup after loading the view.
    [self initUI];
    [self getDataFromServer];
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
    
}

#pragma mark - API
- (void)getDataFromServer {
    //[self getAllStage];
    //[self getAllJar];
}

- (void)getAllStage {
    [API getAllState:^(BOOL success, ListStatesDto *data) {
        //[App hideLoading];
        Config.listState = data;
    }];
}

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

#pragma mark - SlideNavigationController Methods -

- (IBAction)btnCaculator:(id)sender {
    CaculatorVC *vc = VCFromSB(CaculatorVC, SB_Overview);
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
    cell.lblTitle.text = data.type;
    cell.lblSubTitle.text = SF(@"%0.f",data.incomes);
    cell.imgIcon.image = IM(@"ic_jarr");
    
//    [cell.imgIcon sd_setImageWithURL:[NSURL URLWithString:data.image]
//                    placeholderImage:[UIImage imageNamed:@"ic_jarr"]];
    
    return cell;
}

- (void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath {
    JarDetailVC * vc = VCFromSB(JarDetailVC,SB_Overview);
    JarDto *data = _listData.list[indexPath.row];
    vc.jarDto = data;
    [self.navigationController pushViewController:vc animated:YES];
}

@end
