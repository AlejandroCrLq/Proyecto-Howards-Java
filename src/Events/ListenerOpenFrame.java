package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import interfaces.MailWindow;
import interfaces.FTPWindow;

public class ListenerOpenFrame implements ActionListener{
	JFrame MailWindow;
	JFrame FtpWindow;
	public ListenerOpenFrame(JFrame MailWindow,JFrame FtpWindow) {
		this.MailWindow = MailWindow;
		this.FtpWindow = FtpWindow;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		FtpWindow.setVisible(false);
		MailWindow.setVisible(true);
	}
}
