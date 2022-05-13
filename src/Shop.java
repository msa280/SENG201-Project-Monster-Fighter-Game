import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import items.BloodBroth;
import items.CursedSkull;
import items.EnergizerBone;
import items.GuardianArch;
import items.Item;
import items.VirilityGem;
import monsters.Monster;
import monsters.Cavernfreak;
import monsters.Hollowtree;
import monsters.Mornpest;
import monsters.Soilscreamer;
import monsters.Venomhound;

/*
 * Creates a shop where the player can buy and sell items and monsters.
 */
public class Shop {
	
	/*
	 * Players input.
	 */
	private Scanner scanInput = new Scanner(System.in);
	/*
	 * The option the player will choose for what item/monster to buy/sell.
	 */
	private int readOption = 0;
	/*
	 * An array containing all of the monsters that are purchasable by the player.
	 */
	private ArrayList<Monster> monstersForSale = new ArrayList<Monster>();
	/*
	 * An array containing all of the items that are purchasable by the player.
	 */
	private ArrayList<Item> itemsForSale = new ArrayList<Item>();
	/*
	 * A list containing all of the monsters in the game.
	 */
	private Monster allMonsters[] = new Monster[5];
	/*
	 * A list containing all of the items in the game.
	 */
	private Item allItems[] = new Item[5];
	/*
	 * The player.
	 */
	private Player trader;

	/*
	 * Gets all of the monsters that are for sale by the shop.
	 * 
	 * @return Returns the value of monstersForSale.
	 */
	public ArrayList<Monster> getMonstersForSale() {
		return monstersForSale;
	}
	
	/*
	 * Gets all of the items that are for sale by the shop.
	 * 
	 * @return Returns the value of itemsForSale.
	 */
	public ArrayList<Item> getItemsForSale() {
		return itemsForSale;
	}
	
	/*
	 * Sets all of the items that will be for sale to the player.
	 * 
	 * @param itemsForSale The items that are for sale.
	 */
	public void setItemsForSale(ArrayList<Item> itemsForSale) {
		this.itemsForSale = itemsForSale;
	}

	/*
	 * Sets all of the monsters that will be for sale to the player.
	 * 
	 * @param monstersForSale The monsters that are for sale.
	 */
	public void setMonstersForSale(ArrayList<Monster> monstersForSale) {
		this.monstersForSale = monstersForSale;
	}
	
	/*
	 * Initializes the shop by generating all items and monsters, then
	 * takes a random assortment of them to be for sale.
	 */
	public void initializeShop()
	{
		this.generateAllItems();
		this.generateAllMonsters();
		this.randomGenerateItems();
		this.randomGenerateMonsters();
	}
	
	/*
	 * Generates all of the items in the game.
	 */
	public void generateAllItems()
	{
		this.allItems[0] = new BloodBroth();
		this.allItems[1] = new CursedSkull();
		this.allItems[2] = new EnergizerBone();
		this.allItems[3] = new GuardianArch();
		this.allItems[4] = new VirilityGem();
	}
	
	/*
	 * Gets the current instance of Player.
	 * 
	 * @param player The player.
	 */
	public void getTrader(Player player)
	{
		this.trader = player;
	}
	
	/*
	 * Randomly generates a list of items to be used by the shop.
	 */
	public void randomGenerateItems()
	{
		boolean generationComplete = false;
		ArrayList<Integer> numsDone = new ArrayList<Integer>();
		
		this.itemsForSale.clear();   // Clearing array to generate a new array of items if the day changes
		
		while (generationComplete == false)
		{
			Random randNum = new Random();
			int upperbound = 5;
			int position = randNum.nextInt(upperbound);
			
			if (this.getItemsForSale().size() >= 5)
			{
				generationComplete = true;
			}
			else if (numsDone.contains(position))
			{
				continue;
			}
			else
			{
				if (this.itemsForSale.contains(this.allItems[position]))
				{
					continue;
				}
				else
				{
					this.itemsForSale.add(this.allItems[position]);
				}		
			}
		}
	}
	
	/*
	 * Generates all of the monsters in the game.
	 */
	public void generateAllMonsters()
	{
		this.allMonsters[0] = new Cavernfreak();
		this.allMonsters[1] = new Hollowtree();
		this.allMonsters[2] = new Mornpest();
		this.allMonsters[3] = new Soilscreamer();
		this.allMonsters[4] = new Venomhound();
	}
	
	/*
	 * Randomly generates a list of monsters to be used by the shop.
	 */
	public void randomGenerateMonsters()
	{
		boolean generationComplete = false;
		ArrayList<Integer> numsDone = new ArrayList<Integer>();
		
		this.monstersForSale.clear();   // Clearing array to generate a new array of monsters if the day changes
		
		while (generationComplete == false)
		{
			Random randNum = new Random();
			int upperbound = 5;
			int position = randNum.nextInt(upperbound);
			
			if (this.getMonstersForSale().size() >= 5)
			{
				generationComplete = true;
			}
			else if (numsDone.contains(position))
			{
				continue;
			}
			else
			{
				if (this.monstersForSale.contains(this.allMonsters[position]))
				{
					continue;
				}
				else
				{
					this.monstersForSale.add(this.allMonsters[position]);
				}		
			}
		}
	}
	
	/*
	 * Purchases a monster of the players choosing.
	 * 
	 * @param chosenMonster The monster that the player chose.
	 */
	public void buyMonster(Monster chosenMonster)
	{
		boolean inBuyMonster = true;
		
		while (inBuyMonster == true)
		{
			System.out.print(chosenMonster.toString());
			System.out.printf("\nChoose a buying method for %s:\n\n", chosenMonster.getMonsterName());
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			System.out.print("1. Buy and Rename\n");
			System.out.print("2. Buy with Default Name\n");
			System.out.print("0. Go Back\n");
			
			try 
			{
				readOption = scanInput.nextInt();
				if (readOption == 1)
				{
					this.trader.buyMonster(chosenMonster, true);
					inBuyMonster = false;
				} 
				else if (readOption == 2)
				{
					this.trader.buyMonster(chosenMonster, false);
					inBuyMonster = false;
				}
				else if (readOption == 0)
				{
					inBuyMonster = false;
				}
				else
				{
					System.out.print("Please choose a vlid option.\n");
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
	
	
	/*
	 * Sells a monster of the players choosing.
	 * 
	 * @param chosenMonster The monster that the player chose.
	 */
	public void sellMonster(Monster chosenMonster)
	{
		boolean inSellMonster = true;
		
		while (inSellMonster == true)
		{
			System.out.print(chosenMonster.toString());
			System.out.printf("\nWould you like to sell %s for %d?\n\n", chosenMonster.pickMonsterName(), chosenMonster.getPrice());
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			System.out.print("1. Sell\n");
			System.out.print("0. Go Back\n");
			
			try 
			{
				readOption = scanInput.nextInt();
				
				if (readOption == 1)
				{
					this.trader.sellMonster(chosenMonster);
					inSellMonster = false;
				} 
				else if (readOption == 0)
				{
					inSellMonster = false;
				}
				else
				{
					System.out.print("Please choose a vlid option.\n");
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
	
	/*
	 * Executes the purchase of a monster by the player.
	 * 
	 * @param inMonsterBuy Whether or not the player is in the monster buy screen.
	 * @param readOption The players input for which monster they would like to purchase.
	 * @param monstersAvailable The monsters available to purchase by the player.
	 * 
	 * @return Returns whether or not the player is in the monster buy screen.
	 */
	public boolean executeMonsterBuy(boolean inMonsterBuy, int readOption, ArrayList<Monster> monstersAvailable)
	{
		Monster chosenMonster = null;
		
		if (readOption == 0)
		{
			inMonsterBuy = false;
		}
		else if (readOption >= 1 && readOption <= 5)
		{
			int index = readOption - 1;
			chosenMonster = monstersAvailable.get(index);
			this.buyMonster(chosenMonster);
		}
		else
		{
			System.out.print("Please choose a valid option.\n");
		}

		return inMonsterBuy;
	}
	
	/*
	 * Executes the sale of a monster by the player.
	 * 
	 * @param inMonsterSell Whether or not we are in the monster sell screen.
	 * @param readOption The players input for which monster they would like to sell.
	 * 
	 * @return Returns whether or not we are in the monster sell screen.
	 */
	public boolean executeMonsterSell(boolean inMonsterSell, int readOption)
	{
		Monster chosenMonster = null;
			
		if (readOption == 0)
		{
			inMonsterSell = false;
		}
		else if (readOption >= 1 && readOption <= this.trader.getPlayersTeamLength())
		{
			int index = readOption - 1;
			chosenMonster =  this.trader.getPlayerMonsters().get(index);
			this.sellMonster(chosenMonster);
		}
		else
		{
			System.out.print("Please choose a valid option.\n");
		}

		return inMonsterSell;
	}
	
	/*
	 * Allows the player to choose what monster they want to purchase from the shop.
	 */
	public void monsterBuyStoreSection()
	{
		boolean inMonsterBuy = true;
		readOption = 0;
		
		while (inMonsterBuy == true)
		{
			System.out.print("\nHaru: What monster would you like to buy:\n\n");
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			this.randomGenerateMonsters();
			int optionNum = 1;
			for (Monster monster: this.getMonstersForSale())
			{
				System.out.printf("%d. %s (Cost - %d Gold)\n", optionNum, monster.getMonsterName(), monster.getPrice());
				optionNum += 1;
			}
			System.out.print("0. Go back\n");
			
			try 
			{
				readOption = scanInput.nextInt();
				inMonsterBuy = executeMonsterBuy(inMonsterBuy, readOption, this.getMonstersForSale());
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
	 * Allows the player to choose what monster they would like to sell from their team.
	 */
	public void monsterSellStoreSection()
	{
		boolean inMonsterSell = true;
		readOption = 0;
		
		if (this.trader.getPlayersTeamLength() == 0)
		{
			System.out.print("\nYou don't have any monsters to sell.\n");
			return;
		}
		
		
		while (inMonsterSell == true)
		{
			if (this.trader.getPlayersTeamLength() == 0)
			{
				break;
			}
			
			System.out.print("\nHaru: What monster would you like to sell:\n\n");
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			int optionNum = 1;
			for (Monster monster: this.trader.getPlayerMonsters())
			{
				// calculate new selling price of monster
				System.out.printf("%d) %s (Gain - %d Gold)\n", optionNum, monster.getMonsterName(), monster.getPrice());
				optionNum += 1;
			}
			System.out.print("\n0. Go back\n");
			
			try 
			{
				readOption = scanInput.nextInt();
				inMonsterSell = executeMonsterSell(inMonsterSell, readOption);
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
	 * Sells the chosen item.
	 * 
	 * @param chosenItem The item that the player chose.
	 */
	public void sellItem(Item chosenItem)
	{
		boolean inSellItem = true;
		
		while (inSellItem == true)
		{
			
			System.out.printf("%s - (%s)\n", chosenItem.getItemName(), chosenItem.getItemEffect());
			System.out.printf("\nWould you like to sell %s for %d?\n\n", chosenItem.getItemName(), chosenItem.getResalePrice());
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			System.out.print("1. Sell\n");
			System.out.print("0. Go Back\n");
			
			try 
			{
				readOption = scanInput.nextInt();
				
				if (readOption == 1)
				{
					this.trader.sellItem(chosenItem);
					inSellItem = false;
				} 
				else if (readOption == 0)
				{
					inSellItem = false;
				}
				else
				{
					System.out.print("Please choose a valid option.\n");
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
	
	/*
	 * Buys the chosen item.
	 * 
	 * @chosenItem The item that the player chose.
	 */
	public void buyItem(Item chosenItem)
	{
		boolean inBuyItem = true;
		
		while (inBuyItem == true)
		{
			
			System.out.printf("\n%s - (%s)\n", chosenItem.getItemName(), chosenItem.getItemEffect());
			System.out.printf("\nWould you like to buy %s:\n\n", chosenItem.getItemName());
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			System.out.print("1. Buy\n");
			System.out.print("0. Go Back\n");
			
			try 
			{
				readOption = scanInput.nextInt();
				if (readOption == 1)
				{
					this.trader.buyItem(chosenItem);
					inBuyItem = false;
				} 
				else if (readOption == 0)
				{
					inBuyItem = false;
				}
				else
				{
					System.out.print("Please choose a valid option.\n");
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
	
	/*
	 * Executes the purchase of an item.
	 * 
	 * @param inItemBuy Whether or not the player is in the item buy screen.
	 * @param readOption The players input for which item they would like to purchase.
	 * @param itemsAvailable A list of what items are available to purchase.
	 * 
	 * @return Returns whether or not the player is in the item buy screen.
	 */
	public boolean executeItemBuy(boolean inItemBuy, int readOption, ArrayList<Item> itemsAvailable)
	{
		Item chosenItem = null;
		
		if (readOption == 0)
		{
			inItemBuy = false;
		}
		else if (readOption >= 1 && readOption <= 5)
		{
			int index = readOption - 1;
			chosenItem = itemsAvailable.get(index);
			this.buyItem(chosenItem);
		}
		else
		{
			System.out.print("Please choose a valid option.\n");
		}

		return inItemBuy;
	}
	
	/*
	 * Executes the sale of an item.
	 * 
	 * @param inItemSell Whether or not the player is in the item sell screen.
	 * @param readOption The players input for which item they would like to sell.
	 * 
	 * @return Returns whether or not the player is in the item sell screen.
	 */
	public boolean executeItemSell(boolean inItemSell, int readOption)
	{
		Item chosenItem = null;
		
		if (readOption == 0)
		{
			inItemSell = false;
		}
		else if (readOption >= 1 && readOption <= 5)
		{
			chosenItem = this.trader.getPlayerItems().get(readOption-1);
			this.sellItem(chosenItem);
		}
		else
		{
			System.out.print("Please choose a valid option.\n");
		}

		return inItemSell;
	}
	
	/*
	 * Allows the player to choose what items to sell to the shop.
	 */
	public void itemSellStoreSection()
	{
		boolean inItemSell = true;
		readOption = 0;
		
		while (inItemSell == true)
		{	
			if (this.trader.getPlayerInventory().size() == 0)
			{
				return;
			}
		
			System.out.print("\nHaru: What item would you like to sell:\n\n");
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			
			int optionNum = 1;
			for (Map.Entry<Item, Integer> inventory : this.trader.getPlayerInventory().entrySet()) 
			{
				
                int itemResalePrice = inventory.getKey().getResalePrice();
				System.out.printf("%d) %s - %d left (Cost - %d Gold)\n", optionNum, inventory.getKey().getItemName(), inventory.getValue(), itemResalePrice);
				optionNum += 1;
			}
			System.out.print("0. Go back\n");
			
			try 
			{
				readOption = scanInput.nextInt();
				inItemSell = executeItemSell(inItemSell, readOption);
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
	 * Allows the player to choose what items to buy from the shop.
	 */
	public void itemBuyStoreSection()
	{
		boolean inItemBuy = true;
		readOption = 0;
		
		while (inItemBuy == true)
		{

			System.out.print("\nHaru: What item would you like to buy:\n\n");
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			int optionNum = 1;
			for (Item item: this.getItemsForSale())
			{
				System.out.printf("%d. %s (Cost - %d Gold)\n", optionNum, item.getItemName(), item.getPrice());
				optionNum += 1;
			}
			System.out.print("0. Go back\n");
			
			try 
			{
				readOption = scanInput.nextInt();
				inItemBuy = executeItemBuy(inItemBuy, readOption, this.getItemsForSale());
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
	 * Allows the player to choose what section of the store they want to purchase from.
	 * Either monster or item.
	 * 
	 * @param inBuySection Whether or not the player is in the buy section.
	 * @param readOption The player input for which section they would like to shop in.
	 * 
	 * @return Returns whether or not the player is in the inBuySection.
	 */
	public boolean buyStoreSection(boolean inBuySection, int readOption)
	{
		if (readOption == 0)
		{
			inBuySection = false;
		}
		else if (readOption == 1)
		{
			this.monsterBuyStoreSection();
			
		}
		else if (readOption == 2)
		{
			this.itemBuyStoreSection();
		}
		else
		{
			System.out.print("Please enter a valid option number.\n");
		}
		return inBuySection;
	}
	
	/*
	 * Allows the player to choose whether they want to sell monsters or items.
	 * 
	 * @param inSellSection Whether or not the player is in the sell section.
	 * @param readOption The player input for which section they would like to sell in.
	 * 
	 * @return Returns whether or not the player is in the inSellSection.
	 */
	public boolean sellStoreSection(boolean inSellSection, int readOption)
	{
		if (readOption == 0)
		{
			inSellSection = false;
		}
		else if (readOption == 1)
		{
			this.monsterSellStoreSection();
			
		}
		else if (readOption == 2)
		{
			if (this.trader.getPlayerInventory().size() == 0)
			{
				System.out.print("You have no items to sell\n");
			}
			else
			{
				this.itemSellStoreSection();
			}
		}
		else
		{
			System.out.print("Please enter a valid option number.\n");
		}
		return inSellSection;
	}

	/*
	 * Allows the player to choose whether they want to buy or sell.
	 * 
	 * @param inShop Whether or not the player is in the shop.
	 * @param chosenOption The players input for whether they want to buy or sell.
	 * 
	 * @return Whether or not the player is in the shop.
	 */
	public boolean executeBuyOrSell(boolean inShop, int chosenOption)
	{
		if (chosenOption == 1)
		{
			boolean inBuySection = true;
			int readOption = 0;
			
			while (inBuySection == true)
			{
				System.out.print("\nHaru: What would you like to buy:\n\n");
				System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
				System.out.print("1. Monsters\n");
				System.out.print("2. Items\n");
				System.out.print("0. Go Back\n");
				
				try 
				{
					readOption = scanInput.nextInt();
					inBuySection = buyStoreSection(inShop, readOption);
				} 
				catch (InputMismatchException excp) 
				{
					System.out.print("Please enter a valid option number.\n");
					scanInput.next();
					continue;
				}
			}
		}
		else if (chosenOption == 2)
		{
			boolean inSellSection = true;
			int readOption = 0;
			
			while (inSellSection == true)
			{
				
				System.out.print("\nHaru: What would you like to sell:\n\n");
				System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
				System.out.print("1. Monsters\n");
				System.out.print("2. Items\n");
				System.out.print("0. Go Back\n");
				
				try 
				{
					readOption = scanInput.nextInt();
					inSellSection = sellStoreSection(inShop, readOption);
				} 
				catch (InputMismatchException excp) 
				{
					System.out.print("Please enter a valid option number.\n");
					scanInput.next();
					continue;
				}
			}
		}
		else if (chosenOption == 0)
		{
			System.out.print("\nHaru: Hope to see you again!\n");
			System.out.print("Haru: Goodluck in your battles strong fellow!\n\n");
			inShop = false;
		}
		else
		{
			System.out.print("\nPlease select a valid option.\n");
		}
		
		return inShop;
	}
	
	/*
	 * Allows the player to view the shop and interact with it.
	 */
	public void viewShop()
	{
		System.out.print("\nHaru: Welcome to MonsterPod Laboratories!\n");
		System.out.print("Haru: Feel free to explore!\n");
		
		boolean inShop = true;
		readOption = 0;
		
		while (inShop == true)
		{
			
			System.out.print("\nHaru: Please pick what you want to do:\n\n");
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			System.out.print("1. Buy\n");
			System.out.print("2. Sell\n");
			System.out.print("0. Exit Shop\n");
			
			try 
			{
				readOption = scanInput.nextInt();
				inShop = executeBuyOrSell(inShop, readOption);
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
	 * Initializes the shop.
	 */
	public static void main(String[] args)
	{
		Shop newShop = new Shop();
		newShop.viewShop();
	}

	

}