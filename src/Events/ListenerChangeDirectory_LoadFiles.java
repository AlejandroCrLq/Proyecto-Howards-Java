package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.w3c.dom.events.MouseEvent;

import ftp.FTPController;

public class ListenerChangeDirectory_LoadFiles implements MouseListener{
	private JLabel actualDirectory;
	private JList fileList;
	private FTPController FtpController;
	private JList movementsList;
	
	public ListenerChangeDirectory_LoadFiles(JLabel actualDirectory,JList fileList,FTPController FtpController,JList movementsList) {
		this.actualDirectory=actualDirectory;
		this.fileList = fileList;
		this.FtpController=FtpController;
		this.movementsList = movementsList;
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		File file = new File(actualDirectory+""+fileList.getSelectedValue());
		if(e.getClickCount() == 1) {
			
		}else if(e.getClickCount()==2) {
			if(file.isDirectory()) {
				actualDirectory.setText(file.getAbsolutePath());
				 
				//Aquí iría el listado de los movimientos de esta carpeta cogiendolo de la base de datos
				
				
				FtpController.recargarDirectorio();
			}else {
				JOptionPane.showMessageDialog(FtpController.getFtpWindow(), "El archivo seleccionado no es un directorio","Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {		
	}
	
	}

