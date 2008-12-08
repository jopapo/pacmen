package br.pacmen.world.bo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import br.pacmen.world.bo.err.EPacMenException;
import br.pacmen.world.bo.model.GenericModel;
import br.pacmen.world.bo.model.WorldModel;
import br.pacmen.world.bo.utl.ClassIterator;


public class World {
	
	/* Enumeração dos movimentos do fantasma */
	public static enum Movement {LEFT, RIGHT, UP, DOWN};

	private Vector<Actor> actorList = new Vector<Actor>();
	
	private WorldModel worldModel;
	
	private short actorOuidControl = 0;
	
	public World(WorldModel worldModel) {
		this.worldModel = worldModel;		
	}

	public short getId() {
		if (worldModel == null)
			return GenericModel.C_INVALID_ID;
		return worldModel.getId();
	}

	public short getNewOUID() {
		return ++actorOuidControl;
	}

	public Coordinate getRandomFreePosition() throws EPacMenException {
		Random rdm = new Random();
		Coordinate pos = null;
		
		// Tenta 25 vezes buscar uma posição estritamente aleatória
		int count = 0;
		do {
			count ++;
			if (count > 25) 
				break;
			pos = new Coordinate((short)rdm.nextInt(worldModel.getHeight()), 
								 (short)rdm.nextInt(worldModel.getWidth()));
			if (canActorMoveTo(pos))
				break;
		} while (true);

		// Caso não tenha achado no processo anterior, monta uma lista pontual
		if (pos == null) {
			ArrayList<Coordinate> alc = new ArrayList<Coordinate>();
			for (short x = 0; x < worldModel.getHeight(); x++)
				for (short y = 0; y < worldModel.getHeight(); y++) {
					Coordinate c = new Coordinate(x,y);
					if (canActorMoveTo(c))
						alc.add(c);
				}
			if (alc.size() > 0)
				pos = alc.get(rdm.nextInt(alc.size() - 1));
		}
		
		if (pos == null)
			throw new EPacMenException("Nenhuma posição disponível para o ator!");
		
		return pos;
	}

	public void put(Actor actor) {
		actorList.addElement(actor);
	}
	
	public boolean canActorMoveTo(Coordinate pos) throws EPacMenException {
		return worldModel.canActorMoveTo(pos);
	}
	
	public short getWidth() {
		return worldModel.getWidth();
	}
	
	public short getHeight() {
		return worldModel.getHeight();
	}
	
	public static Coordinate createMovement(Coordinate org, Movement dst)
			throws EPacMenException {
		switch (dst) {
		case UP:
			return new Coordinate(org.getX(), (short) (org.getY() - 1));
		case DOWN:
			return new Coordinate(org.getX(), (short) (org.getY() + 1));
		case LEFT:
			return new Coordinate((short) (org.getX() - 1), org.getY());
		case RIGHT:
			return new Coordinate((short) (org.getX() + 1), org.getY());
		}
		throw new EPacMenException("Não preparado para movimento especificado!");
	}
	
	public ClassIterator getIterator(Class c) {
		return new ClassIterator(actorList, c);
	}
	
}
