import java.util.InputMismatchException;
import java.util.Scanner;

public class Shop {
	
	private Scanner scan_input = new Scanner(System.in);

	public Shop() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean execute_BuyOrSell(boolean in_shop, int chosen_option)
	{
		if (chosen_option == 1)
		{
			
		}
		else if (chosen_option == 2)
		{
			
		}
		else if (chosen_option == 0)
		{
			in_shop = false;
		}
		else
		{
			System.out.print("\nPlease select a valid option.\n");
		}
		
		return in_shop;
	}
	
	
	
	public void view_shop()
	{
		boolean in_shop = true;
		int chosen_option = 0;
		
		while (in_shop == true)
		{
			System.out.print("\nPlease pick what you want to do: \n");
			System.out.print("\n1. Buy\n");
			System.out.print("2. Sell\n");
			System.out.print("0. Exit Shop\n");
			
			try 
			{
				chosen_option = scan_input.nextInt();
				in_shop = execute_BuyOrSell(in_shop, chosen_option);
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
