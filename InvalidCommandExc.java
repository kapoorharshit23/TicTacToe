/*
 * Author Name:Harshit Kapoor
 * Student Number:666810
 * 
 * Tic Tac Toe Game Solution
 * InvalidCommandExc.java class
 * */

public class InvalidCommandExc extends Exception{
	
	/*
	 * Two constructors:
	 * one no argument constructor
	 * one with message, which can be used for getMessage()
	 */
	
	public InvalidCommandExc()
	{
		super("InvalidCommandException");
	}
	
	public InvalidCommandExc(String message)
	{
		super("'"+message+"' is not a valid command.");
	}
}
