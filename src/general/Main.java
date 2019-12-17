package general;

import interfaces.Login;
import interfaces.Splash;

public class Main {
	public static void main(String[] args) {
		Splash splash = new Splash();
		Thread splashThread = new Thread(splash);
		splashThread.start();
		Login log = new Login();
		ConnectionToDatabase connect = new ConnectionToDatabase();
		LoginController control = new LoginController(log, connect);
		control.addListeners();
	}


}