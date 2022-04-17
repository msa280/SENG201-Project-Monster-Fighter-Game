import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import monsters.Monster;



public class Battle {
	

	private ArrayList<Enemy> battles = new ArrayList<Enemy>();
	private Player player;
	private Random random = new Random();
	private Scanner scan_input = new Scanner(System.in);
	
	
	public ArrayList <Enemy> getBattles()
	{
		return this.battles;
	}
	
	
	public void setPlayer(Player new_player)
	{
		this.player = new_player;
	}
	
	
	
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
				Enemy new_enemy = new Enemy();        // Generating a distinct enemy name
				String enemy_name = new_enemy.generateEnemyName();
				while (selectedNames.contains(enemy_name))
				{
					enemy_name = new_enemy.generateEnemyName();
				}
				selectedNames.add(enemy_name);
				new_enemy.setEnemyName(enemy_name);
				new_enemy.generateEnemyTeam();
				this.battles.add(new_enemy);
			}
		}
	}
	
	
	
	
	public void fight(Enemy enemy)
	{
		int upperbound = 2;
		int playerTurn = random.nextInt(upperbound); // getting a random size of team between 3 and 5.
		boolean fightOver = false;
		
		System.out.print("\n Fight has begun!\n\n");
		
		if (playerTurn == 0)
		{
			System.out.print("Enemy got the first turn!\n");
			this.enemyMove(this.player);
		}
		else
		{
			System.out.print("You got the first turn!\n");
			this.playerMove(enemy);
		}
		
		while (fightOver == false)
		{
			break;
		}
		
		
		
	}
	
	
	public void playerMove(Enemy enemy)
	{
		Monster currentFighter = this.getPlayerCurrentFighter();
		Monster enemyFighter = this.getEnemyCurrentFighter(enemy);
		
		if (currentFighter == null)
		{
			System.out.print("\nAll your monsters have fainted!\n");
			System.out.print("You have lost the battle!\n");
			System.out.print("You do not recieve any gold or points.\n");
			return;
		}
		else if (enemyFighter == null)
		{
			System.out.print("\nAll enemy monsters have fainted!\n");
			System.out.print("You have won the battle!\n");
			System.out.printf("You have recieved %d gold and %d points.\n");
			//this.player.addgold
			return;
		}
		else
		{
			boolean playerTurnDone = false;
			while (playerTurnDone == false)
			{
				System.out.printf("\nChoose an attack for %s:\n", currentFighter.pickMonsterName());
				System.out.printf("\n1) Name: %s  |  Damage: %d\n", currentFighter.getAttackName(), currentFighter.getDamage());
				System.out.printf("2) Name: %s  |  Damage: %d\n", currentFighter.getSpecial_attackName(), currentFighter.getSpecialDamage());

				try 
				{
					int selection = scan_input.nextInt();
					if (selection == 1)
					{
						System.out.printf("\n%s used %s!\n", currentFighter.pickMonsterName(), currentFighter.getAttackName());
						if (enemyFighter.getCurrentHealth() - currentFighter.getDamage() > 0)
						{
							enemyFighter.setCurrentHealth(enemyFighter.getCurrentHealth() - currentFighter.getDamage());
						}
						else
						{
							System.out.print("\n Effective! Enemy monster has fainted!\n");
							enemyFighter.setCurrentHealth(0);
							enemyFighter.setFaint(true);
						}
						playerTurnDone = true;
					} 
					else if (selection == 2)
					{
						System.out.printf("\n%s used %s!\n", currentFighter.pickMonsterName(), currentFighter.getSpecial_attackName());
						if (enemyFighter.getCurrentHealth() - currentFighter.getSpecialDamage() > 0)
						{
							enemyFighter.setCurrentHealth(enemyFighter.getCurrentHealth() - currentFighter.getSpecialDamage());
						}
						else
						{
							System.out.print("\n Effective! Enemy monster has fainted!\n");
							enemyFighter.setCurrentHealth(0);
							enemyFighter.setFaint(true);
						}
						playerTurnDone = true;
					}
					else
					{
						System.out.print("\nPlease select a valid option.\n");
					}	
				} 
				catch (InputMismatchException excp) 
				{
					System.out.print("Please enter a valid name or option.\n");
					scan_input.next();
					continue;
				}
			}
		}
		
	}
	
	
	
	public void enemyMove(Player player)
	{
		
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
		for (Monster monster: this.player.get_playerMonsters())
		{
			if (monster.isFaint() == false)
			{
				currentMonster = monster;
				return currentMonster;
			}
		}
		return currentMonster;
	}
	
	
	
	public void displayBattle(Enemy enemy)
	{
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
