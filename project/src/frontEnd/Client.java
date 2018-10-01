package frontEnd;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import shared.IHRActions;

public class Client {

	//TODO: System must identify the managerID and init the right
	// registry based on data...
	
	//TODO: A log file is written on the client side for each user in
	// the same folder
	public static void main(String[] args) throws Exception {
		Registry reg = LocateRegistry.getRegistry(5555); 
		IHRActions sum = (IHRActions) reg.lookup("CA");
		System.out.println("Client is off... ");
		

	}

}
