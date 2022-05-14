import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;

import items.Item;
import monsters.Cavernfreak;
import monsters.Hollowtree;
import monsters.Monster;
import monsters.Mornpest;
import monsters.Soilscreamer;
import monsters.Venomhound;


public class Player {
	
	/*
	 * Players input.
	 */
	private Scanner scanInput = new Scanner(System.in);
	/* 
	 * Players gold.
	 */
	private int playerGold = 0;
	/* 
	 * Players inventory.
	 */
	private Map<Item, Integer> playerInventory = new HashMap<Item, Integer>();
	/* 
	 * Players items.
	 */
	private ArrayList<Item> playersItems = new ArrayList<Item>();
	/* 
	 * Players team of monsters.
	 */
	private ArrayList<Monster> playersTeam = new ArrayList<Monster>();
	/* 
	 * Players choice of starting monster.
	 */
	private Monster startingMonsters[] = new Monster[5];
	/* 
	 * An instance of the Battle class.
	 */
	private Battle battle;
	/* 
	 * An instance of the Shop class.
	 */
	private Shop shop;
	/* 
	 * An instance of the Game class.
	 */
	private Game game;
	/* 
	 * The current day.
	 */
	private int currentDay;
	/* 
	 * The days remaining in the game.
	 */
	private int daysRemaining;
	/* 
	 * Whether or not the player is ready for battle.
	 */
	private boolean readyForBattle;
	
	/* 
	 * Gets the current amount of gold the player has.
	 * 
	 * @return Returns the players gold value.
	 */
	public int getPlayerGold() {
		return playerGold;
	}
	
	/* 
	 * Sets the amount of gold the player will start with. 
	 * 
	 * @param playerGold The amount of gold the player will start with.
	 */
	public void setPlayerGold(int playerGold) {
		this.playerGold = playerGold;
	}
	
	/* 
	 * Sets the current day, and will increment as each day passes.
	 * 
	 * @param currentDay The current day for the player.
	 */
	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}
	
	/* 
	 * Gets what the current day is.
	 * 
	 * @return Returns the value of currentDay.
	 */
	public int getCurrentDay() {
		return currentDay;
	}
	
	/* 
	 * Sets the amount of days remaining.
	 * 
	 * @param currentDay The current day.
	 * @game An instance of the Game class.
	 * 
	 */
	public void setDaysRemaining(int currentDay, Game game) {
		this.daysRemaining = game.getGameLength() - currentDay;
	}
	
	/* 
	 * Gets the amount of days remaining.
	 * 
	 * @return The value of daysRemaining.
	 */
	public int getDaysRemaining() {
		return this.daysRemaining;
	}
	/* 
	 * Gets the length of the players monster team.
	 * 
	 * @return Returns the integer value of the length of his team (how many Monsters there are in it).
	 */
	public int getPlayersTeamLength()
	{
		return this.playersTeam.size();
	}
	
	/* 
	 * Creates the game.
	 * 
	 * @param game An instance of the Game class.
	 */
	public void setGame(Game game)
	{
		this.game = game; 
	}
	
	/* 
	 * Gets the game instance.
	 * 
	 * @return Returns the game instance.
	 */
	public Game getGame()
	{
		return this.game;
	}
	
	/* 
	 * Creates the shop.
	 * 
	 * @param newShop An instance of the Shop class.
	 */
	public void setShop(Shop newShop)
	{
		this.shop = newShop;
	}
	
	/* 
	 * Gets the players current items.
	 * 
	 * @return Returns an ArrayList of the players items.
	 */
	public ArrayList<Item> getPlayerItems()
	{
		return this.playersItems;
	}
	
	/* 
	 * Gets the players current monster team.
	 * 
	 * @return Returns an ArrayList containing the players monsters.
	 */
	
	
	public Shop getShop()
	{
		return this.shop;
	}
	
	public ArrayList<Monster> getPlayerMonsters()
	{
		return this.playersTeam;
	}
	
	/*
	 * Gets the players current inventory.
	 * 
	 * @return Returns a Map containing the players inventory.
	 */
	public Map<Item, Integer> getPlayerInventory()
	{
		return this.playerInventory;
	}
	
	/*
	 * Gets a Battle instance.
	 * 
	 * @return Returns an instance of the Battle class.
	 */
	public Battle getBattle() {
		return battle;
	}
	
	/*
	 * Sets the Battle instance.
	 * 
	 * @param battle An instance of the Battle class.
	 */
	public void setBattle(Battle battle) {
		this.battle = battle;
	}
	
	/*
	 * Gets the difficulty of the game that was set by the player,
	 * 
	 * @return Returns the value of gameDifficulty.
	 */
	public double getPlayerDifficulty()
	{
		return this.game.getGameDifficulty();
	}
	
	/*
	 * Adds a new monster to the players monster team.
	 * 
	 * @param monster The monster that is to be added.
	 */
	public void addToTeam(Monster monster)
	{
		this.playersTeam.add(monster);
	}
	
	
	public boolean useItem(Item item, Monster monster)
	{
		String item_name = item.getItemName();
		
		if  (item_name == "Blood Broth")
		{
			monster.setCurrentHealth(monster.getCurrentHealth() + 50);
			System.out.printf("%s's health has increased by 50.\n", monster.getMonsterName());
		}
		
		else if (item_name == "Energizer Bone")
		{
			monster.setDamage(monster.getDamage() + 5);
			System.out.printf("%s's basic attack has increased by 50.\n", monster.getMonsterName());
		}
		
		else if (item_name == "Cursed Skull")
		{
			Random randNum = new Random();
			int upperbound = 3;
			int deciding_num = randNum.nextInt(upperbound);
			
			if (deciding_num <= 1)
			{
				monster.setMaxHealth(monster.getMaxHealth() + 30);
				System.out.printf("Lucky! %s's max health has increased by 30.\n", monster.getMonsterName());
			}
			else
			{
				monster.setMaxHealth(monster.getMaxHealth() + 30);
				System.out.printf("Unlucky! %s's max health has decreased by 30.\n", monster.getMonsterName());
			}
		}
		
		else if (item_name == "Guardian Arch")
		{
			monster.setHealAmount(monster.getHealAmount() + 20);
			System.out.printf("%s's heal amount has increased by 10.\n", monster.getMonsterName());
		}
		
		else if (item_name == "Virility Gem")
		{
			monster.setSpecialDamage(monster.getSpecialDamage() + 10);
			System.out.printf("%s's special attack has increased by 10.\n", monster.getMonsterName());
		}
		

		int quantityLeft = this.playerInventory.get(item);
		
		if (quantityLeft >= 2)
		{
			int newQuantity = quantityLeft - 1;
			this.playerInventory.remove(item);
			this.playerInventory.put(item, newQuantity);
			return false;
		} 
		else 
		{
			this.playerInventory.remove(item);
			return true;
		}
	}
	
	
	public boolean buyItem(Item item)
	{
		if (this.playerGold < item.getPrice()) {
			return false;
		}
		else {
			this.playerGold -= item.getPrice();
			if (this.playerInventory.containsKey(item)) 
			{
				int newValue = this.playerInventory.get(item) + 1;
				this.playerInventory.remove(item);
				this.playerInventory.put(item, newValue);
				System.out.print(this.playerInventory);
			} else 
			{
				this.playerInventory.put(item, 1);
			}
			return true;
		}
	}
	
	/*
	 * Sells an item to the shop.
	 * 
	 * @param item The item to be sold.
	 */
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
	
	/*
	 * Sells a monster to the shop.
	 * 
	 * @param monster The monster to be sold.
	 */
	public void sellMonster(Monster monster)
	{
		this.playerGold += monster.getResalePrice();
		this.playersTeam.remove(monster);
		/** System.out.print("\nMonster sold!\n");
		System.out.printf("\n%s has been removed from the team.\n", monster.pickMonsterName());
		System.out.printf("%d Gold has been given to you.\n", monster.getPrice()); */
	}
	

	/*
	 * Purchases a monster from the shop.
	 * 
	 * @param monster The monster to be purchased.
	 * @param rename The name the player wants to give the monster (optional).
	 */

	
	
	
	
	public String buyMonster(Monster monster)
	{
		String update;
		if (this.playerGold < monster.getPrice())
		{
			update = "You don't have enough gold to buy %s!\n".formatted(monster.getMonsterName());
			update = "No_Gold";
		}
		else if (this.playersTeam.size() == 4)
		{		
			update = "Team_Full";
		}
		else
		{
			this.playerGold -= monster.getPrice();
			this.playersTeam.add(monster);
			update = "No Error";
		}
		return update;
	}
	
	
	/*
	 * Renames a monster. The player inputs the name that they want the monster to have,
	 * and it is checked for validity. If the name isn't valid, is simply asked to try again.
	 * 
	 * @param bought Whether or not the monster has been purchased or not.
	 * @param optionCorrect If the players input was valid.
	 * @param selection The players input.
	 * @param monster The monster the player wants to rename.
	 * 
	 * @return Returns true if the monster had been purchased and a correct option was chosen,
	 * else false.
	 */
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
	
	/*
	 * Views the players teams condition. Their condition consists of their health and name.
	 */
	public void viewTeamCondition()
	{
		System.out.print("Your team's current condition is:\n\n");
		int position = 1;
		for (Monster monster: this.playersTeam)
		{
			System.out.printf("%d) Monsterpod %d (Health: %d): %s\n", position, position, monster.getCurrentHealth(), monster.pickMonsterName());
		}
	}
	
	/*
	 * Makes sure the monster purchase is successful.
	 * 
	 * @param monster The monster to be purchased.
	 * @return Returns true if the monster was purchased, else false.
	 */
	public boolean verifyMonsterPurchase(Monster monster)
	{
		boolean bought = false;
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
	
	
	/*
	 * Executes the players choice of starting monster. If they input a valid monster,
	 * they can take that monster.
	 * 
	 * @param startingMonsters The starting monster in the game.
	 * @param optionNumber The players input.
	 * 
	 * @return Returns true if the player chose a valid starting monster, else false.
	 */

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
	
	/*
	 * Makes the player choose a starting monster to take.
	 */
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
	
	/*
	 * Allows the player to checks the stats of a monster that is in their party.
	 * 
	 * @param inTeamViewer Whether or not the player is in team viewer.
	 * @param optionNumber The players input.
	 * 
	 * @return Whether or not the player is in team viewer.
	 */
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
	
	/*
	 * Uses an item that the player has on their selected monster.
	 * 
	 * @param itemUseVerifying If the player wants to use an item or not.
	 * @param optionNumber The players input on what item they want to use.
	 * @param selectedItem The item that the player had selected.
	 * @param monster What monster the player wanted to use their item on.
	 * 
	 * @return Returns true if an item was used successfully, else false.
	 */
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
	
	/*
	 * Allows the player to choose what monster they want to use the item on.
	 * 
	 * @param inMonsterSelector Whether or not the player is in the monster selector screen or not.
	 * @param optionNumber The player input on what monster they want to use an item on.
	 * @param selectedItem The item that the player selected.
	 * 
	 * @return Returns whether the player is in monster selector or not.
	 */
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
	
	/*
	 * Selects an item and chooses the monster they would like to use it on.
	 * 
	 * @param inInventoryViewer Whether the player is in inventory viewer or not.
	 * @param optionNumber The players input on which item they would like to use.
	 * 
	 * @return Returns whether or not the player is in inventory viewer.
	 */
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
	
	/*
	 * Views the players inventory.
	 */
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
	
	/*
	 * Views the players current monster team.
	 */
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
	
	/*
	 * Puts the player and their team to sleep. Increases the current day by one,
	 * and decreases day remaining by one. If days remaining is 0, the game ends.
	 * Heals the players monster party each by their own healing stat and unfaints them,=.
	 * Randomizes shop and battles again.
	 * 
	 */
	public void playerSleep()
	{
		this.currentDay += 1;
		this.daysRemaining -= 1;
		
		if (this.daysRemaining == 0)
		{
			this.game.setGameOver(true);
		}
		else
		{
			
			// heal monsters by healAmount not exceeding maxHealth
			for (Monster monster: this.playersTeam)
			{
				monster.setFaint(false);
				monster.healUp();
			}
			
			// update items and monsters in shop
			this.shop.randomGenerateItems();
			this.shop.randomGenerateMonsters();
			
			// update all battles
			this.battle.generateBattles();
						
			/* System.out.printf("\nGood Morning %s!\n", this.game.getPlayerName());
			System.out.print("\nAll monsters have healed up over night!\n");
			System.out.print("Shop has been updated!\n");
			System.out.print("New battles available!\n"); */
					
		}
		
		
	}
	
	/*
	 * Executes what the player wants to do in the main screen.
	 * 
	 * @param inPlayerMenu Whether or not the player is still in the player menu.
	 * @param optionNumber What the players input is.
	 * 
	 * @return Returns whether or not the the player is still in the player menu.
	 */
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
		else if (optionNumber == 8)
		{
			
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
	
	/*
	 * Checks if the player has any monsters that can battle (haven't fainted).
	 * 
	 * @return Returns true if the player has any ready monsters, else false.
	 */
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
	
	/*
	 * Takes the players input for what enemy they want to battle.
	 * 
	 * @param chosenEnemy The enemy that the player wants to battle.
	 * @param decidingToFight Whether or not the player wants to battle.
	 * @param optionNumber The players input on what enemy that want to battle.
	 * 
	 * @return Returns true if the player has found an enemy to fight that is valid, else false.
	 */
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
	
	/*
	 * Checks if the enemy that the player has chosen to battle is valid, and begins the battle.
	 * 
	 * @param inBattleViewer Whether or not the player is in the battle viewer.
	 * @param optionNumber The players input on what enemy they want to battle.
	 * 
	 * @return Returns whether or not the player is in the battle viewer.
	 */
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
	
	/*
	 * Allows the player to view who they want to battle.
	 */
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
	
	/*
	 * Allows the player to view the main screen, and decide what they want to do.
	 */
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

	/*
	 * Gets whether or not the player is ready for battle.
	 * 
	 * @return Returns true if at least one of the players monsters have not fainted, else fale.
	 */
	public boolean isReadyForBattle() {
		return readyForBattle;
	}
	
	/*
	 * Sets the value of readyForBattle.
	 * 
	 * @param readyForBattle Whether or not the player is ready for battle.
	 */
	public void setReadyForBattle(boolean readyForBattle) {
		this.readyForBattle = readyForBattle;
	}
	
	/*
	 * Creates the player instance, and sets and outputs their gold.
	 */
	public static void main(String[] args)
	{
		Player player = new Player();
		player.setPlayerGold(1);
		System.out.print(player.getPlayerGold());
	}
}






