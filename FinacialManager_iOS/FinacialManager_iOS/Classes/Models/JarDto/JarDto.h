//
//  JarDto.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 4/5/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseDto.h"
@class JarDto;

@interface JarDto : BaseDto

@property (nonatomic, strong) NSString *_id;
@property (nonatomic, strong) NSString *type;
@property (nonatomic, assign) double income;
@property (nonatomic, assign) double debts;
@property (nonatomic, assign) double spendings;

@end

@interface ListJarDto : BaseDto

@property (nonatomic, strong) NSMutableArray<JarDto *> *list;

@end

