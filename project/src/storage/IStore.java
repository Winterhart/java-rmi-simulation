package storage;

import model.Project;
import model.Record;

public interface IStore {

	public void writeLog(String message, String fileName);
	
	public String getStorageName();
	
	public void addRecord(Record record);
	
	public void addProject(Project project);
	
	public void updateRecordField(String recordId, String fieldName, String newVal);
	
	public String readAllProject();
	
	public String readAllRecord();

	public void removeRecord(Record mrecord);

	public void removeProject(Project proj);
	
	
}
