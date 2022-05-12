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
	private JTextField errorField;
	private JFrame previousFrame;
	private AudioPlayer audioPlayer = new AudioPlayer();
	private AudioPlayer previousAudio;
	
	
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
	
	
	
	public void viewMonsterStat(int buttonIndex, JTextField errorField)
	{
		if (buttonIndex > this.player.getPlayersTeamLength()) {
			errorField.setForeground(Color.RED);
			errorField.setText("Error: Monster Pod is empty!");
		} else {
			errorField.setText("OK.");
			errorField.setForeground(Color.GREEN);
			Monster monster = this.player.getPlayerMonsters().get(buttonIndex-1);
			MonsterStatGUI.launchMonsterStatScreen(monster);
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
		this.previousFrame.setVisible(true);
		this.audioPlayer.playSound("MainMenu.wav");
		SwingUtilities.updateComponentTreeUI(this.previousFrame);
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
		
		JLabel monster1 = new JLabel("New label");
		monster1.setToolTipText("");
		monster1.setBounds(58, 138, 150, 150);
		teamViewer.getContentPane().add(monster1);
		
		JLabel monster2 = new JLabel("New label");
		monster2.setBounds(261, 138, 150, 150);
		teamViewer.getContentPane().add(monster2);
		
		JLabel monster3 = new JLabel("New label");
		monster3.setBounds(474, 138, 150, 150);
		teamViewer.getContentPane().add(monster3);
		
		JLabel monster4 = new JLabel("New label");
		monster4.setBounds(681, 138, 150, 150);
		teamViewer.getContentPane().add(monster4);
		
		JLabel monster5 = new JLabel("New label");
		monster5.setBounds(891, 138, 150, 150);
		teamViewer.getContentPane().add(monster5);
		
		JLabel labelList[] = new JLabel[5];
		labelList[0] = monster1;
		labelList[1] = monster2;
		labelList[2] = monster3;
		labelList[3] = monster4;
		labelList[4] = monster5;
		
		JTextPane name1 = new JTextPane();
		name1.setBackground(new Color(0, 0, 0));
		name1.setForeground(new Color(255, 255, 255));
		name1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name1.setBounds(58, 299, 132, 31);
		teamViewer.getContentPane().add(name1);
		
		
		JTextPane name2 = new JTextPane();
		name2.setForeground(Color.WHITE);
		name2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name2.setBackground(new Color(0, 0, 0));
		name2.setBounds(261, 299, 132, 31);
		teamViewer.getContentPane().add(name2);
		
		JTextPane name3 = new JTextPane();
		name3.setForeground(Color.WHITE);
		name3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name3.setBackground(new Color(0, 0, 0));
		name3.setBounds(474, 299, 132, 31);
		teamViewer.getContentPane().add(name3);
		
		JTextPane name4 = new JTextPane();
		name4.setForeground(Color.WHITE);
		name4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name4.setBackground(new Color(0, 0, 0));
		name4.setBounds(681, 299, 132, 31);
		teamViewer.getContentPane().add(name4);
		
		JTextPane name5 = new JTextPane();
		name5.setForeground(Color.WHITE);
		name5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name5.setBackground(new Color(0, 0, 0));
		name5.setBounds(891, 299, 132, 31);
		teamViewer.getContentPane().add(name5);
		
		JTextPane paneList[] = new JTextPane[5];
		paneList[0] = name1;
		paneList[1] = name2;
		paneList[2] = name3;
		paneList[3] = name4;
		paneList[4] = name5;
		
		
		errorField = new JTextField();
		errorField.setFont(new Font("Tahoma", Font.BOLD, 16));
		errorField.setForeground(Color.RED);
		errorField.setBackground(new Color(0, 0, 0));
		errorField.setBounds(368, 553, 349, 31);
		teamViewer.getContentPane().add(errorField);
		errorField.setColumns(10);
		
		JButton button1 = new JButton("View Stats");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(1, errorField);
			}
		});
		button1.setBackground(new Color(255, 255, 0));
		button1.setBounds(58, 344, 103, 23);
		teamViewer.getContentPane().add(button1);
		
		JButton button2 = new JButton("View Stats");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(2, errorField);
			}
		});
		button2.setBackground(Color.YELLOW);
		button2.setBounds(261, 344, 103, 23);
		teamViewer.getContentPane().add(button2);
		
		JButton button3 = new JButton("View Stats");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(3, errorField);
			}
		});
		button3.setBackground(Color.YELLOW);
		button3.setBounds(474, 341, 103, 23);
		teamViewer.getContentPane().add(button3);
		
		JButton button4 = new JButton("View Stats");
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(4, errorField);
			}
		});
		button4.setBackground(Color.YELLOW);
		button4.setBounds(681, 341, 103, 23);
		teamViewer.getContentPane().add(button4);
		
		JButton button5 = new JButton("View Stats");
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(5, errorField);
			}
		});
		button5.setBackground(Color.YELLOW);
		button5.setBounds(891, 341, 103, 23);
		teamViewer.getContentPane().add(button5);
		
		JProgressBar progressBar1 = new JProgressBar();
		progressBar1.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBar1.setStringPainted(true);
		progressBar1.setToolTipText("");
		progressBar1.setBackground(Color.LIGHT_GRAY);
		progressBar1.setForeground(new Color(50, 205, 50));
		progressBar1.setBounds(58, 113, 146, 14);
		teamViewer.getContentPane().add(progressBar1);
		
		JProgressBar progressBar2 = new JProgressBar();
		progressBar2.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBar2.setStringPainted(true);
		progressBar2.setForeground(new Color(50, 205, 50));
		progressBar2.setBackground(Color.LIGHT_GRAY);
		progressBar2.setBounds(265, 113, 146, 14);
		teamViewer.getContentPane().add(progressBar2);
		
		JProgressBar progressBar3 = new JProgressBar();
		progressBar3.setStringPainted(true);
		progressBar3.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBar3.setForeground(new Color(50, 205, 50));
		progressBar3.setBackground(Color.LIGHT_GRAY);
		progressBar3.setBounds(478, 113, 146, 14);
		teamViewer.getContentPane().add(progressBar3);
		
		JProgressBar progressBar4 = new JProgressBar();
		progressBar4.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBar4.setStringPainted(true);
		progressBar4.setForeground(new Color(50, 205, 50));
		progressBar4.setBackground(Color.LIGHT_GRAY);
		progressBar4.setBounds(685, 113, 146, 14);
		teamViewer.getContentPane().add(progressBar4);
		
		JProgressBar progressBar5 = new JProgressBar();
		progressBar5.setStringPainted(true);
		progressBar5.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBar5.setForeground(new Color(50, 205, 50));
		progressBar5.setBackground(Color.LIGHT_GRAY);
		progressBar5.setBounds(895, 113, 146, 14);
		teamViewer.getContentPane().add(progressBar5);
		
		
		JProgressBar barList[] = new JProgressBar[5];
		barList[0] = progressBar1;
		barList[1] = progressBar2;
		barList[2] = progressBar3;
		barList[3] = progressBar4;
		barList[4] = progressBar5;
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(68, 554, 111, 31);
		teamViewer.getContentPane().add(btnNewButton);
		
		JTextPane txtpnHealth = new JTextPane();
		txtpnHealth.setBackground(new Color(0, 0, 0));
		txtpnHealth.setForeground(new Color(255, 255, 255));
		txtpnHealth.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnHealth.setText("Health");
		txtpnHealth.setBounds(307, 90, 67, 20);
		teamViewer.getContentPane().add(txtpnHealth);
		
		JTextPane txtpnHealth_1 = new JTextPane();
		txtpnHealth_1.setText("Health");
		txtpnHealth_1.setForeground(Color.WHITE);
		txtpnHealth_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnHealth_1.setBackground(Color.BLACK);
		txtpnHealth_1.setBounds(507, 90, 67, 20);
		teamViewer.getContentPane().add(txtpnHealth_1);
		
		JTextPane txtpnHealth_2 = new JTextPane();
		txtpnHealth_2.setText("Health");
		txtpnHealth_2.setForeground(Color.WHITE);
		txtpnHealth_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnHealth_2.setBackground(Color.BLACK);
		txtpnHealth_2.setBounds(725, 90, 67, 20);
		teamViewer.getContentPane().add(txtpnHealth_2);
		
		JTextPane txtpnHealth_3 = new JTextPane();
		txtpnHealth_3.setText("Health");
		txtpnHealth_3.setForeground(Color.WHITE);
		txtpnHealth_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnHealth_3.setBackground(Color.BLACK);
		txtpnHealth_3.setBounds(937, 90, 67, 20);
		teamViewer.getContentPane().add(txtpnHealth_3);
		
		JTextPane txtpnHealth_4 = new JTextPane();
		txtpnHealth_4.setText("Health");
		txtpnHealth_4.setForeground(Color.WHITE);
		txtpnHealth_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnHealth_4.setBackground(Color.BLACK);
		txtpnHealth_4.setBounds(102, 90, 67, 20);
		teamViewer.getContentPane().add(txtpnHealth_4);
		
		
		int i = 0;
		while (i < this.player.getPlayersTeamLength()) {
			Monster monster = this.player.getPlayerMonsters().get(i);
			String monsterImageLink = chooseMonsterImage(monster);
			labelList[i].setIcon(new ImageIcon(monsterImageLink));
			setHealthBar(monster, barList[i]);
			i += 1;
		}
		while (i < labelList.length)
		{
			labelList[i].setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\EmptyPod.png"));
			i += 1;
		}
		
		
		
		int j = 0;
		while (j < this.player.getPlayersTeamLength()) {
			String monsterName = this.player.getPlayerMonsters().get(j).pickMonsterName();
			paneList[j].setText(monsterName);
			j += 1;
		}
		while (j < labelList.length)
		{
			paneList[j].setText("Empty");
			j += 1;
		}
		
		
		
		
		
	
		
		
		teamViewer.setTitle("Team Viewer");
		teamViewer.setBounds(100, 100, 1080, 720);
		teamViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
