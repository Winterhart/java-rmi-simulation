package storage;

public interface IStore {

	public void writeLog(String message, String fileName);
	
	public String printAllFile();
}
