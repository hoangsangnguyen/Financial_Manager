//
//  BaseTBV.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseTBV.h"

@implementation BaseTBV

- (void)addPullRefreshAtVC:(id)vc toReloadAction:(SEL)action{
    
    NSString *str = NSStringFromSelector(action);
    NSRange range = [str rangeOfString:@":"];
    if (range.length > 0) {
        NSLog(@"\n\n\n\n\nERRORR >>>>\n\n\n\n\n\n\n\n\naddPullRefreshAtVC toReloadAction SHOULD NOT HAVE \':\' \n\n\n\n\n<<<<< EROOOR\n\n\n");
        return;
    }
    
    if (!_refreshCtrl) {
        
        if (IOS_10_OR_LATER()) {
            self.refreshControl = [[UIRefreshControl alloc] init];
            _refreshCtrl = self.refreshControl;
            [_refreshCtrl addTarget:vc action:action forControlEvents:(UIControlEventValueChanged)];
            [_refreshCtrl setTintColor:BLUE_COLOR];
            
        }else{
            _refreshCtrl = [[UIRefreshControl alloc] init];
            [_refreshCtrl addTarget:vc action:action forControlEvents:(UIControlEventValueChanged)];
            [_refreshCtrl setTintColor:BLUE_COLOR];
            [self addSubview:_refreshCtrl];
        }
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
