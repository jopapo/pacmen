package br.pacmen.player.ctl;

import netbula.ORPC.rpc_err;
import br.pacmen.world.bo.World;
import br.pacmen.world.bo.model.WorldModel;
import br.pacmen.world.rpc.comm.PacMen_cln;
import br.pacmen.world.rpc.comm.st_World;

public class WorldClientImpl extends PacMen_cln {

	private World world;

	public WorldClientImpl(String host) throws rpc_err {
		super(host, "tcp");
		this.world = new World(ask4WorldModel()); 
	}

	private WorldModel ask4WorldModel() throws rpc_err {
		st_World worldInfo = this.worldInfo();
		return new WorldModel(worldInfo.id, worldInfo.map, worldInfo.width, worldInfo.height);
	}

	public World getWorld() {
		return this.world;
	}

}
