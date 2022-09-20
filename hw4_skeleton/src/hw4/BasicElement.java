package hw4;

import java.awt.Rectangle;
import api.GameElement;

/**
 * Minimal concrete extension of GameElement. The <code>update</code> method in
 * this implementation just increments the frame count.
 * 
 * The hierarchy of this assignment is Platform and Elevator extends an abstract
 * class named Parent. Parent, Lurker, and missile extends MovingElement.
 * MovingElement, Charm, and Flash extends BasicElemet which extends
 * GameElement. The Choices I made were made by checking a class and looking for
 * another class that has all the methods the first one has. For example,
 * MovingElement methods are all used by Lurker, so Lurker extends
 * MovingElement.
 * 
 * Note: The abstract class was made to avoid having duplicate methods in Platform and Elevator.
 * 
 * @author Mishari Alharbi
 */
public class BasicElement extends GameElement {

/**
 * The real number of this object x-coordinate.
 */
private double numX;

/**
 * The real number of this object y-coordinate.
 */
private double numY;

/**
 * The width of this object.
 */
private int width;

/**
 * The height of this object.
 */
private int height;

/**
 * If this variable is marked true, this object is considered deleted.
 */
private boolean mark;

/**
 * A variable used to gold the frame counter.
 */
private int frameCount;

/**
 * Constructs a new BasicElement.
 * 
 * @param x      x-coordinate of upper left corner
 * @param y      y-coordinate of upper left corner
 * @param width  element's width
 * @param height element's height
 */
public BasicElement(double x, double y, int width, int height) {
	numX = x;
	numY = y;
	this.width = width;
	this.height = height;
	mark = false;
	frameCount = 0;
}

/**
 * Returns the horizontal coordinate of the upper-left corner of this object's
 * bounding rectangle, rounded to the nearest integer.
 * 
 * @return the x-coordinate
 */
@Override
public int getXInt() {
	return (int) Math.round(numX);
}

/**
 * Returns the vertical coordinate of the upper-left corner of this object's
 * bounding rectangle, rounded to the nearest integer.
 * 
 * @return the y-coordinate
 */
@Override
public int getYInt() {
	return (int) Math.round(numY);
}

/**
 * Returns the x-coordinate's exact value as a double.
 * 
 * @return the x-coordinate
 */
@Override
public double getXReal() {
	return numX;
}

/**
 * Returns the y-coordinate's exact value as a double.
 * 
 * @return the y-coordinate
 */
@Override
public double getYReal() {
	return numY;
}

/**
 * Returns the width of this object's bounding rectangle.
 * 
 * @return width of bounding rectangle
 */
@Override
public int getWidth() {
	return width;
}

/**
 * Returns the height of this object's bounding rectangle.
 * 
 * @return height of bounding rectangle
 */
@Override
public int getHeight() {
	return height;
}

/**
 * Returns the bounding rectangle for this object as an instance of
 * java.awt.Rectangle.
 * 
 * @return bounding rectangle
 */
@Override
public Rectangle getRect() {
	Rectangle r = new Rectangle(getXInt(), getYInt(), width, height);
	return r.getBounds();
}

/**
 * Sets the position of this element.
 * 
 * @param newX the new x-coordinate
 * @param newY the new y-coordinate
 */
@Override
public void setPosition(double newX, double newY) {
	numX = newX;
	numY = newY;
}

/**
 * Returns true if this element has been marked for deletion.
 * 
 * @return true if this element has been marked for deletion
 */
@Override
public boolean isMarked() {
	return mark;
}

/**
 * Marks this element for deletion.
 */
@Override
public void markForDeletion() {
	mark = true;
}

/**
 * Determines whether this element's bounding rectangle overlaps the given
 * element's bounding rectangle.
 * 
 * @param other given element
 * @return true if this element overlaps the given element
 */
@Override
public boolean collides(GameElement other) {
	return this.getRect().intersects(other.getRect());
}

/**
 * Returns the number of times that update() has been invoked for this object.
 * 
 * @return number of frames
 */
@Override
public int getFrameCount() {
	return frameCount;
}

/**
 * Updates this object's attributes for the next frame.
 */
@Override
public void update() {
	frameCount++;
}

}
