import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;



public class SetupMenu {
	

	private JFrame frame;
	private JTextField txtMonsterFighterV;
	private JTextPane textNameError;

	
	private Game game;
	private String fighter_name;
	boolean setup_complete = false;
	
	
	
	public String checkName(boolean numOrSpecialChar, int nameLength, String scannedName) 
	{
		if (numOrSpecialChar) 
		{
			return ("Error: Name contains numbers or special characters!");
		} 
		else if (nameLength < 3) 
		{
			return ("Error: Name is too short!");
		} 
		else if (nameLength > 15) 
		{
			return ("Error: Name is too long!");
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
		
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 0));
		lblNewLabel.setForeground(new Color(240, 255, 255));
		lblNewLabel.setBounds(29, 287, 303, 182);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\animated-monster-image-0050.gif"));
		frame.getContentPane().add(lblNewLabel);
		
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
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\Animated Creature.gif"));
		lblNewLabel_1.setBounds(336, 289, 216, 226);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\VenomHound.gif"));
		lblNewLabel_2.setBounds(545, 265, 314, 226);
		frame.getContentPane().add(lblNewLabel_2);
		
		
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