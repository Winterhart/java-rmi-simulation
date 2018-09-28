package shared;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.Employee;
import model.Manager;
import model.Project;
import storage.IStore;

public class HRActions extends UnicastRemoteObject implements IHRActions {

	IStore store;
	private boolean isAuthenticated = false;
	public HRActions(IStore storingEngine) throws RemoteException {
		super();
		this.store = storingEngine;
	}

	@Override
	public Manager createMRecord(String firstName, String lastName, String employeeID, String mailID,
			List<Project> projects) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee createERecord(String firstName, String lastName, String employeeID, String mailID, String ProjectID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRecordCount()  throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editRecord(String recordID, String fieldName, Object value) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String sayHello() throws RemoteException {
		String serverStatus = "--------------------------------------------- " +
					"Welcome to " + store.getStorageName() + " Server ";		
		store.writeLog(serverStatus, "Log.txt");
		return serverStatus;
	}


	@Override
	public String login(String id) throws RemoteException {
		isAuthenticated = true;
		return "Thanks...";
	}

}
