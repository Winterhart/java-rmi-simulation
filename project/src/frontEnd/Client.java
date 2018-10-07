package frontEnd;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Location;
import model.Project;
import shared.IHRActions;

public class Client {

	//TODO: System must identify the managerID and init the right
	// registry based on data...
	
	//TODO: A log file is written on the client side for each user in
	// the same folder
	
	//TODO: Build basic CLI
	public static void main(String[] args) throws Exception {
		Registry SregCA ;
		Registry SregUS ;
		Registry SregUK ;

		try {
			SregCA = LocateRegistry.getRegistry(5555);
			SregUS = LocateRegistry.getRegistry(7777);
			SregUK = LocateRegistry.getRegistry(4444);
			
			IHRActions regCA = (IHRActions) SregCA.lookup("CA");
			IHRActions regUS = (IHRActions) SregUS.lookup("US");
			IHRActions regUK = (IHRActions) SregUK.lookup("UK");
			
			String response = "";
			System.out.println(response);
			
			response = regCA.createProject("P00001", "Marcel Foobar", "AnotherBlockChainSoftware");
			System.out.println(response);
			response = regCA.createERecord("Bob", "Bobinson", "ER20321", "bob.bobinson@bobbob.com", "P00001");
			System.out.println(response);
			response = regUS.createProject("P00002", "Marcel Foobar2", "AnotherBlockChainSoftware2");
			System.out.println(response);
			response =regUS.createERecord("BobUS", "BobinsonUS", "ER33321", "bob.bobinsonUS@bobbob.com", "P00002");
			System.out.println(response);
			response =regUS.createMRecord("Micheal", "Scoot", "MR44343", "mscott@dundermuflin.com", "P00002", "US");
			System.out.println(response);
			
			
			response =regUK.createProject("P00003", "Duke Foobar", "AnotherBlockChainSoftware");
			System.out.println(response);
			response = regUK.createERecord("Duke", "Dukinson", "ER20321", "DukeDuke@bobbob.com", "P00003");
			System.out.println(response);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}

}