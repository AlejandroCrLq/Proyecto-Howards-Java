package ftp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.apache.commons.net.ftp.FTPFile;

import Events.ListenerAbout;
import Events.ListenerChangeDirectory_LoadFiles;
import Events.ListenerDeleteFiles;
import Events.ListenerFTPRefresh;
import Events.ListenerNameChange;
import Events.ListenerOpenMail;
import general.ConnectionToDatabase;
import interfaces.FTPWindow;
import interfaces.MailWindow;
import interfaces.RenderizacionDeFicheros;

public class FTPController {
	static FTPWindow ftpWindow;
	MailWindow mailWindow;
	Users user;
	ClientFTP cliente;
	static File selected;

	public FTPController(MailWindow mailWindow, FTPWindow ftpWindow, Users user) {
		this.ftpWindow = ftpWindow;
		this.mailWindow = mailWindow;
		this.user = user;
		JList listFiles = ftpWindow.getListFiles();
		listFiles = cargarDatosJList(new File("C:\\"));
		ftpWindow.setListFiles(listFiles);
		JScrollPane scrollPane = new JScrollPane(listFiles);
		ftpWindow.getPanel().add(scrollPane, BorderLayout.CENTER);
		ftpWindow.repaint();
	}

	public void AsignarEventos() {

		ftpWindow.getBtnAbout().addActionListener(new ListenerAbout(this));
//		ftpWindow.getBtnBorrar().addActionListener(new ListenerDeleteFiles(ftpWindow.getLblFilePath(), this));
		ftpWindow.getBtnBorrar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File fileToDelete = selected;
				if (!fileToDelete.isDirectory()) {
					if (!fileToDelete.delete()) {
						JOptionPane.showMessageDialog(ftpWindow, "Error al borrar archivo.", "Error",
								JOptionPane.ERROR_MESSAGE);

						// Hay que hacer el registro en la base de datos del movimiento fallido.
					} else {
//						path.setText("Archivo borrado con éxito.");           HAY QUE PONER EL MENSAJE EN OTRO LAO

						// Hay que hacer el registro en la base de datos del borrado exitoso.
					}
				} else {
					recursiveDeletion(fileToDelete.getAbsolutePath());
				}
				recargarDirectorio();

			}

		});
		
		ftpWindow.getBtnOpenEmail().addActionListener(new ListenerOpenMail(mailWindow, ftpWindow));
		ftpWindow.getTxtFileName().addActionListener(
				new ListenerNameChange(ftpWindow.getLblFilePath(), ftpWindow.getTxtFileName(), this));
		ftpWindow.getBtnRefrescar().addActionListener(new ListenerFTPRefresh(this));
		

		ftpWindow.getBtnCrearCarpeta().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(selected!= null) {
					String path = selected.getParent();
					File fichero = new File(path + JOptionPane.showInputDialog("Introduce el nombre de la carpeta:")); 
					fichero.mkdir();	
				}
				else {
					String path = "C:\\";
					File fichero = new File(path + JOptionPane.showInputDialog("Introduce el nombre de la carpeta:")); 
					fichero.mkdir();
				}
			}

		});

		ftpWindow.getListFiles().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList) e.getSource();

				selected = (File) ftpWindow.getListFiles().getModel()
						.getElementAt(ftpWindow.getListFiles().getSelectedIndex());

				if (e.getClickCount() == 2) {
					File fichero = (File) ftpWindow.getListFiles().getModel()
							.getElementAt(ftpWindow.getListFiles().getSelectedIndex());
					selected = fichero;
					if (fichero.isDirectory()) {
						recargarDatosJList(ftpWindow.getListFiles(), fichero);
						selected = null;
					}
				}
			}

		});

		// ActualizarListenerJList();
	}

//	public void ActualizarListenerJList() {
//		for(int i=0;i< ftpWindow.getListFiles().getModel().getSize();i++) {
//			System.out.println(ftpWindow.getListFiles().getModel().getElementAt(i).toString());
//			ftpWindow.getListFiles().getComponent(i).addMouseListener(new ListenerChangeDirectory_LoadFiles(ftpWindow.getLblDirectory(), ftpWindow.getListFiles(), this, ftpWindow.getListFileMovements()));
//		}
//	}

	public void recargarDirectorio() {
		FTPFile[] files;
		try {
			files = cliente.listFiles();
			DefaultListModel<FTPFile> model = new DefaultListModel<FTPFile>();
			for (int i = 0; i < files.length; i++) {
				model.addElement(files[i]);
			}
//			ftpWindow.getListFiles().setModel(model);
//			ftpWindow.getListFiles().repaint();
//			ftpWindow.getLblDirectory().setText(cliente.printWorkingDirectory());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public static void recargarDatosJList() {
		File directorioAnterior = selected.getParentFile();
		File[] listFiles;
		if (directorioAnterior != null) {
			listFiles = combine(new File[] { directorioAnterior }, selected.listFiles());
		} else {
			listFiles = selected.listFiles();
		}
		ftpWindow.getListFiles().setListData(listFiles);
	}

	public void recursiveDeletion(String path) {
		/**
		 * 
		 */
		File currentFolder = new File(path);
		File[] files = currentFolder.listFiles();
		for (int index = 0; index < files.length; index++) {
			if (files[index].isFile()) {
				files[index].delete(); // BORRADO DE FICHERO DENTRO DE CARPETAS.
			} else {
				recursiveDeletion(path + "/" + files[index].getName());
				files[index].delete(); // BORRADO DE CARPETA
			}
		}
		currentFolder.delete();
	}

	public void registrarMovimiento(String accion, String usuario, String rutaArchivo) {
		ConnectionToDatabase conexion = new ConnectionToDatabase();
		Statement sentencia = conexion.getSentencia();
		try {
			Calendar fecha = Calendar.getInstance();
			sentencia.executeQuery("insert into movements values('" + accion + "'," + "'" + fecha.get(Calendar.DATE)
					+ "'/" + fecha.get(Calendar.MONTH) + "/" + fecha.get(Calendar.YEAR) + "','" + usuario + "','"
					+ rutaArchivo + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ClientFTP getCliente() {
		return cliente;
	}

	public void setCliente(ClientFTP cliente) {
		this.cliente = cliente;
	}

	public FTPWindow getFtpWindow() {
		return ftpWindow;
	}

	public void setFtpWindow(FTPWindow ftpWindow) {
		this.ftpWindow = ftpWindow;
	}

	public MailWindow getMailWindow() {
		return mailWindow;
	}

	public void setMailWindow(MailWindow mailWindow) {
		this.mailWindow = mailWindow;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
}