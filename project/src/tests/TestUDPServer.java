package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.jupiter.api.Test;

class TestUDPServer {

	@Test
	void test() {
		byte[] buffer = new byte[200];
		DatagramSocket socketData = null;		
		try {
			InetAddress aHost = InetAddress.getByName("localhost");
			socketData = new DatagramSocket();
			DatagramPacket r = new DatagramPacket(buffer, buffer.length, aHost, 5556);
			socketData.send(r);

			r  = new DatagramPacket(buffer, buffer.length);
			socketData.setSoTimeout(3000);
			socketData.receive(r);
			String dataRe = new String(r.getData(), "US-ASCII");
			System.out.println(dataRe);
			fail("Not yet implemented");

		}catch(Exception ee) {
			ee.printStackTrace();
		}
		finally {
			if(socketData != null) {
				socketData.close();
			}

		}

	}

}
