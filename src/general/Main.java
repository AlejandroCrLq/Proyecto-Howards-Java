package general;

import interfaces.Login;

public class Main {
	public static void main(String[] args) {
		Login log = new Login();
		ConnectionToDatabase connect = new ConnectionToDatabase();
		LoginController control = new LoginController(log,connect);
		control.addListeners();
	}
}
