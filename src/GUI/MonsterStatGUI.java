package GUI;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import monsters.Cavernfreak;
import monsters.Hollowtree;
import monsters.Magmataur;
import monsters.Monster;
import monsters.Mornpest;
import monsters.Soilscreamer;
import monsters.Venomhound;
import javax.swing.JTextPane;

import game.AudioPlayer;

import java.awt.Font;


/*
 * This class can show the images and statistics of each of the monsters in the game
 * to the player in the form of a GUI. 
 */
public class MonsterStatGUI {

	private JFrame frmMonsterStats;
	private Monster monster;
	private AudioPlayer buttonAudio = new AudioPlayer();
	/*
	 * Launches the window.
	 * 
	 * @param monster The chosen monster for which their image and statistics will be shown.
	 */
	public static void launchMonsterStatScreen(Monster monster)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonsterStatGUI window = new MonsterStatGUI(monster);
					window.frmMonsterStats.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	
	

	/*
	 * Launches the application.
	 */
	public static void main(String[] args) {
		Monster venomhound = new Venomhound();
		Monster soilscreamer = new Soilscreamer();
		Monster mornpest = new Mornpest();
		Monster cavernfreak = new Cavernfreak();
		Monster hollowtree = new Hollowtree();
		Monster magmataur = new Magmataur();
		
		launchMonsterStatScreen(venomhound);
		launchMonsterStatScreen(soilscreamer);
		launchMonsterStatScreen(mornpest);
		launchMonsterStatScreen(hollowtree);
		launchMonsterStatScreen(cavernfreak);
		launchMonsterStatScreen(magmataur);
	}

	/*
	 * Creates the application.
	 * 
	 * @param monster The chosen monster.
	 */
	public MonsterStatGUI(Monster monster) {
		this.monster = monster;
		initialize(monster);
		this.buttonAudio.playSoundOnce("buttonC.wav");
	}

	/*
	 * Initializes the contents of the frame.
	 * 
	 * @param monster The chosen monster.
	 */
	private void initialize(Monster monster) {
		frmMonsterStats = new JFrame();
		frmMonsterStats.getContentPane().setForeground(new Color(0, 0, 153));
		frmMonsterStats.getContentPane().setBackground(new Color(204, 255, 0));
		frmMonsterStats.getContentPane().setLayout(null);
		frmMonsterStats.setSize(294, 517);
	
		
		JLabel monsterImage = new JLabel("");
		monsterImage.setForeground(new Color(0, 0, 153));
		monsterImage.setBounds(62, 24, 150, 150);
		monsterImage.setIcon(new ImageIcon(this.monster.getMonsterImage()));
		frmMonsterStats.getContentPane().add(monsterImage);
		frmMonsterStats.setTitle("Monster Stats");
		frmMonsterStats.setBounds(100, 100, 294, 454);
		
		
		JTextPane monsterName = new JTextPane();
		monsterName.setForeground(new Color(0, 0, 0));
		monsterName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		monsterName.setEditable(false);
		monsterName.setBackground(new Color(204, 255, 0));
		monsterName.setBounds(10, 233, 257, 143);
		monsterName.setText(this.monster.displayMonsterStats());
		frmMonsterStats.getContentPane().add(monsterName);
		
		
		JTextPane monsterStatsLabel = new JTextPane();
		monsterStatsLabel.setForeground(new Color(0, 0, 0));
		monsterStatsLabel.setBackground(new Color(204, 255, 0));
		monsterStatsLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		monsterStatsLabel.setText("_\u0332_\u0332_\u0332_\u0332_\u0332_\u0332__\u0332_\u0332  \uD835\uDC0C\uD835\uDC28\uD835\uDC27\uD835\uDC2C\uD835\uDC2D\uD835\uDC1E\uD835\uDC2B \uD835\uDC12\uD835\uDC2D\uD835\uDC1A\uD835\uDC2D\uD835\uDC2C _\u0332_\u0332_\u0332_\u0332__\u0332_\u0332_\u0332_\u0332_\u0332_\u0332_\u0332");
		monsterStatsLabel.setBounds(-19, 195, 363, 27);
		frmMonsterStats.getContentPane().add(monsterStatsLabel);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setForeground(new Color(0, 0, 0));
		textPane_1.setBackground(new Color(204, 255, 0));
		textPane_1.setText("_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307_\u0307");
		textPane_1.setBounds(-19, 385, 297, 19);
		frmMonsterStats.getContentPane().add(textPane_1);
	
	}
}
