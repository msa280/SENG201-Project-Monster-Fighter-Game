import java.util.ArrayList;
//import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import monsters.Cavernfreak;
import monsters.Hollowtree;
import monsters.Monster;
import monsters.Mornpest;
import monsters.Soilscreamer;
import monsters.Venomhound;



public class Player {
	
	private Scanner scan_input = new Scanner(System.in);
	private int playerGold = 0;
	//private HashMap<String, String> playerInventory = new HashMap<String, String>(); 
	private ArrayList<Monster> playersTeam = new ArrayList<Monster>();
	private Monster startingMonsters[] = new Monster[4];
	private int currentDay;
	private int daysRemaining;
	
	
	
	public int getPlayerGold() {
		return playerGold;
	}

	public void setPlayerGold(int playerGold, Game game) {
		this.playerGold = (int)(playerGold * game.getGameDifficulty());
	}
	
	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}
	
	public int getCurrentDay() {
		return currentDay;
	}
	
	public void setDaysRemaining(int current_day, Game game) {
		this.daysRemaining = game.getGameLength() - current_day;
	}
	
	public int getDaysRemaining() {
		return this.daysRemaining;
	}
	
	public int get_playersTeam_length()
	{
		return this.playersTeam.size();
	}
	
	
	
	
	public boolean[] monster_reNamer(boolean bought, boolean option_correct, int selection, Monster monster)
	{
		while (option_correct == false)
		{
			System.out.print("\nChoose a naming method for your monster: \n");
			System.out.print("\n1. Rename\n");
			System.out.print("2. Default Name\n");
			System.out.print("0. Go Back\n");

			try 
			{
				selection = scan_input.nextInt();
				if (selection == 1)
				{
					System.out.print("\nMonster bought!\n");
					this.playersTeam.add(monster);
					monster.ask_MonsterName();
					System.out.printf("%s has been added to the team.\n", monster.getMonsterName());
					bought = true;
					option_correct = true;
				} 
				else if (selection == 2)
				{
					System.out.print("\nMonster bought!\n");
					this.playersTeam.add(monster);
					System.out.printf("%s has been added to the team.\n", monster.getMonsterName());
					bought = true;
					option_correct = true;
				}
				else
				{
					option_correct = true;
				}		
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("Please enter a valid name or option.\n");
				scan_input.next();
				continue;
			}
		}
		return new boolean[] {bought, option_correct};
	}
	
	
	
	
	
	public boolean verify_monsterPurchase(Monster monster)
	{
		boolean bought  = false;
		int selected_option = 0;
		int selection = 0;
		
		while (bought == false)
		{
			System.out.print(monster.toString());
			System.out.print("\n Do you want to add this monster?\n");
			System.out.print("1. Yes\n");
			System.out.print("2. No\n");
			try 
			{
				selected_option = scan_input.nextInt();
				if (selected_option == 1)
				{
					boolean option_correct = false;
					boolean[] two_bools = null;
					two_bools = monster_reNamer(bought, option_correct, selection, monster);
					bought = two_bools[0];
					option_correct = two_bools[1];
				}
				else
				{
					return bought;
				}
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("Please enter a valid option number.\n");
				scan_input.next();
				continue;
			}
		}
		
		return bought;
	}
	

	
	
	public boolean execute_monsterSelection(Monster[] startingMonsters, boolean monster_selected, int option_number)
	{
		
		Monster monster = null;
		boolean monsterBought = false;
		
	
		if (option_number == 1) 
		{
			monster = startingMonsters[0];
			monsterBought = verify_monsterPurchase(monster);
		}
		else if (option_number == 2)
		{
			monster = startingMonsters[1];
			monsterBought = verify_monsterPurchase(monster);
		} 
		else if (option_number == 3)
		{
			monster = startingMonsters[2];
			monsterBought = verify_monsterPurchase(monster);
		}
		else if (option_number == 4)
		{
			monster = startingMonsters[3];
			monsterBought = verify_monsterPurchase(monster);
		}
		else if (option_number == 5)
		{
			monster = startingMonsters[4];
			monsterBought = verify_monsterPurchase(monster);
		}
		else
		{
			monsterBought = false;
		}
	
		return monsterBought;
	}
	
	
	
	
	
	
	public void choose_startingMonster()
	{
		startingMonsters[0] = new Cavernfreak();
		startingMonsters[1] = new Hollowtree();
		startingMonsters[2] = new Mornpest();
		startingMonsters[3] = new Soilscreamer();
		startingMonsters[4] = new Venomhound();
		
		boolean monster_selected = false;
		int option_number = 0;
		
		while (monster_selected == false)
		{
			System.out.print("\nPick a staring monster: \n");
			System.out.print("\n1. Cavernfreak\n");
			System.out.print("2. Hollowtree\n");
			System.out.print("3. Mornpest\n");
			System.out.print("4. Soilscreamer\n");
			System.out.print("5. Venomhound\n");
			
			try 
			{
				option_number = scan_input.nextInt();
				monster_selected = execute_monsterSelection(startingMonsters, monster_selected, option_number);
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("Please enter a valid option number.\n");
				scan_input.next();
				continue;
			}
		}
	}
	
	
	
	public boolean monster_statOpener(boolean in_team_viewer, int option_number)
	{
		if (option_number > this.get_playersTeam_length())
		{
			if (option_number < 5)
			{
				System.out.print("\nMonsterPod is empty.\n");
			}
			else
			{
				System.out.print("\nPlease select a valid monster option.\n");
			}
		}
		else if (option_number == 0)
		{
			in_team_viewer = false;
		}
		else 
		{
			Monster selected_monster =  this.playersTeam.get(option_number-1);
			System.out.printf("MonsterPod %d: %s\n\n", option_number, selected_monster.getMonsterName());
			System.out.print(selected_monster.toString());
		}
		
		return in_team_viewer;
		
	}
	
	
	
	public void view_team()
	{
		boolean in_team_viewer = true;
		int option_number = 0;
		
		
		while (in_team_viewer == true)
		{ 
			int order = 1;
			System.out.print("\nYour ordered team monsters are: \n\n");
			for (Monster monster: playersTeam)
			{
				System.out.printf("MonsterPod %d: %s\n", order, monster.getMonsterName());
				order += 1;
			}
			for (int position = order; position < 5; position++)
			{
				System.out.printf("MonsterPod %d: (Empty)\n", position);
					
			}
			System.out.print("\nSelect the monster position to view monster stats.\n");
			System.out.print("\n0 - Exit Team View.\n");
			
			
			try 
			{
				option_number = scan_input.nextInt();
				in_team_viewer = monster_statOpener(in_team_viewer, option_number);
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("\nPlease enter a valid option number.\n");
				scan_input.next();
				continue;
			}
		}
	}
	
	
	
	
	public boolean execute_playerCommand(boolean in_player_menu, int option_number)
	{
		if (option_number == 1) 
		{
			System.out.printf("Available Gold: %d\n", this.getPlayerGold());
        } 
		else if (option_number == 2) 
        {
			System.out.printf("Current Day: %d\n", this.getCurrentDay());
        } 
		else if (option_number == 3) 
		{
			System.out.printf("Remaining Days: %d\n", this.getDaysRemaining());
        } 
		else if (option_number == 4)
		{
			this.view_team();
		}
		else if (option_number == 5)
		{
			
		}
		else if (option_number == 6)
		{
			
		}
		else if (option_number == 7)
		{
			
		}

		else if (option_number == 0) 
		{
			in_player_menu = false;
        }
		
		else 
		{
			System.out.print("Choose an option number between 0 and 6.\n");
		}
		return in_player_menu;
	}
	

	
	
	public void player_viewer() 
	{
		boolean in_player_menu = true;
		int option_number = 0;
		
		while (in_player_menu == true)
		{
			System.out.print("\nWhat would you like to do: \n");
			System.out.print("\n1. View Gold\n");
			System.out.print("2. View Current Day\n");
			System.out.print("3. View Remaining Days\n");
			System.out.print("4. View Team\n");
			System.out.print("5. View Inventory\n");
			System.out.print("6. View Battles\n");
			System.out.print("7. View Shop\n");
			System.out.print("0. Exit\n");
			
			try 
			{
				option_number = scan_input.nextInt();
				in_player_menu = execute_playerCommand(in_player_menu, option_number);
			} 
			catch (InputMismatchException excp) 
			{
				System.out.print("Please enter a valid option number.\n");
				scan_input.next();
				continue;
			}
		}
	}
}






