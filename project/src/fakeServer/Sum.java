package fakeServer;

import java.rmi.*;

public interface Sum extends Remote {
	
	public int add(int x, int y) throws RemoteException;
}
