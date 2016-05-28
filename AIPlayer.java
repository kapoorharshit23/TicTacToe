/*
 * Author Name:Harshit Kapoor
 * Student Number:666810
 * 
 * Tic Tac Toe Game Solution
 * AIPlayer.java class
 * */

public class AIPlayer extends Player{

	//Variables to store the details of Users
			Move moveAI=new Move();
			
			
			//Constructor to initialise the various AI players
			
			public AIPlayer()
			{
				setUserName("");
				setFamilyName("");
				setGivenName("");
				setGamesPlayed(0);
				setGamesWon(0);
				setGamesDrawn(0);
			}

			//Accessors and mutators for the respective variables
			
			public String getUserName() {
				return super.getUserName();
			}

			public void setUserName(String userName) {
				super.setUserName(userName);
			}

			public String getFamilyName() {
				return super.getFamilyName();
			}

			public void setFamilyName(String familyName) {
				super.setFamilyName(familyName);
			}

			public String getGivenName() {
				return super.getGivenName();
			}

			public void setGivenName(String givenName) {
				super.setGivenName(givenName);
			}

			public int getGamesPlayed() {
				return super.getGamesPlayed();
			}

			public void setGamesPlayed(int gamesPlayed) {
				super.setGamesPlayed(gamesPlayed);
			}

			public int getGamesWon() {
				return super.getGamesWon();
			}

			public void setGamesWon(int gamesWon) {
				super.setGamesWon(gamesWon);
			}

			public int getGamesDrawn() {
				return super.getGamesDrawn();
			}

			public void setGamesDrawn(int gamesdrawn) {
				super.setGamesDrawn(gamesdrawn);
			}
			/*
			 * AI Player makeMove() method
			 */
			public Move makeMove(char[][] gameBoard)
			{
				int randomMove = (int)(Math.random()*9);
				int checkMove=0;
				int[][] listOfMoves={{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
				int m1=listOfMoves[randomMove][0];
				int n=listOfMoves[randomMove][1];
				while(checkMove==0)
				{
					if(gameBoard[m1][n]=='y')
					{
						//System.out.println("not a valid move");
						checkMove=0;
						/*
						 * Using random method to choose 
						 * any of the above moves in random
						 * fashion. 
						 */
						randomMove = (int)(Math.random()*9);	 
						m1=listOfMoves[randomMove][0];
						n=listOfMoves[randomMove][1];
						//System.out.println("move:"+randomMove);
						//System.out.println(listOfMoves[randomMove][0]+" "+listOfMoves[randomMove][1]);
					}
					else
					{
						checkMove=1;
					}
				}
				moveAI.setRow(m1);
				moveAI.setCol(n);
				gameBoard[m1][n]='y';		//Substituting 'y' at all the board positions
				return moveAI;
			}
}
