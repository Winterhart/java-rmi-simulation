package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import shared.HRActions;
import shared.IHRActions;
import storage.IStore;
import storage.Logger;

class TestLoginBackEnd {
	
	private String MAIN_TREE_FOLDER = 
			"/home/winterhart/DEV/SOEN423/java-rmi-simulation/storage/";
	
	private String CENTRAL_REPO_LOCATION =
			"/home/winterhart/DEV/SOEN423/java-rmi-simulation/storage/CENTRAL/";
	@Test
	void test() {
		IStore storingEngine = new Logger("CA", MAIN_TREE_FOLDER + "CA" + "/");
		IHRActions actions = new HRActions(storingEngine);
		
		boolean success = actions.managerLogin("CA3841");
		if(!success) {
			fail("Can't login");
		}
		
		
	}

}
