/**
 * SENG201 Project 2022 - Monster Fighter
 * This is the 'Game' class for the monster fighter which runs the 
 * the main game.
 * @author Haider Saeed (msa280) & Jakib Isherwood (jis48)
 * 
 */

import java.util.InputMismatchException;
import java.util.Scanner;  // Imports
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

import monsters.Monster;




/*
 * A class that initializes the game and stores information pertaining to the game.
 */
public class Game {

	/*
	 * The players input.
	 */
	private Scanner scanInput = new Scanner(System.in);
	/*
	 * The players name.
	 */
	private String playerName;
	/*
	 * The total number of days the game is going to be played. 5-15 days.
	 */
	private int gameLength;
	/*
	 * The difficulty of the game.
	 */
	private double gameDifficulty;
	/*
	 * Whether the game is over or not.
	 */
	private boolean gameOver;
	
	private Monster selectedMonster;
	
	
	
	/*
	 * Initiates the game.
	 */
	void init()
	{
		this.playerName = "Monster Fighter";
		this.gameLength = 5;
		this.gameDifficulty = 0.0;
		this.gameOver = false;
		this.selectedMonster = null;
	}


	/*
	 * Gets the players name.
	 * 
	 * @return The value of playerName.
	 */
	public String getPlayerName() 
	{
		return playerName;
	}

	/*
	 * Sets the players name.
	 * 
	 * @param playerName Sets the value playerName.
	 */
	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}
	
	/*
	 * Gets the game length.
	 * 
	 * @return gameLength Returns the duration of the game in in-game days.
	 */
	public int getGameLength() 
	{
		return gameLength;
	}

	/*
	 * Sets the game length.
	 * 
	 * @param gameLength The duration of the game in in-game days.
	 */
	public void setGameLength(int gameLength)
	{
		this.gameLength = gameLength;
	}
	
	/*
	 * Gets the game difficulty .
	 * 
	 * @return Returns the value of gameDifficulty.
	 */
	public double getGameDifficulty() 
	{
		return gameDifficulty;
	}

	/*
	 * Sets the game difficulty. Will be a double between 0.5 and 3.0.
	 * 
	 * @param gameDifficulty The value of gameDifficultly.
	 */
	public void setGameDifficulty(double gameDifficulty)
	{
		this.gameDifficulty = gameDifficulty;
	}
	
	/*
	 * Gets whether the game is over or not.
	 * 
	 * @return Returns the value of gameOver.
	 */
	public boolean getGameOver() {
		return gameOver;
	}

	/*
	 * Sets whether the game is over or not.
	 * 
	 * @param gameOver The current value of gameOver
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	
	/*
	 * Determines whether or not the input for the players name is valid.
	 * 
	 * @param numOrSpecialChar 	Whether or not the input is a number or a special character.
	 * @param nameCheckPassed	Whether or not the input is valid.
	 * @param nameLength		The length of the input.
	 * @param scannedName		The input.
	 * 
	 * @return					Returns true if the players name is valid.
	 */
	public String checkName(boolean numOrSpecialChar, int nameLength, String scannedName) 
	{
		if (numOrSpecialChar) 
		{
			return ("\n" + scannedName + " contains numbers or special characters!\n");
		} 
		else if (nameLength < 3) 
		{
			return (scannedName + " is too short!\n");
		} 
		else if (nameLength > 15) 
		{
			return (scannedName + " is too long!\n");
		}
		else 
		{
			return "Ok.";
		}
	}
	
	/*
	 * Checks if the players name is valid. If so returns "Ok.", else asks to try again.
	 * 
	 * @param name The name the player input.
	 * 
	 * @return Returns whether the players name is valid or not.
	 */
	public String checkPlayerName(String name) 
	{
		
		Pattern pattern = Pattern.compile("[^a-zA-Z]");
		Matcher matcher = null;
		int nameLength = 0;
		boolean numOrSpecialChar = false;
		String error;
		
		nameLength = name.length();
		matcher = pattern.matcher(name);
		numOrSpecialChar = matcher.find();
		error = checkName(numOrSpecialChar, nameLength, name);
		
		return error;
		
	}
	
	/*
	 * Checks if the players input for game length is valid.
	 * 
	 * @param gameLengthCheckPassed Whether or not the game length is valid.
	 * @param scannedLength The number of days the player input.
	 * 
	 * @return Returns whether the input number of days was valid or not.
	 */
	public boolean checkGameLength(boolean gameLengthCheckPassed, int scannedLength) 
	{
		
		if (scannedLength < 5) 
		{
			System.out.println(scannedLength + " is too short!\n");
			System.out.println("\nPlease choose a number of d1ays between 5 and 15.\n");
		} 
		else if (scannedLength > 15) 
		{
			System.out.println(scannedLength + " is too long!\n");
			System.out.println("\nPlease choose a number of days between 5 and 15.\n");
		}
		else 
		{
			gameLengthCheckPassed = true;
		}
		return gameLengthCheckPassed;
	}
	
	/*
	 * Asks the player of the number of days they would like to play for.
	 */
	public void askGameLength() 
	{
		
		int scannedLength = 0;
		boolean gameLengthCheckPassed = false;
		
		while (gameLengthCheckPassed == false) {
			
			System.out.print("\nEnter the number of days you want to play: ");
			
			try 
			{
				scannedLength = scanInput.nextInt();
				gameLengthCheckPassed = checkGameLength(gameLengthCheckPassed, scannedLength);
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("\nGame length should be a number between 5 and 15.\n");
				scanInput.next();
				continue;
			}
		}
		
		this.setGameLength(scannedLength);
	}
	
	/*
	 * Checks if the input value for game difficulty is valid. If not returns false and asks again,
	 * else returns true.
	 * 
	 * @param validOption Whether the players input was a valid option or not.
	 * @param optionNumber What difficulty the player selected.
	 * 
	 * @return Returns whether or not the player input a valid option.
	 */
	public boolean checkAndSetGameDifficulty(boolean validOption, int optionNumber) 
	{
		if (optionNumber == 1) 
		{
			this.setGameDifficulty(1.0);
			validOption = true;
        } 
		else if (optionNumber == 2) 
        {
			this.setGameDifficulty(0.5);
			validOption = true;
        } 
		else if (optionNumber == 3) 
		{
			this.setGameDifficulty(1.5);
			validOption = true;
        } 
		else if (optionNumber == 4) 
		{
			this.setGameDifficulty(2.0);
			validOption = true;
        }
		else if (optionNumber == 5) 
		{
			this.setGameDifficulty(3.0);
			validOption = true;
        }
		else 
		{
			System.out.print("\nChoose an option number between 1 and 5.\n");
		}
		return validOption;
		
	}
	
	/*
	 * Asks the player what difficulty they would like to play on.
	 */
	public void askGameDifficulty() 
	{
		int optionNumber = 0;
		boolean validOption = false;
		
	    while (validOption == false) 
	    {
			
	    	System.out.println("\nWhat is your preferred game difficulty?\n");
		    System.out.println("1. Classic");
		    System.out.println("2. Easy");
		    System.out.println("3. Normal");
		    System.out.println("4. Hard");
		    System.out.println("5. Boss\n");
			
			try 
			{
				optionNumber = scanInput.nextInt();
				
				validOption = checkAndSetGameDifficulty(validOption, optionNumber);
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("\nPlease enter a valid option number.\n");
				scanInput.next();
				continue;
			}
		}
	}
	
	
	
	
	/*
	 * Starts the game.
	 */
	public static void main(String[] args)
	{
		Game newGame = new Game();
		newGame.init();
		SetupMenu setup = new SetupMenu();
		setup.setGame(newGame);
		setup.launch_setup_menu(setup);
	}

	/*
	 * Gets the selected monster.
	 * 
	 * @return Returns that selected monster.
	 */
	public Monster getSelectedMonster() {
		return selectedMonster;
	}

	/*
	 * Sets the selected monster.
	 * 
	 * @param selectedMonster The monster that was selected.
	 */
	public void setSelectedMonster(Monster selectedMonster) {
		this.selectedMonster = selectedMonster;
	}


	
		
		
		
		
		
		
		
		/** System.out.print("\nMonster Fighter V1.0\n\n");
		Game newGame = new Game();
		newGame.askPlayerName();
		newGame.askGameLength();
		newGame.askGameDifficulty();
		
		Shop newShop = new Shop();
		newShop.initializeShop();
		
		Player newPlayer = new Player();
		newPlayer.setGame(newGame);
		int startingGold = (int)(1000 * newGame.getGameDifficulty());
		newPlayer.setPlayerGold(startingGold);
		newPlayer.setCurrentDay(1);
		newPlayer.setDaysRemaining(newPlayer.getCurrentDay(), newGame);
		newPlayer.chooseStartingMonster();
		newPlayer.setShop(newShop);
		newShop.getTrader(newPlayer);
		
		Battle newBattle = new Battle();
		newBattle.setPlayer(newPlayer);
		newBattle.generateBattles();
		newPlayer.setBattle(newBattle);
		
		
		while (!newGame.getGameOver()) 
		{
			newPlayer.playerViewer();
			// do game
			break;
		}
	}*/
}
