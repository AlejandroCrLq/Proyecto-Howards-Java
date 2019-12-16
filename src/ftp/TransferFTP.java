package ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;

public class TransferFTP {

	private Users user;

	public static void main(String[] args) {

		/*
		 * String password = "1234567890"; 
		 * MessageDigest digest; 
		 * byte[] hash = null; 
		 * try
		 * { 
		 * 		digest = MessageDigest.getInstance("SHA-256"); 
		 * 		hash = digest.digest(password.getBytes(StandardCharsets.UTF_8)); 
		 * } catch (NoSuchAlgorithmException e) {
		 * System.out.println("Error en la encriptación de la contraseña.");
		 * }
		 */

		Users user = new Users();
		user.seteMail("rbarranco");
		user.setUserName("Rafael Barranco");
		user.setPassword("1234567890");
		user.setTeacher(false);

		String fileName = "gollum.jpg";
		String directory = "C:\\Users\\rafae\\OneDrive\\Documentos\\Proyecto Multidisciplinar\\";
		String remoteFolder = "..";

		// File fileFromPicker = new File(directory, fileName);
		String fileFromPicker = "peval1";
		String local = "C:\\peval3\\";
		
		//TransferFTP t = new TransferFTP(user, fileFromPicker, remoteFolder);
		TransferFTP t = new TransferFTP(user, local, fileFromPicker);
		

	}

	public TransferFTP(Users user, String local, String remote) {
		this.user = user;

		try {
			ClientFTP client = new ClientFTP(this.user);

			client.connect();
			if (client.isConnected()) {
				boolean login = client.login();
				if (login) {
					client.enterLocalPassiveMode();
					client.setFileType(FTP.BINARY_FILE_TYPE);
					client.setControlEncoding("UTF-8");

					downloadFiles(client, local, remote);

					client.logout();
					client.disconnect();
				} else {
					System.out.println("No ha hecho login.");
				}
			} else {
				System.out.println("No ha conectado.");
			}

		} catch (IOException ex) {
			System.out.println("Error de E/S en la conexión. " + ex.getMessage());
		}
	}

	public void loadFiles(ClientFTP client, String localFile, String remoteFile) throws IOException {
		File file = new File(localFile);
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				String newRemoteFolder = remoteFile + "\\" + file.getName();
				client.makeDirectory(newRemoteFolder);
				for (File f : files) {
					loadFiles(client, f.getAbsolutePath(), newRemoteFolder);
				}
			} else {
				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
				client.storeFile(remoteFile + "\\" + file.getName(), inputStream);
				inputStream.close();
			}
		}
	}

	public void downloadFiles(ClientFTP client, String localFile, String remoteFile) throws IOException {
		FTPFile[] serverFiles = client.listFiles(remoteFile);
		
		for (FTPFile f : serverFiles) {
			File downloadFile;
			if (f.isDirectory()) {
				
				downloadFile = new File(localFile);
				
				File newFolder = new File(downloadFile, f.getName());
				
				newFolder.mkdir();
				
				//if (!downloadFile.exists()) {
				//	downloadFile.mkdir();
				//}
				
				downloadFiles(client, newFolder.getAbsolutePath(), remoteFile + "\\" + f.getName());
			} else {
				
				/*File parentDir = downloadFile.getParentFile();
				if (!parentDir.exists()) {
					parentDir.mkdir();
				}*/
				downloadFile = new File(localFile, f.getName());
				
				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
				try {
					client.retrieveFile(f.getName(), outputStream);
				} catch (IOException ex) {
					throw ex;
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}
			}
		}
	}
}
