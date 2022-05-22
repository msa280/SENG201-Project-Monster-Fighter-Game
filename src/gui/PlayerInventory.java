package gui;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextPane;
import game.AudioPlayer;
import game.Player;
import items.CursedSkull;
import items.Item;
import monsters.Monster;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import java.awt.Choice;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class PlayerInventory
{
	/*
	 * The inventory viewer frame.
	 */
	private JFrame inventoryViewer;
	/*
	 * The player.
	 */
	private Player player;
	/*
	 * The button audio.
	 */
	private AudioPlayer buttonAudio = new AudioPlayer();
	/*
	 * The text field that displays the players gold.
	 */
	private JTextField gold;

	/*
	 * Sets the health bar of the monster.
	 * 
	 * @param monster The monster.
	 * @param progressBar The health bar.
	 */
	public void setHealthBar(Monster monster, JProgressBar progressBar) 
	{
		progressBar.setMinimum(0);
		progressBar.setMaximum(monster.getMaxHealth());
		progressBar.setValue(monster.getCurrentHealth());
	}
	
	
	/*
	 * Views the items statistics
	 * 
	 * @param buttonIndex The index of the button chosen.
	 * @param errorField Sets a text pane to show an error message.
	 */
	public void viewItemStat(int buttonIndex, JTextPane errorField)
	{
		if (buttonIndex > this.player.getPlayerInventory().size())
		{
			errorField.setForeground(Color.RED);
			errorField.setText("Error: Inventory Pod is empty!");
		} 
		else 
		{
			int position = 0;
			for (Entry<Item, Integer> item : this.player.getPlayerInventory().entrySet())
			{
				if (buttonIndex-1 == position) 
				{
					errorField.setText(item.getKey().getItemEffect());
					errorField.setForeground(Color.GREEN);
					break;
				}
				position += 1;
			}
		}
	}
	
	/*
	 * Launches the inventory viewer.
	 * 
	 * @param player The player.
	 */
	public static void launchInventoryViewer(Player player)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerInventory window = new PlayerInventory(player);
					window.inventoryViewer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * Goes back to the main menu.
	 */
	public void goBack()
	{
		buttonAudio.playSoundOnce("buttonA.wav");
		this.inventoryViewer.dispose();
		MainMenu.launchMainMenu(this.player);
	}
	
	/*
	 * Sells an item.
	 * 
	 * @param buttonIndex The index of the chosen button.
	 */
	public void sellItem(int buttonIndex)
	{
		int i = 0;
		
		for (Entry<Item, Integer> item : this.player.getPlayerInventory().entrySet())
		{
			if (i == buttonIndex) 
			{
				Item selected_item = item.getKey();
				this.player.sellItem(selected_item);
				break;
			}
			i += 1;
		}
		
		this.inventoryViewer.dispose();
		PlayerInventory.launchInventoryViewer(this.player);
	}
	
	/*
	 * Uses an item.
	 * 
	 * @param buttonPosition The position of the button.
	 * @param chosenMonsterName The name of the chosen monster.
	 * @param quantityLeftDisplay The quantity of the item left.
	 * @param itemImage The image of the item.
	 * 
	 * @return Returns nothing, the function just does its job and finishes.
	 */
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
						this.player.useItem(selected_item, monster);
						this.inventoryViewer.dispose();
						PlayerInventory.launchInventoryViewer(this.player);
						break;
					}
				} 
				break;
			}
			i += 1;
		}
		return null;
	}
	
	/*
	 * Launches the application.
	 */
	public static void main(String[] args) 
	{
		Player player = new Player();
		player.setPlayerGold(3000);
		player.buyItem(new CursedSkull());
		PlayerInventory.launchInventoryViewer(player);
	
	}

	
	
	/*
	 * Creates the player inventory GUI.
	 * 
	 * @param player The player.
	 */
	public PlayerInventory(Player player) 
	{
		this.player = player;
		initialize();
	}

	/*
	 * Initializes the contents of the frame.
	 */
	private void initialize() {
		
		/** this.audioPlayer.playSound("InventoryViewer.wav"); */
		
		inventoryViewer = new JFrame();
		inventoryViewer.setResizable(false);
		inventoryViewer.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 20));
		inventoryViewer.getContentPane().setBackground(Color.DARK_GRAY);
		inventoryViewer.getContentPane().setLayout(null);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		inventoryViewer.setLocation(dim.width/2-inventoryViewer.getSize().width/2, dim.height/2-inventoryViewer.getSize().height/2);
		
		JTextPane txtpnYourCurrentTeam = new JTextPane();
		txtpnYourCurrentTeam.setBorder(null);
		txtpnYourCurrentTeam.setEditable(false);
		txtpnYourCurrentTeam.setBackground(Color.DARK_GRAY);
		txtpnYourCurrentTeam.setForeground(Color.WHITE);
		txtpnYourCurrentTeam.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtpnYourCurrentTeam.setText("Your inventory:");
		txtpnYourCurrentTeam.setBounds(56, 108, 150, 23);
		inventoryViewer.getContentPane().add(txtpnYourCurrentTeam);
		
		
		
		JTextPane updateArea = new JTextPane();
		updateArea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.MAGENTA));
		updateArea.setBackground(Color.DARK_GRAY);
		updateArea.setForeground(Color.GREEN);
		updateArea.setBounds(275, 606, 588, 64);
		updateArea.setText(this.player.getLastUpdate());
		updateArea.setFont(new Font("Tahoma", Font.BOLD, 16));
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
		
		JButton[] effectButtons = new JButton[5];
		effectButtons[0] = button1;
		effectButtons[1] = button2;
		effectButtons[2] = button3;
		effectButtons[3] = button4;
		effectButtons[4] = button5;
		
		
		JLabel item1 = new JLabel("New label");
		item1.setBackground(Color.DARK_GRAY);
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
		name1.setBackground(Color.DARK_GRAY);
		name1.setForeground(new Color(255, 255, 255));
		name1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name1.setBounds(56, 322, 132, 31);
		inventoryViewer.getContentPane().add(name1);
		
		
		JTextPane name2 = new JTextPane();
		name2.setForeground(Color.WHITE);
		name2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name2.setBackground(Color.DARK_GRAY);
		name2.setBounds(243, 322, 132, 31);
		inventoryViewer.getContentPane().add(name2);
		
		JTextPane name3 = new JTextPane();
		name3.setForeground(Color.WHITE);
		name3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name3.setBackground(Color.DARK_GRAY);
		name3.setBounds(450, 322, 132, 31);
		inventoryViewer.getContentPane().add(name3);
		
		JTextPane name4 = new JTextPane();
		name4.setForeground(Color.WHITE);
		name4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name4.setBackground(Color.DARK_GRAY);
		name4.setBounds(651, 322, 132, 31);
		inventoryViewer.getContentPane().add(name4);
		
		JTextPane name5 = new JTextPane();
		name5.setForeground(Color.WHITE);
		name5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name5.setBackground(Color.DARK_GRAY);
		name5.setBounds(889, 322, 132, 31);
		inventoryViewer.getContentPane().add(name5);
		
		JTextPane paneList[] = new JTextPane[5];
		paneList[0] = name1;
		paneList[1] = name2;
		paneList[2] = name3;
		paneList[3] = name4;
		paneList[4] = name5;
		
		
	
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnNewButton.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK));
				btnNewButton.setBackground(Color.GREEN);
				btnNewButton.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnNewButton.setBorder(null);
				btnNewButton.setBackground(Color.BLACK);
				btnNewButton.setForeground(Color.YELLOW);
			}
		});
		
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.YELLOW);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(56, 606, 121, 43);
		inventoryViewer.getContentPane().add(btnNewButton);
		
		JTextArea txtrChooseAMonster = new JTextArea();
		txtrChooseAMonster.setBorder(null);
		txtrChooseAMonster.setEditable(false);
		txtrChooseAMonster.setBackground(Color.DARK_GRAY);
		txtrChooseAMonster.setForeground(Color.WHITE);
		txtrChooseAMonster.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtrChooseAMonster.setText("Choose a monster to use an item on:");
		txtrChooseAMonster.setBounds(56, 19, 342, 23);
		inventoryViewer.getContentPane().add(txtrChooseAMonster);
		
		Choice choice = new Choice();
		choice.setBackground(Color.DARK_GRAY);
		choice.setFont(new Font("Times New Roman", Font.BOLD, 20));
		choice.setForeground(Color.WHITE);
		choice.setBounds(56, 52, 293, 31);
		inventoryViewer.getContentPane().add(choice);
		
		JTextField quan1 = new JTextField();
		quan1.setEditable(false);
		quan1.setText("Quantity: 0");
		quan1.setFont(new Font("Tahoma", Font.BOLD, 12));
		quan1.setBounds(80, 364, 84, 20);
		inventoryViewer.getContentPane().add(quan1);
		quan1.setColumns(10);
		
		JTextField quan2 = new JTextField();
		quan2.setEditable(false);
		quan2.setText("Quantity: 0");
		quan2.setFont(new Font("Tahoma", Font.BOLD, 12));
		quan2.setColumns(10);
		quan2.setBounds(265, 364, 84, 20);
		inventoryViewer.getContentPane().add(quan2);
		
		JTextField quan3 = new JTextField();
		quan3.setEditable(false);
		quan3.setText("Quantity: 0");
		quan3.setFont(new Font("Tahoma", Font.BOLD, 12));
		quan3.setColumns(10);
		quan3.setBounds(466, 360, 84, 20);
		inventoryViewer.getContentPane().add(quan3);
		
		JTextField quan4 = new JTextField();
		quan4.setEditable(false);
		quan4.setText("Quantity: 0");
		quan4.setFont(new Font("Tahoma", Font.BOLD, 12));
		quan4.setColumns(10);
		quan4.setBounds(674, 364, 84, 20);
		inventoryViewer.getContentPane().add(quan4);
		
		JTextField quan5 = new JTextField();
		quan5.setEditable(false);
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
		sell1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				sell1.setFont(new Font("Tahoma", Font.BOLD, 14));
				sell1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
				sell1.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sell1.setFont(new Font("Tahoma", Font.BOLD, 12));
				sell1.setForeground(new Color(255, 255, 255));
				sell1.setBorder(null);
			}
		});
		sell1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellItem(0);
			}
		});
		sell1.setForeground(new Color(255, 255, 255));
		sell1.setBackground(new Color(255, 0, 0));
		sell1.setFont(new Font("Tahoma", Font.BOLD, 12));
		sell1.setBounds(38, 459, 162, 23);
		inventoryViewer.getContentPane().add(sell1);
		
		
		JButton sell2 = new JButton("Sell (+100 Gold)");
		sell2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				sell2.setFont(new Font("Tahoma", Font.BOLD, 14));
				sell2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
				sell2.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sell2.setFont(new Font("Tahoma", Font.BOLD, 12));
				sell2.setForeground(new Color(255, 255, 255));
				sell2.setBorder(null);
			}
		});
		sell2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellItem(1);
			}
		});
		sell2.setForeground(Color.WHITE);
		sell2.setFont(new Font("Tahoma", Font.BOLD, 12));
		sell2.setBackground(Color.RED);
		sell2.setBounds(231, 459, 162, 23);
		inventoryViewer.getContentPane().add(sell2);
		
		JButton sell3 = new JButton("Sell (+100 Gold)");
		sell3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				sell3.setFont(new Font("Tahoma", Font.BOLD, 14));
				sell3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
				sell3.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sell3.setFont(new Font("Tahoma", Font.BOLD, 12));
				sell3.setForeground(new Color(255, 255, 255));
				sell3.setBorder(null);
			}
		});
		sell3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellItem(2);
			}
		});
		sell3.setForeground(Color.WHITE);
		sell3.setFont(new Font("Tahoma", Font.BOLD, 12));
		sell3.setBackground(Color.RED);
		sell3.setBounds(440, 459, 150, 23);
		inventoryViewer.getContentPane().add(sell3);
		
		JButton sell4 = new JButton("Sell (+100 Gold)");
		sell4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				sell4.setFont(new Font("Tahoma", Font.BOLD, 14));
				sell4.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
				sell4.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sell4.setFont(new Font("Tahoma", Font.BOLD, 12));
				sell4.setForeground(new Color(255, 255, 255));
				sell4.setBorder(null);
			}
		});
		sell4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellItem(3);
			}
		});
		sell4.setForeground(Color.WHITE);
		sell4.setFont(new Font("Tahoma", Font.BOLD, 12));
		sell4.setBackground(Color.RED);
		sell4.setBounds(651, 459, 150, 23);
		inventoryViewer.getContentPane().add(sell4);
		
		JButton sell5 = new JButton("Sell (+100 Gold)");
		sell5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				sell5.setFont(new Font("Tahoma", Font.BOLD, 14));
				sell5.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
				sell5.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sell5.setFont(new Font("Tahoma", Font.BOLD, 12));
				sell5.setForeground(new Color(255, 255, 255));
				sell5.setBorder(null);
			}
		});
		sell5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellItem(4);
			}
		});
		sell5.setForeground(Color.WHITE);
		sell5.setFont(new Font("Tahoma", Font.BOLD, 12));
		sell5.setBackground(Color.RED);
		sell5.setBounds(877, 459, 144, 23);
		inventoryViewer.getContentPane().add(sell5);
		
		JButton[] sellButtons = new JButton[5];
		sellButtons[0] = sell1;
		sellButtons[1] = sell2;
		sellButtons[2] = sell3;
		sellButtons[3] = sell4;
		sellButtons[4] = sell5;
		
		gold = new JTextField();
		gold.setText("Available Gold: %d".formatted(this.player.getPlayerGold()));
		gold.setForeground(Color.YELLOW);
		gold.setFont(new Font("Tahoma", Font.BOLD, 20));
		gold.setEditable(false);
		gold.setColumns(10);
		gold.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.YELLOW));
		gold.setBackground(Color.DARK_GRAY);
		gold.setBounds(771, 19, 246, 28);
		inventoryViewer.getContentPane().add(gold);
	
		
		
		
		for (Monster monster: this.player.getPlayerMonsters()) {
			choice.add(monster.pickMonsterName());
		}
		
		
		
		int i = 0;
		for (Entry<Item, Integer> item : this.player.getPlayerInventory().entrySet()) {
			Item myItem = item.getKey();
			int amount = item.getValue();
			labelList[i].setIcon(new ImageIcon(myItem.getItemImage()));
			paneList[i].setText(myItem.getItemName());
			quantities[i].setText("Quantity: " + Integer.toString(amount));
			sellButtons[i].setText("Sell (+%d Gold)".formatted(myItem.getResellPrice()));
			i += 1;
		}
		
		
		while (i < labelList.length) {
			useButtons[i].setVisible(false);
			labelList[i].setVisible(false);
			paneList[i].setVisible(false);
			quantities[i].setVisible(false);
			sellButtons[i].setVisible(false);
			effectButtons[i].setVisible(false);
			i += 1;
		}
		
	
		

		
		
		
		inventoryViewer.setTitle("Inventory Viewer");
		inventoryViewer.setBounds(100, 100, 1080, 720);
		inventoryViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}