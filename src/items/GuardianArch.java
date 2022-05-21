package items;

import monsters.Monster;

/*
 * Creates an item in the game.
 */
public class GuardianArch extends Item
{
	
	/*
	 * Creates the item and specifies what it does.
	 */
	public GuardianArch() 
	{
		super("Guardian Arch", "Increases monster's healing by 20.", 150);
	}
	
	/*
	 * Uses the item.
	 * 
	 * @param monster The monster to use the item on.
	 */
	public void useGuardianArch(Monster monster)
	{
		monster.setHealAmount(monster.getHealAmount() + 20);
		System.out.printf("%s's heal amount has increased by 10.\n", monster.getMonsterName());
	}
}