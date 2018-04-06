//
//  MView.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 4/2/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "MView.h"
#import "define.h"

@implementation MView

- (void)layoutSubviews {
    [super layoutSubviews];
}

- (void)setOnCustomView:(BOOL)onCustomView {
    if (onCustomView) {
        [self MViewCustom];
    }
}

- (void)setStyleView:(NSString *)styleView {
    if ([styleView isEqualToString:@"viewNext"]) {
        [self MViewNextCaculator];
    }
}

- (void)MViewCustom {
    CAGradientLayer *gradient = [CAGradientLayer layer];
    gradient.frame = self.bounds;
    gradient.colors = @[(id)_fristColor.CGColor,(id)_secondColor.CGColor];
    [self.layer insertSublayer:gradient atIndex:0];
}

- (void) MViewNextCaculator {
    self.layer.borderWidth = 1;
    self.layer.borderColor = GRAY_COLOR.CGColor;
    self.layer.cornerRadius = 5;
    self.layer.shadowColor = GRAY_COLOR.CGColor;
    self.layer.shadowOpacity = 1;
    self.layer.shadowOffset = CGSizeZero;
    self.layer.shadowRadius = 5;
}


@end

