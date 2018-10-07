package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import model.Employee;
import model.Location;
import model.Manager;
import model.Project;
import model.Record;

import java.io.IOException;

public class Logger implements IStore {
	
	String storeName;
	private String currentTargetFolder;
	private String DEFAULT_RECORD_FILE_NAME = "Records.txt";
	private String DEFAULT_PROJECT_FILE_NAME = "Projects.txt";
	public Logger(String name, String mainFolder) {
		this.storeName = name;
		this.currentTargetFolder = mainFolder;
	}

	@Override
	public void writeLog(String message, String fileName) {
		
		Date date = new Date();
		try {
			FileWriter writer = new FileWriter(currentTargetFolder +  fileName, true);
			BufferedWriter bWriter = new BufferedWriter(writer);
			bWriter.append(message);
			bWriter.append("    who: " + storeName);
			bWriter.append("   when: " + date.toString());
			bWriter.newLine();
			bWriter.close();
			writer.close();
		}catch(IOException ee) {
			ee.printStackTrace();
		}
		
	}

	@Override
	public String getStorageName() {
		return this.storeName;
	}

	@Override
	public void addRecord(Record record) {
		Date date = new Date();
		try {
			FileWriter writer = new FileWriter(currentTargetFolder + "/" + DEFAULT_RECORD_FILE_NAME,   true);
			BufferedWriter bWriter = new BufferedWriter(writer);
			bWriter.append(record.toString());
			bWriter.newLine();
			bWriter.close();
			writer.close();
			
		}catch(IOException ee) {
			ee.printStackTrace();
		}
		
	}

	@Override
	public void addProject(Project project) {
		Date date = new Date();
		try {
			FileWriter writer = new FileWriter(currentTargetFolder + "/" + DEFAULT_PROJECT_FILE_NAME,   true);
			BufferedWriter bWriter = new BufferedWriter(writer);
			bWriter.append(project.toString());
			bWriter.newLine();
			bWriter.close();
			writer.close();
			
		}catch(IOException ee) {
			ee.printStackTrace();
		}
		
	}

	@Override
	public String readAllProject() {
		try {
			FileReader reader = new FileReader(currentTargetFolder + "/" + DEFAULT_PROJECT_FILE_NAME);
			BufferedReader bReader = new BufferedReader(reader);
			StringBuffer buff = new StringBuffer();
			String line;
			while( (line = bReader.readLine()) != null) {
				if(line.contains("Project")) {
					buff.append(line);
				}				
			}				
			bReader.close();
			reader.close();
			return buff.toString();			
		}catch(IOException ee) {
			ee.printStackTrace();
			return "";
		}
	}

	@Override
	public String readAllRecord() {
		try {
			FileReader reader = new FileReader(currentTargetFolder + "/" + DEFAULT_RECORD_FILE_NAME);
			BufferedReader bReader = new BufferedReader(reader);
			StringBuffer buff = new StringBuffer();
			String line;
			while( (line = bReader.readLine()) != null) {
				if(line.contains("Record")) {
					buff.append(line);
				}				
			}					
			return buff.toString();			
		}catch(IOException ee) {
			ee.printStackTrace();
			return "";
		}
	}

	@Override
	public void removeRecord(Record mrecord) {
		int lineNumberToRemove = findRecord(mrecord);
		if(lineNumberToRemove == 0) {
			return;
		}
		try {
			FileReader reader = new FileReader(currentTargetFolder + "/" + DEFAULT_RECORD_FILE_NAME);
			BufferedReader bReader = new BufferedReader(reader);
			int index = 0;
			String line;
			String allFile = "";
			while((line = bReader.readLine()) != null) {
				if(index == lineNumberToRemove) {
					line = "";
				}
				index++;
				allFile += line + '\n';
			}
			
			FileOutputStream output = new FileOutputStream(currentTargetFolder + "/" + DEFAULT_RECORD_FILE_NAME);
			output.write(allFile.getBytes());
			output.close();
			bReader.close();
			reader.close();
			
		}catch(IOException ee) {
			ee.printStackTrace();
		}
		
	}

	private int findRecord(Record mrecord) {
		try {
			FileReader reader = new FileReader(currentTargetFolder + "/" + DEFAULT_RECORD_FILE_NAME);
			BufferedReader bReader = new BufferedReader(reader);
			StringBuffer buff = new StringBuffer();
			String line;
			int lineNumber = 0;
			while( (line = bReader.readLine()) != null) {
				if(line.contains(mrecord.getRecordID())) {
					// We found the line
					bReader.close();
					reader.close();
					return lineNumber;
				}
				lineNumber++;
			}
			
			return 0;
		}catch(IOException ee) {
			ee.printStackTrace();
			return 0;
		}
	}

	@Override
	public void removeProject(Project proj) {
		int lineNumberToRemove = findProject(proj);
		if(lineNumberToRemove == 0) {
			return;
		}
		try {
			FileReader reader = new FileReader(currentTargetFolder + "/" + DEFAULT_PROJECT_FILE_NAME);
			BufferedReader bReader = new BufferedReader(reader);
			int index = 0;
			String line;
			String allFile = "";
			while((line = bReader.readLine()) != null) {
				if(index == lineNumberToRemove) {
					line = "";
				}
				index++;
				allFile += line + '\n';
			}
			
			FileOutputStream output = new FileOutputStream(currentTargetFolder + "/" + DEFAULT_PROJECT_FILE_NAME);
			output.write(allFile.getBytes());
			output.close();
			bReader.close();
			reader.close();
			
		}catch(IOException ee) {
			ee.printStackTrace();
		}
		
	}

	private int findProject(Project proj) {
		try {
			FileReader reader = new FileReader(currentTargetFolder + "/" + DEFAULT_PROJECT_FILE_NAME);
			BufferedReader bReader = new BufferedReader(reader);
			StringBuffer buff = new StringBuffer();
			String line;
			int lineNumber = 0;
			while( (line = bReader.readLine()) != null) {
				if(line.contains(proj.getProjectID())) {
					// We found the line
					bReader.close();
					reader.close();
					return lineNumber;
				}
				lineNumber++;
			}
			return 0;
		}catch(IOException ee) {
			ee.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Project> restoreProject() {
		List<Project> returningProjects = new ArrayList<Project>();
		String allProjects = readAllProject();
		String[] splittedProjects = allProjects.split("Project:");
		for(String proj: splittedProjects) {
			String[] projAttrib = proj.split("\\|");
			if(projAttrib != null && projAttrib.length >= 3) {
				// Created project object from storage
				Project projectCreated = new Project(
						projAttrib[0], projAttrib[1],projAttrib[2]);
				
				returningProjects.add(projectCreated);
						
			}
		}
		
		return returningProjects;
	}

	@Override
	public List<Record> restoreRecord() {
		
		List<Record> returningRecord = new ArrayList<Record>();
		String allRecord = readAllRecord();
		String[] splittedRecords = allRecord.split("Record:");
		for(String record: splittedRecords) {
			String[] recordAttrib = record.split("\\|");
			if(recordAttrib != null && recordAttrib.length > 0) {
				
				if(recordAttrib.length == 1 && !recordAttrib[0].isEmpty()) {
					// It's a Record
					Record rec = new Record(recordAttrib[0]);
					returningRecord.add(rec);
				}else if(recordAttrib.length == 5) {
					//It's an employee
					Employee emp = new Employee(recordAttrib[2], recordAttrib[0], recordAttrib[1],
							recordAttrib[3], recordAttrib[4]);
					returningRecord.add(emp);
				}else if(recordAttrib.length == 7) {
					// It's a Manager
					List<Project> projects = restoreProject();
					List<Project> managerProj = new ArrayList<Project>();
					String[] projectIds = recordAttrib[5].split(",");
					List<String> convertedProjects = Arrays.asList(projectIds);
					for(Project prj: projects) {
						
						if(convertedProjects.contains(prj.getProjectID())) {
							managerProj.add(prj);
						}
					}
					// Find location
					String loca = recordAttrib[6];
					Location targetLocation = null;
					for(Location loc: Location.values()) {
						if(loca.equals(loc.toString())) {
							targetLocation = loc;
						}
					}
					Manager manager = new Manager(recordAttrib[0], recordAttrib[1], recordAttrib[2],
							recordAttrib[3], recordAttrib[4], managerProj, targetLocation);
					returningRecord.add(manager);
				}else {
					writeLog("Problem restoring record with  " + record.toString(), "Log.txt");
				}
				
				
			}
			
		}
		
		return returningRecord;
	}
	
	
	
	
	
	
	

}
