package tests;



import org.junit.jupiter.api.Test;
import HrCenterApp.DEMS;
import frontEnd.HrManagerLauncher;
import model.Location;

class TestConcurentClient {
	String[] args = {"-ORBInitialPort", "1050", "&" };
	@Test
	void testConCurrentClient() {
		
		System.out.println("Start...");
		HrManagerLauncher clientUS= new HrManagerLauncher();
		DEMS demsReturnedUS = clientUS.getServerInstance(args, Location.US);
		
		HrManagerLauncher clientCA = new HrManagerLauncher();
		DEMS demsReturnedCA = clientCA.getServerInstance(args, Location.CA);
		
		HrManagerLauncher clientUK = new HrManagerLauncher();
		DEMS demsReturnedUK = clientUK.getServerInstance(args, Location.UK);
		
		Thread runner1 = new Thread(new ARunner(demsReturnedCA));
		Thread runner2 = new Thread(new ARunner(demsReturnedUS));
		Thread runner3 = new Thread(new ARunner(demsReturnedUK));
		
		runner1.start();
		runner2.start();
		runner3.start();
		
	}
	

	class ARunner implements Runnable {
		DEMS dem;
		ARunner(DEMS dem){
			this.dem = dem;
		}
		@Override
		public void run() {
			System.out.println(dem.getWelcomeMessage());
		}

	}

}
