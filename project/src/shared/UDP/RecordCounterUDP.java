package shared.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import shared.IHRActions;

public class RecordCounterUDP extends ServerUDP implements Runnable {

	public RecordCounterUDP(IHRActions serverInstance, int portUDP) {
		super(serverInstance, portUDP);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		DatagramSocket sock = null;
		try {
			byte[] buffer = new byte[256];
			sock = new DatagramSocket(super.getAssignedPort());
	        while (listen) {
	        	//buffer = localInstance.getLocalNumberOfRecords();
	        	DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
	        	sock.receive(packet);
	        	InetAddress add = packet.getAddress();
	        	buffer = localInstance.getLocalNumberOfRecords("FakeManagerID");
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
