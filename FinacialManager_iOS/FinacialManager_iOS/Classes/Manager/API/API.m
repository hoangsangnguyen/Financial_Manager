//
//  AppDelegate.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "API.h"
#import "UserDto.h"
#import "Configure.h"
#import "AppDelegate.h"
#import "JarDto.h"
#import "TypeDto.h"
#import "StatesDto.h"
#import "DetailJarDto.h"

@interface _API ()
@property (nonatomic, assign) NSInteger _countRequest;
@end

@implementation _API

+ (_API *)shared {
    static _API *sharedAPI = nil;
    
    static dispatch_once_t t;
    dispatch_once(&t, ^{
        sharedAPI = [[_API alloc] init];
        sharedAPI._countRequest = 0;
    });
    
    return sharedAPI;
}

- (void)increaseCountRequest{
    __countRequest++;
}

- (void)descreaCountRequest{
    __countRequest--;
}

- (void) processAPI:(NSString*)route
          serverURL:(NSString*)server
             method:(NSInteger)methodType
             header:(NSDictionary*)headers
               body:(id)body
       successClass:(Class)successClass
           callback:(APICallback)callback {
    
    __block APICallback cb = NULL;
    if(callback) {
        cb = [callback copy];
    }
    
    
    // REQUEST
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] init];
    request.URL = [NSURL URLWithString:SF(@"%@/%@",server,route)];
    request.cachePolicy = NSURLRequestReloadIgnoringCacheData;
    request.timeoutInterval = 30.0f;
    
    
    
    // METHOD
    NSArray *arrMethod = arrMethods;
    //methodType = VALRANGE(methodType, 0, [arrMethod count]);
    NSString *method = arrMethod[methodType%NUM_METHOD];
    request.HTTPMethod = method;
    
    // HEADER
    NSMutableDictionary *allHeader = [[NSMutableDictionary alloc] init];
    [allHeader setObject:@"application/json" forKey:@"Content-Type"];
    if(headers) {
        [allHeader addEntriesFromDictionary:headers];
    }
    
    if (App.configure.token) {
        [allHeader setObject:App.configure.token forKey:@"token"];
    }
    
    request.allHTTPHeaderFields = allHeader;
    
    // BODY (except GET)
    if(body) {
        id obj = body;
        if([obj isKindOfClass:[BaseDto class]]) {
            obj = [obj getJSONObjectWithMethod:methodType];
        }
        
        if([obj isKindOfClass:[NSDictionary class]] || [obj isKindOfClass:[NSArray class]]) {
            NSMutableDictionary *dicBody = obj;
            NSData *dataBody = [NSJSONSerialization dataWithJSONObject:dicBody options:0 error:nil];
            request.HTTPBody = dataBody;
            
            NSString *json = dataBody?[[NSString alloc] initWithData:dataBody encoding:NSUTF8StringEncoding]:@"<null>";
            NSLog(@"[API] [%@] [%@] Data: %@", method, route, json);
            
        } else {
            if([obj isKindOfClass:[NSData class]]) {
                request.HTTPBody = obj;
                
            } else if([obj isKindOfClass:[NSString class]]) {
                request.HTTPBody = [obj dataUsingEncoding:NSUTF8StringEncoding];
            }
        }
        
    } else {
        NSLog(@"[API] [%@] [%@]", method, route);
    }
    
    void (^errorCallback)(id data) = ^(id data) {
        NSLog(@"[API] [%@] [%@] errorCallback Error: %@", method, route, E(data));
        
        if(cb) {
            cb(NO, data);
            cb = NULL;
        }
    };
    
    void (^successCallback)(id data) = ^(id data) {
        NSMutableDictionary *respondData = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:nil];
        if(respondData) {
            // Print Track Log
            NSString *strLog = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
            NSLog(@"[API] [%@] [%@] SERVER RESPONSE: \n==>\n %@ \n\n<==\n\n", method, route, strLog);
            
            bool success = [respondData objectForKey:@"success"];
            if (success) {
                NSDictionary *results = [respondData objectForKey:@"results"];
                if ([results isKindOfClass:[NSDictionary class]]) {
                    NSString *token = [results objectForKey:@"token"];
                    if (![token isEqualToString:@""]) {
                        App.configure.token = token;
                        cb(YES, successClass ? [[successClass alloc] initWithData:results] : nil);
                    } else {
                        cb (YES,successClass ? [[successClass alloc] initWithData:results] : nil);
                    }
                } else {
                    cb (YES,successClass ? [[successClass alloc] initWithData:results] : nil);
                }
            } else {
                if(cb) {
                    cb(YES, successClass ? [[successClass alloc] initWithData:respondData] : nil);
                    cb = NULL;
                    return;
                }
            }
        }
    };

    [self increaseCountRequest];
    
    if (__countRequest > 0) {
        [[UIApplication sharedApplication] setNetworkActivityIndicatorVisible:YES];
    }

    NSURLSessionDataTask *task = [[NSURLSession sharedSession] dataTaskWithRequest:request completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
        
        dispatch_async(dispatch_get_main_queue(), ^{
            [self descreaCountRequest];
            if (__countRequest == 0) {
                [[UIApplication sharedApplication] setNetworkActivityIndicatorVisible:NO];
            }
            
            NSHTTPURLResponse *httpResponse = (NSHTTPURLResponse*)response;
            
            if(!error && data) {
                
                if(httpResponse) {
                    NSInteger code = httpResponse.statusCode;
                    if(code == 200) {
                        
                        successCallback(data);
                        return;
                    }
                }
            }else{
               
            }
            
            // Other Case, Failed
            errorCallback(error? error:response.description);
        });
    }];
    
    [task resume];
}

- (void) processAPI:(NSString*)route
             method:(NSInteger)methodType
             header:(NSDictionary*)headers
               body:(id)body
       successClass:(Class)successClass
           callback:(APICallback)callback {
    
    [self processAPI:route serverURL:ServerURL method:methodType header:headers body:body successClass:successClass callback:callback];
}

#pragma mark - Login

- (void)login:(UserDto*)user callback:(APICallback)callback {
    [self processAPI:@"users/login"
              method:METHOD_POST
              header:nil
                body:user
        successClass:[UserDto class]
            callback:callback];
}

- (void)registerAccount:(UserDto*)user callback:(APICallback)callback {
    [self processAPI:@"users"
              method:METHOD_POST_2
              header:nil
                body:user
        successClass:nil
            callback:callback];
}

#pragma mark - Jar

- (void)getAllJars: (APICallback)callback {
    [self processAPI:SF(@"/users/%@/jars", Config.userDto._id)
              method:METHOD_GET
              header:nil
                body:nil
        successClass:[ListJarDto class]
            callback:callback];
}

- (void)getJarDetail: (JarDto *)data callback: (APICallback)callback {
    [self processAPI:SF(@"/users/%@/jars/%@", Config.userDto._id, data._id)
              method:METHOD_GET
              header:nil
                body:nil
        successClass:[JarDto class]
            callback:callback];
}

#pragma mark - Type

- (void)getAllTypes: (APICallback)callback {
    [self processAPI:@"/types"
              method:METHOD_GET
              header:nil
                body:nil
        successClass:[ListTypeDto class]
            callback:callback];
}

- (void)getAllState: (APICallback)callback {
    [self processAPI:@"/states"
              method:METHOD_GET
              header:nil
                body:nil
        successClass:[ListStatesDto class]
            callback:callback];
}

#pragma mark - GetIncome
- (void)getIncomeJarDetail: (JarDto *)data callback: (APICallback)callback {
    [self processAPI:SF(@"/users/%@/jars/%@/incomes",Config.userDto._id,data._id)
              method:METHOD_GET
              header:nil
                body:nil
        successClass:[ListDetailJarDto class]
            callback:callback];
}

- (void)createIncomeJarDetail:(DetailJarDto *)data callback: (APICallback)callback {
    [self processAPI:SF(@"/users/%@/jars/%@/incomes",Config.userDto._id,data._idJar)
              method:METHOD_POST
              header:nil
                body:data
        successClass:[DetailJarDto class]
            callback:callback];
}

- (void)createIncomeGeneral:(DetailJarDto *)data callback: (APICallback)callback {
    [self processAPI:SF(@"/users/%@/incomes",Config.userDto._id)
              method:METHOD_POST
              header:nil
                body:data
        successClass:[DetailJarDto class]
            callback:callback];
}


#pragma mark - GetSpendings
- (void)getSpendingsJarDetail: (JarDto *)data callback: (APICallback)callback{
    [self processAPI:SF(@"/users/%@/jars/%@/spendings",Config.userDto._id,data._id)
              method:METHOD_GET
              header:nil
                body:nil
        successClass:[ListDetailJarDto class]
            callback:callback];
}

- (void)createSpendingsJarDetail:(DetailJarDto *)data callback: (APICallback)callback {
    [self processAPI:SF(@"/users/%@/jars/%@/spendings",Config.userDto._id,data._idJar)
              method:METHOD_POST
              header:nil
                body:data
        successClass:[DetailJarDto class]
            callback:callback];
}

#pragma mark - GetDebts
- (void)getDebtsJarDetail: (JarDto *)data callback: (APICallback)callback {
    [self processAPI:SF(@"/users/%@/jars/%@/debts",Config.userDto._id,data._id)
              method:METHOD_GET
              header:nil
                body:nil
        successClass:[ListDetailJarDto class]
            callback:callback];
}

- (void)createDebtsJarDetail:(DetailJarDto *)data callback: (APICallback)callback {
    [self processAPI:SF(@"/users/%@/jars/%@/debts",Config.userDto._id,data._idJar)
              method:METHOD_POST_2
              header:nil
                body:data
        successClass:[DetailJarDto class]
            callback:callback];
}

@end
