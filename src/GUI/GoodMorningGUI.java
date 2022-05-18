package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

import game.AudioPlayer;
import game.Player;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * Deals with sleep and wishes the player good morning.
 */
public class GoodMorningGUI {
	
	/*
	 * The frame the GUI is displayed on.
	 */
	private JFrame frmGoodMorning;
	/*
	 * The player.
	 */
	private Player player;
	private AudioPlayer buttonAudio = new AudioPlayer();
	
	

	public void okay()
	{
		buttonAudio.playSoundOnce("buttonA.wav");
		this.frmGoodMorning.dispose();
	}
	
	/*
	 * Launches the good morning GUI.
	 * 
	 * @param player The player.
	 */
	public static void launchGoodMorning(Player player)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodMorningGUI window = new GoodMorningGUI(player);
					window.frmGoodMorning.setVisible(true);
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
		GoodMorningGUI.launchGoodMorning(player);
		
	}

	/*
	 * The good morning GUI.
	 * 
	 * @param player The player.
	 */
	public GoodMorningGUI(Player player) {
		this.player = player;
		initialize();
	}

	/*
	 * Initializes the contents of the frame.
	 */
	private void initialize() {
		frmGoodMorning = new JFrame();
		frmGoodMorning.setResizable(false);
		frmGoodMorning.setTitle("Good Morning!");
		frmGoodMorning.setBounds(100, 100, 480, 464);
		frmGoodMorning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGoodMorning.getContentPane().setLayout(null);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmGoodMorning.setLocation(dim.width/2-frmGoodMorning.getSize().width/2, dim.height/2-frmGoodMorning.getSize().height/2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Images\\Good Morning.png"));
		lblNewLabel.setBounds(0, 0, 464, 281);
		frmGoodMorning.getContentPane().add(lblNewLabel);
		
		JTextPane txtpnYoyo = new JTextPane();
		txtpnYoyo.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtpnYoyo.setEditable(false);
		txtpnYoyo.setBackground(Color.YELLOW);
		txtpnYoyo.setText(this.player.getLastUpdate());
		txtpnYoyo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnYoyo.setBounds(0, 281, 464, 110);
		frmGoodMorning.getContentPane().add(txtpnYoyo);
		
		JButton btnNewButton = new JButton("Okay");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okay();
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				btnNewButton.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(0, 0, 0)));
				btnNewButton.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBorder(null);
				btnNewButton.setBackground(Color.YELLOW);
			}
		});
		btnNewButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setBounds(0, 390, 464, 35);
		frmGoodMorning.getContentPane().add(btnNewButton);
	}
}
