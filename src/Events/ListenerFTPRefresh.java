package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ftp.FTPController;

public class ListenerFTPRefresh implements ActionListener{
	FTPController FtpController;
	public ListenerFTPRefresh (FTPController FtpController){
		this.FtpController = FtpController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FtpController.recargarDirectorio();
	}
}
