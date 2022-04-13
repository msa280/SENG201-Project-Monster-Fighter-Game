package items;

import monsters.Monster;
public class BloodBroth extends Item
{

	public BloodBroth() 
	{
		super("Blood Broth", "Increases monster's health by 50.", 50);
	}
	
	
	public void useBloodBroth(Monster monster)
	{
		monster.setCurrentHealth(monster.getCurrentHealth() + 50);
		System.out.printf("%s's health has increased by 50.\n", monster.getMonsterName());
	}
	
}
