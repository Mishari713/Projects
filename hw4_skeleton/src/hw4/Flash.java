
package hw4;

/**
 * Implementation of GameElement that does not move. Instead, it is intended to
 * appear on the screen for a fixed number of frames.
 * 
 * @author Mishari Alharbi
 */
public class Flash extends BasicElement {

/**
 * The remainigLife this object has.
 */
private int remaningLife;

/**
 * Constructs a new Flash.
 * 
 * @param x           x-coordinate of upper left corner
 * @param y           y-coordinate of upper left corner
 * @param width       element's width
 * @param height      element's height
 * @param initialLife the number of frames until this element marks itself for
 *                    deletion
 */
public Flash(double x, double y, int width, int height, int initialLife) {
	super(x, y, width, height);
	remaningLife = initialLife;
}

/**
 * Returns the number of frames remaining until this element marks itself for
 * deletion
 * 
 * @return number of remaining frames
 */
public int getRemainingLife() {
	return remaningLife;
}

/**
 * Decrements the remaining life of this Flash. If it reaches zero, this element
 * marks itself for deletion.
 */
@Override
public void update() {
	super.update();
	if (remaningLife > 0) {
		remaningLife--;
	}
	else {
		markForDeletion();
	}
}

}
