package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import game.AudioPlayer;
import game.Player;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class IntroGUI {

	private JFrame frmMonsterFightV;
	private AudioPlayer backgroundMusic = new AudioPlayer();
	private AudioPlayer buttonSound = new AudioPlayer();
	
	
	
	public void openAvatarSelection()
	{
		this.backgroundMusic.stopSound();
		this.frmMonsterFightV.dispose();
		this.buttonSound.playSoundOnce("PlayButton.wav");
		Player player = new Player();
		ChooseAvatarGUI.launchChooseAvatar(player);
	}

	
	public static void launchIntro()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntroGUI window = new IntroGUI();
					window.frmMonsterFightV.setVisible(true);
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
		launchIntro();
	}

	/**
	 * Create the application.
	 */
	public IntroGUI() {
		
		initialize();
		this.backgroundMusic.playSoundLoop("IntroMusic.wav");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMonsterFightV = new JFrame();
		frmMonsterFightV.setTitle("Monster Fight (Version 1.0)");
		frmMonsterFightV.setBounds(100, 100, 1200, 720);
		frmMonsterFightV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMonsterFightV.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 1264, 692);
		frmMonsterFightV.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnMonsterFight = new JTextPane();
		txtpnMonsterFight.setOpaque(false);
		txtpnMonsterFight.setForeground(Color.YELLOW);
		txtpnMonsterFight.setEditable(false);
		txtpnMonsterFight.setBorder(new MatteBorder(6, 6, 6, 6, (Color) Color.RED));
		txtpnMonsterFight.setBackground(Color.BLACK);
		txtpnMonsterFight.setFont(new Font("Tahoma", Font.BOLD, 99));
		txtpnMonsterFight.setText("Monster \r\n Fighter ");
		txtpnMonsterFight.setBounds(355, 81, 433, 268);
		panel.add(txtpnMonsterFight);
		
		JTextPane txtpnUniversityOfCanterbury = new JTextPane();
		txtpnUniversityOfCanterbury.setOpaque(false);
		txtpnUniversityOfCanterbury.setBackground(Color.BLACK);
		txtpnUniversityOfCanterbury.setForeground(Color.YELLOW);
		txtpnUniversityOfCanterbury.setEditable(false);
		txtpnUniversityOfCanterbury.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.RED));
		txtpnUniversityOfCanterbury.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnUniversityOfCanterbury.setText("University of Canterbury\r\nSENG201 Project - Monster Fighter (Game)\r\nVersion: 1.0\r\nAuthors: Haider (msa280) & Jakib (jis48)\r\nDate Created: 11/05/22\r\n");
		txtpnUniversityOfCanterbury.setBounds(436, 360, 275, 80);
		panel.add(txtpnUniversityOfCanterbury);
		
		JButton play = new JButton("PLAY");
		play.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				play.setOpaque(true);
				play.setBackground(Color.GREEN);
				play.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				play.setOpaque(false);
				play.setForeground(Color.GREEN);
			}
		});
		play.setOpaque(false);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openAvatarSelection();
			}
		});
		play.setForeground(Color.GREEN);
		play.setBorder(new MatteBorder(10, 10, 10, 10, (Color) Color.GREEN));
		play.setBackground(Color.BLACK);
		play.setFont(new Font("Tahoma", Font.BOLD, 60));
		play.setBounds(436, 545, 275, 80);
		panel.add(play);
		
		JLabel image2 = new JLabel("New label");
		image2.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Images\\IntroBackground.gif"));
		image2.setBounds(0, 0, 1264, 681);
		panel.add(image2);
	}
}