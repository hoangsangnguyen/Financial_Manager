//
//  UserDto.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "UserDto.h"

@implementation UserDto

-(id)initWithData:(NSDictionary *)dic {
    self = [super init];
    if (dic) {
        if ([dic isKindOfClass:[NSDictionary class]]) {
            IO(_id);
            IO(firstName);
            IO(lastName);
            IO(email);
            IO(phone);
            IO(userName);
            IO(password);
        }
    }
    return self;
}

- (NSMutableDictionary *)getJSONObject {
    NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
    JO(_id);
    JO(firstName);
    JO(lastName);
    JO(email);
    JO(phone);
    JO(userName);
    JO(password);
    return dic;
}

- (id )getJSONObjectWithMethod:(NSInteger)method{
    NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
    if (method == METHOD_POST) {
        JO(userName);
        JO(password);
    } else if (method == METHOD_POST_2) {
        JO(userName);
        JO(password);
    }
    return dic;
}

@end
