package items;

import monsters.Monster;

public class VirilityGem extends Item{

	public VirilityGem() 
	{
		super("Virility Gem", "Increases monster's special attack by 10.", 100);
	}
	
	public void useVirilityGem(Monster monster)
	{
		monster.setSpecialDamage(monster.getSpecialDamage() + 10);
		System.out.printf("%s's special attack has increased by 10.\n", monster.getMonsterName());
	}

}
