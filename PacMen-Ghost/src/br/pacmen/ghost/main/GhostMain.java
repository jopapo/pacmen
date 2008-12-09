package br.pacmen.ghost.main;


import br.pacmen.ghost.corba.utl.GhostUtils;
import br.pacmen.ghost.ctl.GhostServerImpl;

public class GhostMain {
	
	public static void main(String args[]) {
		try {
			GhostUtils.startOrbd();
			GhostUtils.initializeServer(new GhostServerImpl());
			System.out.println("Encerrando o Servidor.");			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
