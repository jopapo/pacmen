package br.pacmen.player.bo;

import netbula.ORPC.rpc_err;
import br.pacmen.player.main.PlayerControl;
import br.pacmen.world.bo.Actor;
import br.pacmen.world.bo.Coordinate;
import br.pacmen.world.bo.World;
import br.pacmen.world.bo.err.EPacMenException;
import br.pacmen.world.rpc.comm.st_Actor;

public class PacManVO extends Actor {
	
	private PlayerControl playerControl;
	
	public PacManVO(World world, PlayerControl playerControl) throws EPacMenException {
		super(world);
		this.playerControl = playerControl;
	}

	public void move(Coordinate coo) throws EPacMenException {
		super.move(coo);
		updateServer();
	}

	private void updateServer() {
		st_Actor info = new st_Actor();
		info.id = this.getOuid();
		info.x = this.getPos().getX();
		info.y = this.getPos().getY();
			
		try {
			playerControl.getClient().pacManInfo(info);
		} catch (rpc_err e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void perform(long msElapsed) {
	}

	@Override
	protected void prepare() {
	}

}
