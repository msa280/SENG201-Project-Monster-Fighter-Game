package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import game.AudioPlayer;
import game.Battle;
import game.Player;
import game.Shop;
import items.Item;
import monsters.Monster;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;






public class ViewBuySection 
{

	private JFrame frmBuySection;
	private JTextField goldLeft;
	private Player player;
	private AudioPlayer backgroundAudio = new AudioPlayer();
	private AudioPlayer buttonAudio = new AudioPlayer();
	
	
	
	/**
	 * Create the application.
	 */
	public ViewBuySection(Player player) 
	{
		this.player = player;
		initialize();
		this.backgroundAudio.playSoundLoop("ShopSection.wav");
	}

	
	
	public static void launchBuySection(Player player, ViewBuySection buySection)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					buySection.frmBuySection.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	

	
	
	public void viewEffect(int buttonIndex, JTextPane updateArea)
	{
		String update = this.player.getShop().getItemsForSale().get(buttonIndex-1).getItemEffect();
		updateArea.setForeground(Color.YELLOW);
		updateArea.setText(update);
	}
	
	
	public void goBack()
	{
		this.backgroundAudio.stopSound();
		this.frmBuySection.dispose();
		PlayerHomeGUI.launchMainMenu(this.player);
	}
	
	
	public void viewMonsterStat(int buttonIndex, JTextPane errorField)
	{
		Monster monster = this.player.getShop().getMonstersForSale().get(buttonIndex-1);
		errorField.setText("Showing %s stats.".formatted(monster.getMonsterName()));
		errorField.setForeground(Color.YELLOW);
		MonsterStatGUI.launchMonsterStatScreen(monster);
	}
	
	
	
	public void buyMonster(int buttonIndex, JTextPane updateArea, JTextField goldDisplay)
	{
		int i = 1;
		
		for (Monster monster: this.player.getShop().getMonstersForSale())
		{
			if (i == buttonIndex)
			{
				String update = this.player.buyMonster(monster);
			
				if (update == "No_Gold")
				{
					updateArea.setForeground(Color.RED);
					updateArea.setText("You don't have enough gold to buy %s!\n".formatted(monster.getMonsterName()));
				} 
				else if (update == "Team_Full") 
				{
					updateArea.setForeground(Color.RED);
					updateArea.setText("Team Full! You don't have enough space in your team!\n");
				} 
				else 
				{
					updateArea.setForeground(Color.GREEN);
					updateArea.setText("%s bought!\n%s has been added to the team.\n".formatted(monster.getMonsterName(), monster.getMonsterName()));	
				}
				break;
			}
			i += 1;
		}
		goldDisplay.setText("Available Gold: " + Integer.toString(this.player.getPlayerGold()));
	}
	

	
	
	public void buyItem(int buttonIndex, JTextPane updateArea, JTextField goldDisplay)
	{
		int i = 1;
		
		for (Item item: this.player.getShop().getItemsForSale())
		{
			if (i == buttonIndex)
			{
				boolean purchaseSuccessful = this.player.buyItem(item);
				
				if (purchaseSuccessful == false) 
				{
					updateArea.setForeground(Color.RED);
					updateArea.setText("You do not have enough gold to buy %s!".formatted(item.getItemName()));
				} 
				else 
				{
					updateArea.setForeground(Color.GREEN);
					updateArea.setText("%s bought!\n%s has been added to the inventory!\n".formatted(item.getItemName(), item.getItemName()));
				}
				break;
			}
			i += 1;
		}
		goldDisplay.setText("Available Gold: " + Integer.toString(this.player.getPlayerGold()));
	}
	
	
	
	
	
	
	
	
	/**
	 *  Used for testing purposes only!
	 */
	public static void main(String[] args)
	{
		Player player = new Player();
		Shop shop = new Shop();
		shop.initializeShop();
		Battle battle = new Battle();
		player.setBattle(battle);
		player.setShop(shop);
		player.setPlayerGold(5000);
		
		ViewBuySection newSection = new ViewBuySection(player);
		ViewBuySection.launchBuySection(player, newSection);
	}

	
	

	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		
		
		frmBuySection = new JFrame();
		frmBuySection.getContentPane().setForeground(Color.GREEN);
		frmBuySection.getContentPane().setBackground(new Color(0, 0, 0));
		frmBuySection.setTitle("Buy Section");
		frmBuySection.setBounds(100, 100, 1122, 860);
		frmBuySection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBuySection.getContentPane().setLayout(null);
		
		JTextPane txtpnBuyAMonster = new JTextPane();
		txtpnBuyAMonster.setForeground(Color.MAGENTA);
		txtpnBuyAMonster.setBackground(Color.BLACK);
		txtpnBuyAMonster.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtpnBuyAMonster.setText("Buy a monster:");
		txtpnBuyAMonster.setBounds(10, 11, 144, 28);
		frmBuySection.getContentPane().add(txtpnBuyAMonster);
		
		JTextPane updateArea = new JTextPane();
		updateArea.setBackground(Color.DARK_GRAY);
		updateArea.setFont(new Font("Tahoma", Font.BOLD, 16));
		updateArea.setBounds(238, 748, 611, 43);
		frmBuySection.getContentPane().add(updateArea);
		
		JLabel mimage1 = new JLabel("New label");
		mimage1.setBounds(37, 74, 150, 150);
		frmBuySection.getContentPane().add(mimage1);
		
		JLabel mimage2 = new JLabel("New label");
		mimage2.setBounds(258, 74, 150, 150);
		frmBuySection.getContentPane().add(mimage2);
		
		JLabel mimage3 = new JLabel("New label");
		mimage3.setBounds(476, 74, 150, 150);
		frmBuySection.getContentPane().add(mimage3);
		
		JLabel mimage4 = new JLabel("New label");
		mimage4.setBounds(698, 74, 150, 150);
		frmBuySection.getContentPane().add(mimage4);
		
		JLabel mimage5 = new JLabel("New label");
		mimage5.setBounds(924, 74, 150, 150);
		frmBuySection.getContentPane().add(mimage5);
		
		JLabel mimages[] = new JLabel[5];
		
		
		mimages[0] = mimage1;
		mimages[1] = mimage2;
		mimages[2] = mimage3;
		mimages[3] = mimage4;
		mimages[4] = mimage5;
		
		
		
		
		
		JTextPane mname1 = new JTextPane();
		mname1.setBackground(new Color(0, 0, 0));
		mname1.setForeground(new Color(255, 204, 0));
		mname1.setFont(new Font("Tahoma", Font.BOLD, 14));
		mname1.setText("No Monster");
		mname1.setBounds(10, 235, 195, 20);
		frmBuySection.getContentPane().add(mname1);
		
		JTextPane mname2 = new JTextPane();
		mname2.setBackground(new Color(0, 0, 0));
		mname2.setForeground(new Color(255, 204, 0));
		mname2.setText("No Monster");
		mname2.setFont(new Font("Tahoma", Font.BOLD, 14));
		mname2.setBounds(224, 235, 214, 20);
		frmBuySection.getContentPane().add(mname2);
		
		JTextPane mname3 = new JTextPane();
		mname3.setForeground(new Color(255, 204, 0));
		mname3.setBackground(new Color(0, 0, 0));
		mname3.setText("No Monster");
		mname3.setFont(new Font("Tahoma", Font.BOLD, 14));
		mname3.setBounds(448, 235, 208, 20);
		frmBuySection.getContentPane().add(mname3);
		
		JTextPane mname4 = new JTextPane();
		mname4.setForeground(new Color(255, 204, 0));
		mname4.setBackground(new Color(0, 0, 0));
		mname4.setText("No Monster");
		mname4.setFont(new Font("Tahoma", Font.BOLD, 14));
		mname4.setBounds(666, 235, 203, 20);
		frmBuySection.getContentPane().add(mname4);
		
		JTextPane mname5 = new JTextPane();
		mname5.setForeground(new Color(255, 204, 0));
		mname5.setBackground(new Color(0, 0, 0));
		mname5.setText("No Monster");
		mname5.setFont(new Font("Tahoma", Font.BOLD, 14));
		mname5.setBounds(901, 235, 195, 20);
		frmBuySection.getContentPane().add(mname5);
		
		
		JTextPane mnames[] = new JTextPane[5];
		mnames[0] = mname1;
		mnames[1] = mname2;
		mnames[2] = mname3;
		mnames[3] = mname4;
		mnames[4] = mname5;
		
		JLabel iimage1 = new JLabel("New label");
		iimage1.setBounds(37, 428, 150, 150);
		frmBuySection.getContentPane().add(iimage1);
		
		
		JLabel iimage2 = new JLabel("New label");
		iimage2.setBounds(238, 428, 150, 150);
		frmBuySection.getContentPane().add(iimage2);
		
		JLabel iimage3 = new JLabel("New label");
		iimage3.setBounds(454, 428, 150, 150);
		frmBuySection.getContentPane().add(iimage3);
		
		JLabel iimage4 = new JLabel("New label");
		iimage4.setBounds(698, 428, 150, 150);
		frmBuySection.getContentPane().add(iimage4);
		
		JLabel iimage5 = new JLabel("New label");
		iimage5.setBounds(924, 428, 150, 150);
		frmBuySection.getContentPane().add(iimage5);
		
		JLabel iimages[] = new JLabel[5];
		iimages[0] = iimage1;
		iimages[1] = iimage2;
		iimages[2] = iimage3;
		iimages[3] = iimage4;
		iimages[4] = iimage5;
		
		
		JTextPane iname1 = new JTextPane();
		iname1.setBackground(new Color(0, 0, 0));
		iname1.setForeground(new Color(255, 204, 0));
		iname1.setText("No Item");
		iname1.setFont(new Font("Tahoma", Font.BOLD, 14));
		iname1.setBounds(10, 589, 195, 20);
		frmBuySection.getContentPane().add(iname1);
		
		JTextPane iname2 = new JTextPane();
		iname2.setForeground(new Color(255, 204, 0));
		iname2.setBackground(new Color(0, 0, 0));
		iname2.setText("No Item");
		iname2.setFont(new Font("Tahoma", Font.BOLD, 14));
		iname2.setBounds(213, 589, 195, 20);
		frmBuySection.getContentPane().add(iname2);
		
		JTextPane iname3 = new JTextPane();
		iname3.setForeground(new Color(255, 204, 0));
		iname3.setBackground(new Color(0, 0, 0));
		iname3.setText("No Item");
		iname3.setFont(new Font("Tahoma", Font.BOLD, 14));
		iname3.setBounds(442, 589, 214, 20);
		frmBuySection.getContentPane().add(iname3);
		
		JTextPane iname4 = new JTextPane();
		iname4.setForeground(new Color(255, 204, 0));
		iname4.setBackground(new Color(0, 0, 0));
		iname4.setText("No Item");
		iname4.setFont(new Font("Tahoma", Font.BOLD, 14));
		iname4.setBounds(669, 589, 200, 20);
		frmBuySection.getContentPane().add(iname4);
		
		JTextPane iname5 = new JTextPane();
		iname5.setForeground(new Color(255, 204, 0));
		iname5.setBackground(new Color(0, 0, 0));
		iname5.setText("No Item");
		iname5.setFont(new Font("Tahoma", Font.BOLD, 14));
		iname5.setBounds(901, 589, 180, 20);
		frmBuySection.getContentPane().add(iname5);
		
		JTextPane inames[] = new JTextPane[5];
		inames[0] = iname1;
		inames[1] = iname2;
		inames[2] = iname3;
		inames[3] = iname4;
		inames[4] = iname5;
		
		
		int i = 0;
		
		while (i < this.player.getShop().getMonstersForSale().size())
		{
			Monster monster = this.player.getShop().getMonstersForSale().get(i);
			Item item = this.player.getShop().getItemsForSale().get(i);
			mimages[i].setIcon(new ImageIcon(monster.getMonsterImage()));
			iimages[i].setIcon(new ImageIcon(item.getItemImage()));
			mnames[i].setText("%s (Gold: %d)".formatted(monster.getMonsterName(), monster.getPrice()));
			inames[i].setText("%s (Gold: %d)".formatted(item.getItemName(), item.getPrice()));
			i += 1;
		}
		
		
		
		goldLeft = new JTextField();
		goldLeft.setForeground(new Color(255, 255, 0));
		goldLeft.setBackground(new Color(0, 0, 0));
		goldLeft.setFont(new Font("Tahoma", Font.BOLD, 20));
		goldLeft.setText("Available Gold: " + Integer.toString(this.player.getPlayerGold()));
		goldLeft.setBounds(421, 11, 246, 28);
		frmBuySection.getContentPane().add(goldLeft);
		goldLeft.setColumns(10);
		
		JButton stat1 = new JButton("View Stats");
		stat1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(1, updateArea);
			}
		});
		stat1.setFont(new Font("Tahoma", Font.BOLD, 11));
		stat1.setBackground(Color.YELLOW);
		stat1.setBounds(37, 266, 103, 23);
		frmBuySection.getContentPane().add(stat1);
		
		JButton stat2 = new JButton("View Stats");
		stat2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(2, updateArea);
			}
		});
		stat2.setFont(new Font("Tahoma", Font.BOLD, 11));
		stat2.setBackground(Color.YELLOW);
		stat2.setBounds(250, 266, 103, 23);
		frmBuySection.getContentPane().add(stat2);
		
		JButton stat3 = new JButton("View Stats");
		stat3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(3, updateArea);
			}
		});
		stat3.setFont(new Font("Tahoma", Font.BOLD, 11));
		stat3.setBackground(Color.YELLOW);
		stat3.setBounds(476, 266, 103, 23);
		frmBuySection.getContentPane().add(stat3);
		
		JButton stat4 = new JButton("View Stats");
		stat4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(4, updateArea);
			}
		});
		stat4.setFont(new Font("Tahoma", Font.BOLD, 11));
		stat4.setBackground(Color.YELLOW);
		stat4.setBounds(698, 266, 103, 23);
		frmBuySection.getContentPane().add(stat4);
		
		JButton stat5 = new JButton("View Stats");
		stat5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(5, updateArea);
			}
		});
		stat5.setFont(new Font("Tahoma", Font.BOLD, 11));
		stat5.setBackground(Color.YELLOW);
		stat5.setBounds(924, 266, 103, 23);
		frmBuySection.getContentPane().add(stat5);
		

		
		
		JButton mbuy1 = new JButton("Buy");
		mbuy1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyMonster(1, updateArea, goldLeft);
			}
		});
		mbuy1.setForeground(Color.GREEN);
		mbuy1.setBackground(Color.BLACK);
		mbuy1.setFont(new Font("Tahoma", Font.BOLD, 13));
		mbuy1.setBounds(47, 300, 68, 23);
		frmBuySection.getContentPane().add(mbuy1);
		
		JButton mbuy2 = new JButton("Buy");
		mbuy2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyMonster(2, updateArea, goldLeft);
			}
		});
		mbuy2.setForeground(Color.GREEN);
		mbuy2.setFont(new Font("Tahoma", Font.BOLD, 13));
		mbuy2.setBackground(Color.BLACK);
		mbuy2.setBounds(258, 300, 68, 23);
		frmBuySection.getContentPane().add(mbuy2);
		
		JButton mbuy3 = new JButton("Buy");
		mbuy3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyMonster(3, updateArea, goldLeft);
			}
		});
		mbuy3.setForeground(Color.GREEN);
		mbuy3.setFont(new Font("Tahoma", Font.BOLD, 13));
		mbuy3.setBackground(Color.BLACK);
		mbuy3.setBounds(486, 300, 68, 23);
		frmBuySection.getContentPane().add(mbuy3);
		
		JButton mbuy4 = new JButton("Buy");
		mbuy4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyMonster(4, updateArea, goldLeft);
			}
		});
		mbuy4.setForeground(Color.GREEN);
		mbuy4.setFont(new Font("Tahoma", Font.BOLD, 13));
		mbuy4.setBackground(Color.BLACK);
		mbuy4.setBounds(708, 300, 68, 23);
		frmBuySection.getContentPane().add(mbuy4);
		
		JButton mbuy5 = new JButton("Buy");
		mbuy5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyMonster(5, updateArea, goldLeft);
			}
		});
		mbuy5.setForeground(Color.GREEN);
		mbuy5.setFont(new Font("Tahoma", Font.BOLD, 13));
		mbuy5.setBackground(Color.BLACK);
		mbuy5.setBounds(934, 300, 68, 23);
		frmBuySection.getContentPane().add(mbuy5);
		
		JTextPane txtpnBuyAnItem = new JTextPane();
		txtpnBuyAnItem.setText("Buy an item:");
		txtpnBuyAnItem.setForeground(Color.MAGENTA);
		txtpnBuyAnItem.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtpnBuyAnItem.setBackground(Color.BLACK);
		txtpnBuyAnItem.setBounds(10, 378, 144, 28);
		frmBuySection.getContentPane().add(txtpnBuyAnItem);
		

	
		
		JButton eff1 = new JButton("View Effects");
		eff1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewEffect(1, updateArea);
			}
		});
		eff1.setFont(new Font("Tahoma", Font.BOLD, 11));
		eff1.setBackground(Color.YELLOW);
		eff1.setBounds(37, 620, 103, 23);
		frmBuySection.getContentPane().add(eff1);
		
		
		
		JButton eff2 = new JButton("View Effects");
		eff2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewEffect(2, updateArea);
			}
		});
		eff2.setFont(new Font("Tahoma", Font.BOLD, 11));
		eff2.setBackground(Color.YELLOW);
		eff2.setBounds(250, 620, 103, 23);
		frmBuySection.getContentPane().add(eff2);
		
		JButton eff3 = new JButton("View Effects");
		eff3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewEffect(3, updateArea);
			}
		});
		eff3.setFont(new Font("Tahoma", Font.BOLD, 11));
		eff3.setBackground(Color.YELLOW);
		eff3.setBounds(476, 620, 103, 23);
		frmBuySection.getContentPane().add(eff3);
		
		JButton eff4 = new JButton("View Effects");
		eff4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewEffect(4, updateArea);
			}
		});
		eff4.setFont(new Font("Tahoma", Font.BOLD, 11));
		eff4.setBackground(Color.YELLOW);
		eff4.setBounds(711, 620, 103, 23);
		frmBuySection.getContentPane().add(eff4);
		
		JButton eff5 = new JButton("View Effects");
		eff5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewEffect(5, updateArea);
			}
		});
		eff5.setFont(new Font("Tahoma", Font.BOLD, 11));
		eff5.setBackground(Color.YELLOW);
		eff5.setBounds(924, 620, 103, 23);
		frmBuySection.getContentPane().add(eff5);
		
		
		
		
		
		
		JButton ibuy1 = new JButton("Buy");
		ibuy1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(1, updateArea, goldLeft);
			}
		});
		ibuy1.setForeground(Color.GREEN);
		ibuy1.setFont(new Font("Tahoma", Font.BOLD, 13));
		ibuy1.setBackground(Color.BLACK);
		ibuy1.setBounds(47, 654, 68, 23);
		frmBuySection.getContentPane().add(ibuy1);
		
		JButton ibuy2 = new JButton("Buy");
		ibuy2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(2, updateArea, goldLeft);
			}
		});
		ibuy2.setForeground(Color.GREEN);
		ibuy2.setFont(new Font("Tahoma", Font.BOLD, 13));
		ibuy2.setBackground(Color.BLACK);
		ibuy2.setBounds(271, 654, 68, 23);
		frmBuySection.getContentPane().add(ibuy2);
		
		JButton ibuy3 = new JButton("Buy");
		ibuy3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(3, updateArea, goldLeft);
			}
		});
		ibuy3.setForeground(Color.GREEN);
		ibuy3.setFont(new Font("Tahoma", Font.BOLD, 13));
		ibuy3.setBackground(Color.BLACK);
		ibuy3.setBounds(496, 654, 68, 23);
		frmBuySection.getContentPane().add(ibuy3);
		
		JButton ibuy4 = new JButton("Buy");
		ibuy4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(4, updateArea, goldLeft);
			}
		});
		ibuy4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ibuy4.setForeground(Color.GREEN);
		ibuy4.setFont(new Font("Tahoma", Font.BOLD, 13));
		ibuy4.setBackground(Color.BLACK);
		ibuy4.setBounds(733, 654, 68, 23);
		frmBuySection.getContentPane().add(ibuy4);
		
		JButton ibuy5 = new JButton("Buy");
		ibuy5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(5, updateArea, goldLeft);
			}
		});
		ibuy5.setForeground(Color.GREEN);
		ibuy5.setFont(new Font("Tahoma", Font.BOLD, 13));
		ibuy5.setBackground(Color.BLACK);
		ibuy5.setBounds(948, 654, 68, 23);
		frmBuySection.getContentPane().add(ibuy5);
		
		JButton backButton = new JButton("Go Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		backButton.setBounds(21, 760, 111, 31);
		frmBuySection.getContentPane().add(backButton);
		
	
	}
}
