import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;



public class Battle {
	

	private ArrayList<Enemy> battles = new ArrayList<Enemy>();
	private Player player;
	
	
	public ArrayList <Enemy> getBattles()
	{
		return this.battles;
	}
	
	
	public void setPlayer(Player new_player)
	{
		this.player = new_player;
	}
	
	
	
	public void generateBattles()
	{
		
		this.battles.clear();     // clearing all battles if player sleeps to refresh.
		
		boolean generationComplete = false;
		
		Random randomSize = new Random();
		int upperbound = 3;
		int battleOptions = 3 + randomSize.nextInt(upperbound); // getting a random size of team between 3 and 5.
		
		ArrayList<String> selectedNames = new ArrayList<String>();
		
		
		while (generationComplete == false)
		{
			if (this.battles.size() == battleOptions)
			{
				generationComplete = true;
			}
			else
			{
				Enemy new_enemy = new Enemy();        // Generating a distinct enemy name
				String enemy_name = new_enemy.generateEnemyName();
				while (selectedNames.contains(enemy_name))
				{
					enemy_name = new_enemy.generateEnemyName();
				}
				selectedNames.add(enemy_name);
				new_enemy.setEnemyName(enemy_name);
				new_enemy.generateEnemyTeam();
				this.battles.add(new_enemy);
			}
		}
	}
	
	
	
	
	public void fight(Enemy enemy)
	{
		
	}
	
	
	public void playerMove(Enemy enemy)
	{
		
	}
	
	public void enemyMove(Player player)
	{
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
