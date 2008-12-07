package ctl;

import bo.WorldBO;

public class PacManVO extends Actor {
	
	public PacManVO(WorldBO worldBO, short id, Coordinate pos) {
		super(worldBO, id, pos);
	}

	@Override
	protected void perform(long msElapsed) {
	}

	@Override
	protected void prepare() {
	}
	
}
