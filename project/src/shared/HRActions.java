package shared;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.Employee;
import model.Manager;
import model.Project;

public class HRActions extends UnicastRemoteObject implements IHRActions {

	public HRActions() throws RemoteException {
		super();
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

}
