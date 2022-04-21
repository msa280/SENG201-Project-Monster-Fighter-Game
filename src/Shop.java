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


public class Shop {
	
	private Scanner scan_input = new Scanner(System.in);
	private int read_option = 0;
	private ArrayList<Monster> monstersForSale = new ArrayList<Monster>();
	private ArrayList<Item> itemsForSale = new ArrayList<Item>();
	private Monster all_monsters[] = new Monster[5];
	private Item all_items[] = new Item[5];
	private Player trader;


	public ArrayList<Monster> getMonstersForSale() {
		return monstersForSale;
	}
	
	public ArrayList<Item> getItemsForSale() {
		return itemsForSale;
	}

	public void setItemsForSale(ArrayList<Item> itemsForSale) {
		this.itemsForSale = itemsForSale;
	}


	public void setMonstersForSale(ArrayList<Monster> monstersForSale) {
		this.monstersForSale = monstersForSale;
	}
	
	
	public void initialize_shop()
	{
		this.generate_allItems();
		this.generate_allMonsters();
		this.random_generateItems();
		this.random_generateMonsters();
	}
	
	
	
	
	public void generate_allItems()
	{
		this.all_items[0] = new BloodBroth();
		this.all_items[1] = new CursedSkull();
		this.all_items[2] = new EnergizerBone();
		this.all_items[3] = new GuardianArch();
		this.all_items[4] = new VirilityGem();
	}
	
	
	public void get_trader(Player player)
	{
		this.trader = player;
	}
	
	
	public void random_generateItems()
	{
		boolean generationComplete = false;
		ArrayList<Integer> nums_done = new ArrayList<Integer>();
		
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
			else if (nums_done.contains(position))
			{
				continue;
			}
			else
			{
				if (this.itemsForSale.contains(this.all_items[position]))
				{
					continue;
				}
				else
				{
					this.itemsForSale.add(this.all_items[position]);
				}		
			}
		}
	}
	

	
	public void generate_allMonsters()
	{
		this.all_monsters[0] = new Cavernfreak();
		this.all_monsters[1] = new Hollowtree();
		this.all_monsters[2] = new Mornpest();
		this.all_monsters[3] = new Soilscreamer();
		this.all_monsters[4] = new Venomhound();
	}
	
	
	
	
	public void random_generateMonsters()
	{
		boolean generationComplete = false;
		ArrayList<Integer> nums_done = new ArrayList<Integer>();
		
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
			else if (nums_done.contains(position))
			{
				continue;
			}
			else
			{
				if (this.monstersForSale.contains(this.all_monsters[position]))
				{
					continue;
				}
				else
				{
					this.monstersForSale.add(this.all_monsters[position]);
				}		
			}
		}
	}
	
	
	
	
	
	
	
	public void buyMonster(Monster chosen_monster)
	{
		boolean in_buyMonster = true;
		
		while (in_buyMonster == true)
		{
			System.out.print(chosen_monster.toString());
			System.out.printf("\nChoose a buying method for %s:\n\n", chosen_monster.getMonsterName());
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			System.out.print("1. Buy and Rename\n");
			System.out.print("2. Buy with Default Name\n");
			System.out.print("0. Go Back\n");
			
			try 
			{
				read_option = scan_input.nextInt();
				if (read_option == 1)
				{
					this.trader.buyMonster(chosen_monster, true);
					in_buyMonster = false;
				} 
				else if (read_option == 2)
				{
					this.trader.buyMonster(chosen_monster, false);
					in_buyMonster = false;
				}
				else if (read_option == 0)
				{
					in_buyMonster = false;
				}
				else
				{
					System.out.print("Please choose a vlid option.\n");
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
	
	
	
	public void sellMonster(Monster chosen_monster)
	{
		boolean in_sellMonster = true;
		
		while (in_sellMonster == true)
		{
			System.out.print(chosen_monster.toString());
			System.out.printf("\nWould you like to sell %s for %d?\n\n", chosen_monster.pickMonsterName(), chosen_monster.getPrice());
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			System.out.print("1. Sell\n");
			System.out.print("0. Go Back\n");
			
			try 
			{
				read_option = scan_input.nextInt();
				
				if (read_option == 1)
				{
					this.trader.sellMonster(chosen_monster);
					in_sellMonster = false;
				} 
				else if (read_option == 0)
				{
					in_sellMonster = false;
				}
				else
				{
					System.out.print("Please choose a vlid option.\n");
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
	
	
	
	
	
	public boolean execute_MonsterBuy(boolean in_monster_buy, int read_option, ArrayList<Monster> monstersAvailable)
	{
		Monster chosen_monster = null;
		
		if (read_option == 0)
		{
			in_monster_buy = false;
		}
		else if (read_option >= 1 && read_option <= 5)
		{
			int index = read_option - 1;
			chosen_monster = monstersAvailable.get(index);
			this.buyMonster(chosen_monster);
		}
		else
		{
			System.out.print("Please choose a valid option.\n");
		}

		return in_monster_buy;
	}
	
	
	
	
	
	
	public boolean execute_MonsterSell(boolean in_monster_sell, int read_option)
	{
		Monster chosen_monster = null;
			
		if (read_option == 0)
		{
			in_monster_sell = false;
		}
		else if (read_option >= 1 && read_option <= this.trader.get_playersTeam_length())
		{
			int index = read_option - 1;
			chosen_monster =  this.trader.get_playerMonsters().get(index);
			this.sellMonster(chosen_monster);
		}
		else
		{
			System.out.print("Please choose a valid option.\n");
		}

		return in_monster_sell;
	}
	
	
	
	
	
	
	public void monsterBuyStoreSection()
	{
		boolean in_monster_buy = true;
		read_option = 0;
		
		while (in_monster_buy == true)
		{
			System.out.print("\nHaru: What monster would you like to buy:\n\n");
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			this.random_generateMonsters();
			int option_num = 1;
			for (Monster monster: this.getMonstersForSale())
			{
				System.out.printf("%d. %s (Cost - %d Gold)\n", option_num, monster.getMonsterName(), monster.getPrice());
				option_num += 1;
			}
			System.out.print("0. Go back\n");
			
			try 
			{
				read_option = scan_input.nextInt();
				in_monster_buy = execute_MonsterBuy(in_monster_buy, read_option, this.getMonstersForSale());
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("Please enter a valid option number.\n");
				scan_input.next();
				continue;
			}
		}
	}
	
	
	
	
	
	public void monsterSellStoreSection()
	{
		boolean in_monster_sell = true;
		read_option = 0;
		
		if (this.trader.get_playersTeam_length() == 0)
		{
			System.out.print("\nYou don't have any monsters to sell.\n");
			return;
		}
		
		
		while (in_monster_sell == true)
		{
			if (this.trader.get_playersTeam_length() == 0)
			{
				break;
			}
			
			System.out.print("\nHaru: What monster would you like to sell:\n\n");
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			int option_num = 1;
			for (Monster monster: this.trader.get_playerMonsters())
			{
				// calculate new selling price of monster
				System.out.printf("%d) %s (Gain - %d Gold)\n", option_num, monster.getMonsterName(), monster.getPrice());
				option_num += 1;
			}
			System.out.print("\n0. Go back\n");
			
			try 
			{
				read_option = scan_input.nextInt();
				in_monster_sell = execute_MonsterSell(in_monster_sell, read_option);
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("Please enter a valid option number.\n");
				scan_input.next();
				continue;
			}
		}
	}
	
	
	

	
	public void sellItem(Item chosen_item)
	{
		boolean in_sellItem = true;
		
		while (in_sellItem == true)
		{
			
			System.out.printf("%s - (%s)\n", chosen_item.getItemName(), chosen_item.getItemEffect());
			System.out.printf("\nWould you like to sell %s for %d?\n\n", chosen_item.getItemName(), chosen_item.getResalePrice());
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			System.out.print("1. Sell\n");
			System.out.print("0. Go Back\n");
			
			try 
			{
				read_option = scan_input.nextInt();
				
				if (read_option == 1)
				{
					this.trader.sellItem(chosen_item);
					in_sellItem = false;
				} 
				else if (read_option == 0)
				{
					in_sellItem = false;
				}
				else
				{
					System.out.print("Please choose a valid option.\n");
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
	
	
	
	
	
	public void buyItem(Item chosen_item)
	{
		boolean in_buyItem = true;
		
		while (in_buyItem == true)
		{
			
			System.out.printf("\n%s - (%s)\n", chosen_item.getItemName(), chosen_item.getItemEffect());
			System.out.printf("\nWould you like to buy %s:\n\n", chosen_item.getItemName());
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			System.out.print("1. Buy\n");
			System.out.print("0. Go Back\n");
			
			try 
			{
				read_option = scan_input.nextInt();
				if (read_option == 1)
				{
					this.trader.buyItem(chosen_item);
					in_buyItem = false;
				} 
				else if (read_option == 0)
				{
					in_buyItem = false;
				}
				else
				{
					System.out.print("Please choose a valid option.\n");
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
	
	
	
	
	public boolean execute_ItemBuy(boolean in_item_buy, int read_option, ArrayList<Item> itemsAvailable)
	{
		Item chosen_item = null;
		
		if (read_option == 0)
		{
			in_item_buy = false;
		}
		else if (read_option >= 1 && read_option <= 5)
		{
			int index = read_option - 1;
			chosen_item = itemsAvailable.get(index);
			this.buyItem(chosen_item);
		}
		else
		{
			System.out.print("Please choose a valid option.\n");
		}

		return in_item_buy;
	}
	
	
	public boolean execute_ItemSell(boolean in_item_buy, int read_option)
	{
		Item chosen_item = null;
		
		if (read_option == 0)
		{
			in_item_buy = false;
		}
		else if (read_option >= 1 && read_option <= 5)
		{
			chosen_item = this.trader.get_playerItems().get(read_option-1);
			this.sellItem(chosen_item);
		}
		else
		{
			System.out.print("Please choose a valid option.\n");
		}

		return in_item_buy;
	}
	
	
	
	
	
	
	
	
	
	
	public void itemSellStoreSection()
	{
		boolean in_item_sell = true;
		read_option = 0;
		
		while (in_item_sell == true)
		{	
			if (this.trader.get_playerInventory().size() == 0)
			{
				return;
			}
		
			System.out.print("\nHaru: What item would you like to sell:\n\n");
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			
			int option_num = 1;
			for (Map.Entry<Item, Integer> inventory : this.trader.get_playerInventory().entrySet()) 
			{
				
                int item_resale_price = inventory.getKey().getResalePrice();
				System.out.printf("%d) %s - %d left (Cost - %d Gold)\n", option_num, inventory.getKey().getItemName(), inventory.getValue(), item_resale_price);
				option_num += 1;
			}
			System.out.print("0. Go back\n");
			
			try 
			{
				read_option = scan_input.nextInt();
				in_item_sell = execute_ItemSell(in_item_sell, read_option);
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("Please enter a valid option number.\n");
				scan_input.next();
				continue;
			}
		}
	}
	
	
	
	public void itemBuyStoreSection()
	{
		boolean in_item_buy = true;
		read_option = 0;
		
		while (in_item_buy == true)
		{

			System.out.print("\nHaru: What item would you like to buy:\n\n");
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			int option_num = 1;
			for (Item item: this.getItemsForSale())
			{
				System.out.printf("%d. %s (Cost - %d Gold)\n", option_num, item.getItemName(), item.getPrice());
				option_num += 1;
			}
			System.out.print("0. Go back\n");
			
			try 
			{
				read_option = scan_input.nextInt();
				in_item_buy = execute_ItemBuy(in_item_buy, read_option, this.getItemsForSale());
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("Please enter a valid option number.\n");
				scan_input.next();
				continue;
			}
		}
	}
	
	
	
	public boolean buyStoreSection(boolean in_buySection, int read_option)
	{
		if (read_option == 0)
		{
			in_buySection = false;
		}
		else if (read_option == 1)
		{
			this.monsterBuyStoreSection();
			
		}
		else if (read_option == 2)
		{
			this.itemBuyStoreSection();
		}
		else
		{
			System.out.print("Please enter a valid option number.\n");
		}
		return in_buySection;
	}
	
	
	public boolean sellStoreSection(boolean in_sellSection, int read_option)
	{
		if (read_option == 0)
		{
			in_sellSection = false;
		}
		else if (read_option == 1)
		{
			this.monsterSellStoreSection();
			
		}
		else if (read_option == 2)
		{
			if (this.trader.get_playerInventory().size() == 0)
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
		return in_sellSection;
	}
	
	
	public boolean execute_BuyOrSell(boolean in_shop, int chosen_option)
	{
		if (chosen_option == 1)
		{
			boolean in_buySection = true;
			int read_option = 0;
			
			while (in_buySection == true)
			{
				System.out.print("\nHaru: What would you like to buy:\n\n");
				System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
				System.out.print("1. Monsters\n");
				System.out.print("2. Items\n");
				System.out.print("0. Go Back\n");
				
				try 
				{
					read_option = scan_input.nextInt();
					in_buySection = buyStoreSection(in_shop, read_option);
				} 
				catch (InputMismatchException excp) 
				{
					System.out.print("Please enter a valid option number.\n");
					scan_input.next();
					continue;
				}
			}
		}
		else if (chosen_option == 2)
		{
			boolean in_sellSection = true;
			int read_option = 0;
			
			while (in_sellSection == true)
			{
				
				System.out.print("\nHaru: What would you like to sell:\n\n");
				System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
				System.out.print("1. Monsters\n");
				System.out.print("2. Items\n");
				System.out.print("0. Go Back\n");
				
				try 
				{
					read_option = scan_input.nextInt();
					in_sellSection = sellStoreSection(in_shop, read_option);
				} 
				catch (InputMismatchException excp) 
				{
					System.out.print("Please enter a valid option number.\n");
					scan_input.next();
					continue;
				}
			}
		}
		else if (chosen_option == 0)
		{
			System.out.print("\nHaru: Hope to see you again!\n");
			System.out.print("Haru: Goodluck in your battles strong fellow!\n\n");
			in_shop = false;
		}
		else
		{
			System.out.print("\nPlease select a valid option.\n");
		}
		
		return in_shop;
	}
	
	
	
	public void view_shop()
	{
		System.out.print("\nHaru: Welcome to MonsterPod Laboratories!\n");
		System.out.print("Haru: Feel free to explore!\n");
		
		boolean in_shop = true;
		read_option = 0;
		
		while (in_shop == true)
		{
			
			System.out.print("\nHaru: Please pick what you want to do:\n\n");
			System.out.printf("[Available Gold: %d]\n\n", this.trader.getPlayerGold());
			System.out.print("1. Buy\n");
			System.out.print("2. Sell\n");
			System.out.print("0. Exit Shop\n");
			
			try 
			{
				read_option = scan_input.nextInt();
				in_shop = execute_BuyOrSell(in_shop, read_option);
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("Please enter a valid option number.\n");
				scan_input.next();
				continue;
			}
		}
	}
	
	
	public static void main(String[] args)
	{
		Shop new_shop = new Shop();
		new_shop.view_shop();
	}

	

}