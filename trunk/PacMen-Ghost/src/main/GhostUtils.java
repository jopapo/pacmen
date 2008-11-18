package main;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import comm.GhostServer;
import comm.GhostServerHelper;

import ctl.GhostServerImpl;

public class GhostUtils {
	
	public static void initializeServer(String args[]) throws Exception {
		// Cria e inicializa o ORB
		ORB orb = ORB.init(args, null);

		// Cria a implementação e registra no ORB
		GhostServerImpl impl = new GhostServerImpl();

		// Ativa o POA
		POA rootpoa = POAHelper.narrow(orb
				.resolve_initial_references("RootPOA"));
		rootpoa.the_POAManager().activate();

		// Pega a referência do servidor
		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
		GhostServer href = GhostServerHelper.narrow(ref);

		// Obtém uma referência para o servidor de nomes
		org.omg.CORBA.Object objRef = orb
				.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

		// Registra o servidor no servico de nomes
		String name = "Ghost";
		NameComponent path[] = ncRef.to_name(name);
		ncRef.rebind(path, href);

		System.out.println("Servidor aguardando requisicoes ....");

		// Aguarda chamadas dos clientes
		orb.run();
	}

}
