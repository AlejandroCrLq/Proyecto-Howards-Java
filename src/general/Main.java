package general;

import interfaces.LoginWindow;
import interfaces.Splash;

public class Main {
	public static void main(String[] args) {
		Splash splash = new Splash();
		Thread splashThread = new Thread(splash);
		splashThread.start();
	}
}