package game;

import org.junit.jupiter.api.Test;

import monsters.Venomhound;

class GameTest {
	
	Game game = new Game();

	@Test
	/*
	 * Tests the getters and setters and the display settings method.;
	 */
	void test1() 
	{
		game.setSetupComplete(true);
		System.out.print(game.getSetupComplete() + "\n");
		
		game.setPlayerName("Haider");
		System.out.print(game.getPlayerName()+ "\n");
		
		game.setGameLength(7);
		System.out.print(game.getGameLength()+ "\n");
		
		game.setGameDifficulty(1.3);
		System.out.print(game.getGameDifficulty()+ "\n");
		
		game.setGameOver(true);
		System.out.print(game.getGameOver()+ "\n");
		
		game.setSelectedMonster(new Venomhound());
		System.out.print(game.getSelectedMonster().getMonsterName()+ "\n");
		
		System.out.print(game.displaySettings());
	}
	
	@Test
	/*
	 * Tests the "checkNameErrors" function
	 */
	void test2()
	{
		System.out.print(game.checkNameErrors(false, 6, "Haider") + "\n");
		
		System.out.print(game.checkNameErrors(false, 2, "Ha") + "\n");
		
		System.out.print(game.checkNameErrors(false, 18, "HaiderJakibProject") + "\n");
		
		System.out.print(game.checkNameErrors(true, 6, "Haider7") + "\n");
		
		System.out.print(game.checkNameErrors(true, 9, "Haider!@#") + "\n");
		
		System.out.print(game.checkNameErrors(true, 7, "Haider S") + "\n");
	}
	
	
	
	@Test
	/*
	 * Tests the "checkPlayerName" function to see if the player is correct or not.
	 */
	void test3()
	{
		System.out.print(game.checkPlayerName("Haider S") + "\n");
		
		System.out.print(game.checkPlayerName("Haider7") + "\n");
		
		System.out.print(game.checkPlayerName("Haider@") + "\n");
		
		System.out.print(game.checkPlayerName("Haider") + "\n");
	}
	
	
	@Test
	/*
	 * Tests the "chooseGameDifficulty" function to find an accurate difficulty level.
	 */
	void test4()
	{
		game.chooseGameDifficulty("Easy");
		System.out.print(game.getGameDifficulty() + "\n");
		
		game.chooseGameDifficulty("Normal");
		System.out.print(game.getGameDifficulty() + "\n");
		
		game.chooseGameDifficulty("Classic");
		System.out.print(game.getGameDifficulty() + "\n");
		
		game.chooseGameDifficulty("Hard");
		System.out.print(game.getGameDifficulty() + "\n");
		
		game.chooseGameDifficulty("Boss");
		System.out.print(game.getGameDifficulty() + "\n");
	}
	
	
	
	@Test
	/*
	 * Tests the "finishSetup" function.
	 */
	void test5()
	{
		game.setSelectedMonster(new Venomhound());
		game.finishSetup("Amber Heard", 8, "Easy");
		
		System.out.print(game.getSelectedMonster().getMonsterRename() + "\n");
		
		System.out.print(game.getGameLength() + "\n");
		
		System.out.print(game.getGameDifficulty() + "\n");
	}
	
	

}
