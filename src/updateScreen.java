import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import java.awt.Font;

public class updateScreen {

	private JFrame frame;
	
	
	
	
	public void launchUpdateScreen(updateScreen update)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					update.frame.setVisible(true);
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
	public updateScreen(String update) {
		initialize(update);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String update) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\GGPC\\OneDrive\\Desktop\\ItemSold.gif"));
		lblNewLabel.setBounds(0, 0, 384, 185);
		frame.getContentPane().add(lblNewLabel);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.BOLD, 12));
		textPane.setForeground(Color.GREEN);
		textPane.setEditable(false);
		textPane.setBackground(Color.BLACK);
		textPane.setBounds(0, 184, 384, 55);
		frame.getContentPane().add(textPane);
		frame.setBounds(100, 100, 400, 278);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
}
