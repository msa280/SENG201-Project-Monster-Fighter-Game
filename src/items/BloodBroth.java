package items;

import monsters.Monster;

/*
 * Creates an item in the game.
 */
public class BloodBroth extends Item
{	
	/*
	 * Creates the item and specifies what it does.
	 */
	public BloodBroth() 
	{
		super("Blood Broth", "Increases monster's health by 50.", 50);
	}
	
	/*
	 * Uses the item.
	 * 
	 * @param monster The monster to use the item on.
	 */
	public void useBloodBroth(Monster monster)
	{
		monster.setCurrentHealth(monster.getCurrentHealth() + 50);
		System.out.printf("%s's health has increased by 50.\n", monster.getMonsterName());
	}
	
}
