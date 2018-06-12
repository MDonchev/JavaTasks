package structure;


import customExceptions.InvalidDataException;


/**
 * 
 * @author mario
 * 
 *	Class representing the board, that we need.
 *	Its contains an 2D array with rows and columns.
 *  Have public static final variables to represent to its capacity.
 *  Use singleton to be sure that would be created only 1 board.
 *  Have a knight, which is moved on the board.
 */
public class Board {
	
	// defining capacity of the board
	public static final int BOARD_HEIGHT = 3;
	public static final int BOARD_WIDTH = 3;
	
	// defining the final position on which we want our knight to go
	public static final Position FINAL_DESTINATION = new Position(BOARD_HEIGHT - 1, BOARD_WIDTH - 1);
	
	// array, which shows if a position on the board has been visited, and no need to visit again 
	private boolean visited[][] = new boolean[BOARD_HEIGHT][BOARD_WIDTH];
	
	//knight which would be moved
	private Knight knight;
	//board representation via 2D array of positions
	private Position[][] positions = new Position[BOARD_HEIGHT][BOARD_WIDTH];
	
	private static Board instance = null;
	
	
	private Board() {
		for(int i = 0; i<BOARD_HEIGHT; i++) {
			for (int j = 0; j< BOARD_WIDTH; j++) {
				positions[i][j] = new Position(i,j);
			}
		}
	}
	public synchronized static Board getInstance() {
		if (instance == null) {
			instance = new Board();
		}
		return instance;
	}
	
	/**
	 * main method to represent the game.
	 * If we have not add a knight on the board would be generated on left up position on the board
	 */
	public String gameResult() {
		if (knight == null) {
			System.out.println("\n\nNo knight has been set. Setting a knight on position 0 0.\n\n");
			knight = new Knight(positions[0][0]);
		}
		// checking if we have a path from knight current position to the final destination
		if (canReachFinalDestination(knight.getPosition(), FINAL_DESTINATION, visited)) {
			return "\n\nKnight has reached the final destination.\n\n";
		}
		else {
			return "\n\nFinal Destination can not be reached.\n\n";
		}
	}
	
	/**
	 * 
	 * @param currentPosition - represent the current knight position
	 * @param finalPosition - represent the final position where we want our knight to go
	 * @param visited - we use it to see if a position has been visited. 
	 * 					We are not going to visit it again to avoid endless recursion
	 * @return if the is a path from the current position to the final position
	 */
	private boolean canReachFinalDestination(Position currentPosition, Position finalPosition, boolean[][] visited) {
		// if we reached the final position move the horse and return that we have path
		if (currentPosition.equals(finalPosition)) {
			knight.move(finalPosition);
			return true;
		}
		// if the position is not valid or we already have visited it we do not want to go again. 
		if (currentPosition.isInvalidPosition() || 
				visited[currentPosition.getXCoordinate()][currentPosition.getYCoordinate()]) {
			return false;
		}
		
		// mark the current position as visited
		visited[currentPosition.getXCoordinate()][currentPosition.getYCoordinate()] = true;
		
		
		/* 
		 * go on the same method with all possible moves that our horse would go
		 * */
		boolean adjPossibilities = false;
		int currRow = currentPosition.getXCoordinate();
		int currCol = currentPosition.getYCoordinate();

		// all 8 potential positions for our horse must be checked
		for(int i = -2; i<=2; i++) {
			for(int j = -2; j<=2 ;j++) {
				int possRow = currRow + i;
				int possCol = currCol + j;
				int helpRow = Math.abs(possRow - currRow);
				int helpCol = Math.abs(possCol - currCol);
				Position possPos = new Position(possRow, possCol);

				/* if we have valid position, it is part of our path 
					and must be checked if we have path to the final position from there
				*/
				if (!possPos.isInvalidPosition() && ((helpRow == 2 && helpCol == 1) || (helpRow == 1 && helpCol == 2))){
					adjPossibilities = adjPossibilities || canReachFinalDestination(possPos, finalPosition, visited);
				}
				
			}
		}
		return adjPossibilities;
	}
	/**
	 * setting knights position
	 * @param x - X coordinate on the board, showing the row
	 * @param y - Y coordinate on the board, showing the column
	 * @throws InvalidDataException - if the position we want to place the knight is not ok would be thrown custom exception
	 */
	public void setKnight(int x, int y) throws InvalidDataException{
		if (new Position(x, y).isInvalidPosition()) {
			throw new InvalidDataException("Invalid position.");
		} else {
			knight = new Knight(positions[x][y]);
		}
	}
	public Knight getKnight() {
		return knight;
	}
}
