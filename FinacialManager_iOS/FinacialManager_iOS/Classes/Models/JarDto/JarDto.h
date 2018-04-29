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
@property (nonatomic, strong) NSString *image;
@property (nonatomic, assign) double incomes;
@property (nonatomic, assign) double posDebts;
@property (nonatomic, assign) double negWaittingDebts;
@property (nonatomic, assign) double negReadyDebts;
@property (nonatomic, assign) double negDoneDebts;
@property (nonatomic, assign) double spendings;
@property (nonatomic, assign) double avaiableAmount;

@end

@interface ListJarDto : BaseDto

@property (nonatomic, strong) NSMutableArray<JarDto *> *list;

@end

