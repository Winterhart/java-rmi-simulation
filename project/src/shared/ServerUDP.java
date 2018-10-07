package shared;

import storage.IStore;

public class ServerUDP implements IServerUDP {
	
	private IHRActions localInstance;
	private IStore serverStore;
	public ServerUDP(IHRActions serverInstance) {
		this.localInstance = serverInstance;
		this.serverStore = localInstance.store;
	}
	@Override
	public String startUDPServer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
