package br.pacmen.world.bo;

public class GhostVO extends Actor {
	
	public GhostVO(World world, short id) {
		super(world, id, null);
	}

	public GhostVO(World world, short id, Coordinate pos) {
		super(world, id, pos);
	}

	@Override
	protected void perform(long msElapsed) {
	}

	@Override
	protected void prepare() {
	}

}
