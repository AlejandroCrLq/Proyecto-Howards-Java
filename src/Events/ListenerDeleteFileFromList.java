package Events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * La clase ListenerDeleteFileFromList se encarga de
 *
 * @author: Jorge Rico Vivas
 * @version: 1.0
 * @since: 17/12/2019
 */

public class ListenerDeleteFileFromList implements MouseListener {

	private JList<File> listFiles;
	private DefaultListModel<File> fileModel;

	public ListenerDeleteFileFromList(JList<File> listFiles, DefaultListModel<File> fileModel) {
		this.listFiles = listFiles;
		this.fileModel = fileModel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			if (e.getClickCount() >= 2) {
				fileModel.remove(listFiles.getSelectedIndex());
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("No se seleccionó ningún archivo");
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
