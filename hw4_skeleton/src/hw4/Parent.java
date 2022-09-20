package hw4;

import java.util.ArrayList;

import api.GameElement;

/**
 * An abstract class used to avoid having duplicate methods in Platform and
 * Elevator.
 * 
 * @author Mishari Alharbi
 *
 */
public abstract class Parent extends MovingElement {
/**
 * The minimum bound.
 */
private double min;

/**
 * The maximum bound.
 */
private double max;

/**
 * An Arraylist that store the children of this parent.
 */
private ArrayList<GameElement> children;

public Parent(double x, double y, int width, int height) {
	super(x, y, width, height);
	min = Double.NEGATIVE_INFINITY;
	max = Double.POSITIVE_INFINITY;
	children = new ArrayList<GameElement>();
}

/**
 * Sets the boundaries for this parent's movement
 * 
 * @param min - left/ upper boundary
 * @param max - right/ lower boundary
 */
public void setBounds(double min, double max) {
	this.min = min;
	this.max = max;
}

/**
 * Returns the left/ upper boundary for this parent's movement.
 * 
 * @return left/ upper boundary
 */
public double getMin() {
	return min;
}

/**
 * Returns the right/ lower boundary for this parent's movement.
 * 
 * @return right/ lower boundary
 */
public double getMax() {
	return max;
}

/**
 * Adds a child object to this parent, and sets this parent to be the child's
 * parent.
 * 
 * @param charm - a Charm object to become a child of this parent
 */
public void addChild(Charm charm) {
	charm.setParent(this);
	children.add(charm);
}

/**
 * Adds a child object to this parent, and sets this parent to be the child's
 * parent.
 * 
 * @param lurker - a Lurker object to become a child of this parent
 */
public void addChild(Lurker lurker) {
	lurker.setParent(this);
	children.add(lurker);
}

/**
 * Returns a list of all this parent's children.
 * 
 * @return list of children
 */
public ArrayList<GameElement> getChildren() {
	return children;
}

/**
 * Deletes all children of this parent that have been marked.
 */
public void deleteMarkedChildren() {
	for (int i = 0; i < children.size(); i++) {
		if (children.get(i).isMarked()) {
			children.remove(i);
		}
	}
}

/**
 * Update method called by Platform or Elevator that call the MovingElement's
 * Update();
 */
@Override
public void update() {
	super.update();
}
}
