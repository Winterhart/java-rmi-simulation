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
		boolean status2 = sumeUS.getLoginStatus();
		sumeUS.login("bob");
		System.out.println("Status is: " + status2);
		System.out.println("Client is off... ");
		
		Registry regeUS2 = LocateRegistry.getRegistry(7777); 
		IHRActions sumeUS2 = (IHRActions) regeUS2.lookup("US");
		boolean status = sumeUS2.getLoginStatus();
		System.out.println("Status is: "  + status);
		sumeUS2.sayHello();
		System.out.println("Client is off... ");
		
		Registry regr = LocateRegistry.getRegistry(4444); 
		IHRActions sumr = (IHRActions) regr.lookup("UK");
		sumr.sayHello();
		System.out.println("Client is off... ");
	}

}
