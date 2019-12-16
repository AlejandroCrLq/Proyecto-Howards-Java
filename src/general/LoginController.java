package general;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.Base64;
import javax.swing.JOptionPane;

import ftp.Users;
import interfaces.Login;
/**
 * Clase que controla el acceso del usuario.
 * @author Alexs
 *
 */
public class LoginController implements ActionListener {
	Login log;
	ConnectionToDatabase connect;
	ResultSet rs;

	public LoginController(Login log, ConnectionToDatabase connect) {
		this.log = log;
		this.connect = connect;
	}
	/**
	 * Método que añade los listeners al botón del login.
	 */
	public void addListeners() {
		log.getBtnNewButton().addActionListener(this);
	}

	/**
	 * Método que controla el acceso a la aplicación. En caso de error muestra un mensaje y en caso contrario
	 * permite el acceso a la aplicación.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(log.getBtnNewButton())) {
			try {
				rs = connect.getSentencia().executeQuery(
						"SELECT email FROM users WHERE email like '" + log.getUser().getText().trim() + "';");
				if (rs.next()) {
					rs = connect.getSentencia().executeQuery(
							"SELECT password FROM users where email like '" + log.getUser().getText().trim() + "';");
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

						JOptionPane.showMessageDialog(null, "Acceso concedido.", "CORRECTO",
								JOptionPane.INFORMATION_MESSAGE);
						new MainMenuController(user);
					} else {
						throw new Exception();
					}
				} else {
					throw new Exception();
				}
			} catch (Exception a) {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña erróneo.", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/**
	 * Método que permite comprobar la contraseña en la base de datos y compararla con la escrita por el usuario.
	 * @param password Contraseña escrita por el usuario en la ventana login.
	 * @return Devuelve true si la contraseña es correcta y false si es erronea.
	 */
	public boolean decryptPassword(String password) {
		MessageDigest digest;
		byte[] hash = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			hash = digest.digest(log.getPassword().getText().getBytes(StandardCharsets.UTF_8));
			String encoded = Base64.getEncoder().encodeToString(hash);
			System.out.println(encoded);
			if (!password.equals(encoded)) {
				throw new NoSuchAlgorithmException();
			}
		} catch (NoSuchAlgorithmException e) {
			return false;
		}

		return true;
	}
}
