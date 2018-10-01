package shared;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	Map<Integer, RecordList> db;
	IStore store;
	public HRActions(IStore storingEngine) throws RemoteException {
		super();
		this.store = storingEngine;
		db = new HashMap<Integer, RecordList>();
		
		// Initialize the fake local database 
		// Add 26 empty list... from A to Z
		buildfakeDatabase();
		
		//TODO: Populate with existing record in .txt file...
	}

	private void buildfakeDatabase() {
		
		int i = 0;
		for(char alphabet = 'a'; alphabet <='z'; alphabet++ )
	    {
			RecordList list = new RecordList();
			db.put(i, list);
			i++;
	    }
		
	}

	@Override
	public synchronized  Manager createMRecord(String firstName, String lastName, String employeeID, String mailID,
			List<Project> projects) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized  Employee createERecord(String firstName, String lastName, String employeeID, String mailID, String ProjectID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized  String getRecordCount()  throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized  boolean editRecord(String recordID, String fieldName, Object value) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
