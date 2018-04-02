//
//  UIView+Util.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/27/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UIView (Util)

- (UITableViewCell*) getParentTableCell;
- (id) getParentViewWithClass:(Class)cls;

- (void) setBottomCorner:(BOOL)hasCorner;
- (void) setTopCorner:(BOOL)hasCorner;
- (void) setCorner:(BOOL)hasCorner;
- (void) setNoCorner:(BOOL)hasCorner;
- (void) setCornerHeader:(BOOL)hasBottomCorner;

- (void) roundCornersOnTopLeft:(BOOL)tl topRight:(BOOL)tr bottomLeft:(BOOL)bl bottomRight:(BOOL)br radius:(float)radius;

- (void) roundCornersOnTopLeft:(BOOL)tl topRight:(BOOL)tr bottomLeft:(BOOL)bl bottomRight:(BOOL)br radius:(float)radius andShadow:(float)shadow;

- (void) moveTo:(CGPoint)destination duration:(float)secs option:(UIViewAnimationOptions)option;

//Add constraints
+ (void)addConstraintFourEdge:(UIView*)viewParent andView:(UIView*)viewChild;
+ (void)addConstraintFourEdge:(UIView*)viewParent andView:(UIView*)viewChild top:(CGFloat)top left:(CGFloat)left bottom:(CGFloat)bottom right:(CGFloat)right;
+ (void)addConstraintPositionCenter:(UIView*)viewParent andView:(UIView*)viewChild childWidth:(CGFloat)width childHeight:(CGFloat)height;
+ (void)addConstraintPositionCenter:(UIView*)viewParent andView:(UIView*)viewChild childFrame:(CGRect)rect;

+ (void)addConstraintFourEdge:(UIView*)viewParent andView:(UIView*)viewChild top:(CGFloat)top left:(CGFloat)left right:(CGFloat)right height:(CGFloat)height;

+ (void)addConstraintFourEdge:(UIView*)viewParent andView:(UIView*)viewChild left:(CGFloat)left right:(CGFloat)right bottom:(CGFloat)bottom height:(CGFloat)height;


// Add and Remove View No Item
+ (void)removeViewNoItemAtParentView:(UIView*)parentView;
+ (UIView*) addViewNoItemWithTitle:(NSString*)title intoParentView:(UIView*)parentView;
+ (UIView*)addViewNoItemWithTitle:(NSString *)title intoParentView:(UIView*)parentView withRect:(CGRect)rect;
+ (void)removeViewNoItemAtParentView:(UIView*)parentView withRect:(CGRect)rect;

// ShadowView
+ (void)addShadowWithRadius:(CGFloat)shadowSize withShadowOpacity:(CGFloat)shadowOpacity withShadowOffset:(CGSize)shadowOffset withShadowColor:(UIColor *)shadowColor withCornerRadius:(CGFloat)cornerRadius forView:(UIView *)view;

@end
