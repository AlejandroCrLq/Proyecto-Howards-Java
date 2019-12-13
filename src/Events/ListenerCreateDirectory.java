package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import ftp.FTPController;

public class ListenerCreateDirectory implements ActionListener{
	private FTPController ftpController;
	private JLabel actualDirectory;

	public ListenerCreateDirectory(FTPController ftpController, JLabel actualDirectory) {
		this.ftpController = ftpController;
		this.actualDirectory = actualDirectory;
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
