package interfaces;

import java.io.IOException;
import java.util.Vector;

import javax.mail.Flags.Flag;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import Events.ListenerAnswerMessage;
import Events.ListenerFileDownload;
import interfaces.cellrenders.FilePartRender;

public class MailReadWindow extends JFrame {

	private JPanel contentPane;
	private JTextArea textField;

	/**
	 * Create the frame.
	 *
	 * @throws MessagingException
	 * @throws IOException
	 */
	public MailReadWindow(Message message) throws MessagingException, IOException {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		message.setFlag(Flag.SEEN, true);
		JLabel lblDe = new JLabel("De: ");
		String from = MimeUtility.decodeText(message.getFrom()[0].toString()).replaceAll("\"", "");
		if (from.contains("<")) {
			from = from.substring(0, from.indexOf("<"));
		}
		lblDe.setText(lblDe.getText() + from);
		lblDe.setBounds(27, 26, 521, 14);
		contentPane.add(lblDe);

		JLabel lblAsunto = new JLabel("Asunto: ");
		lblAsunto.setText(lblAsunto.getText() + message.getSubject());
		lblAsunto.setBounds(37, 53, 521, 14);
		contentPane.add(lblAsunto);

		setTitle(from + " - " + message.getSubject());

		textField = new JTextArea(30, 80);
		textField.setWrapStyleWord(true);
		textField.setLineWrap(true);
		textField.setEditable(false);
		textField.setBounds(47, 78, 503, 160);
		textField.setText(getText(message));
		contentPane.add(textField);
		textField.setColumns(15);

		JButton btnResponder = new JButton("Responder");
		btnResponder.setBounds(113, 266, 112, 23);
		btnResponder.addActionListener(new ListenerAnswerMessage(message));
		contentPane.add(btnResponder);

		if (message.getContentType().contains("multipart")) {
			Vector<Part> filesInMessage = loadFiles((Multipart) message.getContent());
			if (!filesInMessage.isEmpty()) {
				JList<Part> listFiles = new JList<Part>(filesInMessage);
				listFiles.setCellRenderer(new FilePartRender());
				ListenerFileDownload downloadListener = new ListenerFileDownload(listFiles, this);
				listFiles.addMouseListener(downloadListener);
				listFiles.addKeyListener(downloadListener);
				JScrollPane scroll = new JScrollPane(listFiles);
				scroll.setBounds(47, 258, 503, 150);
				contentPane.add(scroll);
				btnResponder.setBounds(113, 423, 112, 23);
				setBounds(100, 100, 630, 500);
			}
		}
	}

	private String getText(Part p) throws MessagingException, IOException {
		boolean textIsHtml = false;

		if (p.isMimeType("text/*")) {
			String s = (String) p.getContent();
			textIsHtml = p.isMimeType("text/html");
			if (textIsHtml) {
				Document doc = Jsoup.parse(s);
//				Elements tds = doc.getElementsByTag("p");
				return doc.text();

			} else {
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
					if (text == null) {
						text = getText(bp);
					}
					continue;
				} else if (bp.isMimeType("text/html")) {
					String s = getText(bp);
					if (s != null) {
						return s;
					}
				} else {
					return getText(bp);
				}
			}
			return text;
		} else if (p.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) p.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				String s = getText(mp.getBodyPart(i));
				if (s != null) {
					return s;
				}
			}
		}

		return null;

	}

	private boolean hasAttachments(Message msg) throws MessagingException, IOException {
		if (msg.isMimeType("multipart/mixed")) {
			Multipart mp = (Multipart) msg.getContent();
			if (mp.getCount() > 1) {
				return true;
			}
		}
		return false;
	}

	private Vector<Part> loadFiles(Multipart content) throws MessagingException {
		Vector<Part> fileParts = new Vector<Part>();
		for (int i = 0; i < content.getCount(); i++) {
			MimeBodyPart part = (MimeBodyPart) content.getBodyPart(i);
			if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
				fileParts.add(part);
			}
		}
		return fileParts;
	}

}
