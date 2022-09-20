package hw4;

/**
 * An Elevator is a GameElement with two distinctive behaviors. First, it can be
 * set up to move vertically within a fixed set of boundaries. On reaching a
 * boundary, the y-component of its velocity is reversed. Second, it maintains a
 * list of <em>child</em> elements whose basic motion all occurs relative to the
 * Elevator.
 * 
 * @author Mishari Alharbi
 */
public class Elevator extends Parent {

/**
 * Constructs a new Elevator. Initially the upper and lower boundaries are
 * <code>Double.NEGATIVE_INFINITY</code> and
 * <code>Double.POSITIVE_INFINITY</code>, respectively.
 * 
 * @param x      x-coordinate of initial position of upper left corner
 * @param y      y-coordinate of initial position of upper left corner
 * @param width  element's width
 * @param height element's height
 */
public Elevator(double x, double y, int width, int height) {
	super(x, y, width, height);
}

/**
 * Updates this element's state for a new frame, and additionally calls update
 * on all its children.
 */
@Override
public void update() {
	super.update();

	if (getYReal() + getHeight() >= getMax()) {
		setPosition(getXReal(), getMax() - getHeight());
		setVelocity(getDeltaX(), -getDeltaY());
	} else if (getYReal() <= getMin()) {
		setPosition(getXReal(), getMin());
		setVelocity(getDeltaX(), -getDeltaY());
	}

	for (int i = 0; i < getChildren().size(); i++) {
		getChildren().get(i).update();
	}
}

}
