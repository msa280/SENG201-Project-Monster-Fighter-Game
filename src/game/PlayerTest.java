package game;

import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

import items.CursedSkull;
import items.Item;
import monsters.Magmataur;
import monsters.Monster;
import monsters.Venomhound;

class PlayerTest
{
	Player player = new Player();

	@Test
	/*
	 * testing the getters and setters
	 */
	void test1() 
	{
		Game game = new Game();
		game.setGameLength(10);
		game.setGameDifficulty(0.5);
		player.setGame(game);

		player.setPlayerGold(7000);
		System.out.print(player.getPlayerGold() + "\n");
		
		player.setCurrentDay(1);
		System.out.print(player.getCurrentDay());
		
		player.setDaysRemaining();
		System.out.print(player.getDaysRemaining() + "\n");
		
		
		Shop shop = new Shop();
		player.setShop(shop);
		
		Shop check = player.getShop();
		check.generateAllItems();
		
		player.getGame();
		player.getPlayerInventory();
		
		Battle battle = new Battle();
		player.setBattle(battle);
		player.getBattle();
		
		player.getPlayerDifficulty();
		player.addToTeam(new Venomhound());
		player.getPlayersTeamLength();
		player.getPlayerMonsters();
		
		player.setReadyForBattle(true);
		player.isReadyForBattle();
		
		player.setLastUpdate("yoyo");
		player.getLastUpdate();
		
		player.setSelectedAvatar("something");
		player.getSelectedAvatar();

		player.setTotalGoldGained(1000);
		player.getTotalGoldGained();
		
		player.setBattlesWon(1);
		player.getBattlesWon();
	}
	
	
	
	@Test
	/*
	 * testing the getters and setters
	 */
	void test2() 
	{
		Game game = new Game();
		game.setGameLength(10);
		game.setGameDifficulty(0.5);
		game.setSelectedMonster(new Venomhound());
		
		player.initialize(game);
		
		player.canFight();
		
		player.buyItem(new CursedSkull());
		player.buyItem(new CursedSkull());
		
		player.buyMonster(new Magmataur());
		
		for (Monster monster: player.getPlayerMonsters())
		{
			player.sellMonster(monster);
		}
		
		player.buyMonster(new Magmataur());

		player.playerSleep();
		
		System.out.print(player.getGameOverResult());
		
		for (Entry<Item, Integer> item : player.getPlayerInventory().entrySet())
		{
			player.useItem(item.getKey(), player.getPlayerMonsters().get(0));
			
		}
		
		
		
		
	

	}
	
	
	
	
	
	
	
	

	
	

}
