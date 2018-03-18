//
//  MenuDto.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseDto.h"
@class BaseDto;
@class MenuDto;

@interface MenuDto : BaseDto

@property (nonatomic, strong) NSString *title;
@property (nonatomic, assign) NSString *img;

- (id)initWithTitle:(NSString *)title andImage:(NSString *)strImg;

@end


@interface MenuListDto : BaseDto

@property (nonatomic, strong) NSMutableArray <MenuDto*> *list;

@end

