package frontEnd;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import shared.IHRActions;

public class Client {

	public static void main(String[] args) throws Exception {
		Registry reg = LocateRegistry.getRegistry(2462); 
		IHRActions sum = (IHRActions) reg.lookup("CA");
		sum.sayHello();
		System.out.println("Client is off... ");
		
		Registry rege = LocateRegistry.getRegistry(2796); 
		IHRActions sume = (IHRActions) rege.lookup("US");
		sume.sayHello();
		System.out.println("Client is off... ");
		
		Registry regr = LocateRegistry.getRegistry(8352); 
		IHRActions sumr = (IHRActions) regr.lookup("UK");
		sumr.sayHello();
		System.out.println("Client is off... ");
	}

}
