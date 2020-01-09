package interfaces;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class Splash extends JFrame implements Runnable {

	private JPanel contentPane;
	Thread splash = new Thread();

	/**
	 * Create the frame.
	 */
	public Splash() {
		// frame = new Splash();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 668);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setUndecorated(true);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(Splash.class.getResource("/interfaces/logo.jpg")));
		contentPane.add(label, BorderLayout.CENTER);
	}

	@Override
	public void run() {
		try {
			setLocationRelativeTo(null);
			setVisible(true);
			Thread.sleep(4000);
			dispose();
			new Login();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
