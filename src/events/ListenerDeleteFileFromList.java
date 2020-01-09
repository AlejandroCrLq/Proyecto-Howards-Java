package events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class ListenerDeleteFileFromList implements MouseListener, KeyListener {

	private DefaultListModel<File> fileModel;
	private JList<File> listFiles;

	public ListenerDeleteFileFromList(JList<File> listFiles, DefaultListModel<File> fileModel) {
		this.listFiles = listFiles;
		this.fileModel = fileModel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_DELETE) && (listFiles.getSelectedIndex() != -1)) {
			for (int index : listFiles.getSelectedIndices()) {
				fileModel.remove(listFiles.getSelectedIndex());
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			if ((e.getClickCount() >= 2) && (listFiles.getSelectedIndex() != -1)) {
				fileModel.remove(listFiles.getSelectedIndex());
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("No se seleccionó ningún archivo");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
