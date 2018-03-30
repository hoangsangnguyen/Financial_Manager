//
//  CaculatorVC.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/26/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "CaculatorVC.h"
#import <QuartzCore/QuartzCore.h>
#import "CategoryCell.h"

#define Height 150;


typedef enum : NSUInteger {
    Income = 0,
    OutCome,
} typeInput;
@interface CaculatorVC () <UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout, UIScrollViewDelegate> {
    NSInteger _typeButton;
    NSString *_strNumber;
    NSString *_strNumberPoint;
    Boolean _typePoint;
}

@end

@implementation CaculatorVC

- (void)viewDidLoad {
    [super viewDidLoad];
    [self initUI];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - InitUI
- (void)initUI {
    
    // Category
    _contTopClvCategory.constant = Height;
    [_vCategory setHidden:YES];
    [self.view layoutIfNeeded];
    
    // default OutCome
    _typeButton = OutCome;
    [_vBackground setBackgroundColor:Caculator_OUTCOME];
    _btnNumber.contentHorizontalAlignment = UIControlContentHorizontalAlignmentCenter;
    
    // UIButton Next
    _vNext.layer.borderWidth = 1;
    _vNext.layer.borderColor = GRAY_COLOR.CGColor;
    _vNext.layer.cornerRadius = 5;
    _vNext.layer.shadowColor = GRAY_COLOR.CGColor;
    _vNext.layer.shadowOpacity = 1;
    _vNext.layer.shadowOffset = CGSizeZero;
    _vNext.layer.shadowRadius = 5;
    
    // UICategory
    [_vCategory roundCornersOnTopLeft:YES topRight:YES bottomLeft:NO bottomRight:NO radius:10.0f];
    _vCategory.layer.shadowColor = GRAY_COLOR.CGColor;
    _vCategory.layer.shadowOpacity = 1;
    _vCategory.layer.shadowOffset = CGSizeZero;
    _vCategory.layer.shadowRadius = 3;


    _strNumber = @"0";
    _strNumberPoint = @"0";
    _typePoint = NO;
    [self updateNumber];
}

- (void)updateNumber:(NSInteger)number {
    if (_typePoint == NO) {
        if ([_strNumber isEqualToString:@"0"]) {
            _strNumber = SF(@"%li",number);
        } else {
            _strNumber = SF(@"%@%li",_strNumber,number);
        }
    } else {
        if ([_strNumberPoint isEqualToString:@"0"]) {
            _strNumberPoint = SF(@"%li",(long)number);
        } else {
            _strNumberPoint = SF(@"%@%li",_strNumberPoint,number);
        }
    }
    [self updateNumber];
}

- (void)updateNumber {
    char strType = *((_typeButton == Income) ? "+" : "-");
    [_btnNumber setTitle:SF(@"%c%@.%@",strType,_strNumber,_strNumberPoint) forState:UIControlStateNormal];
}

#pragma mark - ActionButton

- (IBAction)selectedDismisVC:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}


-(IBAction)selectedBtnNext:(id)sender {
    _contTopClvCategory.constant = SHEIGHT - 50;
    [self.view layoutIfNeeded];
    [_vCategory setHidden:NO];
    [_clvCategory reloadData];
    [self animationNext];
}

- (IBAction)selectedNumber:(UIButton *)btn {
    [self updateNumber:btn.tag];
}

- (IBAction)selectedNumberDel:(id)sender {
    _strNumber = @"0";
    _strNumberPoint = @"0";
    [self updateNumber];
}

- (IBAction)selectedNumberPoint:(UIButton*)btn {
    _typePoint = !_typePoint;
    if (_typePoint) {
        btn.backgroundColor = GRAY_TAB_COLOR;
    } else {
        btn.backgroundColor = WHITE_COLOR;
    }
}

- (IBAction)selectedBtnIncome:(id)sender {
    [self animationSelectedBtnWith:Income];
}

- (IBAction)selectedBtnOutcome:(id)sender {
    [self animationSelectedBtnWith:OutCome];
}

#pragma mark - CustomAnimation Caculator
- (void)animationSelectedBtnWith:(int)type {
    if (type != _typeButton) {
        _typeButton = type;
        [self updateNumber];
        switch (type) {
            case Income: {
                _btnNumber.contentHorizontalAlignment = UIControlContentHorizontalAlignmentRight;
                _btnIncome.contentHorizontalAlignment = UIControlContentHorizontalAlignmentLeft;
                _btnOutCome.contentHorizontalAlignment = UIControlContentHorizontalAlignmentLeft;
                [UIView animateWithDuration:0.3 animations:^{
                    _vBackground.layer.backgroundColor = Caculator_INCOME.CGColor;
                    [self.view layoutIfNeeded];
                }];
            
            }
                break;
                
            case OutCome: {
                _btnNumber.contentHorizontalAlignment = UIControlContentHorizontalAlignmentCenter;
                _btnIncome.contentHorizontalAlignment = UIControlContentHorizontalAlignmentRight;
                _btnOutCome.contentHorizontalAlignment = UIControlContentHorizontalAlignmentRight;
                [UIView animateWithDuration:0.3 animations:^{
                    _vBackground.layer.backgroundColor = Caculator_OUTCOME.CGColor;
                    [self.view layoutIfNeeded];
                }];
            }
                break;
        }
    }
}

- (void)animationNext {
    CGRect rectBtnIncome = _btnIncome.frame;
    CGRect rectBtnOutCome = _btnOutCome.frame;
    CGRect rectBtnNumber = _btnNumber.frame;
    _contTopClvCategory.constant = 150;
    [UIView animateWithDuration:0.3 delay:0 options:UIViewAnimationOptionCurveLinear  animations:^{
        if (_typeButton == OutCome) {
            [_btnOutCome setFrame:CGRectMake(SWIDTH/2 - rectBtnOutCome.size.width/2 +5, 0, rectBtnOutCome.size.width, rectBtnOutCome.size.height)];
            _btnOutCome.contentHorizontalAlignment = UIControlContentHorizontalAlignmentCenter;
            [_btnIncome setHidden:YES];
        } else {
            [_btnIncome setFrame:CGRectMake(SWIDTH/2 - rectBtnIncome.size.width/2 +5, 0, rectBtnIncome.size.width, rectBtnIncome.size.height)];
            _btnIncome.contentHorizontalAlignment = UIControlContentHorizontalAlignmentCenter;
            [_btnOutCome setHidden:YES];
        }
        
        [_btnNumber setFrame:CGRectMake(rectBtnNumber.origin.x,0, rectBtnNumber.size.width, rectBtnNumber.size.height)];
        _btnNumber.contentHorizontalAlignment = UIControlContentHorizontalAlignmentCenter;
        _btnNumber.transform = CGAffineTransformScale(CGAffineTransformIdentity,0.8, 0.8);
        [self.view layoutIfNeeded];
    } completion:^(BOOL finished) {
    }];
    
}


#pragma mark UICollectionView
#pragma mark - CollectionView

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath {
    float size = collectionView.frame.size.height/3 - 10;
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
    return 20;
}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
    CategoryCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"CategoryCell" forIndexPath:indexPath];
    cell.lblCategory.text = SF(@"Category %ld",indexPath.row);
    
    return cell;
}

- (void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath {

}

#pragma mark UIScrollView
- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
    [self updatePageWithScrollView:scrollView];
}

- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView {
    [self updatePageWithScrollView:scrollView];
}

- (void)updatePageWithScrollView:(UIScrollView *)scr {
    CGFloat w = scr.frame.size.width;
    NSInteger scrollIndex  = (scr.contentOffset.x + w/2) / w;
    _pageCategory.currentPage = scrollIndex;
}

@end
