package components;

/**
 * Classe que possui alguma coordenada do mapa
 * 
 * @author Karlyson Schubert Vargas
 * @version 2.0
 */

public class Coordenada {
	
	private int x, y;
	
	public Coordenada(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int aux){
		x = aux;
	}
	
	public void setY(int aux){
		y = aux;
	}
	
	public String toString() {
		return x + "," + y;
	}

	public boolean equals(Coordenada coo) {
		return (x == coo.x && y == coo.y);
	}
}
