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
	 * The total number of days the game is going to be played.
	 */
	private int gameLength = 5;
	/*
	 * The difficulty of the game.
	 */
	private double gameDifficulty = 0.0;
	/*
	 * Whether the game is over or not.
	 */
	private boolean gameOver = false;


	/*
	 * Gets the players name.
	 */
	public String getPlayerName() 
	{
		return playerName;
	}

	/*
	 * Sets the players name.
	 */
	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}
	
	/*
	 * Gets the players name.
	 */
	public int getGameLength() 
	{
		return gameLength;
	}

	/*
	 * Sets the players name.
	 */
	public void setGameLength(int gameLength)
	{
		this.gameLength = gameLength;
	}
	
	/*
	 * Gets the game difficulty name.
	 */
	public double getGameDifficulty() 
	{
		return gameDifficulty;
	}

	/*
	 * Sets the game difficulty.
	 */
	public void setGameDifficulty(double gameDifficulty)
	{
		this.gameDifficulty = gameDifficulty;
	}
	
	/*
	 * Gets whether the game is over or not.
	 */
	public boolean getGameOver() {
		return gameOver;
	}

	/*
	 * Sets whether the game is over or not.
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
	public boolean checkName(boolean numOrSpecialChar, boolean nameCheckPassed, int nameLength, String scannedName) 
	{
		if (numOrSpecialChar) 
		{
			System.out.println("\n" + scannedName + " contains numbers or special characters!\n");
			System.out.println("Please choose a different fighter name.\n");
		} 
		else if (nameLength < 3) 
		{
			System.out.println(scannedName + " is too short!\n");
			System.out.println("\nPlease choose a longer fighter name.\n");
		} 
		else if (nameLength > 15) 
		{
			System.out.println(scannedName + " is too long!\n");
			System.out.println("\nPlease choose a shorter fighter name.\n");
		}
		else 
		{
			nameCheckPassed = true;
		}
		return nameCheckPassed;
	}
	
	
	public void askPlayerName() 
	{
		
		Pattern pattern = Pattern.compile("[^a-zA-Z]");
		String scannedName = null;
		Matcher matcher = null;
		int nameLength = 0;
		boolean numOrSpecialChar = false;
		boolean nameCheckPassed = false;
		
		while (nameCheckPassed == false) 
		{	
			
			System.out.print("\nEnter your fighter name: ");
			scannedName = scanInput.nextLine();
			nameLength = scannedName.length();
			matcher = pattern.matcher(scannedName);
			numOrSpecialChar = matcher.find();
			nameCheckPassed = checkName(numOrSpecialChar, nameCheckPassed, nameLength, scannedName);
		}
		this.setPlayerName(scannedName);
	}
	
	
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
	
	
	
	
	
	public static void main(String[] args)
	{
		System.out.print("\nMonster Fighter Beta V1\n\n");
		Game new_game = new Game();
		new_game.askPlayerName();
		new_game.askGameLength();
		new_game.askGameDifficulty();
		
		Shop new_shop = new Shop();
		new_shop.initialize_shop();
		
		Player new_player = new Player();
		new_player.setGame(new_game);
		int startingGold = (int)(1000 * new_game.getGameDifficulty());
		new_player.setPlayerGold(startingGold);
		new_player.setCurrentDay(1);
		new_player.setDaysRemaining(new_player.getCurrentDay(), new_game);
		new_player.choose_startingMonster();
		new_player.setShop(new_shop);
		new_shop.get_trader(new_player);
		
		Battle new_battle = new Battle();
		new_battle.setPlayer(new_player);
		new_battle.generateBattles();
		new_player.setBattle(new_battle);
		
		
		while (!new_game.getGameOver()) 
		{
			new_player.player_viewer();
			// do game
			break;
		}
	}
}
