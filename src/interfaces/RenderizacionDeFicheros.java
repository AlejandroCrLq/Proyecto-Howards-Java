package interfaces;

import java.awt.Component;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.filechooser.FileSystemView;

public class RenderizacionDeFicheros extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		if(value instanceof File) {
			File fichero = (File) value;
			setText(fichero.getName());
			setIcon(FileSystemView.getFileSystemView().getSystemIcon(fichero));
            return this;
		}
		else {
			return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		}
	}

	
	
}