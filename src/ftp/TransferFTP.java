package ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
<<<<<<< HEAD
import java.io.FileOutputStream;
=======
>>>>>>> refs/remotes/origin/master
import java.io.IOException;
import java.io.InputStream;
<<<<<<< HEAD
import java.io.OutputStream;
=======
>>>>>>> refs/remotes/origin/master

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;

public class TransferFTP {

	private Users user;

	public static void main(String[] args) {
		Users user = new Users();
		user.seteMail("rbarranco");
		user.setUserName("Rafael Barranco");
		user.setPassword("1234567890");
		user.setTeacher(false);

		String fileName = "gollum.jpg";
		String directory = "C:\\Users\\rafae\\OneDrive\\Documentos\\Proyecto Multidisciplinar\\";
		String remoteFolder = "..";
<<<<<<< HEAD

		// File fileFromPicker = new File(directory, fileName);
		String fileFromPicker = "peval1";
		String local = "C:\\peval3\\";

		//TransferFTP t = new TransferFTP(user, fileFromPicker, remoteFolder); // Carga
		 TransferFTP t = new TransferFTP(user, local, fileFromPicker); // Descarga

	}

	public TransferFTP(Users user, String local, String remote) {
		this.user = user;

=======
		
		//File fileFromPicker = new File(directory, fileName);
		File fileFromPicker = new File("C:\\peval1");
		
>>>>>>> refs/remotes/origin/master
		try {
<<<<<<< HEAD
			ClientFTP client = new ClientFTP(this.user);

=======
		//FTPClient client = new FTPClient();
		ClientFTP client = new ClientFTP(this.user);

		//String sFTP = "localhost";
		//int port = 21;
		//String sUser = user.geteMail();
		//String sPassword = user.getPassword();
		
>>>>>>> refs/remotes/origin/master
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
				String newRemoteFile = remoteFile + "\\" + file.getName();
				client.makeDirectory(newRemoteFile);
				for (File f : files) {
					loadFiles(client, f.getAbsolutePath(), newRemoteFile);
				}
			} else {
				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
				String remotePath = remoteFile + "\\" + file.getName();
				FTPFile[] existingFiles = client.listFiles(remotePath);
				if (existingFiles.length > 0) {
					int i = 1;
					do {
						remotePath = remoteFile + "\\" + FilenameUtils.getBaseName(file.getName()) + " (" + i + ")." + FilenameUtils.getExtension(file.getName());
						existingFiles = client.listFiles(remotePath);
					} while (existingFiles.length > 0);
				}
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

				downloadFiles(client, newFolder.getAbsolutePath(), remoteFile + "\\" + f.getName());
			} else {
				downloadFile = new File(localFile, f.getName());
				if (downloadFile.exists()) {
					int i = 1;
					while (downloadFile.exists()) {
						downloadFile = new File(localFile, FilenameUtils.getBaseName(f.getName()) + " (" + i + ")." + FilenameUtils.getExtension(f.getName()));
						i++;
					}
				}

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
