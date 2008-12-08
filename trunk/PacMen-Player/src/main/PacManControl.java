package main;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import mapa.Mapa;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import components.Coordenada;

/**
 * Classe principal para controle do jogo
 * 
 * @author Karlyson Schubert Vargas
 * @version 2.0
 */
public class PacManControl extends GameObject {

	private SpriteGroup groupTabuleiro, groupPacMan, groupGhost;

	private PlayField play;

	private Coordenada posPacMan, posGhost, posCozinha;

	private List<Coordenada> cheatPoints = new ArrayList<Coordenada>();

	private int fome;

	public final int tempoInicial = 20;

	public final int tempoExtra = 2;

	private int[][] tabuleiro = new int[50][50];

	private Object syncEsperaInit = new Object();

	private boolean looser = false;

	private GameFont font;
	
	private boolean pausa;

	public PacManControl(GameEngine game) {
		super(game);
	}

	public void initResources() {
		pausa = false;
		inicializaAtributos();
		leTabuleiro();
		posicaoInicial();
		play.addGroup(groupTabuleiro);
		play.addGroup(groupPacMan);
		play.addGroup(groupGhost);

		synchronized (syncEsperaInit) {
			syncEsperaInit.notify();
		}
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

	private void posicaoInicial() {
		fome = tempoInicial;
		posPacMan = new Coordenada(1, 1);
		posGhost = new Coordenada(47, 1);
		Sprite sprite = new Sprite(getImage("images/phastasm.png"), 47 * 14,
				1 * 14);
		groupGhost.add(sprite);
		sprite = new Sprite(getImage("images/pacRight.png"), 1 * 14, 1 * 14);
		groupPacMan.add(sprite);
	}

	public void render(Graphics2D g2d) {
		play.render(g2d);
		font.drawString(g2d, "fome: " + getFome(), 30, 45);
		if(estaPausado()){
			font.drawString(g2d, "PAUSADO", 300,350);
		}
		if (looser) {
			font.drawString(g2d, "VOCE PERDEU", 250, 250);
			font.drawString(g2d, "PRESSIONE ESC PRA SAIR", 180, 300);
			font.drawString(g2d, "PRESSIONE ENTER PRA REINICIAR", 120, 350);
			font.drawString(g2d, "DESIGN E ENGINE: KARLYSON SCHUBERT VARGAS", 25, 450);
			font.drawString(g2d, "IA: FERNANDA GUMS E JOAO PAULO POFFO", 55, 500);			
		}
	}

	public void update(long elapsedTime) {
		play.update(elapsedTime);
		
		
		if (!looser) {
			
//			Se pressionar P pausa o jogo ou liberar do pause
			if (keyPressed(KeyEvent.VK_P)) {
	            setPausado(!estaPausado());
	        }
	        if (estaPausado()) {
	            return;
	        }
			
			if (keyPressed(KeyEvent.VK_LEFT)) {
				if (tabuleiro[posPacMan.getX() - 1][posPacMan.getY()] == 1) {
					posPacMan.setX(posPacMan.getX() - 1);
					groupPacMan.clear();
					Sprite sprite = new Sprite(getImage("images/pacLeft.png"),
							posPacMan.getX() * 14, posPacMan.getY() * 14);
					groupPacMan.add(sprite);
				}
			}
			if (keyPressed(KeyEvent.VK_UP)) {
				if (tabuleiro[posPacMan.getX()][posPacMan.getY() - 1] == 1) {
					posPacMan.setY(posPacMan.getY() - 1);
					groupPacMan.clear();
					Sprite sprite = new Sprite(getImage("images/pacUp.png"),
							posPacMan.getX() * 14, posPacMan.getY() * 14);
					groupPacMan.add(sprite);
				}
			}
			if (keyPressed(KeyEvent.VK_DOWN)) {
				if (tabuleiro[posPacMan.getX()][posPacMan.getY() + 1] == 1) {
					posPacMan.setY(posPacMan.getY() + 1);
					groupPacMan.clear();
					Sprite sprite = new Sprite(getImage("images/pacDown.png"),
							posPacMan.getX() * 14, posPacMan.getY() * 14);
					groupPacMan.add(sprite);
				}
			}
			if (keyPressed(KeyEvent.VK_RIGHT)) {
				if (tabuleiro[posPacMan.getX() + 1][posPacMan.getY()] == 1) {
					posPacMan.setX(posPacMan.getX() + 1);
					groupPacMan.clear();
					Sprite sprite = new Sprite(getImage("images/pacRight.png"),
							posPacMan.getX() * 14, posPacMan.getY() * 14);
					groupPacMan.add(sprite);
				}
			}
			if (keyPressed(KeyEvent.VK_ESCAPE))
				finish();
			if (keyPressed(KeyEvent.VK_ENTER)) {
				if (ehCheat(posPacMan.getX(), posPacMan.getY())) {
					Coordenada newPos = getNewPos();
					posPacMan = newPos;
					groupPacMan.clear();
					Sprite sprite = new Sprite(getImage("images/pacRight.png"),
							posPacMan.getX() * 14, posPacMan.getY() * 14);
					groupPacMan.add(sprite);
				}
			}
		} else {
			if (keyPressed(KeyEvent.VK_ENTER)) {
				groupGhost.clear();
				groupPacMan.clear();
				posicaoInicial();
				looser = false;
				synchronized (syncEsperaInit) {
					syncEsperaInit.notify();
				}
			}
			if (keyPressed(KeyEvent.VK_ESCAPE))
				finish();
		}
	}

	private Coordenada getNewPos() {
		int newX = ((int) (Math.random() * 47)) + 1;
		int newY = ((int) (Math.random() * 47)) + 1;
		if (tabuleiro[newX][newY] == 1) {
			return new Coordenada(newX, newY);
		} else {
			return getNewPos();
		}
	}

	private boolean ehCheat(int x, int y) {
		Iterator<Coordenada> i = cheatPoints.iterator();
		while (i.hasNext()) {
			Coordenada quadrante = i.next();
			if (quadrante.getX() == x && quadrante.getY() == y)
				return true;
		}
		return false;
	}

	private void inicializaAtributos() {
		play = new PlayField();
		font = fontManager.getFont(getImages("images/Font.png", 8, 12));
		groupPacMan = new SpriteGroup("PacMan");
		groupGhost = new SpriteGroup("Phantasm");
		groupTabuleiro = new SpriteGroup("Tabuleiro");
	}
	private void leTabuleiro() {
		try {
			Scanner arquivo = new Scanner(new Mapa().mapa1());
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
					sprite = new Sprite(getImage("images/" + nSprite + ".png"),
							x * 14, y * 14);
					if (nSprite == 1 || nSprite == 6 || nSprite == 4) {
						tabuleiro[x][y] = 1;
					} else {
						tabuleiro[x][y] = 0;
					}
					if (nSprite == 4) {
						posCozinha = new Coordenada(x, y);
					}
					if (nSprite == 6) {
						cheatPoints.add(new Coordenada(x, y));
					}
					groupTabuleiro.add(sprite);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Coordenada getPosGhost() {
		return posGhost;
	}

	public Coordenada getPosCozinha() {
		return posCozinha;
	}

	public Coordenada getPosPacMan() {
		return posPacMan;
	}

	public boolean estaLivre(Coordenada c) {
		return tabuleiro[c.getX()][c.getY()] == 1
				|| tabuleiro[c.getX()][c.getY()] == 4;
	}

	public int[][] getTabuleiro() {
		return tabuleiro;
	}

	public int getFome() {
		return fome;
	}

	public void acaoGhostUp() {
		if (!looser) {
			posGhost.setY(posGhost.getY() - 1);
			recolocaGhost();
		}
	}

	public void acaoGhostDown() {
		if (!looser) {
			posGhost.setY(posGhost.getY() + 1);
			recolocaGhost();
		}
	}

	public void acaoGhostLeft() {
		if (!looser) {
			posGhost.setX(posGhost.getX() - 1);
			recolocaGhost();
			if (posGhost.getX() == posCozinha.getX()
					&& posGhost.getY() == posCozinha.getY()) {
				fome = tempoInicial;
			}
		}
	}

	public void acaoGhostRight() {
		if (!looser) {
			posGhost.setX(posGhost.getX() + 1);
			recolocaGhost();
		}
	}

	private void recolocaGhost() {
		groupGhost.clear();
		Sprite sprite = new Sprite(getImage("images/phastasm.png"), posGhost
				.getX() * 14, posGhost.getY() * 14);
		groupGhost.add(sprite);
		fome += tempoExtra;
		verificaLooser();
		try {
			Thread.sleep(fome);
		} catch (Exception e) {
		}
	}

	private void verificaLooser() {
		if (posGhost.getX() == posPacMan.getX()
				&& posGhost.getY() == posPacMan.getY()) {
			looser = true;
//			Tira o pacman da tela 
			groupPacMan.clear();
		}
		
	}
	
	public boolean getLooser(){
		return looser;
	}
	
	public void setPausado(boolean pausa){
		this.pausa = pausa;
	}
	
	public boolean estaPausado(){
		return pausa;
	}

}
