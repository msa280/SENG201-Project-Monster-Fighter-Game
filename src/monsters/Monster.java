package monsters;

public class Monster {
	
	private String monsterName;
	private String monsterRename;
	private String attackName;
	private String specialAttackName;
	private int maxHealth;
	private int damage;
	private int healAmount;
	private int currentHealth;
	private int specialDamage;
	private int price;
	private boolean isFaint;
	private int attackCount;
	
	
	public Monster(String monsterName, String monsterRename, String attack_name, String special_attack_name, int maxHealth,
			int damage, int healAmount, int currentHealth, int specialDamage, int price, boolean isFaint, int attackCount) 
	{
		this.setMonsterName(monsterName);
		this.setMonsterRename(monsterRename);
		this.setAttackName(attack_name);
		this.setSpecialAttackName(special_attack_name);
		this.setMaxHealth(maxHealth);
		this.setDamage(damage);
		this.setHealAmount(healAmount);
		this.setCurrentHealth(currentHealth);
		this.setSpecialDamage(specialDamage);
		this.setPrice(price);
		this.setFaint(isFaint);
		this.setAttackCount(attackCount);
	}

	public String getMonsterName() {
		return monsterName;
	}

	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealAmount() {
		return healAmount;
	}

	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getSpecialDamage() {
		return specialDamage;
	}

	public void setSpecialDamage(int specialDamage) {
		this.specialDamage = specialDamage;
	}

	public String getAttackName() {
		return this.attackName;
	}

	public void setAttackName(String attackName) {
		this.attackName = attackName;
	}

	public String getSpecialAttackName() {
		return this.specialAttackName;
	}

	public void setSpecialAttackName(String specialAttackName) {
		this.specialAttackName = specialAttackName;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getMonsterRename() {
		return monsterRename;
	}

	public void setMonsterRename(String monsterRename) {
		this.monsterRename = monsterRename;
	}

	public boolean isFaint() {
		return isFaint;
	}

	public void setFaint(boolean isFaint) {
		this.isFaint = isFaint;
	}

	public int getAttackCount() {
		return attackCount;
	}

	public void setAttackCount(int attackCount) {
		this.attackCount = attackCount;
	}
	
	
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
	
	
	
	public int getResalePrice()
	{
		return this.price - 100;
	}
	
	
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
		else 
		{
			imageLink = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\noImage.png";
		}
		return imageLink;
	}
	
	
	
	/*
	 * Displays the monsters statistics.
	 * 
	 * @param monster The chosen monster.
	 * @param monsterName The text pane that displays the statistics.
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
	
}