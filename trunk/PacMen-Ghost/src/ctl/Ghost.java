package ctl;

import bo.WorldBO;
import bo.WorldBO.Movement;
import erro.EPacMenException;

public class Ghost extends Actor {

	public Ghost(WorldBO worldBO) throws EPacMenException {
		super(worldBO);
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
			preparedMovement = WorldBO.createMovement(getPos(), mov);
		} catch (EPacMenException e) {
			preparedMovement = null;
			e.printStackTrace();
		}
	}
	
}
