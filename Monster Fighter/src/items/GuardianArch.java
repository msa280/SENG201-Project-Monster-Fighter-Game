package items;

import monsters.Monster;

public class GuardianArch extends Item
{

	public GuardianArch() 
	{
		super("Guardian Arch", "Increases monster's healing by 20.", 150);
	}
	
	public void useGuardianArch(Monster monster)
	{
		monster.setHealAmount(monster.getHealAmount() + 20);
		System.out.printf("%s's heal amount has increased by 10.\n", monster.getMonsterName());
	}

}