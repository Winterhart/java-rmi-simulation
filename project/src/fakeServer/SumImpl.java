package fakeServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SumImpl extends UnicastRemoteObject implements Sum {

	protected SumImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int add(int x, int y) throws RemoteException {
		// TODO Auto-generated method stub
		return x + y;
	}
	
	

}
