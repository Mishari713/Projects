package hw4;
/**
 * A Platform is a GameElement with two distinctive behaviors. First, it can be
 * set up to move horizontally within a fixed set of boundaries. On reaching a
 * boundary, the x-component of its velocity is reversed. Second, it maintains a
 * list of <em>child</em> elements whose basic motion all occurs relative to the
 * Platform.
 * 
 * @author Mishari Alharbi
 */
public class Platform extends Parent {

/**
 * Constructs a new Platform. Initially the left and right boundaries are
 * <code>Double.NEGATIVE_INFINITY</code> and
 * <code>Double.POSITIVE_INFINITY</code>, respectively.
 * 
 * @param x      x-coordinate of initial position of upper left corner
 * @param y      y-coordinate of initial position of upper left corner
 * @param width  object's width
 * @param height object's height
 */
public Platform(double x, double y, int width, int height) {
	super(x, y, width, height);
}

/**
 * Updates this object's state for a new frame, and additionally calls update on all its children.
 */
@Override
public void update() {
	super.update();
	
	if (getXReal() + getWidth() >= getMax()) {
		setPosition(getMax() - getWidth(), getYReal());
		setVelocity(-getDeltaX(), getDeltaY());
	}
	else if(getXReal() <= getMin()) {
		setPosition(getMin(), getYReal());
		setVelocity(Math.abs(getDeltaX()), Math.abs(getDeltaY()));
	}

for (int i = 0; i < getChildren().size(); i++) {
	getChildren().get(i).update();
}
}

}