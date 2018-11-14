package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import HrCenterApp.DEMSPackage.Project;
import model.Location;
import shared.HRActions;
import shared.IHRActions;
import storage.IStore;
import storage.Logger;

class TestBackEnd {
	
	private String MAIN_TREE_FOLDER = 
			"/home/winterhart/DEV/SOEN423/java-rmi-simulation/storage/";

	@Test
	void LoginTest() {
		IStore storingEngine = new Logger("CA", MAIN_TREE_FOLDER + "CA" + "/");
		IHRActions actions = new HRActions(storingEngine);
		
		boolean success = actions.managerLogin("CA3841");
		if(!success) {
			fail("Can't login");
		}
		
		
	}
	
	@Test
	void CreateManagerTest() {
		IStore storingEngine = new Logger("CA", MAIN_TREE_FOLDER + "CA" + "/");
		IHRActions actions = new HRActions(storingEngine);
		Project p = new Project("P00222", "TestClient", "TestProjName");
		Project p2 = new Project("P00221", "TestClient2", "TestProjName2");
		HrCenterApp.DEMSPackage.Location loc = new HrCenterApp.DEMSPackage.Location("CA");
		Project[] projects = {p, p2};
		String message = actions.createMRecord("SuperTest", "BBo", "MR10342"
				, "goade@gma.com", projects, loc , "CA3841");
		System.out.println(message);
		
		
	}

}
