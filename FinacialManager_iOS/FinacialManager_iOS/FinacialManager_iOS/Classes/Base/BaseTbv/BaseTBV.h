//
//  BaseTBV.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "define.h"

@interface BaseTBV : UITableView

@property (nonatomic, strong) UIRefreshControl *refreshCtrl;

- (void)addPullRefreshAtVC:(id)vc toReloadAction:(SEL)action;

- (void)showIndicator;
- (void)hideIndicator;
- (BOOL)isRefreshing;

@end
