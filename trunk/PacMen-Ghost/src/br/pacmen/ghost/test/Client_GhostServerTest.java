package br.pacmen.ghost.test;


import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.ShortHolder;

import br.pacmen.ghost.corba.comm.GhostServer;
import br.pacmen.ghost.corba.utl.GhostUtils;
import br.pacmen.world.bo.model.GenericModel;
import br.pacmen.world.map.Mapa1;




public class Client_GhostServerTest {

	GhostServer server;
	
	@Before
	public void setUp() throws Exception {
		server = GhostUtils.connectToServer("localhost");
	}

	@Test
	public void testWorldInfo() {
		server.worldInfo((short) 1, new Mapa1().mapa1(), (short) 50, (short) 50);
	}

	@Test
	public void testGhostInfo() {
		
		for (int i = 0; i < 5; i++) {
			short id = server.firstGhostInfo();
			ShortHolder x = new ShortHolder(), y = new ShortHolder();
			while (id != GenericModel.C_INVALID_ID) {
				System.out.print("Ghost " + id + " pos ");
				id = server.ghostInfo(id, x, y);
				System.out.println(x.value + ", " + y.value);
			};
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testPacManInfo() {
		for (int i = 0; i < 3; i++) {
			server.pacManInfo((short) 1, (short) 18, (short) 13);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void retestGhostInfo() {
		testGhostInfo();
	}

	@Test
	public void testWorldId() {
		server.worldId();
	}

}
