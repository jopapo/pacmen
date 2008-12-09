package br.pacmen.player.ctl;

import java.util.Collection;
import java.util.Hashtable;

import netbula.ORPC.rpc_err;
import br.pacmen.player.bo.GhostVO;
import br.pacmen.world.bo.Coordinate;
import br.pacmen.world.bo.err.EPacMenException;
import br.pacmen.world.bo.model.GenericModel;
import br.pacmen.world.rpc.comm.st_ActorAndNext;

public class GhostControl extends Thread {

	private final static int C_SLEEP = 500; 

	private PlayerControl playerControl;
	private Hashtable<Short,GhostVO> ghostList;
	
	public GhostControl(PlayerControl playerControl) {
		this.playerControl = playerControl;
		this.ghostList = new Hashtable<Short,GhostVO>();
	}

	public void run() {
		do {

			short id = GenericModel.C_INVALID_ID;
			do {
				st_ActorAndNext info;
				try {
					info = playerControl.getClient().ghostInfo(id);
					if (info.actor.id != GenericModel.C_INVALID_ID) {
						Short sid = new Short(info.actor.id);
						GhostVO gvo = ghostList.get(sid);
						if (gvo == null) {
							gvo = new GhostVO(playerControl.getClient().getWorld(), info.actor.id);
							ghostList.put(sid, gvo);
						}
						Coordinate c = new Coordinate(info.actor.x, info.actor.y);
						try {
							gvo.move(c);
						} catch (EPacMenException e) {
							System.err.println(gvo.toString() + " não pôde mover-se para " + c.toString());
						}
					}
					id = info.next;
				} catch (rpc_err e) {
					id = GenericModel.C_INVALID_ID;
					e.printStackTrace();
				}
			} while (id != GenericModel.C_INVALID_ID);
			
			playerControl.updateGhosts();
			
			try {
				Thread.sleep(C_SLEEP);
			} catch (InterruptedException e) {
			}
			
		} while (true);
		
	}
	
	public Collection<GhostVO> getGhosts() {
		return this.ghostList.values();
	}
	
}
