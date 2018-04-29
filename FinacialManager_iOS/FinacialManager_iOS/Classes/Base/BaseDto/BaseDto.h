//
//  BaseDto.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "define.h"
#import "NSDateFormatter+Util.h"

@interface BaseDto : NSObject

- (id) initWithData:(NSDictionary*)dic;

- (NSMutableDictionary*) getJSONObject;
- (NSMutableDictionary*) getJSONObjectWithMethod:(NSInteger)method;
- (NSString*) getJSONString;

- (BaseDto*) cloneToNewObject;
- (void) mergedFromObject:(BaseDto*)object;


@end
