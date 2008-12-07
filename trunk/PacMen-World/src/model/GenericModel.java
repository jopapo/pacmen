package model;

public class GenericModel {
	
	public static short C_INVALID_ID = -1;
	
	private short id;
	
	public GenericModel() {}
	
	public GenericModel(short id) {
		setId(id);
	}

	public short getId() {
		return id;
	}
	
	public void setId(short id) {
		this.id = id;
	}

}
