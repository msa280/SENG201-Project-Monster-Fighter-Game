package game;
/**
 * SENG201 Project 2022 - Monster Fighter
 * This is the 'Game' class for the monster fighter which runs the 
 * the main game.
 * @author Haider Saeed (msa280) & Jakib Isherwood (jis48)
 * 
 */

import java.util.regex.Matcher; 
import java.util.regex.Pattern;

import javax.swing.JTextPane;

import GUI.Intro;
import monsters.Monster;


/*
 * A class that initializes the game and stores information pertaining to the game.
 */
public class Game 
{

	/*
	 * The players name.
	 */
	private String playerName;
	/*
	 * The total number of days the game is going to be played. 5-15 days.
	 */
	private int gameLength;
	/*
	 * The difficulty of the game. 0.5 - 3.0, the latter being the most difficult.
	 */
	private double gameDifficulty;
	/*
	 * Whether the game is over or not.
	 */
	private boolean gameOver;
	/*
	 * The selected monster.
	 */
	private Monster selectedMonster;
	/*
	 * Whether the setup is complete or not.
	 */
	private boolean setupComplete;
	
	/*
	 * Gets if the setup is complete or not.
	 * 
	 * @return The value of setupComplete, true if true, else false.
	 */
	public boolean getSetupComplete() 
	{
		return setupComplete;
	}
	
	/*
	 * Sets the value of setupComplete.
	 * 
	 * @param The value of setup complete.
	 */
	public void setSetupComplete(boolean setupComplete) 
	{
		this.setupComplete = setupComplete;
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
	public boolean getGameOver() 
	{
		return gameOver;
	}

	/*
	 * Sets whether the game is over or not.
	 * 
	 * @param gameOver The current value of gameOver
	 */
	public void setGameOver(boolean gameOver)
	{
		this.gameOver = gameOver;
	}
	
	
	/*
	 * Gets the selected monster.
	 * 
	 * @return Returns that selected monster.
	 */
	public Monster getSelectedMonster() 
	{
		return selectedMonster;
	}

	
	/*
	 * Sets the selected monster.
	 * 
	 * @param selectedMonster The monster that was selected.
	 */
	public void setSelectedMonster(Monster selectedMonster) 
	{
		this.selectedMonster = selectedMonster;
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
		error = checkNameErrors(numOrSpecialChar, nameLength, name);
		
		return error;
	}
	
	/*
	 * Checks if the input by the player for their name contains any errors and thus would be invalid.
	 * 
	 * @param numOrSpecialChar If there's a number or special character in the name.
	 * @param nameLength The length of the name (number of characters).
	 * @scannedName The players input for their name.
	 * 
	 * @return Returns "OK" if name is valid, an error else.
	 */
	public String checkNameErrors(boolean numOrSpecialChar, int nameLength, String scannedName) 
	{
		if (numOrSpecialChar) {
			return ("Error: Name '%s' contains numbers, spaces or special characters!".formatted(scannedName));
		} 
		else if (nameLength == 0) {
			return ("Error: Please enter your name.".formatted(scannedName));
		}
		else if (nameLength < 3) {
			return ("Error: Name '%s' is too short!".formatted(scannedName));
		} 
		else if (nameLength > 15) {
			return ("Error: Name '%s' is too long!".formatted(scannedName));
		}
		else {
			return "OK";
		}
	}
	
	/*
	 * Sets the game difficulty.
	 * 
	 * @param difficulty The value of difficulty.
	 */
	public void setGameDifficulty(String difficulty)
	{
		if (difficulty == "Easy")
		{
			this.gameDifficulty = 0.5;
		} else if (difficulty == "Normal")
		{
			this.gameDifficulty = 1.0;
		} else if (difficulty == "Classic") 
		{
			this.gameDifficulty = 1.5;
		} else if (difficulty == "Hard") 
		{
			this.gameDifficulty = 2.0;
		} else 
		{
			this.gameDifficulty = 3.0;
		}
	}
	
	/*
	 * Finishes the game setup, and starts the main game.
	 * 
	 * @param monsterName The name of the monster the player chose.
	 * @param gameLength The length of the game in days.
	 * @param difficulty The difficulty of the game.
	 */
	public void finishSetup(String monsterName, int gameLength, String difficulty)
	{
		if (monsterName.length() != 0) {
			this.selectedMonster.setMonsterRename(monsterName);
		}
	
		this.gameLength = gameLength;
		this.setGameDifficulty(difficulty);
		this.setSetupComplete(true);
	}
	
	/*
	 * Whether the name setup was successful or not.
	 * 
	 * @param enterName The players input for a name
	 * 
	 * @return Returns an error if the player had not selected a monster, else
	 * returns an empty string.
	 */
	public String setupSuccessful(JTextPane enterName)
	{
		if (this.selectedMonster == null) {
			return "Please select a monster.";
		} else {
			return "";
		}
	}
	
	/*
	 * Displays the games basic settings.
	 * 
	 * @param pane The pane that displays the settings.
	 */
	public String displaySettings()
	{
		String line1 = "Fighter Name: %s\n".formatted(this.playerName);
		String line2 = "Selected Monster: %s\n".formatted(this.selectedMonster.getMonsterName());
		String line5 = "Renamed Monster: %s".formatted(this.selectedMonster.getMonsterRename());
		String line3 = "Game Days: %d\n".formatted(this.gameLength);
		String line4 = "Game Difficulty: %f\n".formatted(this.gameDifficulty);
		String display = line1 + line2 +line3 + line4 + line5;
		return display;
	}
	
	/*
	 * Starts the main game.
	 */
	public static void main(String[] args)
	{
		Intro.launchIntro();
	}

}
