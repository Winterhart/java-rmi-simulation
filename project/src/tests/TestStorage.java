package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Employee;
import model.Manager;
import model.Project;
import model.Record;
import storage.IStore;
import storage.Logger;
import model.Location;

class TestStorage {

	@Test
	void test() {
		String MAIN_TREE_FOLDER = 
				"/home/winterhart/DEV/SOEN423/java-rmi-simulation/storage/";
		IStore storingEngine = new Logger("CA", MAIN_TREE_FOLDER + "CA" + "/");
		
		
		Record rec1 = new Record("ER23933");
		Employee emp = new Employee("Vivian", 
				"Bobinson", "ER45453", "Vivi@vlv.com", "P2222");
		
		Project proj = new Project("P2222", "SuperClient", "AnotherBlockChain");
		Project proj2 = new Project("P2344", "SuperClient2", "AnotherBlockChain2");
		List<Project> projList = new ArrayList<Project>();
		
		projList.add(proj);
		projList.add(proj2);
		
		Manager mana = new Manager("MR33232", "CA3333", "Micheal", "Scott", 
				"DataMike@dunderM.com", projList, Location.CA);
		
		storingEngine.addProject(proj);
		storingEngine.addProject(proj2);
		
		List<Project> projFromStorage = storingEngine.restoreProject();
		if(!projFromStorage.contains(proj)) {
			fail("No able to restore project");
		}
		
		storingEngine.addRecord(rec1);
		storingEngine.addRecord(mana);
		storingEngine.addRecord(emp);
		
		List<Record> recordFromStorage = storingEngine.restoreRecord();
		
		if(!recordFromStorage.contains(mana)) {
			fail("No able to restore form storage");
		}
		
		

	}

}
