package br.pacmen.world.ctl;

import org.omg.CORBA.ShortHolder;

import br.pacmen.ghost.corba.comm.GhostServer;
import br.pacmen.ghost.corba.comm.GhostServerOperations;
import br.pacmen.ghost.corba.utl.GhostUtils;




public class GhostClientImpl implements GhostServerOperations {
	
	private static short ouidControl = 0;
	
	private short ouid;
	private GhostServer server;
	
	public GhostClientImpl(String host) throws Exception {
		this.ouid = ++ouidControl;
		this.server = GhostUtils.connectToServer(host);
	}

	@Override
	public short firstGhostInfo() {
		return server.firstGhostInfo();
	}

	@Override
	public short ghostInfo(short id, ShortHolder x, ShortHolder y) {
		return server.ghostInfo(id, x, y);
	}

	@Override
	public void pacManInfo(short id, short x, short y) {
		server.pacManInfo(id, x, y);
	}

	@Override
	public short worldId() {
		return server.worldId();
	}

	@Override
	public void worldInfo(short id, String map, short width, short height) {
		server.worldInfo(id, map, width, height);
	}
	
	public short getOuid() {
		return this.ouid;
	}
	
}
