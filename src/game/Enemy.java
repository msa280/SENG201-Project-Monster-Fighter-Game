package game;
import java.util.ArrayList;
import java.util.Random;

import monsters.Cavernfreak;
import monsters.Hollowtree;
import monsters.Monster;
import monsters.Mornpest;
import monsters.Soilscreamer;
import monsters.Venomhound;

/*
 * Generates enemies, and their monster teams.
 */

public class Enemy {
	
	/*
	 *  The name of the Enemy.
	 */
	private String enemyName;
	/*
	 * The enemies team, which is a list of Monsters.
	 */
	private ArrayList<Monster> enemyTeam = new ArrayList<Monster>();
	/*
	 * Whether the enemy has already been fought or not.
	 */
	private boolean alreadyFought = false;
	/*
	 * A list of Monsters.
	 */
	private Monster allMonsters[] = new Monster[5];
	/*
	 * All of the names of the enemies.
	 */
	private String allEnemyNames[] = {"Angela", "Arnold", "Cameron", "Haider", "Haru", "MonsterKing", "Saima", "Sarah", "Zack", "Jonathan"};
	
	
	/*
	 * Sets the value of haveFought.
	 * 
	 * @param haveFought Whether the enemy has already fought the player or not.
	 */
	public void setEnemyFightAvailability(boolean haveFought)
	{
		this.alreadyFought = haveFought;
	}
	
	/*
	 * Sets the value of enemyName.
	 * 
	 * @param enemyName The name of the Enemy.
	 */
	public void setEnemyName(String enemyName)
	{
		this.enemyName = enemyName;
	}
	
	/*
	 * Gets the enemies name.
	 * 
	 * @return Returns the value of enemyName.
	 */
	public String getEnemyName()
	{
		return this.enemyName;
	}
	
	/*
	 * Gets whether the enemy has been fought or not already.
	 * 
	 * @return Returns the value of alreadyFought.
	 */
	public boolean getEnemyFightAvailability()
	{
		return this.alreadyFought;
	}
	
	/*
	 * Gets the enemies team which consists of Monsters.
	 * 
	 * @return Returns the enemyTeam array.
	 */
	public ArrayList<Monster> getEnemyTeam()
	{
		return this.enemyTeam;
	}
	
	/*
	 * Sets the value of alreadyFought.
	 * 
	 * @param alreadyFought Whether or not the player has already fought the enemy.
	 */
	public void setAlreadyFought(boolean alreadyFought) {
		this.alreadyFought = alreadyFought;
	}
	
	/*
	 * Gets whether or not the enemy has already been fought. If they have,
	 * returns true, else false.
	 * 
	 * @return Returns the value of alreadyFought.
	 */
	public boolean getAlreadyFought() {
		return alreadyFought;
	}
	
	/*
	 * Gets the specific enemies image.
	 * 
	 * @return Returns the value of getEnemyImage.
	 */
	public String getEnemyImage()
	{
		String name = this.enemyName;
		String imageLink;
		
		if (name == "Angela") {
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Enemies Artwork\\Angela.png";
		} else if (name == "Arnold") {
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Enemies Artwork\\Arnold.png";
		} else if (name == "Cameron") {
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Enemies Artwork\\Cameron.png";
		} else if (name == "Haider") {
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Enemies Artwork\\Haider.png";
		} else if (name == "Haru") {
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Enemies Artwork\\Haru.png";
		} else if (name == "MonsterKing") {
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Enemies Artwork\\MonsterKing.png";
		} else if (name == "Saima") {
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Enemies Artwork\\Saima.png";
		} else if (name == "Sarah") {
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Enemies Artwork\\Sarah.png";
		} else if (name == "Zack") {
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Enemies Artwork\\Zack.png";
		} else {
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Enemies Artwork\\Jonathan.png";
		}
		
		return imageLink;
	}
	
	/*
	 * Checks if the enemy has any monsters alive in their team. 
	 * 
	 * @return Returns true if the enemy has any monsters alive, false otherwise.
	 */
	public boolean checkAnyMonsterAlive()
	{
		for (Monster monster: this.enemyTeam)
		{
			if (monster.isFaint() == false)
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Generates the enemy monsters used to create their teams.
	 */
	public void generateEnemyMonsters()
	{
		this.allMonsters[0] = new Cavernfreak();
		this.allMonsters[1] = new Hollowtree();
		this.allMonsters[2] = new Mornpest();
		this.allMonsters[3] = new Soilscreamer();
		this.allMonsters[4] = new Venomhound();
	}
	
	/*
	 * Generates the enemies name by randomly picking
	 * a position in a list of names.
	 * 
	 * @return Returns the enemies name.
	 */
	public String generateEnemyName()
	{
		Random randNum = new Random();
		int upperBound = 10;
		int position = randNum.nextInt(upperBound);
		
		String name = this.allEnemyNames[position];
		return name;	
	}
	
	/*
	 * Generates enemy teams.
	 */
	public void generateEnemyTeam()
	{
		boolean generationComplete = false;
		ArrayList<Integer> numsDone = new ArrayList<Integer>();
		
		this.enemyTeam.clear();  // Clearing array to generate a new array of monsters if the day changes
		
		this.generateEnemyMonsters();
		
		Random randomSize = new Random();
		int upperbound = 2;
		int teamSize = 2 + randomSize.nextInt(upperbound); // getting a random size of team between 2 and 4.
		
		while (generationComplete == false)
		{
			Random randNum = new Random();
			int upperBound = 5;
			int position = randNum.nextInt(upperBound);
			
			if (this.enemyTeam.size() == teamSize)
			{
				generationComplete = true;
			}
			else if (numsDone.contains(position))
			{
				continue;
			}
			else
			{
				if (this.enemyTeam.contains(this.allMonsters[position]))
				{
					continue;
				}
				else
				{
					this.enemyTeam.add(this.allMonsters[position]);
				}		
			}
		}
	}
}
