package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WriteMessage extends JFrame {
	private JPanel contentPane;
	private JTextField textFor;
	private JTextField textSubject;
	private JTextField textMessage;

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
		
		textMessage = new JTextField();
		textMessage.setBounds(140, 125, 600, 200);
		layeredPane.add(textMessage);
		textMessage.setColumns(10);
		
		JButton btnSend = new JButton("Enviar");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if((textMessage.getText().contentEquals("") || textSubject.getText().contentEquals("" ||textFor.getText().contentEquals("") )){
					//Mensaje de error porque hay campos vacios
				}
				else {
					
				
				String remitente = "jchicaramirez.sanjose@alumnado.fundacionloyola.net";  //HAY QUE COGER EL USER MAIL

			    Properties props = System.getProperties();
			    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
			    props.put("mail.smtp.user", remitente);
			    
			    props.put("mail.smtp.clave", "26170102");    //HAY QUE COGER LA CONTRASEÑA DEL USUARIO
			    
			    
			    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
			    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
			    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

			    Session session = Session.getDefaultInstance(props);
			    MimeMessage message = new MimeMessage(session);

			    try {
			        message.setFrom(new InternetAddress(remitente, "Your Name"));
			        message.addRecipients(Message.RecipientType.TO, textFor.getText());   //Se podrían añadir varios de la misma manera
			        message.setSubject(textSubject.getText());
			        message.setText(textMessage.getText());
			        Transport transport = session.getTransport("smtp");
			        
			        transport.connect("smtp.gmail.com", remitente, "26170102" ); //HAY QUE COGER LA CONTRASEÑA DEL MODELO
			        
			        transport.sendMessage(message, message.getAllRecipients());
			        transport.close();
			    }
			    catch (MessagingException me) {
			        me.printStackTrace();   //Si se produce un error
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

	public JTextField getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(JTextField textMessage) {
		this.textMessage = textMessage;
	}

}
