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

public class PlayerHomeGUI {

	private JFrame frmMainMenu;
	
	private Player player;
	private AudioPlayer audioPlayer;
	
	
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
	public PlayerHomeGUI(Player player) {
		this.player = player;
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
		
		Player player = new Player();
		player.setGame(game);
		player.setPlayerGold(100);
		player.setCurrentDay(4);
		player.setDaysRemaining(player.getCurrentDay(), game);
		
		PlayerHomeGUI mainMenu = new PlayerHomeGUI(player);
		mainMenu.launchMainMenu(mainMenu);
		System.out.print(mainMenu.getPlayer().getPlayerGold() + "\n");
		System.out.print(mainMenu.getPlayer().getDaysRemaining());
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frmMainMenu = new JFrame();
		frmMainMenu.getContentPane().setBackground(UIManager.getColor("TextPane.foreground"));
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
		
		JTextPane goldTPane = new JTextPane();
		goldTPane.setEditable(false);
		goldTPane.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		goldTPane.setForeground(new Color(255, 255, 255));
		goldTPane.setFont(new Font("Times New Roman", Font.BOLD, 20));
		goldTPane.setBounds(61, 42, 193, 30);
		frmMainMenu.getContentPane().add(goldTPane);
		goldTPane.setText("Available Gold: " + Integer.toString(this.player.getPlayerGold()));
		
		JTextPane currentDayTPane = new JTextPane();
		currentDayTPane.setEditable(false);
		currentDayTPane.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		currentDayTPane.setForeground(new Color(255, 255, 255));
		currentDayTPane.setFont(new Font("Times New Roman", Font.BOLD, 20));
		currentDayTPane.setBounds(61, 83, 181, 34);
		frmMainMenu.getContentPane().add(currentDayTPane);
		currentDayTPane.setText("Current Day: " + Integer.toString(this.player.getCurrentDay()));
		
		JTextPane remainingDaysTPane = new JTextPane();
		remainingDaysTPane.setEditable(false);
		remainingDaysTPane.setForeground(new Color(255, 255, 255));
		remainingDaysTPane.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		remainingDaysTPane.setFont(new Font("Times New Roman", Font.BOLD, 20));
		remainingDaysTPane.setBounds(61, 128, 193, 30);
		frmMainMenu.getContentPane().add(remainingDaysTPane);
		remainingDaysTPane.setText("Remaining Days: " + Integer.toString(this.player.getDaysRemaining()));
		
		JButton teamButton = new JButton("View Team");
		teamButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		teamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JButton sleepButton = new JButton("Sleep");
		sleepButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		sleepButton.setBounds(61, 439, 163, 39);
		frmMainMenu.getContentPane().add(sleepButton);
		
		JButton quitGameButton = new JButton("Quit Game");
		quitGameButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		quitGameButton.setBounds(10, 645, 108, 25);
		frmMainMenu.getContentPane().add(quitGameButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Images\\MainMenuBackground.jpg"));
		lblNewLabel.setBounds(0, 0, 1054, 681);
		frmMainMenu.getContentPane().add(lblNewLabel);
	}
}
