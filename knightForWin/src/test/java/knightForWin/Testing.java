package knightForWin;

import static org.junit.Assert.*;

import org.junit.Test;

import customExceptions.InvalidDataException;
import junit.framework.Assert;
import structure.Board;
import structure.Position;

public class Testing {

	@Test
	public void TestingRightCases() throws InvalidDataException {
		Board board = Board.getInstance();
		board.setKnight(0, 1);
		String result = board.gameResult();
		Assert.assertEquals("\n\nKnight has reached the final destination.\n\n", result);
	}

	@Test
	public void TestingWrongCases() throws InvalidDataException {
		Board board = Board.getInstance();
		board.setKnight(1, 1);
		String result = board.gameResult();
		Assert.assertEquals("\n\nFinal Destination can not be reached.\n\n", result);
	}
	@Test
	public void TestingKnightSetting() throws InvalidDataException {
		Board board = Board.getInstance();
		board.setKnight(2, 2);
		
		Position expectedPos = new Position(2, 2);
		
		Assert.assertEquals(expectedPos, board.getKnight().getPosition());
		
	}
}
