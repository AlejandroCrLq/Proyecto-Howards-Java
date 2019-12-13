package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ftp.FTPController;

public class ListenerNameChange implements ActionListener{
	JLabel path;
	JTextField fileName;
	FTPController FtpController;
	public ListenerNameChange(JLabel path,JTextField fileName,FTPController FtpController) {
		this.path = path;
		this.fileName = fileName;
		this.FtpController = FtpController;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		File fileToChange = new File(path.getText());
		File newName = new File(fileName.getText().trim());
		if (!newName.equals("")) {
			if (fileToChange.renameTo(newName)) {
				path.setText(newName.getName());
				//Hay que hacer registro en la BD de el renombrado del archivo.
			} else {
				JOptionPane.showMessageDialog(FtpController.getFtpWindow(), "Error al renombrar archivo.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(FtpController.getFtpWindow(), "La caja de texto no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}