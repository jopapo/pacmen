package main;
import java.applet.Applet;

import main.Robo.Movimento;

/**
 * Classe exemplo de como implementar o jogo
 * 
 * @author Karlyson Schubert Vargas
 * @version 2.0
 */

@SuppressWarnings("serial")
public class ComeCome extends Applet {

	PacManMain main;

	public static void main(String args[]) {
		new ComeCome().run();
	}
	
	public void init() {
		new ComeCome().run();		
	}

	public void run() {
		PacManControl pmc = new PacManMain().getPacManGame();
		pmc.esperaInit();
		
		//Pausado
		if (pmc.estaPausado()){
			pmc.setPausado(true);
			while(pmc.estaPausado()){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			pmc.setPausado(false);
		}
		
		while (true) {
			if(!pmc.getLooser()){
				Movimento m = Robo.ia(pmc);
				
				if (m == null)
					continue;			
				switch (m) {
				case ESQUERDA:
					pmc.acaoGhostLeft();
					break;
				case DIREITA:
					pmc.acaoGhostRight();
					break;
				case CIMA:
					pmc.acaoGhostUp();
					break;
				case BAIXO:
					pmc.acaoGhostDown();
					break;
				}
			}
			
		}
	}

}
