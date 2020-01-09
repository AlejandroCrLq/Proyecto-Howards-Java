package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class FTPWindow extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FTPWindow frame = new FTPWindow();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private JButton btnAbout;
	private JButton btnBorrar;
	private JButton btnCrearCarpeta;
	private JButton btnOpenEmail;
	private JButton btnSubir;
	private JButton btnSubirCarpeta;
	private JPanel contentPane;
	private JLabel lblDirectory;
	private JLabel lblFilePath;
	private JList listFileMovements;
	private JList listFiles;

	private JTextField txtFileName;

	/**
	 * Create the frame.
	 */
	public FTPWindow() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("");
		menuBar.add(menu);

		btnOpenEmail = new JButton("Correo");
		menuBar.add(btnOpenEmail);

		btnAbout = new JButton("Acerca de");
		menuBar.add(btnAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		lblDirectory = new JLabel();
		panel.add(lblDirectory, BorderLayout.NORTH);

		listFiles = new JList();
		panel.add(listFiles, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		lblFilePath = new JLabel("FilePath");
		panel_3.add(lblFilePath);

		txtFileName = new JTextField();
		panel_3.add(txtFileName);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		btnBorrar = new JButton("Borrar");
		panel_2.add(btnBorrar);

		btnSubir = new JButton("Subir");
		panel_2.add(btnSubir);

		btnSubirCarpeta = new JButton("Subir carpeta");
		panel_2.add(btnSubirCarpeta);

		btnCrearCarpeta = new JButton("Crear carpeta");
		panel_2.add(btnCrearCarpeta);

		listFileMovements = new JList();
		panel_1.add(listFileMovements, BorderLayout.CENTER);
	}

	public JButton getBtnAbout() {
		return btnAbout;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnCrearCarpeta() {
		return btnCrearCarpeta;
	}

	public JButton getBtnOpenEmail() {
		return btnOpenEmail;
	}

	public JButton getBtnSubir() {
		return btnSubir;
	}

	public JButton getBtnSubirCarpeta() {
		return btnSubirCarpeta;
	}

	public JLabel getLblDirectory() {
		return lblDirectory;
	}

	public JLabel getLblFilePath() {
		return lblFilePath;
	}

	public JList getListFileMovements() {
		return listFileMovements;
	}

	public JList getListFiles() {
		return listFiles;
	}

	public JTextField getTxtFileName() {
		return txtFileName;
	}
}
