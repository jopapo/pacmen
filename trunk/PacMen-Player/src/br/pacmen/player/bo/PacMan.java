package br.pacmen.player.bo;

import netbula.ORPC.rpc_err;
import br.pacmen.player.ctl.PlayerControl;
import br.pacmen.world.bo.Actor;
import br.pacmen.world.bo.Coordinate;
import br.pacmen.world.bo.World;
import br.pacmen.world.bo.err.EPacMenException;
import br.pacmen.world.rpc.comm.st_Actor;
import br.pacmen.world.rpc.comm.st_Status;

public class PacMan extends Actor {
	
	private PlayerControl playerControl;
	
	public PacMan(World world, PlayerControl playerControl) throws EPacMenException {
		super(world);
		this.playerControl = playerControl;
	}

	@Override
	protected void perform(long msElapsed) {
	}

	@Override
	protected void prepare() {
	}
	
	public void move(Coordinate coo) throws EPacMenException {
		super.move(coo);
		
		st_Actor info = new st_Actor();
		info.id = this.getOuid();
		info.x = this.getPos().getX();
		info.y = this.getPos().getY();
		
		try {
			st_Status stat = playerControl.getClient().pacManInfo(info);
		} catch (rpc_err e) {
			e.printStackTrace();
			throw new EPacMenException("Erro ao mandar informações do pacman pro servidor.", e);
		}
	}

}
