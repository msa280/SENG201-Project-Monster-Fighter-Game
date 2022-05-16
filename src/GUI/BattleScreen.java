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
import game.Game;
import game.Player;
import monsters.Monster;
import monsters.Venomhound;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BattleScreen {
	
	/*
	 * The player.
	 */
	private Player player;
	/*
	 * The enemy.
	 */
	private Enemy enemy;
	/*
	 * A battle instance.
	 */
	private Battle battle;
	/*
	 * The players current monster (first in team).
	 */
	private Monster playerCurrentMonster;
	/*
	 * The enemies current monster (first in team).
	 */
	private Monster enemyCurrentMonster;
	/*
	 * The GUI frame.
	 */
	private JFrame frame;
	/*
	 * The text pane responsible for showing the player lastUpdate.
	 */
	private JTextPane updateArea;
	/*
	 * Updates the player on what is happening in the game via a message in the 
	 * form of a string.
	 */
	private String lastUpdate = "";
	
	/*
	 * The players monster uses an attack on the enemy monster.
	 * 
	 * @param attackName The name of the attack being used.
	 */
	public void useAttack(String attackName)
	{
		this.battle.playerAttack(this.playerCurrentMonster, this.enemyCurrentMonster, attackName);
		if (this.enemyCurrentMonster.isFaint() == false)
		{
			this.battle.enemyAttack(playerCurrentMonster, enemyCurrentMonster);
		}
		this.frame.dispose();
		BattleScreen.launchBattleScreen(this.player, this.enemy, this.battle.getBattleUpdate());
	}
	
	/*
	 * Sets a monsters health bar colour.
	 * 
	 * @param monster The monster.
	 * @param progressBar The colour of the monsters health bar.
	 */
	public void setHealthBar(Monster monster, JProgressBar progressBar) {
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
		else 
		{
			progressBar.setForeground(Color.RED);
		}
	}
	
	/*
	 * Leaves the battle arena GUI.
	 */
	public void leaveArena()
	{
		this.frame.dispose();
		ChooseBattleGUI.launchChooseBattle(this.player);
	}
	
	/*
	 * Launches the battle screen GUI.
	 * 
	 * @param player The player.
	 * @param enemy The enemy.
	 * @param update The update to send to the player.
	 */
	public static void launchBattleScreen(Player player, Enemy enemy, String update)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleScreen window = new BattleScreen(player, enemy, update);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		Player player = new Player();
		Game game = new Game();
		game.setGameDifficulty(1.0);
		Battle battle = new Battle();
		battle.setPlayer(player);
		player.setGame(game);
		player.setSelectedAvatar("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Enemies Artwork\\Haider.png");
		player.addToTeam(new Venomhound());
		battle.generateBattles();
		player.setBattle(battle);
		BattleScreen.launchBattleScreen(player, player.getBattle().getBattles().get(0), "BATTLE BEGUN!");
		
	}

	
	
	/*
	 * Create the application.
	 * 
	 * Creates the battle screen GUI.
	 * 
	 * @param player The player.
	 * @param enemy The enemy.
	 * @param update The update to send to the player.
	 */
	public BattleScreen(Player player, Enemy enemy, String update) {
		this.player = player;
		this.enemy = enemy;
		this.lastUpdate = update;
		this.battle = this.player.getBattle();
		initialize();
	}

	/*
	 * Initialises the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1063, 684);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel playerMonster = new JLabel("New label");
		playerMonster.setBackground(Color.GRAY);
		playerMonster.setBounds(71, 342, 152, 150);
		frame.getContentPane().add(playerMonster);
		
		JProgressBar playerHealthBar = new JProgressBar();
		playerHealthBar.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		playerHealthBar.setBackground(Color.WHITE);
		playerHealthBar.setStringPainted(true);
		playerHealthBar.setForeground(Color.BLACK);
		playerHealthBar.setBounds(71, 317, 152, 14);
		frame.getContentPane().add(playerHealthBar);
		
		JLabel enemyMonster = new JLabel("New label");
		enemyMonster.setBackground(Color.GRAY);
		enemyMonster.setBounds(821, 342, 152, 150);
		frame.getContentPane().add(enemyMonster);
		
		JProgressBar enemyHealthBar = new JProgressBar();
		enemyHealthBar.setBackground(Color.WHITE);
		enemyHealthBar.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		enemyHealthBar.setForeground(Color.BLACK);
		enemyHealthBar.setStringPainted(true);
		enemyHealthBar.setBounds(821, 317, 152, 14);
		frame.getContentPane().add(enemyHealthBar);
		
		updateArea = new JTextPane();
		updateArea.setFont(new Font("Tahoma", Font.BOLD, 18));
		updateArea.setEditable(false);
		updateArea.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		updateArea.setBounds(314, 234, 419, 226);
		updateArea.setText(this.lastUpdate);
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
		playerMonsterName.setForeground(Color.YELLOW);
		playerMonsterName.setBackground(Color.BLACK);
		playerMonsterName.setEditable(false);
		playerMonsterName.setFont(new Font("Tahoma", Font.BOLD, 18));
		playerMonsterName.setBounds(71, 503, 141, 26);
		frame.getContentPane().add(playerMonsterName);
		
		JButton leaveArena = new JButton("Leave Arena");
		
		leaveArena.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leaveArena.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
				leaveArena.setFont(new Font("Tahoma", Font.BOLD, 20));
				leaveArena.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leaveArena.setBorder(null);
				leaveArena.setFont(new Font("Tahoma", Font.BOLD, 18));
				leaveArena.setBackground(Color.YELLOW);
			}
		});
		leaveArena.setVisible(false);
		leaveArena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leaveArena();
			}
		});
		leaveArena.setBackground(Color.YELLOW);
		leaveArena.setFont(new Font("Tahoma", Font.BOLD, 18));
		leaveArena.setBounds(453, 532, 172, 52);
		frame.getContentPane().add(leaveArena);
		
		JLabel playerImage = new JLabel("New label");
		playerImage.setBounds(48, 11, 200, 212);
		playerImage.setIcon(new ImageIcon(this.player.getSelectedAvatar()));
		frame.getContentPane().add(playerImage);
		
		JLabel enemyImage = new JLabel("New label");
		enemyImage.setBounds(787, 11, 200, 212);
		enemyImage.setIcon(new ImageIcon(this.enemy.getEnemyImage()));
		frame.getContentPane().add(enemyImage);
		
		JTextPane playerName = new JTextPane();
		playerName.setForeground(Color.YELLOW);
		playerName.setOpaque(false);
		playerName.setEditable(false);
		playerName.setFont(new Font("Tahoma", Font.BOLD, 18));
		playerName.setBounds(112, 234, 122, 26);
		playerName.setText(this.player.getGame().getPlayerName());
		frame.getContentPane().add(playerName);
		
		JTextPane enemyName = new JTextPane();
		enemyName.setForeground(Color.YELLOW);
		enemyName.setOpaque(false);
		enemyName.setEditable(false);
		enemyName.setFont(new Font("Tahoma", Font.BOLD, 18));
		enemyName.setBounds(859, 234, 142, 26);
		enemyName.setText(this.enemy.getEnemyName());
		frame.getContentPane().add(enemyName);
		
		JTextPane enemyMonsterName = new JTextPane();
		enemyMonsterName.setBackground(Color.BLACK);
		enemyMonsterName.setForeground(Color.YELLOW);
		enemyMonsterName.setEditable(false);
		enemyMonsterName.setFont(new Font("Tahoma", Font.BOLD, 18));
		enemyMonsterName.setBounds(831, 503, 142, 26);
		frame.getContentPane().add(enemyMonsterName);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Images\\BattleArea.jpg"));
		lblNewLabel.setBounds(-139, 0, 1186, 645);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		/* Generating Player Side stats */
		for (Monster monster: this.player.getPlayerMonsters())
		{
			if (monster.isFaint() == false)
			{
				if (monster.getCurrentHealth() == monster.getMaxHealth())
				{
					this.updateArea.setText("You: Go %s!\n".formatted(monster.pickMonsterName()));
				}
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
				else
				{
					specialAttackButton.setVisible(false);
				}
			}
		}
		
		
		/* Generating Enemy Side stats */
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
		
		if (this.enemy.canFight() == false)
	    {
			this.enemy.setAlreadyFought(true);
			this.lastUpdate += "\nAll enemy monsters have fainted!\nBattle Over!";
			this.updateArea.setText(this.lastUpdate);
			attackButton.setVisible(false);
			specialAttackButton.setVisible(false);
			leaveArena.setVisible(true);
			enemyMonster.setVisible(false);
			enemyMonsterName.setVisible(false);
			enemyHealthBar.setVisible(false);
	    }
		
		if (this.player.canFight() == false)
	    {
			this.enemy.setAlreadyFought(true);
			this.lastUpdate += "\nAll your monsters have fainted!\nBattle Over!";
			this.updateArea.setText(this.lastUpdate);
			attackButton.setVisible(false);
			specialAttackButton.setVisible(false);
			leaveArena.setVisible(true);
			playerMonster.setVisible(false);
			playerMonsterName.setVisible(false);
			playerHealthBar.setVisible(false);
	    }

	}
}
