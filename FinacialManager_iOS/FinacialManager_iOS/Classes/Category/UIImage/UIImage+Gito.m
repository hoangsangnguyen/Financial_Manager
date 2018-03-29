//
//  UIImage+Gito.m
//  Gito
//
//  Created by QuangLoc on 2/14/17.
//  Copyright © 2017 Horical. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "UIImage+Gito.h"

@implementation UIImage (Util)

+ (UIImage *)imageWithImage:(UIImage *)image scaledToWidth:(CGFloat)width{
    
    CGSize newSize = image.size;
    CGFloat radio = newSize.width/newSize.height;
    CGFloat newHeight = width/radio;
    newSize.width = width;
    newSize.height = newHeight;
    
    UIImage *newImage = [self imageWithImage:image scaledToSize:newSize];
    return newImage;
}

+ (UIImage *)imageWithImage:(UIImage *)image scaledToSize:(CGSize)newSize{
    UIGraphicsBeginImageContextWithOptions(newSize, NO, 0.0);
    [image drawInRect:CGRectMake(0, 0, newSize.width, newSize.height)];
    UIImage *newImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return newImage;
}

+ (UIImage*)convertBase64ToImage:(NSString*)base64 {

    NSData *dataUImage = [[NSData alloc]initWithBase64EncodedString:base64 options:NSDataBase64DecodingIgnoreUnknownCharacters];
    return [UIImage imageWithData:dataUImage];
}

@end
