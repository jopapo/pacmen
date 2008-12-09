package br.pacmen.player.bo;

import br.pacmen.world.bo.Actor;
import br.pacmen.world.bo.World;

public class GhostVO extends Actor {

	public GhostVO(World world, short id) {
		super(world, id, null);
	}

	@Override
	protected void perform(long msElapsed) {
	}

	@Override
	protected void prepare() {
	}

}
