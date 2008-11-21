package ctl;

import java.awt.Dimension;

import javax.swing.JOptionPane;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

/**
 * 
 * @author 
 *
 */
public class PacManMain extends GameEngine {

	public World world = null;

	/**
	 * Método construtor
	 * Responsável por carregar o jogo de acordo com as caracteristicas solicitadas 
	 */
	public PacManMain() {
		final GameLoader game = new GameLoader();
		game.setup(this, new Dimension(700, 700), false);
		String endereco = solicitaEndereco();
		//JOptionPane.showMessageDialog(null, endereco);
		new Thread() {
			public void run() {
				game.start();
			}
		}.start();
	}
	
	/**
	 * Solicita endereço do servidor para conexão
	 * @return Endereço do servidor para conexão
	 */
	public static String solicitaEndereco(){
		 String inputValue = JOptionPane.showInputDialog("Digite o endereço do servidor para se conectar: ");
		 if(inputValue == null){
			 System.exit(0);
		 }
		 return inputValue;
	}
	
	/**
	 * Cria um objeto da classe PacMainControl e retorna ele
	 */
	public GameObject getGame(int GameID) {
		world = new World(this);
		return world;
	}
	
	/**
	 * 
	 */
	public World getPacManGame() {
		while (world == null) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}
		return world;
	}

	{
		//Se for false mostra o nome da engine utilizada no jogo
		//e a quantidade de frames por segundo, mas não mostra uma "chamada"
		//da engine
		distribute = true;
	}

}
