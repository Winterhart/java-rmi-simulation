package frontEnd;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import HrCenterApp.DEMS;
import HrCenterApp.DEMSHelper;
import HrCenterApp.DEMSPackage.Project;
import model.Location;

public class HrManagerLauncher {
	 static DEMS currentSession = null;
	 private static String loggedInManager;
	 private static Location chosenLocation;
	public static void main(String[] args) {
		System.out.println("------------------------------------  Welcome Legaxy: The new Employee Management System (running with 90s tech) --------------------------------------");
		try {

			
	    	System.out.println("Please choose a server: \n 1) CA \n 2) US \n 3) UK \n");
	    	Scanner scanner = new Scanner(System.in);
	    	int choiceServer = scanner.nextInt();
	    	switch(choiceServer) {
	    		case 1:
	    			currentSession  = getServerInstance(args, Location.CA);
	    			chosenLocation = Location.CA;
	    			break;
	    		case 2:
	    			currentSession  = getServerInstance(args, Location.US);
	    			chosenLocation = Location.US;
	    			break;
	    		case 3:
	    			currentSession  = getServerInstance(args, Location.UK);
	    			chosenLocation = Location.UK;
	    			break;
	    		default:
	    			System.out.println("The Choice: " + choiceServer +" is not on the list :(");
	    			return;
	    	}
	    	
	    	
	    	System.out.println("You must now login as a Manager, please enter your managerId: \n");
	    	Scanner scannerLogin = new Scanner(System.in);
	    	String managerId = scannerLogin.nextLine();
	    	System.out.println("\n Attempt to login with : " + managerId);
	    	
	    	boolean loginSuccessful = currentSession.managerLogin(managerId);
	    	if(loginSuccessful) {
	    			loggedInManager = managerId;
	    			printLogo();
	    			System.out.println("\n");
	    			System.out.println(currentSession.getWelcomeMessage(managerId));
	    			while(true) {
		    			System.out.println("\n \n Choose your action: \n \n"
		    					+ "1) Create Manager \n"
		    					+ "2) Create Employee \n"
		    					+ "3) Number of Record on Servers \n"
		    					+ "4) Change a Record \n"
		    					+ "5) Print all Records on current server \n"
		    					+ "6) Print all Projects on current server \n"
		    					+ "7) Transfert a Record to another server \n"
		    					+ "8) Shutdown the Server CA \n"
		    					+ "9) Exit \n \n");
		    			
		    	    	Scanner scannerChoi = new Scanner(System.in);
		    	    	int choiceAction = scannerChoi.nextInt();

		    			switch(choiceAction) {
		    			case 1:
		    				createManager();
		    				break;
		    			case 2:
		    				createEmployee();
		    				break;
		    			case 3:
		    				getNumberRecords();
		    				break;
		    			case 4:
		    				editRecord();
		    				break;
		    			case 5:
		    				printAllRecords();
		    				break;
		    			case 6:
		    				printAllProject();
		    				break;
		    			case 7:
		    				transfertRecord();
		    				break;
		    			case 8:
		    				shutdownServer();
		    				return;
		    			case 9:
		    				System.out.println("\n Bye bye ");
		    				return;
		    			default:
		    				System.out.println("This choice doesn't exists...");
		    				break;
		    			}
		    			
	    			}

	    		
	    	}else {
				System.out.println("\n Oups managerId: " + managerId + " not found, please retry" );
	    		return;
	    	}
	    	
		} catch (Exception e) {
			System.out.println("Oups wrong input, please retry" );
			e.printStackTrace();
			return;
		} 

	}
	
	private static void shutdownServer() {
		currentSession.shutdown(loggedInManager);
		System.out.println("System off... you will now be logout !\n");
		
	}

	private static void transfertRecord() {
		
		System.out.println("Enter record id: ");
		Scanner scannerrc = new Scanner(System.in);
		String recordId = scannerrc.nextLine();
		List<String> locations = new ArrayList<String>();
		locations.add("UK");
		locations.add("CA");
		locations.add("US");
		
		locations.remove(chosenLocation.toString());
		
		System.out.println("Choose New Location:\n 1) " + locations.get(0) + 
				" \n 2) "  + locations.get(1) + "\n");
		
		Scanner locaChoiceSc = new Scanner(System.in);
		int choiceLocation = locaChoiceSc.nextInt();

		HrCenterApp.DEMSPackage.Location cLocation = null;
		switch(choiceLocation) {
		case 1:
			cLocation = new HrCenterApp.DEMSPackage.Location(locations.get(0));
			break;
		case 2:
			cLocation = new HrCenterApp.DEMSPackage.Location(locations.get(1));
			break;
		default:
			System.out.println("Invalid location choice \n");
		}
		
		String status = currentSession.transferRecord(loggedInManager, recordId, cLocation);
		System.out.println("Server: " + status);
		
	}

	private static void printAllProject() {
		System.out.println(currentSession.printAllProjects());
		
	}

	private static void printAllRecords() {
		System.out.println(currentSession.printAllRecords());
		
	}

	private static void editRecord() {
		System.out.println("Do you want to edit \n 1) Employee \n 2) Manager \n 3) Project \n 4)Done");
		Scanner choice = new Scanner(System.in);
		int choicesEdit = choice.nextInt();

		switch(choicesEdit) {
		case 1:
			System.out.println("Enter record id: ");
			Scanner scannerrc = new Scanner(System.in);
			String recordId = scannerrc.nextLine();
			
			System.out.println("You can edit: location or mailID\n" );
			System.out.println("To change projectID enter (e.g. projectID:P20201\n");
			System.out.println("To change mail enter (e.g. mailID:brandNewMail@mail.com\n");
			Scanner scanner = new Scanner(System.in);
			String changes = scanner.nextLine();
			
			if(changes.contains("mailID")) {
				String[] changesSplit = changes.split(":");
				String status= currentSession.editRecord(recordId, "mailID", changesSplit[1], loggedInManager);
				System.out.println("Current Status: " + status);
			}
			
			if(changes.contains("projectID")) {
				String[] changesSplit = changes.split(":");
				String status= currentSession.editRecord(recordId, "projectID", changesSplit[1], loggedInManager);
				System.out.println("Current Status: " + status);
			}
			break;
		case 2:
			System.out.println("Enter record id: ");
			Scanner scannerrc2 = new Scanner(System.in);
			String recordId2 = scannerrc2.nextLine();
			
			System.out.println("You can edit: location or mailID\n" );
			System.out.println("To change location enter (e.g. location:UK\n");
			System.out.println("To change mail enter (e.g. mailID:brandNewMail@mail.com\n");
			Scanner scanner2 = new Scanner(System.in);
			String changes2 = scanner2.nextLine();
			
			if(changes2.contains("mailID")) {
				String[] changesSplit = changes2.split(":");
				String status= currentSession.editRecord(recordId2, "mailID", changesSplit[1], loggedInManager);
				System.out.println("Current Status: " + status);
			}
			
			if(changes2.contains("location")) {
				String[] changesSplit = changes2.split(":");
				String status= currentSession.editRecord(recordId2, "location", changesSplit[1], loggedInManager);
				System.out.println("Current Status: " + status);
			}
			break;
		case 3:
			System.out.println("Enter record id: ");
			Scanner scannerP = new Scanner(System.in);
			String projectID = scannerP.nextLine();
			
			System.out.println("You can edit: clientName or projectName\n" );
			System.out.println("To change clientName enter (e.g. clientName:Roger BonTemps\n");
			System.out.println("To change projectName enter (e.g. projectName:Tondeuze Electric\n");
			Scanner scannerPc = new Scanner(System.in);
			String changesPc = scannerPc.nextLine();
			
			if(changesPc.contains("clientName")) {
				String[] changesSplit = changesPc.split(":");
				String status= currentSession.editRecord(projectID, "clientName", changesSplit[1], loggedInManager);
				System.out.println("Current Status: " + status);
			}
			
			if(changesPc.contains("projectName")) {
				String[] changesSplit = changesPc.split(":");
				String status= currentSession.editRecord(projectID, "projectName", changesSplit[1], loggedInManager);
				System.out.println("Current Status: " + status);
			}
			break;
		case 4:
			System.out.println("done edit \n");
			return;
		default:
			System.out.println("Oups wrong choice");
			break;
		}
		
		
	}

	private static void getNumberRecords() {
		String status = currentSession.getRecordCounts(loggedInManager);
		System.out.println("Server: " + status);
		
	}

	private static void createEmployee() {
		System.out.println("Please enter a first name and last name separated by a coma (e.g. Robert, De Niro) \n");
    	Scanner scannerName = new Scanner(System.in);
    	String name = scannerName.nextLine();

    	String[] splittedName = name.split(",");
    	
    	System.out.println("Fistname: " + splittedName[0] + " LastName: " + splittedName[1] + "\n");
    	String fName = splittedName[0];
    	String lName = splittedName[1];
    	
    	
    	System.out.println("Please enter the email: \n" );
    	Scanner scannerEmail = new Scanner(System.in);
    	String email = scannerName.nextLine();

    	
    	System.out.println("Please enter a untaken employee id (e.g. ER12345 ) \n");
    	Scanner scannerEmploID = new Scanner(System.in);
    	String employeeID = scannerName.nextLine();

    	
    	System.out.println("Please enter a current project (e.g. P0000):\n");
    	Scanner scannerProj = new Scanner(System.in);
    	String projectID = scannerProj.nextLine();

    	
    	if(projectID .length() != 6 || projectID .toLowerCase().charAt(0) != 'p'){
    		System.out.println("Oups project id in wrong format");
    		return;
    	}
    	
    	String status = currentSession.createERecord(fName, lName, employeeID, email, projectID, loggedInManager);
    	
    	System.out.println("Server: " +  status);
		
	}

	private static void createManager() {
		System.out.println("Please enter a first name and last name separated by a coma (e.g. Robert, De Niro) \n");
    	Scanner scannerName = new Scanner(System.in);
    	String name = scannerName.nextLine();

    	String[] splittedName = name.split(",");
    	
    	System.out.println("Fistname: " + splittedName[0] + " LastName: " + splittedName[1] + "\n");
    	String fName = splittedName[0];
    	String lName = splittedName[1];
    	
    	
    	System.out.println("Please enter the email: \n" );
    	Scanner scannerEmail = new Scanner(System.in);
    	String email = scannerName.nextLine();

    	
    	System.out.println("Please enter a untaken employee id (e.g. MR12345 ) \n");
    	Scanner scannerEmploID = new Scanner(System.in);
    	String employeeID = scannerName.nextLine();
    	
    	List<HrCenterApp.DEMSPackage.Project> projectsIn = new ArrayList<HrCenterApp.DEMSPackage.Project>();
    	boolean flag = true;
    	while(flag) {
    		System.out.println("Add project to your manager: \n 1) Enter a new project \n 2) You are done");
        	Scanner scannerProjectChoice = new Scanner(System.in);
        	int choice = scannerProjectChoice .nextInt();

        	
        	switch(choice) {
        	case 1:
        		System.out.println("Please enter the project information in this format (e.g. P00000,clientName,projectName");
            	Scanner scannerProject = new Scanner(System.in);
            	String projectInfo = scannerName.nextLine();

            	String[] projectdata = projectInfo.split(",");
            	if(projectdata.length != 3) {
            		System.out.println("Oups not able to catch info");
            		break;
            	}
            	if(projectdata[0].length() != 6 || projectdata[0].toLowerCase().charAt(0) != 'p'){
            		System.out.println("Oups project id in wrong format");
            		break;
            	}
            	HrCenterApp.DEMSPackage.Project proj = new HrCenterApp.DEMSPackage.Project(projectdata[0], projectdata[1], projectdata[2]);
            	projectsIn.add(proj);
            	System.out.println("Project Added !!! \n");
        		break;
        	case 2:
        		flag = false;
        		break;
        	default:
        		System.out.println("Oupss wrong choice");
        		break;
        		
        	}
    	}
    	
    	System.out.println("Launching Request to the Server with your data: \n"
    			+ " \n Firstname:" + fName
    			+ " \n Lastname:" + lName
    			+ " \n Email:" + email
    			+ " \n RecordId:" + employeeID);
    	
    	for(HrCenterApp.DEMSPackage.Project proj: projectsIn) {
    		System.out.println("Project ID: " + proj.projectID + " Client Name: " + proj.clientName 
    				+ " Project Name: " + proj.projectName + "\n");
    	}
    	
    	HrCenterApp.DEMSPackage.Location location = new HrCenterApp.DEMSPackage.Location(chosenLocation.toString());
    	//TODO: Refactor this transfer from list to array that I don't know how to do on Java but that is suppose to be one line in C# or Python
    	Project[] projectsInArr = new Project[projectsIn.size()];
    	int i = 0;
    	for(Project p: projectsIn) {
    		projectsInArr[i] = p;
    		i++;
    	}
		String status = currentSession.createMRecord(
    			fName,
    			lName,
    			employeeID, 
    			email, 
    			projectsInArr, 
    			location, 
    			loggedInManager);
    			
    	System.out.println("\n Server: " + status);
		
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
	
	private static void printLogo() {
		System.out.println("  _____    ______   __  __    _____      ____            _   _                \n" + 
				" |  __ \\  |  ____| |  \\/  |  / ____|    / __ \\          | | (_)               \n" + 
				" | |  | | | |__    | \\  / | | (___     | |  | |  _ __   | |  _   _ __     ___ \n" + 
				" | |  | | |  __|   | |\\/| |  \\___ \\    | |  | | | '_ \\  | | | | | '_ \\   / _ \\\n" + 
				" | |__| | | |____  | |  | |  ____) |   | |__| | | | | | | | | | | | | | |  __/\n" + 
				" |_____/  |______| |_|  |_| |_____/     \\____/  |_| |_| |_| |_| |_| |_|  \\___|");
	}

}
