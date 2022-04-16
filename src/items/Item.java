package items;

public class Item {
	
	private String itemName;
	private String itemEffect;
	private int price;
	

	public Item(String itemName, String itemEffect, int price)
	{
		this.setItemName(itemName);
		this.setItemEffect(itemEffect);
		this.setPrice(price);
		
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemEffect() {
		return itemEffect;
	}


	public void setItemEffect(String itemEffect) {
		this.itemEffect = itemEffect;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getResalePrice()
	{
		return this.price - 20;
	}


}
