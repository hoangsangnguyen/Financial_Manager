//
//  BaseCell.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseCell.h"

@implementation BaseCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

}

- (IBAction)btnAction:(UIButton*)btn {
    if (_delegate && [_delegate respondsToSelector:@selector(baseTableCellSelected:)]) {
        [_delegate baseTableCellSelected:self];
    }
}

@end
