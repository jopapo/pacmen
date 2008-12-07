package ctl;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import map.Mapa1;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class World extends GameObject {

	public World(GameEngine game) {
		super(game);
	}

	private int[][] tabuleiro = new int[50][50];
	
	private List<Coordinate> cheatPoints = new ArrayList<Coordinate>();
	
	private SpriteGroup groupTabuleiro, groupPacMan, groupGhost;

	private PlayField play;
	
	private Coordinate posPacMan, posGhost, posCozinha;
	
	private GameFont font;
	
	private boolean pause;
	
	private Object syncEsperaInit = new Object();
	
	private PacManControl pmc;
	
	@Override
	public void initResources() {
		pause = false;
		inicializaAtributos();
		carregaMundo(new Mapa1().mapa1());
		play.addGroup(groupTabuleiro);
		play.addGroup(groupPacMan);
		play.addGroup(groupGhost);

		synchronized (syncEsperaInit) {
			syncEsperaInit.notify();
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		play.render(g2d);
		if(estaPausado()){
			font.drawString(g2d, "PAUSADO", 300,350);
		}
		/*if (looser) {
			font.drawString(g2d, "VOCE PERDEU", 250, 250);
			font.drawString(g2d, "PRESSIONE ESC PRA SAIR", 180, 300);
			font.drawString(g2d, "PRESSIONE ENTER PRA REINICIAR", 120, 350);
			font.drawString(g2d, "DESIGN E ENGINE: KARLYSON SCHUBERT VARGAS", 25, 450);
			font.drawString(g2d, "IA: FERNANDA GUMS E JOAO PAULO POFFO", 55, 500);			
		}*/
	}

	@Override
	public void update(long arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void esperaInit() {
		try {
			synchronized (syncEsperaInit) {
				syncEsperaInit.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void carregaMundo(String mapa) { //   (new Mapa1().mapa1());
		try {
			Scanner arquivo = new Scanner(mapa);
			String s = "";
			s = arquivo.next();
			StringTokenizer st = new StringTokenizer(s, ",");
			for (int y = 0; y < 50; y++) {
				for (int x = 0; x < 50; x++) {
					if (!st.hasMoreTokens()) {
						s = arquivo.next();
						st = new StringTokenizer(s, ",");
					}
					int nSprite = Integer.parseInt(st.nextToken());
					Sprite sprite = null;
					sprite = new Sprite(getImage("images/" + nSprite + ".png"), x * 14, y * 14);
					if (nSprite == 1 || nSprite == 6 || nSprite == 4) {
						tabuleiro[x][y] = 1;
					} else {
						tabuleiro[x][y] = 0;
					}
					if (nSprite == 4) {
						posCozinha = new Coordinate(x, y);
					}
					if (nSprite == 6) {
						cheatPoints.add(new Coordinate(x, y));
					}
					groupTabuleiro.add(sprite);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void inicializaAtributos() {
		play = new PlayField();
		font = fontManager.getFont(getImages("images/Font.png", 8, 12));
		groupPacMan = new SpriteGroup("PacMan");
		groupGhost = new SpriteGroup("Phantasm");
		groupTabuleiro = new SpriteGroup("Tabuleiro");
	}
	
	public boolean estaPausado(){
		return pause;
	}
	
	public void setPausado(boolean pausa){
		this.pause = pausa;
	}
	
	public SpriteGroup getGroupPacMan(){
		return this.groupPacMan;
	}
	
}
