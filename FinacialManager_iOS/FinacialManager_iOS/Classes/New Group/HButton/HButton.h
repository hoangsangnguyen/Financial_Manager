//
//  HButton.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/27/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <SDWebImage/UIImageView+WebCache.h>
#import <SDWebImage/UIButton+WebCache.h>
#import "define.h"


@interface HButton : UIButton

@property (copy, nonatomic) IBInspectable NSString *styleName;
@property (copy, nonatomic) IBInspectable NSString *text;
@property (assign, nonatomic) IBInspectable BOOL isChecked;

@property (nonatomic, strong) UIImage *image;
@property (nonatomic, strong) UIColor *textColor;
@property (nonatomic, strong) UIColor *bgColor;

- (void) setFont:(UIFont*)font;
- (void) setStyleDefault;
- (void) setStyleBlue;
- (void) setStyleWhite;

@end
