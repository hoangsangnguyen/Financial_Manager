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
            IO(email);
            IO(password);
            IO(fullName);
            IO(created);
        }
    }
    return self;
}

- (NSMutableDictionary *)getJSONObject {
    NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
    JO(email);
    JO(password);
    return dic;
}

- (id )getJSONObjectWithMethod:(NSInteger)method{
    NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
    if (method == METHOD_POST) {
        JO(email);
        JO(password);
    } else if (method == METHOD_POST_2) {
        JO(email);
        JO(password);
        JO(fullName);
    }
    return dic;
}


@end
