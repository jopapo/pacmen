package corba;

import main.GhostUtils;
import map.Mapa1;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.ShortHolder;

import bo.WorldBO;

import comm.GhostServer;
import ctl.Coordinate;


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
			short id = 0;
			ShortHolder x = new ShortHolder(), y = new ShortHolder();
			do {
				System.out.print("Ghost " + id + " pos ");
				id = server.ghostInfo(id, x, y);
				System.out.println(x.value + ", " + y.value);
			} while (id > 0);
			
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
