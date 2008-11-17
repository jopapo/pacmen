package ctl;

import org.omg.CORBA.ShortHolder;

import comm.GhostListener;
import comm.GhostServerPOA;


public class GhostServerImpl extends GhostServerPOA {
	
	GhostListener listener;
	
	// Construtor
	public GhostServerImpl() {
		listener = null;		
	}
	
	@Override
	public boolean ghostInfo(short id, ShortHolder x, ShortHolder y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void register(GhostListener listener) {
		this.listener = listener;		
	}
	
	public GhostListener getListener() throws Exception {
		if (listener == null)
			throw new Exception("Nenhum listener registrado!");
		return listener;
	}

}
