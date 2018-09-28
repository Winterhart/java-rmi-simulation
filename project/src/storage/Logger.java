package storage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Logger implements IStore {
	
	private String pathToFile = "/home/winterhart/DEV/SOEN423/java-rmi-simulation/storage";
	private Boolean APPEND_FILE = true;
	private String LoggerName;
	
	public Logger(String name) {
		this.LoggerName = name;
	}
	
	@Override
	public void writeToFile(String message) {
		try {
			FileWriter writer = new FileWriter(pathToFile + "/"+ LoggerName +".txt", APPEND_FILE);
			PrintWriter lineWriter =  new PrintWriter(writer);
			lineWriter.printf("%s" + "%n", message);
			lineWriter.close();
			
		}catch(IOException ee) {
			ee.printStackTrace();
		}

		
		
	}

	@Override
	public String printAllFile() {
		// TODO Auto-generated method stub
		return null;
	}

}
