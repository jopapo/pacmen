package ctl;

public class Coordinate {
	
	private short x, y;
	
	public Coordinate(short x, short y){
		this.x = x;
		this.y = y;
	}
	
	public short getX(){
		return x;
	}
	
	public short getY(){
		return y;
	}
	
	public void setX(short aux){
		x = aux;
	}
	
	public void setY(short aux){
		y = aux;
	}
	
	public String toString() {
		return x + "," + y;
	}

	public boolean equals(Coordinate coo) {
		return (x == coo.x && y == coo.y);
	}
}
