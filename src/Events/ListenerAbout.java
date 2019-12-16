package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ftp.FTPController;

public class ListenerAbout implements ActionListener{
	FTPController FtpController;
	public ListenerAbout(FTPController FtpController) {
		this.FtpController=FtpController;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(FtpController.getFtpWindow(), "Realizado por Grupo 2, formado por:\n"
				+ "Alejandro Corral\n"
				+ "Jorge Rico\n"
				+ "Rafael Barranco\n"
				+ "Juan José Chica\n"
				+ "Francisco Manuel Rodríguez\n"
				+ "Óscar Brenes");
	}
}
