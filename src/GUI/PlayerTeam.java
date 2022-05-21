package GUI;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextPane;


import game.AudioPlayer;
import game.Player;
import monsters.Monster;
import monsters.Venomhound;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;
import java.awt.Choice;

public class PlayerTeam {
	/*
	 * The team viewer frame.
	 */
	private JFrame teamViewer;
	/*
	 * The player.
	 */
	private Player player;
	/*
	 * The button audio.
	 */
	private AudioPlayer buttonAudio = new AudioPlayer();
	/*
	 * The player update text pane.
	 */
	JTextPane update = new JTextPane();
	/*
	 * The players gold.
	 */
	private JTextField gold;

	/*
	 * Sets the monsters health bar.
	 * 
	 * @param monster The monster.
	 * @param progressBar The health bar of the monster.
	 */
	public void setHealthBar(Monster monster, JProgressBar progressBar)
	{
		progressBar.setMinimum(0);
		progressBar.setMaximum(monster.getMaxHealth());
		progressBar.setValue(monster.getCurrentHealth());
		
		int health = progressBar.getValue();
		
		if (health >= 75)
		{
			progressBar.setForeground(Color.GREEN);
		} 
		else if (health >= 50 && health < 75)
		{
			progressBar.setForeground(Color.ORANGE);
		}
		else if (health >= 25 && health < 50)
		{
			progressBar.setForeground(Color.YELLOW);
		}
		else if (health < 25 && health > 1) 
		{
			progressBar.setForeground(Color.RED);
		}
		else
		{
			progressBar.setBackground(Color.RED);
		}
	}
	
	/*
	 * Views the monsters statistics.
	 * 
	 * @param buttonIndex The index for the chosen button.
	 */
	public void viewMonsterStat(int buttonIndex)
	{
		Monster monster = this.player.getPlayerMonsters().get(buttonIndex-1);
		update.setText("Viewing %s stats.".formatted(monster.pickMonsterName()));
		MonsterStat.launchMonsterStatScreen(monster);
	}
	
	/*
	 * Renames a monster.
	 * 
	 * @param chosenMonsterName The current name of the monster that the player wants to rename.
	 * @param newName The new name.
	 */
	public void renameMonster(String chosenMonsterName, String newName)
	{
		if (newName.length() == 0)
		{
			buttonAudio.playSoundOnce("error.wav");
			update.setText("Enter a name!");
			update.setForeground(Color.RED);
			return;
		}
	    else if (newName.length() >= 16)
		{
			buttonAudio.playSoundOnce("error.wav");
			update.setForeground(Color.RED);
			update.setText("Name too long! Please choose a shorter name.");
			return;
		}
		else
		{
			buttonAudio.playSoundOnce("buttonA.wav");
			
			for (Monster monster: this.player.getPlayerMonsters())
			{
				if (monster.pickMonsterName() == chosenMonsterName)
				{
					monster.setMonsterRename(newName);
					this.player.setLastUpdate("Monster has been renamed!");
					break;
				}
			}
			
			this.teamViewer.dispose();
			PlayerTeam.launchTeamViewer(this.player);
		}
	}
	
	/*
	 * Sells a monster.
	 * 
	 * @param buttonIndex The index of the chosen button.
	 * @param updateArea A text panel that updates the player.
	 */
	public void sellMonster(int buttonIndex, JTextPane updateArea)
	{
		buttonAudio.playSoundOnce("purchaseSound.wav");
		int i = 0;
		for (Monster monster: this.player.getPlayerMonsters())
		{
			if (i == buttonIndex)
			{
				this.player.sellMonster(monster);
				this.player.setLastUpdate( "Monster sold!\n%s has been removed from the team.\n%d Gold has been given to you.\n".formatted(monster.pickMonsterName(), monster.getResalePrice()));
				this.teamViewer.dispose();
				PlayerTeam.launchTeamViewer(this.player);
				break;
			}
			i += 1;
		}
		
	}
	
	/*
	 * Launches the team viewer.
	 * 
	 * @param player The player.
	 */
	public static void launchTeamViewer(Player player)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerTeam window = new PlayerTeam(player);
					window.teamViewer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * Goes back to the main menu.
	 */
	public void goBack()
	{
		buttonAudio.playSoundOnce("buttonA.wav");
		this.player.setLastUpdate("");
		this.teamViewer.dispose();
		MainMenu.launchMainMenu(this.player);
	}
	
	/*
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		Player player = new Player();
		player.addToTeam(new Venomhound());
		player.addToTeam(new Venomhound());
		PlayerTeam.launchTeamViewer(player);
	}

	/*
	 * Creates the player team GUI.
	 * 
	 * @param player The player
	 */
	public PlayerTeam(Player player) 
	{
		this.player = player;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		teamViewer = new JFrame();
		teamViewer.setResizable(false);
		teamViewer.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		teamViewer.getContentPane().setBackground(Color.DARK_GRAY);
		teamViewer.getContentPane().setLayout(null);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		teamViewer.setLocation(dim.width/2-teamViewer.getSize().width/2, dim.height/2-teamViewer.getSize().height/2);
		
		
		JTextPane text = new JTextPane();
		text.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.MAGENTA));
		text.setEditable(false);
		text.setBackground(Color.BLACK);
		text.setForeground(Color.MAGENTA);
		text.setFont(new Font("Tahoma", Font.BOLD, 18));
		text.setText("Your team:");
		text.setBounds(56, 22, 108, 28);
		teamViewer.getContentPane().add(text);
		
		
		update.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 0, 255)));
		update.setEditable(false);
		update.setFont(new Font("Tahoma", Font.BOLD, 16));
		update.setForeground(Color.GREEN);
		update.setBackground(Color.DARK_GRAY);
		update.setBounds(318, 721, 496, 63);
		update.setText(this.player.getLastUpdate());
		teamViewer.getContentPane().add(update);
		
		
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
		name1.setBackground(Color.DARK_GRAY);
		name1.setForeground(new Color(255, 255, 255));
		name1.setFont(new Font("Tahoma", Font.BOLD, 12));
		name1.setBounds(68, 299, 132, 31);
		teamViewer.getContentPane().add(name1);
		
		
		JTextPane name2 = new JTextPane();
		name2.setForeground(Color.WHITE);
		name2.setFont(new Font("Tahoma", Font.BOLD, 12));
		name2.setBackground(Color.DARK_GRAY);
		name2.setBounds(328, 299, 132, 31);
		teamViewer.getContentPane().add(name2);
		
		JTextPane name3 = new JTextPane();
		name3.setForeground(Color.WHITE);
		name3.setFont(new Font("Tahoma", Font.BOLD, 12));
		name3.setBackground(Color.DARK_GRAY);
		name3.setBounds(576, 299, 132, 31);
		teamViewer.getContentPane().add(name3);
		
		JTextPane name4 = new JTextPane();
		name4.setForeground(Color.WHITE);
		name4.setFont(new Font("Tahoma", Font.BOLD, 12));
		name4.setBackground(Color.DARK_GRAY);
		name4.setBounds(814, 299, 132, 31);
		teamViewer.getContentPane().add(name4);
		
		JTextPane paneList[] = new JTextPane[4];
		paneList[0] = name1;
		paneList[1] = name2;
		paneList[2] = name3;
		paneList[3] = name4;
		
		JButton button1 = new JButton("View Stats");
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				button1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
				button1.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button1.setBorder(null);
				button1.setFont(new Font("Tahoma", Font.BOLD, 11));
			}
		});
		button1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(1);
			}
		});
		button1.setBackground(new Color(255, 255, 0));
		button1.setBounds(78, 341, 103, 23);
		teamViewer.getContentPane().add(button1);
		
		JButton button2 = new JButton("View Stats");
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				button2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
				button2.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button2.setBorder(null);
				button2.setFont(new Font("Tahoma", Font.BOLD, 11));
			}
		});
		button2.setFont(new Font("Tahoma", Font.BOLD, 11));
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(2);
			}
		});
		button2.setBackground(Color.YELLOW);
		button2.setBounds(338, 341, 103, 23);
		teamViewer.getContentPane().add(button2);
		
		JButton button3 = new JButton("View Stats");
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				button3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
				button3.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button3.setBorder(null);
				button3.setFont(new Font("Tahoma", Font.BOLD, 11));
			}
		});
		button3.setFont(new Font("Tahoma", Font.BOLD, 11));
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(3);
			}
		});
		button3.setBackground(Color.YELLOW);
		button3.setBounds(586, 341, 103, 23);
		teamViewer.getContentPane().add(button3);
		
		JButton button4 = new JButton("View Stats");
		button4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				button4.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
				button4.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button4.setBorder(null);
				button4.setFont(new Font("Tahoma", Font.BOLD, 11));
			}
		});
		button4.setFont(new Font("Tahoma", Font.BOLD, 11));
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMonsterStat(4);
			}
		});
		button4.setBackground(Color.YELLOW);
		button4.setBounds(824, 341, 103, 23);
		teamViewer.getContentPane().add(button4);
		
		
		JButton[] statButtons = new JButton[4];
		statButtons[0] = button1;
		statButtons[1] = button2;
		statButtons[2] = button3;
		statButtons[3] = button4;
		
		
		
		
		JProgressBar progressBar1 = new JProgressBar();
		progressBar1.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBar1.setStringPainted(true);
		progressBar1.setToolTipText("");
		progressBar1.setBackground(Color.LIGHT_GRAY);
		progressBar1.setForeground(new Color(50, 205, 50));
		progressBar1.setBounds(62, 113, 146, 14);
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
		
		JButton back = new JButton("Go Back");
		
		back.setForeground(Color.YELLOW);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				back.setFont(new Font("Tahoma", Font.BOLD, 16));
				back.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
				back.setForeground(Color.BLACK);
				back.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				back.setFont(new Font("Tahoma", Font.BOLD, 14));
				back.setForeground(Color.YELLOW);
				back.setBorder(null);
				back.setBackground(Color.BLACK);
			}
		});
		back.setBackground(Color.BLACK);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
				
			}
		});
		back.setFont(new Font("Tahoma", Font.BOLD, 14));
		back.setBounds(49, 747, 132, 37);
		teamViewer.getContentPane().add(back);
		
		
		JTextPane health1 = new JTextPane();
		health1.setEditable(false);
		health1.setText("Health");
		health1.setForeground(Color.WHITE);
		health1.setFont(new Font("Tahoma", Font.BOLD, 14));
		health1.setBackground(Color.DARK_GRAY);
		health1.setBounds(102, 90, 67, 20);
		teamViewer.getContentPane().add(health1);
		
		JTextPane health2 = new JTextPane();
		health2.setEditable(false);
		health2.setBackground(Color.DARK_GRAY);
		health2.setForeground(new Color(255, 255, 255));
		health2.setFont(new Font("Tahoma", Font.BOLD, 14));
		health2.setText("Health");
		health2.setBounds(360, 90, 67, 20);
		teamViewer.getContentPane().add(health2);
		
		JTextPane health3 = new JTextPane();
		health3.setEditable(false);
		health3.setText("Health");
		health3.setForeground(Color.WHITE);
		health3.setFont(new Font("Tahoma", Font.BOLD, 14));
		health3.setBackground(Color.DARK_GRAY);
		health3.setBounds(600, 90, 67, 20);
		teamViewer.getContentPane().add(health3);
		
		JTextPane health4 = new JTextPane();
		health4.setEditable(false);
		health4.setText("Health");
		health4.setForeground(Color.WHITE);
		health4.setFont(new Font("Tahoma", Font.BOLD, 14));
		health4.setBackground(Color.DARK_GRAY);
		health4.setBounds(852, 90, 67, 20);
		teamViewer.getContentPane().add(health4);
		
		JTextPane[] healthLabels = new JTextPane[4];
		healthLabels[0] = health1;
		healthLabels[1] = health2;
		healthLabels[2] = health3;
		healthLabels[3] = health4;
		
		
		
		
		
		JTextField price1 = new JTextField();
		price1.setText("Resale Price (Gold: Empty)");
		price1.setBackground(Color.DARK_GRAY);
		price1.setForeground(Color.ORANGE);
		price1.setFont(new Font("Tahoma", Font.BOLD, 14));
		price1.setBounds(41, 374, 204, 20);
		teamViewer.getContentPane().add(price1);
		price1.setColumns(10);
		
		JTextField price2 = new JTextField();
		price2.setText("Resale Price (Gold: Empty)");
		price2.setForeground(Color.ORANGE);
		price2.setFont(new Font("Tahoma", Font.BOLD, 14));
		price2.setColumns(10);
		price2.setBackground(Color.DARK_GRAY);
		price2.setBounds(304, 375, 204, 20);
		teamViewer.getContentPane().add(price2);
		
		JTextField price3 = new JTextField();
		price3.setText("Resale Price (Gold: Empty)");
		price3.setForeground(Color.ORANGE);
		price3.setFont(new Font("Tahoma", Font.BOLD, 14));
		price3.setColumns(10);
		price3.setBackground(Color.DARK_GRAY);
		price3.setBounds(550, 375, 198, 20);
		teamViewer.getContentPane().add(price3);
		
		JTextField price4 = new JTextField();
		price4.setText("Resale Price (Gold: Empty)");
		price4.setForeground(Color.ORANGE);
		price4.setFont(new Font("Tahoma", Font.BOLD, 14));
		price4.setColumns(10);
		price4.setBackground(Color.DARK_GRAY);
		price4.setBounds(804, 375, 198, 20);
		teamViewer.getContentPane().add(price4);
		
		
		JTextField prices[] = new JTextField[4];
		prices[0] = price1;
		prices[1] = price2;
		prices[2] = price3;
		prices[3] = price4;
		
		
		
		
		JButton sell1 = new JButton("Sell");
		
		sell1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sell1.setForeground(Color.BLACK);
				buttonAudio.playSoundOnce("buttonHover.wav");
				sell1.setFont(new Font("Tahoma", Font.BOLD, 16));
				sell1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sell1.setForeground(Color.WHITE);
				sell1.setFont(new Font("Tahoma", Font.BOLD, 14));
				sell1.setBorder(null);
			}
		});
		sell1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellMonster(0, update);
			}
		});
		sell1.setForeground(Color.WHITE);
		sell1.setBackground(Color.RED);
		sell1.setFont(new Font("Tahoma", Font.BOLD, 14));
		sell1.setBounds(87, 406, 82, 31);
		teamViewer.getContentPane().add(sell1);
		
		JButton sell2 = new JButton("Sell");
		sell2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sell2.setForeground(Color.BLACK);
				buttonAudio.playSoundOnce("buttonHover.wav");
				sell2.setFont(new Font("Tahoma", Font.BOLD, 16));
				sell2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sell2.setForeground(Color.WHITE);
				sell2.setFont(new Font("Tahoma", Font.BOLD, 14));
				sell2.setBorder(null);
			}
		});
		sell2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellMonster(1, update);
			}
		});
		sell2.setForeground(Color.WHITE);
		sell2.setFont(new Font("Tahoma", Font.BOLD, 14));
		sell2.setBackground(Color.RED);
		sell2.setBounds(346, 406, 81, 31);
		teamViewer.getContentPane().add(sell2);
		
		JButton sell3 = new JButton("Sell");
		sell3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sell3.setForeground(Color.BLACK);
				buttonAudio.playSoundOnce("buttonHover.wav");
				sell3.setFont(new Font("Tahoma", Font.BOLD, 16));
				sell3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sell3.setForeground(Color.WHITE);
				sell3.setFont(new Font("Tahoma", Font.BOLD, 14));
				sell3.setBorder(null);
			}
		});
		sell3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellMonster(2, update);
			}
		});
		sell3.setForeground(Color.WHITE);
		sell3.setFont(new Font("Tahoma", Font.BOLD, 14));
		sell3.setBackground(Color.RED);
		sell3.setBounds(600, 406, 81, 29);
		teamViewer.getContentPane().add(sell3);
		
		JButton sell4 = new JButton("Sell");
		sell4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sell4.setForeground(Color.BLACK);
				buttonAudio.playSoundOnce("buttonHover.wav");
				sell4.setFont(new Font("Tahoma", Font.BOLD, 16));
				sell4.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sell4.setForeground(Color.WHITE);
				sell4.setFont(new Font("Tahoma", Font.BOLD, 14));
				sell4.setBorder(null);
			}
		});
		sell4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellMonster(3, update);
			}
		});
		sell4.setForeground(Color.WHITE);
		sell4.setFont(new Font("Tahoma", Font.BOLD, 14));
		sell4.setBackground(Color.RED);
		sell4.setBounds(841, 406, 78, 29);
		teamViewer.getContentPane().add(sell4);
		
		
		JButton[] sellButtons = new JButton[5];
		sellButtons[0] = sell1;
		sellButtons[1] = sell2;
		sellButtons[2] = sell3;
		sellButtons[3] = sell4;
		
		gold = new JTextField();
		gold.setText("Available Gold: %d".formatted(this.player.getPlayerGold()));
		gold.setForeground(Color.YELLOW);
		gold.setFont(new Font("Tahoma", Font.BOLD, 20));
		gold.setEditable(false);
		gold.setColumns(10);
		gold.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.YELLOW));
		gold.setBackground(Color.DARK_GRAY);
		gold.setBounds(421, 22, 246, 28);
		teamViewer.getContentPane().add(gold);
		
		
		
		
		
		JTextPane txtpnChooseMonster = new JTextPane();
		txtpnChooseMonster.setEditable(false);
		txtpnChooseMonster.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 255)));
		txtpnChooseMonster.setForeground(Color.MAGENTA);
		txtpnChooseMonster.setBackground(Color.BLACK);
		txtpnChooseMonster.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpnChooseMonster.setText("Choose Monster");
		txtpnChooseMonster.setBounds(278, 501, 135, 23);
		teamViewer.getContentPane().add(txtpnChooseMonster);
		
		JTextPane txtpnRenameAMonster = new JTextPane();
		txtpnRenameAMonster.setEditable(false);
		txtpnRenameAMonster.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.MAGENTA));
		txtpnRenameAMonster.setBackground(Color.BLACK);
		txtpnRenameAMonster.setForeground(Color.MAGENTA);
		txtpnRenameAMonster.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpnRenameAMonster.setText("Rename a monster:");
		txtpnRenameAMonster.setBounds(22, 535, 167, 27);
		teamViewer.getContentPane().add(txtpnRenameAMonster);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.BOLD, 16));
		textPane.setForeground(Color.BLACK);
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setBounds(518, 535, 230, 27);
		teamViewer.getContentPane().add(textPane);
		
		JTextPane txtpnNewName = new JTextPane();
		txtpnNewName.setEditable(false);
		txtpnNewName.setText("New Name");
		txtpnNewName.setForeground(Color.MAGENTA);
		txtpnNewName.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpnNewName.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 255)));
		txtpnNewName.setBackground(Color.BLACK);
		txtpnNewName.setBounds(576, 501, 93, 23);
		teamViewer.getContentPane().add(txtpnNewName);
		
		Choice choice = new Choice();
		choice.setFont(new Font("Dialog", Font.BOLD, 16));
		choice.setForeground(Color.BLACK);
		choice.setBounds(207, 535, 292, 31);
		teamViewer.getContentPane().add(choice);
		
		for (Monster monster: this.player.getPlayerMonsters())
		{
			choice.add(monster.pickMonsterName());
		}
		
		
		
		JButton btnNewButton = new JButton("Rename");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renameMonster(choice.getSelectedItem(), textPane.getText());
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				buttonAudio.playSoundOnce("buttonHover.wav");
				btnNewButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnNewButton.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				btnNewButton.setBorder(null);
				btnNewButton.setBackground(Color.LIGHT_GRAY);
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
			}
		});
		
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(765, 539, 108, 28);
		teamViewer.getContentPane().add(btnNewButton);
		
		
		
		
		int i = 0;
		while (i < this.player.getPlayersTeamLength()) {
			Monster monster = this.player.getPlayerMonsters().get(i);
			labelList[i].setIcon(new ImageIcon(monster.getMonsterImage()));
			paneList[i].setText(monster.pickMonsterName());
			prices[i].setText("Resale Price (Gold: %d)".formatted(monster.getResalePrice()));
			setHealthBar(monster, barList[i]);
			i += 1;
		}
		
		while (i < labelList.length)
		{
			labelList[i].setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\EmptyPod.png"));
			paneList[i].setVisible(false);
			prices[i].setVisible(false);
			barList[i].setVisible(false);
			sellButtons[i].setVisible(false);
			statButtons[i].setVisible(false);
			healthLabels[i].setVisible(false);
			i += 1;
		}
		
		
		teamViewer.setTitle("Team Viewer");
		teamViewer.setBounds(100, 100, 1080, 834);
		teamViewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
