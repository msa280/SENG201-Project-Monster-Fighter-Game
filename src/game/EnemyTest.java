package game;


import org.junit.jupiter.api.Test;

import monsters.Monster;

class EnemyTest
{
	Enemy enemy = new Enemy();

	@Test
	/*
	 * Testing getter and setters.
	 */
	void test1() 
	{
		enemy.setDifficulty(1.5);
		
		enemy.setEnemyFightAvailability(false);
		System.out.print(enemy.getEnemyFightAvailability() + "\n");
		
		enemy.setEnemyName("Angela");
		System.out.print(enemy.getEnemyName() + "\n");
		
		enemy.setAlreadyFought(true);
		System.out.print(enemy.getAlreadyFought() + "\n");
		
		System.out.print(enemy.getEnemyName() + "\n" + enemy.getEnemyImage() + "\n");
	}
	
	
	@Test
	/*
	 * Testing enemy generation functions.
	 */
	void test2() 
	{
		enemy.generateEnemyMonsters();
		
		System.out.print(enemy.generateEnemyName() + "\n");
		
		enemy.generateEnemyTeam();
		
		for (Monster monster: enemy.getEnemyTeam())
		{
			System.out.print("Enemy has %s.\n".formatted(monster.getMonsterName()));
			monster.setFaint(true);
		}
		
		System.out.print(enemy.canFight());
		
		
	}
	
	

	

}
