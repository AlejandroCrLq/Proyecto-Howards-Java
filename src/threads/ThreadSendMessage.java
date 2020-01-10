package threads;

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
import javax.swing.JOptionPane;

import interfaces.WriteMessageWindow;

public class ThreadSendMessage extends Thread {

	private DefaultListModel<File> fileModel;
	private WriteMessageWindow frame;

	public ThreadSendMessage(DefaultListModel<File> fileModel, WriteMessageWindow frame) {
		this.fileModel = fileModel;
		this.frame = frame;
	}

	@Override
	public void run() {
		sendMessage();
	}

	private void sendMessage() {
		if ((frame.getTextMessage().getText().contentEquals("") || frame.getTextSubject().getText().contentEquals("")
				|| frame.getTextFor().getText().contentEquals(""))) {
			JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.", "NO SE ENVIO MENSAJE",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			frame.setEnabled(false);
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
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});

			MimeMessage message = new MimeMessage(session);

			try {

				message.setFrom(new InternetAddress(from, "Usuario")); // ESTO HAY QUE COGERLO DEL MODELO

				String recipients[] = frame.getTextFor().getText().split(", ");

				for (String re : recipients) {
					message.addRecipients(Message.RecipientType.TO, re);
				} // Se podrían añadir varios de la misma manera
				message.setSubject(frame.getTextSubject().getText());
				Multipart multipart = new MimeMultipart();
				BodyPart text = new MimeBodyPart();
				text.setText(frame.getTextMessage().getText());
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

				frame.dispose();
				JOptionPane.showMessageDialog(null, "¡Acción exitosa!", "Su mensaje ha sido enviado",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (com.sun.mail.smtp.SMTPSendFailedException e) {
				JOptionPane.showMessageDialog(null, "No se ha enviado el archivo, contiene ficheros peligrosos",
						"NO SE ENVIÓ MENSAJE", JOptionPane.INFORMATION_MESSAGE);
			} catch (MessagingException | UnsupportedEncodingException me) {
				JOptionPane.showMessageDialog(null, "Ha sucedido un error enviando el mensaje.", "NO SE ENVIÓ MENSAJE",
						JOptionPane.INFORMATION_MESSAGE);
				me.printStackTrace();
			}
			frame.setEnabled(true);
		}
	}
}