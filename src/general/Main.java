package general;

import interfaces.Login;
import interfaces.Splash;

public class Main {
	public static void main(String[] args) {
		Splash Splash = new Splash();
		Splash.run();
		Login log = new Login();
		ConnectionToDatabase connect = new ConnectionToDatabase();
		LoginController control = new LoginController(log, connect);
		control.addListeners();

	}
}
