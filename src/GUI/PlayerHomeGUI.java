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
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

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
	
		Player player = new Player();
		player.initialize(game);
		
		PlayerHomeGUI.launchMainMenu(player);
		
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
		this.buttonAudio.playSoundOnce("buttonA.wav");
		this.player.playerSleep();
		this.frmMainMenu.dispose();
		GoodMorningGUI.launchGoodMorning(this.player);
		PlayerHomeGUI.launchMainMenu(this.player);
	}
	
	
	public void openTeamViewer()
	{
		this.backgroundAudio.stopSound();
		this.frmMainMenu.dispose();
		this.buttonAudio.playSoundOnce("buttonA.wav");
		ViewTeam.launchTeamViewer(this.player);
	}
	
	
	public void openInventoryViewer()
	{
		this.backgroundAudio.stopSound();
		this.frmMainMenu.dispose();
		this.buttonAudio.playSoundOnce("buttonA.wav");
		ViewInventory.launchInventoryViewer(this.player);
	}
	
	public void openShop()
	{
		this.backgroundAudio.stopSound();
		this.frmMainMenu.dispose();
		this.buttonAudio.playSoundOnce("buttonA.wav");
		ViewBuySection shop = new ViewBuySection(this.player);
		ViewBuySection.launchBuySection(player, shop);
	}
	
	public void openBattles()
	{
		this.backgroundAudio.stopSound();
		this.frmMainMenu.dispose();
		this.buttonAudio.playSoundOnce("buttonA.wav");
		ChooseBattleGUI.launchChooseBattle(this.player);
	}
	
	
	public void quitGame()
	{
		this.backgroundAudio.stopSound();
		this.frmMainMenu.dispose();
		this.buttonAudio.playSoundOnce("buttonA.wav");
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
		goldField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.WHITE));
		goldField.setBackground(Color.BLACK);
		goldField.setForeground(Color.WHITE);
		goldField.setFont(new Font("Tahoma", Font.BOLD, 20));
		goldField.setBounds(37, 41, 230, 30);
		frmMainMenu.getContentPane().add(goldField);
		goldField.setText("Available Gold: " + Integer.toString(this.player.getPlayerGold()));
		
		JFormattedTextField currentDayField = new JFormattedTextField();
		currentDayField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.WHITE));
		currentDayField.setText("Available Gold: 0");
		currentDayField.setForeground(Color.WHITE);
		currentDayField.setFont(new Font("Tahoma", Font.BOLD, 20));
		currentDayField.setBackground(Color.BLACK);
		currentDayField.setBounds(37, 82, 230, 30);
		frmMainMenu.getContentPane().add(currentDayField);
		currentDayField.setText("Current Day: " + Integer.toString(this.player.getCurrentDay()));
		
		JFormattedTextField remainingDaysField = new JFormattedTextField();
		remainingDaysField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.WHITE));
		remainingDaysField.setText("Available Gold: 0");
		remainingDaysField.setForeground(Color.WHITE);
		remainingDaysField.setFont(new Font("Tahoma", Font.BOLD, 20));
		remainingDaysField.setBackground(Color.BLACK);
		remainingDaysField.setBounds(37, 124, 230, 30);
		frmMainMenu.getContentPane().add(remainingDaysField);
		remainingDaysField.setText("Days Remaining: " + Integer.toString(this.player.getDaysRemaining()));
		
		JButton teamButton = new JButton("View Team");
		teamButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				teamButton.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				teamButton.setBackground(Color.CYAN);
			}
		});
		teamButton.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
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
				inventoryButton.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inventoryButton.setBackground(Color.CYAN);
			}
		});
		inventoryButton.setBorder(new MatteBorder(5, 5, 5, 5, (Color) Color.BLACK));
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
				battlesButton.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				battlesButton.setBackground(Color.CYAN);
			}
		});
		battlesButton.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
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
				shopButton.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				shopButton.setBackground(Color.CYAN);
			}
		});
		shopButton.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
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
				sleepButton.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sleepButton.setBackground(Color.CYAN);
			}
		});
		sleepButton.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		sleepButton.setBackground(Color.CYAN);
		sleepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sleepTimer();
				sleep();	
			}
		});
		sleepButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		sleepButton.setBounds(37, 509, 203, 53);
		frmMainMenu.getContentPane().add(sleepButton);
		
		JButton quitGameButton = new JButton("Quit Game");
		quitGameButton.setBackground(Color.YELLOW);
		quitGameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitGameButton.setBackground(Color.RED);
				quitGameButton.setForeground(Color.YELLOW);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitGameButton.setBackground(Color.YELLOW);
				quitGameButton.setForeground(Color.BLACK);
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
