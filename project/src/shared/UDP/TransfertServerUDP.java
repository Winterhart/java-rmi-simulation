package shared.UDP;

import shared.IHRActions;

public class TransfertServerUDP extends ServerUDP implements Runnable {

	public TransfertServerUDP(IHRActions serverInstance, int portUDP) {
		super(serverInstance, portUDP);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("Running UNIMPLEMENTED transfer with UDP");
		
	}

	
	

}
