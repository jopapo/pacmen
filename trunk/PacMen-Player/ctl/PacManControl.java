package ctl;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

/**
 * Classe principal para controle do jogo
 * 
 * @author Karlyson Schubert Vargas
 * @version 2.0
 */
public class PacManControl {

	private int fome;

	public final int tempoInicial = 20;

	public final int tempoExtra = 2;

	private boolean looser = false;

	Coordenada posPacMan;
	
	SpriteGroup groupPacMan;
	
	World wd;
	
	private void posicaoInicial() {
		this.groupPacMan = wd.getGroupPacMan();
		this.fome = this.tempoInicial;
		posPacMan = getNewPos();
		Sprite sprite = new Sprite(wd.getImage("images/pacRight.png"), posPacMan.getX() * 14, posPacMan.getY() * 14);
		groupPacMan.add(sprite);
	}

	/*	public void update(long elapsedTime) {
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
	}*/

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
	
	
	
	

}
