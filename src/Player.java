import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import items.Item;
import monsters.Cavernfreak;
import monsters.Hollowtree;
import monsters.Monster;
import monsters.Mornpest;
import monsters.Soilscreamer;
import monsters.Venomhound;


public class Player {
	
	private Scanner scanInput = new Scanner(System.in);
	private int playerGold = 0;
	private Map<Item, Integer> playerInventory = new HashMap<Item, Integer>();
	private ArrayList<Item> playersItems = new ArrayList<Item>();
	private ArrayList<Monster> playersTeam = new ArrayList<Monster>();
	private Monster startingMonsters[] = new Monster[5];
	private Battle battle;
	private Shop shop;
	private Game game;
	private int currentDay;
	private int daysRemaining;
	private boolean readyForBattle;
	
	
	public int getPlayerGold() {
		return playerGold;
	}

	public void setPlayerGold(int playerGold) {
		this.playerGold = playerGold;
	}
	
	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}
	
	public int getCurrentDay() {
		return currentDay;
	}
	
	public void setDaysRemaining(int currentDay, Game game) {
		this.daysRemaining = game.getGameLength() - currentDay;
	}
	
	public int getDaysRemaining() {
		return this.daysRemaining;
	}
	
	public int getPlayersTeamLength()
	{
		return this.playersTeam.size();
	}
	
	public void setGame(Game game)
	{
		this.game = game; 
	}
	
	public Game getGame()
	{
		return this.game;
	}
	
	public void setShop(Shop newShop)
	{
		this.shop = newShop;
	}
	
	public ArrayList<Item> getPlayerItems()
	{
		return this.playersItems;
	}
	
	public ArrayList<Monster> getPlayerMonsters()
	{
		return this.playersTeam;
	}
	
	public Map<Item, Integer> getPlayerInventory()
	{
		return this.playerInventory;
	}
	
	public Battle getBattle() {
		return battle;
	}

	public void setBattle(Battle battle) {
		this.battle = battle;
	}
	
	public double getPlayerDifficulty()
	{
		return this.game.getGameDifficulty();
	}
	
	
	
	
	public void buyItem(Item item)
	{
		if (this.playerGold < item.getPrice())
		{
			System.out.print("You don't have enough gold to buy this item\n");
			return;
		}
		else
		{
			this.playerGold -= item.getPrice();
		}
		
		if (this.playerInventory.containsKey(item))
		{
			this.playerInventory.put(item, this.playerInventory.get(item) + 1);
		}
		else
		{
			this.playerInventory.putIfAbsent(item, 1);
		}
		
		if (!this.playersItems.contains(item))
		{
			this.playersItems.add(item);
		}
		
		System.out.print("\nItem bought!\n");
		System.out.printf("\n%s has been added to the inventory.\n", item.getItemName());
	}
	
	
	public void sellItem(Item item)
	{
		System.out.print("\nItem sold!\n");
		this.playerGold += item.getResalePrice();
		
		for (Map.Entry<Item, Integer> inventoryItem : this.playerInventory.entrySet())
		{
			if (inventoryItem.getKey() == item)
			{
				if (inventoryItem.getValue() == 1)
				{
					this.playerInventory.remove(item);
					System.out.printf("\n%s has been removed from the inventory.\n", item.getItemName());
				}
				else
				{
					this.playerInventory.put(item, this.playerInventory.get(item) - 1);
					System.out.printf("\nYou have sold 1 %s.\n", item.getItemName());
				}
			}
		}
		System.out.printf("%d Gold has been given to you.\n", item.getResalePrice());
	}
	
	
	public void sellMonster(Monster monster)
	{
		this.playerGold += monster.getPrice();
		this.playersTeam.remove(monster);
		System.out.print("\nMonster sold!\n");
		System.out.printf("\n%s has been removed from the team.\n", monster.pickMonsterName());
		System.out.printf("%d Gold has been given to you.\n", monster.getPrice());
	}
	
	
	
	
	
	public void buyMonster(Monster monster, boolean rename)
	{
		if (this.playerGold < monster.getPrice())
		{
			System.out.print("You don't have enough gold to buy this monster\n");
		}
		else if (this.playersTeam.size() == 4)
		{
			System.out.print("You don't have enough space in your team to buy this monster\n");
		}
		else
		{
			this.playerGold -= monster.getPrice();
			System.out.print("\nMonster bought!\n");
			
			if (rename == true)
			{
				monster.askMonsterName();
			}
			this.playersTeam.add(monster);
			System.out.printf("\n%s has been added to the team.\n", monster.pickMonsterName());	
		}
	}
	
	
	
	
	
	public boolean[] monsterRenamer(boolean bought, boolean optionCorrect, int selection, Monster monster)
	{
		while (optionCorrect == false)
		{
			System.out.print("\nChoose a naming method for your monster: \n");
			System.out.print("\n1. Rename\n");
			System.out.print("2. Default Name\n");
			System.out.print("0. Go Back\n");

			try 
			{
				selection = scanInput.nextInt();
				if (selection == 1)
				{
					System.out.print("\nMonster bought!\n");
					this.playersTeam.add(monster);
					monster.askMonsterName();
					System.out.printf("%s has been added to the team.\n", monster.getMonsterRename());
					bought = true;
					optionCorrect = true;
				} 
				else if (selection == 2)
				{
					System.out.print("\nMonster bought!\n");
					this.playersTeam.add(monster);
					System.out.printf("%s has been added to the team.\n", monster.getMonsterName());
					bought = true;
					optionCorrect = true;
				}
				else
				{
					optionCorrect = true;
				}		
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("Please enter a valid name or option.\n");
				scanInput.next();
				continue;
			}
		}
		return new boolean[] {bought, optionCorrect};
	}
	
	
	public void viewTeamCondition()
	{
		System.out.print("Your team's current condition is:\n\n");
		int position = 1;
		for (Monster monster: this.playersTeam)
		{
			System.out.printf("%d) Monsterpod %d (Health: %d): %s\n", position, position, monster.getCurrentHealth(), monster.pickMonsterName());
		}
	}
	
	
	public boolean verifyMonsterPurchase(Monster monster)
	{
		boolean bought  = false;
		int selectedOption = 0;
		int selection = 0;
		
		while (bought == false)
		{
			System.out.print(monster.toString());
			System.out.print("\n Do you want to add this monster?\n");
			System.out.print("1. Yes\n");
			System.out.print("2. No\n");
			try 
			{
				selectedOption = scanInput.nextInt();
				if (selectedOption == 1)
				{
					boolean optionCorrect = false;
					boolean[] twoBools = null;
					twoBools = monsterRenamer(bought, optionCorrect, selection, monster);
					bought = twoBools[0];
					optionCorrect = twoBools[1];
				}
				else
				{
					return bought;
				}
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("Please enter a valid option number.\n");
				scanInput.next();
				continue;
			}
		}
		return bought;
	}
	

	
	
	public boolean executeMonsterSelection(Monster[] startingMonsters, boolean monsterSelected, int optionNumber)
	{
		Monster monster = null;
		boolean monsterBought = false;
		
		if (optionNumber >= 1 && optionNumber <= 5) 
		{
			monster = startingMonsters[optionNumber-1];
			monsterBought = verifyMonsterPurchase(monster);
		}
		else
		{
			monsterBought = false;
		}
	
		return monsterBought;
	}
	
	
	
	
	public void chooseStartingMonster()
	{
		startingMonsters[0] = new Cavernfreak();
		startingMonsters[1] = new Hollowtree();
		startingMonsters[2] = new Mornpest();
		startingMonsters[3] = new Soilscreamer();
		startingMonsters[4] = new Venomhound();
		
		boolean monsterSelected = false;
		int optionNumber = 0;
		
		while (monsterSelected == false)
		{
			System.out.print("\nPick a staring monster: \n");
			System.out.print("\n1. Cavernfreak\n");
			System.out.print("2. Hollowtree\n");
			System.out.print("3. Mornpest\n");
			System.out.print("4. Soilscreamer\n");
			System.out.print("5. Venomhound\n");
			
			try 
			{
				optionNumber = scanInput.nextInt();
				monsterSelected = executeMonsterSelection(startingMonsters, monsterSelected, optionNumber);
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("Please enter a valid option number.\n");
				scanInput.next();
				continue;
			}
		}
	}
	
	
	
	public boolean monsterStatOpener(boolean inTeamViewer, int optionNumber)
	{
		if (optionNumber > this.getPlayersTeamLength())
		{
			if (optionNumber < 5)
			{
				System.out.print("\nMonsterPod is empty.\n");
			}
			else
			{
				System.out.print("\nPlease select a valid monster option.\n");
			}
		}
		else if (optionNumber == 0)
		{
			inTeamViewer = false;
		}
		else 
		{
			Monster selectedMonster =  this.playersTeam.get(optionNumber-1);
			System.out.printf("MonsterPod %d: %s\n\n", optionNumber, selectedMonster.getMonsterName());
			System.out.print(selectedMonster.toString());
		}
		
		return inTeamViewer;
	}
	
	
	
	public boolean useItem(boolean itemUseVerifying, int optionNumber, Item selectedItem, Monster monster)
	{
		if (optionNumber == 0)
		{
			itemUseVerifying = false;
		}
		else if (optionNumber == 1)
		{
			System.out.print("Item used.\n");
			selectedItem.useItem(selectedItem, monster);
			
			if (this.playerInventory.get(selectedItem) == 1)
			{
				this.playerInventory.remove(selectedItem);
			}
			else
			{
				this.playerInventory.put(selectedItem, this.playerInventory.get(selectedItem)-1);
			}
			itemUseVerifying = false;
		}
		else
		{
			System.out.print("Please select a valid option.\n");
		}
		return itemUseVerifying;
	}
	
	
	
	public boolean itemOnMonster(boolean inMonsterSelector, int optionNumber, Item selectedItem)
	{
		if (optionNumber >= 1 && optionNumber <= this.playersTeam.size())
		{
			Monster monster = this.playersTeam.get(optionNumber-1);
			boolean itemUseVerifying = true;
			
			while (itemUseVerifying == true)
			{
				System.out.print(monster.toString());
				System.out.printf("Would you like to use %s on %s?\n\n", selectedItem.getItemName(), monster.pickMonsterName());
				System.out.print("1 - Yes\n");
				System.out.print("0 - Go back.\n");
				
				try 
				{
					optionNumber = scanInput.nextInt();
					itemUseVerifying = useItem(itemUseVerifying, optionNumber, selectedItem, monster);
				} 
				catch (InputMismatchException excp) 
				{
					System.out.print("\nPlease enter a valid option number.\n");
					scanInput.next();
					continue;
				}
			}
			itemUseVerifying = false;
		}
		else if (optionNumber > this.playersTeam.size() && optionNumber <= 4)
		{
			System.out.printf("Monster Pod %d is empty. Please select a different monster pod.\n", optionNumber);
		}
		else if (optionNumber == 0)
		{
			inMonsterSelector = false;
		}
		else
		{
			System.out.print("Please enter a valid option number.\n");
		}
		return inMonsterSelector;
	}
	
	
	
	public boolean itemStatOpener(boolean inInventoryViewer,int optionNumber)
	{
		if (optionNumber > this.playerInventory.size())
		{
			System.out.print("\nPlease select a valid item option.\n");
		}
		else if (optionNumber == 0)
		{
			inInventoryViewer = false;
		}
		else 
		{
			
			boolean inMonsterSelector = true;
			Item selectedItem =  (Item) this.playerInventory.keySet().toArray()[optionNumber-1];
			
			while (inMonsterSelector == true)
			{
				if (this.playerInventory.containsKey(selectedItem))
				{
					System.out.printf("%s: %s\n\n", selectedItem.getItemName(), selectedItem.getItemEffect());
					System.out.printf("Which monster would you like to use %s on:\n\n", selectedItem.getItemName());
					
					int order = 1;
					for (Monster monster: playersTeam)
					{
						System.out.printf("MonsterPod %d (Health: %d): %s\n", order, monster.getCurrentHealth(), monster.pickMonsterName());
						order += 1;
					}
					for (int position = order; position < 5; position++)
					{
						System.out.printf("MonsterPod %d (Health: -): (Empty)\n", position);	
					}
					System.out.print("\nSelect the MonsterPod to view monster stats or use item.\n");
					System.out.print("\n0 - Go back.\n");
					
					
					try 
					{
						optionNumber = scanInput.nextInt();
						inMonsterSelector = itemOnMonster(inMonsterSelector, optionNumber, selectedItem);
					} 
					catch (InputMismatchException excp) 
					{
						System.out.print("\nPlease enter a valid option number.\n");
						scanInput.next();
						continue;
					}
				}
				else
				{
					System.out.printf("All available %s's have been used.\n", selectedItem.getItemName());
					inMonsterSelector = false;
				}
				
			}
		}
		
		return inInventoryViewer;
	}
	
	
	
	public void viewInventory()
	{
		boolean inInventoryViewer = true;
		int optionNumber = 0;
		
		if (this.playerInventory.size() == 0)
		{
			System.out.print("\nYou do not have any items.\n");
			return;
		}
		
		
		while (inInventoryViewer == true)
		{ 
			if (this.playerInventory.size() == 0)
			{
				System.out.print("Player inventory is currently: (Empty)\n");
				inInventoryViewer = false;
			}
			else
			{
				int position = 1;
				System.out.print("\nYour current inventory is:\n");
				for (var entry: playerInventory.entrySet())
				{
					System.out.printf("%d) %s: %d\n", position, entry.getKey().getItemName(), entry.getValue());
					position += 1;
				}
				
				System.out.print("\nSelect the item position to view item effects or use item.\n");
				System.out.print("\n0 - Exit Inventory View.\n");
				
				
				try 
				{
					optionNumber = scanInput.nextInt();
					inInventoryViewer = itemStatOpener(inInventoryViewer, optionNumber);
				} 
				catch (InputMismatchException excp) 
				{
					System.out.print("\nPlease enter a valid option number.\n");
					scanInput.next();
					continue;
				}
			}
		}
	}
	
	public void viewTeam()
	{
		boolean inTeamViewer = true;
		int optionNumber = 0;
		
		
		while (inTeamViewer == true)
		{ 
			int order = 1;
			System.out.print("\nYour ordered team monsters are: \n\n");
			for (Monster monster: playersTeam)
			{
				System.out.printf("MonsterPod %d: %s\n", order, monster.pickMonsterName());
				order += 1;
			}
			for (int position = order; position < 5; position++)
			{
				System.out.printf("MonsterPod %d: (Empty)\n", position);
					
			}
			System.out.print("\nSelect the MonsterPod to view monster stats.\n");
			System.out.print("\n0 - Exit Team View.\n");
			
			
			try 
			{
				optionNumber = scanInput.nextInt();
				inTeamViewer = monsterStatOpener(inTeamViewer, optionNumber);
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("\nPlease enter a valid option number.\n");
				scanInput.next();
				continue;
			}
		}
	}
	
	
	public boolean playerSleep(boolean inPlayerMenu)
	{
		
		this.currentDay += 1;
		this.daysRemaining -= 1;
		
		if (this.daysRemaining == 0)
		{
			System.out.print("GAME OVER!");
			this.game.setGameOver(true);
			inPlayerMenu = false;
		}
		else
		{
			System.out.printf("\nGoodnight %s! *Sleeping...*\n", this.game.getPlayerName());
			
			// heal monsters by healAmount not exceeding maxHealth
			for (Monster monster: this.playersTeam)
			{
				monster.healUp();
			}
			
			// update items and monsters in shop
			shop.randomGenerateItems();
			shop.randomGenerateMonsters();
			
			// implement sleeping time
			long start = System.currentTimeMillis();
			long end = start + 5*1000;
			while (System.currentTimeMillis() < end) 
			{
			}
			
			// update all battles
			this.battle.generateBattles();
						
			System.out.printf("\nGood Morning %s!\n", this.game.getPlayerName());
			System.out.print("\nAll monsters have healed up over night!\n");
			System.out.print("Shop has been updated!\n");
			System.out.print("New battles available!\n");
					
		}
		return inPlayerMenu;
	}
	
	
	public boolean executePlayerCommand(boolean inPlayerMenu, int optionNumber)
	{
		if (optionNumber == 1) 
		{
			System.out.printf("Available Gold: %d\n", this.getPlayerGold());
        } 
		else if (optionNumber == 2) 
        {
			System.out.printf("Current Day: %d\n", this.getCurrentDay());
        } 
		else if (optionNumber == 3) 
		{
			System.out.printf("Remaining Days: %d\n", this.getDaysRemaining());
        } 
		else if (optionNumber == 4)
		{
			this.viewTeam();
		}
		else if (optionNumber == 5)
		{
			this.viewInventory();
		}
		else if (optionNumber == 6)
		{
			this.battleViewer();
		}
		else if (optionNumber == 7)
		{
			this.shop.viewShop();
			
		}
		else if (optionNumber == 8)
		{
			inPlayerMenu = this.playerSleep(inPlayerMenu);
		}

		else if (optionNumber == 0) 
		{
			inPlayerMenu = false;
        }
		
		else 
		{
			System.out.print("Choose an option number between 0 and 6.\n");
		}
		return inPlayerMenu;
	}
	
    
	public boolean monstersReadyBattle()
	{
		for (Monster monster: this.playersTeam)
		{
			if (monster.isFaint() == false)
			{
				return true;
			}
		}
		return false;
	}
	
	
	public boolean executeFightCommand(Enemy chosenEnemy, boolean decidingToFight, int optionNumber)
	{
		if (optionNumber == 0)
		{
			decidingToFight = false;
		}
		else if (optionNumber == 1)
		{
			if (this.monstersReadyBattle() == true)
			{
				if (chosenEnemy.getAlreadyFought() == false)
				{
					this.battle.fight(chosenEnemy);
					decidingToFight = false;
				}
				else
				{
					System.out.print("Enemy already fought! Please pick a different enemy.\n");
					decidingToFight = false;
				}
			}
			else
			{
				System.out.print("All your monsters have fainted! Please have atleast 1 monster alive to fight.\n");
			}
			
		}
		else
		{
			System.out.print("Please select a valid option\n");
		}
		return decidingToFight;
	}
	
	
	
	
	public boolean executeBattleCommand(boolean inBattleViewer, int optionNumber)
	{
		if (optionNumber == 0)
		{
			inBattleViewer = false;
		}
		else if (optionNumber >= 1 && optionNumber <= this.battle.getBattles().size())
		{
			boolean decidingToFight = true;
			
			while (decidingToFight == true)
			{
				Enemy chosenEnemy = this.battle.getBattles().get(optionNumber-1);
				System.out.printf("\nWould you like to fight %s?\n", chosenEnemy.getEnemyName());
				System.out.print("\n1) Yes\n");
				System.out.print("0) Go Back\n");
				
				try 
				{
					optionNumber = scanInput.nextInt();
					decidingToFight = executeFightCommand(chosenEnemy, decidingToFight, optionNumber);
				} 
				catch (InputMismatchException excp) 
				{
					System.out.print("Please enter a valid option number.\n");
					scanInput.next();
					continue;
				}
			}
		}
		else
		{
			System.out.print("Please select a valid option\n");
		}
		return inBattleViewer;
		
	}
	
	
	
	
	
	public void battleViewer()
	{
		boolean inBattleViewer = true;
		int optionNumber = 0;
	
		while (inBattleViewer == true)
		{
			System.out.print("\nWho would you like to fight?\n\n");
			int position = 1;
			for (Enemy enemy: this.battle.getBattles())
			{
				System.out.printf("%d) %s\n", position, enemy.getEnemyName());
				position += 1;
			}
			System.out.print("0) Go Back\n");
			
			try 
			{
				optionNumber = scanInput.nextInt();
				inBattleViewer = executeBattleCommand(inBattleViewer, optionNumber);
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("Please enter a valid option number.\n");
				scanInput.next();
				continue;
			}
		}
		
	}
	
	public void playerViewer() 
	{
		boolean inPlayerMenu = true;
		int optionNumber = 0;
		
		while (inPlayerMenu == true)
		{
			System.out.print("\nWhat would you like to do: \n");
			System.out.print("\n1. View Gold\n");
			System.out.print("2. View Current Day\n");
			System.out.print("3. View Remaining Days\n");
			System.out.print("4. View Team\n");
			System.out.print("5. View Inventory\n");
			System.out.print("6. View Battles\n");
			System.out.print("7. View Shop\n");
			System.out.print("8. Sleep\n");
			System.out.print("0. Exit\n");
			
			try 
			{
				optionNumber = scanInput.nextInt();
				inPlayerMenu = executePlayerCommand(inPlayerMenu, optionNumber);
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("Please enter a valid option number.\n");
				scanInput.next();
				continue;
			}
		}
	}

	public boolean isReadyForBattle() {
		return readyForBattle;
	}

	public void setReadyForBattle(boolean readyForBattle) {
		this.readyForBattle = readyForBattle;
	}

	
}






