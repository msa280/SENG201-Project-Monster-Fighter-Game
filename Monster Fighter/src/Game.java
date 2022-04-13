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
	 * Receives and holds the players input.
	 */	
	private Scanner scan_input = new Scanner(System.in);
	/*
	 * The players name.
	 */
	private String playerName;
	/*
	 * The game length.
	 */
	private int gameLength = 5;
	/*
	 * The game difficulty.
	 */
	private double gameDifficulty = 0.0;
	/*
	 * Whether or not the game is over.
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
	 * Gets the total number of days the game lasts.
	 */	
	public int getGameLength() 
	{
		return gameLength;
	}

	/*
	 * Sets the total number of days the game lasts.
	 */
	public void setGameLength(int gameLength)
	{
		this.gameLength = gameLength;
	}

	/*
	 * Gets the game difficulty.
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
	 * Gets whether or not the game is over.
	 */
	public boolean getGameOver() {
		return gameOver;
	}
	
	/*
	 * Sets whether or not the game is over.
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}


	/*
	 * Checks if the name given is valid.
	 * 
	 * @param numOrSpecialChar 	Checks if the input has numbers or special characters
	 * @param nameCheckPassed	Checks if the name is valid(to be fixed)
	 * @param nameLength		The length of the name that was input
	 * @param scannedName		The name that was input
	 */
	public boolean check_name(boolean num_or_special_char, boolean name_check_passed, int name_length, String scanned_name) 
	{
		if (num_or_special_char) 
		{
			System.out.println("\n" + scanned_name + " contains numbers or special characters!\n");
			System.out.println("Please choose a different fighter name.\n");
		} 
		else if (name_length < 3) 
		{
			System.out.println(scanned_name + " is too short!\n");
			System.out.println("\nPlease choose a longer fighter name.\n");
		} 
		else if (name_length > 15) 
		{
			System.out.println(scanned_name + " is too long!\n");
			System.out.println("\nPlease choose a shorter fighter name.\n");
		}
		else 
		{
			name_check_passed = true;
		}
		return name_check_passed;
	}
	
	
	public void ask_PlayerName() 
	{
		
		Pattern pattern = Pattern.compile("[^a-zA-Z]");
		String scanned_name = null;
		Matcher matcher = null;
		int name_length = 0;
		boolean num_or_special_char = false;
		boolean name_check_passed = false;
		
		while (name_check_passed == false) 
		{	
			System.out.print("\nEnter your fighter name: ");
			scanned_name = scan_input.nextLine();
			name_length = scanned_name.length();
			matcher = pattern.matcher(scanned_name);
			num_or_special_char = matcher.find();
			name_check_passed = check_name(num_or_special_char, name_check_passed, name_length, scanned_name);
		}
		this.setPlayerName(scanned_name);
	}
	
	
	public boolean check_gameLength(boolean gameLength_check_passed, int scanned_length) 
	{
		
		if (scanned_length < 5) 
		{
			System.out.println(scanned_length + " is too short!\n");
			System.out.println("\nPlease choose a number of days between 5 and 15.\n");
		} 
		else if (scanned_length > 15) 
		{
			System.out.println(scanned_length + " is too long!\n");
			System.out.println("\nPlease choose a number of days between 5 and 15.\n");
		}
		else 
		{
			gameLength_check_passed = true;
		}
		return gameLength_check_passed;
	}
	
	
	public void ask_GameLength() 
	{
		
		int scanned_length = 0;
		boolean gameLength_check_passed = false;
		
		while (gameLength_check_passed == false) {
			
			System.out.print("\nEnter the number of days you want to play: ");
			
			try 
			{
				scanned_length = scan_input.nextInt();
				gameLength_check_passed = check_gameLength(gameLength_check_passed, scanned_length);
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("\nGame length should be a number between 5 and 15.\n");
				scan_input.next();
				continue;
			}
		}
		
		this.setGameLength(scanned_length);
	}
	
	
	public boolean check_and_set_gameDifficulty(boolean valid_option, int option_number) 
	{
		if (option_number == 1) 
		{
			this.setGameDifficulty(1.0);
			valid_option = true;
        } 
		else if (option_number == 2) 
        {
			this.setGameDifficulty(0.5);
			valid_option = true;
        } 
		else if (option_number == 3) 
		{
			this.setGameDifficulty(1.5);
			valid_option = true;
        } 
		else if (option_number == 4) 
		{
			this.setGameDifficulty(2.0);
			valid_option = true;
        }
		else if (option_number == 5) 
		{
			this.setGameDifficulty(3.0);
			valid_option = true;
        }
		else 
		{
			System.out.print("\nChoose an option number between 1 and 5.\n");
		}
		return valid_option;
		
	}
	
	
	public void ask_GameDifficulty() 
	{
		int option_number = 0;
		boolean valid_option = false;
		
	    while (valid_option == false) 
	    {
			
	    	System.out.println("\nWhat is your preferred game difficulty?\n");
		    System.out.println("1. Classic");
		    System.out.println("2. Easy");
		    System.out.println("3. Normal");
		    System.out.println("4. Hard");
		    System.out.println("5. Boss\n");
			
			try 
			{
				option_number = scan_input.nextInt();
				valid_option = check_and_set_gameDifficulty(valid_option, option_number);
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("\nPlease enter a valid option number.\n");
				scan_input.next();
				continue;
			}
		}
	}
	
	
	
	
	
	public static void main(String[] args)
	{
		System.out.print("\nMonster Fighter Beta V1\n\n");
		Game new_game = new Game();
		new_game.ask_PlayerName();
		new_game.ask_GameLength();
		new_game.ask_GameDifficulty();
		
		Player new_player = new Player();
		new_player.setPlayerGold(1000, new_game);
		new_player.setCurrentDay(1);
		new_player.setDaysRemaining(new_player.getCurrentDay(), new_game);
		new_player.choose_startingMonster();
		
		
		while (!new_game.getGameOver()) 
		{
			new_player.player_viewer();
			System.out.print("NOW GAME STARTS! Add functions inside main game!");
			// do game
			break;
		}
	}
}
