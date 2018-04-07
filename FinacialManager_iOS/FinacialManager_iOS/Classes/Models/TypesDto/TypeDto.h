//
//  TypeDto.h
//  FinacialManager_iOS
//
//  Created by HHumorous on 4/6/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseDto.h"
@class TypeDto;

@interface TypeDto : BaseDto

@property (nonatomic, strong) NSString *_id;
@property (nonatomic, strong) NSString *name;
@property (nonatomic, assign) float percent;

@end

@interface ListTypeDto : BaseDto

@property (nonatomic, strong) NSMutableArray<TypeDto *> *list;

@end
