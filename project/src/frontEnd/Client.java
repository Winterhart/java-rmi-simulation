package frontEnd;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import shared.IHRActions;

public class Client {

	public static void main(String[] args) throws Exception {
		Registry reg = LocateRegistry.getRegistry(5555); 
		IHRActions sum = (IHRActions) reg.lookup("CA");
		sum.sayHello();
		System.out.println("Client is off... ");
		
		Registry regeUS = LocateRegistry.getRegistry(7777); 
		IHRActions sumeUS = (IHRActions) regeUS.lookup("US");
		sumeUS.sayHello();
		System.out.println("Client is off... ");
		
		Registry regeUS2 = LocateRegistry.getRegistry(7777); 
		IHRActions sumeUS2 = (IHRActions) rege.lookup("US");
		sume.sayHello();
		System.out.println("Client is off... ");
		
		Registry regr = LocateRegistry.getRegistry(4444); 
		IHRActions sumr = (IHRActions) regr.lookup("UK");
		sumr.sayHello();
		System.out.println("Client is off... ");
	}

}
