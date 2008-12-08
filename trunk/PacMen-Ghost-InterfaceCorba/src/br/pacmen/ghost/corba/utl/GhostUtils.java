package br.pacmen.ghost.corba.utl;


import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import br.pacmen.ghost.corba.comm.GhostServer;
import br.pacmen.ghost.corba.comm.GhostServerHelper;
import br.pacmen.ghost.corba.comm.GhostServerPOA;



public class GhostUtils {
	
	private static String C_ROOT = "RootPOA"
				 		,C_NAME_SERVICE = "NameService"
				 		,C_GHOST_SERVICE = "Ghost";

	public static void initializeServer(GhostServerPOA impl) throws InvalidName, AdapterInactive, ServantNotActive, WrongPolicy, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed  {
		// Cria e inicializa o ORB
		ORB orb = ORB.init(new String[] {}, null);

		// Ativa o POA
		POA rootpoa = POAHelper.narrow(orb
				.resolve_initial_references(C_ROOT));
		rootpoa.the_POAManager().activate();

		// Pega a referência do servidor
		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
		GhostServer href = GhostServerHelper.narrow(ref);

		// Obtém uma referência para o servidor de nomes
		org.omg.CORBA.Object objRef = orb
				.resolve_initial_references(C_NAME_SERVICE);
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

		// Registra o servidor no servico de nomes
		String name = C_GHOST_SERVICE;
		NameComponent path[] = ncRef.to_name(name);
		ncRef.rebind(path, href);

		System.out.println("Servidor aguardando requisicoes ....");

		// Aguarda chamadas dos clientes
		orb.run();
	}

	public static GhostServer connectToServer(String host) throws InvalidName, NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
		// Cria e inicializa o ORB
		ORB orb = ORB.init(new String[] {"–ORBInitialPort", "2000", "-ORBInitialHost", host}, null);

		// Obtem referencia para o servico de nomes
		org.omg.CORBA.Object objRef = orb
				.resolve_initial_references(C_NAME_SERVICE);
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

		// Obtem referencia para o servidor
		return GhostServerHelper.narrow(ncRef.resolve_str(C_GHOST_SERVICE));
	}

}
