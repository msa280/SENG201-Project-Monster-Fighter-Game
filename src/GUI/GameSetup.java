package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

import game.AudioPlayer;
import game.Game;
import game.Player;
import monsters.Cavernfreak;
import monsters.Hollowtree;
import monsters.Monster;
import monsters.Mornpest;
import monsters.Soilscreamer;
import monsters.Venomhound;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.JRadioButton;
import java.awt.Choice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

/*
 * Sets up the game. Does basic pre-game setup such as naming the player and
 * initializing audio.
 */
public class GameSetup {
	

	private JFrame frmGameSetup;
	private JTextPane printNameError;
	/*
	 * An instance of the Game class.
	 */
	private Game game;
	/*
	 * Whether or not the setup process has successfully been completed.
	 */
	boolean setupComplete = false;
	/*
	 * An instance of the AudioPlayer class.
	 */
	private AudioPlayer buttonSound = new AudioPlayer();

	
	private Player player;
	
	
	
	
	
	
	/*
	 * A GUI that allows the player to select the monster they want.
	 * 
	 * @param buttons The buttons for each monster.
	 * @param selectedButton The button the player selected.
	 * @param monster The monster that corresponds to the selected button.
	 */
	public void selectMonster(JRadioButton[] buttons, JRadioButton selectedButton, Monster monster)
	{
		this.buttonSound.playSoundOnce("buttonA.wav");
		if (selectedButton.isSelected()) {
			this.game.setSelectedMonster(monster);
			selectedButton.setForeground(Color.GREEN);
			selectedButton.setText("Selected");
			for (JRadioButton button : buttons) {
				if (button != selectedButton) {
					button.setForeground(Color.RED);
					button.setSelected(false);
					button.setText("Select");
				}
			}
		} else {
			this.game.setSelectedMonster(null);
			selectedButton.setForeground(Color.RED);
			selectedButton.setText("Select");
		}	
	}
	
	
	
	
	
	/*
	 * Checks whether the name passed the error check or not. If it did, name is accepted,
	 * else resets the text box.
	 * 
	 * @param enterName The enterName TextPane which will likely contain an input name.
	 * @param printNameError Prints an error if the name is invalid.\
	 * 
	 * @return Returns true if the name was invalid, else false.
	 */
	public boolean nameErrorCheckPassed(JTextPane enterName, JTextPane printNameError) 
	{
		String enteredName = enterName.getText();
		String error = this.game.checkPlayerName(enteredName);
		printNameError.setText(error);
		if (error != "OK") {
			enterName.setText("");
			printNameError.setForeground(Color.RED);
			return true;
		} else {
			this.game.setPlayerName(enteredName);
			printNameError.setForeground(Color.GREEN);
			return false;
		}
	}
	
	
	/*
	 * Checks whether or not the player has selected a monster to start with.
	 * 
	 * @param monsterSelectionError Checks if the player has selected a monster or not. If they have,
	 * prints "OK.", else prints an error.
	 * 
	 * @return Returns true if the player failed to choose a monster, else false.
	 */
	public boolean monsterSelectionCheck(JTextPane monsterSelectionError)
	{
		this.buttonSound.playSoundOnce("buttonA.wav");
		if (this.game.getSelectedMonster() == null) {
			monsterSelectionError.setText("Error: Please select a monster.");
			monsterSelectionError.setForeground(Color.RED);
			return true;
		} else {
			monsterSelectionError.setText("OK.");
			monsterSelectionError.setForeground(Color.GREEN);
			return false;
		}
	}
	
	
	/*
	 * Finishes the game setup, and starts the main game.
	 * 
	 * @param monsterName The name of the monster the player chose.
	 * @param gameLength The length of the game in days.
	 * @param difficulty The difficulty of the game.
	 */
	public void finishSetup(String monsterName, int gameLength, String difficulty)
	{
		this.game.finishSetup(monsterName, gameLength, difficulty);
		this.frmGameSetup.dispose();
		this.player.initialize(this.game);
		MainMenu.launchMainMenu(this.player);
	}
	
	
	
	
	
	/*
	 * Starts the game.
	 */
	public void startGame()
	{
		
	
		
	} 
	
	
	
	
	

	
	/*
	 * Launches the game setup screen.
	 * 
	 * @param window The setup window.
	 */
	public static void launchSetupMenu(Player player, Game game) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameSetup window = new GameSetup(player, game);
					window.frmGameSetup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	

	/*
	 * Creates the game setup screen.
	 */
	public GameSetup(Player player, Game game) {
		this.player = player;
		this.game = game;
		initialize();
	}
	

	/*
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JRadioButton[] buttons = new JRadioButton[5];
		
		frmGameSetup = new JFrame();
		frmGameSetup.setResizable(false);
		frmGameSetup.setTitle("Game Setup");
		frmGameSetup.getContentPane().setBackground(Color.DARK_GRAY);
		frmGameSetup.getContentPane().setForeground(Color.BLACK);
		frmGameSetup.setBounds(100, 100, 1174, 578);
		frmGameSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGameSetup.getContentPane().setLayout(null);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmGameSetup.setLocation(dim.width/2-frmGameSetup.getSize().width/2, dim.height/2-frmGameSetup.getSize().height/2);
		
		JTextArea askFighterName = new JTextArea();
		askFighterName.setForeground(new Color(255, 255, 255));
		askFighterName.setBounds(27, 31, 229, 23);
		askFighterName.setEditable(false);
		askFighterName.setFont(new Font("Tahoma", Font.BOLD, 18));
		askFighterName.setText("Enter your fighter name:");
		askFighterName.setBackground(Color.DARK_GRAY);
		frmGameSetup.getContentPane().add(askFighterName);
		
		JTextArea askChooseMonster = new JTextArea();
		askChooseMonster.setEditable(false);
		askChooseMonster.setForeground(new Color(255, 255, 255));
		askChooseMonster.setBounds(27, 74, 211, 23);
		askChooseMonster.setBackground(Color.DARK_GRAY);
		askChooseMonster.setFont(new Font("Tahoma", Font.BOLD, 18));
		askChooseMonster.setText("Choose your monster:");
		frmGameSetup.getContentPane().add(askChooseMonster);
		
		printNameError = new JTextPane();
		printNameError.setForeground(Color.RED);
		printNameError.setFont(new Font("Tahoma", Font.BOLD, 15));
		printNameError.setBackground(Color.DARK_GRAY);
		printNameError.setEditable(false);
		printNameError.setBounds(468, 31, 680, 23);
		frmGameSetup.getContentPane().add(printNameError);
		
		JTextPane monsterSelectionError = new JTextPane();
		monsterSelectionError.setFont(new Font("Tahoma", Font.BOLD, 15));
		monsterSelectionError.setEditable(false);
		monsterSelectionError.setBackground(Color.DARK_GRAY);
		monsterSelectionError.setForeground(Color.RED);
		monsterSelectionError.setBounds(232, 74, 526, 23);
		frmGameSetup.getContentPane().add(monsterSelectionError);
		
		JTextPane enterName = new JTextPane();
		enterName.setDropMode(DropMode.INSERT);
		enterName.setForeground(new Color(0, 0, 128));
		enterName.setFont(new Font("Tahoma", Font.BOLD, 16));
		enterName.setBackground(Color.LIGHT_GRAY);
		enterName.setBounds(266, 31, 205, 23);
		frmGameSetup.getContentPane().add(enterName);
		
		JTextArea askMonsterRename = new JTextArea();
		askMonsterRename.setEditable(false);
		askMonsterRename.setBackground(Color.DARK_GRAY);
		askMonsterRename.setForeground(new Color(255, 255, 255));
		askMonsterRename.setFont(new Font("Tahoma", Font.BOLD, 18));
		askMonsterRename.setText("(Optional) Rename your selected monster:");
		askMonsterRename.setBounds(27, 399, 390, 22);
		frmGameSetup.getContentPane().add(askMonsterRename);
		
		JTextPane enterMonsterRename = new JTextPane();
		enterMonsterRename.setForeground(new Color(0, 0, 128));
		enterMonsterRename.setFont(new Font("Tahoma", Font.BOLD, 16));
		enterMonsterRename.setDropMode(DropMode.INSERT);
		enterMonsterRename.setBackground(Color.LIGHT_GRAY);
		enterMonsterRename.setBounds(427, 399, 205, 23);
		frmGameSetup.getContentPane().add(enterMonsterRename);
		
		
		Choice enterDays = new Choice();
		enterDays.setForeground(new Color(0, 0, 128));
		enterDays.setFont(new Font("Tahoma", Font.BOLD, 16));
		enterDays.setBackground(Color.LIGHT_GRAY);
		enterDays.setBounds(443, 444, 71, 26);
		frmGameSetup.getContentPane().add(enterDays);
		for (int i = 5; i < 16; i++)
		{
			enterDays.add(Integer.toString(i));
		}
		
		
		Choice enterDifficulty = new Choice();
		enterDifficulty.setFont(new Font("Tahoma", Font.BOLD, 16));
		enterDifficulty.setForeground(new Color(0, 0, 128));
		enterDifficulty.setBackground(Color.LIGHT_GRAY);
		enterDifficulty.setBounds(248, 489, 81, 26);
		frmGameSetup.getContentPane().add(enterDifficulty);
		enterDifficulty.add("Easy");
		enterDifficulty.add("Normal");
		enterDifficulty.add("Classic");
		enterDifficulty.add("Hard");
		enterDifficulty.add("Boss");
		

		JButton StartGameButton = new JButton("Start Game");
		StartGameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonSound.playSoundOnce("buttonHover.wav");
				StartGameButton.setBackground(Color.GREEN);
				StartGameButton.setBorder(new MatteBorder(5, 5, 5, 5, (Color) Color.BLACK));
				StartGameButton.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				StartGameButton.setBackground(Color.BLACK);
				StartGameButton.setBorder(null);
				StartGameButton.setForeground(Color.YELLOW);
			}
		});
		StartGameButton.setBackground(new Color(0, 0, 0));
		StartGameButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		StartGameButton.setForeground(Color.YELLOW);
		StartGameButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				boolean nameErrorExists = nameErrorCheckPassed(enterName, printNameError);
				boolean monsterSelectionErrorExists = monsterSelectionCheck(monsterSelectionError);
				if (nameErrorExists == false && monsterSelectionErrorExists == false)
				{
					finishSetup(enterMonsterRename.getText(), Integer.parseInt(enterDays.getSelectedItem()), enterDifficulty.getSelectedItem());
				}
			}
		});
		StartGameButton.setBounds(977, 470, 149, 40);
		frmGameSetup.getContentPane().add(StartGameButton);
		
		
		JLabel monsterImage1 = new JLabel("New label");
		monsterImage1.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\1.) VenomHound.gif"));
		monsterImage1.setBounds(49, 136, 150, 150);
		frmGameSetup.getContentPane().add(monsterImage1);
		
		JLabel monsterImage2 = new JLabel("New label");
		monsterImage2.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\2.) Soilscreamer.gif"));
		monsterImage2.setBounds(258, 122, 179, 178);
		frmGameSetup.getContentPane().add(monsterImage2);
		
		JLabel monsterImage3 = new JLabel("New label");
		monsterImage3.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\3.) Mornpest.gif"));
		monsterImage3.setBounds(512, 136, 150, 150);
		frmGameSetup.getContentPane().add(monsterImage3);
		
		JLabel monsterImage4 = new JLabel("New label");
		monsterImage4.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\4.) Cavernfreak.gif"));
		monsterImage4.setBounds(734, 136, 150, 150);
		frmGameSetup.getContentPane().add(monsterImage4);
		
		JLabel monsterImage5 = new JLabel("New label");
		monsterImage5.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\5.) Hollowtree.gif"));
		monsterImage5.setBounds(965, 136, 150, 150);
		frmGameSetup.getContentPane().add(monsterImage5);
		
		JRadioButton select1 = new JRadioButton("Select");
		select1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		select1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, select1, new Venomhound());
			}
		});
		select1.setForeground(Color.RED);
		select1.setBackground(Color.DARK_GRAY);
		select1.setBounds(66, 297, 105, 23);
		frmGameSetup.getContentPane().add(select1);
		
		
		
		JRadioButton select2 = new JRadioButton("Select");
		select2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		select2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, select2, new Soilscreamer());
			}
		});
		select2.setForeground(Color.RED);
		select2.setBackground(Color.DARK_GRAY);
		select2.setBounds(302, 297, 106, 23);
		frmGameSetup.getContentPane().add(select2);
		
		
		
		JRadioButton select3 = new JRadioButton("Select");
		select3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		select3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, select3, new Mornpest());
			}
		});
		select3.setForeground(Color.RED);
		select3.setBackground(Color.DARK_GRAY);
		select3.setBounds(545, 297, 104, 23);
		frmGameSetup.getContentPane().add(select3);
		
		
		
		
		JRadioButton select4 = new JRadioButton("Select");
		select4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		select4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, select4, new Cavernfreak());
			}
		});
		select4.setForeground(Color.RED);
		select4.setBackground(Color.DARK_GRAY);
		select4.setBounds(775, 297, 98, 23);
		frmGameSetup.getContentPane().add(select4);
		
		
		
		JRadioButton select5 = new JRadioButton("Select");
		select5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		select5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, select5, new Hollowtree());
			}
		});
		select5.setForeground(Color.RED);
		select5.setBackground(Color.DARK_GRAY);
		select5.setBounds(1009, 297, 102, 23);
		frmGameSetup.getContentPane().add(select5);
		
		
		buttons[0] = select1;
		buttons[1] = select2;
		buttons[2] = select3;
		buttons[3] = select4;
		buttons[4] = select5;
		
		
		JButton view1 = new JButton("View Stats");
		view1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonSound.playSoundOnce("buttonHover.wav");
				view1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				view1.setBorder(null);
			}
		});
		view1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonsterStat.launchMonsterStatScreen(new Venomhound());
			}
		});
		view1.setFont(new Font("Tahoma", Font.BOLD, 16));
		view1.setBackground(Color.LIGHT_GRAY);
		view1.setForeground(new Color(0, 0, 128));
		view1.setBounds(47, 328, 124, 23);
		frmGameSetup.getContentPane().add(view1);
		
		
		JButton view2 = new JButton("View Stats");
		view2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonSound.playSoundOnce("buttonHover.wav");
				view2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				view2.setBorder(null);
			}
		});
		view2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonsterStat.launchMonsterStatScreen(new Soilscreamer());
			}
		});
		view2.setFont(new Font("Tahoma", Font.BOLD, 16));
		view2.setForeground(new Color(0, 0, 128));
		view2.setBackground(Color.LIGHT_GRAY);
		view2.setBounds(284, 327, 124, 23);
		frmGameSetup.getContentPane().add(view2);
		
		
		JButton view3 = new JButton("View Stats");
		view3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonSound.playSoundOnce("buttonHover.wav");
				view3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				view3.setBorder(null);
			}
		});
		view3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonsterStat.launchMonsterStatScreen(new Mornpest());
			}
		});
		view3.setFont(new Font("Tahoma", Font.BOLD, 16));
		view3.setForeground(new Color(0, 0, 128));
		view3.setBackground(Color.LIGHT_GRAY);
		view3.setBounds(525, 327, 124, 23);
		frmGameSetup.getContentPane().add(view3);
		
		
		JButton view4 = new JButton("View Stats");
		view4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonSound.playSoundOnce("buttonHover.wav");
				view4.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				view4.setBorder(null);
			}
		});
		view4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonsterStat.launchMonsterStatScreen(new Cavernfreak());
			}
		});
		view4.setFont(new Font("Tahoma", Font.BOLD, 16));
		view4.setForeground(new Color(0, 0, 128));
		view4.setBackground(Color.LIGHT_GRAY);
		view4.setBounds(754, 327, 130, 23);
		frmGameSetup.getContentPane().add(view4);
		
		
		JButton view5 = new JButton("View Stats");
		view5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonSound.playSoundOnce("buttonHover.wav");
				view5.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				view5.setBorder(null);
			}
		});
		view5.setFont(new Font("Tahoma", Font.BOLD, 16));
		view5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonsterStat.launchMonsterStatScreen(new Hollowtree());
			}
		});
		view5.setForeground(new Color(0, 0, 139));
		view5.setBackground(Color.LIGHT_GRAY);
		view5.setBounds(987, 327, 124, 23);
		frmGameSetup.getContentPane().add(view5);
		
		
		JTextArea askDays = new JTextArea();
		askDays.setText("Select the number of days the game will last:");
		askDays.setForeground(Color.WHITE);
		askDays.setFont(new Font("Tahoma", Font.BOLD, 18));
		askDays.setEditable(false);
		askDays.setBackground(Color.DARK_GRAY);
		askDays.setBounds(27, 444, 410, 22);
		frmGameSetup.getContentPane().add(askDays);
		
		
		JTextArea askGameDifficulty = new JTextArea();
		askGameDifficulty.setText("Select game difficulty:");
		askGameDifficulty.setForeground(Color.WHITE);
		askGameDifficulty.setFont(new Font("Tahoma", Font.BOLD, 18));
		askGameDifficulty.setEditable(false);
		askGameDifficulty.setBackground(Color.DARK_GRAY);
		askGameDifficulty.setBounds(27, 489, 211, 32);
		frmGameSetup.getContentPane().add(askGameDifficulty);
	}
}