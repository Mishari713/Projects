package hw3;

import java.util.ArrayList;

import api.Direction;
import api.SnakePiece;

/**
 * A Snake is a sequence of (row, column) pairs in which each (row, column) in
 * the sequence represents a 2d position that is horizontally or vertically
 * adjacent to the previous one. Each (row, column) is represented by an
 * instance of the class SnakePiece. The first piece in the sequence is called
 * the "head", and the last one is called the "tail". In addition to the list of
 * SnakePiece objects, a Snake has an id consisting of a single character.
 * <p>
 * A snake may or may not be <em>valid</em>. More precisely, a snake is defined
 * to be valid if:
 * <ul>
 * <li>It has at least two pieces
 * <li>There are no null elements in its pieces list
 * <li>There are no duplicates in its pieces list
 * <li>Each piece in the list has a row and column position that is horizontally
 * or vertically adjacent to the previous piece in the list
 * </ul>
 * The methods to add pieces to a snake do NOT do any error-checking. Instead,
 * clients can use the method isValid to check whether the snake is valid.
 * 
 */
public class Snake {

private char snakeId;

private SnakePiece piece;

private ArrayList<SnakePiece> snake;

/**
 * Constructs a Snake with an empty list of SnakePiece objects and with the
 * given character as its ID.
 * 
 * @param givenId ID to use for this Snake
 */
public Snake(char givenId) {
	snakeId = givenId;
	snake = new ArrayList<>();
}

/**
 * Adds a new SnakePiece to the end of this Snake's list of pieces. This method
 * does no error-checking to ensure the given position is actually adjacent to
 * the current tail.
 * 
 * @param row row for the new SnakePiece
 * @param col column for the new SnakePiece
 */
public void addPiece(int row, int col) {
	SnakePiece piece = new SnakePiece(row, col);
	snake.add(piece);

}

/**
 * Sets this Snake's list of pieces at the given index to be a new SnakePiece
 * with the given row and column. If the current list of pieces is shorter than
 * the given index, it is padded with nulls so the given index can be set. This
 * method does no error-checking to ensure the given (row, column) is actually
 * adjacent to its neighbors in the list of pieces.
 * 
 * @param index index in the list of pieces where the new SnakePiece will be set
 * @param row   row for the new SnakePiece
 * @param col   column for the new SnakePiece
 */
public void addPiece(int index, int row, int col) {
	SnakePiece x = new SnakePiece(row, col);
	while(index >= snake.size()) {
			snake.add(null);
	}
	snake.set(index, x);
}

/**
 * Returns the ID for this Snake.
 * 
 * @return ID for this Snake
 */
public char getId() {
	return snakeId;
}

/**
 * Returns true if the ID is one of the characters 'g' or 'G'.
 * 
 * @return true if ID is 'g' or 'G'
 */
public boolean isGreen() {
	if (Character.toLowerCase(snakeId) == 'g') {
		return true;
	}
	return false;
}

/**
 * Returns the first piece in this Snake's list of pieces, or null if this snake
 * has no pieces.
 * 
 * @return first piece
 */
public SnakePiece getHead() {
	if (snake.size() > 0) {
		return snake.get(0);
	}
	return null;
}

/**
 * Returns the last piece in this Snake's list of pieces, or null if this snake
 * has no pieces.
 * 
 * @return last piece
 */
public SnakePiece getTail() {
	if (snake.size() > 0) {
		return snake.get(snake.size() - 1);
	}
	return null;
}

/**
 * Returns true if given row and column match the row and column of this Snake's
 * first piece. Returns false if this snake has no pieces.
 * 
 * @param row given row
 * @param col given column
 * @return true if the head of this Snake has the same row and column
 */
public boolean isHead(int row, int col) {
	SnakePiece x = snake.get(0);
	SnakePiece p = new SnakePiece(row, col);
	if (x.row() == p.row() && x.col() == p.col()) {
		return true;
	}
	return false;
}

/**
 * Returns true if given row and column match the row and column of this Snake's
 * last piece. Returns false if this snake has no pieces.
 * 
 * @param row given row
 * @param col given column
 * @return true if the tail of this Snake has the same row and column
 */
public boolean isTail(int row, int col) {
	SnakePiece x = snake.get(snake.size() - 1);
	SnakePiece p = new SnakePiece(row, col);
	if (x.row() == p.row() && x.col() == p.col()) {
		return true;
	}
	return false;
}

/**
 * Returns this Snake's list of pieces. Normally this method is used to render
 * or display the game; clients should not directly modify the snakes through
 * this method.
 * 
 * @return list of SnakePiece objects for this Snake
 */
public ArrayList<SnakePiece> getPieces() {
	return snake;
}

private void moveHelper(Direction dir, boolean check, int num) {
	if (check) {
		if (dir == Direction.UP) {
			SnakePiece temp = snake.get(0);
			SnakePiece s = new SnakePiece(temp.row() - 1, temp.col());
			snake.add(0, s);
			for (int i = 0; i < num; i++) {
				snake.remove(snake.size() - 1);
			}
		} else if (dir == Direction.RIGHT) {
			SnakePiece temp = snake.get(0);
			SnakePiece s = new SnakePiece(temp.row(), temp.col() + 1);
			snake.add(0, s);
			for (int i = 0; i < num; i++) {
				snake.remove(snake.size() - 1);
			}
		} else if (dir == Direction.DOWN) {
			SnakePiece temp = snake.get(0);
			SnakePiece s = new SnakePiece(temp.row() + 1, temp.col());
			snake.add(0, s);
			for (int i = 0; i < num; i++) {
				snake.remove(snake.size() - 1);
			}
		} else {
			SnakePiece temp = snake.get(0);
			SnakePiece s = new SnakePiece(temp.row(), temp.col() - 1);
			snake.add(0, s);
			for (int i = 0; i < num; i++) {
				snake.remove(snake.size() - 1);
			}
		}
	} else {
		if (dir == Direction.UP) {
			SnakePiece temp = snake.get(snake.size() - 1);
			SnakePiece s = new SnakePiece(temp.row() - 1, temp.col());
			snake.add(snake.size(), s);
			snake.remove(0);
		} else if (dir == Direction.RIGHT) {
			SnakePiece temp = snake.get(snake.size() - 1);
			SnakePiece s = new SnakePiece(temp.row(), temp.col() + 1);
			snake.add(snake.size(), s);
			snake.remove(0);
		} else if (dir == Direction.DOWN) {
			SnakePiece temp = snake.get(snake.size() - 1);
			SnakePiece s = new SnakePiece(temp.row() + 1, temp.col());
			snake.add(snake.size(), s);
			snake.remove(0);
		} else {
			SnakePiece temp = snake.get(snake.size() - 1);
			SnakePiece s = new SnakePiece(temp.row(), temp.col() - 1);
			snake.add(snake.size(), s);
			snake.remove(0);
		}
	}
}

/**
 * Moves the head of this Snake in the given direction without changing its
 * length. Does nothing if the snake has fewer than two pieces.
 * 
 * @param dir which direction
 */
public void moveHead(Direction dir) {
	if (snake.size() < 2) {
		return;
	} else {
		moveHelper(dir, true, 1);
	}
}

/**
 * Moves the tail of this Snake in the given direction without changing its
 * length. Does nothing if the snake has fewer than two pieces.
 * 
 * @param dir which direction
 */
public void moveTail(Direction dir) {
	if (snake.size() < 2) {
		return;
	} else {
		moveHelper(dir, false, 0);
	}
}

/**
 * Moves the head of this Snake in the given direction, increasing its length by
 * 1. Does nothing if the snake has fewer than two pieces.
 * 
 * @param dir which direction
 */
public void moveHeadAndGrow(Direction dir) {
	if (snake.size() < 2) {
		return;
	} else {
		moveHelper(dir, true, 0);
	}
}

/**
 * Moves the head of this Snake in the given direction, decreasing its length by
 * 1. Does nothing if this Snake fewer than three pieces.
 * 
 * @param dir which direction
 */
public void moveHeadAndShrink(Direction dir) {
	if (snake.size() < 2) {
		return;
	} else {
		moveHelper(dir, true, 2);
	}
}

/**
 * Determines whether this snake is valid. A snake is <em>valid</em> if
 * <ul>
 * <li>It has at least two pieces
 * <li>There are no null elements in its pieces list
 * <li>There are no duplicates in its pieces list
 * <li>Each piece in the list has a row and column position that is horizontally
 * or vertically adjacent to the previous piece in the list
 * </ul>
 * 
 * @return true if this snake is valid, false otherwise
 */
public boolean isValid() {
	if (snake.size() < 2) {
		return false;
	}
	if (snake.contains(null)) {
		return false;
	}
	for (int i = 0; i < snake.size() - 1; i++) {
		if (snake.get(i).row() == snake.get(i + 1).row() && (snake.get(i).col() + 1 != snake.get(i + 1).col()
				&& snake.get(i).col() - 1 != snake.get(i + 1).col())) {
			return false;
		} else if (snake.get(i).row() + 1 == snake.get(i + 1).row()
				&& snake.get(i).col() + 1 == snake.get(i + 1).col()) {
			return false;
		} else if ((snake.get(i).row() + 1 != snake.get(i + 1).row()
				&& snake.get(i).row() - 1 != snake.get(i + 1).row()) && snake.get(i).col() == snake.get(i + 1).col()) {
			return false;
		} else if (snake.get(i).row() - 1 == snake.get(i + 1).row()
				&& snake.get(i).col() - 1 == snake.get(i + 1).col()) {
			return false;
		}
		for (int j = i + 1; j < snake.size(); j++) {
			if (snake.get(i).row() == snake.get(j).row() && snake.get(i).col() == snake.get(j).col()) {
				return false;
			}
		}
	}
	return true;
}

}
