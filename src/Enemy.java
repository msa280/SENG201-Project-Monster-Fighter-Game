import java.util.ArrayList;
import java.util.Random;

import monsters.Cavernfreak;
import monsters.Hollowtree;
import monsters.Monster;
import monsters.Mornpest;
import monsters.Soilscreamer;
import monsters.Venomhound;

public class Enemy {
	
	private String enemyName;
	private ArrayList<Monster> enemyTeam = new ArrayList<Monster>();
	private boolean alreadyFought = false;
	
	private Monster allMonsters[] = new Monster[5];
	private String allEnemyNames[] = {"Angela", "Arnold", "Cameron", "Julian", "Bladee", "Thaiboy", "Jonathan", "Mark", "Joshua", "Andrew"};
	
	
	
	public void setEnemyFightAvailability(boolean haveFought)
	{
		this.alreadyFought = haveFought;
	}
	
	public void setEnemyName(String name)
	{
		this.enemyName = name;
	}
	
	public String getEnemyName()
	{
		return this.enemyName;
	}
	
	public boolean getEnemyFightAvailability()
	{
		return this.alreadyFought;
	}
	
	public ArrayList<Monster> getEnemyTeam()
	{
		return this.enemyTeam;
	}
	
	public void setAlreadyFought(boolean alreadyFought) {
		this.alreadyFought = alreadyFought;
	}
	
	public boolean getAlreadyFought() {
		return alreadyFought;
	}
	
	
	
	public void generateEnemyMonsters()
	{
		this.allMonsters[0] = new Cavernfreak();
		this.allMonsters[1] = new Hollowtree();
		this.allMonsters[2] = new Mornpest();
		this.allMonsters[3] = new Soilscreamer();
		this.allMonsters[4] = new Venomhound();
	}
	
	
	public String generateEnemyName()
	{
		Random randNum = new Random();
		int upperBound = 10;
		int position = randNum.nextInt(upperBound);
		
		String name = this.allEnemyNames[position];
		return name;	
	}
	
	
	
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
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
