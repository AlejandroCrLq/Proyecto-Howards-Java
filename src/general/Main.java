package general;

import interfaces.LoginWindow;
import interfaces.Splash;

public class Main {
	public static void main(String[] args) {
		Splash splash = new Splash();
		Thread splashThread = new Thread(splash);
		splashThread.start();
		LoginWindow log = new LoginWindow();
		ConnectionToDatabase connect = new ConnectionToDatabase();
		LoginController control = new LoginController(log, connect);
		control.addListeners();
	}


}