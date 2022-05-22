package items;

import monsters.Monster;

/*
 * Creates an item in the game.
 */
public class EnergizerBone extends Item {
	
	/*
	 * Creates the item and specifies what it does.
	 */
	public EnergizerBone() 
	{
		super("Energizer Bone", "Increases monster's basic attack damage by 5.", 70);
	}
	
	/*
	 * Uses the item.
	 * 
	 * @param monster The monster to use the item on.
	 */
	public void useEnergizerBone(Monster monster)
	{
		monster.setDamage(monster.getDamage() + 5);
		System.out.printf("%s's basic attack has increased by 50.\n", monster.getMonsterName());
	}

}
