package fakeServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	
	public static void main(String[] args) throws Exception {
		Registry reg = LocateRegistry.getRegistry(1212); 
		Sum sum = (Sum) reg.lookup("sum");
		int result = sum.add(0, 222);
		System.out.println("Server has compute: " + result);
	}
}
