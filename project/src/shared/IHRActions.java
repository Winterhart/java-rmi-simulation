package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;
import model.*;

public interface IHRActions extends Remote {
	
	
	
	/**
	 * 
	 * When a manager invokes this method from his/her center through a client program
	 *	called ManagerClient, the server associated with this manager
	 *	attempts to create a ManagerRecord with the information
	 *	passed
	 * 
	 * @param firstName
	 * @param lastName
	 * @param employeeID
	 * @param mailID
	 * @param projects
	 * @return The server returns information to the manager whether the operation
	 *	was successful or not and both the server
	 * @throws RemoteException
	 */
	public Manager createMRecord(String firstName, 
			String lastName, 
			String employeeID, 
			String mailID, 
			List<Project> projects) throws RemoteException;
	
	
	/**
	 * When a manager invokes this method from a ManagerClient, the server associated with
	 *	this manager
	 * @param firstName
	 * @param lastName
	 * @param employeeID
	 * @param mailID
	 * @param ProjectID
	 * @return The server returns information to the manager whether the operation was successful or not
	 * @throws RemoteException
	 */
	public Employee createERecord(String firstName, 
			String lastName, 
			String employeeID,
			String mailID, 
			String ProjectID)throws RemoteException;
	
	/**
	 * Finds out the number of records (both MR and ER)
	 * 
	 * @return it should return the following: CA 6, US 7, UK 8.
	 */
	public String getRecordCount() throws RemoteException;
	
	/**
	 * searches in the hash map to find the recordID and change the
	 *	value of the field identified by “fieldname” to the newValue, if it is found.
	 * 
	 * 
	 * @param recordID
	 * @param fieldName
	 * @param value
	 * @return Upon success
		or failure, it returns a message to the manager and the logs are updated
	 */
	public boolean editRecord(String recordID, String fieldName, Object value) throws RemoteException;

}
