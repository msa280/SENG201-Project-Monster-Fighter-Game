import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import items.BloodBroth;
import items.CursedSkull;
import items.EnergizerBone;
import items.GuardianArch;
import items.Item;
import items.VirilityGem;
import monsters.Monster;
import monsters.Venomhound;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JList;
import java.awt.Choice;

public class ViewInventory {

	private JFrame inventoryViewer;
	private String lastUpdate;
	private Player player;
	private JFrame previousFrame;
	private AudioPlayer audioPlayer = new AudioPlayer();
	private AudioPlayer previousAudio;
	private JTextField quan1;
	private JTextField quan2;
	private JTextField quan3;
	private JTextField quan4;
	private JTextField quan5;
	
	
	public void setLastUpdate(String update)
	{
		this.lastUpdate = update;
	}
	
	public String chooseItemImage(Item item)
	{
		String name = item.getItemName();
		String itemImage;
		
		if (name == "Blood Broth") {
			itemImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\ItemsArtwork\\2.) BloodBroth.png";
		} else if (name == "Cursed Skull") {
			itemImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\ItemsArtwork\\1.) CursedSkull.png";
		} else if (name == "Energizer Bone") {
			itemImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\ItemsArtwork\\3.) EnergizerBone.png";
		} else if (name == "Guardian Arch") {
			itemImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\ItemsArtwork\\4.) GuardianArch.png";
		} else if (name == "Virility Gem") {
			itemImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\ItemsArtwork\\5.) VirilityGem.png";
		} else {
			itemImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\ItemsArtwork\\1.) CursedSkull.png";
		}
		return itemImage;
	}
	
	
	
	public void viewItemStat(int buttonIndex, JTextPane errorField)
	{
		if (buttonIndex > this.player.getPlayerInventory().size()) {
			errorField.setForeground(Color.RED);
			errorField.setText("Error: Inventory Pod is empty!");
		} else {
			
			int position = 0;
			for (Entry<Item, Integer> item : this.player.getPlayerInventory().entrySet()) {
				
				if (buttonIndex-1 == position) {
					errorField.setText(item.getKey().getItemEffect());
					errorField.setForeground(Color.GREEN);
					break;
				}
				position += 1;
			}
		
		}
	}
	
	
	public void setHealthBar(Monster monster, JProgressBar progressbar) {
		progressbar.setMinimum(0);
		progressbar.setMaximum(monster.getMaxHealth());
		progressbar.setValue(monster.getCurrentHealth());
	}
	
	
	
	
	public static void launchInventoryViewer(Player player, ViewInventory inventory)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inventory.inventoryViewer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void goBack()
	{
		this.audioPlayer.stopSound();
		this.inventoryViewer.dispose();
		this.previousFrame.setVisible(true);
		SwingUtilities.updateComponentTreeUI(this.previousFrame);
	}
	
	
	
	public void sellItem(int buttonIndex)
	{
		int i = 0;
		

		for (Entry<Item, Integer> item : this.player.getPlayerInventory().entrySet())
		{
			if (i == buttonIndex) {
				Item selected_item = item.getKey();
				this.player.sellItem(selected_item);
				break;
			}
			i += 1;
		}
		this.inventoryViewer.dispose();
		ViewInventory refreshInventory = new ViewInventory(this.player, this.previousFrame);
		ViewInventory.launchInventoryViewer(player, refreshInventory);
		
	}
	
	
	
	
	public Item UseItem(int buttonPosition, String chosenMonsterName, JTextField quantityLeftDisplay, JLabel itemImage)
	{
	
		Item selected_item;
		int i = 0;
	
		for (Entry<Item, Integer> item : this.player.getPlayerInventory().entrySet())
		{
			if (i == buttonPosition) {
				selected_item = item.getKey();
				
				for (Monster monster: this.player.getPlayerMonsters())
				{
					if (monster.pickMonsterName() == chosenMonsterName) 
					{
						boolean itemFinished = this.player.useItem(selected_item, monster);
						this.inventoryViewer.dispose();
						ViewInventory refreshInventory = new ViewInventory(this.player, this.previousFrame);
						ViewInventory.launchInventoryViewer(player, refreshInventory);
						
						break;
					}
				} 
				break;
			}
			i += 1;
		}
		return null;
			

		
	}
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Player player = new Player();
		player.addToTeam(new Venomhound());
		player.setPlayerGold(5000);
		player.buyItem(new CursedSkull());
		player.buyItem(new BloodBroth());
		player.buyItem(new VirilityGem());
		player.buyItem(new GuardianArch());
		player.buyItem(new GuardianArch());

		
		for (Entry<Item, Integer> item : player.getPlayerInventory().entrySet()) 
		{
			System.out.printf("%s, %d", item.getKey().getItemName(), item.getValue());
		}
		JFrame test = new JFrame();
		ViewInventory inventory = new ViewInventory(player, test);
		ViewInventory.launchInventoryViewer(player, inventory);
		
	}

	/**
	 * Create the application.
	 */
	public ViewInventory(Player fighter, JFrame oldFrame) {
		this.player = fighter;
		this.previousFrame = oldFrame;
		System.out.print(fighter.getLastUpdate());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/** this.audioPlayer.playSound("InventoryViewer.wav"); */
		
		inventoryViewer = new JFrame();
		inventoryViewer.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 20));
		inventoryViewer.getContentPane().setBackground(Color.BLACK);
		inventoryViewer.getContentPane().setLayout(null);
		
		JTextPane txtpnYourCurrentTeam = new JTextPane();
		txtpnYourCurrentTeam.setBackground(Color.BLACK);
		txtpnYourCurrentTeam.setForeground(Color.WHITE);
		txtpnYourCurrentTeam.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtpnYourCurrentTeam.setText("Your inventory:");
		txtpnYourCurrentTeam.setBounds(56, 108, 206, 31);
		inventoryViewer.getContentPane().add(txtpnYourCurrentTeam);
		
		
		
		JTextPane updateArea = new JTextPane();
		updateArea.setBackground(Color.BLACK);
		updateArea.setForeground(Color.GREEN);
		updateArea.setBounds(275, 606, 588, 64);
		updateArea.setText(this.player.getLastUpdate());
		updateArea.setFont(new Font("Tahoma", Font.BOLD, 11));
		inventoryViewer.getContentPane().add(updateArea);
		
		
	
		
		JButton button1 = new JButton("View Effects");
		button1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewItemStat(1, updateArea);
			}
		});
		button1.setBackground(new Color(255, 255, 0));
		button1.setBounds(66, 391, 111, 23);
		inventoryViewer.getContentPane().add(button1);
		
		JButton button2 = new JButton("View Effects");
		button2.setFont(new Font("Tahoma", Font.BOLD, 11));
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewItemStat(2, updateArea);
			}
		});
		button2.setBackground(Color.YELLOW);
		button2.setBounds(254, 391, 111, 23);
		inventoryViewer.getContentPane().add(button2);
		
		JButton button3 = new JButton("View Effects");
		button3.setFont(new Font("Tahoma", Font.BOLD, 11));
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewItemStat(3, updateArea);
			}
		});
		button3.setBackground(Color.YELLOW);
		button3.setBounds(456, 391, 111, 23);
		inventoryViewer.getContentPane().add(button3);
		
		JButton button4 = new JButton("View Effects");
		button4.setFont(new Font("Tahoma", Font.BOLD, 11));
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewItemStat(4, updateArea);
			}
		});
		button4.setBackground(Color.YELLOW);
		button4.setBounds(665, 391, 111, 23);
		inventoryViewer.getContentPane().add(button4);
		
		JButton button5 = new JButton("View Effects");
		button5.setFont(new Font("Tahoma", Font.BOLD, 11));
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewItemStat(5, updateArea);
			}
		});
		button5.setBackground(Color.YELLOW);
		button5.setBounds(890, 391, 111, 23);
		inventoryViewer.getContentPane().add(button5);
		
		JLabel item1 = new JLabel("New label");
		item1.setToolTipText("");
		item1.setBounds(38, 161, 150, 150);
		inventoryViewer.getContentPane().add(item1);
		
		JLabel item2 = new JLabel("New label");
		item2.setBounds(243, 161, 150, 150);
		inventoryViewer.getContentPane().add(item2);
		
		JLabel item3 = new JLabel("New label");
		item3.setBounds(440, 161, 150, 150);
		inventoryViewer.getContentPane().add(item3);
		
		JLabel item4 = new JLabel("New label");
		item4.setBounds(665, 161, 150, 150);
		inventoryViewer.getContentPane().add(item4);
		
		JLabel item5 = new JLabel("");
		item5.setBounds(867, 161, 150, 150);
		inventoryViewer.getContentPane().add(item5);
		
		JLabel labelList[] = new JLabel[5];
		labelList[0] = item1;
		labelList[1] = item2;
		labelList[2] = item3;
		labelList[3] = item4;
		labelList[4] = item5;
		
		JTextPane name1 = new JTextPane();
		name1.setBackground(new Color(0, 0, 0));
		name1.setForeground(new Color(255, 255, 255));
		name1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name1.setBounds(56, 322, 132, 31);
		inventoryViewer.getContentPane().add(name1);
		
		
		JTextPane name2 = new JTextPane();
		name2.setForeground(Color.WHITE);
		name2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name2.setBackground(new Color(0, 0, 0));
		name2.setBounds(243, 322, 132, 31);
		inventoryViewer.getContentPane().add(name2);
		
		JTextPane name3 = new JTextPane();
		name3.setForeground(Color.WHITE);
		name3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name3.setBackground(new Color(0, 0, 0));
		name3.setBounds(450, 322, 132, 31);
		inventoryViewer.getContentPane().add(name3);
		
		JTextPane name4 = new JTextPane();
		name4.setForeground(Color.WHITE);
		name4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name4.setBackground(new Color(0, 0, 0));
		name4.setBounds(651, 322, 132, 31);
		inventoryViewer.getContentPane().add(name4);
		
		JTextPane name5 = new JTextPane();
		name5.setForeground(Color.WHITE);
		name5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name5.setBackground(new Color(0, 0, 0));
		name5.setBounds(889, 322, 132, 31);
		inventoryViewer.getContentPane().add(name5);
		
		JTextPane paneList[] = new JTextPane[5];
		paneList[0] = name1;
		paneList[1] = name2;
		paneList[2] = name3;
		paneList[3] = name4;
		paneList[4] = name5;
		
		
	
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(56, 618, 111, 31);
		inventoryViewer.getContentPane().add(btnNewButton);
		
		JTextArea txtrChooseAMonster = new JTextArea();
		txtrChooseAMonster.setBackground(Color.BLACK);
		txtrChooseAMonster.setForeground(Color.WHITE);
		txtrChooseAMonster.setFont(new Font("Times New Roman", Font.BOLD, 22));
		txtrChooseAMonster.setText("Choose a monster to use an item on:");
		txtrChooseAMonster.setBounds(56, 11, 361, 23);
		inventoryViewer.getContentPane().add(txtrChooseAMonster);
		
		Choice choice = new Choice();
		choice.setBackground(Color.BLACK);
		choice.setFont(new Font("Times New Roman", Font.BOLD, 20));
		choice.setForeground(Color.WHITE);
		choice.setBounds(56, 52, 293, 31);
		inventoryViewer.getContentPane().add(choice);
		
		quan1 = new JTextField();
		quan1.setText("Quantity: 0");
		quan1.setFont(new Font("Tahoma", Font.BOLD, 12));
		quan1.setBounds(80, 364, 84, 20);
		inventoryViewer.getContentPane().add(quan1);
		quan1.setColumns(10);
		
		quan2 = new JTextField();
		quan2.setText("Quantity: 0");
		quan2.setFont(new Font("Tahoma", Font.BOLD, 12));
		quan2.setColumns(10);
		quan2.setBounds(265, 364, 84, 20);
		inventoryViewer.getContentPane().add(quan2);
		
		quan3 = new JTextField();
		quan3.setText("Quantity: 0");
		quan3.setFont(new Font("Tahoma", Font.BOLD, 12));
		quan3.setColumns(10);
		quan3.setBounds(466, 360, 84, 20);
		inventoryViewer.getContentPane().add(quan3);
		
		quan4 = new JTextField();
		quan4.setText("Quantity: 0");
		quan4.setFont(new Font("Tahoma", Font.BOLD, 12));
		quan4.setColumns(10);
		quan4.setBounds(674, 364, 84, 20);
		inventoryViewer.getContentPane().add(quan4);
		
		quan5 = new JTextField();
		quan5.setText("Quantity: 0");
		quan5.setFont(new Font("Tahoma", Font.BOLD, 12));
		quan5.setColumns(10);
		quan5.setBounds(899, 364, 84, 20);
		inventoryViewer.getContentPane().add(quan5);
		
		
		JTextField quantities[] = new JTextField[5];
		quantities[0] = quan1;
		quantities[1] = quan2;
		quantities[2] = quan3;
		quantities[3] = quan4;
		quantities[4] = quan5;
		
		JButton use1 = new JButton("Use Item");
		use1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UseItem(0, choice.getSelectedItem(), quan1, item1);
			}
		});
		use1.setFont(new Font("Tahoma", Font.BOLD, 12));
		use1.setBackground(Color.GREEN);
		use1.setForeground(Color.BLACK);
		use1.setBounds(76, 425, 94, 23);
		inventoryViewer.getContentPane().add(use1);
		
		JButton use2 = new JButton("Use Item");
		use2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UseItem(1, choice.getSelectedItem(), quan2, item2);
			}
		});
		use2.setForeground(Color.BLACK);
		use2.setFont(new Font("Tahoma", Font.BOLD, 12));
		use2.setBackground(Color.GREEN);
		use2.setBounds(261, 425, 94, 23);
		inventoryViewer.getContentPane().add(use2);
		
		JButton use3 = new JButton("Use Item");
		use3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UseItem(2, choice.getSelectedItem(), quan3, item3);
			}
		});
		use3.setForeground(Color.BLACK);
		use3.setFont(new Font("Tahoma", Font.BOLD, 12));
		use3.setBackground(Color.GREEN);
		use3.setBounds(466, 425, 94, 23);
		inventoryViewer.getContentPane().add(use3);
		
		JButton use4 = new JButton("Use Item");
		use4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UseItem(3, choice.getSelectedItem(), quan4, item4);
			}
		});
		use4.setForeground(Color.BLACK);
		use4.setFont(new Font("Tahoma", Font.BOLD, 12));
		use4.setBackground(Color.GREEN);
		use4.setBounds(675, 425, 94, 23);
		inventoryViewer.getContentPane().add(use4);
		
		JButton use5 = new JButton("Use Item");
		use5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UseItem(4, choice.getSelectedItem(), quan5, item5);
			}
		});
		use5.setForeground(Color.BLACK);
		use5.setFont(new Font("Tahoma", Font.BOLD, 12));
		use5.setBackground(Color.GREEN);
		use5.setBounds(900, 425, 94, 23);
		inventoryViewer.getContentPane().add(use5);
		
		JButton useButtons[] = new JButton[5];
		useButtons[0] = use1;
		useButtons[1] = use2;
		useButtons[2] = use3;
		useButtons[3] = use4;
		useButtons[4] = use5;
		
		JButton sell1 = new JButton("Sell (+100 Gold)");
		sell1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellItem(0);
			}
		});
		sell1.setForeground(new Color(255, 255, 255));
		sell1.setBackground(new Color(255, 0, 0));
		sell1.setFont(new Font("Tahoma", Font.BOLD, 11));
		sell1.setBounds(56, 459, 132, 23);
		inventoryViewer.getContentPane().add(sell1);
		
		JButton sell2 = new JButton("Sell (+100 Gold)");
		sell2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellItem(1);
			}
		});
		sell2.setForeground(Color.WHITE);
		sell2.setFont(new Font("Tahoma", Font.BOLD, 11));
		sell2.setBackground(Color.RED);
		sell2.setBounds(243, 459, 132, 23);
		inventoryViewer.getContentPane().add(sell2);
		
		JButton sell3 = new JButton("Sell (+100 Gold)");
		sell3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellItem(2);
			}
		});
		sell3.setForeground(Color.WHITE);
		sell3.setFont(new Font("Tahoma", Font.BOLD, 11));
		sell3.setBackground(Color.RED);
		sell3.setBounds(456, 459, 132, 23);
		inventoryViewer.getContentPane().add(sell3);
		
		JButton sell4 = new JButton("Sell (+100 Gold)");
		sell4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellItem(3);
			}
		});
		sell4.setForeground(Color.WHITE);
		sell4.setFont(new Font("Tahoma", Font.BOLD, 11));
		sell4.setBackground(Color.RED);
		sell4.setBounds(665, 459, 132, 23);
		inventoryViewer.getContentPane().add(sell4);
		
		JButton sell5 = new JButton("Sell (+100 Gold)");
		sell5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellItem(4);
			}
		});
		sell5.setForeground(Color.WHITE);
		sell5.setFont(new Font("Tahoma", Font.BOLD, 11));
		sell5.setBackground(Color.RED);
		sell5.setBounds(885, 459, 132, 23);
		inventoryViewer.getContentPane().add(sell5);
		
		JButton[] sellButtons = new JButton[5];
		sellButtons[0] = sell1;
		sellButtons[1] = sell2;
		sellButtons[2] = sell3;
		sellButtons[3] = sell4;
		sellButtons[4] = sell5;
	
		
		
		
		for (Monster monster: this.player.getPlayerMonsters()) {
			choice.add(monster.pickMonsterName());
		}
		
		
		
		int i = 0;
		for (Entry<Item, Integer> item : this.player.getPlayerInventory().entrySet()) {
			Item myItem = item.getKey();
			int amount = item.getValue();
			String itemImageLink = chooseItemImage(myItem);
			labelList[i].setIcon(new ImageIcon(itemImageLink));
			paneList[i].setText(myItem.getItemName());
			quantities[i].setText("Quantity: " + Integer.toString(amount));
			sellButtons[i].setText("Sell (+%d Gold)".formatted(myItem.getResalePrice()));
			i += 1;
		}
		
		
		while (i < labelList.length) {
			labelList[i].setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\ItemsArtwork\\NoItem.png"));
			useButtons[i].setText("No Item");
			i += 1;
		}
		
	
		

		
		
		
		inventoryViewer.setTitle("Inventory Viewer");
		inventoryViewer.setBounds(100, 100, 1080, 720);
		inventoryViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}