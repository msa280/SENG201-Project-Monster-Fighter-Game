import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ChooseBattleGUI {

	private JFrame frame;
	private Player player;
	private JButton b1;
	
	
	public static void launchChooseBattle(Player player)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseBattleGUI window = new ChooseBattleGUI(player);
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
	public static void main(String[] args) {
		Player player = new Player();
		Battle battle = new Battle();
		battle.generateBattles();
		player.setBattle(battle);
		ChooseBattleGUI.launchChooseBattle(player);
	}

	/**
	 * Create the application.
	 */
	public ChooseBattleGUI(Player player) {
		this.player = player;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 1280, 553);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnChooseAnEnemy = new JTextPane();
		txtpnChooseAnEnemy.setForeground(Color.GREEN);
		txtpnChooseAnEnemy.setBackground(Color.BLACK);
		txtpnChooseAnEnemy.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnChooseAnEnemy.setText("Choose an enemy to battle:");
		txtpnChooseAnEnemy.setBounds(10, 11, 205, 23);
		frame.getContentPane().add(txtpnChooseAnEnemy);
		
		JLabel eimage1 = new JLabel("");
		eimage1.setBounds(10, 73, 200, 212);
		frame.getContentPane().add(eimage1);
		
		JLabel eimage2 = new JLabel("");
		eimage2.setBounds(257, 73, 200, 212);
		frame.getContentPane().add(eimage2);
		
		JLabel eimage3 = new JLabel("");
		eimage3.setBounds(513, 73, 200, 212);
		frame.getContentPane().add(eimage3);
		
		JLabel eimage4 = new JLabel("");
		eimage4.setBounds(768, 73, 200, 212);
		frame.getContentPane().add(eimage4);
		
		JLabel eimage5 = new JLabel("");
		eimage5.setBounds(1033, 73, 200, 212);
		frame.getContentPane().add(eimage5);
		
		
		JLabel eimages[] = new JLabel[5];
		eimages[0] = eimage1;
		eimages[1] = eimage2;
		eimages[2] = eimage3;
		eimages[3] = eimage4;
		eimages[4] = eimage5;
		
		JTextPane name1 = new JTextPane();
		name1.setFont(new Font("Tahoma", Font.BOLD, 18));
		name1.setForeground(Color.BLUE);
		name1.setBackground(Color.BLACK);
		name1.setBounds(45, 296, 125, 28);
		frame.getContentPane().add(name1);
		
		JTextPane name2 = new JTextPane();
		name2.setForeground(Color.GREEN);
		name2.setFont(new Font("Tahoma", Font.BOLD, 18));
		name2.setBackground(Color.BLACK);
		name2.setBounds(296, 296, 125, 28);
		frame.getContentPane().add(name2);
		
		JTextPane name3 = new JTextPane();
		name3.setForeground(Color.YELLOW);
		name3.setFont(new Font("Tahoma", Font.BOLD, 18));
		name3.setBackground(Color.BLACK);
		name3.setBounds(547, 296, 125, 28);
		frame.getContentPane().add(name3);
		
		JTextPane name4 = new JTextPane();
		name4.setForeground(Color.RED);
		name4.setFont(new Font("Tahoma", Font.BOLD, 18));
		name4.setBackground(Color.BLACK);
		name4.setBounds(805, 296, 125, 28);
		frame.getContentPane().add(name4);
		
		JTextPane name5 = new JTextPane();
		name5.setForeground(Color.CYAN);
		name5.setFont(new Font("Tahoma", Font.BOLD, 18));
		name5.setBackground(Color.BLACK);
		name5.setBounds(1062, 296, 125, 28);
		frame.getContentPane().add(name5);
		
		JTextPane names[] = new JTextPane[5];
		names[0] = name1;
		names[1] = name2;
		names[2] = name3;
		names[3] = name4;
		names[4] = name5;
		
		b1 = new JButton("");
		b1.setForeground(new Color(255, 255, 255));
		b1.setBackground(new Color(255, 51, 51));
		b1.setFont(new Font("SansSerif", Font.BOLD, 16));
		b1.setBounds(45, 335, 125, 33);
		frame.getContentPane().add(b1);
		
		JButton btnNewButton_1 = new JButton("Go Back");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(27, 467, 108, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton b2 = new JButton("");
		b2.setForeground(Color.WHITE);
		b2.setFont(new Font("SansSerif", Font.BOLD, 16));
		b2.setBackground(new Color(255, 51, 51));
		b2.setBounds(296, 335, 125, 33);
		frame.getContentPane().add(b2);
		
		JButton b3 = new JButton("");
		b3.setForeground(Color.WHITE);
		b3.setFont(new Font("SansSerif", Font.BOLD, 16));
		b3.setBackground(new Color(255, 51, 51));
		b3.setBounds(547, 335, 125, 33);
		frame.getContentPane().add(b3);
		
		JButton b4 = new JButton("");
		b4.setForeground(Color.WHITE);
		b4.setFont(new Font("SansSerif", Font.BOLD, 16));
		b4.setBackground(new Color(255, 51, 51));
		b4.setBounds(805, 335, 125, 33);
		frame.getContentPane().add(b4);
		
		JButton b5 = new JButton("");
		b5.setForeground(Color.WHITE);
		b5.setFont(new Font("SansSerif", Font.BOLD, 16));
		b5.setBackground(new Color(255, 51, 51));
		b5.setBounds(1062, 335, 125, 33);
		frame.getContentPane().add(b5);
		
		JButton buttons[] = new JButton[5];
		buttons[0] = b1;
		buttons[1] = b2;
		buttons[2] = b3;
		buttons[3] = b4;
		buttons[4] = b5;
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		textPane.setBounds(423, 457, 453, 33);
		frame.getContentPane().add(textPane);
		
		
		int i = 0;
		
		while (i < this.player.getBattle().getBattles().size())
		{
			Enemy enemy = this.player.getBattle().getBattles().get(i);
			
			eimages[i].setIcon(new ImageIcon(enemy.getEnemyImage()));
			names[i].setText(enemy.getEnemyName());
			buttons[i].setText("Battle!");
			i += 1;
		}
		
		
		while (i < 5)
		{
			eimages[i].hide();
			names[i].hide();
			buttons[i].hide();
			i += 1;
		}
		
		
		
	
		
		
		
		
	}
}
