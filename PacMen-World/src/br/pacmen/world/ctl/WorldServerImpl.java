package br.pacmen.world.ctl;

import br.pacmen.world.bo.World;
import br.pacmen.world.bo.model.WorldModel;
import br.pacmen.world.map.Mapa1;
import br.pacmen.world.rpc.comm.PacMen_svcb;
import br.pacmen.world.rpc.comm.st_Actor;
import br.pacmen.world.rpc.comm.st_ActorAndNext;
import br.pacmen.world.rpc.comm.st_Status;
import br.pacmen.world.rpc.comm.st_World;



public class WorldServerImpl extends PacMen_svcb {
	
	private Mapa1 map;
	private World world;
	private GhostControl ghostControl;
	
	public WorldServerImpl() {
		// Fixo pq é o que deu tempo de fazer
		map = new Mapa1();
		world = new World(new WorldModel(map.getId(), map.mapa1(), map.getHeight(), map.getWidth()));
		ghostControl = new GhostControl(this);
		ghostControl.start();
	}

	public GhostControl getGhostControl() {
		return ghostControl;
	}

	@Override
	public st_ActorAndNext ghostInfo(short in_arg) {
		// Neste caso o id do ghost será o id do mundo (dois dígitos) + o do ghost (id do ghost)
		return null;
	}

	@Override
	public st_Status pacManInfo(st_Actor in_arg) {
		return null;
	}

	@Override
	public st_World worldInfo() {
		st_World wld = new st_World();
		wld.id = world.getId();
		wld.height = world.getHeight();
		wld.width = world.getWidth();
		wld.map = map.mapa1(); 
		return wld;
	}
	
	public World getWorld() {
		return world;
	}
	
	public String getMap() {
		return map.mapa1();
	}

}
