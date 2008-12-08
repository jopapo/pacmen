package br.pacmen.world.bo;

import br.pacmen.world.bo.err.EPacMenException;


public abstract class Actor extends Thread {

	private static int C_TIMING = 1000; 
	
	private Short ouid;
	private Coordinate pos;
	private World world;
	
	public Actor(World world) throws EPacMenException {
		this(world, world.getNewOUID());
	}
	
	public Actor(World world, short id) throws EPacMenException {
		this(world, id, world.getRandomFreePosition());
	}
	
	public Actor(World world, short id, Coordinate pos) {
		this.world = world;
		this.ouid = id;
		this.pos = pos;
		world.put(this);
	}
	
	public Short getOuid() {
		return ouid;
	}
	
	public Coordinate getPos() {
		return pos;
	}
	
	public void move(Coordinate pos) throws EPacMenException {
		if (this.pos.equals(pos))
			return;
		if (world.canActorMoveTo(pos)) {
			this.pos = pos;
			System.out.println("Moved: " + toString());
		} else
			throw new EPacMenException("Posição ocupada!");
	}
	
	public void run() {
		long timeA = System.currentTimeMillis()
			,timeB;
		
		do {

			timeB = System.currentTimeMillis();
			
			Thread prep = new Thread() {
				public void run() {
					prepare();
				}
			};
			prep.start();

			slep();
			
			try {
				prep.join();
			} catch (InterruptedException e) {
				System.err.println("Erro ao esperar preparar! " + e.getMessage());
			}
			
			perform(timeB - timeA);

			timeA = timeB;

		} while (true);
	}
	
	private void slep() {
		Thread slep = new Thread() {
			public void run() {
				try {
					Thread.sleep(C_TIMING);
				} catch (InterruptedException e) {
					System.err.println("Dormência interrompida! " + e.getMessage());
				}
			}
		};
		slep.start();
		try {
			slep.join();
		} catch (InterruptedException e) {
			System.err.println("Erro ao esperar acordar! " + e.getMessage());
		}
	}
	
	protected World getworld() {
		return this.world;
	}

	public String toString() {
		return this.getClass().getName() +  " " + getOuid() + " in world " + getworld().getId() + " at " + getPos().toString();
	}
	
	public World getWorld() {
		return this.world;
	}

	protected abstract void prepare();

	protected abstract void perform(long msElapsed);

}
