package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

import model.Project;
import model.Record;

import java.io.IOException;

public class Logger implements IStore {
	
	private String LoggerName;
	private String currentTargetFolder;
	
	public Logger(String name, String mainFolder) {
		this.LoggerName = name;
		this.currentTargetFolder = mainFolder;
	}

	@Override
	public void writeLog(String message, String fileName) {
		
		Date date = new Date();
		try {
			FileWriter writer = new FileWriter(currentTargetFolder +  fileName, true);
			BufferedWriter bWriter = new BufferedWriter(writer);
			bWriter.append(message);
			bWriter.append("    who: " + LoggerName);
			bWriter.append("   when: " + date.toString());
			bWriter.newLine();
			bWriter.close();
			
		}catch(IOException ee) {
			ee.printStackTrace();
		}
		
	}

	@Override
	public String getStorageName() {
		return this.LoggerName;
	}

	@Override
	public void addRecord(Record record) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProject(Project project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRecordField(String recordId, String fieldName, String newVal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String readAllProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readAllRecord() {
		// TODO Auto-generated method stub
		return null;
	}

}
