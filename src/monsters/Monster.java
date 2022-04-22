package monsters;

import java.util.Scanner;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

public class Monster {
	
	private Scanner scanInput = new Scanner(System.in);
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
			int damage, int healAmount, int currentHealth, int specialDamage, int price, boolean isFaint, int attackCount) {
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
		return attackName;
	}

	public void setAttackName(String attackName) {
		this.attackName = attackName;
	}

	public String getSpecialAttackName() {
		return specialAttackName;
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
	
	
	public boolean specialAttackAvailable()
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
	
	
	
	
	
	
	public boolean checkName(boolean numOrSpecialChar, boolean nameCheckPassed, int nameLength, String scannedName) 
	{
		if (numOrSpecialChar) 
		{
			System.out.println(scannedName + " contains numbers or special characters!\n");
			System.out.println("Please choose a different monster name.\n");
		} 
		else if (nameLength < 3) 
		{
			System.out.println(scannedName + " is too short!\n");
			System.out.println("Please choose a longer monster name.\n");
		} 
		else if (nameLength > 15) 
		{
			System.out.println(scannedName + " is too long!\n");
			System.out.println("Please choose a shorter monster name.\n");
		}
		else 
		{
			nameCheckPassed = true;
		}
		return nameCheckPassed;
	}
	
	
	public void askMonsterName() 
	{
		
		Pattern pattern = Pattern.compile("[^a-zA-Z]");
		String scannedName = null;
		Matcher matcher = null;
		int nameLength = 0;
		boolean numOrSpecialChar = false;
		boolean nameCheckPassed = false;
		
		while (nameCheckPassed == false) 
		{	
			System.out.print("\nRename your monster: ");
			scannedName = scanInput.nextLine();
			nameLength = scannedName.length();
			matcher = pattern.matcher(scannedName);
			numOrSpecialChar = matcher.find();
			nameCheckPassed = checkName(numOrSpecialChar, nameCheckPassed, nameLength, scannedName);
		}
		this.setMonsterRename(scannedName);
	}
	
	public String toString()
	{
		String spacing = "\n\n\n";
		String a0 = "_____________Monster Stats___________\n\n";
		String a = "Name: %s\n".formatted(this.pickMonsterName());
		String a1 = "_____________________________________\n";
		String b = "Attack Name: %s\n".formatted(this.attackName);
		String b1 = "Attack Damage: %d\n".formatted(this.damage);
		String c = "Special Attack Name: %s\n".formatted(this.specialAttackName);
		String c1 = "Speical Attack Damage: %s\n".formatted(this.specialDamage);
		String d = "Maximum Health: %d\n".formatted(this.maxHealth);
		String d1 = "Current Health: %d\n".formatted(this.currentHealth);
		String e = "Heal Amount: %d\n".formatted(this.healAmount);
		String e0 = "_____________________________________\n";
		
		String monsterDetails = spacing + a0 + a + a1 + b + b1 + c + c1 + d + d1 + e + e0 + spacing;
		
		return monsterDetails;
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
	
	
	

}