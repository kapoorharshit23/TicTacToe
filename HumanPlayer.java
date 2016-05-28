/*
 * Author Name:Harshit Kapoor
 * Student Number:666810
 * 
 * Tic Tac Toe Game Solution
 * HumanPlayer.java class
 * */

public class HumanPlayer extends Player{

	//Variables to store the details of Users
		Move move=new Move();
		
		/*//private String userName;
		
		private String familyName;
		
		private String givenName;
		
		private int gamesPlayed;
		
		private int gamesWon;
		
		private int gamesDrawn;*/
		
		//Constructor to initialise the various Human players
		
		public HumanPlayer()
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
		 * Human makeMove() method
		 */
		
		public Move makeMove(char[][] gameBoard)
		{
			String oInput=TicTacToe.keyboard.nextLine();
			int m1=Integer.parseInt(oInput.substring(0, 1));
			int n=Integer.parseInt(oInput.substring(2, 3));
			move.setRow(m1);
			move.setCol(n);
			if((m1<3)&&(n<3))
			{
			gameBoard[m1][n]='y';		//Substituting 'y' at all the occupied positions
			}
			return move;
		}
}
