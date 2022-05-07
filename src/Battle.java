import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

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
	 * Players input for what move they want to use.
	 */
	private Scanner scanInput = new Scanner(System.in);
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
			this.playerMove(enemy);
			this.enemyMove(enemy);
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
		
		this.player.viewTeamCondition();
		enemy.setAlreadyFought(true);
		this.battleOver = false;
	}
	
	
	
	/*
	 * Allows the player to have their turn.
	 * 
	 * @param enemy An instance of Enemy.
	 */
	public void playerMove(Enemy enemy)
	{
		Monster playerFighter = this.getPlayerCurrentFighter();
		Monster enemyFighter = this.getEnemyCurrentFighter(enemy);
		
		if (playerFighter == null)
		{
			this.playerWon = false;
			this.battleOver = true;
		}
		else if (enemyFighter == null)
		{
			this.playerWon = true;
			this.battleOver = true;
		}
		
		else
		{
			boolean playerTurnDone = false;
			double difficulty = this.player.getPlayerDifficulty();
			
			while (playerTurnDone == false)
			{
				this.displayBattle(playerFighter, enemyFighter);
				System.out.printf("\nChoose an attack for %s:\n", playerFighter.pickMonsterName());
				System.out.printf("\n1) Name: %s  |  Damage: %d\n", playerFighter.getAttackName(), playerFighter.getDamage());
				System.out.printf("2) Name: %s  |  Damage: %d\n", playerFighter.getSpecialAttackName(), playerFighter.getSpecialDamage());

				try 
				{
					int selection = scanInput.nextInt();
					if (selection == 1)
					{
						System.out.printf("\n%s used %s!\n", playerFighter.pickMonsterName(), playerFighter.getAttackName());
						if (enemyFighter.getCurrentHealth() - playerFighter.getDamage() > 0)
						{
							enemyFighter.setCurrentHealth(enemyFighter.getCurrentHealth() - playerFighter.getDamage());
							this.battleGold += (int)(playerFighter.getDamage() * difficulty);
						}
						else
						{
							System.out.print("\n Effective! Enemy monster has fainted!\n");
							this.experiencePoints += 1;
							enemyFighter.setCurrentHealth(0);
							enemyFighter.setFaint(true);
						}
						playerFighter.setAttackCount(playerFighter.getAttackCount()+1);
						playerTurnDone = true;
					} 
					else if (selection == 2)
					{
						if (playerFighter.specialAttackAvailable() == true)
						{
							System.out.printf("\n%s used %s!\n", playerFighter.pickMonsterName(), playerFighter.getSpecialAttackName());
							if (enemyFighter.getCurrentHealth() - playerFighter.getSpecialDamage() > 0)
							{
								enemyFighter.setCurrentHealth(enemyFighter.getCurrentHealth() - playerFighter.getSpecialDamage());
								this.battleGold += (int)(playerFighter.getSpecialDamage() * difficulty);
							}
							else
							{
								System.out.print("\nEffective! Enemy monster has fainted!\n");
								this.experiencePoints += 1;
								enemyFighter.setCurrentHealth(0);
								enemyFighter.setFaint(true);
							}
							playerFighter.setAttackCount(0);
							playerTurnDone = true;
						}
						else
						{
							System.out.print("Special Attack hasn't generated yet!\n");
						}	
					}
					else
					{
						System.out.print("\nPlease select a valid option.\n");
					}	
				} 
				catch (InputMismatchException excp) 
				{
					System.out.print("Please enter a valid name or option.\n");
					scanInput.next();
					continue;
				}
			}
		}
	}
	
	
	
	public void enemyMove(Enemy enemy)
	{
		Monster playerFighter = this.getPlayerCurrentFighter();
		Monster enemyFighter = this.getEnemyCurrentFighter(enemy);
		double difficulty = this.player.getPlayerDifficulty();
		
		if (playerFighter == null)
		{
			this.playerWon = false;
			this.battleOver = true;
		}
		else if (enemyFighter == null)
		{
			this.playerWon = true;
			this.battleOver = true;
		}
		
		else if (enemyFighter.specialAttackAvailable() == true)
		{
			this.displayBattle(playerFighter, enemyFighter);
			System.out.printf("\n%s used %s!\n", enemyFighter.pickMonsterName(), enemyFighter.getSpecialAttackName());
			if (playerFighter.getCurrentHealth() - (int)(enemyFighter.getSpecialDamage() * difficulty) > 0)
			{
				playerFighter.setCurrentHealth(playerFighter.getCurrentHealth() - (int)(enemyFighter.getSpecialDamage() * difficulty));
			}
			else
			{
				System.out.print("\nOH NO! Your monster has fainted!\n");
				playerFighter.setCurrentHealth(0);
				playerFighter.setFaint(true);
			}	
			enemyFighter.setAttackCount(0);
		}
		else
		{
			this.displayBattle(playerFighter, enemyFighter);
			System.out.printf("\n%s used %s!\n", enemyFighter.pickMonsterName(), enemyFighter.getAttackName());
			if (playerFighter.getCurrentHealth() - (int)(playerFighter.getDamage() * difficulty) > 0)
			{
				playerFighter.setCurrentHealth(playerFighter.getCurrentHealth() - (int)(playerFighter.getDamage() * difficulty));
			}
			else
			{
				System.out.print("\nOH NO! Your monster has fainted!\n");
				playerFighter.setCurrentHealth(0);
				playerFighter.setFaint(true);
			}
			enemyFighter.setAttackCount(enemyFighter.getAttackCount()+1);
		}
		
		
		
	}
	
	
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
	
	
	
	public void displayBattle(Monster playerFighter, Monster enemyFighter)
	{
		System.out.printf("\nName: %s                  Name: %s\n", playerFighter.pickMonsterName(), enemyFighter.pickMonsterName());
		System.out.printf("Health: %d                           Health: %d\n", playerFighter.getCurrentHealth(), enemyFighter.getCurrentHealth());
	}
	
	
	
	public boolean getBattleOver() {
		return battleOver;
	}


	public void setBattleOver(boolean battleOver) {
		this.battleOver = battleOver;
	}


	public boolean getPlayerWon() {
		return playerWon;
	}


	public void setPlayerWon(boolean playerWon) {
		this.playerWon = playerWon;
	}


	public int getBattleGold() {
		return battleGold;
	}


	public void setBattleGold(int battleGold) {
		this.battleGold = battleGold;
	}


	public int getExperiencePoints() {
		return experiencePoints;
	}


	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}

}
