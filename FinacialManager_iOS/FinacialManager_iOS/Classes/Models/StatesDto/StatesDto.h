//
//  StatesDto.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 4/10/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseDto.h"

@class StatesDto;

@interface StatesDto : BaseDto

@property (nonatomic, strong) NSString *_id;
@property (nonatomic, strong) NSString *name;

@end

@interface ListStatesDto : BaseDto

@property (nonatomic, strong) NSMutableArray<StatesDto *> *list;

@end
