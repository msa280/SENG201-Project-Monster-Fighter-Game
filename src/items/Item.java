package items;

import java.util.Random;

import monsters.Monster;

/*
 * Creates the items in the game and sets their effects and various attributes.
 */
public class Item {

	/*
	 * The name of the item.
	 */
	private String itemName;
	/*
	 * The effect of the item.
	 */
	private String itemEffect;
	/*
	 * The price of the item.
	 */
	private int price;
	
	/*
	 * Creates an item.
	 * 
	 * @param itemName The name of the item.
	 * @param itemEffect The effect of the item.
	 * @param price The price of the item.
	 */
	public Item(String itemName, String itemEffect, int price)
	{
		this.setItemName(itemName);
		this.setItemEffect(itemEffect);
		this.setPrice(price);
		
	}

	/*
	 * Gets the item name.
	 * 
	 * @return Returns the item name.
	 */
	public String getItemName() {
		return itemName;
	}

	/*
	 * Sets the item name.
	 * 
	 * @param itenName The item name.
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	/*
	 * Gets the items effect.
	 * 
	 * @return Returns the items effect.
	 */
	public String getItemEffect() {
		return itemEffect;
	}

	/*
	 * Sets the items effect.
	 * 
	 * @param itemEffect The items effect.
	 */
	public void setItemEffect(String itemEffect) {
		this.itemEffect = itemEffect;
	}

	/*
	 * Gets the price of the item.
	 * 
	 * @return Returns the price.
	 */
	public int getPrice() {
		return price;
	}

	/*
	 * Sets the price of the item.
	 * 
	 * @param price The price.
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/*
	 * Gets the resell price.
	 * 
	 * @return Returns the resell price.
	 */
	public int getResellPrice()
	{
		return this.price - 20;
	}
	
	/*
	 * Gets the image of the item.
	 * 
	 * @return Returns the image (the URL of it to be precise).
	 */
	public String getItemImage()
	{
		String itemImage;
		
		if (this.itemName == "Blood Broth") 
		{
			itemImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\ItemsArtwork\\2.) BloodBroth.png";
		} else if (this.itemName == "Cursed Skull") 
		{
			itemImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\ItemsArtwork\\1.) CursedSkull.png";
		} else if (this.itemName == "Energizer Bone")
		{
			itemImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\ItemsArtwork\\3.) EnergizerBone.png";
		} else if (this.itemName == "Guardian Arch")
		{
			itemImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\ItemsArtwork\\4.) GuardianArch.png";
		} else if (this.itemName == "Virility Gem") 
		{
			itemImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\ItemsArtwork\\5.) VirilityGem.png";
		} else {
			itemImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\ItemsArtwork\\1.) CursedSkull.png";
		}
		return itemImage;
	}
	
	/*
	 * Uses the item.
	 * 
	 * @param item The item.
	 * @param monster The monster the item is being used on.
	 */
	public void useItem(Item item, Monster monster)
	{
		String item_name = item.getItemName();

		if  (item_name == "Blood Broth")
		{
			if (monster.getCurrentHealth() >= monster.getMaxHealth())
			{
				return;
			}
		    else if (monster.getCurrentHealth() <= 0)
			{
				monster.setFaint(false);
				monster.setCurrentHealth(50);
			}
		    else if (monster.getCurrentHealth() + 50 > monster.getMaxHealth())
			{
				monster.setCurrentHealth(monster.getMaxHealth());
			}
			else
			{
				monster.setCurrentHealth(monster.getCurrentHealth() + 50);
			}
		}
		
		else if (item_name == "Energizer Bone")
		{
			monster.setDamage(monster.getDamage() + 5);
		}
		
		else if (item_name == "Cursed Skull")
		{
			Random randNum = new Random();
			int upperbound = 3;
			int deciding_num = randNum.nextInt(upperbound);
			
			if (deciding_num <= 1) // 2/3 chance
			{
				monster.setMaxHealth(monster.getMaxHealth() + 30);
			}
			else // 1/3 chance
			{
				if (monster.getMaxHealth() > 30)
				{
					monster.setMaxHealth(monster.getMaxHealth() - 30);
				}
				else
				{
					monster.setMaxHealth(0);
				}
			}
		}
		
		else if (item_name == "Guardian Arch")
		{
			monster.setHealAmount(monster.getHealAmount() + 20);
		}
		
		else if (item_name == "Virility Gem")
		{
			monster.setSpecialDamage(monster.getSpecialDamage() + 10);
		}
	}
}
	



