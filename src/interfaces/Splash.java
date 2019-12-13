package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Splash extends JFrame implements Runnable {

	Thread splash = new Thread();
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Splash() {
		// frame = new Splash();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 668);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setUndecorated(true);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(Splash.class.getResource("/interfaces/logo.jpg")));
		contentPane.add(label, BorderLayout.CENTER);
	}

	@Override
	public void run() {
		try {
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			splash.sleep(4000);
			this.dispose();
			new Login();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
