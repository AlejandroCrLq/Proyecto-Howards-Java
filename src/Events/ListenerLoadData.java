package Events;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

public class ListenerLoadData implements ActionListener{
	private JList list,fileMovements;
	private TextField fileName;
	public ListenerLoadData(JList list,TextField fileName,JList fileMovements) {
		this.list=list;
		this.fileName=fileName;
		this.fileMovements=fileMovements;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
