package AppDemo;

import customExceptions.InvalidDataException;
import structure.Board;
import structure.Position;
/**
 *
 * @author mario
 * 
 * Here we have some simple demo to the functionalities for the task.
 * We receive parameters outside and check there validation using exceptions:
 * custom exception for the number of parameters received - must be exactly 2 : position of the horse on the board
 * catched potential NumberFormatException if the parameters cannot be converted to integers
 * custom exception for checking if the position parsed if legit.  
 * Output either that knight has reached its destination, or it has not and if there are some wrong data print particular message.
 * 
 * 
 * Running : <project directory> $ mvn exec:java -Dexec.mainClass="AppDemo.Demo" -Dexec.args="arg0 arg1" where arg0 and arg1 are passed arguments
 */
public class Demo {
	public static void main(String[] args) {

		try {
			//validation of the parameters
			if (args.length != 2) throw new InvalidDataException("\n\nWrong number of paramenters parsed.\n\n");
			int x = Integer.parseInt(args[0]);
			int y = Integer.parseInt(args[1]);
			if (new Position(x, y).isInvalidPosition()) throw new InvalidDataException("Wrong data parsed.");
			
			
			// creating the board and starting the game
			Board board = Board.getInstance();
			board.setKnight(x, y);
			System.out.println(board.gameResult());
			
		} catch (InvalidDataException  e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("\n\nPlease enter only integers.\n\n");
		}
	}
}
