package br.pacmen.ghost.ctl;


import org.omg.CORBA.ShortHolder;

import br.pacmen.ghost.bo.Ghost;
import br.pacmen.ghost.bo.PacManVO;
import br.pacmen.ghost.corba.comm.GhostServerPOA;
import br.pacmen.world.bo.Coordinate;
import br.pacmen.world.bo.World;
import br.pacmen.world.bo.err.EPacMenException;
import br.pacmen.world.bo.model.GenericModel;
import br.pacmen.world.bo.model.WorldModel;
import br.pacmen.world.bo.utl.ClassIterator;



public class GhostServerImpl extends GhostServerPOA {

	private final static int C_GHOST_POPULATION = 10;
	
	private World world;

	@Override
	public short firstGhostInfo() {
		if (!validateWorld())
			return GenericModel.C_INVALID_ID;

		ClassIterator ci = world.getIterator(Ghost.class);
		if ( ci.hasNext() ) {
			Ghost g = (Ghost) ci.next();
			return g.getOuid();
		}

		return GenericModel.C_INVALID_ID;
	}

	private boolean validateWorld() {
		if (world == null) {
			System.out.println("World dont exists yet!");
			return false;
		}
		return true;
	}

	@Override
	public short ghostInfo(short id, ShortHolder x, ShortHolder y) {
		System.out.print("Getting ghost id... ");

		if (!validateWorld())
			return GenericModel.C_INVALID_ID;

		ClassIterator ci = world.getIterator(Ghost.class);
		while ( ci.hasNext() ) {
			Ghost g = (Ghost) ci.next();
 			if (id == g.getOuid()) {
 	 			x.value = g.getPos().getX();
 	 			y.value = g.getPos().getY();
 				System.out.print("Sending from " + g.toString());
 	 			if ( ci.hasNext() ) {
 	 				g = (Ghost) ci.next();
 	 				System.out.println("... going to the next.");
 	 	 			return g.getOuid();
 	 			}
 				System.out.println(" - No next.");
 			}
 		}
		System.out.println("Ghost: id does not exists (" + id + ")");
		return GenericModel.C_INVALID_ID;
	}

	@Override
	public short worldId() {
		System.out.print("Getting world id... ");

		if (!validateWorld())
			return GenericModel.C_INVALID_ID;

		try {
			return world.getId();
		} finally {
			System.out.println("OK!");
		}
	}

	@Override
	public void pacManInfo(short id, short x, short y) {
		System.out.print("Updating pacman... ");

		if (!validateWorld())
			return;

		Coordinate c = new Coordinate(x, y);

		ClassIterator ci = world.getIterator(PacManVO.class);
		while (ci.hasNext()) {
			PacManVO pac = (PacManVO) ci.next();
			if (pac.getOuid() == id) {
				try {
					pac.move(c);
					System.out.println("Position updated!");
				} catch (EPacMenException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		new PacManVO(world, id, c);
		System.out.println("PacMan created!");
	}

	@Override
	public void worldInfo(short id, String map, short width, short height) {
		System.out.print("Creating world... ");
		world = new World(new WorldModel(id, map, width, height));
		System.out.println("Created with id " + world.getId() + "!");
		
		// Atualmente a população de fantasmas é fixa por servidor de Ghosts 
		System.out.print("Populating world with ghosts...");
		
		// No caso, 10.
		for (int i = 1; i <= C_GHOST_POPULATION; i++)
			try {
				new Ghost(world).start();
			} catch (EPacMenException e) {
				e.printStackTrace();
			}
		
		System.out.println("Populated!");
	}

	public World getworld() {
		return world;
	}

}
