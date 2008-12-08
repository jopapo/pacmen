package main;

import java.awt.Dimension;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

/**
 * Classe que inicia o jogo
 * 
 * @author Karlyson Schubert Vargas
 * @version 2.0
 */
public class PacManMain extends GameEngine {

	public PacManControl pacMan = null;

	/**
	 * Método construtor
	 * Responsável por carregar o jogo de acordo com as caracteristicas solicitadas 
	 */
	public PacManMain() {
		final GameLoader game = new GameLoader();
		game.setup(this, new Dimension(700, 700), false);
		new Thread() {
			public void run() {
				game.start();
			}
		}.start();
	}
	
	/**
	 * Cria um objeto da classe PacMainControl e retorna ele
	 */
	public GameObject getGame(int GameID) {
		pacMan = new PacManControl(this);
		return pacMan;
	}
	
	/**
	 * 
	 */
	public PacManControl getPacManGame() {
		while (pacMan == null) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}
		return pacMan;
	}

	{
		//Se for false mostra o nome da engine utilizada no jogo
		//e a quantidade de frames por segundo, mas não mostra uma "chamada"
		//da engine
		distribute = true;
	}

}
