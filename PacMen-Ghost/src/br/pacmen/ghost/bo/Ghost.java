package br.pacmen.ghost.bo;

import br.pacmen.ghost.ctl.Robo;
import br.pacmen.world.bo.Actor;
import br.pacmen.world.bo.Coordinate;
import br.pacmen.world.bo.World;
import br.pacmen.world.bo.World.Movement;
import br.pacmen.world.bo.err.EPacMenException;

public class Ghost extends Actor {

	public Ghost(World world) throws EPacMenException {
		super(world);
	}

	private Coordinate preparedMovement = null;
	
	@Override
	protected void perform(long msElapsed) {
		try {
			if (preparedMovement == null)
				return;
				
			this.move(preparedMovement);
			
		} catch (EPacMenException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void prepare() {
		setPreparedMovement(Robo.ia(this));
	}

	private void setPreparedMovement(Movement mov) {
		if (mov == null)
			return;
		try {
			preparedMovement = World.createMovement(getPos(), mov);
		} catch (EPacMenException e) {
			preparedMovement = null;
			e.printStackTrace();
		}
	}
	
}
