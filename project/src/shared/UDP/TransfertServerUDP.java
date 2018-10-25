package shared.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

import shared.IHRActions;

public class TransfertServerUDP extends ServerUDP implements Runnable {

	public TransfertServerUDP(IHRActions serverInstance, int portUDP) {
		super(serverInstance, portUDP);
	}

	@Override
	public void run() {
		DatagramSocket sock = null;
		try {
			byte[] buffer = new byte[256];
			sock = new DatagramSocket(super.getAssignedPort());
	        while (listen) {
	        	DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
	        	sock.receive(packet);
	        	InetAddress add = packet.getAddress();
	        	String bob = new String(packet.getData(), StandardCharsets.UTF_8);
	        	System.out.println(bob);
	        	DatagramPacket reply = new DatagramPacket(buffer, buffer.length,
	        			add, packet.getPort());
	        	
	        	sock.send(reply);
	        	
	        }
			
		}catch(Exception ee) {
			ee.printStackTrace();
			serverStore.writeLog("Problem with Transfer, UDP Server: " + ee.getMessage(), "Log.txt");
		}
		
	}

	
	

}
