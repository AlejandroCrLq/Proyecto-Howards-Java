/*
 * Date:12/12/2019
 * 
 * Author:Jorge Rico Vivas
 * 
 * Description: 
 * 
 * Version: 1.0
 */
package interfaces;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

public class FTPWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtFileName;
	private JButton btnOpenEmail;
	private JLabel lblFilePath;
	private JButton btnCrearCarpeta;
	private JButton btnAbout;
	private JButton btnSubir;
	private JButton btnBorrar;
	private JButton btnRefrescar;
	private JButton btnSubirCarpeta;
	private JList listFileMovements;
	private JLabel lblDirectory;
	private JList listFiles;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public FTPWindow() {
		setBounds(100, 100, 600, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("");
		menuBar.add(menu);

		btnOpenEmail = new JButton("Correo");
		menuBar.add(btnOpenEmail);

		btnAbout = new JButton("Acerca de");
		menuBar.add(btnAbout);
		btnRefrescar = new JButton("Refrescar");
		menuBar.add(btnRefrescar);

		JButton btnHistory = new JButton("Historial");
		menuBar.add(btnHistory);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		lblDirectory = new JLabel();
		panel.add(lblDirectory, BorderLayout.NORTH);

		listFiles = null;  //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		JScrollPane scrollPane = new JScrollPane(listFiles);
		panel.add(scrollPane, BorderLayout.CENTER);

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

		btnSubirCarpeta = new JButton("Descargar");
		panel_2.add(btnSubirCarpeta);

		btnCrearCarpeta = new JButton("Crear carpeta");
		panel_2.add(btnCrearCarpeta);

//		listFileMovements = new JList();
//		panel_1.add(listFileMovements, BorderLayout.CENTER);		
	}

	public void setListFiles(JList listFiles) {
		this.listFiles = listFiles;
	}

	public static File[] combine(File[] listaFicheros1, File[] listaFicheros2) {
		int length = listaFicheros1.length + listaFicheros2.length;
		File[] result = new File[length];
		System.arraycopy(listaFicheros1, 0, result, 0, listaFicheros1.length);
		System.arraycopy(listaFicheros2, 0, result, listaFicheros1.length, listaFicheros2.length);
		return result;
	}

	public static JList<File> cargarDatosJList(File archivoACargar) {
		JList<File> list = new JList<File>();
		recargarDatosJList(list, archivoACargar);
		list.setCellRenderer(new RenderizacionDeFicheros());
		return list;
	}

	public static void recargarDatosJList(JList<File> list, File directorioACargar) {
		File directorioAnterior = directorioACargar.getParentFile();
		File[] listFiles;
		if (directorioAnterior != null) {
			listFiles = combine(new File[] { directorioAnterior }, directorioACargar.listFiles());
		} else {
			listFiles = directorioACargar.listFiles();
		}
		list.setListData(listFiles);
	}

	/*
	 * Getter and Setter methods
	 */
	public JButton getBtnOpenEmail() {
		return btnOpenEmail;
	}

	public JTextField getTxtFileName() {
		return txtFileName;
	}

	public JLabel getLblFilePath() {
		return lblFilePath;
	}

	public JButton getBtnCrearCarpeta() {
		return btnCrearCarpeta;
	}

	public JButton getBtnAbout() {
		return btnAbout;
	}

	public JButton getBtnSubir() {
		return btnSubir;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnSubirCarpeta() {
		return btnSubirCarpeta;
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JList getListFileMovements() {
		return listFileMovements;
	}

	public JLabel getLblDirectory() {
		return lblDirectory;
	}

	public JList getListFiles() {
		return listFiles;
	}

	public JButton getBtnRefrescar() {
		return btnRefrescar;
	}

}
