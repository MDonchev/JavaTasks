package structure;

public class Knight {

	private Position position;
	
	public Knight(Position knightPosition) {
		this.position = knightPosition;
	}
	
	
	public synchronized void move(Position pos) {
		if(!pos.isInvalidPosition()) {
			this.position = pos;
		}
	}
	
	public synchronized Position getPosition() {
		return this.position;
	}
}
