/*
 * Author Name:Harshit Kapoor
 * Student Number:666810
 * 
 * Tic Tac Toe Game Solution
 * InvalidArgs.java class
 * */

public class InvalidArgs extends Exception {
	
	/*
	 * Two constructors:
	 * one no argument constructor
	 * one with message, which can be used for getMessage()
	 */
	public InvalidArgs()
	{
		super("Incorrect number of arguments supplied to command.");
	}
	
	public InvalidArgs(String message)
	{
		super("Incorrect number of arguments supplied to command.");
	}
}
