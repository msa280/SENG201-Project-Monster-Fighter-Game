/**
 * SENG201 Project 2022 - Monster Fighter
 * This is the 'Game' class for the monster fighter which runs the 
 * the main game.
 * @author Haider Saeed (msa280) & Jakib Isherwood (jis48)
 * 
 */

import java.util.Scanner;  // Import the Scanner class 
import java.util.regex.Matcher; 
import java.util.regex.Pattern;


public class Game {

	/**
	 * @param args
	 * 
	 */
	
	
	private String playerName;
	
	
	public void setPlayerName() {
		
		boolean name_check_passed = false;
		Scanner scan_name = new Scanner(System.in); //Asks player name 
		System.out.print("Enter your fighter name: ");
		String scanned_name = scan_name.nextLine();
		
		int name_length = scanned_name.length();
		Pattern pattern = Pattern.compile("[^a-zA-Z]");
		Matcher matcher = pattern.matcher(scanned_name);
		boolean name_has_num_or_special_character = matcher.find();
		
		
		while (name_check_passed == false) {
			
			if (name_has_num_or_special_character) {
				System.out.println(scanned_name + " contains a number or special character!\n");
				System.out.println("Please choose a different fighter name.\n");
				break;
			} 
			else if (name_length < 3) {
				System.out.println(scanned_name + " is too short!\n");
				System.out.println("Please choose a longer fighter name.\n");
				break;
			} 
			else if (name_length > 15) {
				System.out.println(scanned_name + " is too long!\n");
				System.out.println("Please choose a shorter fighter name.\n");
				setPlayerName();
				break;
			}
			else {
				name_check_passed = true;
			}
		}
		

		if (name_check_passed == true) {
			this.playerName = scanned_name;
		} 
		else {
			this.setPlayerName();
			
		}
	}
	
	
	
	public static void main(String[] args) {
		
		Game new_game = new Game();
		new_game.setPlayerName();

	}



	

}
