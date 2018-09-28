package backend;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.Location;
import shared.HRActions;

public class ServerLauncher {

	public static void main(String[] args) {
		try {
			
			for(Location loc: Location.values()) {
				int randomPort = ((int) (Math.random()*(10000 - 1090))) + 1090;
				HRActions instanceHRAction = new HRActions();
				Registry reg = LocateRegistry.createRegistry(randomPort);
				reg.bind(loc.toString(), instanceHRAction);
				System.out.println("The Server: " + loc.toString() +
						" is on port:  " + randomPort );
			}
			
		}catch(Exception ee) {
			ee.printStackTrace();
		}



	}

}
