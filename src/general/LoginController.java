package general;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.Base64;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;

import ftp.Users;
import interfaces.LoginWindow;
import mail.MailInbox;

/**
 * Clase que controla el acceso del usuario.
 * 
 * @author Alexs
 *
 */
public class LoginController implements ActionListener {

	ConnectionToDatabase connect;
	LoginWindow log;

	ResultSet rs;

	public LoginController(LoginWindow log, ConnectionToDatabase connect) {
		this.log = log;
		this.connect = connect;
	}

	/**
	 * Mï¿½todo que controla el acceso a la aplicaciï¿½n. En caso de error muestra
	 * un mensaje y en caso contrario permite el acceso a la aplicaciï¿½n.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(log.getBtnNewButton())) {
			try {
				String sqlEmails = "SELECT email FROM users WHERE email like '" + log.getUser().getText().trim() + "';";
				rs = connect.getSentencia().executeQuery(sqlEmails);
				if (rs.next()) {
					String sqlPasswords = "SELECT password FROM users where email like '"
							+ log.getUser().getText().trim() + "';";
					rs = connect.getSentencia().executeQuery(sqlPasswords);
					if (rs.next() && decryptPassword(rs.getString(1))) {

						Users user = new Users();

						user.seteMail(log.getUser().getText());

						rs = connect.getSentencia().executeQuery(
								"SELECT name FROM users WHERE email like '" + log.getUser().getText().trim() + "';");
						rs.next();
						user.setUserName(rs.getString(1));

						user.setPassword(log.getPassword().getText());

						rs = connect.getSentencia().executeQuery("SELECT professor FROM users WHERE email like '"
								+ log.getUser().getText().trim() + "';");
						rs.next();
						user.setTeacher(rs.getBoolean(1));

						String email = user.geteMail();
						String pass = log.geteMailPasswordField().getText();

						try {
							MailInbox.tryConnection(email, pass);
						} catch (MessagingException ex) {
							ex.printStackTrace();
							throw new Exception();
						}
						JOptionPane.showMessageDialog(null, "Acceso concedido.", "CORRECTO",
								JOptionPane.INFORMATION_MESSAGE);
						MailInbox.setMailPassword(pass);
						log.setVisible(false);
						new MainMenuController(user, pass);
					} else {
						throw new Exception();
					}
				} else {
					throw new Exception();
				}
			} catch (Exception a) {
				a.printStackTrace();
				JOptionPane.showMessageDialog(null, "Usuario, contraseña o contraseña de correo erróneo/s", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * Mï¿½todo que aï¿½ade los listeners al botï¿½n del login.
	 */
	public void addListeners() {
		log.getBtnNewButton().addActionListener(this);
	}

	/**
	 * Mï¿½todo que permite comprobar la contraseï¿½a en la base de datos y
	 * compararla con la escrita por el usuario.
	 * 
	 * @param password Contraseï¿½a escrita por el usuario en la ventana login.
	 * @return Devuelve true si la contraseï¿½a es correcta y false si es erronea.
	 */
	public boolean decryptPassword(String password) {
		MessageDigest digest;
		byte[] hash = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			hash = digest.digest(log.getPassword().getText().getBytes(StandardCharsets.UTF_8));
			String encoded = Base64.getEncoder().encodeToString(hash);
			if (!password.equals(encoded)) {
				throw new NoSuchAlgorithmException();
			}
		} catch (NoSuchAlgorithmException e) {
			return false;
		}

		return true;
	}
}
