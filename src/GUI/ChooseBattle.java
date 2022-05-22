package GUI;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextPane;

import game.AudioPlayer;
import game.Battle;
import game.Enemy;
import game.Player;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

public class ChooseBattle {

	/*
	 * The frame that the GUI is displayed on.
	 */
	private JFrame frmEnemySelection;
	/*
	 * The list of enemies the player can battle.
	 */
	private ArrayList<Enemy> battles;

	/*
	 * A text pane that updates the player.
	 */
	private JTextPane updateArea = new JTextPane();

	/*
	 * The player.
	 */
	private Player player;
	/* 
	 * A button.
	 */
	private JButton b1;
	/*
	 * The button audio.
	 */
	private AudioPlayer buttonAudio = new AudioPlayer();
	
	/*
	 * Starts a fight by launching the battle screen GUI.
	 * 
	 * @param buttonIndex The enemy to battle.
	 */
	public void startFight(int buttonIndex)
	{
		if (this.player.getPlayersTeamLength() == 0)
		{
			buttonAudio.playSoundOnce("error.wav");
			updateArea.setText("You do not have any monsters to fight with!");
			return;
		}
		else if (this.player.canFight() == false)
		{
			buttonAudio.playSoundOnce("error.wav");
			updateArea.setText("All your monsters are fainted!");
			return;
		}
		else
		{
			buttonAudio.playSoundOnce("buttonA.wav");
			this.frmEnemySelection.dispose();
			Enemy enemy = this.battles.get(buttonIndex);
			this.player.getBattle().setEnemy(enemy);
			
			BattleScreen.launchBattleScreen(this.player, enemy, "");
		}
		
		
	}
	
	public void goBack()
	{
		buttonAudio.playSoundOnce("buttonA.wav");
		this.frmEnemySelection.dispose();
		MainMenu.launchMainMenu(this.player);
	}
	
	/*
	 * Launches the choose battle GUI.
	 * 
	 * @param player The player.
	 */
	public static void launchChooseBattle(Player player)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseBattle window = new ChooseBattle(player);
					window.frmEnemySelection.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Player player = new Player();
		Battle battle = new Battle();
		battle.generateBattles();
		player.setBattle(battle);
		ChooseBattle.launchChooseBattle(player);
	}

	/*
	 * Gets the battle instance.
	 * 
	 * @param player The player.
	 */
	public ChooseBattle(Player player) {
		this.player = player;
		this.battles = this.player.getBattle().getBattles();
		initialize();
	}

	/*
	 * initialize the contents of the frame.
	 */
	private void initialize() {
		frmEnemySelection = new JFrame();
		frmEnemySelection.setTitle("Enemy Selection");
		frmEnemySelection.setResizable(false);
		frmEnemySelection.getContentPane().setBackground(Color.DARK_GRAY);
		frmEnemySelection.setBounds(100, 100, 1280, 553);
		frmEnemySelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEnemySelection.getContentPane().setLayout(null);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmEnemySelection.setLocation(dim.width/2-frmEnemySelection.getSize().width/2, dim.height/2-frmEnemySelection.getSize().height/2);
		
		
		JTextPane txtpnChooseAnEnemy = new JTextPane();
		txtpnChooseAnEnemy.setBorder(null);
		txtpnChooseAnEnemy.setForeground(Color.WHITE);
		txtpnChooseAnEnemy.setBackground(Color.DARK_GRAY);
		txtpnChooseAnEnemy.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpnChooseAnEnemy.setText("Choose an enemy to battle:");
		txtpnChooseAnEnemy.setBounds(10, 11, 232, 23);
		frmEnemySelection.getContentPane().add(txtpnChooseAnEnemy);
		
		JLabel eimage1 = new JLabel("");
		eimage1.setBounds(10, 73, 200, 212);
		frmEnemySelection.getContentPane().add(eimage1);
		
		JLabel eimage2 = new JLabel("");
		eimage2.setBounds(257, 73, 200, 212);
		frmEnemySelection.getContentPane().add(eimage2);
		
		JLabel eimage3 = new JLabel("");
		eimage3.setBounds(513, 73, 200, 212);
		frmEnemySelection.getContentPane().add(eimage3);
		
		JLabel eimage4 = new JLabel("");
		eimage4.setBounds(768, 73, 200, 212);
		frmEnemySelection.getContentPane().add(eimage4);
		
		JLabel eimage5 = new JLabel("");
		eimage5.setBounds(1033, 73, 200, 212);
		frmEnemySelection.getContentPane().add(eimage5);
		
		
		JLabel eimages[] = new JLabel[5];
		eimages[0] = eimage1;
		eimages[1] = eimage2;
		eimages[2] = eimage3;
		eimages[3] = eimage4;
		eimages[4] = eimage5;
		
		JTextPane name1 = new JTextPane();
		name1.setFont(new Font("Tahoma", Font.BOLD, 18));
		name1.setForeground(Color.WHITE);
		name1.setBackground(Color.DARK_GRAY);
		name1.setBounds(45, 296, 125, 28);
		frmEnemySelection.getContentPane().add(name1);
		
		JTextPane name2 = new JTextPane();
		name2.setForeground(Color.WHITE);
		name2.setFont(new Font("Tahoma", Font.BOLD, 18));
		name2.setBackground(Color.DARK_GRAY);
		name2.setBounds(296, 296, 125, 28);
		frmEnemySelection.getContentPane().add(name2);
		
		JTextPane name3 = new JTextPane();
		name3.setForeground(Color.WHITE);
		name3.setFont(new Font("Tahoma", Font.BOLD, 18));
		name3.setBackground(Color.DARK_GRAY);
		name3.setBounds(547, 296, 125, 28);
		frmEnemySelection.getContentPane().add(name3);
		
		JTextPane name4 = new JTextPane();
		name4.setForeground(Color.WHITE);
		name4.setFont(new Font("Tahoma", Font.BOLD, 18));
		name4.setBackground(Color.DARK_GRAY);
		name4.setBounds(805, 296, 125, 28);
		frmEnemySelection.getContentPane().add(name4);
		
		JTextPane name5 = new JTextPane();
		name5.setForeground(Color.WHITE);
		name5.setFont(new Font("Tahoma", Font.BOLD, 18));
		name5.setBackground(Color.DARK_GRAY);
		name5.setBounds(1062, 296, 125, 28);
		frmEnemySelection.getContentPane().add(name5);
		
		JTextPane names[] = new JTextPane[5];
		names[0] = name1;
		names[1] = name2;
		names[2] = name3;
		names[3] = name4;
		names[4] = name5;
		
		b1 = new JButton("");
		b1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				b1.setFont(new Font("SansSerif", Font.BOLD, 20));
				b1.setForeground(Color.BLACK);
				b1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLACK));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				b1.setFont(new Font("SansSerif", Font.BOLD, 16));
				b1.setForeground(Color.WHITE);
				b1.setBorder(null);
			}
		});
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startFight(0);
			}
		});
		b1.setForeground(new Color(255, 255, 255));
		b1.setBackground(new Color(255, 51, 51));
		b1.setFont(new Font("SansSerif", Font.BOLD, 16));
		b1.setBounds(45, 335, 125, 33);
		frmEnemySelection.getContentPane().add(b1);
		
		JButton goBack = new JButton("Go Back");
		goBack.setForeground(Color.YELLOW);
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
		
		goBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				goBack.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
				buttonAudio.playSoundOnce("buttonHover.wav");
				goBack.setFont(new Font("Tahoma", Font.BOLD, 16));
				goBack.setBackground(Color.GREEN);
				goBack.setForeground(Color.BLACK);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				goBack.setBorder(null);
				goBack.setFont(new Font("Tahoma", Font.BOLD, 14));
				goBack.setBackground(Color.BLACK);
				goBack.setForeground(Color.YELLOW);
			}
		});
		goBack.setBackground(Color.BLACK);
		goBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		goBack.setBounds(27, 457, 118, 33);
		frmEnemySelection.getContentPane().add(goBack);
		
		JButton b2 = new JButton("");
		b2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				b2.setFont(new Font("SansSerif", Font.BOLD, 20));
				buttonAudio.playSoundOnce("buttonHover.wav");
				b2.setForeground(Color.BLACK);
				b2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLACK));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				b2.setFont(new Font("SansSerif", Font.BOLD, 16));
				b2.setForeground(Color.WHITE);
				b2.setBorder(null);
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startFight(1);
			}
		});
		b2.setForeground(Color.WHITE);
		b2.setFont(new Font("SansSerif", Font.BOLD, 16));
		b2.setBackground(new Color(255, 51, 51));
		b2.setBounds(296, 335, 125, 33);
		frmEnemySelection.getContentPane().add(b2);
		
		JButton b3 = new JButton("");
		b3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				b3.setFont(new Font("SansSerif", Font.BOLD, 20));
				b3.setForeground(Color.BLACK);
				buttonAudio.playSoundOnce("buttonHover.wav");
				b3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLACK));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				b3.setFont(new Font("SansSerif", Font.BOLD, 16));
				b3.setForeground(Color.WHITE);
				b3.setBorder(null);
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startFight(2);
			}
		});
		b3.setForeground(Color.WHITE);
		b3.setFont(new Font("SansSerif", Font.BOLD, 16));
		b3.setBackground(new Color(255, 51, 51));
		b3.setBounds(547, 335, 125, 33);
		frmEnemySelection.getContentPane().add(b3);
		
		JButton b4 = new JButton("");
		b4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				b4.setFont(new Font("SansSerif", Font.BOLD, 20));
				b4.setForeground(Color.BLACK);
				buttonAudio.playSoundOnce("buttonHover.wav");
				b4.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLACK));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				b4.setFont(new Font("SansSerif", Font.BOLD, 16));
				b4.setForeground(Color.WHITE);
				b4.setBorder(null);
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startFight(3);
			}
		});
		b4.setForeground(Color.WHITE);
		b4.setFont(new Font("SansSerif", Font.BOLD, 16));
		b4.setBackground(new Color(255, 51, 51));
		b4.setBounds(805, 335, 125, 33);
		frmEnemySelection.getContentPane().add(b4);
		
		JButton b5 = new JButton("");
		b5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				b5.setFont(new Font("SansSerif", Font.BOLD, 20));
				b5.setForeground(Color.BLACK);
				buttonAudio.playSoundOnce("buttonHover.wav");
				b5.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLACK));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				b5.setFont(new Font("SansSerif", Font.BOLD, 16));
				b5.setForeground(Color.WHITE);
				b5.setBorder(null);
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startFight(4);
			}
		});
		b5.setForeground(Color.WHITE);
		b5.setFont(new Font("SansSerif", Font.BOLD, 16));
		b5.setBackground(new Color(255, 51, 51));
		b5.setBounds(1062, 335, 125, 33);
		frmEnemySelection.getContentPane().add(b5);
		
		JButton buttons[] = new JButton[5];
		buttons[0] = b1;
		buttons[1] = b2;
		buttons[2] = b3;
		buttons[3] = b4;
		buttons[4] = b5;
		updateArea.setForeground(Color.RED);
		
		
		updateArea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.MAGENTA));
		updateArea.setEditable(false);
		updateArea.setBackground(Color.BLACK);
		updateArea.setFont(new Font("Tahoma", Font.BOLD, 16));
		updateArea.setBounds(437, 467, 430, 23);
		frmEnemySelection.getContentPane().add(updateArea);
		
		
		int i = 0;
		
		while (i < this.player.getBattle().getBattles().size())
		{
			Enemy enemy = this.player.getBattle().getBattles().get(i);
			
			if (enemy.getAlreadyFought() == true)
			{
				eimages[i].setIcon(new ImageIcon(enemy.getEnemyImage()));
				names[i].setText(enemy.getEnemyName());
				buttons[i].setVisible(false);
				i += 1;	
			}
			else
			{
				eimages[i].setIcon(new ImageIcon(enemy.getEnemyImage()));
				names[i].setText(enemy.getEnemyName());
				buttons[i].setText("Battle!");
				i += 1;
			}	
		}	
		while (i < 5)
		{
			eimages[i].setVisible(false);
			names[i].setVisible(false);
			buttons[i].setVisible(false);
			i += 1;
		}		
	}
}
