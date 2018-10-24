package shared;

import java.lang.reflect.Field;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.CORBA.ORB;

import Config.PortConfiguration;
import HrCenterApp.DEMSPOA;
import HrCenterApp.DEMSPackage.ServerLocation;
import model.Employee;
import model.Location;
import model.Manager;
import model.Project;
import model.Record;
import storage.IStore;

public class HRActions  extends DEMSPOA implements IHRActions  {

	private String DEFAULT_LOG_FILE = "Log.txt";
	private Map<Integer, ArrayList<Record>> db;
	private List<Project> dbProject;
	private List<String> currentRecordID;
	private List<String> currentProjectID;
	private List<String> currentManagerID;
	IStore store;
	private ORB orb;
	
	public HRActions(IStore storingEngine) {
		super();
		this.store = storingEngine;
		db = new HashMap<Integer, ArrayList<Record>>();
		dbProject = new ArrayList<Project>();
		currentRecordID = new ArrayList<String>();
		currentProjectID = new ArrayList<String>();
		currentManagerID = new ArrayList<String>();
		buildfakeDatabase();
		restoreFromStorage();
	}
	
	public void setORB(ORB orb) {
		this.orb = orb;
	}

	private void restoreFromStorage() {
		
		store.writeLog("Restoring Data from Storage...", DEFAULT_LOG_FILE);
		List<Project> restoredProject = store.restoreProject();
		for (Project project : restoredProject) {
			dbProject.add(project);
			currentProjectID.add(project.getProjectID());
		}
		
		List<Record> restoredRecord = store.restoreRecord();
		store.writeLog("We have " + restoredRecord.size() + " records to restore", DEFAULT_LOG_FILE);
		for(Record record: restoredRecord) {
			currentRecordID.add(record.getRecordID());
		}
		
		for(Record record: restoredRecord) {
			store.writeLog("RecordIndex" + record.getRecordIndex(), DEFAULT_LOG_FILE);
			Integer indexToInsertRecord = (Integer)record.getRecordIndex();
			ArrayList<Record> newListIn = new ArrayList<Record>();
			
			if(db.get(indexToInsertRecord) == null) {
				newListIn.add(record);
				db.replace(indexToInsertRecord, newListIn);
			}else {
				newListIn = db.get(indexToInsertRecord);
				newListIn.add(record);
				db.replace(indexToInsertRecord, newListIn);
			}
		}
		
	}

	/**
	 * Initialize the fake local database 
	 * Add 26 empty list... from A to Z
	 */
	private void buildfakeDatabase() {
		
		store.writeLog("Building Database...", DEFAULT_LOG_FILE);
		
		for(int i = 0; i < 26; i++)
	    {
			ArrayList<Record> list = new ArrayList<Record>();
			db.put(i, list);
	    }
		
	}

	@Override
	public synchronized String createMRecord (String firstName, String lastName, String employeeID, String mailID, String managerID, 
			HrCenterApp.DEMSPackage.Project[] projects, HrCenterApp.DEMSPackage.ServerLocation location, String managerAuthorOfRequest){
		
		
		
		store.writeLog("Attempt to write a new Manager", DEFAULT_LOG_FILE);
		Manager newManager = null;
		try {
			//Validate EmployeeID: (7 letters, "ER" at beginning, and not already taken)
			String employeeIDUpper = employeeID.toUpperCase();
			if(employeeIDUpper.length() != 7 ||
					!employeeIDUpper.startsWith("MR")) {
				return "Wrong Employee ID";
			}
			// If employee already exists... ?
			if(currentRecordID.contains(employeeIDUpper)) {
				return "Manager Already Exists";
			}
			
			
			// Validate Mail
			if(!emailIsNotValid(mailID)) {
				return "Email not valid";
			}
			
			List<String> projectIn = ConvertToInternalProjectObj(projects);
			List<Project> projectToPassIn = new ArrayList<Project>();
			//Validate Project ID: Must already exists
			for(Project proj: dbProject) {
				if(projectIn.contains(proj.getProjectID())) {
					projectToPassIn.add(proj);
				}
			}
			
			Location locationE = null;
			for(Location loc: Location.values()) {
				if(loc.toString().equals(location)) {
					locationE = loc;
				}
			}
			
			newManager = new Manager(employeeIDUpper, generateUniqueManagerId(locationE),
					firstName, lastName, mailID, projectToPassIn, locationE);
			
			//Obtain first Letter of LastName for DB
			String lowerLastName = lastName.toLowerCase();
			lowerLastName = lowerLastName.trim();
			
			Integer indexOfFirstLetter = getIndexFirstLetter(lowerLastName);
			if(indexOfFirstLetter == null) {
				return "Lastname not in correct format";
			}
			
			// Prevent NULL Pointer
			ArrayList<Record> tmpList;
			if(db.get((int)indexOfFirstLetter) == null) {
				tmpList = new ArrayList<Record>();
			}else {
				tmpList = db.get((int)indexOfFirstLetter);
			}
			
			
			if(!tmpList.contains(newManager) && 
					!currentRecordID.contains(newManager.getEmployeeID())
					&& !currentManagerID.contains(newManager.getManagerID())) {
				tmpList.add(newManager);
				currentRecordID.add(newManager.getEmployeeID());
				currentManagerID.add(newManager.getManagerID());
			}
			
			// Add to the hashMap
			db.replace((int)indexOfFirstLetter, tmpList);
			// Add to the storage
			store.addRecord(newManager);
		
			return "Manager created: " + newManager.getEmployeeID();
			
		}catch(Exception ee) {
			
			ee.printStackTrace();
			store.writeLog(ee.getLocalizedMessage(), DEFAULT_LOG_FILE);
			return "Error: " + ee.getMessage();
		}

		

	}

	private List<String> ConvertToInternalProjectObj(HrCenterApp.DEMSPackage.Project[] projects) {
		List<String> createdProject = new ArrayList<String>();
		for(HrCenterApp.DEMSPackage.Project proj: projects) {
			boolean projCreated = createProject(proj.projectID, proj.clientName, proj.projectName);
			if(projCreated) {
				createdProject.add(proj.projectID);
			}
			
		}
		return createdProject;
	}

	private String generateUniqueManagerId(Location location) {
		Random ran = new Random();
		// From 1000 to 9999
		int randomInt = ran.nextInt(8999) + 1000;
		String managerId = location.toString() + Integer.toString(randomInt);
		while(currentManagerID.contains(managerId)) {
			randomInt = ran.nextInt(8999) + 1000;
			managerId = location.toString() + Integer.toString(randomInt);
		}
		
		return managerId;
	}

	@Override
	public synchronized String createERecord (String firstName, String lastName, String employeeID,
			String mailID, String projectID, String managerID){
		
		
		store.writeLog("Attempt to write a new Employee", DEFAULT_LOG_FILE);
		Employee newEmployee = null;
		
		try {
			
			//Validate EmployeeID: (7 letters, "ER" at beginning, and not already taken)
			String employeeIDUpper = employeeID.toUpperCase();
			if(employeeIDUpper.length() != 7 || !employeeIDUpper.startsWith("ER")) {
				store.writeLog("Employee ID not valid", DEFAULT_LOG_FILE);
				return "Wrong EmployeeID format";
			}
			// If employee already exists... ?
			if(currentRecordID.contains(employeeID)) {
				store.writeLog("Employee Already exists", DEFAULT_LOG_FILE);
				return "Employee already exists";
			}
			
			
			// Validate Mail
			if(!emailIsNotValid(mailID)) {
				store.writeLog("Email not in valid format", DEFAULT_LOG_FILE);
				return "Wrong Email format";
			}
			
			//Validate Project ID: Must already exists
			if(!currentProjectID.contains(projectID)) {
				store.writeLog("Project doesn't exists", DEFAULT_LOG_FILE);
				return "Referenced Project doesn't exists";
			}
			
			
			
			newEmployee = new Employee(firstName.trim(), lastName.trim(), employeeID, mailID,
					projectID);
			//Obtain first Letter of LastName for DB
			String lowerLastName = lastName.toLowerCase();
			lowerLastName = lowerLastName.trim();
			
			Integer indexOfFirstLetter = getIndexFirstLetter(lowerLastName);
			if(indexOfFirstLetter == null) {
				return "Wrong format lastname";
			}
			ArrayList<Record> tmpList = new ArrayList<Record>();
			
			if(db.get((int)indexOfFirstLetter) != null) {
				tmpList = db.get((int)indexOfFirstLetter);
			}
		
			
			tmpList.add(newEmployee);
			currentRecordID.add(newEmployee.getEmployeeID());
			
			// Add to the hashMap
			db.replace((int)indexOfFirstLetter, tmpList);
			// Add to the storage
			store.addRecord(newEmployee);
			return "New Employee created: " + newEmployee.getEmployeeID();
			
		}catch(Exception ee) {
			ee.printStackTrace();
			String problem = ee.getMessage();
			store.writeLog("Exception in Create Employee" + problem, DEFAULT_LOG_FILE);
			return "Error: " + ee.getMessage();
		}
		
		//return newEmployee;
	}

	
	private Integer getIndexFirstLetter(String lowerLastName) {
		char firstLetter = lowerLastName.charAt(0);
		int index = 0;
		for(char alpha = 'a'; alpha <= 'z'; alpha++) {
			if(alpha == firstLetter) {
				return index;
			}
			
			index++;
		}
		return null;
	}

	//This regex method is from this website: 
	// https://howtodoinjava.com/regex/java-regex-validate-email-address/
	private boolean emailIsNotValid(String mailID) {
		String regex =  "^(.+)@(.+)$";
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(mailID);
		return mat.matches();
	}

	@Override
	public synchronized String getRecordCounts (String managerID){
		StringBuffer outString = new StringBuffer();
		store.writeLog("Attempt to get number of records in server", DEFAULT_LOG_FILE);
		byte[] localData = null;
			HashMap<Location, Integer> serverConfiguration = PortConfiguration.getConfig();
			for(Location loc: Location.values()) {
				String locName = loc.toString();
				String storageName = store.getStorageName();
				if(!locName.equals(storageName)) {
						int port = serverConfiguration.get(loc) + 1;
						String resp  = getNumberOfRecordsWithServer(port);
						store.writeLog("Append with " + resp, DEFAULT_LOG_FILE);
						outString.append(resp);
						outString.append(" ");
					
				}else {

					try {
						localData = getLocalNumberOfRecords(managerID);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
					
					String localString = null;
					try {
						localString = new String(localData, StandardCharsets.UTF_8);
					}catch(Exception ee) {
						store.writeLog("!!!!!! ", DEFAULT_LOG_FILE);
					}

					outString.append(localString);
					outString.append(" ");
					store.writeLog("Append with " + localString, DEFAULT_LOG_FILE);
				}
			}
			
			return outString.toString();
	}
				

	private int getNumberOfRecordsHelper() {
		int numberOfRecord = 0;
		for(ArrayList<Record> list: db.values()) {
			if(list != null) {
				numberOfRecord = numberOfRecord + list.size();
			}
		}
		
		return numberOfRecord;
	}

	public synchronized  byte[] getLocalNumberOfRecords(String managerID) {
		store.writeLog("Attempt to get the local number of record", DEFAULT_LOG_FILE);

		byte[] data = null;

		int numberOfRecord =  getNumberOfRecordsHelper();
		String dd = store.getStorageName() + ": " +  Integer.toString(numberOfRecord);
		store.writeLog("Local Number of Record " + dd, DEFAULT_LOG_FILE);
		try {
			
			data = dd.getBytes("UTF-8");
		}catch(Exception ee) {
			ee.printStackTrace();
			store.writeLog("Attempt to get the local, ERROR " + ee.getMessage(), DEFAULT_LOG_FILE);
		}
		return data;
	}
	/**
	 * This code is inspired by this tutorial:
	 * https://www.baeldung.com/udp-in-java
	 * @param port
	 * @return
	 */
	private String getNumberOfRecordsWithServer(int port) {
		String returningString = "";
		store.writeLog("Attempt to get Record on port " + port, DEFAULT_LOG_FILE);
		byte[] buffer = new byte[200];
		DatagramSocket socketData = null;		
		byte[] dataReceived = new byte[200];
		try {
			InetAddress aHost = InetAddress.getByName("localhost");
			socketData = new DatagramSocket();
			DatagramPacket r = new DatagramPacket(buffer, buffer.length, aHost, port);
			socketData.send(r);

			r  = new DatagramPacket(buffer, buffer.length);
			socketData.setSoTimeout(3000);
			socketData.receive(r);
			String dataRe = new String(r.getData(), StandardCharsets.UTF_8);
			store.writeLog("Attempt to get Record on port  dataRe " + dataRe, DEFAULT_LOG_FILE);
			returningString = dataRe.trim();

		}catch(Exception ee) {
			ee.printStackTrace();
		}
		finally {
			if(socketData != null) {
				socketData.close();
			}

		}
		return returningString;
	}
	

	@Override
	public synchronized String editRecord (String recordID, String fieldName, String newValue){
		
		store.writeLog("Attemps to Update a Record...", DEFAULT_LOG_FILE);
		// If the record is not a project/Employee/Manager
		if(!currentRecordID.contains(recordID) && !currentProjectID.contains(recordID)) {
			return "Can't update, the record doesn't exists";
		}
		// At this point, we know we have the record in our system
		// Record ID could be ER20222, MR20494, P20123
		char firstLetter = recordID.toUpperCase().charAt(0);
		switch (firstLetter){
		case 'E':
			Record erecord = FindRecordWithId(recordID);
			if(erecord == null) {
				return "Can't find the Employee";
			}
			return UpdateEmployee(erecord, fieldName, newValue);
		case 'M':
			Record mrecord = FindRecordWithId(recordID);
			if(mrecord == null) {
				return "Can't find the Manager";
			}
			return UpdateManager(mrecord, fieldName, newValue);
		case 'P':
			Project project = FindProjectWithId(recordID);
			if(project== null) {
				return "Can't find the project";
			}
			return UpdateProject(project, fieldName, newValue);
		default:
			store.writeLog("editRecord... recordID Not Found", DEFAULT_LOG_FILE);
			return "Something went wrong";
			
		}
	}

	/**
	 * In the project all field can be updated, except ProjectID which == to RecordID
	 * @param proj
	 * @param fieldName
	 * @param value
	 * @return true if updated
	 */
	private String UpdateProject(Project proj, String fieldName, Object value) {

		
		String[] allowedFields = {"clientName", "projectName"};
		if(!Arrays.asList(allowedFields).contains(fieldName)) {
			return "Field not found" ;
		}
		
		try {
			
			dbProject.remove(proj);
			store.removeProject(proj);
			// Use Reflection to Update Project
			Field targetUpdate = proj.getClass().getDeclaredField(fieldName);
			targetUpdate.setAccessible(true);
			targetUpdate.set(proj, value);
			dbProject.add(proj);
			store.addProject(proj);
			store.writeLog("Project Record Updated", DEFAULT_LOG_FILE);
			return "Project Update";
			
		}catch(Exception ee) {
			ee.printStackTrace();
			store.writeLog("Exception while updating project", DEFAULT_LOG_FILE);
			return "Something went wrong updating project";
		}

	}

	/**
	 * In Manager location, mailID, List of Project
	 * @param mrecord
	 * @param fieldName
	 * @param value
	 * @return  feedback message
	 */
	private String UpdateManager(Record mrecord, String fieldName, String value) {

		
		String[] allowedFields = {"location", "mailID", "currentProjects"};
		if(!Arrays.asList(allowedFields).contains(fieldName)) {
			return "Field not found";
		}
		
		try {
			
			Manager tmpMan = (Manager)mrecord;
			int indexList = getIndexFirstLetter(tmpMan.getLastName().toLowerCase());
			ArrayList<Record>tmpList = db.get(indexList);
			tmpList.remove(mrecord);
			store.removeRecord(mrecord);
			
			Field targetUpdate = mrecord.getClass().getDeclaredField(fieldName);
			targetUpdate.setAccessible(true);
			targetUpdate.set(tmpMan, value);
			
			if(fieldName.equals("location")) {
				Location targetLocation = null;
				// Update the Manager ID with New Location ?
				for(Location loc: Location.values()) {
					if(loc.toString().equalsIgnoreCase(value)) {
						targetLocation = loc;
					}
				}
				tmpMan.setManagerID(generateUniqueManagerId(targetLocation));
				currentManagerID.remove(tmpMan.getManagerID());
				//TODO: Using UDP/IP create a new Manager on the other Server 
			}else {
				// Update internal db
				tmpList.add(tmpMan);
				db.replace(indexList, tmpList);
				store.addRecord(mrecord);
				
			}
			store.writeLog("Manager Record Updated", DEFAULT_LOG_FILE);			
			return "Manager record updated";
			
		} catch (Exception e) {
			store.writeLog("FieldName not found", DEFAULT_LOG_FILE);			
			e.printStackTrace();
			}
		return "Something went wrong";

	}

	/**
	 * In Employee only MailID and projectID can be updated
	 * @param record
	 * @param fieldName
	 * @param value
	 * @return feedback message if updated
	 */
	private String UpdateEmployee(Record record, String fieldName, String value) {

		String[] allowedFields = {"mailID", "projectID"};
		if(!Arrays.asList(allowedFields).contains(fieldName)) {
			return "Field not found";
		}
		
		try {
			

			Employee tmpEmp = (Employee)record;
			
			int indexList = getIndexFirstLetter(tmpEmp.getLastName().toLowerCase());
			ArrayList<Record> tmpList = db.get(indexList);
			tmpList.remove(record);
			store.removeRecord(record);
			
			Field targetUpdate = record.getClass().getDeclaredField(fieldName);
			targetUpdate.setAccessible(true);
			targetUpdate.set(tmpEmp, value);
			
			tmpList.add(tmpEmp);
			store.addRecord(record);
			
			db.replace(indexList, tmpList);
			store.writeLog("Employee Record Updated", DEFAULT_LOG_FILE);
			return "Success field Updated";
			
		}catch(Exception ee) {
			store.writeLog("Problem while updating employee", DEFAULT_LOG_FILE);
			return "Something went wrong in update";
		}
		

	}
	/**
	 * Finding a project record in our db of project
	 * @param recordID
	 * @return
	 */
	private Project FindProjectWithId(String recordID) {
		Project foundProj = null;
		
		for(Project proj: dbProject) {
			if(proj.getProjectID().equals(recordID)) {
				return proj;
			}
		}
		store.writeLog("Project Record Not Found", DEFAULT_LOG_FILE);
		return foundProj;
	}

	/**
	 * Finding a employee or Manager record in our db of Record
	 * @param recordID
	 * @return
	 */
	private Record FindRecordWithId(String recordID) {

		Record foundRec  = null;
		store.writeLog("Collection of " + 		db.values().size() + 
				"sear with : " + recordID, DEFAULT_LOG_FILE);
		for(Integer i: db.keySet()) {
			ArrayList<Record> records = db.get(i);
			store.writeLog("Collection of " + records.size() + 
					" records with key : " + i, DEFAULT_LOG_FILE);
			for(Record r: records) {
				if(recordID.equalsIgnoreCase(r.getRecordID())) {
					store.writeLog("Found a record", DEFAULT_LOG_FILE);
					return r;
				}
			}
		}
		store.writeLog("Employee/Manager Record Not Found", DEFAULT_LOG_FILE);
		return foundRec;
	}

	boolean createProject(String projectID, String clientName, String projectName) {
		store.writeLog("Attempt to write a new Project", DEFAULT_LOG_FILE);
		char firstChar = projectID.toLowerCase().charAt(0);
		if(projectID.length() != 6 || firstChar != 'p') {
			store.writeLog("Wrong project ID format ", DEFAULT_LOG_FILE);
			return false;
		}
		try {
			Project newProj = new Project(projectID, clientName, projectName);
			if(dbProject.contains(newProj)) {
				store.writeLog("Project Already Exists", DEFAULT_LOG_FILE);
			}
			
			dbProject.add(newProj);
			currentProjectID.add(projectID);
			store.addProject(newProj);
			return true;
			
		}catch(Exception ee) {
			store.writeLog("Problem while creating a project", DEFAULT_LOG_FILE);
			ee.printStackTrace();
			return false;
		}
		

	}

	@Override
	public String transferRecord(String managerID, String recordID, ServerLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutdown(String managerID) {
		store.writeLog("Server has been shutdown", DEFAULT_LOG_FILE);
		orb.shutdown(false);
	}

	@Override
	public boolean managerLogin(String managerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getWelcomeMessage(String managerID) {
		StringBuilder welcomeStatus = new StringBuilder();
		welcomeStatus.append("Welcome to : " + this.store.getStorageName() + " center");
		welcomeStatus.append(" currently have " + this.getNumberOfRecordsHelper() + " records");
		return welcomeStatus.toString();
	}

	
	

}
