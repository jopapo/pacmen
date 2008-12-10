package br.pacmen.world.ctl;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import br.pacmen.world.bo.Coordinate;
import br.pacmen.world.bo.GhostVO;
import br.pacmen.world.bo.PacManVO;
import br.pacmen.world.bo.World;
import br.pacmen.world.bo.err.EPacMenException;
import br.pacmen.world.bo.model.GenericModel;
import br.pacmen.world.bo.model.WorldModel;
import br.pacmen.world.bo.utl.MapLoader;
import br.pacmen.world.rpc.comm.PacMen_svcb;
import br.pacmen.world.rpc.comm.st_Actor;
import br.pacmen.world.rpc.comm.st_ActorAndNext;
import br.pacmen.world.rpc.comm.st_Status;
import br.pacmen.world.rpc.comm.st_World;



public class WorldServerImpl extends PacMen_svcb {
	
	private MapLoader mapLoader;
	private World world;
	private GhostControl ghostControl;
	
	public WorldServerImpl() throws IOException {
		mapLoader = new MapLoader();
		world = new World(new WorldModel(mapLoader.getOuid(), 
				mapLoader.getMap(), mapLoader.getHeight(), mapLoader.getWidth()));
		ghostControl = new GhostControl(this);
		ghostControl.start();
	}

	public GhostControl getGhostControl() {
		return ghostControl;
	}

	@Override
	public st_ActorAndNext ghostInfo(short id) {
		
		st_ActorAndNext info = new st_ActorAndNext();
		info.actor = new st_Actor();
		info.actor.id = GenericModel.C_INVALID_ID;
		info.next = GenericModel.C_INVALID_ID;
		
		// Neste caso o id do ghost será o id do mundo (dois dígitos) + o do ghost (id do ghost)
		Collection<GhostVO> gList = ghostControl.getGhosts();
		for (GhostVO gvo : gList)
			if (info.actor.id != GenericModel.C_INVALID_ID) {
				info.next = gvo.getOuid();
				break;
			} else				
			if ((id == GenericModel.C_INVALID_ID) ||
				(gvo.getOuid() == id)) {
				info.actor.id = gvo.getOuid();
				info.actor.x = gvo.getPos().getX();
				info.actor.y = gvo.getPos().getY();
			}
		return info;
	}

	@Override
	public st_Status pacManInfo(st_Actor info) {
		
		st_Status stat = new st_Status();
		stat.id = 0;
		stat.msg = "";
		
		Iterator iPacMan = world.getIterator(PacManVO.class);
		PacManVO pac = null;
		
		while (iPacMan.hasNext()) {
			PacManVO pacAux = (PacManVO) iPacMan.next();
			if (pacAux.getOuid() == info.id) {
				pac = pacAux;
				break;
			}
		}
		
		Coordinate c = new Coordinate(info.x, info.y);
		try {
			if (pac == null)
				pac = new PacManVO(world, info.id);
			pac.move(c);
			
			// Avisa clients
			ghostControl.pacManInfo(pac);
			
		} catch (EPacMenException e) {
			System.err.println(pac.toString() + " não pôde mover-se para " + c.toString());
		}
			
		return stat;
	}

	@Override
	public st_World worldInfo() {
		st_World wld = new st_World();
		wld.id = world.getId();
		wld.height = world.getHeight();
		wld.width = world.getWidth();
		wld.map = this.getMap(); 
		return wld;
	}
	
	public World getWorld() {
		return world;
	}
	
	public String getMap() {
		return mapLoader.getMap();
	}

}
