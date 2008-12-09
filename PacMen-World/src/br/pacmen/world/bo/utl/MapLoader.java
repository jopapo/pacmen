package br.pacmen.world.bo.utl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MapLoader {
	
	private static short ouidControl = 0;
	
	private short ouid;
	private String map;
	private short height, width;
	
	public MapLoader() throws IOException {
		this.ouid = ++ouidControl;
		this.loadFromFile(new File("map/map.pac"));
	}
	
	private void loadFromFile(File file) throws IOException {
		System.out.println("Loading map file: " + file.getCanonicalPath());
		FileReader fr = new FileReader(file);
		try {
			BufferedReader br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
	
			// Lê a primeira linha para pegar largura
			String line = br.readLine();
			if (line == null)
				throw new IOException("File is empty!");

			this.width = (short) (line.length() / 2);
			this.height = 1;
			sb.append(line);
			while ((line = br.readLine()) != null) {
				sb.append(line);
				this.height++;
			}
			this.map = sb.toString();
		} finally {
			fr.close();
		}
			
	}

	public short getOuid() {
		return this.ouid;
	}

	public short getHeight() {
		return this.height;
	}
	
	public short getWidth() {
		return this.width;
	}
	
	public String getMap() {
		return this.map;
	}
	
}
