package ftp;

import java.io.File;

import Events.ListenerAbout;
import Events.ListenerDeleteFiles;
import Events.ListenerNameChange;
import Events.ListenerOpenMail;
import interfaces.FTPWindow;
import interfaces.MailWindow;

public class FTPController {
	FTPWindow ftpWindow;
	MailWindow mailWindow;
	Users user;
	public FTPController(MailWindow mailWindow, FTPWindow ftpWindow, Users user) {
		this.ftpWindow = ftpWindow;
		this.mailWindow=mailWindow;
		this.user=user;
		AsignarEventos();
	}
	public void AsignarEventos() {
		ftpWindow.getBtnAbout().addActionListener(new ListenerAbout(this));
		ftpWindow.getBtnBorrar().addActionListener(new ListenerDeleteFiles(ftpWindow.getLblFilePath(), this));
		ftpWindow.getBtnOpenEmail().addActionListener(new ListenerOpenMail(mailWindow, ftpWindow));
		ftpWindow.getTxtFileName().addActionListener(new ListenerNameChange(ftpWindow.getLblFilePath(), ftpWindow.getTxtFileName(), this));
		
		
	}
	public void recargarDirectorio() {
		File file = new File(ftpWindow.getLblFilePath().getText());
		File[] files = file.listFiles();
		ftpWindow.getListFiles().setListData(files); //Comprobar que funcione, no estamos seguros. Recordar a Yorch.
		ftpWindow.getListFiles().repaint();
		ftpWindow.getListFileMovements().removeAll();
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