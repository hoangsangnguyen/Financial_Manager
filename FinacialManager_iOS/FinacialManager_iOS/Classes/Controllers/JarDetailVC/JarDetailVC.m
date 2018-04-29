//
//  JarDetailVC.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 4/11/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "JarDetailVC.h"
#import "UIView+Util.h"
#import "JarDetailCell.h"
#import "DetailJarDto.h"
#import "NSDate+NSDateEx.h"


#define offset_HeaderStop 160
#define offset_B_LabelHeader 160
#define distance_W_LabelHeader 50

typedef enum : NSUInteger {
    Income = 0,
    Debt,
    Spending,
} Segment;

@interface JarDetailVC () <UITableViewDataSource, UITableViewDelegate, UIWebViewDelegate, UIScrollViewDelegate> {
    BOOL isSelected;
    int indexSegment;
    ListDetailJarDto *_listData;
    NSMutableArray *_arrData;
}

@end

@implementation JarDetailVC

- (void)viewDidLoad
{
    [self.navigationController setNavigationBarHidden:YES];
    [super viewDidLoad];
    [self initUIHeader];
    [self initVar];
    [self getDataFromServer];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:YES];
    [self.navigationController setNavigationBarHidden:YES];
}

- (void)initUIHeader {
    [_imgAvatar roundCornersOnTopLeft:YES topRight:YES bottomLeft:YES bottomRight:YES radius:_imgAvatar.frame.size.height/2];
    [_vHeaderTbv setFrame:CGRectMake(0, 0, SWIDTH, 280)]; // include (160 + size View 80)
    _tbvContent.tableHeaderView = _vHeaderTbv;
    //[_imgHeaderView setImage:IM(@"bg_jar")];
    _lblDescAvaiable.text = SF(@"%0.f$",_jarDto.avaiableAmount);
    _lblDesIncomes.text = SF(@"%0.f$",_jarDto.incomes);
    _lblDesSpendings.text = SF(@"%0.f$",_jarDto.spendings);
    _lblTitleHeaderTBV.text = _jarDto.type;
    _lblHeader.text = _jarDto.type;

    _vHeaderView.clipsToBounds = YES;
}
- (void)initVar {
    
    [_tbvContent reloadData];
}

- (void)initUISegment {
    [_btnDebts setTitleColor:[UIColor darkGrayColor] forState:UIControlStateNormal];
    [_btnIncomes setTitleColor:[UIColor darkGrayColor] forState:UIControlStateNormal];
    [_btnSpendings setTitleColor:[UIColor darkGrayColor] forState:UIControlStateNormal];
    
    [_vIncomes setHidden:YES];
    [_vSpendings setHidden:YES];
    [_vDebts setHidden:YES];
}

- (void)getDataFromServer {
    switch (indexSegment) {
        case Income: {
            [API getIncomeJarDetail:_jarDto callback:^(BOOL success, ListDetailJarDto *data) {
                _listData = data;
                [self sortData];
            }];
        }
            break;
            
        case Spending: {
            [API getSpendingsJarDetail:_jarDto callback:^(BOOL success, ListDetailJarDto *data) {
                _listData = data;
                [self sortData];
            }];
        }
            break;
            
        case Debt: {
            [API getDebtsJarDetail:_jarDto callback:^(BOOL success, ListDetailJarDto *data) {
                _listData = data;
                [self sortData];
            }];
        }
            break;
    }

}

- (void)sortData {
    _arrData = [[NSMutableArray alloc] init];
    ListDetailJarDto *dtoList = [[ListDetailJarDto alloc] init];
    for (int i = 0; i<_listData.list.count; i++) {
        DetailJarDto *dto = _listData.list[i];
        if (dtoList.list.count == 0) {
            [dtoList.list addObject:dto];
        } else {
            if ([dtoList.list.firstObject.date isEqualExactDay:dto.date]) {
                [dtoList.list addObject:dto];
            } else {
                [_arrData addObject:dtoList];
                dtoList = [[ListDetailJarDto alloc] init];
                [dtoList.list addObject:dto];
            }
        }
        if (i == _listData.list.count - 1) {
            [_arrData addObject:dtoList];
        }
    }
    [_tbvContent reloadData];
}

#pragma mark - NAV
-(IBAction)backBtn:(id)sender {
    [self.navigationController popViewControllerAnimated:YES];
}

#pragma mark Tableview data source
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return _arrData.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    ListDetailJarDto *list = _arrData[section];
    return !list.isCollapse? list.list.count : 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 40;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {

    return (indexSegment == Debt)? 180 :80;
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    ListDetailJarDto *list = _arrData[section];
    JarDetailCell *cell = [tableView dequeueReusableCellWithIdentifier:@"daycell"];
    cell.lblTitle.text = [[NSDateFormatter serverDateFormatter] stringFromDate: list.list.firstObject.date];
    if (list.isCollapse) {
        [cell.btnAvatar setImage:[UIImage imageNamed:@"ic_arrowDown"] forState:UIControlStateNormal];
    } else {
        [cell.btnAvatar setImage:[UIImage imageNamed:@"ic_arrowUp"] forState:UIControlStateNormal];
    }
    cell.btnAction.tag = section;
    
    return cell;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    NSInteger row = indexPath.row;
    NSInteger section = indexPath.section;
    ListDetailJarDto *list = _arrData[section];
    DetailJarDto *data = list.list[row];
    if (indexSegment == Debt) {
        JarDetailCell *cell = [tableView dequeueReusableCellWithIdentifier:@"celldebt"];
        cell.lblDetail.text = data.detail;
        cell.lblOrigin.text = data.origin;
        cell.lblStaate.text = data.state;
        cell.lblAmuont.text = SF(@"%.0f",data.amount);
        [cell.vBackground setHidden:(row == list.list.count -1)];
        
        if (!data.isPositive) {
            [cell.btnNev setImage:[UIImage imageNamed:@"ic-ovalChecked"] forState:UIControlStateNormal];
            [cell.btnPos setImage:[UIImage imageNamed:@"ic-ovalNonCheck"] forState:UIControlStateNormal];
        } else {
            [cell.btnNev setImage:[UIImage imageNamed:@"ic-ovalNonCheck"] forState:UIControlStateNormal];
            [cell.btnPos setImage:[UIImage imageNamed:@"ic-ovalChecked"] forState:UIControlStateNormal];
        }
        
        return cell;
    } else {
        JarDetailCell *cell = [tableView dequeueReusableCellWithIdentifier:@"JarDetailCell"];
        cell.lblTitle.text = data.detail;
        cell.lblSubTitle.text = SF(@"%.0f",data.amount);
        [cell.vBackground setHidden:(row == list.list.count -1)];
        
        return cell;
    }

}

#pragma mark - Action

- (IBAction)selectedSegment:(UIButton *)btn {
    indexSegment = (int)btn.tag;
    [self initUISegment];
    switch (btn.tag) {
        case Income:
        {
            [_btnIncomes setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
            [_vIncomes setHidden:NO];
        }
            break;
        
        case Debt:
        {
            [_btnDebts setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
            [_vDebts setHidden:NO];
        }
            break;
            
        case Spending:
        {
            [_btnSpendings setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
            [_vSpendings setHidden:NO];
        }
            break;
    }
    [self getDataFromServer];
}

- (IBAction)selectedCollapse:(UIButton *)btn {
    ListDetailJarDto *list = _arrData[btn.tag];
    list.isCollapse = !list.isCollapse;
    [_tbvContent reloadData];
}

- (IBAction)selectedPos:(UIButton *)btn {
    
}

- (IBAction)selectedEdit:(UIButton *)btn {
    ListDetailJarDto *list = _arrData[btn.tag];
//    list.isCollapse = !list.isCollapse;
//    [_tbvContent reloadData];
}

#pragma mark - animation

- (void)animationButtonWithTopBot:(UIButton *)btn {
    CALayer *layer = [CALayer layer];
    layer.frame = CGRectMake(0, 0, btn.bounds.size.width, btn.bounds.size.height);
    layer.backgroundColor = [UIColor whiteColor].CGColor;
    [btn.layer addSublayer:layer];
    
    CABasicAnimation *animation = [CABasicAnimation animation];
    animation.keyPath = @"position.y";
    animation.byValue = @(0);
    animation.duration = 0.3;
    
    animation.fillMode = kCAFillModeForwards;
    animation.removedOnCompletion = NO;
    
    [layer addAnimation:animation forKey:@"Splash"];
}

- (void)animationButtonWithBotTop:(UIButton *)btn {
    CALayer *layer = [CALayer layer];
    layer.frame = CGRectMake(0, 0, btn.bounds.size.width, btn.bounds.size.height);
    layer.backgroundColor = [UIColor blueColor].CGColor;
    [btn.layer addSublayer:layer];
    
    CABasicAnimation *animation = [CABasicAnimation animation];
    animation.keyPath = @"position.y";
    animation.byValue = @(btn.bounds.size.height);

    animation.duration = 0.3;
    
    animation.fillMode = kCAFillModeForwards;
    animation.removedOnCompletion = NO;
    
    [layer addAnimation:animation forKey:@"Splash"];
}


#pragma mark - ScrollDeletage

- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
    if (scrollView.contentOffset.y < 0) {
        [self collapHeaderWithContentOffSetPull:scrollView.contentOffset.y];
    } else {
        [self collapHeaderWithContentOffSetUpDown:scrollView.contentOffset.y];
    }
    
}

- (void)collapHeaderWithContentOffSetPull :(float)offset {
    float height = _vHeaderView.bounds.size.height;
    float scale = -(offset) / height + 1;
    float headerSizevariation = ((height * scale) - height)/2.0;
    
    CATransform3D transform = CATransform3DIdentity;
    transform = CATransform3DTranslate(transform, 0, headerSizevariation, 0);
    transform = CATransform3DScale(transform, scale ,scale, 0);
    _vHeaderView.layer.transform = transform;
}

- (void)collapHeaderWithContentOffSetUpDown :(float)offset {
    //    float height = _vHeaderView.bounds.size.height;
    //    float scale = -(offset) / height + 1;
    CATransform3D transform = CATransform3DIdentity;
    // Header -----------
    
    transform = CATransform3DTranslate(transform, 0, MAX(-offset_HeaderStop, -offset),0);
    
    _vHeaderView.layer.transform = transform;
    
    //  ------------ Label
    
    CATransform3D labelTransform = CATransform3DMakeTranslation(0, MAX(-distance_W_LabelHeader, offset_B_LabelHeader - offset), 0);
    _lblHeader.layer.transform = labelTransform;
    
    //ViewHeader eff
    _vEff.alpha = MIN(1.0, (offset - offset_B_LabelHeader)/distance_W_LabelHeader);
    
}

- (void)roundedConners:(UIRectCorner )corners withRadius:(CGFloat )radius for:(UIView *)view {
    UIBezierPath *path = [UIBezierPath bezierPathWithRoundedRect:view.bounds byRoundingCorners:corners cornerRadii:CGSizeMake(radius, radius)];
    CAShapeLayer *maskLayer = [CAShapeLayer layer];
    maskLayer.frame = view.bounds;
    maskLayer.path = path.CGPath;
    view.layer.mask = maskLayer;
}

@end
