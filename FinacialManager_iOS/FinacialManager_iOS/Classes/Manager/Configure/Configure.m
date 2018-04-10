//
//  Configure.m
//  AppFood
//
//  Created by ThanhSon on 3/16/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "Configure.h"

@implementation Configure

- (BOOL)checkLogin {
    _defaults = [NSUserDefaults standardUserDefaults];
    NSDictionary *dicUser = [_defaults dictionaryForKey:@"UserDto"];
    _token = [_defaults objectForKey:@"token"];
    UserDto *user = [[UserDto alloc] initWithData:dicUser];
    if (![user._id isEqualToString:@""] && user._id != nil && _token != nil) {
        _userDto = user;
        return true;
    } else {
        return false;
    }
}

- (void)updateUser:(UserDto *)userDto {
    NSDictionary *dicUser = [userDto getJSONObject];
    [_defaults setObject:dicUser forKey:@"UserDto"];
    [_defaults setObject:_token forKey:@"token"];
    _userDto = userDto;
}


@end
