//
//  AlertVC.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 4/2/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "AlertVC.h"

@interface AlertVC () {
    AlertCallback _callback;
}

@end

@implementation AlertVC

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _vContent.transform = CGAffineTransformScale(CGAffineTransformIdentity,1.3, 1.3);
    _vContent.alpha = 0;
    // Do any additional setup after loading the view.

    _lblTitile.text = _strTitle;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)setCallback:(AlertCallback)callback {
    _callback = [callback copy];
}

+ (void) showAlert:(NSString*)content title:(NSString*)title callback:(AlertCallback)callback {
    AlertVC *vc = VCFromSB(AlertVC, SB_Common);
    [vc setCallback:callback];
    vc.strTitle = title;
    [AppNav presentViewController:vc animated:NO completion:^{
        [UIView animateWithDuration:0.4 animations:^{
            vc.vContent.transform = CGAffineTransformIdentity;
            vc.vContent.alpha = 1;
        }];
    }];
}

+ (void)show:(UIViewController*)controller content:(NSString *)content title:(NSString *)title callback:(AlertCallback)callback {
    AlertVC *vc = VCFromSB(AlertVC, SB_Common);
    [vc setCallback:callback];
    vc.strTitle = title;
    [controller presentViewController:vc animated:NO completion:^{
        [UIView animateWithDuration:0.4 animations:^{
            vc.vContent.transform = CGAffineTransformIdentity;
            vc.vContent.alpha = 1;
        }];
    }];
}

- (IBAction)btnOk:(id)sender {
    [UIView animateWithDuration:0.3 animations:^{
        _vContent.transform = CGAffineTransformScale(CGAffineTransformIdentity,1.3, 1.3);
        _vContent.alpha = 0;
    } completion:^(BOOL finished) {
        [self dismissViewControllerAnimated:NO completion:nil];
    }];
    
}

@end
