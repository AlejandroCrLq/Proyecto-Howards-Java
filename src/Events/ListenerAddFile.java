package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * La clase AddFileListener se encarga de 
 *
 * @author: Jorge Rico Vivas
 * @version: 1.0
 * @since: 17/12/2019
 */

public class ListenerAddFile implements ActionListener {

	private DefaultListModel<File> fileModel;


	public ListenerAddFile(DefaultListModel<File> fileModel) {
		this.fileModel = fileModel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);
		int optionTaken = fileChooser.showOpenDialog(new JFrame());
		if (optionTaken == JFileChooser.APPROVE_OPTION) {
			for(File file : fileChooser.getSelectedFiles()) {
				if(fileModel.indexOf(file)==-1) {
					fileModel.addElement(file);
				}else {
				}
			}
		}
	}

}
