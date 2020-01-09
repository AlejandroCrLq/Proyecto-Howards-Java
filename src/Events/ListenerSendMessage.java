package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;

import interfaces.WriteMessage;
import threads.ThreadSendMessage;

public class ListenerSendMessage implements ActionListener {

	private final DefaultListModel<File> fileModel;
	private WriteMessage frame;

	public ListenerSendMessage(DefaultListModel<File> fileModel, WriteMessage frame) {
		this.fileModel = fileModel;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Thread messageSender = new ThreadSendMessage(fileModel, frame);
		messageSender.start();
	}

}