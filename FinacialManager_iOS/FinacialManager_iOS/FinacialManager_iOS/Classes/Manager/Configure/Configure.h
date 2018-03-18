//
//  Configure.h
//  AppFood
//
//  Created by ThanhSon on 3/16/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "UserDto.h"

@interface Configure : NSObject

@property (nonatomic, strong) NSString *token;
@property (nonatomic, strong) UserDto *userDto;

@end
