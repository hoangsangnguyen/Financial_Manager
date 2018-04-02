//
//  UIView+Util.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/27/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "UIView+Util.h"
#import "define.h"

@implementation UIView (Util)

- (UITableViewCell *)getParentTableCell {
    return [self getParentViewWithClass:[UITableViewCell class]];
}

- (id)getParentViewWithClass:(Class)cls {

    UIView *pView = self.superview;
    if(pView) {
        if([pView isKindOfClass:cls]) {
            return pView;

        } else {
            return [pView getParentViewWithClass:cls];
        }
    }

    return nil;
}

#pragma mark - Radius

- (void)setBottomCorner:(BOOL)hasCorner {
    [self roundCornersOnTopLeft:NO topRight:NO bottomLeft:hasCorner bottomRight:hasCorner radius:5.0];
}

- (void)setTopCorner:(BOOL)hasCorner {
    [self roundCornersOnTopLeft:hasCorner topRight:hasCorner bottomLeft:NO bottomRight:NO radius:5.0];
}

- (void)setCorner:(BOOL)hasCorner {
    [self roundCornersOnTopLeft:hasCorner topRight:hasCorner bottomLeft:hasCorner bottomRight:hasCorner radius:5.0];
}

- (void)setNoCorner:(BOOL)hasCorner {
    [self roundCornersOnTopLeft:hasCorner topRight:hasCorner bottomLeft:hasCorner bottomRight:hasCorner radius:0.0];
}

- (void)setCornerHeader:(BOOL)hasBottomCorner {
    [self roundCornersOnTopLeft:YES topRight:YES bottomLeft:hasBottomCorner bottomRight:hasBottomCorner radius:5.0];
}

- (void)roundCornersOnTopLeft:(BOOL)tl topRight:(BOOL)tr bottomLeft:(BOOL)bl bottomRight:(BOOL)br radius:(float)radius {
    
    if (tl || tr || bl || br) {
        UIRectCorner corner = 0;
        if (tl) corner = corner | UIRectCornerTopLeft;
        if (tr) corner = corner | UIRectCornerTopRight;
        if (bl) corner = corner | UIRectCornerBottomLeft;
        if (br) corner = corner | UIRectCornerBottomRight;
        
        UIView *roundedView = self;
        UIBezierPath *maskPath = [UIBezierPath bezierPathWithRoundedRect:roundedView.bounds byRoundingCorners:corner cornerRadii:CGSizeMake(radius, radius)];
        CAShapeLayer *maskLayer = [CAShapeLayer layer];
        maskLayer.frame = roundedView.bounds;
        maskLayer.path = maskPath.CGPath;
        roundedView.layer.mask = maskLayer;
    }
}

- (void) roundCornersOnTopLeft:(BOOL)tl topRight:(BOOL)tr bottomLeft:(BOOL)bl bottomRight:(BOOL)br radius:(float)radius andShadow:(float)shadow {
    
    if (tl || tr || bl || br) {
        UIRectCorner corner = 0;
        if (tl) corner = corner | UIRectCornerTopLeft;
        if (tr) corner = corner | UIRectCornerTopRight;
        if (bl) corner = corner | UIRectCornerBottomLeft;
        if (br) corner = corner | UIRectCornerBottomRight;
        
        UIBezierPath *maskPath = [UIBezierPath bezierPathWithRoundedRect:self.bounds byRoundingCorners:corner cornerRadii:CGSizeMake(radius, radius)];
        CAShapeLayer *maskLayer = [CAShapeLayer layer];
        maskLayer.frame = self.bounds;
        maskLayer.path = maskPath.CGPath;
        maskLayer.shadowRadius = shadow;
        maskLayer.shadowColor = GRAY_COLOR.CGColor;
        maskLayer.shadowOffset = CGSizeMake(0, 1);
        self.layer.mask = maskLayer;
    }
}

#pragma mark- Add View NoItem
+ (UIView*)addViewNoItemWithTitle:(NSString *)title intoParentView:(UIView*)parentView{

    [self removeViewNoItemAtParentView:parentView];
    
    UIView *view = [[UIView alloc] initWithFrame:CGRectMake(0, 0,  parentView.frame.size.width/2,70)];
    UIImageView *imv = [[UIImageView alloc] initWithFrame:CGRectMake(0, 5, view.frame.size.width, 32)];
    imv.contentMode = UIViewContentModeScaleAspectFit;
    imv.image = [UIImage imageNamed:@"ic-NoImageGray"];
    [view addSubview:imv];
    UILabel *lbl = [[UILabel alloc] initWithFrame:CGRectMake(0, 45, view.frame.size.width, 20)];
    lbl.text = title;
    lbl.textAlignment = NSTextAlignmentCenter;
    lbl.textColor = GRAY_COLOR;
    [view addSubview:lbl];
    view.center = parentView.center;
    //view.tag = TAG_NOITEM;
    [parentView addSubview:view];
    return view;
}

+ (void)removeViewNoItemAtParentView:(UIView*)parentView{
//    for (NSInteger i = 0; i < parentView.subviews.count; i++) {
//        UIView *v = parentView.subviews[i];
//        if(v.tag == TAG_NOITEM) {
//            [v removeFromSuperview];
//            break;
//        }
//    }
}


+ (UIView*)addViewNoItemWithTitle:(NSString *)title intoParentView:(UIView*)parentView withRect:(CGRect)rect{
    
    [self removeViewNoItemAtParentView:parentView withRect:rect];
    
    UIView *view = [[UIView alloc] initWithFrame:rect];
    UIImageView *imv = [[UIImageView alloc] initWithFrame:CGRectMake(0, 5, view.frame.size.width, 32)];
    imv.contentMode = UIViewContentModeScaleAspectFit;
    imv.image = [UIImage imageNamed:@"ic-NoImageGray"];
    [view addSubview:imv];
    UILabel *lbl = [[UILabel alloc] initWithFrame:CGRectMake(0, 45, view.frame.size.width, 18)];
    lbl.text = title;
    lbl.textAlignment = NSTextAlignmentCenter;
    lbl.textColor = GRAY_COLOR;
    [view addSubview:lbl];
//    view.center = parentView.center;
//    view.tag = TAG_NOITEM;
    [parentView addSubview:view];
    return view;
}


+ (void)removeViewNoItemAtParentView:(UIView*)parentView withRect:(CGRect)rect {
    [UIView removeViewNoItemAtParentView:parentView];
}


#pragma mark - animation
- (void) moveTo:(CGPoint)destination duration:(float)secs option:(UIViewAnimationOptions)option
{
    [UIView animateWithDuration:secs delay:0.0 options:option
                     animations:^{
                         self.frame = CGRectMake(destination.x,destination.y, self.frame.size.width, self.frame.size.height);
                     }
                     completion:^(BOOL finished) {}];
}

#pragma mark - Add constraint
+ (void)addConstraintFourEdge:(UIView*)viewParent andView:(UIView*)viewChild{
    viewChild.translatesAutoresizingMaskIntoConstraints = NO;
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeTop)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeTop)
                                                          multiplier:1
                                                            constant:0]];
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeLeft)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeLeft)
                                                          multiplier:1
                                                            constant:0]];
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeBottom)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeBottom)
                                                          multiplier:1
                                                            constant:0]];
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeRight)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeRight)
                                                          multiplier:1
                                                            constant:0]];
}

+ (void)addConstraintFourEdge:(UIView*)viewParent andView:(UIView*)viewChild top:(CGFloat)top left:(CGFloat)left bottom:(CGFloat)bottom right:(CGFloat)right{
    viewChild.translatesAutoresizingMaskIntoConstraints = NO;
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeTop)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeTop)
                                                          multiplier:1
                                                            constant:-top]];
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeLeft)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeLeft)
                                                          multiplier:1
                                                            constant:-left]];
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeBottom)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeBottom)
                                                          multiplier:1
                                                            constant:bottom]];
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeRight)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeRight)
                                                          multiplier:1
                                                            constant:right]];
}



+ (void)addConstraintFourEdge:(UIView*)viewParent andView:(UIView*)viewChild top:(CGFloat)top left:(CGFloat)left right:(CGFloat)right height:(CGFloat)height{
    viewChild.translatesAutoresizingMaskIntoConstraints = NO;
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeTop)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeTop)
                                                          multiplier:1
                                                            constant:-top]];
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeLeft)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeLeft)
                                                          multiplier:1
                                                            constant:-left]];
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeRight)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeRight)
                                                          multiplier:1
                                                            constant:right]];
    
    [viewChild addConstraint:[NSLayoutConstraint constraintWithItem:viewChild
                                                          attribute:(NSLayoutAttributeHeight)
                                                          relatedBy:(NSLayoutRelationEqual)
                                                             toItem:nil
                                                          attribute:NSLayoutAttributeHeight
                                                         multiplier:1
                                                           constant:height]];
}


+ (void)addConstraintFourEdge:(UIView*)viewParent andView:(UIView*)viewChild left:(CGFloat)left right:(CGFloat)right bottom:(CGFloat)bottom height:(CGFloat)height{
    viewChild.translatesAutoresizingMaskIntoConstraints = NO;
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeLeft)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeLeft)
                                                          multiplier:1
                                                            constant:-left]];
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeRight)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeRight)
                                                          multiplier:1
                                                            constant:right]];
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeBottom)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeBottom)
                                                          multiplier:1
                                                            constant:bottom]];
    
    [viewChild addConstraint:[NSLayoutConstraint constraintWithItem:viewChild
                                                          attribute:(NSLayoutAttributeHeight)
                                                          relatedBy:(NSLayoutRelationEqual)
                                                             toItem:nil
                                                          attribute:NSLayoutAttributeHeight
                                                         multiplier:1
                                                           constant:height]];
}


+ (void)addConstraintPositionCenter:(UIView*)viewParent andView:(UIView*)viewChild childWidth:(CGFloat)width childHeight:(CGFloat)height{
    viewChild.translatesAutoresizingMaskIntoConstraints = NO;
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeCenterX)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeCenterX)
                                                          multiplier:1
                                                            constant:0]];
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeCenterY)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeCenterY)
                                                          multiplier:1
                                                            constant:0]];
    
    [viewChild addConstraint:[NSLayoutConstraint constraintWithItem:viewChild
                                                          attribute:(NSLayoutAttributeHeight)
                                                          relatedBy:(NSLayoutRelationEqual)
                                                             toItem:nil
                                                          attribute:(NSLayoutAttributeNotAnAttribute)
                                                         multiplier:1
                                                           constant:height]];
    
    [viewChild addConstraint:[NSLayoutConstraint constraintWithItem:viewChild
                                                          attribute:(NSLayoutAttributeWidth)
                                                          relatedBy:(NSLayoutRelationEqual)
                                                             toItem:nil
                                                          attribute:(NSLayoutAttributeNotAnAttribute)
                                                         multiplier:1
                                                           constant:width]];
}

+ (void)addConstraintPositionCenter:(UIView*)viewParent andView:(UIView*)viewChild childFrame:(CGRect)rect{
    viewChild.translatesAutoresizingMaskIntoConstraints = NO;
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeCenterX)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeCenterX)
                                                          multiplier:1
                                                            constant:0]];
    
    [viewParent addConstraint:[NSLayoutConstraint constraintWithItem:viewParent
                                                           attribute:(NSLayoutAttributeCenterY)
                                                           relatedBy:(NSLayoutRelationEqual)
                                                              toItem:viewChild
                                                           attribute:(NSLayoutAttributeCenterY)
                                                          multiplier:1
                                                            constant:0]];
    
    [viewChild addConstraint:[NSLayoutConstraint constraintWithItem:viewChild
                                                          attribute:(NSLayoutAttributeHeight)
                                                          relatedBy:(NSLayoutRelationEqual)
                                                             toItem:nil
                                                          attribute:(NSLayoutAttributeNotAnAttribute)
                                                         multiplier:1
                                                           constant:CGRectGetHeight(rect)]];
    
    [viewChild addConstraint:[NSLayoutConstraint constraintWithItem:viewChild
                                                          attribute:(NSLayoutAttributeWidth)
                                                          relatedBy:(NSLayoutRelationEqual)
                                                             toItem:nil
                                                          attribute:(NSLayoutAttributeNotAnAttribute)
                                                         multiplier:1
                                                           constant:CGRectGetWidth(rect)]];
}

#pragma mark UIShadow
+ (void)addShadowWithRadius:(CGFloat)shadowSize withShadowOpacity:(CGFloat)shadowOpacity withShadowOffset:(CGSize)shadowOffset withShadowColor:(UIColor *)shadowColor withCornerRadius:(CGFloat)cornerRadius forView:(UIView *)view{

    UIBezierPath *shadowPath = [UIBezierPath bezierPathWithRect:CGRectMake(view.frame.origin.x - shadowSize / 2,view.frame.origin.y - shadowSize / 2,view.frame.size.width + shadowSize,view.frame.size.height + shadowSize)];
    view.layer.masksToBounds = NO;
    view.layer.shadowColor = shadowColor.CGColor;
    view.layer.shadowOffset = shadowOffset;
    view.layer.shadowOpacity = shadowOpacity;
    view.layer.shadowPath = shadowPath.CGPath;
}

@end
