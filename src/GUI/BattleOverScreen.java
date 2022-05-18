package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import game.Battle;
import game.Enemy;
import game.Game;
import game.Player;
import monsters.Venomhound;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BattleOverScreen {

	private JFrame frmBattleStats;
	private Battle battle;
	
	
	public static void launchBattleStats(Battle battle)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleOverScreen window = new BattleOverScreen(battle);
					window.frmBattleStats.setVisible(true);
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
		Game game = new Game();
		game.setGameDifficulty(1.0);
		Battle battle = new Battle();
		battle.setPlayer(player);
		player.setGame(game);
		player.setSelectedAvatar("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Enemies Artwork\\Haider.png");
		player.addToTeam(new Venomhound());
		battle.generateBattles();
		player.setBattle(battle);
		BattleOverScreen.launchBattleStats(battle);
		
		
	}

	/**
	 * Create the application.
	 */
	public BattleOverScreen(Battle battle) {
		this.battle = battle;
		Enemy enemy = new Enemy();
		enemy.generateEnemyMonsters();
		enemy.generateEnemyName();
		enemy.generateEnemyTeam();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBattleStats = new JFrame();
		frmBattleStats.getContentPane().setBackground(Color.BLACK);
		frmBattleStats.setTitle("Battle Stats");
		frmBattleStats.setBounds(100, 100, 333, 609);
		frmBattleStats.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBattleStats.getContentPane().setLayout(null);
		
		JLabel winner = new JLabel("New label");
		winner.setBackground(Color.BLACK);
		winner.setOpaque(true);
		winner.setBounds(49, 30, 200, 212);
		if (this.battle.getPlayerWon() == true) {
			winner.setIcon(new ImageIcon(this.battle.getPlayer().getSelectedAvatar()));
		} else {
			winner.setIcon(new ImageIcon(this.battle.getEnemy().getEnemyImage()));
		}
		frmBattleStats.getContentPane().add(winner);
		
		JTextPane stats = new JTextPane();
		stats.setFont(new Font("Tahoma", Font.BOLD, 16));
		stats.setEditable(false);
		stats.setBackground(Color.YELLOW);
		stats.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.RED));
		stats.setBounds(16, 253, 284, 251);
		stats.setText(this.battle.getBattleStats());
		frmBattleStats.getContentPane().add(stats);
		
		JButton okay = new JButton("Okay");
		okay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBattleStats.dispose();
			}
		});
		okay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				okay.setFont(new Font("Tahoma", Font.BOLD, 16));
				okay.setForeground(Color.BLACK);
				okay.setBackground(Color.GREEN);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				okay.setFont(new Font("Tahoma", Font.BOLD, 14));
				okay.setForeground(Color.GREEN);
				okay.setBackground(Color.BLACK);
			}
		});
		okay.setFont(new Font("Tahoma", Font.BOLD, 14));
		okay.setForeground(Color.GREEN);
		okay.setBackground(Color.BLACK);
		okay.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.GREEN));
		okay.setBounds(87, 527, 132, 32);
		frmBattleStats.getContentPane().add(okay);
		
	
	}
}
