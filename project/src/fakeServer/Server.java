package fakeServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	
	public static void main(String[] args) throws Exception {
		SumImpl obj = new SumImpl();
		Registry registry = LocateRegistry.createRegistry(1212);
		registry.bind("sum", obj);
		System.out.println("Server is up");
		
	}
}
