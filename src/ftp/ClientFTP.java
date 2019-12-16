package ftp;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

import general.ConfigurationData;

public class ClientFTP extends FTPClient {
	private Users user;
	private String sFTP;
	private int port;

	public ClientFTP(Users user) throws IOException {
		this.user = user;

		ConfigurationData config = new ConfigurationData();

		sFTP = config.ReadProperty(ConfigurationData.Property.ftpServer.toString());
		port = Integer.parseInt(config.ReadProperty(ConfigurationData.Property.ftpPort.toString()));
	}

	public void connect() throws SocketException, IOException {
		super.connect(sFTP, port);
	}

	public boolean login() throws IOException {
		return super.login(user.geteMail(), user.getPassword());
	}
}
