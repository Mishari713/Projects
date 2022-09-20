package hw4;

/**
 * Moving element in which the vertical velocity is adjusted each frame by a
 * gravitational constant to simulate gravity. It may also be put into a
 * "ballistic" state in which no other modifications to velocity take place
 * other than gravity.
 * 
 * @author Mishari Alharbi
 */
public class Missile extends MovingElement {

/**
 * A variable used to hold the state of this object.
 */
private boolean ballisticCheck;

/**
 * A number to store the gravity.
 */
private double numG;

/**
 * Constructs a new Missile.
 * 
 * @param x      x-coordinate of upper left corner
 * @param y      y-coordinate of upper left corner
 * @param width  element's width
 * @param height element's height
 */
public Missile(double x, double y, int width, int height) {
	super(x, y, width, height);
	ballisticCheck = false;
	numG = 0;

}

/**
 * Sets or unsets the ballistic state of this Missile.
 * 
 * @param ballistic - true if ballistic, false otherwise
 */
public void setBallistic(boolean ballistic) {
	ballisticCheck = ballistic;

}

/**
 * Determines whether this Missile is in a ballistic state.
 * 
 * @return true if ballistic, false otherwise
 */
public boolean isBallistic() {
	return ballisticCheck;
}

/**
 * Sets the gravitational constant, assumed to be in units of "pixels per frame
 * per frame". Remember that the positive direction is down.
 * 
 * @param gravity - gravitational constant to use
 */
public void setGravity(double gravity) {
	numG = gravity;
}

/**
 * Sets the velocity for this element, but does nothing if the element is
 * currently ballistic.
 * 
 * @param deltaX - new horizontal velocity
 * @param deltaY - new vertical velocity
 */
@Override
public void setVelocity(double deltaX, double deltaY) {
	if(!isBallistic()) {
		super.setVelocity(deltaX, deltaY);
	}

}

/**
 * Updates position and adds the gravitational constant to the y-component of
 * the velocity.
 */
@Override
public void update() {
	super.update();
	super.setVelocity(getDeltaX(), getDeltaY() + numG);
}

}