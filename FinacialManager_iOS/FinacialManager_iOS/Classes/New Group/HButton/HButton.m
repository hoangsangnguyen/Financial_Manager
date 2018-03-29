//
//  HButton.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/27/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "HButton.h"
#import "AppDelegate.h"
#import "define.h"
#import "NSString+Util.h"

@implementation HButton {
}

-(void)layoutSubviews {
    [super layoutSubviews];
}


- (void) setStyleName:(NSString *)styleName {
    if ([styleName isEqualToString:@"Login"]) {
        [self setStyleBlue];
        
    } else if ([styleName isEqualToString:@"SignUp"]) {
        [self setStyleWhite];
        
    } else if ([styleName isEqualToString:@"Clear"]) {
        [self setStyleClear];
        
    }
}

- (void) setText:(NSString *)text {
    [self setTitle:text forState:UIControlStateNormal];
}

- (void) setFont:(UIFont*)font {
    self.titleLabel.font = font;
}

- (void) setStyleAvatar {
    
    self.layer.masksToBounds = YES;
    self.clipsToBounds = YES;

    self.layer.cornerRadius = self.frame.size.height/2;
    self.layer.borderColor = MAINCOLOR.CGColor;
    self.layer.borderWidth = 1.5;
    [self layoutIfNeeded];
}

- (void)setStyleButtonWithCornerRadius:(CGFloat)radius colorTitle:(UIColor*)color
                        colorbacground:(UIColor*)colorBackground {
    self.layer.cornerRadius = radius;
    self.clipsToBounds = YES;
    self.layer.masksToBounds = YES;
    [self setTitleColor:color forState:UIControlStateNormal];
    [self setBackgroundColor:colorBackground];

}

- (void) setStyleDefault {
    self.text = nil;
    self.textColor = [UIColor clearColor];
    self.layer.masksToBounds = YES;
    self.clipsToBounds = YES;
    self.backgroundColor = [UIColor clearColor];
    self.layer.cornerRadius = 0;
    self.layer.borderColor = [UIColor clearColor].CGColor;
    self.layer.borderWidth = 0;
    [self layoutIfNeeded];
}

- (void) setStyleBlue {
    self.layer.cornerRadius = 5.0f;
    self.clipsToBounds = YES;
    [self setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [self setBackgroundColor:MAINCOLOR];
}

- (void) setStyleWhite {
    self.layer.cornerRadius = 5.0f;
    self.clipsToBounds = YES;
    [self setTitleColor:MAINCOLOR forState:UIControlStateNormal];
    [self setBackgroundColor:[UIColor whiteColor]];
}

- (void) setStyleClear {
    [self setTitleColor:MAINCOLOR forState:UIControlStateNormal];
}

@end
