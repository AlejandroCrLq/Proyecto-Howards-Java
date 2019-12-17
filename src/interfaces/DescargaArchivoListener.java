package interfaces;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.mail.Part;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 * La clase DescargaArchivoListener se encarga de 
 *
 * @author: Jorge Rico Vivas
 * @version: 1.0
 * @since: 17/12/2019
 */

public class DescargaArchivoListener implements MouseListener {

	private JList<Part> listFiles;

	public DescargaArchivoListener(JList<Part> listFiles) {
		this.listFiles = listFiles;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2) {
			Part part = listFiles.getModel().getElementAt(listFiles.getSelectedIndex());
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");   
			int userSelection = fileChooser.showSaveDialog(new JFrame());
		    File fileToSave = fileChooser.getSelectedFile();
			boolean replaced = false;
			if(fileToSave.exists()) {
				replaced = true;
			}
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    if(!fileToSave.exists()) {
			    	try {
						fileToSave.createNewFile();
					} catch (IOException e1) {
						System.out.println("Couldn't save file");
					}
			    }
			    try {
			    	InputStream input = part.getInputStream();
			    	FileOutputStream fos = new FileOutputStream(fileToSave);
			    	
			        byte[] buf = new byte[4096];
			        int bytesRead;
			        while((bytesRead = input.read(buf))!=-1) {
			            fos.write(buf, 0, bytesRead);
			        }
			        if(replaced) {
						JOptionPane.showMessageDialog(new JFrame(), "Archivo reemplazado y descargado");
			        } else {
						JOptionPane.showMessageDialog(new JFrame(), "Archivo descargado");
			        }
				} catch (IOException | MessagingException e1) {
					System.out.println("Ha sucedido un error");
				}
			    
			}
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
