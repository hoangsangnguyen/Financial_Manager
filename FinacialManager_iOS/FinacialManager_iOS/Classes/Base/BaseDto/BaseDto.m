//
//  BaseDto.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseDto.h"

@implementation BaseDto

- (id)initWithData:(NSDictionary *)dic {
    self = [super init];
    
    return self;
}

- (NSMutableDictionary *)getJSONObject {
    NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
    return dic;
}

- (NSMutableDictionary *)getJSONObjectWithMethod:(NSInteger)method {
    return [self getJSONObject];
}

- (NSString *)getJSONString {
    NSDictionary *dic = [self getJSONObject];
    NSData *data = [NSJSONSerialization dataWithJSONObject:dic options:0 error:nil];
    NSString *str = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
    return str;
}

- (BaseDto *)cloneToNewObject {
    NSDictionary *dic = [self getJSONObject];
    return [[self.class alloc] initWithData:dic];
}

- (void)mergedFromObject:(BaseDto *)object {
}

@end
