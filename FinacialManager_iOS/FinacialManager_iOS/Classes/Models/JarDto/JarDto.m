//
//  JarDto.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 4/5/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "JarDto.h"

@implementation JarDto

-(id)initWithData:(NSDictionary *)dic {
    self = [super init];
    if (dic) {
        if ([dic isKindOfClass:[NSDictionary class]]) {
            IO(_id);
            IO(type);
            ID(incomes);
            ID(posDebts);
            ID(negWaittingDebts);
            ID(negReadyDebts);
            ID(negDoneDebts);
            ID(spendings);
            ID(avaiableAmount);
        }
    }
    return self;
}

- (NSMutableDictionary *)getJSONObject {
    NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
    JO(_id);
    JO(type);
    JD(incomes);
    JD(posDebts);
    JD(negWaittingDebts);
    JD(negReadyDebts);
    JD(negDoneDebts);
    JD(spendings);
    JD(avaiableAmount);
    
    return dic;
}

- (id )getJSONObjectWithMethod:(NSInteger)method{
    NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
    
    return dic;
}

@end

@implementation ListJarDto

- (instancetype)init {
    self = [super init];
    _list = [[NSMutableArray alloc] init];
    return self;
}

- (id)initWithData:(NSDictionary *)dic {
    self = [super init];
    IAObj(list, dic, JarDto);
    return self;
}

@end
