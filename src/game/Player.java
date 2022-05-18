package game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import items.Item;
import monsters.Monster;


/*
 * Creates the player and all of their main attributes.
 */
public class Player 
{
	/*
	 * Updates the player on what is happening in the game via a message in the 
	 * form of a string.
	 */
	private String lastUpdate = "";
	/* 
	 * Players gold.
	 */
	private int playerGold = 0;
	/* 
	 * Players inventory.
	 */
	private Map<Item, Integer> playerInventory = new HashMap<Item, Integer>();
	/* 
	 * Players items.
	 */
	private ArrayList<Item> playersItems = new ArrayList<Item>();
	/* 
	 * Players team of monsters.
	 */
	private ArrayList<Monster> playersTeam = new ArrayList<Monster>();
	/* 
	 * An instance of the Battle class.
	 */
	private Battle battle;
	/* 
	 * An instance of the Shop class.
	 */
	private Shop shop;
	/* 
	 * An instance of the Game class.
	 */
	private Game game;
	/* 
	 * The current day.
	 */
	private int currentDay;
	/* 
	 * The days remaining in the game.
	 */
	private int daysRemaining;
	/* 
	 * Whether or not the player is ready for battle.
	 */
	private boolean readyForBattle;
	/*
	 * The experience points earned by the player
	 */
	private int xpPoints;
	/*
	 * The total gold earned by the player.
	 */
	private int totalGoldGained;
	/*
	 * Battles won in one day.
	 */
	private int battlesWon;
	/*
	 * The players selected avatar.
	 */
	private String selectedAvatar;
	
	/* 
	 * Gets the current amount of gold the player has.
	 * 
	 * @return Returns the players gold value.
	 */
	public int getPlayerGold() 
	{
		return playerGold;
	}
	
	/* 
	 * Sets the amount of gold the player will start with. 
	 * 
	 * @param playerGold The amount of gold the player will start with.
	 */
	public void setPlayerGold(int playerGold) 
	{
		this.playerGold = playerGold;
	}
	
	/* 
	 * Sets the current day, and will increment as each day passes.
	 * 
	 * @param currentDay The current day for the player.
	 */
	public void setCurrentDay(int currentDay)
	{
		this.currentDay = currentDay;
	}
	
	/* 
	 * Gets what the current day is.
	 * 
	 * @return Returns the value of currentDay.
	 */
	public int getCurrentDay() 
	{
		return currentDay;
	}
	
	/* 
	 * Sets the amount of days remaining.
	 * 
	 * @param currentDay The current day.
	 * @game An instance of the Game class.
	 * 
	 */
	public void setDaysRemaining() 
	{
		this.daysRemaining = this.game.getGameLength() - this.currentDay;
	}
	
	/* 
	 * Gets the amount of days remaining.
	 * 
	 * @return The value of daysRemaining.
	 */
	public int getDaysRemaining() {
		return this.daysRemaining;
	}
	/* 
	 * Gets the length of the players monster team.
	 * 
	 * @return Returns the integer value of the length of his team (how many Monsters there are in it).
	 */
	public int getPlayersTeamLength()
	{
		return this.playersTeam.size();
	}
	
	/* 
	 * Creates the game.
	 * 
	 * @param game An instance of the Game class.
	 */
	public void setGame(Game game)
	{
		this.game = game; 
	}
	
	/* 
	 * Gets the game instance.
	 * 
	 * @return Returns the game instance.
	 */
	public Game getGame()
	{
		return this.game;
	}
	
	/* 
	 * Creates the shop.
	 * 
	 * @param newShop An instance of the Shop class.
	 */
	public void setShop(Shop newShop)
	{
		this.shop = newShop;
	}
	
	/* 
	 * Gets the players current items.
	 * 
	 * @return Returns an ArrayList of the players items.
	 */
	public ArrayList<Item> getPlayerItems()
	{
		return this.playersItems;
	}	
	
	/*
	 * Gets the current instance of the Shop class.
	 * 
	 * @return Returns the current instance of shop.
	 */
	public Shop getShop()
	{
		return this.shop;
	}
	
	/* 
	 * Gets the players current monster team.
	 * 
	 * @return Returns an ArrayList containing the players monsters.
	 */
	public ArrayList<Monster> getPlayerMonsters()
	{
		return this.playersTeam;
	}
	
	/*
	 * Gets the players current inventory.
	 * 
	 * @return Returns a Map containing the players inventory.
	 */
	public Map<Item, Integer> getPlayerInventory()
	{
		return this.playerInventory;
	}
	
	/*
	 * Gets a Battle instance.
	 * 
	 * @return Returns an instance of the Battle class.
	 */
	public Battle getBattle() 
	{
		return battle;
	}
	
	/*
	 * Sets the Battle instance.
	 * 
	 * @param battle An instance of the Battle class.
	 */
	public void setBattle(Battle battle)
	{
		this.battle = battle;
	}
	
	/*
	 * Gets the difficulty of the game that was set by the player,
	 * 
	 * @return Returns the value of gameDifficulty.
	 */
	public double getPlayerDifficulty()
	{
		return this.game.getGameDifficulty();
	}
	
	/*
	 * Adds a new monster to the players monster team.
	 * 
	 * @param monster The monster that is to be added.
	 */
	public void addToTeam(Monster monster)
	{
		this.playersTeam.add(monster);
	}
	
	/*
	 * Gets whether or not the player is ready for battle.
	 * 
	 * @return Returns true if at least one of the players monsters have not fainted, else fale.
	 */
	public boolean isReadyForBattle() 
	{
		return readyForBattle;
	}
	
	/*
	 * Sets the value of readyForBattle.
	 * 
	 * @param readyForBattle Whether or not the player is ready for battle.
	 */
	public void setReadyForBattle(boolean readyForBattle) 
	{
		this.readyForBattle = readyForBattle;
	}
	
	/*
	 * Creates the player instance, and sets and outputs their gold.
	 */
	public static void main(String[] args)
	{
		Player player = new Player();
		player.setPlayerGold(1);
		System.out.print(player.getPlayerGold());
	}
	/*
	 * Gets the last update for the player.
	 * 
	 * @return Returns the value of lastUpdate.
	 */
	public String getLastUpdate() 
	{
		return lastUpdate;
	}

	/*
	 * Sets the last update or the player.
	 *
	 * @param lastUpdate The new lastUpdate.
	 */
	public void setLastUpdate(String lastUpdate) 
	{
		this.lastUpdate = lastUpdate;
	}
	
	/*
	 * Gets the currently selected avatar.
	 * 
	 * @return Returns the avatar.
	 */
	public String getSelectedAvatar()
	{
		return selectedAvatar;
	}
	
	/*
	 * Sets the avatar.
	 * 
	 * @param selectedAvatar The avatar to be set.
	 */
	public void setSelectedAvatar(String selectedAvatar) 
	{
		this.selectedAvatar = selectedAvatar;
	}
	
	
	 /*
     * Sets the xp points of the player
     * 
     *@param xpPoints
     */
	public void setXpPoints(int xpPoints)
	{
		this.xpPoints = xpPoints;
	}

	/*
	 * Gets the xp points of the player
	 */
	public int getXpPoints()
	{
		return this.xpPoints;
	}


	/*
	 * Sets the total gold gained by the player
	 * 
	 * @param totalGoldGained
	 */
	public void setTotalGoldGained(int totalGoldGained) 
	{
		this.totalGoldGained = totalGoldGained;
	}

	/*
	 * Gets the total gold gained by the player
	 */
	public int getTotalGoldGained() 
	{
		return this.totalGoldGained;
	}

	/*
	 * Gets the battles won during that day
	 */
	public int getBattlesWon() 
	{
		return battlesWon;
	}

	/*
	 * Sets the battles won during that day
	 * 
	 * @param battlesWon
	 */
	public void setBattlesWon(int battlesWon)
	{
		this.battlesWon = battlesWon;
	}
	
	
	
	
	/*
	 * Checks if the player has any monsters that can battle (haven't fainted).
	 * 
	 * @return Returns true if the player has any ready monsters, else false.
	 */
	public boolean canFight()
	{
		for (Monster monster: this.playersTeam)
		{
			if (monster.isFaint() == false)
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Initializes the game.
	 * 
	 * @param game An instance of the Game class.
	 */
	public void initialize(Game game)
	{
		this.setGame(game);
		int startingGold = (int)(4000 - this.game.getGameDifficulty() * 1000);
		this.playerGold = startingGold;
		this.totalGoldGained += startingGold;
		this.currentDay = 1;
		this.setDaysRemaining();
		this.playersTeam.add(this.game.getSelectedMonster());
		
		Battle battle = new Battle();
		battle.setDifficutly(this.getPlayerDifficulty());
		battle.setCurrentDay(this.currentDay);
		battle.generateBattles();
		this.battle = battle;
		
		Shop shop = new Shop();
		shop.setDay(this.currentDay);
		shop.initializeShop();
		this.shop = shop;
		
	}
	
	/*
	 * Uses one of the players items on a monster from their team.
	 * 
	 * @param item The item to be used.
	 * @param monster The monster that the item will be used on.
	 * 
	 * @return Returns true if quantity left < 2, else false.
	 */
	public boolean useItem(Item item, Monster monster)
	{
		String item_name = item.getItemName();
		
		if  (item_name == "Blood Broth")
		{
			monster.setCurrentHealth(monster.getCurrentHealth() + 50);
			System.out.printf("%s's health has increased by 50.\n", monster.getMonsterName());
		}
		
		else if (item_name == "Energizer Bone")
		{
			monster.setDamage(monster.getDamage() + 5);
			System.out.printf("%s's basic attack has increased by 50.\n", monster.getMonsterName());
		}
		
		else if (item_name == "Cursed Skull")
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
		
		else if (item_name == "Guardian Arch")
		{
			monster.setHealAmount(monster.getHealAmount() + 20);
			System.out.printf("%s's heal amount has increased by 10.\n", monster.getMonsterName());
		}
		
		else if (item_name == "Virility Gem")
		{
			monster.setSpecialDamage(monster.getSpecialDamage() + 10);
			System.out.printf("%s's special attack has increased by 10.\n", monster.getMonsterName());
		}
		

		int quantityLeft = this.playerInventory.get(item);
		
		if (quantityLeft >= 2)
		{
			int newQuantity = quantityLeft - 1;
			this.playerInventory.remove(item);
			this.playerInventory.put(item, newQuantity);
			return false;
		} 
		else 
		{
			this.playerInventory.remove(item);
			return true;
		}
	}
	
	/*
	 * Purchases an item from the store.
	 * 
	 * @param item The item to be purchased
	 * 
	 * @return Returns true if the player has enough gold to buy the item,
	 * and successfully purchases it, else false. 
	 */
	public boolean buyItem(Item item)
	{
		if (this.playerGold < item.getPrice()) {
			return false;
		}
		else {
			this.playerGold -= item.getPrice();
			if (this.playerInventory.containsKey(item)) 
			{
				int newValue = this.playerInventory.get(item) + 1;
				this.playerInventory.remove(item);
				this.playerInventory.put(item, newValue);
				System.out.print(this.playerInventory);
			} else 
			{
				this.playerInventory.put(item, 1);
			}
			return true;
		}
	}
	
	/*
	 * Sells an item to the shop.
	 * 
	 * @param item The item to be sold.
	 */
	public void sellItem(Item item)
	{
		String update = "";
		update += ("%s sold!\n".formatted(item.getItemName()));
		
		this.playerGold += item.getResalePrice();
		
		if (this.playerInventory.get(item) == 1)
		{
			this.playerInventory.remove(item);
			update += ("%s has been removed from the inventory.\n".formatted(item.getItemName()));
		} 
		else
		{
			this.playerInventory.put(item, this.playerInventory.get(item) - 1);
			update += ("You have sold 1 %s.\n".formatted(item.getItemName()));
		}
		
		update += ("%d Gold has been given to you.\n".formatted(item.getResalePrice()));
		this.lastUpdate = update;
	}
	
	
	
	/*
	 * Sells a monster to the shop.
	 * 
	 * @param monster The monster to be sold.
	 */
	public void sellMonster(Monster monster)
	{
		this.playerGold += monster.getResalePrice();
		this.playersTeam.remove(monster);
	}
	
	/*
	 * Purchases a monster from the store.
	 * 
	 * @param monster The monster to be purchased.
	 * 
	 * @return Returns an error message in the form of a string, or "No Error" in the case
	 * where the purchase succeeds.
	 */
	public String buyMonster(Monster monster)
	{
		String update;
		if (this.playerGold < monster.getPrice())
		{
			update = "You don't have enough gold to buy %s!\n".formatted(monster.getMonsterName());
			update = "No_Gold";
		}
		else if (this.playersTeam.size() == 4)
		{		
			update = "Team_Full";
		}
		else
		{
			this.playerGold -= monster.getPrice();
			this.playersTeam.add(monster);
			update = "No Error";
		}
		return update;
	}
	
	/*
	 * Puts the player and their team to sleep. Increases the current day by one,
	 * and decreases day remaining by one. If days remaining is 0, the game ends.
	 * Heals the players monster party each by their own healing statistic and unfaints them.
	 * Randomizes shop and battles again.
	 * 
	 */
	public void playerSleep()
	{
		String update = "";
		
		this.currentDay += 1;
		this.daysRemaining -= 1;
		
		update += "Good Morning %s!\n".formatted(this.game.getPlayerName());
		
		if (this.daysRemaining == 0)
		{
			this.game.setGameOver(true);
		}
		else
		{
			// heal monsters by healAmount not exceeding maxHealth
			for (Monster monster: this.playersTeam)
			{
				monster.healUp();
				monster.setFaint(false);
			}
			update += "All your monsters have healed up overnight!\n";
			
			// update items and monsters in shop
			this.shop.setDay(this.currentDay);
			this.shop.randomGenerateItems();
			this.shop.randomGenerateMonsters();
			
			update += "The shop has been refreshed!\n";
			
			// New monsters unlocked message
			if (this.currentDay == 5 || this.currentDay == 9 || this.currentDay == 12) 
			{
				update += "New monsters unlocked in shop!\n";
			}
			
			// update all battles
			this.battle.generateBattles();	
			
			update += "New battles have been generated!\n";
			
			
			// Runs a chance of a monster leaving overnight.
			update += monsterLeaveOvernight();
			
			// Runs a chance of monster joining overnight.
			update += monsterJoinOvernight();
			
			// Runs a chance of a monster leveling up overnight.
			update += monsterLevelUpOvernight();
			
			
			// resets the battles won in a specific day back to 0.
			this.battlesWon = 0;
		}
		
		this.lastUpdate = update;
	}
	
	
	/*
	 * It controls the chances of a monster leaving overnight. The chances are low 
	 * and are affected by wether the monster fainted that day or not.
	 * 
	 */
	public String monsterLeaveOvernight()
	{
		String update = "";
		
		// Selecting a random monster in team
		Random randNum = new Random();
		int upperbound = this.playersTeam.size();
		int index = 0;
		if (upperbound == 0)
		{
			return update;
		}
		else
		{
			index = randNum.nextInt(upperbound); 
		}
		
		// Creating a random chance for monster to leave from 0-9
		int chance = randNum.nextInt(10);
		
		Monster monster = this.playersTeam.get(index);
		
		if (monster.isFaint() == true)
		{
			if (chance <= 1)
			{
				this.playersTeam.remove(monster);  // if monster fainted that day, 2/10 chance of monster leaving the team.
				update = "OH NO! %s left the team overnight!\n".formatted(monster.pickMonsterName());
			}	
		}
		else
		{
			if (chance == 0)
			{
				this.playersTeam.remove(monster); // // if monster didn't faint that day, 1/10 chance of monster leaving the team.
				update = "OH NO! %s left the team overnight!\n".formatted(monster.pickMonsterName());
			}
		}
		
		return update;
	}
	
	
	/*
	 * Controls the chances of a random monster joining the team overnight.
	 * Chances increase if the team size is less.
	 */
	public String monsterJoinOvernight()
	{
		String update = "";
		
		// Selecting a random monster in team
		Random randNum = new Random();
		int teamSize = this.playersTeam.size();
	
		// Creating a random chance for monster to join from 0-9
		int chance = randNum.nextInt(10);
		
		// getting a random number from the available monsters and picking a random monster to join overnight.
		int randomMonster = randNum.nextInt(this.shop.getMonstersForSale().size());
		Monster monster = this.shop.getMonstersForSale().get(randomMonster);
		
		if (teamSize == 0)
		{
			if (chance <= 3) 
			{
				this.playersTeam.add(monster);  // if player team is empty, there is a 40% chance of monster joining overnight
				update = "OMG! A wild %s joined the team overnight!\n".formatted(monster.getMonsterName());
			}	
		}
		else if (teamSize == 1)
		{
			if (chance <= 2)
			{
				this.playersTeam.add(monster); // // if team size is 1, 30% chance of monster joining the team overnight!
				update = "OMG! A wild %s joined the team overnight!\n".formatted(monster.getMonsterName());
			}
		}
		else if (teamSize == 2)
		{
			if (chance <= 1)
			{
				this.playersTeam.add(monster); // // if team size is 2, 20% chance of monster joining the team overnight!
				update = "OMG! A wild %s joined the team overnight!\n".formatted(monster.getMonsterName());
			}
		}
		else if (teamSize == 3)
		{
			if (chance == 0)
			{
				this.playersTeam.add(monster); // // if team size is 1, 10% chance of monster joining the team overnight!
				update = "OMG! A wild %s joined the team overnight!\n".formatted(monster.getMonsterName());
			}
		}
		
		return update;
	}
	
	
	/*
	 * Control the chances of a monster leveling up overnight.
	 * The chances of a monster levelling up increases with the battles won during that day.
	 */
	public String monsterLevelUpOvernight()
	{
		String update = "";
		
		// Creating a random chance for monster to level up from 0-9
		Random randNum = new Random();
		int chance = randNum.nextInt(10);
		
		// selecting a random monster to level up
		int upperbound = this.playersTeam.size();
		int index = 0;
		
		if (upperbound == 0)
		{
			return update;
		}
		else
		{
			index = randNum.nextInt(upperbound); 
		}
		
		Monster monster = this.playersTeam.get(index);
		
		if (this.battlesWon == 1)
		{
			if (chance == 0)
			{
				monster.levelUp();  // 10% chance of monster leveling up if only 1 battle won
				update = "%s leveled up overnight!\n".formatted(monster.pickMonsterName());
			}
		}
		else if (this.battlesWon == 2)
		{
			if (chance <= 1)
			{
				monster.levelUp();  // 20% chance of monster leveling up if 2 battles won
				update = "%s leveled up overnight!\n".formatted(monster.pickMonsterName());
			}
		}
		else if (this.battlesWon == 3)
		{
			if (chance <= 2)
			{
				monster.levelUp();  // 30% chance of monster leveling up if 3 battles won
				update = "%s leveled up overnight!\n".formatted(monster.pickMonsterName());
			}
		}
		else if (this.battlesWon == 4)
		{
			if (chance <= 3)
			{
				monster.levelUp();  // 40% chance of monster leveling up if 4 battles won
				update = "%s leveled up overnight!\n".formatted(monster.pickMonsterName());
			}
		}
		else if (this.battlesWon == 5)
		{
			if (chance <= 5)
			{
				monster.levelUp();  // 50% chance of monster leveling up if 5 battles won
				update = "%s leveled up overnight!\n".formatted(monster.pickMonsterName());
			}
		}
		
		return update;
		
	}
	
	
	/*
	 * Once the game is over, it prepares the end game result text.
	 */
	public String getGameOverResult()
	{
		String a = "Player Name: %s\n".formatted(this.game.getPlayerName());
		String b = "Game Duration: %d\n".formatted(this.game.getGameLength());
		String c = "Gold Gained: %d\n".formatted(this.totalGoldGained);
		String d = "XP Points Earned: %d\n".formatted(this.xpPoints);
		return (a + b + c + d);
	}
}






