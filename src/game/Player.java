package game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import items.Item;
import monsters.Monster;



public class Player {
	
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
	
	
	private String selectedAvatar;
	
	/* 
	 * Gets the current amount of gold the player has.
	 * 
	 * @return Returns the players gold value.
	 */
	public int getPlayerGold() {
		return playerGold;
	}
	
	/* 
	 * Sets the amount of gold the player will start with. 
	 * 
	 * @param playerGold The amount of gold the player will start with.
	 */
	public void setPlayerGold(int playerGold) {
		this.playerGold = playerGold;
	}
	
	/* 
	 * Sets the current day, and will increment as each day passes.
	 * 
	 * @param currentDay The current day for the player.
	 */
	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}
	
	/* 
	 * Gets what the current day is.
	 * 
	 * @return Returns the value of currentDay.
	 */
	public int getCurrentDay() {
		return currentDay;
	}
	
	/* 
	 * Sets the amount of days remaining.
	 * 
	 * @param currentDay The current day.
	 * @game An instance of the Game class.
	 * 
	 */
	public void setDaysRemaining(int currentDay, Game game) {
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
	 * Gets the players current monster team.
	 * 
	 * @return Returns an ArrayList containing the players monsters.
	 */
	
	
	public Shop getShop()
	{
		return this.shop;
	}
	
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
	public Battle getBattle() {
		return battle;
	}
	
	/*
	 * Sets the Battle instance.
	 * 
	 * @param battle An instance of the Battle class.
	 */
	public void setBattle(Battle battle) {
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
	public boolean isReadyForBattle() {
		return readyForBattle;
	}
	
	/*
	 * Sets the value of readyForBattle.
	 * 
	 * @param readyForBattle Whether or not the player is ready for battle.
	 */
	public void setReadyForBattle(boolean readyForBattle) {
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

	public String getLastUpdate() 
	{
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) 
	{
		this.lastUpdate = lastUpdate;
	}

	public String getSelectedAvatar()
	{
		return selectedAvatar;
	}

	public void setSelectedAvatar(String selectedAvatar) 
	{
		this.selectedAvatar = selectedAvatar;
	}
	
	
	
	/*
	 * Checks if the player has any monsters that can battle (haven't fainted).
	 * 
	 * @return Returns true if the player has any ready monsters, else false.
	 */
	public boolean monsterReadyBattle()
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
	
	
	
	
	public void initialize(Game game)
	{
		this.setGame(game);
		
		Shop shop = new Shop();
		Battle battle = new Battle();
		
		shop.initializeShop();
		battle.generateBattles();
		
		this.setShop(shop);
		this.setBattle(battle);
		
		int startingGold = (int)(1000 * this.game.getGameDifficulty());
		this.playerGold = startingGold;
		this.currentDay = 1;
		this.setDaysRemaining(startingGold, this.game);
		this.playersTeam.add(this.game.getSelectedMonster());
	}
	
	
	
	
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
	 * Heals the players monster party each by their own healing stat and unfaints them,=.
	 * Randomizes shop and battles again.
	 * 
	 */
	public void playerSleep()
	{
		this.currentDay += 1;
		this.daysRemaining -= 1;
		
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
			
			// update items and monsters in shop
			this.shop.randomGenerateItems();
			this.shop.randomGenerateMonsters();
			
			// update all battles
			this.battle.generateBattles();	
		}
	}
}






