package model;

import java.util.HashMap;

public class PortConfiguration {
		
		private static HashMap<Location, Integer> serverConfig;
		
		private PortConfiguration() {}
		public static HashMap<Location, Integer> getConfig(){
			if(serverConfig == null) {
				serverConfig = new HashMap<Location, Integer>();
			}
			return serverConfig;
		}
		
		public static void updateConfig(HashMap<Location, Integer> updateInput) {
			serverConfig = updateInput;
		}
		
}
