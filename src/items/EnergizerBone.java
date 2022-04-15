package items;

import monsters.Monster;

public class EnergizerBone extends Item {

	public EnergizerBone() 
	{
		super("Energizer Bone", "Increases monster's basic attack damage by 5.", 70);
	}
	
	public void useEnergizerBone(Monster monster)
	{
		monster.setDamage(monster.getDamage() + 5);
		System.out.printf("%s's basic attack has increased by 50.\n", monster.getMonsterName());
	}

}
