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



public class SetupMenu {
	

	private JFrame frame;
	private JTextField txtMonsterFighterV;
	private JTextPane textNameError;

	
	private Game game;
	private String fighter_name;
	boolean setup_complete = false;
	Monster selected_monster;
	
	
	public String checkName(boolean numOrSpecialChar, int nameLength, String scannedName) 
	{
		if (numOrSpecialChar) 
		{
			return ("Error: {} contained numbers, spaces or special characters!".formatted(scannedName));
		} 
		else if (nameLength < 3) 
		{
			return ("Error: {} is too short!".formatted(scannedName));
		} 
		else if (nameLength > 15) 
		{
			return ("Error: {} is too long!".formatted(scannedName));
		}
		else 
		{
			return "Ok.";
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
		
		if (error == "Ok.")
		{
			this.game.setPlayerName(name);
			this.setup_complete = true;
			System.out.print(this.game.getPlayerName());
		}
	
		
		return error;
	}
	
	
	public void selectMonster(JRadioButton[] buttons, JRadioButton selectedButton, Monster monster)
	{
		this.selected_monster = monster;
		for (JRadioButton button : buttons) {
			if (button != selectedButton)
			{
				button.setSelected(false);
			}
		}
	}
	
	
	

	
	/**
	 * Launch the application.
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
	 * Create the application.
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
		frame.setBounds(100, 100, 1100, 715);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtMonsterFighterV = new JTextField();
		txtMonsterFighterV.setBounds(298, 32, 461, 84);
		txtMonsterFighterV.setEditable(false);
		txtMonsterFighterV.setForeground(new Color(0, 255, 0));
		txtMonsterFighterV.setFont(new Font("Agency FB", Font.BOLD, 65));
		txtMonsterFighterV.setText("Monster Fighter V1.0");
		txtMonsterFighterV.setBackground(Color.BLACK);
		txtMonsterFighterV.setColumns(10);
		frame.getContentPane().add(txtMonsterFighterV);
		
		JTextArea txtrEnterYourFighter = new JTextArea();
		txtrEnterYourFighter.setForeground(Color.YELLOW);
		txtrEnterYourFighter.setBounds(33, 166, 293, 31);
		txtrEnterYourFighter.setEditable(false);
		txtrEnterYourFighter.setFont(new Font("Monospaced", Font.BOLD, 20));
		txtrEnterYourFighter.setText("Enter your fighter name:");
		txtrEnterYourFighter.setBackground(Color.GRAY);
		frame.getContentPane().add(txtrEnterYourFighter);
		
		JTextArea txtrChooseYourMonster = new JTextArea();
		txtrChooseYourMonster.setForeground(Color.YELLOW);
		txtrChooseYourMonster.setBounds(33, 219, 245, 31);
		txtrChooseYourMonster.setBackground(Color.GRAY);
		txtrChooseYourMonster.setFont(new Font("Monospaced", Font.BOLD, 20));
		txtrChooseYourMonster.setText("Choose your monster:");
		frame.getContentPane().add(txtrChooseYourMonster);
		
		textNameError = new JTextPane();
		textNameError.setForeground(Color.RED);
		textNameError.setFont(new Font("Tahoma", Font.BOLD, 15));
		textNameError.setBackground(Color.BLACK);
		textNameError.setEditable(false);
		textNameError.setBounds(551, 166, 446, 31);
		frame.getContentPane().add(textNameError);
		
		JTextPane textAskName = new JTextPane();
		textAskName.setDropMode(DropMode.INSERT);
		textAskName.setForeground(new Color(255, 0, 255));
		textAskName.setFont(new Font("Tahoma", Font.BOLD, 20));
		textAskName.setBackground(new Color(192, 192, 192));
		textAskName.setBounds(336, 166, 205, 31);
		frame.getContentPane().add(textAskName);
		
		game = getGame();
		
		JButton btnNewButton = new JButton("Check");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String error = checkPlayerName(textAskName.getText());
				textNameError.setText(error);
				if (error != "Ok.") {
					textAskName.setText("");
					textNameError.setForeground(Color.RED);
				} else {
					textNameError.setForeground(Color.GREEN);
                    frame.dispose();
				}
			}
		});
		btnNewButton.setBounds(892, 621, 120, 44);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\1.) VenomHound.gif"));
		lblNewLabel.setBounds(36, 291, 150, 150);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\2.) Soilscreamer.gif"));
		lblNewLabel_1.setBounds(228, 277, 179, 178);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\3.) Mornpest.gif"));
		lblNewLabel_2.setBounds(436, 291, 150, 150);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\4.) Cavernfreak.gif"));
		lblNewLabel_3.setBounds(628, 291, 150, 150);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\5.) Hollowtree.gif"));
		lblNewLabel_4.setBounds(835, 291, 150, 150);
		frame.getContentPane().add(lblNewLabel_4);
		
		JRadioButton selectVenomhound = new JRadioButton("Select");
		selectVenomhound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectVenomhound, new Venomhound());
			}
		});
		selectVenomhound.setForeground(new Color(255, 255, 255));
		selectVenomhound.setBackground(new Color(0, 0, 0));
		selectVenomhound.setBounds(33, 448, 70, 23);
		frame.getContentPane().add(selectVenomhound);
		
		
		
		JRadioButton selectSoilscreamer = new JRadioButton("Select");
		selectSoilscreamer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectSoilscreamer, new Soilscreamer());
			}
		});
		selectSoilscreamer.setForeground(Color.WHITE);
		selectSoilscreamer.setBackground(Color.BLACK);
		selectSoilscreamer.setBounds(228, 462, 70, 23);
		frame.getContentPane().add(selectSoilscreamer);
		
		
		
		JRadioButton selectMornpest = new JRadioButton("Select");
		selectMornpest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectMornpest, new Mornpest());
			}
		});
		selectMornpest.setForeground(Color.WHITE);
		selectMornpest.setBackground(Color.BLACK);
		selectMornpest.setBounds(436, 448, 70, 23);
		frame.getContentPane().add(selectMornpest);
		
		
		
		
		JRadioButton selectCavernfreak = new JRadioButton("Select");
		selectCavernfreak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectCavernfreak, new Cavernfreak());
			}
		});
		selectCavernfreak.setForeground(Color.WHITE);
		selectCavernfreak.setBackground(Color.BLACK);
		selectCavernfreak.setBounds(628, 448, 70, 23);
		frame.getContentPane().add(selectCavernfreak);
		
		JRadioButton selectHollowtree = new JRadioButton("Select");
		selectHollowtree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectHollowtree, new Hollowtree());
			}
		});
		selectHollowtree.setForeground(Color.WHITE);
		selectHollowtree.setBackground(Color.BLACK);
		selectHollowtree.setBounds(835, 448, 70, 23);
		frame.getContentPane().add(selectHollowtree);
		
		
		buttons[0] = selectVenomhound;
		buttons[1] = selectSoilscreamer;
		buttons[2] = selectMornpest;
		buttons[3] = selectCavernfreak;
		buttons[4] = selectHollowtree;
		
		
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
}