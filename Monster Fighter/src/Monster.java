
public class Monster {
	private String monsterName;
	private int maxHealth;
	private int damage;
	private int healAmount;
	private int currentHealth;
	
	public Monster(String monsterName, int maxHealth, int damage, int healAmount, int currentHealth) {
		this.monsterName = monsterName;
		this.maxHealth = maxHealth;
		this.damage = damage;
		this.healAmount = healAmount;
		this.currentHealth = currentHealth;
	}
}
