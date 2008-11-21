package ctl;

import org.omg.CORBA.ShortHolder;

import comm.GhostServerPOA;


public class GhostServerImpl extends GhostServerPOA {
	
	@Override
	public short ghostInfo(short id, ShortHolder x, ShortHolder y) {
		System.out.println("ghostInfo");
		return 0;
	}

	@Override
	public void pacManInfo(short x, short y) {
		System.out.println("pacManInfo");
	}

	@Override
	public short worldId() {
		System.out.println("worldId");
		return 0;
	}

	@Override
	public void worldInfo(String matrix) {
		System.out.println("worldInfo");
	}

}
