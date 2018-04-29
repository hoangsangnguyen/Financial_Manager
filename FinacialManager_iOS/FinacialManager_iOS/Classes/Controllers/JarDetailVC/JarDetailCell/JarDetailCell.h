//
//  JarDetailCell.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 4/11/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseCell.h"

@interface JarDetailCell : BaseCell


@property (nonatomic, weak) IBOutlet UILabel *lblType;
@property (nonatomic, weak) IBOutlet UILabel *lblDetail;
@property (nonatomic, weak) IBOutlet UILabel *lblAmuont;
@property (nonatomic, weak) IBOutlet UILabel *lblOrigin;
@property (nonatomic, weak) IBOutlet UILabel *lblStaate;
@property (nonatomic, weak) IBOutlet UIImageView *imgJar;

@property (nonatomic, strong) IBOutlet UIButton *btnNev;
@property (nonatomic, strong) IBOutlet UIButton *btnPos;

@end
