package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import game.AudioPlayer;
import game.Battle;
import game.Game;
import game.Player;
import game.Shop;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

public class PlayerHomeGUI 
{

	private JFrame frmMainMenu;
	private Player player;
	AudioPlayer backgroundAudio = new AudioPlayer();
	AudioPlayer buttonAudio = new AudioPlayer();
	
	
	/**
	 * Launch the main menu of the game.
	 */
	public static void launchMainMenu(Player player)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerHomeGUI menu = new PlayerHomeGUI(player);
					menu.frmMainMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	public PlayerHomeGUI(Player player) 
	{
		
		this.player = player;
		initialize();
		this.backgroundAudio.playSoundLoop("MainMenu.wav");
	}
	
	
	public void setLabel(JTextPane goldPane)
	{
		goldPane.setText(Integer.toString(this.player.getPlayerGold()));
	}
	
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.setGameLength(7);
	
		Shop shop = new Shop();
		shop.initializeShop();
		
		Battle battles = new Battle();
		battles.generateBattles();
		
		Player player = new Player();
		player.setGame(game);
		player.setPlayerGold(6000);
		player.setCurrentDay(4);
		player.setDaysRemaining(player.getCurrentDay(), game);
		player.setShop(shop);
		
	} 
	
	
	
	
	public void sleepTimer() 
	{	
		this.backgroundAudio.stopSound();
		// implement sleeping time
		long start = System.currentTimeMillis();
		long end = start + 5*1000;
		while (System.currentTimeMillis() < end) 
		{
		}
	}
	
	
	
	public void sleep()
	{
		this.player.playerSleep();
		this.frmMainMenu.dispose();
		GoodMorningGUI.launchGoodMorning(this.player);
		PlayerHomeGUI.launchMainMenu(this.player);
	}
	
	
	public void openTeamViewer()
	{
		this.backgroundAudio.stopSound();
		this.frmMainMenu.dispose();
		ViewTeam.launchTeamViewer(this.player);
	}
	
	
	public void openInventoryViewer()
	{
		this.backgroundAudio.stopSound();
		this.frmMainMenu.dispose();
		ViewInventory.launchInventoryViewer(this.player);
	}
	
	public void openShop()
	{
		this.backgroundAudio.stopSound();
		this.frmMainMenu.dispose();
		ViewBuySection shop = new ViewBuySection(this.player);
		ViewBuySection.launchBuySection(player, shop);
	}
	
	public void openBattles()
	{
		this.backgroundAudio.stopSound();
		this.frmMainMenu.dispose();
		ChooseBattleGUI.launchChooseBattle(this.player);
	}
	
	
	public void quitGame()
	{
		this.backgroundAudio.stopSound();
		this.frmMainMenu.dispose();
	}
	
	

	
	
	
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frmMainMenu = new JFrame();
		frmMainMenu.getContentPane().setForeground(Color.WHITE);
		frmMainMenu.getContentPane().setBackground(Color.BLACK);
		frmMainMenu.setTitle("Main Menu");
		frmMainMenu.setBounds(100, 100, 1080, 720);
		frmMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainMenu.getContentPane().setLayout(null);
		
		JFormattedTextField goldField = new JFormattedTextField();
		goldField.setBackground(Color.BLACK);
		goldField.setForeground(Color.WHITE);
		goldField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		goldField.setBounds(61, 41, 220, 30);
		frmMainMenu.getContentPane().add(goldField);
		goldField.setText("Available Gold: " + Integer.toString(this.player.getPlayerGold()));
		
		JFormattedTextField currentDayField = new JFormattedTextField();
		currentDayField.setText("Available Gold: 0");
		currentDayField.setForeground(Color.WHITE);
		currentDayField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		currentDayField.setBackground(Color.BLACK);
		currentDayField.setBounds(61, 82, 220, 30);
		frmMainMenu.getContentPane().add(currentDayField);
		currentDayField.setText("Current Day: " + Integer.toString(this.player.getCurrentDay()));
		
		JFormattedTextField remainingDaysField = new JFormattedTextField();
		remainingDaysField.setText("Available Gold: 0");
		remainingDaysField.setForeground(Color.WHITE);
		remainingDaysField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		remainingDaysField.setBackground(Color.BLACK);
		remainingDaysField.setBounds(61, 123, 220, 30);
		frmMainMenu.getContentPane().add(remainingDaysField);
		remainingDaysField.setText("Days Remaining: " + Integer.toString(this.player.getDaysRemaining()));
		
		JButton teamButton = new JButton("View Team");
		teamButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		teamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openTeamViewer();
			}
		});
		teamButton.setBounds(61, 239, 163, 39);
		frmMainMenu.getContentPane().add(teamButton);
		
		JButton inventoryButton = new JButton("View Inventory");
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openInventoryViewer();
			}
		});
		inventoryButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		inventoryButton.setBounds(61, 289, 163, 39);
		frmMainMenu.getContentPane().add(inventoryButton);
		
		JButton battlesButton = new JButton("View Battles");
		battlesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openBattles();
			}
		});
		battlesButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		battlesButton.setBounds(61, 339, 163, 39);
		frmMainMenu.getContentPane().add(battlesButton);
		
		JButton shopButton = new JButton("View Shop");
		shopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openShop();
			}
		});
		shopButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		shopButton.setBounds(61, 389, 163, 39);
		frmMainMenu.getContentPane().add(shopButton);
		
		JTextPane updateArea = new JTextPane();
		updateArea.setFont(new Font("Tahoma", Font.BOLD, 14));
		updateArea.setBackground(new Color(169, 169, 169));
		updateArea.setBounds(377, 594, 333, 76);
		frmMainMenu.getContentPane().add(updateArea);
		
		JButton sleepButton = new JButton("Sleep");
		sleepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sleepTimer();
				sleep();	
			}
		});
		sleepButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		sleepButton.setBounds(61, 439, 163, 39);
		frmMainMenu.getContentPane().add(sleepButton);
		
		JButton quitGameButton = new JButton("Quit Game");
		quitGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitGame();
			}
		});
		quitGameButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		quitGameButton.setBounds(10, 645, 108, 25);
		frmMainMenu.getContentPane().add(quitGameButton);
		
		JTextPane txtpnNotifications = new JTextPane();
		txtpnNotifications.setForeground(new Color(255, 255, 255));
		txtpnNotifications.setBackground(new Color(0, 0, 0));
		txtpnNotifications.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtpnNotifications.setText("Notifications");
		txtpnNotifications.setBounds(487, 563, 108, 21);
		frmMainMenu.getContentPane().add(txtpnNotifications);
		
		JTextArea txtrWhatWouldYou = new JTextArea();
		txtrWhatWouldYou.setBackground(Color.BLACK);
		txtrWhatWouldYou.setForeground(Color.WHITE);
		txtrWhatWouldYou.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtrWhatWouldYou.setText("What would you like to do?");
		txtrWhatWouldYou.setBounds(61, 194, 285, 30);
		frmMainMenu.getContentPane().add(txtrWhatWouldYou);
		
	}
}
