
package hw4;

import api.GameElement;

/**
 * Moving element associated with a Platform or Elevator. Optionally, a Lurker
 * can be given a nonzero horizontal velocity and it will oscillate between the
 * left and right edges of the Platform or Elevator.
 * 
 * @author Mishari Alharbi
 */
public class Lurker extends MovingElement {

/**
 * The amount added to the parent's x-coordinate to calculate this element's
 * x-coordinate.
 */
private int offset;

/**
 * The minimum bound.
 */
private double min;

/**
 *  * The maximum bound.
 */
private double max;

/**
 * A MovingElement type variable that used to store this object's parent.
 */
private MovingElement parent;

/**
 * Constructs a new Lurker. Before being added to a parent element such as a
 * Platform or Elevator, the x and y coordinates are zero. When a parent element
 * is set, the initial x-coordinate becomes the parent's x-coordinate, plus the
 * given offset, and the y-coordinate becomes the parent's y-coordinate, minus
 * this element's height.
 * 
 * @param width         element's width
 * @param height        element's height
 * @param initialOffset when a parent is set, this amount is added to the
 *                      parent's x-coordinate to calculate this element's
 *                      initial x-coordinate
 */
public Lurker(int width, int height, int initialOffset) {
	super(0, 0, width, height);
	min = 0;
	max = 0;
	parent = null;
	offset = initialOffset;
}

/**
 * Set's this element's parent. This element's x-coordinate becomes the parent's
 * x-coordinate, plus the initial offset, and the y-coordinate becomes the
 * parent's y-coordinate, minus this element's height.
 * 
 * @param p - the parent of this object
 */
public void setParent(GameElement p) {
	parent = (MovingElement) p;
	setPosition(p.getXReal() + offset, p.getYReal() - getHeight());
	setBounds(p.getXInt(), p.getXInt() + p.getWidth());

}

/**
 * Sets the right and left boundaries for this Lurker's movement. This method
 * normally has no effect in practice, since the Lurker is typically associated
 * with a parent element such as a Platform or Elevator, which determines its
 * boundaries.
 * 
 * @param min - left boundary
 * @param max - right boundary
 */
public void setBounds(double min, double max) {
	this.min = min;
	this.max = max;
}

/**
 * Returns the left boundary for this Lurker's movement.
 * 
 * @return left boundary
 */
public double getMin() {
	return min;
}

/**
 * Returns the right boundary for this Lurker's movement.
 * 
 * @return right boundary
 */
public double getMax() {
	return max;
}

/**
 * Updates this element's position to move horizontally according to its
 * velocity (possibly zero) relative to the parent object, and remain "resting"
 * on the parent object (provided that a parent has been set). Upon reaching the
 * left or right boundary of the parent, the horizontal velocity is reversed.
 */
@Override
public void update() {
	setBounds(parent.getXReal(), parent.getXReal() + parent.getWidth());
	setPosition(getXReal() + parent.getDeltaX(), parent.getYReal() - getWidth());
	super.update();
	if (getXReal() + getWidth() >= max) {
		setPosition(max - getWidth(), getYReal());
		setVelocity(-getDeltaX(), -getDeltaY());
	}
	else if(getXReal() <= min) {
		setPosition(min, getYReal());
		setVelocity(Math.abs(getDeltaX()), Math.abs(getDeltaY()));
	}
}

}
