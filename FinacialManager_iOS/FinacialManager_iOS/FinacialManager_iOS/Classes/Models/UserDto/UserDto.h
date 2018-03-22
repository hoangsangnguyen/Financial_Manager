//
//  UserDto.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "BaseDto.h"

@interface UserDto : BaseDto

@property (nonatomic, strong) NSString *_id;
@property (nonatomic, strong) NSString *firstName;
@property (nonatomic, strong) NSString *lastName;
@property (nonatomic, strong) NSString *email;
@property (nonatomic, strong) NSString *phone;
@property (nonatomic, strong) NSString *userName;
@property (nonatomic, strong) NSString *password;

@end
