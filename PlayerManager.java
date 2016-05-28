/*
 * Author Name:Harshit Kapoor
 * Student Number:666810
 * 
 * Tic Tac Toe Game Solution
 * PlayerManager.java class
 * */

/*
 * Important Information:
 * I have used substring() because the mechanism,
 * by which i am differentiating between AI and human players
 * is that I add a prefix AI to the given names of AI players
 * and hence when I print them I need to eliminate the first two characters.
 */

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayerManager {

	//static public Scanner scan=new Scanner(System.in);
	static private int count=0;						//to keep the count of number of players
	/*
	 * Made two different arrays just for object creation
	 * and initialization. Please don't confuse between the other array mentioned below
	 * which is being used for storing all the players. 
	 */
	static HumanPlayer[] tp=new HumanPlayer[100];	
	static AIPlayer[] tpAI=new AIPlayer[100];		
	private static final String ADD_PLAYER="addplayer";
	private static final String REMOVE_PLAYER="removeplayer";
	private static final String EDIT_PLAYER="editplayer";
	private static final String RESET_STATS="resetstats";
	private static final String DISPLAY_PLAYER="displayplayer";
	private static final String EXIT_PLAYERMANAGER="exit";
	private static final String ADD_AI_PLAYER="addaiplayer";
	/*
	 * Single array being used to store both AI and human players
	 * as per the specification.
	 */
	private static String[] userNames=new String[100]; 
	private static String[] familyNames=new String[100]; 
	private static String[] givenNames=new String[100];  
	private static Integer[] gamesPlayed=new Integer[100];
	private static Integer[] gamesWon=new Integer[100];
	private static Integer[] gamesDraw=new Integer[100];

	public static PrintWriter newPrint;
	
	/*
	 * Constructor to initialise the various arrays used
	 */
	
	public PlayerManager()
	{
		//initializing the array
		for(int j=0;j<tp.length;j++)
		{
			tp[j]=new HumanPlayer();
			tpAI[j]=new AIPlayer();
			userNames[j]=null;
			familyNames[j]=null;
			givenNames[j]=null;
			gamesPlayed[j]=0;
			gamesWon[j]=0;
			gamesDraw[j]=0;
		}
		fetchData();
	}
	/*
	 * Method to fetch data
	 * from the file on the disk
	 * "players.dat"
	 */
	public void fetchData()
	{
		int blankSpace=0;
		String userN=null;
		String familyN=null;
		String givenN=null;
		int gPlayed=0;
		int gWon=0;
		int gDraw=0;
		int g=0;
		try{
			Scanner fetchData=new Scanner(new FileInputStream("players.dat"));
			String playerRecord=fetchData.nextLine();
			//System.out.println("record"+playerRecord);
			while(!(playerRecord.equals(null)))
			{
				blankSpace=playerRecord.indexOf(" ");
				userN=playerRecord.substring(0, blankSpace);
				//System.out.println(userN);
				userNames[g]=userN;
				playerRecord=playerRecord.substring(blankSpace+1);
				blankSpace=playerRecord.indexOf(" ");
				familyN=playerRecord.substring(0, blankSpace);
				familyNames[g]=familyN;
				playerRecord=playerRecord.substring(blankSpace+1);
				blankSpace=playerRecord.indexOf(" ");
				givenN=playerRecord.substring(0, blankSpace);
				givenNames[g]=givenN;
				playerRecord=playerRecord.substring(blankSpace+1);
				blankSpace=playerRecord.indexOf(" ");
				gPlayed=Integer.parseInt(playerRecord.substring(0, blankSpace));
				gamesPlayed[g]=gPlayed;
				playerRecord=playerRecord.substring(blankSpace+1);
				blankSpace=playerRecord.indexOf(" ");
				gWon=Integer.parseInt(playerRecord.substring(0, blankSpace));
				gamesWon[g]=gWon;
				gDraw=Integer.parseInt(playerRecord.substring(blankSpace+1));
				gamesDraw[g]=gDraw;
				playerRecord=fetchData.nextLine();
				g=g+1;
				count=g;
			}
			fetchData.close();
		}catch(Exception e)
		{
			/*
			 * This means no file exists
			 * therefore, count should be equal to 0.
			 */
			count=0;		
		}
		try
		{
			/*
			 * If it throws a null pointer exception
			 * it means that file does not have any content
			 * and therefore no need to increament count counter.
			 */
			if(userNames[g].equals("null"))
			{
				
			}
			count=g+1;
			//System.out.println("1");
		}catch(Exception e)
		{
			count=g;
			//System.out.println("2");
		}
		
		//System.out.println("g"+g+"count"+count);
		//RemoveFile("players.dat");
	}
	/*
	 * Method to return the given name of 
	 * a username.
	 */
	public String returnGivenName(String name)
	{
		int searchFlag1=0,pos=0;
		for (int i=0;i<count;i++)
		{
			if(name.equals(userNames[i]))
			{
				searchFlag1=1;
				pos=i;
				break;
			}
		}
		return givenNames[pos];
	}
	/*
	 * Method to process the user input commands
	 * passed on by TicTacToe class as arguments
	 */
	public void processing(String mainInput) throws InvalidArgs
	{

		String first;
		int blank;
		
		if(!mainInput.equals(EXIT_PLAYERMANAGER))
		{
			blank=mainInput.indexOf(" ");		//checking if the user input is single command or command with arguments
			if(blank==(-1))
			{
				first=mainInput;
			}else
			{
				first=mainInput.substring(0, blank);
			}
		try
		{
			switch(first)
			{
			case ADD_PLAYER:
				String userName,familyName,remain,givenName;
				int flag=0;						//flag variable to check if the user already exists
				int comma,comma2;				//variables to store comma positions by dividing the user input on comma basis
				comma=mainInput.indexOf(",");
				if(comma==-1)					//If a user gives only one argument
				{
					throw new InvalidArgs();
				}
				else
				{
					userName=mainInput.substring(10, comma);	//since the length of addplayer is 9 therefore from 10th position
					remain=mainInput.substring(comma+1);
					comma2=remain.indexOf(",");
					if(comma2==-1)				//If a user gives only two arguments
					{
						throw new InvalidArgs();
					}
					else
					{
						familyName=remain.substring(0, comma2);
						givenName=remain.substring(comma2+1);
						for(int i=0;i<count;i++)
						{
							if(userName.equals(userNames[i]))
							{
								System.out.println("The username has been used already.");
								flag=1;
							}
						}
						if(flag==0)
						{
							tp[count]=new HumanPlayer();
							tp[count].setUserName(userName);
							tp[count].setFamilyName(familyName);
							tp[count].setGivenName(givenName);
							tp[count].setGamesPlayed(0);
							tp[count].setGamesDrawn(0);
							tp[count].setGamesWon(0);
							userNames[count]=userName;
							givenNames[count]=givenName;
							familyNames[count]=familyName;
							gamesPlayed[count]=0;
							gamesWon[count]=0;
							gamesDraw[count]=0;
							count=count+1;
							//System.out.println("Player added.Count:"+count);
						}
					}
				}
				//System.out.println("_____");
				/*for(int y=0;y<count+1;y++)
				{
					System.out.println(userNames[y]+" "+givenNames[y]+" "+familyNames[y]+" "+gamesPlayed[y]+
							" "+gamesWon[y]+" "+gamesDraw[y]);
					newPrint.println(userNames[y]+" "+givenNames[y]+" "+familyNames[y]+" "+gamesPlayed[y]+
							" "+gamesWon[y]+" "+gamesDraw[y]);
				}*/
				break;
			case ADD_AI_PLAYER:
				String userNameAI,familyNameAI,remainAI,givenNameAI;
				int flagAI=0;						//flag variable to check if the user already exists
				int commaAI,comma2AI;				//variables to store comma positions by dividing the user input on comma basis
				commaAI=mainInput.indexOf(",");
				if(commaAI==-1)					//If a user gives only one argument
				{
					throw new InvalidArgs();
				}
				else
				{
					userNameAI=mainInput.substring(12, commaAI);	//since the length of addplayer is 9 therefore from 10th position
					remainAI=mainInput.substring(commaAI+1);
					comma2AI=remainAI.indexOf(",");
					if(comma2AI==-1)				//If a user gives only two arguments
					{
						throw new InvalidArgs();
					}
					else
					{
						familyNameAI=remainAI.substring(0, comma2AI);
						givenNameAI=remainAI.substring(comma2AI+1);
						for(int i=0;i<count;i++)
						{
							if(userNameAI.equals(userNames[i]))
							{
								System.out.println("The username has been used already.");
								flag=1;
							}
						}
						if(flagAI==0)
						{
							tpAI[count]=new AIPlayer();
							tpAI[count].setUserName(userNameAI);
							tpAI[count].setFamilyName(familyNameAI);
							tpAI[count].setGivenName("AI"+givenNameAI);		//To differentiate between AI users and Human
							tpAI[count].setGamesPlayed(0);
							tpAI[count].setGamesDrawn(0);
							tpAI[count].setGamesWon(0);
							userNames[count]=userNameAI;
							givenNames[count]="AI"+givenNameAI;				//To differentiate between AI users and Human
							familyNames[count]=familyNameAI;
							gamesPlayed[count]=0;
							gamesWon[count]=0;
							gamesDraw[count]=0;
							count=count+1;
							//System.out.println("Player added.Count:"+count);
						}
					}
				}
				//System.out.println("_____");
				/*for(int y=0;y<count+1;y++)
				{
					System.out.println(userNames[y]+" "+givenNames[y]+" "+familyNames[y]+" "+gamesPlayed[y]+
							" "+gamesWon[y]+" "+gamesDraw[y]);
					newPrint.println(userNames[y]+" "+givenNames[y]+" "+familyNames[y]+" "+gamesPlayed[y]+
							" "+gamesWon[y]+" "+gamesDraw[y]);
				}*/
				break;
			case REMOVE_PLAYER:
				//System.out.println("remove.");
				flag=0;
				String removeName;
				String fInput="n";
				/*
				 * length of removeplayer =12
				 * therefore, confirming if the user wants to remove all players
				 */
				if(mainInput.length()==12){
					System.out.println("Are you sure you want to remove all players? (y/n)");
					fInput=TicTacToe.keyboard.nextLine();
					if(fInput.equals("y")||fInput.equals("Y"))
					{
						for(int i=0;i<count;i++)
						{
							userNames[i]=null;
							givenNames[i]=null;
							familyNames[i]=null;
							gamesPlayed[i]=0;
							gamesWon[i]=0;
							gamesDraw[i]=0;
							count=0;
						}
						break;
					}
					else
					{
						
					}			
				}else
				{
					removeName=mainInput.substring(13);
					//System.out.println(removeName);
					for(int i=0;i<count;i++)
					{
						if(removeName.equals(userNames[i]))
						{						
							flag=1;
							//System.out.println("The player exists at :"+i);
							/*
							 * Therefore, to remove a user,
							 * just setting its userName value in Player class
							 * to "".
							 */
							/*
							 * Checking if the player is an AI or a human player
							 */
							if(givenNames[i].substring(0, 2).equals("AI"))		
							{
								tpAI[i].setUserName("");		
								//pushing behind the remaining elements in array by one space  
								for(int j=i;j<(count-1);j++)
								{
									tpAI[j]=tpAI[j+1];
									userNames[j]=userNames[j+1];
									givenNames[j]=givenNames[j+1];
									familyNames[j]=familyNames[j+1];
									gamesPlayed[j]=gamesPlayed[j+1];
									gamesWon[j]=gamesWon[j+1];
									gamesDraw[j]=gamesDraw[j+1];
								}
								count=count-1;
								/*for(int y=0;y<count;y++)
								{
									System.out.println(userNames[y]+" "+givenNames[y]+" "+familyNames[y]+" "+gamesPlayed[y]+
											" "+gamesWon[y]+" "+gamesDraw[y]);
									newPrint.println(userNames[y]+" "+givenNames[y]+" "+familyNames[y]+" "+gamesPlayed[y]+
											" "+gamesWon[y]+" "+gamesDraw[y]);
								}*/
								break;
								
							}else
							{
								tp[i].setUserName("");		
								//pushing behind the remaining elements in array by one space  
								for(int j=i;j<(count-1);j++)
								{
									tp[j]=tp[j+1];
									userNames[j]=userNames[j+1];
									givenNames[j]=givenNames[j+1];
									familyNames[j]=familyNames[j+1];
									gamesPlayed[j]=gamesPlayed[j+1];
									gamesWon[j]=gamesWon[j+1];
									gamesDraw[j]=gamesDraw[j+1];
								}
								count=count-1;
								/*for(int y=0;y<count;y++)
								{
									System.out.println(userNames[y]+" "+givenNames[y]+" "+familyNames[y]+" "+gamesPlayed[y]+
											" "+gamesWon[y]+" "+gamesDraw[y]);
									newPrint.println(userNames[y]+" "+givenNames[y]+" "+familyNames[y]+" "+gamesPlayed[y]+
											" "+gamesWon[y]+" "+gamesDraw[y]);
								}*/
								break;
							}
						}
					}
					if(flag==0)
					{
						System.out.println("The player does not exist.");
					}
				}
				break;
			case EDIT_PLAYER:
				/*
				 *Same concept used that of addplayer case 
				 */
				//System.out.println("edit.");
				String uName,fName,gName,remaining;
				int c,c2;
				flag=0;
				c=mainInput.indexOf(",");
				if(c==-1)
				{
					throw new InvalidArgs();
				}else
				{
					uName=mainInput.substring(11, c);
					remaining=mainInput.substring(c+1);
					c2=remaining.indexOf(",");
					if(c2==-1)
					{
						throw new InvalidArgs();
					}else
					{
						fName=remaining.substring(0, c2);
						
						gName=remaining.substring(c2+1);
						
						for(int i=0;i<count;i++)
						{
							if(uName.equals(userNames[i]))
							{
								if(givenNames[i].substring(0, 2).equals("AI"))
								{
									tpAI[i].setGivenName("AI"+gName);
									givenNames[i]="AI"+gName;
									tpAI[i].setFamilyName(fName);
									familyNames[i]=fName;
								}else
								{
									tp[i].setGivenName(gName);
									givenNames[i]=gName;
									tp[i].setFamilyName(fName);
									familyNames[i]=fName;
								}
								flag=1;
							}
						}
						if(flag==0)
						{
							System.out.println("The player does not exist.");
						}
					}
				}
				break;
			case RESET_STATS:
				/*
				 * Same concept used that of removeplayer case
				 */
				//System.out.println("reset.");
				flag=0;
				String resetName;
				String cInput="n";
				//length of remove =6
				if(mainInput.length()==10){
					System.out.println("Are you sure you want to reset all player statistics? (y/n)");
					cInput=TicTacToe.keyboard.nextLine();
					if(cInput.equals("y")||cInput.equals("Y"))
					{
						for(int i=0;i<count;i++)
						{
							tp[i].setGamesWon(0);
							tp[i].setGamesPlayed(0);
							tp[i].setGamesDrawn(0);
							tpAI[i].setGamesWon(0);
							tpAI[i].setGamesPlayed(0);
							tpAI[i].setGamesDrawn(0);
							gamesPlayed[i]=0;
							gamesWon[i]=0;
							gamesDraw[i]=0;
						}
					}
					else
					{
						
					}
				}else
				{
					resetName=mainInput.substring(11);
					//System.out.println(resetName);
					for(int i=0;i<count;i++)
					{
						if(resetName.equals(userNames[i]))
						{						
							flag=1;
							//System.out.println("The player exists at :"+i);
							if(givenNames[i].substring(0, 2).equals("AI"))
							{
								tpAI[i].setGamesWon(0);
								tpAI[i].setGamesPlayed(0);
								tpAI[i].setGamesDrawn(0);
							}else
							{
								tp[i].setGamesWon(0);
								tp[i].setGamesPlayed(0);
								tp[i].setGamesDrawn(0);
							}
							gamesPlayed[i]=0;
							gamesWon[i]=0;
							gamesDraw[i]=0;
							/*for(int m=0;m<count;m++)
							{
								System.out.println(tp[m].getUsername()+" "+tp[m].getFamilyName()+
										" "+tp[m].getGivenName()+" "+tp[m].getGamesPlayed()+
										" "+tp[m].getGamesWon()+" "+tp[m].getGamesDrawn());
							}*/
							break;
						}
					}
					if(flag==0)
					{
						System.out.println("The player does not exist.");
					}
				}
				break;
			case DISPLAY_PLAYER:
				//System.out.println("display.");
				String pName;  //player name
				flag=0;
				/*
				 * checking if it is a single command
				 * or command with player name as argument
				 */
				if(mainInput.length()==13){
					for(int i=(count-1);i>=0;i--)
					{
/*						System.out.println(tp[i].getUserName()+","+tp[i].getFamilyName()+
								","+tp[i].getGivenName()+","+tp[i].getGamesPlayed()+" games,"+
								tp[i].getGamesWon()+" wins,"+tp[i].getGamesDrawn()+" draws");*/
						if((givenNames[i].length()>2) && (givenNames[i].substring(0, 2).equals("AI")))
						{
							System.out.println(userNames[i]+","+familyNames[i]+
									","+givenNames[i].substring(2)+","+gamesPlayed[i]+" games,"+
									gamesWon[i]+" wins,"+gamesDraw[i]+" draws");
						}else
						{
							System.out.println(userNames[i]+","+familyNames[i]+
									","+givenNames[i]+","+gamesPlayed[i]+" games,"+
									gamesWon[i]+" wins,"+gamesDraw[i]+" draws");
						}
					}
				}
				else
				{
					pName=mainInput.substring(14);
					for(int i=0;i<count;i++)
					{
						if(pName.equals(userNames[i]))
						{						
							flag=1;
							//System.out.println("The player exists at :"+i);
/*							System.out.println(tp[i].getUserName()+","+tp[i].getFamilyName()+
									","+tp[i].getGivenName()+","+tp[i].getGamesPlayed()+" games,"+
									tp[i].getGamesWon()+" wins,"+tp[i].getGamesDrawn()+" draws");*/
							if(givenNames[i].substring(0, 2).equals("AI"))
							{
								System.out.println(userNames[i]+","+familyNames[i]+
										","+givenNames[i].substring(2)+","+gamesPlayed[i]+" games,"+
										gamesWon[i]+" wins,"+gamesDraw[i]+" draws");
							}else
							{
								System.out.println(userNames[i]+","+familyNames[i]+
										","+givenNames[i]+","+gamesPlayed[i]+" games,"+
										gamesWon[i]+" wins,"+gamesDraw[i]+" draws");
							}
							break;
						}
					}
					if(flag==0)
					{
						System.out.println("The player does not exist.");
					}
				}
				break;
				}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		}else
		{
			//System.out.println("Icam here");
			try {
				newPrint=new PrintWriter(new FileOutputStream("players.dat",false));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int q=0;q<count;q++)
			{
				newPrint.println(userNames[q]+" "+familyNames[q]+
						" "+givenNames[q]+" "+gamesPlayed[q]+" "+
						gamesWon[q]+" "+gamesDraw[q]);
			}
			newPrint.close();
			System.out.println("");
			System.exit(0);
		}
	}
	public void displayRanking()
	{
		//TODO ranking of the players--Independent file
		double[] win=new double[100];		//Array to calculate win %
		double[] draw=new double[100];		//Array to calculate draw %
		double[] winCopy=new double[100];	//Copy of win array, to be used in sorting when there is a tie
		String[] sorted=new String[count];	//Sorted list of player's usernames
		
		//checking if the number of player are more than 10
		if(count>10)
		{
			for( int i=0;i<count;i++)
			{
				if(gamesPlayed[i]==0)
				{
					win[i]=0;
					draw[i]=0;
					//System.out.println(tp[i].getUsername()+" "+win[i]+" "+draw[i]);
				}else
				{
					win[i]=(double)(gamesWon[i])/(gamesPlayed[i])*100;
					draw[i]=(double)(gamesDraw[i])/(gamesPlayed[i])*100;
					//System.out.println(tp[i].getUsername()+" "+win[i]+" "+draw[i]);
				}
			}
			
			double highest=0.0;
			int pos=0;
			for(int j=0;j<10;j++)
			{
				//sorting using win %
				highest=0.0;
				for (int i=0;i<count;i++)
				{
					if(win[i]>=highest)
					{
						highest=win[i];
						pos=i;
					}
				}
				//Storing the values in respective arrays
				sorted[j]=userNames[pos];
				winCopy[j]=win[pos];
				if(gamesPlayed[pos]==0){
					draw[j]=0;
				}else
				{
					draw[j]=(double)(gamesDraw[pos])/(gamesPlayed[pos])*100;
				}
				/*
				 * last step of the loop is to assign -1 to 
				 * player with highest win percentage
				 * so that we could move to second highest value of win%
				 */
				win[pos]=-1;	
			}
			/*System.out.println("____________");
			for(int i=0;i<10;i++){
				System.out.println(sorted[i]+" "+winCopy[i]+" "+draw[i]);
			}
			System.out.println("____________");*/
			
			//Sorting by Draw games
			for(int i=0;i<10-1;i++)
			{
				//using the winCopy array, during a tie
				if(winCopy[i]==winCopy[i+1])
				{
					if(draw[i]<draw[i+1])	//Checking the draw percentage of tied players
					{
						//swapping their corresponding win,draw and position values
						String temp=sorted[i];
						sorted[i]=sorted[i+1];
						sorted[i+1]=temp;
						double temp1=winCopy[i];
						winCopy[i]=winCopy[i+1];
						winCopy[i+1]=temp1;
						draw[i]=draw[i+1];
						draw[i+1]=draw[i];
					}else
					{
						if(draw[i]==draw[i+1])		//if draw % is also tied, then checking alphabetical order using compareTo()
						{
							int comp= sorted[i].compareTo(sorted[i+1]);
							//System.out.println(sorted[i]+" "+sorted[i+1]);
							//System.out.println(comp);
							if(comp>0)
							{
								String temp=sorted[i];
								sorted[i]=sorted[i+1];
								sorted[i+1]=temp;
							}
						}
					}
				}
			}
			System.out.print(" WIN  | DRAW | GAME | USERNAME");
			for(int i=0;i<10;i++){
				String outName=sorted[i];
				for(int j=0;j<count;j++)
				{
					if(outName.equals(userNames[j]))
					{
						if(gamesPlayed[j]==0)
						{
							int winP=0;
							int drawP=0;
							int gameP= gamesPlayed[j];
							System.out.printf("\n %3d%% | %3d%% | %2d   | %s", winP,drawP,gameP,outName);
						}else
						{
							double winP=((double)(gamesWon[j])/(gamesPlayed[j]))*100;
							double drawP=((double)(gamesDraw[j])/(gamesPlayed[j]))*100;
							int gameP= gamesPlayed[j];
							System.out.printf("\n %3d%% | %3d%% | %2d   | %s", (int)winP,(int)drawP,gameP,outName);
						}
					}
				}
			}
			System.out.println();
		}
		/*
		 * Same concept but for player count
		 * less than 10.
		 */
		else
		{
		for( int i=0;i<count;i++)
		{
			if(gamesPlayed[i]==0)
			{
				win[i]=0;
				draw[i]=0;
				//System.out.println(tp[i].getUsername()+" "+win[i]+" "+draw[i]);
			}else
			{
				win[i]=(double)(gamesWon[i])/(gamesPlayed[i])*100;
				draw[i]=(double)(gamesDraw[i])/(gamesPlayed[i])*100;
				//System.out.println(tp[i].getUsername()+" "+win[i]+" "+draw[i]);
			}
		}
		
		double highest=0.0;
		int pos=0;
		for(int j=0;j<count;j++)
		{
			//Win sorting
			highest=0.0;
			for (int i=0;i<count;i++)
			{
				if(win[i]>=highest)
				{
					highest=win[i];
					pos=i;
				}
			}
			//System.out.println(tp[pos].getName()+" "+win[pos]);
			sorted[j]=userNames[pos];
			winCopy[j]=win[pos];
			if(gamesPlayed[pos]==0){
				draw[j]=0;
			}else
			{
				draw[j]=(double)(gamesDraw[pos])/(gamesPlayed[pos])*100;
			}
			win[pos]=-1;
		}
		/*System.out.println("____________");
		for(int i=0;i<count;i++){
			System.out.println(sorted[i]+" "+winCopy[i]+" "+draw[i]);
		}
		System.out.println("____________");*/
		//Sorting by Draw games
		for(int i=0;i<count-1;i++)
		{
			if(winCopy[i]==winCopy[i+1])
			{
				//System.out.println(winCopy[i]);
				//System.out.println(draw[i]+" "+draw[i+1]);
				if(draw[i]<draw[i+1])
				{
					String temp=sorted[i];
					sorted[i]=sorted[i+1];
					sorted[i+1]=temp;
					double temp1=winCopy[i];
					winCopy[i]=winCopy[i+1];
					winCopy[i+1]=temp1;
					draw[i]=draw[i+1];
					draw[i+1]=draw[i];
				}else
				{
					if(draw[i]==draw[i+1])
					{
						int comp= sorted[i].compareTo(sorted[i+1]);
						//System.out.println(sorted[i]+" "+sorted[i+1]);
						//System.out.println(comp);
						if(comp>0)
						{
							String temp=sorted[i];
							sorted[i]=sorted[i+1];
							sorted[i+1]=temp;
						}
					}
				}
			}
		}
		System.out.print(" WIN  | DRAW | GAME | USERNAME");
		for(int i=0;i<count;i++){
			String outName=sorted[i];
			for(int j=0;j<count;j++)
			{
				if(outName.equals(userNames[j]))
				{
					if(gamesPlayed[j]==0)
					{
						int winP=0;
						int drawP=0;
						int gameP= gamesPlayed[j];
						System.out.printf("\n %3d%% | %3d%% | %2d   | %s", winP,drawP,gameP,outName);
					}else
					{
						double winP=((double)(gamesWon[j])/(gamesPlayed[j]))*100;
						double drawP=((double)(gamesDraw[j])/(gamesPlayed[j]))*100;
						int gameP= gamesPlayed[j];
						System.out.printf("\n %3d%% | %3d%% | %2d   | %s", (int)winP,(int)drawP,gameP,outName);
					}
				}
			}			
		}
		System.out.println();
		}
	}
	
	/*
	 * To search for a player, if he/she already exists 
	 */
	public int search(String player1,String player2)
	{
		int searchFlag1=0,searchFlag2=0;
		for (int i=0;i<count;i++)
		{
			if(player1.equals(userNames[i]))
			{
				searchFlag1=1;
			}
		}
		for (int i=0;i<count;i++)
		{
			if(player2.equals(userNames[i]))
			{
				searchFlag2=1;
			}
		}
		if((searchFlag1+searchFlag2)==2)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	/*
	 * Method to update the data of the respective player,after any game
	 */
	public void update(String p1,String p2,int res)
	{	
		//updating number of games played by player1(p1)
		for(int u=0;u<count;u++)
		{
			if(p1.equals(userNames[u]))
			{
				if(givenNames[u].substring(0, 2).equals("AI"))
				{
					tpAI[u].setGamesPlayed((tpAI[u].getGamesPlayed()+1));
				}else
				{
					tp[u].setGamesPlayed((tp[u].getGamesPlayed()+1));
				}
				gamesPlayed[u]=gamesPlayed[u]+1;
			}
		}
		//updating number of games played by player2(p2)
		for(int u=0;u<count;u++)
		{
			if(p2.equals(userNames[u]))
			{
				if(givenNames[u].substring(0, 2).equals("AI"))
				{
					tpAI[u].setGamesPlayed((tpAI[u].getGamesPlayed()+1));
				}else
				{
					tp[u].setGamesPlayed((tp[u].getGamesPlayed()+1));
				}
				gamesPlayed[u]=gamesPlayed[u]+1;
			}
		}
		if(res==1)
		{
			//P1 win
			for(int u=0;u<count;u++)
			{
				if(p1.equals(userNames[u]))
				{
					if(givenNames[u].substring(0, 2).equals("AI"))
					{
						tpAI[u].setGamesWon((tpAI[u].getGamesWon()+1));
					}else
					{
						tp[u].setGamesWon((tp[u].getGamesWon()+1));
					}
					gamesWon[u]=gamesWon[u]+1;
				}
			}
		}
		if(res==2)
		{
			//P2 win
			for(int u=0;u<count;u++)
			{
				if(p2.equals(userNames[u]))
				{
					if(givenNames[u].substring(0, 2).equals("AI"))
					{
						tpAI[u].setGamesWon((tpAI[u].getGamesWon()+1));
					}else
					{
						tp[u].setGamesWon((tp[u].getGamesWon()+1));
					}
					gamesWon[u]=gamesWon[u]+1;
				}
			}
		}
		if(res==3)
		{
			//P1 and P2 drew
			for(int u=0;u<count;u++)
			{
				if(p1.equals(userNames[u]))
				{
					if(givenNames[u].substring(0, 2).equals("AI"))
					{
						tpAI[u].setGamesDrawn((tpAI[u].getGamesDrawn()+1));
					}else
					{
						tp[u].setGamesDrawn((tp[u].getGamesDrawn()+1));
					}
					gamesDraw[u]=gamesDraw[u]+1;
				}
			}
			for(int u=0;u<count;u++)
			{
				if(p2.equals(userNames[u]))
				{
					if(givenNames[u].substring(0, 2).equals("AI"))
					{
						tpAI[u].setGamesDrawn((tpAI[u].getGamesDrawn()+1));
					}else
					{
						tp[u].setGamesDrawn((tp[u].getGamesDrawn()+1));
					}
					gamesDraw[u]=gamesDraw[u]+1;
				}
			}
		}
	}
}