package main;

public class GhostMain {

	public static void main(String args[]) {
		try {
			GhostUtils.initializeServer(args);
			System.out.println("Encerrando o Servidor.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
