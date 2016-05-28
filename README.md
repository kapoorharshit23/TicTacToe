# TicTacToe
TicTacToe game with commands such as adding a player,editing a player etc. You can also add an AI player. 
Some of the coomands are explained below:

addplayer username,family_name,given_name //AddPlayer command

removeplayer [username] //RemovePlayer;can be used as a standalone command as well to remove all the players

editplayer username,new_family_name,new_given_name  //EditPlayer command

resetstats [username] //Allow player statistics to be reset (that is, setting the numbers of games played/won/drawn to be 0).

displayplayer [username] //Display player information by calling the displayPlayer method of the PlayerManager object. 
                          The username of the player whose information is to be displayed is given as an argument to the command.
                          
rankings  //Outputs the top-10 players ranked based on winning ratio from the highest to the lowest.

playgame username1,username2  //Play a game of Tic Tac Toe by calling the playGame method of the GameManager object, 
                                with the usernames of the two players provided as arguments. 
