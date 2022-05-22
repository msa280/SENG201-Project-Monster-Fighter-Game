package items;

import monsters.Monster;

/*
 * Creates an item in the game.
 */
public class VirilityGem extends Item{
	
	/*
	 * Creates the item and specifies what it does.
	 */
	public VirilityGem() 
	{
		super("Virility Gem", "Increases monster's special attack by 10.", 100);
	}
	
	/*
	 * Uses the item.
	 * 
	 * @param monster The monster to use the item on.
	 */
	public void useVirilityGem(Monster monster)
	{
		monster.setSpecialDamage(monster.getSpecialDamage() + 10);
		System.out.printf("%s's special attack has increased by 10.\n", monster.getMonsterName());
	}

}
