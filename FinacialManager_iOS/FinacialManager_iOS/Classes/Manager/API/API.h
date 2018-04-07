//
//  AppDelegate.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BaseDto.h"
#import "define.h"
#import "UIKit/UIKit.h"

#define API [_API shared]

@class UserDto;
@class DetailDishDto;
@class ListDishDto;
@class DishTypeDto;
@class JarDto;

typedef void (^APICallback)(BOOL success, id data);

@interface _API : NSObject

+ (_API*) shared;


- (void) processAPI:(NSString*)route
          serverURL:(NSString*)server
             method:(NSInteger)methodType
             header:(NSDictionary*)headers
               body:(id)body
       successClass:(Class)successClass
           callback:(APICallback)callback;
- (void) processAPI:(NSString*)route
             method:(NSInteger)methodType
             header:(NSDictionary*)headers
               body:(id)body
       successClass:(Class)successClass
           callback:(APICallback)callback;

#pragma mark - Login
- (void)login:(UserDto*)user callback:(APICallback)callback;
- (void)registerAccount:(UserDto*)user callback:(APICallback)callback;

#pragma mark - Jar

- (void)getAllJars: (APICallback)callback;
- (void)getJarDetail: (JarDto *)data callback: (APICallback)callback;

#pragma mark - Type

- (void)getAllTypes: (APICallback)callback;

@end





