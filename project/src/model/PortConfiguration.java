package model;

import java.util.HashMap;

public class PortConfiguration {
		
		private static HashMap<Location, Integer> serverConfig;
		private static HashMap<Location, Integer> udpServerConfig;
		private static HashMap<Location, Integer> udpTransfertConfig;
		
		private PortConfiguration() {}
		public static HashMap<Location, Integer> getConfig(){
			if(serverConfig == null) {
				serverConfig = new HashMap<Location, Integer>();
			}
			return serverConfig;
		}

		public static HashMap<Location, Integer> getUdpConfig(){
			if(udpServerConfig == null){
				udpServerConfig = new HashMap<Location, Integer>();
			}

			return udpServerConfig;
		}

		public static HashMap<Location, Integer> getUdpTransfertConfig(){
			if(udpTransfertConfig == null){
				udpTransfertConfig = new HashMap<Location, Integer>();
			}

			return udpTransfertConfig;
		}
		
		public static void updateConfig(HashMap<Location, Integer> updateInput) {
			serverConfig = updateInput;
		}

		public static void updateUdpConfig(HashMap<Location, Integer> updatedInput){
			udpServerConfig = updatedInput;
		}

		public static void updateUdpTransfert(HashMap<Location, Integer> updateUdp){
			udpTransfertConfig = updateUdp;
		}
		
}
