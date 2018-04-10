//
//  BaseColCell.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/30/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BaseColCell : UICollectionViewCell

@property (nonatomic, weak) IBOutlet UILabel *lblTitle;
@property (nonatomic, weak) IBOutlet UILabel *lblSubTitle;

@property (nonatomic, weak) IBOutlet UIImageView *imgIcon;

@property (nonatomic, weak) IBOutlet UIView *vBackground;


@end
