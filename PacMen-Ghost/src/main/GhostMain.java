package main;


import ctl.GhostServerImpl;

public class GhostMain {
	
	private static GhostServerImpl server; 

	public static void main(String args[]) {
		try {
			server = new GhostServerImpl();
			GhostUtils.initializeServer(server);
			System.out.println("Encerrando o Servidor.");			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static GhostServerImpl getServer() {
		return server;
	}
}
