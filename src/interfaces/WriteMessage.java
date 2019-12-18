package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WriteMessage extends JFrame {
	private JPanel contentPane;
	private JTextField textFor;
	private JTextField textSubject;
	private JTextArea textMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WriteMessage frame = new WriteMessage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WriteMessage() {
		setTitle("Universidad de Howards");
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		this.setLocationRelativeTo(null);

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
		Events.ListenerDeleteFileFromList deleteFileFromList = new Events.ListenerDeleteFileFromList(listFiles, fileModel);
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
		btnAddFile.addActionListener(new Events.ListenerAddFile(fileModel));
		this.setVisible(true);

		btnSend.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				setEnabled(false);
				Thread messageSender = new Thread() {

					@Override
					public void run() {
						sendMessage(fileModel);
					}
				};
				messageSender.start();
			}

			private void sendMessage(DefaultListModel<File> fileModel) {
				if ((textMessage.getText().contentEquals("") || textSubject.getText().contentEquals("")
						|| textFor.getText().contentEquals(""))) {
					JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.", "NO SE ENVIO MENSAJE",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					String from = "acorralluque.sanjose@alumnado.fundacionloyola.net"; // HAY QUE COGER EL USER MAIL
					String password = "92405668";

					Properties props = System.getProperties();
					props.setProperty("mail.transport.protocol", "smtp");
					props.setProperty("mail.host", "smtp.gmail.com"); // El servidor SMTP de Google
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.user", from);

					props.put("mail.smtp.clave", "92405668"); // HAY QUE COGER LA CONTRASEÑA DEL USUARIO

					props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
					props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
					props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
					props.put("mail.smtp.socketFactory.port", "587");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

					Session session = Session.getInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(from, password);
						}
					});

					MimeMessage message = new MimeMessage(session);

					try {

						message.setFrom(new InternetAddress(from, "Usuario")); // ESTO HAY QUE COGERLO DEL MODELO

						String recipients[] = textFor.getText().split(", ");

						for (String re : recipients) {
							message.addRecipients(Message.RecipientType.TO, re);
						} // Se podrían añadir varios de la misma manera
						message.setSubject(textSubject.getText());
						Multipart multipart = new MimeMultipart();
						BodyPart text = new MimeBodyPart();
						text.setText(textMessage.getText());
						multipart.addBodyPart(text);
						for (int counFile = 0; counFile < fileModel.getSize(); counFile++) {
							BodyPart filePart = new MimeBodyPart();
							filePart.setFileName(fileModel.get(counFile).getName());
							filePart.setDataHandler(new DataHandler(new FileDataSource(fileModel.get(counFile))));
							multipart.addBodyPart(filePart);
						}
						message.setContent(multipart);
						Transport transport = session.getTransport("smtp");
						transport.connect("smtp.gmail.com", from, "92405668"); // HAY QUE COGER LA CONTRASEÑA DEL MODELO
						transport.sendMessage(message, message.getAllRecipients());
						transport.close();

						dispose();
						JOptionPane.showMessageDialog(null, "¡Acción exitosa!", "Su mensaje ha sido enviado",
								JOptionPane.INFORMATION_MESSAGE);

					} catch (com.sun.mail.smtp.SMTPSendFailedException e) {
						JOptionPane.showMessageDialog(null, "No se ha enviado el archivo, contiene ficheros peligrosos",
								"NO SE ENVIÓ MENSAJE", JOptionPane.INFORMATION_MESSAGE);
					} catch (MessagingException | UnsupportedEncodingException me) {
						JOptionPane.showMessageDialog(null, "Ha sucedido un error enviando el mensaje.",
								"NO SE ENVIÓ MENSAJE", JOptionPane.INFORMATION_MESSAGE);
						me.printStackTrace();
					}
					setEnabled(true);
				}
			}
		});
	}

	public JTextField getTextFor() {
		return textFor;
	}

	public void setTextFor(JTextField textFor) {
		this.textFor = textFor;
	}

	public JTextField getTextSubject() {
		return textSubject;
	}

	public void setTextSubject(JTextField textSubject) {
		this.textSubject = textSubject;
	}

	public JTextArea getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(JTextArea textMessage) {
		this.textMessage = textMessage;
	}
}
