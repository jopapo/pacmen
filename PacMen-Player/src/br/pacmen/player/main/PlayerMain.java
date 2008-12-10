package br.pacmen.player.main;

import javax.swing.JOptionPane;

import netbula.ORPC.rpc_err;


/**
 * Classe exemplo de como implementar o jogo
 * 
 * @author Karlyson Schubert Vargas
 * @version 2.0
 */

@SuppressWarnings("serial")
public class PlayerMain {

	public static void main(String args[]) {
		String host = JOptionPane.showInputDialog(null, "Digite o endereço do servidor de mundos do Pacmen:", "localhost");
 		if (host != null)
			try {
				new PlayerGUI(host).getPacManGame().esperaInit();
			} catch (rpc_err e) {
				JOptionPane.showMessageDialog(null, "Impossível conectar ao servidor.\n\nErro: ".concat(e.getMessage()));
				e.printStackTrace();
			}
	}
	
}
