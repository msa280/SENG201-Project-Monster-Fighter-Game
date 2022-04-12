import java.util.ArrayList;
import java.util.HashMap;
public class Player {
	
	private int playerGold;
	private HashMap<String, String> playerInventory = new HashMap<String, String>(); 
	private ArrayList<Monster> playersTeam = new ArrayList<Monster>();
	private int currentDay;
	private int daysRemaining;
	
	
	

	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}
	
	public int getCurrentDay() {
		return currentDay;
	}
	
	public void setDaysRemaining(int daysRemaining) {
		this.daysRemaining = daysRemaining;
	}
	
	public int getDaysRemaining() {
		return daysRemaining;
	}
	
}
