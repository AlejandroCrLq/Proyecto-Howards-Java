package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ftp.FTPController;

public class ListenerDeleteFiles implements ActionListener {
	private JLabel path;
	private FTPController FtpController;

	public ListenerDeleteFiles(JLabel path, FTPController FtpController) {
		this.path = path;
		this.FtpController = FtpController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		File fileToDelete = new File(path.getText());
		if (!fileToDelete.isDirectory()) {
			if (!fileToDelete.delete()) {
				JOptionPane.showMessageDialog(FtpController.getFtpWindow(), "Error al borrar archivo.", "Error",
						JOptionPane.ERROR_MESSAGE);

				// Hay que hacer el registro en la base de datos del movimiento fallido.
			} else {
				path.setText("Archivo borrado con éxito.");

				// Hay que hacer el registro en la base de datos del borrado exitoso.
			}
		} else {
			recursiveDeletion(fileToDelete.getAbsolutePath());
		}
		FtpController.recargarDirectorio();
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
}
