package backend;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.Location;
import shared.HRActions;
import storage.IStore;
import storage.Logger;

public class ServerLauncher {

	public static void main(String[] args) {
		try {
			
			for(Location loc: Location.values()) {
				int randomPort = ((int) (Math.random()*(10000 - 1090))) + 1090;
				// Create and pass a storing engine to the HRAction
				IStore storingEngine = new Logger(loc.toString());
				HRActions instanceHRAction = new HRActions(storingEngine);
				
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
