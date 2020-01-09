package ftp;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPFile;

import Events.ListenerAbout;
import Events.ListenerChangeDirectory_LoadFiles;
import Events.ListenerDeleteFiles;
import Events.ListenerFTPRefresh;
import Events.ListenerNameChange;
import Events.ListenerOpenFrame;
import general.ConnectionToDatabase;
import interfaces.FTPWindow;
import interfaces.MailWindow;

public class FTPController {
	FTPWindow ftpWindow;
	MailWindow mailWindow;
	Users user;
	ClientFTP cliente;
	
	public FTPController(MailWindow mailWindow, FTPWindow ftpWindow, Users user) {
		this.ftpWindow = ftpWindow;
		this.mailWindow = mailWindow;
		this.user = user;
	}

	public void AsignarEventos() {
		ftpWindow.getBtnAbout().addActionListener(new ListenerAbout(this));
		ftpWindow.getBtnBorrar().addActionListener(new ListenerDeleteFiles(ftpWindow.getLblFilePath(), this));
		ftpWindow.getBtnOpenEmail().addActionListener(new ListenerOpenFrame(mailWindow, ftpWindow));
		ftpWindow.getTxtFileName().addActionListener(
				new ListenerNameChange(ftpWindow.getLblFilePath(), ftpWindow.getTxtFileName(), this));
		ftpWindow.getBtnRefrescar().addActionListener(new ListenerFTPRefresh(this));
		ActualizarListenerJList();
	}

	public void ActualizarListenerJList() {
		for(int i=0;i< ftpWindow.getListFiles().getModel().getSize();i++) {
			System.out.println(ftpWindow.getListFiles().getModel().getElementAt(i).toString());
			ftpWindow.getListFiles().getComponent(i).addMouseListener(new ListenerChangeDirectory_LoadFiles(ftpWindow.getLblDirectory(), ftpWindow.getListFiles(), this, ftpWindow.getListFileMovements()));
		}
	}
	
	public void recargarDirectorio() {
		FTPFile[] files;
		try {			
			files = cliente.listFiles();
			DefaultListModel<FTPFile> model = new DefaultListModel<FTPFile>();			
			for(int i=0;i<files.length;i++) {
				model.addElement(files[i]);
			}
			ftpWindow.getListFiles().setModel(model);
			ftpWindow.getListFiles().repaint();
			ftpWindow.getLblDirectory().setText(cliente.printWorkingDirectory());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void registrarMovimiento(String accion,String usuario,String rutaArchivo) {
		ConnectionToDatabase conexion = new ConnectionToDatabase();
	    Statement sentencia= conexion.getSentencia();
	    try {
			Calendar fecha = Calendar.getInstance();
			sentencia.executeQuery("insert into movements values('"+accion+"',"+"'"+fecha.get(Calendar.DATE)+"'/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR)+"','"
					+ usuario+"','"+rutaArchivo+"')");
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