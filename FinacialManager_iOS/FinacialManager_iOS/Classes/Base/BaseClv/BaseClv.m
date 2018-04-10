//
//  BaseClv.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/30/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseClv.h"
#import "BaseVC.h"

@interface BaseClv ()
@end

@implementation BaseClv

- (void)addPullRefreshAtVC:(id)vc toReloadAction:(SEL)action{
    
    if (!_refreshCtrl) {
        _refreshCtrl = [[UIRefreshControl alloc] init];
        [_refreshCtrl addTarget:vc action:action forControlEvents:(UIControlEventValueChanged)];
        [_refreshCtrl setTintColor:BLUE_COLOR];
        [self addSubview:_refreshCtrl];
    }
}

- (void)showIndicator{
    if (!_refreshCtrl.isRefreshing) {
        [_refreshCtrl beginRefreshing];
    }
}

- (void)hideIndicator{
    if (_refreshCtrl.isRefreshing) {
        [_refreshCtrl endRefreshing];
    }
}

- (BOOL)isRefreshing{
    return [_refreshCtrl isRefreshing];
}

@end
