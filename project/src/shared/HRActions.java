package shared;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.Employee;
import model.Manager;
import model.Project;
import storage.IStore;

//TODO: Send a log before doing an operation...
//TODO: Implement all operation
//TODO: Create the hashMap based on Last Name first letter
//TODO: Ensure concurrency with Synchronized keyword
//TODO: Implement the UDP/IP method
public class HRActions extends UnicastRemoteObject implements IHRActions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	IStore store;
	public HRActions(IStore storingEngine) throws RemoteException {
		super();
		this.store = storingEngine;
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
