package frontEnd;


import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import HrCenterApp.DEMS;
import HrCenterApp.DEMSHelper;
import model.Location;

public class HrManagerLauncher {

	public static void main(String[] args) {
		System.out.println("------------------------------------  Welcome Legaxy: The new Employee Management System (running with 90s tech) --------------------------------------");
		try {
			
			 for(String s : args) {
				 System.out.println(s);
			 }
			 DEMS demsImpl = null;;
	    	  System.out.println("Start Hello Client" );
	    	  demsImpl = getServerInstance(args, Location.CA);
	    	  System.out.println(demsImpl.getWelcomeMessage("fakeManagerId"));
	    	  
	    	  demsImpl = getServerInstance(args, Location.US);
	    	  System.out.println(demsImpl.getWelcomeMessage("fakeManagerId"));
	    	  
	    	  demsImpl = getServerInstance(args, Location.UK);
	    	  System.out.println(demsImpl.getWelcomeMessage("fakeManagerId"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	public static DEMS getServerInstance(String[] args, Location loc) {
        // create and initialize the ORB
        ORB orb = ORB.init(args, null);
        try {
            // get the root naming context
            org.omg.CORBA.Object objRef = 
                orb.resolve_initial_references("NameService");
            // Use NamingContextExt instead of NamingContext. This is 
            // part of the Interoperable naming Service.  
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            // resolve the Object Reference in Naming
    	    return DEMSHelper.narrow(ncRef.resolve_str(loc.toString()));
        	
        }catch(Exception ee) {
        	System.out.println("**** NOT ABLE TO JOIN THE SERVER ****");
        	return null;
        }

	}

}
