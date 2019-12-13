package ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class TransferFTP {

	private Users user;

	public static void main(String[] args) {

		/*
		 * String password = "1234567890"; MessageDigest digest; byte[] hash = null; try
		 * { digest = MessageDigest.getInstance("SHA-256"); hash =
		 * digest.digest(password.getBytes(StandardCharsets.UTF_8)); } catch
		 * (NoSuchAlgorithmException e) {
		 * System.out.println("Error en la encriptación de la contraseña."); }
		 */

		Users user = new Users();
		user.seteMail("rbarranco");
		user.setUserName("Rafael Barranco");
		user.setPassword("1234567890");
		user.setTeacher(false);
		TransferFTP t = new TransferFTP(user);

	}

	public TransferFTP(Users user) {
		this.user = user;

		String fileName = "gollum.jpg";
		String directory = "C:\\Users\\rafae\\OneDrive\\Documentos\\Proyecto Multidisciplinar\\";
		String remoteFolder = "..";
		
		//File fileFromPicker = new File(directory, fileName);
		File fileFromPicker = new File("C:\\peval1");
		
		try {
		//FTPClient client = new FTPClient();
		ClientFTP client = new ClientFTP(this.user);

		//String sFTP = "localhost";
		//int port = 21;
		//String sUser = user.geteMail();
		//String sPassword = user.getPassword();
		
			client.connect();
			if (client.isConnected()) {
				boolean login = client.login();
				if (login) {
					client.enterLocalPassiveMode();
					client.setFileType(FTP.BINARY_FILE_TYPE);

					loadFiles(client, fileFromPicker, remoteFolder);

					client.logout();
					client.disconnect();
				} else {
					System.out.println("No ha hecho login.");
				}
			} else {
				System.out.println("No ha conectado.");
			}

		} catch (IOException ioe) {
			System.out.println("Error de E/S en la conexión. " + ioe.getMessage());
		}
	}

	public void loadFiles(FTPClient client, File file, String remoteFolder) throws IOException {
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				String newRemoteFolder = remoteFolder + "\\" + file.getName();
				client.makeDirectory(newRemoteFolder);
				for (File f : files) {
					loadFiles(client, f, newRemoteFolder);
				}
			} else {
				InputStream inputStream = new FileInputStream(file);
				client.storeFile(remoteFolder + "\\" + file.getName(), inputStream);
				inputStream.close();
			}
		}
	}

}
