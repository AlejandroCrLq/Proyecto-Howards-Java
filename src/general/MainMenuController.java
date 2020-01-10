package general;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;

import ftp.ClientFTP;
import ftp.FTPController;
import ftp.Users;
import interfaces.FTPWindow;
import interfaces.MailWindow;
import interfaces.StartMenuWindow;

public class MainMenuController implements ActionListener{
	Users user;
	StartMenuWindow StartMenu;
	
	public MainMenuController(Users user) {
		this.user = user;
		StartMenu = new StartMenuWindow();
		AsignarEventos();
		
	}
	
	public void AsignarEventos() {
		StartMenu.getBtnFTP().addActionListener(this);
		StartMenu.getBtnMail().addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==StartMenu.getBtnFTP()) {			
			FTPWindow FtpWindow = new FTPWindow();
			MailWindow MailWindow;
			MailWindow = null;
			FTPController FtpController = new FTPController(MailWindow, FtpWindow, user);				
			FtpController.getFtpWindow().setVisible(true);
			try {
				ClientFTP clientFTP = new ClientFTP(user);
				clientFTP.connect();
				if(!clientFTP.login()) {
					JOptionPane.showMessageDialog(FtpController.getFtpWindow(), "Error al loguearse, no existe el usuario en el servidor FTP, contacte con el administrador del sistema.");
				}else {
					FtpController.setCliente(clientFTP);						
					FtpController.AsignarEventos();
					//FtpController.recargarDirectorio();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			
		}
	}
}