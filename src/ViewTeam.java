import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import monsters.Monster;
import monsters.Venomhound;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JProgressBar;

public class ViewTeam {

	private JFrame teamViewer;
	private Player player;
	private JFrame previousFrame;
	private AudioPlayer audioPlayer = new AudioPlayer();
	private AudioPlayer previousAudio;
	private JTextField price1;
	private JTextField price2;
	private JTextField price3;
	private JTextField price4;
	
	
	public String chooseMonsterImage(Monster monster)
	{
		String name = monster.getMonsterName();
		String monsterImage;
		
		if (name == "Venomhound") {
			monsterImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\1.) Venomhound.gif";
		} else if (name == "Soilscreamer") {
			monsterImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\2.) Soilscreamer.gif";
		} else if (name == "Mornpest") {
			monsterImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\3.) Mornpest.gif";
		} else if (name == "Cavernfreak") {
			monsterImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\4.) Cavernfreak.gif";
		} else if (name == "Hollowtree") {
			monsterImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\5.) Hollowtree.gif";
		} else if (name == "Magmataur") {
			monsterImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\6.) Magmataur.gif";
		} else {
			monsterImage = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\1.) VenomHound.gif";
		}
		return monsterImage;
	}
	
	
	
	public void viewMonsterStat(int buttonIndex, JTextPane errorField)
	{
		if (buttonIndex > this.player.getPlayersTeamLength()) {
			errorField.setForeground(Color.RED);
			errorField.setText("Error: Monster Pod is empty!");
		} else {
			errorField.setForeground(Color.GREEN);
			Monster monster = this.player.getPlayerMonsters().get(buttonIndex-1);
			MonsterStatGUI.launchMonsterStatScreen(monster);
			errorField.setText("Viewing %s stats.".formatted(monster.pickMonsterName()));
		}
	}
	
	
	
	public void sellMonster(int buttonIndex, JTextPane updateArea)
	{
		boolean monsterSold= false;
		int i = 0;
		for (Monster monster: this.player.getPlayerMonsters())
		{
			if (i == buttonIndex)
			{
				this.player.sellMonster(monster);
				monsterSold = true;
				this.audioPlayer.stopSound();
				ViewTeam refreshTeam = new ViewTeam(this.player, this.teamViewer);
				ViewTeam.launchTeamViewer(player, refreshTeam);
				updateArea.setForeground(Color.GREEN);
				updateArea.setText("Monster sold!\n%s has been removed from the team.\n%d Gold has been given to you.\n".formatted(monster.pickMonsterName(), monster.getResalePrice()));
				this.teamViewer.dispose();
				break;
			}
			i += 1;
		}
		
		if (monsterSold == false)
		{
			updateArea.setForeground(Color.RED);
			updateArea.setText("Error: Monster Pod is empty!\n");
		} 
	}
	
	
	public void setHealthBar(Monster monster, JProgressBar progressbar) {
		progressbar.setMinimum(0);
		progressbar.setMaximum(monster.getMaxHealth());
		progressbar.setValue(monster.getCurrentHealth());
	}
	
	
	
	
	public static void launchTeamViewer(Player player, ViewTeam teamViewer)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					teamViewer.teamViewer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void goBack()
	{
		this.audioPlayer.stopSound();
		this.teamViewer.dispose();
		PlayerHomeGUI previousWindow = new PlayerHomeGUI(this.player, this.player.getShop(), this.player.getBattle());
		previousWindow.launchMainMenu(previousWindow);
	}
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Player player = new Player();
		player.addToTeam(new Venomhound());
		JFrame test = new JFrame();
		ViewTeam team = new ViewTeam(player, test);
		ViewTeam.launchTeamViewer(player, team);
		
	}

	/**
	 * Create the application.
	 */
	public ViewTeam(Player fighter, JFrame oldFrame) {
		this.player = fighter;
		this.previousFrame = oldFrame;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.audioPlayer.playSound("TeamViewer.wav");
		
		teamViewer = new JFrame();
		teamViewer.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 20));
		teamViewer.getContentPane().setBackground(Color.BLACK);
		teamViewer.getContentPane().setLayout(null);
		
		JTextPane txtpnYourCurrentTeam = new JTextPane();
		txtpnYourCurrentTeam.setBackground(Color.BLACK);
		txtpnYourCurrentTeam.setForeground(Color.WHITE);
		txtpnYourCurrentTeam.setFont(new Font("Times New Roman", Font.BOLD, 22));
		txtpnYourCurrentTeam.setText("Your team:");
		txtpnYourCurrentTeam.setBounds(56, 22, 113, 31);
		teamViewer.getContentPane().add(txtpnYourCurrentTeam);
		
		JTextPane errorField = new JTextPane();
		errorField.setFont(new Font("Tahoma", Font.BOLD, 16));
		errorField.setForeground(Color.GREEN);
		errorField.setBackground(Color.BLACK);
		errorField.setBounds(350, 622, 496, 48);
		teamViewer.getContentPane().add(errorField);
		
		
		JLabel monster1 = new JLabel("New label");
		monster1.setToolTipText("");
		monster1.setBounds(58, 138, 150, 150);
		teamViewer.getContentPane().add(monster1);
		
		JLabel monster2 = new JLabel("New label");
		monster2.setBounds(318, 138, 150, 150);
		teamViewer.getContentPane().add(monster2);
		
		JLabel monster3 = new JLabel("New label");
		monster3.setBounds(566, 138, 150, 150);
		teamViewer.getContentPane().add(monster3);
		
		JLabel monster4 = new JLabel("New label");
		monster4.setBounds(804, 138, 150, 150);
		teamViewer.getContentPane().add(monster4);
		
		JLabel labelList[] = new JLabel[4];
		labelList[0] = monster1;
		labelList[1] = monster2;
		labelList[2] = monster3;
		labelList[3] = monster4;
		
		JTextPane name1 = new JTextPane();
		name1.setBackground(new Color(0, 0, 0));
		name1.setForeground(new Color(255, 255, 255));
		name1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name1.setBounds(68, 299, 132, 31);
		teamViewer.getContentPane().add(name1);
		
		
		JTextPane name2 = new JTextPane();
		name2.setForeground(Color.WHITE);
		name2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name2.setBackground(new Color(0, 0, 0));
		name2.setBounds(328, 299, 132, 31);
		teamViewer.getContentPane().add(name2);
		
		JTextPane name3 = new JTextPane();
		name3.setForeground(Color.WHITE);
		name3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name3.setBackground(new Color(0, 0, 0));
		name3.setBounds(576, 299, 132, 31);
		teamViewer.getContentPane().add(name3);
		
		JTextPane name4 = new JTextPane();
		name4.setForeground(Color.WHITE);
		name4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name4.setBackground(new Color(0, 0, 0));
		name4.setBounds(814, 299, 132, 31);
		teamViewer.getContentPane().add(name4);
		
		JTextPane paneList[] = new JTextPane[4];
		paneList[0] = name1;
		paneList[1] = name2;
		paneList[2] = name3;
		paneList[3] = name4;
		
		JButton button1 = new JButton("View Stats");
		button1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(1, errorField);
			}
		});
		button1.setBackground(new Color(255, 255, 0));
		button1.setBounds(85, 341, 103, 23);
		teamViewer.getContentPane().add(button1);
		
		JButton button2 = new JButton("View Stats");
		button2.setFont(new Font("Tahoma", Font.BOLD, 11));
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(2, errorField);
			}
		});
		button2.setBackground(Color.YELLOW);
		button2.setBounds(338, 341, 103, 23);
		teamViewer.getContentPane().add(button2);
		
		JButton button3 = new JButton("View Stats");
		button3.setFont(new Font("Tahoma", Font.BOLD, 11));
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(3, errorField);
			}
		});
		button3.setBackground(Color.YELLOW);
		button3.setBounds(586, 341, 103, 23);
		teamViewer.getContentPane().add(button3);
		
		JButton button4 = new JButton("View Stats");
		button4.setFont(new Font("Tahoma", Font.BOLD, 11));
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(4, errorField);
			}
		});
		button4.setBackground(Color.YELLOW);
		button4.setBounds(824, 341, 103, 23);
		teamViewer.getContentPane().add(button4);
		
		JProgressBar progressBar1 = new JProgressBar();
		progressBar1.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBar1.setStringPainted(true);
		progressBar1.setToolTipText("");
		progressBar1.setBackground(Color.LIGHT_GRAY);
		progressBar1.setForeground(new Color(50, 205, 50));
		progressBar1.setBounds(56, 113, 146, 14);
		teamViewer.getContentPane().add(progressBar1);
		
		JProgressBar progressBar2 = new JProgressBar();
		progressBar2.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBar2.setStringPainted(true);
		progressBar2.setForeground(new Color(50, 205, 50));
		progressBar2.setBackground(Color.LIGHT_GRAY);
		progressBar2.setBounds(322, 113, 146, 14);
		teamViewer.getContentPane().add(progressBar2);
		
		JProgressBar progressBar3 = new JProgressBar();
		progressBar3.setStringPainted(true);
		progressBar3.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBar3.setForeground(new Color(50, 205, 50));
		progressBar3.setBackground(Color.LIGHT_GRAY);
		progressBar3.setBounds(570, 113, 146, 14);
		teamViewer.getContentPane().add(progressBar3);
		
		JProgressBar progressBar4 = new JProgressBar();
		progressBar4.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBar4.setStringPainted(true);
		progressBar4.setForeground(new Color(50, 205, 50));
		progressBar4.setBackground(Color.LIGHT_GRAY);
		progressBar4.setBounds(808, 113, 146, 14);
		teamViewer.getContentPane().add(progressBar4);
		
		
		JProgressBar barList[] = new JProgressBar[4];
		barList[0] = progressBar1;
		barList[1] = progressBar2;
		barList[2] = progressBar3;
		barList[3] = progressBar4;
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(56, 622, 111, 31);
		teamViewer.getContentPane().add(btnNewButton);
		
		JTextPane txtpnHealth = new JTextPane();
		txtpnHealth.setBackground(new Color(0, 0, 0));
		txtpnHealth.setForeground(new Color(255, 255, 255));
		txtpnHealth.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnHealth.setText("Health");
		txtpnHealth.setBounds(360, 90, 67, 20);
		teamViewer.getContentPane().add(txtpnHealth);
		
		JTextPane txtpnHealth_1 = new JTextPane();
		txtpnHealth_1.setText("Health");
		txtpnHealth_1.setForeground(Color.WHITE);
		txtpnHealth_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnHealth_1.setBackground(Color.BLACK);
		txtpnHealth_1.setBounds(600, 90, 67, 20);
		teamViewer.getContentPane().add(txtpnHealth_1);
		
		JTextPane txtpnHealth_2 = new JTextPane();
		txtpnHealth_2.setText("Health");
		txtpnHealth_2.setForeground(Color.WHITE);
		txtpnHealth_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnHealth_2.setBackground(Color.BLACK);
		txtpnHealth_2.setBounds(852, 90, 67, 20);
		teamViewer.getContentPane().add(txtpnHealth_2);
		
		JTextPane txtpnHealth_4 = new JTextPane();
		txtpnHealth_4.setText("Health");
		txtpnHealth_4.setForeground(Color.WHITE);
		txtpnHealth_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnHealth_4.setBackground(Color.BLACK);
		txtpnHealth_4.setBounds(102, 90, 67, 20);
		teamViewer.getContentPane().add(txtpnHealth_4);
		
		
		price1 = new JTextField();
		price1.setText("Resale Price (Gold: Empty)");
		price1.setBackground(Color.BLACK);
		price1.setForeground(Color.ORANGE);
		price1.setFont(new Font("Tahoma", Font.BOLD, 12));
		price1.setBounds(56, 375, 172, 20);
		teamViewer.getContentPane().add(price1);
		price1.setColumns(10);
		
		price2 = new JTextField();
		price2.setText("Resale Price (Gold: Empty)");
		price2.setForeground(Color.ORANGE);
		price2.setFont(new Font("Tahoma", Font.BOLD, 12));
		price2.setColumns(10);
		price2.setBackground(Color.BLACK);
		price2.setBounds(316, 375, 172, 20);
		teamViewer.getContentPane().add(price2);
		
		price3 = new JTextField();
		price3.setText("Resale Price (Gold: Empty)");
		price3.setForeground(Color.ORANGE);
		price3.setFont(new Font("Tahoma", Font.BOLD, 12));
		price3.setColumns(10);
		price3.setBackground(Color.BLACK);
		price3.setBounds(560, 375, 172, 20);
		teamViewer.getContentPane().add(price3);
		
		price4 = new JTextField();
		price4.setText("Resale Price (Gold: Empty)");
		price4.setForeground(Color.ORANGE);
		price4.setFont(new Font("Tahoma", Font.BOLD, 12));
		price4.setColumns(10);
		price4.setBackground(Color.BLACK);
		price4.setBounds(804, 375, 172, 20);
		teamViewer.getContentPane().add(price4);
		
		
		JTextField prices[] = new JTextField[4];
		prices[0] = price1;
		prices[1] = price2;
		prices[2] = price3;
		prices[3] = price4;
		
		
		
		
		JButton sell1 = new JButton("Sell");
		sell1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellMonster(0, errorField);
			}
		});
		sell1.setForeground(Color.WHITE);
		sell1.setBackground(Color.RED);
		sell1.setFont(new Font("Tahoma", Font.BOLD, 14));
		sell1.setBounds(99, 416, 70, 23);
		teamViewer.getContentPane().add(sell1);
		
		JButton sell2 = new JButton("Sell");
		sell2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellMonster(1, errorField);
			}
		});
		sell2.setForeground(Color.WHITE);
		sell2.setFont(new Font("Tahoma", Font.BOLD, 14));
		sell2.setBackground(Color.RED);
		sell2.setBounds(360, 416, 70, 23);
		teamViewer.getContentPane().add(sell2);
		
		JButton sell3 = new JButton("Sell");
		sell3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellMonster(2, errorField);
			}
		});
		sell3.setForeground(Color.WHITE);
		sell3.setFont(new Font("Tahoma", Font.BOLD, 14));
		sell3.setBackground(Color.RED);
		sell3.setBounds(608, 418, 70, 23);
		teamViewer.getContentPane().add(sell3);
		
		JButton sell4 = new JButton("Sell");
		sell4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellMonster(3, errorField);
			}
		});
		sell4.setForeground(Color.WHITE);
		sell4.setFont(new Font("Tahoma", Font.BOLD, 14));
		sell4.setBackground(Color.RED);
		sell4.setBounds(849, 418, 70, 23);
		teamViewer.getContentPane().add(sell4);
		
		
		int i = 0;
		while (i < this.player.getPlayersTeamLength()) {
			Monster monster = this.player.getPlayerMonsters().get(i);
			String monsterImageLink = chooseMonsterImage(monster);
			labelList[i].setIcon(new ImageIcon(monsterImageLink));
			paneList[i].setText(monster.pickMonsterName());
			prices[i].setText("Resale Price (Gold: %d)".formatted(monster.getResalePrice()));
			setHealthBar(monster, barList[i]);
			i += 1;
		}
		while (i < labelList.length)
		{
			labelList[i].setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\EmptyPod.png"));
			paneList[i].setText("- Empty Pod -");
			i += 1;
		}
		
		
		
		
		
		
		
		teamViewer.setTitle("Team Viewer");
		teamViewer.setBounds(100, 100, 1080, 720);
		teamViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
