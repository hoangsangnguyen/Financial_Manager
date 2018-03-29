//
//  NSString+Util.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/27/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "NSString+Util.h"

@implementation NSString (Util)

+ (CGFloat)widthOfString:(NSString *)string withFont:(UIFont *)font{
    NSDictionary *attributes = [NSDictionary dictionaryWithObjectsAndKeys:font, NSFontAttributeName, nil];
    return [[[NSAttributedString alloc] initWithString:string attributes:attributes] size].width;
}


+ (id)randomizedStringWithAlphabet:(NSString *)alphabet length:(NSUInteger)len
{
    return [[self alloc] initWithAlphabet:alphabet length:len] ;
}


- (id)initWithAlphabet:(NSString *)alphabet length:(NSUInteger)len
{
    NSMutableString *s = [NSMutableString stringWithCapacity:len];
    for (NSUInteger i = 0U; i < len; i++) {
        u_int32_t r = arc4random() % [alphabet length];
        unichar c = [alphabet characterAtIndex:r];
        [s appendFormat:@"%C", c];
    }
    return [s copy];
}


+(id)makeClickableString:(NSString *)string withHighlightStr:(NSString*)strHighlight forLabel:(UILabel*)lbl withRange:(NSRange)range{
    return [[self alloc] initWithClickableString:string hightLight:strHighlight forLabel:lbl withRange:range];
}

- (id)initWithClickableString:(NSString*)string hightLight:(NSString*)strHighlight forLabel:(UILabel*)lbl withRange:(NSRange)range{
    NSMutableAttributedString *attributedString = [[NSMutableAttributedString alloc] initWithString:string attributes:nil];
    
    
    NSDictionary *linkAttributes = @{NSForegroundColorAttributeName : [UIColor colorWithRed:0.05 green:0.4 blue:0.65 alpha:1.0],
                                     NSUnderlineStyleAttributeName : @(NSUnderlineStyleSingle)};
    
    [attributedString setAttributes:linkAttributes range:range];
    lbl.attributedText = attributedString;
    
    lbl.userInteractionEnabled = YES;
    [lbl addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(handleTapOnLabel:)]];
    
    return [lbl copy];
}

- (void)handleTapOnLabel:(UITapGestureRecognizer *)tapGesture {
    /////
}

- (NSString *)urlencode {
    NSMutableString *output = [NSMutableString string];
    const unsigned char *source = (const unsigned char *)[self UTF8String];
    int sourceLen = strlen((const char *)source) ;
    for (int i = 0; i < sourceLen; ++i) {
        const unsigned char thisChar = source[i];
        if (thisChar == ' '){
            [output appendString:@"+"];
        } else if (thisChar == '.' || thisChar == '-' || thisChar == '_' || thisChar == '~' ||
                   (thisChar >= 'a' && thisChar <= 'z') ||
                   (thisChar >= 'A' && thisChar <= 'Z') ||
                   (thisChar >= '0' && thisChar <= '9')) {
            [output appendFormat:@"%c", thisChar];
        } else {
            [output appendFormat:@"%%%02X", thisChar];
        }
    }
    return output;
}

@end
