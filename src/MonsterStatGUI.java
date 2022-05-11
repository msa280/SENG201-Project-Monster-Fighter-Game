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
import java.awt.Font;

public class MonsterStatGUI {

	private JFrame frmMonsterStats;
	
	
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
	
	
	public void setMonsterImage(JLabel image, Monster monster)
	{
		String monster_name = monster.getMonsterName();
		if (monster_name == "Venomhound") {
			image.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\1.) VenomHound.gif"));
		} else if (monster_name == "Soilscreamer") {
			image.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\2.) Soilscreamer.gif"));
		} else if (monster_name == "Mornpest") {
			image.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\3.) Mornpest.gif"));
		} else if (monster_name == "Cavernfreak") {
			image.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\4.) Cavernfreak.gif"));
		} else if (monster_name == "Hollowtree") {
			image.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\5.) Hollowtree.gif"));
		} else if (monster_name == "Magmataur") {
			image.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\6.) Magmataur.gif"));
		} else {
			image.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Monsters Artwork\\noImage.png"));
		}
	}
	
	
	public void displayMonsterStats(Monster monster, JTextPane monsterName) {
		
		
		String a = "Name: %s\n".formatted(monster.pickMonsterName());
		String b = "Attack Name: %s\n".formatted(monster.getAttackName());
		String b1 = "Attack Damage: %d\n".formatted(monster.getDamage());
		String c = "Special Attack Name: %s\n".formatted(monster.getSpecialAttackName());
		String c1 = "Special Attack Damage: %s\n".formatted(monster.getSpecialDamage());
		String d = "Maximum Health: %d\n".formatted(monster.getMaxHealth());
		String d1 = "Current Health: %d\n".formatted(monster.getCurrentHealth());
		String e = "Heal Amount: %d\n".formatted(monster.getHealAmount());
		
		
		String monsterDetails = a + b + b1 + c + c1 + d + d1 + e;
		
		
		monsterName.setText(monsterDetails);
		
	}
	
	

	/**
	 * Launch the application.
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

	/**
	 * Create the application.
	 */
	public MonsterStatGUI(Monster monster) {
		initialize(monster);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Monster monster) {
		frmMonsterStats = new JFrame();
		frmMonsterStats.setResizable(false);
		frmMonsterStats.getContentPane().setForeground(new Color(0, 0, 153));
		frmMonsterStats.getContentPane().setBackground(new Color(204, 255, 0));
		frmMonsterStats.getContentPane().setLayout(null);
		frmMonsterStats.setSize(1100, 715);
	
		
		
		JLabel monsterImage = new JLabel("");
		monsterImage.setForeground(new Color(0, 0, 153));
		monsterImage.setBounds(62, 24, 150, 150);
		frmMonsterStats.getContentPane().add(monsterImage);
		frmMonsterStats.setTitle("Monster Stats");
		frmMonsterStats.setBounds(100, 100, 294, 454);
		
		
		setMonsterImage(monsterImage, monster);
		
		JTextPane monsterName = new JTextPane();
		monsterName.setForeground(new Color(0, 0, 0));
		monsterName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		monsterName.setEditable(false);
		monsterName.setBackground(new Color(204, 255, 0));
		monsterName.setBounds(10, 233, 257, 143);
		frmMonsterStats.getContentPane().add(monsterName);
		
		displayMonsterStats(monster, monsterName);
		
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
