package ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class TransferFTP {

	private Users user;

	public TransferFTP(Users user) {
		this.user = user;

		String fileName = "gollum.jpg";
		String directory = "C:\\Users\\rafae\\OneDrive\\Documentos\\Proyecto Multidisciplinar\\";
		String remoteFolder = "..";
		
		//File fileFromPicker = new File(directory, fileName);
		File fileFromPicker = new File("C:\\peval1");

		FTPClient client = new FTPClient();

		String sFTP = "localhost";
		int port = 21;
		String sUser = user.geteMail();
		String sPassword = user.getPassword();

		try {
			client.connect(sFTP, port);
			if (client.isConnected()) {
				boolean login = client.login(sUser, sPassword);
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
