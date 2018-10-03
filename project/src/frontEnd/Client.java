package frontEnd;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.Employee;
import model.Project;
import shared.IHRActions;

public class Client {

	//TODO: System must identify the managerID and init the right
	// registry based on data...
	
	//TODO: A log file is written on the client side for each user in
	// the same folder
	public static void main(String[] args) throws Exception {
		Registry reg = LocateRegistry.getRegistry(5555); 
		IHRActions sum = (IHRActions) reg.lookup("CA");
		String projct= sum.createProject("P00001", "Marcel Foobar", "AnotherBlockChainSoftware");
		String empID = sum.createERecord("Bob", "Bobinson", "ER20321", "bob.bobinson@bobbob.com", "P00001");
		System.out.println("Client is off... ");
		

	}

}
