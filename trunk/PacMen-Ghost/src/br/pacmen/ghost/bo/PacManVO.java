package br.pacmen.ghost.bo;

import br.pacmen.world.bo.Actor;
import br.pacmen.world.bo.Coordinate;
import br.pacmen.world.bo.World;



public class PacManVO extends Actor {
	
	public PacManVO(World world, short id, Coordinate pos) {
		super(world, id, pos);
	}

	@Override
	protected void perform(long msElapsed) {
	}

	@Override
	protected void prepare() {
	}
	
}
