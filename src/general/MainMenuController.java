package general;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import ftp.ClientFTP;
import ftp.FTPController;
import ftp.Users;
import interfaces.FTPWindow;
import interfaces.MailWindow;
import interfaces.StartMenu;

public class MainMenuController implements ActionListener, MouseListener {
	Users user;
	StartMenu StartMenu;

	public MainMenuController(Users user) {
		this.user = user;
		StartMenu = new StartMenu();
		AsignarEventos();

	}

	public void AsignarEventos() {
		StartMenu.getBtnFTP().addActionListener(this);
		StartMenu.getBtnFTP().addMouseListener(this);
		StartMenu.getBtnMail().addActionListener(this);
		StartMenu.getBtnMail().addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == StartMenu.getBtnFTP()) {
			FTPWindow FtpWindow = new FTPWindow();
			MailWindow MailWindow;
			try {
				MailWindow = new MailWindow();
				FTPController FtpController = new FTPController(MailWindow, FtpWindow, user);
				FtpController.getFtpWindow().setVisible(true);
				try {
					ClientFTP clientFTP = new ClientFTP(user);
					clientFTP.connect();
					if (!clientFTP.login()) {
						JOptionPane.showMessageDialog(FtpController.getFtpWindow(),
								"Error al loguearse, no existe el usuario en el servidor FTP, contacte con el administrador del sistema.");
					} else {
						FtpController.setCliente(clientFTP);
						FtpController.AsignarEventos();
						FtpController.recargarDirectorio();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == StartMenu.getBtnFTP()) {
			StartMenu.getBtnFTP().setIcon(new ImageIcon(this.getClass().getResource("/imagenes/FTPMouseOver.png")));
		} else {
			StartMenu.getBtnMail().setIcon(new ImageIcon(this.getClass().getResource("/imagenes/carterochiquito.png")));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == StartMenu.getBtnFTP()) {
			StartMenu.getBtnFTP().setIcon(new ImageIcon(this.getClass().getResource("/imagenes/FTP.png")));
		} else {
			StartMenu.getBtnMail()
					.setIcon(new ImageIcon(this.getClass().getResource("/imagenes/carterochiquito2.png")));
		}
	}
}