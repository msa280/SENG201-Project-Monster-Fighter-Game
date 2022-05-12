import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JProgressBar;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

public class PlayerHomeGUI {

	private JFrame frmMainMenu;
	
	private Player player;
	AudioPlayer audio = new AudioPlayer();
	
	
	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	
	/**
	 * Launch the main menu of the game.
	 */
	
	public void launchMainMenu(PlayerHomeGUI mainMenu)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainMenu.frmMainMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	public PlayerHomeGUI(Player player, Shop shop, Battle battle) {
		
		this.player = player;
		this.player.setShop(shop);
		this.player.setBattle(battle);
		shop.getTrader(player);
		
		battle.setPlayer(player);
		
		initialize();
		
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
		
		
		Player player = new Player();
		player.setGame(game);
		player.setPlayerGold(100);
		player.setCurrentDay(4);
		player.setDaysRemaining(player.getCurrentDay(), game);
		
		Battle battles = new Battle();
		battles.generateBattles();
		
		PlayerHomeGUI mainMenu = new PlayerHomeGUI(player, shop, battles);
		mainMenu.launchMainMenu(mainMenu);
	} 
	
	
	
	
	public void sleepMessage(JTextPane pane) 
	{	
		// implement sleeping time
		long start = System.currentTimeMillis();
		long end = start + 5*1000;
		while (System.currentTimeMillis() < end) 
		{
		}
		
		/* currentDay.setText("Current Day: " + Integer.toString(this.player.getCurrentDay()));
		remainingDays.setText("Remaining Days: " + Integer.toString(this.player.getDaysRemaining())); */
		
		pane.setText("Good Morning %s!\nAll monsters have healed up over night!\nThe shop has been updated!\nNew battles are also available!\n".formatted(player.getGame().getPlayerName()));
	}
	
	
	
	public void updateDisplay(JFormattedTextField currentDayField, JFormattedTextField remainingDaysField)
	{
		currentDayField.setText("Current Day: " + Integer.toString(this.player.getCurrentDay()));
		remainingDaysField.setText("Remaining Days: " + Integer.toString(this.player.getDaysRemaining()));
	}
	
	
	public void sleep(JFrame frmMainMenu)
	{
		this.player.playerSleep();
		
		/* Exits the game if game is over. */
		if (this.player.getDaysRemaining() == -1)
		{
			frmMainMenu.dispose();
		}
	}
	
	
	public void openTeamViewer(JFrame oldFrame)
	{
		ViewTeam teamViewer = new ViewTeam(this.player, oldFrame);
		ViewTeam.launchTeamViewer(player, teamViewer);
		oldFrame.setVisible(false);
		this.audio.stopSound();
		
	}
	
	
	public AudioPlayer getAudioPlayer()
	{
		return this.audio;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.audio.playSound("MainMenu.wav");
		
		frmMainMenu = new JFrame();
		frmMainMenu.getContentPane().setForeground(Color.WHITE);
		frmMainMenu.getContentPane().setBackground(Color.BLACK);
		frmMainMenu.setTitle("Main Menu");
		frmMainMenu.setBounds(100, 100, 1080, 720);
		frmMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainMenu.getContentPane().setLayout(null);
		
		
		JTextPane txtpnWhatWouldYou = new JTextPane();
		txtpnWhatWouldYou.setEditable(false);
		txtpnWhatWouldYou.setForeground(new Color(255, 255, 255));
		txtpnWhatWouldYou.setBackground(UIManager.getColor("windowText"));
		txtpnWhatWouldYou.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtpnWhatWouldYou.setText("What would you like to do?");
		txtpnWhatWouldYou.setBounds(61, 185, 235, 30);
		frmMainMenu.getContentPane().add(txtpnWhatWouldYou);
		txtpnWhatWouldYou.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		
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
				openTeamViewer(frmMainMenu);
			}
		});
		teamButton.setBounds(61, 239, 163, 39);
		frmMainMenu.getContentPane().add(teamButton);
		
		JButton inventoryButton = new JButton("View Inventory");
		inventoryButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		inventoryButton.setBounds(61, 289, 163, 39);
		frmMainMenu.getContentPane().add(inventoryButton);
		
		JButton battlesButton = new JButton("View Battles");
		battlesButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		battlesButton.setBounds(61, 339, 163, 39);
		frmMainMenu.getContentPane().add(battlesButton);
		
		JButton shopButton = new JButton("View Shop");
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
				sleep(frmMainMenu);	
				sleepMessage(updateArea);
				updateDisplay(currentDayField, remainingDaysField);
			}
		});
		sleepButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		sleepButton.setBounds(61, 439, 163, 39);
		frmMainMenu.getContentPane().add(sleepButton);
		
		JButton quitGameButton = new JButton("Quit Game");
		quitGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmMainMenu.dispose();
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
		
	}
}
