//
//  TypeDto.m
//  FinacialManager_iOS
//
//  Created by HHumorous on 4/6/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "TypeDto.h"

@implementation TypeDto

-(id)initWithData:(NSDictionary *)dic {
    self = [super init];
    if (dic) {
        if ([dic isKindOfClass:[NSDictionary class]]) {
            IO(_id);
            IO(name);
            IF(percent);
        }
    }
    return self;
}

- (NSMutableDictionary *)getJSONObject {
    NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
    JO(_id);
    JO(name);
    JN(percent);
    
    return dic;
}

- (id )getJSONObjectWithMethod:(NSInteger)method{
    NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
    
    return dic;
}

@end

@implementation ListTypeDto

- (instancetype)init {
    self = [super init];
    _list = [[NSMutableArray alloc] init];
    return self;
}

- (id)initWithData:(NSDictionary *)dic {
    self = [super init];
    IAObj(list, dic, TypeDto);
    return self;
}


@end
