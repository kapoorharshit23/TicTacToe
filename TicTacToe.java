/*
 * Author Name:Harshit Kapoor
 * Student Number:666810
 * 
 * Tic Tac Toe Game Solution
 * TicTacToe.java
 * */

import java.util.Scanner;

public class TicTacToe {

	public static Scanner keyboard=new Scanner(System.in);
	private static final String PLAY_GAME="playgame";
	private static final String RANKINGS="rankings";
	private static final String EXIT="Exit";
	private static final String EXIT_H="exit";
	private static final String ADD_PLAYER_TIC="addplayer";
	private static final String REMOVE_PLAYER_TIC="removeplayer";
	private static final String EDIT_PLAYER_TIC="editplayer";
	private static final String RESET_STATS_TIC="resetstats";
	private static final String DISPLAY_PLAYER_TIC="displayplayer";
	private static final String ADD_AI_PLAYER_TIC="addaiplayer";
	/*
	 * Function to display prompt whenever user
	 * enters any command.
	 */
	
	public static String disp()
	{
		String userInput;
		System.out.print("\n");
		System.out.print(">");
		userInput=keyboard.nextLine();
		return userInput;
	}
	
	/*
	 * Function which has object of the other classes
	 * and accordingly call the functions of the other classes. 
	 */
	
	public void run() throws InvalidCommandExc,InvalidArgs
	{
		PlayerManager pManager=new PlayerManager();
		GameManager gManager=new GameManager();
		String uInput;
		String first;
		int blank;
		int result;
		System.out.println("Welcome to Tic Tac Toe!");
		while(!(uInput=disp()).equals(EXIT))
		{
			blank=uInput.indexOf(" ");
			//checking if the input is single command or command with arguments
			if(blank==(-1))
			{
				first=uInput;
			}else
			{
				first=uInput.substring(0, blank);
			}
			try
			{
				if((first.equals(PLAY_GAME))||(first.equals(RANKINGS))||(first.equals(ADD_PLAYER_TIC))||(first.equals(REMOVE_PLAYER_TIC))
						||(first.equals(EDIT_PLAYER_TIC))||(first.equals(RESET_STATS_TIC))||(first.equals(DISPLAY_PLAYER_TIC))
						||(first.equals(EXIT_H))||(first.equals(ADD_AI_PLAYER_TIC)))
				{
					switch(first)
					{
					case PLAY_GAME:
						String pName1,pName2,gName1,gName2;
						int commaPos;
						commaPos=uInput.indexOf(",");
						if(commaPos==-1)
						{
							throw new InvalidArgs();
						}
						else
						{
							pName1=uInput.substring(9, commaPos);	//dividing the user input on the basis of the comma position
							pName2=uInput.substring(commaPos+1);
							if(pManager.search(pName1, pName2)==0)
							{
								System.out.println("Player does not exist.");
							}else
							{
								gName1=pManager.returnGivenName(pName1);
								gName2=pManager.returnGivenName(pName2);
								result=gManager.playGame(gName1,gName2);
								pManager.update(pName1, pName2, result);
							}
						}
						break;
					case RANKINGS:
						pManager.displayRanking();
						break;
					default:
						pManager.processing(uInput);
						break;
					}
				}
				else
				{
					throw new InvalidCommandExc(first);		//throwing Invalid Command exception
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		System.out.println("");
		System.exit(0);
	}
	//Main Method
	public static void main(String[] args) throws InvalidCommandExc,InvalidArgs{
		// TODO Auto-generated method stub
		TicTacToe gameSystem=new TicTacToe();
		//calling the run() method
		gameSystem.run();
	}
}
