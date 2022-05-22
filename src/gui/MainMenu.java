package gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import game.AudioPlayer;
import game.Game;
import game.Player;
import monsters.Venomhound;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


public class MainMenu 
{
	/*
	 * The main menu frame.
	 */
	private JFrame frmMainMenu;
	/*
	 * The player.
	 */
	private Player player;
	/*
	 * The background audio.
	 */
	private AudioPlayer backgroundAudio = new AudioPlayer();
	/*
	 * The button audio.
	 */
	private AudioPlayer buttonAudio = new AudioPlayer();
	
	
	/*
	 * Launches the main menu of the game.
	 * 
	 * @param player The player.
	 */
	public static void launchMainMenu(Player player)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu menu = new MainMenu(player);
					menu.frmMainMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Creates the player home GUI.
	 * 
	 * @param player The player.
	 */
	public MainMenu(Player player) 
	{
		this.player = player;
		this.player.getBattle().setPlayer(this.player);
		initialize();
	}
	
	/*
	 * Sets the text on the label.
	 * 
	 * @param goldPane The text pane that shows the players gold.
	 */
	public void setLabel(JTextPane goldPane)
	{
		goldPane.setText(Integer.toString(this.player.getPlayerGold()));
	}
	
	/*
	 * Initializes the GUI.
	 */
	public static void main(String[] args)
	{
		Game game = new Game();
		game.setGameLength(7);
	
		Player player = new Player();
		player.initialize(game);
		player.addToTeam(new Venomhound());
		player.setGame(game);
		player.setPlayerGold(6000);
		
		MainMenu.launchMainMenu(player);
		
	} 
	
	/*
	 * The delay timer for when the player sleeps.
	 */
	public void sleepTimer() 
	{	
		this.backgroundAudio.stopSound();
		// implement sleeping time
		long start = System.currentTimeMillis();
		long end = start + 3*1000;
		while (System.currentTimeMillis() < end) 
		{
		}
	}
	
	/*
	 * Allows the player to sleep.
	 */
	public void sleep()
	{
		this.buttonAudio.playSoundOnce("buttonA.wav");
		this.frmMainMenu.dispose();
		this.player.playerSleep();
		
		if (this.player.getGame().getGameOver() == true)
		{
			GameOver.launchGameOver(this.player);
		}
		else
		{
			MainMenu.launchMainMenu(this.player);
			GoodMorningGUI.launchGoodMorning(this.player);
		}	
	}
	
	/*
	 * Launches the team view GUI.
	 */
	public void openTeamViewer()
	{
		this.frmMainMenu.dispose();
		this.buttonAudio.playSoundOnce("buttonA.wav");
		PlayerTeam.launchTeamViewer(this.player);
	}
	
	/*
	 * Launches the inventory GUI.
	 */
	public void openInventoryViewer()
	{
		this.frmMainMenu.dispose();
		this.buttonAudio.playSoundOnce("buttonA.wav");
		PlayerInventory.launchInventoryViewer(this.player);
	}
	
	/*
	 * Launches the shop GUI.
	 */
	public void openShop()
	{
		this.frmMainMenu.dispose();
		this.buttonAudio.playSoundOnce("buttonA.wav");
		BuyShop.launchBuySection(player);
	}
	
	/*
	 * Launches the choose battle GUI.
	 */
	public void openBattles()
	{
		this.frmMainMenu.dispose();
		this.buttonAudio.playSoundOnce("buttonA.wav");
		ChooseBattle.launchChooseBattle(this.player);
	}
	
	/*
	 * Quits the game.
	 */
	public void quitGame()
	{
		this.frmMainMenu.dispose();
		this.buttonAudio.playSoundOnce("buttonA.wav");
	}
	
	/*
	 * Ends the game by launching the game over screen.
	 */
	public void gameOver()
	{
		this.frmMainMenu.dispose();
		GameOver.launchGameOver(this.player);
	}
	
	/*
	 * Initializes the contents of the frame.
	 */
	private void initialize() {
		
		
		frmMainMenu = new JFrame();
		frmMainMenu.setResizable(false);
		frmMainMenu.getContentPane().setForeground(Color.WHITE);
		frmMainMenu.getContentPane().setBackground(Color.BLACK);
		frmMainMenu.setTitle("Main Menu");
		frmMainMenu.setBounds(100, 100, 1080, 720);
		frmMainMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMainMenu.getContentPane().setLayout(null);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmMainMenu.setLocation(dim.width/2-frmMainMenu.getSize().width/2, dim.height/2-frmMainMenu.getSize().height/2);
		
		
		JFormattedTextField goldField = new JFormattedTextField();
		goldField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.YELLOW));
		goldField.setBackground(Color.BLACK);
		goldField.setForeground(Color.YELLOW);
		goldField.setFont(new Font("Tahoma", Font.BOLD, 20));
		goldField.setBounds(37, 41, 230, 30);
		frmMainMenu.getContentPane().add(goldField);
		goldField.setText("Available Gold: " + Integer.toString(this.player.getPlayerGold()));
		
		JFormattedTextField currentDayField = new JFormattedTextField();
		currentDayField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.GREEN));
		currentDayField.setText("Available Gold: 0");
		currentDayField.setForeground(Color.GREEN);
		currentDayField.setFont(new Font("Tahoma", Font.BOLD, 20));
		currentDayField.setBackground(Color.BLACK);
		currentDayField.setBounds(37, 82, 230, 30);
		frmMainMenu.getContentPane().add(currentDayField);
		currentDayField.setText("Current Day: " + Integer.toString(this.player.getCurrentDay()));
		
		JFormattedTextField remainingDaysField = new JFormattedTextField();
		remainingDaysField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.ORANGE));
		remainingDaysField.setText("Available Gold: 0");
		remainingDaysField.setForeground(Color.ORANGE);
		remainingDaysField.setFont(new Font("Tahoma", Font.BOLD, 20));
		remainingDaysField.setBackground(Color.BLACK);
		remainingDaysField.setBounds(37, 124, 230, 30);
		frmMainMenu.getContentPane().add(remainingDaysField);
		remainingDaysField.setText("Days Remaining: " + Integer.toString(this.player.getDaysRemaining()));
		
		JButton teamButton = new JButton("View Team");
		teamButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				teamButton.setBorder(new MatteBorder(5, 5, 5, 5, (Color) Color.BLACK));
				teamButton.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				teamButton.setBackground(Color.CYAN);
				teamButton.setBorder(null);
			}
		});
		
		teamButton.setBackground(Color.CYAN);
		teamButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		teamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openTeamViewer();
			}
		});
		teamButton.setBounds(37, 251, 203, 53);
		frmMainMenu.getContentPane().add(teamButton);
		
		JButton inventoryButton = new JButton("View Inventory");
		inventoryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				inventoryButton.setBackground(Color.GREEN);
				inventoryButton.setBorder(new MatteBorder(5, 5, 5, 5, (Color) Color.BLACK));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inventoryButton.setBackground(Color.CYAN);
				inventoryButton.setBorder(null);
			}
		});

		inventoryButton.setBackground(Color.CYAN);
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openInventoryViewer();
			}
		});
		inventoryButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		inventoryButton.setBounds(37, 315, 203, 53);
		frmMainMenu.getContentPane().add(inventoryButton);
		
		JButton battlesButton = new JButton("View Battles");
		battlesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				battlesButton.setBackground(Color.GREEN);
				battlesButton.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				battlesButton.setBackground(Color.CYAN);
				battlesButton.setBorder(null);
			}
		});
		
		battlesButton.setBackground(Color.CYAN);
		battlesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openBattles();
			}
		});
		battlesButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		battlesButton.setBounds(37, 446, 203, 53);
		frmMainMenu.getContentPane().add(battlesButton);
		
		JButton shopButton = new JButton("View Shop");
		shopButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				shopButton.setBackground(Color.GREEN);
				shopButton.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				shopButton.setBackground(Color.CYAN);
				shopButton.setBorder(null);
			}
		});
		
		shopButton.setBackground(Color.CYAN);
		shopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openShop();
			}
		});
		shopButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		shopButton.setBounds(37, 379, 203, 56);
		frmMainMenu.getContentPane().add(shopButton);
		
		JButton sleepButton = new JButton("Sleep");
		sleepButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				sleepButton.setBackground(Color.GREEN);
				sleepButton.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sleepButton.setBackground(Color.CYAN);
				sleepButton.setBorder(null);
			}
		});
		sleepButton.setBackground(Color.CYAN);
		sleepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sleep();	
			}
		});
		sleepButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		sleepButton.setBounds(37, 509, 203, 53);
		frmMainMenu.getContentPane().add(sleepButton);
		
		JButton quitGameButton = new JButton("Quit Game");
		quitGameButton.setForeground(Color.YELLOW);
		quitGameButton.setBackground(Color.BLACK);
		quitGameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				quitGameButton.setBackground(Color.RED);
				quitGameButton.setForeground(Color.BLACK);
				quitGameButton.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitGameButton.setBackground(Color.BLACK);
				quitGameButton.setForeground(Color.YELLOW);
				quitGameButton.setBorder(null);
			}
		});
		quitGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitGame();
			}
		});
		quitGameButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		quitGameButton.setBounds(904, 617, 134, 41);
		frmMainMenu.getContentPane().add(quitGameButton);
		
		JTextArea txtrWhatWouldYou = new JTextArea();
		txtrWhatWouldYou.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 255, 255)));
		txtrWhatWouldYou.setBackground(new Color(0, 0, 0));
		txtrWhatWouldYou.setForeground(Color.WHITE);
		txtrWhatWouldYou.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtrWhatWouldYou.setText("What would you like to do?");
		txtrWhatWouldYou.setBounds(37, 196, 285, 30);
		frmMainMenu.getContentPane().add(txtrWhatWouldYou);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Images\\MenuBackground.gif"));
		lblNewLabel.setBounds(0, 0, 1064, 681);
		frmMainMenu.getContentPane().add(lblNewLabel);
		
	}
}
