//
//  MView.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 4/2/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import <UIKit/UIKit.h>

IB_DESIGNABLE

@interface MView : UIView


@property (nonatomic, strong) IBInspectable NSString *styleView;
@property (nonatomic, strong) IBInspectable UIColor *fristColor;
@property (nonatomic, strong) IBInspectable UIColor *secondColor;
@property (nonatomic) IBInspectable BOOL onCustomView;

@end
