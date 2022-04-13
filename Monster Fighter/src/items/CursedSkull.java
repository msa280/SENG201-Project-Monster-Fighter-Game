package items;

import monsters.Monster;
import java.util.Random;

public class CursedSkull extends Item{

	public CursedSkull() 
	{
		super("Cursed Skull", "May increase or decrease a monsters max health by 30.", 300);
	}
	
	public void useCursedSkull(Monster monster)
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

}