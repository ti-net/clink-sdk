/*
 Erica Sadun, http://ericasadun.com
 iPhone Developer's Cookbook, 3.0 Edition
 BSD License, Use at your own risk
 */

#import "UIViewExt.h"

CGRect CGRectMoveToCenter(CGRect rect, CGPoint center)
{
    CGRect newrect = CGRectZero;
    newrect.origin.x = center.x-CGRectGetMidX(rect);
    newrect.origin.y = center.y-CGRectGetMidY(rect);
    newrect.size = rect.size;
    return newrect;
}

@implementation UIView (ViewGeometry)

// Retrieve and set the origin
- (CGPoint) origin
{
	return self.frame.origin;
}

- (void) setOrigin: (CGPoint) aPoint
{
	CGRect newframe = self.frame;
	newframe.origin = aPoint;
	self.frame = newframe;
}


// Retrieve and set the size
- (CGSize) size
{
	return self.frame.size;
}

- (void) setSize: (CGSize) aSize
{
	CGRect newframe = self.frame;
	newframe.size = aSize;
	self.frame = newframe;
}

// Query other frame locations
- (CGPoint) bottomRight
{
	CGFloat x = self.frame.origin.x + self.frame.size.width;
	CGFloat y = self.frame.origin.y + self.frame.size.height;
	return CGPointMake(x, y);
}

- (CGPoint) bottomLeft
{
	CGFloat x = self.frame.origin.x;
	CGFloat y = self.frame.origin.y + self.frame.size.height;
	return CGPointMake(x, y);
}

- (CGPoint) topRight
{
	CGFloat x = self.frame.origin.x + self.frame.size.width;
	CGFloat y = self.frame.origin.y;
	return CGPointMake(x, y);
}


// Retrieve and set height, width, top, bottom, left, right

- (CGFloat)tosVFG_height {
    return self.frame.size.height;
}

- (void)setTosVFG_height:(CGFloat)tosVFG_height {
    CGRect newframe = self.frame;
    newframe.size.height = tosVFG_height;
    self.frame = newframe;
}

- (CGFloat)tosVFG_width {
    return self.frame.size.width;
}

- (void)setTosVFG_width:(CGFloat)tosVFG_width {
    CGRect newframe = self.frame;
    newframe.size.width = tosVFG_width;
    self.frame = newframe;
}

//- (CGFloat) height
//{
//	return self.frame.size.height;
//}
//
//- (void) setHeight: (CGFloat) newheight
//{
//	CGRect newframe = self.frame;
//	newframe.size.height = newheight;
//	self.frame = newframe;
//}
//
//- (CGFloat) width
//{
//	return self.frame.size.width;
//}
//
//- (void) setWidth: (CGFloat) newwidth
//{
//	CGRect newframe = self.frame;
//	newframe.size.width = newwidth;
//	self.frame = newframe;
//}

- (CGFloat)tos_x {
    return self.frame.origin.x;
}

- (void)setTos_x:(CGFloat)tos_x {
    CGRect newframe = self.frame;
    newframe.origin.x = tos_x;
    self.frame = newframe;
}

- (CGFloat)tos_y {
    return self.frame.origin.y;
}

- (void)setTos_y:(CGFloat)tos_y {
    CGRect newframe = self.frame;
    newframe.origin.y = tos_y;
    self.frame = newframe;
}

- (CGFloat) top
{
	return self.frame.origin.y;
}

- (void) setTop: (CGFloat) newtop
{
	CGRect newframe = self.frame;
	newframe.origin.y = newtop;
	self.frame = newframe;
}

- (CGFloat) left
{
	return self.frame.origin.x;
}

- (void) setLeft: (CGFloat) newleft
{
	CGRect newframe = self.frame;
	newframe.origin.x = newleft;
	self.frame = newframe;
}

- (CGFloat) bottom
{
	return self.frame.origin.y + self.frame.size.height;
}

- (void) setBottom: (CGFloat) newbottom
{
	CGRect newframe = self.frame;
	newframe.origin.y = newbottom - self.frame.size.height;
	self.frame = newframe;
}

- (CGFloat) right
{
	return self.frame.origin.x + self.frame.size.width;
}

- (void) setRight: (CGFloat) newright
{
	CGFloat delta = newright - (self.frame.origin.x + self.frame.size.width);
	CGRect newframe = self.frame;
	newframe.origin.x += delta ;
	self.frame = newframe;
}




- (CGFloat)centerX
{
    return self.center.x;
}

- (void)setCenterX:(CGFloat)centerX
{
    CGRect newFrame = self.frame;
    newFrame.origin.x = centerX- newFrame.size.width/2 ;
    self.frame = newFrame;
}

- (CGFloat)centerY
{
    return self.center.y ;
}

- (void)setCenterY:(CGFloat)centerY
{
    CGRect newFrame = self.frame;
    newFrame.origin.y = centerY - newFrame.size.height/2 ;
    self.frame = newFrame;
}


- (void)verticalCenter {
    self.centerY = self.superview.tosVFG_height/2;
}

- (void)horizontalCenter {
    self.centerX = self.superview.tosVFG_width/2;
}


- (void)toLeftView:(UIView *)view ofPix:(float)left {
    self.left = view.left + view.tosVFG_width + left;
}

/**
 *  距离view的right的距离
 */
- (void)toRightView:(UIView *)view ofPix:(float)right {
    self.right = view.left - right;
}

/**
 *  距离view的top的距离
 */
- (void)toTopView:(UIView *)view ofPix:(float)top {
    self.top = view.bottom + top;
}

/**
 *  self的bottom距离view的top的距离
 */
- (void)toBottomView:(UIView *)view ofPix:(float)bottom {
    self.bottom = view.top - bottom;
}

- (void)marginBottom:(CGFloat)bottom {
    self.bottom = self.superview.tosVFG_height - bottom;
}

- (void)marginRight:(CGFloat)right {
    self.right = self.superview.tosVFG_width - right;
}

- (void)marginTop:(CGFloat)top {
    self.top = self.superview.top + top;
}

- (void)marginLeft:(CGFloat)left {
    self.left = self.superview.left + left;
}

// Move via offset
- (void) moveBy: (CGPoint) delta
{
	CGPoint newcenter = self.center;
	newcenter.x += delta.x;
	newcenter.y += delta.y;
	self.center = newcenter;
}

// Scaling
- (void) scaleBy: (CGFloat) scaleFactor
{
	CGRect newframe = self.frame;
	newframe.size.width *= scaleFactor;
	newframe.size.height *= scaleFactor;
	self.frame = newframe;
}

// Ensure that both dimensions fit within the given size by scaling down
- (void) fitInSize: (CGSize) aSize
{
	CGFloat scale;
	CGRect newframe = self.frame;
	
	if (newframe.size.height && (newframe.size.height > aSize.height))
	{
		scale = aSize.height / newframe.size.height;
		newframe.size.width *= scale;
		newframe.size.height *= scale;
	}
	
	if (newframe.size.width && (newframe.size.width >= aSize.width))
	{
		scale = aSize.width / newframe.size.width;
		newframe.size.width *= scale;
		newframe.size.height *= scale;
	}
	
	self.frame = newframe;	
}
@end
