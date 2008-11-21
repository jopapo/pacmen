package ctl;
import java.applet.Applet;


/**
 * 
 * @author ruanita
 *
 */
@SuppressWarnings("serial")
public class PacWorld extends Applet {

	PacManMain main;

	public static void main(String args[]) {
		PacWorld c = new PacWorld();
		c.run();
	}
	
	public void init() {
		new PacWorld().run();		
	}

	public void run() {
		World pmc = new PacManMain().getPacManGame();
		pmc.esperaInit();
		
		//Pausado
		/*if (pmc.estaPausado()){
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
			
		}*/
	}

}
