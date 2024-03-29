package br.pacmen.world.ctl;

import java.io.IOException;

import netbula.ORPC.rpc_err;


public abstract class WorldServerThread extends Thread {
	
    protected WorldServerImpl serverImpl;
    
    public WorldServerThread() throws IOException {
    	serverImpl = new WorldServerImpl();    	
    }
	
	public WorldServerImpl getServerImpl() {
		return serverImpl;
	}
	
	public void run() {
		try {
			on();
			serverImpl.run();
		} catch (rpc_err e) {
			e.printStackTrace();
			off(e.getMessage());
		}
	}

	public abstract void on();

	public abstract void off(String msg);
		
}
