package shared;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import storage.IStore;

public class ServerUDP extends Thread implements IServerUDP  {
	
	private IHRActions localInstance;
	private IStore serverStore;
	private static final String SERVER_ADDRESS = "locahost";
	private DatagramSocket socket = null;
	public boolean listen = true;
	private int assignedPort = 0;
	
	public ServerUDP(IHRActions serverInstance, int portUDP) {
		this.localInstance = serverInstance;
		this.serverStore = localInstance.store;
		this.setAssignedPort(portUDP);
	}
	@Override
	public String startUDPServer() {
		Run();
		return "UDP Server started...";
	}
	
	public void Run() {
		try {
			byte[] buffer = new byte[1024];
			DatagramSocket sock = new DatagramSocket(assignedPort);
			DatagramPacket pack = new DatagramPacket(buffer, buffer.length);
	        while (listen) {
	        	sock.receive(pack);
	        	String secretPass = pack.toString();
	        	if(secretPass.equals("coucou")) {
		        	buffer = localInstance.getLocalNumberOfRecords();
	        	}

	        	DatagramPacket reply = new DatagramPacket(pack.getData(), pack.getLength(),
	        			pack.getAddress(), pack.getPort());
	        	
	        	sock.send(reply);
	        	
	        }
			
		}catch(Exception ee) {
			ee.printStackTrace();
			serverStore.writeLog("Problem with UDP Server: " + ee.getMessage(), "Log.txt");
		}
	}
	public int getAssignedPort() {
		return assignedPort;
	}
	public void setAssignedPort(int assignedPort) {
		this.assignedPort = assignedPort;
	}
	
	

}
