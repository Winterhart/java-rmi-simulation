package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

import model.Project;
import model.Record;

import java.io.IOException;

public class Logger implements IStore {
	
	private String LoggerName;
	private String currentTargetFolder;
	private String DEFAULT_RECORD_FILE_NAME = "Records.txt";
	private String DEFAULT_PROJECT_FILE_NAME = "Projects.txt";
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
			writer.close();
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
		Date date = new Date();
		try {
			FileWriter writer = new FileWriter(currentTargetFolder + "/" + DEFAULT_RECORD_FILE_NAME,   true);
			BufferedWriter bWriter = new BufferedWriter(writer);
			bWriter.append(record.toString());
			bWriter.append("    who: " + LoggerName);
			bWriter.append("   when: " + date.toString());
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
			bWriter.append("    who: " + LoggerName);
			bWriter.append("   when: " + date.toString());
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

}
