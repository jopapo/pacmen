package br.pacmen.world.ctl;

import java.util.Collection;
import java.util.Hashtable;

import org.omg.CORBA.ShortHolder;

import br.pacmen.world.bo.Coordinate;
import br.pacmen.world.bo.GhostVO;
import br.pacmen.world.bo.PacManVO;
import br.pacmen.world.bo.err.EPacMenException;
import br.pacmen.world.bo.model.GenericModel;

public class GhostControl extends Thread {
	
	private final static int C_SLEEP = 500; 

	private WorldServerImpl worldServerImpl;
	private Hashtable<String,GhostClientImpl> ghostServerList;
	private Hashtable<Short,GhostVO> ghostList;
	
	public GhostControl(WorldServerImpl worldServerImpl) {
		this.worldServerImpl = worldServerImpl;
		this.ghostServerList = new Hashtable<String,GhostClientImpl>();
		this.ghostList = new Hashtable<Short,GhostVO>();
	}

	public GhostClientImpl addGhostServer(String host) throws Exception {
		GhostClientImpl client = new GhostClientImpl(host);
		client.worldInfo(worldServerImpl.getWorld().getId(), 
				worldServerImpl.getMap(), 
				worldServerImpl.getWorld().getWidth(), 
				worldServerImpl.getWorld().getHeight());
		return ghostServerList.put(host, client);
	}

	public void removeGhostServer(String host) {
		GhostClientImpl client = this.ghostServerList.get(host);
		
		this.ghostList.remove(client);
		this.ghostServerList.remove(host);
	}
	
	public void run() {
		do {

			for (GhostClientImpl client : ghostServerList.values()) {
				short id = client.firstGhostInfo();
				ShortHolder x = new ShortHolder(), y = new ShortHolder();
				while (id != GenericModel.C_INVALID_ID) {
					short ouid = GhostOuid.encode(worldServerImpl.getWorld().getId(), client.getOuid(), id);
					GhostVO g = ghostList.get(ouid);
					if (g == null) {
						g = new GhostVO(worldServerImpl.getWorld(), id);
						ghostList.put(ouid, g);
					}
					id = client.ghostInfo(id, x, y);
					Coordinate c = new Coordinate(x.value,y.value);
					try {
						g.move(c);
					} catch (EPacMenException e) {
						System.err.println(g.toString() + " não pôd mover-se para " + c.toString());
					}
				}
				
			}
			
			try {
				Thread.sleep(C_SLEEP);
			} catch (InterruptedException e) {
			}
			
		} while (true);
	}
	
	public void pacManInfo(PacManVO pac) {
		for (GhostClientImpl client : ghostServerList.values())
			client.pacManInfo(pac.getOuid(), pac.getPos().getX(), pac.getPos().getY());
	}
	
	public Collection<GhostVO> getGhosts() {
		return ghostList.values();
	}

}
