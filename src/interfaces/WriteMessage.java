package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sun.mail.smtp.SMTPAddressFailedException;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 420);
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
		
		textMessage = new JTextArea(30,80);
		textMessage.setWrapStyleWord(true);
		textMessage.setLineWrap(true);
		textMessage.setBounds(140, 125, 600, 200);
		layeredPane.add(textMessage);
		textMessage.setColumns(10);
		
		JButton btnSend = new JButton("Enviar");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if((textMessage.getText().contentEquals("") || textSubject.getText().contentEquals("") ||textFor.getText().contentEquals("") )){
					JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.", "NO SE ENVIO MENSAJE",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					
				
				String from = "acorralluque.sanjose@alumnado.fundacionloyola.net";  //HAY QUE COGER EL USER MAIL
				String password = "92405668";
				
			    Properties props = System.getProperties();
			    props.setProperty("mail.transport.protocol", "smtp");   
			    props.setProperty("mail.host", "smtp.gmail.com");   //El servidor SMTP de Google
			    props.put("mail.smtp.auth", "true");
			    props.put("mail.smtp.user", from);
			    
			    props.put("mail.smtp.clave", "92405668");    //HAY QUE COGER LA CONTRASE�A DEL USUARIO
			    
			    
			    props.put("mail.smtp.auth", "true");    //Usar autenticaci�n mediante usuario y clave
			    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
			    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
			    props.put("mail.smtp.socketFactory.port", "587");  
			    props.put("mail.smtp.starttls.enable","true");
			    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  

			    Session session = Session.getInstance(props,  
			    	    new javax.mail.Authenticator() {
			    	       protected PasswordAuthentication getPasswordAuthentication() {  
			    	       return new PasswordAuthentication(from,password);  
			    	   }  
			    	   });  
			    
			    MimeMessage message = new MimeMessage(session);

			    try {
			    	
			        message.setFrom(new InternetAddress(from, "El jefe cabr�n")); //ESTO HAY QUE COGERLO DEL MODELO
			        
			        String recipients[] = textFor.getText().split(", ");
			        
			        for(String re:recipients ) {
			        	 message.addRecipients(Message.RecipientType.TO, re);
			        }
			          //Se podr�an a�adir varios de la misma manera
			        message.setSubject(textSubject.getText());
			        message.setText(textMessage.getText());
			        Transport transport = session.getTransport("smtp");
			        
			        transport.connect("smtp.gmail.com", from, "92405668" ); //HAY QUE COGER LA CONTRASE�A DEL MODELO
			        
			        transport.sendMessage(message, message.getAllRecipients());
			        transport.close();
			        
			        dispose();
			    }
			    catch (MessagingException | UnsupportedEncodingException me ) {
					JOptionPane.showMessageDialog(null, "El correo no existe.", "NO SE ENVI� MENSAJE",
							JOptionPane.INFORMATION_MESSAGE);
					me.printStackTrace();
			    }
			}
			}
		});
		btnSend.setBounds(360, 335, 97, 25);
		layeredPane.add(btnSend);
		this.setVisible(true);

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