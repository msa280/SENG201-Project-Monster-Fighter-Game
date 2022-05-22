package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.border.MatteBorder;

import game.AudioPlayer;
import game.Player;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * Ends the game.
 */
public class GameOver {
	
	/*
	 * The game over frame.
	 */
	private JFrame frmGameOver;
	/*
	 * The player.
	 */
	private Player player;
	/*
	 * The button audio.
	 */
	private AudioPlayer buttonAudio = new AudioPlayer();

	
	/*
	 * The backgroundAudio audio.
	 */
	/** private AudioPlayer backgroundAudio = new AudioPlayer(); */

	
	/*
	 * Exits the game.
	 */
	public void quitGame()
	{

		/** this.background.stopSound(); */
		this.buttonAudio.playSoundOnce("buttonA.wav");
		this.frmGameOver.dispose();
	}
	
	/*
	 * Launches the game over GUI.
	 */
	public static void launchGameOver(Player player)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOver window = new GameOver(player);
					window.frmGameOver.setVisible(true);
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
		GameOver.launchGameOver(player);
		
	}

	/*
	 * Creates the game over GUI.
	 * 
	 * @param player The player.
	 */
	public GameOver(Player player) {
		this.player = player;
		initialize();
		this.buttonAudio.playSoundOnce("GameOver.wav");

		/** this.background.playSoundLoop("credits.wav"); */

	}

	/*
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		
		frmGameOver = new JFrame();
		frmGameOver.setResizable(false);
		frmGameOver.getContentPane().setBackground(Color.BLACK);
		frmGameOver.setTitle("Game Over!");
		frmGameOver.setBounds(100, 100, 721, 512);
		frmGameOver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGameOver.getContentPane().setLayout(null);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmGameOver.setLocation(dim.width/2-frmGameOver.getSize().width/2, dim.height/2-frmGameOver.getSize().height/2);
		
		
		JTextPane txtpnGameOver = new JTextPane();
		txtpnGameOver.setBackground(Color.BLACK);
		txtpnGameOver.setBorder(null);
		txtpnGameOver.setFont(new Font("Tahoma", Font.BOLD, 99));
		txtpnGameOver.setEditable(false);
		txtpnGameOver.setForeground(Color.RED);
		txtpnGameOver.setText("Game Over!");
		txtpnGameOver.setBounds(52, 25, 591, 132);
		frmGameOver.getContentPane().add(txtpnGameOver);
		
		JTextPane gameStats = new JTextPane();
		gameStats.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.RED));
		gameStats.setForeground(Color.WHITE);
		gameStats.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		gameStats.setBackground(Color.BLACK);
		gameStats.setBounds(159, 177, 364, 197);
		gameStats.setText(this.player.getGameOverResult());
		frmGameOver.getContentPane().add(gameStats);
		
		JButton btnNewButton = new JButton("Exit Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				quitGame();
			}
		});

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAudio.playSoundOnce("buttonHover.wav");
				btnNewButton.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
				btnNewButton.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBorder(null);
				btnNewButton.setBackground(Color.WHITE);
				btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBounds(248, 400, 196, 62);
		frmGameOver.getContentPane().add(btnNewButton);
	}
}
