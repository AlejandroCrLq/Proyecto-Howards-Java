package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.swing.JButton;

public class MailRead extends JFrame {

	private JPanel contentPane;
	private JTextArea textField;

	/**
	 * Create the frame.
	 * 
	 * @throws MessagingException
	 * @throws IOException
	 */
	public MailRead(Message message) throws MessagingException, IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDe = new JLabel("De: ");
		lblDe.setText(lblDe.getText() + message.getFrom()[0]);
		lblDe.setBounds(27, 26, 521, 14);
		contentPane.add(lblDe);

		JLabel lblAsunto = new JLabel("Asunto: ");
		lblAsunto.setText(lblAsunto.getText() + message.getSubject());
		lblAsunto.setBounds(37, 53, 521, 14);
		contentPane.add(lblAsunto);

		textField = new JTextArea(30,80);
		textField.setWrapStyleWord(true);
		textField.setLineWrap(true);
		textField.setEditable(false);
		textField.setBounds(47, 78, 503, 160);
		textField.setText(getText(message));
		contentPane.add(textField);
		textField.setColumns(15);

		JButton btnResponder = new JButton("Responder");
		btnResponder.setBounds(113, 266, 112, 23);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WriteMessage write = new WriteMessage();
				write.
			}
		});
		contentPane.add(btnResponder);
	}

	private String getText(Part p) throws MessagingException, IOException {
		boolean textIsHtml = false;
		
		if (p.isMimeType("text/*")) {
			String s = (String) p.getContent();
			textIsHtml = p.isMimeType("text/html");
			if(textIsHtml) {
				Document doc = Jsoup.parse(s);
//				Elements tds = doc.getElementsByTag("p");
				return doc.text();
				
			}
			else {
				return s;
			}
			
		}

		if (p.isMimeType("multipart/alternative")) {
			// prefer html text over plain text
			Multipart mp = (Multipart) p.getContent();
			String text = null;
			for (int i = 0; i < mp.getCount(); i++) {
				Part bp = mp.getBodyPart(i);
				if (bp.isMimeType("text/plain")) {
					if (text == null)
						text = getText(bp);
					continue;
				} else if (bp.isMimeType("text/html")) {
					String s = getText(bp);
					if (s != null)
						return s;
				} else {
					return getText(bp);
				}
			}
			return text;
		} else if (p.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) p.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				String s = getText(mp.getBodyPart(i));
				if (s != null)
					return s;
			}
		}

		return null;
		
	}
	
    private boolean hasAttachments(Message msg) throws MessagingException, IOException {
	if (msg.isMimeType("multipart/mixed")) {
	    Multipart mp = (Multipart) msg.getContent();
	    if (mp.getCount() > 1)
		return true;
	}
	return false;
    }
    
}
