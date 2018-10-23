package backEnd;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

import Config.PortConfiguration;
import model.Location;
import shared.HRActions;
import shared.UDP.RecordCounterUDP;
import shared.UDP.ServerUDP;
import shared.UDP.TransfertServerUDP;
import storage.IStore;
import storage.Logger;

public class ServerConfigurator {
	
	private String MAIN_TREE_FOLDER = 
			"/home/winterhart/DEV/SOEN423/java-rmi-simulation/storage/";
	
	private String CENTRAL_REPO_LOCATION =
			"/home/winterhart/DEV/SOEN423/java-rmi-simulation/storage/CENTRAL/";
	
	private IStore configStoring;
	private int CORBA_PORT = 1050;
	
	public ServerConfigurator() {
		 configStoring	= 
				new Logger("ServerConfigurator", CENTRAL_REPO_LOCATION);
	}
	
	 void configureCenter() {
		 
		for(Location loc: Location.values()) {
			switch(loc){
			case CA:
				buildCenter(loc, 5555);
				// Save configuration in an object, in a real server we will save this to a .env file or other
				PortConfiguration.addConfig(loc, CORBA_PORT);
				break;
			case US:
				buildCenter(loc, 7777);
				// Save configuration in an object, in a real server we will save this to a .env file or other
				PortConfiguration.addConfig(loc, CORBA_PORT );
				break;
			case UK:
				buildCenter(loc, 4444);
				// Save configuration in an object, in a real server we will save this to a .env file or other
				PortConfiguration.addConfig(loc, CORBA_PORT);
				break;
			}
	
		}
		
		String startingMessage = "The CORBA Server: " +
				" is on port:  " + CORBA_PORT;

		System.out.println(startingMessage);

		configStoring.writeLog(startingMessage, "CentralRepo.txt");
		
	}
	
	private void buildCenter(Location loca, int port) {
		
		// Create and pass a storing engine to the HRAction
		IStore storingEngine = new Logger(loca.toString(), MAIN_TREE_FOLDER + loca.toString() + "/");
		
		int udpPortCounter = port + 1;
		int udpPortTransfert = port -1;
		try {
			
			HRActions instanceHRAction = new HRActions(storingEngine);
			Thread UDPCounterThread = new Thread(new RecordCounterUDP(instanceHRAction, udpPortCounter));
			Thread UDPTransfertThread  = new Thread(new TransfertServerUDP(instanceHRAction, udpPortTransfert));
			
			// Starting the UDP process
			String udpStartingMessage = "The UDP Server for: " + loca.toString() + 
					" is start on port " + udpPortCounter;
			String udpTransfertMessage = "The UDP Server for transfert record on: " + loca.toString() + 
					" is started on port " + udpPortTransfert;
			
			UDPCounterThread.start();
			UDPTransfertThread.start();
			
			System.out.println(udpStartingMessage);
			System.out.println(udpTransfertMessage);
			
			//Log starting
			configStoring.writeLog(udpStartingMessage, "CentralRepo.txt");
			configStoring.writeLog(udpTransfertMessage, "CentralRepo.txt");			
			
			// Create the ORB Object in the CORBA System
			PortConfiguration.addConfigUDP(loca, udpPortCounter);
			PortConfiguration.addConfigUDPTransfert(loca, udpPortTransfert);


		}catch(Exception ee) {
			ee.printStackTrace();
		}
		
	}
}
