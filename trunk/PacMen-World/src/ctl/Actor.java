package ctl;

import bo.WorldBO;
import erro.EPacMenException;

public abstract class Actor extends Thread {

	private static int C_TIMING = 1000; 
	
	private Short ouid;
	private Coordinate pos;
	private WorldBO worldBO;
	
	public Actor(WorldBO worldBO) throws EPacMenException {
		this(worldBO, worldBO.getNewOUID());
	}
	
	public Actor(WorldBO worldBO, short id) throws EPacMenException {
		this(worldBO, id, worldBO.getRandomFreePosition());
	}
	
	public Actor(WorldBO worldBO, short id, Coordinate pos) {
		this.worldBO = worldBO;
		this.ouid = id;
		this.pos = pos;
		worldBO.put(this);
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
		if (worldBO.canActorMoveTo(pos)) {
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
	
	protected WorldBO getWorldBO() {
		return this.worldBO;
	}

	public String toString() {
		return this.getClass().getName() +  " " + getOuid() + " in world " + getWorldBO().getId() + " at " + getPos().toString();
	}

	protected abstract void prepare();

	protected abstract void perform(long msElapsed);

}
