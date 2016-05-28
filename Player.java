/*
 * Author Name:Harshit Kapoor
 * Student Number:666810
 * 
 * Tic Tac Toe Game Solution
 * Player.java class
 * */

public abstract class Player {
	
	//Variables to store the details of Users
	
	private String userName;
	
	private String familyName;
	
	private String givenName;
	
	private int gamesPlayed;
	
	private int gamesWon;
	
	private int gamesDrawn;
	
	//Constructor to initialise the various variables
	
	public Player()
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
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	public int getGamesDrawn() {
		return gamesDrawn;
	}

	public void setGamesDrawn(int gamesdrawn) {
		this.gamesDrawn = gamesdrawn;
	}
	public abstract Move makeMove(char[][] gameBoard);		//make Move method which will be defined in child classes
}
