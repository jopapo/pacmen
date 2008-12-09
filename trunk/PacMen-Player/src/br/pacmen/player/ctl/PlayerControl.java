package br.pacmen.player.ctl;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import netbula.ORPC.rpc_err;
import br.pacmen.player.bo.GhostVO;
import br.pacmen.player.bo.PacMan;
import br.pacmen.world.bo.Coordinate;
import br.pacmen.world.bo.World;
import br.pacmen.world.bo.World.Movement;
import br.pacmen.world.bo.err.EPacMenException;

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
public class PlayerControl extends GameObject {

	private WorldClientImpl client;
	
	private SpriteGroup groupTabuleiro, groupPacMan, groupGhost;

	private PlayField play;

	private PacMan pacMan;

	private int fome;

	public final int tempoInicial = 20;

	public final int tempoExtra = 2;

	private Object syncEsperaInit = new Object();

	private boolean looser = false;

	private GameFont font;
	
	private GhostControl ghostControl;
	
	
	public PlayerControl(GameEngine game, String host) throws rpc_err, EPacMenException {
		super(game);
		// Conecta ao servidor
		this.client = new WorldClientImpl(host);
		this.pacMan = new PacMan(client.getWorld(), this);
		this.ghostControl = new GhostControl(this);
	}

	public void initResources() {
		inicializaAtributos();
		leTabuleiro();
		posicaoInicial();
		play.addGroup(groupTabuleiro);
		play.addGroup(groupPacMan);
		play.addGroup(groupGhost);

		updatePacMan(Movement.LEFT);
		updateGhosts();
		
		synchronized (syncEsperaInit) {
			syncEsperaInit.notify();
		}
		this.ghostControl.start();
	}

	public void updateGhosts() {
		groupGhost.clear();
		for (GhostVO gvo : ghostControl.getGhosts()) {
			groupGhost.add(new Sprite(getImage("images/phastasm.png"), 
					gvo.getPos().getX() * 14, gvo.getPos().getY() * 14));
			
			if (gvo.getPos().equals(pacMan.getPos())) {
				looser = true;
				// Tira o pacman da tela 
				groupPacMan.clear();
			}
		}
	}

	private void updatePacMan(Movement movement) {
		String posName = "pacLeft";
		switch (movement) {
			case DOWN: posName = "pacDown"; break;
			case RIGHT: posName = "pacRight"; break;
			case UP: posName = "pacUp"; break;
		}
		
		groupPacMan.clear();
		Sprite sprite = new Sprite(getImage("images/" + posName + ".png"),
				pacMan.getPos().getX() * 14, pacMan.getPos().getY() * 14);
		groupPacMan.add(sprite);
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
	}

	public void render(Graphics2D g2d) {
		play.render(g2d);
		font.drawString(g2d, "fome: " + getFome(), 30, 45);
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
						
			Movement m = null;
			if (keyPressed(KeyEvent.VK_LEFT))
				m = Movement.LEFT;
			if (keyPressed(KeyEvent.VK_UP))
				m = Movement.UP;
			if (keyPressed(KeyEvent.VK_DOWN))
				m = Movement.DOWN;
			if (keyPressed(KeyEvent.VK_RIGHT))
				m = Movement.RIGHT;
			if (m != null) 
				try {
					pacMan.move(World.createMovement(pacMan.getPos(), m));
					updatePacMan(m);
				} catch (EPacMenException e) {
					System.err.println("Não pode mover para " + m.toString() + ". " + e.getMessage());
				}

			if (keyPressed(KeyEvent.VK_ESCAPE))
				finish();

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

	private void inicializaAtributos() {
		play = new PlayField();
		font = fontManager.getFont(getImages("images/Font.png", 8, 12));
		groupPacMan = new SpriteGroup("PacMan");
		groupGhost = new SpriteGroup("Phantasm");
		groupTabuleiro = new SpriteGroup("Tabuleiro");
	}
	
	private void leTabuleiro() {
		// Monta tabuleiro de sprites
		byte[][] map = client.getWorld().getModel().getMap();
		for (short x = 0; x < map.length; x++)
			for (short y = 0; y < map[x].length; y++) {
				Sprite sprite = new Sprite(getImage("images/" + map[x][y] + ".png"),
						x * 14, y * 14);
				groupTabuleiro.add(sprite);
			}
	}

	public Coordinate getPosPacMan() {
		return pacMan.getPos();
	}

	public boolean estaLivre(Coordinate c) throws EPacMenException {
		return client.getWorld().canActorMoveTo(c);
	}

	public int getFome() {
		return fome;
	}
	
	public boolean getLooser(){
		return looser;
	}

	public WorldClientImpl getClient() {
		return this.client;
	}

}
