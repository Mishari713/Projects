package hw3;

import java.util.ArrayList;
//import static Status;

import api.BasketCell;
import api.Direction;
import api.SnakeLayoutException;
import api.SnakePiece;
import api.GridUtil;

/**
 * This class represents the basic game state for a "Snake Escape" game,
 * including a 2d grid of cells and a list of snakes.
 */
public class SnakeBasket {
/**
 * The 2D array of cells.
 */
private BasketCell[][] grid;

/**
 * The list of snakes.
 */
private ArrayList<Snake> snakes;

private Snake currentSnake;

private SnakePiece currentPiece;

private int numMoves;

private boolean gameEnd;
// TODO: any other instance variables you need

/**
 * Constructs an instance of this game from the given string array and list of
 * snakes. <em>Snake information, if any, in the given string array is
 * ignored</em>.
 * 
 * @param desc        array of strings representing the initial grid layout
 * @param givenSnakes array of snakes
 */
public SnakeBasket(String[] desc, ArrayList<Snake> givenSnakes) {
	grid = GridUtil.createGridFromDescriptor(desc);
	snakes = givenSnakes;
	resetAllSnakes();
	currentSnake = null;
	numMoves = 0;
	gameEnd = false;

	// TODO:
	// implement resetAllSnakes
	// any other initialization you need
}

/**
 * Constructs an instance of this game from the given string array.
 * 
 * @param desc array of strings representing the initial grid layout, including
 *             ids and placement of snakes
 */
public SnakeBasket(String[] desc) {
	grid = GridUtil.createGridFromDescriptor(desc);
	snakes = SnakeUtil.findSnakes(desc);
	resetAllSnakes();
	currentSnake = null;
	numMoves = 0;
	gameEnd = false;
	// TODO:
	// implement SnakeUtil.findSnakes
	// implement resetAllSnakes
	// any other initialization you need
}

/**
 * Returns the grid cell at the given row and column.
 * 
 * @param row given row
 * @param col given column
 * @return grid cell at the given row and column
 */
public BasketCell getCell(int row, int col) {
	return grid[row][col];
}

/**
 * Returns the number of rows in this game.
 * 
 * @return number of rows
 */
public int getRows() {
	return grid.length;
}

/**
 * Returns the number of columns in this game.
 * 
 * @return number of columns
 */
public int getCols() {
	return grid[0].length;
}

/**
 * Returns the currently grabbed snake, if any. Returns null if there is no
 * current snake.
 * 
 * @return current snake, if any
 */
public Snake currentSnake() {
	return currentSnake;
}

/**
 * Returns true if there is a current snake and it was grabbed at the head end,
 * false if it was grabbed by the tail.
 * 
 * @return true if current snake was grabbed by the head
 */
public boolean currentWasGrabbedByHead() {
	if (currentSnake.isHead(currentPiece.row(), currentPiece.col())) {
		return true;
	}
	return false;
}

/**
 * Returns this SnakeBasket's list of all snakes. Normally this method is used
 * to easily render or display the game; clients should not modify the list or
 * the snakes.
 * 
 * @return list of all snakes
 */
public ArrayList<Snake> getAllSnakes() {
	return snakes;
}

/**
 * Returns true if the green snake is in the exit cell, false otherwise.
 * 
 * @return true if green snake is in the exit
 */
public boolean isOver() {
	return gameEnd;
}

/**
 * Returns the total number of moves that have been made so far in this game.
 * 
 * @return number of moves
 */
public int getMoves() {
	return numMoves;
}

/**
 * Attempts to grab a snake by the head or tail at the given position. If there
 * is not already a current snake, and if the given position contains a snake
 * head or tail, that becomes the current snake. If this game already has a
 * current snake selected, this method does nothing.
 * 
 * @param row given row at which to grab a snake
 * @param col given column at which to grab a snake
 */
public void grabSnake(int row, int col) {
	if (currentSnake() == null) {
		if (getCell(row, col).hasSnake()) {
			Snake s = grid[row][col].getSnake();
			SnakePiece head = s.getHead();
			SnakePiece tail = s.getTail();
			if (row == head.row() && col == head.col()) {
				currentSnake = s;
				currentPiece = currentSnake.getHead();
			} else if (row == tail.row() && col == tail.col()) {
				currentSnake = s;
				currentPiece = currentSnake.getTail();
			}

		}
	} else {
		return;
	}
}

/**
 * Sets the current snake to null, if any.
 */
public void releaseSnake() {
	if (currentSnake() != null) {
		currentSnake = null;
	}
}

/**
 * Attempt to move the current snake (head or tail) to an adjacent cell in the
 * given direction. If a move is possible, this method updates the current
 * snake, the move count, and the grid cells (via resetAllSnakes).
 * <p>
 * It is only possible to move in the following cases:
 * <ul>
 * <li>The adjacent cell is empty; then the snake (head or tail) moves into the
 * cell
 * <li>The adjacent cell is the exit and the current snake is the green one;
 * then the snake (head or tail) moves into the exit and the game ends
 * <li>The current snake was grabbed by the head, and the adjacent cell is
 * row/column of the current snake's tail; then the snake (head) moves into the
 * cell
 * <li>The current snake was grabbed by the tail, and the adjacent cell is
 * row/column of the current snake's head; then the snake (tail) moves into the
 * cell
 * <li>The current snake was grabbed by the head and the adjacent cell is an
 * apple; then the apple is removed and the snake (head) moves into the cell,
 * increasing its size by one piece
 * <li>The current snake was grabbed by the head, has at least three pieces, and
 * the adjacent cell is a mushroom; then the mushroom is removed and the snake
 * (head) moves into the cell, reducing its size by one piece
 * </ul>
 * If none of the above conditions is met, this method does nothing. If any of
 * the conditions is met and a move occurs, the move count is increased by 1. If
 * there is no currently grabbed snake, this method does nothing.
 * 
 * @param dir Direction in which to attempt to move
 */
public void move(Direction dir) {
	if (!isOver() && currentSnake != null) {

		// currentPiece is the snake head
		if (currentWasGrabbedByHead()) {

			// currentPiece is the snake head, the direction is UP, the chosen cell is
			// empty, and not a wall
			if (dir == Direction.UP && grid[currentPiece.row() - 1][currentPiece.col()].isEmpty()
					&& !grid[currentPiece.row() - 1][currentPiece.col()].isWall()) {
				currentSnake.moveHead(Direction.UP);
				numMoves++;
				currentPiece = currentSnake.getHead();
				resetAllSnakes();

				// currentPiece is the snake head, the direction is UP, and the chosen cell is
				// not
				// a wall
			} else if (dir == Direction.UP && !grid[currentPiece.row() - 1][currentPiece.col()].isWall()) {

				// currentPiece is the snake head, the direction is UP, the chosen cell is not a
				// wall, and an apple
				if (grid[currentPiece.row() - 1][currentPiece.col()].isApple()) {
					currentSnake.moveHeadAndGrow(Direction.UP);
					numMoves++;
					grid[currentPiece.row() - 1][currentPiece.col()].clearFood();
					currentPiece = currentSnake.getHead();
					resetAllSnakes();

					// currentPiece is the snake head, the direction is UP, the chosen cell is not a
					// wall, and a mushroom
				} else if (grid[currentPiece.row() - 1][currentPiece.col()].isMushroom()) {
					currentSnake.moveHeadAndShrink(Direction.UP);
					numMoves++;
					grid[currentPiece.row() - 1][currentPiece.col()].clearFood();
					currentPiece = currentSnake.getHead();
					resetAllSnakes();

					// currentPiece is the snake head, the direction is UP, the chosen cell is not a
					// wall, and an exit
				} else if (grid[currentPiece.row() - 1][currentPiece.col()].isExit() && currentSnake.getId() == 'g') {
					currentSnake.moveHead(Direction.UP);
					numMoves++;
					currentPiece = currentSnake.getHead();
					resetAllSnakes();
					gameEnd = true;
				}

				// currentPiece is the snake head, the direction is DOWN, the chosen cell is
				// empty, and not a wall
			} else if (dir == Direction.DOWN && grid[currentPiece.row() + 1][currentPiece.col()].isEmpty()
					&& !grid[currentPiece.row() + 1][currentPiece.col()].isWall()) {
				currentSnake.moveHead(Direction.DOWN);
				numMoves++;
				currentPiece = currentSnake.getHead();
				resetAllSnakes();

				// currentPiece is the snake head, the direction is DOWN, and the chosen cell is
				// not a wall
			} else if (dir == Direction.DOWN && !grid[currentPiece.row() + 1][currentPiece.col()].isWall()) {

				// currentPiece is the snake head, the direction is DOWN, the chosen cell is not
				// a
				// wall, and an apple
				if (grid[currentPiece.row() + 1][currentPiece.col()].isApple()) {
					currentSnake.moveHeadAndGrow(Direction.DOWN);
					numMoves++;
					grid[currentPiece.row() + 1][currentPiece.col()].clearFood();
					currentPiece = currentSnake.getHead();
					resetAllSnakes();

					// currentPiece is the snake head, the direction is DOWN, the chosen cell is not
					// a
					// wall, and a mushroom
				} else if (grid[currentPiece.row() + 1][currentPiece.col()].isMushroom()) {
					currentSnake.moveHeadAndShrink(Direction.DOWN);
					numMoves++;
					grid[currentPiece.row() + 1][currentPiece.col()].clearFood();
					currentPiece = currentSnake.getHead();
					resetAllSnakes();

					// currentPiece is the snake head, the direction is DOWN, the chosen cell is not
					// a
					// wall, and an exit
				} else if (grid[currentPiece.row() + 1][currentPiece.col()].isExit() && currentSnake.getId() == 'g') {
					currentSnake.moveHead(Direction.DOWN);
					numMoves++;
					currentPiece = currentSnake.getHead();
					resetAllSnakes();
					gameEnd = true;
				}

				// currentPiece is the snake head, the direction is RIGHT, the chosen cell is
				// empty, and not a wall
			} else if (dir == Direction.RIGHT && grid[currentPiece.row()][currentPiece.col() + 1].isEmpty()
					&& !grid[currentPiece.row()][currentPiece.col() + 1].isWall()) {
				currentSnake.moveHead(Direction.RIGHT);
				numMoves++;
				currentPiece = currentSnake.getHead();
				resetAllSnakes();

				// currentPiece is the snake head, the direction is RIGHT, and the chosen cell
				// is not a wall
			} else if (dir == Direction.RIGHT && !grid[currentPiece.row()][currentPiece.col() + 1].isWall()) {

				// currentPiece is the snake head, the direction is RIGHT, the chosen cell is
				// not a
				// wall, and an apple
				if (grid[currentPiece.row()][currentPiece.col() + 1].isApple()) {
					currentSnake.moveHeadAndGrow(Direction.RIGHT);
					numMoves++;
					grid[currentPiece.row()][currentPiece.col() + 1].clearFood();
					currentPiece = currentSnake.getHead();
					resetAllSnakes();

					// currentPiece is the snake head, the direction is RIGHT, the chosen cell is
					// not a
					// wall, and a mushroom
				} else if (grid[currentPiece.row()][currentPiece.col() + 1].isMushroom()) {
					currentSnake.moveHeadAndShrink(Direction.RIGHT);
					numMoves++;
					grid[currentPiece.row()][currentPiece.col() + 1].clearFood();
					currentPiece = currentSnake.getHead();
					resetAllSnakes();

					// currentPiece is the snake head, the direction is RIGHT, the chosen is cell
					// not a
					// wall, and an exit
				} else if (grid[currentPiece.row()][currentPiece.col() + 1].isExit() && currentSnake.getId() == 'g') {
					currentSnake.moveHead(Direction.RIGHT);
					numMoves++;
					currentPiece = currentSnake.getHead();
					resetAllSnakes();
					gameEnd = true;
				}

				// currentPiece is the snake head, the direction is LEFT, and the chosen cell
				// is not a wall
			} else if (dir == Direction.LEFT && grid[currentPiece.row()][currentPiece.col() - 1].isEmpty()
					&& !grid[currentPiece.row()][currentPiece.col() - 1].isWall()) {
				currentSnake.moveHead(Direction.LEFT);
				numMoves++;
				currentPiece = currentSnake.getHead();
				resetAllSnakes();

				// currentPiece is the snake head, the direction is LEFT, and the chosen cell
				// is not a wall
			} else if (dir == Direction.LEFT && !grid[currentPiece.row()][currentPiece.col() - 1].isWall()) {

				// currentPiece is the snake head, the direction is LEFT, the chosen cell is not
				// a
				// wall, and an apple
				if (grid[currentPiece.row()][currentPiece.col() - 1].isApple()) {
					currentSnake.moveHeadAndGrow(Direction.LEFT);
					numMoves++;
					grid[currentPiece.row()][currentPiece.col() - 1].clearFood();
					currentPiece = currentSnake.getHead();
					resetAllSnakes();

					// currentPiece is the snake head, the direction is LEFT, the chosen cell is not
					// a
					// wall, and a mushroom
				} else if (grid[currentPiece.row()][currentPiece.col() - 1].isMushroom()) {
					currentSnake.moveHeadAndShrink(Direction.LEFT);
					numMoves++;
					grid[currentPiece.row()][currentPiece.col() - 1].clearFood();
					currentPiece = currentSnake.getHead();
					resetAllSnakes();

					// currentPiece is the snake head, the direction is LEFT, the chosen cell is not
					// a
					// wall, and an exit
				} else if (grid[currentPiece.row()][currentPiece.col() - 1].isExit() && currentSnake.getId() == 'g') {
					currentSnake.moveHead(Direction.LEFT);
					numMoves++;
					currentPiece = currentSnake.getHead();
					resetAllSnakes();
					gameEnd = true;
				}
			}

			// currentPiece is the snake tail
		} else {

			// currentPiece is the snake tail, the direction is UP, and the chosen cell is
			// empty,
			// not a wall, not an apple, and not a mushroom
			if (dir == Direction.UP && grid[currentPiece.row() - 1][currentPiece.col()].isEmpty()
					&& !grid[currentPiece.row() - 1][currentPiece.col()].isWall()
					&& !grid[currentPiece.row() - 1][currentPiece.col()].isApple()
					&& !grid[currentPiece.row() - 1][currentPiece.col()].isMushroom()) {
				currentSnake.moveTail(Direction.UP);
				numMoves++;
				currentPiece = currentSnake.getTail();
				resetAllSnakes();

				// currentPiece is the snake tail, the direction is UP, the chosen cell is not a
				// wall, and an exit
			} else if (grid[currentPiece.row() - 1][currentPiece.col()].isExit() && currentSnake.getId() == 'g') {
				currentSnake.moveTail(Direction.UP);
				numMoves++;
				currentPiece = currentSnake.getTail();
				resetAllSnakes();
				gameEnd = true;
			}

			// currentPiece is the snake tail, the direction is DOWN, and the chosen cell is
			// empty,
			// not a wall, not an apple, and not a mushroom
			else if (dir == Direction.DOWN && grid[currentPiece.row() + 1][currentPiece.col()].isEmpty()
					&& !grid[currentPiece.row() + 1][currentPiece.col()].isWall()
					&& !grid[currentPiece.row() + 1][currentPiece.col()].isApple()
					&& !grid[currentPiece.row() + 1][currentPiece.col()].isMushroom()) {
				currentSnake.moveTail(Direction.DOWN);
				numMoves++;
				currentPiece = currentSnake.getTail();
				resetAllSnakes();

				// currentPiece is the snake tail, the direction is DOWN, the chosen cell is not a
				// wall, and an exit
			} else if (grid[currentPiece.row() + 1][currentPiece.col()].isExit() && currentSnake.getId() == 'g') {
				currentSnake.moveTail(Direction.DOWN);
				numMoves++;
				currentPiece = currentSnake.getTail();
				resetAllSnakes();
				gameEnd = true;
			}
			
			// currentPiece is the snake tail, the direction is RIGHT, and the chosen cell is
			// empty,
			// not a wall, not an apple, and not a mushroom
			else if (dir == Direction.RIGHT && grid[currentPiece.row()][currentPiece.col() + 1].isEmpty()
					&& !grid[currentPiece.row()][currentPiece.col() + 1].isWall()
					&& !grid[currentPiece.row()][currentPiece.col() + 1].isApple()
					&& !grid[currentPiece.row()][currentPiece.col() + 1].isMushroom()) {
				currentSnake.moveTail(Direction.RIGHT);
				numMoves++;
				currentPiece = currentSnake.getTail();
				resetAllSnakes();

				// currentPiece is the snake tail, the direction is RIGHT, the chosen cell is not a
				// wall, and an exit
			} else if (grid[currentPiece.row()][currentPiece.col() + 1].isExit() && currentSnake.getId() == 'g') {
				currentSnake.moveTail(Direction.RIGHT);
				numMoves++;
				currentPiece = currentSnake.getTail();
				resetAllSnakes();
				gameEnd = true;
			}
			
			// currentPiece is the snake tail, the direction is LEFT, and the chosen cell is
			// empty,
			// not a wall, not an apple, and not a mushroom
			else if (dir == Direction.LEFT && grid[currentPiece.row()][currentPiece.col() - 1].isEmpty()
					&& !grid[currentPiece.row()][currentPiece.col() - 1].isWall()
					&& !grid[currentPiece.row()][currentPiece.col() - 1].isApple()
					&& !grid[currentPiece.row()][currentPiece.col() - 1].isMushroom()) {
				currentSnake.moveTail(Direction.LEFT);
				numMoves++;
				currentPiece = currentSnake.getTail();
				resetAllSnakes();

				// currentPiece is the snake tail, the direction is LEFT, the chosen cell is not a
				// wall, and an exit
			} else if (grid[currentPiece.row()][currentPiece.col() - 1].isExit() && currentSnake.getId() == 'g') {
				currentSnake.moveTail(Direction.LEFT);
				numMoves++;
				currentPiece = currentSnake.getTail();
				resetAllSnakes();
				gameEnd = true;
			}
		}
	}
}

/**
 * Clears all snake information from the grid, if any, and then sets it from the
 * current list of snakes. After executing this method, the information in the
 * grid cells and the information in the snake list should be fully consistent.
 */
public void resetAllSnakes() {
	for (int i = 0; i < grid.length; i++) {
		for (int j = 0; j < grid[i].length; j++) {
			if (grid[i][j].hasSnake()) {
				grid[i][j].clearSnake();
			}
		}
	}
	for (int k = 0; k < snakes.size(); k++) {
		ArrayList<SnakePiece> copy = snakes.get(k).getPieces();
		for (int i = 0; i < copy.size(); i++) {
			grid[copy.get(i).row()][copy.get(i).col()].setSnake(snakes.get(k));
		}
	}
}

}
