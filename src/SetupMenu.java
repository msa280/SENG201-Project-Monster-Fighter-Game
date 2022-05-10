import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

import monsters.Cavernfreak;
import monsters.Hollowtree;
import monsters.Monster;
import monsters.Mornpest;
import monsters.Soilscreamer;
import monsters.Venomhound;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import java.awt.Choice;
import java.awt.ScrollPane;
import java.awt.Canvas;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;



public class SetupMenu {
	

	private JFrame frame;
	private JTextPane printNameError;

	
	private Game game;
	private String fighter_name;
	boolean setup_complete = false;
	private Monster selected_monster = null;
	private int gameLength;
	private double gameDifficulty;

	
	
	public String checkName(boolean numOrSpecialChar, int nameLength, String scannedName) 
	{
		if (numOrSpecialChar) {
			return ("Error: Name '%s' contains numbers, spaces or special characters! Please reenter name.".formatted(scannedName));
		} 
		else if (nameLength == 0) {
			return ("Error: Please enter your name.".formatted(scannedName));
		}
		else if (nameLength < 3) {
			return ("Error: Name '%s' is too short! Please reenter name.".formatted(scannedName));
		} 
		else if (nameLength > 15) {
			return ("Error: Name '%s' is too long! Please reenter name.".formatted(scannedName));
		}
		else {
			return "OK";
		}
	}
	
	
	public String checkPlayerName(String name) 
	{	
		Pattern pattern = Pattern.compile("[^a-zA-Z]");
		Matcher matcher = null;
		int nameLength = 0;
		boolean numOrSpecialChar = false;
		String error;
		
		nameLength = name.length();
		matcher = pattern.matcher(name);
		numOrSpecialChar = matcher.find();
		error = checkName(numOrSpecialChar, nameLength, name);
		
		if (error == "Ok.") {
			this.game.setPlayerName(name);
			this.setup_complete = true;
			System.out.print(this.game.getPlayerName());
		}
		return error;
	}
	
	
	
	public void selectMonster(JRadioButton[] buttons, JRadioButton selectedButton, Monster monster)
	{
		if (selectedButton.isSelected()) {
			this.selected_monster = monster;
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
			this.selected_monster = null;
			selectedButton.setForeground(Color.RED);
			selectedButton.setText("Select");
		}	
	}
	
	
	
	public void setMonsterRename(String newName) 
	{
		if (newName.length() > 0) {
			this.selected_monster.setMonsterRename(newName);
		}
	}
	
	
	public void setGameLength(int days) 
	{
		this.gameLength = days;
	}
	
	
	public void setGameDifficulty(String difficulty)
	{
		if (difficulty == "Easy") {
			this.setGameDifficulty(0.5);
		} else if (difficulty == "Normal") {
			this.setGameDifficulty(1.0);
		} else if (difficulty == "Classic") {
			this.setGameDifficulty(1.5);
		} else if (difficulty == "Hard") {
			this.setGameDifficulty(2.0);
		} else {
			this.setGameDifficulty(2.5);
		}
	}
	
	
	
	
	
	public void renameMonster(String name)
	{
		this.selected_monster.setMonsterRename(name);
	}
	
	
	
	public String setupSuccessful(JTextPane enterName)
	{
		if (this.selected_monster == null) {
			return "Please select a monster.";
		} else {
			return "";
		}
	}
	
	
	
	public boolean nameErrorCheckPassed(JTextPane enterName, JTextPane printNameError) 
	{
		String error = checkPlayerName(enterName.getText());
		printNameError.setText(error);
		if (error != "OK") {
			enterName.setText("");
			printNameError.setForeground(Color.RED);
			return true;
		} else {
			printNameError.setForeground(Color.GREEN);
			return false;
            /**frame.dispose();*/
		}
	}
	
	
	public boolean monsterSelectionCheck(JTextPane monsterSelectionError)
	{
		if (this.selected_monster == null) {
			monsterSelectionError.setText("Error: Please select a monster.");
			monsterSelectionError.setForeground(Color.RED);
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	

	
	/**
	 * Launches the game setup screen.
	 */
	public void launch_setup_menu(SetupMenu window) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					window.frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	
	

	/**
	 * Creates the game setup screen.
	 */
	public SetupMenu() {
		initialize();
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JRadioButton[] buttons = new JRadioButton[5];
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea askFighterName = new JTextArea();
		askFighterName.setForeground(new Color(255, 255, 255));
		askFighterName.setBounds(33, 136, 196, 23);
		askFighterName.setEditable(false);
		askFighterName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		askFighterName.setText("Enter your fighter name:");
		askFighterName.setBackground(Color.BLACK);
		frame.getContentPane().add(askFighterName);
		
		JTextArea askChooseMonster = new JTextArea();
		askChooseMonster.setEditable(false);
		askChooseMonster.setForeground(new Color(255, 255, 255));
		askChooseMonster.setBounds(33, 163, 190, 23);
		askChooseMonster.setBackground(Color.BLACK);
		askChooseMonster.setFont(new Font("Times New Roman", Font.BOLD, 18));
		askChooseMonster.setText("Choose your monster:");
		frame.getContentPane().add(askChooseMonster);
		
		printNameError = new JTextPane();
		printNameError.setForeground(Color.RED);
		printNameError.setFont(new Font("Tahoma", Font.BOLD, 15));
		printNameError.setBackground(Color.BLACK);
		printNameError.setEditable(false);
		printNameError.setBounds(460, 136, 601, 23);
		frame.getContentPane().add(printNameError);
		
		JTextPane monsterSelectionError = new JTextPane();
		monsterSelectionError.setFont(new Font("Tahoma", Font.BOLD, 15));
		monsterSelectionError.setEditable(false);
		monsterSelectionError.setBackground(Color.BLACK);
		monsterSelectionError.setForeground(Color.RED);
		monsterSelectionError.setBounds(218, 163, 526, 28);
		frame.getContentPane().add(monsterSelectionError);
		
		JTextPane enterName = new JTextPane();
		enterName.setDropMode(DropMode.INSERT);
		enterName.setForeground(new Color(0, 0, 128));
		enterName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		enterName.setBackground(Color.LIGHT_GRAY);
		enterName.setBounds(239, 136, 205, 23);
		frame.getContentPane().add(enterName);
		
		JTextArea askMonsterRename = new JTextArea();
		askMonsterRename.setEditable(false);
		askMonsterRename.setBackground(new Color(0, 0, 0));
		askMonsterRename.setForeground(new Color(255, 255, 255));
		askMonsterRename.setFont(new Font("Times New Roman", Font.BOLD, 18));
		askMonsterRename.setText("(Optional) Rename your selected monster:");
		askMonsterRename.setBounds(33, 510, 333, 22);
		frame.getContentPane().add(askMonsterRename);
		
		
		Choice enterDays = new Choice();
		enterDays.setForeground(new Color(0, 0, 128));
		enterDays.setFont(new Font("Times New Roman", Font.BOLD, 12));
		enterDays.setBackground(Color.LIGHT_GRAY);
		enterDays.setBounds(391, 545, 53, 20);
		frame.getContentPane().add(enterDays);
		for (int i = 5; i < 16; i++)
		{
			enterDays.add(Integer.toString(i));
		}
		
		
		Choice enterDifficulty = new Choice();
		enterDifficulty.setFont(new Font("Times New Roman", Font.BOLD, 12));
		enterDifficulty.setForeground(new Color(0, 0, 128));
		enterDifficulty.setBackground(Color.LIGHT_GRAY);
		enterDifficulty.setBounds(216, 578, 72, 20);
		frame.getContentPane().add(enterDifficulty);
		enterDifficulty.add("Easy");
		enterDifficulty.add("Normal");
		enterDifficulty.add("Classic");
		enterDifficulty.add("Hard");
		enterDifficulty.add("Boss");
		

		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				boolean nameErrorExists = nameErrorCheckPassed(enterName, printNameError);
				boolean monsterSelectionErrorExists = monsterSelectionCheck(monsterSelectionError);
				if (nameErrorExists == false && monsterSelectionErrorExists == false)
				{
					setMonsterRename(askMonsterRename.getText());
					setGameLength(Integer.parseInt(enterDays.getSelectedItem()));
					setGameDifficulty(enterDifficulty.getSelectedItem());
					frame.dispose();
				}
				
				
			}
		});
		btnNewButton.setBounds(1105, 630, 133, 40);
		frame.getContentPane().add(btnNewButton);
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\1.) VenomHound.gif"));
		lblNewLabel.setBounds(50, 236, 150, 150);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\2.) Soilscreamer.gif"));
		lblNewLabel_1.setBounds(264, 222, 179, 178);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\3.) Mornpest.gif"));
		lblNewLabel_2.setBounds(528, 236, 150, 150);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\4.) Cavernfreak.gif"));
		lblNewLabel_3.setBounds(775, 236, 150, 150);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\5.) Hollowtree.gif"));
		lblNewLabel_4.setBounds(1037, 236, 150, 150);
		frame.getContentPane().add(lblNewLabel_4);
		
		JRadioButton selectVenomhound = new JRadioButton("Select");
		selectVenomhound.setFont(new Font("Tahoma", Font.PLAIN, 11));
		selectVenomhound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectVenomhound, new Venomhound());
			}
		});
		selectVenomhound.setForeground(Color.RED);
		selectVenomhound.setBackground(new Color(0, 0, 0));
		selectVenomhound.setBounds(70, 393, 80, 23);
		frame.getContentPane().add(selectVenomhound);
		
		
		
		JRadioButton selectSoilscreamer = new JRadioButton("Select");
		selectSoilscreamer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectSoilscreamer, new Soilscreamer());
			}
		});
		selectSoilscreamer.setForeground(Color.RED);
		selectSoilscreamer.setBackground(Color.BLACK);
		selectSoilscreamer.setBounds(314, 393, 80, 23);
		frame.getContentPane().add(selectSoilscreamer);
		
		
		
		JRadioButton selectMornpest = new JRadioButton("Select");
		selectMornpest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectMornpest, new Mornpest());
			}
		});
		selectMornpest.setForeground(Color.RED);
		selectMornpest.setBackground(Color.BLACK);
		selectMornpest.setBounds(574, 393, 80, 23);
		frame.getContentPane().add(selectMornpest);
		
		
		
		
		JRadioButton selectCavernfreak = new JRadioButton("Select");
		selectCavernfreak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectCavernfreak, new Cavernfreak());
			}
		});
		selectCavernfreak.setForeground(Color.RED);
		selectCavernfreak.setBackground(Color.BLACK);
		selectCavernfreak.setBounds(823, 393, 80, 23);
		frame.getContentPane().add(selectCavernfreak);
		
		JRadioButton selectHollowtree = new JRadioButton("Select");
		selectHollowtree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectHollowtree, new Hollowtree());
			}
		});
		selectHollowtree.setForeground(Color.RED);
		selectHollowtree.setBackground(Color.BLACK);
		selectHollowtree.setBounds(1078, 393, 80, 23);
		frame.getContentPane().add(selectHollowtree);
		
		
		
		
		JButton viewVHButton = new JButton("View Stats");
		viewVHButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonsterStatGUI.launchMonsterStatScreen(new Venomhound());
			}
		});
		viewVHButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		viewVHButton.setBackground(Color.LIGHT_GRAY);
		viewVHButton.setForeground(new Color(0, 0, 128));
		viewVHButton.setBounds(55, 423, 95, 23);
		frame.getContentPane().add(viewVHButton);
		
		
		JButton viewSSbutton = new JButton("View Stats");
		viewSSbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonsterStatGUI.launchMonsterStatScreen(new Soilscreamer());
			}
		});
		viewSSbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		viewSSbutton.setForeground(new Color(0, 0, 128));
		viewSSbutton.setBackground(Color.LIGHT_GRAY);
		viewSSbutton.setBounds(302, 423, 95, 23);
		frame.getContentPane().add(viewSSbutton);
		
		
		JButton viewMPbutton = new JButton("View Stats");
		viewMPbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonsterStatGUI.launchMonsterStatScreen(new Mornpest());
			}
		});
		viewMPbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		viewMPbutton.setForeground(new Color(0, 0, 128));
		viewMPbutton.setBackground(Color.LIGHT_GRAY);
		viewMPbutton.setBounds(559, 423, 95, 23);
		frame.getContentPane().add(viewMPbutton);
		
		
		JButton viewCFbutton = new JButton("View Stats");
		viewCFbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonsterStatGUI.launchMonsterStatScreen(new Cavernfreak());
			}
		});
		viewCFbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		viewCFbutton.setForeground(new Color(0, 0, 128));
		viewCFbutton.setBackground(Color.LIGHT_GRAY);
		viewCFbutton.setBounds(808, 423, 95, 23);
		frame.getContentPane().add(viewCFbutton);
		
		
		JButton viewHTbutton = new JButton("View Stats");
		viewHTbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		viewHTbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonsterStatGUI.launchMonsterStatScreen(new Hollowtree());
			}
		});
		viewHTbutton.setForeground(new Color(0, 0, 139));
		viewHTbutton.setBackground(Color.LIGHT_GRAY);
		viewHTbutton.setBounds(1063, 423, 95, 23);
		frame.getContentPane().add(viewHTbutton);
		
		
		JTextPane txtpnUniversityOfCanterbury = new JTextPane();
		txtpnUniversityOfCanterbury.setBackground(new Color(0, 0, 0));
		txtpnUniversityOfCanterbury.setForeground(new Color(255, 255, 255));
		txtpnUniversityOfCanterbury.setFont(new Font("Tahoma", Font.BOLD, 9));
		txtpnUniversityOfCanterbury.setText("University of Canterbury\r\nSENG201 Project - Monster Fighter (Game)\r\nVersion: 1.0\r\nAuthors: Haider (msa280) & Jakib (jis48)\r\nDate Created: 11/05/22\r\n");
		txtpnUniversityOfCanterbury.setBounds(1015, 11, 239, 63);
		frame.getContentPane().add(txtpnUniversityOfCanterbury);
		
		
		JTextPane enterMonsterRename = new JTextPane();
		enterMonsterRename.setForeground(new Color(0, 0, 128));
		enterMonsterRename.setFont(new Font("Times New Roman", Font.BOLD, 18));
		enterMonsterRename.setDropMode(DropMode.INSERT);
		enterMonsterRename.setBackground(Color.LIGHT_GRAY);
		enterMonsterRename.setBounds(371, 509, 205, 23);
		frame.getContentPane().add(enterMonsterRename);
		
		
		JTextPane txtpnMonsterFighter = new JTextPane();
		txtpnMonsterFighter.setBackground(new Color(0, 0, 0));
		txtpnMonsterFighter.setForeground(new Color(199, 21, 133));
		txtpnMonsterFighter.setText("Monster Fighter");
		txtpnMonsterFighter.setFont(new Font("Agency FB", Font.BOLD, 65));
		txtpnMonsterFighter.setBounds(427, 0, 364, 84);
		frame.getContentPane().add(txtpnMonsterFighter);
		
		
		JTextArea askDays = new JTextArea();
		askDays.setText("Select the number of days the game will last:");
		askDays.setForeground(Color.WHITE);
		askDays.setFont(new Font("Times New Roman", Font.BOLD, 18));
		askDays.setEditable(false);
		askDays.setBackground(Color.BLACK);
		askDays.setBounds(33, 543, 349, 22);
		frame.getContentPane().add(askDays);
		
		
		JTextArea askGameDifficulty = new JTextArea();
		askGameDifficulty.setText("Select game difficulty:");
		askGameDifficulty.setForeground(Color.WHITE);
		askGameDifficulty.setFont(new Font("Times New Roman", Font.BOLD, 18));
		askGameDifficulty.setEditable(false);
		askGameDifficulty.setBackground(Color.BLACK);
		askGameDifficulty.setBounds(33, 576, 179, 22);
		frame.getContentPane().add(askGameDifficulty);
		
	}


	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}


	public String getFighter_name() {
		return fighter_name;
	}


	public void setFighter_name(String fighter_name) {
		this.fighter_name = fighter_name;
	}


	public double getGameDifficulty() {
		return gameDifficulty;
	}


	public void setGameDifficulty(double gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
	}
}