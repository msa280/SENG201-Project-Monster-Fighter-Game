package items;

import monsters.Monster;
import java.util.Random;

/*
 * Creates an item in the game.
 */
public class CursedSkull extends Item{

	/*
	 * Creates the item and specifies what it does.
	 */
	public CursedSkull() 
	{
		super("Cursed Skull", "May increase or decrease a monsters max health by 30.", 300);
	}
	
	/*
	 * Uses the item.
	 * 
	 * @param monster The monster to use the item on.
	 */
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