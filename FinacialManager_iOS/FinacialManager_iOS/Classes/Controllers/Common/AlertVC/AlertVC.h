//
//  AlertVC.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 4/2/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseVC.h"

typedef void (^AlertCallback)(BOOL hasPressOK);

@interface AlertVC : BaseVC

@property (nonatomic, strong) NSString *strTitle;
@property (nonatomic, strong) NSString *strTitleSub;
@property (nonatomic, strong) IBOutlet UIView *vContent;
@property (nonatomic, strong) IBOutlet UILabel *lblTitile;
@property (nonatomic, strong) IBOutlet UILabel *lblSub;

- (void) setCallback:(AlertCallback)callback;

+ (void) showAlert:(NSString*)content title:(NSString*)title callback:(AlertCallback)callback;

+ (void)show:(UIViewController*)controller content:(NSString *)content title:(NSString *)title callback:(AlertCallback)callback;


@end
