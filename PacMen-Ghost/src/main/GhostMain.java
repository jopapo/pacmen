package main;

import comm.GhostUtils;

import ctl.GhostServerImpl;

public class GhostMain {

	public static void main(String args[]) {
		try {
			GhostUtils.initializeServer(new GhostServerImpl());
			System.out.println("Encerrando o Servidor.");			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
