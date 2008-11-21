package corba;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.ShortHolder;

import comm.GhostServer;
import comm.GhostUtils;


public class Client_GhostServerTest {

	GhostServer server;
	
	@Before
	public void setUp() throws Exception {
		server = GhostUtils.connectToServer("localhost");
	}

	@Test
	public void testGhostInfo() {
		short id = 0;
		ShortHolder x = new ShortHolder(), y = new ShortHolder();
		do {
			id = server.ghostInfo(id, x, y);
		} while (id > 0);
	}

	@Test
	public void testPacManInfo() {
		server.pacManInfo(new Integer(10).shortValue(), new Integer(20).shortValue());
	}

	@Test
	public void testWorldId() {
		server.worldId();
	}

	@Test
	public void testWorldInfo() {
		server.worldInfo("EITA QUE MENSAGEM GRANDE!!! AQUI DEVE ESTAR O MAPA!");
	}

}
