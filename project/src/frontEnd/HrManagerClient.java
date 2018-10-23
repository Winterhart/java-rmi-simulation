package frontEnd;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import HrCenterApp.DEMS;
import HrCenterApp.DEMSHelper;

public class HrManagerClient {

	static DEMS demsImpl;
	public static void main(String[] args) {
		System.out.println("------------------------------------  Welcome Legaxy: The new Employee Management System (running with 90s tech) --------------------------------------");
		try {
			
	    	  System.out.println("Start Hello Client" );
		        // create and initialize the ORB
		        ORB orb = ORB.init(args, null);

		        // get the root naming context
		        org.omg.CORBA.Object objRef = 
		            orb.resolve_initial_references("NameService");
		        // Use NamingContextExt instead of NamingContext. This is 
		        // part of the Interoperable naming Service.  
		        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		 
		        // resolve the Object Reference in Naming
		        String name = "CA";
		        demsImpl = DEMSHelper.narrow(ncRef.resolve_str(name));
		        System.out.println(demsImpl.getWelcomeMessage());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
