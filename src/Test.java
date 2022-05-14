import java.util.HashMap;
import java.util.Map;

import items.CursedSkull;
import items.Item;

public class Test {
	
	public  Map<Item, Integer> playerInventory = new HashMap<Item, Integer>();
	
	
	public void check()
	{
		
		Item item = new CursedSkull();
        
		this.playerInventory.put(item, 1);
		
		if (this.playerInventory.containsKey(item)) {
			int increment = this.playerInventory.get(item) + 1;
			this.playerInventory.remove(item);
			this.playerInventory.put(item, increment);
		} 
		System.out.print(this.playerInventory);
	}
	
	
	public static void main(String[] args)
	{
		Test test = new Test();
		test.check();
	}

}
