import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;

public class GoodMorningGUI {

	private JFrame frmGoodMorning;
	private Player player;
	
	public String morningMessage()
	{
		String line1 = "Good Morning %s!\n".formatted(this.player);
		String line2 = "All monster have healed up over night!\n";
		String line3 = "Shop was updated! Check out the new items!\n";
		String line4 = "New battles were also generated!\n";
		return (line1 + line2 + line3 + line4);
	}
	
	
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
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the application.
	 */
	public GoodMorningGUI(Player player) {
		this.player = player;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGoodMorning = new JFrame();
		frmGoodMorning.setResizable(false);
		frmGoodMorning.setTitle("Good Morning!");
		frmGoodMorning.setBounds(100, 100, 480, 412);
		frmGoodMorning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGoodMorning.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\UC 2022 Semester 1\\SENG201 - Software Engineering I\\Project\\SENG201-Project-Monster-Fighter\\src\\Images\\Good Morning.png"));
		lblNewLabel.setBounds(0, 0, 464, 281);
		frmGoodMorning.getContentPane().add(lblNewLabel);
		
		JTextPane txtpnYoyo = new JTextPane();
		txtpnYoyo.setEditable(false);
		txtpnYoyo.setBackground(Color.YELLOW);
		txtpnYoyo.setText(morningMessage());
		txtpnYoyo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnYoyo.setBounds(88, 281, 301, 92);
		frmGoodMorning.getContentPane().add(txtpnYoyo);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 204, 102));
		panel.setBounds(0, 281, 88, 92);
		frmGoodMorning.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 204, 102));
		panel_1.setBounds(389, 281, 88, 92);
		frmGoodMorning.getContentPane().add(panel_1);
	}
}
