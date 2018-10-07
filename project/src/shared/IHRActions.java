package shared;

import java.net.DatagramSocket;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;
import model.*;
import storage.IStore;

public interface IHRActions extends Remote {
	
	IStore store = null;


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
	 * @param location
	 * @return The server returns information to the manager whether the operation
	 *	was successful or not and both the server
	 * @throws RemoteException
	 */
	public String createMRecord(String firstName, 
			String lastName, 
			String employeeID, 
			String mailID, 
			List<Project> projects, Location location) throws RemoteException;
	
	
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
	public String createERecord(String firstName, 
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
	
	public String createProject(String projectID, String clientName, String projectName) throws RemoteException;
	
	

}
