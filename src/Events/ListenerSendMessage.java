package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;

import interfaces.WriteMessageWindow;
import threads.ThreadSendMessage;

public class ListenerSendMessage implements ActionListener {

	private final DefaultListModel<File> fileModel;
	private WriteMessageWindow frame;

	public ListenerSendMessage(DefaultListModel<File> fileModel, WriteMessageWindow frame) {
		this.fileModel = fileModel;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Thread messageSender = new ThreadSendMessage(fileModel, frame);
		messageSender.start();
	}

}