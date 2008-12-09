package br.pacmen.world.bo.model;

import java.util.Scanner;
import java.util.StringTokenizer;

import br.pacmen.world.bo.Coordinate;
import br.pacmen.world.bo.err.EPacMenException;


public class WorldModel extends GenericModel {

	private byte[][] map;  

	public WorldModel(short id, String map, short width, short height) {
		super(id);
		this.map = load(map, width, height);
	}
	
	public byte[][] getMap() {
		return this.map;
	}

	private byte[][] load(String map, short width, short height) {
		byte[][] arrMap = new byte[width][height];

		Scanner arquivo = new Scanner(map);
		String s = "";
		s = arquivo.next();
		StringTokenizer st = new StringTokenizer(s, ",");
		for (int x = 0; x < arrMap.length; x++) {
			for (int y = 0; y < arrMap[x].length; y++) {
				if (!st.hasMoreTokens()) {
					s = arquivo.next();
					st = new StringTokenizer(s, ",");
				}
				arrMap[x][y] = Byte.parseByte(st.nextToken());
			}
		}
		return arrMap;
	}

	public short getHeight() {
		return new Integer(this.map.length).shortValue();
	}

	public short getWidth() {
		if (map.length == 0)
			return 0;
		return new Integer(this.map[0].length).shortValue();
	}

	public boolean canActorMoveTo(Coordinate pos) throws EPacMenException {
		try { 
			return map[pos.getX()][pos.getY()] == 1 ||
				map[pos.getX()][pos.getY()] == 4 ||
				map[pos.getX()][pos.getY()] == 6;
		} catch (Exception e) {
			throw new EPacMenException("O tamanho do mapa (" + getHeight() + "/" + getWidth() + ") não possui a posição " + pos.toString());
		}
	}

}
