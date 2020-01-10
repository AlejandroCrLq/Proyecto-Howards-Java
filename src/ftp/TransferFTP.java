package ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;

public class TransferFTP {

	private Users user;

	public TransferFTP(Users user, String local, String remote, boolean upload) {

		try {

			ClientFTP client = new ClientFTP(user);

			client.connect();
			if (client.isConnected()) {
				boolean login = client.login();
				if (login) {
					client.enterLocalPassiveMode();
					client.setFileType(FTP.BINARY_FILE_TYPE);
					client.setControlEncoding("UTF-8");

					if (upload) {
						loadFiles(client, local, remote);
					} else {
						downloadFiles(client, local, remote);
					}

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
						remotePath = remoteFile + "\\" + FilenameUtils.getBaseName(file.getName()) + " (" + i + ")."
								+ FilenameUtils.getExtension(file.getName());
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
						downloadFile = new File(localFile, FilenameUtils.getBaseName(f.getName()) + " (" + i + ")."
								+ FilenameUtils.getExtension(f.getName()));
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
