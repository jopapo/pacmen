package br.pacmen.world.ctl;

public class GhostOuid {

	private short ouid; 
	
	public GhostOuid(short worldOuid, short clientOuid, short ghostOuid) {
		this.ouid = GhostOuid.encode(worldOuid, clientOuid, ghostOuid);
	}
	
	public GhostOuid(short ouid) {
		this.ouid = new Short(ouid);
	}
	
	public static short encode(short worldOuid, short clientOuid, short ghostOuid) {
		return (short) (worldOuid * 10000 + clientOuid * 1000 + ghostOuid);
	}
	
	public short getWorldOuid() {
		return (short) (this.ouid / 10000);
	}
	
	public short getClientOuid() {
		return (short) (this.ouid / 1000 - getWorldOuid());
	}
	
	public short getGhostOuid() {
		return (short) (this.ouid - getClientOuid() - getWorldOuid());
	}
	
}
