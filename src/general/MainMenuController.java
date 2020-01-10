package general;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ftp.ClientFTP;
import ftp.FTPController;
import ftp.Users;
import interfaces.FTPWindow;
import interfaces.MailWindow;
import interfaces.StartMenuWindow;

public class MainMenuController implements ActionListener {
	private String pass;
	private StartMenuWindow StartMenu;
	private Users user;

	public MainMenuController(Users user, String pass) {
		this.user = user;
		this.pass = pass;
		StartMenu = new StartMenuWindow();
		AsignarEventos();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MailWindow mailWindow = new MailWindow();
		FTPWindow ftpWindow = new FTPWindow();
		FTPController ftpController = new FTPController(mailWindow, ftpWindow, user);
		MailController mailController = new MailController(user.geteMail(), pass, mailWindow, ftpWindow);
		if (e.getSource() == StartMenu.getBtnFTP()) {
			try {
				ClientFTP clientFTP = new ClientFTP(user);
				clientFTP.connect();
				StartMenu.setVisible(false);
				mailWindow.setVisible(false);
				if (!clientFTP.login()) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Error al loguearse, no existe el usuario en el servidor FTP, contacte con el administrador del sistema.");
				} else {
					ftpController.setCliente(clientFTP);
					ftpController.AsignarEventos();
					ftpController.recargarDirectorio();
				}
				ftpWindow.setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == StartMenu.getBtnMail()){
			mailWindow.setVisible(true);
			ftpWindow.setVisible(false);
			StartMenu.setVisible(false);
		}
	}

	public void AsignarEventos() {
		StartMenu.getBtnFTP().addActionListener(this);
		StartMenu.getBtnMail().addActionListener(this);
	}
}