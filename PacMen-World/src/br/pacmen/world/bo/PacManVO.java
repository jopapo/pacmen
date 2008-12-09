package br.pacmen.world.bo;

import br.pacmen.world.bo.err.EPacMenException;

public class PacManVO extends Actor {

	public PacManVO(World world, short id) throws EPacMenException {
		super(world, id);
	}

	@Override
	protected void perform(long msElapsed) {
	}

	@Override
	protected void prepare() {
	}

}
