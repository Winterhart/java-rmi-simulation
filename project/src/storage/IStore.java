package storage;

public interface IStore {

	public void writeToFile(String message);
	
	public String printAllFile();
}
