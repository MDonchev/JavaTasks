package structure;

/**
 * 
 * @author mario
 * Simple class for representing position on the board through its coordinates.
 * We have variable x used for displaying on which row on the board we are.
 * And variable y with same usige for colomn.
 */
public class Position {
	
	// variables for representing single place on the board
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// check if the position is on the board
	public boolean isInvalidPosition() {
		return !((x>=0 && x<=Board.BOARD_HEIGHT-1) && (y>=0 && y<=Board.BOARD_WIDTH-1));
	}
	
	//getters
	public int getXCoordinate() {
		return x;
	}
	public int getYCoordinate() {
		return y;
	}
	
	
	/*
	 * overriden methods for using class properly
	 * equals using for comparing to objects properly by their position
	 */
	@Override
	public String toString() {
		return this.x + "" + this.y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
