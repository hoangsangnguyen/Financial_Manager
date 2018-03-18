//
//  MenuDto.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "MenuDto.h"

@implementation MenuDto

- (id)initWithData:(NSDictionary *)dic {
    self = [super init];
    
    if(dic) {
        if([dic isKindOfClass:[NSDictionary class]]) {
  
        } else if([dic isKindOfClass:[NSString class]]) {
    
        }
    }
    
    return self;
}

- (id)initWithTitle:(NSString *)title andImage:(NSString *)strImg {
    self = [super init];
    
    _title = title;
    _img = strImg;
    
    return self;
}

@end

@implementation MenuListDto

- (instancetype)init {
    self = [super init];
    _list = [NSMutableArray array];
    
    return self;
}

- (id)initWithData:(NSDictionary *)dic {
    self = [super init];
    
    return self;
}

@end




