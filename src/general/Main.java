package general;

public class Main {
	public static void main(String[] args) {
		Thread hiloSplash= new Thread ( new interfaz.Splash());
		hiloSplash.start();
	}
}
