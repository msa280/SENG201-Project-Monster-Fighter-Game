package monsters;

import java.util.Scanner;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

public class Monster {
	
	private Scanner scan_input = new Scanner(System.in);
	private String monsterName;
	private String attackName;
	private String special_attackName;
	private int maxHealth;
	private int damage;
	private int healAmount;
	private int currentHealth;
	private int specialDamage;
	
	
	public Monster(String monsterName, String attack_name, String special_attack_name, int maxHealth, int damage, int healAmount, int currentHealth, int specialDamage) {
		this.setMonsterName(monsterName);
		this.setAttackName(attack_name);
		this.setSpecial_attackName(special_attack_name);
		this.setMaxHealth(maxHealth);
		this.setDamage(damage);
		this.setHealAmount(healAmount);
		this.setCurrentHealth(currentHealth);
		this.setSpecialDamage(specialDamage);
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

	public String getSpecial_attackName() {
		return special_attackName;
	}

	public void setSpecial_attackName(String special_attackName) {
		this.special_attackName = special_attackName;
	}
	
	public boolean check_name(boolean num_or_special_char, boolean name_check_passed, int name_length, String scanned_name) 
	{
		if (num_or_special_char) 
		{
			System.out.println(scanned_name + " contains numbers or special characters!\n");
			System.out.println("Please choose a different monster name.\n");
		} 
		else if (name_length < 3) 
		{
			System.out.println(scanned_name + " is too short!\n");
			System.out.println("Please choose a longer monster name.\n");
		} 
		else if (name_length > 15) 
		{
			System.out.println(scanned_name + " is too long!\n");
			System.out.println("Please choose a shorter monster name.\n");
		}
		else 
		{
			name_check_passed = true;
		}
		return name_check_passed;
	}
	
	
	public void ask_MonsterName() 
	{
		
		Pattern pattern = Pattern.compile("[^a-zA-Z]");
		String scanned_name = null;
		Matcher matcher = null;
		int name_length = 0;
		boolean num_or_special_char = false;
		boolean name_check_passed = false;
		
		while (name_check_passed == false) 
		{	
			System.out.print("\nRename your monster: ");
			scanned_name = scan_input.nextLine();
			name_length = scanned_name.length();
			matcher = pattern.matcher(scanned_name);
			num_or_special_char = matcher.find();
			name_check_passed = check_name(num_or_special_char, name_check_passed, name_length, scanned_name);
		}
		this.setMonsterName(scanned_name);
	}
	
	public String toString()
	{
		String a0 = "_____________Monster Stats___________\n\n";
		String a = "Name: %s\n".formatted(this.monsterName);
		String a1 = "_____________________________________\n";
		String b = "Attack Name: %s\n".formatted(this.attackName);
		String b1 = "Attack Damage: %d\n".formatted(this.damage);
		String c = "Special Attack Name: %s\n".formatted(this.special_attackName);
		String c1 = "Speical Attack Damage: %s\n".formatted(this.specialDamage);
		String d = "Maximum Health: %d\n".formatted(this.maxHealth);
		String d1 = "Current Health: %d\n".formatted(this.currentHealth);
		String e = "Heal Amount: %d\n".formatted(this.healAmount);
		String e0 = "_____________________________________\n";
		
		String monsterDetails = a0 + a + a1 + b + b1 + c + c1 + d + d1 + e + e0;
		
		return monsterDetails;
	}

}