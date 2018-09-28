package backEnd;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.Location;
import shared.HRActions;
import storage.IStore;
import storage.Logger;

public class ServerConfigurator {
	
	private String MAIN_TREE_FOLDER = 
			"/home/winterhart/DEV/SOEN423/java-rmi-simulation/storage/";
	
	private String CENTRAL_REPO_LOCATION =
			"/home/winterhart/DEV/SOEN423/java-rmi-simulation/storage/CENTRAL/";
	
	private IStore configStoring;
	
	public ServerConfigurator() {
		 configStoring	= 
				new Logger("ServerConfigurator", CENTRAL_REPO_LOCATION);
	}
	
	 void configureCenter() {
		for(Location loc: Location.values()) {
			String locat = loc.toString();
			switch(loc){
			case CA:
				buildCenter(locat, 5555);
				break;
			case US:
				buildCenter(locat, 7777);
				break;
			case UK:
				buildCenter(locat, 4444);
				break;
			}
	
		}
		
	}
	
	private void buildCenter(String loca, int port) {
		
		// Create and pass a storing engine to the HRAction
		IStore storingEngine = new Logger(loca, MAIN_TREE_FOLDER + loca + "/");
		try {
			HRActions instanceHRAction = new HRActions(storingEngine);		
			Registry reg = LocateRegistry.createRegistry(port);
			reg.bind(loca, instanceHRAction);
		}catch(Exception ee) {
			ee.printStackTrace();
		}
		
		String startingMessage = "The Server: " + loca +
				" is on port:  " + port;
		
		System.out.println(startingMessage);		
		configStoring.writeLog(startingMessage, "CentralRepo.txt");
		
	}
}
