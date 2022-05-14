package items;

import java.util.Random;

import monsters.Monster;

public class Item {
	
	private String itemName;
	private String itemEffect;
	private int price;
	

	public Item(String itemName, String itemEffect, int price)
	{
		this.setItemName(itemName);
		this.setItemEffect(itemEffect);
		this.setPrice(price);
		
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemEffect() {
		return itemEffect;
	}


	public void setItemEffect(String itemEffect) {
		this.itemEffect = itemEffect;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getResalePrice()
	{
		return this.price - 20;
	}
	
	

	public void useItem(Item item, Monster monster)
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
	}
}
	



