package monsters;

import game.AudioPlayer;
/*
 * Creates the monsters in the game and sets their various attributes.
 */
public class Monster {
	
	/*
	 * The name of the monster.
	 */
	private String monsterName;
	/*
	 * The new name of the monster if it's being renamed.
	 */
	private String monsterRename;
	/*
	 * The name of the monsters attack.
	 */
	private String attackName;
	/*
	 * The name of the monsters special attack.
	 */
	private String specialAttackName;
	/*
	 * The max health of the monster.
	 */
	private int maxHealth;
	/*
	 * The damage that the monster deals.
	 */
	private int damage;
	/*
	 * How much the monster heals when sleeping.
	 */
	private int healAmount;
	/*
	 * The monsters current health.
	 */
	private int currentHealth;
	/*
	 * The damage of the monsters special attack.
	 */
	private int specialDamage;
	/*
	 * The price to buy the monster if it's in the shop.
	 */
	private int price;
	/*
	 * Whether the monster is currently fainted or not.
	 */
	private boolean isFaint;
	/*
	 * How many times the monster has attacked.
	 */
	private int attackCount;
	/*
	 * The sound of the monster.
	 */
	private AudioPlayer monsterSound = new AudioPlayer();
	
	/*
	 * The monster constructor where it is initialized with all of its various attributes.
	 * 
	 * @param monsterName The name of the monster.
	 * @param monsterRename The new name of the monster if it's being renamed.
	 * @param attackName The name of the monsters attack.
	 * @param specialAttackName The name of the monsters special attack.
	 * @param maxHealth The max health of the monster.
	 * @param damage The damage that the monster deals.
	 * @param healAmount How much the monster heals when it sleeps.
	 * @param currentHealth The monsters current health.
	 * @param specialDamage The damage of the monsters special attack.
	 * @param price The price to buy the monster if it's in the shop.
	 * @param isFaint Whether the monster is currently fainted or not.
	 * @param attackCount How many times the monster has attacked.
	 */
	public Monster(String monsterName, String monsterRename, String attackName, String specialAttackName, int maxHealth,
			int damage, int healAmount, int currentHealth, int specialDamage, int price, boolean isFaint, int attackCount) 
	{
		this.setMonsterName(monsterName);
		this.setMonsterRename(monsterRename);
		this.setAttackName(attackName);
		this.setSpecialAttackName(specialAttackName);
		this.setMaxHealth(maxHealth);
		this.setDamage(damage);
		this.setHealAmount(healAmount);
		this.setCurrentHealth(currentHealth);
		this.setSpecialDamage(specialDamage);
		this.setPrice(price);
		this.setFaint(isFaint);
		this.setAttackCount(attackCount);
	}

	/*
	 * Gets the monsters name.
	 * 
	 * @return Returns the monsters name.
	 */
	public String getMonsterName() {
		return monsterName;
	}

	/*
	 * Sets the monsters name.
	 * 
	 * @param monsterName The name of the monster.
	 */
	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}
	
	/*
	 * Gets the max health of the monster.
	 * 
	 * @return Returns the max health of the monster.
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/*
	 * Sets the max health of the monster.
	 * 
	 * @param maxHealth The max health of the monster.
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/*
	 * Gets the damage that the monster deals.
	 * 
	 * @return Returns the damage that the monster deals.
	 */
	public int getDamage() {
		return damage;
	}
	
	/*
	 * Sets the damage that the monster deals.
	 * 
	 * @param damage The damage that the monster deals.
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/*
	 * Gets the amount the monster heals when sleeping.
	 * 
	 * @return Returns the amount the monster heals.
	 */
	public int getHealAmount() {
		return healAmount;
	}

	/*
	 * Sets the amount the monster heals when sleeping.
	 * 
	 * @param healAmount The amount the monster heals.
	 */
	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}

	/*
	 * Gets the monsters current health.
	 * 
	 * @return Returns the current health of the monster.
	 */
	public int getCurrentHealth() {
		return currentHealth;
	}

	/*
	 * Sets the monsters current health.
	 * 
	 * @param currentHealth The current health of the monster.
	 */
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	/*
	 * Gets the amount of damage that the monsters special attack deals.
	 * 
	 * @return Returns the amount of special damage.
	 */
	public int getSpecialDamage() {
		return specialDamage;
	}

	/*
	 * Sets the amount of damage that the monsters special attack deals.
	 * 
	 * @param specialDamage The amount of special damage.
	 */
	public void setSpecialDamage(int specialDamage) {
		this.specialDamage = specialDamage;
	}

	/*
	 * Gets the name of the monsters attack.
	 * 
	 * @return Returns the name of the monsters attack.
	 */
	public String getAttackName() {
		return this.attackName;
	}

	/*
	 * Sets the name of the monsters attack.
	 * 
	 * @param attackName The name of the attack.
	 */
	public void setAttackName(String attackName) {
		this.attackName = attackName;
	}

	/*
	 * Gets the name of the monsters special attack.
	 * 
	 * @return Returns the name of the monsters special attack.
	 */
	public String getSpecialAttackName() {
		return this.specialAttackName;
	}

	/*
	 * Sets the name of the monsters special attack.
	 * 
	 * @param specialAttackName The name of the monsters special attack.
	 */
	public void setSpecialAttackName(String specialAttackName) {
		this.specialAttackName = specialAttackName;
	}
	
	/*
	 * Gets the price of the monster.
	 * 
	 * @return Returns the price of the monster.
	 */
	public int getPrice() {
		return price;
	}

	/*
	 * Sets the price of the monster.
	 * 
	 * @param price The price of the monster.
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/*
	 * Gets the new name of the monster.
	 * 
	 * @return Returns the new name of the monster.
	 */
	public String getMonsterRename() {
		return monsterRename;
	}

	/*
	 * Sets the new name of the monster.
	 * 
	 * @param monsterRename The new name of the monster.
	 */
	public void setMonsterRename(String monsterRename) {
		this.monsterRename = monsterRename;
	}
	
	/*
	 * Whether the monster is currently fainted or not.
	 * 
	 * @return Returns whether the monster is currently fainted or not.
	 */
	public boolean isFaint() {
		return isFaint;
	}
	
	/*
	 * Sets whether the monster is currently fainted or not.
	 * 
	 * @param isFaint Whether the monster is currently fainted.
	 */
	public void setFaint(boolean isFaint) {
		this.isFaint = isFaint;
	}

	/*
	 * Gets the number of times the monster has attacked.
	 * 
	 * @return Returns the number of times the monster has attacked.
	 */
	public int getAttackCount() {
		return attackCount;
	}

	/*
	 * Sets the number of times the monster has attacked.
	 * 
	 * @param attackCount The number of times the monster has attacked.
	 */
	public void setAttackCount(int attackCount) {
		this.attackCount = attackCount;
	}
	
	/*
	 * Heals the monster.
	 */
	public void healUp()
	{
		if (this.currentHealth + this.healAmount <= this.maxHealth)
		{
			this.currentHealth += this.healAmount;
		}
		else
		{
			this.currentHealth = this.maxHealth;
		}
	}
	
	/*
	 * Gets the resell price of the monster.
	 * 
	 * @return Returns the resell price.
	 */
	public int getResellPrice()
	{
		return this.price - 100;
	}
	
	/*
	 * Gets whether the monsters special attack is available or not.
	 * 
	 * @return Returns true if the special attack is available, else false.
	 */
	public boolean getSpecialAttackAvailable()
	{
		if (this.getAttackCount() >= 3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*
	 * Makes the monsters sound.
	 */
	public void makeSound()
	{
		if (this.monsterName == "Venomhound")
		{
			this.monsterSound.playSoundOnce("Monster Audio\\Venomhound.wav");
		}
		else if (this.monsterName == "Soilscreamer")
		{
			this.monsterSound.playSoundOnce("Monster Audio\\Soilscreamer.wav");
		}
		else if (this.monsterName == "Mornpest")
		{
			this.monsterSound.playSoundOnce("Monster Audio\\Mornpest.wav");
		}
		else if (this.monsterName == "Cavernfreak")
		{
			this.monsterSound.playSoundOnce("Monster Audio\\Cavernfreak.wav");
		}
		else if (this.monsterName == "Hollowtree")
		{
			this.monsterSound.playSoundOnce("Monster Audio\\Hollowtree.wav");
		}
		else if (this.monsterName == "ManicBoy")
		{
			this.monsterSound.playSoundOnce("Monster Audio\\Manicboy.wav");
		}
		else if (this.monsterName == "Magmataur")
		{
			this.monsterSound.playSoundOnce("Monster Audio\\Magmataur.wav");
		}
		else if (this.monsterName == "ShapeShifter")
		{
			this.monsterSound.playSoundOnce("Monster Audio\\Shapeshifter.wav");
		}
		else if (this.monsterName == "SkullDiablo")
		{
			this.monsterSound.playSoundOnce("Monster Audio\\Skulldiablo.wav");
		}
		else if (this.monsterName == "SleepDemon")
		{
			this.monsterSound.playSoundOnce("Monster Audio\\Sleepdemon.wav");
		}
		else if (this.monsterName == "SpineEater")
		{
			this.monsterSound.playSoundOnce("Monster Audio\\Spineeater.wav");
		}
	}
	
	/*
	 * Gets the image of the monster.
	 * 
	 * @return Returns the image of the monster (the URL to it, more precisely).
	 */
	public String getMonsterImage()
	{
		String imageLink;
		if (this.monsterName == "Venomhound") 
		{
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\1.) VenomHound.gif";
		} 
		else if (this.monsterName == "Soilscreamer") 
		{
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\2.) Soilscreamer.gif";
		}
		else if (this.monsterName == "Mornpest") 
		{
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\3.) Mornpest.gif";
		} 
		else if (this.monsterName == "Cavernfreak") 
		{
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\4.) Cavernfreak.gif";
		} 
		else if (this.monsterName == "Hollowtree") 
		{
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\5.) Hollowtree.gif";
		} 
		else if (this.monsterName == "Magmataur") 
		{
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\6.) Magmataur.gif";
		} 
		else if (this.monsterName == "ManicBoy")
		{
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\7.) Manicboy.gif";
		}
		else if (this.monsterName == "ShapeShifter")
		{
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\8.) Shapeshifter.gif";
		}
		else if (this.monsterName == "SkullDiablo")
		{
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\9.) Skulldiablo.gif";
		}
		else if (this.monsterName == "SleepDemon")
		{
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\10.) Sleepdemon.gif";
		}
		else
		{
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\11.) Spineeater.gif";
		}
		return imageLink;
	}
	
	/*
	 * Levels the monster up.
	 */
	public void levelUp()
	{
		this.currentHealth = this.maxHealth;
		this.damage += 5;
		this.specialDamage += 5;
	}
	
	
	
	/*
	 * Displays the monsters statistics.
	 * 
	 * @return Returns the monster statistics display.
	 */
	public String displayMonsterStats() 
	{
		String a = "Name: %s\n".formatted(this.pickMonsterName());
		String b = "Attack - (%s: %d)\n".formatted(this.attackName, this.damage);
		String c = "Special Attack - (%s: %d)\n".formatted(this.specialAttackName, this.specialDamage);
		String d1 = "Current Health: %d\n".formatted(this.currentHealth);
		String d = "Maximum Health: %d\n".formatted(this.maxHealth);
		String e = "Heal Amount: %d\n".formatted(this.healAmount);
		
		String monsterDetails = a + b + c + d + d1 + e;
		return monsterDetails;
	}
	
	/*
	 * Picks the monsters name at the start of the game.
	 * 
	 * @return Returns the monsters default name, or the name the player gave
	 * them based on what the player chose.
	 */
	public String pickMonsterName() 
	{
		if (this.monsterRename == null)
		{
			return this.monsterName;
		}
		else
		{
			return this.monsterRename;
		}
	}
	
}