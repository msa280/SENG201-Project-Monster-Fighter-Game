package game;
import java.util.ArrayList;
import java.util.Random;

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
public class Shop 
{
	
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
}