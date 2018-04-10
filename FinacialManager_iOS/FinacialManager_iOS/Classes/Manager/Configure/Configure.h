//
//  Configure.h
//  AppFood
//
//  Created by ThanhSon on 3/16/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "UserDto.h"
#import "StatesDto.h"
#import "JarDto.h"

@interface Configure : NSObject

@property (nonatomic, strong) NSString *token;
@property (nonatomic, strong) UserDto *userDto;

@property (nonatomic, strong) NSUserDefaults* defaults;

// Object Default
@property (nonatomic, strong) ListStatesDto *listState;
@property (nonatomic, strong) ListJarDto *listJar;

- (BOOL) checkLogin;
- (void)updateUser:(UserDto *)userDto;

@end
