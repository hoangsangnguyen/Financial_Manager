//
//  OverViewVC.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/22/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseVC.h"
#import "BaseClv.h"
#import "VCFloatingActionButton.h"

@interface OverViewVC : BaseVC

@property (nonatomic, weak) IBOutlet BaseClv *clvContent;

@property (strong, nonatomic) VCFloatingActionButton *btnFloat;

@end
