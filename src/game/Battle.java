package game;
import java.util.ArrayList;
import java.util.Random;


import monsters.Monster;

/**
 * Generates battles between the player and the enemy.
 */

public class Battle {
	
	/*
	 * The list of enemies the player will verse.
	 */
	private ArrayList<Enemy> battles = new ArrayList<Enemy>();
	/*
	 * The player.
	 */
	private Player player;
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
		int battleOptions = 3 + random.nextInt(upperbound); // getting a random size of team between 3 and 5.
		
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
	 * Allows the player to have their turn.
	 * 
	 * @param enemy An instance of Enemy.
	 */
	public void playerAttack(Monster playerMonster, Monster enemyMonster, String attackType)
	{
		this.battleUpdate = "";
		double difficulty = this.player.getPlayerDifficulty();
		String update = "";
			
		if (attackType == "Attack")
		{
			update += ("Your %s used %s!\n".formatted(playerMonster.pickMonsterName(), playerMonster.getAttackName()));
			if (enemyMonster.getCurrentHealth() - playerMonster.getDamage() > 0)
			{
				enemyMonster.setCurrentHealth(enemyMonster.getCurrentHealth() - playerMonster.getDamage());
				this.battleGold += (int)(playerMonster.getDamage() * difficulty);
			}
			else
			{
				update += ("Effective! Enemy monster has fainted!\n");
				this.experiencePoints += 1;
				enemyMonster.setCurrentHealth(0);
				enemyMonster.setFaint(true);
			}
			playerMonster.setAttackCount(playerMonster.getAttackCount()+1);
		} 
		
		else
		{
			if (playerMonster.getSpecialAttackAvailable() == true)
			{
				update += ("Your %s used %s!\n".formatted(playerMonster.pickMonsterName(), playerMonster.getSpecialAttackName()));
				if (enemyMonster.getCurrentHealth() - playerMonster.getSpecialDamage() > 0)
				{
					enemyMonster.setCurrentHealth(enemyMonster.getCurrentHealth() - playerMonster.getSpecialDamage());
					this.battleGold += (int)(playerMonster.getSpecialDamage() * difficulty);
				}
				else
				{
					update += ("Effective! Enemy monster has fainted!\n");
					this.experiencePoints += 1;
					enemyMonster.setCurrentHealth(0);
					enemyMonster.setFaint(true);
				}
			}
			playerMonster.setAttackCount(0);
		}
		
		this.setBattleUpdate(this.getBattleUpdate() + update);
	}
	
	/*
	 * Simulates the enemy turn
	 * 
	 * @param enemy An instance of Enemy.
	 */
	public void enemyAttack(Monster playerMonster, Monster enemyMonster)
	{
	
		double difficulty = this.player.getPlayerDifficulty();
		String update = "";
		
		if (enemyMonster.getSpecialAttackAvailable() == true)
		{
		
			update += ("\nEnemy %s used %s!\n".formatted(enemyMonster.getMonsterName(), enemyMonster.getSpecialAttackName()));
			if (playerMonster.getCurrentHealth() - (int)(enemyMonster.getSpecialDamage() * difficulty) > 0)
			{
				playerMonster.setCurrentHealth(playerMonster.getCurrentHealth() - (int)(enemyMonster.getSpecialDamage() * difficulty));
			}
			else
			{
				update += "OH NO! Your monster had fainted!\n";
				playerMonster.setCurrentHealth(0);
				playerMonster.setFaint(true);
			}	
			enemyMonster.setAttackCount(0);
		}
		else
		{
			update += ("\nEnemy %s used %s!\n".formatted(enemyMonster.pickMonsterName(), enemyMonster.getAttackName()));
			if (playerMonster.getCurrentHealth() - (int)(playerMonster.getDamage() * difficulty) > 0)
			{
				playerMonster.setCurrentHealth(playerMonster.getCurrentHealth() - (int)(playerMonster.getDamage() * difficulty));
			}
			else
			{
				update += "OH NO! Your monster has fainted!\n";
				playerMonster.setCurrentHealth(0);
				playerMonster.setFaint(true);
			}
			enemyMonster.setAttackCount(enemyMonster.getAttackCount()+1);
		}
		
		this.setBattleUpdate(this.getBattleUpdate() + update);
		
		

	}
	
	/*
	 * Gets the first monster in the enemies monster list (their current fighter).
	 * 
	 * @param enemy An instance of Enemy.
	 * @return Returns the first monster in the enemies monster list.
	 */
	public Monster getEnemyCurrentFighter(Enemy enemy)
	{
		Monster currentMonster = null;
		for (Monster monster: enemy.getEnemyTeam())
		{
			if (monster.isFaint() == false)
			{
				currentMonster = monster;
				return currentMonster;
			}
		}
		return currentMonster;
	}
	
	/*
	 * Gets the first monster in the players monster list (their current fighter).
	 * 
	 * @return Returns the first monster in the players monster list.
	 */
	public Monster getPlayerCurrentFighter()
	{
		Monster currentMonster = null;
		for (Monster monster: this.player.getPlayerMonsters())
		{
			if (monster.isFaint() == false)
			{
				currentMonster = monster;
				return currentMonster;
			}
		}
		return currentMonster;
	}
	
	/*
	 * The visual interface that displays what's happening in the battle.
	 * 
	 * @param playerFighter The players current monster that is fighting.
	 * @param enemyFighter The enemies current monster that is fighting.
	 */
	public void displayBattle(Monster playerFighter, Monster enemyFighter)
	{
		System.out.printf("\nName: %s                  Name: %s\n", playerFighter.pickMonsterName(), enemyFighter.pickMonsterName());
		System.out.printf("Health: %d                           Health: %d\n", playerFighter.getCurrentHealth(), enemyFighter.getCurrentHealth());
	}
	
	/*
	 * Gets the current value of battleOver. If either the
	 * player or the enemy has no monsters remaining who have not fainted,
	 * will return true, else false.
	 * 
	 * @return The value of battleOver.
	 */
	public boolean getBattleOver() {
		return battleOver;
	}

	/*
	 * Sets the value of battleOver.
	 * 
	 * @param battleOver Whether the battle is over or not.
	 */
	public void setBattleOver(boolean battleOver) {
		this.battleOver = battleOver;
	}

	/*
	 * Gets whether or not the player won the battle.
	 * If the player won, will return true, else false.
	 * 
	 * @return The value of playerWon
	 */
	public boolean getPlayerWon() {
		return playerWon;
	}

	/*
	 * Sets the value of playerWon.
	 * 
	 * @param playerWon Whether the player won or not.
	 */
	public void setPlayerWon(boolean playerWon) {
		this.playerWon = playerWon;
	}

	/*
	 * Gets the gold gained from the battle.
	 * 
	 * @return battleGold The amount of gold.
	 */
	public int getBattleGold() {
		return battleGold;
	}

	/*
	 * Sets the gold gained from the battle. This is dependent on
	 * difficulty settings, and damage dealt during the battle.
	 * 
	 * @param battleGold The amount of gold given from the battle.
	 */
	public void setBattleGold(int battleGold) {
		this.battleGold = battleGold;
	}

	/*
	 * Gets the amount of experience points gained
	 * from the battle. 1 gained per enemy monster defeated.
	 * 
	 * @return experiencePoints Returns the number of experience points gained total over the battle.
	 */
	public int getExperiencePoints() {
		return experiencePoints;
	}

	/*
	 * Sets the amount of experience points gained
	 * from the battle.
	 * 
	 * @param experiencePoints The number of experience points.
	 */
	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}


	public String getBattleUpdate() {
		return battleUpdate;
	}

	public void setBattleUpdate(String battleUpdate) {
		this.battleUpdate = battleUpdate;
	}

	/*
	 * Gets the last player attack update.
	 * 
	 * @return lastUpdate Returns the value of last update.
	 */
	public String getLastUpdate() {
		return lastUpdate;
	}

	/*
	 * Sets the last player attack update.
	 * 
	 * @param lastUpdate The current value of lastUpdate.
	 */
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;

	}

}
