package ctl;

import model.GenericModel;
import model.WorldModel;

import org.omg.CORBA.ShortHolder;

import utl.ClassIterator;
import bo.WorldBO;

import comm.GhostServerPOA;

import erro.EPacMenException;

public class GhostServerImpl extends GhostServerPOA {

	private final static int C_GHOST_POPULATION = 1;
	
	private WorldBO worldBO;

	@Override
	public short ghostInfo(short id, ShortHolder x, ShortHolder y) {
		System.out.print("Getting ghost id... ");
		if (worldBO == null) {
			System.out.println("World dont exists yet!");
			return GenericModel.C_INVALID_ID;
		}
		ClassIterator ci = worldBO.getIterator(Ghost.class);
		while ( ci.hasNext() ) {
			Ghost g = (Ghost) ci.next();
 			if ((id <= 0) || (id == g.getOuid())) {
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
		System.out.println("Ghost: send id finished.");
		return GenericModel.C_INVALID_ID;
	}

	@Override
	public short worldId() {
		System.out.print("Getting world id... ");
		try {
			if (worldBO == null)
				return GenericModel.C_INVALID_ID;
			return worldBO.getId();
		} finally {
			System.out.println("OK!");
		}
	}

	@Override
	public void pacManInfo(short id, short x, short y) {
		System.out.print("Updating pacman... ");
		if (worldBO == null) {
			System.out.println("World dont exists yet!");
			return;
		}

		Coordinate c = new Coordinate(x, y);

		ClassIterator ci = worldBO.getIterator(PacManVO.class);
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
		new PacManVO(worldBO, id, c);
		System.out.println("PacMan created!");
	}

	@Override
	public void worldInfo(short id, String map, short width, short height) {
		System.out.print("Creating world... ");
		worldBO = new WorldBO(new WorldModel(id, map, width, height));
		System.out.println("Created with id " + worldBO.getId() + "!");
		
		// Atualmente a população de fantasmas é fixa por servidor de Ghosts 
		System.out.print("Populating world with ghosts...");
		
		// No caso, 10.
		for (int i = 1; i <= C_GHOST_POPULATION; i++)
			try {
				new Ghost(worldBO).start();
			} catch (EPacMenException e) {
				e.printStackTrace();
			}
		
		System.out.println("Populated!");
	}

	public ClassIterator getPacManIterator() {
		return worldBO.getIterator(PacManVO.class);
	}

	public WorldBO getWorldBO() {
		return worldBO;
	}

}
