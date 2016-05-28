/*
 * Author Name:Harshit Kapoor
 * Student Number:666810
 * 
 * Tic Tac Toe Game Solution
 * GameManager.java class
 * */

public class GameManager {
	
	private static String[] gridPos=new String[9];				//Specifying the 9 different positions in game grid
	//public static Scanner scan=new Scanner(System.in);
	private static String[] gridPosOccupied=new String[9];		//Array to check and store the occupied positions in the grid
	static char[][] gameBoard=new char[3][3];					//gameBoard for AI player to determine empty positions
	
	/*
	 * Just for the sake of convenience I've
	 * divided the player part into 4 parts
	 * such that two belong to AI player and
	 * remaining two belong to human player. 
	 */
	
	public void AIMoveP1(String AIPlayer,int turnVal)
	{
		int sumAI=0;
		GameManager gameP1=new GameManager();
		AIPlayer aiP1=new AIPlayer();
		//System.out.println("AI PLAyer Move");
		System.out.println(AIPlayer.substring(2)+"'s move:");
		Move alpha=aiP1.makeMove(gameBoard);
		//oInput=TicTacToe.keyboard.nextLine();
		int m=alpha.getRow();
		int n=alpha.getCol();
		
		 /* Checking the move using checkMove()
		 -if the grid space is already occupied
		 -if the player input is not between {0,1,2}*/
		 
		int result=0;
		while((result=checkMove(AIPlayer,m,n,turnVal))!=0)
		{
			System.out.println(AIPlayer.substring(2)+"'s move:");
			alpha=aiP1.makeMove(gameBoard);
			m=alpha.getRow();
			n=alpha.getCol();
		}
		
		if(result==0)
		{
			if(m==0)										//First row in game grid for player A
			{
				sumAI=m+n+1;
				gameP1.printGrid(sumAI,"O");
			}
			if(m==1)										//Second row in game grid for player A
			{
				sumAI=m+n+3;
				gameP1.printGrid(sumAI,"O");
			}
			if(m==2)										//Third row in game grid for player A
			{
				sumAI=m+n+5;
				gameP1.printGrid(sumAI,"O");
			}
		}
	}
	public void AIMoveP2(String AIPlayer,int turnVal)
	{
		int sumAI=0;
		GameManager gameP2=new GameManager();
		AIPlayer aiP2=new AIPlayer();
		//System.out.println("AI PLAyer Move 2");
		System.out.println(AIPlayer.substring(2)+"'s move:");
		//xInput=TicTacToe.keyboard.nextLine();
		Move beta=aiP2.makeMove(gameBoard);
		int p=beta.getRow();
		int q=beta.getCol();
		int result=0;
		while((result=checkMove(AIPlayer,p,q,turnVal))!=0)
		{
			System.out.println(AIPlayer.substring(2)+"'s move:");
			beta=aiP2.makeMove(gameBoard);
			p=beta.getRow();
			q=beta.getCol();
		}
		
		if(result==0)
		{
			if(p==0)										//First row in game grid for player B
			{
				sumAI=p+q+1;
				gameP2.printGrid(sumAI,"X");
			}
			if(p==1)										//Second row in game grid for player B
			{
				sumAI=p+q+3;
				gameP2.printGrid(sumAI,"X");
			}
			if(p==2)										//Third row in game grid for player B
			{
				sumAI=p+q+5;
				gameP2.printGrid(sumAI,"X");
			}
		}
	}
	public void hMoveP1(String hPlayer1,int turnVal)
	{
		GameManager gameHP1=new GameManager();
		HumanPlayer hP1=new HumanPlayer();
		int sumVal=0;
		System.out.println(hPlayer1+"'s move:");
		//xInput=TicTacToe.keyboard.nextLine();
		Move humanP=hP1.makeMove(gameBoard);
		int p=humanP.getRow();
		int q=humanP.getCol();
		int result=0;
		while((result=checkMove(hPlayer1,p,q,turnVal))!=0)
		{
			System.out.println(hPlayer1+"'s move:");
			humanP=hP1.makeMove(gameBoard);
			p=humanP.getRow();
			q=humanP.getCol();
		}
		
		if(result==0)
		{
			if(p==0)										//First row in game grid for player A
			{
				sumVal=p+q+1;
				gameHP1.printGrid(sumVal,"O");
			}
			if(p==1)										//Second row in game grid for player A
			{
				sumVal=p+q+3;
				gameHP1.printGrid(sumVal,"O");
			}
			if(p==2)										//Third row in game grid for player A
			{
				sumVal=p+q+5;
				gameHP1.printGrid(sumVal,"O");
			}
		}
	}
	public void hMoveP2(String hPlayer2,int turnVal)
	{
		GameManager gameHP2=new GameManager();
		HumanPlayer hP2=new HumanPlayer();
		int sumVal=0;
		System.out.println(hPlayer2+"'s move:");
		Move p2Move=hP2.makeMove(gameBoard);
		//xInput=TicTacToe.keyboard.nextLine();
		int p=p2Move.getRow();
		int q=p2Move.getCol();
		int result=0;
		while((result=checkMove(hPlayer2,p,q,turnVal))!=0)
		{
			System.out.println(hPlayer2+"'s move:");
			p2Move=hP2.makeMove(gameBoard);
			p=p2Move.getRow();
			q=p2Move.getCol();
		}
		
		if(result==0)
		{
			if(p==0)										//First row in game grid for player B
			{
				sumVal=p+q+1;
				gameHP2.printGrid(sumVal,"X");
			}
			if(p==1)										//Second row in game grid for player B
			{
				sumVal=p+q+3;
				gameHP2.printGrid(sumVal,"X");
			}
			if(p==2)										//Third row in game grid for player B
			{
				sumVal=p+q+5;
				gameHP2.printGrid(sumVal,"X");
			}
		}
	}

	public int playGame(String player1,String player2)
	{
		//System.out.println(player1+" "+player2+" wants to play a game.");
		
		//intializing the respective arrays
		GameManager game=new GameManager();
		
		for(int i=0;i<9;i++)
		{
			gridPosOccupied[i]=null;
		}
		
		for(int j=0;j<3;j++)
		{
			for(int k=0;k<3;k++)
			{
				gameBoard[j][k]='n';
			}
		}
		
		String playerO=player1;
		String playerX=player2;

		int returnVal=0;
		game.printGrid(0," ");
		
		/*
		 * Both the players being AI
		 */
		if((player1.substring(0,2).equals("AI"))&&(player2.substring(0,2).equals("AI")))
		{
			for(int i=0;i<9;i++)
			{
				if(i%2==0)											//Logic for alternate chance for alternate player 
				{
					AIMoveP1(playerO,i);
					//System.out.println(sum+" "+"O");
				}
				else
				{
					AIMoveP2(playerX,i);
					//System.out.println(sum+" "+"O");
				}
				//Conditions to check the state of the game
				if(game.getGameState(i)==1)
				{
					System.out.println("Game over. "+playerO.substring(2)+" won!");
					returnVal=1;
					break;
				}
				if(game.getGameState(i)==2)
				{
					System.out.println("Game over. "+playerX.substring(2)+" won!");
					returnVal=2;
					break;
				}
				if(game.getGameState(i)==3)
				{
					System.out.println("Game over. It was a draw!");
					returnVal=3;
					break;
				}
			}
		}else
		{
			/*
			 * If any one of the players is AI
			 */
			if((player1.substring(0,2).equals("AI"))||(player2.substring(0,2).equals("AI")))
			{
				//AI part
				if(player1.substring(0,2).equals("AI"))
				{
					for(int i=0;i<9;i++)
					{
						if(i%2==0)											//Logic for alternate chance for alternate player 
						{
							AIMoveP1(playerO,i);
						}
						else
						{
							hMoveP2(playerX,i);
							//System.out.println(sum+" "+"O");
						}
						//Conditions to check the state of the game
						if(game.getGameState(i)==1)
						{
							System.out.println("Game over. "+playerO.substring(2)+" won!");
							returnVal=1;
							break;
						}
						if(game.getGameState(i)==2)
						{
							System.out.println("Game over. "+playerX+" won!");
							returnVal=2;
							break;
						}
						if(game.getGameState(i)==3)
						{
							System.out.println("Game over. It was a draw!");
							returnVal=3;
							break;
						}
					}
				}else
				{
					for(int i=0;i<9;i++)
					{
						if(i%2==0)											//Logic for alternate chance for alternate player 
						{
							hMoveP1(playerO,i);
						}
						else
						{
							AIMoveP2(playerX,i);
							//System.out.println(sum+" "+"O");
						}
						//Conditions to check the state of the game
						if(game.getGameState(i)==1)
						{
							System.out.println("Game over. "+playerO+" won!");
							returnVal=1;
							break;
						}
						if(game.getGameState(i)==2)
						{
							System.out.println("Game over. "+playerX.substring(2)+" won!");
							returnVal=2;
							break;
						}
						if(game.getGameState(i)==3)
						{
							System.out.println("Game over. It was a draw!");
							returnVal=3;
							break;
						}
					}
				}
			}else
			{
				/*
				 * Both the players are humans
				 */
				for(int i=0;i<9;i++)
				{
					if(i%2==0)											//Logic for alternate chance for alternate player 
					{
						hMoveP1(playerO,i);
						//System.out.println(sum+" "+"O");
					}
					else
					{
						hMoveP2(playerX,i);
						//System.out.println(sum+" "+"O");
					}
					//Conditions to check the state of the game
					if(game.getGameState(i)==1)
					{
						System.out.println("Game over. "+playerO+" won!");
						returnVal=1;
						break;
					}
					if(game.getGameState(i)==2)
					{
						System.out.println("Game over. "+playerX+" won!");
						returnVal=2;
						break;
					}
					if(game.getGameState(i)==3)
					{
						System.out.println("Game over. It was a draw!");
						returnVal=3;
						break;
					}
				}
			}
		}
		
		if(returnVal!=0)
		{
			return returnVal;
		}
		else
		{
			return 0;
		}
	}
	
	/*
	 * Checking the move using checkMove()
	 * -if the grid space is already occupied
	 * -if the player input is not between {0,1,2}
	 */
	
	public int checkMove(String name,int a, int b,int turn)
	{
		
		/*
		 * Forming a string to using the moves of the Player
		 * So that it becomes easier for the comparison by using 
		 * gridPosOccupied[] array and executing direct comparisons
		 */
		String occ=a+"occ"+b;	
		int check=0;
		int ret=0;
		//System.out.println(occ);
		
		if((a<=2)&&(b<=2))
		{
			for(int k=0;k<9;k++)
			{
				if(occ.equals(gridPosOccupied[k]))
				{
					check=1;
					ret=1;
					System.out.println("Invalid move. The cell has been occupied.");
				}
			}
		}else
		{
			check=1;
			ret=1;
			System.out.println("Invalid move. You must place at a cell within {0,1,2} {0,1,2}.");
		}
		if(check==0)
		{
			gridPosOccupied[turn]=occ;
		}
		return ret;
	}
	//Function to print on the user's screen
	public void printGrid(int sumVal,String pTurn)
	{
		if(sumVal==0)
		{
			for (int i=0;i<9;i++)
			{
				gridPos[i]=" ";
			}
			//do Nothing
		}
		else
		{
			for (int j=0;j<9;j++)
			{
				if(sumVal==(j+1))
				{
					gridPos[j]=pTurn;
					break;
				}
			}
		}
		System.out.println(gridPos[0]+"|"+gridPos[1]+"|"+gridPos[2]);
		System.out.println("-----");
		System.out.println(gridPos[3]+"|"+gridPos[4]+"|"+gridPos[5]);
		System.out.println("-----");
		System.out.println(gridPos[6]+"|"+gridPos[7]+"|"+gridPos[8]);
	}
	
	//Function returning state of the Tic Tac Toe game
	
	public int getGameState(int chance)
	{
		final int WONA=1;
		final int WONB=2;
		final int DRAW=3;
		final int CONT=4;
			
		//Horizontal Check
			
		for(int i=0;i<=6;i+=3)
		{
			if((gridPos[i].equals(" "))||(gridPos[i+1].equals(" "))||(gridPos[i+2].equals(" ")))
			{
				continue;
			}else
			{
				if(gridPos[i].equals(gridPos[i+1]))
				{
					if(gridPos[i+1].equals(gridPos[i+2]))
					{
						if(gridPos[i].equals("O"))
						{
							return WONA;
						}else
						{
							return WONB;
						}
					}
				}
			}
		}
			
		//Vertical Check
			
		for(int i=0;i<=2;i++)
		{
			if((gridPos[i].equals(" "))||(gridPos[i+3].equals(" "))||(gridPos[i+6].equals(" ")))
			{
				continue;
			}else
			{
				if(gridPos[i].equals(gridPos[i+3]))
				{
					if(gridPos[i+3].equals(gridPos[i+6]))
					{
						if(gridPos[i].equals("O"))
						{
							return WONA;
						}else
						{
							return WONB;
						}
					}
				}
			}
		}
			
		//Diagonal Check
		
		if((gridPos[0].equals(" "))||(gridPos[4].equals(" "))||(gridPos[8].equals(" ")))
		{
			return CONT;
		}else
		{
			if(gridPos[0].equals(gridPos[4]))
			{
				if(gridPos[4].equals(gridPos[8]))
				{
					if(gridPos[0].equals("O"))
					{
						return WONA;
					}else
					{
						return WONB;
					}
				}
			}
		}
			
		//Anti Diagonal Checks
			
		if((gridPos[2].equals(" "))||(gridPos[4].equals(" "))||(gridPos[6].equals(" ")))
		{
			return CONT;
		}else
		{
			if(gridPos[2].equals(gridPos[4]))
			{
				if(gridPos[4].equals(gridPos[6]))
				{
					if(gridPos[2].equals("O"))
					{
						return WONA;
					}else
					{
						return WONB;
					}
				}
			}
		}
		
		if(chance==8)
		{
			return DRAW;
		}else
		{
		return CONT;
		}
	}
}
