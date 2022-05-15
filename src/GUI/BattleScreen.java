package GUI;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JTextPane;

import game.Battle;
import game.Enemy;
import game.Player;
import monsters.Monster;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class BattleScreen {
	
	private Player player;
	private Enemy enemy;
	private Battle battle;
	private Monster playerCurrentMonster;
	private Monster enemyCurrentMonster;
	private JFrame frame;
	private JTextPane updateArea;
	private String lastUpdate = "";
	
	
	public void useAttack(String attackName)
	{
		this.battle.playerAttack(playerCurrentMonster, enemyCurrentMonster, attackName);
		this.frame.dispose();
		
		BattleScreen.launchBattleScreen(this.player, this.enemy, this.battle.getLastUpdate());
	}
	
	

	public void setHealthBar(Monster monster, JProgressBar progressbar) {
		progressbar.setMinimum(0);
		progressbar.setMaximum(monster.getMaxHealth());
		progressbar.setValue(monster.getCurrentHealth());
		int health = progressbar.getValue();
		
		if (health >= 75)
		{
			progressbar.setForeground(Color.GREEN);
		} 
		else if (health >= 50 && health < 75)
		{
			progressbar.setForeground(Color.ORANGE);
		}
		else if (health >= 25 && health < 50)
		{
			progressbar.setForeground(Color.YELLOW);
		}
		else 
		{
			progressbar.setForeground(Color.RED);
		}
	}
	
	
	
	
	public void leaveArena()
	{
		this.frame.dispose();
	}
	
	
	public static void launchBattleScreen(Player player, Enemy enemy, String lastUpdate)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleScreen window = new BattleScreen(player, enemy, lastUpdate);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		Player player = new Player();
		Battle battle = new Battle();
		battle.generateBattles();
		player.setBattle(battle);
		
		BattleScreen.launchBattleScreen(player, player.getBattle().getBattles().get(0), "");
		
	}

	
	
	/**
	 * Create the application.
	 */
	public BattleScreen(Player player, Enemy enemy, String lastUpdate) {
		this.player = player;
		this.enemy = enemy;
		this.battle = this.player.getBattle();
		this.lastUpdate = lastUpdate;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1065, 689);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel playerMonster = new JLabel("New label");
		playerMonster.setBackground(Color.GRAY);
		playerMonster.setBounds(71, 342, 152, 150);
		frame.getContentPane().add(playerMonster);
		
		JProgressBar playerHealthBar = new JProgressBar();
		playerHealthBar.setStringPainted(true);
		playerHealthBar.setBounds(71, 317, 152, 14);
		frame.getContentPane().add(playerHealthBar);
		
		JLabel enemyMonster = new JLabel("New label");
		enemyMonster.setBackground(Color.GRAY);
		enemyMonster.setBounds(821, 342, 152, 150);
		frame.getContentPane().add(enemyMonster);
		
		JProgressBar enemyHealthBar = new JProgressBar();
		enemyHealthBar.setStringPainted(true);
		enemyHealthBar.setBounds(821, 317, 152, 14);
		frame.getContentPane().add(enemyHealthBar);
		
		updateArea = new JTextPane();
		updateArea.setBounds(305, 593, 477, 46);
		updateArea.setText(lastUpdate);
		frame.getContentPane().add(updateArea);
		
		JButton attackButton = new JButton("");
		attackButton.setForeground(new Color(255, 255, 255));
		attackButton.setBackground(new Color(0, 0, 0));
		attackButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useAttack("Attack");
			}
		});
		attackButton.setBounds(71, 540, 141, 26);
		frame.getContentPane().add(attackButton);
		
		JButton specialAttackButton = new JButton("");
		specialAttackButton.setVisible(false);
		specialAttackButton.setForeground(new Color(255, 0, 0));
		specialAttackButton.setBackground(new Color(255, 255, 0));
		specialAttackButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		specialAttackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useAttack("Special Attack");
			}
		});
		specialAttackButton.setBounds(71, 577, 141, 26);
		frame.getContentPane().add(specialAttackButton);
		
		JTextPane playerMonsterName = new JTextPane();
		playerMonsterName.setFont(new Font("Tahoma", Font.BOLD, 14));
		playerMonsterName.setBounds(71, 503, 141, 26);
		frame.getContentPane().add(playerMonsterName);
		
		JButton btnNewButton = new JButton("Leave Arena");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leaveArena();
			}
		});
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(893, 603, 132, 36);
		frame.getContentPane().add(btnNewButton);
		
		JLabel playerImage = new JLabel("New label");
		playerImage.setBounds(48, 11, 200, 212);
		playerImage.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Enemies Artwork\\Haider.png"));
		frame.getContentPane().add(playerImage);
		
		JLabel enemyImage = new JLabel("New label");
		enemyImage.setBounds(804, 11, 200, 212);
		enemyImage.setIcon(new ImageIcon(this.enemy.getEnemyImage()));
		frame.getContentPane().add(enemyImage);
		
		JTextPane playerName = new JTextPane();
		playerName.setFont(new Font("Tahoma", Font.BOLD, 18));
		playerName.setBounds(101, 234, 86, 26);
		playerName.setText("Haider");
		frame.getContentPane().add(playerName);
		
		JTextPane enemyName = new JTextPane();
		enemyName.setFont(new Font("Tahoma", Font.BOLD, 18));
		enemyName.setBounds(833, 234, 123, 26);
		enemyName.setText(this.enemy.getEnemyName());
		frame.getContentPane().add(enemyName);
		
		JTextPane enemyMonsterName = new JTextPane();
		enemyMonsterName.setFont(new Font("Tahoma", Font.BOLD, 14));
		enemyMonsterName.setBounds(833, 503, 123, 26);
		frame.getContentPane().add(enemyMonsterName);
		
		
		
		
		
		/* Generating Player Side stats */
		for (Monster monster: this.player.getPlayerMonsters())
		{
			if (monster.isFaint() == false)
			{
				this.playerCurrentMonster = monster;
				playerMonster.setIcon(new ImageIcon(monster.getMonsterImage()));
				playerMonsterName.setText(monster.pickMonsterName());
				setHealthBar(monster, playerHealthBar);
				attackButton.setText(monster.getAttackName());
				
				if (monster.getSpecialAttackAvailable())
				{
					specialAttackButton.setVisible(true);
					specialAttackButton.setText(monster.getSpecialAttackName());
				}
			}
		}
		
		for (Monster monster: this.enemy.getEnemyTeam())
		{
			if (monster.isFaint() == false)
			{
				this.enemyCurrentMonster = monster;
				enemyMonster.setIcon(new ImageIcon(monster.getMonsterImage()));
				enemyMonsterName.setText(monster.pickMonsterName());
				setHealthBar(monster, enemyHealthBar);
			}
		}
		
		
		
		
		
		
		
		
	}
}
