package game;
import java.util.ArrayList;
import java.util.Random;


import monsters.Monster;

/**
 * Generates battles between the player and the enemy.
 */

public class Battle 
{
	/*
	 * The list of enemies the player will verse.
	 */
	private ArrayList<Enemy> battles = new ArrayList<Enemy>();
	/*
	 * The player.
	 */
	private Player player;
	
	/*
	 * The enemy.
	 */
	private Enemy enemy;
	/*
	 * Generates a stream of pseudorandom numbers.
	 */
	private Random random = new Random();

	/*
	 * Whether or not the battle is over.
	 */
	private boolean battleOver = false;
	/*
	 * Whether or not the player won the battle.
	 */
	private boolean playerWon;
	/*
	 * The amount of gold earned.
	 */
	private int battleGold = 0;
	/*
	 * The amount of experience gained.
	 */
	private int experiencePoints = 0;
    /*
     * The amount of hits taken.
     */
	private int hitsTaken = 0;
	
	/*
     * The amount of hits given.
     */
	private int hitsGiven = 0;
	
	/*
     * The amount of damage taken by the player.
     */
	private int totalDamageTaken = 0;
	
	/*
     * The amount of damage done by the player.
     */
	private int totalDamageDone = 0;
	
	/*
     * The day on which the battle is being done.
     */
	private int currentDay;
	
	/*
     * This is the battle difficulty.
     */
	private double difficulty;
	

	/*
	 * Gets the current value of battleOver. If either the
	 * player or the enemy has no monsters remaining who have not fainted,
	 * will return true, else false.
	 * 
	 * @return The value of battleOver.
	 */
	public boolean getBattleOver() 
	{
		return battleOver;
	}

	/*
	 * Sets the value of battleOver.
	 * 
	 * @param battleOver Whether the battle is over or not.
	 */
	public void setBattleOver(boolean battleOver)
	{
		this.battleOver = battleOver;
	}

	/*
	 * Gets whether or not the player won the battle.
	 * If the player won, will return true, else false.
	 * 
	 * @return The value of playerWon
	 */
	public boolean getPlayerWon() 
	{
		return playerWon;
	}

	/*
	 * Sets the value of playerWon.
	 * 
	 * @param playerWon Whether the player won or not.
	 */
	public void setPlayerWon(boolean playerWon)
	{
		this.playerWon = playerWon;
	}

	/*
	 * Gets the gold gained from the battle.
	 * 
	 * @return The amount of gold.
	 */
	public int getBattleGold()
	{
		return battleGold;
	}

	/*
	 * Sets the gold gained from the battle. This is dependent on
	 * difficulty settings, and damage dealt during the battle.
	 * 
	 * @param battleGold The amount of gold given from the battle.
	 */
	public void setBattleGold(int battleGold)
	{
		this.battleGold = battleGold;
	}

	/*
	 * Gets the amount of experience points gained
	 * from the battle. 1 gained per enemy monster defeated.
	 * 
	 * @return Returns the number of experience points gained total over the battle.
	 */
	public int getExperiencePoints()
	{
		return experiencePoints;
	}

	/*
	 * Sets the amount of experience points gained
	 * from the battle.
	 * 
	 * @param experiencePoints The number of experience points.
	 */
	public void setExperiencePoints(int experiencePoints)
	{
		this.experiencePoints = experiencePoints;
	}


	/*
	 * Gets the new update message from the battle.
	 * 
	 * @return The update to give to the player.
	 */
	public String getBattleUpdate() 
	{
		return battleUpdate;
	}

	
	/*
	 * Sets the new update message for the battle.
	 * 
	 * @param battleUpdate The update to give to the player.
	 */
	public void setBattleUpdate(String battleUpdate)
	{
		this.battleUpdate = battleUpdate;
	}

	/*
	 * Gets the last player attack update.
	 * 
	 * @return Returns the value of last update.
	 */
	public String getLastUpdate()
	{
		return lastUpdate;
	}

	/*
	 * Sets the last player attack update.
	 * 
	 * @param lastUpdate The current value of lastUpdate.
	 */
	public void setLastUpdate(String lastUpdate)
	{
		this.lastUpdate = lastUpdate;

	}
    
	/*
	 * Gets the battle enemy.
	 * 
	 * @return Returns enemy.
	 */
	public Enemy getEnemy() 
	{
		return enemy;
	}

	/*
	 * Sets the battle enemy.
	 * 
	 * @param enemy The enemy.
	 */
	public void setEnemy(Enemy enemy) 
	{
		this.enemy = enemy;
	}
	
	/*
	 * Gets the player.
	 * 
	 * @return Returns the player.
	 */
	public Player getPlayer()
	{
		return player;
	}

	/*
	 * Gets the current day
	 */
	public int getCurrentDay() 
	{
		return currentDay;
	}

	/*
	 * Sets the current day.
	 * 
	 * @param currentDay The current day in game.
	 */
	public void setCurrentDay(int currentDay) 
	{
		this.currentDay = currentDay;
	}

	/*
	 * Gets the difficulty.
	 * 
	 * 
	 * @return Returns the difficulty.
	 */
	public double getDifficulty() 
	{
		return difficulty;
	}

	/*
	 * Sets the difficulty
	 * 
	 * @param difficulty The difficulty.
	 */
	public void setDifficutly(double difficulty)
	{
		this.difficulty = difficulty;
	}


	/*
	 * Updates the player on what is occurring in the current battle.
	 */
	private String battleUpdate;

	/*
	 * Updates the player on what is happening in the game via a message in the 
	 * form of a string.
	 */
	private String lastUpdate;

	
	/*
	 * Gets the possible battles from an ArrayList
	 * 
	 *@return Returns the possible battles.
	 */
	public ArrayList<Enemy> getBattles()
	{
		return this.battles;
	}
	
	
	/*
	 * Creates the player instance.
	 * 
	 * @param player An instance of Player.
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	
	/*
	 *  Generates the necessary set up for the battle to occur.
	 */
	public void generateBattles()
	{
		this.battles.clear();     // clearing all battles if player sleeps to refresh.
		
		boolean generationComplete = false;
		
		int upperbound = 3;
		int battleOptions;
		
		// The battles are generated somewhat randomly, but largely influenced by the current day.
		
		int currentDay = this.currentDay;
		
		if (currentDay <= 5)
		{
			 battleOptions = 3 + random.nextInt(upperbound-2); // getting a battle size of 3 members only
		}
		else if (currentDay > 5 && currentDay <= 10)
		{
			 battleOptions = 3 + random.nextInt(upperbound-1); // getting a battle size between 3 and 4 members
		}
		else
		{
			 battleOptions = 4 + random.nextInt(upperbound); // getting a battle size between 4 and 5 members
		}
		
		
		ArrayList<String> selectedNames = new ArrayList<String>();
		
		while (generationComplete == false)
		{
			if (this.battles.size() == battleOptions)
			{
				generationComplete = true;
			}
			else
			{
				Enemy newEnemy = new Enemy();        // Generating a distinct enemy name
				String enemyName = newEnemy.generateEnemyName();
				while (selectedNames.contains(enemyName))
				{
					enemyName = newEnemy.generateEnemyName();
				}
				selectedNames.add(enemyName);
				newEnemy.setEnemyName(enemyName);
				newEnemy.setDifficulty(this.difficulty);
				newEnemy.generateEnemyTeam();
				this.battles.add(newEnemy);
			}
		}
	}
	


	/*
	 * Starts the battle, calls methods to make player and 
	 * enemy moves, ends the battle.
	 *
	 * 
	 * @param enemy An instance of Enemy.
	 */
	public void fight(Enemy enemy)
	{
		System.out.print("\nFight has begun!\n\n");
		while (this.battleOver == false)
		{
			//this.enemyMove(enemy);
		}
		
		if (this.playerWon == true)
		{
			System.out.print("\nAll enemy monsters have fainted!\n");
			System.out.print("You have won the battle!\n");
			this.player.setPlayerGold(this.player.getPlayerGold() + this.battleGold);
			System.out.printf("You have received %d gold and %d points.\n", this.battleGold, this.experiencePoints);	
		}
		else
		{
			System.out.print("\nAll your monsters have fainted!\n");
			System.out.print("You have lost the battle!\n");
			System.out.print("You do not receive any gold or points.\n");
		}
		
		enemy.setAlreadyFought(true);
		this.battleOver = false;
	}


	
	/*
	 * Makes the player attack.
	 * 
	 * @param playerMonster The monster to attack.
	 * @param enemyMonster The monster that is being attacked.
	 * @param attackType The attack that the monster is using.
	 */
	public void playerAttack(Monster playerMonster, Monster enemyMonster, String attackType)
	{
		this.battleUpdate = "";
		double difficulty = this.difficulty;
		String update = "";
			
		if (attackType == "Attack")
		{
			update += ("Your %s used %s!\n".formatted(playerMonster.pickMonsterName(), playerMonster.getAttackName()));
			if (enemyMonster.getCurrentHealth() - playerMonster.getDamage() > 0)
			{
				enemyMonster.setCurrentHealth(enemyMonster.getCurrentHealth() - playerMonster.getDamage());
				this.battleGold += (int)(playerMonster.getDamage() - (difficulty * 10));
			}
			else
			{
				update += ("Effective! Enemy monster has fainted!\n");
				this.experiencePoints += 1 + difficulty;
				enemyMonster.setCurrentHealth(0);
				enemyMonster.setFaint(true);
			}
			playerMonster.setAttackCount(playerMonster.getAttackCount()+1);
			this.totalDamageDone += playerMonster.getDamage();
		} 
		
		else
		{
			if (playerMonster.getSpecialAttackAvailable() == true)
			{
				update += ("Your %s used %s!\n".formatted(playerMonster.pickMonsterName(), playerMonster.getSpecialAttackName()));
				if (enemyMonster.getCurrentHealth() - playerMonster.getSpecialDamage() > 0)
				{
					enemyMonster.setCurrentHealth(enemyMonster.getCurrentHealth() - playerMonster.getSpecialDamage());
					this.battleGold += (int)(playerMonster.getSpecialDamage() - (difficulty * 10));
				}
				else
				{
					update += ("Effective! Enemy monster has fainted!\n");
					this.experiencePoints += 1 + difficulty;
					enemyMonster.setCurrentHealth(0);
					enemyMonster.setFaint(true);
				}
			}
			playerMonster.setAttackCount(0);
			this.totalDamageDone += playerMonster.getSpecialDamage();
		}
		
		this.setBattleUpdate(this.getBattleUpdate() + update);
		this.hitsGiven += 1;
	}
	
	
	
	/*
	 * Simulates the enemy attack turn.
	 * 
	 * @param playerMonster The monster the enemy is attacking.
	 * @param enemyMonster The monster attacking.
	 */
	public void enemyAttack(Monster playerMonster, Monster enemyMonster)
	{
	
		double difficulty = this.difficulty;
		String update = "";
		
		if (enemyMonster.getSpecialAttackAvailable() == true)
		{
		
			update += ("\nEnemy %s used %s!\n".formatted(enemyMonster.getMonsterName(), enemyMonster.getSpecialAttackName()));
			if (playerMonster.getCurrentHealth() - (int)(enemyMonster.getSpecialDamage() + difficulty) > 0)
			{
				playerMonster.setCurrentHealth(playerMonster.getCurrentHealth() - (int)(enemyMonster.getSpecialDamage() + difficulty));
			}
			else
			{
				update += "OH NO! Your monster had fainted!\n";
				playerMonster.setCurrentHealth(0);
				playerMonster.setFaint(true);
			}	
			enemyMonster.setAttackCount(0);
			this.totalDamageTaken += enemyMonster.getSpecialDamage();
		}
		else
		{
			update += ("\nEnemy %s used %s!\n".formatted(enemyMonster.pickMonsterName(), enemyMonster.getAttackName()));
			if (playerMonster.getCurrentHealth() - (int)(playerMonster.getDamage() + difficulty) > 0)
			{
				playerMonster.setCurrentHealth(playerMonster.getCurrentHealth() - (int)(playerMonster.getDamage() + difficulty));
			}
			else
			{
				update += "OH NO! Your monster has fainted!\n";
				playerMonster.setCurrentHealth(0);
				playerMonster.setFaint(true);
			}
			enemyMonster.setAttackCount(enemyMonster.getAttackCount()+1);
			this.totalDamageTaken += enemyMonster.getDamage();
		}
		
		this.setBattleUpdate(this.getBattleUpdate() + update);
		this.hitsTaken += 1;
	}
	
	
	
	/*
	 * Gives the battle statistics after the battle has concluded.
	 */
	public String getBattleStats()
	{
		String a;
		String a1;
		if (this.playerWon == false)
		{
			a1 = "\nYou have lost the battle!\n";
			a = "Battle Winner: %s\n\n".formatted(this.enemy.getEnemyName());
		}
		else
		{
			a1 = "You have won the battle!\n";
			a = "Battle Winner: %s\n\n".formatted(this.player.getGame().getPlayerName());
		}
		
		String b = "Your Battle Gold Earned: %d\n".formatted(this.battleGold);
		String c = "Your XP Earned: %d\n".formatted(this.experiencePoints);
		String d = "Your Hits taken: %d\n".formatted(this.hitsTaken);
		String e = "Your Hits given: %d\n".formatted(this.hitsGiven);
		String f = "Your Total Damage Taken: %d\n".formatted(this.totalDamageTaken);
		String g = "Your Total Damage Done: %d\n".formatted(this.totalDamageDone);
		
		return (a1 + a + b + c + d + e + f + g);
	}
}
