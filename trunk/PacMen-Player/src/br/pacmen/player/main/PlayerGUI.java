package br.pacmen.player.main;

import java.awt.Dimension;

import javax.swing.JOptionPane;

import netbula.ORPC.rpc_err;
import br.pacmen.player.ctl.PlayerControl;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

/**
 * Classe que inicia o jogo
 * 
 * @author Karlyson Schubert Vargas
 * @version 2.0
 */
public class PlayerGUI extends GameEngine {

	private String host;
	private PlayerControl pacMan = null;

	/**
	 * Método construtor
	 * Responsável por carregar o jogo de acordo com as caracteristicas solicitadas 
	 * @throws rpc_err 
	 */
	public PlayerGUI(String host) throws rpc_err {
		this.host = host;
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
		try {
			pacMan = new PlayerControl(this, host);
		} catch (Exception e) {
			PlayerGUI.fatalCommunicationError("Impossível estabelecer a conexão!", e);
		}
		return pacMan;
	}
	
	public static void fatalCommunicationError(String msg, Exception e) {
		try {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, msg + "\n\nErro: " + e.getMessage());
		} finally {
			System.exit(0);
		}
	}
	
	/**
	 * 
	 */
	public PlayerControl getPacManGame() {
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
