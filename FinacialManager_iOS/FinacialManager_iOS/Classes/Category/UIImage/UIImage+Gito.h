//
//  UIImage+Gito.h
//  Gito
//
//  Created by QuangLoc on 2/14/17.
//  Copyright © 2017 Horical. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface UIImage (Util)

+ (UIImage *)imageWithImage:(UIImage *)image scaledToWidth:(CGFloat)width;

+ (UIImage *)imageWithImage:(UIImage *)image scaledToSize:(CGSize)newSize;

+ (UIImage*)convertBase64ToImage:(NSString*)base64;

@end
