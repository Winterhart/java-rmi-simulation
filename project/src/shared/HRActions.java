package shared;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Employee;
import model.Manager;
import model.Project;
import storage.IStore;

//TODO: Send a log before doing an operation...
//TODO: Implement all operation
//TODO: Create the hashMap based on Last Name first letter -> DONE
//TODO: Ensure concurrency with Synchronized keyword -> DONE
//TODO: Implement the UDP/IP method
public class HRActions extends UnicastRemoteObject implements IHRActions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String DEFAULT_LOG_FILE = "Log";
	private Map<Integer, RecordList> db;
	private List<Project> dbProject;
	private List<String> currentRecordID;
	private List<String> currentProjectID;
	IStore store;
	public HRActions(IStore storingEngine) throws RemoteException {
		super();
		this.store = storingEngine;
		db = new HashMap<Integer, RecordList>();
		dbProject = new ArrayList<Project>();
		currentRecordID = new ArrayList<String>();
		currentProjectID = new ArrayList<String>();
		
		buildfakeDatabase();
		restoreFromStorage();
	}

	private void restoreFromStorage() {
		
		store.writeLog("Restoring Data from Storage...", "Log");
		
		//TODO: Restore Project List from txt file
		//TODO: Restore Record List from main recordList
		
	}

	/**
	 * Initialize the fake local database 
	 * Add 26 empty list... from A to Z
	 */
	private void buildfakeDatabase() {
		
		store.writeLog("Building Database...", DEFAULT_LOG_FILE);
		
		for(int i = 0; i < 26; i++)
	    {
			RecordList list = new RecordList();
			db.put(i, list);
			i++;
	    }
		
	}

	@Override
	public synchronized  Manager createMRecord(String firstName, String lastName, 
			String employeeID, String mailID,
			List<Project> projects) throws RemoteException {
		// TODO: Validate New Manager Data, Add it to the hashMap, Log and Store in Text
		return null;
	}

	@Override
	public synchronized  Employee createERecord(String firstName, String lastName, 
			String employeeID, String mailID, String projectID)
			throws RemoteException {
		
		Employee newEmployee = null;
		
		try {
			
			//Validate EmployeeID: (7 letters, "ER" at beginning, and not already taken)
			String employeeIDUpper = employeeID.toUpperCase();
			if(employeeIDUpper.length() != 7 || !employeeIDUpper.startsWith("ER")) {
				return null;
			}
			// If employee already exists... ?
			if(currentRecordID.contains(employeeIDUpper)) {
				return null;
			}
			
			
			// Validate Mail
			if(emailIsNotValid(mailID)) {
				return null;
			}
			
			//Validate Project ID: Must already exists
			if(!currentProjectID.contains(projectID.toUpperCase())) {
				return null;
			}
			
			
			
			newEmployee = new Employee(firstName.trim(), lastName.trim(), employeeIDUpper, mailID,
					projectID);
			//Obtain first Letter of LastName for DB
			String lowerLastName = lastName.toLowerCase();
			lowerLastName = lowerLastName.trim();
			
			Integer indexOfFirstLetter = getIndexFirstLetter(lowerLastName);
			if(indexOfFirstLetter == null) {
				return null;
			}
			
			RecordList tmpList = db.get((int)indexOfFirstLetter);
			
			if(!tmpList.contains(newEmployee)) {
				tmpList.add(newEmployee);
			}
			
			// Add to the hashMap
			db.replace((int)indexOfFirstLetter, tmpList);
			// Add to the storage
			store.addRecord(newEmployee);
			
		}catch(Exception ee) {
			ee.printStackTrace();
			store.writeLog(ee.getLocalizedMessage(), DEFAULT_LOG_FILE);
		}
		
		return newEmployee;
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
	public synchronized  String getRecordCount()  throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized  boolean editRecord(String recordID, String fieldName,
			Object value) throws RemoteException {
		// TODO: Validate Request Data, Add it to the hashMap, Log and Store in Text
		return false;
	}
	
	

}
