package hw4;

/**
 * Concrete implementation of GameElement in which the <code>update</code>
 * method updates the position each frame according to a <em>velocity</em>
 * vector (deltaX, deltaY). The units are assumed to be "pixels per frame".
 * 
 * @author Mishari Alharbi
 */
public class MovingElement extends BasicElement {

/**
 * The velocity x-coordinate.
 */
private double deltaX;

/**
 * The velocity y-coordinate.
 */
private double deltaY;

/**
 * Constructs a MovingElement with a default velocity of zero in both
 * directions.
 * 
 * @param x      x-coordinate of upper left corner
 * @param y      y-coordinate of upper left corner
 * @param width  object's width
 * @param height object's height
 */
public MovingElement(double x, double y, int width, int height) {
	super(x, y, width, height);
	deltaX = 0;
	deltaY = 0;
}

/**
 * Returns the x-component of this object's velocity.
 * 
 * @return horizontal velocity
 */
public double getDeltaX() {

	return deltaX;
}

/**
 * Returns the y-component of this object's velocity.
 * 
 * @return vertical velocity
 */
public double getDeltaY() {

	return deltaY;
}

/**
 * Sets the velocity for this object.
 * 
 * @param deltaX - new horizontal velocity
 * @param deltaY - new vertical velocity
 */
public void setVelocity(double deltaX, double deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;

}


/**
 * Update method adds (deltaX, deltaY) to the current position.
 */
@Override
public void update() {
	super.update();
	setPosition(getXReal() + deltaX, getYReal() + deltaY);
}

}
