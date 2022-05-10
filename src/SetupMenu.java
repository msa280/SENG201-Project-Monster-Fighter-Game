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
			return ("Error: Name '%s' contains numbers, spaces or special characters! Please reenter name.".formatted(scannedName));
		} 
		else if (nameLength < 3) 
		{
			return ("Error: Name '%s' is too short! Please reenter name.".formatted(scannedName));
		} 
		else if (nameLength > 15) 
		{
			return ("Error: Name '%s' is too long! Please reenter name.".formatted(scannedName));
		}
		else 
		{
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
		if (selectedButton.isSelected())
		{
			this.selected_monster = monster;
			selectedButton.setForeground(Color.GREEN);
			selectedButton.setText("Selected");
			for (JRadioButton button : buttons) {
				if (button != selectedButton)
				{
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
		txtMonsterFighterV.setBounds(299, 11, 461, 84);
		txtMonsterFighterV.setEditable(false);
		txtMonsterFighterV.setForeground(new Color(51, 0, 255));
		txtMonsterFighterV.setFont(new Font("Agency FB", Font.BOLD, 65));
		txtMonsterFighterV.setText("Monster Fighter V1.0");
		txtMonsterFighterV.setBackground(Color.BLACK);
		txtMonsterFighterV.setColumns(10);
		frame.getContentPane().add(txtMonsterFighterV);
		
		JTextArea txtrEnterYourFighter = new JTextArea();
		txtrEnterYourFighter.setForeground(new Color(255, 255, 255));
		txtrEnterYourFighter.setBounds(33, 126, 196, 23);
		txtrEnterYourFighter.setEditable(false);
		txtrEnterYourFighter.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtrEnterYourFighter.setText("Enter your fighter name:");
		txtrEnterYourFighter.setBackground(Color.BLACK);
		frame.getContentPane().add(txtrEnterYourFighter);
		
		JTextArea txtrChooseYourMonster = new JTextArea();
		txtrChooseYourMonster.setEditable(false);
		txtrChooseYourMonster.setForeground(new Color(255, 255, 255));
		txtrChooseYourMonster.setBounds(33, 163, 190, 23);
		txtrChooseYourMonster.setBackground(Color.BLACK);
		txtrChooseYourMonster.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtrChooseYourMonster.setText("Choose your monster:");
		frame.getContentPane().add(txtrChooseYourMonster);
		
		textNameError = new JTextPane();
		textNameError.setForeground(Color.RED);
		textNameError.setFont(new Font("Times New Roman", Font.BOLD, 15));
		textNameError.setBackground(Color.BLACK);
		textNameError.setEditable(false);
		textNameError.setBounds(473, 126, 601, 23);
		frame.getContentPane().add(textNameError);
		
		JTextPane textAskName = new JTextPane();
		textAskName.setDropMode(DropMode.INSERT);
		textAskName.setForeground(Color.WHITE);
		textAskName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		textAskName.setBackground(Color.DARK_GRAY);
		textAskName.setBounds(239, 123, 205, 23);
		frame.getContentPane().add(textAskName);
		
		game = getGame();
		
		JButton btnNewButton = new JButton("Check");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String error = checkPlayerName(textAskName.getText());
				textNameError.setText(error);
				if (error != "OK") {
					textAskName.setText("");
					textNameError.setForeground(Color.RED);
				} else {
					textNameError.setForeground(Color.GREEN);
                    /**frame.dispose();*/
				}
			}
		});
		btnNewButton.setBounds(892, 621, 120, 44);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\1.) VenomHound.gif"));
		lblNewLabel.setBounds(42, 236, 150, 150);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\2.) Soilscreamer.gif"));
		lblNewLabel_1.setBounds(224, 209, 179, 178);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\3.) Mornpest.gif"));
		lblNewLabel_2.setBounds(453, 236, 150, 150);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\4.) Cavernfreak.gif"));
		lblNewLabel_3.setBounds(654, 236, 150, 150);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\5.) Hollowtree.gif"));
		lblNewLabel_4.setBounds(862, 236, 150, 150);
		frame.getContentPane().add(lblNewLabel_4);
		
		JRadioButton selectVenomhound = new JRadioButton("Select");
		selectVenomhound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectVenomhound, new Venomhound());
			}
		});
		selectVenomhound.setForeground(Color.RED);
		selectVenomhound.setBackground(new Color(0, 0, 0));
		selectVenomhound.setBounds(63, 393, 80, 23);
		frame.getContentPane().add(selectVenomhound);
		
		
		
		JRadioButton selectSoilscreamer = new JRadioButton("Select");
		selectSoilscreamer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectSoilscreamer, new Soilscreamer());
			}
		});
		selectSoilscreamer.setForeground(Color.RED);
		selectSoilscreamer.setBackground(Color.BLACK);
		selectSoilscreamer.setBounds(268, 394, 80, 23);
		frame.getContentPane().add(selectSoilscreamer);
		
		
		
		JRadioButton selectMornpest = new JRadioButton("Select");
		selectMornpest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectMornpest, new Mornpest());
			}
		});
		selectMornpest.setForeground(Color.RED);
		selectMornpest.setBackground(Color.BLACK);
		selectMornpest.setBounds(473, 393, 80, 23);
		frame.getContentPane().add(selectMornpest);
		
		
		
		
		JRadioButton selectCavernfreak = new JRadioButton("Select");
		selectCavernfreak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectCavernfreak, new Cavernfreak());
			}
		});
		selectCavernfreak.setForeground(Color.RED);
		selectCavernfreak.setBackground(Color.BLACK);
		selectCavernfreak.setBounds(691, 393, 80, 23);
		frame.getContentPane().add(selectCavernfreak);
		
		JRadioButton selectHollowtree = new JRadioButton("Select");
		selectHollowtree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonster(buttons, selectHollowtree, new Hollowtree());
			}
		});
		selectHollowtree.setForeground(Color.RED);
		selectHollowtree.setBackground(Color.BLACK);
		selectHollowtree.setBounds(903, 393, 80, 23);
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