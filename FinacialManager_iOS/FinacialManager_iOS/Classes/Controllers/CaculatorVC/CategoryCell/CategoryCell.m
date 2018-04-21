//
//  CategoryCell.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/30/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "CategoryCell.h"

@implementation CategoryCell

- (void)layoutSubviews {
    [super layoutSubviews];
    _vCategory.layer.cornerRadius = _vCategory.frame.size.height/2;
    _vCategory.clipsToBounds = YES;
    _vCategory.layer.borderWidth = 1;
    _vCategory.layer.borderColor = [UIColor blueColor].CGColor;
    [self layoutIfNeeded];
}


@end
