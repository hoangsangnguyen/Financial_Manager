//
//  StatesDto.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 4/10/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "StatesDto.h"

@implementation StatesDto

-(id)initWithData:(NSDictionary *)dic {
    self = [super init];
    if (dic) {
        if ([dic isKindOfClass:[NSDictionary class]]) {
            IO(_id);
            IO(name);
        }
    }
    return self;
}

- (NSMutableDictionary *)getJSONObject {
    NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
    JO(_id);
    JO(name);
    
    return dic;
}

- (id )getJSONObjectWithMethod:(NSInteger)method{
    NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
    
    return dic;
}

@end

@implementation ListStatesDto

- (instancetype)init {
    self = [super init];
    _list = [[NSMutableArray alloc] init];
    return self;
}

- (id)initWithData:(NSDictionary *)dic {
    self = [super init];
    IAObj(list, dic, StatesDto);
    return self;
}

@end
