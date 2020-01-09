package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import events.ListenerSendMessage;

public class WriteMessage extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				WriteMessage frame = new WriteMessage();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private JPanel contentPane;
	private JTextField textFor;
	private JTextArea textMessage;

	private JTextField textSubject;

	/**
	 * Create the frame.
	 */
	public WriteMessage() {
		setTitle("Escritura de mensajes");
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		setLocationRelativeTo(null);

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);

		JLabel lblFor = new JLabel("Para");
		lblFor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFor.setBounds(40, 45, 75, 25);
		layeredPane.add(lblFor);

		JLabel lblSubject = new JLabel("Asunto");
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSubject.setBounds(40, 85, 97, 19);
		layeredPane.add(lblSubject);

		JLabel lblMessage = new JLabel("Mensaje");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMessage.setBounds(40, 125, 97, 19);
		layeredPane.add(lblMessage);

		textFor = new JTextField();
		textFor.setBounds(140, 45, 600, 22);
		layeredPane.add(textFor);
		textFor.setColumns(10);

		textSubject = new JTextField();
		textSubject.setBounds(140, 85, 600, 22);
		layeredPane.add(textSubject);
		textSubject.setColumns(10);

		textMessage = new JTextArea(30, 80);
		textMessage.setWrapStyleWord(true);
		textMessage.setLineWrap(true);
		textMessage.setBounds(140, 125, 600, 200);
		layeredPane.add(textMessage);
		textMessage.setColumns(10);

		JButton btnSend = new JButton("Enviar");
		btnSend.setBounds(442, 505, 97, 25);
		layeredPane.add(btnSend);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(140, 350, 600, 131);
		layeredPane.add(scrollPane);

		DefaultListModel<File> fileModel = new DefaultListModel<File>();
		JList<File> listFiles = new JList<File>(fileModel);
		events.ListenerDeleteFileFromList deleteFileFromList = new events.ListenerDeleteFileFromList(listFiles,
				fileModel);
		listFiles.addMouseListener(deleteFileFromList);
		listFiles.addKeyListener(deleteFileFromList);
		scrollPane.setViewportView(listFiles);

		JLabel label = new JLabel("Archivos");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(40, 352, 97, 19);
		layeredPane.add(label);

		JButton btnAddFile = new JButton("Adjuntar archivo");
		btnAddFile.setBounds(250, 505, 133, 25);
		layeredPane.add(btnAddFile);
		btnAddFile.addActionListener(new events.ListenerAddFile(fileModel));
		setVisible(true);

		btnSend.addActionListener(new ListenerSendMessage(fileModel, this));
	}

	public JTextField getTextFor() {
		return textFor;
	}

	public JTextArea getTextMessage() {
		return textMessage;
	}

	public JTextField getTextSubject() {
		return textSubject;
	}

	public void setTextFor(JTextField textFor) {
		this.textFor = textFor;
	}

	public void setTextMessage(JTextArea textMessage) {
		this.textMessage = textMessage;
	}

	public void setTextSubject(JTextField textSubject) {
		this.textSubject = textSubject;
	}
}
