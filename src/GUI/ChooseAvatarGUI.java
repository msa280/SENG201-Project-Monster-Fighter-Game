package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

import game.AudioPlayer;
import game.Game;
import game.Player;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class ChooseAvatarGUI {

	private JFrame frmChooseAvatar;
	private JButton nextButton = new JButton("Next");
	private JToggleButton[] avatarButtons = new JToggleButton[10];
	private Player player;
	private String[] allAvatars = new String[10];
	private AudioPlayer backgroundMusic = new AudioPlayer();
	private AudioPlayer buttonSound = new AudioPlayer();
	
	
	public void generateAvatars()
	{
		this.allAvatars[0] = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Player Avatars\\Avatar1.png";
		this.allAvatars[1] = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Player Avatars\\Avatar2.png";
		this.allAvatars[2] = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Player Avatars\\Avatar3.png";
		this.allAvatars[3] = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Player Avatars\\Avatar4.png";
		this.allAvatars[4] = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Player Avatars\\Avatar5.png";
		this.allAvatars[5] = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Player Avatars\\Avatar6.png";
		this.allAvatars[6] = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Player Avatars\\Avatar7.png";
		this.allAvatars[7] = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Player Avatars\\Avatar8.png";
		this.allAvatars[8] = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Player Avatars\\Avatar9.png";
		this.allAvatars[9] = "C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Player Avatars\\Avatar10.png";
	}
	
	
	
	public void selectAvatar(int buttonIndex)
	{
		this.buttonSound.playSoundOnce("buttonA.wav");
		
		if (this.avatarButtons[buttonIndex-1].isSelected())
		{
			String selectedAvatar = this.allAvatars[buttonIndex-1];
			this.player.setSelectedAvatar(selectedAvatar);
			this.nextButton.setVisible(true);
		}
		else
		{
			this.player.setSelectedAvatar(null);
			this.nextButton.setVisible(false);
		}
		
		int i = 0;
		while (i < this.avatarButtons.length)
		{
			if (i != buttonIndex-1)
			{
				this.avatarButtons[i].setSelected(false);
			}
			i += 1;
		}
	}
	
	
	
	public void openGameSetup()
	{
		this.frmChooseAvatar.dispose();
		this.buttonSound.playSoundOnce("nextButton.wav");
		Game game = new Game();
		SetupMenu.launchSetupMenu(this.player, game);
	}
	
	
	
	public static void launchChooseAvatar(Player player)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseAvatarGUI window = new ChooseAvatarGUI(player);
					window.frmChooseAvatar.setVisible(true);
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
		ChooseAvatarGUI.launchChooseAvatar(player);
	}
	

	/**
	 * Creates the application.
	 */
	public ChooseAvatarGUI(Player player) {
		this.player = player;
		generateAvatars();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		
		
		frmChooseAvatar = new JFrame();
		frmChooseAvatar.setResizable(false);
		frmChooseAvatar.getContentPane().setBackground(Color.DARK_GRAY);
		frmChooseAvatar.setTitle("Choose Avatar");
		frmChooseAvatar.setBounds(100, 100, 1280, 720);
		frmChooseAvatar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChooseAvatar.getContentPane().setLayout(null);
		
		JTextPane txtpnPickYourAvatar = new JTextPane();
		txtpnPickYourAvatar.setForeground(Color.WHITE);
		txtpnPickYourAvatar.setEditable(false);
		txtpnPickYourAvatar.setBackground(Color.DARK_GRAY);
		txtpnPickYourAvatar.setText("Pick your avatar:");
		txtpnPickYourAvatar.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtpnPickYourAvatar.setBounds(21, 22, 161, 30);
		frmChooseAvatar.getContentPane().add(txtpnPickYourAvatar);
		

		
		JToggleButton avatar1 = new JToggleButton("");
		avatar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAvatar(1);
			}
		});
		avatar1.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		avatar1.setBackground(Color.BLACK);
		avatar1.setForeground(new Color(0, 0, 0));
		avatar1.setBounds(21, 87, 200, 212);
		frmChooseAvatar.getContentPane().add(avatar1);
		
		
		
		JToggleButton avatar2 = new JToggleButton("");
		avatar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAvatar(2);
			}
		});
		avatar2.setForeground(Color.BLACK);
		avatar2.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		avatar2.setBackground(Color.BLACK);
		avatar2.setBounds(275, 87, 200, 212);
		frmChooseAvatar.getContentPane().add(avatar2);
		
		JToggleButton avatar3 = new JToggleButton("");
		avatar3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAvatar(3);
			}
		});
		avatar3.setForeground(Color.BLACK);
		avatar3.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		avatar3.setBackground(Color.BLACK);
		avatar3.setBounds(533, 87, 200, 212);
		frmChooseAvatar.getContentPane().add(avatar3);
		
		JToggleButton avatar4 = new JToggleButton("");
		avatar4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAvatar(4);
			}
		});
		avatar4.setForeground(Color.BLACK);
		avatar4.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		avatar4.setBackground(Color.BLACK);
		avatar4.setBounds(783, 87, 200, 212);
		frmChooseAvatar.getContentPane().add(avatar4);
		
		JToggleButton avatar5 = new JToggleButton("");
		avatar5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAvatar(5);
			}
		});
		avatar5.setForeground(Color.BLACK);
		avatar5.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		avatar5.setBackground(Color.BLACK);
		avatar5.setBounds(1034, 87, 200, 212);
		frmChooseAvatar.getContentPane().add(avatar5);
		
		JToggleButton avatar6 = new JToggleButton("");
		avatar6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAvatar(6);
			}
		});
		avatar6.setForeground(Color.BLACK);
		avatar6.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		avatar6.setBackground(Color.BLACK);
		avatar6.setBounds(21, 334, 200, 212);
		frmChooseAvatar.getContentPane().add(avatar6);
		
		JToggleButton avatar7 = new JToggleButton("");
		avatar7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAvatar(7);
			}
		});
		avatar7.setForeground(Color.BLACK);
		avatar7.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		avatar7.setBackground(Color.BLACK);
		avatar7.setBounds(275, 334, 200, 212);
		frmChooseAvatar.getContentPane().add(avatar7);
		
		JToggleButton avatar8 = new JToggleButton("");
		avatar8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAvatar(8);
			}
		});
		avatar8.setForeground(Color.BLACK);
		avatar8.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		avatar8.setBackground(Color.BLACK);
		avatar8.setBounds(533, 334, 200, 212);
		frmChooseAvatar.getContentPane().add(avatar8);
		
		JToggleButton avatar9 = new JToggleButton("");
		avatar9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAvatar(9);
			}
		});
		avatar9.setForeground(Color.BLACK);
		avatar9.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		avatar9.setBackground(Color.BLACK);
		avatar9.setBounds(783, 334, 200, 212);
		frmChooseAvatar.getContentPane().add(avatar9);
		
		JToggleButton avatar10 = new JToggleButton("");
		avatar10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAvatar(10);
			}
		});
		avatar10.setForeground(Color.BLACK);
		avatar10.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		avatar10.setBackground(Color.BLACK);
		avatar10.setBounds(1034, 334, 200, 212);
		frmChooseAvatar.getContentPane().add(avatar10);
		
		
		this.avatarButtons[0] = avatar1;
		this.avatarButtons[1] = avatar2;
		this.avatarButtons[2] = avatar3;
		this.avatarButtons[3] = avatar4;
		this.avatarButtons[4] = avatar5;
		this.avatarButtons[5] = avatar6;
		this.avatarButtons[6] = avatar7;
		this.avatarButtons[7] = avatar8;
		this.avatarButtons[8] = avatar9;
		this.avatarButtons[9] = avatar10;
		
		
		int i = 0;
		while (i < avatarButtons.length)
		{
			avatarButtons[i].setIcon(new ImageIcon(this.allAvatars[i]));
			i += 1;
		}
		
		
		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openGameSetup();
			}
		});
		nextButton.setVisible(false);
		nextButton.setForeground(Color.WHITE);
		nextButton.setBackground(SystemColor.desktop);
		nextButton.setFont(new Font("Tahoma", Font.BOLD, 26));
		nextButton.setBounds(569, 601, 140, 49);
		frmChooseAvatar.getContentPane().add(nextButton);
	}
}
