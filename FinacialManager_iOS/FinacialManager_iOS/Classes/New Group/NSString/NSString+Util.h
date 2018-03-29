//
//  NSString+Util.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/27/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface NSString (Util)

+ (CGFloat)widthOfString:(NSString *)string withFont:(UIFont *)font;
+ (id)randomizedStringWithAlphabet:(NSString *)alphabet length:(NSUInteger)len;
+ (id)makeClickableString:(NSString *)string withHighlightStr:(NSString*)strHighlight forLabel:(UILabel*)lbl withRange:(NSRange)range;

- (id)initWithAlphabet:(NSString *)alphabet length:(NSUInteger)len;
- (id)initWithClickableString:(NSString*)string hightLight:(NSString*)strHighlight forLabel:(UILabel*)lbl withRange:(NSRange)range;
- (NSString *)urlencode;

@end
