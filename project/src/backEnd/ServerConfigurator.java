package backEnd;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

import model.Location;
import model.PortConfiguration;
import shared.HRActions;
import shared.UDP.ServerUDP;
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
			switch(loc){
			case CA:
				buildCenter(loc, 5555);
				break;
			case US:
				buildCenter(loc, 7777);
				break;
			case UK:
				buildCenter(loc, 4444);
				break;
			}
	
		}
		
	}
	
	private void buildCenter(Location loca, int port) {
		
		// Create and pass a storing engine to the HRAction
		IStore storingEngine = new Logger(loca.toString(), MAIN_TREE_FOLDER + loca.toString() + "/");
		int udpPort = port + 1;
		try {
			HRActions instanceHRAction = new HRActions(storingEngine);
			Thread thread = new Thread(new ServerUDP(instanceHRAction, udpPort));
			thread.start();
			// Update configuration of the server
			HashMap<Location, Integer> serverConf = PortConfiguration.getConfig();
			serverConf.put(loca, port);
			PortConfiguration.updateConfig(serverConf);
			Registry reg = LocateRegistry.createRegistry(port);
			//reg.bind(loca.toString(), instanceHRAction);

		}catch(Exception ee) {
			ee.printStackTrace();
		}
		
		String startingMessage = "The Server: " + loca.toString() +
				" is on port:  " + port;
		String udpStartingMessage = "The UDP Server for: " + loca.toString() + 
				" is start on port " + udpPort;
		System.out.println(startingMessage);
		System.out.println(udpStartingMessage);
		configStoring.writeLog(startingMessage, "CentralRepo.txt");
		
	}
}
