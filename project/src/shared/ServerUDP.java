package shared;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import storage.IStore;
/**
 * This code is inspired by this tutorial: https://www.baeldung.com/udp-in-java
 * @author winterhart
 *
 */
public class ServerUDP implements Runnable  {
	
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
	public int getAssignedPort() {
		return assignedPort;
	}
	public void setAssignedPort(int assignedPort) {
		this.assignedPort = assignedPort;
	}
	@Override
	public void run() {
		DatagramSocket sock = null;
		try {
			byte[] buffer = new byte[256];
			sock = new DatagramSocket(assignedPort);
	        while (listen) {
	        	//buffer = localInstance.getLocalNumberOfRecords();
	        	DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
	        	sock.receive(packet);
	        	InetAddress add = packet.getAddress();
	        	buffer = localInstance.getLocalNumberOfRecords();
	        	DatagramPacket reply = new DatagramPacket(buffer, buffer.length,
	        			add, packet.getPort());
	        	
	        	sock.send(reply);
	        	
	        }
			
		}catch(Exception ee) {
			ee.printStackTrace();
			serverStore.writeLog("Problem with UDP Server: " + ee.getMessage(), "Log.txt");
		}
		
	}
	
	

}
